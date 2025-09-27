package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.satisfy.farm_and_charm.core.recipe.RoasterRecipe;
import net.satisfy.farm_and_charm.core.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleContainerGenericRecipe;

public class RoasterGenericRecipe extends SimpleContainerGenericRecipe
{
	public RoasterGenericRecipe(RecipeHolder<RoasterRecipe> recipe, HolderLookup.Provider provider)
	{
		super(recipe, Arrays.asList(recipe.value().getContainer()), provider);
	}

	public RoasterGenericRecipe(ResourceLocation recipeId, List<List<ItemStack>> ingredients, List<ItemStack> container, ItemStack output)
	{
		super(recipeId, ingredients, container, output);
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ObjectRegistry.ROASTER.get();
	}

}
