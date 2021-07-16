package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.world.World;

public class RecipesBanners {
   void func_179534_a(CraftingManager p_179534_1_) {
      for(EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
         p_179534_1_.func_92103_a(new ItemStack(Items.field_179564_cE, 1, enumdyecolor.func_176767_b()), new Object[]{"###", "###", " | ", Character.valueOf('#'), new ItemStack(Blocks.field_150325_L, 1, enumdyecolor.func_176765_a()), Character.valueOf('|'), Items.field_151055_y});
      }

      p_179534_1_.func_180302_a(new RecipesBanners.RecipeDuplicatePattern());
      p_179534_1_.func_180302_a(new RecipesBanners.RecipeAddPattern());
   }

   static class RecipeAddPattern implements IRecipe {
      private RecipeAddPattern() {
      }

      public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
         boolean flag = false;

         for(int i = 0; i < p_77569_1_.func_70302_i_(); ++i) {
            ItemStack itemstack = p_77569_1_.func_70301_a(i);
            if(itemstack != null && itemstack.func_77973_b() == Items.field_179564_cE) {
               if(flag) {
                  return false;
               }

               if(TileEntityBanner.func_175113_c(itemstack) >= 6) {
                  return false;
               }

               flag = true;
            }
         }

         if(!flag) {
            return false;
         } else {
            return this.func_179533_c(p_77569_1_) != null;
         }
      }

      public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
         ItemStack itemstack = null;

         for(int i = 0; i < p_77572_1_.func_70302_i_(); ++i) {
            ItemStack itemstack1 = p_77572_1_.func_70301_a(i);
            if(itemstack1 != null && itemstack1.func_77973_b() == Items.field_179564_cE) {
               itemstack = itemstack1.func_77946_l();
               itemstack.field_77994_a = 1;
               break;
            }
         }

         TileEntityBanner.EnumBannerPattern tileentitybanner$enumbannerpattern = this.func_179533_c(p_77572_1_);
         if(tileentitybanner$enumbannerpattern != null) {
            int k = 0;

            for(int j = 0; j < p_77572_1_.func_70302_i_(); ++j) {
               ItemStack itemstack2 = p_77572_1_.func_70301_a(j);
               if(itemstack2 != null && itemstack2.func_77973_b() == Items.field_151100_aR) {
                  k = itemstack2.func_77960_j();
                  break;
               }
            }

            NBTTagCompound nbttagcompound1 = itemstack.func_179543_a("BlockEntityTag", true);
            NBTTagList nbttaglist = null;
            if(nbttagcompound1.func_150297_b("Patterns", 9)) {
               nbttaglist = nbttagcompound1.func_150295_c("Patterns", 10);
            } else {
               nbttaglist = new NBTTagList();
               nbttagcompound1.func_74782_a("Patterns", nbttaglist);
            }

            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74778_a("Pattern", tileentitybanner$enumbannerpattern.func_177273_b());
            nbttagcompound.func_74768_a("Color", k);
            nbttaglist.func_74742_a(nbttagcompound);
         }

         return itemstack;
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

