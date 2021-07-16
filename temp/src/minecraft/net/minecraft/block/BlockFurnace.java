package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockFurnace extends BlockContainer {
   public static final PropertyDirection field_176447_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   private final boolean field_149932_b;
   private static boolean field_149934_M;

   protected BlockFurnace(boolean p_i45407_1_) {
      super(Material.field_151576_e);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176447_a, EnumFacing.NORTH));
      this.field_149932_b = p_i45407_1_;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(Blocks.field_150460_al);
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176445_e(p_176213_1_, p_176213_2_, p_176213_3_);
   }

   private void func_176445_e(World p_176445_1_, BlockPos p_176445_2_, IBlockState p_176445_3_) {
      if(!p_176445_1_.field_72995_K) {
         Block block = p_176445_1_.func_180495_p(p_176445_2_.func_177978_c()).func_177230_c();
         Block block1 = p_176445_1_.func_180495_p(p_176445_2_.func_177968_d()).func_177230_c();
         Block block2 = p_176445_1_.func_180495_p(p_176445_2_.func_177976_e()).func_177230_c();
         Block block3 = p_176445_1_.func_180495_p(p_176445_2_.func_177974_f()).func_177230_c();
         EnumFacing enumfacing = (EnumFacing)p_176445_3_.func_177229_b(field_176447_a);
         if(enumfacing == EnumFacing.NORTH && block.func_149730_j() && !block1.func_149730_j()) {
            enumfacing = EnumFacing.SOUTH;
         } else if(enumfacing == EnumFacing.SOUTH && block1.func_149730_j() && !block.func_149730_j()) {
            enumfacing = EnumFacing.NORTH;
         } else if(enumfacing == EnumFacing.WEST && block2.func_149730_j() && !block3.func_149730_j()) {
            enumfacing = EnumFacing.EAST;
         } else if(enumfacing == EnumFacing.EAST && block3.func_149730_j() && !block2.func_149730_j()) {
            enumfacing = EnumFacing.WEST;
         }

         p_176445_1_.func_180501_a(p_176445_2_, p_176445_3_.func_177226_a(field_176447_a, enumfacing), 2);
      }
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if(this.field_149932_b) {
         EnumFacing enumfacing = (EnumFacing)p_180655_3_.func_177229_b(field_176447_a);
         double d0 = (double)p_180655_2_.func_177958_n() + 0.5D;
         double d1 = (double)p_180655_2_.func_177956_o() + p_180655_4_.nextDouble() * 6.0D / 16.0D;
         double d2 = (double)p_180655_2_.func_177952_p() + 0.5D;
         double d3 = 0.52D;
         double d4 = p_180655_4_.nextDouble() * 0.6D - 0.3D;
         switch(enumfacing) {
         case WEST:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
            break;
         case EAST:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
            break;
         case NORTH:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
            break;
         case SOUTH:
            p_180655_1_.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
            p_180655_1_.func_175688_a(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
         }

      }
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity tileentity = p_180639_1_.func_175625_s(p_180639_2_);
         if(tileentity instanceof TileEntityFurnace) {
            p_180639_4_.func_71007_a((TileEntityFurnace)tileentity);
            p_180639_4_.func_71029_a(StatList.field_181741_Y);
         }

         return true;
      }
   }

   public static void func_176446_a(boolean p_176446_0_, World p_176446_1_, BlockPos p_176446_2_) {
      IBlockState iblockstate = p_176446_1_.func_180495_p(p_176446_2_);
      TileEntity tileentity = p_176446_1_.func_175625_s(p_176446_2_);
      field_149934_M = true;
      if(p_176446_0_) {
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150470_am.func_176223_P().func_177226_a(field_176447_a, iblockstate.func_177229_b(field_176447_a)), 3);
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150470_am.func_176223_P().func_177226_a(field_176447_a, iblockstate.func_177229_b(field_176447_a)), 3);
      } else {
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150460_al.func_176223_P().func_177226_a(field_176447_a, iblockstate.func_177229_b(field_176447_a)), 3);
         p_176446_1_.func_180501_a(p_176446_2_, Blocks.field_150460_al.func_176223_P().func_177226_a(field_176447_a, iblockstate.func_177229_b(field_176447_a)), 3);
      }

      field_149934_M = false;
      if(tileentity != null) {
         tileentity.func_145829_t();
         p_176446_1_.func_175690_a(p_176446_2_, tileentity);
      }

   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityFurnace();
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176447_a, p_180642_8_.func_174811_aO().func_176734_d());
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_.func_177226_a(field_176447_a, p_180633_4_.func_174811_aO().func_176734_d()), 2);
      if(p_180633_5_.func_82837_s()) {
         TileEntity tileentity = p_180633_1_.func_175625_s(p_180633_2_);
         if(tileentity instanceof TileEntityFurnace) {
            ((TileEntityFurnace)tileentity).func_145951_a(p_180633_5_.func_82833_r());
         }
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      if(!field_149934_M) {
         TileEntity tileentity = p_180663_1_.func_175625_s(p_180663_2_);
         if(tileentity instanceof TileEntityFurnace) {
            InventoryHelper.func_180175_a(p_180663_1_, p_180663_2_, (TileEntityFurnace)tileentity);
            p_180663_1_.func_175666_e(p_180663_2_, this);
         }
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public boolean func_149740_M() {
      return true;
   }

   public int func_180641_l(World p_180641_1_, BlockPos p_180641_2_) {
      return Container.func_178144_a(p_180641_1_.func_175625_s(p_180641_2_));
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Item.func_150898_a(Blocks.field_150460_al);
   }

   public int func_149645_b() {
      return 3;
   }

   public IBlockState func_176217_b(IBlockState p_176217_1_) {
      return this.func_176223_P().func_177226_a(field_176447_a, EnumFacing.SOUTH);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing enumfacing = EnumFacing.func_82600_a(p_176203_1_);
      if(enumfacing.func_176740_k() == EnumFacing.Axis.Y) {
         enumfacing = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176447_a, enumfacing);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176447_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176447_a});
   }
}
