package steve_gall.minecolonies_letsdo.module.client.farm_and_charm;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.satisfy.farm_and_charm.core.recipe.MincerRecipe;
import steve_gall.minecolonies_compatibility.core.client.gui.TeachCraftingRecipeScreen;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.MincerRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleCraftingTypes;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.MincerTeachMenu;
import steve_gall.minecolonies_tweaks.api.common.crafting.ICustomizedRecipeStorage;

public class MincerTeachScreen extends TeachCraftingRecipeScreen<MincerTeachMenu, RecipeHolder<MincerRecipe>>
{
	public static final ResourceLocation TEXTURE = MineColoniesLetsDo.rl("textures/gui/farm_and_charm_mincer_teach.png");

	public MincerTeachScreen(MincerTeachMenu menu, Inventory inventory, Component title)
	{
		super(menu, inventory, title);

		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public ResourceLocation getTexture()
	{
		return TEXTURE;
	}

	@Override
	public CraftingType getCraftingType()
	{
		return ModuleCraftingTypes.MINCER.get();
	}

	@Override
	protected ICustomizedRecipeStorage createRecipeStorage(RecipeHolder<MincerRecipe> recipe, List<ItemStorage> input)
	{
		var resultContainer = this.menu.getResultContainer();
		var output = resultContainer.getItem(0);
		return new MincerRecipeStorage(recipe.id(), input, output);
	}

}
