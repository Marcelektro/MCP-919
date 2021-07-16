package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenPlains extends BiomeGenBase {
   protected boolean field_150628_aC;

   protected BiomeGenPlains(int p_i1986_1_) {
      super(p_i1986_1_);
      this.func_76732_a(0.8F, 0.4F);
      this.func_150570_a(field_150593_e);
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
      this.field_76760_I.field_76832_z = -999;
      this.field_76760_I.field_76802_A = 4;
      this.field_76760_I.field_76803_B = 10;
   }

   public BlockFlower.EnumFlowerType func_180623_a(Random p_180623_1_, BlockPos p_180623_2_) {
      double d0 = field_180281_af.func_151601_a((double)p_180623_2_.func_177958_n() / 200.0D, (double)p_180623_2_.func_177952_p() / 200.0D);
      if(d0 < -0.8D) {
         int j = p_180623_1_.nextInt(4);
         switch(j) {
         case 0:
            return BlockFlower.EnumFlowerType.ORANGE_TULIP;
         case 1:
            return BlockFlower.EnumFlowerType.RED_TULIP;
         case 2:
            return BlockFlower.EnumFlowerType.PINK_TULIP;
         case 3:
         default:
            return BlockFlower.EnumFlowerType.WHITE_TULIP;
         }
      } else if(p_180623_1_.nextInt(3) > 0) {
         int i = p_180623_1_.nextInt(3);
         return i == 0?BlockFlower.EnumFlowerType.POPPY:(i == 1?BlockFlower.EnumFlowerType.HOUSTONIA:BlockFlower.EnumFlowerType.OXEYE_DAISY);
      } else {
         return BlockFlower.EnumFlowerType.DANDELION;
      }
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      double d0 = field_180281_af.func_151601_a((double)(p_180624_3_.func_177958_n() + 8) / 200.0D, (double)(p_180624_3_.func_177952_p() + 8) / 200.0D);
      if(d0 < -0.8D) {
         this.field_76760_I.field_76802_A = 15;
         this.field_76760_I.field_76803_B = 5;
      } else {
         this.field_76760_I.field_76802_A = 4;
         this.field_76760_I.field_76803_B = 10;
         field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.GRASS);

         for(int i = 0; i < 7; ++i) {
            int j = p_180624_2_.nextInt(16) + 8;
            int k = p_180624_2_.nextInt(16) + 8;
            int l = p_180624_2_.nextInt(p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(j, 0, k)).func_177956_o() + 32);
            field_180280_ag.func_180709_b(p_180624_1_, p_180624_2_, p_180624_3_.func_177982_a(j, l, k));
         }
      }

      if(this.field_150628_aC) {
         field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.SUNFLOWER);

         for(int i1 = 0; i1 < 10; ++i1) {
            int j1 = p_180624_2_.nextInt(16) + 8;
            int k1 = p_180624_2_.nextInt(16) + 8;
            int l1 = p_180624_2_.nextInt(p_180624_1_.func_175645_m(p_180624_3_.func_177982_a(j1, 0, k1)).func_177956_o() + 32);
            field_180280_ag.func_180709_b(p_180624_1_, p_180624_2_, p_180624_3_.func_177982_a(j1, l1, k1));
         }
      }

      super.func_180624_a(p_180624_1_, p_180624_2_, p_180624_3_);
   }

   protected BiomeGenBase func_180277_d(int p_180277_1_) {
      BiomeGenPlains biomegenplains = new BiomeGenPlains(p_180277_1_);
      biomegenplains.func_76735_a("Sunflower Plains");
      biomegenplains.field_150628_aC = true;
      biomegenplains.func_76739_b(9286496);
      biomegenplains.field_150609_ah = 14273354;
      return biomegenplains;
   }
}
