package net.minecraft.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityComparator extends TileEntity {
   private int field_145997_a;

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74768_a("OutputSignal", this.field_145997_a);
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_145997_a = p_145839_1_.func_74762_e("OutputSignal");
   }

   public int func_145996_a() {
      return this.field_145997_a;
   }

   public void func_145995_a(int p_145995_1_) {
      this.field_145997_a = p_145995_1_;
   }
}
