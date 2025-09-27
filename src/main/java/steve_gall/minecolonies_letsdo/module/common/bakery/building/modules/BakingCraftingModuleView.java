package steve_gall.minecolonies_letsdo.module.common.bakery.building.modules;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import net.neoforged.neoforge.network.PacketDistributor;
import steve_gall.minecolonies_letsdo.module.common.bakery.network.BakingOpenTeachMessage;

public class BakingCraftingModuleView extends CraftingModuleView
{
	public BakingCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		PacketDistributor.sendToServer(new BakingOpenTeachMessage(this));
	}

}
