package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S11PacketSpawnExperienceOrb implements Packet<INetHandlerPlayClient> {
   private int field_148992_a;
   private int field_148990_b;
   private int field_148991_c;
   private int field_148988_d;
   private int field_148989_e;

   public S11PacketSpawnExperienceOrb() {
   }

   public S11PacketSpawnExperienceOrb(EntityXPOrb p_i45167_1_) {
      this.field_148992_a = p_i45167_1_.func_145782_y();
      this.field_148990_b = MathHelper.func_76128_c(p_i45167_1_.field_70165_t * 32.0D);
      this.field_148991_c = MathHelper.func_76128_c(p_i45167_1_.field_70163_u * 32.0D);
      this.field_148988_d = MathHelper.func_76128_c(p_i45167_1_.field_70161_v * 32.0D);
      this.field_148989_e = p_i45167_1_.func_70526_d();
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_148992_a = p_148837_1_.func_150792_a();
      this.field_148990_b = p_148837_1_.readInt();
      this.field_148991_c = p_148837_1_.readInt();
      this.field_148988_d = p_148837_1_.readInt();
      this.field_148989_e = p_148837_1_.readShort();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_148992_a);
      p_148840_1_.writeInt(this.field_148990_b);
      p_148840_1_.writeInt(this.field_148991_c);
      p_148840_1_.writeInt(this.field_148988_d);
      p_148840_1_.writeShort(this.field_148989_e);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147286_a(this);
   }

   public int func_148985_c() {
      return this.field_148992_a;
   }

   public int func_148984_d() {
      return this.field_148990_b;
   }

   public int func_148983_e() {
      return this.field_148991_c;
   }

   public int func_148982_f() {
      return this.field_148988_d;
   }

   public int func_148986_g() {
      return this.field_148989_e;
   }
}
