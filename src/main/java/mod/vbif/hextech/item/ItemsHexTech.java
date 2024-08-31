package mod.vbif.hextech.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static mod.vbif.hextech.HexTech.MOD_ID;

public class ItemsHexTech {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> EXAMPLE_ITEM1 = ITEMS.register("example_item1", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));
    public static final RegistryObject<Item> HEX = ITEMS.register("hex", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build())));
    public static final RegistryObject<Item> HEX_DAGGER = ITEMS.register("hex_dagger",
            () -> new SwordItem(new Tier() {
                @Override
                public int getUses() {
                    return 250;
                }

                @Override
                public float getSpeed() {
                    return 6.0F;
                }

                @Override
                public float getAttackDamageBonus() {
                    return 3.0F;
                }

                @Override
                public int getLevel() {
                    return 2;
                }

                @Override
                public int getEnchantmentValue() {
                    return 14;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(Items.IRON_INGOT);
                }
            }, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> VIR = ITEMS.register("vir",
            () -> new SwordItem(new Tier() {
                @Override
                public int getUses() {
                    return 250;
                }

                @Override
                public float getSpeed() {
                    return 6.0F;
                }

                @Override
                public float getAttackDamageBonus() {
                    return 3.0F;
                }

                @Override
                public int getLevel() {
                    return 2;
                }

                @Override
                public int getEnchantmentValue() {
                    return 14;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(Items.IRON_INGOT);
                }
            }, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> VIL = ITEMS.register("vil",
            () -> new SwordItem(new Tier() {
                @Override
                public int getUses() {
                    return 250;
                }

                @Override
                public float getSpeed() {
                    return 6.0F;
                }

                @Override
                public float getAttackDamageBonus() {
                    return 3.0F;
                }

                @Override
                public int getLevel() {
                    return 2;
                }

                @Override
                public int getEnchantmentValue() {
                    return 14;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(Items.IRON_INGOT);
                }
            }, 3, -2.4F, new Item.Properties()));
}
