package net.minecraft.util;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class AxisAlignedBB {
   public final double field_72340_a;
   public final double field_72338_b;
   public final double field_72339_c;
   public final double field_72336_d;
   public final double field_72337_e;
   public final double field_72334_f;

   public AxisAlignedBB(double p_i2300_1_, double p_i2300_3_, double p_i2300_5_, double p_i2300_7_, double p_i2300_9_, double p_i2300_11_) {
      this.field_72340_a = Math.min(p_i2300_1_, p_i2300_7_);
      this.field_72338_b = Math.min(p_i2300_3_, p_i2300_9_);
      this.field_72339_c = Math.min(p_i2300_5_, p_i2300_11_);
      this.field_72336_d = Math.max(p_i2300_1_, p_i2300_7_);
      this.field_72337_e = Math.max(p_i2300_3_, p_i2300_9_);
      this.field_72334_f = Math.max(p_i2300_5_, p_i2300_11_);
   }

   public AxisAlignedBB(BlockPos p_i45554_1_, BlockPos p_i45554_2_) {
      this.field_72340_a = (double)p_i45554_1_.func_177958_n();
      this.field_72338_b = (double)p_i45554_1_.func_177956_o();
      this.field_72339_c = (double)p_i45554_1_.func_177952_p();
      this.field_72336_d = (double)p_i45554_2_.func_177958_n();
      this.field_72337_e = (double)p_i45554_2_.func_177956_o();
      this.field_72334_f = (double)p_i45554_2_.func_177952_p();
   }

   public AxisAlignedBB func_72321_a(double p_72321_1_, double p_72321_3_, double p_72321_5_) {
      double d0 = this.field_72340_a;
      double d1 = this.field_72338_b;
      double d2 = this.field_72339_c;
      double d3 = this.field_72336_d;
      double d4 = this.field_72337_e;
      double d5 = this.field_72334_f;
      if(p_72321_1_ < 0.0D) {
         d0 += p_72321_1_;
      } else if(p_72321_1_ > 0.0D) {
         d3 += p_72321_1_;
      }

      if(p_72321_3_ < 0.0D) {
         d1 += p_72321_3_;
      } else if(p_72321_3_ > 0.0D) {
         d4 += p_72321_3_;
      }

      if(p_72321_5_ < 0.0D) {
         d2 += p_72321_5_;
      } else if(p_72321_5_ > 0.0D) {
         d5 += p_72321_5_;
      }

      return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
   }

   public AxisAlignedBB func_72314_b(double p_72314_1_, double p_72314_3_, double p_72314_5_) {
      double d0 = this.field_72340_a - p_72314_1_;
      double d1 = this.field_72338_b - p_72314_3_;
      double d2 = this.field_72339_c - p_72314_5_;
      double d3 = this.field_72336_d + p_72314_1_;
      double d4 = this.field_72337_e + p_72314_3_;
      double d5 = this.field_72334_f + p_72314_5_;
      return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
   }

   public AxisAlignedBB func_111270_a(AxisAlignedBB p_111270_1_) {
      double d0 = Math.min(this.field_72340_a, p_111270_1_.field_72340_a);
      double d1 = Math.min(this.field_72338_b, p_111270_1_.field_72338_b);
      double d2 = Math.min(this.field_72339_c, p_111270_1_.field_72339_c);
      double d3 = Math.max(this.field_72336_d, p_111270_1_.field_72336_d);
      double d4 = Math.max(this.field_72337_e, p_111270_1_.field_72337_e);
      double d5 = Math.max(this.field_72334_f, p_111270_1_.field_72334_f);
      return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
   }

   public static AxisAlignedBB func_178781_a(double p_178781_0_, double p_178781_2_, double p_178781_4_, double p_178781_6_, double p_178781_8_, double p_178781_10_) {
      double d0 = Math.min(p_178781_0_, p_178781_6_);
      double d1 = Math.min(p_178781_2_, p_178781_8_);
      double d2 = Math.min(p_178781_4_, p_178781_10_);
      double d3 = Math.max(p_178781_0_, p_178781_6_);
      double d4 = Math.max(p_178781_2_, p_178781_8_);
      double d5 = Math.max(p_178781_4_, p_178781_10_);
      return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
   }

   public AxisAlignedBB func_72317_d(double p_72317_1_, double p_72317_3_, double p_72317_5_) {
      return new AxisAlignedBB(this.field_72340_a + p_72317_1_, this.field_72338_b + p_72317_3_, this.field_72339_c + p_72317_5_, this.field_72336_d + p_72317_1_, this.field_72337_e + p_72317_3_, this.field_72334_f + p_72317_5_);
   }

   public double func_72316_a(AxisAlignedBB p_72316_1_, double p_72316_2_) {
      if(p_72316_1_.field_72337_e > this.field_72338_b && p_72316_1_.field_72338_b < this.field_72337_e && p_72316_1_.field_72334_f > this.field_72339_c && p_72316_1_.field_72339_c < this.field_72334_f) {
         if(p_72316_2_ > 0.0D && p_72316_1_.field_72336_d <= this.field_72340_a) {
            double d1 = this.field_72340_a - p_72316_1_.field_72336_d;
            if(d1 < p_72316_2_) {
               p_72316_2_ = d1;
            }
         } else if(p_72316_2_ < 0.0D && p_72316_1_.field_72340_a >= this.field_72336_d) {
            double d0 = this.field_72336_d - p_72316_1_.field_72340_a;
            if(d0 > p_72316_2_) {
               p_72316_2_ = d0;
            }
         }

         return p_72316_2_;
      } else {
         return p_72316_2_;
      }
   }

   public double func_72323_b(AxisAlignedBB p_72323_1_, double p_72323_2_) {
      if(p_72323_1_.field_72336_d > this.field_72340_a && p_72323_1_.field_72340_a < this.field_72336_d && p_72323_1_.field_72334_f > this.field_72339_c && p_72323_1_.field_72339_c < this.field_72334_f) {
         if(p_72323_2_ > 0.0D && p_72323_1_.field_72337_e <= this.field_72338_b) {
            double d1 = this.field_72338_b - p_72323_1_.field_72337_e;
            if(d1 < p_72323_2_) {
               p_72323_2_ = d1;
            }
         } else if(p_72323_2_ < 0.0D && p_72323_1_.field_72338_b >= this.field_72337_e) {
            double d0 = this.field_72337_e - p_72323_1_.field_72338_b;
            if(d0 > p_72323_2_) {
               p_72323_2_ = d0;
            }
         }

         return p_72323_2_;
      } else {
         return p_72323_2_;
      }
   }

   public double func_72322_c(AxisAlignedBB p_72322_1_, double p_72322_2_) {
      if(p_72322_1_.field_72336_d > this.field_72340_a && p_72322_1_.field_72340_a < this.field_72336_d && p_72322_1_.field_72337_e > this.field_72338_b && p_72322_1_.field_72338_b < this.field_72337_e) {
         if(p_72322_2_ > 0.0D && p_72322_1_.field_72334_f <= this.field_72339_c) {
            double d1 = this.field_72339_c - p_72322_1_.field_72334_f;
            if(d1 < p_72322_2_) {
               p_72322_2_ = d1;
            }
         } else if(p_72322_2_ < 0.0D && p_72322_1_.field_72339_c >= this.field_72334_f) {
            double d0 = this.field_72334_f - p_72322_1_.field_72339_c;
            if(d0 > p_72322_2_) {
               p_72322_2_ = d0;
            }
         }

         return p_72322_2_;
      } else {
         return p_72322_2_;
      }
   }

   public boolean func_72326_a(AxisAlignedBB p_72326_1_) {
      return p_72326_1_.field_72336_d > this.field_72340_a && p_72326_1_.field_72340_a < this.field_72336_d?(p_72326_1_.field_72337_e > this.field_72338_b && p_72326_1_.field_72338_b < this.field_72337_e?p_72326_1_.field_72334_f > this.field_72339_c && p_72326_1_.field_72339_c < this.field_72334_f:false):false;
   }

   public boolean func_72318_a(Vec3 p_72318_1_) {
      return p_72318_1_.field_72450_a > this.field_72340_a && p_72318_1_.field_72450_a < this.field_72336_d?(p_72318_1_.field_72448_b > this.field_72338_b && p_72318_1_.field_72448_b < this.field_72337_e?p_72318_1_.field_72449_c > this.field_72339_c && p_72318_1_.field_72449_c < this.field_72334_f:false):false;
   }

   public double func_72320_b() {
      double d0 = this.field_72336_d - this.field_72340_a;
      double d1 = this.field_72337_e - this.field_72338_b;
      double d2 = this.field_72334_f - this.field_72339_c;
      return (d0 + d1 + d2) / 3.0D;
   }

   public AxisAlignedBB func_72331_e(double p_72331_1_, double p_72331_3_, double p_72331_5_) {
      double d0 = this.field_72340_a + p_72331_1_;
      double d1 = this.field_72338_b + p_72331_3_;
      double d2 = this.field_72339_c + p_72331_5_;
      double d3 = this.field_72336_d - p_72331_1_;
      double d4 = this.field_72337_e - p_72331_3_;
      double d5 = this.field_72334_f - p_72331_5_;
      return new AxisAlignedBB(d0, d1, d2, d3, d4, d5);
   }

   public MovingObjectPosition func_72327_a(Vec3 p_72327_1_, Vec3 p_72327_2_) {
      Vec3 vec3 = p_72327_1_.func_72429_b(p_72327_2_, this.field_72340_a);
      Vec3 vec31 = p_72327_1_.func_72429_b(p_72327_2_, this.field_72336_d);
      Vec3 vec32 = p_72327_1_.func_72435_c(p_72327_2_, this.field_72338_b);
      Vec3 vec33 = p_72327_1_.func_72435_c(p_72327_2_, this.field_72337_e);
      Vec3 vec34 = p_72327_1_.func_72434_d(p_72327_2_, this.field_72339_c);
      Vec3 vec35 = p_72327_1_.func_72434_d(p_72327_2_, this.field_72334_f);
      if(!this.func_72333_b(vec3)) {
         vec3 = null;
      }

      if(!this.func_72333_b(vec31)) {
         vec31 = null;
      }

      if(!this.func_72315_c(vec32)) {
         vec32 = null;
      }

      if(!this.func_72315_c(vec33)) {
         vec33 = null;
      }

      if(!this.func_72319_d(vec34)) {
         vec34 = null;
      }

      if(!this.func_72319_d(vec35)) {
         vec35 = null;
      }

      Vec3 vec36 = null;
      if(vec3 != null) {
         vec36 = vec3;
      }

      if(vec31 != null && (vec36 == null || p_72327_1_.func_72436_e(vec31) < p_72327_1_.func_72436_e(vec36))) {
         vec36 = vec31;
      }

      if(vec32 != null && (vec36 == null || p_72327_1_.func_72436_e(vec32) < p_72327_1_.func_72436_e(vec36))) {
         vec36 = vec32;
      }

      if(vec33 != null && (vec36 == null || p_72327_1_.func_72436_e(vec33) < p_72327_1_.func_72436_e(vec36))) {
         vec36 = vec33;
      }

      if(vec34 != null && (vec36 == null || p_72327_1_.func_72436_e(vec34) < p_72327_1_.func_72436_e(vec36))) {
         vec36 = vec34;
      }

      if(vec35 != null && (vec36 == null || p_72327_1_.func_72436_e(vec35) < p_72327_1_.func_72436_e(vec36))) {
         vec36 = vec35;
      }

      if(vec36 == null) {
         return null;
      } else {
         EnumFacing enumfacing = null;
         if(vec36 == vec3) {
            enumfacing = EnumFacing.WEST;
         } else if(vec36 == vec31) {
            enumfacing = EnumFacing.EAST;
         } else if(vec36 == vec32) {
            enumfacing = EnumFacing.DOWN;
         } else if(vec36 == vec33) {
            enumfacing = EnumFacing.UP;
         } else if(vec36 == vec34) {
            enumfacing = EnumFacing.NORTH;
         } else {
            enumfacing = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(vec36, enumfacing);
      }
   }

   private boolean func_72333_b(Vec3 p_72333_1_) {
      return p_72333_1_ == null?false:p_72333_1_.field_72448_b >= this.field_72338_b && p_72333_1_.field_72448_b <= this.field_72337_e && p_72333_1_.field_72449_c >= this.field_72339_c && p_72333_1_.field_72449_c <= this.field_72334_f;
   }

   private boolean func_72315_c(Vec3 p_72315_1_) {
      return p_72315_1_ == null?false:p_72315_1_.field_72450_a >= this.field_72340_a && p_72315_1_.field_72450_a <= this.field_72336_d && p_72315_1_.field_72449_c >= this.field_72339_c && p_72315_1_.field_72449_c <= this.field_72334_f;
   }

   private boolean func_72319_d(Vec3 p_72319_1_) {
      return p_72319_1_ == null?false:p_72319_1_.field_72450_a >= this.field_72340_a && p_72319_1_.field_72450_a <= this.field_72336_d && p_72319_1_.field_72448_b >= this.field_72338_b && p_72319_1_.field_72448_b <= this.field_72337_e;
   }

   public String toString() {
      return "box[" + this.field_72340_a + ", " + this.field_72338_b + ", " + this.field_72339_c + " -> " + this.field_72336_d + ", " + this.field_72337_e + ", " + this.field_72334_f + "]";
   }

   public boolean func_181656_b() {
      return Double.isNaN(this.field_72340_a) || Double.isNaN(this.field_72338_b) || Double.isNaN(this.field_72339_c) || Double.isNaN(this.field_72336_d) || Double.isNaN(this.field_72337_e) || Double.isNaN(this.field_72334_f);
   }
}
