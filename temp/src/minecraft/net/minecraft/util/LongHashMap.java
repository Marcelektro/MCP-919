package net.minecraft.util;

public class LongHashMap<V> {
   private transient LongHashMap.Entry<V>[] field_76169_a = new LongHashMap.Entry[4096];
   private transient int field_76167_b;
   private int field_180201_c;
   private int field_76168_c = 3072;
   private final float field_76165_d = 0.75F;
   private transient volatile int field_76166_e;

   public LongHashMap() {
      this.field_180201_c = this.field_76169_a.length - 1;
   }

   private static int func_76155_g(long p_76155_0_) {
      return func_76157_a((int)(p_76155_0_ ^ p_76155_0_ >>> 32));
   }

   private static int func_76157_a(int p_76157_0_) {
      p_76157_0_ = p_76157_0_ ^ p_76157_0_ >>> 20 ^ p_76157_0_ >>> 12;
      return p_76157_0_ ^ p_76157_0_ >>> 7 ^ p_76157_0_ >>> 4;
   }

   private static int func_76158_a(int p_76158_0_, int p_76158_1_) {
      return p_76158_0_ & p_76158_1_;
   }

   public int func_76162_a() {
      return this.field_76167_b;
   }

   public V func_76164_a(long p_76164_1_) {
      int i = func_76155_g(p_76164_1_);

      for(LongHashMap.Entry<V> entry = this.field_76169_a[func_76158_a(i, this.field_180201_c)]; entry != null; entry = entry.field_76149_c) {
         if(entry.field_76150_a == p_76164_1_) {
            return entry.field_76148_b;
         }
      }

      return (V)null;
   }

   public boolean func_76161_b(long p_76161_1_) {
      return this.func_76160_c(p_76161_1_) != null;
   }

   final LongHashMap.Entry<V> func_76160_c(long p_76160_1_) {
      int i = func_76155_g(p_76160_1_);

      for(LongHashMap.Entry<V> entry = this.field_76169_a[func_76158_a(i, this.field_180201_c)]; entry != null; entry = entry.field_76149_c) {
         if(entry.field_76150_a == p_76160_1_) {
            return entry;
         }
      }

      return null;
   }

   public void func_76163_a(long p_76163_1_, V p_76163_3_) {
      int i = func_76155_g(p_76163_1_);
      int j = func_76158_a(i, this.field_180201_c);

      for(LongHashMap.Entry<V> entry = this.field_76169_a[j]; entry != null; entry = entry.field_76149_c) {
         if(entry.field_76150_a == p_76163_1_) {
            entry.field_76148_b = p_76163_3_;
            return;
         }
      }

      ++this.field_76166_e;
      this.func_76156_a(i, p_76163_1_, p_76163_3_, j);
   }

   private void func_76153_b(int p_76153_1_) {
      LongHashMap.Entry<V>[] entry = this.field_76169_a;
      int i = entry.length;
      if(i == 1073741824) {
         this.field_76168_c = Integer.MAX_VALUE;
      } else {
         LongHashMap.Entry<V>[] entry1 = new LongHashMap.Entry[p_76153_1_];
         this.func_76154_a(entry1);
         this.field_76169_a = entry1;
         this.field_180201_c = this.field_76169_a.length - 1;
         this.field_76168_c = (int)((float)p_76153_1_ * this.field_76165_d);
      }
   }

   private void func_76154_a(LongHashMap.Entry<V>[] p_76154_1_) {
      LongHashMap.Entry<V>[] entry = this.field_76169_a;
      int i = p_76154_1_.length;

      for(int j = 0; j < entry.length; ++j) {
         LongHashMap.Entry<V> entry1 = entry[j];
         if(entry1 != null) {
            entry[j] = null;

            while(true) {
               LongHashMap.Entry<V> entry2 = entry1.field_76149_c;
               int k = func_76158_a(entry1.field_76147_d, i - 1);
               entry1.field_76149_c = p_76154_1_[k];
               p_76154_1_[k] = entry1;
               entry1 = entry2;
               if(entry2 == null) {
                  break;
               }
            }
         }
      }

   }

   public V func_76159_d(long p_76159_1_) {
      LongHashMap.Entry<V> entry = this.func_76152_e(p_76159_1_);
      return (V)(entry == null?null:entry.field_76148_b);
   }

   final LongHashMap.Entry<V> func_76152_e(long p_76152_1_) {
      int i = func_76155_g(p_76152_1_);
      int j = func_76158_a(i, this.field_180201_c);
      LongHashMap.Entry<V> entry = this.field_76169_a[j];

      LongHashMap.Entry<V> entry1;
      LongHashMap.Entry<V> entry2;
      for(entry1 = entry; entry1 != null; entry1 = entry2) {
         entry2 = entry1.field_76149_c;
         if(entry1.field_76150_a == p_76152_1_) {
            ++this.field_76166_e;
            --this.field_76167_b;
            if(entry == entry1) {
               this.field_76169_a[j] = entry2;
            } else {
               entry.field_76149_c = entry2;
            }

            return entry1;
         }

         entry = entry1;
      }

      return entry1;
   }

   private void func_76156_a(int p_76156_1_, long p_76156_2_, V p_76156_4_, int p_76156_5_) {
      LongHashMap.Entry<V> entry = this.field_76169_a[p_76156_5_];
      this.field_76169_a[p_76156_5_] = new LongHashMap.Entry(p_76156_1_, p_76156_2_, p_76156_4_, entry);
      if(this.field_76167_b++ >= this.field_76168_c) {
         this.func_76153_b(2 * this.field_76169_a.length);
      }

   }

   static class Entry<V> {
      final long field_76150_a;
      V field_76148_b;
      LongHashMap.Entry<V> field_76149_c;
      final int field_76147_d;

      Entry(int p_i1553_1_, long p_i1553_2_, V p_i1553_4_, LongHashMap.Entry<V> p_i1553_5_) {
         this.field_76148_b = p_i1553_4_;
         this.field_76149_c = p_i1553_5_;
         this.field_76150_a = p_i1553_2_;
         this.field_76147_d = p_i1553_1_;
      }

      public final long func_76146_a() {
         return this.field_76150_a;
      }

      public final V func_76145_b() {
         return this.field_76148_b;
      }

      public final boolean equals(Object p_equals_1_) {
         if(!(p_equals_1_ instanceof LongHashMap.Entry)) {
            return false;
         } else {
            LongHashMap.Entry<V> entry = (LongHashMap.Entry)p_equals_1_;
            Object object = Long.valueOf(this.func_76146_a());
            Object object1 = Long.valueOf(entry.func_76146_a());
            if(object == object1 || object != null && object.equals(object1)) {
               Object object2 = this.func_76145_b();
               Object object3 = entry.func_76145_b();
               if(object2 == object3 || object2 != null && object2.equals(object3)) {
                  return true;
               }
            }

            return false;
         }
      }

      public final int hashCode() {
         return LongHashMap.func_76155_g(this.field_76150_a);
      }

      public final String toString() {
         return this.func_76146_a() + "=" + this.func_76145_b();
      }
   }
}
