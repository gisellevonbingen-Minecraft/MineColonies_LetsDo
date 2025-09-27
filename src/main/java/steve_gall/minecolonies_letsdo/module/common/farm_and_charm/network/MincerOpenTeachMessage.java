package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.network;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.colony.buildings.modules.IBuildingModuleView;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import steve_gall.minecolonies_compatibility.core.common.MineColoniesCompatibility;
import steve_gall.minecolonies_compatibility.core.common.network.message.ModuleMenuOpenMessage;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.MincerTeachMenu;

public class MincerOpenTeachMessage extends ModuleMenuOpenMessage
{
	public static final CustomPacketPayload.Type<MincerOpenTeachMessage> TYPE = new CustomPacketPayload.Type<>(MineColoniesCompatibility.rl("lets_do_farm_and_charm_mincer_open_teach"));

	public MincerOpenTeachMessage(IBuildingModuleView module)
	{
		super(module);
	}

	public MincerOpenTeachMessage(RegistryFriendlyByteBuf buffer)
	{
		super(buffer);
	}

	@Override
	public void encode(RegistryFriendlyByteBuf buffer)
	{
		super.encode(buffer);
	}

	@Override
	protected AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player, IBuildingModule module)
	{
		return new MincerTeachMenu(windowId, inventory, module);
	}

	@Override
	protected void toBuffer(RegistryFriendlyByteBuf buffer, IBuildingModule module)
	{
		super.toBuffer(buffer, module);
	}

	@Override
	public CustomPacketPayload.Type<MincerOpenTeachMessage> type()
	{
		return TYPE;
	}

}
