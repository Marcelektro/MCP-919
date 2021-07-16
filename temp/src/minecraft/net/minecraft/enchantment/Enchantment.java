package net.minecraft.enchantment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.enchantment.EnchantmentArrowDamage;
import net.minecraft.enchantment.EnchantmentArrowFire;
import net.minecraft.enchantment.EnchantmentArrowInfinite;
import net.minecraft.enchantment.EnchantmentArrowKnockback;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentDigging;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentFireAspect;
import net.minecraft.enchantment.EnchantmentFishingSpeed;
import net.minecraft.enchantment.EnchantmentKnockback;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.enchantment.EnchantmentOxygen;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.enchantment.EnchantmentUntouching;
import net.minecraft.enchantment.EnchantmentWaterWalker;
import net.minecraft.enchantment.EnchantmentWaterWorker;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public abstract class Enchantment {
   private static final Enchantment[] field_180311_a = new Enchantment[256];
   public static final Enchantment[] field_77331_b;
   private static final Map<ResourceLocation, Enchantment> field_180307_E = Maps.<ResourceLocation, Enchantment>newHashMap();
   public static final Enchantment field_180310_c = new EnchantmentProtection(0, new ResourceLocation("protection"), 10, 0);
   public static final Enchantment field_77329_d = new EnchantmentProtection(1, new ResourceLocation("fire_protection"), 5, 1);
   public static final Enchantment field_180309_e = new EnchantmentProtection(2, new ResourceLocation("feather_falling"), 5, 2);
   public static final Enchantment field_77327_f = new EnchantmentProtection(3, new ResourceLocation("blast_protection"), 2, 3);
   public static final Enchantment field_180308_g = new EnchantmentProtection(4, new ResourceLocation("projectile_protection"), 5, 4);
   public static final Enchantment field_180317_h = new EnchantmentOxygen(5, new ResourceLocation("respiration"), 2);
   public static final Enchantment field_77341_i = new EnchantmentWaterWorker(6, new ResourceLocation("aqua_affinity"), 2);
   public static final Enchantment field_92091_k = new EnchantmentThorns(7, new ResourceLocation("thorns"), 1);
   public static final Enchantment field_180316_k = new EnchantmentWaterWalker(8, new ResourceLocation("depth_strider"), 2);
   public static final Enchantment field_180314_l = new EnchantmentDamage(16, new ResourceLocation("sharpness"), 10, 0);
   public static final Enchantment field_180315_m = new EnchantmentDamage(17, new ResourceLocation("smite"), 5, 1);
   public static final Enchantment field_180312_n = new EnchantmentDamage(18, new ResourceLocation("bane_of_arthropods"), 5, 2);
   public static final Enchantment field_180313_o = new EnchantmentKnockback(19, new ResourceLocation("knockback"), 5);
   public static final Enchantment field_77334_n = new EnchantmentFireAspect(20, new ResourceLocation("fire_aspect"), 2);
   public static final Enchantment field_77335_o = new EnchantmentLootBonus(21, new ResourceLocation("looting"), 2, EnumEnchantmentType.WEAPON);
   public static final Enchantment field_77349_p = new EnchantmentDigging(32, new ResourceLocation("efficiency"), 10);
   public static final Enchantment field_77348_q = new EnchantmentUntouching(33, new ResourceLocation("silk_touch"), 1);
   public static final Enchantment field_77347_r = new EnchantmentDurability(34, new ResourceLocation("unbreaking"), 5);
   public static final Enchantment field_77346_s = new EnchantmentLootBonus(35, new ResourceLocation("fortune"), 2, EnumEnchantmentType.DIGGER);
   public static final Enchantment field_77345_t = new EnchantmentArrowDamage(48, new ResourceLocation("power"), 10);
   public static final Enchantment field_77344_u = new EnchantmentArrowKnockback(49, new ResourceLocation("punch"), 2);
   public static final Enchantment field_77343_v = new EnchantmentArrowFire(50, new ResourceLocation("flame"), 2);
   public static final Enchantment field_77342_w = new EnchantmentArrowInfinite(51, new ResourceLocation("infinity"), 1);
   public static final Enchantment field_151370_z = new EnchantmentLootBonus(61, new ResourceLocation("luck_of_the_sea"), 2, EnumEnchantmentType.FISHING_ROD);
   public static final Enchantment field_151369_A = new EnchantmentFishingSpeed(62, new ResourceLocation("lure"), 2, EnumEnchantmentType.FISHING_ROD);
   public final int field_77352_x;
   private final int field_77333_a;
   public EnumEnchantmentType field_77351_y;
   protected String field_77350_z;

   public static Enchantment func_180306_c(int p_180306_0_) {
      return p_180306_0_ >= 0 && p_180306_0_ < field_180311_a.length?field_180311_a[p_180306_0_]:null;
   }

   protected Enchantment(int p_i45771_1_, ResourceLocation p_i45771_2_, int p_i45771_3_, EnumEnchantmentType p_i45771_4_) {
      this.field_77352_x = p_i45771_1_;
      this.field_77333_a = p_i45771_3_;
      this.field_77351_y = p_i45771_4_;
      if(field_180311_a[p_i45771_1_] != null) {
         throw new IllegalArgumentException("Duplicate enchantment id!");
      } else {
         field_180311_a[p_i45771_1_] = this;
         field_180307_E.put(p_i45771_2_, this);
      }
   }

   public static Enchantment func_180305_b(String p_180305_0_) {
      return (Enchantment)field_180307_E.get(new ResourceLocation(p_180305_0_));
   }

   public static Set<ResourceLocation> func_181077_c() {
      return field_180307_E.keySet();
   }

   public int func_77324_c() {
      return this.field_77333_a;
   }

   public int func_77319_d() {
      return 1;
   }

   public int func_77325_b() {
      return 1;
   }

   public int func_77321_a(int p_77321_1_) {
      return 1 + p_77321_1_ * 10;
   }

   public int func_77317_b(int p_77317_1_) {
      return this.func_77321_a(p_77317_1_) + 5;
   }

   public int func_77318_a(int p_77318_1_, DamageSource p_77318_2_) {
      return 0;
   }

   public float func_152376_a(int p_152376_1_, EnumCreatureAttribute p_152376_2_) {
      return 0.0F;
   }

   public boolean func_77326_a(Enchantment p_77326_1_) {
      return this != p_77326_1_;
   }

   public Enchantment func_77322_b(String p_77322_1_) {
      this.field_77350_z = p_77322_1_;
      return this;
   }

   public String func_77320_a() {
      return "enchantment." + this.field_77350_z;
   }

   public String func_77316_c(int p_77316_1_) {
      String s = StatCollector.func_74838_a(this.func_77320_a());
      return s + " " + StatCollector.func_74838_a("enchantment.level." + p_77316_1_);
   }

   public boolean func_92089_a(ItemStack p_92089_1_) {
      return this.field_77351_y.func_77557_a(p_92089_1_.func_77973_b());
   }

   public void func_151368_a(EntityLivingBase p_151368_1_, Entity p_151368_2_, int p_151368_3_) {
   }

   public void func_151367_b(EntityLivingBase p_151367_1_, Entity p_151367_2_, int p_151367_3_) {
   }

   static {
      List<Enchantment> list = Lists.<Enchantment>newArrayList();

      for(Enchantment enchantment : field_180311_a) {
         if(enchantment != null) {
            list.add(enchantment);
         }
      }

      field_77331_b = (Enchantment[])list.toArray(new Enchantment[list.size()]);
   }
}
