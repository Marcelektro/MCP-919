package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;

public class C08PacketPlayerBlockPlacement implements Packet<INetHandlerPlayServer> {
   private static final BlockPos field_179726_a = new BlockPos(-1, -1, -1);
   private BlockPos field_179725_b;
   private int field_149579_d;
   private ItemStack field_149580_e;
   private float field_149577_f;
   private float field_149578_g;
   private float field_149584_h;

   public C08PacketPlayerBlockPlacement() {
   }

   public C08PacketPlayerBlockPlacement(ItemStack p_i45930_1_) {
      this(field_179726_a, 255, p_i45930_1_, 0.0F, 0.0F, 0.0F);
   }

   public C08PacketPlayerBlockPlacement(BlockPos p_i45931_1_, int p_i45931_2_, ItemStack p_i45931_3_, float p_i45931_4_, float p_i45931_5_, float p_i45931_6_) {
      this.field_179725_b = p_i45931_1_;
      this.field_149579_d = p_i45931_2_;
      this.field_149580_e = p_i45931_3_ != null?p_i45931_3_.func_77946_l():null;
      this.field_149577_f = p_i45931_4_;
      this.field_149578_g = p_i45931_5_;
      this.field_149584_h = p_i45931_6_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179725_b = p_148837_1_.func_179259_c();
      this.field_149579_d = p_148837_1_.readUnsignedByte();
      this.field_149580_e = p_148837_1_.func_150791_c();
      this.field_149577_f = (float)p_148837_1_.readUnsignedByte() / 16.0F;
      this.field_149578_g = (float)p_148837_1_.readUnsignedByte() / 16.0F;
      this.field_149584_h = (float)p_148837_1_.readUnsignedByte() / 16.0F;
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_179255_a(this.field_179725_b);
      p_148840_1_.writeByte(this.field_149579_d);
      p_148840_1_.func_150788_a(this.field_149580_e);
      p_148840_1_.writeByte((int)(this.field_149577_f * 16.0F));
      p_148840_1_.writeByte((int)(this.field_149578_g * 16.0F));
      p_148840_1_.writeByte((int)(this.field_149584_h * 16.0F));
   }

   public void func_148833_a(INetHandlerPlayServer p_148833_1_) {
      p_148833_1_.func_147346_a(this);
   }

   public BlockPos func_179724_a() {
      return this.field_179725_b;
   }

   public int func_149568_f() {
      return this.field_149579_d;
   }

   public ItemStack func_149574_g() {
      return this.field_149580_e;
   }

   public float func_149573_h() {
      return this.field_149577_f;
   }

   public float func_149569_i() {
      return this.field_149578_g;
   }

   public float func_149575_j() {
      return this.field_149584_h;
   }
}
