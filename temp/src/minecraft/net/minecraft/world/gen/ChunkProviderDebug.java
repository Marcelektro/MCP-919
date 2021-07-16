package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderDebug implements IChunkProvider {
   private static final List<IBlockState> field_177464_a = Lists.<IBlockState>newArrayList();
   private static final int field_177462_b;
   private static final int field_181039_c;
   private final World field_177463_c;

   public ChunkProviderDebug(World p_i45638_1_) {
      this.field_177463_c = p_i45638_1_;
   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      ChunkPrimer chunkprimer = new ChunkPrimer();

      for(int i = 0; i < 16; ++i) {
         for(int j = 0; j < 16; ++j) {
            int k = p_73154_1_ * 16 + i;
            int l = p_73154_2_ * 16 + j;
            chunkprimer.func_177855_a(i, 60, j, Blocks.field_180401_cv.func_176223_P());
            IBlockState iblockstate = func_177461_b(k, l);
            if(iblockstate != null) {
               chunkprimer.func_177855_a(i, 70, j, iblockstate);
            }
         }
      }

      Chunk chunk = new Chunk(this.field_177463_c, chunkprimer, p_73154_1_, p_73154_2_);
      chunk.func_76603_b();
      BiomeGenBase[] abiomegenbase = this.field_177463_c.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      byte[] abyte = chunk.func_76605_m();

      for(int i1 = 0; i1 < abyte.length; ++i1) {
         abyte[i1] = (byte)abiomegenbase[i1].field_76756_M;
      }

      chunk.func_76603_b();
      return chunk;
   }

   public static IBlockState func_177461_b(int p_177461_0_, int p_177461_1_) {
      IBlockState iblockstate = null;
      if(p_177461_0_ > 0 && p_177461_1_ > 0 && p_177461_0_ % 2 != 0 && p_177461_1_ % 2 != 0) {
         p_177461_0_ = p_177461_0_ / 2;
         p_177461_1_ = p_177461_1_ / 2;
         if(p_177461_0_ <= field_177462_b && p_177461_1_ <= field_181039_c) {
            int i = MathHelper.func_76130_a(p_177461_0_ * field_177462_b + p_177461_1_);
            if(i < field_177464_a.size()) {
               iblockstate = (IBlockState)field_177464_a.get(i);
            }
         }
      }

      return iblockstate;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
   }

   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
      return false;
   }

   public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
      return true;
   }

   public void func_104112_b() {
   }

   public boolean func_73156_b() {
      return false;
   }

   public boolean func_73157_c() {
      return true;
   }

   public String func_73148_d() {
      return "DebugLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      BiomeGenBase biomegenbase = this.field_177463_c.func_180494_b(p_177458_2_);
      return biomegenbase.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }

   static {
      for(Block block : Block.field_149771_c) {
         field_177464_a.addAll(block.func_176194_O().func_177619_a());
      }

      field_177462_b = MathHelper.func_76123_f(MathHelper.func_76129_c((float)field_177464_a.size()));
      field_181039_c = MathHelper.func_76123_f((float)field_177464_a.size() / (float)field_177462_b);
   }
}
