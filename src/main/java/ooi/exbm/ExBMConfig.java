package ooi.exbm;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = BloodMagicExtra.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExBMConfig {
    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.IntValue daggerConversion;
        public final ForgeConfigSpec.BooleanValue daggerFakePlayer;

        CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Amount of LP the Sacrificial Dagger should provide for each damage dealt.").push("Extra Dagger Setting");
            daggerConversion = builder.defineInRange("daggerConversion", 500, 0, 50000);

            builder.comment("Enable daggers for use by fake players");
            daggerFakePlayer = builder.define("daggerFakePlayer", false);
            builder.pop();
        }
    }
}
