package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.menu;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.colony.buildings.modules.IBuildingModule;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.satisfy.farm_and_charm.recipe.CraftingBowlRecipe;
import net.satisfy.farm_and_charm.registry.RecipeTypeRegistry;
import steve_gall.minecolonies_compatibility.api.common.inventory.IMenuRecipeValidator;
import steve_gall.minecolonies_compatibility.api.common.inventory.MenuRecipeValidatorRecipe;
import steve_gall.minecolonies_compatibility.core.common.inventory.ContainerHelper;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachContainer;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachInputSlot;
import steve_gall.minecolonies_compatibility.core.common.inventory.TeachResultSlot;
import steve_gall.minecolonies_compatibility.core.common.item.ItemStackHelper;
import steve_gall.minecolonies_compatibility.core.common.util.NBTUtils2;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleCraftingTypes;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleMenuTypes;

public class BowlTeachMenu extends FarmAndCharmTeachMenu<CraftingBowlRecipe>
{
	public static final int INVENTORY_X = 8;
	public static final int INVENTORY_Y = 84;

	public static final int CRAFTING_SLOTS = 4;
	public static final int CRAFTING_COLS = 2;
	public static final int CRAFTING_X = 35;
	public static final int CRAFTING_Y = 26;
	public static final int CRAFTING_COLUMNS = 3;
	public static final int CRAFTING_ROW = 2;

	public static final int RESULT_X = 130;
	public static final int RESULT_Y = 35;

	public BowlTeachMenu(int windowId, Inventory inventory, IBuildingModule module)
	{
		super(ModuleMenuTypes.BOWL_TEACH.get(), windowId, inventory, module);
		this.setup();
	}

	public BowlTeachMenu(int windowId, Inventory inventory, FriendlyByteBuf buffer)
	{
		super(ModuleMenuTypes.BOWL_TEACH.get(), windowId, inventory, buffer);
		this.setup();
	}

	private void setup()
	{
		this.addInventorySlots(INVENTORY_X, INVENTORY_Y);

		this.inputContainer = new TeachContainer(this, CRAFTING_SLOTS);

		for (var i = 0; i < CRAFTING_SLOTS; i++)
		{
			var xi = i % CRAFTING_COLS;
			var yi = i / CRAFTING_COLS;
			this.inputSlots.add(this.addSlot(new TeachInputSlot(this.inputContainer, i, CRAFTING_X + xi * SLOT_OFFSET, CRAFTING_Y + yi * SLOT_OFFSET)));
		}

		this.resultContainer = new TeachContainer(this, 1);
		this.resultSlots.add(this.addSlot(new TeachResultSlot(this.resultContainer, 0, RESULT_X, RESULT_Y)));
	}

	@Override
	protected IMenuRecipeValidator<CraftingBowlRecipe> createRecipeValidator()
	{
		return new MenuRecipeValidatorRecipe<>(this.inventory.player.level())
		{
			@Override
			public RecipeType<CraftingBowlRecipe> getRecipeType()
			{
				return RecipeTypeRegistry.CRAFTING_BOWL_RECIPE_TYPE.get();
			}

			@Override
			public Container createRecipeContainer(Container container)
			{
				return container;
			}

			@Override
			protected boolean test(CraftingBowlRecipe recipe, ServerPlayer player, Container container)
			{
				return recipe.getIngredients().size() == ItemStackHelper.filterNotEmpty(ContainerHelper.getItemStacks(container)).size();
			}

		};
	}

	@Override
	protected void setContainerByTransfer(@NotNull CraftingBowlRecipe recipe, @NotNull CompoundTag payload)
	{
		super.setContainerByTransfer(recipe, payload);

		var input = NBTUtils2.deserializeList(payload, "input", ItemStack::of);

		for (var i = 0; i < CRAFTING_SLOTS; i++)
		{
			this.inputContainer.setItem(i, i < input.size() ? input.get(i) : ItemStack.EMPTY);
		}

	}

	@Override
	protected void onRecipeChanged()
	{
		this.resultContainer.setItem(0, this.recipe != null ? this.recipe.getResultItem(this.inventory.player.level().registryAccess()) : ItemStack.EMPTY);
	}

	@Override
	public CraftingType getCraftingType()
	{
		return ModuleCraftingTypes.BOWL.get();
	}

}
