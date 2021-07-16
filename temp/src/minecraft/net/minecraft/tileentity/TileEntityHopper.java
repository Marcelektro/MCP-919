package net.minecraft.tileentity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockHopper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityHopper extends TileEntityLockable implements IHopper, ITickable {
   private ItemStack[] field_145900_a = new ItemStack[5];
   private String field_145902_i;
   private int field_145901_j = -1;

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
      this.field_145900_a = new ItemStack[this.func_70302_i_()];
      if(p_145839_1_.func_150297_b("CustomName", 8)) {
         this.field_145902_i = p_145839_1_.func_74779_i("CustomName");
      }

      this.field_145901_j = p_145839_1_.func_74762_e("TransferCooldown");

      for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
         int j = nbttagcompound.func_74771_c("Slot");
         if(j >= 0 && j < this.field_145900_a.length) {
            this.field_145900_a[j] = ItemStack.func_77949_a(nbttagcompound);
         }
      }

   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.field_145900_a.length; ++i) {
         if(this.field_145900_a[i] != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)i);
            this.field_145900_a[i].func_77955_b(nbttagcompound);
            nbttaglist.func_74742_a(nbttagcompound);
         }
      }

      p_145841_1_.func_74782_a("Items", nbttaglist);
      p_145841_1_.func_74768_a("TransferCooldown", this.field_145901_j);
      if(this.func_145818_k_()) {
         p_145841_1_.func_74778_a("CustomName", this.field_145902_i);
      }

   }

   public void func_70296_d() {
      super.func_70296_d();
   }

   public int func_70302_i_() {
      return this.field_145900_a.length;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return this.field_145900_a[p_70301_1_];
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if(this.field_145900_a[p_70298_1_] != null) {
         if(this.field_145900_a[p_70298_1_].field_77994_a <= p_70298_2_) {
            ItemStack itemstack1 = this.field_145900_a[p_70298_1_];
            this.field_145900_a[p_70298_1_] = null;
            return itemstack1;
         } else {
            ItemStack itemstack = this.field_145900_a[p_70298_1_].func_77979_a(p_70298_2_);
            if(this.field_145900_a[p_70298_1_].field_77994_a == 0) {
               this.field_145900_a[p_70298_1_] = null;
            }

            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if(this.field_145900_a[p_70304_1_] != null) {
         ItemStack itemstack = this.field_145900_a[p_70304_1_];
         this.field_145900_a[p_70304_1_] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      this.field_145900_a[p_70299_1_] = p_70299_2_;
      if(p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
         p_70299_2_.field_77994_a = this.func_70297_j_();
      }

   }

   public String func_70005_c_() {
      return this.func_145818_k_()?this.field_145902_i:"container.hopper";
   }

   public boolean func_145818_k_() {
      return this.field_145902_i != null && this.field_145902_i.length() > 0;
   }

   public void func_145886_a(String p_145886_1_) {
      this.field_145902_i = p_145886_1_;
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

   public void func_73660_a() {
      if(this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
         --this.field_145901_j;
         if(!this.func_145888_j()) {
            this.func_145896_c(0);
            this.func_145887_i();
         }

      }
   }

   public boolean func_145887_i() {
      if(this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
         if(!this.func_145888_j() && BlockHopper.func_149917_c(this.func_145832_p())) {
            boolean flag = false;
            if(!this.func_152104_k()) {
               flag = this.func_145883_k();
            }

            if(!this.func_152105_l()) {
               flag = func_145891_a(this) || flag;
            }

            if(flag) {
               this.func_145896_c(8);
               this.func_70296_d();
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private boolean func_152104_k() {
      for(ItemStack itemstack : this.field_145900_a) {
         if(itemstack != null) {
            return false;
         }
      }

      return true;
   }

   private boolean func_152105_l() {
      for(ItemStack itemstack : this.field_145900_a) {
         if(itemstack == null || itemstack.field_77994_a != itemstack.func_77976_d()) {
            return false;
         }
      }

      return true;
   }

   private boolean func_145883_k() {
      IInventory iinventory = this.func_145895_l();
      if(iinventory == null) {
         return false;
      } else {
         EnumFacing enumfacing = BlockHopper.func_176428_b(this.func_145832_p()).func_176734_d();
         if(this.func_174919_a(iinventory, enumfacing)) {
            return false;
         } else {
            for(int i = 0; i < this.func_70302_i_(); ++i) {
               if(this.func_70301_a(i) != null) {
                  ItemStack itemstack = this.func_70301_a(i).func_77946_l();
                  ItemStack itemstack1 = func_174918_a(iinventory, this.func_70298_a(i, 1), enumfacing);
                  if(itemstack1 == null || itemstack1.field_77994_a == 0) {
                     iinventory.func_70296_d();
                     return true;
                  }

                  this.func_70299_a(i, itemstack);
               }
            }

            return false;
         }
      }
   }

   private boolean func_174919_a(IInventory p_174919_1_, EnumFacing p_174919_2_) {
      if(p_174919_1_ instanceof ISidedInventory) {
         ISidedInventory isidedinventory = (ISidedInventory)p_174919_1_;
         int[] aint = isidedinventory.func_180463_a(p_174919_2_);

         for(int k = 0; k < aint.length; ++k) {
            ItemStack itemstack1 = isidedinventory.func_70301_a(aint[k]);
            if(itemstack1 == null || itemstack1.field_77994_a != itemstack1.func_77976_d()) {
               return false;
            }
         }
      } else {
         int i = p_174919_1_.func_70302_i_();

         for(int j = 0; j < i; ++j) {
            ItemStack itemstack = p_174919_1_.func_70301_a(j);
            if(itemstack == null || itemstack.field_77994_a != itemstack.func_77976_d()) {
               return false;
            }
         }
      }

      return true;
   }

   private static boolean func_174917_b(IInventory p_174917_0_, EnumFacing p_174917_1_) {
      if(p_174917_0_ instanceof ISidedInventory) {
         ISidedInventory isidedinventory = (ISidedInventory)p_174917_0_;
         int[] aint = isidedinventory.func_180463_a(p_174917_1_);

         for(int i = 0; i < aint.length; ++i) {
            if(isidedinventory.func_70301_a(aint[i]) != null) {
               return false;
            }
         }
      } else {
         int j = p_174917_0_.func_70302_i_();

         for(int k = 0; k < j; ++k) {
            if(p_174917_0_.func_70301_a(k) != null) {
               return false;
            }
         }
      }

      return true;
   }

   public static boolean func_145891_a(IHopper p_145891_0_) {
      IInventory iinventory = func_145884_b(p_145891_0_);
      if(iinventory != null) {
         EnumFacing enumfacing = EnumFacing.DOWN;
         if(func_174917_b(iinventory, enumfacing)) {
            return false;
         }

         if(iinventory instanceof ISidedInventory) {
            ISidedInventory isidedinventory = (ISidedInventory)iinventory;
            int[] aint = isidedinventory.func_180463_a(enumfacing);

            for(int i = 0; i < aint.length; ++i) {
               if(func_174915_a(p_145891_0_, iinventory, aint[i], enumfacing)) {
                  return true;
               }
            }
         } else {
            int j = iinventory.func_70302_i_();

            for(int k = 0; k < j; ++k) {
               if(func_174915_a(p_145891_0_, iinventory, k, enumfacing)) {
                  return true;
               }
            }
         }
      } else {
         for(EntityItem entityitem : func_181556_a(p_145891_0_.func_145831_w(), p_145891_0_.func_96107_aA(), p_145891_0_.func_96109_aB() + 1.0D, p_145891_0_.func_96108_aC())) {
            if(func_145898_a(p_145891_0_, entityitem)) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean func_174915_a(IHopper p_174915_0_, IInventory p_174915_1_, int p_174915_2_, EnumFacing p_174915_3_) {
      ItemStack itemstack = p_174915_1_.func_70301_a(p_174915_2_);
      if(itemstack != null && func_174921_b(p_174915_1_, itemstack, p_174915_2_, p_174915_3_)) {
         ItemStack itemstack1 = itemstack.func_77946_l();
         ItemStack itemstack2 = func_174918_a(p_174915_0_, p_174915_1_.func_70298_a(p_174915_2_, 1), (EnumFacing)null);
         if(itemstack2 == null || itemstack2.field_77994_a == 0) {
            p_174915_1_.func_70296_d();
            return true;
         }

         p_174915_1_.func_70299_a(p_174915_2_, itemstack1);
      }

      return false;
   }

   public static boolean func_145898_a(IInventory p_145898_0_, EntityItem p_145898_1_) {
      boolean flag = false;
      if(p_145898_1_ == null) {
         return false;
      } else {
         ItemStack itemstack = p_145898_1_.func_92059_d().func_77946_l();
         ItemStack itemstack1 = func_174918_a(p_145898_0_, itemstack, (EnumFacing)null);
         if(itemstack1 != null && itemstack1.field_77994_a != 0) {
            p_145898_1_.func_92058_a(itemstack1);
         } else {
            flag = true;
            p_145898_1_.func_70106_y();
         }

         return flag;
      }
   }

   public static ItemStack func_174918_a(IInventory p_174918_0_, ItemStack p_174918_1_, EnumFacing p_174918_2_) {
      if(p_174918_0_ instanceof ISidedInventory && p_174918_2_ != null) {
         ISidedInventory isidedinventory = (ISidedInventory)p_174918_0_;
         int[] aint = isidedinventory.func_180463_a(p_174918_2_);

         for(int k = 0; k < aint.length && p_174918_1_ != null && p_174918_1_.field_77994_a > 0; ++k) {
            p_174918_1_ = func_174916_c(p_174918_0_, p_174918_1_, aint[k], p_174918_2_);
         }
      } else {
         int i = p_174918_0_.func_70302_i_();

         for(int j = 0; j < i && p_174918_1_ != null && p_174918_1_.field_77994_a > 0; ++j) {
            p_174918_1_ = func_174916_c(p_174918_0_, p_174918_1_, j, p_174918_2_);
         }
      }

      if(p_174918_1_ != null && p_174918_1_.field_77994_a == 0) {
         p_174918_1_ = null;
      }

      return p_174918_1_;
   }

   private static boolean func_174920_a(IInventory p_174920_0_, ItemStack p_174920_1_, int p_174920_2_, EnumFacing p_174920_3_) {
      return !p_174920_0_.func_94041_b(p_174920_2_, p_174920_1_)?false:!(p_174920_0_ instanceof ISidedInventory) || ((ISidedInventory)p_174920_0_).func_180462_a(p_174920_2_, p_174920_1_, p_174920_3_);
   }

   private static boolean func_174921_b(IInventory p_174921_0_, ItemStack p_174921_1_, int p_174921_2_, EnumFacing p_174921_3_) {
      return !(p_174921_0_ instanceof ISidedInventory) || ((ISidedInventory)p_174921_0_).func_180461_b(p_174921_2_, p_174921_1_, p_174921_3_);
   }

   private static ItemStack func_174916_c(IInventory p_174916_0_, ItemStack p_174916_1_, int p_174916_2_, EnumFacing p_174916_3_) {
      ItemStack itemstack = p_174916_0_.func_70301_a(p_174916_2_);
      if(func_174920_a(p_174916_0_, p_174916_1_, p_174916_2_, p_174916_3_)) {
         boolean flag = false;
         if(itemstack == null) {
            p_174916_0_.func_70299_a(p_174916_2_, p_174916_1_);
            p_174916_1_ = null;
            flag = true;
         } else if(func_145894_a(itemstack, p_174916_1_)) {
            int i = p_174916_1_.func_77976_d() - itemstack.field_77994_a;
            int j = Math.min(p_174916_1_.field_77994_a, i);
            p_174916_1_.field_77994_a -= j;
            itemstack.field_77994_a += j;
            flag = j > 0;
         }

         if(flag) {
            if(p_174916_0_ instanceof TileEntityHopper) {
               TileEntityHopper tileentityhopper = (TileEntityHopper)p_174916_0_;
               if(tileentityhopper.func_174914_o()) {
                  tileentityhopper.func_145896_c(8);
               }

               p_174916_0_.func_70296_d();
            }

            p_174916_0_.func_70296_d();
         }
      }

      return p_174916_1_;
   }

   private IInventory func_145895_l() {
      EnumFacing enumfacing = BlockHopper.func_176428_b(this.func_145832_p());
      return func_145893_b(this.func_145831_w(), (double)(this.field_174879_c.func_177958_n() + enumfacing.func_82601_c()), (double)(this.field_174879_c.func_177956_o() + enumfacing.func_96559_d()), (double)(this.field_174879_c.func_177952_p() + enumfacing.func_82599_e()));
   }

   public static IInventory func_145884_b(IHopper p_145884_0_) {
      return func_145893_b(p_145884_0_.func_145831_w(), p_145884_0_.func_96107_aA(), p_145884_0_.func_96109_aB() + 1.0D, p_145884_0_.func_96108_aC());
   }

   public static List<EntityItem> func_181556_a(World p_181556_0_, double p_181556_1_, double p_181556_3_, double p_181556_5_) {
      return p_181556_0_.<EntityItem>func_175647_a(EntityItem.class, new AxisAlignedBB(p_181556_1_ - 0.5D, p_181556_3_ - 0.5D, p_181556_5_ - 0.5D, p_181556_1_ + 0.5D, p_181556_3_ + 0.5D, p_181556_5_ + 0.5D), EntitySelectors.field_94557_a);
   }

   public static IInventory func_145893_b(World p_145893_0_, double p_145893_1_, double p_145893_3_, double p_145893_5_) {
      IInventory iinventory = null;
      int i = MathHelper.func_76128_c(p_145893_1_);
      int j = MathHelper.func_76128_c(p_145893_3_);
      int k = MathHelper.func_76128_c(p_145893_5_);
      BlockPos blockpos = new BlockPos(i, j, k);
      Block block = p_145893_0_.func_180495_p(blockpos).func_177230_c();
      if(block.func_149716_u()) {
         TileEntity tileentity = p_145893_0_.func_175625_s(blockpos);
         if(tileentity instanceof IInventory) {
            iinventory = (IInventory)tileentity;
            if(iinventory instanceof TileEntityChest && block instanceof BlockChest) {
               iinventory = ((BlockChest)block).func_180676_d(p_145893_0_, blockpos);
            }
         }
      }

      if(iinventory == null) {
         List<Entity> list = p_145893_0_.func_175674_a((Entity)null, new AxisAlignedBB(p_145893_1_ - 0.5D, p_145893_3_ - 0.5D, p_145893_5_ - 0.5D, p_145893_1_ + 0.5D, p_145893_3_ + 0.5D, p_145893_5_ + 0.5D), EntitySelectors.field_96566_b);
         if(list.size() > 0) {
            iinventory = (IInventory)list.get(p_145893_0_.field_73012_v.nextInt(list.size()));
         }
      }

      return iinventory;
   }

   private static boolean func_145894_a(ItemStack p_145894_0_, ItemStack p_145894_1_) {
      return p_145894_0_.func_77973_b() != p_145894_1_.func_77973_b()?false:(p_145894_0_.func_77960_j() != p_145894_1_.func_77960_j()?false:(p_145894_0_.field_77994_a > p_145894_0_.func_77976_d()?false:ItemStack.func_77970_a(p_145894_0_, p_145894_1_)));
   }

   public double func_96107_aA() {
      return (double)this.field_174879_c.func_177958_n() + 0.5D;
   }

   public double func_96109_aB() {
      return (double)this.field_174879_c.func_177956_o() + 0.5D;
   }

   public double func_96108_aC() {
      return (double)this.field_174879_c.func_177952_p() + 0.5D;
   }

   public void func_145896_c(int p_145896_1_) {
      this.field_145901_j = p_145896_1_;
   }

   public boolean func_145888_j() {
      return this.field_145901_j > 0;
   }

   public boolean func_174914_o() {
      return this.field_145901_j <= 1;
   }

   public String func_174875_k() {
      return "minecraft:hopper";
   }

   public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
      return new ContainerHopper(p_174876_1_, this, p_174876_2_);
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
      for(int i = 0; i < this.field_145900_a.length; ++i) {
         this.field_145900_a[i] = null;
      }

   }
}
