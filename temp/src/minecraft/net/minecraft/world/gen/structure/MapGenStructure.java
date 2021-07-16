package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.MapGenStructureData;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;

public abstract class MapGenStructure extends MapGenBase {
   private MapGenStructureData field_143029_e;
   protected Map<Long, StructureStart> field_75053_d = Maps.<Long, StructureStart>newHashMap();

   public abstract String func_143025_a();

   protected final void func_180701_a(World p_180701_1_, final int p_180701_2_, final int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
      this.func_143027_a(p_180701_1_);
      if(!this.field_75053_d.containsKey(Long.valueOf(ChunkCoordIntPair.func_77272_a(p_180701_2_, p_180701_3_)))) {
         this.field_75038_b.nextInt();

         try {
            if(this.func_75047_a(p_180701_2_, p_180701_3_)) {
               StructureStart structurestart = this.func_75049_b(p_180701_2_, p_180701_3_);
               this.field_75053_d.put(Long.valueOf(ChunkCoordIntPair.func_77272_a(p_180701_2_, p_180701_3_)), structurestart);
               this.func_143026_a(p_180701_2_, p_180701_3_, structurestart);
            }

         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception preparing structure feature");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Feature being prepared");
            crashreportcategory.func_71500_a("Is feature chunk", new Callable<String>() {
               public String call() throws Exception {
                  return MapGenStructure.this.func_75047_a(p_180701_2_, p_180701_3_)?"True":"False";
               }
            });
            crashreportcategory.func_71507_a("Chunk location", String.format("%d,%d", new Object[]{Integer.valueOf(p_180701_2_), Integer.valueOf(p_180701_3_)}));
            crashreportcategory.func_71500_a("Chunk pos hash", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf(ChunkCoordIntPair.func_77272_a(p_180701_2_, p_180701_3_));
               }
            });
            crashreportcategory.func_71500_a("Structure type", new Callable<String>() {
               public String call() throws Exception {
                  return MapGenStructure.this.getClass().getCanonicalName();
               }
            });
            throw new ReportedException(crashreport);
         }
      }
   }

   public boolean func_175794_a(World p_175794_1_, Random p_175794_2_, ChunkCoordIntPair p_175794_3_) {
      this.func_143027_a(p_175794_1_);
      int i = (p_175794_3_.field_77276_a << 4) + 8;
      int j = (p_175794_3_.field_77275_b << 4) + 8;
      boolean flag = false;

      for(StructureStart structurestart : this.field_75053_d.values()) {
         if(structurestart.func_75069_d() && structurestart.func_175788_a(p_175794_3_) && structurestart.func_75071_a().func_78885_a(i, j, i + 15, j + 15)) {
            structurestart.func_75068_a(p_175794_1_, p_175794_2_, new StructureBoundingBox(i, j, i + 15, j + 15));
            structurestart.func_175787_b(p_175794_3_);
            flag = true;
            this.func_143026_a(structurestart.func_143019_e(), structurestart.func_143018_f(), structurestart);
         }
      }

      return flag;
   }

   public boolean func_175795_b(BlockPos p_175795_1_) {
      this.func_143027_a(this.field_75039_c);
      return this.func_175797_c(p_175795_1_) != null;
   }

   protected StructureStart func_175797_c(BlockPos p_175797_1_) {
      label24:
      for(StructureStart structurestart : this.field_75053_d.values()) {
         if(structurestart.func_75069_d() && structurestart.func_75071_a().func_175898_b(p_175797_1_)) {
            Iterator<StructureComponent> iterator = structurestart.func_75073_b().iterator();

            while(true) {
               if(!iterator.hasNext()) {
                  continue label24;
               }

               StructureComponent structurecomponent = (StructureComponent)iterator.next();
               if(structurecomponent.func_74874_b().func_175898_b(p_175797_1_)) {
                  break;
               }
            }

            return structurestart;
         }
      }

      return null;
   }

   public boolean func_175796_a(World p_175796_1_, BlockPos p_175796_2_) {
      this.func_143027_a(p_175796_1_);

      for(StructureStart structurestart : this.field_75053_d.values()) {
         if(structurestart.func_75069_d() && structurestart.func_75071_a().func_175898_b(p_175796_2_)) {
            return true;
         }
      }

      return false;
   }

   public BlockPos func_180706_b(World p_180706_1_, BlockPos p_180706_2_) {
      this.field_75039_c = p_180706_1_;
      this.func_143027_a(p_180706_1_);
      this.field_75038_b.setSeed(p_180706_1_.func_72905_C());
      long i = this.field_75038_b.nextLong();
      long j = this.field_75038_b.nextLong();
      long k = (long)(p_180706_2_.func_177958_n() >> 4) * i;
      long l = (long)(p_180706_2_.func_177952_p() >> 4) * j;
      this.field_75038_b.setSeed(k ^ l ^ p_180706_1_.func_72905_C());
      this.func_180701_a(p_180706_1_, p_180706_2_.func_177958_n() >> 4, p_180706_2_.func_177952_p() >> 4, 0, 0, (ChunkPrimer)null);
      double d0 = Double.MAX_VALUE;
      BlockPos blockpos = null;

      for(StructureStart structurestart : this.field_75053_d.values()) {
         if(structurestart.func_75069_d()) {
            StructureComponent structurecomponent = (StructureComponent)structurestart.func_75073_b().get(0);
            BlockPos blockpos1 = structurecomponent.func_180776_a();
            double d1 = blockpos1.func_177951_i(p_180706_2_);
            if(d1 < d0) {
               d0 = d1;
               blockpos = blockpos1;
            }
         }
      }

      if(blockpos != null) {
         return blockpos;
      } else {
         List<BlockPos> list = this.func_75052_o_();
         if(list != null) {
            BlockPos blockpos2 = null;

            for(BlockPos blockpos3 : list) {
               double d2 = blockpos3.func_177951_i(p_180706_2_);
               if(d2 < d0) {
                  d0 = d2;
                  blockpos2 = blockpos3;
               }
            }

            return blockpos2;
         } else {
            return null;
         }
      }
   }

   protected List<BlockPos> func_75052_o_() {
      return null;
   }

   private void func_143027_a(World p_143027_1_) {
      if(this.field_143029_e == null) {
         this.field_143029_e = (MapGenStructureData)p_143027_1_.func_72943_a(MapGenStructureData.class, this.func_143025_a());
         if(this.field_143029_e == null) {
            this.field_143029_e = new MapGenStructureData(this.func_143025_a());
            p_143027_1_.func_72823_a(this.func_143025_a(), this.field_143029_e);
         } else {
            NBTTagCompound nbttagcompound = this.field_143029_e.func_143041_a();

            for(String s : nbttagcompound.func_150296_c()) {
               NBTBase nbtbase = nbttagcompound.func_74781_a(s);
               if(nbtbase.func_74732_a() == 10) {
                  NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbtbase;
                  if(nbttagcompound1.func_74764_b("ChunkX") && nbttagcompound1.func_74764_b("ChunkZ")) {
                     int i = nbttagcompound1.func_74762_e("ChunkX");
                     int j = nbttagcompound1.func_74762_e("ChunkZ");
                     StructureStart structurestart = MapGenStructureIO.func_143035_a(nbttagcompound1, p_143027_1_);
                     if(structurestart != null) {
                        this.field_75053_d.put(Long.valueOf(ChunkCoordIntPair.func_77272_a(i, j)), structurestart);
                     }
                  }
               }
            }
         }
      }

   }

   private void func_143026_a(int p_143026_1_, int p_143026_2_, StructureStart p_143026_3_) {
      this.field_143029_e.func_143043_a(p_143026_3_.func_143021_a(p_143026_1_, p_143026_2_), p_143026_1_, p_143026_2_);
      this.field_143029_e.func_76185_a();
   }

   protected abstract boolean func_75047_a(int var1, int var2);

   protected abstract StructureStart func_75049_b(int var1, int var2);
}
