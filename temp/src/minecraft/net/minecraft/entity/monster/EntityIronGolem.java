package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityIronGolem extends EntityGolem {
   private int field_70858_e;
   Village field_70857_d;
   private int field_70855_f;
   private int field_70856_g;

   public EntityIronGolem(World p_i1694_1_) {
      super(p_i1694_1_);
      this.func_70105_a(1.4F, 2.9F);
      ((PathNavigateGround)this.func_70661_as()).func_179690_a(true);
      this.field_70714_bg.func_75776_a(1, new EntityAIAttackOnCollide(this, 1.0D, true));
      this.field_70714_bg.func_75776_a(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
      this.field_70714_bg.func_75776_a(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
      this.field_70714_bg.func_75776_a(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
      this.field_70714_bg.func_75776_a(5, new EntityAILookAtVillager(this));
      this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 0.6D));
      this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIDefendVillage(this));
      this.field_70715_bh.func_75776_a(2, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70715_bh.func_75776_a(3, new EntityIronGolem.AINearestAttackableTargetNonCreeper(this, EntityLiving.class, 10, false, true, IMob.field_175450_e));
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
   }

   protected void func_70619_bc() {
      if(--this.field_70858_e <= 0) {
         this.field_70858_e = 70 + this.field_70146_Z.nextInt(50);
         this.field_70857_d = this.field_70170_p.func_175714_ae().func_176056_a(new BlockPos(this), 32);
         if(this.field_70857_d == null) {
            this.func_110177_bN();
         } else {
            BlockPos blockpos = this.field_70857_d.func_180608_a();
            this.func_175449_a(blockpos, (int)((float)this.field_70857_d.func_75568_b() * 0.6F));
         }
      }

      super.func_70619_bc();
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
   }

   protected int func_70682_h(int p_70682_1_) {
      return p_70682_1_;
   }

   protected void func_82167_n(Entity p_82167_1_) {
      if(p_82167_1_ instanceof IMob && !(p_82167_1_ instanceof EntityCreeper) && this.func_70681_au().nextInt(20) == 0) {
         this.func_70624_b((EntityLivingBase)p_82167_1_);
      }

      super.func_82167_n(p_82167_1_);
   }

   public void func_70636_d() {
      super.func_70636_d();
      if(this.field_70855_f > 0) {
         --this.field_70855_f;
      }

      if(this.field_70856_g > 0) {
         --this.field_70856_g;
      }

      if(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7D && this.field_70146_Z.nextInt(5) == 0) {
         int i = MathHelper.func_76128_c(this.field_70165_t);
         int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D);
         int k = MathHelper.func_76128_c(this.field_70161_v);
         IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos(i, j, k));
         Block block = iblockstate.func_177230_c();
         if(block.func_149688_o() != Material.field_151579_a) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + ((double)this.field_70146_Z.nextFloat() - 0.5D) * (double)this.field_70130_N, this.func_174813_aQ().field_72338_b + 0.1D, this.field_70161_v + ((double)this.field_70146_Z.nextFloat() - 0.5D) * (double)this.field_70130_N, 4.0D * ((double)this.field_70146_Z.nextFloat() - 0.5D), 0.5D, ((double)this.field_70146_Z.nextFloat() - 0.5D) * 4.0D, new int[]{Block.func_176210_f(iblockstate)});
         }
      }

   }

   public boolean func_70686_a(Class<? extends EntityLivingBase> p_70686_1_) {
      return this.func_70850_q() && EntityPlayer.class.isAssignableFrom(p_70686_1_)?false:(p_70686_1_ == EntityCreeper.class?false:super.func_70686_a(p_70686_1_));
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("PlayerCreated", this.func_70850_q());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_70849_f(p_70037_1_.func_74767_n("PlayerCreated"));
   }

   public boolean func_70652_k(Entity p_70652_1_) {
      this.field_70855_f = 10;
      this.field_70170_p.func_72960_a(this, (byte)4);
      boolean flag = p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), (float)(7 + this.field_70146_Z.nextInt(15)));
      if(flag) {
         p_70652_1_.field_70181_x += 0.4000000059604645D;
         this.func_174815_a(this, p_70652_1_);
      }

      this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
      return flag;
   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 4) {
         this.field_70855_f = 10;
         this.func_85030_a("mob.irongolem.throw", 1.0F, 1.0F);
      } else if(p_70103_1_ == 11) {
         this.field_70856_g = 400;
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   public Village func_70852_n() {
      return this.field_70857_d;
   }

   public int func_70854_o() {
      return this.field_70855_f;
   }

   public void func_70851_e(boolean p_70851_1_) {
      this.field_70856_g = p_70851_1_?400:0;
      this.field_70170_p.func_72960_a(this, (byte)11);
   }

   protected String func_70621_aR() {
      return "mob.irongolem.hit";
   }

   protected String func_70673_aS() {
      return "mob.irongolem.death";
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.irongolem.walk", 1.0F, 1.0F);
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      int i = this.field_70146_Z.nextInt(3);

      for(int j = 0; j < i; ++j) {
         this.func_145778_a(Item.func_150898_a(Blocks.field_150328_O), 1, (float)BlockFlower.EnumFlowerType.POPPY.func_176968_b());
      }

      int l = 3 + this.field_70146_Z.nextInt(3);

      for(int k = 0; k < l; ++k) {
         this.func_145779_a(Items.field_151042_j, 1);
      }

   }

   public int func_70853_p() {
      return this.field_70856_g;
   }

   public boolean func_70850_q() {
      return (this.field_70180_af.func_75683_a(16) & 1) != 0;
   }

   public void func_70849_f(boolean p_70849_1_) {
      byte b0 = this.field_70180_af.func_75683_a(16);
      if(p_70849_1_) {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 1)));
      } else {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & -2)));
      }

   }

   public void func_70645_a(DamageSource p_70645_1_) {
      if(!this.func_70850_q() && this.field_70717_bb != null && this.field_70857_d != null) {
         this.field_70857_d.func_82688_a(this.field_70717_bb.func_70005_c_(), -5);
      }

      super.func_70645_a(p_70645_1_);
   }

   static class AINearestAttackableTargetNonCreeper<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
      public AINearestAttackableTargetNonCreeper(final EntityCreature p_i45858_1_, Class<T> p_i45858_2_, int p_i45858_3_, boolean p_i45858_4_, boolean p_i45858_5_, final Predicate<? super T> p_i45858_6_) {
         super(p_i45858_1_, p_i45858_2_, p_i45858_3_, p_i45858_4_, p_i45858_5_, p_i45858_6_);
         this.field_82643_g = new Predicate<T>() {
            public boolean apply(T p_apply_1_) {
               if(p_i45858_6_ != null && !p_i45858_6_.apply(p_apply_1_)) {
                  return false;
               } else if(p_apply_1_ instanceof EntityCreeper) {
                  return false;
               } else {
                  if(p_apply_1_ instanceof EntityPlayer) {
                     double d0 = AINearestAttackableTargetNonCreeper.this.func_111175_f();
                     if(p_apply_1_.func_70093_af()) {
                        d0 *= 0.800000011920929D;
                     }

                     if(p_apply_1_.func_82150_aj()) {
                        float f = ((EntityPlayer)p_apply_1_).func_82243_bO();
                        if(f < 0.1F) {
                           f = 0.1F;
                        }

                        d0 *= (double)(0.7F * f);
                     }

                     if((double)p_apply_1_.func_70032_d(p_i45858_1_) > d0) {
                        return false;
                     }
                  }

                  return AINearestAttackableTargetNonCreeper.this.func_75296_a(p_apply_1_, false);
               }
            }
         };
      }
   }
}
