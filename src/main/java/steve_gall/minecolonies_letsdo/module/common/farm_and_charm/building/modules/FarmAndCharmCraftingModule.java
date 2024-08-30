package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.crafting.IRecipeStorage;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import steve_gall.minecolonies_compatibility.api.common.building.module.AbstractCraftingModuleWithExternalWorkingBlocks;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.FarmAndCharmModule;

public abstract class FarmAndCharmCraftingModule extends AbstractCraftingModuleWithExternalWorkingBlocks
{
	private final boolean isBakery;

	public FarmAndCharmCraftingModule(JobEntry jobEntry, boolean isBakery)
	{
		super(jobEntry);

		this.isBakery = isBakery;
	}

	@Override
	public void improveRecipe(IRecipeStorage recipe, int count, ICitizenData citizen)
	{

	}

	@Override
	public boolean needWorkingBlock(@NotNull IRecipeStorage recipeStorage)
	{
		return true;
	}

	@Override
	public boolean isRecipeCompatible(@NotNull IGenericRecipe recipe)
	{
		return this.isOutputCompatible(recipe.getPrimaryOutput());
	}

	public boolean isOutputCompatible(ItemStack output)
	{
		return FarmAndCharmModule.testBakery(this.isBakery, output);
	}

	@Override
	public void serializeToView(@NotNull FriendlyByteBuf buf, boolean fullSync)
	{
		super.serializeToView(buf, fullSync);

		buf.writeBoolean(this.isBakery);
	}

	public boolean isBakery()
	{
		return this.isBakery;
	}

}
