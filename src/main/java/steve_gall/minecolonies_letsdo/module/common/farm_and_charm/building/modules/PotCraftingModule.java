package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.building.modules;

import java.util.Collections;
import java.util.Set;

import org.jetbrains.annotations.NotNull;

import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IRecipeStorage;
import com.minecolonies.api.crafting.registry.CraftingType;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.farm_and_charm.block.entity.CookingPotBlockEntity;
import net.satisfy.farm_and_charm.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.core.common.util.InteractionMessageHelper;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init.ModuleCraftingTypes;

public class PotCraftingModule extends FarmAndCharmCraftingModule
{
	public PotCraftingModule(JobEntry jobEntry, boolean isBakery)
	{
		super(jobEntry, isBakery);
	}

	@Override
	public boolean isWorkingBlock(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state)
	{
		return level.getBlockEntity(pos) instanceof CookingPotBlockEntity cookingPot && cookingPot.isBeingBurned();
	}

	@Override
	public @NotNull BlockPos getParticlePosition(@NotNull BlockPos pos)
	{
		return super.getParticlePosition(pos).below();
	}

	@Override
	public @NotNull String getId()
	{
		return "lets_do_farm_and_charm_pot";
	}

	@Override
	public Set<CraftingType> getSupportedCraftingTypes()
	{
		return Collections.singleton(ModuleCraftingTypes.POT.get());
	}

	@Override
	public @NotNull Component getWorkingBlockNotFoundMessage(@NotNull IRecipeStorage recipeStorage)
	{
		return InteractionMessageHelper.getWorkingBlockAndUnderHeatSourceNotFound(ObjectRegistry.COOKING_POT.get());
	}

}
