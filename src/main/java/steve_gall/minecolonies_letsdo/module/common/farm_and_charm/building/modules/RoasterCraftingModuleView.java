package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import net.neoforged.neoforge.network.PacketDistributor;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.RoasterOpenTeachMessage;

public class RoasterCraftingModuleView extends CraftingModuleView
{
	public RoasterCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		PacketDistributor.sendToServer(new RoasterOpenTeachMessage(this));
	}

}
