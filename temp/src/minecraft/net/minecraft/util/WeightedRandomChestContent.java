package net.minecraft.util;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.WeightedRandom;

public class WeightedRandomChestContent extends WeightedRandom.Item {
   private ItemStack field_76297_b;
   private int field_76295_d;
   private int field_76296_e;

   public WeightedRandomChestContent(Item p_i45311_1_, int p_i45311_2_, int p_i45311_3_, int p_i45311_4_, int p_i45311_5_) {
      super(p_i45311_5_);
      this.field_76297_b = new ItemStack(p_i45311_1_, 1, p_i45311_2_);
      this.field_76295_d = p_i45311_3_;
      this.field_76296_e = p_i45311_4_;
   }

   public WeightedRandomChestContent(ItemStack p_i1558_1_, int p_i1558_2_, int p_i1558_3_, int p_i1558_4_) {
      super(p_i1558_4_);
      this.field_76297_b = p_i1558_1_;
      this.field_76295_d = p_i1558_2_;
      this.field_76296_e = p_i1558_3_;
   }

   public static void func_177630_a(Random p_177630_0_, List<WeightedRandomChestContent> p_177630_1_, IInventory p_177630_2_, int p_177630_3_) {
      for(int i = 0; i < p_177630_3_; ++i) {
         WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent)WeightedRandom.func_76271_a(p_177630_0_, p_177630_1_);
         int j = weightedrandomchestcontent.field_76295_d + p_177630_0_.nextInt(weightedrandomchestcontent.field_76296_e - weightedrandomchestcontent.field_76295_d + 1);
         if(weightedrandomchestcontent.field_76297_b.func_77976_d() >= j) {
            ItemStack itemstack1 = weightedrandomchestcontent.field_76297_b.func_77946_l();
            itemstack1.field_77994_a = j;
            p_177630_2_.func_70299_a(p_177630_0_.nextInt(p_177630_2_.func_70302_i_()), itemstack1);
         } else {
            for(int k = 0; k < j; ++k) {
               ItemStack itemstack = weightedrandomchestcontent.field_76297_b.func_77946_l();
               itemstack.field_77994_a = 1;
               p_177630_2_.func_70299_a(p_177630_0_.nextInt(p_177630_2_.func_70302_i_()), itemstack);
            }
         }
      }

   }

   public static void func_177631_a(Random p_177631_0_, List<WeightedRandomChestContent> p_177631_1_, TileEntityDispenser p_177631_2_, int p_177631_3_) {
      for(int i = 0; i < p_177631_3_; ++i) {
         WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent)WeightedRandom.func_76271_a(p_177631_0_, p_177631_1_);
         int j = weightedrandomchestcontent.field_76295_d + p_177631_0_.nextInt(weightedrandomchestcontent.field_76296_e - weightedrandomchestcontent.field_76295_d + 1);
         if(weightedrandomchestcontent.field_76297_b.func_77976_d() >= j) {
            ItemStack itemstack1 = weightedrandomchestcontent.field_76297_b.func_77946_l();
            itemstack1.field_77994_a = j;
            p_177631_2_.func_70299_a(p_177631_0_.nextInt(p_177631_2_.func_70302_i_()), itemstack1);
         } else {
            for(int k = 0; k < j; ++k) {
               ItemStack itemstack = weightedrandomchestcontent.field_76297_b.func_77946_l();
               itemstack.field_77994_a = 1;
               p_177631_2_.func_70299_a(p_177631_0_.nextInt(p_177631_2_.func_70302_i_()), itemstack);
            }
         }
      }

   }

   public static List<WeightedRandomChestContent> func_177629_a(List<WeightedRandomChestContent> p_177629_0_, WeightedRandomChestContent... p_177629_1_) {
      List<WeightedRandomChestContent> list = Lists.newArrayList(p_177629_0_);
      Collections.addAll(list, p_177629_1_);
      return list;
   }
}
