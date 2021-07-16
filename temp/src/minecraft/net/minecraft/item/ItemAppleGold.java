package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAppleGold extends ItemFood {
   public ItemAppleGold(int p_i45341_1_, float p_i45341_2_, boolean p_i45341_3_) {
      super(p_i45341_1_, p_i45341_2_, p_i45341_3_);
      this.func_77627_a(true);
   }

   public boolean func_77636_d(ItemStack p_77636_1_) {
      return p_77636_1_.func_77960_j() > 0;
   }

   public EnumRarity func_77613_e(ItemStack p_77613_1_) {
      return p_77613_1_.func_77960_j() == 0?EnumRarity.RARE:EnumRarity.EPIC;
   }

   protected void func_77849_c(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
      if(!p_77849_2_.field_72995_K) {
         p_77849_3_.func_70690_d(new PotionEffect(Potion.field_76444_x.field_76415_H, 2400, 0));
      }

      if(p_77849_1_.func_77960_j() > 0) {
         if(!p_77849_2_.field_72995_K) {
            p_77849_3_.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 600, 4));
            p_77849_3_.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 6000, 0));
            p_77849_3_.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 6000, 0));
         }
      } else {
         super.func_77849_c(p_77849_1_, p_77849_2_, p_77849_3_);
      }

   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      p_150895_3_.add(new ItemStack(p_150895_1_, 1, 0));
      p_150895_3_.add(new ItemStack(p_150895_1_, 1, 1));
   }
}
