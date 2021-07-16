package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class ShapelessRecipes implements IRecipe {
   private final ItemStack field_77580_a;
   private final List<ItemStack> field_77579_b;

   public ShapelessRecipes(ItemStack p_i1918_1_, List<ItemStack> p_i1918_2_) {
      this.field_77580_a = p_i1918_1_;
      this.field_77579_b = p_i1918_2_;
   }

   public ItemStack func_77571_b() {
      return this.field_77580_a;
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

   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      List<ItemStack> list = Lists.newArrayList(this.field_77579_b);

      for(int i = 0; i < p_77569_1_.func_174923_h(); ++i) {
         for(int j = 0; j < p_77569_1_.func_174922_i(); ++j) {
            ItemStack itemstack = p_77569_1_.func_70463_b(j, i);
            if(itemstack != null) {
               boolean flag = false;

               for(ItemStack itemstack1 : list) {
                  if(itemstack.func_77973_b() == itemstack1.func_77973_b() && (itemstack1.func_77960_j() == 32767 || itemstack.func_77960_j() == itemstack1.func_77960_j())) {
                     flag = true;
                     list.remove(itemstack1);
                     break;
                  }
               }

               if(!flag) {
                  return false;
               }
            }
         }
      }

      return list.isEmpty();
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      return this.field_77580_a.func_77946_l();
   }

   public int func_77570_a() {
      return this.field_77579_b.size();
   }
}
