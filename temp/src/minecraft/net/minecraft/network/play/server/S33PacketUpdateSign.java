package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class S33PacketUpdateSign implements Packet<INetHandlerPlayClient> {
   private World field_179706_a;
   private BlockPos field_179705_b;
   private IChatComponent[] field_149349_d;

   public S33PacketUpdateSign() {
   }

   public S33PacketUpdateSign(World p_i45951_1_, BlockPos p_i45951_2_, IChatComponent[] p_i45951_3_) {
      this.field_179706_a = p_i45951_1_;
      this.field_179705_b = p_i45951_2_;
      this.field_149349_d = new IChatComponent[]{p_i45951_3_[0], p_i45951_3_[1], p_i45951_3_[2], p_i45951_3_[3]};
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179705_b = p_148837_1_.func_179259_c();
      this.field_149349_d = new IChatComponent[4];

      for(int i = 0; i < 4; ++i) {
         this.field_149349_d[i] = p_148837_1_.func_179258_d();
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_179255_a(this.field_179705_b);

      for(int i = 0; i < 4; ++i) {
         p_148840_1_.func_179256_a(this.field_149349_d[i]);
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147248_a(this);
   }

   public BlockPos func_179704_a() {
      return this.field_179705_b;
   }

   public IChatComponent[] func_180753_b() {
      return this.field_149349_d;
   }
}
