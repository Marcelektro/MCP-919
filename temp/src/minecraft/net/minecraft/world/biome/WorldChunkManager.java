package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManager {
   private GenLayer field_76944_d;
   private GenLayer field_76945_e;
   private BiomeCache field_76942_f;
   private List<BiomeGenBase> field_76943_g;
   private String field_180301_f;

   protected WorldChunkManager() {
      this.field_76942_f = new BiomeCache(this);
      this.field_180301_f = "";
      this.field_76943_g = Lists.<BiomeGenBase>newArrayList();
      this.field_76943_g.add(BiomeGenBase.field_76767_f);
      this.field_76943_g.add(BiomeGenBase.field_76772_c);
      this.field_76943_g.add(BiomeGenBase.field_76768_g);
      this.field_76943_g.add(BiomeGenBase.field_76784_u);
      this.field_76943_g.add(BiomeGenBase.field_76785_t);
      this.field_76943_g.add(BiomeGenBase.field_76782_w);
      this.field_76943_g.add(BiomeGenBase.field_76792_x);
   }

   public WorldChunkManager(long p_i45744_1_, WorldType p_i45744_3_, String p_i45744_4_) {
      this();
      this.field_180301_f = p_i45744_4_;
      GenLayer[] agenlayer = GenLayer.func_180781_a(p_i45744_1_, p_i45744_3_, p_i45744_4_);
      this.field_76944_d = agenlayer[0];
      this.field_76945_e = agenlayer[1];
   }

   public WorldChunkManager(World p_i1976_1_) {
      this(p_i1976_1_.func_72905_C(), p_i1976_1_.func_72912_H().func_76067_t(), p_i1976_1_.func_72912_H().func_82571_y());
   }

   public List<BiomeGenBase> func_76932_a() {
      return this.field_76943_g;
   }

   public BiomeGenBase func_180631_a(BlockPos p_180631_1_) {
      return this.func_180300_a(p_180631_1_, (BiomeGenBase)null);
   }

   public BiomeGenBase func_180300_a(BlockPos p_180300_1_, BiomeGenBase p_180300_2_) {
      return this.field_76942_f.func_180284_a(p_180300_1_.func_177958_n(), p_180300_1_.func_177952_p(), p_180300_2_);
   }

   public float[] func_76936_a(float[] p_76936_1_, int p_76936_2_, int p_76936_3_, int p_76936_4_, int p_76936_5_) {
      IntCache.func_76446_a();
      if(p_76936_1_ == null || p_76936_1_.length < p_76936_4_ * p_76936_5_) {
         p_76936_1_ = new float[p_76936_4_ * p_76936_5_];
      }

      int[] aint = this.field_76945_e.func_75904_a(p_76936_2_, p_76936_3_, p_76936_4_, p_76936_5_);

      for(int i = 0; i < p_76936_4_ * p_76936_5_; ++i) {
         try {
            float f = (float)BiomeGenBase.func_180276_a(aint[i], BiomeGenBase.field_180279_ad).func_76744_g() / 65536.0F;
            if(f > 1.0F) {
               f = 1.0F;
            }

            p_76936_1_[i] = f;
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("DownfallBlock");
            crashreportcategory.func_71507_a("biome id", Integer.valueOf(i));
            crashreportcategory.func_71507_a("downfalls[] size", Integer.valueOf(p_76936_1_.length));
            crashreportcategory.func_71507_a("x", Integer.valueOf(p_76936_2_));
            crashreportcategory.func_71507_a("z", Integer.valueOf(p_76936_3_));
            crashreportcategory.func_71507_a("w", Integer.valueOf(p_76936_4_));
            crashreportcategory.func_71507_a("h", Integer.valueOf(p_76936_5_));
            throw new ReportedException(crashreport);
         }
      }

      return p_76936_1_;
   }

   public float func_76939_a(float p_76939_1_, int p_76939_2_) {
      return p_76939_1_;
   }

   public BiomeGenBase[] func_76937_a(BiomeGenBase[] p_76937_1_, int p_76937_2_, int p_76937_3_, int p_76937_4_, int p_76937_5_) {
      IntCache.func_76446_a();
      if(p_76937_1_ == null || p_76937_1_.length < p_76937_4_ * p_76937_5_) {
         p_76937_1_ = new BiomeGenBase[p_76937_4_ * p_76937_5_];
      }

      int[] aint = this.field_76944_d.func_75904_a(p_76937_2_, p_76937_3_, p_76937_4_, p_76937_5_);

      try {
         for(int i = 0; i < p_76937_4_ * p_76937_5_; ++i) {
            p_76937_1_[i] = BiomeGenBase.func_180276_a(aint[i], BiomeGenBase.field_180279_ad);
         }

         return p_76937_1_;
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Invalid Biome id");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("RawBiomeBlock");
         crashreportcategory.func_71507_a("biomes[] size", Integer.valueOf(p_76937_1_.length));
         crashreportcategory.func_71507_a("x", Integer.valueOf(p_76937_2_));
         crashreportcategory.func_71507_a("z", Integer.valueOf(p_76937_3_));
         crashreportcategory.func_71507_a("w", Integer.valueOf(p_76937_4_));
         crashreportcategory.func_71507_a("h", Integer.valueOf(p_76937_5_));
         throw new ReportedException(crashreport);
      }
   }

   public BiomeGenBase[] func_76933_b(BiomeGenBase[] p_76933_1_, int p_76933_2_, int p_76933_3_, int p_76933_4_, int p_76933_5_) {
      return this.func_76931_a(p_76933_1_, p_76933_2_, p_76933_3_, p_76933_4_, p_76933_5_, true);
   }

   public BiomeGenBase[] func_76931_a(BiomeGenBase[] p_76931_1_, int p_76931_2_, int p_76931_3_, int p_76931_4_, int p_76931_5_, boolean p_76931_6_) {
      IntCache.func_76446_a();
      if(p_76931_1_ == null || p_76931_1_.length < p_76931_4_ * p_76931_5_) {
         p_76931_1_ = new BiomeGenBase[p_76931_4_ * p_76931_5_];
      }

      if(p_76931_6_ && p_76931_4_ == 16 && p_76931_5_ == 16 && (p_76931_2_ & 15) == 0 && (p_76931_3_ & 15) == 0) {
         BiomeGenBase[] abiomegenbase = this.field_76942_f.func_76839_e(p_76931_2_, p_76931_3_);
         System.arraycopy(abiomegenbase, 0, p_76931_1_, 0, p_76931_4_ * p_76931_5_);
         return p_76931_1_;
      } else {
         int[] aint = this.field_76945_e.func_75904_a(p_76931_2_, p_76931_3_, p_76931_4_, p_76931_5_);

         for(int i = 0; i < p_76931_4_ * p_76931_5_; ++i) {
            p_76931_1_[i] = BiomeGenBase.func_180276_a(aint[i], BiomeGenBase.field_180279_ad);
         }

         return p_76931_1_;
      }
   }

   public boolean func_76940_a(int p_76940_1_, int p_76940_2_, int p_76940_3_, List<BiomeGenBase> p_76940_4_) {
      IntCache.func_76446_a();
      int i = p_76940_1_ - p_76940_3_ >> 2;
      int j = p_76940_2_ - p_76940_3_ >> 2;
      int k = p_76940_1_ + p_76940_3_ >> 2;
      int l = p_76940_2_ + p_76940_3_ >> 2;
      int i1 = k - i + 1;
      int j1 = l - j + 1;
      int[] aint = this.field_76944_d.func_75904_a(i, j, i1, j1);

      try {
         for(int k1 = 0; k1 < i1 * j1; ++k1) {
            BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d(aint[k1]);
            if(!p_76940_4_.contains(biomegenbase)) {
               return false;
            }
         }

         return true;
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Invalid Biome id");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Layer");
         crashreportcategory.func_71507_a("Layer", this.field_76944_d.toString());
         crashreportcategory.func_71507_a("x", Integer.valueOf(p_76940_1_));
         crashreportcategory.func_71507_a("z", Integer.valueOf(p_76940_2_));
         crashreportcategory.func_71507_a("radius", Integer.valueOf(p_76940_3_));
         crashreportcategory.func_71507_a("allowed", p_76940_4_);
         throw new ReportedException(crashreport);
      }
   }

   public BlockPos func_180630_a(int p_180630_1_, int p_180630_2_, int p_180630_3_, List<BiomeGenBase> p_180630_4_, Random p_180630_5_) {
      IntCache.func_76446_a();
      int i = p_180630_1_ - p_180630_3_ >> 2;
      int j = p_180630_2_ - p_180630_3_ >> 2;
      int k = p_180630_1_ + p_180630_3_ >> 2;
      int l = p_180630_2_ + p_180630_3_ >> 2;
      int i1 = k - i + 1;
      int j1 = l - j + 1;
      int[] aint = this.field_76944_d.func_75904_a(i, j, i1, j1);
      BlockPos blockpos = null;
      int k1 = 0;

      for(int l1 = 0; l1 < i1 * j1; ++l1) {
         int i2 = i + l1 % i1 << 2;
         int j2 = j + l1 / i1 << 2;
         BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d(aint[l1]);
         if(p_180630_4_.contains(biomegenbase) && (blockpos == null || p_180630_5_.nextInt(k1 + 1) == 0)) {
            blockpos = new BlockPos(i2, 0, j2);
            ++k1;
         }
      }

      return blockpos;
   }

   public void func_76938_b() {
      this.field_76942_f.func_76838_a();
   }
}
