package steve_gall.minecolonies_letsdo.module.client.bakery.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.bakery.compat.jei.category.BakerStationCategory;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.client.bakery.BakingTeachScreen;
import steve_gall.minecolonies_letsdo.module.common.ModuleManager;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.BAKERY.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(BakingTeachScreen.class, 91, 26, 22, 15, BakerStationCategory.CAKING);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.BAKERY.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new BakingTeachRecipeTransferHandler(transferHelper), BakerStationCategory.CAKING);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesLetsDo.rl(ModuleManager.BAKERY.getModId());
	}

}
