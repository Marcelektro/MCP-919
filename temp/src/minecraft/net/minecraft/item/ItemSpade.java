package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

public class ItemSpade extends ItemTool {
   private static final Set<Block> field_150916_c = Sets.newHashSet(new Block[]{Blocks.field_150435_aG, Blocks.field_150346_d, Blocks.field_150458_ak, Blocks.field_150349_c, Blocks.field_150351_n, Blocks.field_150391_bh, Blocks.field_150354_m, Blocks.field_150433_aE, Blocks.field_150431_aC, Blocks.field_150425_aM});

   public ItemSpade(Item.ToolMaterial p_i45353_1_) {
      super(1.0F, p_i45353_1_, field_150916_c);
   }

   public boolean func_150897_b(Block p_150897_1_) {
      return p_150897_1_ == Blocks.field_150431_aC?true:p_150897_1_ == Blocks.field_150433_aE;
   }
}
