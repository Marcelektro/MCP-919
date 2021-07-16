package net.minecraft.network.login.client;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;

public class C00PacketLoginStart implements Packet<INetHandlerLoginServer> {
   private GameProfile field_149305_a;

   public C00PacketLoginStart() {
   }

   public C00PacketLoginStart(GameProfile p_i45270_1_) {
      this.field_149305_a = p_i45270_1_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149305_a = new GameProfile((UUID)null, p_148837_1_.func_150789_c(16));
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_180714_a(this.field_149305_a.getName());
   }

   public void func_148833_a(INetHandlerLoginServer p_148833_1_) {
      p_148833_1_.func_147316_a(this);
   }

   public GameProfile func_149304_c() {
      return this.field_149305_a;
   }
}
