package steve_gall.minecolonies_letsdo.module.client.farm_and_charm;

import java.util.List;

import com.minecolonies.api.crafting.ItemStorage;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.satisfy.farm_and_charm.recipe.SiloRecipe;
import steve_gall.minecolonies_compatibility.core.client.gui.TeachRecipeScreen;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.SiloRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.SiloTeachMenu;
import steve_gall.minecolonies_tweaks.api.common.crafting.ICustomizedRecipeStorage;

public class SiloTeachScreen extends TeachRecipeScreen<SiloTeachMenu, SiloRecipe>
{
	public static final ResourceLocation TEXTURE = MineColoniesLetsDo.rl("textures/gui/farm_and_charm_silo_teach.png");

	public SiloTeachScreen(SiloTeachMenu menu, Inventory inventory, Component title)
	{
		super(menu, inventory, title);

		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	protected ICustomizedRecipeStorage createRecipeStorage(SiloRecipe recipe, List<ItemStorage> input)
	{
		var resultContainer = this.menu.getResultContainer();
		var output = resultContainer.getItem(0);
		return new SiloRecipeStorage(recipe.getId(), input, output);
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks)
	{
		this.renderBackground(graphics);

		super.render(graphics, mouseX, mouseY, partialTicks);

		this.renderTooltip(graphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY)
	{
		graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

}
