package net.minecraft.item;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;

public class ItemMap extends ItemMapBase {
   protected ItemMap() {
      this.func_77627_a(true);
   }

   public static MapData func_150912_a(int p_150912_0_, World p_150912_1_) {
      String s = "map_" + p_150912_0_;
      MapData mapdata = (MapData)p_150912_1_.func_72943_a(MapData.class, s);
      if(mapdata == null) {
         mapdata = new MapData(s);
         p_150912_1_.func_72823_a(s, mapdata);
      }

      return mapdata;
   }

   public MapData func_77873_a(ItemStack p_77873_1_, World p_77873_2_) {
      String s = "map_" + p_77873_1_.func_77960_j();
      MapData mapdata = (MapData)p_77873_2_.func_72943_a(MapData.class, s);
      if(mapdata == null && !p_77873_2_.field_72995_K) {
         p_77873_1_.func_77964_b(p_77873_2_.func_72841_b("map"));
         s = "map_" + p_77873_1_.func_77960_j();
         mapdata = new MapData(s);
         mapdata.field_76197_d = 3;
         mapdata.func_176054_a((double)p_77873_2_.func_72912_H().func_76079_c(), (double)p_77873_2_.func_72912_H().func_76074_e(), mapdata.field_76197_d);
         mapdata.field_76200_c = (byte)p_77873_2_.field_73011_w.func_177502_q();
         mapdata.func_76185_a();
         p_77873_2_.func_72823_a(s, mapdata);
      }

      return mapdata;
   }

   public void func_77872_a(World p_77872_1_, Entity p_77872_2_, MapData p_77872_3_) {
      if(p_77872_1_.field_73011_w.func_177502_q() == p_77872_3_.field_76200_c && p_77872_2_ instanceof EntityPlayer) {
         int i = 1 << p_77872_3_.field_76197_d;
         int j = p_77872_3_.field_76201_a;
         int k = p_77872_3_.field_76199_b;
         int l = MathHelper.func_76128_c(p_77872_2_.field_70165_t - (double)j) / i + 64;
         int i1 = MathHelper.func_76128_c(p_77872_2_.field_70161_v - (double)k) / i + 64;
         int j1 = 128 / i;
         if(p_77872_1_.field_73011_w.func_177495_o()) {
            j1 /= 2;
         }

         MapData.MapInfo mapdata$mapinfo = p_77872_3_.func_82568_a((EntityPlayer)p_77872_2_);
         ++mapdata$mapinfo.field_82569_d;
         boolean flag = false;

         for(int k1 = l - j1 + 1; k1 < l + j1; ++k1) {
            if((k1 & 15) == (mapdata$mapinfo.field_82569_d & 15) || flag) {
               flag = false;
               double d0 = 0.0D;

               for(int l1 = i1 - j1 - 1; l1 < i1 + j1; ++l1) {
                  if(k1 >= 0 && l1 >= -1 && k1 < 128 && l1 < 128) {
                     int i2 = k1 - l;
                     int j2 = l1 - i1;
                     boolean flag1 = i2 * i2 + j2 * j2 > (j1 - 2) * (j1 - 2);
                     int k2 = (j / i + k1 - 64) * i;
                     int l2 = (k / i + l1 - 64) * i;
                     Multiset<MapColor> multiset = HashMultiset.<MapColor>create();
                     Chunk chunk = p_77872_1_.func_175726_f(new BlockPos(k2, 0, l2));
                     if(!chunk.func_76621_g()) {
                        int i3 = k2 & 15;
                        int j3 = l2 & 15;
                        int k3 = 0;
                        double d1 = 0.0D;
                        if(p_77872_1_.field_73011_w.func_177495_o()) {
                           int l3 = k2 + l2 * 231871;
                           l3 = l3 * l3 * 31287121 + l3 * 11;
                           if((l3 >> 20 & 1) == 0) {
                              multiset.add(Blocks.field_150346_d.func_180659_g(Blocks.field_150346_d.func_176223_P().func_177226_a(BlockDirt.field_176386_a, BlockDirt.DirtType.DIRT)), 10);
                           } else {
                              multiset.add(Blocks.field_150348_b.func_180659_g(Blocks.field_150348_b.func_176223_P().func_177226_a(BlockStone.field_176247_a, BlockStone.EnumType.STONE)), 100);
                           }

                           d1 = 100.0D;
                        } else {
                           BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                           for(int i4 = 0; i4 < i; ++i4) {
                              for(int j4 = 0; j4 < i; ++j4) {
                                 int k4 = chunk.func_76611_b(i4 + i3, j4 + j3) + 1;
                                 IBlockState iblockstate = Blocks.field_150350_a.func_176223_P();
                                 if(k4 > 1) {
                                    label541: {
                                       while(true) {
                                          --k4;
                                          iblockstate = chunk.func_177435_g(blockpos$mutableblockpos.func_181079_c(i4 + i3, k4, j4 + j3));
                                          if(iblockstate.func_177230_c().func_180659_g(iblockstate) != MapColor.field_151660_b || k4 <= 0) {
                                             break;
                                          }
                                       }

                                       if(k4 > 0 && iblockstate.func_177230_c().func_149688_o().func_76224_d()) {
                                          int l4 = k4 - 1;

                                          while(true) {
                                             Block block = chunk.func_177438_a(i4 + i3, l4--, j4 + j3);
                                             ++k3;
                                             if(l4 <= 0 || !block.func_149688_o().func_76224_d()) {
                                                break label541;
                                             }
                                          }
                                       }
                                    }
                                 }

                                 d1 += (double)k4 / (double)(i * i);
                                 multiset.add(iblockstate.func_177230_c().func_180659_g(iblockstate));
                              }
                           }
                        }

                        k3 = k3 / (i * i);
                        double d2 = (d1 - d0) * 4.0D / (double)(i + 4) + ((double)(k1 + l1 & 1) - 0.5D) * 0.4D;
                        int i5 = 1;
                        if(d2 > 0.6D) {
                           i5 = 2;
                        }

                        if(d2 < -0.6D) {
                           i5 = 0;
                        }

                        MapColor mapcolor = (MapColor)Iterables.getFirst(Multisets.<T>copyHighestCountFirst(multiset), MapColor.field_151660_b);
                        if(mapcolor == MapColor.field_151662_n) {
                           d2 = (double)k3 * 0.1D + (double)(k1 + l1 & 1) * 0.2D;
                           i5 = 1;
                           if(d2 < 0.5D) {
                              i5 = 2;
                           }

                           if(d2 > 0.9D) {
                              i5 = 0;
                           }
                        }

                        d0 = d1;
                        if(l1 >= 0 && i2 * i2 + j2 * j2 < j1 * j1 && (!flag1 || (k1 + l1 & 1) != 0)) {
                           byte b0 = p_77872_3_.field_76198_e[k1 + l1 * 128];
                           byte b1 = (byte)(mapcolor.field_76290_q * 4 + i5);
                           if(b0 != b1) {
                              p_77872_3_.field_76198_e[k1 + l1 * 128] = b1;
                              p_77872_3_.func_176053_a(k1, l1);
                              flag = true;
                           }
                        }
                     }
                  }
               }
            }
         }

      }
   }

