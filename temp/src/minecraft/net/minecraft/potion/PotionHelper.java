package net.minecraft.potion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IntegerCache;

public class PotionHelper {
   public static final String field_77924_a = null;
   public static final String field_77922_b = "-0+1-2-3&4-4+13";
   public static final String field_77923_c = "+0-1-2-3&4-4+13";
   public static final String field_77920_d = "-0-1+2-3&4-4+13";
   public static final String field_77921_e = "-0+3-4+13";
   public static final String field_77918_f = "+0-1+2-3&4-4+13";
   public static final String field_77919_g = "+0-1-2+3&4-4+13";
   public static final String field_77931_h = "+0+1-2-3&4-4+13";
   public static final String field_77932_i = "-5+6-7";
   public static final String field_77929_j = "+5-6-7";
   public static final String field_77930_k = "+14&13-13";
   public static final String field_82818_l = "-0+1+2-3+13&4-4";
   public static final String field_151423_m = "+0-1+2+3+13&4-4";
   public static final String field_179538_n = "+0+1-2+3&4-4+13";
   private static final Map<Integer, String> field_179539_o = Maps.<Integer, String>newHashMap();
   private static final Map<Integer, String> field_179540_p = Maps.<Integer, String>newHashMap();
   private static final Map<Integer, Integer> field_77925_n = Maps.<Integer, Integer>newHashMap();
   private static final String[] field_77926_o = new String[]{"potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky"};

   public static boolean func_77914_a(int p_77914_0_, int p_77914_1_) {
      return (p_77914_0_ & 1 << p_77914_1_) != 0;
   }

   private static int func_77910_c(int p_77910_0_, int p_77910_1_) {
      return func_77914_a(p_77910_0_, p_77910_1_)?1:0;
   }

   private static int func_77916_d(int p_77916_0_, int p_77916_1_) {
      return func_77914_a(p_77916_0_, p_77916_1_)?0:1;
   }

   public static int func_77909_a(int p_77909_0_) {
      return func_77908_a(p_77909_0_, 5, 4, 3, 2, 1);
   }

   public static int func_77911_a(Collection<PotionEffect> p_77911_0_) {
      int i = 3694022;
      if(p_77911_0_ != null && !p_77911_0_.isEmpty()) {
         float f = 0.0F;
         float f1 = 0.0F;
         float f2 = 0.0F;
         float f3 = 0.0F;

         for(PotionEffect potioneffect : p_77911_0_) {
            if(potioneffect.func_180154_f()) {
               int j = Potion.field_76425_a[potioneffect.func_76456_a()].func_76401_j();

               for(int k = 0; k <= potioneffect.func_76458_c(); ++k) {
                  f += (float)(j >> 16 & 255) / 255.0F;
                  f1 += (float)(j >> 8 & 255) / 255.0F;
                  f2 += (float)(j >> 0 & 255) / 255.0F;
                  ++f3;
               }
            }
         }

         if(f3 == 0.0F) {
            return 0;
         } else {
            f = f / f3 * 255.0F;
            f1 = f1 / f3 * 255.0F;
            f2 = f2 / f3 * 255.0F;
            return (int)f << 16 | (int)f1 << 8 | (int)f2;
         }
      } else {
         return i;
      }
   }

   public static boolean func_82817_b(Collection<PotionEffect> p_82817_0_) {
      for(PotionEffect potioneffect : p_82817_0_) {
         if(!potioneffect.func_82720_e()) {
            return false;
         }
      }

      return true;
   }

   public static int func_77915_a(int p_77915_0_, boolean p_77915_1_) {
      Integer integer = IntegerCache.func_181756_a(p_77915_0_);
      if(!p_77915_1_) {
         if(field_77925_n.containsKey(integer)) {
            return ((Integer)field_77925_n.get(integer)).intValue();
         } else {
            int i = func_77911_a(func_77917_b(integer.intValue(), false));
            field_77925_n.put(integer, Integer.valueOf(i));
            return i;
         }
      } else {
         return func_77911_a(func_77917_b(integer.intValue(), true));
      }
   }

   public static String func_77905_c(int p_77905_0_) {
      int i = func_77909_a(p_77905_0_);
      return field_77926_o[i];
   }

   private static int func_77904_a(boolean p_77904_0_, boolean p_77904_1_, boolean p_77904_2_, int p_77904_3_, int p_77904_4_, int p_77904_5_, int p_77904_6_) {
      int i = 0;
      if(p_77904_0_) {
         i = func_77916_d(p_77904_6_, p_77904_4_);
      } else if(p_77904_3_ != -1) {
         if(p_77904_3_ == 0 && func_77907_h(p_77904_6_) == p_77904_4_) {
            i = 1;
         } else if(p_77904_3_ == 1 && func_77907_h(p_77904_6_) > p_77904_4_) {
            i = 1;
         } else if(p_77904_3_ == 2 && func_77907_h(p_77904_6_) < p_77904_4_) {
            i = 1;
         }
      } else {
         i = func_77910_c(p_77904_6_, p_77904_4_);
      }

      if(p_77904_1_) {
         i *= p_77904_5_;
      }

      if(p_77904_2_) {
         i *= -1;
      }

      return i;
   }

