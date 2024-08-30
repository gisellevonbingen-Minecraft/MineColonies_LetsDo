package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.satisfy.farm_and_charm.recipe.SiloRecipe;
import net.satisfy.farm_and_charm.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleGenericRecipe;

public class SiloGenericRecipe extends SimpleGenericRecipe
{
	public SiloGenericRecipe(@NotNull SiloRecipe recipe, @NotNull RegistryAccess registryAccess)
	{
		super(recipe, registryAccess);
	}

	public SiloGenericRecipe(@NotNull ResourceLocation recipeId, @NotNull List<List<ItemStack>> ingredients, @NotNull ItemStack output)
	{
		super(recipeId, ingredients, output);
	}

	@Override
	public @NotNull Block getIntermediate()
	{
		return ObjectRegistry.SILO_WOOD.get();
	}

}
