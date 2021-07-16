package net.minecraft.client.resources.data;

import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.IChatComponent;

public class PackMetadataSection implements IMetadataSection {
   private final IChatComponent field_110464_a;
   private final int field_110463_b;

   public PackMetadataSection(IChatComponent p_i1034_1_, int p_i1034_2_) {
      this.field_110464_a = p_i1034_1_;
      this.field_110463_b = p_i1034_2_;
   }

   public IChatComponent func_152805_a() {
      return this.field_110464_a;
   }

   public int func_110462_b() {
      return this.field_110463_b;
   }
}
