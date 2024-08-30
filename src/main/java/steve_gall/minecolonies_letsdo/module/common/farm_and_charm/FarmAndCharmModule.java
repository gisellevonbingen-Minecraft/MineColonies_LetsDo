package steve_gall.minecolonies_letsdo.module.common.farm_and_charm;

import com.minecolonies.api.colony.buildings.ModBuildings;
import com.minecolonies.api.util.ItemStackUtils;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedCrop;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.BowlTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.MincerTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.PotTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.RoasterTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.SiloTeachScreen;
import steve_gall.minecolonies_letsdo.module.client.farm_and_charm.StoveTeachScreen;
import steve_gall.minecolonies_letsdo.module.common.AbstractModule;
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

public class FarmAndCharmModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

		var fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModuleCraftingTypes.REGISTER.register(fml_bus);
		ModuleMenuTypes.REGISTER.register(fml_bus);

		var network = MineColoniesLetsDo.network();
		network.registerMessage(StoveOpenTeachMessage.class, StoveOpenTeachMessage::new);
		network.registerMessage(BowlOpenTeachMessage.class, BowlOpenTeachMessage::new);
		network.registerMessage(PotOpenTeachMessage.class, PotOpenTeachMessage::new);
		network.registerMessage(MincerOpenTeachMessage.class, MincerOpenTeachMessage::new);
		network.registerMessage(RoasterOpenTeachMessage.class, RoasterOpenTeachMessage::new);
		network.registerMessage(SiloOpenTeachMessage.class, SiloOpenTeachMessage::new);

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
	protected void onFMLClientSetup(FMLClientSetupEvent e)
	{
		super.onFMLClientSetup(e);
		MenuScreens.register(ModuleMenuTypes.STOVE_TEACH.get(), StoveTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.BOWL_TEACH.get(), BowlTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.POT_TEACH.get(), PotTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.MINCER_TEACH.get(), MincerTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.ROASTER_TEACH.get(), RoasterTeachScreen::new);
		MenuScreens.register(ModuleMenuTypes.SILO_TEACH.get(), SiloTeachScreen::new);
	}

	public static boolean isFromBakery(ItemStack output)
	{
		return output.is(ModuleTags.Items.BAKERY_OUTPUT) || ForgeRegistries.ITEMS.getKey(output.getItem()).getNamespace().equals(ModuleManager.BAKERY.getModId());
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
