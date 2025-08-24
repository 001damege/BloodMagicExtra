package ooi.exbm.common.init;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ooi.exbm.BloodMagicExtra;
import ooi.exbm.common.item.ItemExtraSacrificialDagger;

@SuppressWarnings("unused")
public final class InitItems {
    public static final DeferredRegister<Item> DR = DeferredRegister.create(ForgeRegistries.ITEMS, BloodMagicExtra.MODID);

    public static final RegistryObject<ItemExtraSacrificialDagger> EXTRA_SACRIFICIAL_DAGGER = DR.register("extra_sacrificial_dagger", () -> new ItemExtraSacrificialDagger(new Item.Properties().stacksTo(1)));

}
