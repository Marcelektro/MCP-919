package net.minecraft.world.chunk.storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.chunk.storage.RegionFileCache;
import net.minecraft.world.storage.IThreadedFileIO;
import net.minecraft.world.storage.ThreadedFileIOBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnvilChunkLoader implements IChunkLoader, IThreadedFileIO {
   private static final Logger field_151505_a = LogManager.getLogger();
   private Map<ChunkCoordIntPair, NBTTagCompound> field_75828_a = new ConcurrentHashMap();
   private Set<ChunkCoordIntPair> field_75826_b = Collections.<ChunkCoordIntPair>newSetFromMap(new ConcurrentHashMap());
   private final File field_75825_d;
   private boolean field_183014_e = false;

   public AnvilChunkLoader(File p_i2003_1_) {
      this.field_75825_d = p_i2003_1_;
   }

   public Chunk func_75815_a(World p_75815_1_, int p_75815_2_, int p_75815_3_) throws IOException {
      ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_75815_2_, p_75815_3_);
      NBTTagCompound nbttagcompound = (NBTTagCompound)this.field_75828_a.get(chunkcoordintpair);
      if(nbttagcompound == null) {
         DataInputStream datainputstream = RegionFileCache.func_76549_c(this.field_75825_d, p_75815_2_, p_75815_3_);
         if(datainputstream == null) {
            return null;
         }

         nbttagcompound = CompressedStreamTools.func_74794_a(datainputstream);
      }

      return this.func_75822_a(p_75815_1_, p_75815_2_, p_75815_3_, nbttagcompound);
   }

   protected Chunk func_75822_a(World p_75822_1_, int p_75822_2_, int p_75822_3_, NBTTagCompound p_75822_4_) {
      if(!p_75822_4_.func_150297_b("Level", 10)) {
         field_151505_a.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is missing level data, skipping");
         return null;
      } else {
         NBTTagCompound nbttagcompound = p_75822_4_.func_74775_l("Level");
         if(!nbttagcompound.func_150297_b("Sections", 9)) {
            field_151505_a.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is missing block data, skipping");
            return null;
         } else {
            Chunk chunk = this.func_75823_a(p_75822_1_, nbttagcompound);
            if(!chunk.func_76600_a(p_75822_2_, p_75822_3_)) {
               field_151505_a.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is in the wrong location; relocating. (Expected " + p_75822_2_ + ", " + p_75822_3_ + ", got " + chunk.field_76635_g + ", " + chunk.field_76647_h + ")");
               nbttagcompound.func_74768_a("xPos", p_75822_2_);
               nbttagcompound.func_74768_a("zPos", p_75822_3_);
               chunk = this.func_75823_a(p_75822_1_, nbttagcompound);
            }

            return chunk;
         }
      }
   }

   public void func_75816_a(World p_75816_1_, Chunk p_75816_2_) throws MinecraftException, IOException {
      p_75816_1_.func_72906_B();

      try {
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
         nbttagcompound.func_74782_a("Level", nbttagcompound1);
         this.func_75820_a(p_75816_2_, p_75816_1_, nbttagcompound1);
         this.func_75824_a(p_75816_2_.func_76632_l(), nbttagcompound);
      } catch (Exception exception) {
         field_151505_a.error((String)"Failed to save chunk", (Throwable)exception);
      }

   }

   protected void func_75824_a(ChunkCoordIntPair p_75824_1_, NBTTagCompound p_75824_2_) {
      if(!this.field_75826_b.contains(p_75824_1_)) {
         this.field_75828_a.put(p_75824_1_, p_75824_2_);
      }

      ThreadedFileIOBase.func_178779_a().func_75735_a(this);
   }

   public boolean func_75814_c() {
      if(this.field_75828_a.isEmpty()) {
         if(this.field_183014_e) {
            field_151505_a.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", new Object[]{this.field_75825_d.getName()});
         }

         return false;
      } else {
         ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)this.field_75828_a.keySet().iterator().next();

         boolean lvt_3_1_;
         try {
            this.field_75826_b.add(chunkcoordintpair);
            NBTTagCompound nbttagcompound = (NBTTagCompound)this.field_75828_a.remove(chunkcoordintpair);
            if(nbttagcompound != null) {
               try {
                  this.func_183013_b(chunkcoordintpair, nbttagcompound);
               } catch (Exception exception) {
                  field_151505_a.error((String)"Failed to save chunk", (Throwable)exception);
               }
            }

            lvt_3_1_ = true;
         } finally {
            this.field_75826_b.remove(chunkcoordintpair);
         }

         return lvt_3_1_;
      }
   }

   private void func_183013_b(ChunkCoordIntPair p_183013_1_, NBTTagCompound p_183013_2_) throws IOException {
      DataOutputStream dataoutputstream = RegionFileCache.func_76552_d(this.field_75825_d, p_183013_1_.field_77276_a, p_183013_1_.field_77275_b);
      CompressedStreamTools.func_74800_a(p_183013_2_, dataoutputstream);
      dataoutputstream.close();
   }

   public void func_75819_b(World p_75819_1_, Chunk p_75819_2_) throws IOException {
   }

   public void func_75817_a() {
   }

   public void func_75818_b() {
      try {
         this.field_183014_e = true;

         while(true) {
            if(this.func_75814_c()) {
               continue;
            }
         }
      } finally {
         this.field_183014_e = false;
      }

   }

   private void func_75820_a(Chunk p_75820_1_, World p_75820_2_, NBTTagCompound p_75820_3_) {
      p_75820_3_.func_74774_a("V", (byte)1);
      p_75820_3_.func_74768_a("xPos", p_75820_1_.field_76635_g);
      p_75820_3_.func_74768_a("zPos", p_75820_1_.field_76647_h);
      p_75820_3_.func_74772_a("LastUpdate", p_75820_2_.func_82737_E());
      p_75820_3_.func_74783_a("HeightMap", p_75820_1_.func_177445_q());
      p_75820_3_.func_74757_a("TerrainPopulated", p_75820_1_.func_177419_t());
      p_75820_3_.func_74757_a("LightPopulated", p_75820_1_.func_177423_u());
      p_75820_3_.func_74772_a("InhabitedTime", p_75820_1_.func_177416_w());
      ExtendedBlockStorage[] aextendedblockstorage = p_75820_1_.func_76587_i();
      NBTTagList nbttaglist = new NBTTagList();
      boolean flag = !p_75820_2_.field_73011_w.func_177495_o();

      for(ExtendedBlockStorage extendedblockstorage : aextendedblockstorage) {
         if(extendedblockstorage != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Y", (byte)(extendedblockstorage.func_76662_d() >> 4 & 255));
            byte[] abyte = new byte[extendedblockstorage.func_177487_g().length];
            NibbleArray nibblearray = new NibbleArray();
            NibbleArray nibblearray1 = null;

            for(int i = 0; i < extendedblockstorage.func_177487_g().length; ++i) {
               char c0 = extendedblockstorage.func_177487_g()[i];
               int j = i & 15;
               int k = i >> 8 & 15;
               int l = i >> 4 & 15;
               if(c0 >> 12 != 0) {
                  if(nibblearray1 == null) {
                     nibblearray1 = new NibbleArray();
                  }

                  nibblearray1.func_76581_a(j, k, l, c0 >> 12);
               }

               abyte[i] = (byte)(c0 >> 4 & 255);
               nibblearray.func_76581_a(j, k, l, c0 & 15);
            }

            nbttagcompound.func_74773_a("Blocks", abyte);
            nbttagcompound.func_74773_a("Data", nibblearray.func_177481_a());
            if(nibblearray1 != null) {
               nbttagcompound.func_74773_a("Add", nibblearray1.func_177481_a());
            }

            nbttagcompound.func_74773_a("BlockLight", extendedblockstorage.func_76661_k().func_177481_a());
            if(flag) {
               nbttagcompound.func_74773_a("SkyLight", extendedblockstorage.func_76671_l().func_177481_a());
            } else {
               nbttagcompound.func_74773_a("SkyLight", new byte[extendedblockstorage.func_76661_k().func_177481_a().length]);
            }

            nbttaglist.func_74742_a(nbttagcompound);
         }
      }

      p_75820_3_.func_74782_a("Sections", nbttaglist);
      p_75820_3_.func_74773_a("Biomes", p_75820_1_.func_76605_m());
      p_75820_1_.func_177409_g(false);
      NBTTagList nbttaglist1 = new NBTTagList();

      for(int i1 = 0; i1 < p_75820_1_.func_177429_s().length; ++i1) {
         for(Entity entity : p_75820_1_.func_177429_s()[i1]) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            if(entity.func_70039_c(nbttagcompound1)) {
               p_75820_1_.func_177409_g(true);
               nbttaglist1.func_74742_a(nbttagcompound1);
            }
         }
      }

      p_75820_3_.func_74782_a("Entities", nbttaglist1);
      NBTTagList nbttaglist2 = new NBTTagList();

      for(TileEntity tileentity : p_75820_1_.func_177434_r().values()) {
         NBTTagCompound nbttagcompound2 = new NBTTagCompound();
         tileentity.func_145841_b(nbttagcompound2);
         nbttaglist2.func_74742_a(nbttagcompound2);
      }

      p_75820_3_.func_74782_a("TileEntities", nbttaglist2);
      List<NextTickListEntry> list = p_75820_2_.func_72920_a(p_75820_1_, false);
      if(list != null) {
         long j1 = p_75820_2_.func_82737_E();
         NBTTagList nbttaglist3 = new NBTTagList();

         for(NextTickListEntry nextticklistentry : list) {
            NBTTagCompound nbttagcompound3 = new NBTTagCompound();
            ResourceLocation resourcelocation = (ResourceLocation)Block.field_149771_c.func_177774_c(nextticklistentry.func_151351_a());
            nbttagcompound3.func_74778_a("i", resourcelocation == null?"":resourcelocation.toString());
            nbttagcompound3.func_74768_a("x", nextticklistentry.field_180282_a.func_177958_n());
            nbttagcompound3.func_74768_a("y", nextticklistentry.field_180282_a.func_177956_o());
            nbttagcompound3.func_74768_a("z", nextticklistentry.field_180282_a.func_177952_p());
            nbttagcompound3.func_74768_a("t", (int)(nextticklistentry.field_77180_e - j1));
            nbttagcompound3.func_74768_a("p", nextticklistentry.field_82754_f);
            nbttaglist3.func_74742_a(nbttagcompound3);
         }

         p_75820_3_.func_74782_a("TileTicks", nbttaglist3);
      }

   }

   private Chunk func_75823_a(World p_75823_1_, NBTTagCompound p_75823_2_) {
      int i = p_75823_2_.func_74762_e("xPos");
      int j = p_75823_2_.func_74762_e("zPos");
      Chunk chunk = new Chunk(p_75823_1_, i, j);
      chunk.func_177420_a(p_75823_2_.func_74759_k("HeightMap"));
      chunk.func_177446_d(p_75823_2_.func_74767_n("TerrainPopulated"));
      chunk.func_177421_e(p_75823_2_.func_74767_n("LightPopulated"));
      chunk.func_177415_c(p_75823_2_.func_74763_f("InhabitedTime"));
      NBTTagList nbttaglist = p_75823_2_.func_150295_c("Sections", 10);
      int k = 16;
      ExtendedBlockStorage[] aextendedblockstorage = new ExtendedBlockStorage[k];
      boolean flag = !p_75823_1_.field_73011_w.func_177495_o();

      for(int l = 0; l < nbttaglist.func_74745_c(); ++l) {
         NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(l);
         int i1 = nbttagcompound.func_74771_c("Y");
         ExtendedBlockStorage extendedblockstorage = new ExtendedBlockStorage(i1 << 4, flag);
         byte[] abyte = nbttagcompound.func_74770_j("Blocks");
         NibbleArray nibblearray = new NibbleArray(nbttagcompound.func_74770_j("Data"));
         NibbleArray nibblearray1 = nbttagcompound.func_150297_b("Add", 7)?new NibbleArray(nbttagcompound.func_74770_j("Add")):null;
         char[] achar = new char[abyte.length];

         for(int j1 = 0; j1 < achar.length; ++j1) {
            int k1 = j1 & 15;
            int l1 = j1 >> 8 & 15;
            int i2 = j1 >> 4 & 15;
            int j2 = nibblearray1 != null?nibblearray1.func_76582_a(k1, l1, i2):0;
            achar[j1] = (char)(j2 << 12 | (abyte[j1] & 255) << 4 | nibblearray.func_76582_a(k1, l1, i2));
         }

         extendedblockstorage.func_177486_a(achar);
         extendedblockstorage.func_76659_c(new NibbleArray(nbttagcompound.func_74770_j("BlockLight")));
         if(flag) {
            extendedblockstorage.func_76666_d(new NibbleArray(nbttagcompound.func_74770_j("SkyLight")));
         }

         extendedblockstorage.func_76672_e();
         aextendedblockstorage[i1] = extendedblockstorage;
      }

      chunk.func_76602_a(aextendedblockstorage);
      if(p_75823_2_.func_150297_b("Biomes", 7)) {
         chunk.func_76616_a(p_75823_2_.func_74770_j("Biomes"));
      }

      NBTTagList nbttaglist1 = p_75823_2_.func_150295_c("Entities", 10);
      if(nbttaglist1 != null) {
         for(int k2 = 0; k2 < nbttaglist1.func_74745_c(); ++k2) {
            NBTTagCompound nbttagcompound1 = nbttaglist1.func_150305_b(k2);
            Entity entity = EntityList.func_75615_a(nbttagcompound1, p_75823_1_);
            chunk.func_177409_g(true);
            if(entity != null) {
               chunk.func_76612_a(entity);
               Entity entity1 = entity;

               for(NBTTagCompound nbttagcompound4 = nbttagcompound1; nbttagcompound4.func_150297_b("Riding", 10); nbttagcompound4 = nbttagcompound4.func_74775_l("Riding")) {
                  Entity entity2 = EntityList.func_75615_a(nbttagcompound4.func_74775_l("Riding"), p_75823_1_);
                  if(entity2 != null) {
                     chunk.func_76612_a(entity2);
                     entity1.func_70078_a(entity2);
                  }

                  entity1 = entity2;
               }
            }
         }
      }

      NBTTagList nbttaglist2 = p_75823_2_.func_150295_c("TileEntities", 10);
      if(nbttaglist2 != null) {
         for(int l2 = 0; l2 < nbttaglist2.func_74745_c(); ++l2) {
            NBTTagCompound nbttagcompound2 = nbttaglist2.func_150305_b(l2);
            TileEntity tileentity = TileEntity.func_145827_c(nbttagcompound2);
            if(tileentity != null) {
               chunk.func_150813_a(tileentity);
            }
         }
      }

      if(p_75823_2_.func_150297_b("TileTicks", 9)) {
         NBTTagList nbttaglist3 = p_75823_2_.func_150295_c("TileTicks", 10);
         if(nbttaglist3 != null) {
            for(int i3 = 0; i3 < nbttaglist3.func_74745_c(); ++i3) {
               NBTTagCompound nbttagcompound3 = nbttaglist3.func_150305_b(i3);
               Block block;
               if(nbttagcompound3.func_150297_b("i", 8)) {
                  block = Block.func_149684_b(nbttagcompound3.func_74779_i("i"));
               } else {
                  block = Block.func_149729_e(nbttagcompound3.func_74762_e("i"));
               }

               p_75823_1_.func_180497_b(new BlockPos(nbttagcompound3.func_74762_e("x"), nbttagcompound3.func_74762_e("y"), nbttagcompound3.func_74762_e("z")), block, nbttagcompound3.func_74762_e("t"), nbttagcompound3.func_74762_e("p"));
            }
         }
      }

      return chunk;
   }
}
