package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryCrafting implements IInventory {
   private final ItemStack[] field_70466_a;
   private final int field_70464_b;
   private final int field_174924_c;
   private final Container field_70465_c;

   public InventoryCrafting(Container p_i1807_1_, int p_i1807_2_, int p_i1807_3_) {
      int i = p_i1807_2_ * p_i1807_3_;
      this.field_70466_a = new ItemStack[i];
      this.field_70465_c = p_i1807_1_;
      this.field_70464_b = p_i1807_2_;
      this.field_174924_c = p_i1807_3_;
   }

   public int func_70302_i_() {
      return this.field_70466_a.length;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return p_70301_1_ >= this.func_70302_i_()?null:this.field_70466_a[p_70301_1_];
   }

   public ItemStack func_70463_b(int p_70463_1_, int p_70463_2_) {
      return p_70463_1_ >= 0 && p_70463_1_ < this.field_70464_b && p_70463_2_ >= 0 && p_70463_2_ <= this.field_174924_c?this.func_70301_a(p_70463_1_ + p_70463_2_ * this.field_70464_b):null;
   }

   public String func_70005_c_() {
      return "container.crafting";
   }

   public boolean func_145818_k_() {
      return false;
   }

   public IChatComponent func_145748_c_() {
      return (IChatComponent)(this.func_145818_k_()?new ChatComponentText(this.func_70005_c_()):new ChatComponentTranslation(this.func_70005_c_(), new Object[0]));
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if(this.field_70466_a[p_70304_1_] != null) {
         ItemStack itemstack = this.field_70466_a[p_70304_1_];
         this.field_70466_a[p_70304_1_] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if(this.field_70466_a[p_70298_1_] != null) {
         if(this.field_70466_a[p_70298_1_].field_77994_a <= p_70298_2_) {
            ItemStack itemstack1 = this.field_70466_a[p_70298_1_];
            this.field_70466_a[p_70298_1_] = null;
            this.field_70465_c.func_75130_a(this);
            return itemstack1;
         } else {
            ItemStack itemstack = this.field_70466_a[p_70298_1_].func_77979_a(p_70298_2_);
            if(this.field_70466_a[p_70298_1_].field_77994_a == 0) {
               this.field_70466_a[p_70298_1_] = null;
            }

            this.field_70465_c.func_75130_a(this);
            return itemstack;
         }
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.field_70466_a[p_70299_1_] = p_70299_2_;
      this.field_70465_c.func_75130_a(this);
   }

   public int func_70297_j_() {
      return 64;
   }

   public void func_70296_d() {
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
      for(int i = 0; i < this.field_70466_a.length; ++i) {
         this.field_70466_a[i] = null;
      }

   }

   public int func_174923_h() {
      return this.field_174924_c;
   }

   public int func_174922_i() {
      return this.field_70464_b;
   }
}