   public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
      if(!p_77663_2_.field_72995_K) {
         MapData mapdata = this.func_77873_a(p_77663_1_, p_77663_2_);
         if(p_77663_3_ instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)p_77663_3_;
            mapdata.func_76191_a(entityplayer, p_77663_1_);
         }

         if(p_77663_5_) {
            this.func_77872_a(p_77663_2_, p_77663_3_, mapdata);
         }

      }
   }

   public Packet func_150911_c(ItemStack p_150911_1_, World p_150911_2_, EntityPlayer p_150911_3_) {
      return this.func_77873_a(p_150911_1_, p_150911_2_).func_176052_a(p_150911_1_, p_150911_2_, p_150911_3_);
   }

   public void func_77622_d(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {
      if(p_77622_1_.func_77942_o() && p_77622_1_.func_77978_p().func_74767_n("map_is_scaling")) {
         MapData mapdata = Items.field_151098_aY.func_77873_a(p_77622_1_, p_77622_2_);
         p_77622_1_.func_77964_b(p_77622_2_.func_72841_b("map"));
         MapData mapdata1 = new MapData("map_" + p_77622_1_.func_77960_j());
         mapdata1.field_76197_d = (byte)(mapdata.field_76197_d + 1);
         if(mapdata1.field_76197_d > 4) {
            mapdata1.field_76197_d = 4;
         }

         mapdata1.func_176054_a((double)mapdata.field_76201_a, (double)mapdata.field_76199_b, mapdata1.field_76197_d);
         mapdata1.field_76200_c = mapdata.field_76200_c;
         mapdata1.func_76185_a();
         p_77622_2_.func_72823_a("map_" + p_77622_1_.func_77960_j(), mapdata1);
      }

   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      MapData mapdata = this.func_77873_a(p_77624_1_, p_77624_2_.field_70170_p);
      if(p_77624_4_) {
         if(mapdata == null) {
            p_77624_3_.add("Unknown map");
         } else {
            p_77624_3_.add("Scaling at 1:" + (1 << mapdata.field_76197_d));
            p_77624_3_.add("(Level " + mapdata.field_76197_d + "/" + 4 + ")");
         }
      }

   }
}
