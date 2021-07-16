package net.minecraft.world.storage;

import net.minecraft.world.WorldSettings;

public class SaveFormatComparator implements Comparable<SaveFormatComparator> {
   private final String field_75797_a;
   private final String field_75795_b;
   private final long field_75796_c;
   private final long field_75793_d;
   private final boolean field_75794_e;
   private final WorldSettings.GameType field_75791_f;
   private final boolean field_75792_g;
   private final boolean field_75798_h;

   public SaveFormatComparator(String p_i2161_1_, String p_i2161_2_, long p_i2161_3_, long p_i2161_5_, WorldSettings.GameType p_i2161_7_, boolean p_i2161_8_, boolean p_i2161_9_, boolean p_i2161_10_) {
      this.field_75797_a = p_i2161_1_;
      this.field_75795_b = p_i2161_2_;
      this.field_75796_c = p_i2161_3_;
      this.field_75793_d = p_i2161_5_;
      this.field_75791_f = p_i2161_7_;
      this.field_75794_e = p_i2161_8_;
      this.field_75792_g = p_i2161_9_;
      this.field_75798_h = p_i2161_10_;
   }

   public String func_75786_a() {
      return this.field_75797_a;
   }

   public String func_75788_b() {
      return this.field_75795_b;
   }

   public long func_154336_c() {
      return this.field_75793_d;
   }

   public boolean func_75785_d() {
      return this.field_75794_e;
   }

   public long func_75784_e() {
      return this.field_75796_c;
   }

   public int compareTo(SaveFormatComparator p_compareTo_1_) {
      return this.field_75796_c < p_compareTo_1_.field_75796_c?1:(this.field_75796_c > p_compareTo_1_.field_75796_c?-1:this.field_75797_a.compareTo(p_compareTo_1_.field_75797_a));
   }

   public WorldSettings.GameType func_75790_f() {
      return this.field_75791_f;
   }

   public boolean func_75789_g() {
      return this.field_75792_g;
   }

   public boolean func_75783_h() {
      return this.field_75798_h;
   }
}
