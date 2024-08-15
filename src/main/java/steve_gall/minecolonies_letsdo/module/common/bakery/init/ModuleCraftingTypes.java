package steve_gall.minecolonies_letsdo.module.common.bakery.init;

import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.satisfy.bakery.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.crafting.SimpleCraftingType;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;
import steve_gall.minecolonies_letsdo.module.common.bakery.crafting.BakingGenericRecipe;
import steve_gall.minecolonies_tweaks.api.registries.DeferredRegisterHelper;

public class ModuleCraftingTypes
{
	public static final DeferredRegister<CraftingType> REGISTER = DeferredRegisterHelper.craftingTypes(MineColoniesLetsDo.MOD_ID);
	public static final RegistryObject<CraftingType> BAKING = DeferredRegisterHelper.registerCraftingType(REGISTER, "bakery_baking", id -> new SimpleCraftingType<>(id, RecipeTypeRegistry.BAKING_STATION_RECIPE_TYPE, BakingGenericRecipe::new));

	private ModuleCraftingTypes()
	{

	}

}
