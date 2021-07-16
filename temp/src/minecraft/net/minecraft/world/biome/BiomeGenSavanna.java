package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenSavanna extends BiomeGenBase {
   private static final WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);

   protected BiomeGenSavanna(int p_i45383_1_) {
      super(p_i45383_1_);
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
      this.field_76760_I.field_76832_z = 1;
      this.field_76760_I.field_76802_A = 4;
      this.field_76760_I.field_76803_B = 20;
   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      return (WorldGenAbstractTree)(p_150567_1_.nextInt(5) > 0?field_150627_aC:this.field_76757_N);
   }

   protected BiomeGenBase func_180277_d(int p_180277_1_) {
      BiomeGenBase biomegenbase = new BiomeGenSavanna.Mutated(p_180277_1_, this);
      biomegenbase.field_76750_F = (this.field_76750_F + 1.0F) * 0.5F;
      biomegenbase.field_76748_D = this.field_76748_D * 0.5F + 0.3F;
      biomegenbase.field_76749_E = this.field_76749_E * 0.5F + 1.2F;
      return biomegenbase;
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.GRASS);

      for(int i = 0; i < 7; ++i) {
         int j = p_180624_2_.nextInt(16) + 8;
         int k = p_180624_2_.nextInt(16) + 8;
         int l = p_180624_2_.nextInt(p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(j, 0, k)).func_177956_o() + 32);
         field_180280_ag.func_180709_b(p_180624_1_, p_180624_2_, p_180624_3_.func_177982_a(j, l, k));
      }

      super.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
   }

   public static class Mutated extends BiomeGenMutated {
      public Mutated(int p_i45382_1_, BiomeGenBase p_i45382_2_) {
         super(p_i45382_1_, p_i45382_2_);
         this.field_76760_I.field_76832_z = 2;
         this.field_76760_I.field_76802_A = 2;
         this.field_76760_I.field_76803_B = 5;
      }

      public void func_180622_a(World p_180622_1_, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
         this.field_76752_A = Blocks.field_150349_c.func_176223_P();
         this.field_76753_B = Blocks.field_150346_d.func_176223_P();
         if(p_180622_6_ > 1.75D) {
            this.field_76752_A = Blocks.field_150348_b.func_176223_P();
            this.field_76753_B = Blocks.field_150348_b.func_176223_P();
         } else if(p_180622_6_ > -0.5D) {
            this.field_76752_A = Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.COARSE_DIRT);
         }

         this.func_180628_b(p_180622_1_, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
      }

      public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
         this.field_76760_I.func_180292_a(p_180624_1_, p_180624_2_, this, p_180624_3_);
      }
   }
}
