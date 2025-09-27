package steve_gall.minecolonies_letsdo.module.common.bakery.crafting;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.satisfy.bakery.core.recipe.BakingStationRecipe;
import net.satisfy.bakery.core.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleGenericRecipe;

public class BakingGenericRecipe extends SimpleGenericRecipe
{
	public BakingGenericRecipe(RecipeHolder<BakingStationRecipe> recipe, HolderLookup.Provider provider)
	{
		super(recipe, provider);
	}

	public BakingGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, ItemStack output)
	{
		super(recipeId, ingredients, output);
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ObjectRegistry.BAKER_STATION.get();
	}

}
