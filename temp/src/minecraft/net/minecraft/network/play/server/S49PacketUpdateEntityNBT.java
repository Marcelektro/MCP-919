package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S49PacketUpdateEntityNBT implements Packet<INetHandlerPlayClient> {
   private int field_179766_a;
   private NBTTagCompound field_179765_b;

   public S49PacketUpdateEntityNBT() {
   }

   public S49PacketUpdateEntityNBT(int p_i45979_1_, NBTTagCompound p_i45979_2_) {
      this.field_179766_a = p_i45979_1_;
      this.field_179765_b = p_i45979_2_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179766_a = p_148837_1_.func_150792_a();
      this.field_179765_b = p_148837_1_.func_150793_b();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_179766_a);
      p_148840_1_.func_150786_a(this.field_179765_b);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_175097_a(this);
   }

   public NBTTagCompound func_179763_a() {
      return this.field_179765_b;
   }

   public Entity func_179764_a(World p_179764_1_) {
      return p_179764_1_.func_73045_a(this.field_179766_a);
   }
}
