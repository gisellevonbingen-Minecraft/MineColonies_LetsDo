package steve_gall.minecolonies_letsdo.module.client.bakery.jei;

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
import net.satisfy.bakery.core.compat.jei.category.BakerStationCategory;
import net.satisfy.bakery.core.recipe.BakingStationRecipe;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.bakery.menu.BakingTeachMenu;
import steve_gall.minecolonies_tweaks.core.common.item.ItemSerializationHelper;

public class BakingTeachRecipeTransferHandler extends TeachRecipeTransferHandler<BakingTeachMenu, RecipeHolder<BakingStationRecipe>, RecipeInput, BakingStationRecipe>
{
	private static final ResourceLocation HOLDER_NAME = MineColoniesLetsDo.rl("dummy");

	public BakingTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends BakingTeachMenu> getContainerClass()
	{
		return BakingTeachMenu.class;
	}

	@Override
	public Optional<MenuType<BakingTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<BakingStationRecipe> getRecipeType()
	{
		return BakerStationCategory.CAKING;
	}

	@Override
	protected RecipeHolder<BakingStationRecipe> getRecipe(BakingTeachMenu menu, BakingStationRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return new RecipeHolder<>(HOLDER_NAME, categoryRecipe);
	}

	@Override
	protected void serializePayload(BakingTeachMenu menu, RecipeHolder<BakingStationRecipe> recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		NBTUtils2.serializeCollection(tag, "input", input, ItemSerializationHelper.serializerTag(player.registryAccess()));
	}

}
