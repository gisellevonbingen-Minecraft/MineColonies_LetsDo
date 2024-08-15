package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.RoasterOpenTeachMessage;

public class RoasterCraftingModuleView extends CraftingModuleView
{
	public RoasterCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesLetsDo.network().sendToServer(new RoasterOpenTeachMessage(this));
	}

}
