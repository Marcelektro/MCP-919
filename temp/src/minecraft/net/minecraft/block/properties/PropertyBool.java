package net.minecraft.block.properties;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import net.minecraft.block.properties.PropertyHelper;

public class PropertyBool extends PropertyHelper<Boolean> {
   private final ImmutableSet<Boolean> field_177717_a = ImmutableSet.<Boolean>of(Boolean.valueOf(true), Boolean.valueOf(false));

   protected PropertyBool(String p_i45651_1_) {
      super(p_i45651_1_, Boolean.class);
   }

   public Collection<Boolean> func_177700_c() {
      return this.field_177717_a;
   }

   public static PropertyBool func_177716_a(String p_177716_0_) {
      return new PropertyBool(p_177716_0_);
   }

   public String func_177702_a(Boolean p_177702_1_) {
      return p_177702_1_.toString();
   }
}
