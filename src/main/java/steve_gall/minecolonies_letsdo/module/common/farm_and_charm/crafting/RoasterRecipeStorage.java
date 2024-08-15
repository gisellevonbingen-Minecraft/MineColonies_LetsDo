package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleContainerRecipeStorage;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;

public class RoasterRecipeStorage extends SimpleContainerRecipeStorage<RoasterGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesLetsDo.rl("farm_and_charm_roaster");

	public RoasterRecipeStorage(CompoundTag tag)
	{
		super(tag);
	}

	public RoasterRecipeStorage(ResourceLocation recipeId, List<ItemStorage> ingredients, ItemStorage container, ItemStack output)
	{
		super(recipeId, ingredients, container, output);
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	protected ContainerGenericRecipeFactory<RoasterGenericRecipe> getContainerGenericRecipeFactory()
	{
		return RoasterGenericRecipe::new;
	}

}
