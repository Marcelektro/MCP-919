package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerAddSnow;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerDeepOcean;
import net.minecraft.world.gen.layer.GenLayerEdge;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayer {
   private long field_75907_b;
   protected GenLayer field_75909_a;
   private long field_75908_c;
   protected long field_75906_d;

   public static GenLayer[] func_180781_a(long p_180781_0_, WorldType p_180781_2_, String p_180781_3_) {
      GenLayer genlayer = new GenLayerIsland(1L);
      genlayer = new GenLayerFuzzyZoom(2000L, genlayer);
      GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayer);
      GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
      GenLayerAddIsland genlayeraddisland1 = new GenLayerAddIsland(2L, genlayerzoom);
      genlayeraddisland1 = new GenLayerAddIsland(50L, genlayeraddisland1);
      genlayeraddisland1 = new GenLayerAddIsland(70L, genlayeraddisland1);
      GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland1);
      GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
      GenLayerAddIsland genlayeraddisland2 = new GenLayerAddIsland(3L, genlayeraddsnow);
      GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland2, GenLayerEdge.Mode.COOL_WARM);
      genlayeredge = new GenLayerEdge(2L, genlayeredge, GenLayerEdge.Mode.HEAT_ICE);
      genlayeredge = new GenLayerEdge(3L, genlayeredge, GenLayerEdge.Mode.SPECIAL);
      GenLayerZoom genlayerzoom1 = new GenLayerZoom(2002L, genlayeredge);
      genlayerzoom1 = new GenLayerZoom(2003L, genlayerzoom1);
      GenLayerAddIsland genlayeraddisland3 = new GenLayerAddIsland(4L, genlayerzoom1);
      GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland3);
      GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
      GenLayer genlayer4 = GenLayerZoom.func_75915_a(1000L, genlayerdeepocean, 0);
      ChunkProviderSettings chunkprovidersettings = null;
      int i = 4;
      int j = i;
      if(p_180781_2_ == WorldType.field_180271_f && p_180781_3_.length() > 0) {
         chunkprovidersettings = ChunkProviderSettings.Factory.func_177865_a(p_180781_3_).func_177864_b();
         i = chunkprovidersettings.field_177780_G;
         j = chunkprovidersettings.field_177788_H;
      }

      if(p_180781_2_ == WorldType.field_77135_d) {
         i = 6;
      }

      GenLayer lvt_8_1_ = GenLayerZoom.func_75915_a(1000L, genlayer4, 0);
      GenLayerRiverInit genlayerriverinit = new GenLayerRiverInit(100L, lvt_8_1_);
      GenLayerBiome lvt_9_1_ = new GenLayerBiome(200L, genlayer4, p_180781_2_, p_180781_3_);
      GenLayer genlayer6 = GenLayerZoom.func_75915_a(1000L, lvt_9_1_, 2);
      GenLayerBiomeEdge genlayerbiomeedge = new GenLayerBiomeEdge(1000L, genlayer6);
      GenLayer lvt_10_1_ = GenLayerZoom.func_75915_a(1000L, genlayerriverinit, 2);
      GenLayerHills genlayerhills = new GenLayerHills(1000L, genlayerbiomeedge, lvt_10_1_);
      GenLayer genlayer5 = GenLayerZoom.func_75915_a(1000L, genlayerriverinit, 2);
      genlayer5 = GenLayerZoom.func_75915_a(1000L, genlayer5, j);
      GenLayerRiver genlayerriver = new GenLayerRiver(1L, genlayer5);
      GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayerriver);
      genlayerhills = new GenLayerRareBiome(1001L, genlayerhills);

      for(int k = 0; k < i; ++k) {
         genlayerhills = new GenLayerZoom((long)(1000 + k), genlayerhills);
         if(k == 0) {
            genlayerhills = new GenLayerAddIsland(3L, genlayerhills);
         }

         if(k == 1 || i == 1) {
            genlayerhills = new GenLayerShore(1000L, genlayerhills);
         }
      }

      GenLayerSmooth genlayersmooth1 = new GenLayerSmooth(1000L, genlayerhills);
      GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, genlayersmooth1, genlayersmooth);
      GenLayer genlayer3 = new GenLayerVoronoiZoom(10L, genlayerrivermix);
      genlayerrivermix.func_75905_a(p_180781_0_);
      genlayer3.func_75905_a(p_180781_0_);
      return new GenLayer[]{genlayerrivermix, genlayer3, genlayerrivermix};
   }

   public GenLayer(long p_i2125_1_) {
      this.field_75906_d = p_i2125_1_;
      this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
      this.field_75906_d += p_i2125_1_;
      this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
      this.field_75906_d += p_i2125_1_;
      this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
      this.field_75906_d += p_i2125_1_;
   }

   public void func_75905_a(long p_75905_1_) {
      this.field_75907_b = p_75905_1_;
      if(this.field_75909_a != null) {
         this.field_75909_a.func_75905_a(p_75905_1_);
      }

      this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
      this.field_75907_b += this.field_75906_d;
      this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
      this.field_75907_b += this.field_75906_d;
      this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
      this.field_75907_b += this.field_75906_d;
   }

   public void func_75903_a(long p_75903_1_, long p_75903_3_) {
      this.field_75908_c = this.field_75907_b;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_1_;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_3_;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_1_;
      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += p_75903_3_;
   }

   protected int func_75902_a(int p_75902_1_) {
      int i = (int)((this.field_75908_c >> 24) % (long)p_75902_1_);
      if(i < 0) {
         i += p_75902_1_;
      }

      this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
      this.field_75908_c += this.field_75907_b;
      return i;
   }

   public abstract int[] func_75904_a(int var1, int var2, int var3, int var4);

   protected static boolean func_151616_a(int p_151616_0_, int p_151616_1_) {
      if(p_151616_0_ == p_151616_1_) {
         return true;
      } else if(p_151616_0_ != BiomeGenBase.field_150607_aa.field_76756_M && p_151616_0_ != BiomeGenBase.field_150608_ab.field_76756_M) {
         final BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d(p_151616_0_);
         final BiomeGenBase biomegenbase1 = BiomeGenBase.func_150568_d(p_151616_1_);

         try {
            return biomegenbase != null && biomegenbase1 != null?biomegenbase.func_150569_a(biomegenbase1):false;
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Comparing biomes");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Biomes being compared");
            crashreportcategory.func_71507_a("Biome A ID", Integer.valueOf(p_151616_0_));
            crashreportcategory.func_71507_a("Biome B ID", Integer.valueOf(p_151616_1_));
            crashreportcategory.func_71500_a("Biome A", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf((Object)biomegenbase);
               }
            });
            crashreportcategory.func_71500_a("Biome B", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf((Object)biomegenbase1);
               }
            });
            throw new ReportedException(crashreport);
         }
      } else {
         return p_151616_1_ == BiomeGenBase.field_150607_aa.field_76756_M || p_151616_1_ == BiomeGenBase.field_150608_ab.field_76756_M;
      }
   }

   protected static boolean func_151618_b(int p_151618_0_) {
      return p_151618_0_ == BiomeGenBase.field_76771_b.field_76756_M || p_151618_0_ == BiomeGenBase.field_150575_M.field_76756_M || p_151618_0_ == BiomeGenBase.field_76776_l.field_76756_M;
   }

   protected int func_151619_a(int... p_151619_1_) {
      return p_151619_1_[this.func_75902_a(p_151619_1_.length)];
   }

   protected int func_151617_b(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_) {
      return p_151617_2_ == p_151617_3_ && p_151617_3_ == p_151617_4_?p_151617_2_:(p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_3_?p_151617_1_:(p_151617_1_ == p_151617_2_ && p_151617_1_ == p_151617_4_?p_151617_1_:(p_151617_1_ == p_151617_3_ && p_151617_1_ == p_151617_4_?p_151617_1_:(p_151617_1_ == p_151617_2_ && p_151617_3_ != p_151617_4_?p_151617_1_:(p_151617_1_ == p_151617_3_ && p_151617_2_ != p_151617_4_?p_151617_1_:(p_151617_1_ == p_151617_4_ && p_151617_2_ != p_151617_3_?p_151617_1_:(p_151617_2_ == p_151617_3_ && p_151617_1_ != p_151617_4_?p_151617_2_:(p_151617_2_ == p_151617_4_ && p_151617_1_ != p_151617_3_?p_151617_2_:(p_151617_3_ == p_151617_4_ && p_151617_1_ != p_151617_2_?p_151617_3_:this.func_151619_a(new int[]{p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_}))))))))));
   }
}
