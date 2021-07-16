package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemAxe extends ItemTool {
   private static final Set<Block> field_150917_c = Sets.newHashSet(new Block[]{Blocks.field_150344_f, Blocks.field_150342_X, Blocks.field_150364_r, Blocks.field_150363_s, Blocks.field_150486_ae, Blocks.field_150423_aK, Blocks.field_150428_aP, Blocks.field_150440_ba, Blocks.field_150468_ap});

   protected ItemAxe(Item.ToolMaterial p_i45327_1_) {
      super(3.0F, p_i45327_1_, field_150917_c);
   }

   public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
      return p_150893_2_.func_149688_o() != Material.field_151575_d && p_150893_2_.func_149688_o() != Material.field_151585_k && p_150893_2_.func_149688_o() != Material.field_151582_l?super.func_150893_a(p_150893_1_, p_150893_2_):this.field_77864_a;
   }
}
