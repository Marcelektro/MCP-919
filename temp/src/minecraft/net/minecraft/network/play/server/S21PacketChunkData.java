package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class S21PacketChunkData implements Packet<INetHandlerPlayClient> {
   private int field_149284_a;
   private int field_149282_b;
   private S21PacketChunkData.Extracted field_179758_c;
   private boolean field_149279_g;

   public S21PacketChunkData() {
   }

   public S21PacketChunkData(Chunk p_i45196_1_, boolean p_i45196_2_, int p_i45196_3_) {
      this.field_149284_a = p_i45196_1_.field_76635_g;
      this.field_149282_b = p_i45196_1_.field_76647_h;
      this.field_149279_g = p_i45196_2_;
      this.field_179758_c = func_179756_a(p_i45196_1_, p_i45196_2_, !p_i45196_1_.func_177412_p().field_73011_w.func_177495_o(), p_i45196_3_);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149284_a = p_148837_1_.readInt();
      this.field_149282_b = p_148837_1_.readInt();
      this.field_149279_g = p_148837_1_.readBoolean();
      this.field_179758_c = new S21PacketChunkData.Extracted();
      this.field_179758_c.field_150280_b = p_148837_1_.readShort();
      this.field_179758_c.field_150282_a = p_148837_1_.func_179251_a();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeInt(this.field_149284_a);
      p_148840_1_.writeInt(this.field_149282_b);
      p_148840_1_.writeBoolean(this.field_149279_g);
      p_148840_1_.writeShort((short)(this.field_179758_c.field_150280_b & '\uffff'));
      p_148840_1_.func_179250_a(this.field_179758_c.field_150282_a);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147263_a(this);
   }

   public byte[] func_149272_d() {
      return this.field_179758_c.field_150282_a;
   }

   protected static int func_180737_a(int p_180737_0_, boolean p_180737_1_, boolean p_180737_2_) {
      int i = p_180737_0_ * 2 * 16 * 16 * 16;
      int j = p_180737_0_ * 16 * 16 * 16 / 2;
      int k = p_180737_1_?p_180737_0_ * 16 * 16 * 16 / 2:0;
      int l = p_180737_2_?256:0;
      return i + j + k + l;
   }

   public static S21PacketChunkData.Extracted func_179756_a(Chunk p_179756_0_, boolean p_179756_1_, boolean p_179756_2_, int p_179756_3_) {
      ExtendedBlockStorage[] aextendedblockstorage = p_179756_0_.func_76587_i();
      S21PacketChunkData.Extracted s21packetchunkdata$extracted = new S21PacketChunkData.Extracted();
      List<ExtendedBlockStorage> list = Lists.<ExtendedBlockStorage>newArrayList();

      for(int i = 0; i < aextendedblockstorage.length; ++i) {
         ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[i];
         if(extendedblockstorage != null && (!p_179756_1_ || !extendedblockstorage.func_76663_a()) && (p_179756_3_ & 1 << i) != 0) {
            s21packetchunkdata$extracted.field_150280_b |= 1 << i;
            list.add(extendedblockstorage);
         }
      }

      s21packetchunkdata$extracted.field_150282_a = new byte[func_180737_a(Integer.bitCount(s21packetchunkdata$extracted.field_150280_b), p_179756_2_, p_179756_1_)];
      int j = 0;

      for(ExtendedBlockStorage extendedblockstorage1 : list) {
         char[] achar = extendedblockstorage1.func_177487_g();

         for(char c0 : achar) {
            s21packetchunkdata$extracted.field_150282_a[j++] = (byte)(c0 & 255);
            s21packetchunkdata$extracted.field_150282_a[j++] = (byte)(c0 >> 8 & 255);
         }
      }

      for(ExtendedBlockStorage extendedblockstorage2 : list) {
         j = func_179757_a(extendedblockstorage2.func_76661_k().func_177481_a(), s21packetchunkdata$extracted.field_150282_a, j);
      }

      if(p_179756_2_) {
         for(ExtendedBlockStorage extendedblockstorage3 : list) {
            j = func_179757_a(extendedblockstorage3.func_76671_l().func_177481_a(), s21packetchunkdata$extracted.field_150282_a, j);
         }
      }

      if(p_179756_1_) {
         func_179757_a(p_179756_0_.func_76605_m(), s21packetchunkdata$extracted.field_150282_a, j);
      }

      return s21packetchunkdata$extracted;
   }

   private static int func_179757_a(byte[] p_179757_0_, byte[] p_179757_1_, int p_179757_2_) {
      System.arraycopy(p_179757_0_, 0, p_179757_1_, p_179757_2_, p_179757_0_.length);
      return p_179757_2_ + p_179757_0_.length;
   }

   public int func_149273_e() {
      return this.field_149284_a;
   }

   public int func_149271_f() {
      return this.field_149282_b;
   }

   public int func_149276_g() {
      return this.field_179758_c.field_150280_b;
   }

   public boolean func_149274_i() {
      return this.field_149279_g;
   }

   public static class Extracted {
      public byte[] field_150282_a;
      public int field_150280_b;
   }
}
