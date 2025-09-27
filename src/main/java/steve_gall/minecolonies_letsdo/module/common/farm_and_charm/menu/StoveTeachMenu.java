package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import net.satisfy.farm_and_charm.core.recipe.StoveRecipe;
import net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.api.common.inventory.MenuRecipeValidatorRecipe;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachContainer;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachInputSlot;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachResultSlot;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_letsdo.core.common.crafting.RecipeHelper;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleMenuTypes;
import steve_gall.minecolonies_tweaks.core.common.item.ItemSerializationHelper;

public class StoveTeachMenu extends FarmAndCharmTeachMenu<StoveRecipe, RecipeInput>
{
	public static final int INVENTORY_X = 8;
	public static final int INVENTORY_Y = 84;

	public static final int CRAFTING_SLOTS = 3;
	public static final int CRAFTING_X = 26;
	public static final int CRAFTING_Y = 26;
	public static final int CRAFTING_COLUMNS = 3;
	public static final int CRAFTING_ROW = 2;

	public static final int RESULT_X = 130;
	public static final int RESULT_Y = 26;

	public StoveTeachMenu(int windowId, Inventory inventory, IBuildingModule module)
	{
		super(ModuleMenuTypes.STOVE_TEACH.get(), windowId, inventory, module);
		this.setup();
	}

	public StoveTeachMenu(int windowId, Inventory inventory, RegistryFriendlyByteBuf buffer)
	{
		super(ModuleMenuTypes.STOVE_TEACH.get(), windowId, inventory, buffer);
		this.setup();
	}

	private void setup()
	{
		this.addInventorySlots(INVENTORY_X, INVENTORY_Y);

		this.inputContainer = new TeachContainer(this, CRAFTING_SLOTS);

		for (var i = 0; i < CRAFTING_SLOTS; i++)
		{
			this.inputSlots.add(this.addSlot(new TeachInputSlot(this.inputContainer, i, CRAFTING_X + i * SLOT_OFFSET, CRAFTING_Y)));
		}

		this.resultContainer = new TeachContainer(this, 1);
		this.resultSlots.add(this.addSlot(new TeachResultSlot(this.resultContainer, 0, RESULT_X, RESULT_Y)));
	}

	@Override
	protected IMenuRecipeValidator<RecipeHolder<StoveRecipe>, RecipeInput> createRecipeValidator()
	{
		return new MenuRecipeValidatorRecipe<>(this.inventory.player.level())
		{
			@Override
			public RecipeType<StoveRecipe> getRecipeType()
			{
				return RecipeTypeRegistry.STOVE_RECIPE_TYPE.get();
			}

			@Override
			public @NotNull RecipeInput getInput(@NotNull Container container, @Nullable RecipeHolder<StoveRecipe> recipe)
			{
				return new RecipeWrapper(new InvWrapper(new CompoundContainer(new SimpleContainer(1), container)));
			}

			@Override
			protected boolean test(RecipeHolder<StoveRecipe> recipe, Container container, ServerPlayer player)
			{
				return super.test(recipe, container, player) && RecipeHelper.matchesIngredientCount(recipe.value(), container);
			}

		};
	}

	@Override
	protected void setContainerByTransfer(@NotNull HolderLookup.Provider provider, @NotNull RecipeHolder<StoveRecipe> recipe, @NotNull CompoundTag payload)
	{
		super.setContainerByTransfer(provider, recipe, payload);

		var input = NBTUtils2.deserializeList(payload, "input", ItemSerializationHelper.deserializerTag(provider));

		for (var i = 0; i < CRAFTING_SLOTS; i++)
		{
			this.inputContainer.setItem(i, i < input.size() ? input.get(i) : ItemStack.EMPTY);
		}

	}

	@Override
	protected void onRecipeChanged(HolderLookup.Provider provider, RecipeInput input)
	{
		this.resultContainer.setItem(0, this.recipe != null ? this.recipe.value().getResultItem(provider) : ItemStack.EMPTY);
	}

}