   private static int func_77907_h(int p_77907_0_) {
      int i;
      for(i = 0; p_77907_0_ > 0; ++i) {
         p_77907_0_ &= p_77907_0_ - 1;
      }

      return i;
   }

   private static int func_77912_a(String p_77912_0_, int p_77912_1_, int p_77912_2_, int p_77912_3_) {
      if(p_77912_1_ < p_77912_0_.length() && p_77912_2_ >= 0 && p_77912_1_ < p_77912_2_) {
         int i = p_77912_0_.indexOf(124, p_77912_1_);
         if(i >= 0 && i < p_77912_2_) {
            int l1 = func_77912_a(p_77912_0_, p_77912_1_, i - 1, p_77912_3_);
            if(l1 > 0) {
               return l1;
            } else {
               int j2 = func_77912_a(p_77912_0_, i + 1, p_77912_2_, p_77912_3_);
               return j2 > 0?j2:0;
            }
         } else {
            int j = p_77912_0_.indexOf(38, p_77912_1_);
            if(j >= 0 && j < p_77912_2_) {
               int i2 = func_77912_a(p_77912_0_, p_77912_1_, j - 1, p_77912_3_);
               if(i2 <= 0) {
                  return 0;
               } else {
                  int k2 = func_77912_a(p_77912_0_, j + 1, p_77912_2_, p_77912_3_);
                  return k2 <= 0?0:(i2 > k2?i2:k2);
               }
            } else {
               boolean flag = false;
               boolean flag1 = false;
               boolean flag2 = false;
               boolean flag3 = false;
               boolean flag4 = false;
               int k = -1;
               int l = 0;
               int i1 = 0;
               int j1 = 0;

               for(int k1 = p_77912_1_; k1 < p_77912_2_; ++k1) {
                  char c0 = p_77912_0_.charAt(k1);
                  if(c0 >= 48 && c0 <= 57) {
                     if(flag) {
                        i1 = c0 - 48;
                        flag1 = true;
                     } else {
                        l = l * 10;
                        l = l + (c0 - 48);
                        flag2 = true;
                     }
                  } else if(c0 == 42) {
                     flag = true;
                  } else if(c0 == 33) {
                     if(flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, k, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        k = -1;
                     }

                     flag3 = true;
                  } else if(c0 == 45) {
                     if(flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, k, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        k = -1;
                     }

                     flag4 = true;
                  } else if(c0 != 61 && c0 != 60 && c0 != 62) {
                     if(c0 == 43 && flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, k, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        k = -1;
                     }
                  } else {
                     if(flag2) {
                        j1 += func_77904_a(flag3, flag1, flag4, k, l, i1, p_77912_3_);
                        flag3 = false;
                        flag4 = false;
                        flag = false;
                        flag1 = false;
                        flag2 = false;
                        i1 = 0;
                        l = 0;
                        k = -1;
                     }

                     if(c0 == 61) {
                        k = 0;
                     } else if(c0 == 60) {
                        k = 2;
                     } else if(c0 == 62) {
                        k = 1;
                     }
                  }
               }

               if(flag2) {
                  j1 += func_77904_a(flag3, flag1, flag4, k, l, i1, p_77912_3_);
               }

               return j1;
            }
         }
      } else {
         return 0;
      }
   }

   public static List<PotionEffect> func_77917_b(int p_77917_0_, boolean p_77917_1_) {
      List<PotionEffect> list = null;

      for(Potion potion : Potion.field_76425_a) {
         if(potion != null && (!potion.func_76395_i() || p_77917_1_)) {
            String s = (String)field_179539_o.get(Integer.valueOf(potion.func_76396_c()));
            if(s != null) {
               int i = func_77912_a(s, 0, s.length(), p_77917_0_);
               if(i > 0) {
                  int j = 0;
                  String s1 = (String)field_179540_p.get(Integer.valueOf(potion.func_76396_c()));
                  if(s1 != null) {
                     j = func_77912_a(s1, 0, s1.length(), p_77917_0_);
                     if(j < 0) {
                        j = 0;
                     }
                  }

                  if(potion.func_76403_b()) {
                     i = 1;
                  } else {
                     i = 1200 * (i * 3 + (i - 1) * 2);
                     i = i >> j;
                     i = (int)Math.round((double)i * potion.func_76388_g());
                     if((p_77917_0_ & 16384) != 0) {
                        i = (int)Math.round((double)i * 0.75D + 0.5D);
                     }
                  }

                  if(list == null) {
                     list = Lists.<PotionEffect>newArrayList();
                  }

                  PotionEffect potioneffect = new PotionEffect(potion.func_76396_c(), i, j);
                  if((p_77917_0_ & 16384) != 0) {
                     potioneffect.func_82721_a(true);
                  }

                  list.add(potioneffect);
               }
            }
         }
      }

      return list;
   }

