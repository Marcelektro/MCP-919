package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;

public class TileEntityBeacon extends TileEntityLockable implements ITickable, IInventory {
   public static final Potion[][] field_146009_a = new Potion[][]{{Potion.field_76424_c, Potion.field_76422_e}, {Potion.field_76429_m, Potion.field_76430_j}, {Potion.field_76420_g}, {Potion.field_76428_l}};
   private final List<TileEntityBeacon.BeamSegment> field_174909_f = Lists.<TileEntityBeacon.BeamSegment>newArrayList();
   private long field_146016_i;
   private float field_146014_j;
   private boolean field_146015_k;
   private int field_146012_l = -1;
   private int field_146013_m;
   private int field_146010_n;
   private ItemStack field_146011_o;
   private String field_146008_p;

   public void func_73660_a() {
      if(this.field_145850_b.func_82737_E() % 80L == 0L) {
         this.func_174908_m();
      }

   }

   public void func_174908_m() {
      this.func_146003_y();
      this.func_146000_x();
   }

   private void func_146000_x() {
      if(this.field_146015_k && this.field_146012_l > 0 && !this.field_145850_b.field_72995_K && this.field_146013_m > 0) {
         double d0 = (double)(this.field_146012_l * 10 + 10);
         int i = 0;
         if(this.field_146012_l >= 4 && this.field_146013_m == this.field_146010_n) {
            i = 1;
         }

         int j = this.field_174879_c.func_177958_n();
         int k = this.field_174879_c.func_177956_o();
         int l = this.field_174879_c.func_177952_p();
         AxisAlignedBB axisalignedbb = (new AxisAlignedBB((double)j, (double)k, (double)l, (double)(j + 1), (double)(k + 1), (double)(l + 1))).func_72314_b(d0, d0, d0).func_72321_a(0.0D, (double)this.field_145850_b.func_72800_K(), 0.0D);
         List<EntityPlayer> list = this.field_145850_b.<EntityPlayer>func_72872_a(EntityPlayer.class, axisalignedbb);

         for(EntityPlayer entityplayer : list) {
            entityplayer.func_70690_d(new PotionEffect(this.field_146013_m, 180, i, true, true));
         }

         if(this.field_146012_l >= 4 && this.field_146013_m != this.field_146010_n && this.field_146010_n > 0) {
            for(EntityPlayer entityplayer1 : list) {
               entityplayer1.func_70690_d(new PotionEffect(this.field_146010_n, 180, 0, true, true));
            }
         }
      }

   }

   private void func_146003_y() {
      int i = this.field_146012_l;
      int j = this.field_174879_c.func_177958_n();
      int k = this.field_174879_c.func_177956_o();
      int l = this.field_174879_c.func_177952_p();
      this.field_146012_l = 0;
      this.field_174909_f.clear();
      this.field_146015_k = true;
      TileEntityBeacon.BeamSegment tileentitybeacon$beamsegment = new TileEntityBeacon.BeamSegment(EntitySheep.func_175513_a(EnumDyeColor.WHITE));
      this.field_174909_f.add(tileentitybeacon$beamsegment);
      boolean flag = true;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int i1 = k + 1; i1 < 256; ++i1) {
         IBlockState iblockstate = this.field_145850_b.func_180495_p(blockpos$mutableblockpos.func_181079_c(j, i1, l));
         float[] afloat;
         if(iblockstate.func_177230_c() == Blocks.field_150399_cn) {
            afloat = EntitySheep.func_175513_a((EnumDyeColor)iblockstate.func_177229_b(BlockStainedGlass.field_176547_a));
         } else {
            if(iblockstate.func_177230_c() != Blocks.field_150397_co) {
               if(iblockstate.func_177230_c().func_149717_k() >= 15 && iblockstate.func_177230_c() != Blocks.field_150357_h) {
                  this.field_146015_k = false;
                  this.field_174909_f.clear();
                  break;
               }

               tileentitybeacon$beamsegment.func_177262_a();
               continue;
            }

            afloat = EntitySheep.func_175513_a((EnumDyeColor)iblockstate.func_177229_b(BlockStainedGlassPane.field_176245_a));
         }

         if(!flag) {
            afloat = new float[]{(tileentitybeacon$beamsegment.func_177263_b()[0] + afloat[0]) / 2.0F, (tileentitybeacon$beamsegment.func_177263_b()[1] + afloat[1]) / 2.0F, (tileentitybeacon$beamsegment.func_177263_b()[2] + afloat[2]) / 2.0F};
         }

         if(Arrays.equals(afloat, tileentitybeacon$beamsegment.func_177263_b())) {
            tileentitybeacon$beamsegment.func_177262_a();
         } else {
            tileentitybeacon$beamsegment = new TileEntityBeacon.BeamSegment(afloat);
            this.field_174909_f.add(tileentitybeacon$beamsegment);
         }

         flag = false;
      }

