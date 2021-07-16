package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.GeneratorBushFeature;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;

public class ChunkProviderHell implements IChunkProvider {
   private final World field_73175_o;
   private final boolean field_177466_i;
   private final Random field_73181_i;
   private double[] field_73185_q = new double[256];
   private double[] field_73184_r = new double[256];
   private double[] field_73183_s = new double[256];
   private double[] field_73186_p;
   private final NoiseGeneratorOctaves field_73178_j;
   private final NoiseGeneratorOctaves field_73179_k;
   private final NoiseGeneratorOctaves field_73176_l;
   private final NoiseGeneratorOctaves field_73177_m;
   private final NoiseGeneratorOctaves field_73174_n;
   public final NoiseGeneratorOctaves field_73173_a;
   public final NoiseGeneratorOctaves field_73171_b;
   private final WorldGenFire field_177470_t = new WorldGenFire();
   private final WorldGenGlowStone1 field_177469_u = new WorldGenGlowStone1();
   private final WorldGenGlowStone2 field_177468_v = new WorldGenGlowStone2();
   private final WorldGenerator field_177467_w = new WorldGenMinable(Blocks.field_150449_bY.func_176223_P(), 14, BlockHelper.func_177642_a(Blocks.field_150424_aL));
   private final WorldGenHellLava field_177473_x = new WorldGenHellLava(Blocks.field_150356_k, true);
   private final WorldGenHellLava field_177472_y = new WorldGenHellLava(Blocks.field_150356_k, false);
   private final GeneratorBushFeature field_177471_z = new GeneratorBushFeature(Blocks.field_150338_P);
   private final GeneratorBushFeature field_177465_A = new GeneratorBushFeature(Blocks.field_150337_Q);
   private final MapGenNetherBridge field_73172_c = new MapGenNetherBridge();
   private final MapGenBase field_73182_t = new MapGenCavesHell();
   double[] field_73169_d;
   double[] field_73170_e;
   double[] field_73167_f;
   double[] field_73168_g;
   double[] field_73180_h;

   public ChunkProviderHell(World p_i45637_1_, boolean p_i45637_2_, long p_i45637_3_) {
      this.field_73175_o = p_i45637_1_;
      this.field_177466_i = p_i45637_2_;
      this.field_73181_i = new Random(p_i45637_3_);
      this.field_73178_j = new NoiseGeneratorOctaves(this.field_73181_i, 16);
      this.field_73179_k = new NoiseGeneratorOctaves(this.field_73181_i, 16);
      this.field_73176_l = new NoiseGeneratorOctaves(this.field_73181_i, 8);
      this.field_73177_m = new NoiseGeneratorOctaves(this.field_73181_i, 4);
      this.field_73174_n = new NoiseGeneratorOctaves(this.field_73181_i, 4);
      this.field_73173_a = new NoiseGeneratorOctaves(this.field_73181_i, 10);
      this.field_73171_b = new NoiseGeneratorOctaves(this.field_73181_i, 16);
      p_i45637_1_.func_181544_b(63);
   }

