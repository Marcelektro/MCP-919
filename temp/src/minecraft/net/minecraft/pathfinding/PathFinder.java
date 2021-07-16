package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.pathfinder.NodeProcessor;

public class PathFinder {
   private Path field_75866_b = new Path();
   private PathPoint[] field_75864_d = new PathPoint[32];
   private NodeProcessor field_176190_c;

   public PathFinder(NodeProcessor p_i45557_1_) {
      this.field_176190_c = p_i45557_1_;
   }

   public PathEntity func_176188_a(IBlockAccess p_176188_1_, Entity p_176188_2_, Entity p_176188_3_, float p_176188_4_) {
      return this.func_176189_a(p_176188_1_, p_176188_2_, p_176188_3_.field_70165_t, p_176188_3_.func_174813_aQ().field_72338_b, p_176188_3_.field_70161_v, p_176188_4_);
   }

   public PathEntity func_180782_a(IBlockAccess p_180782_1_, Entity p_180782_2_, BlockPos p_180782_3_, float p_180782_4_) {
      return this.func_176189_a(p_180782_1_, p_180782_2_, (double)((float)p_180782_3_.func_177958_n() + 0.5F), (double)((float)p_180782_3_.func_177956_o() + 0.5F), (double)((float)p_180782_3_.func_177952_p() + 0.5F), p_180782_4_);
   }

   private PathEntity func_176189_a(IBlockAccess p_176189_1_, Entity p_176189_2_, double p_176189_3_, double p_176189_5_, double p_176189_7_, float p_176189_9_) {
      this.field_75866_b.func_75848_a();
      this.field_176190_c.func_176162_a(p_176189_1_, p_176189_2_);
      PathPoint pathpoint = this.field_176190_c.func_176161_a(p_176189_2_);
      PathPoint pathpoint1 = this.field_176190_c.func_176160_a(p_176189_2_, p_176189_3_, p_176189_5_, p_176189_7_);
      PathEntity pathentity = this.func_176187_a(p_176189_2_, pathpoint, pathpoint1, p_176189_9_);
      this.field_176190_c.func_176163_a();
      return pathentity;
   }

   private PathEntity func_176187_a(Entity p_176187_1_, PathPoint p_176187_2_, PathPoint p_176187_3_, float p_176187_4_) {
      p_176187_2_.field_75836_e = 0.0F;
      p_176187_2_.field_75833_f = p_176187_2_.func_75832_b(p_176187_3_);
      p_176187_2_.field_75834_g = p_176187_2_.field_75833_f;
      this.field_75866_b.func_75848_a();
      this.field_75866_b.func_75849_a(p_176187_2_);
      PathPoint pathpoint = p_176187_2_;

      while(!this.field_75866_b.func_75845_e()) {
         PathPoint pathpoint1 = this.field_75866_b.func_75844_c();
         if(pathpoint1.equals(p_176187_3_)) {
            return this.func_75853_a(p_176187_2_, p_176187_3_);
         }

         if(pathpoint1.func_75832_b(p_176187_3_) < pathpoint.func_75832_b(p_176187_3_)) {
            pathpoint = pathpoint1;
         }

         pathpoint1.field_75842_i = true;
         int i = this.field_176190_c.func_176164_a(this.field_75864_d, p_176187_1_, pathpoint1, p_176187_3_, p_176187_4_);

         for(int j = 0; j < i; ++j) {
            PathPoint pathpoint2 = this.field_75864_d[j];
            float f = pathpoint1.field_75836_e + pathpoint1.func_75832_b(pathpoint2);
            if(f < p_176187_4_ * 2.0F && (!pathpoint2.func_75831_a() || f < pathpoint2.field_75836_e)) {
               pathpoint2.field_75841_h = pathpoint1;
               pathpoint2.field_75836_e = f;
               pathpoint2.field_75833_f = pathpoint2.func_75832_b(p_176187_3_);
               if(pathpoint2.func_75831_a()) {
                  this.field_75866_b.func_75850_a(pathpoint2, pathpoint2.field_75836_e + pathpoint2.field_75833_f);
               } else {
                  pathpoint2.field_75834_g = pathpoint2.field_75836_e + pathpoint2.field_75833_f;
                  this.field_75866_b.func_75849_a(pathpoint2);
               }
            }
         }
      }

      if(pathpoint == p_176187_2_) {
         return null;
      } else {
         return this.func_75853_a(p_176187_2_, pathpoint);
      }
   }

   private PathEntity func_75853_a(PathPoint p_75853_1_, PathPoint p_75853_2_) {
      int i = 1;

      for(PathPoint pathpoint = p_75853_2_; pathpoint.field_75841_h != null; pathpoint = pathpoint.field_75841_h) {
         ++i;
      }

      PathPoint[] apathpoint = new PathPoint[i];
      PathPoint pathpoint1 = p_75853_2_;
      --i;

      for(apathpoint[i] = p_75853_2_; pathpoint1.field_75841_h != null; apathpoint[i] = pathpoint1) {
         pathpoint1 = pathpoint1.field_75841_h;
         --i;
      }

      return new PathEntity(apathpoint);
   }
}
