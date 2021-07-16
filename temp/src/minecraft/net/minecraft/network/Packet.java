package net.minecraft.network;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;

public interface Packet<T extends INetHandler> {
   void func_148837_a(PacketBuffer var1) throws IOException;

   void func_148840_b(PacketBuffer var1) throws IOException;

   void func_148833_a(T var1);
}
