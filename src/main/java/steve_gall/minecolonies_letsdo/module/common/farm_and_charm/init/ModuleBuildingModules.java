package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.ModJobs;

import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.FarmAndCharmModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.BowlCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.BowlCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.MincerCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.MincerCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.PotCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.PotCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.RoasterCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.RoasterCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.StoveCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.StoveCraftingModuleView;

public class ModuleBuildingModules
{
	public static final BuildingEntry.ModuleProducer<StoveCraftingModule, StoveCraftingModuleView> CHEF_STOVE = new BuildingEntry.ModuleProducer<>("chef_farm_and_charm_stove", //
			() -> new StoveCraftingModule(ModJobs.chef.get(), false), //
			() -> StoveCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<BowlCraftingModule, BowlCraftingModuleView> CHEF_BOWL = new BuildingEntry.ModuleProducer<>("chef_lets_do_farm_and_charm_bowl", //
			() -> new BowlCraftingModule(ModJobs.chef.get(), false), //
			() -> BowlCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<PotCraftingModule, PotCraftingModuleView> CHEF_POT = new BuildingEntry.ModuleProducer<>("chef_lets_do_farm_and_charm_pot", //
			() -> new PotCraftingModule(ModJobs.chef.get(), false), //
			() -> PotCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<MincerCraftingModule, MincerCraftingModuleView> CHEF_MINCER = new BuildingEntry.ModuleProducer<>("chef_lets_do_farm_and_charm_mincer", //
			() -> new MincerCraftingModule(ModJobs.chef.get())
			{
				@Override
				public boolean isOutputCompatible(ItemStack output)
				{
					return FarmAndCharmModule.isChefMincerable(output);
				};
			}, //
			() -> () -> new MincerCraftingModuleView()
			{
				@Override
				public boolean isOutputCompatible(ItemStack output)
				{
					return FarmAndCharmModule.isChefMincerable(output);
				};

			});//

	public static final BuildingEntry.ModuleProducer<RoasterCraftingModule, RoasterCraftingModuleView> CHEF_ROASTER = new BuildingEntry.ModuleProducer<>("chef_lets_do_farm_and_charm_roaster", //
			() -> new RoasterCraftingModule(ModJobs.chef.get()), //
			() -> RoasterCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<MincerCraftingModule, MincerCraftingModuleView> FARMER_MINCER = new BuildingEntry.ModuleProducer<>("farmer_lets_do_farm_and_charm_mincer", //
			() -> new MincerCraftingModule(ModJobs.farmer.get())
			{
				@Override
				public boolean isOutputCompatible(ItemStack output)
				{
					return output.is(ModuleTags.Items.SEEDS);
				};
			}, //
			() -> () -> new MincerCraftingModuleView()
			{
				@Override
				public boolean isOutputCompatible(ItemStack output)
				{
					return output.is(ModuleTags.Items.SEEDS);
				};

			});//

	private ModuleBuildingModules()
	{

	}

}
