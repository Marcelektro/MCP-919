package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTallGrass extends BlockBush implements IGrowable {
   public static final PropertyEnum<BlockTallGrass.EnumType> field_176497_a = PropertyEnum.<BlockTallGrass.EnumType>func_177709_a("type", BlockTallGrass.EnumType.class);

   protected BlockTallGrass() {
      super(Material.field_151582_l);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176497_a, BlockTallGrass.EnumType.DEAD_BUSH));
      float f = 0.4F;
      this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
   }

   public int func_149635_D() {
      return ColorizerGrass.func_77480_a(0.5D, 1.0D);
   }

   public boolean func_180671_f(World p_180671_1_, BlockPos p_180671_2_, IBlockState p_180671_3_) {
      return this.func_149854_a(p_180671_1_.func_180495_p(p_180671_2_.func_177977_b()).func_177230_c());
   }

   public boolean func_176200_f(World p_176200_1_, BlockPos p_176200_2_) {
      return true;
   }

   public int func_180644_h(IBlockState p_180644_1_) {
      if(p_180644_1_.func_177230_c() != this) {
         return super.func_180644_h(p_180644_1_);
      } else {
         BlockTallGrass.EnumType blocktallgrass$enumtype = (BlockTallGrass.EnumType)p_180644_1_.func_177229_b(field_176497_a);
         return blocktallgrass$enumtype == BlockTallGrass.EnumType.DEAD_BUSH?16777215:ColorizerGrass.func_77480_a(0.5D, 1.0D);
      }
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return p_180662_1_.func_180494_b(p_180662_2_).func_180627_b(p_180662_2_);
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return p_180660_2_.nextInt(8) == 0?Items.field_151014_N:null;
   }

   public int func_149679_a(int p_149679_1_, Random p_149679_2_) {
      return 1 + p_149679_2_.nextInt(p_149679_1_ * 2 + 1);
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, TileEntity p_180657_5_) {
      if(!p_180657_1_.field_72995_K && p_180657_2_.func_71045_bC() != null && p_180657_2_.func_71045_bC().func_77973_b() == Items.field_151097_aZ) {
         p_180657_2_.func_71029_a(StatList.field_75934_C[Block.func_149682_b(this)]);
         func_180635_a(p_180657_1_, p_180657_3_, new ItemStack(Blocks.field_150329_H, 1, ((BlockTallGrass.EnumType)p_180657_4_.func_177229_b(field_176497_a)).func_177044_a()));
      } else {
         super.func_180657_a(p_180657_1_, p_180657_2_, p_180657_3_, p_180657_4_, p_180657_5_);
      }

   }

   public int func_176222_j(World p_176222_1_, BlockPos p_176222_2_) {
      IBlockState iblockstate = p_176222_1_.func_180495_p(p_176222_2_);
      return iblockstate.func_177230_c().func_176201_c(iblockstate);
   }

   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
      for(int i = 1; i < 3; ++i) {
         p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
      }

   }

   public boolean func_176473_a(World p_176473_1_, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
      return p_176473_3_.func_177229_b(field_176497_a) != BlockTallGrass.EnumType.DEAD_BUSH;
   }

   public boolean func_180670_a(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
      return true;
   }

   public void func_176474_b(World p_176474_1_, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
      BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = BlockDoublePlant.EnumPlantType.GRASS;
      if(p_176474_4_.func_177229_b(field_176497_a) == BlockTallGrass.EnumType.FERN) {
         blockdoubleplant$enumplanttype = BlockDoublePlant.EnumPlantType.FERN;
      }

      if(Blocks.field_150398_cm.func_176196_c(p_176474_1_, p_176474_3_)) {
         Blocks.field_150398_cm.func_176491_a(p_176474_1_, p_176474_3_, blockdoubleplant$enumplanttype, 2);
      }

   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176497_a, BlockTallGrass.EnumType.func_177045_a(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((BlockTallGrass.EnumType)p_176201_1_.func_177229_b(field_176497_a)).func_177044_a();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176497_a});
   }

   public Block.EnumOffsetType func_176218_Q() {
      return Block.EnumOffsetType.XYZ;
   }

   public static enum EnumType implements IStringSerializable {
      DEAD_BUSH(0, "dead_bush"),
      GRASS(1, "tall_grass"),
      FERN(2, "fern");

      private static final BlockTallGrass.EnumType[] field_177048_d = new BlockTallGrass.EnumType[values().length];
      private final int field_177049_e;
      private final String field_177046_f;

      private EnumType(int p_i45676_3_, String p_i45676_4_) {
         this.field_177049_e = p_i45676_3_;
         this.field_177046_f = p_i45676_4_;
      }

      public int func_177044_a() {
         return this.field_177049_e;
      }

      public String toString() {
         return this.field_177046_f;
      }

      public static BlockTallGrass.EnumType func_177045_a(int p_177045_0_) {
         if(p_177045_0_ < 0 || p_177045_0_ >= field_177048_d.length) {
            p_177045_0_ = 0;
         }

         return field_177048_d[p_177045_0_];
      }

      public String func_176610_l() {
         return this.field_177046_f;
      }

      static {
         for(BlockTallGrass.EnumType blocktallgrass$enumtype : values()) {
            field_177048_d[blocktallgrass$enumtype.func_177044_a()] = blocktallgrass$enumtype;
         }

      }
   }
}
