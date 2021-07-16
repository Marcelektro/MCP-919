package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S31PacketWindowProperty implements Packet<INetHandlerPlayClient> {
   private int field_149186_a;
   private int field_149184_b;
   private int field_149185_c;

   public S31PacketWindowProperty() {
   }

   public S31PacketWindowProperty(int p_i45187_1_, int p_i45187_2_, int p_i45187_3_) {
      this.field_149186_a = p_i45187_1_;
      this.field_149184_b = p_i45187_2_;
      this.field_149185_c = p_i45187_3_;
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147245_a(this);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149186_a = p_148837_1_.readUnsignedByte();
      this.field_149184_b = p_148837_1_.readShort();
      this.field_149185_c = p_148837_1_.readShort();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeByte(this.field_149186_a);
      p_148840_1_.writeShort(this.field_149184_b);
      p_148840_1_.writeShort(this.field_149185_c);
   }

   public int func_149182_c() {
      return this.field_149186_a;
   }

   public int func_149181_d() {
      return this.field_149184_b;
   }

   public int func_149180_e() {
      return this.field_149185_c;
   }
}
