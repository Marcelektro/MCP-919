package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class ChunkProviderFlat implements IChunkProvider {
   private World field_73163_a;
   private Random field_73161_b;
   private final IBlockState[] field_82700_c = new IBlockState[256];
   private final FlatGeneratorInfo field_82699_e;
   private final List<MapGenStructure> field_82696_f = Lists.<MapGenStructure>newArrayList();
   private final boolean field_82697_g;
   private final boolean field_82702_h;
   private WorldGenLakes field_82703_i;
   private WorldGenLakes field_82701_j;

   public ChunkProviderFlat(World p_i2004_1_, long p_i2004_2_, boolean p_i2004_4_, String p_i2004_5_) {
      this.field_73163_a = p_i2004_1_;
      this.field_73161_b = new Random(p_i2004_2_);
      this.field_82699_e = FlatGeneratorInfo.func_82651_a(p_i2004_5_);
      if(p_i2004_4_) {
         Map<String, Map<String, String>> map = this.field_82699_e.func_82644_b();
         if(map.containsKey("village")) {
            Map<String, String> map1 = (Map)map.get("village");
            if(!map1.containsKey("size")) {
               map1.put("size", "1");
            }

            this.field_82696_f.add(new MapGenVillage(map1));
         }

         if(map.containsKey("biome_1")) {
            this.field_82696_f.add(new MapGenScatteredFeature((Map)map.get("biome_1")));
         }

         if(map.containsKey("mineshaft")) {
            this.field_82696_f.add(new MapGenMineshaft((Map)map.get("mineshaft")));
         }

         if(map.containsKey("stronghold")) {
            this.field_82696_f.add(new MapGenStronghold((Map)map.get("stronghold")));
         }

         if(map.containsKey("oceanmonument")) {
            this.field_82696_f.add(new StructureOceanMonument((Map)map.get("oceanmonument")));
         }
      }

      if(this.field_82699_e.func_82644_b().containsKey("lake")) {
         this.field_82703_i = new WorldGenLakes(Blocks.field_150355_j);
      }

      if(this.field_82699_e.func_82644_b().containsKey("lava_lake")) {
         this.field_82701_j = new WorldGenLakes(Blocks.field_150353_l);
      }

      this.field_82702_h = this.field_82699_e.func_82644_b().containsKey("dungeon");
      int j = 0;
      int k = 0;
      boolean flag = true;

      for(FlatLayerInfo flatlayerinfo : this.field_82699_e.func_82650_c()) {
         for(int i = flatlayerinfo.func_82656_d(); i < flatlayerinfo.func_82656_d() + flatlayerinfo.func_82657_a(); ++i) {
            IBlockState iblockstate = flatlayerinfo.func_175900_c();
            if(iblockstate.func_177230_c() != Blocks.field_150350_a) {
               flag = false;
               this.field_82700_c[i] = iblockstate;
            }
         }

         if(flatlayerinfo.func_175900_c().func_177230_c() == Blocks.field_150350_a) {
            k += flatlayerinfo.func_82657_a();
         } else {
            j += flatlayerinfo.func_82657_a() + k;
            k = 0;
         }
      }

      p_i2004_1_.func_181544_b(j);
      this.field_82697_g = flag?false:this.field_82699_e.func_82644_b().containsKey("decoration");
   }

   public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
      ChunkPrimer chunkprimer = new ChunkPrimer();

      for(int i = 0; i < this.field_82700_c.length; ++i) {
         IBlockState iblockstate = this.field_82700_c[i];
         if(iblockstate != null) {
            for(int j = 0; j < 16; ++j) {
               for(int k = 0; k < 16; ++k) {
                  chunkprimer.func_177855_a(j, i, k, iblockstate);
               }
            }
         }
      }

      for(MapGenBase mapgenbase : this.field_82696_f) {
         mapgenbase.func_175792_a(this, this.field_73163_a, p_73154_1_, p_73154_2_, chunkprimer);
      }

      Chunk chunk = new Chunk(this.field_73163_a, chunkprimer, p_73154_1_, p_73154_2_);
      BiomeGenBase[] abiomegenbase = this.field_73163_a.func_72959_q().func_76933_b((BiomeGenBase[])null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
      byte[] abyte = chunk.func_76605_m();

      for(int l = 0; l < abyte.length; ++l) {
         abyte[l] = (byte)abiomegenbase[l].field_76756_M;
      }

      chunk.func_76603_b();
      return chunk;
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return true;
   }

   public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
      int i = p_73153_2_ * 16;
      int j = p_73153_3_ * 16;
      BlockPos blockpos = new BlockPos(i, 0, j);
      BiomeGenBase biomegenbase = this.field_73163_a.func_180494_b(new BlockPos(i + 16, 0, j + 16));
      boolean flag = false;
      this.field_73161_b.setSeed(this.field_73163_a.func_72905_C());
      long k = this.field_73161_b.nextLong() / 2L * 2L + 1L;
      long l = this.field_73161_b.nextLong() / 2L * 2L + 1L;
      this.field_73161_b.setSeed((long)p_73153_2_ * k + (long)p_73153_3_ * l ^ this.field_73163_a.func_72905_C());
      ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);

      for(MapGenStructure mapgenstructure : this.field_82696_f) {
         boolean flag1 = mapgenstructure.func_175794_a(this.field_73163_a, this.field_73161_b, chunkcoordintpair);
         if(mapgenstructure instanceof MapGenVillage) {
            flag |= flag1;
         }
      }

      if(this.field_82703_i != null && !flag && this.field_73161_b.nextInt(4) == 0) {
         this.field_82703_i.func_180709_b(this.field_73163_a, this.field_73161_b, blockpos.func_177982_a(this.field_73161_b.nextInt(16) + 8, this.field_73161_b.nextInt(256), this.field_73161_b.nextInt(16) + 8));
      }

      if(this.field_82701_j != null && !flag && this.field_73161_b.nextInt(8) == 0) {
         BlockPos blockpos1 = blockpos.func_177982_a(this.field_73161_b.nextInt(16) + 8, this.field_73161_b.nextInt(this.field_73161_b.nextInt(248) + 8), this.field_73161_b.nextInt(16) + 8);
         if(blockpos1.func_177956_o() < this.field_73163_a.func_181545_F() || this.field_73161_b.nextInt(10) == 0) {
            this.field_82701_j.func_180709_b(this.field_73163_a, this.field_73161_b, blockpos1);
         }
      }

      if(this.field_82702_h) {
         for(int i1 = 0; i1 < 8; ++i1) {
            (new WorldGenDungeons()).func_180709_b(this.field_73163_a, this.field_73161_b, blockpos.func_177982_a(this.field_73161_b.nextInt(16) + 8, this.field_73161_b.nextInt(256), this.field_73161_b.nextInt(16) + 8));
         }
      }

      if(this.field_82697_g) {
         biomegenbase.func_180624_a(this.field_73163_a, this.field_73161_b, blockpos);
      }

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
      return "FlatLevelSource";
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      BiomeGenBase biomegenbase = this.field_73163_a.func_180494_b(p_177458_2_);
      return biomegenbase.func_76747_a(p_177458_1_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      if("Stronghold".equals(p_180513_2_)) {
         for(MapGenStructure mapgenstructure : this.field_82696_f) {
            if(mapgenstructure instanceof MapGenStronghold) {
               return mapgenstructure.func_180706_b(p_180513_1_, p_180513_3_);
            }
         }
      }

      return null;
   }

   public int func_73152_e() {
      return 0;
   }

   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
      for(MapGenStructure mapgenstructure : this.field_82696_f) {
         mapgenstructure.func_175792_a(this, this.field_73163_a, p_180514_2_, p_180514_3_, (ChunkPrimer)null);
      }

   }

   public Chunk func_177459_a(BlockPos p_177459_1_) {
      return this.func_73154_d(p_177459_1_.func_177958_n() >> 4, p_177459_1_.func_177952_p() >> 4);
   }
}
