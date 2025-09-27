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
import net.satisfy.farm_and_charm.core.compat.jei.category.SiloCategory;
import net.satisfy.farm_and_charm.core.recipe.SiloRecipe;
import steve_gall.minecolonies_compatibility.module.client.jei.TeachRecipeTransferHandler;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.SiloTeachMenu;
import steve_gall.minecolonies_tweaks.core.common.item.ItemSerializationHelper;

public class SiloTeachRecipeTransferHandler extends TeachRecipeTransferHandler<SiloTeachMenu, RecipeHolder<SiloRecipe>, RecipeInput, SiloRecipe>
{
	private static final ResourceLocation HOLDER_NAME = MineColoniesLetsDo.rl("dummy");

	public SiloTeachRecipeTransferHandler(IRecipeTransferHandlerHelper recipeTransferHandlerHelper)
	{
		super(recipeTransferHandlerHelper);
	}

	@Override
	public Class<? extends SiloTeachMenu> getContainerClass()
	{
		return SiloTeachMenu.class;
	}

	@Override
	public Optional<MenuType<SiloTeachMenu>> getMenuType()
	{
		return Optional.empty();
	}

	@Override
	public RecipeType<SiloRecipe> getRecipeType()
	{
		return SiloCategory.DRYING_TYPE;
	}

	@Override
	protected RecipeHolder<SiloRecipe> getRecipe(SiloTeachMenu menu, SiloRecipe categoryRecipe, IRecipeSlotsView recipeSlots, Player player)
	{
		return new RecipeHolder<>(HOLDER_NAME, categoryRecipe);
	}

	@Override
	protected void serializePayload(SiloTeachMenu menu, RecipeHolder<SiloRecipe> recipe, IRecipeSlotsView recipeSlots, Player player, CompoundTag tag)
	{
		var input = this.getDisplayedItemStacks(recipeSlots, RecipeIngredientRole.INPUT);
		tag.put("input", ItemSerializationHelper.serializeTag(player.registryAccess(), input.get(0)));
	}

}
