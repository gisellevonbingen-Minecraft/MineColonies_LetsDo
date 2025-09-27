package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network.MincerOpenTeachMessage;

public class MincerCraftingModuleView extends CraftingModuleView
{
	public MincerCraftingModuleView()
	{

	}

	@Override
	public void openCraftingGUI()
	{
		PacketDistributor.sendToServer(new MincerOpenTeachMessage(this));
	}

	public boolean isOutputCompatible(ItemStack output)
	{
		return true;
	}

}
