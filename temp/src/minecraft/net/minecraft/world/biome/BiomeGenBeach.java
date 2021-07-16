package net.minecraft.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBeach extends BiomeGenBase {
   public BiomeGenBeach(int p_i1969_1_) {
      super(p_i1969_1_);
      this.field_76762_K.clear();
      this.field_76752_A = Blocks.field_150354_m.func_176223_P();
      this.field_76753_B = Blocks.field_150354_m.func_176223_P();
      this.field_76760_I.field_76832_z = -999;
      this.field_76760_I.field_76804_C = 0;
      this.field_76760_I.field_76799_E = 0;
      this.field_76760_I.field_76800_F = 0;
   }
}
