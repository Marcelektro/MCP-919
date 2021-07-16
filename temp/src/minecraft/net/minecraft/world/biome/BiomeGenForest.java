package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenMutated;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;

public class BiomeGenForest extends BiomeGenBase {
   private int field_150632_aF;
   protected static final WorldGenForest field_150629_aC = new WorldGenForest(false, true);
   protected static final WorldGenForest field_150630_aD = new WorldGenForest(false, false);
   protected static final WorldGenCanopyTree field_150631_aE = new WorldGenCanopyTree(false);

   public BiomeGenForest(int p_i45377_1_, int p_i45377_2_) {
      super(p_i45377_1_);
      this.field_150632_aF = p_i45377_2_;
      this.field_76760_I.field_76832_z = 10;
      this.field_76760_I.field_76803_B = 2;
      if(this.field_150632_aF == 1) {
         this.field_76760_I.field_76832_z = 6;
         this.field_76760_I.field_76802_A = 100;
         this.field_76760_I.field_76803_B = 1;
      }

      this.func_76733_a(5159473);
      this.func_76732_a(0.7F, 0.8F);
      if(this.field_150632_aF == 2) {
         this.field_150609_ah = 353825;
         this.field_76790_z = 3175492;
         this.func_76732_a(0.6F, 0.6F);
      }

      if(this.field_150632_aF == 0) {
         this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
      }

      if(this.field_150632_aF == 3) {
         this.field_76760_I.field_76832_z = -999;
      }

   }

   protected BiomeGenBase func_150557_a(int p_150557_1_, boolean p_150557_2_) {
      if(this.field_150632_aF == 2) {
         this.field_150609_ah = 353825;
         this.field_76790_z = p_150557_1_;
         if(p_150557_2_) {
            this.field_150609_ah = (this.field_150609_ah & 16711422) >> 1;
         }

         return this;
      } else {
         return super.func_150557_a(p_150557_1_, p_150557_2_);
      }
   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      return (WorldGenAbstractTree)(this.field_150632_aF == 3 && p_150567_1_.nextInt(3) > 0?field_150631_aE:(this.field_150632_aF != 2 && p_150567_1_.nextInt(5) != 0?this.field_76757_N:field_150630_aD));
   }

   public BlockFlower.EnumFlowerType func_180623_a(Random p_180623_1_, BlockPos p_180623_2_) {
      if(this.field_150632_aF == 1) {
         double d0 = MathHelper.func_151237_a((1.0D + field_180281_af.func_151601_a((double)p_180623_2_.func_177958_n() / 48.0D, (double)p_180623_2_.func_177952_p() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
         BlockFlower.EnumFlowerType blockflower$enumflowertype = BlockFlower.EnumFlowerType.values()[(int)(d0 * (double)BlockFlower.EnumFlowerType.values().length)];
         return blockflower$enumflowertype == BlockFlower.EnumFlowerType.BLUE_ORCHID?BlockFlower.EnumFlowerType.POPPY:blockflower$enumflowertype;
      } else {
         return super.func_180623_a(p_180623_1_, p_180623_2_);
      }
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      if(this.field_150632_aF == 3) {
         for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < 4; ++j) {
               int k = i * 4 + 1 + 8 + p_180624_2_.nextInt(3);
               int l = j * 4 + 1 + 8 + p_180624_2_.nextInt(3);
               BlockPos blockpos = p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(k, 0, l));
               if(p_180624_2_.nextInt(20) == 0) {
                  WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
                  worldgenbigmushroom.func_180709_b(p_180624_1_, p_180624_2_, blockpos);
               } else {
                  WorldGenAbstractTree worldgenabstracttree = this.func_150567_a(p_180624_2_);
                  worldgenabstracttree.func_175904_e();
                  if(worldgenabstracttree.func_180709_b(p_180624_1_, p_180624_2_, blockpos)) {
                     worldgenabstracttree.func_180711_a(p_180624_1_, p_180624_2_, blockpos);
                  }
               }
            }
         }
      }

      int j1 = p_180624_2_.nextInt(5) - 3;
      if(this.field_150632_aF == 1) {
         j1 += 2;
      }

      for(int k1 = 0; k1 < j1; ++k1) {
         int l1 = p_180624_2_.nextInt(3);
         if(l1 == 0) {
            field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.SYRINGA);
         } else if(l1 == 1) {
            field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.ROSE);
         } else if(l1 == 2) {
            field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.PAEONIA);
         }

         for(int i2 = 0; i2 < 5; ++i2) {
            int j2 = p_180624_2_.nextInt(16) + 8;
            int k2 = p_180624_2_.nextInt(16) + 8;
            int i1 = p_180624_2_.nextInt(p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(j2, 0, k2)).func_177956_o() + 32);
            if(field_180280_ag.func_180709_b(p_180624_1_, p_180624_2_, new BlockPos(p_180624_3_.func_177958_n() + j2, i1, p_180624_3_.func_177952_p() + k2))) {
               break;
            }
         }
      }

      super.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
   }

   public int func_180627_b(BlockPos p_180627_1_) {
      int i = super.func_180627_b(p_180627_1_);
      return this.field_150632_aF == 3?(i & 16711422) + 2634762 >> 1:i;
   }

   protected BiomeGenBase func_180277_d(final int p_180277_1_) {
      if(this.field_76756_M == BiomeGenBase.field_76767_f.field_76756_M) {
         BiomeGenForest biomegenforest = new BiomeGenForest(p_180277_1_, 1);
         biomegenforest.func_150570_a(new BiomeGenBase.Height(this.field_76748_D, this.field_76749_E + 0.2F));
         biomegenforest.func_76735_a("Flower Forest");
         biomegenforest.func_150557_a(6976549, true);
         biomegenforest.func_76733_a(8233509);
         return biomegenforest;
      } else {
         return this.field_76756_M != BiomeGenBase.field_150583_P.field_76756_M && this.field_76756_M != BiomeGenBase.field_150582_Q.field_76756_M?new BiomeGenMutated(p_180277_1_, this) {
            public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
               this.field_150611_aD.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
            }
         }:new BiomeGenMutated(p_180277_1_, this) {
            public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
               return p_150567_1_.nextBoolean()?BiomeGenForest.field_150629_aC:BiomeGenForest.field_150630_aD;
            }
         };
      }
   }
}
