package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1FPacketSetExperience implements Packet<INetHandlerPlayClient> {
   private float field_149401_a;
   private int field_149399_b;
   private int field_149400_c;

   public S1FPacketSetExperience() {
   }

   public S1FPacketSetExperience(float p_i45222_1_, int p_i45222_2_, int p_i45222_3_) {
      this.field_149401_a = p_i45222_1_;
      this.field_149399_b = p_i45222_2_;
      this.field_149400_c = p_i45222_3_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149401_a = p_148837_1_.readFloat();
      this.field_149400_c = p_148837_1_.func_150792_a();
      this.field_149399_b = p_148837_1_.func_150792_a();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeFloat(this.field_149401_a);
      p_148840_1_.func_150787_b(this.field_149400_c);
      p_148840_1_.func_150787_b(this.field_149399_b);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147295_a(this);
   }

   public float func_149397_c() {
      return this.field_149401_a;
   }

   public int func_149396_d() {
      return this.field_149399_b;
   }

   public int func_149395_e() {
      return this.field_149400_c;
   }
}
