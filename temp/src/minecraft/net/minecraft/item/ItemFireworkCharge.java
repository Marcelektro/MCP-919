package net.minecraft.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.StatCollector;

public class ItemFireworkCharge extends Item {
   public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
      if(p_82790_2_ != 1) {
         return super.func_82790_a(p_82790_1_, p_82790_2_);
      } else {
         NBTBase nbtbase = func_150903_a(p_82790_1_, "Colors");
         if(!(nbtbase instanceof NBTTagIntArray)) {
            return 9079434;
         } else {
            NBTTagIntArray nbttagintarray = (NBTTagIntArray)nbtbase;
            int[] aint = nbttagintarray.func_150302_c();
            if(aint.length == 1) {
               return aint[0];
            } else {
               int i = 0;
               int j = 0;
               int k = 0;

               for(int l : aint) {
                  i += (l & 16711680) >> 16;
                  j += (l & '\uff00') >> 8;
                  k += (l & 255) >> 0;
               }

               i = i / aint.length;
               j = j / aint.length;
               k = k / aint.length;
               return i << 16 | j << 8 | k;
            }
         }
      }
   }

   public static NBTBase func_150903_a(ItemStack p_150903_0_, String p_150903_1_) {
      if(p_150903_0_.func_77942_o()) {
         NBTTagCompound nbttagcompound = p_150903_0_.func_77978_p().func_74775_l("Explosion");
         if(nbttagcompound != null) {
            return nbttagcompound.func_74781_a(p_150903_1_);
         }
      }

      return null;
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      if(p_77624_1_.func_77942_o()) {
         NBTTagCompound nbttagcompound = p_77624_1_.func_77978_p().func_74775_l("Explosion");
         if(nbttagcompound != null) {
            func_150902_a(nbttagcompound, p_77624_3_);
         }
      }

   }

   public static void func_150902_a(NBTTagCompound p_150902_0_, List<String> p_150902_1_) {
      byte b0 = p_150902_0_.func_74771_c("Type");
      if(b0 >= 0 && b0 <= 4) {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.type." + b0).trim());
      } else {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.type").trim());
      }

      int[] aint = p_150902_0_.func_74759_k("Colors");
      if(aint.length > 0) {
         boolean flag = true;
         String s = "";

         for(int i : aint) {
            if(!flag) {
               s = s + ", ";
            }

            flag = false;
            boolean flag1 = false;

            for(int j = 0; j < ItemDye.field_150922_c.length; ++j) {
               if(i == ItemDye.field_150922_c[j]) {
                  flag1 = true;
                  s = s + StatCollector.func_74838_a("item.fireworksCharge." + EnumDyeColor.func_176766_a(j).func_176762_d());
                  break;
               }
            }

            if(!flag1) {
               s = s + StatCollector.func_74838_a("item.fireworksCharge.customColor");
            }
         }

         p_150902_1_.add(s);
      }

      int[] aint1 = p_150902_0_.func_74759_k("FadeColors");
      if(aint1.length > 0) {
         boolean flag2 = true;
         String s1 = StatCollector.func_74838_a("item.fireworksCharge.fadeTo") + " ";

         for(int l : aint1) {
            if(!flag2) {
               s1 = s1 + ", ";
            }

            flag2 = false;
            boolean flag5 = false;

            for(int k = 0; k < 16; ++k) {
               if(l == ItemDye.field_150922_c[k]) {
                  flag5 = true;
                  s1 = s1 + StatCollector.func_74838_a("item.fireworksCharge." + EnumDyeColor.func_176766_a(k).func_176762_d());
                  break;
               }
            }

            if(!flag5) {
               s1 = s1 + StatCollector.func_74838_a("item.fireworksCharge.customColor");
            }
         }

         p_150902_1_.add(s1);
      }

      boolean flag3 = p_150902_0_.func_74767_n("Trail");
      if(flag3) {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.trail"));
      }

      boolean flag4 = p_150902_0_.func_74767_n("Flicker");
      if(flag4) {
         p_150902_1_.add(StatCollector.func_74838_a("item.fireworksCharge.flicker"));
      }

   }
}
