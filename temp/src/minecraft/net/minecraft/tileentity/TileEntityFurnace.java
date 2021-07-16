package net.minecraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.MathHelper;

public class TileEntityFurnace extends TileEntityLockable implements ITickable, ISidedInventory {
   private static final int[] field_145962_k = new int[]{0};
   private static final int[] field_145959_l = new int[]{2, 1};
   private static final int[] field_145960_m = new int[]{1};
   private ItemStack[] field_145957_n = new ItemStack[3];
   private int field_145956_a;
   private int field_145963_i;
   private int field_174906_k;
   private int field_174905_l;
   private String field_145958_o;

   public int func_70302_i_() {
      return this.field_145957_n.length;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return this.field_145957_n[p_70301_1_];
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if(this.field_145957_n[p_70298_1_] != null) {
         if(this.field_145957_n[p_70298_1_].field_77994_a <= p_70298_2_) {
            ItemStack itemstack1 = this.field_145957_n[p_70298_1_];
            this.field_145957_n[p_70298_1_] = null;
            return itemstack1;
         } else {
            ItemStack itemstack = this.field_145957_n[p_70298_1_].func_77979_a(p_70298_2_);
            if(this.field_145957_n[p_70298_1_].field_77994_a == 0) {
               this.field_145957_n[p_70298_1_] = null;
            }

            return itemstack;
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if(this.field_145957_n[p_70304_1_] != null) {
         ItemStack itemstack = this.field_145957_n[p_70304_1_];
         this.field_145957_n[p_70304_1_] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      boolean flag = p_70299_2_ != null && p_70299_2_.func_77969_a(this.field_145957_n[p_70299_1_]) && ItemStack.func_77970_a(p_70299_2_, this.field_145957_n[p_70299_1_]);
      this.field_145957_n[p_70299_1_] = p_70299_2_;
      if(p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
         p_70299_2_.field_77994_a = this.func_70297_j_();
      }

      if(p_70299_1_ == 0 && !flag) {
         this.field_174905_l = this.func_174904_a(p_70299_2_);
         this.field_174906_k = 0;
         this.func_70296_d();
      }

   }

   public String func_70005_c_() {
      return this.func_145818_k_()?this.field_145958_o:"container.furnace";
   }

   public boolean func_145818_k_() {
      return this.field_145958_o != null && this.field_145958_o.length() > 0;
   }

   public void func_145951_a(String p_145951_1_) {
      this.field_145958_o = p_145951_1_;
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
      this.field_145957_n = new ItemStack[this.func_70302_i_()];

      for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
         int j = nbttagcompound.func_74771_c("Slot");
         if(j >= 0 && j < this.field_145957_n.length) {
            this.field_145957_n[j] = ItemStack.func_77949_a(nbttagcompound);
         }
      }

      this.field_145956_a = p_145839_1_.func_74765_d("BurnTime");
      this.field_174906_k = p_145839_1_.func_74765_d("CookTime");
      this.field_174905_l = p_145839_1_.func_74765_d("CookTimeTotal");
      this.field_145963_i = func_145952_a(this.field_145957_n[1]);
      if(p_145839_1_.func_150297_b("CustomName", 8)) {
         this.field_145958_o = p_145839_1_.func_74779_i("CustomName");
      }

   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74777_a("BurnTime", (short)this.field_145956_a);
      p_145841_1_.func_74777_a("CookTime", (short)this.field_174906_k);
      p_145841_1_.func_74777_a("CookTimeTotal", (short)this.field_174905_l);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.field_145957_n.length; ++i) {
         if(this.field_145957_n[i] != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)i);
            this.field_145957_n[i].func_77955_b(nbttagcompound);
            nbttaglist.func_74742_a(nbttagcompound);
         }
      }

      p_145841_1_.func_74782_a("Items", nbttaglist);
      if(this.func_145818_k_()) {
         p_145841_1_.func_74778_a("CustomName", this.field_145958_o);
      }

   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_145950_i() {
      return this.field_145956_a > 0;
   }

   public static boolean func_174903_a(IInventory p_174903_0_) {
      return p_174903_0_.func_174887_a_(0) > 0;
   }

   public void func_73660_a() {
      boolean flag = this.func_145950_i();
      boolean flag1 = false;
      if(this.func_145950_i()) {
         --this.field_145956_a;
      }

      if(!this.field_145850_b.field_72995_K) {
         if(this.func_145950_i() || this.field_145957_n[1] != null && this.field_145957_n[0] != null) {
            if(!this.func_145950_i() && this.func_145948_k()) {
               this.field_145963_i = this.field_145956_a = func_145952_a(this.field_145957_n[1]);
               if(this.func_145950_i()) {
                  flag1 = true;
                  if(this.field_145957_n[1] != null) {
                     --this.field_145957_n[1].field_77994_a;
                     if(this.field_145957_n[1].field_77994_a == 0) {
                        Item item = this.field_145957_n[1].func_77973_b().func_77668_q();
                        this.field_145957_n[1] = item != null?new ItemStack(item):null;
                     }
                  }
               }
            }

            if(this.func_145950_i() && this.func_145948_k()) {
               ++this.field_174906_k;
               if(this.field_174906_k == this.field_174905_l) {
                  this.field_174906_k = 0;
                  this.field_174905_l = this.func_174904_a(this.field_145957_n[0]);
                  this.func_145949_j();
                  flag1 = true;
               }
            } else {
               this.field_174906_k = 0;
            }
         } else if(!this.func_145950_i() && this.field_174906_k > 0) {
            this.field_174906_k = MathHelper.func_76125_a(this.field_174906_k - 2, 0, this.field_174905_l);
         }

         if(flag != this.func_145950_i()) {
            flag1 = true;
            BlockFurnace.func_176446_a(this.func_145950_i(), this.field_145850_b, this.field_174879_c);
         }
      }

      if(flag1) {
         this.func_70296_d();
      }

   }

