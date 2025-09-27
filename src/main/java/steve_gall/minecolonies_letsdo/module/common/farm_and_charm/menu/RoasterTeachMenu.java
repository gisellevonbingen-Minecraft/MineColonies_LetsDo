package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import net.satisfy.farm_and_charm.core.recipe.RoasterRecipe;
import net.satisfy.farm_and_charm.core.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.api.common.inventory.MenuRecipeValidatorRecipe;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachContainer;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachInputSlot;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachRecipeMenu;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachResultSlot;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_letsdo.core.common.crafting.RecipeHelper;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleMenuTypes;
import steve_gall.minecolonies_tweaks.core.common.item.ItemSerializationHelper;

public class RoasterTeachMenu extends TeachRecipeMenu<RecipeHolder<RoasterRecipe>, RecipeInput>
{
	public static final int INVENTORY_X = 8;
	public static final int INVENTORY_Y = 84;

	public static final int CRAFTING_SLOTS = 6;
	public static final int CRAFTING_COLS = 3;
	public static final int CRAFTING_X = 26;
	public static final int CRAFTING_Y = 17;

	public static final int RESULT_X = 120;
	public static final int RESULT_Y = 28;
	public static final int CONTAINER_X = 92;
	public static final int CONTAINER_Y = 55;

	public RoasterTeachMenu(int windowId, Inventory inventory, IBuildingModule module)
	{
		super(ModuleMenuTypes.ROASTER_TEACH.get(), windowId, inventory, module);
		this.setup();
	}

	public RoasterTeachMenu(int windowId, Inventory inventory, RegistryFriendlyByteBuf buffer)
	{
		super(ModuleMenuTypes.ROASTER_TEACH.get(), windowId, inventory, buffer);
		this.setup();
	}

	private void setup()
	{
		this.addInventorySlots(INVENTORY_X, INVENTORY_Y);

		this.inputContainer = new TeachContainer(this, CRAFTING_SLOTS);

		for (var i = 0; i < CRAFTING_SLOTS; i++)
		{
			var col = i % CRAFTING_COLS;
			var row = i / CRAFTING_COLS;
			this.inputSlots.add(this.addSlot(new TeachInputSlot(this.inputContainer, i, CRAFTING_X + col * SLOT_OFFSET, CRAFTING_Y + row * SLOT_OFFSET)));
		}

		this.resultContainer = new TeachContainer(this, 2);
		this.resultSlots.add(this.addSlot(new TeachResultSlot(this.resultContainer, 0, RESULT_X, RESULT_Y)));
		this.resultSlots.add(this.addSlot(new TeachResultSlot(this.resultContainer, 1, CONTAINER_X, CONTAINER_Y)));
	}

	@Override
	protected IMenuRecipeValidator<RecipeHolder<RoasterRecipe>, RecipeInput> createRecipeValidator()
	{
		return new MenuRecipeValidatorRecipe<>(this.inventory.player.level())
		{
			@Override
			public RecipeType<RoasterRecipe> getRecipeType()
			{
				return RecipeTypeRegistry.ROASTER_RECIPE_TYPE.get();
			}

			@Override
			public @NotNull RecipeInput getInput(@NotNull Container container, @Nullable RecipeHolder<RoasterRecipe> recipe)
			{
				return new RecipeWrapper(new InvWrapper(container));
			}

			@Override
			protected boolean test(RecipeHolder<RoasterRecipe> recipe, Container container, ServerPlayer player)
			{
				return super.test(recipe, container, player) && RecipeHelper.matchesIngredientCount(recipe.value(), container);
			}

		};
	}

	@Override
	protected void setContainerByTransfer(@NotNull HolderLookup.Provider provider, @NotNull RecipeHolder<RoasterRecipe> recipe, @NotNull CompoundTag payload)
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
		this.resultContainer.setItem(1, this.recipe != null ? this.recipe.value().getContainer() : ItemStack.EMPTY);
	}

}
