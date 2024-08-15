package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleContainerRecipeStorage;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;

public class PotRecipeStorage extends SimpleContainerRecipeStorage<PotGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesLetsDo.rl("farm_and_charm_pot");

	public PotRecipeStorage(CompoundTag tag)
	{
		super(tag);
	}

	public PotRecipeStorage(ResourceLocation recipeId, List<ItemStorage> ingredients, ItemStorage container, ItemStack output)
	{
		super(recipeId, ingredients, container, output);
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	protected ContainerGenericRecipeFactory<PotGenericRecipe> getContainerGenericRecipeFactory()
	{
		return PotGenericRecipe::new;
	}

}
