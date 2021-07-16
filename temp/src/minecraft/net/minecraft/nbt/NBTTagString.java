package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;

public class NBTTagString extends NBTBase {
   private String field_74751_a;

   public NBTTagString() {
      this.field_74751_a = "";
   }

   public NBTTagString(String p_i1389_1_) {
      this.field_74751_a = p_i1389_1_;
      if(p_i1389_1_ == null) {
         throw new IllegalArgumentException("Empty string not allowed");
      }
   }

   void func_74734_a(DataOutput p_74734_1_) throws IOException {
      p_74734_1_.writeUTF(this.field_74751_a);
   }

   void func_152446_a(DataInput p_152446_1_, int p_152446_2_, NBTSizeTracker p_152446_3_) throws IOException {
      p_152446_3_.func_152450_a(288L);
      this.field_74751_a = p_152446_1_.readUTF();
      p_152446_3_.func_152450_a((long)(16 * this.field_74751_a.length()));
   }

   public byte func_74732_a() {
      return (byte)8;
   }

   public String toString() {
      return "\"" + this.field_74751_a.replace("\"", "\\\"") + "\"";
   }

   public NBTBase func_74737_b() {
      return new NBTTagString(this.field_74751_a);
   }

   public boolean func_82582_d() {
      return this.field_74751_a.isEmpty();
   }

   public boolean equals(Object p_equals_1_) {
      if(!super.equals(p_equals_1_)) {
         return false;
      } else {
         NBTTagString nbttagstring = (NBTTagString)p_equals_1_;
         return this.field_74751_a == null && nbttagstring.field_74751_a == null || this.field_74751_a != null && this.field_74751_a.equals(nbttagstring.field_74751_a);
      }
   }

   public int hashCode() {
      return super.hashCode() ^ this.field_74751_a.hashCode();
   }

   public String func_150285_a_() {
      return this.field_74751_a;
   }
}
