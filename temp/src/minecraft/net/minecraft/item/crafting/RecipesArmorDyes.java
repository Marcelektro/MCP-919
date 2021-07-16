package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesArmorDyes implements IRecipe {
   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      ItemStack itemstack = null;
      List<ItemStack> list = Lists.<ItemStack>newArrayList();

      for(int i = 0; i < p_77569_1_.func_70302_i_(); ++i) {
         ItemStack itemstack1 = p_77569_1_.func_70301_a(i);
         if(itemstack1 != null) {
            if(itemstack1.func_77973_b() instanceof ItemArmor) {
               ItemArmor itemarmor = (ItemArmor)itemstack1.func_77973_b();
               if(itemarmor.func_82812_d() != ItemArmor.ArmorMaterial.LEATHER || itemstack != null) {
                  return false;
               }

               itemstack = itemstack1;
            } else {
               if(itemstack1.func_77973_b() != Items.field_151100_aR) {
                  return false;
               }

               list.add(itemstack1);
            }
         }
      }

      return itemstack != null && !list.isEmpty();
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      ItemStack itemstack = null;
      int[] aint = new int[3];
      int i = 0;
      int j = 0;
      ItemArmor itemarmor = null;

      for(int k = 0; k < p_77572_1_.func_70302_i_(); ++k) {
         ItemStack itemstack1 = p_77572_1_.func_70301_a(k);
         if(itemstack1 != null) {
            if(itemstack1.func_77973_b() instanceof ItemArmor) {
               itemarmor = (ItemArmor)itemstack1.func_77973_b();
               if(itemarmor.func_82812_d() != ItemArmor.ArmorMaterial.LEATHER || itemstack != null) {
                  return null;
               }

               itemstack = itemstack1.func_77946_l();
               itemstack.field_77994_a = 1;
               if(itemarmor.func_82816_b_(itemstack1)) {
                  int l = itemarmor.func_82814_b(itemstack);
                  float f = (float)(l >> 16 & 255) / 255.0F;
                  float f1 = (float)(l >> 8 & 255) / 255.0F;
                  float f2 = (float)(l & 255) / 255.0F;
                  i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
                  aint[0] = (int)((float)aint[0] + f * 255.0F);
                  aint[1] = (int)((float)aint[1] + f1 * 255.0F);
                  aint[2] = (int)((float)aint[2] + f2 * 255.0F);
                  ++j;
               }
            } else {
               if(itemstack1.func_77973_b() != Items.field_151100_aR) {
                  return null;
               }

               float[] afloat = EntitySheep.func_175513_a(EnumDyeColor.func_176766_a(itemstack1.func_77960_j()));
               int l1 = (int)(afloat[0] * 255.0F);
               int i2 = (int)(afloat[1] * 255.0F);
               int j2 = (int)(afloat[2] * 255.0F);
               i += Math.max(l1, Math.max(i2, j2));
               aint[0] += l1;
               aint[1] += i2;
               aint[2] += j2;
               ++j;
            }
         }
      }

      if(itemarmor == null) {
         return null;
      } else {
         int i1 = aint[0] / j;
         int j1 = aint[1] / j;
         int k1 = aint[2] / j;
         float f3 = (float)i / (float)j;
         float f4 = (float)Math.max(i1, Math.max(j1, k1));
         i1 = (int)((float)i1 * f3 / f4);
         j1 = (int)((float)j1 * f3 / f4);
         k1 = (int)((float)k1 * f3 / f4);
         int lvt_12_3_ = (i1 << 8) + j1;
         lvt_12_3_ = (lvt_12_3_ << 8) + k1;
         itemarmor.func_82813_b(itemstack, lvt_12_3_);
         return itemstack;
      }
   }

   public int func_77570_a() {
      return 10;
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
