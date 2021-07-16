package net.minecraft.world;

import net.minecraft.util.BlockPos;

public class ChunkCoordIntPair {
   public final int field_77276_a;
   public final int field_77275_b;

   public ChunkCoordIntPair(int p_i1947_1_, int p_i1947_2_) {
      this.field_77276_a = p_i1947_1_;
      this.field_77275_b = p_i1947_2_;
   }

   public static long func_77272_a(int p_77272_0_, int p_77272_1_) {
      return (long)p_77272_0_ & 4294967295L | ((long)p_77272_1_ & 4294967295L) << 32;
   }

   public int hashCode() {
      int i = 1664525 * this.field_77276_a + 1013904223;
      int j = 1664525 * (this.field_77275_b ^ -559038737) + 1013904223;
      return i ^ j;
   }

   public boolean equals(Object p_equals_1_) {
      if(this == p_equals_1_) {
         return true;
      } else if(!(p_equals_1_ instanceof ChunkCoordIntPair)) {
         return false;
      } else {
         ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)p_equals_1_;
         return this.field_77276_a == chunkcoordintpair.field_77276_a && this.field_77275_b == chunkcoordintpair.field_77275_b;
      }
   }

   public int func_77273_a() {
      return (this.field_77276_a << 4) + 8;
   }

   public int func_77274_b() {
      return (this.field_77275_b << 4) + 8;
   }

   public int func_180334_c() {
      return this.field_77276_a << 4;
   }

   public int func_180333_d() {
      return this.field_77275_b << 4;
   }

   public int func_180332_e() {
      return (this.field_77276_a << 4) + 15;
   }

   public int func_180330_f() {
      return (this.field_77275_b << 4) + 15;
   }

   public BlockPos func_180331_a(int p_180331_1_, int p_180331_2_, int p_180331_3_) {
      return new BlockPos((this.field_77276_a << 4) + p_180331_1_, p_180331_2_, (this.field_77275_b << 4) + p_180331_3_);
   }

   public BlockPos func_180619_a(int p_180619_1_) {
      return new BlockPos(this.func_77273_a(), p_180619_1_, this.func_77274_b());
   }

   public String toString() {
      return "[" + this.field_77276_a + ", " + this.field_77275_b + "]";
   }
}