   private static int func_77906_a(int p_77906_0_, int p_77906_1_, boolean p_77906_2_, boolean p_77906_3_, boolean p_77906_4_) {
      if(p_77906_4_) {
         if(!func_77914_a(p_77906_0_, p_77906_1_)) {
            return 0;
         }
      } else if(p_77906_2_) {
         p_77906_0_ &= ~(1 << p_77906_1_);
      } else if(p_77906_3_) {
         if((p_77906_0_ & 1 << p_77906_1_) == 0) {
            p_77906_0_ |= 1 << p_77906_1_;
         } else {
            p_77906_0_ &= ~(1 << p_77906_1_);
         }
      } else {
         p_77906_0_ |= 1 << p_77906_1_;
      }

      return p_77906_0_;
   }

   public static int func_77913_a(int p_77913_0_, String p_77913_1_) {
      int i = 0;
      int j = p_77913_1_.length();
      boolean flag = false;
      boolean flag1 = false;
      boolean flag2 = false;
      boolean flag3 = false;
      int k = 0;

      for(int l = i; l < j; ++l) {
         char c0 = p_77913_1_.charAt(l);
         if(c0 >= 48 && c0 <= 57) {
            k = k * 10;
            k = k + (c0 - 48);
            flag = true;
         } else if(c0 == 33) {
            if(flag) {
               p_77913_0_ = func_77906_a(p_77913_0_, k, flag2, flag1, flag3);
               flag3 = false;
               flag1 = false;
               flag2 = false;
               flag = false;
               k = 0;
            }

            flag1 = true;
         } else if(c0 == 45) {
            if(flag) {
               p_77913_0_ = func_77906_a(p_77913_0_, k, flag2, flag1, flag3);
               flag3 = false;
               flag1 = false;
               flag2 = false;
               flag = false;
               k = 0;
            }

            flag2 = true;
         } else if(c0 == 43) {
            if(flag) {
               p_77913_0_ = func_77906_a(p_77913_0_, k, flag2, flag1, flag3);
               flag3 = false;
               flag1 = false;
               flag2 = false;
               flag = false;
               k = 0;
            }
         } else if(c0 == 38) {
            if(flag) {
               p_77913_0_ = func_77906_a(p_77913_0_, k, flag2, flag1, flag3);
               flag3 = false;
               flag1 = false;
               flag2 = false;
               flag = false;
               k = 0;
            }

            flag3 = true;
         }
      }

      if(flag) {
         p_77913_0_ = func_77906_a(p_77913_0_, k, flag2, flag1, flag3);
      }

      return p_77913_0_ & 32767;
   }

   public static int func_77908_a(int p_77908_0_, int p_77908_1_, int p_77908_2_, int p_77908_3_, int p_77908_4_, int p_77908_5_) {
      return (func_77914_a(p_77908_0_, p_77908_1_)?16:0) | (func_77914_a(p_77908_0_, p_77908_2_)?8:0) | (func_77914_a(p_77908_0_, p_77908_3_)?4:0) | (func_77914_a(p_77908_0_, p_77908_4_)?2:0) | (func_77914_a(p_77908_0_, p_77908_5_)?1:0);
   }

   static {
      field_179539_o.put(Integer.valueOf(Potion.field_76428_l.func_76396_c()), "0 & !1 & !2 & !3 & 0+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76424_c.func_76396_c()), "!0 & 1 & !2 & !3 & 1+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76426_n.func_76396_c()), "0 & 1 & !2 & !3 & 0+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76432_h.func_76396_c()), "0 & !1 & 2 & !3");
      field_179539_o.put(Integer.valueOf(Potion.field_76436_u.func_76396_c()), "!0 & !1 & 2 & !3 & 2+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76437_t.func_76396_c()), "!0 & !1 & !2 & 3 & 3+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76433_i.func_76396_c()), "!0 & !1 & 2 & 3");
      field_179539_o.put(Integer.valueOf(Potion.field_76421_d.func_76396_c()), "!0 & 1 & !2 & 3 & 3+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76420_g.func_76396_c()), "0 & !1 & !2 & 3 & 3+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76439_r.func_76396_c()), "!0 & 1 & 2 & !3 & 2+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76441_p.func_76396_c()), "!0 & 1 & 2 & 3 & 2+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76427_o.func_76396_c()), "0 & !1 & 2 & 3 & 2+6");
      field_179539_o.put(Integer.valueOf(Potion.field_76430_j.func_76396_c()), "0 & 1 & !2 & 3 & 3+6");
      field_179540_p.put(Integer.valueOf(Potion.field_76424_c.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76422_e.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76420_g.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76428_l.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76433_i.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76432_h.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76429_m.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76436_u.func_76396_c()), "5");
      field_179540_p.put(Integer.valueOf(Potion.field_76430_j.func_76396_c()), "5");
   }
}
