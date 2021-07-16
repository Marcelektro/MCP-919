package net.minecraft.pathfinding;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;

public abstract class PathNavigate {
   protected EntityLiving field_75515_a;
   protected World field_75513_b;
   protected PathEntity field_75514_c;
   protected double field_75511_d;
   private final IAttributeInstance field_75512_e;
   private int field_75510_g;
   private int field_75520_h;
   private Vec3 field_75521_i = new Vec3(0.0D, 0.0D, 0.0D);
   private float field_179682_i = 1.0F;
   private final PathFinder field_179681_j;

   public PathNavigate(EntityLiving p_i1671_1_, World p_i1671_2_) {
      this.field_75515_a = p_i1671_1_;
      this.field_75513_b = p_i1671_2_;
      this.field_75512_e = p_i1671_1_.func_110148_a(SharedMonsterAttributes.field_111265_b);
      this.field_179681_j = this.func_179679_a();
   }

   protected abstract PathFinder func_179679_a();

   public void func_75489_a(double p_75489_1_) {
      this.field_75511_d = p_75489_1_;
   }

   public float func_111269_d() {
      return (float)this.field_75512_e.func_111126_e();
   }

   public final PathEntity func_75488_a(double p_75488_1_, double p_75488_3_, double p_75488_5_) {
      return this.func_179680_a(new BlockPos(MathHelper.func_76128_c(p_75488_1_), (int)p_75488_3_, MathHelper.func_76128_c(p_75488_5_)));
   }

   public PathEntity func_179680_a(BlockPos p_179680_1_) {
      if(!this.func_75485_k()) {
         return null;
      } else {
         float f = this.func_111269_d();
         this.field_75513_b.field_72984_F.func_76320_a("pathfind");
         BlockPos blockpos = new BlockPos(this.field_75515_a);
         int i = (int)(f + 8.0F);
         ChunkCache chunkcache = new ChunkCache(this.field_75513_b, blockpos.func_177982_a(-i, -i, -i), blockpos.func_177982_a(i, i, i), 0);
         PathEntity pathentity = this.field_179681_j.func_180782_a(chunkcache, this.field_75515_a, p_179680_1_, f);
         this.field_75513_b.field_72984_F.func_76319_b();
         return pathentity;
      }
   }

   public boolean func_75492_a(double p_75492_1_, double p_75492_3_, double p_75492_5_, double p_75492_7_) {
      PathEntity pathentity = this.func_75488_a((double)MathHelper.func_76128_c(p_75492_1_), (double)((int)p_75492_3_), (double)MathHelper.func_76128_c(p_75492_5_));
      return this.func_75484_a(pathentity, p_75492_7_);
   }

   public void func_179678_a(float p_179678_1_) {
      this.field_179682_i = p_179678_1_;
   }

   public PathEntity func_75494_a(Entity p_75494_1_) {
      if(!this.func_75485_k()) {
         return null;
      } else {
         float f = this.func_111269_d();
         this.field_75513_b.field_72984_F.func_76320_a("pathfind");
         BlockPos blockpos = (new BlockPos(this.field_75515_a)).func_177984_a();
         int i = (int)(f + 16.0F);
         ChunkCache chunkcache = new ChunkCache(this.field_75513_b, blockpos.func_177982_a(-i, -i, -i), blockpos.func_177982_a(i, i, i), 0);
         PathEntity pathentity = this.field_179681_j.func_176188_a(chunkcache, this.field_75515_a, p_75494_1_, f);
         this.field_75513_b.field_72984_F.func_76319_b();
         return pathentity;
      }
   }

   public boolean func_75497_a(Entity p_75497_1_, double p_75497_2_) {
      PathEntity pathentity = this.func_75494_a(p_75497_1_);
      return pathentity != null?this.func_75484_a(pathentity, p_75497_2_):false;
   }

   public boolean func_75484_a(PathEntity p_75484_1_, double p_75484_2_) {
      if(p_75484_1_ == null) {
         this.field_75514_c = null;
         return false;
      } else {
         if(!p_75484_1_.func_75876_a(this.field_75514_c)) {
            this.field_75514_c = p_75484_1_;
         }

         this.func_75487_m();
         if(this.field_75514_c.func_75874_d() == 0) {
            return false;
         } else {
            this.field_75511_d = p_75484_2_;
            Vec3 vec3 = this.func_75502_i();
            this.field_75520_h = this.field_75510_g;
            this.field_75521_i = vec3;
            return true;
         }
      }
   }

