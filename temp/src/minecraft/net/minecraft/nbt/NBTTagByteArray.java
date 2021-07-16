package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagByteArray extends NBTBase {
   private byte[] field_74754_a;

   NBTTagByteArray() {
   }

   public NBTTagByteArray(byte[] p_i45128_1_) {
      this.field_74754_a = p_i45128_1_;
   }

   void func_74734_a(DataOutput p_74734_1_) throws IOException {
      p_74734_1_.writeInt(this.field_74754_a.length);
      p_74734_1_.write(this.field_74754_a);
   }

   void func_152446_a(DataInput p_152446_1_, int p_152446_2_, NBTSizeTracker p_152446_3_) throws IOException {
      p_152446_3_.func_152450_a(192L);
      int i = p_152446_1_.readInt();
      p_152446_3_.func_152450_a((long)(8 * i));
      this.field_74754_a = new byte[i];
      p_152446_1_.readFully(this.field_74754_a);
   }

   public byte func_74732_a() {
      return (byte)7;
   }

   public String toString() {
      return "[" + this.field_74754_a.length + " bytes]";
   }

   public NBTBase func_74737_b() {
      byte[] abyte = new byte[this.field_74754_a.length];
      System.arraycopy(this.field_74754_a, 0, abyte, 0, this.field_74754_a.length);
      return new NBTTagByteArray(abyte);
   }

   public boolean equals(Object p_equals_1_) {
      return super.equals(p_equals_1_)?Arrays.equals(this.field_74754_a, ((NBTTagByteArray)p_equals_1_).field_74754_a):false;
   }

   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.field_74754_a);
   }

   public byte[] func_150292_c() {
      return this.field_74754_a;
   }
}
