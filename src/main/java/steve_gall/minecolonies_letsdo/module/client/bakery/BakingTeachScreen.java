package steve_gall.minecolonies_letsdo.module.client.bakery;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfy.bakery.core.recipe.BakingStationRecipe;
import steve_gall.minecolonies_compatibility.core.client.gui.TeachCraftingRecipeScreen;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.bakery.crafting.BakingRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.bakery.init.ModuleCraftingTypes;
import steve_gall.minecolonies_letsdo.module.common.bakery.menu.BakingTeachMenu;
import steve_gall.minecolonies_tweaks.api.common.crafting.ICustomizedRecipeStorage;

public class BakingTeachScreen extends TeachCraftingRecipeScreen<BakingTeachMenu, BakingStationRecipe>
{
	public static final ResourceLocation TEXTURE = MineColoniesLetsDo.rl("textures/gui/bakery_baking_teach.png");

	public BakingTeachScreen(BakingTeachMenu menu, Inventory inventory, Component title)
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
		return ModuleCraftingTypes.BAKING.get();
	}

	@Override
	protected ICustomizedRecipeStorage createRecipeStorage(BakingStationRecipe recipe, List<ItemStorage> input)
	{
		var resultContainer = this.menu.getResultContainer();
		var output = resultContainer.getItem(0);
		return new BakingRecipeStorage(recipe.getId(), input, output);
	}

}