      if(this.field_146015_k) {
         for(int l1 = 1; l1 <= 4; this.field_146012_l = l1++) {
            int i2 = k - l1;
            if(i2 < 0) {
               break;
            }

            boolean flag1 = true;

            for(int j1 = j - l1; j1 <= j + l1 && flag1; ++j1) {
               for(int k1 = l - l1; k1 <= l + l1; ++k1) {
                  Block block = this.field_145850_b.func_180495_p(new BlockPos(j1, i2, k1)).func_177230_c();
                  if(block != Blocks.field_150475_bE && block != Blocks.field_150340_R && block != Blocks.field_150484_ah && block != Blocks.field_150339_S) {
                     flag1 = false;
                     break;
                  }
               }
            }

            if(!flag1) {
               break;
            }
         }

         if(this.field_146012_l == 0) {
            this.field_146015_k = false;
         }
      }

      if(!this.field_145850_b.field_72995_K && this.field_146012_l == 4 && i < this.field_146012_l) {
         for(EntityPlayer entityplayer : this.field_145850_b.func_72872_a(EntityPlayer.class, (new AxisAlignedBB((double)j, (double)k, (double)l, (double)j, (double)(k - 4), (double)l)).func_72314_b(10.0D, 5.0D, 10.0D))) {
            entityplayer.func_71029_a(AchievementList.field_150965_K);
         }
      }

   }

   public List<TileEntityBeacon.BeamSegment> func_174907_n() {
      return this.field_174909_f;
   }

   public float func_146002_i() {
      if(!this.field_146015_k) {
         return 0.0F;
      } else {
         int i = (int)(this.field_145850_b.func_82737_E() - this.field_146016_i);
         this.field_146016_i = this.field_145850_b.func_82737_E();
         if(i > 1) {
            this.field_146014_j -= (float)i / 40.0F;
            if(this.field_146014_j < 0.0F) {
               this.field_146014_j = 0.0F;
            }
         }

         this.field_146014_j += 0.025F;
         if(this.field_146014_j > 1.0F) {
            this.field_146014_j = 1.0F;
         }

         return this.field_146014_j;
      }
   }

   public Packet func_145844_m() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      this.func_145841_b(nbttagcompound);
      return new S35PacketUpdateTileEntity(this.field_174879_c, 3, nbttagcompound);
   }

   public double func_145833_n() {
      return 65536.0D;
   }

   private int func_183001_h(int p_183001_1_) {
      if(p_183001_1_ >= 0 && p_183001_1_ < Potion.field_76425_a.length && Potion.field_76425_a[p_183001_1_] != null) {
         Potion potion = Potion.field_76425_a[p_183001_1_];
         return potion != Potion.field_76424_c && potion != Potion.field_76422_e && potion != Potion.field_76429_m && potion != Potion.field_76430_j && potion != Potion.field_76420_g && potion != Potion.field_76428_l?0:p_183001_1_;
      } else {
         return 0;
      }
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_146013_m = this.func_183001_h(p_145839_1_.func_74762_e("Primary"));
      this.field_146010_n = this.func_183001_h(p_145839_1_.func_74762_e("Secondary"));
      this.field_146012_l = p_145839_1_.func_74762_e("Levels");
   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74768_a("Primary", this.field_146013_m);
      p_145841_1_.func_74768_a("Secondary", this.field_146010_n);
      p_145841_1_.func_74768_a("Levels", this.field_146012_l);
   }

   public int func_70302_i_() {
      return 1;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      return p_70301_1_ == 0?this.field_146011_o:null;
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      if(p_70298_1_ == 0 && this.field_146011_o != null) {
         if(p_70298_2_ >= this.field_146011_o.field_77994_a) {
            ItemStack itemstack = this.field_146011_o;
            this.field_146011_o = null;
            return itemstack;
         } else {
            this.field_146011_o.field_77994_a -= p_70298_2_;
            return new ItemStack(this.field_146011_o.func_77973_b(), p_70298_2_, this.field_146011_o.func_77960_j());
         }
      } else {
         return null;
      }
   }

   public ItemStack func_70304_b(int p_70304_1_) {
      if(p_70304_1_ == 0 && this.field_146011_o != null) {
         ItemStack itemstack = this.field_146011_o;
         this.field_146011_o = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      if(p_70299_1_ == 0) {
         this.field_146011_o = p_70299_2_;
      }

   }

   public String func_70005_c_() {
      return this.func_145818_k_()?this.field_146008_p:"container.beacon";
   }

   public boolean func_145818_k_() {
      return this.field_146008_p != null && this.field_146008_p.length() > 0;
   }

   public void func_145999_a(String p_145999_1_) {
      this.field_146008_p = p_145999_1_;
   }

   public int func_70297_j_() {
      return 1;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_145850_b.func_175625_s(this.field_174879_c) != this?false:p_70300_1_.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5D, (double)this.field_174879_c.func_177956_o() + 0.5D, (double)this.field_174879_c.func_177952_p() + 0.5D) <= 64.0D;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return p_94041_2_.func_77973_b() == Items.field_151166_bC || p_94041_2_.func_77973_b() == Items.field_151045_i || p_94041_2_.func_77973_b() == Items.field_151043_k || p_94041_2_.func_77973_b() == Items.field_151042_j;
   }

   public String func_174875_k() {
      return "minecraft:beacon";
   }

   public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
      return new ContainerBeacon(p_174876_1_, this);
   }

   public int func_174887_a_(int p_174887_1_) {
      switch(p_174887_1_) {
      case 0:
         return this.field_146012_l;
      case 1:
         return this.field_146013_m;
      case 2:
         return this.field_146010_n;
      default:
         return 0;
      }
   }

   public void func_174885_b(int p_174885_1_, int p_174885_2_) {
      switch(p_174885_1_) {
      case 0:
         this.field_146012_l = p_174885_2_;
         break;
      case 1:
         this.field_146013_m = this.func_183001_h(p_174885_2_);
         break;
      case 2:
         this.field_146010_n = this.func_183001_h(p_174885_2_);
      }

   }

   public int func_174890_g() {
      return 3;
   }

   public void func_174888_l() {
      this.field_146011_o = null;
   }

   public boolean func_145842_c(int p_145842_1_, int p_145842_2_) {
      if(p_145842_1_ == 1) {
         this.func_174908_m();
         return true;
      } else {
         return super.func_145842_c(p_145842_1_, p_145842_2_);
      }
   }

   public static class BeamSegment {
      private final float[] field_177266_a;
      private int field_177265_b;

      public BeamSegment(float[] p_i45669_1_) {
         this.field_177266_a = p_i45669_1_;
         this.field_177265_b = 1;
      }

      protected void func_177262_a() {
         ++this.field_177265_b;
      }

      public float[] func_177263_b() {
         return this.field_177266_a;
      }

      public int func_177264_c() {
         return this.field_177265_b;
      }
   }
}
