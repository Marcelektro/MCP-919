package net.minecraft.client.renderer;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.EnumWorldBlockLayer;

public class RegionRenderCacheBuilder {
   private final WorldRenderer[] field_179040_a = new WorldRenderer[EnumWorldBlockLayer.values().length];

   public RegionRenderCacheBuilder() {
      this.field_179040_a[EnumWorldBlockLayer.SOLID.ordinal()] = new WorldRenderer(2097152);
      this.field_179040_a[EnumWorldBlockLayer.CUTOUT.ordinal()] = new WorldRenderer(131072);
      this.field_179040_a[EnumWorldBlockLayer.CUTOUT_MIPPED.ordinal()] = new WorldRenderer(131072);
      this.field_179040_a[EnumWorldBlockLayer.TRANSLUCENT.ordinal()] = new WorldRenderer(262144);
   }

   public WorldRenderer func_179038_a(EnumWorldBlockLayer p_179038_1_) {
      return this.field_179040_a[p_179038_1_.ordinal()];
   }

   public WorldRenderer func_179039_a(int p_179039_1_) {
      return this.field_179040_a[p_179039_1_];
   }
}
