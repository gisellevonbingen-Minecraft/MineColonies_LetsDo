package steve_gall.minecolonies_letsdo.module.client.bakery.jei;

import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.satisfy.bakery.compat.jei.category.BakerStationCategory;
import steve_gall.minecolonies_letsdo.module.client.bakery.BakingTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.jei.AbstractModulePlugin;
import steve_gall.minecolonies_letsdo.module.common.ModuleManager;
import steve_gall.minecolonies_letsdo.module.common.OptionalModule;

@JeiPlugin
public class ModulePlugin extends AbstractModulePlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!this.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(BakingTeachScreen.class, 91, 26, 22, 15, BakerStationCategory.CAKING);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!this.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new BakingTeachRecipeTransferHandler(transferHelper), BakerStationCategory.CAKING);
	}

	@Override
	public OptionalModule<?> getModule()
	{
		return ModuleManager.BAKERY;
	}

}
