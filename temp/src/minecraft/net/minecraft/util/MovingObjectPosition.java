package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class MovingObjectPosition {
   private BlockPos field_178783_e;
   public MovingObjectPosition.MovingObjectType field_72313_a;
   public EnumFacing field_178784_b;
   public Vec3 field_72307_f;
   public Entity field_72308_g;

   public MovingObjectPosition(Vec3 p_i45551_1_, EnumFacing p_i45551_2_, BlockPos p_i45551_3_) {
      this(MovingObjectPosition.MovingObjectType.BLOCK, p_i45551_1_, p_i45551_2_, p_i45551_3_);
   }

   public MovingObjectPosition(Vec3 p_i45552_1_, EnumFacing p_i45552_2_) {
      this(MovingObjectPosition.MovingObjectType.BLOCK, p_i45552_1_, p_i45552_2_, BlockPos.field_177992_a);
   }

   public MovingObjectPosition(Entity p_i2304_1_) {
      this(p_i2304_1_, new Vec3(p_i2304_1_.field_70165_t, p_i2304_1_.field_70163_u, p_i2304_1_.field_70161_v));
   }

   public MovingObjectPosition(MovingObjectPosition.MovingObjectType p_i45553_1_, Vec3 p_i45553_2_, EnumFacing p_i45553_3_, BlockPos p_i45553_4_) {
      this.field_72313_a = p_i45553_1_;
      this.field_178783_e = p_i45553_4_;
      this.field_178784_b = p_i45553_3_;
      this.field_72307_f = new Vec3(p_i45553_2_.field_72450_a, p_i45553_2_.field_72448_b, p_i45553_2_.field_72449_c);
   }

   public MovingObjectPosition(Entity p_i45482_1_, Vec3 p_i45482_2_) {
      this.field_72313_a = MovingObjectPosition.MovingObjectType.ENTITY;
      this.field_72308_g = p_i45482_1_;
      this.field_72307_f = p_i45482_2_;
   }

   public BlockPos func_178782_a() {
      return this.field_178783_e;
   }

   public String toString() {
      return "HitResult{type=" + this.field_72313_a + ", blockpos=" + this.field_178783_e + ", f=" + this.field_178784_b + ", pos=" + this.field_72307_f + ", entity=" + this.field_72308_g + '}';
   }

   public static enum MovingObjectType {
      MISS,
      BLOCK,
      ENTITY;
   }
}
