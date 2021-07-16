package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S02PacketChat implements Packet<INetHandlerPlayClient> {
   private IChatComponent field_148919_a;
   private byte field_179842_b;

   public S02PacketChat() {
   }

   public S02PacketChat(IChatComponent p_i45179_1_) {
      this(p_i45179_1_, (byte)1);
   }

   public S02PacketChat(IChatComponent p_i45986_1_, byte p_i45986_2_) {
      this.field_148919_a = p_i45986_1_;
      this.field_179842_b = p_i45986_2_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_148919_a = p_148837_1_.func_179258_d();
      this.field_179842_b = p_148837_1_.readByte();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_179256_a(this.field_148919_a);
      p_148840_1_.writeByte(this.field_179842_b);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147251_a(this);
   }

   public IChatComponent func_148915_c() {
      return this.field_148919_a;
   }

   public boolean func_148916_d() {
      return this.field_179842_b == 1 || this.field_179842_b == 2;
   }

   public byte func_179841_c() {
      return this.field_179842_b;
   }
}
