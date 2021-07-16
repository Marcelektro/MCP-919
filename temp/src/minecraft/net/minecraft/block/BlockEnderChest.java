package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockEnderChest extends BlockContainer {
   public static final PropertyDirection field_176437_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);

   protected BlockEnderChest() {
      super(Material.field_151576_e);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176437_a, EnumFacing.NORTH));
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

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(Blocks.field_150343_Z);
   }

   public int func_149745_a(Random p_149745_1_) {
      return 8;
   }

   protected boolean func_149700_E() {
      return true;
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176437_a, p_180642_8_.func_174811_aO().func_176734_d());
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      p_180633_1_.func_180501_a(p_180633_2_, p_180633_3_.func_177226_a(field_176437_a, p_180633_4_.func_174811_aO().func_176734_d()), 2);
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      InventoryEnderChest inventoryenderchest = p_180639_4_.func_71005_bN();
      TileEntity tileentity = p_180639_1_.func_175625_s(p_180639_2_);
      if(inventoryenderchest != null && tileentity instanceof TileEntityEnderChest) {
         if(p_180639_1_.func_180495_p(p_180639_2_.func_177984_a()).func_177230_c().func_149721_r()) {
            return true;
         } else if(p_180639_1_.field_72995_K) {
            return true;
         } else {
            inventoryenderchest.func_146031_a((TileEntityEnderChest)tileentity);
            p_180639_4_.func_71007_a(inventoryenderchest);
            p_180639_4_.func_71029_a(StatList.field_181738_V);
            return true;
         }
      } else {
         return true;
      }
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityEnderChest();
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      for(int i = 0; i < 3; ++i) {
         int j = p_180655_4_.nextInt(2) * 2 - 1;
         int k = p_180655_4_.nextInt(2) * 2 - 1;
         double d0 = (double)p_180655_2_.func_177958_n() + 0.5D + 0.25D * (double)j;
         double d1 = (double)((float)p_180655_2_.func_177956_o() + p_180655_4_.nextFloat());
         double d2 = (double)p_180655_2_.func_177952_p() + 0.5D + 0.25D * (double)k;
         double d3 = (double)(p_180655_4_.nextFloat() * (float)j);
         double d4 = ((double)p_180655_4_.nextFloat() - 0.5D) * 0.125D;
         double d5 = (double)(p_180655_4_.nextFloat() * (float)k);
         p_180655_1_.func_175688_a(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5, new int[0]);
      }

   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing enumfacing = EnumFacing.func_82600_a(p_176203_1_);
      if(enumfacing.func_176740_k() == EnumFacing.Axis.Y) {
         enumfacing = EnumFacing.NORTH;
      }

      return this.func_176223_P().func_177226_a(field_176437_a, enumfacing);
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_176437_a)).func_176745_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176437_a});
   }
}
