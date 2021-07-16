package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class ChunkProviderEnd implements IChunkProvider {
   private Random field_73204_i;
   private NoiseGeneratorOctaves field_73201_j;
   private NoiseGeneratorOctaves field_73202_k;
   private NoiseGeneratorOctaves field_73199_l;
   public NoiseGeneratorOctaves field_73196_a;
   public NoiseGeneratorOctaves field_73194_b;
   private World field_73200_m;
   private double[] field_73197_n;
   private BiomeGenBase[] field_73198_o;
   double[] field_73195_c;
   double[] field_73192_d;
   double[] field_73193_e;
   double[] field_73190_f;
   double[] field_73191_g;

   public ChunkProviderEnd(World p_i2007_1_, long p_i2007_2_) {
      this.field_73200_m = p_i2007_1_;
      this.field_73204_i = new Random(p_i2007_2_);
      this.field_73201_j = new NoiseGeneratorOctaves(this.field_73204_i, 16);
      this.field_73202_k = new NoiseGeneratorOctaves(this.field_73204_i, 16);
      this.field_73199_l = new NoiseGeneratorOctaves(this.field_73204_i, 8);
      this.field_73196_a = new NoiseGeneratorOctaves(this.field_73204_i, 10);
      this.field_73194_b = new NoiseGeneratorOctaves(this.field_73204_i, 16);
   }

   public void func_180520_a(int p_180520_1_, int p_180520_2_, ChunkPrimer p_180520_3_) {
      int i = 2;
      int j = i + 1;
      int k = 33;
      int l = i + 1;
      this.field_73197_n = this.func_73187_a(this.field_73197_n, p_180520_1_ * i, 0, p_180520_2_ * i, j, k, l);

      for(int i1 = 0; i1 < i; ++i1) {
         for(int j1 = 0; j1 < i; ++j1) {
            for(int k1 = 0; k1 < 32; ++k1) {
               double d0 = 0.25D;
               double d1 = this.field_73197_n[((i1 + 0) * l + j1 + 0) * k + k1 + 0];
               double d2 = this.field_73197_n[((i1 + 0) * l + j1 + 1) * k + k1 + 0];
               double d3 = this.field_73197_n[((i1 + 1) * l + j1 + 0) * k + k1 + 0];
               double d4 = this.field_73197_n[((i1 + 1) * l + j1 + 1) * k + k1 + 0];
               double d5 = (this.field_73197_n[((i1 + 0) * l + j1 + 0) * k + k1 + 1] - d1) * d0;
               double d6 = (this.field_73197_n[((i1 + 0) * l + j1 + 1) * k + k1 + 1] - d2) * d0;
               double d7 = (this.field_73197_n[((i1 + 1) * l + j1 + 0) * k + k1 + 1] - d3) * d0;
               double d8 = (this.field_73197_n[((i1 + 1) * l + j1 + 1) * k + k1 + 1] - d4) * d0;

               for(int l1 = 0; l1 < 4; ++l1) {
                  double d9 = 0.125D;
                  double d10 = d1;
                  double d11 = d2;
                  double d12 = (d3 - d1) * d9;
                  double d13 = (d4 - d2) * d9;

                  for(int i2 = 0; i2 < 8; ++i2) {
                     double d14 = 0.125D;
                     double d15 = d10;
                     double d16 = (d11 - d10) * d14;

                     for(int j2 = 0; j2 < 8; ++j2) {
                        IBlockState iblockstate = null;
                        if(d15 > 0.0D) {
                           iblockstate = Blocks.field_150377_bs.func_176223_P();
                        }

                        int k2 = i2 + i1 * 8;
                        int l2 = l1 + k1 * 4;
                        int i3 = j2 + j1 * 8;
                        p_180520_3_.func_177855_a(k2, l2, i3, iblockstate);
                        d15 += d16;
                     }

                     d10 += d12;
                     d11 += d13;
                  }

                  d1 += d5;
                  d2 += d6;
                  d3 += d7;
                  d4 += d8;
               }
            }
         }
      }

   }

   public void func_180519_a(ChunkPrimer p_180519_1_) {
      for(int i = 0; i < 16; ++i) {
         for(int j = 0; j < 16; ++j) {
            int k = 1;
            int l = -1;
            IBlockState iblockstate = Blocks.field_150377_bs.func_176223_P();
            IBlockState iblockstate1 = Blocks.field_150377_bs.func_176223_P();

            for(int i1 = 127; i1 >= 0; --i1) {
               IBlockState iblockstate2 = p_180519_1_.func_177856_a(i, i1, j);
               if(iblockstate2.func_177230_c().func_149688_o() == Material.field_151579_a) {
                  l = -1;
               } else if(iblockstate2.func_177230_c() == Blocks.field_150348_b) {
                  if(l == -1) {
                     if(k <= 0) {
                        iblockstate = Blocks.field_150350_a.func_176223_P();
                        iblockstate1 = Blocks.field_150377_bs.func_176223_P();
                     }

                     l = k;
                     if(i1 >= 0) {
                        p_180519_1_.func_177855_a(i, i1, j, iblockstate);
                     } else {
                        p_180519_1_.func_177855_a(i, i1, j, iblockstate1);
                     }
                  } else if(l > 0) {
                     --l;
                     p_180519_1_.func_177855_a(i, i1, j, iblockstate1);
                  }
               }
            }
         }
      }

   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      this.field_73204_i.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
      ChunkPrimer chunkprimer = new ChunkPrimer();
      this.field_73198_o = this.field_73200_m.func_72959_q().func_76933_b(this.field_73198_o, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      this.func_180520_a(p_73154_1_, p_73154_2_, chunkprimer);
      this.func_180519_a(chunkprimer);
      Chunk chunk = new Chunk(this.field_73200_m, chunkprimer, p_73154_1_, p_73154_2_);
      byte[] abyte = chunk.func_76605_m();

      for(int i = 0; i < abyte.length; ++i) {
         abyte[i] = (byte)this.field_73198_o[i].field_76756_M;
      }

      chunk.func_76603_b();
      return chunk;
   }

   private double[] func_73187_a(double[] p_73187_1_, int p_73187_2_, int p_73187_3_, int p_73187_4_, int p_73187_5_, int p_73187_6_, int p_73187_7_) {
      if(p_73187_1_ == null) {
         p_73187_1_ = new double[p_73187_5_ * p_73187_6_ * p_73187_7_];
      }

      double d0 = 684.412D;
      double d1 = 684.412D;
      this.field_73190_f = this.field_73196_a.func_76305_a(this.field_73190_f, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 1.121D, 1.121D, 0.5D);
      this.field_73191_g = this.field_73194_b.func_76305_a(this.field_73191_g, p_73187_2_, p_73187_4_, p_73187_5_, p_73187_7_, 200.0D, 200.0D, 0.5D);
      d0 = d0 * 2.0D;
      this.field_73195_c = this.field_73199_l.func_76304_a(this.field_73195_c, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0 / 80.0D, d1 / 160.0D, d0 / 80.0D);
      this.field_73192_d = this.field_73201_j.func_76304_a(this.field_73192_d, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0, d1, d0);
      this.field_73193_e = this.field_73202_k.func_76304_a(this.field_73193_e, p_73187_2_, p_73187_3_, p_73187_4_, p_73187_5_, p_73187_6_, p_73187_7_, d0, d1, d0);
      int i = 0;

      for(int j = 0; j < p_73187_5_; ++j) {
         for(int k = 0; k < p_73187_7_; ++k) {
            float f = (float)(j + p_73187_2_) / 1.0F;
            float f1 = (float)(k + p_73187_4_) / 1.0F;
            float f2 = 100.0F - MathHelper.func_76129_c(f * f + f1 * f1) * 8.0F;
            if(f2 > 80.0F) {
               f2 = 80.0F;
            }

            if(f2 < -100.0F) {
               f2 = -100.0F;
            }

            for(int l = 0; l < p_73187_6_; ++l) {
               double d2 = 0.0D;
               double d3 = this.field_73192_d[i] / 512.0D;
               double d4 = this.field_73193_e[i] / 512.0D;
               double d5 = (this.field_73195_c[i] / 10.0D + 1.0D) / 2.0D;
               if(d5 < 0.0D) {
                  d2 = d3;
               } else if(d5 > 1.0D) {
                  d2 = d4;
               } else {
                  d2 = d3 + (d4 - d3) * d5;
               }

               d2 = d2 - 8.0D;
               d2 = d2 + (double)f2;
               int i1 = 2;
               if(l > p_73187_6_ / 2 - i1) {
                  double d6 = (double)((float)(l - (p_73187_6_ / 2 - i1)) / 64.0F);
                  d6 = MathHelper.func_151237_a(d6, 0.0D, 1.0D);
                  d2 = d2 * (1.0D - d6) + -3000.0D * d6;
               }

               i1 = 8;
               if(l < i1) {
                  double d7 = (double)((float)(i1 - l) / ((float)i1 - 1.0F));
                  d2 = d2 * (1.0D - d7) + -30.0D * d7;
               }

               p_73187_1_[i] = d2;
               ++i;
            }
         }
      }

      return p_73187_1_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      BlockFalling.field_149832_M = true;
      BlockPos blockpos = new BlockPos(p_73153_2_ * 16, 0, p_73153_3_ * 16);
      this.field_73200_m.func_180494_b(blockpos.func_177982_a(16, 0, 16)).func_180624_a(this.field_73200_m, this.field_73200_m.field_73012_v, blockpos);
      BlockFalling.field_149832_M = false;
   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      return false;
   }

   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
      return true;
   }

   public void func_104112_b() {
   }

   public boolean func_73156_b() {
      return false;
   }

   public boolean func_73157_c() {
      return true;
   }

   public String func_73148_d() {
      return "RandomLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      return this.field_73200_m.func_180494_b(p_177458_2_).func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
