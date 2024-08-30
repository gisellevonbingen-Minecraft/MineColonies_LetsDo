package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init;

import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.satisfy.farm_and_charm.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleCraftingType;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.BowlGenericRecipe;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.MincerGenericRecipe;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.PotGenericRecipe;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.RoasterGenericRecipe;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.SiloGenericRecipe;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.crafting.StoveGenericRecipe;
import steve_gall.minecolonies_tweaks.api.registries.DeferredRegisterHelper;

public class ModuleCraftingTypes
{
	public static final DeferredRegister<CraftingType> REGISTER = DeferredRegisterHelper.craftingTypes(MineColoniesLetsDo.MOD_ID);
	public static final RegistryObject<CraftingType> STOVE = DeferredRegisterHelper.registerCraftingType(REGISTER, "farm_and_charm_stove", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.STOVE_RECIPE_TYPE, StoveGenericRecipe::new));
	public static final RegistryObject<CraftingType> BOWL = DeferredRegisterHelper.registerCraftingType(REGISTER, "farm_and_charm_crafting_bowl", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.CRAFTING_BOWL_RECIPE_TYPE, BowlGenericRecipe::new));
	public static final RegistryObject<CraftingType> POT = DeferredRegisterHelper.registerCraftingType(REGISTER, "farm_and_charm_cooking_pot", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.COOKING_POT_RECIPE_TYPE, PotGenericRecipe::new));
	public static final RegistryObject<CraftingType> MINCER = DeferredRegisterHelper.registerCraftingType(REGISTER, "farm_and_charm_mincer", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.MINCER_RECIPE_TYPE, MincerGenericRecipe::new));
	public static final RegistryObject<CraftingType> ROASTER = DeferredRegisterHelper.registerCraftingType(REGISTER, "farm_and_charm_roaster", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.ROASTER_RECIPE_TYPE, RoasterGenericRecipe::new));
	public static final RegistryObject<CraftingType> SILO = DeferredRegisterHelper.registerCraftingType(REGISTER, "farm_and_charm_silo", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.SILO_RECIPE_TYPE, SiloGenericRecipe::new));

	private ModuleCraftingTypes()
	{

	}

}
