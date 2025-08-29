package ooi.exbm.common.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import ooi.exbm.BloodMagicExtra;
import ooi.exbm.common.init.InitItems;
import wayoftime.bloodmagic.altar.AltarTier;
import wayoftime.bloodmagic.common.data.recipe.BaseRecipeProvider;
import wayoftime.bloodmagic.common.data.recipe.builder.BloodAltarRecipeBuilder;
import wayoftime.bloodmagic.common.recipe.ISubRecipeProvider;

import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerators extends BaseRecipeProvider {
    public RecipeGenerators(PackOutput output) {
        super(output, BloodMagicExtra.MODID);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        super.buildRecipes(consumer);
    }

    @Override
    protected List<ISubRecipeProvider> getSubRecipeProviders() {
        return List.of(new BloodAltarRecipeGenerators());
    }

    private static class BloodAltarRecipeGenerators implements ISubRecipeProvider {
        @Override
        public void addRecipes(Consumer<FinishedRecipe> consumer) {
            String path = "altar/";

            BloodAltarRecipeBuilder.altar(Ingredient.of(Items.NETHERITE_SWORD), new ItemStack(InitItems.EXTRA_SACRIFICIAL_DAGGER.get()),
                    AltarTier.TWO.ordinal(), 5000, 5, 5).build(consumer, BloodMagicExtra.rl(path + "extra_sacrificial_dagger"));
        }
    }
}
