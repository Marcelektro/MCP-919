package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockIce extends BlockBreakable {
   public BlockIce() {
      super(Material.field_151588_w, false);
      this.field_149765_K = 0.98F;
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78030_b);
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, TileEntity p_180657_5_) {
      p_180657_2_.func_71029_a(StatList.field_75934_C[Block.func_149682_b(this)]);
      p_180657_2_.func_71020_j(0.025F);
      if(this.func_149700_E() && EnchantmentHelper.func_77502_d(p_180657_2_)) {
         ItemStack itemstack = this.func_180643_i(p_180657_4_);
         if(itemstack != null) {
            func_180635_a(p_180657_1_, p_180657_3_, itemstack);
         }
      } else {
         if(p_180657_1_.field_73011_w.func_177500_n()) {
            p_180657_1_.func_175698_g(p_180657_3_);
            return;
         }

         int i = EnchantmentHelper.func_77517_e(p_180657_2_);
         this.func_176226_b(p_180657_1_, p_180657_3_, p_180657_4_, i);
         Material material = p_180657_1_.func_180495_p(p_180657_3_.func_177977_b()).func_177230_c().func_149688_o();
         if(material.func_76230_c() || material.func_76224_d()) {
            p_180657_1_.func_175656_a(p_180657_3_, Blocks.field_150358_i.func_176223_P());
         }
      }

   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(p_180650_1_.func_175642_b(EnumSkyBlock.BLOCK, p_180650_2_) > 11 - this.func_149717_k()) {
         if(p_180650_1_.field_73011_w.func_177500_n()) {
            p_180650_1_.func_175698_g(p_180650_2_);
         } else {
            this.func_176226_b(p_180650_1_, p_180650_2_, p_180650_1_.func_180495_p(p_180650_2_), 0);
            p_180650_1_.func_175656_a(p_180650_2_, Blocks.field_150355_j.func_176223_P());
         }
      }
   }

   public int func_149656_h() {
      return 0;
   }
}
