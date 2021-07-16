package net.minecraft.potion;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealth;
import net.minecraft.potion.PotionHealthBoost;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class Potion {
   public static final Potion[] field_76425_a = new Potion[32];
   private static final Map<ResourceLocation, Potion> field_180150_I = Maps.<ResourceLocation, Potion>newHashMap();
   public static final Potion field_180151_b = null;
   public static final Potion field_76424_c = (new Potion(1, new ResourceLocation("speed"), false, 8171462)).func_76390_b("potion.moveSpeed").func_76399_b(0, 0).func_111184_a(SharedMonsterAttributes.field_111263_d, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
   public static final Potion field_76421_d = (new Potion(2, new ResourceLocation("slowness"), true, 5926017)).func_76390_b("potion.moveSlowdown").func_76399_b(1, 0).func_111184_a(SharedMonsterAttributes.field_111263_d, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
   public static final Potion field_76422_e = (new Potion(3, new ResourceLocation("haste"), false, 14270531)).func_76390_b("potion.digSpeed").func_76399_b(2, 0).func_76404_a(1.5D);
   public static final Potion field_76419_f = (new Potion(4, new ResourceLocation("mining_fatigue"), true, 4866583)).func_76390_b("potion.digSlowDown").func_76399_b(3, 0);
   public static final Potion field_76420_g = (new PotionAttackDamage(5, new ResourceLocation("strength"), false, 9643043)).func_76390_b("potion.damageBoost").func_76399_b(4, 0).func_111184_a(SharedMonsterAttributes.field_111264_e, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.5D, 2);
   public static final Potion field_76432_h = (new PotionHealth(6, new ResourceLocation("instant_health"), false, 16262179)).func_76390_b("potion.heal");
   public static final Potion field_76433_i = (new PotionHealth(7, new ResourceLocation("instant_damage"), true, 4393481)).func_76390_b("potion.harm");
   public static final Potion field_76430_j = (new Potion(8, new ResourceLocation("jump_boost"), false, 2293580)).func_76390_b("potion.jump").func_76399_b(2, 1);
   public static final Potion field_76431_k = (new Potion(9, new ResourceLocation("nausea"), true, 5578058)).func_76390_b("potion.confusion").func_76399_b(3, 1).func_76404_a(0.25D);
   public static final Potion field_76428_l = (new Potion(10, new ResourceLocation("regeneration"), false, 13458603)).func_76390_b("potion.regeneration").func_76399_b(7, 0).func_76404_a(0.25D);
   public static final Potion field_76429_m = (new Potion(11, new ResourceLocation("resistance"), false, 10044730)).func_76390_b("potion.resistance").func_76399_b(6, 1);
   public static final Potion field_76426_n = (new Potion(12, new ResourceLocation("fire_resistance"), false, 14981690)).func_76390_b("potion.fireResistance").func_76399_b(7, 1);
   public static final Potion field_76427_o = (new Potion(13, new ResourceLocation("water_breathing"), false, 3035801)).func_76390_b("potion.waterBreathing").func_76399_b(0, 2);
   public static final Potion field_76441_p = (new Potion(14, new ResourceLocation("invisibility"), false, 8356754)).func_76390_b("potion.invisibility").func_76399_b(0, 1);
   public static final Potion field_76440_q = (new Potion(15, new ResourceLocation("blindness"), true, 2039587)).func_76390_b("potion.blindness").func_76399_b(5, 1).func_76404_a(0.25D);
   public static final Potion field_76439_r = (new Potion(16, new ResourceLocation("night_vision"), false, 2039713)).func_76390_b("potion.nightVision").func_76399_b(4, 1);
   public static final Potion field_76438_s = (new Potion(17, new ResourceLocation("hunger"), true, 5797459)).func_76390_b("potion.hunger").func_76399_b(1, 1);
   public static final Potion field_76437_t = (new PotionAttackDamage(18, new ResourceLocation("weakness"), true, 4738376)).func_76390_b("potion.weakness").func_76399_b(5, 0).func_111184_a(SharedMonsterAttributes.field_111264_e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
   public static final Potion field_76436_u = (new Potion(19, new ResourceLocation("poison"), true, 5149489)).func_76390_b("potion.poison").func_76399_b(6, 0).func_76404_a(0.25D);
   public static final Potion field_82731_v = (new Potion(20, new ResourceLocation("wither"), true, 3484199)).func_76390_b("potion.wither").func_76399_b(1, 2).func_76404_a(0.25D);
   public static final Potion field_180152_w = (new PotionHealthBoost(21, new ResourceLocation("health_boost"), false, 16284963)).func_76390_b("potion.healthBoost").func_76399_b(2, 2).func_111184_a(SharedMonsterAttributes.field_111267_a, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0);
   public static final Potion field_76444_x = (new PotionAbsorption(22, new ResourceLocation("absorption"), false, 2445989)).func_76390_b("potion.absorption").func_76399_b(2, 2);
   public static final Potion field_76443_y = (new PotionHealth(23, new ResourceLocation("saturation"), false, 16262179)).func_76390_b("potion.saturation");
   public static final Potion field_180153_z = null;
   public static final Potion field_180147_A = null;
   public static final Potion field_180148_B = null;
   public static final Potion field_180149_C = null;
   public static final Potion field_180143_D = null;
   public static final Potion field_180144_E = null;
   public static final Potion field_180145_F = null;
   public static final Potion field_180146_G = null;
   public final int field_76415_H;
   private final Map<IAttribute, AttributeModifier> field_111188_I = Maps.<IAttribute, AttributeModifier>newHashMap();
   private final boolean field_76418_K;
   private final int field_76414_N;
   private String field_76416_I = "";
   private int field_76417_J = -1;
   private double field_76412_L;
   private boolean field_76413_M;

   protected Potion(int p_i45897_1_, ResourceLocation p_i45897_2_, boolean p_i45897_3_, int p_i45897_4_) {
      this.field_76415_H = p_i45897_1_;
      field_76425_a[p_i45897_1_] = this;
      field_180150_I.put(p_i45897_2_, this);
      this.field_76418_K = p_i45897_3_;
      if(p_i45897_3_) {
         this.field_76412_L = 0.5D;
      } else {
         this.field_76412_L = 1.0D;
      }

      this.field_76414_N = p_i45897_4_;
   }

   public static Potion func_180142_b(String p_180142_0_) {
      return (Potion)field_180150_I.get(new ResourceLocation(p_180142_0_));
   }

   public static Set<ResourceLocation> func_181168_c() {
      return field_180150_I.keySet();
   }

   protected Potion func_76399_b(int p_76399_1_, int p_76399_2_) {
      this.field_76417_J = p_76399_1_ + p_76399_2_ * 8;
      return this;
   }

   public int func_76396_c() {
      return this.field_76415_H;
   }

   public void func_76394_a(EntityLivingBase p_76394_1_, int p_76394_2_) {
      if(this.field_76415_H == field_76428_l.field_76415_H) {
         if(p_76394_1_.func_110143_aJ() < p_76394_1_.func_110138_aP()) {
            p_76394_1_.func_70691_i(1.0F);
         }
      } else if(this.field_76415_H == field_76436_u.field_76415_H) {
         if(p_76394_1_.func_110143_aJ() > 1.0F) {
            p_76394_1_.func_70097_a(DamageSource.field_76376_m, 1.0F);
         }
      } else if(this.field_76415_H == field_82731_v.field_76415_H) {
         p_76394_1_.func_70097_a(DamageSource.field_82727_n, 1.0F);
      } else if(this.field_76415_H == field_76438_s.field_76415_H && p_76394_1_ instanceof EntityPlayer) {
         ((EntityPlayer)p_76394_1_).func_71020_j(0.025F * (float)(p_76394_2_ + 1));
      } else if(this.field_76415_H == field_76443_y.field_76415_H && p_76394_1_ instanceof EntityPlayer) {
         if(!p_76394_1_.field_70170_p.field_72995_K) {
            ((EntityPlayer)p_76394_1_).func_71024_bL().func_75122_a(p_76394_2_ + 1, 1.0F);
         }
      } else if((this.field_76415_H != field_76432_h.field_76415_H || p_76394_1_.func_70662_br()) && (this.field_76415_H != field_76433_i.field_76415_H || !p_76394_1_.func_70662_br())) {
         if(this.field_76415_H == field_76433_i.field_76415_H && !p_76394_1_.func_70662_br() || this.field_76415_H == field_76432_h.field_76415_H && p_76394_1_.func_70662_br()) {
            p_76394_1_.func_70097_a(DamageSource.field_76376_m, (float)(6 << p_76394_2_));
         }
      } else {
         p_76394_1_.func_70691_i((float)Math.max(4 << p_76394_2_, 0));
      }

   }

   public void func_180793_a(Entity p_180793_1_, Entity p_180793_2_, EntityLivingBase p_180793_3_, int p_180793_4_, double p_180793_5_) {
      if((this.field_76415_H != field_76432_h.field_76415_H || p_180793_3_.func_70662_br()) && (this.field_76415_H != field_76433_i.field_76415_H || !p_180793_3_.func_70662_br())) {
         if(this.field_76415_H == field_76433_i.field_76415_H && !p_180793_3_.func_70662_br() || this.field_76415_H == field_76432_h.field_76415_H && p_180793_3_.func_70662_br()) {
            int j = (int)(p_180793_5_ * (double)(6 << p_180793_4_) + 0.5D);
            if(p_180793_1_ == null) {
               p_180793_3_.func_70097_a(DamageSource.field_76376_m, (float)j);
            } else {
               p_180793_3_.func_70097_a(DamageSource.func_76354_b(p_180793_1_, p_180793_2_), (float)j);
            }
         }
      } else {
         int i = (int)(p_180793_5_ * (double)(4 << p_180793_4_) + 0.5D);
         p_180793_3_.func_70691_i((float)i);
      }

   }

   public boolean func_76403_b() {
      return false;
   }

   public boolean func_76397_a(int p_76397_1_, int p_76397_2_) {
      if(this.field_76415_H == field_76428_l.field_76415_H) {
         int k = 50 >> p_76397_2_;
         return k > 0?p_76397_1_ % k == 0:true;
      } else if(this.field_76415_H == field_76436_u.field_76415_H) {
         int j = 25 >> p_76397_2_;
         return j > 0?p_76397_1_ % j == 0:true;
      } else if(this.field_76415_H == field_82731_v.field_76415_H) {
         int i = 40 >> p_76397_2_;
         return i > 0?p_76397_1_ % i == 0:true;
      } else {
         return this.field_76415_H == field_76438_s.field_76415_H;
      }
   }

   public Potion func_76390_b(String p_76390_1_) {
      this.field_76416_I = p_76390_1_;
      return this;
   }

   public String func_76393_a() {
      return this.field_76416_I;
   }

   public boolean func_76400_d() {
      return this.field_76417_J >= 0;
   }

   public int func_76392_e() {
      return this.field_76417_J;
   }

   public boolean func_76398_f() {
      return this.field_76418_K;
   }

   public static String func_76389_a(PotionEffect p_76389_0_) {
      if(p_76389_0_.func_100011_g()) {
         return "**:**";
      } else {
         int i = p_76389_0_.func_76459_b();
         return StringUtils.func_76337_a(i);
      }
   }

   protected Potion func_76404_a(double p_76404_1_) {
      this.field_76412_L = p_76404_1_;
      return this;
   }

   public double func_76388_g() {
      return this.field_76412_L;
   }

   public boolean func_76395_i() {
      return this.field_76413_M;
   }

   public int func_76401_j() {
      return this.field_76414_N;
   }

   public Potion func_111184_a(IAttribute p_111184_1_, String p_111184_2_, double p_111184_3_, int p_111184_5_) {
      AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(p_111184_2_), this.func_76393_a(), p_111184_3_, p_111184_5_);
      this.field_111188_I.put(p_111184_1_, attributemodifier);
      return this;
   }

   public Map<IAttribute, AttributeModifier> func_111186_k() {
      return this.field_111188_I;
   }

   public void func_111187_a(EntityLivingBase p_111187_1_, BaseAttributeMap p_111187_2_, int p_111187_3_) {
      for(Entry<IAttribute, AttributeModifier> entry : this.field_111188_I.entrySet()) {
         IAttributeInstance iattributeinstance = p_111187_2_.func_111151_a((IAttribute)entry.getKey());
         if(iattributeinstance != null) {
            iattributeinstance.func_111124_b((AttributeModifier)entry.getValue());
         }
      }

   }

   public void func_111185_a(EntityLivingBase p_111185_1_, BaseAttributeMap p_111185_2_, int p_111185_3_) {
      for(Entry<IAttribute, AttributeModifier> entry : this.field_111188_I.entrySet()) {
         IAttributeInstance iattributeinstance = p_111185_2_.func_111151_a((IAttribute)entry.getKey());
         if(iattributeinstance != null) {
            AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
            iattributeinstance.func_111124_b(attributemodifier);
            iattributeinstance.func_111121_a(new AttributeModifier(attributemodifier.func_111167_a(), this.func_76393_a() + " " + p_111185_3_, this.func_111183_a(p_111185_3_, attributemodifier), attributemodifier.func_111169_c()));
         }
      }

   }

   public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_) {
      return p_111183_2_.func_111164_d() * (double)(p_111183_1_ + 1);
   }
}
