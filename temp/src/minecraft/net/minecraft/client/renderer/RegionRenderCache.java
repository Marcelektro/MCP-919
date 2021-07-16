package net.minecraft.client.renderer;

import java.util.Arrays;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class RegionRenderCache extends ChunkCache {
   private static final IBlockState field_175632_f = Blocks.field_150350_a.func_176223_P();
   private final BlockPos field_175633_g;
   private int[] field_175634_h;
   private IBlockState[] field_175635_i;

   public RegionRenderCache(World p_i46273_1_, BlockPos p_i46273_2_, BlockPos p_i46273_3_, int p_i46273_4_) {
      super(p_i46273_1_, p_i46273_2_, p_i46273_3_, p_i46273_4_);
      this.field_175633_g = p_i46273_2_.func_177973_b(new Vec3i(p_i46273_4_, p_i46273_4_, p_i46273_4_));
      int i = 8000;
      this.field_175634_h = new int[8000];
      Arrays.fill((int[])this.field_175634_h, (int)-1);
      this.field_175635_i = new IBlockState[8000];
   }

   public TileEntity func_175625_s(BlockPos p_175625_1_) {
      int i = (p_175625_1_.func_177958_n() >> 4) - this.field_72818_a;
      int j = (p_175625_1_.func_177952_p() >> 4) - this.field_72816_b;
      return this.field_72817_c[i][j].func_177424_a(p_175625_1_, Chunk.EnumCreateEntityType.QUEUED);
   }

   public int func_175626_b(BlockPos p_175626_1_, int p_175626_2_) {
      int i = this.func_175630_e(p_175626_1_);
      int j = this.field_175634_h[i];
      if(j == -1) {
         j = super.func_175626_b(p_175626_1_, p_175626_2_);
         this.field_175634_h[i] = j;
      }

      return j;
   }

   public IBlockState func_180495_p(BlockPos p_180495_1_) {
      int i = this.func_175630_e(p_180495_1_);
      IBlockState iblockstate = this.field_175635_i[i];
      if(iblockstate == null) {
         iblockstate = this.func_175631_c(p_180495_1_);
         this.field_175635_i[i] = iblockstate;
      }

      return iblockstate;
   }

   private IBlockState func_175631_c(BlockPos p_175631_1_) {
      if(p_175631_1_.func_177956_o() >= 0 && p_175631_1_.func_177956_o() < 256) {
         int i = (p_175631_1_.func_177958_n() >> 4) - this.field_72818_a;
         int j = (p_175631_1_.func_177952_p() >> 4) - this.field_72816_b;
         return this.field_72817_c[i][j].func_177435_g(p_175631_1_);
      } else {
         return field_175632_f;
      }
   }

   private int func_175630_e(BlockPos p_175630_1_) {
      int i = p_175630_1_.func_177958_n() - this.field_175633_g.func_177958_n();
      int j = p_175630_1_.func_177956_o() - this.field_175633_g.func_177956_o();
      int k = p_175630_1_.func_177952_p() - this.field_175633_g.func_177952_p();
      return i * 400 + k * 20 + j;
   }
}
