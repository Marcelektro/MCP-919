package net.minecraft.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityWitherSkull extends EntityFireball {
   public EntityWitherSkull(World p_i1793_1_) {
      super(p_i1793_1_);
      this.func_70105_a(0.3125F, 0.3125F);
   }

   public EntityWitherSkull(World p_i1794_1_, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_) {
      super(p_i1794_1_, p_i1794_2_, p_i1794_3_, p_i1794_5_, p_i1794_7_);
      this.func_70105_a(0.3125F, 0.3125F);
   }

   protected float func_82341_c() {
      return this.func_82342_d()?0.73F:super.func_82341_c();
   }

   public EntityWitherSkull(World p_i1795_1_, double p_i1795_2_, double p_i1795_4_, double p_i1795_6_, double p_i1795_8_, double p_i1795_10_, double p_i1795_12_) {
      super(p_i1795_1_, p_i1795_2_, p_i1795_4_, p_i1795_6_, p_i1795_8_, p_i1795_10_, p_i1795_12_);
      this.func_70105_a(0.3125F, 0.3125F);
   }

   public boolean func_70027_ad() {
      return false;
   }

   public float func_180428_a(Explosion p_180428_1_, World p_180428_2_, BlockPos p_180428_3_, IBlockState p_180428_4_) {
      float f = super.func_180428_a(p_180428_1_, p_180428_2_, p_180428_3_, p_180428_4_);
      Block block = p_180428_4_.func_177230_c();
      if(this.func_82342_d() && EntityWither.func_181033_a(block)) {
         f = Math.min(0.8F, f);
      }

      return f;
   }

   protected void func_70227_a(MovingObjectPosition p_70227_1_) {
      if(!this.field_70170_p.field_72995_K) {
         if(p_70227_1_.field_72308_g != null) {
            if(this.field_70235_a != null) {
               if(p_70227_1_.field_72308_g.func_70097_a(DamageSource.func_76358_a(this.field_70235_a), 8.0F)) {
                  if(!p_70227_1_.field_72308_g.func_70089_S()) {
                     this.field_70235_a.func_70691_i(5.0F);
                  } else {
                     this.func_174815_a(this.field_70235_a, p_70227_1_.field_72308_g);
                  }
               }
            } else {
               p_70227_1_.field_72308_g.func_70097_a(DamageSource.field_76376_m, 5.0F);
            }

            if(p_70227_1_.field_72308_g instanceof EntityLivingBase) {
               int i = 0;
               if(this.field_70170_p.func_175659_aa() == EnumDifficulty.NORMAL) {
                  i = 10;
               } else if(this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) {
                  i = 40;
               }

               if(i > 0) {
                  ((EntityLivingBase)p_70227_1_.field_72308_g).func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 20 * i, 1));
               }
            }
         }

         this.field_70170_p.func_72885_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, false, this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"));
         this.func_70106_y();
      }

   }

   public boolean func_70067_L() {
      return false;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      return false;
   }

   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(10, Byte.valueOf((byte)0));
   }

   public boolean func_82342_d() {
      return this.field_70180_af.func_75683_a(10) == 1;
   }

   public void func_82343_e(boolean p_82343_1_) {
      this.field_70180_af.func_75692_b(10, Byte.valueOf((byte)(p_82343_1_?1:0)));
   }
}
