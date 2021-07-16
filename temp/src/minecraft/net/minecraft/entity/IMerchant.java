package net.minecraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public interface IMerchant {
   void func_70932_a_(EntityPlayer var1);

   EntityPlayer func_70931_l_();

   MerchantRecipeList func_70934_b(EntityPlayer var1);

   void func_70930_a(MerchantRecipeList var1);

   void func_70933_a(MerchantRecipe var1);

   void func_110297_a_(ItemStack var1);

   IChatComponent func_145748_c_();
}
