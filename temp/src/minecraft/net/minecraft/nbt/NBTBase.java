package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

public abstract class NBTBase {
   public static final String[] field_82578_b = new String[]{"END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]"};

   abstract void func_74734_a(DataOutput var1) throws IOException;

   abstract void func_152446_a(DataInput var1, int var2, NBTSizeTracker var3) throws IOException;

   public abstract String toString();

   public abstract byte func_74732_a();

   protected static NBTBase func_150284_a(byte p_150284_0_) {
      switch(p_150284_0_) {
      case 0:
         return new NBTTagEnd();
      case 1:
         return new NBTTagByte();
      case 2:
         return new NBTTagShort();
      case 3:
         return new NBTTagInt();
      case 4:
         return new NBTTagLong();
      case 5:
         return new NBTTagFloat();
      case 6:
         return new NBTTagDouble();
      case 7:
         return new NBTTagByteArray();
      case 8:
         return new NBTTagString();
      case 9:
         return new NBTTagList();
      case 10:
         return new NBTTagCompound();
      case 11:
         return new NBTTagIntArray();
      default:
         return null;
      }
   }

   public abstract NBTBase func_74737_b();

   public boolean func_82582_d() {
      return false;
   }

   public boolean equals(Object p_equals_1_) {
      if(!(p_equals_1_ instanceof NBTBase)) {
         return false;
      } else {
         NBTBase nbtbase = (NBTBase)p_equals_1_;
         return this.func_74732_a() == nbtbase.func_74732_a();
      }
   }

   public int hashCode() {
      return this.func_74732_a();
   }

   protected String func_150285_a_() {
      return this.toString();
   }

   public abstract static class NBTPrimitive extends NBTBase {
      public abstract long func_150291_c();

      public abstract int func_150287_d();

      public abstract short func_150289_e();

      public abstract byte func_150290_f();

      public abstract double func_150286_g();

      public abstract float func_150288_h();
   }
}
