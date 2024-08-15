package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleRecipeStorage;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;

public class MincerRecipeStorage extends SimpleRecipeStorage<MincerGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesLetsDo.rl("farm_and_charm_mincer");

	public MincerRecipeStorage(CompoundTag tag)
	{
		super(tag);
	}

	public MincerRecipeStorage(ResourceLocation recipeId, List<ItemStorage> ingredients, ItemStack output)
	{
		super(recipeId, ingredients, output);
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	protected GenericRecipeFactory<MincerGenericRecipe> getGenericRecipeFactory()
	{
		return MincerGenericRecipe::new;
	}

}
