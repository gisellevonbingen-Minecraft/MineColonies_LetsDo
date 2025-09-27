package steve_gall.minecolonies_letsdo.module.common.farm_and_charm;

import com.minecolonies.api.colony.buildings.ModBuildings;
import com.minecolonies.api.util.ItemStackUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedCrop;
import steve_gall.minecolonies_compatibility.module.common.AbstractModule;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.BowlTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.MincerTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.PotTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.RoasterTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.SiloTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.StoveTeachScreen;
import steve_gall.minecolonies_letsdo.module.common.ModuleManager;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.BowlRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.MincerRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.PotRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.RoasterRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.SiloRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.StoveRecipeStorage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleBuildingModules;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleCraftingTypes;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleMenuTypes;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleTags;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.BowlOpenTeachMessage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.MincerOpenTeachMessage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.PotOpenTeachMessage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.RoasterOpenTeachMessage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.SiloOpenTeachMessage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.StoveOpenTeachMessage;
import steve_gall.minecolonies_tweaks.api.common.crafting.CustomizedRecipeStorageRegistry;
import steve_gall.minecolonies_tweaks.api.common.network.MessageRegistrar;

public class FarmAndCharmModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

		var fml_bus = ModLoadingContext.get().getActiveContainer().getEventBus();
		ModuleCraftingTypes.REGISTER.register(fml_bus);
		ModuleMenuTypes.REGISTER.register(fml_bus);

		CustomizedRecipeStorageRegistry.INSTANCE.register(StoveRecipeStorage.ID, StoveRecipeStorage::serialize, StoveRecipeStorage::new);
		CustomizedRecipeStorageRegistry.INSTANCE.register(BowlRecipeStorage.ID, BowlRecipeStorage::serialize, BowlRecipeStorage::new);
		CustomizedRecipeStorageRegistry.INSTANCE.register(PotRecipeStorage.ID, PotRecipeStorage::serialize, PotRecipeStorage::new);
		CustomizedRecipeStorageRegistry.INSTANCE.register(MincerRecipeStorage.ID, MincerRecipeStorage::serialize, MincerRecipeStorage::new);
		CustomizedRecipeStorageRegistry.INSTANCE.register(RoasterRecipeStorage.ID, RoasterRecipeStorage::serialize, RoasterRecipeStorage::new);
		CustomizedRecipeStorageRegistry.INSTANCE.register(SiloRecipeStorage.ID, SiloRecipeStorage::serialize, SiloRecipeStorage::new);
	}

	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			CustomizedCrop.register(new TomatoCrop());

			ModBuildings.kitchen.get().getModuleProducers().add(ModuleBuildingModules.CHEF_STOVE);
			ModBuildings.kitchen.get().getModuleProducers().add(ModuleBuildingModules.CHEF_BOWL);
			ModBuildings.kitchen.get().getModuleProducers().add(ModuleBuildingModules.CHEF_POT);
			ModBuildings.kitchen.get().getModuleProducers().add(ModuleBuildingModules.CHEF_MINCER);
			ModBuildings.kitchen.get().getModuleProducers().add(ModuleBuildingModules.CHEF_ROASTER);

			ModBuildings.farmer.get().getModuleProducers().add(ModuleBuildingModules.FARMER_MINCER);
			ModBuildings.farmer.get().getModuleProducers().add(ModuleBuildingModules.FARMER_SILO);
		});
	}

	@Override
	protected void onRegisterMenuScreens(RegisterMenuScreensEvent e)
	{
		super.onRegisterMenuScreens(e);

		e.register(ModuleMenuTypes.STOVE_TEACH.get(), StoveTeachScreen::new);
		e.register(ModuleMenuTypes.BOWL_TEACH.get(), BowlTeachScreen::new);
		e.register(ModuleMenuTypes.POT_TEACH.get(), PotTeachScreen::new);
		e.register(ModuleMenuTypes.MINCER_TEACH.get(), MincerTeachScreen::new);
		e.register(ModuleMenuTypes.ROASTER_TEACH.get(), RoasterTeachScreen::new);
		e.register(ModuleMenuTypes.SILO_TEACH.get(), SiloTeachScreen::new);
	}

	@Override
	protected void onRegisterNetwork(MessageRegistrar channel)
	{
		super.onRegisterNetwork(channel);

		channel.playToServer(StoveOpenTeachMessage.TYPE, StoveOpenTeachMessage::new);
		channel.playToServer(BowlOpenTeachMessage.TYPE, BowlOpenTeachMessage::new);
		channel.playToServer(PotOpenTeachMessage.TYPE, PotOpenTeachMessage::new);
		channel.playToServer(MincerOpenTeachMessage.TYPE, MincerOpenTeachMessage::new);
		channel.playToServer(RoasterOpenTeachMessage.TYPE, RoasterOpenTeachMessage::new);
		channel.playToServer(SiloOpenTeachMessage.TYPE, SiloOpenTeachMessage::new);
	}

	public static boolean isFromBakery(ItemStack output)
	{
		return output.is(ModuleTags.Items.BAKERY_OUTPUT) || BuiltInRegistries.ITEM.getKey(output.getItem()).getNamespace().equals(ModuleManager.BAKERY.getModId());
	}

	public static boolean testBakery(boolean isBakery, ItemStack output)
	{
		if (isBakery)
		{
			return isFromBakery(output);
		}
		else
		{
			return !isFromBakery(output);
		}

	}

	public static boolean isChefMincerable(ItemStack output)
	{
		return output.is(ModuleTags.Items.CHEF_MINCER_PRODUCT) || ItemStackUtils.ISFOOD.test(output);
	}

}
