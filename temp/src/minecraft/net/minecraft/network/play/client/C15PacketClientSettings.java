package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C15PacketClientSettings implements Packet<INetHandlerPlayServer> {
   private String field_149530_a;
   private int field_149528_b;
   private EntityPlayer.EnumChatVisibility field_149529_c;
   private boolean field_149526_d;
   private int field_179711_e;

   public C15PacketClientSettings() {
   }

   public C15PacketClientSettings(String p_i45946_1_, int p_i45946_2_, EntityPlayer.EnumChatVisibility p_i45946_3_, boolean p_i45946_4_, int p_i45946_5_) {
      this.field_149530_a = p_i45946_1_;
      this.field_149528_b = p_i45946_2_;
      this.field_149529_c = p_i45946_3_;
      this.field_149526_d = p_i45946_4_;
      this.field_179711_e = p_i45946_5_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149530_a = p_148837_1_.func_150789_c(7);
      this.field_149528_b = p_148837_1_.readByte();
      this.field_149529_c = EntityPlayer.EnumChatVisibility.func_151426_a(p_148837_1_.readByte());
      this.field_149526_d = p_148837_1_.readBoolean();
      this.field_179711_e = p_148837_1_.readUnsignedByte();
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_180714_a(this.field_149530_a);
      p_148840_1_.writeByte(this.field_149528_b);
      p_148840_1_.writeByte(this.field_149529_c.func_151428_a());
      p_148840_1_.writeBoolean(this.field_149526_d);
      p_148840_1_.writeByte(this.field_179711_e);
   }

   public void func_148833_a(INetHandlerPlayServer p_148833_1_) {
      p_148833_1_.func_147352_a(this);
   }

   public String func_149524_c() {
      return this.field_149530_a;
   }

   public EntityPlayer.EnumChatVisibility func_149523_e() {
      return this.field_149529_c;
   }

   public boolean func_149520_f() {
      return this.field_149526_d;
   }

   public int func_149521_d() {
      return this.field_179711_e;
   }
}