   public void func_180515_a(int p_180515_1_, int p_180515_2_, ChunkPrimer p_180515_3_) {
      int i = 4;
      int j = this.field_73175_o.func_181545_F() / 2 + 1;
      int k = i + 1;
      int l = 17;
      int i1 = i + 1;
      this.field_73186_p = this.func_73164_a(this.field_73186_p, p_180515_1_ * i, 0, p_180515_2_ * i, k, l, i1);

      for(int j1 = 0; j1 < i; ++j1) {
         for(int k1 = 0; k1 < i; ++k1) {
            for(int l1 = 0; l1 < 16; ++l1) {
               double d0 = 0.125D;
               double d1 = this.field_73186_p[((j1 + 0) * i1 + k1 + 0) * l + l1 + 0];
               double d2 = this.field_73186_p[((j1 + 0) * i1 + k1 + 1) * l + l1 + 0];
               double d3 = this.field_73186_p[((j1 + 1) * i1 + k1 + 0) * l + l1 + 0];
               double d4 = this.field_73186_p[((j1 + 1) * i1 + k1 + 1) * l + l1 + 0];
               double d5 = (this.field_73186_p[((j1 + 0) * i1 + k1 + 0) * l + l1 + 1] - d1) * d0;
               double d6 = (this.field_73186_p[((j1 + 0) * i1 + k1 + 1) * l + l1 + 1] - d2) * d0;
               double d7 = (this.field_73186_p[((j1 + 1) * i1 + k1 + 0) * l + l1 + 1] - d3) * d0;
               double d8 = (this.field_73186_p[((j1 + 1) * i1 + k1 + 1) * l + l1 + 1] - d4) * d0;

               for(int i2 = 0; i2 < 8; ++i2) {
                  double d9 = 0.25D;
                  double d10 = d1;
                  double d11 = d2;
                  double d12 = (d3 - d1) * d9;
                  double d13 = (d4 - d2) * d9;

                  for(int j2 = 0; j2 < 4; ++j2) {
                     double d14 = 0.25D;
                     double d15 = d10;
                     double d16 = (d11 - d10) * d14;

                     for(int k2 = 0; k2 < 4; ++k2) {
                        IBlockState iblockstate = null;
                        if(l1 * 8 + i2 < j) {
                           iblockstate = Blocks.field_150353_l.func_176223_P();
                        }

                        if(d15 > 0.0D) {
                           iblockstate = Blocks.field_150424_aL.func_176223_P();
                        }

                        int l2 = j2 + j1 * 4;
                        int i3 = i2 + l1 * 8;
                        int j3 = k2 + k1 * 4;
                        p_180515_3_.func_177855_a(l2, i3, j3, iblockstate);
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

   public void func_180516_b(int p_180516_1_, int p_180516_2_, ChunkPrimer p_180516_3_) {
      int i = this.field_73175_o.func_181545_F() + 1;
      double d0 = 0.03125D;
      this.field_73185_q = this.field_73177_m.func_76304_a(this.field_73185_q, p_180516_1_ * 16, p_180516_2_ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
      this.field_73184_r = this.field_73177_m.func_76304_a(this.field_73184_r, p_180516_1_ * 16, 109, p_180516_2_ * 16, 16, 1, 16, d0, 1.0D, d0);
      this.field_73183_s = this.field_73174_n.func_76304_a(this.field_73183_s, p_180516_1_ * 16, p_180516_2_ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

      for(int j = 0; j < 16; ++j) {
         for(int k = 0; k < 16; ++k) {
            boolean flag = this.field_73185_q[j + k * 16] + this.field_73181_i.nextDouble() * 0.2D > 0.0D;
            boolean flag1 = this.field_73184_r[j + k * 16] + this.field_73181_i.nextDouble() * 0.2D > 0.0D;
            int l = (int)(this.field_73183_s[j + k * 16] / 3.0D + 3.0D + this.field_73181_i.nextDouble() * 0.25D);
            int i1 = -1;
            IBlockState iblockstate = Blocks.field_150424_aL.func_176223_P();
            IBlockState iblockstate1 = Blocks.field_150424_aL.func_176223_P();

            for(int j1 = 127; j1 >= 0; --j1) {
               if(j1 < 127 - this.field_73181_i.nextInt(5) && j1 > this.field_73181_i.nextInt(5)) {
                  IBlockState iblockstate2 = p_180516_3_.func_177856_a(k, j1, j);
                  if(iblockstate2.func_177230_c() != null && iblockstate2.func_177230_c().func_149688_o() != Material.field_151579_a) {
                     if(iblockstate2.func_177230_c() == Blocks.field_150424_aL) {
                        if(i1 == -1) {
                           if(l <= 0) {
                              iblockstate = null;
                              iblockstate1 = Blocks.field_150424_aL.func_176223_P();
                           } else if(j1 >= i - 4 && j1 <= i + 1) {
                              iblockstate = Blocks.field_150424_aL.func_176223_P();
                              iblockstate1 = Blocks.field_150424_aL.func_176223_P();
                              if(flag1) {
                                 iblockstate = Blocks.field_150351_n.func_176223_P();
                                 iblockstate1 = Blocks.field_150424_aL.func_176223_P();
                              }

                              if(flag) {
                                 iblockstate = Blocks.field_150425_aM.func_176223_P();
                                 iblockstate1 = Blocks.field_150425_aM.func_176223_P();
                              }
                           }

                           if(j1 < i && (iblockstate == null || iblockstate.func_177230_c().func_149688_o() == Material.field_151579_a)) {
                              iblockstate = Blocks.field_150353_l.func_176223_P();
                           }

                           i1 = l;
                           if(j1 >= i - 1) {
                              p_180516_3_.func_177855_a(k, j1, j, iblockstate);
                           } else {
                              p_180516_3_.func_177855_a(k, j1, j, iblockstate1);
                           }
                        } else if(i1 > 0) {
                           --i1;
                           p_180516_3_.func_177855_a(k, j1, j, iblockstate1);
                        }
                     }
                  } else {
                     i1 = -1;
                  }
               } else {
                  p_180516_3_.func_177855_a(k, j1, j, Blocks.field_150357_h.func_176223_P());
               }
            }
         }
      }

   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      this.field_73181_i.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
      ChunkPrimer chunkprimer = new ChunkPrimer();
      this.func_180515_a(p_73154_1_, p_73154_2_, chunkprimer);
      this.func_180516_b(p_73154_1_, p_73154_2_, chunkprimer);
      this.field_73182_t.func_175792_a(this, this.field_73175_o, p_73154_1_, p_73154_2_, chunkprimer);
      if(this.field_177466_i) {
         this.field_73172_c.func_175792_a(this, this.field_73175_o, p_73154_1_, p_73154_2_, chunkprimer);
      }

      Chunk chunk = new Chunk(this.field_73175_o, chunkprimer, p_73154_1_, p_73154_2_);
      BiomeGenBase[] abiomegenbase = this.field_73175_o.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      byte[] abyte = chunk.func_76605_m();

      for(int i = 0; i < abyte.length; ++i) {
         abyte[i] = (byte)abiomegenbase[i].field_76756_M;
      }

      chunk.func_76613_n();
      return chunk;
   }

   private double[] func_73164_a(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_) {
      if(p_73164_1_ == null) {
         p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
      }

      double d0 = 684.412D;
      double d1 = 2053.236D;
      this.field_73168_g = this.field_73173_a.func_76304_a(this.field_73168_g, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
      this.field_73180_h = this.field_73171_b.func_76304_a(this.field_73180_h, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0D, 0.0D, 100.0D);
      this.field_73169_d = this.field_73176_l.func_76304_a(this.field_73169_d, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D);
      this.field_73170_e = this.field_73178_j.func_76304_a(this.field_73170_e, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
      this.field_73167_f = this.field_73179_k.func_76304_a(this.field_73167_f, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
      int i = 0;
      double[] adouble = new double[p_73164_6_];

      for(int j = 0; j < p_73164_6_; ++j) {
         adouble[j] = Math.cos((double)j * 3.141592653589793D * 6.0D / (double)p_73164_6_) * 2.0D;
         double d2 = (double)j;
         if(j > p_73164_6_ / 2) {
            d2 = (double)(p_73164_6_ - 1 - j);
         }

         if(d2 < 4.0D) {
            d2 = 4.0D - d2;
            adouble[j] -= d2 * d2 * d2 * 10.0D;
         }
      }

      for(int l = 0; l < p_73164_5_; ++l) {
         for(int i1 = 0; i1 < p_73164_7_; ++i1) {
            double d3 = 0.0D;

            for(int k = 0; k < p_73164_6_; ++k) {
               double d4 = 0.0D;
               double d5 = adouble[k];
               double d6 = this.field_73170_e[i] / 512.0D;
               double d7 = this.field_73167_f[i] / 512.0D;
               double d8 = (this.field_73169_d[i] / 10.0D + 1.0D) / 2.0D;
               if(d8 < 0.0D) {
                  d4 = d6;
               } else if(d8 > 1.0D) {
                  d4 = d7;
               } else {
                  d4 = d6 + (d7 - d6) * d8;
               }

               d4 = d4 - d5;
               if(k > p_73164_6_ - 4) {
                  double d9 = (double)((float)(k - (p_73164_6_ - 4)) / 3.0F);
                  d4 = d4 * (1.0D - d9) + -10.0D * d9;
               }

               if((double)k < d3) {
                  double d10 = (d3 - (double)k) / 4.0D;
                  d10 = MathHelper.func_151237_a(d10, 0.0D, 1.0D);
                  d4 = d4 * (1.0D - d10) + -10.0D * d10;
               }

               p_73164_1_[i] = d4;
               ++i;
            }
         }
      }

      return p_73164_1_;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      BlockFalling.field_149832_M = true;
      BlockPos blockpos = new BlockPos(p_73153_2_ * 16, 0, p_73153_3_ * 16);
      ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
      this.field_73172_c.func_175794_a(this.field_73175_o, this.field_73181_i, chunkcoordintpair);

      for(int i = 0; i < 8; ++i) {
         this.field_177472_y.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(120) + 4, this.field_73181_i.nextInt(16) + 8));
      }

      for(int j = 0; j < this.field_73181_i.nextInt(this.field_73181_i.nextInt(10) + 1) + 1; ++j) {
         this.field_177470_t.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(120) + 4, this.field_73181_i.nextInt(16) + 8));
      }

      for(int k = 0; k < this.field_73181_i.nextInt(this.field_73181_i.nextInt(10) + 1); ++k) {
         this.field_177469_u.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(120) + 4, this.field_73181_i.nextInt(16) + 8));
      }

      for(int l = 0; l < 10; ++l) {
         this.field_177468_v.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(128), this.field_73181_i.nextInt(16) + 8));
      }

      if(this.field_73181_i.nextBoolean()) {
         this.field_177471_z.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(128), this.field_73181_i.nextInt(16) + 8));
      }

      if(this.field_73181_i.nextBoolean()) {
         this.field_177465_A.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16) + 8, this.field_73181_i.nextInt(128), this.field_73181_i.nextInt(16) + 8));
      }

      for(int i1 = 0; i1 < 16; ++i1) {
         this.field_177467_w.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16), this.field_73181_i.nextInt(108) + 10, this.field_73181_i.nextInt(16)));
      }

      for(int j1 = 0; j1 < 16; ++j1) {
         this.field_177473_x.func_180709_b(this.field_73175_o, this.field_73181_i, blockpos.func_177982_a(this.field_73181_i.nextInt(16), this.field_73181_i.nextInt(108) + 10, this.field_73181_i.nextInt(16)));
      }

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
      return "HellRandomLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      if(p_177458_1_ == EnumCreatureType.MONSTER) {
         if(this.field_73172_c.func_175795_b(p_177458_2_)) {
            return this.field_73172_c.func_75059_a();
         }

         if(this.field_73172_c.func_175796_a(this.field_73175_o, p_177458_2_) && this.field_73175_o.func_180495_p(p_177458_2_.func_177977_b()).func_177230_c() == Blocks.field_150385_bj) {
            return this.field_73172_c.func_75059_a();
         }
      }

      BiomeGenBase biomegenbase = this.field_73175_o.func_180494_b(p_177458_2_);
      return biomegenbase.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
      this.field_73172_c.func_175792_a(this, this.field_73175_o, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
