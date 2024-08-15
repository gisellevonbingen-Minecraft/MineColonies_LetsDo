package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.BowlTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.MincerTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.PotTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.RoasterTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.StoveTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MineColoniesLetsDo.MOD_ID);
	public static final RegistryObject<MenuType<StoveTeachMenu>> STOVE_TEACH = REGISTER.register("farm_and_charm_stove_teach", () -> IForgeMenuType.create(StoveTeachMenu::new));
	public static final RegistryObject<MenuType<BowlTeachMenu>> BOWL_TEACH = REGISTER.register("farm_and_charm_bowl_teach", () -> IForgeMenuType.create(BowlTeachMenu::new));
	public static final RegistryObject<MenuType<PotTeachMenu>> POT_TEACH = REGISTER.register("farm_and_charm_cooking_teach", () -> IForgeMenuType.create(PotTeachMenu::new));
	public static final RegistryObject<MenuType<MincerTeachMenu>> MINCER_TEACH = REGISTER.register("farm_and_charm_mincer_teach", () -> IForgeMenuType.create(MincerTeachMenu::new));
	public static final RegistryObject<MenuType<RoasterTeachMenu>> ROASTER_TEACH = REGISTER.register("farm_and_charm_roaster_teach", () -> IForgeMenuType.create(RoasterTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
