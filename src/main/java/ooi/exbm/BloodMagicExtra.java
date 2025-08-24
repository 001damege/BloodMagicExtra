package ooi.exbm;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ooi.exbm.common.init.InitCreativeTabs;
import ooi.exbm.common.init.InitItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BloodMagicExtra.MODID)
public class BloodMagicExtra {
    public static final String MODID = "exbm";
    public static final Logger LOGGER = LogManager.getLogger();

    public BloodMagicExtra() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        InitItems.DR.register(eventBus);
        InitCreativeTabs.DR.register(eventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ExBMConfig.COMMON_SPEC);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    @SubscribeEvent
    public void serverStarting(ServerStartingEvent event) {
    }
}
