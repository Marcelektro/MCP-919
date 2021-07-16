package net.minecraft.util;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public class BlockPos extends Vec3i {
   public static final BlockPos field_177992_a = new BlockPos(0, 0, 0);
   private static final int field_177990_b = 1 + MathHelper.func_151239_c(MathHelper.func_151236_b(30000000));
   private static final int field_177991_c = field_177990_b;
   private static final int field_177989_d = 64 - field_177990_b - field_177991_c;
   private static final int field_177987_f = 0 + field_177991_c;
   private static final int field_177988_g = field_177987_f + field_177989_d;
   private static final long field_177994_h = (1L << field_177990_b) - 1L;
   private static final long field_177995_i = (1L << field_177989_d) - 1L;
   private static final long field_177993_j = (1L << field_177991_c) - 1L;

   public BlockPos(int p_i46030_1_, int p_i46030_2_, int p_i46030_3_) {
      super(p_i46030_1_, p_i46030_2_, p_i46030_3_);
   }

   public BlockPos(double p_i46031_1_, double p_i46031_3_, double p_i46031_5_) {
      super(p_i46031_1_, p_i46031_3_, p_i46031_5_);
   }

   public BlockPos(Entity p_i46032_1_) {
      this(p_i46032_1_.field_70165_t, p_i46032_1_.field_70163_u, p_i46032_1_.field_70161_v);
   }

   public BlockPos(Vec3 p_i46033_1_) {
      this(p_i46033_1_.field_72450_a, p_i46033_1_.field_72448_b, p_i46033_1_.field_72449_c);
   }

   public BlockPos(Vec3i p_i46034_1_) {
      this(p_i46034_1_.func_177958_n(), p_i46034_1_.func_177956_o(), p_i46034_1_.func_177952_p());
   }

   public BlockPos func_177963_a(double p_177963_1_, double p_177963_3_, double p_177963_5_) {
      return p_177963_1_ == 0.0D && p_177963_3_ == 0.0D && p_177963_5_ == 0.0D?this:new BlockPos((double)this.func_177958_n() + p_177963_1_, (double)this.func_177956_o() + p_177963_3_, (double)this.func_177952_p() + p_177963_5_);
   }

   public BlockPos func_177982_a(int p_177982_1_, int p_177982_2_, int p_177982_3_) {
      return p_177982_1_ == 0 && p_177982_2_ == 0 && p_177982_3_ == 0?this:new BlockPos(this.func_177958_n() + p_177982_1_, this.func_177956_o() + p_177982_2_, this.func_177952_p() + p_177982_3_);
   }

   public BlockPos func_177971_a(Vec3i p_177971_1_) {
      return p_177971_1_.func_177958_n() == 0 && p_177971_1_.func_177956_o() == 0 && p_177971_1_.func_177952_p() == 0?this:new BlockPos(this.func_177958_n() + p_177971_1_.func_177958_n(), this.func_177956_o() + p_177971_1_.func_177956_o(), this.func_177952_p() + p_177971_1_.func_177952_p());
   }

   public BlockPos func_177973_b(Vec3i p_177973_1_) {
      return p_177973_1_.func_177958_n() == 0 && p_177973_1_.func_177956_o() == 0 && p_177973_1_.func_177952_p() == 0?this:new BlockPos(this.func_177958_n() - p_177973_1_.func_177958_n(), this.func_177956_o() - p_177973_1_.func_177956_o(), this.func_177952_p() - p_177973_1_.func_177952_p());
   }

   public BlockPos func_177984_a() {
      return this.func_177981_b(1);
   }

   public BlockPos func_177981_b(int p_177981_1_) {
      return this.func_177967_a(EnumFacing.UP, p_177981_1_);
   }

   public BlockPos func_177977_b() {
      return this.func_177979_c(1);
   }

   public BlockPos func_177979_c(int p_177979_1_) {
      return this.func_177967_a(EnumFacing.DOWN, p_177979_1_);
   }

   public BlockPos func_177978_c() {
      return this.func_177964_d(1);
   }

   public BlockPos func_177964_d(int p_177964_1_) {
      return this.func_177967_a(EnumFacing.NORTH, p_177964_1_);
   }

   public BlockPos func_177968_d() {
      return this.func_177970_e(1);
   }

   public BlockPos func_177970_e(int p_177970_1_) {
      return this.func_177967_a(EnumFacing.SOUTH, p_177970_1_);
   }

   public BlockPos func_177976_e() {
      return this.func_177985_f(1);
   }

   public BlockPos func_177985_f(int p_177985_1_) {
      return this.func_177967_a(EnumFacing.WEST, p_177985_1_);
   }

   public BlockPos func_177974_f() {
      return this.func_177965_g(1);
   }

   public BlockPos func_177965_g(int p_177965_1_) {
      return this.func_177967_a(EnumFacing.EAST, p_177965_1_);
   }

   public BlockPos func_177972_a(EnumFacing p_177972_1_) {
      return this.func_177967_a(p_177972_1_, 1);
   }

   public BlockPos func_177967_a(EnumFacing p_177967_1_, int p_177967_2_) {
      return p_177967_2_ == 0?this:new BlockPos(this.func_177958_n() + p_177967_1_.func_82601_c() * p_177967_2_, this.func_177956_o() + p_177967_1_.func_96559_d() * p_177967_2_, this.func_177952_p() + p_177967_1_.func_82599_e() * p_177967_2_);
   }

   public BlockPos func_177955_d(Vec3i p_177955_1_) {
      return new BlockPos(this.func_177956_o() * p_177955_1_.func_177952_p() - this.func_177952_p() * p_177955_1_.func_177956_o(), this.func_177952_p() * p_177955_1_.func_177958_n() - this.func_177958_n() * p_177955_1_.func_177952_p(), this.func_177958_n() * p_177955_1_.func_177956_o() - this.func_177956_o() * p_177955_1_.func_177958_n());
   }

   public long func_177986_g() {
      return ((long)this.func_177958_n() & field_177994_h) << field_177988_g | ((long)this.func_177956_o() & field_177995_i) << field_177987_f | ((long)this.func_177952_p() & field_177993_j) << 0;
   }

   public static BlockPos func_177969_a(long p_177969_0_) {
      int i = (int)(p_177969_0_ << 64 - field_177988_g - field_177990_b >> 64 - field_177990_b);
      int j = (int)(p_177969_0_ << 64 - field_177987_f - field_177989_d >> 64 - field_177989_d);
      int k = (int)(p_177969_0_ << 64 - field_177991_c >> 64 - field_177991_c);
      return new BlockPos(i, j, k);
   }

   public static Iterable<BlockPos> func_177980_a(BlockPos p_177980_0_, BlockPos p_177980_1_) {
      final BlockPos blockpos = new BlockPos(Math.min(p_177980_0_.func_177958_n(), p_177980_1_.func_177958_n()), Math.min(p_177980_0_.func_177956_o(), p_177980_1_.func_177956_o()), Math.min(p_177980_0_.func_177952_p(), p_177980_1_.func_177952_p()));
      final BlockPos blockpos1 = new BlockPos(Math.max(p_177980_0_.func_177958_n(), p_177980_1_.func_177958_n()), Math.max(p_177980_0_.func_177956_o(), p_177980_1_.func_177956_o()), Math.max(p_177980_0_.func_177952_p(), p_177980_1_.func_177952_p()));
      return new Iterable<BlockPos>() {
         public Iterator<BlockPos> iterator() {
            return new AbstractIterator<BlockPos>() {
               private BlockPos field_179309_b = null;

               protected BlockPos computeNext() {
                  if(this.field_179309_b == null) {
                     this.field_179309_b = blockpos;
                     return this.field_179309_b;
                  } else if(this.field_179309_b.equals(blockpos1)) {
                     return (BlockPos)this.endOfData();
                  } else {
                     int i = this.field_179309_b.func_177958_n();
                     int j = this.field_179309_b.func_177956_o();
                     int k = this.field_179309_b.func_177952_p();
                     if(i < blockpos1.func_177958_n()) {
                        ++i;
                     } else if(j < blockpos1.func_177956_o()) {
                        i = blockpos.func_177958_n();
                        ++j;
                     } else if(k < blockpos1.func_177952_p()) {
                        i = blockpos.func_177958_n();
                        j = blockpos.func_177956_o();
                        ++k;
                     }

                     this.field_179309_b = new BlockPos(i, j, k);
                     return this.field_179309_b;
                  }
               }
            };
         }
      };
   }

   public static Iterable<BlockPos.MutableBlockPos> func_177975_b(BlockPos p_177975_0_, BlockPos p_177975_1_) {
      final BlockPos blockpos = new BlockPos(Math.min(p_177975_0_.func_177958_n(), p_177975_1_.func_177958_n()), Math.min(p_177975_0_.func_177956_o(), p_177975_1_.func_177956_o()), Math.min(p_177975_0_.func_177952_p(), p_177975_1_.func_177952_p()));
      final BlockPos blockpos1 = new BlockPos(Math.max(p_177975_0_.func_177958_n(), p_177975_1_.func_177958_n()), Math.max(p_177975_0_.func_177956_o(), p_177975_1_.func_177956_o()), Math.max(p_177975_0_.func_177952_p(), p_177975_1_.func_177952_p()));
      return new Iterable<BlockPos.MutableBlockPos>() {
         public Iterator<BlockPos.MutableBlockPos> iterator() {
            return new AbstractIterator<BlockPos.MutableBlockPos>() {
               private BlockPos.MutableBlockPos field_179314_b = null;

               protected BlockPos.MutableBlockPos computeNext() {
                  if(this.field_179314_b == null) {
                     this.field_179314_b = new BlockPos.MutableBlockPos(blockpos.func_177958_n(), blockpos.func_177956_o(), blockpos.func_177952_p());
                     return this.field_179314_b;
                  } else if(this.field_179314_b.equals(blockpos1)) {
                     return (BlockPos.MutableBlockPos)this.endOfData();
                  } else {
                     int i = this.field_179314_b.func_177958_n();
                     int j = this.field_179314_b.func_177956_o();
                     int k = this.field_179314_b.func_177952_p();
                     if(i < blockpos1.func_177958_n()) {
                        ++i;
                     } else if(j < blockpos1.func_177956_o()) {
                        i = blockpos.func_177958_n();
                        ++j;
                     } else if(k < blockpos1.func_177952_p()) {
                        i = blockpos.func_177958_n();
                        j = blockpos.func_177956_o();
                        ++k;
                     }

                     this.field_179314_b.field_177997_b = i;
                     this.field_179314_b.field_177998_c = j;
                     this.field_179314_b.field_177996_d = k;
                     return this.field_179314_b;
                  }
               }
            };
         }
      };
   }

   public static final class MutableBlockPos extends BlockPos {
      private int field_177997_b;
      private int field_177998_c;
      private int field_177996_d;

      public MutableBlockPos() {
         this(0, 0, 0);
      }

      public MutableBlockPos(int p_i46024_1_, int p_i46024_2_, int p_i46024_3_) {
         super(0, 0, 0);
         this.field_177997_b = p_i46024_1_;
         this.field_177998_c = p_i46024_2_;
         this.field_177996_d = p_i46024_3_;
      }

      public int func_177958_n() {
         return this.field_177997_b;
      }

      public int func_177956_o() {
         return this.field_177998_c;
      }

      public int func_177952_p() {
         return this.field_177996_d;
      }

      public BlockPos.MutableBlockPos func_181079_c(int p_181079_1_, int p_181079_2_, int p_181079_3_) {
         this.field_177997_b = p_181079_1_;
         this.field_177998_c = p_181079_2_;
         this.field_177996_d = p_181079_3_;
         return this;
      }
   }
}
