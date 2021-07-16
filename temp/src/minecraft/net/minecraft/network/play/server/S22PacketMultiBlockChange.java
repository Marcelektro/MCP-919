package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;

public class S22PacketMultiBlockChange implements Packet<INetHandlerPlayClient> {
   private ChunkCoordIntPair field_148925_b;
   private S22PacketMultiBlockChange.BlockUpdateData[] field_179845_b;

   public S22PacketMultiBlockChange() {
   }

   public S22PacketMultiBlockChange(int p_i45181_1_, short[] p_i45181_2_, Chunk p_i45181_3_) {
      this.field_148925_b = new ChunkCoordIntPair(p_i45181_3_.field_76635_g, p_i45181_3_.field_76647_h);
      this.field_179845_b = new S22PacketMultiBlockChange.BlockUpdateData[p_i45181_1_];

      for(int i = 0; i < this.field_179845_b.length; ++i) {
         this.field_179845_b[i] = new S22PacketMultiBlockChange.BlockUpdateData(p_i45181_2_[i], p_i45181_3_);
      }

   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_148925_b = new ChunkCoordIntPair(p_148837_1_.readInt(), p_148837_1_.readInt());
      this.field_179845_b = new S22PacketMultiBlockChange.BlockUpdateData[p_148837_1_.func_150792_a()];

      for(int i = 0; i < this.field_179845_b.length; ++i) {
         this.field_179845_b[i] = new S22PacketMultiBlockChange.BlockUpdateData(p_148837_1_.readShort(), (IBlockState)Block.field_176229_d.func_148745_a(p_148837_1_.func_150792_a()));
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeInt(this.field_148925_b.field_77276_a);
      p_148840_1_.writeInt(this.field_148925_b.field_77275_b);
      p_148840_1_.func_150787_b(this.field_179845_b.length);

      for(S22PacketMultiBlockChange.BlockUpdateData s22packetmultiblockchange$blockupdatedata : this.field_179845_b) {
         p_148840_1_.writeShort(s22packetmultiblockchange$blockupdatedata.func_180089_b());
         p_148840_1_.func_150787_b(Block.field_176229_d.func_148747_b(s22packetmultiblockchange$blockupdatedata.func_180088_c()));
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147287_a(this);
   }

   public S22PacketMultiBlockChange.BlockUpdateData[] func_179844_a() {
      return this.field_179845_b;
   }

   public class BlockUpdateData {
      private final short field_180091_b;
      private final IBlockState field_180092_c;

      public BlockUpdateData(short p_i45984_2_, IBlockState p_i45984_3_) {
         this.field_180091_b = p_i45984_2_;
         this.field_180092_c = p_i45984_3_;
      }

      public BlockUpdateData(short p_i45985_2_, Chunk p_i45985_3_) {
         this.field_180091_b = p_i45985_2_;
         this.field_180092_c = p_i45985_3_.func_177435_g(this.func_180090_a());
      }

      public BlockPos func_180090_a() {
         return new BlockPos(S22PacketMultiBlockChange.this.field_148925_b.func_180331_a(this.field_180091_b >> 12 & 15, this.field_180091_b & 255, this.field_180091_b >> 8 & 15));
      }

      public short func_180089_b() {
         return this.field_180091_b;
      }

      public IBlockState func_180088_c() {
         return this.field_180092_c;
      }
   }
}
