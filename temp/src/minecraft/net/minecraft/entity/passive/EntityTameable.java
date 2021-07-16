package net.minecraft.entity.passive;

import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public abstract class EntityTameable extends EntityAnimal implements IEntityOwnable {
   protected EntityAISit field_70911_d = new EntityAISit(this);

   public EntityTameable(World p_i1604_1_) {
      super(p_i1604_1_);
      this.func_175544_ck();
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
      this.field_70180_af.func_75682_a(17, "");
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      if(this.func_152113_b() == null) {
         p_70014_1_.func_74778_a("OwnerUUID", "");
      } else {
         p_70014_1_.func_74778_a("OwnerUUID", this.func_152113_b());
      }

      p_70014_1_.func_74757_a("Sitting", this.func_70906_o());
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      String s = "";
      if(p_70037_1_.func_150297_b("OwnerUUID", 8)) {
         s = p_70037_1_.func_74779_i("OwnerUUID");
      } else {
         String s1 = p_70037_1_.func_74779_i("Owner");
         s = PreYggdrasilConverter.func_152719_a(s1);
      }

      if(s.length() > 0) {
         this.func_152115_b(s);
         this.func_70903_f(true);
      }

      this.field_70911_d.func_75270_a(p_70037_1_.func_74767_n("Sitting"));
      this.func_70904_g(p_70037_1_.func_74767_n("Sitting"));
   }

   protected void func_70908_e(boolean p_70908_1_) {
      EnumParticleTypes enumparticletypes = EnumParticleTypes.HEART;
      if(!p_70908_1_) {
         enumparticletypes = EnumParticleTypes.SMOKE_NORMAL;
      }

      for(int i = 0; i < 7; ++i) {
         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
         this.field_70170_p.func_175688_a(enumparticletypes, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, d0, d1, d2, new int[0]);
      }

   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 7) {
         this.func_70908_e(true);
      } else if(p_70103_1_ == 6) {
         this.func_70908_e(false);
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   public boolean func_70909_n() {
      return (this.field_70180_af.func_75683_a(16) & 4) != 0;
   }

   public void func_70903_f(boolean p_70903_1_) {
      byte b0 = this.field_70180_af.func_75683_a(16);
      if(p_70903_1_) {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 4)));
      } else {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & -5)));
      }

      this.func_175544_ck();
   }

   protected void func_175544_ck() {
   }

   public boolean func_70906_o() {
      return (this.field_70180_af.func_75683_a(16) & 1) != 0;
   }

   public void func_70904_g(boolean p_70904_1_) {
      byte b0 = this.field_70180_af.func_75683_a(16);
      if(p_70904_1_) {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 | 1)));
      } else {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(b0 & -2)));
      }

   }

   public String func_152113_b() {
      return this.field_70180_af.func_75681_e(17);
   }

   public void func_152115_b(String p_152115_1_) {
      this.field_70180_af.func_75692_b(17, p_152115_1_);
   }

   public EntityLivingBase func_70902_q() {
      try {
         UUID uuid = UUID.fromString(this.func_152113_b());
         return uuid == null?null:this.field_70170_p.func_152378_a(uuid);
      } catch (IllegalArgumentException var2) {
         return null;
      }
   }

   public boolean func_152114_e(EntityLivingBase p_152114_1_) {
      return p_152114_1_ == this.func_70902_q();
   }

   public EntityAISit func_70907_r() {
      return this.field_70911_d;
   }

   public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
      return true;
   }

   public Team func_96124_cp() {
      if(this.func_70909_n()) {
         EntityLivingBase entitylivingbase = this.func_70902_q();
         if(entitylivingbase != null) {
            return entitylivingbase.func_96124_cp();
         }
      }

      return super.func_96124_cp();
   }

   public boolean func_142014_c(EntityLivingBase p_142014_1_) {
      if(this.func_70909_n()) {
         EntityLivingBase entitylivingbase = this.func_70902_q();
         if(p_142014_1_ == entitylivingbase) {
            return true;
         }

         if(entitylivingbase != null) {
            return entitylivingbase.func_142014_c(p_142014_1_);
         }
      }

      return super.func_142014_c(p_142014_1_);
   }

   public void func_70645_a(DamageSource p_70645_1_) {
      if(!this.field_70170_p.field_72995_K && this.field_70170_p.func_82736_K().func_82766_b("showDeathMessages") && this.func_145818_k_() && this.func_70902_q() instanceof EntityPlayerMP) {
         ((EntityPlayerMP)this.func_70902_q()).func_145747_a(this.func_110142_aN().func_151521_b());
      }

      super.func_70645_a(p_70645_1_);
   }
}
