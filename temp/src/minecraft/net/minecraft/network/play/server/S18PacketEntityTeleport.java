package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S18PacketEntityTeleport implements Packet<INetHandlerPlayClient> {
   private int field_149458_a;
   private int field_149456_b;
   private int field_149457_c;
   private int field_149454_d;
   private byte field_149455_e;
   private byte field_149453_f;
   private boolean field_179698_g;

   public S18PacketEntityTeleport() {
   }

   public S18PacketEntityTeleport(Entity p_i45233_1_) {
      this.field_149458_a = p_i45233_1_.func_145782_y();
      this.field_149456_b = MathHelper.func_76128_c(p_i45233_1_.field_70165_t * 32.0D);
      this.field_149457_c = MathHelper.func_76128_c(p_i45233_1_.field_70163_u * 32.0D);
      this.field_149454_d = MathHelper.func_76128_c(p_i45233_1_.field_70161_v * 32.0D);
      this.field_149455_e = (byte)((int)(p_i45233_1_.field_70177_z * 256.0F / 360.0F));
      this.field_149453_f = (byte)((int)(p_i45233_1_.field_70125_A * 256.0F / 360.0F));
      this.field_179698_g = p_i45233_1_.field_70122_E;
   }

   public S18PacketEntityTeleport(int p_i45949_1_, int p_i45949_2_, int p_i45949_3_, int p_i45949_4_, byte p_i45949_5_, byte p_i45949_6_, boolean p_i45949_7_) {
      this.field_149458_a = p_i45949_1_;
      this.field_149456_b = p_i45949_2_;
      this.field_149457_c = p_i45949_3_;
      this.field_149454_d = p_i45949_4_;
      this.field_149455_e = p_i45949_5_;
      this.field_149453_f = p_i45949_6_;
      this.field_179698_g = p_i45949_7_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149458_a = p_148837_1_.func_150792_a();
      this.field_149456_b = p_148837_1_.readInt();
      this.field_149457_c = p_148837_1_.readInt();
      this.field_149454_d = p_148837_1_.readInt();
      this.field_149455_e = p_148837_1_.readByte();
      this.field_149453_f = p_148837_1_.readByte();
      this.field_179698_g = p_148837_1_.readBoolean();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_149458_a);
      p_148840_1_.writeInt(this.field_149456_b);
      p_148840_1_.writeInt(this.field_149457_c);
      p_148840_1_.writeInt(this.field_149454_d);
      p_148840_1_.writeByte(this.field_149455_e);
      p_148840_1_.writeByte(this.field_149453_f);
      p_148840_1_.writeBoolean(this.field_179698_g);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147275_a(this);
   }

   public int func_149451_c() {
      return this.field_149458_a;
   }

   public int func_149449_d() {
      return this.field_149456_b;
   }

   public int func_149448_e() {
      return this.field_149457_c;
   }

   public int func_149446_f() {
      return this.field_149454_d;
   }

   public byte func_149450_g() {
      return this.field_149455_e;
   }

   public byte func_149447_h() {
      return this.field_149453_f;
   }

   public boolean func_179697_g() {
      return this.field_179698_g;
   }
}