   public PathEntity func_75505_d() {
      return this.field_75514_c;
   }

   public void func_75501_e() {
      ++this.field_75510_g;
      if(!this.func_75500_f()) {
         if(this.func_75485_k()) {
            this.func_75508_h();
         } else if(this.field_75514_c != null && this.field_75514_c.func_75873_e() < this.field_75514_c.func_75874_d()) {
            Vec3 vec3 = this.func_75502_i();
            Vec3 vec31 = this.field_75514_c.func_75881_a(this.field_75515_a, this.field_75514_c.func_75873_e());
            if(vec3.field_72448_b > vec31.field_72448_b && !this.field_75515_a.field_70122_E && MathHelper.func_76128_c(vec3.field_72450_a) == MathHelper.func_76128_c(vec31.field_72450_a) && MathHelper.func_76128_c(vec3.field_72449_c) == MathHelper.func_76128_c(vec31.field_72449_c)) {
               this.field_75514_c.func_75872_c(this.field_75514_c.func_75873_e() + 1);
            }
         }

         if(!this.func_75500_f()) {
            Vec3 vec32 = this.field_75514_c.func_75878_a(this.field_75515_a);
            if(vec32 != null) {
               AxisAlignedBB axisalignedbb1 = (new AxisAlignedBB(vec32.field_72450_a, vec32.field_72448_b, vec32.field_72449_c, vec32.field_72450_a, vec32.field_72448_b, vec32.field_72449_c)).func_72314_b(0.5D, 0.5D, 0.5D);
               List<AxisAlignedBB> list = this.field_75513_b.func_72945_a(this.field_75515_a, axisalignedbb1.func_72321_a(0.0D, -1.0D, 0.0D));
               double d0 = -1.0D;
               axisalignedbb1 = axisalignedbb1.func_72317_d(0.0D, 1.0D, 0.0D);

               for(AxisAlignedBB axisalignedbb : list) {
                  d0 = axisalignedbb.func_72323_b(axisalignedbb1, d0);
               }

               this.field_75515_a.func_70605_aq().func_75642_a(vec32.field_72450_a, vec32.field_72448_b + d0, vec32.field_72449_c, this.field_75511_d);
            }
         }
      }
   }

   protected void func_75508_h() {
      Vec3 vec3 = this.func_75502_i();
      int i = this.field_75514_c.func_75874_d();

      for(int j = this.field_75514_c.func_75873_e(); j < this.field_75514_c.func_75874_d(); ++j) {
         if(this.field_75514_c.func_75877_a(j).field_75837_b != (int)vec3.field_72448_b) {
            i = j;
            break;
         }
      }

      float f = this.field_75515_a.field_70130_N * this.field_75515_a.field_70130_N * this.field_179682_i;

      for(int k = this.field_75514_c.func_75873_e(); k < i; ++k) {
         Vec3 vec31 = this.field_75514_c.func_75881_a(this.field_75515_a, k);
         if(vec3.func_72436_e(vec31) < (double)f) {
            this.field_75514_c.func_75872_c(k + 1);
         }
      }

      int j1 = MathHelper.func_76123_f(this.field_75515_a.field_70130_N);
      int k1 = (int)this.field_75515_a.field_70131_O + 1;
      int l = j1;

      for(int i1 = i - 1; i1 >= this.field_75514_c.func_75873_e(); --i1) {
         if(this.func_75493_a(vec3, this.field_75514_c.func_75881_a(this.field_75515_a, i1), j1, k1, l)) {
            this.field_75514_c.func_75872_c(i1);
            break;
         }
      }

      this.func_179677_a(vec3);
   }

   protected void func_179677_a(Vec3 p_179677_1_) {
      if(this.field_75510_g - this.field_75520_h > 100) {
         if(p_179677_1_.func_72436_e(this.field_75521_i) < 2.25D) {
            this.func_75499_g();
         }

         this.field_75520_h = this.field_75510_g;
         this.field_75521_i = p_179677_1_;
      }

   }

   public boolean func_75500_f() {
      return this.field_75514_c == null || this.field_75514_c.func_75879_b();
   }

   public void func_75499_g() {
      this.field_75514_c = null;
   }

   protected abstract Vec3 func_75502_i();

   protected abstract boolean func_75485_k();

   protected boolean func_75506_l() {
      return this.field_75515_a.func_70090_H() || this.field_75515_a.func_180799_ab();
   }

   protected void func_75487_m() {
   }

   protected abstract boolean func_75493_a(Vec3 var1, Vec3 var2, int var3, int var4, int var5);
}
