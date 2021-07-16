package net.minecraft.world.biome;

import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenHell extends BiomeGenBase {
   public BiomeGenHell(int p_i1981_1_) {
      super(p_i1981_1_);
      this.field_76761_J.clear();
      this.field_76762_K.clear();
      this.field_76755_L.clear();
      this.field_82914_M.clear();
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityGhast.class, 50, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
   }
}
