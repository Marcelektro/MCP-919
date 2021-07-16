package net.minecraft.entity.boss;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDragon extends EntityLiving implements IBossDisplayData, IEntityMultiPart, IMob {
   public double field_70980_b;
   public double field_70981_c;
   public double field_70978_d;
   public double[][] field_70979_e = new double[64][3];
   public int field_70976_f = -1;
   public EntityDragonPart[] field_70977_g;
   public EntityDragonPart field_70986_h;
   public EntityDragonPart field_70987_i;
   public EntityDragonPart field_70985_j;
   public EntityDragonPart field_70984_by;
   public EntityDragonPart field_70982_bz;
   public EntityDragonPart field_70983_bA;
   public EntityDragonPart field_70990_bB;
   public float field_70991_bC;
   public float field_70988_bD;
   public boolean field_70989_bE;
   public boolean field_70994_bF;
   private Entity field_70993_bI;
   public int field_70995_bG;
   public EntityEnderCrystal field_70992_bH;

   public EntityDragon(World p_i1700_1_) {
      super(p_i1700_1_);
      this.field_70977_g = new EntityDragonPart[]{this.field_70986_h = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.field_70987_i = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.field_70985_j = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70984_by = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70982_bz = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.field_70983_bA = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.field_70990_bB = new EntityDragonPart(this, "wing", 4.0F, 4.0F)};
      this.func_70606_j(this.func_110138_aP());
      this.func_70105_a(16.0F, 8.0F);
      this.field_70145_X = true;
      this.field_70178_ae = true;
      this.field_70981_c = 100.0D;
      this.field_70158_ak = true;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
   }

   protected void func_70088_a() {
      super.func_70088_a();
   }

   public double[] func_70974_a(int p_70974_1_, float p_70974_2_) {
      if(this.func_110143_aJ() <= 0.0F) {
         p_70974_2_ = 0.0F;
      }

      p_70974_2_ = 1.0F - p_70974_2_;
      int i = this.field_70976_f - p_70974_1_ * 1 & 63;
      int j = this.field_70976_f - p_70974_1_ * 1 - 1 & 63;
      double[] adouble = new double[3];
      double d0 = this.field_70979_e[i][0];
      double d1 = MathHelper.func_76138_g(this.field_70979_e[j][0] - d0);
      adouble[0] = d0 + d1 * (double)p_70974_2_;
      d0 = this.field_70979_e[i][1];
      d1 = this.field_70979_e[j][1] - d0;
      adouble[1] = d0 + d1 * (double)p_70974_2_;
      adouble[2] = this.field_70979_e[i][2] + (this.field_70979_e[j][2] - this.field_70979_e[i][2]) * (double)p_70974_2_;
      return adouble;
   }

   public void func_70636_d() {
      if(this.field_70170_p.field_72995_K) {
         float f = MathHelper.func_76134_b(this.field_70988_bD * 3.1415927F * 2.0F);
         float f1 = MathHelper.func_76134_b(this.field_70991_bC * 3.1415927F * 2.0F);
         if(f1 <= -0.3F && f >= -0.3F && !this.func_174814_R()) {
            this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "mob.enderdragon.wings", 5.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.3F, false);
         }
      }

      this.field_70991_bC = this.field_70988_bD;
      if(this.func_110143_aJ() <= 0.0F) {
         float f11 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         float f13 = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
         float f14 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.field_70165_t + (double)f11, this.field_70163_u + 2.0D + (double)f13, this.field_70161_v + (double)f14, 0.0D, 0.0D, 0.0D, new int[0]);
      } else {
         this.func_70969_j();
         float f10 = 0.2F / (MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 10.0F + 1.0F);
         f10 = f10 * (float)Math.pow(2.0D, this.field_70181_x);
         if(this.field_70994_bF) {
            this.field_70988_bD += f10 * 0.5F;
         } else {
            this.field_70988_bD += f10;
         }

         this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
         if(this.func_175446_cd()) {
            this.field_70988_bD = 0.5F;
         } else {
            if(this.field_70976_f < 0) {
               for(int i = 0; i < this.field_70979_e.length; ++i) {
                  this.field_70979_e[i][0] = (double)this.field_70177_z;
                  this.field_70979_e[i][1] = this.field_70163_u;
               }
            }

            if(++this.field_70976_f == this.field_70979_e.length) {
               this.field_70976_f = 0;
            }

            this.field_70979_e[this.field_70976_f][0] = (double)this.field_70177_z;
            this.field_70979_e[this.field_70976_f][1] = this.field_70163_u;
            if(this.field_70170_p.field_72995_K) {
               if(this.field_70716_bi > 0) {
                  double d10 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / (double)this.field_70716_bi;
                  double d0 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / (double)this.field_70716_bi;
                  double d1 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / (double)this.field_70716_bi;
                  double d2 = MathHelper.func_76138_g(this.field_70712_bm - (double)this.field_70177_z);
                  this.field_70177_z = (float)((double)this.field_70177_z + d2 / (double)this.field_70716_bi);
                  this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70705_bn - (double)this.field_70125_A) / (double)this.field_70716_bi);
                  --this.field_70716_bi;
                  this.func_70107_b(d10, d0, d1);
                  this.func_70101_b(this.field_70177_z, this.field_70125_A);
               }
            } else {
               double d11 = this.field_70980_b - this.field_70165_t;
               double d12 = this.field_70981_c - this.field_70163_u;
               double d13 = this.field_70978_d - this.field_70161_v;
               double d14 = d11 * d11 + d12 * d12 + d13 * d13;
               if(this.field_70993_bI != null) {
                  this.field_70980_b = this.field_70993_bI.field_70165_t;
                  this.field_70978_d = this.field_70993_bI.field_70161_v;
                  double d3 = this.field_70980_b - this.field_70165_t;
                  double d5 = this.field_70978_d - this.field_70161_v;
                  double d7 = Math.sqrt(d3 * d3 + d5 * d5);
                  double d8 = 0.4000000059604645D + d7 / 80.0D - 1.0D;
                  if(d8 > 10.0D) {
                     d8 = 10.0D;
                  }

                  this.field_70981_c = this.field_70993_bI.func_174813_aQ().field_72338_b + d8;
               } else {
                  this.field_70980_b += this.field_70146_Z.nextGaussian() * 2.0D;
                  this.field_70978_d += this.field_70146_Z.nextGaussian() * 2.0D;
               }

               if(this.field_70989_bE || d14 < 100.0D || d14 > 22500.0D || this.field_70123_F || this.field_70124_G) {
                  this.func_70967_k();
               }

               d12 = d12 / (double)MathHelper.func_76133_a(d11 * d11 + d13 * d13);
               float f17 = 0.6F;
               d12 = MathHelper.func_151237_a(d12, (double)(-f17), (double)f17);
               this.field_70181_x += d12 * 0.10000000149011612D;
               this.field_70177_z = MathHelper.func_76142_g(this.field_70177_z);
               double d4 = 180.0D - MathHelper.func_181159_b(d11, d13) * 180.0D / 3.1415927410125732D;
               double d6 = MathHelper.func_76138_g(d4 - (double)this.field_70177_z);
               if(d6 > 50.0D) {
                  d6 = 50.0D;
               }

               if(d6 < -50.0D) {
                  d6 = -50.0D;
               }

               Vec3 vec3 = (new Vec3(this.field_70980_b - this.field_70165_t, this.field_70981_c - this.field_70163_u, this.field_70978_d - this.field_70161_v)).func_72432_b();
               double d15 = (double)(-MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F));
               Vec3 vec31 = (new Vec3((double)MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F), this.field_70181_x, d15)).func_72432_b();
               float f5 = ((float)vec31.func_72430_b(vec3) + 0.5F) / 1.5F;
               if(f5 < 0.0F) {
                  f5 = 0.0F;
               }

               this.field_70704_bt *= 0.8F;
               float f6 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0F + 1.0F;
               double d9 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y) * 1.0D + 1.0D;
               if(d9 > 40.0D) {
                  d9 = 40.0D;
               }

               this.field_70704_bt = (float)((double)this.field_70704_bt + d6 * (0.699999988079071D / d9 / (double)f6));
               this.field_70177_z += this.field_70704_bt * 0.1F;
               float f7 = (float)(2.0D / (d9 + 1.0D));
               float f8 = 0.06F;
               this.func_70060_a(0.0F, -1.0F, f8 * (f5 * f7 + (1.0F - f7)));
               if(this.field_70994_bF) {
                  this.func_70091_d(this.field_70159_w * 0.800000011920929D, this.field_70181_x * 0.800000011920929D, this.field_70179_y * 0.800000011920929D);
               } else {
                  this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
               }

               Vec3 vec32 = (new Vec3(this.field_70159_w, this.field_70181_x, this.field_70179_y)).func_72432_b();
               float f9 = ((float)vec32.func_72430_b(vec31) + 1.0F) / 2.0F;
               f9 = 0.8F + 0.15F * f9;
               this.field_70159_w *= (double)f9;
               this.field_70179_y *= (double)f9;
               this.field_70181_x *= 0.9100000262260437D;
            }

            this.field_70761_aq = this.field_70177_z;
            this.field_70986_h.field_70130_N = this.field_70986_h.field_70131_O = 3.0F;
            this.field_70985_j.field_70130_N = this.field_70985_j.field_70131_O = 2.0F;
            this.field_70984_by.field_70130_N = this.field_70984_by.field_70131_O = 2.0F;
            this.field_70982_bz.field_70130_N = this.field_70982_bz.field_70131_O = 2.0F;
            this.field_70987_i.field_70131_O = 3.0F;
            this.field_70987_i.field_70130_N = 5.0F;
            this.field_70983_bA.field_70131_O = 2.0F;
            this.field_70983_bA.field_70130_N = 4.0F;
            this.field_70990_bB.field_70131_O = 3.0F;
            this.field_70990_bB.field_70130_N = 4.0F;
            float f12 = (float)(this.func_70974_a(5, 1.0F)[1] - this.func_70974_a(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
            float f2 = MathHelper.func_76134_b(f12);
            float f15 = -MathHelper.func_76126_a(f12);
            float f3 = this.field_70177_z * 3.1415927F / 180.0F;
            float f16 = MathHelper.func_76126_a(f3);
            float f4 = MathHelper.func_76134_b(f3);
            this.field_70987_i.func_70071_h_();
            this.field_70987_i.func_70012_b(this.field_70165_t + (double)(f16 * 0.5F), this.field_70163_u, this.field_70161_v - (double)(f4 * 0.5F), 0.0F, 0.0F);
            this.field_70983_bA.func_70071_h_();
            this.field_70983_bA.func_70012_b(this.field_70165_t + (double)(f4 * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v + (double)(f16 * 4.5F), 0.0F, 0.0F);
            this.field_70990_bB.func_70071_h_();
            this.field_70990_bB.func_70012_b(this.field_70165_t - (double)(f4 * 4.5F), this.field_70163_u + 2.0D, this.field_70161_v - (double)(f16 * 4.5F), 0.0F, 0.0F);
            if(!this.field_70170_p.field_72995_K && this.field_70737_aN == 0) {
               this.func_70970_a(this.field_70170_p.func_72839_b(this, this.field_70983_bA.func_174813_aQ().func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
               this.func_70970_a(this.field_70170_p.func_72839_b(this, this.field_70990_bB.func_174813_aQ().func_72314_b(4.0D, 2.0D, 4.0D).func_72317_d(0.0D, -2.0D, 0.0D)));
               this.func_70971_b(this.field_70170_p.func_72839_b(this, this.field_70986_h.func_174813_aQ().func_72314_b(1.0D, 1.0D, 1.0D)));
            }

            double[] adouble1 = this.func_70974_a(5, 1.0F);
            double[] adouble = this.func_70974_a(0, 1.0F);
            float f18 = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F - this.field_70704_bt * 0.01F);
            float f19 = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F - this.field_70704_bt * 0.01F);
            this.field_70986_h.func_70071_h_();
            this.field_70986_h.func_70012_b(this.field_70165_t + (double)(f18 * 5.5F * f2), this.field_70163_u + (adouble[1] - adouble1[1]) * 1.0D + (double)(f15 * 5.5F), this.field_70161_v - (double)(f19 * 5.5F * f2), 0.0F, 0.0F);

            for(int j = 0; j < 3; ++j) {
               EntityDragonPart entitydragonpart = null;
               if(j == 0) {
                  entitydragonpart = this.field_70985_j;
               }

               if(j == 1) {
                  entitydragonpart = this.field_70984_by;
               }

               if(j == 2) {
                  entitydragonpart = this.field_70982_bz;
               }

               double[] adouble2 = this.func_70974_a(12 + j * 2, 1.0F);
               float f20 = this.field_70177_z * 3.1415927F / 180.0F + this.func_70973_b(adouble2[0] - adouble1[0]) * 3.1415927F / 180.0F * 1.0F;
               float f21 = MathHelper.func_76126_a(f20);
               float f22 = MathHelper.func_76134_b(f20);
               float f23 = 1.5F;
               float f24 = (float)(j + 1) * 2.0F;
               entitydragonpart.func_70071_h_();
               entitydragonpart.func_70012_b(this.field_70165_t - (double)((f16 * f23 + f21 * f24) * f2), this.field_70163_u + (adouble2[1] - adouble1[1]) * 1.0D - (double)((f24 + f23) * f15) + 1.5D, this.field_70161_v + (double)((f4 * f23 + f22 * f24) * f2), 0.0F, 0.0F);
            }

            if(!this.field_70170_p.field_72995_K) {
               this.field_70994_bF = this.func_70972_a(this.field_70986_h.func_174813_aQ()) | this.func_70972_a(this.field_70987_i.func_174813_aQ());
            }

         }
      }
   }

   private void func_70969_j() {
      if(this.field_70992_bH != null) {
         if(this.field_70992_bH.field_70128_L) {
            if(!this.field_70170_p.field_72995_K) {
               this.func_70965_a(this.field_70986_h, DamageSource.func_94539_a((Explosion)null), 10.0F);
            }

            this.field_70992_bH = null;
         } else if(this.field_70173_aa % 10 == 0 && this.func_110143_aJ() < this.func_110138_aP()) {
            this.func_70606_j(this.func_110143_aJ() + 1.0F);
         }
      }

      if(this.field_70146_Z.nextInt(10) == 0) {
         float f = 32.0F;
         List<EntityEnderCrystal> list = this.field_70170_p.<EntityEnderCrystal>func_72872_a(EntityEnderCrystal.class, this.func_174813_aQ().func_72314_b((double)f, (double)f, (double)f));
         EntityEnderCrystal entityendercrystal = null;
         double d0 = Double.MAX_VALUE;

         for(EntityEnderCrystal entityendercrystal1 : list) {
            double d1 = entityendercrystal1.func_70068_e(this);
            if(d1 < d0) {
               d0 = d1;
               entityendercrystal = entityendercrystal1;
            }
         }

         this.field_70992_bH = entityendercrystal;
      }

   }

   private void func_70970_a(List<Entity> p_70970_1_) {
      double d0 = (this.field_70987_i.func_174813_aQ().field_72340_a + this.field_70987_i.func_174813_aQ().field_72336_d) / 2.0D;
      double d1 = (this.field_70987_i.func_174813_aQ().field_72339_c + this.field_70987_i.func_174813_aQ().field_72334_f) / 2.0D;

      for(Entity entity : p_70970_1_) {
         if(entity instanceof EntityLivingBase) {
            double d2 = entity.field_70165_t - d0;
            double d3 = entity.field_70161_v - d1;
            double d4 = d2 * d2 + d3 * d3;
            entity.func_70024_g(d2 / d4 * 4.0D, 0.20000000298023224D, d3 / d4 * 4.0D);
         }
      }

   }

   private void func_70971_b(List<Entity> p_70971_1_) {
      for(int i = 0; i < p_70971_1_.size(); ++i) {
         Entity entity = (Entity)p_70971_1_.get(i);
         if(entity instanceof EntityLivingBase) {
            entity.func_70097_a(DamageSource.func_76358_a(this), 10.0F);
            this.func_174815_a(this, entity);
         }
      }

   }

   private void func_70967_k() {
      this.field_70989_bE = false;
      List<EntityPlayer> list = Lists.newArrayList(this.field_70170_p.field_73010_i);
      Iterator<EntityPlayer> iterator = list.iterator();

      while(iterator.hasNext()) {
         if(((EntityPlayer)iterator.next()).func_175149_v()) {
            iterator.remove();
         }
      }

      if(this.field_70146_Z.nextInt(2) == 0 && !list.isEmpty()) {
         this.field_70993_bI = (Entity)list.get(this.field_70146_Z.nextInt(list.size()));
      } else {
         while(true) {
            this.field_70980_b = 0.0D;
            this.field_70981_c = (double)(70.0F + this.field_70146_Z.nextFloat() * 50.0F);
            this.field_70978_d = 0.0D;
            this.field_70980_b += (double)(this.field_70146_Z.nextFloat() * 120.0F - 60.0F);
            this.field_70978_d += (double)(this.field_70146_Z.nextFloat() * 120.0F - 60.0F);
            double d0 = this.field_70165_t - this.field_70980_b;
            double d1 = this.field_70163_u - this.field_70981_c;
            double d2 = this.field_70161_v - this.field_70978_d;
            boolean flag = d0 * d0 + d1 * d1 + d2 * d2 > 100.0D;
            if(flag) {
               break;
            }
         }

         this.field_70993_bI = null;
      }

   }

   private float func_70973_b(double p_70973_1_) {
      return (float)MathHelper.func_76138_g(p_70973_1_);
   }

   private boolean func_70972_a(AxisAlignedBB p_70972_1_) {
      int i = MathHelper.func_76128_c(p_70972_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_70972_1_.field_72338_b);
      int k = MathHelper.func_76128_c(p_70972_1_.field_72339_c);
      int l = MathHelper.func_76128_c(p_70972_1_.field_72336_d);
      int i1 = MathHelper.func_76128_c(p_70972_1_.field_72337_e);
      int j1 = MathHelper.func_76128_c(p_70972_1_.field_72334_f);
      boolean flag = false;
      boolean flag1 = false;

      for(int k1 = i; k1 <= l; ++k1) {
         for(int l1 = j; l1 <= i1; ++l1) {
            for(int i2 = k; i2 <= j1; ++i2) {
               BlockPos blockpos = new BlockPos(k1, l1, i2);
               Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
               if(block.func_149688_o() != Material.field_151579_a) {
                  if(block != Blocks.field_180401_cv && block != Blocks.field_150343_Z && block != Blocks.field_150377_bs && block != Blocks.field_150357_h && block != Blocks.field_150483_bI && this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
                     flag1 = this.field_70170_p.func_175698_g(blockpos) || flag1;
                  } else {
                     flag = true;
                  }
               }
            }
         }
      }

      if(flag1) {
         double d0 = p_70972_1_.field_72340_a + (p_70972_1_.field_72336_d - p_70972_1_.field_72340_a) * (double)this.field_70146_Z.nextFloat();
         double d1 = p_70972_1_.field_72338_b + (p_70972_1_.field_72337_e - p_70972_1_.field_72338_b) * (double)this.field_70146_Z.nextFloat();
         double d2 = p_70972_1_.field_72339_c + (p_70972_1_.field_72334_f - p_70972_1_.field_72339_c) * (double)this.field_70146_Z.nextFloat();
         this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
      }

      return flag;
   }

   public boolean func_70965_a(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_) {
      if(p_70965_1_ != this.field_70986_h) {
         p_70965_3_ = p_70965_3_ / 4.0F + 1.0F;
      }

      float f = this.field_70177_z * 3.1415927F / 180.0F;
      float f1 = MathHelper.func_76126_a(f);
      float f2 = MathHelper.func_76134_b(f);
      this.field_70980_b = this.field_70165_t + (double)(f1 * 5.0F) + (double)((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
      this.field_70981_c = this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * 3.0F) + 1.0D;
      this.field_70978_d = this.field_70161_v - (double)(f2 * 5.0F) + (double)((this.field_70146_Z.nextFloat() - 0.5F) * 2.0F);
      this.field_70993_bI = null;
      if(p_70965_2_.func_76346_g() instanceof EntityPlayer || p_70965_2_.func_94541_c()) {
         this.func_82195_e(p_70965_2_, p_70965_3_);
      }

      return true;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(p_70097_1_ instanceof EntityDamageSource && ((EntityDamageSource)p_70097_1_).func_180139_w()) {
         this.func_82195_e(p_70097_1_, p_70097_2_);
      }

      return false;
   }

   protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_) {
      return super.func_70097_a(p_82195_1_, p_82195_2_);
   }

   public void func_174812_G() {
      this.func_70106_y();
   }

   protected void func_70609_aI() {
      ++this.field_70995_bG;
      if(this.field_70995_bG >= 180 && this.field_70995_bG <= 200) {
         float f = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 4.0F;
         float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 8.0F;
         this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, this.field_70165_t + (double)f, this.field_70163_u + 2.0D + (double)f1, this.field_70161_v + (double)f2, 0.0D, 0.0D, 0.0D, new int[0]);
      }

      boolean flag = this.field_70170_p.func_82736_K().func_82766_b("doMobLoot");
      if(!this.field_70170_p.field_72995_K) {
         if(this.field_70995_bG > 150 && this.field_70995_bG % 5 == 0 && flag) {
            int i = 1000;

            while(i > 0) {
               int k = EntityXPOrb.func_70527_a(i);
               i -= k;
               this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, k));
            }
         }

         if(this.field_70995_bG == 1) {
            this.field_70170_p.func_175669_a(1018, new BlockPos(this), 0);
         }
      }

      this.func_70091_d(0.0D, 0.10000000149011612D, 0.0D);
      this.field_70761_aq = this.field_70177_z += 20.0F;
      if(this.field_70995_bG == 200 && !this.field_70170_p.field_72995_K) {
         if(flag) {
            int j = 2000;

            while(j > 0) {
               int l = EntityXPOrb.func_70527_a(j);
               j -= l;
               this.field_70170_p.func_72838_d(new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, l));
            }
         }

         this.func_175499_a(new BlockPos(this.field_70165_t, 64.0D, this.field_70161_v));
         this.func_70106_y();
      }

   }

   private void func_175499_a(BlockPos p_175499_1_) {
      int i = 4;
      double d0 = 12.25D;
      double d1 = 6.25D;

      for(int j = -1; j <= 32; ++j) {
         for(int k = -4; k <= 4; ++k) {
            for(int l = -4; l <= 4; ++l) {
               double d2 = (double)(k * k + l * l);
               if(d2 <= 12.25D) {
                  BlockPos blockpos = p_175499_1_.func_177982_a(k, j, l);
                  if(j < 0) {
                     if(d2 <= 6.25D) {
                        this.field_70170_p.func_175656_a(blockpos, Blocks.field_150357_h.func_176223_P());
                     }
                  } else if(j > 0) {
                     this.field_70170_p.func_175656_a(blockpos, Blocks.field_150350_a.func_176223_P());
                  } else if(d2 > 6.25D) {
                     this.field_70170_p.func_175656_a(blockpos, Blocks.field_150357_h.func_176223_P());
                  } else {
                     this.field_70170_p.func_175656_a(blockpos, Blocks.field_150384_bq.func_176223_P());
                  }
               }
            }
         }
      }

      this.field_70170_p.func_175656_a(p_175499_1_, Blocks.field_150357_h.func_176223_P());
      this.field_70170_p.func_175656_a(p_175499_1_.func_177984_a(), Blocks.field_150357_h.func_176223_P());
      BlockPos blockpos1 = p_175499_1_.func_177981_b(2);
      this.field_70170_p.func_175656_a(blockpos1, Blocks.field_150357_h.func_176223_P());
      this.field_70170_p.func_175656_a(blockpos1.func_177976_e(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.EAST));
      this.field_70170_p.func_175656_a(blockpos1.func_177974_f(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.WEST));
      this.field_70170_p.func_175656_a(blockpos1.func_177978_c(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.SOUTH));
      this.field_70170_p.func_175656_a(blockpos1.func_177968_d(), Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.NORTH));
      this.field_70170_p.func_175656_a(p_175499_1_.func_177981_b(3), Blocks.field_150357_h.func_176223_P());
      this.field_70170_p.func_175656_a(p_175499_1_.func_177981_b(4), Blocks.field_150380_bt.func_176223_P());
   }

   protected void func_70623_bb() {
   }

   public Entity[] func_70021_al() {
      return this.field_70977_g;
   }

   public boolean func_70067_L() {
      return false;
   }

   public World func_82194_d() {
      return this.field_70170_p;
   }

   protected String func_70639_aQ() {
      return "mob.enderdragon.growl";
   }

   protected String func_70621_aR() {
      return "mob.enderdragon.hit";
   }

   protected float func_70599_aP() {
      return 5.0F;
   }
}
