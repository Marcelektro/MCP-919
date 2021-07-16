package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCreeper extends EntityMob {
   private int field_70834_e;
   private int field_70833_d;
   private int field_82225_f = 30;
   private int field_82226_g = 3;
   private int field_175494_bm = 0;

   public EntityCreeper(World p_i1733_1_) {
      super(p_i1733_1_);
      this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(2, new EntityAICreeperSwell(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
      this.field_70714_bg.func_75776_a(4, new EntityAIAttackOnCollide(this, 1.0D, false));
      this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 0.8D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
      this.field_70715_bh.func_75776_a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
      this.field_70715_bh.func_75776_a(2, new EntityAIHurtByTarget(this, false, new Class[0]));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
   }

   public int func_82143_as() {
      return this.func_70638_az() == null?3:3 + (int)(this.func_110143_aJ() - 1.0F);
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
      super.func_180430_e(p_180430_1_, p_180430_2_);
      this.field_70833_d = (int)((float)this.field_70833_d + p_180430_1_ * 1.5F);
      if(this.field_70833_d > this.field_82225_f - 5) {
         this.field_70833_d = this.field_82225_f - 5;
      }

   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)-1));
      this.field_70180_af.func_75682_a(17, Byte.valueOf((byte)0));
      this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      if(this.field_70180_af.func_75683_a(17) == 1) {
         p_70014_1_.func_74757_a("powered", true);
      }

      p_70014_1_.func_74777_a("Fuse", (short)this.field_82225_f);
      p_70014_1_.func_74774_a("ExplosionRadius", (byte)this.field_82226_g);
      p_70014_1_.func_74757_a("ignited", this.func_146078_ca());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)(p_70037_1_.func_74767_n("powered")?1:0)));
      if(p_70037_1_.func_150297_b("Fuse", 99)) {
         this.field_82225_f = p_70037_1_.func_74765_d("Fuse");
      }

      if(p_70037_1_.func_150297_b("ExplosionRadius", 99)) {
         this.field_82226_g = p_70037_1_.func_74771_c("ExplosionRadius");
      }

      if(p_70037_1_.func_74767_n("ignited")) {
         this.func_146079_cb();
      }

   }

   public void func_70071_h_() {
      if(this.func_70089_S()) {
         this.field_70834_e = this.field_70833_d;
         if(this.func_146078_ca()) {
            this.func_70829_a(1);
         }

         int i = this.func_70832_p();
         if(i > 0 && this.field_70833_d == 0) {
            this.func_85030_a("creeper.primed", 1.0F, 0.5F);
         }

         this.field_70833_d += i;
         if(this.field_70833_d < 0) {
            this.field_70833_d = 0;
         }

         if(this.field_70833_d >= this.field_82225_f) {
            this.field_70833_d = this.field_82225_f;
            this.func_146077_cc();
         }
      }

      super.func_70071_h_();
   }

   protected String func_70621_aR() {
      return "mob.creeper.say";
   }

   protected String func_70673_aS() {
      return "mob.creeper.death";
   }

   public void func_70645_a(DamageSource p_70645_1_) {
      super.func_70645_a(p_70645_1_);
      if(p_70645_1_.func_76346_g() instanceof EntitySkeleton) {
         int i = Item.func_150891_b(Items.field_151096_cd);
         int j = Item.func_150891_b(Items.field_151084_co);
         int k = i + this.field_70146_Z.nextInt(j - i + 1);
         this.func_145779_a(Item.func_150899_d(k), 1);
      } else if(p_70645_1_.func_76346_g() instanceof EntityCreeper && p_70645_1_.func_76346_g() != this && ((EntityCreeper)p_70645_1_.func_76346_g()).func_70830_n() && ((EntityCreeper)p_70645_1_.func_76346_g()).func_70650_aV()) {
         ((EntityCreeper)p_70645_1_.func_76346_g()).func_175493_co();
         this.func_70099_a(new ItemStack(Items.field_151144_bL, 1, 4), 0.0F);
      }

   }

   public boolean func_70652_k(Entity p_70652_1_) {
      return true;
   }

   public boolean func_70830_n() {
      return this.field_70180_af.func_75683_a(17) == 1;
   }

   public float func_70831_j(float p_70831_1_) {
      return ((float)this.field_70834_e + (float)(this.field_70833_d - this.field_70834_e) * p_70831_1_) / (float)(this.field_82225_f - 2);
   }

   protected Item func_146068_u() {
      return Items.field_151016_H;
   }

   public int func_70832_p() {
      return this.field_70180_af.func_75683_a(16);
   }

   public void func_70829_a(int p_70829_1_) {
      this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)p_70829_1_));
   }

   public void func_70077_a(EntityLightningBolt p_70077_1_) {
      super.func_70077_a(p_70077_1_);
      this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)1));
   }

   protected boolean func_70085_c(EntityPlayer p_70085_1_) {
      ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
      if(itemstack != null && itemstack.func_77973_b() == Items.field_151033_d) {
         this.field_70170_p.func_72908_a(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D, "fire.ignite", 1.0F, this.field_70146_Z.nextFloat() * 0.4F + 0.8F);
         p_70085_1_.func_71038_i();
         if(!this.field_70170_p.field_72995_K) {
            this.func_146079_cb();
            itemstack.func_77972_a(1, p_70085_1_);
            return true;
         }
      }

      return super.func_70085_c(p_70085_1_);
   }

   private void func_146077_cc() {
      if(!this.field_70170_p.field_72995_K) {
         boolean flag = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing");
         float f = this.func_70830_n()?2.0F:1.0F;
         this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u, this.field_70161_v, (float)this.field_82226_g * f, flag);
         this.func_70106_y();
      }

   }

   public boolean func_146078_ca() {
      return this.field_70180_af.func_75683_a(18) != 0;
   }

   public void func_146079_cb() {
      this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)1));
   }

   public boolean func_70650_aV() {
      return this.field_175494_bm < 1 && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot");
   }

   public void func_175493_co() {
      ++this.field_175494_bm;
   }
}
