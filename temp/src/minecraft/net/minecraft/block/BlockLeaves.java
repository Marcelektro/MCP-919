package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;

public abstract class BlockLeaves extends BlockLeavesBase {
   public static final PropertyBool field_176237_a = PropertyBool.func_177716_a("decayable");
   public static final PropertyBool field_176236_b = PropertyBool.func_177716_a("check_decay");
   int[] field_150128_a;
   protected int field_150127_b;
   protected boolean field_176238_O;

   public BlockLeaves() {
      super(Material.field_151584_j, false);
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
      this.func_149711_c(0.2F);
      this.func_149713_g(1);
      this.func_149672_a(field_149779_h);
   }

   public int func_149635_D() {
      return ColorizerFoliage.func_77470_a(0.5D, 1.0D);
   }

   public int func_180644_h(IBlockState p_180644_1_) {
      return ColorizerFoliage.func_77468_c();
   }

   public int func_180662_a(IBlockAccess p_180662_1_, BlockPos p_180662_2_, int p_180662_3_) {
      return BiomeColorHelper.func_180287_b(p_180662_1_, p_180662_2_);
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      int i = 1;
      int j = i + 1;
      int k = p_180663_2_.func_177958_n();
      int l = p_180663_2_.func_177956_o();
      int i1 = p_180663_2_.func_177952_p();
      if(p_180663_1_.func_175707_a(new BlockPos(k - j, l - j, i1 - j), new BlockPos(k + j, l + j, i1 + j))) {
         for(int j1 = -i; j1 <= i; ++j1) {
            for(int k1 = -i; k1 <= i; ++k1) {
               for(int l1 = -i; l1 <= i; ++l1) {
                  BlockPos blockpos = p_180663_2_.func_177982_a(j1, k1, l1);
                  IBlockState iblockstate = p_180663_1_.func_180495_p(blockpos);
                  if(iblockstate.func_177230_c().func_149688_o() == Material.field_151584_j && !((Boolean)iblockstate.func_177229_b(field_176236_b)).booleanValue()) {
                     p_180663_1_.func_180501_a(blockpos, iblockstate.func_177226_a(field_176236_b, Boolean.valueOf(true)), 4);
                  }
               }
            }
         }
      }

   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(!p_180650_1_.field_72995_K) {
         if(((Boolean)p_180650_3_.func_177229_b(field_176236_b)).booleanValue() && ((Boolean)p_180650_3_.func_177229_b(field_176237_a)).booleanValue()) {
            int i = 4;
            int j = i + 1;
            int k = p_180650_2_.func_177958_n();
            int l = p_180650_2_.func_177956_o();
            int i1 = p_180650_2_.func_177952_p();
            int j1 = 32;
            int k1 = j1 * j1;
            int l1 = j1 / 2;
            if(this.field_150128_a == null) {
               this.field_150128_a = new int[j1 * j1 * j1];
            }

            if(p_180650_1_.func_175707_a(new BlockPos(k - j, l - j, i1 - j), new BlockPos(k + j, l + j, i1 + j))) {
               BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

               for(int i2 = -i; i2 <= i; ++i2) {
                  for(int j2 = -i; j2 <= i; ++j2) {
                     for(int k2 = -i; k2 <= i; ++k2) {
                        Block block = p_180650_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(k + i2, l + j2, i1 + k2)).func_177230_c();
                        if(block != Blocks.field_150364_r && block != Blocks.field_150363_s) {
                           if(block.func_149688_o() == Material.field_151584_j) {
                              this.field_150128_a[(i2 + l1) * k1 + (j2 + l1) * j1 + k2 + l1] = -2;
                           } else {
                              this.field_150128_a[(i2 + l1) * k1 + (j2 + l1) * j1 + k2 + l1] = -1;
                           }
                        } else {
                           this.field_150128_a[(i2 + l1) * k1 + (j2 + l1) * j1 + k2 + l1] = 0;
                        }
                     }
                  }
               }

               for(int i3 = 1; i3 <= 4; ++i3) {
                  for(int j3 = -i; j3 <= i; ++j3) {
                     for(int k3 = -i; k3 <= i; ++k3) {
                        for(int l3 = -i; l3 <= i; ++l3) {
                           if(this.field_150128_a[(j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1] == i3 - 1) {
                              if(this.field_150128_a[(j3 + l1 - 1) * k1 + (k3 + l1) * j1 + l3 + l1] == -2) {
                                 this.field_150128_a[(j3 + l1 - 1) * k1 + (k3 + l1) * j1 + l3 + l1] = i3;
                              }

                              if(this.field_150128_a[(j3 + l1 + 1) * k1 + (k3 + l1) * j1 + l3 + l1] == -2) {
                                 this.field_150128_a[(j3 + l1 + 1) * k1 + (k3 + l1) * j1 + l3 + l1] = i3;
                              }

                              if(this.field_150128_a[(j3 + l1) * k1 + (k3 + l1 - 1) * j1 + l3 + l1] == -2) {
                                 this.field_150128_a[(j3 + l1) * k1 + (k3 + l1 - 1) * j1 + l3 + l1] = i3;
                              }

                              if(this.field_150128_a[(j3 + l1) * k1 + (k3 + l1 + 1) * j1 + l3 + l1] == -2) {
                                 this.field_150128_a[(j3 + l1) * k1 + (k3 + l1 + 1) * j1 + l3 + l1] = i3;
                              }

                              if(this.field_150128_a[(j3 + l1) * k1 + (k3 + l1) * j1 + (l3 + l1 - 1)] == -2) {
                                 this.field_150128_a[(j3 + l1) * k1 + (k3 + l1) * j1 + (l3 + l1 - 1)] = i3;
                              }

                              if(this.field_150128_a[(j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1 + 1] == -2) {
                                 this.field_150128_a[(j3 + l1) * k1 + (k3 + l1) * j1 + l3 + l1 + 1] = i3;
                              }
                           }
                        }
                     }
                  }
               }
            }

            int l2 = this.field_150128_a[l1 * k1 + l1 * j1 + l1];
            if(l2 >= 0) {
               p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(field_176236_b, Boolean.valueOf(false)), 4);
            } else {
               this.func_176235_d(p_180650_1_, p_180650_2_);
            }
         }

      }
   }

