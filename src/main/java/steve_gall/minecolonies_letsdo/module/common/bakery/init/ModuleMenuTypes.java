package steve_gall.minecolonies_letsdo.module.common.bakery.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.bakery.menu.BakingTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesLetsDo.MOD_ID);
	public static final RegistryObject<MenuType<BakingTeachMenu>> BAKING_TEACH = REGISTER.register("bakery_baking_teach", () -> IForgeMenuType.create(BakingTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
