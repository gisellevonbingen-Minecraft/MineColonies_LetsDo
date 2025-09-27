package steve_gall.minecolonies_letsdo.module.common.bakery.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.bakery.menu.BakingTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(Registries.MENU, MineColoniesLetsDo.MOD_ID);
	public static final DeferredHolder<MenuType<?>, MenuType<BakingTeachMenu>> BAKING_TEACH = REGISTER.register("bakery_baking_teach", () -> IMenuTypeExtension.create(BakingTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
