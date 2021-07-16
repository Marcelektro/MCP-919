package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S2DPacketOpenWindow implements Packet<INetHandlerPlayClient> {
   private int field_148909_a;
   private String field_148907_b;
   private IChatComponent field_148908_c;
   private int field_148905_d;
   private int field_148904_f;

   public S2DPacketOpenWindow() {
   }

   public S2DPacketOpenWindow(int p_i45981_1_, String p_i45981_2_, IChatComponent p_i45981_3_) {
      this(p_i45981_1_, p_i45981_2_, p_i45981_3_, 0);
   }

   public S2DPacketOpenWindow(int p_i45982_1_, String p_i45982_2_, IChatComponent p_i45982_3_, int p_i45982_4_) {
      this.field_148909_a = p_i45982_1_;
      this.field_148907_b = p_i45982_2_;
      this.field_148908_c = p_i45982_3_;
      this.field_148905_d = p_i45982_4_;
   }

   public S2DPacketOpenWindow(int p_i45983_1_, String p_i45983_2_, IChatComponent p_i45983_3_, int p_i45983_4_, int p_i45983_5_) {
      this(p_i45983_1_, p_i45983_2_, p_i45983_3_, p_i45983_4_);
      this.field_148904_f = p_i45983_5_;
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147265_a(this);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_148909_a = p_148837_1_.readUnsignedByte();
      this.field_148907_b = p_148837_1_.func_150789_c(32);
      this.field_148908_c = p_148837_1_.func_179258_d();
      this.field_148905_d = p_148837_1_.readUnsignedByte();
      if(this.field_148907_b.equals("EntityHorse")) {
         this.field_148904_f = p_148837_1_.readInt();
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeByte(this.field_148909_a);
      p_148840_1_.func_180714_a(this.field_148907_b);
      p_148840_1_.func_179256_a(this.field_148908_c);
      p_148840_1_.writeByte(this.field_148905_d);
      if(this.field_148907_b.equals("EntityHorse")) {
         p_148840_1_.writeInt(this.field_148904_f);
      }

   }

   public int func_148901_c() {
      return this.field_148909_a;
   }

   public String func_148902_e() {
      return this.field_148907_b;
   }

   public IChatComponent func_179840_c() {
      return this.field_148908_c;
   }

   public int func_148898_f() {
      return this.field_148905_d;
   }

   public int func_148897_h() {
      return this.field_148904_f;
   }

   public boolean func_148900_g() {
      return this.field_148905_d > 0;
   }
}
