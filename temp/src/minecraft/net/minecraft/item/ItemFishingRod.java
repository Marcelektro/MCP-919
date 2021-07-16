package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {
   public ItemFishingRod() {
      this.func_77656_e(64);
      this.func_77625_d(1);
      this.func_77637_a(CreativeTabs.field_78040_i);
   }

   public boolean func_77662_d() {
      return true;
   }

   public boolean func_77629_n_() {
      return true;
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if(p_77659_3_.field_71104_cf != null) {
         int i = p_77659_3_.field_71104_cf.func_146034_e();
         p_77659_1_.func_77972_a(i, p_77659_3_);
         p_77659_3_.func_71038_i();
      } else {
         p_77659_2_.func_72956_a(p_77659_3_, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
         if(!p_77659_2_.field_72995_K) {
            p_77659_2_.func_72838_d(new EntityFishHook(p_77659_2_, p_77659_3_));
         }

         p_77659_3_.func_71038_i();
         p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
      }

      return p_77659_1_;
   }

   public boolean func_77616_k(ItemStack p_77616_1_) {
      return super.func_77616_k(p_77616_1_);
   }

   public int func_77619_b() {
      return 1;
   }
}
