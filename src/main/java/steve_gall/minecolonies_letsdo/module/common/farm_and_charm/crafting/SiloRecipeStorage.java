package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleRecipeStorage;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;

public class SiloRecipeStorage extends SimpleRecipeStorage<SiloGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesCompatibility.rl("farm_and_charm_silo");

	public SiloRecipeStorage(CompoundTag tag)
	{
		super(tag);
	}

	public SiloRecipeStorage(ResourceLocation recipeId, List<ItemStorage> ingredients, ItemStack output)
	{
		super(recipeId, ingredients, output);
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	protected GenericRecipeFactory<SiloGenericRecipe> getGenericRecipeFactory()
	{
		return SiloGenericRecipe::new;
	}

}
