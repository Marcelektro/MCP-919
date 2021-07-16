package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S25PacketBlockBreakAnim implements Packet<INetHandlerPlayClient> {
   private int field_148852_a;
   private BlockPos field_179822_b;
   private int field_148849_e;

   public S25PacketBlockBreakAnim() {
   }

   public S25PacketBlockBreakAnim(int p_i45991_1_, BlockPos p_i45991_2_, int p_i45991_3_) {
      this.field_148852_a = p_i45991_1_;
      this.field_179822_b = p_i45991_2_;
      this.field_148849_e = p_i45991_3_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_148852_a = p_148837_1_.func_150792_a();
      this.field_179822_b = p_148837_1_.func_179259_c();
      this.field_148849_e = p_148837_1_.readUnsignedByte();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_148852_a);
      p_148840_1_.func_179255_a(this.field_179822_b);
      p_148840_1_.writeByte(this.field_148849_e);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147294_a(this);
   }

   public int func_148845_c() {
      return this.field_148852_a;
   }

   public BlockPos func_179821_b() {
      return this.field_179822_b;
   }

   public int func_148846_g() {
      return this.field_148849_e;
   }
}
