package steve_gall.minecolonies_letsdo.module.common.farm_and_charm;

import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.farm_and_charm.block.crops.TomatoCropBlock;
import net.satisfy.farm_and_charm.registry.ObjectRegistry;
import steve_gall.minecolonies_compatibility.api.common.plant.CustomizedCrop;
import steve_gall.minecolonies_compatibility.api.common.plant.HarvesterContext;
import steve_gall.minecolonies_compatibility.api.common.plant.PlantBlockContext;
import steve_gall.minecolonies_compatibility.api.common.plant.PlantSeedContext;

public class TomatoCrop extends CustomizedCrop
{
	public static final int MAX_AGE = 4;

	@Override
	public boolean isSeed(@NotNull PlantSeedContext context)
	{
		return context.getSeed().is(ObjectRegistry.TOMATO_SEEDS.get());
	}

	@Override
	public boolean isCrop(@NotNull PlantBlockContext context)
	{
		return context.getState().getBlock() instanceof TomatoCropBlock;
	}

	@Override
	public @Nullable BlockState getPlantState(@NotNull PlantSeedContext context)
	{
		return ObjectRegistry.TOMATO_CROP.get().defaultBlockState();
	}

	@Override
	public @Nullable SpecialHarvestPositionFunction getSpecialHarvestPosition(@NotNull PlantBlockContext context)
	{
		return this::getHarvestPosition;
	}

	@Override
	public @Nullable SpecialHarvestMethodFunction getSpecialHarvestMethod(@NotNull PlantBlockContext context)
	{
		return this::harvest;
	}

	private @Nullable BlockPos getHarvestPosition(@NotNull PlantBlockContext context)
	{
		var pos = context.getPosition();

		while (true)
		{
			var state = context.getLevel().getBlockState(pos);

			if (state.getBlock() instanceof TomatoCropBlock)
			{
				if (state.getValue(TomatoCropBlock.AGE) >= MAX_AGE)
				{
					return pos;
				}
				else
				{
					pos = pos.above();
				}

			}
			else
			{
				break;
			}

		}

		return null;
	}

	private @NotNull List<ItemStack> harvest(@NotNull PlantBlockContext context, @NotNull HarvesterContext harvester)
	{
		var pos = this.getHarvestPosition(context);

		if (pos != null && context.getLevel() instanceof LevelAccessor level)
		{
			var state = level.getBlockState(pos);
			var amount = level.getRandom().nextInt(2) + 1;
			var drop = new ItemStack(ObjectRegistry.TOMATO.get(), amount);

			if (level.getRandom().nextFloat() < 0.05D)
			{
				drop = new ItemStack(ObjectRegistry.ROTTEN_TOMATO.get(), 1);
			}

			level.setBlock(pos, state.setValue(TomatoCropBlock.AGE, 1), Block.UPDATE_CLIENTS);
			return Collections.singletonList(drop);
		}

		return Collections.emptyList();
	}

}
