package ooi.exbm.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import ooi.exbm.BloodMagicExtra;

public class InitCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> DR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BloodMagicExtra.MODID);

    public static final RegistryObject<CreativeModeTab> TAB = DR.register("tab", () -> CreativeModeTab.builder()
            .title(Component.literal("BloodMagic: Extra"))
            .icon(() -> new ItemStack(InitItems.EXTRA_SACRIFICIAL_DAGGER.get()))
            .displayItems((params, output) -> {
                for (var item : InitItems.DR.getEntries()) {
                    output.accept(item.get());
                }
            })
            .build());
}
