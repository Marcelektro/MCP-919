package net.minecraft.pathfinding;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.SwimNodeProcessor;

public class PathNavigateSwimmer extends PathNavigate {
   public PathNavigateSwimmer(EntityLiving p_i45873_1_, World p_i45873_2_) {
      super(p_i45873_1_, p_i45873_2_);
   }

   protected PathFinder func_179679_a() {
      return new PathFinder(new SwimNodeProcessor());
   }

   protected boolean func_75485_k() {
      return this.func_75506_l();
   }

   protected Vec3 func_75502_i() {
      return new Vec3(this.field_75515_a.field_70165_t, this.field_75515_a.field_70163_u + (double)this.field_75515_a.field_70131_O * 0.5D, this.field_75515_a.field_70161_v);
   }

   protected void func_75508_h() {
      Vec3 vec3 = this.func_75502_i();
      float f = this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N;
      int i = 6;
      if(vec3.func_72436_e(this.field_75514_c.func_75881_a(this.field_75515_a, this.field_75514_c.func_75873_e())) < (double)f) {
         this.field_75514_c.func_75875_a();
      }

      for(int j = Math.min(this.field_75514_c.func_75873_e() + i, this.field_75514_c.func_75874_d() - 1); j > this.field_75514_c.func_75873_e(); --j) {
         Vec3 vec31 = this.field_75514_c.func_75881_a(this.field_75515_a, j);
         if(vec31.func_72436_e(vec3) <= 36.0D && this.func_75493_a(vec3, vec31, 0, 0, 0)) {
            this.field_75514_c.func_75872_c(j);
            break;
         }
      }

      this.func_179677_a(vec3);
   }

   protected void func_75487_m() {
      super.func_75487_m();
   }

   protected boolean func_75493_a(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
      MovingObjectPosition movingobjectposition = this.field_75513_b.func_147447_a(p_75493_1_, new Vec3(p_75493_2_.field_72450_a, p_75493_2_.field_72448_b + (double)this.field_75515_a.field_70131_O * 0.5D, p_75493_2_.field_72449_c), false, true, false);
      return movingobjectposition == null || movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.MISS;
   }
}
