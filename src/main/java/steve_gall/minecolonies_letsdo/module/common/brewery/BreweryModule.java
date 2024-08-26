package steve_gall.minecolonies_letsdo.module.common.brewery;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedCrop;
import steve_gall.minecolonies_letsdo.module.common.AbstractModule;

public class BreweryModule extends AbstractModule
{
	@Override
	protected void onLoad()
	{
		super.onLoad();

	}

	@Override
	protected void onFMLCommonSetup(FMLCommonSetupEvent e)
	{
		super.onFMLCommonSetup(e);
		e.enqueueWork(() ->
		{
			CustomizedCrop.register(new HopsCrop());
		});
	}

}
