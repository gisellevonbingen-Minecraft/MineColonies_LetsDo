package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.satisfy.farm_and_charm.recipe.RoasterRecipe;
import net.satisfy.farm_and_charm.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleContainerGenericRecipe;

public class RoasterGenericRecipe extends SimpleContainerGenericRecipe
{
	public RoasterGenericRecipe(RoasterRecipe recipe, RegistryAccess registryAccess)
	{
		super(recipe, Arrays.asList(recipe.getContainer()), registryAccess);
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
