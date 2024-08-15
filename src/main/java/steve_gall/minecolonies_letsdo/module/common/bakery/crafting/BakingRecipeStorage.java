package steve_gall.minecolonies_letsdo.module.common.bakery.crafting;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleRecipeStorage;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;

public class BakingRecipeStorage extends SimpleRecipeStorage<BakingGenericRecipe>
{
	public static final ResourceLocation ID = MineColoniesLetsDo.rl("bakery_baking");

	public BakingRecipeStorage(CompoundTag tag)
	{
		super(tag);
	}

	public BakingRecipeStorage(ResourceLocation recipeId, List<ItemStorage> ingredients, ItemStack output)
	{
		super(recipeId, ingredients, output);
	}

	@Override
	public ResourceLocation getId()
	{
		return ID;
	}

	@Override
	protected GenericRecipeFactory<BakingGenericRecipe> getGenericRecipeFactory()
	{
		return BakingGenericRecipe::new;
	}

}
