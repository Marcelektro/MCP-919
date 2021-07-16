package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1DPacketEntityEffect implements Packet<INetHandlerPlayClient> {
   private int field_149434_a;
   private byte field_149432_b;
   private byte field_149433_c;
   private int field_149431_d;
   private byte field_179708_e;

   public S1DPacketEntityEffect() {
   }

   public S1DPacketEntityEffect(int p_i45237_1_, PotionEffect p_i45237_2_) {
      this.field_149434_a = p_i45237_1_;
      this.field_149432_b = (byte)(p_i45237_2_.func_76456_a() & 255);
      this.field_149433_c = (byte)(p_i45237_2_.func_76458_c() & 255);
      if(p_i45237_2_.func_76459_b() > 32767) {
         this.field_149431_d = 32767;
      } else {
         this.field_149431_d = p_i45237_2_.func_76459_b();
      }

      this.field_179708_e = (byte)(p_i45237_2_.func_180154_f()?1:0);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149434_a = p_148837_1_.func_150792_a();
      this.field_149432_b = p_148837_1_.readByte();
      this.field_149433_c = p_148837_1_.readByte();
      this.field_149431_d = p_148837_1_.func_150792_a();
      this.field_179708_e = p_148837_1_.readByte();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_150787_b(this.field_149434_a);
      p_148840_1_.writeByte(this.field_149432_b);
      p_148840_1_.writeByte(this.field_149433_c);
      p_148840_1_.func_150787_b(this.field_149431_d);
      p_148840_1_.writeByte(this.field_179708_e);
   }

   public boolean func_149429_c() {
      return this.field_149431_d == 32767;
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147260_a(this);
   }

   public int func_149426_d() {
      return this.field_149434_a;
   }

   public byte func_149427_e() {
      return this.field_149432_b;
   }

   public byte func_149428_f() {
      return this.field_149433_c;
   }

   public int func_180755_e() {
      return this.field_149431_d;
   }

   public boolean func_179707_f() {
      return this.field_179708_e != 0;
   }
}
