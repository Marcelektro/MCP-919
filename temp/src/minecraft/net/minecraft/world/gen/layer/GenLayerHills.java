package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerHills extends GenLayer {
   private static final Logger field_151629_c = LogManager.getLogger();
   private GenLayer field_151628_d;

   public GenLayerHills(long p_i45479_1_, GenLayer p_i45479_3_, GenLayer p_i45479_4_) {
      super(p_i45479_1_);
      this.field_75909_a = p_i45479_3_;
      this.field_151628_d = p_i45479_4_;
   }

   public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int[] aint = this.field_75909_a.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] aint1 = this.field_151628_d.func_75904_a(p_75904_1_ - 1, p_75904_2_ - 1, p_75904_3_ + 2, p_75904_4_ + 2);
      int[] aint2 = IntCache.func_76445_a(p_75904_3_ * p_75904_4_);

      for(int i = 0; i < p_75904_4_; ++i) {
         for(int j = 0; j < p_75904_3_; ++j) {
            this.func_75903_a((long)(j + p_75904_1_), (long)(i + p_75904_2_));
            int k = aint[j + 1 + (i + 1) * (p_75904_3_ + 2)];
            int l = aint1[j + 1 + (i + 1) * (p_75904_3_ + 2)];
            boolean flag = (l - 2) % 29 == 0;
            if(k > 255) {
               field_151629_c.debug("old! " + k);
            }

            if(k != 0 && l >= 2 && (l - 2) % 29 == 1 && k < 128) {
               if(BiomeGenBase.func_150568_d(k + 128) != null) {
                  aint2[j + i * p_75904_3_] = k + 128;
               } else {
                  aint2[j + i * p_75904_3_] = k;
               }
            } else if(this.func_75902_a(3) != 0 && !flag) {
               aint2[j + i * p_75904_3_] = k;
            } else {
               int i1 = k;
               if(k == BiomeGenBase.field_76769_d.field_76756_M) {
                  i1 = BiomeGenBase.field_76786_s.field_76756_M;
               } else if(k == BiomeGenBase.field_76767_f.field_76756_M) {
                  i1 = BiomeGenBase.field_76785_t.field_76756_M;
               } else if(k == BiomeGenBase.field_150583_P.field_76756_M) {
                  i1 = BiomeGenBase.field_150582_Q.field_76756_M;
               } else if(k == BiomeGenBase.field_150585_R.field_76756_M) {
                  i1 = BiomeGenBase.field_76772_c.field_76756_M;
               } else if(k == BiomeGenBase.field_76768_g.field_76756_M) {
                  i1 = BiomeGenBase.field_76784_u.field_76756_M;
               } else if(k == BiomeGenBase.field_150578_U.field_76756_M) {
                  i1 = BiomeGenBase.field_150581_V.field_76756_M;
               } else if(k == BiomeGenBase.field_150584_S.field_76756_M) {
                  i1 = BiomeGenBase.field_150579_T.field_76756_M;
               } else if(k == BiomeGenBase.field_76772_c.field_76756_M) {
                  if(this.func_75902_a(3) == 0) {
                     i1 = BiomeGenBase.field_76785_t.field_76756_M;
                  } else {
                     i1 = BiomeGenBase.field_76767_f.field_76756_M;
                  }
               } else if(k == BiomeGenBase.field_76774_n.field_76756_M) {
                  i1 = BiomeGenBase.field_76775_o.field_76756_M;
               } else if(k == BiomeGenBase.field_76782_w.field_76756_M) {
                  i1 = BiomeGenBase.field_76792_x.field_76756_M;
               } else if(k == BiomeGenBase.field_76771_b.field_76756_M) {
                  i1 = BiomeGenBase.field_150575_M.field_76756_M;
               } else if(k == BiomeGenBase.field_76770_e.field_76756_M) {
                  i1 = BiomeGenBase.field_150580_W.field_76756_M;
               } else if(k == BiomeGenBase.field_150588_X.field_76756_M) {
                  i1 = BiomeGenBase.field_150587_Y.field_76756_M;
               } else if(func_151616_a(k, BiomeGenBase.field_150607_aa.field_76756_M)) {
                  i1 = BiomeGenBase.field_150589_Z.field_76756_M;
               } else if(k == BiomeGenBase.field_150575_M.field_76756_M && this.func_75902_a(3) == 0) {
                  int j1 = this.func_75902_a(2);
                  if(j1 == 0) {
                     i1 = BiomeGenBase.field_76772_c.field_76756_M;
                  } else {
                     i1 = BiomeGenBase.field_76767_f.field_76756_M;
                  }
               }

               if(flag && i1 != k) {
                  if(BiomeGenBase.func_150568_d(i1 + 128) != null) {
                     i1 += 128;
                  } else {
                     i1 = k;
                  }
               }

               if(i1 == k) {
                  aint2[j + i * p_75904_3_] = k;
               } else {
                  int k2 = aint[j + 1 + (i + 1 - 1) * (p_75904_3_ + 2)];
                  int k1 = aint[j + 1 + 1 + (i + 1) * (p_75904_3_ + 2)];
                  int l1 = aint[j + 1 - 1 + (i + 1) * (p_75904_3_ + 2)];
                  int i2 = aint[j + 1 + (i + 1 + 1) * (p_75904_3_ + 2)];
                  int j2 = 0;
                  if(func_151616_a(k2, k)) {
                     ++j2;
                  }

                  if(func_151616_a(k1, k)) {
                     ++j2;
                  }

                  if(func_151616_a(l1, k)) {
                     ++j2;
                  }

                  if(func_151616_a(i2, k)) {
                     ++j2;
                  }

                  if(j2 >= 3) {
                     aint2[j + i * p_75904_3_] = i1;
                  } else {
                     aint2[j + i * p_75904_3_] = k;
                  }
               }
            }
         }
      }

      return aint2;
   }
}
