package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOre extends Block {
   public BlockOre() {
      this(Material.field_151576_e.func_151565_r());
   }

   public BlockOre(MapColor p_i46390_1_) {
      super(Material.field_151576_e, p_i46390_1_);
      this.func_149647_a(CreativeTabs.field_78030_b);
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return this == Blocks.field_150365_q?Items.field_151044_h:(this == Blocks.field_150482_ag?Items.field_151045_i:(this == Blocks.field_150369_x?Items.field_151100_aR:(this == Blocks.field_150412_bA?Items.field_151166_bC:(this == Blocks.field_150449_bY?Items.field_151128_bU:Item.func_150898_a(this)))));
   }

   public int func_149745_a(Random p_149745_1_) {
      return this == Blocks.field_150369_x?4 + p_149745_1_.nextInt(5):1;
   }

   public int func_149679_a(int p_149679_1_, Random p_149679_2_) {
      if(p_149679_1_ > 0 && Item.func_150898_a(this) != this.func_180660_a((IBlockState)this.func_176194_O().func_177619_a().iterator().next(), p_149679_2_, p_149679_1_)) {
         int i = p_149679_2_.nextInt(p_149679_1_ + 2) - 1;
         if(i < 0) {
            i = 0;
         }

         return this.func_149745_a(p_149679_2_) * (i + 1);
      } else {
         return this.func_149745_a(p_149679_2_);
      }
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      super.func_180653_a(p_180653_1_, p_180653_2_, p_180653_3_, p_180653_4_, p_180653_5_);
      if(this.func_180660_a(p_180653_3_, p_180653_1_.field_73012_v, p_180653_5_) != Item.func_150898_a(this)) {
         int i = 0;
         if(this == Blocks.field_150365_q) {
            i = MathHelper.func_76136_a(p_180653_1_.field_73012_v, 0, 2);
         } else if(this == Blocks.field_150482_ag) {
            i = MathHelper.func_76136_a(p_180653_1_.field_73012_v, 3, 7);
         } else if(this == Blocks.field_150412_bA) {
            i = MathHelper.func_76136_a(p_180653_1_.field_73012_v, 3, 7);
         } else if(this == Blocks.field_150369_x) {
            i = MathHelper.func_76136_a(p_180653_1_.field_73012_v, 2, 5);
         } else if(this == Blocks.field_150449_bY) {
            i = MathHelper.func_76136_a(p_180653_1_.field_73012_v, 2, 5);
         }

         this.func_180637_b(p_180653_1_, p_180653_2_, i);
      }

   }

   public int func_176222_j(World p_176222_1_, BlockPos p_176222_2_) {
      return 0;
   }

   public int func_180651_a(IBlockState p_180651_1_) {
      return this == Blocks.field_150369_x?EnumDyeColor.BLUE.func_176767_b():0;
   }
}
