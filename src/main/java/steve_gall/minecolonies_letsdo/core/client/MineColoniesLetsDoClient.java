package steve_gall.minecolonies_letsdo.core.client;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MineColoniesLetsDoClient
{
	public MineColoniesLetsDoClient()
	{
		var fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		var forge_bus = MinecraftForge.EVENT_BUS;
	}

}
