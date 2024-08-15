package steve_gall.minecolonies_letsdo.core.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import steve_gall.minecolonies_letsdo.core.common.network.NetworkChannel;
import steve_gall.minecolonies_letsdo.module.common.ModuleManager;

@Mod(MineColoniesLetsDo.MOD_ID)
public class MineColoniesLetsDo
{
	public static final String MOD_ID = "minecolonies_letsdo";
	public static final Logger LOGGER = LogManager.getLogger();

	private static NetworkChannel NETWORK;

	public MineColoniesLetsDo()
	{
		var fml_bus = FMLJavaModLoadingContext.get().getModEventBus();
		fml_bus.addListener(this::onFMLCommonSetup);
		fml_bus.addListener(this::onFMLClientSetup);

		var forge_bus = MinecraftForge.EVENT_BUS;

		NETWORK = new NetworkChannel("main");
		ModuleManager.initialize();
	}

	private void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		e.enqueueWork(() ->
		{
		});
	}

	private void onFMLClientSetup(FMLClientSetupEvent e)
	{

	}

	public static NetworkChannel network()
	{
		return NETWORK;
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MOD_ID, path);
	}

	public static String tl(String path)
	{
		return MOD_ID + "." + path;
	}

}
