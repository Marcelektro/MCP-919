package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBanner extends BlockContainer {
   public static final PropertyDirection field_176449_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyInteger field_176448_b = PropertyInteger.func_177719_a("rotation", 0, 15);

   protected BlockBanner() {
      super(Material.field_151575_d);
      float f = 0.25F;
      float f1 = 1.0F;
      this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
   }

   public String func_149732_F() {
      return StatCollector.func_74838_a("item.banner.white.name");
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public AxisAlignedBB func_180646_a(World p_180646_1_, BlockPos p_180646_2_) {
      this.func_180654_a(p_180646_1_, p_180646_2_);
      return super.func_180646_a(p_180646_1_, p_180646_2_);
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return true;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_181623_g() {
      return true;
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityBanner();
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_179564_cE;
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_179564_cE;
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      TileEntity tileentity = p_180653_1_.func_175625_s(p_180653_2_);
      if(tileentity instanceof TileEntityBanner) {
         ItemStack itemstack = new ItemStack(Items.field_179564_cE, 1, ((TileEntityBanner)tileentity).func_175115_b());
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         tileentity.func_145841_b(nbttagcompound);
         nbttagcompound.func_82580_o("x");
         nbttagcompound.func_82580_o("y");
         nbttagcompound.func_82580_o("z");
         nbttagcompound.func_82580_o("id");
         itemstack.func_77983_a("BlockEntityTag", nbttagcompound);
         func_180635_a(p_180653_1_, p_180653_2_, itemstack);
      } else {
         super.func_180653_a(p_180653_1_, p_180653_2_, p_180653_3_, p_180653_4_, p_180653_5_);
      }

   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return !this.func_181087_e(p_176196_1_, p_176196_2_) && super.func_176196_c(p_176196_1_, p_176196_2_);
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, TileEntity p_180657_5_) {
      if(p_180657_5_ instanceof TileEntityBanner) {
         TileEntityBanner tileentitybanner = (TileEntityBanner)p_180657_5_;
         ItemStack itemstack = new ItemStack(Items.field_179564_cE, 1, ((TileEntityBanner)p_180657_5_).func_175115_b());
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         TileEntityBanner.func_181020_a(nbttagcompound, tileentitybanner.func_175115_b(), tileentitybanner.func_181021_d());
         itemstack.func_77983_a("BlockEntityTag", nbttagcompound);
         func_180635_a(p_180657_1_, p_180657_3_, itemstack);
      } else {
         super.func_180657_a(p_180657_1_, p_180657_2_, p_180657_3_, p_180657_4_, (TileEntity)null);
      }

   }

   public static class BlockBannerHanging extends BlockBanner {
      public BlockBannerHanging() {
         this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176449_a, EnumFacing.NORTH));
      }

      public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
         EnumFacing enumfacing = (EnumFacing)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176449_a);
         float f = 0.0F;
         float f1 = 0.78125F;
         float f2 = 0.0F;
         float f3 = 1.0F;
         float f4 = 0.125F;
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         switch(enumfacing) {
         case NORTH:
         default:
            this.func_149676_a(f2, f, 1.0F - f4, f3, f1, 1.0F);
            break;
         case SOUTH:
            this.func_149676_a(f2, f, 0.0F, f3, f1, f4);
            break;
         case WEST:
            this.func_149676_a(1.0F - f4, f, f2, 1.0F, f1, f3);
            break;
         case EAST:
            this.func_149676_a(0.0F, f, f2, f4, f1, f3);
         }

      }

      public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
         EnumFacing enumfacing = (EnumFacing)p_176204_3_.func_177229_b(field_176449_a);
         if(!p_176204_1_.func_180495_p(p_176204_2_.func_177972_a(enumfacing.func_176734_d())).func_177230_c().func_149688_o().func_76220_a()) {
            this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
            p_176204_1_.func_175698_g(p_176204_2_);
         }

         super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
      }

      public IBlockState func_176203_a(int p_176203_1_) {
         EnumFacing enumfacing = EnumFacing.func_82600_a(p_176203_1_);
         if(enumfacing.func_176740_k() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
         }

         return this.func_176223_P().func_177226_a(field_176449_a, enumfacing);
      }

      public int func_176201_c(IBlockState p_176201_1_) {
         return ((EnumFacing)p_176201_1_.func_177229_b(field_176449_a)).func_176745_a();
      }

      protected BlockState func_180661_e() {
         return new BlockState(this, new IProperty[]{field_176449_a});
      }
   }

   public static class BlockBannerStanding extends BlockBanner {
      public BlockBannerStanding() {
         this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176448_b, Integer.valueOf(0)));
      }

      public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
         if(!p_176204_1_.func_180495_p(p_176204_2_.func_177977_b()).func_177230_c().func_149688_o().func_76220_a()) {
            this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
            p_176204_1_.func_175698_g(p_176204_2_);
         }

         super.func_176204_a(p_176204_1_, p_176204_2_, p_176204_3_, p_176204_4_);
      }

      public IBlockState func_176203_a(int p_176203_1_) {
         return this.func_176223_P().func_177226_a(field_176448_b, Integer.valueOf(p_176203_1_));
      }

      public int func_176201_c(IBlockState p_176201_1_) {
         return ((Integer)p_176201_1_.func_177229_b(field_176448_b)).intValue();
      }

      protected BlockState func_180661_e() {
         return new BlockState(this, new IProperty[]{field_176448_b});
      }
   }
}
