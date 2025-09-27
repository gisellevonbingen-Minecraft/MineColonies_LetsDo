package steve_gall.minecolonies_letsdo.module.common.bakery;

import com.minecolonies.api.colony.buildings.ModBuildings;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;
import steve_gall.minecolonies_letsdo.module.client.bakery.BakingTeachScreen;
import steve_gall.minecolonies_letsdo.module.common.bakery.crafting.BakingRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.bakery.init.ModuleBuildingModules;
import steve_gall.minecolonies_letsdo.module.common.bakery.init.ModuleCraftingTypes;
import steve_gall.minecolonies_letsdo.module.common.bakery.init.ModuleMenuTypes;
import steve_gall.minecolonies_letsdo.module.common.bakery.network.BakingOpenTeachMessage;
import steve_gall.minecolonies_tweaks.api.common.crafting.CustomizedRecipeStorageRegistry;
import steve_gall.minecolonies_tweaks.api.common.network.MessageRegistrar;

public class BakeryModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

		var fml_bus = ModLoadingContext.get().getActiveContainer().getEventBus();
		ModuleCraftingTypes.REGISTER.register(fml_bus);
		ModuleMenuTypes.REGISTER.register(fml_bus);

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
	protected void onRegisterMenuScreens(RegisterMenuScreensEvent e)
	{
		super.onRegisterMenuScreens(e);

		e.register(ModuleMenuTypes.BAKING_TEACH.get(), BakingTeachScreen::new);
	}

	@Override
	protected void onRegisterNetwork(MessageRegistrar channel)
	{
		super.onRegisterNetwork(channel);

		channel.playToServer(BakingOpenTeachMessage.TYPE, BakingOpenTeachMessage::new);
	}

}
