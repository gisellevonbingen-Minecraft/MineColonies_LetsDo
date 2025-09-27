package steve_gall.minecolonies_letsdo.core.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import steve_gall.minecolonies_letsdo.module.common.ModuleManager;
import steve_gall.minecolonies_tweaks.api.common.network.MessageRegistrar;

@Mod(MineColoniesLetsDo.MOD_ID)
public class MineColoniesLetsDo
{
	public static final String MOD_ID = "minecolonies_letsdo";
	public static final Logger LOGGER = LogManager.getLogger();

	public MineColoniesLetsDo()
	{
		var fml_bus = ModLoadingContext.get().getActiveContainer().getEventBus();
		fml_bus.addListener(this::onFMLCommonSetup);
		fml_bus.addListener(this::onFMLClientSetup);
		fml_bus.addListener(this::onRegisterPayloadHandlers);

		var forge_bus = NeoForge.EVENT_BUS;

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

	private void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event)
	{
		var modVersion = ModList.get().getModContainerById(MOD_ID).get().getModInfo().getVersion().toString();
		var registry = new MessageRegistrar(event.registrar(MOD_ID).versioned(modVersion));
		ModuleManager.LOADED_MODULES.forEach(m -> m.onRegisterNetwork(registry));
	}

	public static ResourceLocation rl(String path)
	{
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public static String tl(String path)
	{
		return MOD_ID + "." + path;
	}

}
