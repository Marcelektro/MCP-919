package net.minecraft.world.gen.feature;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenDungeons extends WorldGenerator {
   private static final Logger field_175918_a = LogManager.getLogger();
   private static final String[] field_175916_b = new String[]{"Skeleton", "Zombie", "Zombie", "Spider"};
   private static final List<WeightedRandomChestContent> field_175917_c = Lists.newArrayList(new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.field_151141_av, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151042_j, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151015_O, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151016_H, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151007_F, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151133_ar, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151153_ao, 0, 1, 1, 1), new WeightedRandomChestContent(Items.field_151137_ax, 0, 1, 4, 10), new WeightedRandomChestContent(Items.field_151096_cd, 0, 1, 1, 4), new WeightedRandomChestContent(Items.field_151093_ce, 0, 1, 1, 4), new WeightedRandomChestContent(Items.field_151057_cb, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151136_bY, 0, 1, 1, 2), new WeightedRandomChestContent(Items.field_151138_bX, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151125_bZ, 0, 1, 1, 1)});

   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int i = 3;
      int j = p_180709_2_.nextInt(2) + 2;
      int k = -j - 1;
      int l = j + 1;
      int i1 = -1;
      int j1 = 4;
      int k1 = p_180709_2_.nextInt(2) + 2;
      int l1 = -k1 - 1;
      int i2 = k1 + 1;
      int j2 = 0;

      for(int k2 = k; k2 <= l; ++k2) {
         for(int l2 = -1; l2 <= 4; ++l2) {
            for(int i3 = l1; i3 <= i2; ++i3) {
               BlockPos blockpos = p_180709_3_.func_177982_a(k2, l2, i3);
               Material material = p_180709_1_.func_180495_p(blockpos).func_177230_c().func_149688_o();
               boolean flag = material.func_76220_a();
               if(l2 == -1 && !flag) {
                  return false;
               }

               if(l2 == 4 && !flag) {
                  return false;
               }

               if((k2 == k || k2 == l || i3 == l1 || i3 == i2) && l2 == 0 && p_180709_1_.func_175623_d(blockpos) && p_180709_1_.func_175623_d(blockpos.func_177984_a())) {
                  ++j2;
               }
            }
         }
      }

      if(j2 >= 1 && j2 <= 5) {
         for(int k3 = k; k3 <= l; ++k3) {
            for(int i4 = 3; i4 >= -1; --i4) {
               for(int k4 = l1; k4 <= i2; ++k4) {
                  BlockPos blockpos1 = p_180709_3_.func_177982_a(k3, i4, k4);
                  if(k3 != k && i4 != -1 && k4 != l1 && k3 != l && i4 != 4 && k4 != i2) {
                     if(p_180709_1_.func_180495_p(blockpos1).func_177230_c() != Blocks.field_150486_ae) {
                        p_180709_1_.func_175698_g(blockpos1);
                     }
                  } else if(blockpos1.func_177956_o() >= 0 && !p_180709_1_.func_180495_p(blockpos1.func_177977_b()).func_177230_c().func_149688_o().func_76220_a()) {
                     p_180709_1_.func_175698_g(blockpos1);
                  } else if(p_180709_1_.func_180495_p(blockpos1).func_177230_c().func_149688_o().func_76220_a() && p_180709_1_.func_180495_p(blockpos1).func_177230_c() != Blocks.field_150486_ae) {
                     if(i4 == -1 && p_180709_2_.nextInt(4) != 0) {
                        p_180709_1_.func_180501_a(blockpos1, Blocks.field_150341_Y.func_176223_P(), 2);
                     } else {
                        p_180709_1_.func_180501_a(blockpos1, Blocks.field_150347_e.func_176223_P(), 2);
                     }
                  }
               }
            }
         }

         for(int l3 = 0; l3 < 2; ++l3) {
            for(int j4 = 0; j4 < 3; ++j4) {
               int l4 = p_180709_3_.func_177958_n() + p_180709_2_.nextInt(j * 2 + 1) - j;
               int i5 = p_180709_3_.func_177956_o();
               int j5 = p_180709_3_.func_177952_p() + p_180709_2_.nextInt(k1 * 2 + 1) - k1;
               BlockPos blockpos2 = new BlockPos(l4, i5, j5);
               if(p_180709_1_.func_175623_d(blockpos2)) {
                  int j3 = 0;

                  for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                     if(p_180709_1_.func_180495_p(blockpos2.func_177972_a(enumfacing)).func_177230_c().func_149688_o().func_76220_a()) {
                        ++j3;
                     }
                  }

                  if(j3 == 1) {
                     p_180709_1_.func_180501_a(blockpos2, Blocks.field_150486_ae.func_176458_f(p_180709_1_, blockpos2, Blocks.field_150486_ae.func_176223_P()), 2);
                     List<WeightedRandomChestContent> list = WeightedRandomChestContent.func_177629_a(field_175917_c, new WeightedRandomChestContent[]{Items.field_151134_bR.func_92114_b(p_180709_2_)});
                     TileEntity tileentity1 = p_180709_1_.func_175625_s(blockpos2);
                     if(tileentity1 instanceof TileEntityChest) {
                        WeightedRandomChestContent.func_177630_a(p_180709_2_, list, (TileEntityChest)tileentity1, 8);
                     }
                     break;
                  }
               }
            }
         }

         p_180709_1_.func_180501_a(p_180709_3_, Blocks.field_150474_ac.func_176223_P(), 2);
         TileEntity tileentity = p_180709_1_.func_175625_s(p_180709_3_);
         if(tileentity instanceof TileEntityMobSpawner) {
            ((TileEntityMobSpawner)tileentity).func_145881_a().func_98272_a(this.func_76543_b(p_180709_2_));
         } else {
            field_175918_a.error("Failed to fetch mob spawner entity at (" + p_180709_3_.func_177958_n() + ", " + p_180709_3_.func_177956_o() + ", " + p_180709_3_.func_177952_p() + ")");
         }

         return true;
      } else {
         return false;
      }
   }

   private String func_76543_b(Random p_76543_1_) {
      return field_175916_b[p_76543_1_.nextInt(field_175916_b.length)];
   }
}
