package ooi.exbm.common.data;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import ooi.exbm.BloodMagicExtra;
import ooi.exbm.common.init.InitItems;

public class LanguageGenerators extends LanguageProvider {
    protected LanguageGenerators(PackOutput output) {
        super(output, BloodMagicExtra.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItem(InitItems.EXTRA_SACRIFICIAL_DAGGER, "Extra Sacrificial Knife");
    }
}
