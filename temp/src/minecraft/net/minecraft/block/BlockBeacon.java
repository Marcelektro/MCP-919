package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.HttpUtil;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class BlockBeacon extends BlockContainer {
   public BlockBeacon() {
      super(Material.field_151592_s, MapColor.field_151648_G);
      this.func_149711_c(3.0F);
      this.func_149647_a(CreativeTabs.field_78026_f);
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityBeacon();
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity tileentity = p_180639_1_.func_175625_s(p_180639_2_);
         if(tileentity instanceof TileEntityBeacon) {
            p_180639_4_.func_71007_a((TileEntityBeacon)tileentity);
            p_180639_4_.func_71029_a(StatList.field_181730_N);
         }

         return true;
      }
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public int func_149645_b() {
      return 3;
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      super.func_180633_a(p_180633_1_, p_180633_2_, p_180633_3_, p_180633_4_, p_180633_5_);
      if(p_180633_5_.func_82837_s()) {
         TileEntity tileentity = p_180633_1_.func_175625_s(p_180633_2_);
         if(tileentity instanceof TileEntityBeacon) {
            ((TileEntityBeacon)tileentity).func_145999_a(p_180633_5_.func_82833_r());
         }
      }

   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      TileEntity tileentity = p_176204_1_.func_175625_s(p_176204_2_);
      if(tileentity instanceof TileEntityBeacon) {
         ((TileEntityBeacon)tileentity).func_174908_m();
         p_176204_1_.func_175641_c(p_176204_2_, this, 1, 0);
      }

   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public static void func_176450_d(final World p_176450_0_, final BlockPos p_176450_1_) {
      HttpUtil.field_180193_a.submit(new Runnable() {
         public void run() {
            Chunk chunk = p_176450_0_.func_175726_f(p_176450_1_);

            for(int i = p_176450_1_.func_177956_o() - 1; i >= 0; --i) {
               final BlockPos blockpos = new BlockPos(p_176450_1_.func_177958_n(), i, p_176450_1_.func_177952_p());
               if(!chunk.func_177444_d(blockpos)) {
                  break;
               }

               IBlockState iblockstate = p_176450_0_.func_180495_p(blockpos);
               if(iblockstate.func_177230_c() == Blocks.field_150461_bJ) {
                  ((WorldServer)p_176450_0_).func_152344_a(new Runnable() {
                     public void run() {
                        TileEntity tileentity = p_176450_0_.func_175625_s(blockpos);
                        if(tileentity instanceof TileEntityBeacon) {
                           ((TileEntityBeacon)tileentity).func_174908_m();
                           p_176450_0_.func_175641_c(blockpos, Blocks.field_150461_bJ, 1, 0);
                        }

                     }
                  });
               }
            }

         }
      });
   }
}
