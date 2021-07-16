package net.minecraft.world.biome;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenMushroomIsland extends BiomeGenBase {
   public BiomeGenMushroomIsland(int p_i1984_1_) {
      super(p_i1984_1_);
      this.field_76760_I.field_76832_z = -100;
      this.field_76760_I.field_76802_A = -100;
      this.field_76760_I.field_76803_B = -100;
      this.field_76760_I.field_76798_D = 1;
      this.field_76760_I.field_76807_J = 1;
      this.field_76752_A = Blocks.field_150391_bh.func_176223_P();
      this.field_76761_J.clear();
      this.field_76762_K.clear();
      this.field_76755_L.clear();
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
   }
}
