package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityTNTPrimed extends Entity {
   public int field_70516_a;
   private EntityLivingBase field_94084_b;

   public EntityTNTPrimed(World p_i1729_1_) {
      super(p_i1729_1_);
      this.field_70156_m = true;
      this.func_70105_a(0.98F, 0.98F);
   }

   public EntityTNTPrimed(World p_i1730_1_, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_) {
      this(p_i1730_1_);
      this.func_70107_b(p_i1730_2_, p_i1730_4_, p_i1730_6_);
      float f = (float)(Math.random() * 3.1415927410125732D * 2.0D);
      this.field_70159_w = (double)(-((float)Math.sin((double)f)) * 0.02F);
      this.field_70181_x = 0.20000000298023224D;
      this.field_70179_y = (double)(-((float)Math.cos((double)f)) * 0.02F);
      this.field_70516_a = 80;
      this.field_70169_q = p_i1730_2_;
      this.field_70167_r = p_i1730_4_;
      this.field_70166_s = p_i1730_6_;
      this.field_94084_b = p_i1730_8_;
   }

   protected void func_70088_a() {
   }

   protected boolean func_70041_e_() {
      return false;
   }

   public boolean func_70067_L() {
      return !this.field_70128_L;
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      this.field_70181_x -= 0.03999999910593033D;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.9800000190734863D;
      this.field_70181_x *= 0.9800000190734863D;
      this.field_70179_y *= 0.9800000190734863D;
      if(this.field_70122_E) {
         this.field_70159_w *= 0.699999988079071D;
         this.field_70179_y *= 0.699999988079071D;
         this.field_70181_x *= -0.5D;
      }

      if(this.field_70516_a-- <= 0) {
         this.func_70106_y();
         if(!this.field_70170_p.field_72995_K) {
            this.func_70515_d();
         }
      } else {
         this.func_70072_I();
         this.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v, 0.0D, 0.0D, 0.0D, new int[0]);
      }

   }

   private void func_70515_d() {
      float f = 4.0F;
      this.field_70170_p.func_72876_a(this, this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 16.0F), this.field_70161_v, f, true);
   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.func_74774_a("Fuse", (byte)this.field_70516_a);
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_70516_a = p_70037_1_.func_74771_c("Fuse");
   }

   public EntityLivingBase func_94083_c() {
      return this.field_94084_b;
   }

   public float func_70047_e() {
      return 0.0F;
   }
}
