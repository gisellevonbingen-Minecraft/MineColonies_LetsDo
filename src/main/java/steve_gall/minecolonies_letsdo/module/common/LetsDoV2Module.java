package steve_gall.minecolonies_letsdo.module.common;

import java.util.function.Supplier;

import net.minecraftforge.fml.ModList;

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
			var majorVersion = c.getModInfo().getVersion().getMajorVersion();
			return majorVersion == 2;
		}).isPresent();
	}

}
