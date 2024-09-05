package mod.vbif.hextech.item;


import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Gloves extends SwordItem {

    private long lastSpecialAttackTime = 0;
    private final long cooldown = 3000;

    public Gloves(Tier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpecialAttackTime >= cooldown) {
            lastSpecialAttackTime = currentTime;

            Vec3 lookVec = player.getLookAngle();
            Vec3 startPos = player.position().add(0, player.getEyeHeight(), 0);
            Vec3 endPos = startPos.add(lookVec.scale(5));

            AABB aabb = new AABB(startPos, endPos);
            List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, aabb);

            for (LivingEntity entity : entities) {
                if (entity != player) {
                    entity.hurt(player.damageSources().playerAttack(player), 10.0F);
                }
            }

            player.getCooldowns().addCooldown(this, (int) (cooldown / 50));
        }

        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }
}
