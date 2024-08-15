package steve_gall.minecolonies_letsdo.module.common.bakery.building.modules;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.bakery.network.BakingOpenTeachMessage;

public class BakingCraftingModuleView extends CraftingModuleView
{
	public BakingCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		MineColoniesLetsDo.network().sendToServer(new BakingOpenTeachMessage(this));
	}

}
