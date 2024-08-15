package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.BowlOpenTeachMessage;

public class BowlCraftingModuleView extends FarmAndCharmCraftingModuleView
{
	@Override
	public void openCraftingGUI()
	{
		MineColoniesLetsDo.network().sendToServer(new BowlOpenTeachMessage(this));
	}

}