   public void func_180655_c(World p_180655_1_, BlockPos p_180655_2_, IBlockState p_180655_3_, Random p_180655_4_) {
      if(p_180655_1_.func_175727_C(p_180655_2_.func_177984_a()) && !World.func_175683_a(p_180655_1_, p_180655_2_.func_177977_b()) && p_180655_4_.nextInt(15) == 1) {
         double d0 = (double)((float)p_180655_2_.func_177958_n() + p_180655_4_.nextFloat());
         double d1 = (double)p_180655_2_.func_177956_o() - 0.05D;
         double d2 = (double)((float)p_180655_2_.func_177952_p() + p_180655_4_.nextFloat());
         p_180655_1_.func_175688_a(EnumParticleTypes.DRIP_WATER, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
      }

   }

   private void func_176235_d(World p_176235_1_, BlockPos p_176235_2_) {
      this.func_176226_b(p_176235_1_, p_176235_2_, p_176235_1_.func_180495_p(p_176235_2_), 0);
      p_176235_1_.func_175698_g(p_176235_2_);
   }

   public int func_149745_a(Random p_149745_1_) {
      return p_149745_1_.nextInt(20) == 0?1:0;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Item.func_150898_a(Blocks.field_150345_g);
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      if(!p_180653_1_.field_72995_K) {
         int i = this.func_176232_d(p_180653_3_);
         if(p_180653_5_ > 0) {
            i -= 2 << p_180653_5_;
            if(i < 10) {
               i = 10;
            }
         }

         if(p_180653_1_.field_73012_v.nextInt(i) == 0) {
            Item item = this.func_180660_a(p_180653_3_, p_180653_1_.field_73012_v, p_180653_5_);
            func_180635_a(p_180653_1_, p_180653_2_, new ItemStack(item, 1, this.func_180651_a(p_180653_3_)));
         }

         i = 200;
         if(p_180653_5_ > 0) {
            i -= 10 << p_180653_5_;
            if(i < 40) {
               i = 40;
            }
         }

         this.func_176234_a(p_180653_1_, p_180653_2_, p_180653_3_, i);
      }

   }

   protected void func_176234_a(World p_176234_1_, BlockPos p_176234_2_, IBlockState p_176234_3_, int p_176234_4_) {
   }

   protected int func_176232_d(IBlockState p_176232_1_) {
      return 20;
   }

   public boolean func_149662_c() {
      return !this.field_150121_P;
   }

   public void func_150122_b(boolean p_150122_1_) {
      this.field_176238_O = p_150122_1_;
      this.field_150121_P = p_150122_1_;
      this.field_150127_b = p_150122_1_?0:1;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return this.field_176238_O?EnumWorldBlockLayer.CUTOUT_MIPPED:EnumWorldBlockLayer.SOLID;
   }

   public boolean func_176214_u() {
      return false;
   }

   public abstract BlockPlanks.EnumType func_176233_b(int var1);
}
