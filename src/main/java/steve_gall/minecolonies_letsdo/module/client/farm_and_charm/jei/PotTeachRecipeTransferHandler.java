package steve_gall.minecolonies_letsdo.module.client.farm_and_charm.jei;

import java.util.Optional;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.satisfy.farm_and_charm.core.compat.jei.category.CookingPotCategory;
import net.satisfy.farm_and_charm.core.recipe.CookingPotRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.PotTeachMenu;
import steve_gall.minecolonies_tweaks.core.common.item.ItemSerializationHelper;

public class PotTeachRecipeTransferHandler extends TeachRecipeTransferHandler<PotTeachMenu, RecipeHolder<CookingPotRecipe>, RecipeInput, CookingPotRecipe>
{
	private static final ResourceLocation HOLDER_NAME = MineColoniesLetsDo.rl("dummy");

	public PotTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends PotTeachMenu> getContainerClass()
	{
		return PotTeachMenu.class;
	}

	@Override
	public Optional<MenuType<PotTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<CookingPotRecipe> getRecipeType()
	{
		return CookingPotCategory.COOKING_POT;
	}

	@Override
	protected RecipeHolder<CookingPotRecipe> getRecipe(PotTeachMenu menu, CookingPotRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return new RecipeHolder<>(HOLDER_NAME, categoryRecipe);
	}

	@Override
	protected void serializePayload(PotTeachMenu menu, RecipeHolder<CookingPotRecipe> recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		input = input.subList(1, input.size());
		NBTUtils2.serializeCollection(tag, "input", input, ItemSerializationHelper.serializerTag(player.registryAccess()));
	}

}
