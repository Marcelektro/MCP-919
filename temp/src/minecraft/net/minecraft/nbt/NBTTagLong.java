package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagLong extends NBTBase.NBTPrimitive {
   private long field_74753_a;

   NBTTagLong() {
   }

   public NBTTagLong(long p_i45134_1_) {
      this.field_74753_a = p_i45134_1_;
   }

   void func_74734_a(DataOutput p_74734_1_) throws IOException {
      p_74734_1_.writeLong(this.field_74753_a);
   }

   void func_152446_a(DataInput p_152446_1_, int p_152446_2_, NBTSizeTracker p_152446_3_) throws IOException {
      p_152446_3_.func_152450_a(128L);
      this.field_74753_a = p_152446_1_.readLong();
   }

   public byte func_74732_a() {
      return (byte)4;
   }

   public String toString() {
      return "" + this.field_74753_a + "L";
   }

   public NBTBase func_74737_b() {
      return new NBTTagLong(this.field_74753_a);
   }

   public boolean equals(Object p_equals_1_) {
      if(super.equals(p_equals_1_)) {
         NBTTagLong nbttaglong = (NBTTagLong)p_equals_1_;
         return this.field_74753_a == nbttaglong.field_74753_a;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return super.hashCode() ^ (int)(this.field_74753_a ^ this.field_74753_a >>> 32);
   }

   public long func_150291_c() {
      return this.field_74753_a;
   }

   public int func_150287_d() {
      return (int)(this.field_74753_a & -1L);
   }

   public short func_150289_e() {
      return (short)((int)(this.field_74753_a & 65535L));
   }

   public byte func_150290_f() {
      return (byte)((int)(this.field_74753_a & 255L));
   }

   public double func_150286_g() {
      return (double)this.field_74753_a;
   }

   public float func_150288_h() {
      return (float)this.field_74753_a;
   }
}
