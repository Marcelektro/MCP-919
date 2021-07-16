package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockNote extends BlockContainer {
   private static final List<String> field_176434_a = Lists.newArrayList(new String[]{"harp", "bd", "snare", "hat", "bassattack"});

   public BlockNote() {
      super(Material.field_151575_d);
      this.func_149647_a(CreativeTabs.field_78028_d);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      boolean flag = p_176204_1_.func_175640_z(p_176204_2_);
      TileEntity tileentity = p_176204_1_.func_175625_s(p_176204_2_);
      if(tileentity instanceof TileEntityNote) {
         TileEntityNote tileentitynote = (TileEntityNote)tileentity;
         if(tileentitynote.field_145880_i != flag) {
            if(flag) {
               tileentitynote.func_175108_a(p_176204_1_, p_176204_2_);
            }

            tileentitynote.field_145880_i = flag;
         }
      }

   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(p_180639_1_.field_72995_K) {
         return true;
      } else {
         TileEntity tileentity = p_180639_1_.func_175625_s(p_180639_2_);
         if(tileentity instanceof TileEntityNote) {
            TileEntityNote tileentitynote = (TileEntityNote)tileentity;
            tileentitynote.func_145877_a();
            tileentitynote.func_175108_a(p_180639_1_, p_180639_2_);
            p_180639_4_.func_71029_a(StatList.field_181735_S);
         }

         return true;
      }
   }

   public void func_180649_a(World p_180649_1_, BlockPos p_180649_2_, EntityPlayer p_180649_3_) {
      if(!p_180649_1_.field_72995_K) {
         TileEntity tileentity = p_180649_1_.func_175625_s(p_180649_2_);
         if(tileentity instanceof TileEntityNote) {
            ((TileEntityNote)tileentity).func_175108_a(p_180649_1_, p_180649_2_);
            p_180649_3_.func_71029_a(StatList.field_181734_R);
         }

      }
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityNote();
   }

   private String func_176433_b(int p_176433_1_) {
      if(p_176433_1_ < 0 || p_176433_1_ >= field_176434_a.size()) {
         p_176433_1_ = 0;
      }

      return (String)field_176434_a.get(p_176433_1_);
   }

   public boolean func_180648_a(World p_180648_1_, BlockPos p_180648_2_, IBlockState p_180648_3_, int p_180648_4_, int p_180648_5_) {
      float f = (float)Math.pow(2.0D, (double)(p_180648_5_ - 12) / 12.0D);
      p_180648_1_.func_72908_a((double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 0.5D, (double)p_180648_2_.func_177952_p() + 0.5D, "note." + this.func_176433_b(p_180648_4_), 3.0F, f);
      p_180648_1_.func_175688_a(EnumParticleTypes.NOTE, (double)p_180648_2_.func_177958_n() + 0.5D, (double)p_180648_2_.func_177956_o() + 1.2D, (double)p_180648_2_.func_177952_p() + 0.5D, (double)p_180648_5_ / 24.0D, 0.0D, 0.0D, new int[0]);
      return true;
   }

   public int func_149645_b() {
      return 3;
   }
}
