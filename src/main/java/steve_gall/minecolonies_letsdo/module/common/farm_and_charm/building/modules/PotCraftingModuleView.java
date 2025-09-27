package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import net.neoforged.neoforge.network.PacketDistributor;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.PotOpenTeachMessage;

public class PotCraftingModuleView extends FarmAndCharmCraftingModuleView
{
	@Override
	public void openCraftingGUI()
	{
		PacketDistributor.sendToServer(new PotOpenTeachMessage(this));
	}

}
