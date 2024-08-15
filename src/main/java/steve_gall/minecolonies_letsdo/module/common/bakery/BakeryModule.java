package steve_gall.minecolonies_letsdo.module.common.bakery;

import com.minecolonies.api.colony.buildings.ModBuildings;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.client.bakery.BakingTeachScreen;
import steve_gall.minecolonies_letsdo.module.common.AbstractModule;
import steve_gall.minecolonies_letsdo.module.common.bakery.crafting.BakingRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.bakery.init.ModuleBuildingModules;
import steve_gall.minecolonies_letsdo.module.common.bakery.init.ModuleCraftingTypes;
import steve_gall.minecolonies_letsdo.module.common.bakery.init.ModuleMenuTypes;
import steve_gall.minecolonies_letsdo.module.common.bakery.network.BakingOpenTeachMessage;
import steve_gall.minecolonies_tweaks.api.common.crafting.CustomizedRecipeStorageRegistry;

public class BakeryModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

		var fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModuleCraftingTypes.REGISTER.register(fml_bus);
		ModuleMenuTypes.REGISTER.register(fml_bus);

		var network = MineColoniesLetsDo.network();
		network.registerMessage(BakingOpenTeachMessage.class, BakingOpenTeachMessage::new);

		CustomizedRecipeStorageRegistry.INSTANCE.register(BakingRecipeStorage.ID, BakingRecipeStorage::serialize, BakingRecipeStorage::new);
	}

	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			ModBuildings.bakery.get().getModuleProducers().add(ModuleBuildingModules.BAKER_STOVE);
			ModBuildings.bakery.get().getModuleProducers().add(ModuleBuildingModules.BAKER_BOWL);
			ModBuildings.bakery.get().getModuleProducers().add(ModuleBuildingModules.BAKER_POT);
			ModBuildings.bakery.get().getModuleProducers().add(ModuleBuildingModules.BAKER_BAKING);
		});
	}

	@Override
	protected void onFMLClientSetup(FMLClientSetupEvent e)
	{
		super.onFMLClientSetup(e);

		MenuScreens.register(ModuleMenuTypes.BAKING_TEACH.get(), BakingTeachScreen::new);
	}

}