   public int func_174904_a(ItemStack p_174904_1_) {
      return 200;
   }

   private boolean func_145948_k() {
      if(this.field_145957_n[0] == null) {
         return false;
      } else {
         ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.field_145957_n[0]);
         return itemstack == null?false:(this.field_145957_n[2] == null?true:(!this.field_145957_n[2].func_77969_a(itemstack)?false:(this.field_145957_n[2].field_77994_a < this.func_70297_j_() && this.field_145957_n[2].field_77994_a < this.field_145957_n[2].func_77976_d()?true:this.field_145957_n[2].field_77994_a < itemstack.func_77976_d())));
      }
   }

   public void func_145949_j() {
      if(this.func_145948_k()) {
         ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.field_145957_n[0]);
         if(this.field_145957_n[2] == null) {
            this.field_145957_n[2] = itemstack.func_77946_l();
         } else if(this.field_145957_n[2].func_77973_b() == itemstack.func_77973_b()) {
            ++this.field_145957_n[2].field_77994_a;
         }

         if(this.field_145957_n[0].func_77973_b() == Item.func_150898_a(Blocks.field_150360_v) && this.field_145957_n[0].func_77960_j() == 1 && this.field_145957_n[1] != null && this.field_145957_n[1].func_77973_b() == Items.field_151133_ar) {
            this.field_145957_n[1] = new ItemStack(Items.field_151131_as);
         }

         --this.field_145957_n[0].field_77994_a;
         if(this.field_145957_n[0].field_77994_a <= 0) {
            this.field_145957_n[0] = null;
         }

      }
   }

   public static int func_145952_a(ItemStack p_145952_0_) {
      if(p_145952_0_ == null) {
         return 0;
      } else {
         Item item = p_145952_0_.func_77973_b();
         if(item instanceof ItemBlock && Block.func_149634_a(item) != Blocks.field_150350_a) {
            Block block = Block.func_149634_a(item);
            if(block == Blocks.field_150376_bx) {
               return 150;
            }

            if(block.func_149688_o() == Material.field_151575_d) {
               return 300;
            }

            if(block == Blocks.field_150402_ci) {
               return 16000;
            }
         }

         return item instanceof ItemTool && ((ItemTool)item).func_77861_e().equals("WOOD")?200:(item instanceof ItemSword && ((ItemSword)item).func_150932_j().equals("WOOD")?200:(item instanceof ItemHoe && ((ItemHoe)item).func_77842_f().equals("WOOD")?200:(item == Items.field_151055_y?100:(item == Items.field_151044_h?1600:(item == Items.field_151129_at?20000:(item == Item.func_150898_a(Blocks.field_150345_g)?100:(item == Items.field_151072_bj?2400:0)))))));
      }
   }

   public static boolean func_145954_b(ItemStack p_145954_0_) {
      return func_145952_a(p_145954_0_) > 0;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_145850_b.func_175625_s(this.field_174879_c) != this?false:p_70300_1_.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.5D, (double)this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return p_94041_1_ == 2?false:(p_94041_1_ != 1?true:func_145954_b(p_94041_2_) || SlotFurnaceFuel.func_178173_c_(p_94041_2_));
   }

   public int[] func_180463_a(EnumFacing p_180463_1_) {
      return p_180463_1_ == EnumFacing.DOWN?field_145959_l:(p_180463_1_ == EnumFacing.UP?field_145962_k:field_145960_m);
   }

   public boolean func_180462_a(int p_180462_1_, ItemStack p_180462_2_, EnumFacing p_180462_3_) {
      return this.func_94041_b(p_180462_1_, p_180462_2_);
   }

   public boolean func_180461_b(int p_180461_1_, ItemStack p_180461_2_, EnumFacing p_180461_3_) {
      if(p_180461_3_ == EnumFacing.DOWN && p_180461_1_ == 1) {
         Item item = p_180461_2_.func_77973_b();
         if(item != Items.field_151131_as && item != Items.field_151133_ar) {
            return false;
         }
      }

      return true;
   }

   public String func_174875_k() {
      return "minecraft:furnace";
   }

   public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
      return new ContainerFurnace(p_174876_1_, this);
   }

   public int func_174887_a_(int p_174887_1_) {
      switch(p_174887_1_) {
      case 0:
         return this.field_145956_a;
      case 1:
         return this.field_145963_i;
      case 2:
         return this.field_174906_k;
      case 3:
         return this.field_174905_l;
      default:
         return 0;
      }
   }

   public void func_174885_b(int p_174885_1_, int p_174885_2_) {
      switch(p_174885_1_) {
      case 0:
         this.field_145956_a = p_174885_2_;
         break;
      case 1:
         this.field_145963_i = p_174885_2_;
         break;
      case 2:
         this.field_174906_k = p_174885_2_;
         break;
      case 3:
         this.field_174905_l = p_174885_2_;
      }

   }

   public int func_174890_g() {
      return 4;
   }

   public void func_174888_l() {
      for(int i = 0; i < this.field_145957_n.length; ++i) {
         this.field_145957_n[i] = null;
      }

   }
}
