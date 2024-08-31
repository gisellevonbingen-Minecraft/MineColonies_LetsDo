package steve_gall.minecolonies_letsdo.module.client.jei;

import mezz.jei.api.IModPlugin;
import net.minecraft.resources.ResourceLocation;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.OptionalModule;

public abstract class AbstractModulePlugin implements IModPlugin
{
	@Override
	public ResourceLocation getPluginUid()
	{
		return MineColoniesLetsDo.rl(this.getModule().getModId());
	}

	public boolean isLoaded()
	{
		return this.getModule().isLoaded();
	}

	public abstract OptionalModule<?> getModule();
}
