package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class C12PacketUpdateSign implements Packet<INetHandlerPlayServer> {
   private BlockPos field_179723_a;
   private IChatComponent[] field_149590_d;

   public C12PacketUpdateSign() {
   }

   public C12PacketUpdateSign(BlockPos p_i45933_1_, IChatComponent[] p_i45933_2_) {
      this.field_179723_a = p_i45933_1_;
      this.field_149590_d = new IChatComponent[]{p_i45933_2_[0], p_i45933_2_[1], p_i45933_2_[2], p_i45933_2_[3]};
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179723_a = p_148837_1_.func_179259_c();
      this.field_149590_d = new IChatComponent[4];

      for(int i = 0; i < 4; ++i) {
         String s = p_148837_1_.func_150789_c(384);
         IChatComponent ichatcomponent = IChatComponent.Serializer.func_150699_a(s);
         this.field_149590_d[i] = ichatcomponent;
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_179255_a(this.field_179723_a);

      for(int i = 0; i < 4; ++i) {
         IChatComponent ichatcomponent = this.field_149590_d[i];
         String s = IChatComponent.Serializer.func_150696_a(ichatcomponent);
         p_148840_1_.func_180714_a(s);
      }

   }

   public void func_148833_a(INetHandlerPlayServer p_148833_1_) {
      p_148833_1_.func_147343_a(this);
   }

   public BlockPos func_179722_a() {
      return this.field_179723_a;
   }

   public IChatComponent[] func_180768_b() {
      return this.field_149590_d;
   }
}
