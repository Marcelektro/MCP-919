package net.minecraft.client.renderer.block.model;

import net.minecraft.util.EnumFacing;

public class BakedQuad {
   protected final int[] field_178215_a;
   protected final int field_178213_b;
   protected final EnumFacing field_178214_c;

   public BakedQuad(int[] p_i46232_1_, int p_i46232_2_, EnumFacing p_i46232_3_) {
      this.field_178215_a = p_i46232_1_;
      this.field_178213_b = p_i46232_2_;
      this.field_178214_c = p_i46232_3_;
   }

   public int[] func_178209_a() {
      return this.field_178215_a;
   }

   public boolean func_178212_b() {
      return this.field_178213_b != -1;
   }

   public int func_178211_c() {
      return this.field_178213_b;
   }

   public EnumFacing func_178210_d() {
      return this.field_178214_c;
   }
}
