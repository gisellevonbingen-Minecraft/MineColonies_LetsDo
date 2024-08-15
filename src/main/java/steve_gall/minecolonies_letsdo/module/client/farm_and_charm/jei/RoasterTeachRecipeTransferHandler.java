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
import net.satisfy.farm_and_charm.compat.jei.category.RoasterCategory;
import net.satisfy.farm_and_charm.recipe.RoasterRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.RoasterTeachMenu;

public class RoasterTeachRecipeTransferHandler extends TeachRecipeTransferHandler<RoasterTeachMenu, RoasterRecipe, RoasterRecipe>
{
	public RoasterTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends RoasterTeachMenu> getContainerClass()
	{
		return RoasterTeachMenu.class;
	}

	@Override
	public Optional<MenuType<RoasterTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<RoasterRecipe> getRecipeType()
	{
		return RoasterCategory.ROASTER;
	}

	@Override
	protected RoasterRecipe getRecipe(RoasterTeachMenu menu, RoasterRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return categoryRecipe;
	}

	@Override
	protected void serializePayload(RoasterTeachMenu menu, RoasterRecipe recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		input = input.subList(1, input.size());
		NBTUtils2.serializeCollection(tag, "input", input, ItemStack::serializeNBT);
	}

}
