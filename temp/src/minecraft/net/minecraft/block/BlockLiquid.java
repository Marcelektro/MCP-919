package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public abstract class BlockLiquid extends Block {
   public static final PropertyInteger field_176367_b = PropertyInteger.func_177719_a("level", 0, 15);

   protected BlockLiquid(Material p_i45413_1_) {
      super(p_i45413_1_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176367_b, Integer.valueOf(0)));
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.func_149675_a(true);
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return this.field_149764_J != Material.field_151587_i;
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return this.field_149764_J == Material.field_151586_h?BiomeColorHelper.func_180288_c(p_180662_1_, p_180662_2_):16777215;
   }

   public static float func_149801_b(int p_149801_0_) {
      if(p_149801_0_ >= 8) {
         p_149801_0_ = 0;
      }

      return (float)(p_149801_0_ + 1) / 9.0F;
   }

   protected int func_176362_e(IBlockAccess p_176362_1_, BlockPos p_176362_2_) {
      return p_176362_1_.func_180495_p(p_176362_2_).func_177230_c().func_149688_o() == this.field_149764_J?((Integer)p_176362_1_.func_180495_p(p_176362_2_).func_177229_b(field_176367_b)).intValue():-1;
   }

   protected int func_176366_f(IBlockAccess p_176366_1_, BlockPos p_176366_2_) {
      int i = this.func_176362_e(p_176366_1_, p_176366_2_);
      return i >= 8?0:i;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_176209_a(IBlockState p_176209_1_, boolean p_176209_2_) {
      return p_176209_2_ && ((Integer)p_176209_1_.func_177229_b(field_176367_b)).intValue() == 0;
   }

   public boolean func_176212_b(IBlockAccess p_176212_1_, BlockPos p_176212_2_, EnumFacing p_176212_3_) {
      Material material = p_176212_1_.func_180495_p(p_176212_2_).func_177230_c().func_149688_o();
      return material == this.field_149764_J?false:(p_176212_3_ == EnumFacing.UP?true:(material == Material.field_151588_w?false:super.func_176212_b(p_176212_1_, p_176212_2_, p_176212_3_)));
   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      return p_176225_1_.func_180495_p(p_176225_2_).func_177230_c().func_149688_o() == this.field_149764_J?false:(p_176225_3_ == EnumFacing.UP?true:super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_));
   }

   public boolean func_176364_g(IBlockAccess p_176364_1_, BlockPos p_176364_2_) {
      for(int i = -1; i <= 1; ++i) {
         for(int j = -1; j <= 1; ++j) {
            IBlockState iblockstate = p_176364_1_.func_180495_p(p_176364_2_.func_177982_a(i, 0, j));
            Block block = iblockstate.func_177230_c();
            Material material = block.func_149688_o();
            if(material != this.field_149764_J && !block.func_149730_j()) {
               return true;
            }
         }
      }

      return false;
   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      return null;
   }

   public int func_149645_b() {
      return 1;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   protected Vec3 func_180687_h(IBlockAccess p_180687_1_, BlockPos p_180687_2_) {
      Vec3 vec3 = new Vec3(0.0D, 0.0D, 0.0D);
      int i = this.func_176366_f(p_180687_1_, p_180687_2_);

      for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
         BlockPos blockpos = p_180687_2_.func_177972_a(enumfacing);
         int j = this.func_176366_f(p_180687_1_, blockpos);
         if(j < 0) {
            if(!p_180687_1_.func_180495_p(blockpos).func_177230_c().func_149688_o().func_76230_c()) {
               j = this.func_176366_f(p_180687_1_, blockpos.func_177977_b());
               if(j >= 0) {
                  int k = j - (i - 8);
                  vec3 = vec3.func_72441_c((double)((blockpos.func_177958_n() - p_180687_2_.func_177958_n()) * k), (double)((blockpos.func_177956_o() - p_180687_2_.func_177956_o()) * k), (double)((blockpos.func_177952_p() - p_180687_2_.func_177952_p()) * k));
               }
            }
         } else if(j >= 0) {
            int l = j - i;
            vec3 = vec3.func_72441_c((double)((blockpos.func_177958_n() - p_180687_2_.func_177958_n()) * l), (double)((blockpos.func_177956_o() - p_180687_2_.func_177956_o()) * l), (double)((blockpos.func_177952_p() - p_180687_2_.func_177952_p()) * l));
         }
      }

      if(((Integer)p_180687_1_.func_180495_p(p_180687_2_).func_177229_b(field_176367_b)).intValue() >= 8) {
         for(EnumFacing enumfacing1 : EnumFacing.Plane.HORIZONTAL) {
            BlockPos blockpos1 = p_180687_2_.func_177972_a(enumfacing1);
            if(this.func_176212_b(p_180687_1_, blockpos1, enumfacing1) || this.func_176212_b(p_180687_1_, blockpos1.func_177984_a(), enumfacing1)) {
               vec3 = vec3.func_72432_b().func_72441_c(0.0D, -6.0D, 0.0D);
               break;
            }
         }
      }

      return vec3.func_72432_b();
   }

   public Vec3 func_176197_a(World p_176197_1_, BlockPos p_176197_2_, Entity p_176197_3_, Vec3 p_176197_4_) {
      return p_176197_4_.func_178787_e(this.func_180687_h(p_176197_1_, p_176197_2_));
   }

   public int func_149738_a(World p_149738_1_) {
      return this.field_149764_J == Material.field_151586_h?5:(this.field_149764_J == Material.field_151587_i?(p_149738_1_.field_73011_w.func_177495_o()?10:30):0);
   }

   public int func_176207_c(IBlockAccess p_176207_1_, BlockPos p_176207_2_) {
      int i = p_176207_1_.func_175626_b(p_176207_2_, 0);
      int j = p_176207_1_.func_175626_b(p_176207_2_.func_177984_a(), 0);
      int k = i & 255;
      int l = j & 255;
      int i1 = i >> 16 & 255;
      int j1 = j >> 16 & 255;
      return (k > l?k:l) | (i1 > j1?i1:j1) << 16;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return this.field_149764_J == Material.field_151586_h?EnumWorldBlockLayer.TRANSLUCENT:EnumWorldBlockLayer.SOLID;
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      double d0 = (double)p_180655_2_.func_177958_n();
      double d1 = (double)p_180655_2_.func_177956_o();
      double d2 = (double)p_180655_2_.func_177952_p();
      if(this.field_149764_J == Material.field_151586_h) {
         int i = ((Integer)p_180655_3_.func_177229_b(field_176367_b)).intValue();
         if(i > 0 && i < 8) {
            if(p_180655_4_.nextInt(64) == 0) {
               p_180655_1_.func_72980_b(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "liquid.water", p_180655_4_.nextFloat() * 0.25F + 0.75F, p_180655_4_.nextFloat() * 1.0F + 0.5F, false);
            }
         } else if(p_180655_4_.nextInt(10) == 0) {
            p_180655_1_.func_175688_a(EnumParticleTypes.SUSPENDED, d0 + (double)p_180655_4_.nextFloat(), d1 + (double)p_180655_4_.nextFloat(), d2 + (double)p_180655_4_.nextFloat(), 0.0D, 0.0D, 0.0D, new int[0]);
         }
      }

      if(this.field_149764_J == Material.field_151587_i && p_180655_1_.func_180495_p(p_180655_2_.func_177984_a()).func_177230_c().func_149688_o() == Material.field_151579_a && !p_180655_1_.func_180495_p(p_180655_2_.func_177984_a()).func_177230_c().func_149662_c()) {
         if(p_180655_4_.nextInt(100) == 0) {
            double d8 = d0 + (double)p_180655_4_.nextFloat();
            double d4 = d1 + this.field_149756_F;
            double d6 = d2 + (double)p_180655_4_.nextFloat();
            p_180655_1_.func_175688_a(EnumParticleTypes.LAVA, d8, d4, d6, 0.0D, 0.0D, 0.0D, new int[0]);
            p_180655_1_.func_72980_b(d8, d4, d6, "liquid.lavapop", 0.2F + p_180655_4_.nextFloat() * 0.2F, 0.9F + p_180655_4_.nextFloat() * 0.15F, false);
         }

         if(p_180655_4_.nextInt(200) == 0) {
            p_180655_1_.func_72980_b(d0, d1, d2, "liquid.lava", 0.2F + p_180655_4_.nextFloat() * 0.2F, 0.9F + p_180655_4_.nextFloat() * 0.15F, false);
         }
      }

      if(p_180655_4_.nextInt(10) == 0 && World.func_175683_a(p_180655_1_, p_180655_2_.func_177977_b())) {
         Material material = p_180655_1_.func_180495_p(p_180655_2_.func_177979_c(2)).func_177230_c().func_149688_o();
         if(!material.func_76230_c() && !material.func_76224_d()) {
            double d3 = d0 + (double)p_180655_4_.nextFloat();
            double d5 = d1 - 1.05D;
            double d7 = d2 + (double)p_180655_4_.nextFloat();
            if(this.field_149764_J == Material.field_151586_h) {
               p_180655_1_.func_175688_a(EnumParticleTypes.DRIP_WATER, d3, d5, d7, 0.0D, 0.0D, 0.0D, new int[0]);
            } else {
               p_180655_1_.func_175688_a(EnumParticleTypes.DRIP_LAVA, d3, d5, d7, 0.0D, 0.0D, 0.0D, new int[0]);
            }
         }
      }

   }

   public static double func_180689_a(IBlockAccess p_180689_0_, BlockPos p_180689_1_, Material p_180689_2_) {
      Vec3 vec3 = func_176361_a(p_180689_2_).func_180687_h(p_180689_0_, p_180689_1_);
      return vec3.field_72450_a == 0.0D && vec3.field_72449_c == 0.0D?-1000.0D:MathHelper.func_181159_b(vec3.field_72449_c, vec3.field_72450_a) - 1.5707963267948966D;
   }

   public void func_176213_c(World p_176213_1_, BlockPos p_176213_2_, IBlockState p_176213_3_) {
      this.func_176365_e(p_176213_1_, p_176213_2_, p_176213_3_);
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      this.func_176365_e(p_176204_1_, p_176204_2_, p_176204_3_);
   }

   public boolean func_176365_e(World p_176365_1_, BlockPos p_176365_2_, IBlockState p_176365_3_) {
      if(this.field_149764_J == Material.field_151587_i) {
         boolean flag = false;

         for(EnumFacing enumfacing : EnumFacing.values()) {
            if(enumfacing != EnumFacing.DOWN && p_176365_1_.func_180495_p(p_176365_2_.func_177972_a(enumfacing)).func_177230_c().func_149688_o() == Material.field_151586_h) {
               flag = true;
               break;
            }
         }

         if(flag) {
            Integer integer = (Integer)p_176365_3_.func_177229_b(field_176367_b);
            if(integer.intValue() == 0) {
               p_176365_1_.func_175656_a(p_176365_2_, Blocks.field_150343_Z.func_176223_P());
               this.func_180688_d(p_176365_1_, p_176365_2_);
               return true;
            }

            if(integer.intValue() <= 4) {
               p_176365_1_.func_175656_a(p_176365_2_, Blocks.field_150347_e.func_176223_P());
               this.func_180688_d(p_176365_1_, p_176365_2_);
               return true;
            }
         }
      }

      return false;
   }

   protected void func_180688_d(World p_180688_1_, BlockPos p_180688_2_) {
      double d0 = (double)p_180688_2_.func_177958_n();
      double d1 = (double)p_180688_2_.func_177956_o();
      double d2 = (double)p_180688_2_.func_177952_p();
      p_180688_1_.func_72908_a(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (p_180688_1_.field_73012_v.nextFloat() - p_180688_1_.field_73012_v.nextFloat()) * 0.8F);

      for(int i = 0; i < 8; ++i) {
         p_180688_1_.func_175688_a(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
      }

   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176367_b, Integer.valueOf(p_176203_1_));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176367_b)).intValue();
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176367_b});
   }

   public static BlockDynamicLiquid func_176361_a(Material p_176361_0_) {
      if(p_176361_0_ == Material.field_151586_h) {
         return Blocks.field_150358_i;
      } else if(p_176361_0_ == Material.field_151587_i) {
         return Blocks.field_150356_k;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }

   public static BlockStaticLiquid func_176363_b(Material p_176363_0_) {
      if(p_176363_0_ == Material.field_151586_h) {
         return Blocks.field_150355_j;
      } else if(p_176363_0_ == Material.field_151587_i) {
         return Blocks.field_150353_l;
      } else {
         throw new IllegalArgumentException("Invalid material");
      }
   }
}
