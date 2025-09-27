package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu;

import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import net.satisfy.farm_and_charm.core.recipe.SiloRecipe;
import net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.api.common.inventory.MenuRecipeValidatorRecipe;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachContainer;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachInputSlot;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachRecipeMenu;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachResultSlot;
import steve_gall.minecolonies_letsdo.core.common.crafting.RecipeHelper;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.SiloCraftingModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules.SiloCraftingModuleView;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleMenuTypes;
import steve_gall.minecolonies_tweaks.core.common.item.ItemSerializationHelper;

public class SiloTeachMenu extends TeachRecipeMenu<RecipeHolder<SiloRecipe>, RecipeInput>
{
	public static final int INVENTORY_X = 8;
	public static final int INVENTORY_Y = 84;

	public static final int CRAFTING_X = 44;
	public static final int CRAFTING_Y = 26;

	public static final int RESULT_X = 116;
	public static final int RESULT_Y = 26;

	private final Predicate<ItemStack> isOutputCompatible;

	public SiloTeachMenu(int windowId, Inventory inventory, IBuildingModule module)
	{
		super(ModuleMenuTypes.SILO_TEACH.get(), windowId, inventory, module);
		this.setup();

		this.isOutputCompatible = ((SiloCraftingModule) module)::isOutputCompatible;
	}

	public SiloTeachMenu(int windowId, Inventory inventory, RegistryFriendlyByteBuf buffer)
	{
		super(ModuleMenuTypes.SILO_TEACH.get(), windowId, inventory, buffer);
		this.setup();

		this.isOutputCompatible = ((SiloCraftingModuleView) this.modulePos.getModuleView())::isOutputCompatible;
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
	protected IMenuRecipeValidator<RecipeHolder<SiloRecipe>, RecipeInput> createRecipeValidator()
	{
		return new MenuRecipeValidatorRecipe<>(this.inventory.player.level())
		{
			@Override
			public RecipeType<SiloRecipe> getRecipeType()
			{
				return RecipeTypeRegistry.SILO_RECIPE_TYPE.get();
			}

			@Override
			public @NotNull RecipeInput getInput(@NotNull Container container, @Nullable RecipeHolder<SiloRecipe> recipe)
			{
				return new RecipeWrapper(new InvWrapper(container));
			}

			@Override
			protected boolean test(RecipeHolder<SiloRecipe> recipe, Container container, ServerPlayer player)
			{
				return super.test(recipe, container, player) && RecipeHelper.matchesIngredientCount(recipe.value(), container);
			}

		};
	}

	@Override
	protected void setContainerByTransfer(@NotNull HolderLookup.Provider provider, @NotNull RecipeHolder<SiloRecipe> recipe, @NotNull CompoundTag payload)
	{
		super.setContainerByTransfer(provider, recipe, payload);

		this.inputContainer.setItem(0, ItemSerializationHelper.deserializeTag(provider, payload.getCompound("input")));
	}

	@Override
	protected void onRecipeChanged(HolderLookup.Provider provider, RecipeInput input)
	{
		this.resultContainer.setItem(0, this.recipe != null ? this.recipe.value().getResultItem(provider) : ItemStack.EMPTY);
	}

	@Override
	public @Nullable Component getRecipeError(@NotNull RecipeHolder<SiloRecipe> recipe)
	{
		if (!this.isOutputCompatible(recipe.value().getResultItem(this.inventory.player.level().registryAccess())))
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
