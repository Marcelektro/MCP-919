package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenOcean extends BiomeGenBase {
   public BiomeGenOcean(int p_i1985_1_) {
      super(p_i1985_1_);
      this.field_76762_K.clear();
   }

   public BiomeGenBase.TempCategory func_150561_m() {
      return BiomeGenBase.TempCategory.OCEAN;
   }

   public void func_180622_a(World p_180622_1_, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
      super.func_180622_a(p_180622_1_, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
   }
}
