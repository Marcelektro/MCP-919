package net.minecraft.world.chunk;

import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public interface IChunkProvider {
   boolean func_73149_a(int var1, int var2);

   Chunk func_73154_d(int var1, int var2);

   Chunk func_177459_a(BlockPos var1);

   void func_73153_a(IChunkProvider var1, int var2, int var3);

   boolean func_177460_a(IChunkProvider var1, Chunk var2, int var3, int var4);

   boolean func_73151_a(boolean var1, IProgressUpdate var2);

   boolean func_73156_b();

   boolean func_73157_c();

   String func_73148_d();

   List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType var1, BlockPos var2);

   BlockPos func_180513_a(World var1, String var2, BlockPos var3);

   int func_73152_e();

   void func_180514_a(Chunk var1, int var2, int var3);

   void func_104112_b();
}
