package net.minecraft.item;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemPickaxe extends ItemTool {
   private static final Set<Block> field_150915_c = Sets.newHashSet(new Block[]{Blocks.field_150408_cc, Blocks.field_150365_q, Blocks.field_150347_e, Blocks.field_150319_E, Blocks.field_150484_ah, Blocks.field_150482_ag, Blocks.field_150334_T, Blocks.field_150318_D, Blocks.field_150340_R, Blocks.field_150352_o, Blocks.field_150432_aD, Blocks.field_150339_S, Blocks.field_150366_p, Blocks.field_150368_y, Blocks.field_150369_x, Blocks.field_150439_ay, Blocks.field_150341_Y, Blocks.field_150424_aL, Blocks.field_150403_cj, Blocks.field_150448_aq, Blocks.field_150450_ax, Blocks.field_150322_A, Blocks.field_180395_cM, Blocks.field_150348_b, Blocks.field_150333_U});

   protected ItemPickaxe(Item.ToolMaterial p_i45347_1_) {
      super(2.0F, p_i45347_1_, field_150915_c);
   }

   public boolean func_150897_b(Block p_150897_1_) {
      return p_150897_1_ == Blocks.field_150343_Z?this.field_77862_b.func_77996_d() == 3:(p_150897_1_ != Blocks.field_150484_ah && p_150897_1_ != Blocks.field_150482_ag?(p_150897_1_ != Blocks.field_150412_bA && p_150897_1_ != Blocks.field_150475_bE?(p_150897_1_ != Blocks.field_150340_R && p_150897_1_ != Blocks.field_150352_o?(p_150897_1_ != Blocks.field_150339_S && p_150897_1_ != Blocks.field_150366_p?(p_150897_1_ != Blocks.field_150368_y && p_150897_1_ != Blocks.field_150369_x?(p_150897_1_ != Blocks.field_150450_ax && p_150897_1_ != Blocks.field_150439_ay?(p_150897_1_.func_149688_o() == Material.field_151576_e?true:(p_150897_1_.func_149688_o() == Material.field_151573_f?true:p_150897_1_.func_149688_o() == Material.field_151574_g)):this.field_77862_b.func_77996_d() >= 2):this.field_77862_b.func_77996_d() >= 1):this.field_77862_b.func_77996_d() >= 1):this.field_77862_b.func_77996_d() >= 2):this.field_77862_b.func_77996_d() >= 2):this.field_77862_b.func_77996_d() >= 2);
   }

   public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
      return p_150893_2_.func_149688_o() != Material.field_151573_f && p_150893_2_.func_149688_o() != Material.field_151574_g && p_150893_2_.func_149688_o() != Material.field_151576_e?super.func_150893_a(p_150893_1_, p_150893_2_):this.field_77864_a;
   }
}
