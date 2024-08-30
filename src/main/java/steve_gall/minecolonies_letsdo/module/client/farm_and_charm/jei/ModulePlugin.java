package steve_gall.minecolonies_letsdo.module.client.farm_and_charm.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.resources.ResourceLocation;
import net.satisfy.farm_and_charm.compat.jei.category.CookingPotCategory;
import net.satisfy.farm_and_charm.compat.jei.category.CraftingBowlCategory;
import net.satisfy.farm_and_charm.compat.jei.category.MincerCategory;
import net.satisfy.farm_and_charm.compat.jei.category.RoasterCategory;
import net.satisfy.farm_and_charm.compat.jei.category.SiloCategory;
import net.satisfy.farm_and_charm.compat.jei.category.StoveCategory;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.BowlTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.MincerTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.PotTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.RoasterTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.SiloTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.StoveTeachScreen;
import steve_gall.minecolonies_letsdo.module.common.ModuleManager;

@JeiPlugin
public class ModulePlugin implements IModPlugin
{
	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		if (!ModuleManager.FARM_AND_CHARM.isLoaded())
		{
			return;
		}

		registration.addRecipeClickArea(StoveTeachScreen.class, 91, 26, 22, 15, StoveCategory.STOVE);
		registration.addRecipeClickArea(BowlTeachScreen.class, 91, 35, 22, 15, CraftingBowlCategory.DOUGHING);
		registration.addRecipeClickArea(PotTeachScreen.class, 91, 26, 22, 15, CookingPotCategory.COOKING_POT);
		registration.addRecipeClickArea(MincerTeachScreen.class, 77, 26, 22, 15, MincerCategory.MINCING_TYPE);
		registration.addRecipeClickArea(RoasterTeachScreen.class, 91, 26, 22, 15, RoasterCategory.ROASTER);
		registration.addRecipeClickArea(SiloTeachScreen.class, 77, 26, 22, 15, SiloCategory.DRYING_TYPE);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		if (!ModuleManager.FARM_AND_CHARM.isLoaded())
		{
			return;
		}

		var transferHelper = registration.getTransferHelper();
		registration.addRecipeTransferHandler(new StoveTeachRecipeTransferHandler(transferHelper), StoveCategory.STOVE);
		registration.addRecipeTransferHandler(new BowlTeachRecipeTransferHandler(transferHelper), CraftingBowlCategory.DOUGHING);
		registration.addRecipeTransferHandler(new PotTeachRecipeTransferHandler(transferHelper), CookingPotCategory.COOKING_POT);
		registration.addRecipeTransferHandler(new MincerTeachRecipeTransferHandler(transferHelper), MincerCategory.MINCING_TYPE);
		registration.addRecipeTransferHandler(new RoasterTeachRecipeTransferHandler(transferHelper), RoasterCategory.ROASTER);
		registration.addRecipeTransferHandler(new SiloTeachRecipeTransferHandler(transferHelper), SiloCategory.DRYING_TYPE);
	}

	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesLetsDo.rl(ModuleManager.FARM_AND_CHARM.getModId());
	}

}
