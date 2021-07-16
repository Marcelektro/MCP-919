package net.minecraft.entity.projectile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityArrow extends Entity implements IProjectile {
   private int field_145791_d = -1;
   private int field_145792_e = -1;
   private int field_145789_f = -1;
   private Block field_145790_g;
   private int field_70253_h;
   private boolean field_70254_i;
   public int field_70251_a;
   public int field_70249_b;
   public Entity field_70250_c;
   private int field_70252_j;
   private int field_70257_an;
   private double field_70255_ao = 2.0D;
   private int field_70256_ap;

   public EntityArrow(World p_i1753_1_) {
      super(p_i1753_1_);
      this.field_70155_l = 10.0D;
      this.func_70105_a(0.5F, 0.5F);
   }

   public EntityArrow(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
      super(p_i1754_1_);
      this.field_70155_l = 10.0D;
      this.func_70105_a(0.5F, 0.5F);
      this.func_70107_b(p_i1754_2_, p_i1754_4_, p_i1754_6_);
   }

   public EntityArrow(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_) {
      super(p_i1755_1_);
      this.field_70155_l = 10.0D;
      this.field_70250_c = p_i1755_2_;
      if(p_i1755_2_ instanceof EntityPlayer) {
         this.field_70251_a = 1;
      }

      this.field_70163_u = p_i1755_2_.field_70163_u + (double)p_i1755_2_.func_70047_e() - 0.10000000149011612D;
      double d0 = p_i1755_3_.field_70165_t - p_i1755_2_.field_70165_t;
      double d1 = p_i1755_3_.func_174813_aQ().field_72338_b + (double)(p_i1755_3_.field_70131_O / 3.0F) - this.field_70163_u;
      double d2 = p_i1755_3_.field_70161_v - p_i1755_2_.field_70161_v;
      double d3 = (double)MathHelper.func_76133_a(d0 * d0 + d2 * d2);
      if(d3 >= 1.0E-7D) {
         float f = (float)(MathHelper.func_181159_b(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
         float f1 = (float)(-(MathHelper.func_181159_b(d1, d3) * 180.0D / 3.1415927410125732D));
         double d4 = d0 / d3;
         double d5 = d2 / d3;
         this.func_70012_b(p_i1755_2_.field_70165_t + d4, this.field_70163_u, p_i1755_2_.field_70161_v + d5, f, f1);
         float f2 = (float)(d3 * 0.20000000298023224D);
         this.func_70186_c(d0, d1 + (double)f2, d2, p_i1755_4_, p_i1755_5_);
      }
   }

   public EntityArrow(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
      super(p_i1756_1_);
      this.field_70155_l = 10.0D;
      this.field_70250_c = p_i1756_2_;
      if(p_i1756_2_ instanceof EntityPlayer) {
         this.field_70251_a = 1;
      }

      this.func_70105_a(0.5F, 0.5F);
      this.func_70012_b(p_i1756_2_.field_70165_t, p_i1756_2_.field_70163_u + (double)p_i1756_2_.func_70047_e(), p_i1756_2_.field_70161_v, p_i1756_2_.field_70177_z, p_i1756_2_.field_70125_A);
      this.field_70165_t -= (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.field_70163_u -= 0.10000000149011612D;
      this.field_70161_v -= (double)(MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
      this.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F));
      this.field_70181_x = (double)(-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F));
      this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, p_i1756_3_ * 1.5F, 1.0F);
   }

   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
   }

   public void func_70186_c(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
      float f = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
      p_70186_1_ = p_70186_1_ / (double)f;
      p_70186_3_ = p_70186_3_ / (double)f;
      p_70186_5_ = p_70186_5_ / (double)f;
      p_70186_1_ = p_70186_1_ + this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean()?-1:1) * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_3_ = p_70186_3_ + this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean()?-1:1) * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_5_ = p_70186_5_ + this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean()?-1:1) * 0.007499999832361937D * (double)p_70186_8_;
      p_70186_1_ = p_70186_1_ * (double)p_70186_7_;
      p_70186_3_ = p_70186_3_ * (double)p_70186_7_;
      p_70186_5_ = p_70186_5_ * (double)p_70186_7_;
      this.field_70159_w = p_70186_1_;
      this.field_70181_x = p_70186_3_;
      this.field_70179_y = p_70186_5_;
      float f1 = MathHelper.func_76133_a(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
      this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_70186_1_, p_70186_5_) * 180.0D / 3.1415927410125732D);
      this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_70186_3_, (double)f1) * 180.0D / 3.1415927410125732D);
      this.field_70252_j = 0;
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
      this.func_70101_b(p_180426_7_, p_180426_8_);
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70159_w = p_70016_1_;
      this.field_70181_x = p_70016_3_;
      this.field_70179_y = p_70016_5_;
      if(this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float f = MathHelper.func_76133_a(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
         this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(p_70016_1_, p_70016_5_) * 180.0D / 3.1415927410125732D);
         this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(p_70016_3_, (double)f) * 180.0D / 3.1415927410125732D);
         this.field_70127_C = this.field_70125_A;
         this.field_70126_B = this.field_70177_z;
         this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
         this.field_70252_j = 0;
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70126_B = this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 180.0D / 3.1415927410125732D);
         this.field_70127_C = this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)f) * 180.0D / 3.1415927410125732D);
      }

      BlockPos blockpos = new BlockPos(this.field_145791_d, this.field_145792_e, this.field_145789_f);
      IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
      Block block = iblockstate.func_177230_c();
      if(block.func_149688_o() != Material.field_151579_a) {
         block.func_180654_a(this.field_70170_p, blockpos);
         AxisAlignedBB axisalignedbb = block.func_180640_a(this.field_70170_p, blockpos, iblockstate);
         if(axisalignedbb != null && axisalignedbb.func_72318_a(new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
            this.field_70254_i = true;
         }
      }

      if(this.field_70249_b > 0) {
         --this.field_70249_b;
      }

      if(this.field_70254_i) {
         int j = block.func_176201_c(iblockstate);
         if(block == this.field_145790_g && j == this.field_70253_h) {
            ++this.field_70252_j;
            if(this.field_70252_j >= 1200) {
               this.func_70106_y();
            }
         } else {
            this.field_70254_i = false;
            this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70252_j = 0;
            this.field_70257_an = 0;
         }

      } else {
         ++this.field_70257_an;
         Vec3 vec31 = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         Vec3 vec3 = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec31, vec3, false, true, false);
         vec31 = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         vec3 = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         if(movingobjectposition != null) {
            vec3 = new Vec3(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
         }

         Entity entity = null;
         List<Entity> list = this.field_70170_p.func_72839_b(this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
         double d0 = 0.0D;

         for(int i = 0; i < list.size(); ++i) {
            Entity entity1 = (Entity)list.get(i);
            if(entity1.func_70067_L() && (entity1 != this.field_70250_c || this.field_70257_an >= 5)) {
               float f1 = 0.3F;
               AxisAlignedBB axisalignedbb1 = entity1.func_174813_aQ().func_72314_b((double)f1, (double)f1, (double)f1);
               MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec31, vec3);
               if(movingobjectposition1 != null) {
                  double d1 = vec31.func_72436_e(movingobjectposition1.field_72307_f);
                  if(d1 < d0 || d0 == 0.0D) {
                     entity = entity1;
                     d0 = d1;
                  }
               }
            }
         }

         if(entity != null) {
            movingobjectposition = new MovingObjectPosition(entity);
         }

         if(movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
            if(entityplayer.field_71075_bZ.field_75102_a || this.field_70250_c instanceof EntityPlayer && !((EntityPlayer)this.field_70250_c).func_96122_a(entityplayer)) {
               movingobjectposition = null;
            }
         }

         if(movingobjectposition != null) {
            if(movingobjectposition.field_72308_g != null) {
               float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
               int l = MathHelper.func_76143_f((double)f2 * this.field_70255_ao);
               if(this.func_70241_g()) {
                  l += this.field_70146_Z.nextInt(l / 2 + 2);
               }

               DamageSource damagesource;
               if(this.field_70250_c == null) {
                  damagesource = DamageSource.func_76353_a(this, this);
               } else {
                  damagesource = DamageSource.func_76353_a(this, this.field_70250_c);
               }

               if(this.func_70027_ad() && !(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
                  movingobjectposition.field_72308_g.func_70015_d(5);
               }

               if(movingobjectposition.field_72308_g.func_70097_a(damagesource, (float)l)) {
                  if(movingobjectposition.field_72308_g instanceof EntityLivingBase) {
                     EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
                     if(!this.field_70170_p.field_72995_K) {
                        entitylivingbase.func_85034_r(entitylivingbase.func_85035_bI() + 1);
                     }

                     if(this.field_70256_ap > 0) {
                        float f7 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                        if(f7 > 0.0F) {
                           movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * (double)this.field_70256_ap * 0.6000000238418579D / (double)f7, 0.1D, this.field_70179_y * (double)this.field_70256_ap * 0.6000000238418579D / (double)f7);
                        }
                     }

                     if(this.field_70250_c instanceof EntityLivingBase) {
                        EnchantmentHelper.func_151384_a(entitylivingbase, this.field_70250_c);
                        EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, entitylivingbase);
                     }

                     if(this.field_70250_c != null && movingobjectposition.field_72308_g != this.field_70250_c && movingobjectposition.field_72308_g instanceof EntityPlayer && this.field_70250_c instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a(new S2BPacketChangeGameState(6, 0.0F));
                     }
                  }

                  this.func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
                  if(!(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
                     this.func_70106_y();
                  }
               } else {
                  this.field_70159_w *= -0.10000000149011612D;
                  this.field_70181_x *= -0.10000000149011612D;
                  this.field_70179_y *= -0.10000000149011612D;
                  this.field_70177_z += 180.0F;
                  this.field_70126_B += 180.0F;
                  this.field_70257_an = 0;
               }
            } else {
               BlockPos blockpos1 = movingobjectposition.func_178782_a();
               this.field_145791_d = blockpos1.func_177958_n();
               this.field_145792_e = blockpos1.func_177956_o();
               this.field_145789_f = blockpos1.func_177952_p();
               IBlockState iblockstate1 = this.field_70170_p.func_180495_p(blockpos1);
               this.field_145790_g = iblockstate1.func_177230_c();
               this.field_70253_h = this.field_145790_g.func_176201_c(iblockstate1);
               this.field_70159_w = (double)((float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t));
               this.field_70181_x = (double)((float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u));
               this.field_70179_y = (double)((float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v));
               float f5 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
               this.field_70165_t -= this.field_70159_w / (double)f5 * 0.05000000074505806D;
               this.field_70163_u -= this.field_70181_x / (double)f5 * 0.05000000074505806D;
               this.field_70161_v -= this.field_70179_y / (double)f5 * 0.05000000074505806D;
               this.func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
               this.field_70254_i = true;
               this.field_70249_b = 7;
               this.func_70243_d(false);
               if(this.field_145790_g.func_149688_o() != Material.field_151579_a) {
                  this.field_145790_g.func_180634_a(this.field_70170_p, blockpos1, iblockstate1, this);
               }
            }
         }

         if(this.func_70241_g()) {
            for(int k = 0; k < 4; ++k) {
               this.field_70170_p.func_175688_a(EnumParticleTypes.CRIT, this.field_70165_t + this.field_70159_w * (double)k / 4.0D, this.field_70163_u + this.field_70181_x * (double)k / 4.0D, this.field_70161_v + this.field_70179_y * (double)k / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y, new int[0]);
            }
         }

         this.field_70165_t += this.field_70159_w;
         this.field_70163_u += this.field_70181_x;
         this.field_70161_v += this.field_70179_y;
         float f3 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 180.0D / 3.1415927410125732D);

         for(this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)f3) * 180.0D / 3.1415927410125732D); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F) {
            ;
         }

         while(this.field_70125_A - this.field_70127_C >= 180.0F) {
            this.field_70127_C += 360.0F;
         }

         while(this.field_70177_z - this.field_70126_B < -180.0F) {
            this.field_70126_B -= 360.0F;
         }

         while(this.field_70177_z - this.field_70126_B >= 180.0F) {
            this.field_70126_B += 360.0F;
         }

         this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
         this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
         float f4 = 0.99F;
         float f6 = 0.05F;
         if(this.func_70090_H()) {
            for(int i1 = 0; i1 < 4; ++i1) {
               float f8 = 0.25F;
               this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * (double)f8, this.field_70163_u - this.field_70181_x * (double)f8, this.field_70161_v - this.field_70179_y * (double)f8, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
            }

            f4 = 0.6F;
         }

         if(this.func_70026_G()) {
            this.func_70066_B();
         }

         this.field_70159_w *= (double)f4;
         this.field_70181_x *= (double)f4;
         this.field_70179_y *= (double)f4;
         this.field_70181_x -= (double)f6;
         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.func_145775_I();
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.func_74777_a("xTile", (short)this.field_145791_d);
      p_70014_1_.func_74777_a("yTile", (short)this.field_145792_e);
      p_70014_1_.func_74777_a("zTile", (short)this.field_145789_f);
      p_70014_1_.func_74777_a("life", (short)this.field_70252_j);
      ResourceLocation resourcelocation = (ResourceLocation)Block.field_149771_c.func_177774_c(this.field_145790_g);
      p_70014_1_.func_74778_a("inTile", resourcelocation == null?"":resourcelocation.toString());
      p_70014_1_.func_74774_a("inData", (byte)this.field_70253_h);
      p_70014_1_.func_74774_a("shake", (byte)this.field_70249_b);
      p_70014_1_.func_74774_a("inGround", (byte)(this.field_70254_i?1:0));
      p_70014_1_.func_74774_a("pickup", (byte)this.field_70251_a);
      p_70014_1_.func_74780_a("damage", this.field_70255_ao);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_145791_d = p_70037_1_.func_74765_d("xTile");
      this.field_145792_e = p_70037_1_.func_74765_d("yTile");
      this.field_145789_f = p_70037_1_.func_74765_d("zTile");
      this.field_70252_j = p_70037_1_.func_74765_d("life");
      if(p_70037_1_.func_150297_b("inTile", 8)) {
         this.field_145790_g = Block.func_149684_b(p_70037_1_.func_74779_i("inTile"));
      } else {
         this.field_145790_g = Block.func_149729_e(p_70037_1_.func_74771_c("inTile") & 255);
      }

      this.field_70253_h = p_70037_1_.func_74771_c("inData") & 255;
      this.field_70249_b = p_70037_1_.func_74771_c("shake") & 255;
      this.field_70254_i = p_70037_1_.func_74771_c("inGround") == 1;
      if(p_70037_1_.func_150297_b("damage", 99)) {
         this.field_70255_ao = p_70037_1_.func_74769_h("damage");
      }

      if(p_70037_1_.func_150297_b("pickup", 99)) {
         this.field_70251_a = p_70037_1_.func_74771_c("pickup");
      } else if(p_70037_1_.func_150297_b("player", 99)) {
         this.field_70251_a = p_70037_1_.func_74767_n("player")?1:0;
      }

   }

   public void func_70100_b_(EntityPlayer p_70100_1_) {
      if(!this.field_70170_p.field_72995_K && this.field_70254_i && this.field_70249_b <= 0) {
         boolean flag = this.field_70251_a == 1 || this.field_70251_a == 2 && p_70100_1_.field_71075_bZ.field_75098_d;
         if(this.field_70251_a == 1 && !p_70100_1_.field_71071_by.func_70441_a(new ItemStack(Items.field_151032_g, 1))) {
            flag = false;
         }

         if(flag) {
            this.func_85030_a("random.pop", 0.2F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            p_70100_1_.func_71001_a(this, 1);
            this.func_70106_y();
         }

      }
   }

   protected boolean func_70041_e_() {
      return false;
   }

   public void func_70239_b(double p_70239_1_) {
      this.field_70255_ao = p_70239_1_;
   }

   public double func_70242_d() {
      return this.field_70255_ao;
   }

   public void func_70240_a(int p_70240_1_) {
      this.field_70256_ap = p_70240_1_;
   }

   public boolean func_70075_an() {
      return false;
   }

   public float func_70047_e() {
      return 0.0F;
   }

   public void func_70243_d(boolean p_70243_1_) {
      byte b0 = this.field_70180_af.func_75683_a(16);
      if(p_70243_1_) {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 1)));
      } else {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & -2)));
      }

   }

   public boolean func_70241_g() {
      byte b0 = this.field_70180_af.func_75683_a(16);
      return (b0 & 1) != 0;
   }
}
