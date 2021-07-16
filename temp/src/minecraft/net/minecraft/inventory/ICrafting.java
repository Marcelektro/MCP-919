package net.minecraft.inventory;

import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ICrafting {
   void func_71110_a(Container var1, List<ItemStack> var2);

   void func_71111_a(Container var1, int var2, ItemStack var3);

   void func_71112_a(Container var1, int var2, int var3);

   void func_175173_a(Container var1, IInventory var2);
}
