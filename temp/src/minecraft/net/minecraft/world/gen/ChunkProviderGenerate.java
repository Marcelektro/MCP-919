package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class ChunkProviderGenerate implements IChunkProvider {
   private Random field_73220_k;
   private NoiseGeneratorOctaves field_147431_j;
   private NoiseGeneratorOctaves field_147432_k;
   private NoiseGeneratorOctaves field_147429_l;
   private NoiseGeneratorPerlin field_147430_m;
   public NoiseGeneratorOctaves field_73214_a;
   public NoiseGeneratorOctaves field_73212_b;
   public NoiseGeneratorOctaves field_73213_c;
   private World field_73230_p;
   private final boolean field_73229_q;
   private WorldType field_177475_o;
   private final double[] field_147434_q;
   private final float[] field_147433_r;
   private ChunkProviderSettings field_177477_r;
   private Block field_177476_s = Blocks.field_150355_j;
   private double[] field_73227_s = new double[256];
   private MapGenBase field_73226_t = new MapGenCaves();
   private MapGenStronghold field_73225_u = new MapGenStronghold();
   private MapGenVillage field_73224_v = new MapGenVillage();
   private MapGenMineshaft field_73223_w = new MapGenMineshaft();
   private MapGenScatteredFeature field_73233_x = new MapGenScatteredFeature();
   private MapGenBase field_73232_y = new MapGenRavine();
   private StructureOceanMonument field_177474_A = new StructureOceanMonument();
   private BiomeGenBase[] field_73231_z;
   double[] field_147427_d;
   double[] field_147428_e;
   double[] field_147425_f;
   double[] field_147426_g;

   public ChunkProviderGenerate(World p_i45636_1_, long p_i45636_2_, boolean p_i45636_4_, String p_i45636_5_) {
      this.field_73230_p = p_i45636_1_;
      this.field_73229_q = p_i45636_4_;
      this.field_177475_o = p_i45636_1_.func_72912_H().func_76067_t();
      this.field_73220_k = new Random(p_i45636_2_);
      this.field_147431_j = new NoiseGeneratorOctaves(this.field_73220_k, 16);
      this.field_147432_k = new NoiseGeneratorOctaves(this.field_73220_k, 16);
      this.field_147429_l = new NoiseGeneratorOctaves(this.field_73220_k, 8);
      this.field_147430_m = new NoiseGeneratorPerlin(this.field_73220_k, 4);
      this.field_73214_a = new NoiseGeneratorOctaves(this.field_73220_k, 10);
      this.field_73212_b = new NoiseGeneratorOctaves(this.field_73220_k, 16);
      this.field_73213_c = new NoiseGeneratorOctaves(this.field_73220_k, 8);
      this.field_147434_q = new double[825];
      this.field_147433_r = new float[25];

      for(int i = -2; i <= 2; ++i) {
         for(int j = -2; j <= 2; ++j) {
            float f = 10.0F / MathHelper.func_76129_c((float)(i * i + j * j) + 0.2F);
            this.field_147433_r[i + 2 + (j + 2) * 5] = f;
         }
      }

      if(p_i45636_5_ != null) {
         this.field_177477_r = ChunkProviderSettings.Factory.func_177865_a(p_i45636_5_).func_177864_b();
         this.field_177476_s = this.field_177477_r.field_177778_E?Blocks.field_150353_l:Blocks.field_150355_j;
         p_i45636_1_.func_181544_b(this.field_177477_r.field_177841_q);
      }

   }

   public void func_180518_a(int p_180518_1_, int p_180518_2_, ChunkPrimer p_180518_3_) {
      this.field_73231_z = this.field_73230_p.func_72959_q().func_76937_a(this.field_73231_z, p_180518_1_ * 4 - 2, p_180518_2_ * 4 - 2, 10, 10);
      this.func_147423_a(p_180518_1_ * 4, 0, p_180518_2_ * 4);

      for(int i = 0; i < 4; ++i) {
         int j = i * 5;
         int k = (i + 1) * 5;

         for(int l = 0; l < 4; ++l) {
            int i1 = (j + l) * 33;
            int j1 = (j + l + 1) * 33;
            int k1 = (k + l) * 33;
            int l1 = (k + l + 1) * 33;

            for(int i2 = 0; i2 < 32; ++i2) {
               double d0 = 0.125D;
               double d1 = this.field_147434_q[i1 + i2];
               double d2 = this.field_147434_q[j1 + i2];
               double d3 = this.field_147434_q[k1 + i2];
               double d4 = this.field_147434_q[l1 + i2];
               double d5 = (this.field_147434_q[i1 + i2 + 1] - d1) * d0;
               double d6 = (this.field_147434_q[j1 + i2 + 1] - d2) * d0;
               double d7 = (this.field_147434_q[k1 + i2 + 1] - d3) * d0;
               double d8 = (this.field_147434_q[l1 + i2 + 1] - d4) * d0;

               for(int j2 = 0; j2 < 8; ++j2) {
                  double d9 = 0.25D;
                  double d10 = d1;
                  double d11 = d2;
                  double d12 = (d3 - d1) * d9;
                  double d13 = (d4 - d2) * d9;

                  for(int k2 = 0; k2 < 4; ++k2) {
                     double d14 = 0.25D;
                     double d16 = (d11 - d10) * d14;
                     double lvt_45_1_ = d10 - d16;

                     for(int l2 = 0; l2 < 4; ++l2) {
                        if((lvt_45_1_ += d16) > 0.0D) {
                           p_180518_3_.func_177855_a(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.field_150348_b.func_176223_P());
                        } else if(i2 * 8 + j2 < this.field_177477_r.field_177841_q) {
                           p_180518_3_.func_177855_a(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.field_177476_s.func_176223_P());
                        }
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

   public void func_180517_a(int p_180517_1_, int p_180517_2_, ChunkPrimer p_180517_3_, BiomeGenBase[] p_180517_4_) {
      double d0 = 0.03125D;
      this.field_73227_s = this.field_147430_m.func_151599_a(this.field_73227_s, (double)(p_180517_1_ * 16), (double)(p_180517_2_ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

      for(int i = 0; i < 16; ++i) {
         for(int j = 0; j < 16; ++j) {
            BiomeGenBase biomegenbase = p_180517_4_[j + i * 16];
            biomegenbase.func_180622_a(this.field_73230_p, this.field_73220_k, p_180517_3_, p_180517_1_ * 16 + i, p_180517_2_ * 16 + j, this.field_73227_s[j + i * 16]);
         }
      }

   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      this.field_73220_k.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
      ChunkPrimer chunkprimer = new ChunkPrimer();
      this.func_180518_a(p_73154_1_, p_73154_2_, chunkprimer);
      this.field_73231_z = this.field_73230_p.func_72959_q().func_76933_b(this.field_73231_z, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      this.func_180517_a(p_73154_1_, p_73154_2_, chunkprimer, this.field_73231_z);
      if(this.field_177477_r.field_177839_r) {
         this.field_73226_t.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, chunkprimer);
      }

      if(this.field_177477_r.field_177850_z) {
         this.field_73232_y.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, chunkprimer);
      }

      if(this.field_177477_r.field_177829_w && this.field_73229_q) {
         this.field_73223_w.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, chunkprimer);
      }

      if(this.field_177477_r.field_177831_v && this.field_73229_q) {
         this.field_73224_v.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, chunkprimer);
      }

      if(this.field_177477_r.field_177833_u && this.field_73229_q) {
         this.field_73225_u.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, chunkprimer);
      }

      if(this.field_177477_r.field_177854_x && this.field_73229_q) {
         this.field_73233_x.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, chunkprimer);
      }

      if(this.field_177477_r.field_177852_y && this.field_73229_q) {
         this.field_177474_A.func_175792_a(this, this.field_73230_p, p_73154_1_, p_73154_2_, chunkprimer);
      }

      Chunk chunk = new Chunk(this.field_73230_p, chunkprimer, p_73154_1_, p_73154_2_);
      byte[] abyte = chunk.func_76605_m();

      for(int i = 0; i < abyte.length; ++i) {
         abyte[i] = (byte)this.field_73231_z[i].field_76756_M;
      }

      chunk.func_76603_b();
      return chunk;
   }

   private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_) {
      this.field_147426_g = this.field_73212_b.func_76305_a(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, (double)this.field_177477_r.field_177808_e, (double)this.field_177477_r.field_177803_f, (double)this.field_177477_r.field_177804_g);
      float f = this.field_177477_r.field_177811_a;
      float f1 = this.field_177477_r.field_177809_b;
      this.field_147427_d = this.field_147429_l.func_76304_a(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)(f / this.field_177477_r.field_177825_h), (double)(f1 / this.field_177477_r.field_177827_i), (double)(f / this.field_177477_r.field_177821_j));
      this.field_147428_e = this.field_147431_j.func_76304_a(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
      this.field_147425_f = this.field_147432_k.func_76304_a(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double)f, (double)f1, (double)f);
      p_147423_3_ = 0;
      p_147423_1_ = 0;
      int i = 0;
      int j = 0;

      for(int k = 0; k < 5; ++k) {
         for(int l = 0; l < 5; ++l) {
            float f2 = 0.0F;
            float f3 = 0.0F;
            float f4 = 0.0F;
            int i1 = 2;
            BiomeGenBase biomegenbase = this.field_73231_z[k + 2 + (l + 2) * 10];

            for(int j1 = -i1; j1 <= i1; ++j1) {
               for(int k1 = -i1; k1 <= i1; ++k1) {
                  BiomeGenBase biomegenbase1 = this.field_73231_z[k + j1 + 2 + (l + k1 + 2) * 10];
                  float f5 = this.field_177477_r.field_177813_n + biomegenbase1.field_76748_D * this.field_177477_r.field_177819_m;
                  float f6 = this.field_177477_r.field_177843_p + biomegenbase1.field_76749_E * this.field_177477_r.field_177815_o;
                  if(this.field_177475_o == WorldType.field_151360_e && f5 > 0.0F) {
                     f5 = 1.0F + f5 * 2.0F;
                     f6 = 1.0F + f6 * 4.0F;
                  }

                  float f7 = this.field_147433_r[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);
                  if(biomegenbase1.field_76748_D > biomegenbase.field_76748_D) {
                     f7 /= 2.0F;
                  }

                  f2 += f6 * f7;
                  f3 += f5 * f7;
                  f4 += f7;
               }
            }

            f2 = f2 / f4;
            f3 = f3 / f4;
            f2 = f2 * 0.9F + 0.1F;
            f3 = (f3 * 4.0F - 1.0F) / 8.0F;
            double d7 = this.field_147426_g[j] / 8000.0D;
            if(d7 < 0.0D) {
               d7 = -d7 * 0.3D;
            }

            d7 = d7 * 3.0D - 2.0D;
            if(d7 < 0.0D) {
               d7 = d7 / 2.0D;
               if(d7 < -1.0D) {
                  d7 = -1.0D;
               }

               d7 = d7 / 1.4D;
               d7 = d7 / 2.0D;
            } else {
               if(d7 > 1.0D) {
                  d7 = 1.0D;
               }

               d7 = d7 / 8.0D;
            }

            ++j;
            double d8 = (double)f3;
            double d9 = (double)f2;
            d8 = d8 + d7 * 0.2D;
            d8 = d8 * (double)this.field_177477_r.field_177823_k / 8.0D;
            double d0 = (double)this.field_177477_r.field_177823_k + d8 * 4.0D;

            for(int l1 = 0; l1 < 33; ++l1) {
               double d1 = ((double)l1 - d0) * (double)this.field_177477_r.field_177817_l * 128.0D / 256.0D / d9;
               if(d1 < 0.0D) {
                  d1 *= 4.0D;
               }

               double d2 = this.field_147428_e[i] / (double)this.field_177477_r.field_177806_d;
               double d3 = this.field_147425_f[i] / (double)this.field_177477_r.field_177810_c;
               double d4 = (this.field_147427_d[i] / 10.0D + 1.0D) / 2.0D;
               double d5 = MathHelper.func_151238_b(d2, d3, d4) - d1;
               if(l1 > 29) {
                  double d6 = (double)((float)(l1 - 29) / 3.0F);
                  d5 = d5 * (1.0D - d6) + -10.0D * d6;
               }

               this.field_147434_q[i] = d5;
               ++i;
            }
         }
      }

   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      BlockFalling.field_149832_M = true;
      int i = p_73153_2_ * 16;
      int j = p_73153_3_ * 16;
      BlockPos blockpos = new BlockPos(i, 0, j);
      BiomeGenBase biomegenbase = this.field_73230_p.func_180494_b(blockpos.func_177982_a(16, 0, 16));
      this.field_73220_k.setSeed(this.field_73230_p.func_72905_C());
      long k = this.field_73220_k.nextLong() / 2L * 2L + 1L;
      long l = this.field_73220_k.nextLong() / 2L * 2L + 1L;
      this.field_73220_k.setSeed((long)p_73153_2_ * k + (long)p_73153_3_ * l ^ this.field_73230_p.func_72905_C());
      boolean flag = false;
      ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);
      if(this.field_177477_r.field_177829_w && this.field_73229_q) {
         this.field_73223_w.func_175794_a(this.field_73230_p, this.field_73220_k, chunkcoordintpair);
      }

      if(this.field_177477_r.field_177831_v && this.field_73229_q) {
         flag = this.field_73224_v.func_175794_a(this.field_73230_p, this.field_73220_k, chunkcoordintpair);
      }

      if(this.field_177477_r.field_177833_u && this.field_73229_q) {
         this.field_73225_u.func_175794_a(this.field_73230_p, this.field_73220_k, chunkcoordintpair);
      }

      if(this.field_177477_r.field_177854_x && this.field_73229_q) {
         this.field_73233_x.func_175794_a(this.field_73230_p, this.field_73220_k, chunkcoordintpair);
      }

      if(this.field_177477_r.field_177852_y && this.field_73229_q) {
         this.field_177474_A.func_175794_a(this.field_73230_p, this.field_73220_k, chunkcoordintpair);
      }

      if(biomegenbase != BiomeGenBase.field_76769_d && biomegenbase != BiomeGenBase.field_76786_s && this.field_177477_r.field_177781_A && !flag && this.field_73220_k.nextInt(this.field_177477_r.field_177782_B) == 0) {
         int i1 = this.field_73220_k.nextInt(16) + 8;
         int j1 = this.field_73220_k.nextInt(256);
         int k1 = this.field_73220_k.nextInt(16) + 8;
         (new WorldGenLakes(Blocks.field_150355_j)).func_180709_b(this.field_73230_p, this.field_73220_k, blockpos.func_177982_a(i1, j1, k1));
      }

      if(!flag && this.field_73220_k.nextInt(this.field_177477_r.field_177777_D / 10) == 0 && this.field_177477_r.field_177783_C) {
         int i2 = this.field_73220_k.nextInt(16) + 8;
         int l2 = this.field_73220_k.nextInt(this.field_73220_k.nextInt(248) + 8);
         int k3 = this.field_73220_k.nextInt(16) + 8;
         if(l2 < this.field_73230_p.func_181545_F() || this.field_73220_k.nextInt(this.field_177477_r.field_177777_D / 8) == 0) {
            (new WorldGenLakes(Blocks.field_150353_l)).func_180709_b(this.field_73230_p, this.field_73220_k, blockpos.func_177982_a(i2, l2, k3));
         }
      }

      if(this.field_177477_r.field_177837_s) {
         for(int j2 = 0; j2 < this.field_177477_r.field_177835_t; ++j2) {
            int i3 = this.field_73220_k.nextInt(16) + 8;
            int l3 = this.field_73220_k.nextInt(256);
            int l1 = this.field_73220_k.nextInt(16) + 8;
            (new WorldGenDungeons()).func_180709_b(this.field_73230_p, this.field_73220_k, blockpos.func_177982_a(i3, l3, l1));
         }
      }

      biomegenbase.func_180624_a(this.field_73230_p, this.field_73220_k, new BlockPos(i, 0, j));
      SpawnerAnimals.func_77191_a(this.field_73230_p, biomegenbase, i + 8, j + 8, 16, 16, this.field_73220_k);
      blockpos = blockpos.func_177982_a(8, 0, 8);

      for(int k2 = 0; k2 < 16; ++k2) {
         for(int j3 = 0; j3 < 16; ++j3) {
            BlockPos blockpos1 = this.field_73230_p.func_175725_q(blockpos.func_177982_a(k2, 0, j3));
            BlockPos blockpos2 = blockpos1.func_177977_b();
            if(this.field_73230_p.func_175675_v(blockpos2)) {
               this.field_73230_p.func_180501_a(blockpos2, Blocks.field_150432_aD.func_176223_P(), 2);
            }

            if(this.field_73230_p.func_175708_f(blockpos1, true)) {
               this.field_73230_p.func_180501_a(blockpos1, Blocks.field_150431_aC.func_176223_P(), 2);
            }
         }
      }

      BlockFalling.field_149832_M = false;
   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      boolean flag = false;
      if(this.field_177477_r.field_177852_y && this.field_73229_q && p_177460_2_.func_177416_w() < 3600L) {
         flag |= this.field_177474_A.func_175794_a(this.field_73230_p, this.field_73220_k, new ChunkCoordIntPair(p_177460_3_, p_177460_4_));
      }

      return flag;
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
      BiomeGenBase biomegenbase = this.field_73230_p.func_180494_b(p_177458_2_);
      if(this.field_73229_q) {
         if(p_177458_1_ == EnumCreatureType.MONSTER && this.field_73233_x.func_175798_a(p_177458_2_)) {
            return this.field_73233_x.func_82667_a();
         }

         if(p_177458_1_ == EnumCreatureType.MONSTER && this.field_177477_r.field_177852_y && this.field_177474_A.func_175796_a(this.field_73230_p, p_177458_2_)) {
            return this.field_177474_A.func_175799_b();
         }
      }

      return biomegenbase.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return "Stronghold".equals(p_180513_2_) && this.field_73225_u != null?this.field_73225_u.func_180706_b(p_180513_1_, p_180513_3_):null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
      if(this.field_177477_r.field_177829_w && this.field_73229_q) {
         this.field_73223_w.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if(this.field_177477_r.field_177831_v && this.field_73229_q) {
         this.field_73224_v.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if(this.field_177477_r.field_177833_u && this.field_73229_q) {
         this.field_73225_u.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if(this.field_177477_r.field_177854_x && this.field_73229_q) {
         this.field_73233_x.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

      if(this.field_177477_r.field_177852_y && this.field_73229_q) {
         this.field_177474_A.func_175792_a(this, this.field_73230_p, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
