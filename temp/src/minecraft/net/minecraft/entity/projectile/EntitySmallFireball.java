package net.minecraft.entity.projectile;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySmallFireball extends EntityFireball {
   public EntitySmallFireball(World p_i1770_1_) {
      super(p_i1770_1_);
      this.func_70105_a(0.3125F, 0.3125F);
   }

   public EntitySmallFireball(World p_i1771_1_, EntityLivingBase p_i1771_2_, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_) {
      super(p_i1771_1_, p_i1771_2_, p_i1771_3_, p_i1771_5_, p_i1771_7_);
      this.func_70105_a(0.3125F, 0.3125F);
   }

   public EntitySmallFireball(World p_i1772_1_, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_) {
      super(p_i1772_1_, p_i1772_2_, p_i1772_4_, p_i1772_6_, p_i1772_8_, p_i1772_10_, p_i1772_12_);
      this.func_70105_a(0.3125F, 0.3125F);
   }

   protected void func_70227_a(MovingObjectPosition p_70227_1_) {
      if(!this.field_70170_p.field_72995_K) {
         if(p_70227_1_.field_72308_g != null) {
            boolean flag = p_70227_1_.field_72308_g.func_70097_a(DamageSource.func_76362_a(this, this.field_70235_a), 5.0F);
            if(flag) {
               this.func_174815_a(this.field_70235_a, p_70227_1_.field_72308_g);
               if(!p_70227_1_.field_72308_g.func_70045_F()) {
                  p_70227_1_.field_72308_g.func_70015_d(5);
               }
            }
         } else {
            boolean flag1 = true;
            if(this.field_70235_a != null && this.field_70235_a instanceof EntityLiving) {
               flag1 = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");
            }

            if(flag1) {
               BlockPos blockpos = p_70227_1_.func_178782_a().func_177972_a(p_70227_1_.field_178784_b);
               if(this.field_70170_p.func_175623_d(blockpos)) {
                  this.field_70170_p.func_175656_a(blockpos, Blocks.field_150480_ab.func_176223_P());
               }
            }
         }

         this.func_70106_y();
      }

   }

   public boolean func_70067_L() {
      return false;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      return false;
   }
}