      private TileEntityBanner.EnumBannerPattern func_179533_c(InventoryCrafting p_179533_1_) {
         for(TileEntityBanner.EnumBannerPattern tileentitybanner$enumbannerpattern : TileEntityBanner.EnumBannerPattern.values()) {
            if(tileentitybanner$enumbannerpattern.func_177270_d()) {
               boolean flag = true;
               if(tileentitybanner$enumbannerpattern.func_177269_e()) {
                  boolean flag1 = false;
                  boolean flag2 = false;

                  for(int i = 0; i < p_179533_1_.func_70302_i_() && flag; ++i) {
                     ItemStack itemstack = p_179533_1_.func_70301_a(i);
                     if(itemstack != null && itemstack.func_77973_b() != Items.field_179564_cE) {
                        if(itemstack.func_77973_b() == Items.field_151100_aR) {
                           if(flag2) {
                              flag = false;
                              break;
                           }

                           flag2 = true;
                        } else {
                           if(flag1 || !itemstack.func_77969_a(tileentitybanner$enumbannerpattern.func_177272_f())) {
                              flag = false;
                              break;
                           }

                           flag1 = true;
                        }
                     }
                  }

                  if(!flag1) {
                     flag = false;
                  }
               } else if(p_179533_1_.func_70302_i_() == tileentitybanner$enumbannerpattern.func_177267_c().length * tileentitybanner$enumbannerpattern.func_177267_c()[0].length()) {
                  int j = -1;

                  for(int k = 0; k < p_179533_1_.func_70302_i_() && flag; ++k) {
                     int l = k / 3;
                     int i1 = k % 3;
                     ItemStack itemstack1 = p_179533_1_.func_70301_a(k);
                     if(itemstack1 != null && itemstack1.func_77973_b() != Items.field_179564_cE) {
                        if(itemstack1.func_77973_b() != Items.field_151100_aR) {
                           flag = false;
                           break;
                        }

                        if(j != -1 && j != itemstack1.func_77960_j()) {
                           flag = false;
                           break;
                        }

                        if(tileentitybanner$enumbannerpattern.func_177267_c()[l].charAt(i1) == 32) {
                           flag = false;
                           break;
                        }

                        j = itemstack1.func_77960_j();
                     } else if(tileentitybanner$enumbannerpattern.func_177267_c()[l].charAt(i1) != 32) {
                        flag = false;
                        break;
                     }
                  }
               } else {
                  flag = false;
               }

               if(flag) {
                  return tileentitybanner$enumbannerpattern;
               }
            }
         }

         return null;
      }
   }

   static class RecipeDuplicatePattern implements IRecipe {
      private RecipeDuplicatePattern() {
      }

      public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
         ItemStack itemstack = null;
         ItemStack itemstack1 = null;

         for(int i = 0; i < p_77569_1_.func_70302_i_(); ++i) {
            ItemStack itemstack2 = p_77569_1_.func_70301_a(i);
            if(itemstack2 != null) {
               if(itemstack2.func_77973_b() != Items.field_179564_cE) {
                  return false;
               }

               if(itemstack != null && itemstack1 != null) {
                  return false;
               }

               int j = TileEntityBanner.func_175111_b(itemstack2);
               boolean flag = TileEntityBanner.func_175113_c(itemstack2) > 0;
               if(itemstack != null) {
                  if(flag) {
                     return false;
                  }

                  if(j != TileEntityBanner.func_175111_b(itemstack)) {
                     return false;
                  }

                  itemstack1 = itemstack2;
               } else if(itemstack1 != null) {
                  if(!flag) {
                     return false;
                  }

                  if(j != TileEntityBanner.func_175111_b(itemstack1)) {
                     return false;
                  }

                  itemstack = itemstack2;
               } else if(flag) {
                  itemstack = itemstack2;
               } else {
                  itemstack1 = itemstack2;
               }
            }
         }

         return itemstack != null && itemstack1 != null;
      }

      public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
         for(int i = 0; i < p_77572_1_.func_70302_i_(); ++i) {
            ItemStack itemstack = p_77572_1_.func_70301_a(i);
            if(itemstack != null && TileEntityBanner.func_175113_c(itemstack) > 0) {
               ItemStack itemstack1 = itemstack.func_77946_l();
               itemstack1.field_77994_a = 1;
               return itemstack1;
            }
         }

         return null;
      }

      public int func_77570_a() {
         return 2;
      }

      public ItemStack func_77571_b() {
         return null;
      }

      public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
         ItemStack[] aitemstack = new ItemStack[p_179532_1_.func_70302_i_()];

         for(int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = p_179532_1_.func_70301_a(i);
            if(itemstack != null) {
               if(itemstack.func_77973_b().func_77634_r()) {
                  aitemstack[i] = new ItemStack(itemstack.func_77973_b().func_77668_q());
               } else if(itemstack.func_77942_o() && TileEntityBanner.func_175113_c(itemstack) > 0) {
                  aitemstack[i] = itemstack.func_77946_l();
                  aitemstack[i].field_77994_a = 1;
               }
            }
         }

         return aitemstack;
      }
   }
}
