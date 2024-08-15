package steve_gall.minecolonies_letsdo.module.common.bakery.init;

import com.minecolonies.api.colony.buildings.registry.BuildingEntry;
import com.minecolonies.api.colony.jobs.ModJobs;

import steve_gall.minecolonies_letsdo.module.common.bakery.building.modules.BakingCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.bakery.building.modules.BakingCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.BowlCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.BowlCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.PotCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.PotCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.StoveCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.StoveCraftingModuleView;

public class ModuleBuildingModules
{
	public static final BuildingEntry.ModuleProducer<StoveCraftingModule, StoveCraftingModuleView> BAKER_STOVE = new BuildingEntry.ModuleProducer<>("baker_lets_do_bakery_stove", //
			() -> new StoveCraftingModule(ModJobs.baker.get(), true), //
			() -> StoveCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<BowlCraftingModule, BowlCraftingModuleView> BAKER_BOWL = new BuildingEntry.ModuleProducer<>("baker_lets_do_bakery_bowl", //
			() -> new BowlCraftingModule(ModJobs.baker.get(), true), //
			() -> BowlCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<PotCraftingModule, PotCraftingModuleView> BAKER_POT = new BuildingEntry.ModuleProducer<>("baker_lets_do_bakery_pot", //
			() -> new PotCraftingModule(ModJobs.baker.get(), true), //
			() -> PotCraftingModuleView::new);//

	public static final BuildingEntry.ModuleProducer<BakingCraftingModule, BakingCraftingModuleView> BAKER_BAKING = new BuildingEntry.ModuleProducer<>("baker_lets_do_bakery_baking", //
			() -> new BakingCraftingModule(ModJobs.baker.get()), //
			() -> BakingCraftingModuleView::new);//

	private ModuleBuildingModules()
	{

	}

}
