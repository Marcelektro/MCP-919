package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockBed extends BlockDirectional {
   public static final PropertyEnum<BlockBed.EnumPartType> field_176472_a = PropertyEnum.<BlockBed.EnumPartType>func_177709_a("part", BlockBed.EnumPartType.class);
   public static final PropertyBool field_176471_b = PropertyBool.func_177716_a("occupied");

   public BlockBed() {
      super(Material.field_151580_n);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176472_a, BlockBed.EnumPartType.FOOT).func_177226_a(field_176471_b, Boolean.valueOf(false)));
      this.func_149978_e();
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(p_180639_1_.field_72995_K) {
         return true;
      } else {
         if(p_180639_3_.func_177229_b(field_176472_a) != BlockBed.EnumPartType.HEAD) {
            p_180639_2_ = p_180639_2_.func_177972_a((EnumFacing)p_180639_3_.func_177229_b(field_176387_N));
            p_180639_3_ = p_180639_1_.func_180495_p(p_180639_2_);
            if(p_180639_3_.func_177230_c() != this) {
               return true;
            }
         }

         if(p_180639_1_.field_73011_w.func_76567_e() && p_180639_1_.func_180494_b(p_180639_2_) != BiomeGenBase.field_76778_j) {
            if(((Boolean)p_180639_3_.func_177229_b(field_176471_b)).booleanValue()) {
               EntityPlayer entityplayer = this.func_176470_e(p_180639_1_, p_180639_2_);
               if(entityplayer != null) {
                  p_180639_4_.func_146105_b(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
                  return true;
               }

               p_180639_3_ = p_180639_3_.func_177226_a(field_176471_b, Boolean.valueOf(false));
               p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 4);
            }

            EntityPlayer.EnumStatus entityplayer$enumstatus = p_180639_4_.func_180469_a(p_180639_2_);
            if(entityplayer$enumstatus == EntityPlayer.EnumStatus.OK) {
               p_180639_3_ = p_180639_3_.func_177226_a(field_176471_b, Boolean.valueOf(true));
               p_180639_1_.func_180501_a(p_180639_2_, p_180639_3_, 4);
               return true;
            } else {
               if(entityplayer$enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
                  p_180639_4_.func_146105_b(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
               } else if(entityplayer$enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
                  p_180639_4_.func_146105_b(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
               }

               return true;
            }
         } else {
            p_180639_1_.func_175698_g(p_180639_2_);
            BlockPos blockpos = p_180639_2_.func_177972_a(((EnumFacing)p_180639_3_.func_177229_b(field_176387_N)).func_176734_d());
            if(p_180639_1_.func_180495_p(blockpos).func_177230_c() == this) {
               p_180639_1_.func_175698_g(blockpos);
            }

            p_180639_1_.func_72885_a((Entity)null, (double)p_180639_2_.func_177958_n() + 0.5D, (double)p_180639_2_.func_177956_o() + 0.5D, (double)p_180639_2_.func_177952_p() + 0.5D, 5.0F, true, true);
            return true;
         }
      }
   }

   private EntityPlayer func_176470_e(World p_176470_1_, BlockPos p_176470_2_) {
      for(EntityPlayer entityplayer : p_176470_1_.field_73010_i) {
         if(entityplayer.func_70608_bn() && entityplayer.field_71081_bT.equals(p_176470_2_)) {
            return entityplayer;
         }
      }

      return null;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      this.func_149978_e();
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      EnumFacing enumfacing = (EnumFacing)p_176204_3_.func_177229_b(field_176387_N);
      if(p_176204_3_.func_177229_b(field_176472_a) == BlockBed.EnumPartType.HEAD) {
         if(p_176204_1_.func_180495_p(p_176204_2_.func_177972_a(enumfacing.func_176734_d())).func_177230_c() != this) {
            p_176204_1_.func_175698_g(p_176204_2_);
         }
      } else if(p_176204_1_.func_180495_p(p_176204_2_.func_177972_a(enumfacing)).func_177230_c() != this) {
         p_176204_1_.func_175698_g(p_176204_2_);
         if(!p_176204_1_.field_72995_K) {
            this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         }
      }

   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return p_180660_1_.func_177229_b(field_176472_a) == BlockBed.EnumPartType.HEAD?null:Items.field_151104_aV;
   }

   private void func_149978_e() {
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
   }

   public static BlockPos func_176468_a(World p_176468_0_, BlockPos p_176468_1_, int p_176468_2_) {
      EnumFacing enumfacing = (EnumFacing)p_176468_0_.func_180495_p(p_176468_1_).func_177229_b(field_176387_N);
      int i = p_176468_1_.func_177958_n();
      int j = p_176468_1_.func_177956_o();
      int k = p_176468_1_.func_177952_p();

      for(int l = 0; l <= 1; ++l) {
         int i1 = i - enumfacing.func_82601_c() * l - 1;
         int j1 = k - enumfacing.func_82599_e() * l - 1;
         int k1 = i1 + 2;
         int l1 = j1 + 2;

         for(int i2 = i1; i2 <= k1; ++i2) {
            for(int j2 = j1; j2 <= l1; ++j2) {
               BlockPos blockpos = new BlockPos(i2, j, j2);
               if(func_176469_d(p_176468_0_, blockpos)) {
                  if(p_176468_2_ <= 0) {
                     return blockpos;
                  }

                  --p_176468_2_;
               }
            }
         }
      }

      return null;
   }

   protected static boolean func_176469_d(World p_176469_0_, BlockPos p_176469_1_) {
      return World.func_175683_a(p_176469_0_, p_176469_1_.func_177977_b()) && !p_176469_0_.func_180495_p(p_176469_1_).func_177230_c().func_149688_o().func_76220_a() && !p_176469_0_.func_180495_p(p_176469_1_.func_177984_a()).func_177230_c().func_149688_o().func_76220_a();
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
      if(p_180653_3_.func_177229_b(field_176472_a) == BlockBed.EnumPartType.FOOT) {
         super.func_180653_a(p_180653_1_, p_180653_2_, p_180653_3_, p_180653_4_, 0);
      }

   }

   public int func_149656_h() {
      return 1;
   }

   public EnumWorldBlockLayer func_180664_k() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151104_aV;
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      if(p_176208_4_.field_71075_bZ.field_75098_d && p_176208_3_.func_177229_b(field_176472_a) == BlockBed.EnumPartType.HEAD) {
         BlockPos blockpos = p_176208_2_.func_177972_a(((EnumFacing)p_176208_3_.func_177229_b(field_176387_N)).func_176734_d());
         if(p_176208_1_.func_180495_p(blockpos).func_177230_c() == this) {
            p_176208_1_.func_175698_g(blockpos);
         }
      }

   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing enumfacing = EnumFacing.func_176731_b(p_176203_1_);
      return (p_176203_1_ & 8) > 0?this.func_176223_P().func_177226_a(field_176472_a, BlockBed.EnumPartType.HEAD).func_177226_a(field_176387_N, enumfacing).func_177226_a(field_176471_b, Boolean.valueOf((p_176203_1_ & 4) > 0)):this.func_176223_P().func_177226_a(field_176472_a, BlockBed.EnumPartType.FOOT).func_177226_a(field_176387_N, enumfacing);
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      if(p_176221_1_.func_177229_b(field_176472_a) == BlockBed.EnumPartType.FOOT) {
         IBlockState iblockstate = p_176221_2_.func_180495_p(p_176221_3_.func_177972_a((EnumFacing)p_176221_1_.func_177229_b(field_176387_N)));
         if(iblockstate.func_177230_c() == this) {
            p_176221_1_ = p_176221_1_.func_177226_a(field_176471_b, iblockstate.func_177229_b(field_176471_b));
         }
      }

      return p_176221_1_;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176387_N)).func_176736_b();
      if(p_176201_1_.func_177229_b(field_176472_a) == BlockBed.EnumPartType.HEAD) {
         i |= 8;
         if(((Boolean)p_176201_1_.func_177229_b(field_176471_b)).booleanValue()) {
            i |= 4;
         }
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176387_N, field_176472_a, field_176471_b});
   }

   public static enum EnumPartType implements IStringSerializable {
      HEAD("head"),
      FOOT("foot");

      private final String field_177036_c;

      private EnumPartType(String p_i45735_3_) {
         this.field_177036_c = p_i45735_3_;
      }

      public String toString() {
         return this.field_177036_c;
      }

      public String func_176610_l() {
         return this.field_177036_c;
      }
   }
}
