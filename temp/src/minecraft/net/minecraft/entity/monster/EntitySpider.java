package net.minecraft.entity.monster;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpider extends EntityMob {
   public EntitySpider(World p_i1743_1_) {
      super(p_i1743_1_);
      this.func_70105_a(1.4F, 0.9F);
      this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(3, new EntityAILeapAtTarget(this, 0.4F));
      this.field_70714_bg.func_75776_a(4, new EntitySpider.AISpiderAttack(this, EntityPlayer.class));
      this.field_70714_bg.func_75776_a(4, new EntitySpider.AISpiderAttack(this, EntityIronGolem.class));
      this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 0.8D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70715_bh.func_75776_a(2, new EntitySpider.AISpiderTarget(this, EntityPlayer.class));
      this.field_70715_bh.func_75776_a(3, new EntitySpider.AISpiderTarget(this, EntityIronGolem.class));
   }

   public double func_70042_X() {
      return (double)(this.field_70131_O * 0.5F);
   }

   protected PathNavigate func_175447_b(World p_175447_1_) {
      return new PathNavigateClimber(this, p_175447_1_);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, new Byte((byte)0));
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(!this.field_70170_p.field_72995_K) {
         this.func_70839_e(this.field_70123_F);
      }

   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(16.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
   }

   protected String func_70639_aQ() {
      return "mob.spider.say";
   }

   protected String func_70621_aR() {
      return "mob.spider.say";
   }

   protected String func_70673_aS() {
      return "mob.spider.death";
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.spider.step", 0.15F, 1.0F);
   }

   protected Item func_146068_u() {
      return Items.field_151007_F;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      super.func_70628_a(p_70628_1_, p_70628_2_);
      if(p_70628_1_ && (this.field_70146_Z.nextInt(3) == 0 || this.field_70146_Z.nextInt(1 + p_70628_2_) > 0)) {
         this.func_145779_a(Items.field_151070_bp, 1);
      }

   }

   public boolean func_70617_f_() {
      return this.func_70841_p();
   }

   public void func_70110_aj() {
   }

   public EnumCreatureAttribute func_70668_bt() {
      return EnumCreatureAttribute.ARTHROPOD;
   }

   public boolean func_70687_e(PotionEffect p_70687_1_) {
      return p_70687_1_.func_76456_a() == Potion.field_76436_u.field_76415_H?false:super.func_70687_e(p_70687_1_);
   }

   public boolean func_70841_p() {
      return (this.field_70180_af.func_75683_a(16) & 1) != 0;
   }

   public void func_70839_e(boolean p_70839_1_) {
      byte b0 = this.field_70180_af.func_75683_a(16);
      if(p_70839_1_) {
         b0 = (byte)(b0 | 1);
      } else {
         b0 = (byte)(b0 & -2);
      }

      this.field_70180_af.func_75692_b(16, Byte.valueOf(b0));
   }

   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
      p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
      if(this.field_70170_p.field_73012_v.nextInt(100) == 0) {
         EntitySkeleton entityskeleton = new EntitySkeleton(this.field_70170_p);
         entityskeleton.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0F);
         entityskeleton.func_180482_a(p_180482_1_, (IEntityLivingData)null);
         this.field_70170_p.func_72838_d(entityskeleton);
         entityskeleton.func_70078_a(this);
      }

      if(p_180482_2_ == null) {
         p_180482_2_ = new EntitySpider.GroupData();
         if(this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD && this.field_70170_p.field_73012_v.nextFloat() < 0.1F * p_180482_1_.func_180170_c()) {
            ((EntitySpider.GroupData)p_180482_2_).func_111104_a(this.field_70170_p.field_73012_v);
         }
      }

      if(p_180482_2_ instanceof EntitySpider.GroupData) {
         int i = ((EntitySpider.GroupData)p_180482_2_).field_111105_a;
         if(i > 0 && Potion.field_76425_a[i] != null) {
            this.func_70690_d(new PotionEffect(i, Integer.MAX_VALUE));
         }
      }

      return p_180482_2_;
   }

   public float func_70047_e() {
      return 0.65F;
   }

   static class AISpiderAttack extends EntityAIAttackOnCollide {
      public AISpiderAttack(EntitySpider p_i45819_1_, Class<? extends Entity> p_i45819_2_) {
         super(p_i45819_1_, p_i45819_2_, 1.0D, true);
      }

      public boolean func_75253_b() {
         float f = this.field_75441_b.func_70013_c(1.0F);
         if(f >= 0.5F && this.field_75441_b.func_70681_au().nextInt(100) == 0) {
            this.field_75441_b.func_70624_b((EntityLivingBase)null);
            return false;
         } else {
            return super.func_75253_b();
         }
      }

      protected double func_179512_a(EntityLivingBase p_179512_1_) {
         return (double)(4.0F + p_179512_1_.field_70130_N);
      }
   }

   static class AISpiderTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {
      public AISpiderTarget(EntitySpider p_i45818_1_, Class<T> p_i45818_2_) {
         super(p_i45818_1_, p_i45818_2_, true);
      }

      public boolean func_75250_a() {
         float f = this.field_75299_d.func_70013_c(1.0F);
         return f >= 0.5F?false:super.func_75250_a();
      }
   }

   public static class GroupData implements IEntityLivingData {
      public int field_111105_a;

      public void func_111104_a(Random p_111104_1_) {
         int i = p_111104_1_.nextInt(5);
         if(i <= 1) {
            this.field_111105_a = Potion.field_76424_c.field_76415_H;
         } else if(i <= 2) {
            this.field_111105_a = Potion.field_76420_g.field_76415_H;
         } else if(i <= 3) {
            this.field_111105_a = Potion.field_76428_l.field_76415_H;
         } else if(i <= 4) {
            this.field_111105_a = Potion.field_76441_p.field_76415_H;
         }

      }
   }
}
