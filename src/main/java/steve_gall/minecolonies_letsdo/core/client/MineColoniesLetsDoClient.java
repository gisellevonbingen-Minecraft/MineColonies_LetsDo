package steve_gall.minecolonies_letsdo.core.client;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;

public class MineColoniesLetsDoClient
{
	public MineColoniesLetsDoClient()
	{
		var fml_bus = ModLoadingContext.get().getActiveContainer().getEventBus();
		var forge_bus = NeoForge.EVENT_BUS;
	}

}
