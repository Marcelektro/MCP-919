package net.minecraft.world.chunk.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.chunk.storage.RegionFileCache;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.ThreadedFileIOBase;
import net.minecraft.world.storage.WorldInfo;

public class AnvilSaveHandler extends SaveHandler {
   public AnvilSaveHandler(File p_i2142_1_, String p_i2142_2_, boolean p_i2142_3_) {
      super(p_i2142_1_, p_i2142_2_, p_i2142_3_);
   }

   public IChunkLoader func_75763_a(WorldProvider p_75763_1_) {
      File file1 = this.func_75765_b();
      if(p_75763_1_ instanceof WorldProviderHell) {
         File file3 = new File(file1, "DIM-1");
         file3.mkdirs();
         return new AnvilChunkLoader(file3);
      } else if(p_75763_1_ instanceof WorldProviderEnd) {
         File file2 = new File(file1, "DIM1");
         file2.mkdirs();
         return new AnvilChunkLoader(file2);
      } else {
         return new AnvilChunkLoader(file1);
      }
   }

   public void func_75755_a(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_) {
      p_75755_1_.func_76078_e(19133);
      super.func_75755_a(p_75755_1_, p_75755_2_);
   }

   public void func_75759_a() {
      try {
         ThreadedFileIOBase.func_178779_a().func_75734_a();
      } catch (InterruptedException interruptedexception) {
         interruptedexception.printStackTrace();
      }

      RegionFileCache.func_76551_a();
   }
}
