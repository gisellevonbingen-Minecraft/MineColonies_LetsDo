package steve_gall.minecolonies_letsdo.module.common.farm_and_charm.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import steve_gall.minecolonies_letsdo.core.common.MineColoniesLetsDo;

public class ModuleTags
{
	public static class Items
	{
		public static final TagKey<Item> SEEDS = forge("seeds");

		public static final TagKey<Item> BAKERY_OUTPUT = create("bakery_output");
		public static final TagKey<Item> CHEF_MINCER_PRODUCT = create("chef_mincer_product");
		public static final TagKey<Item> FARMER_SILO_PRODUCT = create("farmer_silo_product");

		public static TagKey<Item> forge(String path)
		{
			return ItemTags.create(new ResourceLocation("forge", path));
		}

		public static TagKey<Item> create(String path)
		{
			return ItemTags.create(MineColoniesLetsDo.rl(path));
		}

		private Items()
		{

		}

	}

	private ModuleTags()
	{

	}

}
