package net.minecraft.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenEnd extends BiomeGenBase {
   public BiomeGenEnd(int p_i1990_1_) {
      super(p_i1990_1_);
      this.field_76761_J.clear();
      this.field_76762_K.clear();
      this.field_76755_L.clear();
      this.field_82914_M.clear();
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
      this.field_76752_A = Blocks.field_150346_d.func_176223_P();
      this.field_76753_B = Blocks.field_150346_d.func_176223_P();
      this.field_76760_I = new BiomeEndDecorator();
   }

   public int func_76731_a(float p_76731_1_) {
      return 0;
   }
}
