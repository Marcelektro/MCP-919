package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0FPacketConfirmTransaction implements Packet<INetHandlerPlayServer> {
   private int field_149536_a;
   private short field_149534_b;
   private boolean field_149535_c;

   public C0FPacketConfirmTransaction() {
   }

   public C0FPacketConfirmTransaction(int p_i45244_1_, short p_i45244_2_, boolean p_i45244_3_) {
      this.field_149536_a = p_i45244_1_;
      this.field_149534_b = p_i45244_2_;
      this.field_149535_c = p_i45244_3_;
   }

   public void func_148833_a(INetHandlerPlayServer p_148833_1_) {
      p_148833_1_.func_147339_a(this);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149536_a = p_148837_1_.readByte();
      this.field_149534_b = p_148837_1_.readShort();
      this.field_149535_c = p_148837_1_.readByte() != 0;
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeByte(this.field_149536_a);
      p_148840_1_.writeShort(this.field_149534_b);
      p_148840_1_.writeByte(this.field_149535_c?1:0);
   }

   public int func_149532_c() {
      return this.field_149536_a;
   }

   public short func_149533_d() {
      return this.field_149534_b;
   }
}
