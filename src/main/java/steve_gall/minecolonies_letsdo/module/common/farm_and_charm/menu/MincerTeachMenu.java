package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu;

import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.satisfy.farm_and_charm.recipe.MincerRecipe;
import net.satisfy.farm_and_charm.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.api.common.inventory.MenuRecipeValidatorRecipe;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachContainer;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachInputSlot;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachRecipeMenu;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachResultSlot;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.MincerCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.MincerCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleCraftingTypes;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleMenuTypes;

public class MincerTeachMenu extends TeachRecipeMenu<MincerRecipe>
{
	public static final int INVENTORY_X = 8;
	public static final int INVENTORY_Y = 84;

	public static final int CRAFTING_X = 44;
	public static final int CRAFTING_Y = 26;

	public static final int RESULT_X = 116;
	public static final int RESULT_Y = 26;

	private final Predicate<ItemStack> isOutputCompatible;

	public MincerTeachMenu(int windowId, Inventory inventory, IBuildingModule module)
	{
		super(ModuleMenuTypes.MINCER_TEACH.get(), windowId, inventory, module);

		this.isOutputCompatible = ((MincerCraftingModule) module)::isOutputCompatible;
		this.setup();
	}

	public MincerTeachMenu(int windowId, Inventory inventory, FriendlyByteBuf buffer)
	{
		super(ModuleMenuTypes.MINCER_TEACH.get(), windowId, inventory, buffer);

		this.isOutputCompatible = ((MincerCraftingModuleView) this.modulePos.getModuleView())::isOutputCompatible;
		this.setup();
	}

	private void setup()
	{
		this.addInventorySlots(INVENTORY_X, INVENTORY_Y);

		this.inputContainer = new TeachContainer(this, 1);
		this.inputSlots.add(this.addSlot(new TeachInputSlot(this.inputContainer, 0, CRAFTING_X, CRAFTING_Y)));

		this.resultContainer = new TeachContainer(this, 1);
		this.resultSlots.add(this.addSlot(new TeachResultSlot(this.resultContainer, 0, RESULT_X, RESULT_Y)));
	}

	@Override
	protected IMenuRecipeValidator<MincerRecipe> createRecipeValidator()
	{
		return new MenuRecipeValidatorRecipe<>(this.inventory.player.level())
		{
			@Override
			public RecipeType<MincerRecipe> getRecipeType()
			{
				return RecipeTypeRegistry.MINCER_RECIPE_TYPE.get();
			}

			@Override
			protected boolean test(MincerRecipe recipe, Container container, ServerPlayer player)
			{
				return this.matchesWithIngredientsCount(recipe, container);
			}

		};
	}

	@Override
	protected void setContainerByTransfer(@NotNull MincerRecipe recipe, @NotNull CompoundTag payload)
	{
		super.setContainerByTransfer(recipe, payload);

		this.inputContainer.setItem(0, ItemStack.of(payload.getCompound("input")));
	}

	@Override
	protected void onRecipeChanged()
	{
		this.resultContainer.setItem(0, this.recipe != null ? this.recipe.getResultItem(this.inventory.player.level().registryAccess()) : ItemStack.EMPTY);
	}

	@Override
	public @Nullable Component getRecipeError(@NotNull MincerRecipe recipe)
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

	@Override
	public CraftingType getCraftingType()
	{
		return ModuleCraftingTypes.MINCER.get();
	}

}
