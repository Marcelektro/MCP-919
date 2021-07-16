package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityChicken extends EntityAnimal {
   public float field_70886_e;
   public float field_70883_f;
   public float field_70884_g;
   public float field_70888_h;
   public float field_70889_i = 1.0F;
   public int field_70887_j;
   public boolean field_152118_bv;

   public EntityChicken(World p_i1682_1_) {
      super(p_i1682_1_);
      this.func_70105_a(0.4F, 0.7F);
      this.field_70887_j = this.field_70146_Z.nextInt(6000) + 6000;
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.4D));
      this.field_70714_bg.func_75776_a(2, new EntityAIMate(this, 1.0D));
      this.field_70714_bg.func_75776_a(3, new EntityAITempt(this, 1.0D, Items.field_151014_N, false));
      this.field_70714_bg.func_75776_a(4, new EntityAIFollowParent(this, 1.1D));
      this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
   }

   public float func_70047_e() {
      return this.field_70131_O;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
   }

   public void func_70636_d() {
      super.func_70636_d();
      this.field_70888_h = this.field_70886_e;
      this.field_70884_g = this.field_70883_f;
      this.field_70883_f = (float)((double)this.field_70883_f + (double)(this.field_70122_E?-1:4) * 0.3D);
      this.field_70883_f = MathHelper.func_76131_a(this.field_70883_f, 0.0F, 1.0F);
      if(!this.field_70122_E && this.field_70889_i < 1.0F) {
         this.field_70889_i = 1.0F;
      }

      this.field_70889_i = (float)((double)this.field_70889_i * 0.9D);
      if(!this.field_70122_E && this.field_70181_x < 0.0D) {
         this.field_70181_x *= 0.6D;
      }

      this.field_70886_e += this.field_70889_i * 2.0F;
      if(!this.field_70170_p.field_72995_K && !this.func_70631_g_() && !this.func_152116_bZ() && --this.field_70887_j <= 0) {
         this.func_85030_a("mob.chicken.plop", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
         this.func_145779_a(Items.field_151110_aK, 1);
         this.field_70887_j = this.field_70146_Z.nextInt(6000) + 6000;
      }

   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
   }

   protected String func_70639_aQ() {
      return "mob.chicken.say";
   }

   protected String func_70621_aR() {
      return "mob.chicken.hurt";
   }

   protected String func_70673_aS() {
      return "mob.chicken.hurt";
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.chicken.step", 0.15F, 1.0F);
   }

   protected Item func_146068_u() {
      return Items.field_151008_G;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      int i = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + p_70628_2_);

      for(int j = 0; j < i; ++j) {
         this.func_145779_a(Items.field_151008_G, 1);
      }

      if(this.func_70027_ad()) {
         this.func_145779_a(Items.field_151077_bg, 1);
      } else {
         this.func_145779_a(Items.field_151076_bf, 1);
      }

   }

   public EntityChicken func_90011_a(EntityAgeable p_90011_1_) {
      return new EntityChicken(this.field_70170_p);
   }

   public boolean func_70877_b(ItemStack p_70877_1_) {
      return p_70877_1_ != null && p_70877_1_.func_77973_b() == Items.field_151014_N;
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_152118_bv = p_70037_1_.func_74767_n("IsChickenJockey");
      if(p_70037_1_.func_74764_b("EggLayTime")) {
         this.field_70887_j = p_70037_1_.func_74762_e("EggLayTime");
      }

   }

   protected int func_70693_a(EntityPlayer p_70693_1_) {
      return this.func_152116_bZ()?10:super.func_70693_a(p_70693_1_);
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("IsChickenJockey", this.field_152118_bv);
      p_70014_1_.func_74768_a("EggLayTime", this.field_70887_j);
   }

   protected boolean func_70692_ba() {
      return this.func_152116_bZ() && this.field_70153_n == null;
   }

   public void func_70043_V() {
      super.func_70043_V();
      float f = MathHelper.func_76126_a(this.field_70761_aq * 3.1415927F / 180.0F);
      float f1 = MathHelper.func_76134_b(this.field_70761_aq * 3.1415927F / 180.0F);
      float f2 = 0.1F;
      float f3 = 0.0F;
      this.field_70153_n.func_70107_b(this.field_70165_t + (double)(f2 * f), this.field_70163_u + (double)(this.field_70131_O * 0.5F) + this.field_70153_n.func_70033_W() + (double)f3, this.field_70161_v - (double)(f2 * f1));
      if(this.field_70153_n instanceof EntityLivingBase) {
         ((EntityLivingBase)this.field_70153_n).field_70761_aq = this.field_70761_aq;
      }

   }

   public boolean func_152116_bZ() {
      return this.field_152118_bv;
   }

   public void func_152117_i(boolean p_152117_1_) {
      this.field_152118_bv = p_152117_1_;
   }
}
