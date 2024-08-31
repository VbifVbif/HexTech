package mod.vbif.hextech;

import com.mojang.logging.LogUtils;
import mod.vbif.hextech.item.ItemsHexTech;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(HexTech.MOD_ID)
public class HexTech
{
    // Определяем ID мода в общем месте, чтобы его могли использовать все
    public static final String MOD_ID = "hextech";
    // Прямо ссылаемся на логгер slf4j
    private static final Logger LOGGER = LogUtils.getLogger();
    // Создаем отложенный регистр для хранения блоков, которые будут зарегистрированы под пространством имен "hextech"
//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
   public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // Создаем новый блок с ID "hextech:example_block", объединяя пространство имен и путь
//    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    // Создаем новый блок-предмет с ID "hextech:example_block", объединяя пространство имен и путь
//    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));


    // Создаем вкладку в креативном режиме с ID "hextech:example_tab" для примерного предмета, которая будет располагаться после вкладки "боевые предметы"
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemsHexTech.HEX.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemsHexTech.HEX.get());
                output.accept(ItemsHexTech.EXAMPLE_ITEM1.get());
            }).build());

    public HexTech()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ItemsHexTech.register(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);

        // Регистрируем себя для серверных и других игровых событий, которые нас интересуют
        MinecraftForge.EVENT_BUS.register(this);

        // Регистрируем предмет во вкладке креативного режима
        modEventBus.addListener(this::addCreative);

        // Регистрируем конфигурацию мода, чтобы Forge мог создать и загрузить файл конфигурации для нас
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Некоторый общий код настройки
        LOGGER.info("HELLO FROM COMMON SETUP");

        // Если параметр logDirtBlock установлен в true, логируем информацию о блоке грязи
        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        // Логируем введение к magicNumber и его значение
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        // Логируем информацию о каждом элементе в списке items из конфигурации
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Добавляем примерный блок-предмет во вкладку строительных блоков
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // Если вкладка равна строительным блокам, добавляем предмет в эту вкладку
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){}
//            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // Можно использовать SubscribeEvent и позволить шине событий обнаружить методы для вызова
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Делаем что-то при запуске сервера
        LOGGER.info("HELLO from server starting");
    }

    // Можно использовать EventBusSubscriber для автоматической регистрации всех статических методов в классе, аннотированном @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Некоторый клиентский код настройки
            LOGGER.info("HELLO FROM CLIENT SETUP");
            // Логируем имя пользователя Minecraft
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
