package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.BowlTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.MincerTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.PotTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.RoasterTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.SiloTeachMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu.StoveTeachMenu;

public class ModuleMenuTypes
{
	public static final DeferredRegister<MenuType<?>> REGISTER = DeferredRegister.create(Registries.MENU, MineColoniesLetsDo.MOD_ID);
	public static final DeferredHolder<MenuType<?>, MenuType<StoveTeachMenu>> STOVE_TEACH = REGISTER.register("farm_and_charm_stove_teach", () -> IMenuTypeExtension.create(StoveTeachMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BowlTeachMenu>> BOWL_TEACH = REGISTER.register("farm_and_charm_bowl_teach", () -> IMenuTypeExtension.create(BowlTeachMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<PotTeachMenu>> POT_TEACH = REGISTER.register("farm_and_charm_cooking_teach", () -> IMenuTypeExtension.create(PotTeachMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<MincerTeachMenu>> MINCER_TEACH = REGISTER.register("farm_and_charm_mincer_teach", () -> IMenuTypeExtension.create(MincerTeachMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<RoasterTeachMenu>> ROASTER_TEACH = REGISTER.register("farm_and_charm_roaster_teach", () -> IMenuTypeExtension.create(RoasterTeachMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<SiloTeachMenu>> SILO_TEACH = REGISTER.register("farm_and_charm_silo_teach", () -> IMenuTypeExtension.create(SiloTeachMenu::new));

	private ModuleMenuTypes()
	{

	}

}
