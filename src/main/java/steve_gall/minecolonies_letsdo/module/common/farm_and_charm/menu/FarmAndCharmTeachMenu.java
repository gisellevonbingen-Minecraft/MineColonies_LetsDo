package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu;

import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachRecipeMenu;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.FarmAndCharmCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.FarmAndCharmCraftingModuleView;

public abstract class FarmAndCharmTeachMenu<RECIPE extends Recipe<?>> extends TeachRecipeMenu<RECIPE>
{
	private final Predicate<ItemStack> isOutputCompatible;

	public FarmAndCharmTeachMenu(MenuType<?> menuType, int windowId, Inventory inventory, IBuildingModule module)
	{
		super(menuType, windowId, inventory, module);

		this.isOutputCompatible = ((FarmAndCharmCraftingModule) module)::isRecipeCompatible;
	}

	public FarmAndCharmTeachMenu(MenuType<?> menuType, int windowId, Inventory inventory, FriendlyByteBuf buffer)
	{
		super(menuType, windowId, inventory, buffer);

		this.isOutputCompatible = ((FarmAndCharmCraftingModuleView) this.modulePos.getModuleView())::isOutputCompatible;
	}

	@Override
	public @Nullable Component getRecipeError(@NotNull RECIPE recipe)
	{
		if (!this.isOutputCompatible(recipe.getResultItem(this.inventory.player.level().registryAccess())))
		{
			return Component.translatable("minecolonies_letsdo.text.unsupported_recipe");
		}

		return super.getRecipeError(recipe);
	}

	public boolean isOutputCompatible(ItemStack output)
	{
		return this.isOutputCompatible.test(output);
	}

}
