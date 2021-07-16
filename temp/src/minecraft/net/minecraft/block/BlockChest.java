package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

public class BlockChest extends BlockContainer {
   public static final PropertyDirection field_176459_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   public final int field_149956_a;

   protected BlockChest(int p_i45397_1_) {
      super(Material.field_151575_d);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176459_a, EnumFacing.NORTH));
      this.field_149956_a = p_i45397_1_;
      this.func_149647_a(CreativeTabs.field_78031_c);
      this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public int func_149645_b() {
      return 2;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      if(p_180654_1_.func_180495_p(p_180654_2_.func_177978_c()).func_177230_c() == this) {
         this.func_149676_a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
      } else if(p_180654_1_.func_180495_p(p_180654_2_.func_177968_d()).func_177230_c() == this) {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
      } else if(p_180654_1_.func_180495_p(p_180654_2_.func_177976_e()).func_177230_c() == this) {
         this.func_149676_a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      } else if(p_180654_1_.func_180495_p(p_180654_2_.func_177974_f()).func_177230_c() == this) {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
      } else {
         this.func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
      }

   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176455_e(p_176213_1_, p_176213_2_, p_176213_3_);

      for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
         BlockPos blockpos = p_176213_2_.func_177972_a(enumfacing);
         IBlockState iblockstate = p_176213_1_.func_180495_p(blockpos);
         if(iblockstate.func_177230_c() == this) {
            this.func_176455_e(p_176213_1_, blockpos, iblockstate);
         }
      }

   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176459_a, p_180642_8_.func_174811_aO());
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      EnumFacing enumfacing = EnumFacing.func_176731_b(MathHelper.func_76128_c((double)(p_180633_4_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3).func_176734_d();
      p_180633_3_ = p_180633_3_.func_177226_a(field_176459_a, enumfacing);
      BlockPos blockpos = p_180633_2_.func_177978_c();
      BlockPos blockpos1 = p_180633_2_.func_177968_d();
      BlockPos blockpos2 = p_180633_2_.func_177976_e();
      BlockPos blockpos3 = p_180633_2_.func_177974_f();
      boolean flag = this == p_180633_1_.func_180495_p(blockpos).func_177230_c();
      boolean flag1 = this == p_180633_1_.func_180495_p(blockpos1).func_177230_c();
      boolean flag2 = this == p_180633_1_.func_180495_p(blockpos2).func_177230_c();
      boolean flag3 = this == p_180633_1_.func_180495_p(blockpos3).func_177230_c();
      if(!flag && !flag1 && !flag2 && !flag3) {
         p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_, 3);
      } else if(enumfacing.func_176740_k() != EnumFacing.Axis.X || !flag && !flag1) {
         if(enumfacing.func_176740_k() == EnumFacing.Axis.Z && (flag2 || flag3)) {
            if(flag2) {
               p_180633_1_.func_180501_a(blockpos2, p_180633_3_, 3);
            } else {
               p_180633_1_.func_180501_a(blockpos3, p_180633_3_, 3);
            }

            p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_, 3);
         }
      } else {
         if(flag) {
            p_180633_1_.func_180501_a(blockpos, p_180633_3_, 3);
         } else {
            p_180633_1_.func_180501_a(blockpos1, p_180633_3_, 3);
         }

         p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_, 3);
      }

      if(p_180633_5_.func_82837_s()) {
         TileEntity tileentity = p_180633_1_.func_175625_s(p_180633_2_);
         if(tileentity instanceof TileEntityChest) {
            ((TileEntityChest)tileentity).func_145976_a(p_180633_5_.func_82833_r());
         }
      }

   }

   public IBlockState func_176455_e(World p_176455_1_, BlockPos p_176455_2_, IBlockState p_176455_3_) {
      if(p_176455_1_.field_72995_K) {
         return p_176455_3_;
      } else {
         IBlockState iblockstate = p_176455_1_.func_180495_p(p_176455_2_.func_177978_c());
         IBlockState iblockstate1 = p_176455_1_.func_180495_p(p_176455_2_.func_177968_d());
         IBlockState iblockstate2 = p_176455_1_.func_180495_p(p_176455_2_.func_177976_e());
         IBlockState iblockstate3 = p_176455_1_.func_180495_p(p_176455_2_.func_177974_f());
         EnumFacing enumfacing = (EnumFacing)p_176455_3_.func_177229_b(field_176459_a);
         Block block = iblockstate.func_177230_c();
         Block block1 = iblockstate1.func_177230_c();
         Block block2 = iblockstate2.func_177230_c();
         Block block3 = iblockstate3.func_177230_c();
         if(block != this && block1 != this) {
            boolean flag = block.func_149730_j();
            boolean flag1 = block1.func_149730_j();
            if(block2 == this || block3 == this) {
               BlockPos blockpos1 = block2 == this?p_176455_2_.func_177976_e():p_176455_2_.func_177974_f();
               IBlockState iblockstate6 = p_176455_1_.func_180495_p(blockpos1.func_177978_c());
               IBlockState iblockstate7 = p_176455_1_.func_180495_p(blockpos1.func_177968_d());
               enumfacing = EnumFacing.SOUTH;
               EnumFacing enumfacing2;
               if(block2 == this) {
                  enumfacing2 = (EnumFacing)iblockstate2.func_177229_b(field_176459_a);
               } else {
                  enumfacing2 = (EnumFacing)iblockstate3.func_177229_b(field_176459_a);
               }

               if(enumfacing2 == EnumFacing.NORTH) {
                  enumfacing = EnumFacing.NORTH;
               }

               Block block6 = iblockstate6.func_177230_c();
               Block block7 = iblockstate7.func_177230_c();
               if((flag || block6.func_149730_j()) && !flag1 && !block7.func_149730_j()) {
                  enumfacing = EnumFacing.SOUTH;
               }

               if((flag1 || block7.func_149730_j()) && !flag && !block6.func_149730_j()) {
                  enumfacing = EnumFacing.NORTH;
               }
            }
         } else {
            BlockPos blockpos = block == this?p_176455_2_.func_177978_c():p_176455_2_.func_177968_d();
            IBlockState iblockstate4 = p_176455_1_.func_180495_p(blockpos.func_177976_e());
            IBlockState iblockstate5 = p_176455_1_.func_180495_p(blockpos.func_177974_f());
            enumfacing = EnumFacing.EAST;
            EnumFacing enumfacing1;
            if(block == this) {
               enumfacing1 = (EnumFacing)iblockstate.func_177229_b(field_176459_a);
            } else {
               enumfacing1 = (EnumFacing)iblockstate1.func_177229_b(field_176459_a);
            }

            if(enumfacing1 == EnumFacing.WEST) {
               enumfacing = EnumFacing.WEST;
            }

            Block block4 = iblockstate4.func_177230_c();
            Block block5 = iblockstate5.func_177230_c();
            if((block2.func_149730_j() || block4.func_149730_j()) && !block3.func_149730_j() && !block5.func_149730_j()) {
               enumfacing = EnumFacing.EAST;
            }

            if((block3.func_149730_j() || block5.func_149730_j()) && !block2.func_149730_j() && !block4.func_149730_j()) {
               enumfacing = EnumFacing.WEST;
            }
         }

         p_176455_3_ = p_176455_3_.func_177226_a(field_176459_a, enumfacing);
         p_176455_1_.func_180501_a(p_176455_2_, p_176455_3_, 3);
         return p_176455_3_;
      }
   }

   public IBlockState func_176458_f(World p_176458_1_, BlockPos p_176458_2_, IBlockState p_176458_3_) {
      EnumFacing enumfacing = null;

      for(EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
         IBlockState iblockstate = p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(enumfacing1));
         if(iblockstate.func_177230_c() == this) {
            return p_176458_3_;
         }

         if(iblockstate.func_177230_c().func_149730_j()) {
            if(enumfacing != null) {
               enumfacing = null;
               break;
            }

            enumfacing = enumfacing1;
         }
      }

      if(enumfacing != null) {
         return p_176458_3_.func_177226_a(field_176459_a, enumfacing.func_176734_d());
      } else {
         EnumFacing enumfacing2 = (EnumFacing)p_176458_3_.func_177229_b(field_176459_a);
         if(p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(enumfacing2)).func_177230_c().func_149730_j()) {
            enumfacing2 = enumfacing2.func_176734_d();
         }

         if(p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(enumfacing2)).func_177230_c().func_149730_j()) {
            enumfacing2 = enumfacing2.func_176746_e();
         }

         if(p_176458_1_.func_180495_p(p_176458_2_.func_177972_a(enumfacing2)).func_177230_c().func_149730_j()) {
            enumfacing2 = enumfacing2.func_176734_d();
         }

         return p_176458_3_.func_177226_a(field_176459_a, enumfacing2);
      }
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      int i = 0;
      BlockPos blockpos = p_176196_2_.func_177976_e();
      BlockPos blockpos1 = p_176196_2_.func_177974_f();
      BlockPos blockpos2 = p_176196_2_.func_177978_c();
      BlockPos blockpos3 = p_176196_2_.func_177968_d();
      if(p_176196_1_.func_180495_p(blockpos).func_177230_c() == this) {
         if(this.func_176454_e(p_176196_1_, blockpos)) {
            return false;
         }

         ++i;
      }

      if(p_176196_1_.func_180495_p(blockpos1).func_177230_c() == this) {
         if(this.func_176454_e(p_176196_1_, blockpos1)) {
            return false;
         }

         ++i;
      }

      if(p_176196_1_.func_180495_p(blockpos2).func_177230_c() == this) {
         if(this.func_176454_e(p_176196_1_, blockpos2)) {
            return false;
         }

         ++i;
      }

      if(p_176196_1_.func_180495_p(blockpos3).func_177230_c() == this) {
         if(this.func_176454_e(p_176196_1_, blockpos3)) {
            return false;
         }

         ++i;
      }

      return i <= 1;
   }

   private boolean func_176454_e(World p_176454_1_, BlockPos p_176454_2_) {
      if(p_176454_1_.func_180495_p(p_176454_2_).func_177230_c() != this) {
         return false;
      } else {
         for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            if(p_176454_1_.func_180495_p(p_176454_2_.func_177972_a(enumfacing)).func_177230_c() == this) {
               return true;
            }
         }

         return false;
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
      TileEntity tileentity = p_176204_1_.func_175625_s(p_176204_2_);
      if(tileentity instanceof TileEntityChest) {
         tileentity.func_145836_u();
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      TileEntity tileentity = p_180663_1_.func_175625_s(p_180663_2_);
      if(tileentity instanceof IInventory) {
         InventoryHelper.func_180175_a(p_180663_1_, p_180663_2_, (IInventory)tileentity);
         p_180663_1_.func_175666_e(p_180663_2_, this);
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(p_180639_1_.field_72995_K) {
         return true;
      } else {
         ILockableContainer ilockablecontainer = this.func_180676_d(p_180639_1_, p_180639_2_);
         if(ilockablecontainer != null) {
            p_180639_4_.func_71007_a(ilockablecontainer);
            if(this.field_149956_a == 0) {
               p_180639_4_.func_71029_a(StatList.field_181723_aa);
            } else if(this.field_149956_a == 1) {
               p_180639_4_.func_71029_a(StatList.field_181737_U);
            }
         }

         return true;
      }
   }

   public ILockableContainer func_180676_d(World p_180676_1_, BlockPos p_180676_2_) {
      TileEntity tileentity = p_180676_1_.func_175625_s(p_180676_2_);
      if(!(tileentity instanceof TileEntityChest)) {
         return null;
      } else {
         ILockableContainer ilockablecontainer = (TileEntityChest)tileentity;
         if(this.func_176457_m(p_180676_1_, p_180676_2_)) {
            return null;
         } else {
            for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
               BlockPos blockpos = p_180676_2_.func_177972_a(enumfacing);
               Block block = p_180676_1_.func_180495_p(blockpos).func_177230_c();
               if(block == this) {
                  if(this.func_176457_m(p_180676_1_, blockpos)) {
                     return null;
                  }

                  TileEntity tileentity1 = p_180676_1_.func_175625_s(blockpos);
                  if(tileentity1 instanceof TileEntityChest) {
                     if(enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH) {
                        ilockablecontainer = new InventoryLargeChest("container.chestDouble", ilockablecontainer, (TileEntityChest)tileentity1);
                     } else {
                        ilockablecontainer = new InventoryLargeChest("container.chestDouble", (TileEntityChest)tileentity1, ilockablecontainer);
                     }
                  }
               }
            }

            return ilockablecontainer;
         }
      }
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityChest();
   }

   public boolean func_149744_f() {
      return this.field_149956_a == 1;
   }

   public int func_180656_a(IBlockAccess p_180656_1_, BlockPos p_180656_2_, IBlockState p_180656_3_, EnumFacing p_180656_4_) {
      if(!this.func_149744_f()) {
         return 0;
      } else {
         int i = 0;
         TileEntity tileentity = p_180656_1_.func_175625_s(p_180656_2_);
         if(tileentity instanceof TileEntityChest) {
            i = ((TileEntityChest)tileentity).field_145987_o;
         }

         return MathHelper.func_76125_a(i, 0, 15);
      }
   }

   public int func_176211_b(IBlockAccess p_176211_1_, BlockPos p_176211_2_, IBlockState p_176211_3_, EnumFacing p_176211_4_) {
      return p_176211_4_ == EnumFacing.UP?this.func_180656_a(p_176211_1_, p_176211_2_, p_176211_3_, p_176211_4_):0;
   }

   private boolean func_176457_m(World p_176457_1_, BlockPos p_176457_2_) {
      return this.func_176456_n(p_176457_1_, p_176457_2_) || this.func_176453_o(p_176457_1_, p_176457_2_);
   }

   private boolean func_176456_n(World p_176456_1_, BlockPos p_176456_2_) {
      return p_176456_1_.func_180495_p(p_176456_2_.func_177984_a()).func_177230_c().func_149721_r();
   }

   private boolean func_176453_o(World p_176453_1_, BlockPos p_176453_2_) {
      for(Entity entity : p_176453_1_.func_72872_a(EntityOcelot.class, new AxisAlignedBB((double)p_176453_2_.func_177958_n(), (double)(p_176453_2_.func_177956_o() + 1), (double)p_176453_2_.func_177952_p(), (double)(p_176453_2_.func_177958_n() + 1), (double)(p_176453_2_.func_177956_o() + 2), (double)(p_176453_2_.func_177952_p() + 1)))) {
         EntityOcelot entityocelot = (EntityOcelot)entity;
         if(entityocelot.func_70906_o()) {
            return true;
         }
      }

      return false;
   }

   public boolean func_149740_M() {
      return true;
   }

   public int func_180641_l(World p_180641_1_, BlockPos p_180641_2_) {
      return Container.func_94526_b(this.func_180676_d(p_180641_1_, p_180641_2_));
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing enumfacing = EnumFacing.func_82600_a(p_176203_1_);
      if(enumfacing.func_176740_k() == EnumFacing.Axis.Y) {
         enumfacing = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176459_a, enumfacing);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176459_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176459_a});
   }
}
