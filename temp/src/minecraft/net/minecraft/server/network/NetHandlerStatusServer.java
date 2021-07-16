package net.minecraft.server.network;

import net.minecraft.network.NetworkManager;
import net.minecraft.network.status.INetHandlerStatusServer;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class NetHandlerStatusServer implements INetHandlerStatusServer {
   private static final IChatComponent field_183007_a = new ChatComponentText("Status request has been handled.");
   private final MinecraftServer field_147314_a;
   private final NetworkManager field_147313_b;
   private boolean field_183008_d;

   public NetHandlerStatusServer(MinecraftServer p_i45299_1_, NetworkManager p_i45299_2_) {
      this.field_147314_a = p_i45299_1_;
      this.field_147313_b = p_i45299_2_;
   }

   public void func_147231_a(IChatComponent p_147231_1_) {
   }

   public void func_147312_a(C00PacketServerQuery p_147312_1_) {
      if(this.field_183008_d) {
         this.field_147313_b.func_150718_a(field_183007_a);
      } else {
         this.field_183008_d = true;
         this.field_147313_b.func_179290_a(new S00PacketServerInfo(this.field_147314_a.func_147134_at()));
      }
   }

   public void func_147311_a(C01PacketPing p_147311_1_) {
      this.field_147313_b.func_179290_a(new S01PacketPong(p_147311_1_.func_149289_c()));
      this.field_147313_b.func_150718_a(field_183007_a);
   }
}
