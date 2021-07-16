package net.minecraft.entity.player;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;

public class InventoryPlayer implements IInventory {
   public ItemStack[] field_70462_a = new ItemStack[36];
   public ItemStack[] field_70460_b = new ItemStack[4];
   public int field_70461_c;
   public EntityPlayer field_70458_d;
   private ItemStack field_70457_g;
   public boolean field_70459_e;

   public InventoryPlayer(EntityPlayer p_i1750_1_) {
      this.field_70458_d = p_i1750_1_;
   }

   public ItemStack func_70448_g() {
      return this.field_70461_c < 9 && this.field_70461_c >= 0?this.field_70462_a[this.field_70461_c]:null;
   }

   public static int func_70451_h() {
      return 9;
   }

   private int func_146029_c(Item p_146029_1_) {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null && this.field_70462_a[i].func_77973_b() == p_146029_1_) {
            return i;
         }
      }

      return -1;
   }

   private int func_146024_c(Item p_146024_1_, int p_146024_2_) {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null && this.field_70462_a[i].func_77973_b() == p_146024_1_ && this.field_70462_a[i].func_77960_j() == p_146024_2_) {
            return i;
         }
      }

      return -1;
   }

   private int func_70432_d(ItemStack p_70432_1_) {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null && this.field_70462_a[i].func_77973_b() == p_70432_1_.func_77973_b() && this.field_70462_a[i].func_77985_e() && this.field_70462_a[i].field_77994_a < this.field_70462_a[i].func_77976_d() && this.field_70462_a[i].field_77994_a < this.func_70297_j_() && (!this.field_70462_a[i].func_77981_g() || this.field_70462_a[i].func_77960_j() == p_70432_1_.func_77960_j()) && ItemStack.func_77970_a(this.field_70462_a[i], p_70432_1_)) {
            return i;
         }
      }

      return -1;
   }

   public int func_70447_i() {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] == null) {
            return i;
         }
      }

      return -1;
   }

   public void func_146030_a(Item p_146030_1_, int p_146030_2_, boolean p_146030_3_, boolean p_146030_4_) {
      ItemStack itemstack = this.func_70448_g();
      int i = p_146030_3_?this.func_146024_c(p_146030_1_, p_146030_2_):this.func_146029_c(p_146030_1_);
      if(i >= 0 && i < 9) {
         this.field_70461_c = i;
      } else if(p_146030_4_ && p_146030_1_ != null) {
         int j = this.func_70447_i();
         if(j >= 0 && j < 9) {
            this.field_70461_c = j;
         }

         if(itemstack == null || !itemstack.func_77956_u() || this.func_146024_c(itemstack.func_77973_b(), itemstack.func_77952_i()) != this.field_70461_c) {
            int k = this.func_146024_c(p_146030_1_, p_146030_2_);
            int l;
            if(k >= 0) {
               l = this.field_70462_a[k].field_77994_a;
               this.field_70462_a[k] = this.field_70462_a[this.field_70461_c];
            } else {
               l = 1;
            }

            this.field_70462_a[this.field_70461_c] = new ItemStack(p_146030_1_, l, p_146030_2_);
         }

      }
   }

   public void func_70453_c(int p_70453_1_) {
      if(p_70453_1_ > 0) {
         p_70453_1_ = 1;
      }

      if(p_70453_1_ < 0) {
         p_70453_1_ = -1;
      }

      for(this.field_70461_c -= p_70453_1_; this.field_70461_c < 0; this.field_70461_c += 9) {
         ;
      }

      while(this.field_70461_c >= 9) {
         this.field_70461_c -= 9;
      }

   }

   public int func_174925_a(Item p_174925_1_, int p_174925_2_, int p_174925_3_, NBTTagCompound p_174925_4_) {
      int i = 0;

      for(int j = 0; j < this.field_70462_a.length; ++j) {
         ItemStack itemstack = this.field_70462_a[j];
         if(itemstack != null && (p_174925_1_ == null || itemstack.func_77973_b() == p_174925_1_) && (p_174925_2_ <= -1 || itemstack.func_77960_j() == p_174925_2_) && (p_174925_4_ == null || NBTUtil.func_181123_a(p_174925_4_, itemstack.func_77978_p(), true))) {
            int k = p_174925_3_ <= 0?itemstack.field_77994_a:Math.min(p_174925_3_ - i, itemstack.field_77994_a);
            i += k;
            if(p_174925_3_ != 0) {
               this.field_70462_a[j].field_77994_a -= k;
               if(this.field_70462_a[j].field_77994_a == 0) {
                  this.field_70462_a[j] = null;
               }

               if(p_174925_3_ > 0 && i >= p_174925_3_) {
                  return i;
               }
            }
         }
      }

      for(int l = 0; l < this.field_70460_b.length; ++l) {
         ItemStack itemstack1 = this.field_70460_b[l];
         if(itemstack1 != null && (p_174925_1_ == null || itemstack1.func_77973_b() == p_174925_1_) && (p_174925_2_ <= -1 || itemstack1.func_77960_j() == p_174925_2_) && (p_174925_4_ == null || NBTUtil.func_181123_a(p_174925_4_, itemstack1.func_77978_p(), false))) {
            int j1 = p_174925_3_ <= 0?itemstack1.field_77994_a:Math.min(p_174925_3_ - i, itemstack1.field_77994_a);
            i += j1;
            if(p_174925_3_ != 0) {
               this.field_70460_b[l].field_77994_a -= j1;
               if(this.field_70460_b[l].field_77994_a == 0) {
                  this.field_70460_b[l] = null;
               }

               if(p_174925_3_ > 0 && i >= p_174925_3_) {
                  return i;
               }
            }
         }
      }

      if(this.field_70457_g != null) {
         if(p_174925_1_ != null && this.field_70457_g.func_77973_b() != p_174925_1_) {
            return i;
         }

         if(p_174925_2_ > -1 && this.field_70457_g.func_77960_j() != p_174925_2_) {
            return i;
         }

         if(p_174925_4_ != null && !NBTUtil.func_181123_a(p_174925_4_, this.field_70457_g.func_77978_p(), false)) {
            return i;
         }

         int i1 = p_174925_3_ <= 0?this.field_70457_g.field_77994_a:Math.min(p_174925_3_ - i, this.field_70457_g.field_77994_a);
         i += i1;
         if(p_174925_3_ != 0) {
            this.field_70457_g.field_77994_a -= i1;
            if(this.field_70457_g.field_77994_a == 0) {
               this.field_70457_g = null;
            }

            if(p_174925_3_ > 0 && i >= p_174925_3_) {
               return i;
            }
         }
      }

      return i;
   }

   private int func_70452_e(ItemStack p_70452_1_) {
      Item item = p_70452_1_.func_77973_b();
      int i = p_70452_1_.field_77994_a;
      int j = this.func_70432_d(p_70452_1_);
      if(j < 0) {
         j = this.func_70447_i();
      }

      if(j < 0) {
         return i;
      } else {
         if(this.field_70462_a[j] == null) {
            this.field_70462_a[j] = new ItemStack(item, 0, p_70452_1_.func_77960_j());
            if(p_70452_1_.func_77942_o()) {
               this.field_70462_a[j].func_77982_d((NBTTagCompound)p_70452_1_.func_77978_p().func_74737_b());
            }
         }

         int k = i;
         if(i > this.field_70462_a[j].func_77976_d() - this.field_70462_a[j].field_77994_a) {
            k = this.field_70462_a[j].func_77976_d() - this.field_70462_a[j].field_77994_a;
         }

         if(k > this.func_70297_j_() - this.field_70462_a[j].field_77994_a) {
            k = this.func_70297_j_() - this.field_70462_a[j].field_77994_a;
         }

         if(k == 0) {
            return i;
         } else {
            i = i - k;
            this.field_70462_a[j].field_77994_a += k;
            this.field_70462_a[j].field_77992_b = 5;
            return i;
         }
      }
   }

   public void func_70429_k() {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null) {
            this.field_70462_a[i].func_77945_a(this.field_70458_d.field_70170_p, this.field_70458_d, i, this.field_70461_c == i);
         }
      }

   }

   public boolean func_146026_a(Item p_146026_1_) {
      int i = this.func_146029_c(p_146026_1_);
      if(i < 0) {
         return false;
      } else {
         if(--this.field_70462_a[i].field_77994_a <= 0) {
            this.field_70462_a[i] = null;
         }

         return true;
      }
   }

   public boolean func_146028_b(Item p_146028_1_) {
      int i = this.func_146029_c(p_146028_1_);
      return i >= 0;
   }

   public boolean func_70441_a(final ItemStack p_70441_1_) {
      if(p_70441_1_ != null && p_70441_1_.field_77994_a != 0 && p_70441_1_.func_77973_b() != null) {
         try {
            if(p_70441_1_.func_77951_h()) {
               int j = this.func_70447_i();
               if(j >= 0) {
                  this.field_70462_a[j] = ItemStack.func_77944_b(p_70441_1_);
                  this.field_70462_a[j].field_77992_b = 5;
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else if(this.field_70458_d.field_71075_bZ.field_75098_d) {
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else {
                  return false;
               }
            } else {
               int i;
               while(true) {
                  i = p_70441_1_.field_77994_a;
                  p_70441_1_.field_77994_a = this.func_70452_e(p_70441_1_);
                  if(p_70441_1_.field_77994_a <= 0 || p_70441_1_.field_77994_a >= i) {
                     break;
                  }
               }

               if(p_70441_1_.field_77994_a == i && this.field_70458_d.field_71075_bZ.field_75098_d) {
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else {
                  return p_70441_1_.field_77994_a < i;
               }
            }
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding item to inventory");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being added");
            crashreportcategory.func_71507_a("Item ID", Integer.valueOf(Item.func_150891_b(p_70441_1_.func_77973_b())));
            crashreportcategory.func_71507_a("Item data", Integer.valueOf(p_70441_1_.func_77960_j()));
            crashreportcategory.func_71500_a("Item name", new Callable<String>() {
               public String call() throws Exception {
                  return p_70441_1_.func_82833_r();
               }
            });
            throw new ReportedException(crashreport);
         }
      } else {
         return false;
      }
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      ItemStack[] aitemstack = this.field_70462_a;
      if(p_70298_1_ >= this.field_70462_a.length) {
         aitemstack = this.field_70460_b;
         p_70298_1_ -= this.field_70462_a.length;
      }

      if(aitemstack[p_70298_1_] != null) {
         if(aitemstack[p_70298_1_].field_77994_a <= p_70298_2_) {
            ItemStack itemstack1 = aitemstack[p_70298_1_];
            aitemstack[p_70298_1_] = null;
            return itemstack1;
         } else {
            ItemStack itemstack = aitemstack[p_70298_1_].func_77979_a(p_70298_2_);
            if(aitemstack[p_70298_1_].field_77994_a == 0) {
               aitemstack[p_70298_1_] = null;
            }

            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      ItemStack[] aitemstack = this.field_70462_a;
      if(p_70304_1_ >= this.field_70462_a.length) {
         aitemstack = this.field_70460_b;
         p_70304_1_ -= this.field_70462_a.length;
      }

      if(aitemstack[p_70304_1_] != null) {
         ItemStack itemstack = aitemstack[p_70304_1_];
         aitemstack[p_70304_1_] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      ItemStack[] aitemstack = this.field_70462_a;
      if(p_70299_1_ >= aitemstack.length) {
         p_70299_1_ -= aitemstack.length;
         aitemstack = this.field_70460_b;
      }

      aitemstack[p_70299_1_] = p_70299_2_;
   }

   public float func_146023_a(Block p_146023_1_) {
      float f = 1.0F;
      if(this.field_70462_a[this.field_70461_c] != null) {
         f *= this.field_70462_a[this.field_70461_c].func_150997_a(p_146023_1_);
      }

      return f;
   }

   public NBTTagList func_70442_a(NBTTagList p_70442_1_) {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)i);
            this.field_70462_a[i].func_77955_b(nbttagcompound);
            p_70442_1_.func_74742_a(nbttagcompound);
         }
      }

      for(int j = 0; j < this.field_70460_b.length; ++j) {
         if(this.field_70460_b[j] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)(j + 100));
            this.field_70460_b[j].func_77955_b(nbttagcompound1);
            p_70442_1_.func_74742_a(nbttagcompound1);
         }
      }

      return p_70442_1_;
   }

   public void func_70443_b(NBTTagList p_70443_1_) {
      this.field_70462_a = new ItemStack[36];
      this.field_70460_b = new ItemStack[4];

      for(int i = 0; i < p_70443_1_.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = p_70443_1_.func_150305_b(i);
         int j = nbttagcompound.func_74771_c("Slot") & 255;
         ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);
         if(itemstack != null) {
            if(j >= 0 && j < this.field_70462_a.length) {
               this.field_70462_a[j] = itemstack;
            }

            if(j >= 100 && j < this.field_70460_b.length + 100) {
               this.field_70460_b[j - 100] = itemstack;
            }
         }
      }

   }

   public int func_70302_i_() {
      return this.field_70462_a.length + 4;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      ItemStack[] aitemstack = this.field_70462_a;
      if(p_70301_1_ >= aitemstack.length) {
         p_70301_1_ -= aitemstack.length;
         aitemstack = this.field_70460_b;
      }

      return aitemstack[p_70301_1_];
   }

   public String func_70005_c_() {
      return "container.inventory";
   }

   public boolean func_145818_k_() {
      return false;
   }

   public IChatComponent func_145748_c_() {
      return (IChatComponent)(this.func_145818_k_()?new ChatComponentText(this.func_70005_c_()):new ChatComponentTranslation(this.func_70005_c_(), new Object[0]));
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_146025_b(Block p_146025_1_) {
      if(p_146025_1_.func_149688_o().func_76229_l()) {
         return true;
      } else {
         ItemStack itemstack = this.func_70301_a(this.field_70461_c);
         return itemstack != null?itemstack.func_150998_b(p_146025_1_):false;
      }
   }

   public ItemStack func_70440_f(int p_70440_1_) {
      return this.field_70460_b[p_70440_1_];
   }

   public int func_70430_l() {
      int i = 0;

      for(int j = 0; j < this.field_70460_b.length; ++j) {
         if(this.field_70460_b[j] != null && this.field_70460_b[j].func_77973_b() instanceof ItemArmor) {
            int k = ((ItemArmor)this.field_70460_b[j].func_77973_b()).field_77879_b;
            i += k;
         }
      }

      return i;
   }

   public void func_70449_g(float p_70449_1_) {
      p_70449_1_ = p_70449_1_ / 4.0F;
      if(p_70449_1_ < 1.0F) {
         p_70449_1_ = 1.0F;
      }

      for(int i = 0; i < this.field_70460_b.length; ++i) {
         if(this.field_70460_b[i] != null && this.field_70460_b[i].func_77973_b() instanceof ItemArmor) {
            this.field_70460_b[i].func_77972_a((int)p_70449_1_, this.field_70458_d);
            if(this.field_70460_b[i].field_77994_a == 0) {
               this.field_70460_b[i] = null;
            }
         }
      }

   }

   public void func_70436_m() {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null) {
            this.field_70458_d.func_146097_a(this.field_70462_a[i], true, false);
            this.field_70462_a[i] = null;
         }
      }

      for(int j = 0; j < this.field_70460_b.length; ++j) {
         if(this.field_70460_b[j] != null) {
            this.field_70458_d.func_146097_a(this.field_70460_b[j], true, false);
            this.field_70460_b[j] = null;
         }
      }

   }

   public void func_70296_d() {
      this.field_70459_e = true;
   }

   public void func_70437_b(ItemStack p_70437_1_) {
      this.field_70457_g = p_70437_1_;
   }

   public ItemStack func_70445_o() {
      return this.field_70457_g;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_70458_d.field_70128_L?false:p_70300_1_.func_70068_e(this.field_70458_d) <= 64.0D;
   }

   public boolean func_70431_c(ItemStack p_70431_1_) {
      for(int i = 0; i < this.field_70460_b.length; ++i) {
         if(this.field_70460_b[i] != null && this.field_70460_b[i].func_77969_a(p_70431_1_)) {
            return true;
         }
      }

      for(int j = 0; j < this.field_70462_a.length; ++j) {
         if(this.field_70462_a[j] != null && this.field_70462_a[j].func_77969_a(p_70431_1_)) {
            return true;
         }
      }

      return false;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public void func_70455_b(InventoryPlayer p_70455_1_) {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         this.field_70462_a[i] = ItemStack.func_77944_b(p_70455_1_.field_70462_a[i]);
      }

      for(int j = 0; j < this.field_70460_b.length; ++j) {
         this.field_70460_b[j] = ItemStack.func_77944_b(p_70455_1_.field_70460_b[j]);
      }

      this.field_70461_c = p_70455_1_.field_70461_c;
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
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         this.field_70462_a[i] = null;
      }

      for(int j = 0; j < this.field_70460_b.length; ++j) {
         this.field_70460_b[j] = null;
      }

   }
}
