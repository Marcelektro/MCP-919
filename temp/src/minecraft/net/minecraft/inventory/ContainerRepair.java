package net.minecraft.inventory;

import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContainerRepair extends Container {
   private static final Logger field_148326_f = LogManager.getLogger();
   private IInventory field_82852_f;
   private IInventory field_82853_g;
   private World field_82860_h;
   private BlockPos field_178156_j;
   public int field_82854_e;
   private int field_82856_l;
   private String field_82857_m;
   private final EntityPlayer field_82855_n;

   public ContainerRepair(InventoryPlayer p_i45806_1_, World p_i45806_2_, EntityPlayer p_i45806_3_) {
      this(p_i45806_1_, p_i45806_2_, BlockPos.field_177992_a, p_i45806_3_);
   }

   public ContainerRepair(InventoryPlayer p_i45807_1_, final World p_i45807_2_, final BlockPos p_i45807_3_, EntityPlayer p_i45807_4_) {
      this.field_82852_f = new InventoryCraftResult();
      this.field_82853_g = new InventoryBasic("Repair", true, 2) {
         public void func_70296_d() {
            super.func_70296_d();
            ContainerRepair.this.func_75130_a(this);
         }
      };
      this.field_178156_j = p_i45807_3_;
      this.field_82860_h = p_i45807_2_;
      this.field_82855_n = p_i45807_4_;
      this.func_75146_a(new Slot(this.field_82853_g, 0, 27, 47));
      this.func_75146_a(new Slot(this.field_82853_g, 1, 76, 47));
      this.func_75146_a(new Slot(this.field_82852_f, 2, 134, 47) {
         public boolean func_75214_a(ItemStack p_75214_1_) {
            return false;
         }

         public boolean func_82869_a(EntityPlayer p_82869_1_) {
            return (p_82869_1_.field_71075_bZ.field_75098_d || p_82869_1_.field_71068_ca >= ContainerRepair.this.field_82854_e) && ContainerRepair.this.field_82854_e > 0 && this.func_75216_d();
         }

         public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
            if(!p_82870_1_.field_71075_bZ.field_75098_d) {
               p_82870_1_.func_82242_a(-ContainerRepair.this.field_82854_e);
            }

            ContainerRepair.this.field_82853_g.func_70299_a(0, (ItemStack)null);
            if(ContainerRepair.this.field_82856_l > 0) {
               ItemStack itemstack = ContainerRepair.this.field_82853_g.func_70301_a(1);
               if(itemstack != null && itemstack.field_77994_a > ContainerRepair.this.field_82856_l) {
                  itemstack.field_77994_a -= ContainerRepair.this.field_82856_l;
                  ContainerRepair.this.field_82853_g.func_70299_a(1, itemstack);
               } else {
                  ContainerRepair.this.field_82853_g.func_70299_a(1, (ItemStack)null);
               }
            } else {
               ContainerRepair.this.field_82853_g.func_70299_a(1, (ItemStack)null);
            }

            ContainerRepair.this.field_82854_e = 0;
            IBlockState iblockstate = p_i45807_2_.func_180495_p(p_i45807_3_);
            if(!p_82870_1_.field_71075_bZ.field_75098_d && !p_i45807_2_.field_72995_K && iblockstate.func_177230_c() == Blocks.field_150467_bQ && p_82870_1_.func_70681_au().nextFloat() < 0.12F) {
               int l = ((Integer)iblockstate.func_177229_b(BlockAnvil.field_176505_b)).intValue();
               ++l;
               if(l > 2) {
                  p_i45807_2_.func_175698_g(p_i45807_3_);
                  p_i45807_2_.func_175718_b(1020, p_i45807_3_, 0);
               } else {
                  p_i45807_2_.func_180501_a(p_i45807_3_, iblockstate.func_177226_a(BlockAnvil.field_176505_b, Integer.valueOf(l)), 2);
                  p_i45807_2_.func_175718_b(1021, p_i45807_3_, 0);
               }
            } else if(!p_i45807_2_.field_72995_K) {
               p_i45807_2_.func_175718_b(1021, p_i45807_3_, 0);
            }

         }
      });

      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(p_i45807_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for(int k = 0; k < 9; ++k) {
         this.func_75146_a(new Slot(p_i45807_1_, k, 8 + k * 18, 142));
      }

   }

   public void func_75130_a(IInventory p_75130_1_) {
      super.func_75130_a(p_75130_1_);
      if(p_75130_1_ == this.field_82853_g) {
         this.func_82848_d();
      }

   }

   public void func_82848_d() {
      int i = 0;
      int j = 1;
      int k = 1;
      int l = 1;
      int i1 = 2;
      int j1 = 1;
      int k1 = 1;
      ItemStack itemstack = this.field_82853_g.func_70301_a(0);
      this.field_82854_e = 1;
      int l1 = 0;
      int i2 = 0;
      int j2 = 0;
      if(itemstack == null) {
         this.field_82852_f.func_70299_a(0, (ItemStack)null);
         this.field_82854_e = 0;
      } else {
         ItemStack itemstack1 = itemstack.func_77946_l();
         ItemStack itemstack2 = this.field_82853_g.func_70301_a(1);
         Map<Integer, Integer> map = EnchantmentHelper.func_82781_a(itemstack1);
         boolean flag = false;
         i2 = i2 + itemstack.func_82838_A() + (itemstack2 == null?0:itemstack2.func_82838_A());
         this.field_82856_l = 0;
         if(itemstack2 != null) {
            flag = itemstack2.func_77973_b() == Items.field_151134_bR && Items.field_151134_bR.func_92110_g(itemstack2).func_74745_c() > 0;
            if(itemstack1.func_77984_f() && itemstack1.func_77973_b().func_82789_a(itemstack, itemstack2)) {
               int j4 = Math.min(itemstack1.func_77952_i(), itemstack1.func_77958_k() / 4);
               if(j4 <= 0) {
                  this.field_82852_f.func_70299_a(0, (ItemStack)null);
                  this.field_82854_e = 0;
                  return;
               }

               int l4;
               for(l4 = 0; j4 > 0 && l4 < itemstack2.field_77994_a; ++l4) {
                  int j5 = itemstack1.func_77952_i() - j4;
                  itemstack1.func_77964_b(j5);
                  ++l1;
                  j4 = Math.min(itemstack1.func_77952_i(), itemstack1.func_77958_k() / 4);
               }

               this.field_82856_l = l4;
            } else {
               if(!flag && (itemstack1.func_77973_b() != itemstack2.func_77973_b() || !itemstack1.func_77984_f())) {
                  this.field_82852_f.func_70299_a(0, (ItemStack)null);
                  this.field_82854_e = 0;
                  return;
               }

               if(itemstack1.func_77984_f() && !flag) {
                  int k2 = itemstack.func_77958_k() - itemstack.func_77952_i();
                  int l2 = itemstack2.func_77958_k() - itemstack2.func_77952_i();
                  int i3 = l2 + itemstack1.func_77958_k() * 12 / 100;
                  int j3 = k2 + i3;
                  int k3 = itemstack1.func_77958_k() - j3;
                  if(k3 < 0) {
                     k3 = 0;
                  }

                  if(k3 < itemstack1.func_77960_j()) {
                     itemstack1.func_77964_b(k3);
                     l1 += 2;
                  }
               }

               Map<Integer, Integer> map1 = EnchantmentHelper.func_82781_a(itemstack2);
               Iterator iterator1 = map1.keySet().iterator();

               while(iterator1.hasNext()) {
                  int i5 = ((Integer)iterator1.next()).intValue();
                  Enchantment enchantment = Enchantment.func_180306_c(i5);
                  if(enchantment != null) {
                     int k5 = map.containsKey(Integer.valueOf(i5))?((Integer)map.get(Integer.valueOf(i5))).intValue():0;
                     int l3 = ((Integer)map1.get(Integer.valueOf(i5))).intValue();
                     int i6;
                     if(k5 == l3) {
                        ++l3;
                        i6 = l3;
                     } else {
                        i6 = Math.max(l3, k5);
                     }

                     l3 = i6;
                     boolean flag1 = enchantment.func_92089_a(itemstack);
                     if(this.field_82855_n.field_71075_bZ.field_75098_d || itemstack.func_77973_b() == Items.field_151134_bR) {
                        flag1 = true;
                     }

                     Iterator iterator = map.keySet().iterator();

                     while(iterator.hasNext()) {
                        int i4 = ((Integer)iterator.next()).intValue();
                        if(i4 != i5 && !enchantment.func_77326_a(Enchantment.func_180306_c(i4))) {
                           flag1 = false;
                           ++l1;
                        }
                     }

                     if(flag1) {
                        if(l3 > enchantment.func_77325_b()) {
                           l3 = enchantment.func_77325_b();
                        }

                        map.put(Integer.valueOf(i5), Integer.valueOf(l3));
                        int l5 = 0;
                        switch(enchantment.func_77324_c()) {
                        case 1:
                           l5 = 8;
                           break;
                        case 2:
                           l5 = 4;
                        case 3:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        default:
                           break;
                        case 5:
                           l5 = 2;
                           break;
                        case 10:
                           l5 = 1;
                        }

                        if(flag) {
                           l5 = Math.max(1, l5 / 2);
                        }

                        l1 += l5 * l3;
                     }
                  }
               }
            }
         }

         if(StringUtils.isBlank(this.field_82857_m)) {
            if(itemstack.func_82837_s()) {
               j2 = 1;
               l1 += j2;
               itemstack1.func_135074_t();
            }
         } else if(!this.field_82857_m.equals(itemstack.func_82833_r())) {
            j2 = 1;
            l1 += j2;
            itemstack1.func_151001_c(this.field_82857_m);
         }

         this.field_82854_e = i2 + l1;
         if(l1 <= 0) {
            itemstack1 = null;
         }

         if(j2 == l1 && j2 > 0 && this.field_82854_e >= 40) {
            this.field_82854_e = 39;
         }

         if(this.field_82854_e >= 40 && !this.field_82855_n.field_71075_bZ.field_75098_d) {
            itemstack1 = null;
         }

         if(itemstack1 != null) {
            int k4 = itemstack1.func_82838_A();
            if(itemstack2 != null && k4 < itemstack2.func_82838_A()) {
               k4 = itemstack2.func_82838_A();
            }

            k4 = k4 * 2 + 1;
            itemstack1.func_82841_c(k4);
            EnchantmentHelper.func_82782_a(map, itemstack1);
         }

         this.field_82852_f.func_70299_a(0, itemstack1);
         this.func_75142_b();
      }
   }

   public void func_75132_a(ICrafting p_75132_1_) {
      super.func_75132_a(p_75132_1_);
      p_75132_1_.func_71112_a(this, 0, this.field_82854_e);
   }

   public void func_75137_b(int p_75137_1_, int p_75137_2_) {
      if(p_75137_1_ == 0) {
         this.field_82854_e = p_75137_2_;
      }

   }

   public void func_75134_a(EntityPlayer p_75134_1_) {
      super.func_75134_a(p_75134_1_);
      if(!this.field_82860_h.field_72995_K) {
         for(int i = 0; i < this.field_82853_g.func_70302_i_(); ++i) {
            ItemStack itemstack = this.field_82853_g.func_70304_b(i);
            if(itemstack != null) {
               p_75134_1_.func_71019_a(itemstack, false);
            }
         }

      }
   }

   public boolean func_75145_c(EntityPlayer p_75145_1_) {
      return this.field_82860_h.func_180495_p(this.field_178156_j).func_177230_c() != Blocks.field_150467_bQ?false:p_75145_1_.func_70092_e((double)this.field_178156_j.func_177958_n() + 0.5D, (double)this.field_178156_j.func_177956_o() + 0.5D, (double)this.field_178156_j.func_177952_p() + 0.5D) <= 64.0D;
   }

   public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
      if(slot != null && slot.func_75216_d()) {
         ItemStack itemstack1 = slot.func_75211_c();
         itemstack = itemstack1.func_77946_l();
         if(p_82846_2_ == 2) {
            if(!this.func_75135_a(itemstack1, 3, 39, true)) {
               return null;
            }

            slot.func_75220_a(itemstack1, itemstack);
         } else if(p_82846_2_ != 0 && p_82846_2_ != 1) {
            if(p_82846_2_ >= 3 && p_82846_2_ < 39 && !this.func_75135_a(itemstack1, 0, 2, false)) {
               return null;
            }
         } else if(!this.func_75135_a(itemstack1, 3, 39, false)) {
            return null;
         }

         if(itemstack1.field_77994_a == 0) {
            slot.func_75215_d((ItemStack)null);
         } else {
            slot.func_75218_e();
         }

         if(itemstack1.field_77994_a == itemstack.field_77994_a) {
            return null;
         }

         slot.func_82870_a(p_82846_1_, itemstack1);
      }

      return itemstack;
   }

   public void func_82850_a(String p_82850_1_) {
      this.field_82857_m = p_82850_1_;
      if(this.func_75139_a(2).func_75216_d()) {
         ItemStack itemstack = this.func_75139_a(2).func_75211_c();
         if(StringUtils.isBlank(p_82850_1_)) {
            itemstack.func_135074_t();
         } else {
            itemstack.func_151001_c(this.field_82857_m);
         }
      }

      this.func_82848_d();
   }
}
