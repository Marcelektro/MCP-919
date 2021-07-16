package net.minecraft.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockPistonStructureHelper;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPistonBase extends Block {
   public static final PropertyDirection field_176321_a = PropertyDirection.func_177714_a("facing");
   public static final PropertyBool field_176320_b = PropertyBool.func_177716_a("extended");
   private final boolean field_150082_a;

   public BlockPistonBase(boolean p_i45443_1_) {
      super(Material.field_76233_E);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176321_a, EnumFacing.NORTH).func_177226_a(field_176320_b, Boolean.valueOf(false)));
      this.field_150082_a = p_i45443_1_;
      this.func_149672_a(field_149780_i);
      this.func_149711_c(0.5F);
      this.func_149647_a(CreativeTabs.field_78028_d);
   }

   public boolean func_149662_c() {
      return false;
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_.func_177226_a(field_176321_a, func_180695_a(p_180633_1_, p_180633_2_, p_180633_4_)), 2);
      if(!p_180633_1_.field_72995_K) {
         this.func_176316_e(p_180633_1_, p_180633_2_, p_180633_3_);
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K) {
         this.func_176316_e(p_176204_1_, p_176204_2_, p_176204_3_);
      }

   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      if(!p_176213_1_.field_72995_K && p_176213_1_.func_175625_s(p_176213_2_) == null) {
         this.func_176316_e(p_176213_1_, p_176213_2_, p_176213_3_);
      }

   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176321_a, func_180695_a(p_180642_1_, p_180642_2_, p_180642_8_)).func_177226_a(field_176320_b, Boolean.valueOf(false));
   }

   private void func_176316_e(World p_176316_1_, BlockPos p_176316_2_, IBlockState p_176316_3_) {
      EnumFacing enumfacing = (EnumFacing)p_176316_3_.func_177229_b(field_176321_a);
      boolean flag = this.func_176318_b(p_176316_1_, p_176316_2_, enumfacing);
      if(flag && !((Boolean)p_176316_3_.func_177229_b(field_176320_b)).booleanValue()) {
         if((new BlockPistonStructureHelper(p_176316_1_, p_176316_2_, enumfacing, true)).func_177253_a()) {
            p_176316_1_.func_175641_c(p_176316_2_, this, 0, enumfacing.func_176745_a());
         }
      } else if(!flag && ((Boolean)p_176316_3_.func_177229_b(field_176320_b)).booleanValue()) {
         p_176316_1_.func_180501_a(p_176316_2_, p_176316_3_.func_177226_a(field_176320_b, Boolean.valueOf(false)), 2);
         p_176316_1_.func_175641_c(p_176316_2_, this, 1, enumfacing.func_176745_a());
      }

   }

   private boolean func_176318_b(World p_176318_1_, BlockPos p_176318_2_, EnumFacing p_176318_3_) {
      for(EnumFacing enumfacing : EnumFacing.values()) {
         if(enumfacing != p_176318_3_ && p_176318_1_.func_175709_b(p_176318_2_.func_177972_a(enumfacing), enumfacing)) {
            return true;
         }
      }

      if(p_176318_1_.func_175709_b(p_176318_2_, EnumFacing.DOWN)) {
         return true;
      } else {
         BlockPos blockpos = p_176318_2_.func_177984_a();

         for(EnumFacing enumfacing1 : EnumFacing.values()) {
            if(enumfacing1 != EnumFacing.DOWN && p_176318_1_.func_175709_b(blockpos.func_177972_a(enumfacing1), enumfacing1)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean func_180648_a(World p_180648_1_, BlockPos p_180648_2_, IBlockState p_180648_3_, int p_180648_4_, int p_180648_5_) {
      EnumFacing enumfacing = (EnumFacing)p_180648_3_.func_177229_b(field_176321_a);
      if(!p_180648_1_.field_72995_K) {
         boolean flag = this.func_176318_b(p_180648_1_, p_180648_2_, enumfacing);
         if(flag && p_180648_4_ == 1) {
            p_180648_1_.func_180501_a(p_180648_2_, p_180648_3_.func_177226_a(field_176320_b, Boolean.valueOf(true)), 2);
            return false;
         }

         if(!flag && p_180648_4_ == 0) {
            return false;
         }
      }

      if(p_180648_4_ == 0) {
         if(!this.func_176319_a(p_180648_1_, p_180648_2_, enumfacing, true)) {
            return false;
         }

         p_180648_1_.func_180501_a(p_180648_2_, p_180648_3_.func_177226_a(field_176320_b, Boolean.valueOf(true)), 2);
         p_180648_1_.func_72908_a((double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 0.5D, (double)p_180648_2_.func_177952_p() + 0.5D, "tile.piston.out", 0.5F, p_180648_1_.field_73012_v.nextFloat() * 0.25F + 0.6F);
      } else if(p_180648_4_ == 1) {
         TileEntity tileentity1 = p_180648_1_.func_175625_s(p_180648_2_.func_177972_a(enumfacing));
         if(tileentity1 instanceof TileEntityPiston) {
            ((TileEntityPiston)tileentity1).func_145866_f();
         }

         p_180648_1_.func_180501_a(p_180648_2_, Blocks.field_180384_M.func_176223_P().func_177226_a(BlockPistonMoving.field_176426_a, enumfacing).func_177226_a(BlockPistonMoving.field_176425_b, this.field_150082_a?BlockPistonExtension.EnumPistonType.STICKY:BlockPistonExtension.EnumPistonType.DEFAULT), 3);
         p_180648_1_.func_175690_a(p_180648_2_, BlockPistonMoving.func_176423_a(this.func_176203_a(p_180648_5_), enumfacing, false, true));
         if(this.field_150082_a) {
            BlockPos blockpos = p_180648_2_.func_177982_a(enumfacing.func_82601_c() * 2, enumfacing.func_96559_d() * 2, enumfacing.func_82599_e() * 2);
            Block block = p_180648_1_.func_180495_p(blockpos).func_177230_c();
            boolean flag1 = false;
            if(block == Blocks.field_180384_M) {
               TileEntity tileentity = p_180648_1_.func_175625_s(blockpos);
               if(tileentity instanceof TileEntityPiston) {
                  TileEntityPiston tileentitypiston = (TileEntityPiston)tileentity;
                  if(tileentitypiston.func_174930_e() == enumfacing && tileentitypiston.func_145868_b()) {
                     tileentitypiston.func_145866_f();
                     flag1 = true;
                  }
               }
            }

            if(!flag1 && block.func_149688_o() != Material.field_151579_a && func_180696_a(block, p_180648_1_, blockpos, enumfacing.func_176734_d(), false) && (block.func_149656_h() == 0 || block == Blocks.field_150331_J || block == Blocks.field_150320_F)) {
               this.func_176319_a(p_180648_1_, p_180648_2_, enumfacing, false);
            }
         } else {
            p_180648_1_.func_175698_g(p_180648_2_.func_177972_a(enumfacing));
         }

         p_180648_1_.func_72908_a((double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 0.5D, (double)p_180648_2_.func_177952_p() + 0.5D, "tile.piston.in", 0.5F, p_180648_1_.field_73012_v.nextFloat() * 0.15F + 0.6F);
      }

      return true;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      IBlockState iblockstate = p_180654_1_.func_180495_p(p_180654_2_);
      if(iblockstate.func_177230_c() == this && ((Boolean)iblockstate.func_177229_b(field_176320_b)).booleanValue()) {
         float f = 0.25F;
         EnumFacing enumfacing = (EnumFacing)iblockstate.func_177229_b(field_176321_a);
         if(enumfacing != null) {
            switch(enumfacing) {
            case DOWN:
               this.func_149676_a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
               break;
            case UP:
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
               break;
            case NORTH:
               this.func_149676_a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
               break;
            case SOUTH:
               this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
               break;
            case WEST:
               this.func_149676_a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
               break;
            case EAST:
               this.func_149676_a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
         }
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public void func_149683_g() {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void func_180638_a(World p_180638_1_, BlockPos p_180638_2_, IBlockState p_180638_3_, AxisAlignedBB p_180638_4_, List<AxisAlignedBB> p_180638_5_, Entity p_180638_6_) {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.func_180638_a(p_180638_1_, p_180638_2_, p_180638_3_, p_180638_4_, p_180638_5_, p_180638_6_);
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      this.func_180654_a(p_180640_1_, p_180640_2_);
      return super.func_180640_a(p_180640_1_, p_180640_2_, p_180640_3_);
   }

   public boolean func_149686_d() {
      return false;
   }

   public static EnumFacing func_176317_b(int p_176317_0_) {
      int i = p_176317_0_ & 7;
      return i > 5?null:EnumFacing.func_82600_a(i);
   }

   public static EnumFacing func_180695_a(World p_180695_0_, BlockPos p_180695_1_, EntityLivingBase p_180695_2_) {
      if(MathHelper.func_76135_e((float)p_180695_2_.field_70165_t - (float)p_180695_1_.func_177958_n()) < 2.0F && MathHelper.func_76135_e((float)p_180695_2_.field_70161_v - (float)p_180695_1_.func_177952_p()) < 2.0F) {
         double d0 = p_180695_2_.field_70163_u + (double)p_180695_2_.func_70047_e();
         if(d0 - (double)p_180695_1_.func_177956_o() > 2.0D) {
            return EnumFacing.UP;
         }

         if((double)p_180695_1_.func_177956_o() - d0 > 0.0D) {
            return EnumFacing.DOWN;
         }
      }

      return p_180695_2_.func_174811_aO().func_176734_d();
   }

   public static boolean func_180696_a(Block p_180696_0_, World p_180696_1_, BlockPos p_180696_2_, EnumFacing p_180696_3_, boolean p_180696_4_) {
      if(p_180696_0_ == Blocks.field_150343_Z) {
         return false;
      } else if(!p_180696_1_.func_175723_af().func_177746_a(p_180696_2_)) {
         return false;
      } else if(p_180696_2_.func_177956_o() >= 0 && (p_180696_3_ != EnumFacing.DOWN || p_180696_2_.func_177956_o() != 0)) {
         if(p_180696_2_.func_177956_o() <= p_180696_1_.func_72800_K() - 1 && (p_180696_3_ != EnumFacing.UP || p_180696_2_.func_177956_o() != p_180696_1_.func_72800_K() - 1)) {
            if(p_180696_0_ != Blocks.field_150331_J && p_180696_0_ != Blocks.field_150320_F) {
               if(p_180696_0_.func_176195_g(p_180696_1_, p_180696_2_) == -1.0F) {
                  return false;
               }

               if(p_180696_0_.func_149656_h() == 2) {
                  return false;
               }

               if(p_180696_0_.func_149656_h() == 1) {
                  if(!p_180696_4_) {
                     return false;
                  }

                  return true;
               }
            } else if(((Boolean)p_180696_1_.func_180495_p(p_180696_2_).func_177229_b(field_176320_b)).booleanValue()) {
               return false;
            }

            return !(p_180696_0_ instanceof ITileEntityProvider);
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean func_176319_a(World p_176319_1_, BlockPos p_176319_2_, EnumFacing p_176319_3_, boolean p_176319_4_) {
      if(!p_176319_4_) {
         p_176319_1_.func_175698_g(p_176319_2_.func_177972_a(p_176319_3_));
      }

      BlockPistonStructureHelper blockpistonstructurehelper = new BlockPistonStructureHelper(p_176319_1_, p_176319_2_, p_176319_3_, p_176319_4_);
      List<BlockPos> list = blockpistonstructurehelper.func_177254_c();
      List<BlockPos> list1 = blockpistonstructurehelper.func_177252_d();
      if(!blockpistonstructurehelper.func_177253_a()) {
         return false;
      } else {
         int i = list.size() + list1.size();
         Block[] ablock = new Block[i];
         EnumFacing enumfacing = p_176319_4_?p_176319_3_:p_176319_3_.func_176734_d();

         for(int j = list1.size() - 1; j >= 0; --j) {
            BlockPos blockpos = (BlockPos)list1.get(j);
            Block block = p_176319_1_.func_180495_p(blockpos).func_177230_c();
            block.func_176226_b(p_176319_1_, blockpos, p_176319_1_.func_180495_p(blockpos), 0);
            p_176319_1_.func_175698_g(blockpos);
            --i;
            ablock[i] = block;
         }

         for(int k = list.size() - 1; k >= 0; --k) {
            BlockPos blockpos2 = (BlockPos)list.get(k);
            IBlockState iblockstate = p_176319_1_.func_180495_p(blockpos2);
            Block block1 = iblockstate.func_177230_c();
            block1.func_176201_c(iblockstate);
            p_176319_1_.func_175698_g(blockpos2);
            blockpos2 = blockpos2.func_177972_a(enumfacing);
            p_176319_1_.func_180501_a(blockpos2, Blocks.field_180384_M.func_176223_P().func_177226_a(field_176321_a, p_176319_3_), 4);
            p_176319_1_.func_175690_a(blockpos2, BlockPistonMoving.func_176423_a(iblockstate, p_176319_3_, p_176319_4_, false));
            --i;
            ablock[i] = block1;
         }

         BlockPos blockpos1 = p_176319_2_.func_177972_a(p_176319_3_);
         if(p_176319_4_) {
            BlockPistonExtension.EnumPistonType blockpistonextension$enumpistontype = this.field_150082_a?BlockPistonExtension.EnumPistonType.STICKY:BlockPistonExtension.EnumPistonType.DEFAULT;
            IBlockState iblockstate1 = Blocks.field_150332_K.func_176223_P().func_177226_a(BlockPistonExtension.field_176326_a, p_176319_3_).func_177226_a(BlockPistonExtension.field_176325_b, blockpistonextension$enumpistontype);
            IBlockState iblockstate2 = Blocks.field_180384_M.func_176223_P().func_177226_a(BlockPistonMoving.field_176426_a, p_176319_3_).func_177226_a(BlockPistonMoving.field_176425_b, this.field_150082_a?BlockPistonExtension.EnumPistonType.STICKY:BlockPistonExtension.EnumPistonType.DEFAULT);
            p_176319_1_.func_180501_a(blockpos1, iblockstate2, 4);
            p_176319_1_.func_175690_a(blockpos1, BlockPistonMoving.func_176423_a(iblockstate1, p_176319_3_, true, false));
         }

         for(int l = list1.size() - 1; l >= 0; --l) {
            p_176319_1_.func_175685_c((BlockPos)list1.get(l), ablock[i++]);
         }

         for(int i1 = list.size() - 1; i1 >= 0; --i1) {
            p_176319_1_.func_175685_c((BlockPos)list.get(i1), ablock[i++]);
         }

         if(p_176319_4_) {
            p_176319_1_.func_175685_c(blockpos1, Blocks.field_150332_K);
            p_176319_1_.func_175685_c(p_176319_2_, this);
         }

         return true;
      }
   }

   public IBlockState func_176217_b(IBlockState p_176217_1_) {
      return this.func_176223_P().func_177226_a(field_176321_a, EnumFacing.UP);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176321_a, func_176317_b(p_176203_1_)).func_177226_a(field_176320_b, Boolean.valueOf((p_176203_1_ & 8) > 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176321_a)).func_176745_a();
      if(((Boolean)p_176201_1_.func_177229_b(field_176320_b)).booleanValue()) {
         i |= 8;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176321_a, field_176320_b});
   }
}
