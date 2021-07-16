package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeRepairItem implements IRecipe {
   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      List<ItemStack> list = Lists.<ItemStack>newArrayList();

      for(int i = 0; i < p_77569_1_.func_70302_i_(); ++i) {
         ItemStack itemstack = p_77569_1_.func_70301_a(i);
         if(itemstack != null) {
            list.add(itemstack);
            if(list.size() > 1) {
               ItemStack itemstack1 = (ItemStack)list.get(0);
               if(itemstack.func_77973_b() != itemstack1.func_77973_b() || itemstack1.field_77994_a != 1 || itemstack.field_77994_a != 1 || !itemstack1.func_77973_b().func_77645_m()) {
                  return false;
               }
            }
         }
      }

      return list.size() == 2;
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      List<ItemStack> list = Lists.<ItemStack>newArrayList();

      for(int i = 0; i < p_77572_1_.func_70302_i_(); ++i) {
         ItemStack itemstack = p_77572_1_.func_70301_a(i);
         if(itemstack != null) {
            list.add(itemstack);
            if(list.size() > 1) {
               ItemStack itemstack1 = (ItemStack)list.get(0);
               if(itemstack.func_77973_b() != itemstack1.func_77973_b() || itemstack1.field_77994_a != 1 || itemstack.field_77994_a != 1 || !itemstack1.func_77973_b().func_77645_m()) {
                  return null;
               }
            }
         }
      }

      if(list.size() == 2) {
         ItemStack itemstack2 = (ItemStack)list.get(0);
         ItemStack itemstack3 = (ItemStack)list.get(1);
         if(itemstack2.func_77973_b() == itemstack3.func_77973_b() && itemstack2.field_77994_a == 1 && itemstack3.field_77994_a == 1 && itemstack2.func_77973_b().func_77645_m()) {
            Item item = itemstack2.func_77973_b();
            int j = item.func_77612_l() - itemstack2.func_77952_i();
            int k = item.func_77612_l() - itemstack3.func_77952_i();
            int l = j + k + item.func_77612_l() * 5 / 100;
            int i1 = item.func_77612_l() - l;
            if(i1 < 0) {
               i1 = 0;
            }

            return new ItemStack(itemstack2.func_77973_b(), 1, i1);
         }
      }

      return null;
   }

   public int func_77570_a() {
      return 4;
   }

   public ItemStack func_77571_b() {
      return null;
   }

   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
      ItemStack[] aitemstack = new ItemStack[p_179532_1_.func_70302_i_()];

      for(int i = 0; i < aitemstack.length; ++i) {
         ItemStack itemstack = p_179532_1_.func_70301_a(i);
         if(itemstack != null && itemstack.func_77973_b().func_77634_r()) {
            aitemstack[i] = new ItemStack(itemstack.func_77973_b().func_77668_q());
         }
      }

      return aitemstack;
   }
}
