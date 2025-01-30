package steve_gall.minecolonies_letsdo.module.common;

import java.util.function.Supplier;

import net.minecraftforge.fml.ModList;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;

public class LetsDoV2Module<MODULE extends AbstractModule> extends OptionalModule<MODULE>
{
	public LetsDoV2Module(String modid, Supplier<Supplier<MODULE>> initializer)
	{
		super(modid, initializer);
	}

	@Override
	protected boolean canLoad()
	{
		return super.canLoad() && ModList.get().getModContainerById(this.getModId()).filter(c ->
		{
			var rawVersion = c.getModInfo().getConfig().<String> getConfigElement("version").orElse("");
			var index = rawVersion.indexOf('.');

			try
			{
				var majorVersion = Integer.parseInt(rawVersion.substring(0, index));
				return majorVersion == 2;
			}
			catch (Exception e)
			{
				MineColoniesLetsDo.LOGGER.error(e);
				return true;
			}

		}).isPresent();
	}

}
