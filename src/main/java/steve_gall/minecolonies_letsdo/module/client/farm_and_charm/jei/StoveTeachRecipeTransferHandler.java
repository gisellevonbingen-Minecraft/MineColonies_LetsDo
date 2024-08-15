package steve_gall.minecolonies_letsdo.module.client.farm_and_charm.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.satisfy.farm_and_charm.compat.jei.category.StoveCategory;
import net.satisfy.farm_and_charm.recipe.StoveRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.StoveTeachMenu;

public class StoveTeachRecipeTransferHandler extends TeachRecipeTransferHandler<StoveTeachMenu, StoveRecipe, StoveRecipe>
{
	public StoveTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends StoveTeachMenu> getContainerClass()
	{
		return StoveTeachMenu.class;
	}

	@Override
	public Optional<MenuType<StoveTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<StoveRecipe> getRecipeType()
	{
		return StoveCategory.STOVE;
	}

	@Override
	protected StoveRecipe getRecipe(StoveTeachMenu menu, StoveRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(StoveTeachMenu menu, StoveRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
