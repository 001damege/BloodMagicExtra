package ooi.exbm.common.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ooi.exbm.BloodMagicExtra;

@Mod.EventBusSubscriber(modid = BloodMagicExtra.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existing = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new LanguageGenerators(output));
        generator.addProvider(event.includeServer(), new RecipeGenerators(output));
    }
}
