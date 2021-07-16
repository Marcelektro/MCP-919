package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureOceanMonument extends MapGenStructure {
   private int field_175800_f;
   private int field_175801_g;
   public static final List<BiomeGenBase> field_175802_d = Arrays.<BiomeGenBase>asList(new BiomeGenBase[]{BiomeGenBase.field_76771_b, BiomeGenBase.field_150575_M, BiomeGenBase.field_76781_i, BiomeGenBase.field_76776_l, BiomeGenBase.field_76777_m});
   private static final List<BiomeGenBase.SpawnListEntry> field_175803_h = Lists.<BiomeGenBase.SpawnListEntry>newArrayList();

   public StructureOceanMonument() {
      this.field_175800_f = 32;
      this.field_175801_g = 5;
   }

   public StructureOceanMonument(Map<String, String> p_i45608_1_) {
      this();

      for(Entry<String, String> entry : p_i45608_1_.entrySet()) {
         if(((String)entry.getKey()).equals("spacing")) {
            this.field_175800_f = MathHelper.func_82714_a((String)entry.getValue(), this.field_175800_f, 1);
         } else if(((String)entry.getKey()).equals("separation")) {
            this.field_175801_g = MathHelper.func_82714_a((String)entry.getValue(), this.field_175801_g, 1);
         }
      }

   }

   public String func_143025_a() {
      return "Monument";
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      int i = p_75047_1_;
      int j = p_75047_2_;
      if(p_75047_1_ < 0) {
         p_75047_1_ -= this.field_175800_f - 1;
      }

      if(p_75047_2_ < 0) {
         p_75047_2_ -= this.field_175800_f - 1;
      }

      int k = p_75047_1_ / this.field_175800_f;
      int l = p_75047_2_ / this.field_175800_f;
      Random random = this.field_75039_c.func_72843_D(k, l, 10387313);
      k = k * this.field_175800_f;
      l = l * this.field_175800_f;
      k = k + (random.nextInt(this.field_175800_f - this.field_175801_g) + random.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
      l = l + (random.nextInt(this.field_175800_f - this.field_175801_g) + random.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
      if(i == k && j == l) {
         if(this.field_75039_c.func_72959_q().func_180300_a(new BlockPos(i * 16 + 8, 64, j * 16 + 8), (BiomeGenBase)null) != BiomeGenBase.field_150575_M) {
            return false;
         }

         boolean flag = this.field_75039_c.func_72959_q().func_76940_a(i * 16 + 8, j * 16 + 8, 29, field_175802_d);
         if(flag) {
            return true;
         }
      }

      return false;
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      return new StructureOceanMonument.StartMonument(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_);
   }

   public List<BiomeGenBase.SpawnListEntry> func_175799_b() {
      return field_175803_h;
   }

   static {
      field_175803_h.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
   }

   public static class StartMonument extends StructureStart {
      private Set<ChunkCoordIntPair> field_175791_c = Sets.<ChunkCoordIntPair>newHashSet();
      private boolean field_175790_d;

      public StartMonument() {
      }

      public StartMonument(World p_i45607_1_, Random p_i45607_2_, int p_i45607_3_, int p_i45607_4_) {
         super(p_i45607_3_, p_i45607_4_);
         this.func_175789_b(p_i45607_1_, p_i45607_2_, p_i45607_3_, p_i45607_4_);
      }

      private void func_175789_b(World p_175789_1_, Random p_175789_2_, int p_175789_3_, int p_175789_4_) {
         p_175789_2_.setSeed(p_175789_1_.func_72905_C());
         long i = p_175789_2_.nextLong();
         long j = p_175789_2_.nextLong();
         long k = (long)p_175789_3_ * i;
         long l = (long)p_175789_4_ * j;
         p_175789_2_.setSeed(k ^ l ^ p_175789_1_.func_72905_C());
         int i1 = p_175789_3_ * 16 + 8 - 29;
         int j1 = p_175789_4_ * 16 + 8 - 29;
         EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.func_179518_a(p_175789_2_);
         this.field_75075_a.add(new StructureOceanMonumentPieces.MonumentBuilding(p_175789_2_, i1, j1, enumfacing));
         this.func_75072_c();
         this.field_175790_d = true;
      }

      public void func_75068_a(World p_75068_1_, Random p_75068_2_, StructureBoundingBox p_75068_3_) {
         if(!this.field_175790_d) {
            this.field_75075_a.clear();
            this.func_175789_b(p_75068_1_, p_75068_2_, this.func_143019_e(), this.func_143018_f());
         }

         super.func_75068_a(p_75068_1_, p_75068_2_, p_75068_3_);
      }

      public boolean func_175788_a(ChunkCoordIntPair p_175788_1_) {
         return this.field_175791_c.contains(p_175788_1_)?false:super.func_175788_a(p_175788_1_);
      }

      public void func_175787_b(ChunkCoordIntPair p_175787_1_) {
         super.func_175787_b(p_175787_1_);
         this.field_175791_c.add(p_175787_1_);
      }

      public void func_143022_a(NBTTagCompound p_143022_1_) {
         super.func_143022_a(p_143022_1_);
         NBTTagList nbttaglist = new NBTTagList();

         for(ChunkCoordIntPair chunkcoordintpair : this.field_175791_c) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("X", chunkcoordintpair.field_77276_a);
            nbttagcompound.func_74768_a("Z", chunkcoordintpair.field_77275_b);
            nbttaglist.func_74742_a(nbttagcompound);
         }

         p_143022_1_.func_74782_a("Processed", nbttaglist);
      }

      public void func_143017_b(NBTTagCompound p_143017_1_) {
         super.func_143017_b(p_143017_1_);
         if(p_143017_1_.func_150297_b("Processed", 9)) {
            NBTTagList nbttaglist = p_143017_1_.func_150295_c("Processed", 10);

            for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
               NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
               this.field_175791_c.add(new ChunkCoordIntPair(nbttagcompound.func_74762_e("X"), nbttagcompound.func_74762_e("Z")));
            }
         }

      }
   }
}
