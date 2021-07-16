package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S30PacketWindowItems implements Packet<INetHandlerPlayClient> {
   private int field_148914_a;
   private ItemStack[] field_148913_b;

   public S30PacketWindowItems() {
   }

   public S30PacketWindowItems(int p_i45186_1_, List<ItemStack> p_i45186_2_) {
      this.field_148914_a = p_i45186_1_;
      this.field_148913_b = new ItemStack[p_i45186_2_.size()];

      for(int i = 0; i < this.field_148913_b.length; ++i) {
         ItemStack itemstack = (ItemStack)p_i45186_2_.get(i);
         this.field_148913_b[i] = itemstack == null?null:itemstack.func_77946_l();
      }

   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_148914_a = p_148837_1_.readUnsignedByte();
      int i = p_148837_1_.readShort();
      this.field_148913_b = new ItemStack[i];

      for(int j = 0; j < i; ++j) {
         this.field_148913_b[j] = p_148837_1_.func_150791_c();
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeByte(this.field_148914_a);
      p_148840_1_.writeShort(this.field_148913_b.length);

      for(ItemStack itemstack : this.field_148913_b) {
         p_148840_1_.func_150788_a(itemstack);
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147241_a(this);
   }

   public int func_148911_c() {
      return this.field_148914_a;
   }

   public ItemStack[] func_148910_d() {
      return this.field_148913_b;
   }
}
