package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;

public class MapGenStronghold extends MapGenStructure {
   private List<BiomeGenBase> field_151546_e;
   private boolean field_75056_f;
   private ChunkCoordIntPair[] field_75057_g;
   private double field_82671_h;
   private int field_82672_i;

   public MapGenStronghold() {
      this.field_75057_g = new ChunkCoordIntPair[3];
      this.field_82671_h = 32.0D;
      this.field_82672_i = 3;
      this.field_151546_e = Lists.<BiomeGenBase>newArrayList();

      for(BiomeGenBase biomegenbase : BiomeGenBase.func_150565_n()) {
         if(biomegenbase != null && biomegenbase.field_76748_D > 0.0F) {
            this.field_151546_e.add(biomegenbase);
         }
      }

   }

   public MapGenStronghold(Map<String, String> p_i2068_1_) {
      this();

      for(Entry<String, String> entry : p_i2068_1_.entrySet()) {
         if(((String)entry.getKey()).equals("distance")) {
            this.field_82671_h = MathHelper.func_82713_a((String)entry.getValue(), this.field_82671_h, 1.0D);
         } else if(((String)entry.getKey()).equals("count")) {
            this.field_75057_g = new ChunkCoordIntPair[MathHelper.func_82714_a((String)entry.getValue(), this.field_75057_g.length, 1)];
         } else if(((String)entry.getKey()).equals("spread")) {
            this.field_82672_i = MathHelper.func_82714_a((String)entry.getValue(), this.field_82672_i, 1);
         }
      }

   }

   public String func_143025_a() {
      return "Stronghold";
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      if(!this.field_75056_f) {
         Random random = new Random();
         random.setSeed(this.field_75039_c.func_72905_C());
         double d0 = random.nextDouble() * 3.141592653589793D * 2.0D;
         int i = 1;

         for(int j = 0; j < this.field_75057_g.length; ++j) {
            double d1 = (1.25D * (double)i + random.nextDouble()) * this.field_82671_h * (double)i;
            int k = (int)Math.round(Math.cos(d0) * d1);
            int l = (int)Math.round(Math.sin(d0) * d1);
            BlockPos blockpos = this.field_75039_c.func_72959_q().func_180630_a((k << 4) + 8, (l << 4) + 8, 112, this.field_151546_e, random);
            if(blockpos != null) {
               k = blockpos.func_177958_n() >> 4;
               l = blockpos.func_177952_p() >> 4;
            }

            this.field_75057_g[j] = new ChunkCoordIntPair(k, l);
            d0 += 6.283185307179586D * (double)i / (double)this.field_82672_i;
            if(j == this.field_82672_i) {
               i += 2 + random.nextInt(5);
               this.field_82672_i += 1 + random.nextInt(2);
            }
         }

         this.field_75056_f = true;
      }

      for(ChunkCoordIntPair chunkcoordintpair : this.field_75057_g) {
         if(p_75047_1_ == chunkcoordintpair.field_77276_a && p_75047_2_ == chunkcoordintpair.field_77275_b) {
            return true;
         }
      }

      return false;
   }

   protected List<BlockPos> func_75052_o_() {
      List<BlockPos> list = Lists.<BlockPos>newArrayList();

      for(ChunkCoordIntPair chunkcoordintpair : this.field_75057_g) {
         if(chunkcoordintpair != null) {
            list.add(chunkcoordintpair.func_180619_a(64));
         }
      }

      return list;
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      MapGenStronghold.Start mapgenstronghold$start;
      for(mapgenstronghold$start = new MapGenStronghold.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_); mapgenstronghold$start.func_75073_b().isEmpty() || ((StructureStrongholdPieces.Stairs2)mapgenstronghold$start.func_75073_b().get(0)).field_75025_b == null; mapgenstronghold$start = new MapGenStronghold.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_)) {
         ;
      }

      return mapgenstronghold$start;
   }

   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World p_i2067_1_, Random p_i2067_2_, int p_i2067_3_, int p_i2067_4_) {
         super(p_i2067_3_, p_i2067_4_);
         StructureStrongholdPieces.func_75198_a();
         StructureStrongholdPieces.Stairs2 structurestrongholdpieces$stairs2 = new StructureStrongholdPieces.Stairs2(0, p_i2067_2_, (p_i2067_3_ << 4) + 2, (p_i2067_4_ << 4) + 2);
         this.field_75075_a.add(structurestrongholdpieces$stairs2);
         structurestrongholdpieces$stairs2.func_74861_a(structurestrongholdpieces$stairs2, this.field_75075_a, p_i2067_2_);
         List<StructureComponent> list = structurestrongholdpieces$stairs2.field_75026_c;

         while(!list.isEmpty()) {
            int i = p_i2067_2_.nextInt(list.size());
            StructureComponent structurecomponent = (StructureComponent)list.remove(i);
            structurecomponent.func_74861_a(structurestrongholdpieces$stairs2, this.field_75075_a, p_i2067_2_);
         }

         this.func_75072_c();
         this.func_75067_a(p_i2067_1_, p_i2067_2_, 10);
      }
   }
}
