package net.minecraft.world.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.WorldInfo;

public interface ISaveHandler {
   WorldInfo func_75757_d();

   void func_75762_c() throws MinecraftException;

   IChunkLoader func_75763_a(WorldProvider var1);

   void func_75755_a(WorldInfo var1, NBTTagCompound var2);

   void func_75761_a(WorldInfo var1);

   IPlayerFileData func_75756_e();

   void func_75759_a();

   File func_75765_b();

   File func_75758_b(String var1);

   String func_75760_g();
}
