package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;

public class TileEntityDispenser extends TileEntityLockable implements IInventory {
   private static final Random field_174913_f = new Random();
   private ItemStack[] field_146022_i = new ItemStack[9];
   protected String field_146020_a;

   public int func_70302_i_() {
      return 9;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return this.field_146022_i[p_70301_1_];
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if(this.field_146022_i[p_70298_1_] != null) {
         if(this.field_146022_i[p_70298_1_].field_77994_a <= p_70298_2_) {
            ItemStack itemstack1 = this.field_146022_i[p_70298_1_];
            this.field_146022_i[p_70298_1_] = null;
            this.func_70296_d();
            return itemstack1;
         } else {
            ItemStack itemstack = this.field_146022_i[p_70298_1_].func_77979_a(p_70298_2_);
            if(this.field_146022_i[p_70298_1_].field_77994_a == 0) {
               this.field_146022_i[p_70298_1_] = null;
            }

            this.func_70296_d();
            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if(this.field_146022_i[p_70304_1_] != null) {
         ItemStack itemstack = this.field_146022_i[p_70304_1_];
         this.field_146022_i[p_70304_1_] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public int func_146017_i() {
      int i = -1;
      int j = 1;

      for(int k = 0; k < this.field_146022_i.length; ++k) {
         if(this.field_146022_i[k] != null && field_174913_f.nextInt(j++) == 0) {
            i = k;
         }
      }

      return i;
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.field_146022_i[p_70299_1_] = p_70299_2_;
      if(p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
         p_70299_2_.field_77994_a = this.func_70297_j_();
      }

      this.func_70296_d();
   }

   public int func_146019_a(ItemStack p_146019_1_) {
      for(int i = 0; i < this.field_146022_i.length; ++i) {
         if(this.field_146022_i[i] == null || this.field_146022_i[i].func_77973_b() == null) {
            this.func_70299_a(i, p_146019_1_);
            return i;
         }
      }

      return -1;
   }

   public String func_70005_c_() {
      return this.func_145818_k_()?this.field_146020_a:"container.dispenser";
   }

   public void func_146018_a(String p_146018_1_) {
      this.field_146020_a = p_146018_1_;
   }

   public boolean func_145818_k_() {
      return this.field_146020_a != null;
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
      this.field_146022_i = new ItemStack[this.func_70302_i_()];

      for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
         int j = nbttagcompound.func_74771_c("Slot") & 255;
         if(j >= 0 && j < this.field_146022_i.length) {
            this.field_146022_i[j] = ItemStack.func_77949_a(nbttagcompound);
         }
      }

      if(p_145839_1_.func_150297_b("CustomName", 8)) {
         this.field_146020_a = p_145839_1_.func_74779_i("CustomName");
      }

   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.field_146022_i.length; ++i) {
         if(this.field_146022_i[i] != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)i);
            this.field_146022_i[i].func_77955_b(nbttagcompound);
            nbttaglist.func_74742_a(nbttagcompound);
         }
      }

      p_145841_1_.func_74782_a("Items", nbttaglist);
      if(this.func_145818_k_()) {
         p_145841_1_.func_74778_a("CustomName", this.field_146020_a);
      }

   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_145850_b.func_175625_s(this.field_174879_c) != this?false:p_70300_1_.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.5D, (double)this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public String func_174875_k() {
      return "minecraft:dispenser";
   }

   public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
      return new ContainerDispenser(p_174876_1_, this);
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
      for(int i = 0; i < this.field_146022_i.length; ++i) {
         this.field_146022_i[i] = null;
      }

   }
}
