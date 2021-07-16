package net.minecraft.client.settings;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IntHashMap;

public class KeyBinding implements Comparable<KeyBinding> {
   private static final List<KeyBinding> field_74516_a = Lists.<KeyBinding>newArrayList();
   private static final IntHashMap<KeyBinding> field_74514_b = new IntHashMap();
   private static final Set<String> field_151473_c = Sets.<String>newHashSet();
   private final String field_74515_c;
   private final int field_151472_e;
   private final String field_151471_f;
   private int field_74512_d;
   private boolean field_74513_e;
   private int field_151474_i;

   public static void func_74507_a(int p_74507_0_) {
      if(p_74507_0_ != 0) {
         KeyBinding keybinding = (KeyBinding)field_74514_b.func_76041_a(p_74507_0_);
         if(keybinding != null) {
            ++keybinding.field_151474_i;
         }

      }
   }

   public static void func_74510_a(int p_74510_0_, boolean p_74510_1_) {
      if(p_74510_0_ != 0) {
         KeyBinding keybinding = (KeyBinding)field_74514_b.func_76041_a(p_74510_0_);
         if(keybinding != null) {
            keybinding.field_74513_e = p_74510_1_;
         }

      }
   }

   public static void func_74506_a() {
      for(KeyBinding keybinding : field_74516_a) {
         keybinding.func_74505_d();
      }

   }

   public static void func_74508_b() {
      field_74514_b.func_76046_c();

      for(KeyBinding keybinding : field_74516_a) {
         field_74514_b.func_76038_a(keybinding.field_74512_d, keybinding);
      }

   }

   public static Set<String> func_151467_c() {
      return field_151473_c;
   }

   public KeyBinding(String p_i45001_1_, int p_i45001_2_, String p_i45001_3_) {
      this.field_74515_c = p_i45001_1_;
      this.field_74512_d = p_i45001_2_;
      this.field_151472_e = p_i45001_2_;
      this.field_151471_f = p_i45001_3_;
      field_74516_a.add(this);
      field_74514_b.func_76038_a(p_i45001_2_, this);
      field_151473_c.add(p_i45001_3_);
   }

   public boolean func_151470_d() {
      return this.field_74513_e;
   }

   public String func_151466_e() {
      return this.field_151471_f;
   }

   public boolean func_151468_f() {
      if(this.field_151474_i == 0) {
         return false;
      } else {
         --this.field_151474_i;
         return true;
      }
   }

   private void func_74505_d() {
      this.field_151474_i = 0;
      this.field_74513_e = false;
   }

   public String func_151464_g() {
      return this.field_74515_c;
   }

   public int func_151469_h() {
      return this.field_151472_e;
   }

   public int func_151463_i() {
      return this.field_74512_d;
   }

   public void func_151462_b(int p_151462_1_) {
      this.field_74512_d = p_151462_1_;
   }

   public int compareTo(KeyBinding p_compareTo_1_) {
      int i = I18n.func_135052_a(this.field_151471_f, new Object[0]).compareTo(I18n.func_135052_a(p_compareTo_1_.field_151471_f, new Object[0]));
      if(i == 0) {
         i = I18n.func_135052_a(this.field_74515_c, new Object[0]).compareTo(I18n.func_135052_a(p_compareTo_1_.field_74515_c, new Object[0]));
      }

      return i;
   }
}
