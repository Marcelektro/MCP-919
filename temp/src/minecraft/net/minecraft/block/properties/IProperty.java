package net.minecraft.block.properties;

import java.util.Collection;

public interface IProperty<T extends Comparable<T>> {
   String func_177701_a();

   Collection<T> func_177700_c();

   Class<T> func_177699_b();

   String func_177702_a(T var1);
}
