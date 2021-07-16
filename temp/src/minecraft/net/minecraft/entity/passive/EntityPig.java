package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityPig extends EntityAnimal {
   private final EntityAIControlledByPlayer field_82184_d;

   public EntityPig(World p_i1689_1_) {
      super(p_i1689_1_);
      this.func_70105_a(0.9F, 0.9F);
      ((PathNavigateGround)this.func_70661_as()).func_179690_a(true);
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.25D));
      this.field_70714_bg.func_75776_a(2, this.field_82184_d = new EntityAIControlledByPlayer(this, 0.3F));
      this.field_70714_bg.func_75776_a(3, new EntityAIMate(this, 1.0D));
      this.field_70714_bg.func_75776_a(4, new EntityAITempt(this, 1.2D, Items.field_151146_bM, false));
      this.field_70714_bg.func_75776_a(4, new EntityAITempt(this, 1.2D, Items.field_151172_bF, false));
      this.field_70714_bg.func_75776_a(5, new EntityAIFollowParent(this, 1.1D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
   }

   public boolean func_82171_bF() {
      ItemStack itemstack = ((EntityPlayer)this.field_70153_n).func_70694_bm();
      return itemstack != null && itemstack.func_77973_b() == Items.field_151146_bM;
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("Saddle", this.func_70901_n());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_70900_e(p_70037_1_.func_74767_n("Saddle"));
   }

   protected String func_70639_aQ() {
      return "mob.pig.say";
   }

   protected String func_70621_aR() {
      return "mob.pig.say";
   }

   protected String func_70673_aS() {
      return "mob.pig.death";
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.pig.step", 0.15F, 1.0F);
   }

   public boolean func_70085_c(EntityPlayer p_70085_1_) {
      if(super.func_70085_c(p_70085_1_)) {
         return true;
      } else if(!this.func_70901_n() || this.field_70170_p.field_72995_K || this.field_70153_n != null && this.field_70153_n != p_70085_1_) {
         return false;
      } else {
         p_70085_1_.func_70078_a(this);
         return true;
      }
   }

   protected Item func_146068_u() {
      return this.func_70027_ad()?Items.field_151157_am:Items.field_151147_al;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      int i = this.field_70146_Z.nextInt(3) + 1 + this.field_70146_Z.nextInt(1 + p_70628_2_);

      for(int j = 0; j < i; ++j) {
         if(this.func_70027_ad()) {
            this.func_145779_a(Items.field_151157_am, 1);
         } else {
            this.func_145779_a(Items.field_151147_al, 1);
         }
      }

      if(this.func_70901_n()) {
         this.func_145779_a(Items.field_151141_av, 1);
      }

   }

   public boolean func_70901_n() {
      return (this.field_70180_af.func_75683_a(16) & 1) != 0;
   }

   public void func_70900_e(boolean p_70900_1_) {
      if(p_70900_1_) {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)1));
      } else {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)0));
      }

   }

   public void func_70077_a(EntityLightningBolt p_70077_1_) {
      if(!this.field_70170_p.field_72995_K && !this.field_70128_L) {
         EntityPigZombie entitypigzombie = new EntityPigZombie(this.field_70170_p);
         entitypigzombie.func_70062_b(0, new ItemStack(Items.field_151010_B));
         entitypigzombie.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
         entitypigzombie.func_94061_f(this.func_175446_cd());
         if(this.func_145818_k_()) {
            entitypigzombie.func_96094_a(this.func_95999_t());
            entitypigzombie.func_174805_g(this.func_174833_aM());
         }

         this.field_70170_p.func_72838_d(entitypigzombie);
         this.func_70106_y();
      }
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
      super.func_180430_e(p_180430_1_, p_180430_2_);
      if(p_180430_1_ > 5.0F && this.field_70153_n instanceof EntityPlayer) {
         ((EntityPlayer)this.field_70153_n).func_71029_a(AchievementList.field_76021_u);
      }

   }

   public EntityPig func_90011_a(EntityAgeable p_90011_1_) {
      return new EntityPig(this.field_70170_p);
   }

   public boolean func_70877_b(ItemStack p_70877_1_) {
      return p_70877_1_ != null && p_70877_1_.func_77973_b() == Items.field_151172_bF;
   }

   public EntityAIControlledByPlayer func_82183_n() {
      return this.field_82184_d;
   }
}
