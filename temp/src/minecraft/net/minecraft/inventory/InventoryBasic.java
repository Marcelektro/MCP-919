package net.minecraft.inventory;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryBasic implements IInventory {
   private String field_70483_a;
   private int field_70481_b;
   private ItemStack[] field_70482_c;
   private List<IInvBasic> field_70480_d;
   private boolean field_94051_e;

   public InventoryBasic(String p_i1561_1_, boolean p_i1561_2_, int p_i1561_3_) {
      this.field_70483_a = p_i1561_1_;
      this.field_94051_e = p_i1561_2_;
      this.field_70481_b = p_i1561_3_;
      this.field_70482_c = new ItemStack[p_i1561_3_];
   }

   public InventoryBasic(IChatComponent p_i45902_1_, int p_i45902_2_) {
      this(p_i45902_1_.func_150260_c(), true, p_i45902_2_);
   }

   public void func_110134_a(IInvBasic p_110134_1_) {
      if(this.field_70480_d == null) {
         this.field_70480_d = Lists.<IInvBasic>newArrayList();
      }

      this.field_70480_d.add(p_110134_1_);
   }

   public void func_110132_b(IInvBasic p_110132_1_) {
      this.field_70480_d.remove(p_110132_1_);
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return p_70301_1_ >= 0 && p_70301_1_ < this.field_70482_c.length?this.field_70482_c[p_70301_1_]:null;
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if(this.field_70482_c[p_70298_1_] != null) {
         if(this.field_70482_c[p_70298_1_].field_77994_a <= p_70298_2_) {
            ItemStack itemstack1 = this.field_70482_c[p_70298_1_];
            this.field_70482_c[p_70298_1_] = null;
            this.func_70296_d();
            return itemstack1;
         } else {
            ItemStack itemstack = this.field_70482_c[p_70298_1_].func_77979_a(p_70298_2_);
            if(this.field_70482_c[p_70298_1_].field_77994_a == 0) {
               this.field_70482_c[p_70298_1_] = null;
            }

            this.func_70296_d();
            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_174894_a(ItemStack p_174894_1_) {
      ItemStack itemstack = p_174894_1_.func_77946_l();

      for(int i = 0; i < this.field_70481_b; ++i) {
         ItemStack itemstack1 = this.func_70301_a(i);
         if(itemstack1 == null) {
            this.func_70299_a(i, itemstack);
            this.func_70296_d();
            return null;
         }

         if(ItemStack.func_179545_c(itemstack1, itemstack)) {
            int j = Math.min(this.func_70297_j_(), itemstack1.func_77976_d());
            int k = Math.min(itemstack.field_77994_a, j - itemstack1.field_77994_a);
            if(k > 0) {
               itemstack1.field_77994_a += k;
               itemstack.field_77994_a -= k;
               if(itemstack.field_77994_a <= 0) {
                  this.func_70296_d();
                  return null;
               }
            }
         }
      }

      if(itemstack.field_77994_a != p_174894_1_.field_77994_a) {
         this.func_70296_d();
      }

      return itemstack;
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if(this.field_70482_c[p_70304_1_] != null) {
         ItemStack itemstack = this.field_70482_c[p_70304_1_];
         this.field_70482_c[p_70304_1_] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.field_70482_c[p_70299_1_] = p_70299_2_;
      if(p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
         p_70299_2_.field_77994_a = this.func_70297_j_();
      }

      this.func_70296_d();
   }

   public int func_70302_i_() {
      return this.field_70481_b;
   }

   public String func_70005_c_() {
      return this.field_70483_a;
   }

   public boolean func_145818_k_() {
      return this.field_94051_e;
   }

   public void func_110133_a(String p_110133_1_) {
      this.field_94051_e = true;
      this.field_70483_a = p_110133_1_;
   }

   public IChatComponent func_145748_c_() {
      return (IChatComponent)(this.func_145818_k_()?new ChatComponentText(this.func_70005_c_()):new ChatComponentTranslation(this.func_70005_c_(), new Object[0]));
   }

   public int func_70297_j_() {
      return 64;
   }

   public void func_70296_d() {
      if(this.field_70480_d != null) {
         for(int i = 0; i < this.field_70480_d.size(); ++i) {
            ((IInvBasic)this.field_70480_d.get(i)).func_76316_a(this);
         }
      }

   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return true;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public int func_174887_a_(int p_174887_1_) {
      return 0;
   }

   public void func_174885_b(int p_174885_1_, int p_174885_2_) {
   }

   public int func_174890_g() {
      return 0;
   }

   public void func_174888_l() {
      for(int i = 0; i < this.field_70482_c.length; ++i) {
         this.field_70482_c[i] = null;
      }

   }
}
