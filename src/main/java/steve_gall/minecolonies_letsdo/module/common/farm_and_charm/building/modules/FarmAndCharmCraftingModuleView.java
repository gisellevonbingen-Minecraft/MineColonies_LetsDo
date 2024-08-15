package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.core.colony.buildings.moduleviews.CraftingModuleView;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.FarmAndCharmModule;

public class FarmAndCharmCraftingModuleView extends CraftingModuleView
{
	private boolean isBakery;

	public FarmAndCharmCraftingModuleView()
	{

	}

	public boolean isOutputCompatible(ItemStack output)
	{
		return FarmAndCharmModule.testBakery(this.isBakery, output);
	}

	@Override
	public void deserialize(@NotNull FriendlyByteBuf buf)
	{
		super.deserialize(buf);

		this.isBakery = buf.readBoolean();
	}

	public boolean isBakery()
	{
		return this.isBakery;
	}

}
