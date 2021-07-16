package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public abstract class StructureComponent {
   protected StructureBoundingBox field_74887_e;
   protected EnumFacing field_74885_f;
   protected int field_74886_g;

   public StructureComponent() {
   }

   protected StructureComponent(int p_i2091_1_) {
      this.field_74886_g = p_i2091_1_;
   }

   public NBTTagCompound func_143010_b() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      nbttagcompound.func_74778_a("id", MapGenStructureIO.func_143036_a(this));
      nbttagcompound.func_74782_a("BB", this.field_74887_e.func_151535_h());
      nbttagcompound.func_74768_a("O", this.field_74885_f == null?-1:this.field_74885_f.func_176736_b());
      nbttagcompound.func_74768_a("GD", this.field_74886_g);
      this.func_143012_a(nbttagcompound);
      return nbttagcompound;
   }

   protected abstract void func_143012_a(NBTTagCompound var1);

   public void func_143009_a(World p_143009_1_, NBTTagCompound p_143009_2_) {
      if(p_143009_2_.func_74764_b("BB")) {
         this.field_74887_e = new StructureBoundingBox(p_143009_2_.func_74759_k("BB"));
      }

      int i = p_143009_2_.func_74762_e("O");
      this.field_74885_f = i == -1?null:EnumFacing.func_176731_b(i);
      this.field_74886_g = p_143009_2_.func_74762_e("GD");
      this.func_143011_b(p_143009_2_);
   }

   protected abstract void func_143011_b(NBTTagCompound var1);

   public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
   }

   public abstract boolean func_74875_a(World var1, Random var2, StructureBoundingBox var3);

   public StructureBoundingBox func_74874_b() {
      return this.field_74887_e;
   }

   public int func_74877_c() {
      return this.field_74886_g;
   }

   public static StructureComponent func_74883_a(List<StructureComponent> p_74883_0_, StructureBoundingBox p_74883_1_) {
      for(StructureComponent structurecomponent : p_74883_0_) {
         if(structurecomponent.func_74874_b() != null && structurecomponent.func_74874_b().func_78884_a(p_74883_1_)) {
            return structurecomponent;
         }
      }

      return null;
   }

   public BlockPos func_180776_a() {
      return new BlockPos(this.field_74887_e.func_180717_f());
   }

   protected boolean func_74860_a(World p_74860_1_, StructureBoundingBox p_74860_2_) {
      int i = Math.max(this.field_74887_e.field_78897_a - 1, p_74860_2_.field_78897_a);
      int j = Math.max(this.field_74887_e.field_78895_b - 1, p_74860_2_.field_78895_b);
      int k = Math.max(this.field_74887_e.field_78896_c - 1, p_74860_2_.field_78896_c);
      int l = Math.min(this.field_74887_e.field_78893_d + 1, p_74860_2_.field_78893_d);
      int i1 = Math.min(this.field_74887_e.field_78894_e + 1, p_74860_2_.field_78894_e);
      int j1 = Math.min(this.field_74887_e.field_78892_f + 1, p_74860_2_.field_78892_f);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k1 = i; k1 <= l; ++k1) {
         for(int l1 = k; l1 <= j1; ++l1) {
            if(p_74860_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(k1, j, l1)).func_177230_c().func_149688_o().func_76224_d()) {
               return true;
            }

            if(p_74860_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(k1, i1, l1)).func_177230_c().func_149688_o().func_76224_d()) {
               return true;
            }
         }
      }

      for(int i2 = i; i2 <= l; ++i2) {
         for(int k2 = j; k2 <= i1; ++k2) {
            if(p_74860_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(i2, k2, k)).func_177230_c().func_149688_o().func_76224_d()) {
               return true;
            }

            if(p_74860_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(i2, k2, j1)).func_177230_c().func_149688_o().func_76224_d()) {
               return true;
            }
         }
      }

      for(int j2 = k; j2 <= j1; ++j2) {
         for(int l2 = j; l2 <= i1; ++l2) {
            if(p_74860_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(i, l2, j2)).func_177230_c().func_149688_o().func_76224_d()) {
               return true;
            }

            if(p_74860_1_.func_180495_p(blockpos$mutableblockpos.func_181079_c(l, l2, j2)).func_177230_c().func_149688_o().func_76224_d()) {
               return true;
            }
         }
      }

      return false;
   }

   protected int func_74865_a(int p_74865_1_, int p_74865_2_) {
      if(this.field_74885_f == null) {
         return p_74865_1_;
      } else {
         switch(this.field_74885_f) {
         case NORTH:
         case SOUTH:
            return this.field_74887_e.field_78897_a + p_74865_1_;
         case WEST:
            return this.field_74887_e.field_78893_d - p_74865_2_;
         case EAST:
            return this.field_74887_e.field_78897_a + p_74865_2_;
         default:
            return p_74865_1_;
         }
      }
   }

   protected int func_74862_a(int p_74862_1_) {
      return this.field_74885_f == null?p_74862_1_:p_74862_1_ + this.field_74887_e.field_78895_b;
   }

   protected int func_74873_b(int p_74873_1_, int p_74873_2_) {
      if(this.field_74885_f == null) {
         return p_74873_2_;
      } else {
         switch(this.field_74885_f) {
         case NORTH:
            return this.field_74887_e.field_78892_f - p_74873_2_;
         case SOUTH:
            return this.field_74887_e.field_78896_c + p_74873_2_;
         case WEST:
         case EAST:
            return this.field_74887_e.field_78896_c + p_74873_1_;
         default:
            return p_74873_2_;
         }
      }
   }

   protected int func_151555_a(Block p_151555_1_, int p_151555_2_) {
      if(p_151555_1_ == Blocks.field_150448_aq) {
         if(this.field_74885_f == EnumFacing.WEST || this.field_74885_f == EnumFacing.EAST) {
            if(p_151555_2_ == 1) {
               return 0;
            }

            return 1;
         }
      } else if(p_151555_1_ instanceof BlockDoor) {
         if(this.field_74885_f == EnumFacing.SOUTH) {
            if(p_151555_2_ == 0) {
               return 2;
            }

            if(p_151555_2_ == 2) {
               return 0;
            }
         } else {
            if(this.field_74885_f == EnumFacing.WEST) {
               return p_151555_2_ + 1 & 3;
            }

            if(this.field_74885_f == EnumFacing.EAST) {
               return p_151555_2_ + 3 & 3;
            }
         }
      } else if(p_151555_1_ != Blocks.field_150446_ar && p_151555_1_ != Blocks.field_150476_ad && p_151555_1_ != Blocks.field_150387_bl && p_151555_1_ != Blocks.field_150390_bg && p_151555_1_ != Blocks.field_150372_bz) {
         if(p_151555_1_ == Blocks.field_150468_ap) {
            if(this.field_74885_f == EnumFacing.SOUTH) {
               if(p_151555_2_ == EnumFacing.NORTH.func_176745_a()) {
                  return EnumFacing.SOUTH.func_176745_a();
               }

               if(p_151555_2_ == EnumFacing.SOUTH.func_176745_a()) {
                  return EnumFacing.NORTH.func_176745_a();
               }
            } else if(this.field_74885_f == EnumFacing.WEST) {
               if(p_151555_2_ == EnumFacing.NORTH.func_176745_a()) {
                  return EnumFacing.WEST.func_176745_a();
               }

               if(p_151555_2_ == EnumFacing.SOUTH.func_176745_a()) {
                  return EnumFacing.EAST.func_176745_a();
               }

               if(p_151555_2_ == EnumFacing.WEST.func_176745_a()) {
                  return EnumFacing.NORTH.func_176745_a();
               }

               if(p_151555_2_ == EnumFacing.EAST.func_176745_a()) {
                  return EnumFacing.SOUTH.func_176745_a();
               }
            } else if(this.field_74885_f == EnumFacing.EAST) {
               if(p_151555_2_ == EnumFacing.NORTH.func_176745_a()) {
                  return EnumFacing.EAST.func_176745_a();
               }

               if(p_151555_2_ == EnumFacing.SOUTH.func_176745_a()) {
                  return EnumFacing.WEST.func_176745_a();
               }

               if(p_151555_2_ == EnumFacing.WEST.func_176745_a()) {
                  return EnumFacing.NORTH.func_176745_a();
               }

               if(p_151555_2_ == EnumFacing.EAST.func_176745_a()) {
                  return EnumFacing.SOUTH.func_176745_a();
               }
            }
         } else if(p_151555_1_ == Blocks.field_150430_aB) {
            if(this.field_74885_f == EnumFacing.SOUTH) {
               if(p_151555_2_ == 3) {
                  return 4;
               }

               if(p_151555_2_ == 4) {
                  return 3;
               }
            } else if(this.field_74885_f == EnumFacing.WEST) {
               if(p_151555_2_ == 3) {
                  return 1;
               }

               if(p_151555_2_ == 4) {
                  return 2;
               }

               if(p_151555_2_ == 2) {
                  return 3;
               }

               if(p_151555_2_ == 1) {
                  return 4;
               }
            } else if(this.field_74885_f == EnumFacing.EAST) {
               if(p_151555_2_ == 3) {
                  return 2;
               }

               if(p_151555_2_ == 4) {
                  return 1;
               }

               if(p_151555_2_ == 2) {
                  return 3;
               }

               if(p_151555_2_ == 1) {
                  return 4;
               }
            }
         } else if(p_151555_1_ != Blocks.field_150479_bC && !(p_151555_1_ instanceof BlockDirectional)) {
            if(p_151555_1_ == Blocks.field_150331_J || p_151555_1_ == Blocks.field_150320_F || p_151555_1_ == Blocks.field_150442_at || p_151555_1_ == Blocks.field_150367_z) {
               if(this.field_74885_f == EnumFacing.SOUTH) {
                  if(p_151555_2_ == EnumFacing.NORTH.func_176745_a() || p_151555_2_ == EnumFacing.SOUTH.func_176745_a()) {
                     return EnumFacing.func_82600_a(p_151555_2_).func_176734_d().func_176745_a();
                  }
               } else if(this.field_74885_f == EnumFacing.WEST) {
                  if(p_151555_2_ == EnumFacing.NORTH.func_176745_a()) {
                     return EnumFacing.WEST.func_176745_a();
                  }

                  if(p_151555_2_ == EnumFacing.SOUTH.func_176745_a()) {
                     return EnumFacing.EAST.func_176745_a();
                  }

                  if(p_151555_2_ == EnumFacing.WEST.func_176745_a()) {
                     return EnumFacing.NORTH.func_176745_a();
                  }

                  if(p_151555_2_ == EnumFacing.EAST.func_176745_a()) {
                     return EnumFacing.SOUTH.func_176745_a();
                  }
               } else if(this.field_74885_f == EnumFacing.EAST) {
                  if(p_151555_2_ == EnumFacing.NORTH.func_176745_a()) {
                     return EnumFacing.EAST.func_176745_a();
                  }

                  if(p_151555_2_ == EnumFacing.SOUTH.func_176745_a()) {
                     return EnumFacing.WEST.func_176745_a();
                  }

                  if(p_151555_2_ == EnumFacing.WEST.func_176745_a()) {
                     return EnumFacing.NORTH.func_176745_a();
                  }

                  if(p_151555_2_ == EnumFacing.EAST.func_176745_a()) {
                     return EnumFacing.SOUTH.func_176745_a();
                  }
               }
            }
         } else {
            EnumFacing enumfacing = EnumFacing.func_176731_b(p_151555_2_);
            if(this.field_74885_f == EnumFacing.SOUTH) {
               if(enumfacing == EnumFacing.SOUTH || enumfacing == EnumFacing.NORTH) {
                  return enumfacing.func_176734_d().func_176736_b();
               }
            } else if(this.field_74885_f == EnumFacing.WEST) {
               if(enumfacing == EnumFacing.NORTH) {
                  return EnumFacing.WEST.func_176736_b();
               }

               if(enumfacing == EnumFacing.SOUTH) {
                  return EnumFacing.EAST.func_176736_b();
               }

               if(enumfacing == EnumFacing.WEST) {
                  return EnumFacing.NORTH.func_176736_b();
               }

               if(enumfacing == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.func_176736_b();
               }
            } else if(this.field_74885_f == EnumFacing.EAST) {
               if(enumfacing == EnumFacing.NORTH) {
                  return EnumFacing.EAST.func_176736_b();
               }

               if(enumfacing == EnumFacing.SOUTH) {
                  return EnumFacing.WEST.func_176736_b();
               }

               if(enumfacing == EnumFacing.WEST) {
                  return EnumFacing.NORTH.func_176736_b();
               }

               if(enumfacing == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.func_176736_b();
               }
            }
         }
      } else if(this.field_74885_f == EnumFacing.SOUTH) {
         if(p_151555_2_ == 2) {
            return 3;
         }

         if(p_151555_2_ == 3) {
            return 2;
         }
      } else if(this.field_74885_f == EnumFacing.WEST) {
         if(p_151555_2_ == 0) {
            return 2;
         }

         if(p_151555_2_ == 1) {
            return 3;
         }

         if(p_151555_2_ == 2) {
            return 0;
         }

         if(p_151555_2_ == 3) {
            return 1;
         }
      } else if(this.field_74885_f == EnumFacing.EAST) {
         if(p_151555_2_ == 0) {
            return 2;
         }

         if(p_151555_2_ == 1) {
            return 3;
         }

         if(p_151555_2_ == 2) {
            return 1;
         }

         if(p_151555_2_ == 3) {
            return 0;
         }
      }

      return p_151555_2_;
   }

   protected void func_175811_a(World p_175811_1_, IBlockState p_175811_2_, int p_175811_3_, int p_175811_4_, int p_175811_5_, StructureBoundingBox p_175811_6_) {
      BlockPos blockpos = new BlockPos(this.func_74865_a(p_175811_3_, p_175811_5_), this.func_74862_a(p_175811_4_), this.func_74873_b(p_175811_3_, p_175811_5_));
      if(p_175811_6_.func_175898_b(blockpos)) {
         p_175811_1_.func_180501_a(blockpos, p_175811_2_, 2);
      }
   }

   protected IBlockState func_175807_a(World p_175807_1_, int p_175807_2_, int p_175807_3_, int p_175807_4_, StructureBoundingBox p_175807_5_) {
      int i = this.func_74865_a(p_175807_2_, p_175807_4_);
      int j = this.func_74862_a(p_175807_3_);
      int k = this.func_74873_b(p_175807_2_, p_175807_4_);
      BlockPos blockpos = new BlockPos(i, j, k);
      return !p_175807_5_.func_175898_b(blockpos)?Blocks.field_150350_a.func_176223_P():p_175807_1_.func_180495_p(blockpos);
   }

   protected void func_74878_a(World p_74878_1_, StructureBoundingBox p_74878_2_, int p_74878_3_, int p_74878_4_, int p_74878_5_, int p_74878_6_, int p_74878_7_, int p_74878_8_) {
      for(int i = p_74878_4_; i <= p_74878_7_; ++i) {
         for(int j = p_74878_3_; j <= p_74878_6_; ++j) {
            for(int k = p_74878_5_; k <= p_74878_8_; ++k) {
               this.func_175811_a(p_74878_1_, Blocks.field_150350_a.func_176223_P(), j, i, k, p_74878_2_);
            }
         }
      }

   }

   protected void func_175804_a(World p_175804_1_, StructureBoundingBox p_175804_2_, int p_175804_3_, int p_175804_4_, int p_175804_5_, int p_175804_6_, int p_175804_7_, int p_175804_8_, IBlockState p_175804_9_, IBlockState p_175804_10_, boolean p_175804_11_) {
      for(int i = p_175804_4_; i <= p_175804_7_; ++i) {
         for(int j = p_175804_3_; j <= p_175804_6_; ++j) {
            for(int k = p_175804_5_; k <= p_175804_8_; ++k) {
               if(!p_175804_11_ || this.func_175807_a(p_175804_1_, j, i, k, p_175804_2_).func_177230_c().func_149688_o() != Material.field_151579_a) {
                  if(i != p_175804_4_ && i != p_175804_7_ && j != p_175804_3_ && j != p_175804_6_ && k != p_175804_5_ && k != p_175804_8_) {
                     this.func_175811_a(p_175804_1_, p_175804_10_, j, i, k, p_175804_2_);
                  } else {
                     this.func_175811_a(p_175804_1_, p_175804_9_, j, i, k, p_175804_2_);
                  }
               }
            }
         }
      }

   }

   protected void func_74882_a(World p_74882_1_, StructureBoundingBox p_74882_2_, int p_74882_3_, int p_74882_4_, int p_74882_5_, int p_74882_6_, int p_74882_7_, int p_74882_8_, boolean p_74882_9_, Random p_74882_10_, StructureComponent.BlockSelector p_74882_11_) {
      for(int i = p_74882_4_; i <= p_74882_7_; ++i) {
         for(int j = p_74882_3_; j <= p_74882_6_; ++j) {
            for(int k = p_74882_5_; k <= p_74882_8_; ++k) {
               if(!p_74882_9_ || this.func_175807_a(p_74882_1_, j, i, k, p_74882_2_).func_177230_c().func_149688_o() != Material.field_151579_a) {
                  p_74882_11_.func_75062_a(p_74882_10_, j, i, k, i == p_74882_4_ || i == p_74882_7_ || j == p_74882_3_ || j == p_74882_6_ || k == p_74882_5_ || k == p_74882_8_);
                  this.func_175811_a(p_74882_1_, p_74882_11_.func_180780_a(), j, i, k, p_74882_2_);
               }
            }
         }
      }

   }

   protected void func_175805_a(World p_175805_1_, StructureBoundingBox p_175805_2_, Random p_175805_3_, float p_175805_4_, int p_175805_5_, int p_175805_6_, int p_175805_7_, int p_175805_8_, int p_175805_9_, int p_175805_10_, IBlockState p_175805_11_, IBlockState p_175805_12_, boolean p_175805_13_) {
      for(int i = p_175805_6_; i <= p_175805_9_; ++i) {
         for(int j = p_175805_5_; j <= p_175805_8_; ++j) {
            for(int k = p_175805_7_; k <= p_175805_10_; ++k) {
               if(p_175805_3_.nextFloat() <= p_175805_4_ && (!p_175805_13_ || this.func_175807_a(p_175805_1_, j, i, k, p_175805_2_).func_177230_c().func_149688_o() != Material.field_151579_a)) {
                  if(i != p_175805_6_ && i != p_175805_9_ && j != p_175805_5_ && j != p_175805_8_ && k != p_175805_7_ && k != p_175805_10_) {
                     this.func_175811_a(p_175805_1_, p_175805_12_, j, i, k, p_175805_2_);
                  } else {
                     this.func_175811_a(p_175805_1_, p_175805_11_, j, i, k, p_175805_2_);
                  }
               }
            }
         }
      }

   }

   protected void func_175809_a(World p_175809_1_, StructureBoundingBox p_175809_2_, Random p_175809_3_, float p_175809_4_, int p_175809_5_, int p_175809_6_, int p_175809_7_, IBlockState p_175809_8_) {
      if(p_175809_3_.nextFloat() < p_175809_4_) {
         this.func_175811_a(p_175809_1_, p_175809_8_, p_175809_5_, p_175809_6_, p_175809_7_, p_175809_2_);
      }

   }

   protected void func_180777_a(World p_180777_1_, StructureBoundingBox p_180777_2_, int p_180777_3_, int p_180777_4_, int p_180777_5_, int p_180777_6_, int p_180777_7_, int p_180777_8_, IBlockState p_180777_9_, boolean p_180777_10_) {
      float f = (float)(p_180777_6_ - p_180777_3_ + 1);
      float f1 = (float)(p_180777_7_ - p_180777_4_ + 1);
      float f2 = (float)(p_180777_8_ - p_180777_5_ + 1);
      float f3 = (float)p_180777_3_ + f / 2.0F;
      float f4 = (float)p_180777_5_ + f2 / 2.0F;

      for(int i = p_180777_4_; i <= p_180777_7_; ++i) {
         float f5 = (float)(i - p_180777_4_) / f1;

         for(int j = p_180777_3_; j <= p_180777_6_; ++j) {
            float f6 = ((float)j - f3) / (f * 0.5F);

            for(int k = p_180777_5_; k <= p_180777_8_; ++k) {
               float f7 = ((float)k - f4) / (f2 * 0.5F);
               if(!p_180777_10_ || this.func_175807_a(p_180777_1_, j, i, k, p_180777_2_).func_177230_c().func_149688_o() != Material.field_151579_a) {
                  float f8 = f6 * f6 + f5 * f5 + f7 * f7;
                  if(f8 <= 1.05F) {
                     this.func_175811_a(p_180777_1_, p_180777_9_, j, i, k, p_180777_2_);
                  }
               }
            }
         }
      }

   }

   protected void func_74871_b(World p_74871_1_, int p_74871_2_, int p_74871_3_, int p_74871_4_, StructureBoundingBox p_74871_5_) {
      BlockPos blockpos = new BlockPos(this.func_74865_a(p_74871_2_, p_74871_4_), this.func_74862_a(p_74871_3_), this.func_74873_b(p_74871_2_, p_74871_4_));
      if(p_74871_5_.func_175898_b(blockpos)) {
         while(!p_74871_1_.func_175623_d(blockpos) && blockpos.func_177956_o() < 255) {
            p_74871_1_.func_180501_a(blockpos, Blocks.field_150350_a.func_176223_P(), 2);
            blockpos = blockpos.func_177984_a();
         }

      }
   }

   protected void func_175808_b(World p_175808_1_, IBlockState p_175808_2_, int p_175808_3_, int p_175808_4_, int p_175808_5_, StructureBoundingBox p_175808_6_) {
      int i = this.func_74865_a(p_175808_3_, p_175808_5_);
      int j = this.func_74862_a(p_175808_4_);
      int k = this.func_74873_b(p_175808_3_, p_175808_5_);
      if(p_175808_6_.func_175898_b(new BlockPos(i, j, k))) {
         while((p_175808_1_.func_175623_d(new BlockPos(i, j, k)) || p_175808_1_.func_180495_p(new BlockPos(i, j, k)).func_177230_c().func_149688_o().func_76224_d()) && j > 1) {
            p_175808_1_.func_180501_a(new BlockPos(i, j, k), p_175808_2_, 2);
            --j;
         }

      }
   }

   protected boolean func_180778_a(World p_180778_1_, StructureBoundingBox p_180778_2_, Random p_180778_3_, int p_180778_4_, int p_180778_5_, int p_180778_6_, List<WeightedRandomChestContent> p_180778_7_, int p_180778_8_) {
      BlockPos blockpos = new BlockPos(this.func_74865_a(p_180778_4_, p_180778_6_), this.func_74862_a(p_180778_5_), this.func_74873_b(p_180778_4_, p_180778_6_));
      if(p_180778_2_.func_175898_b(blockpos) && p_180778_1_.func_180495_p(blockpos).func_177230_c() != Blocks.field_150486_ae) {
         IBlockState iblockstate = Blocks.field_150486_ae.func_176223_P();
         p_180778_1_.func_180501_a(blockpos, Blocks.field_150486_ae.func_176458_f(p_180778_1_, blockpos, iblockstate), 2);
         TileEntity tileentity = p_180778_1_.func_175625_s(blockpos);
         if(tileentity instanceof TileEntityChest) {
            WeightedRandomChestContent.func_177630_a(p_180778_3_, p_180778_7_, (TileEntityChest)tileentity, p_180778_8_);
         }

         return true;
      } else {
         return false;
      }
   }

   protected boolean func_175806_a(World p_175806_1_, StructureBoundingBox p_175806_2_, Random p_175806_3_, int p_175806_4_, int p_175806_5_, int p_175806_6_, int p_175806_7_, List<WeightedRandomChestContent> p_175806_8_, int p_175806_9_) {
      BlockPos blockpos = new BlockPos(this.func_74865_a(p_175806_4_, p_175806_6_), this.func_74862_a(p_175806_5_), this.func_74873_b(p_175806_4_, p_175806_6_));
      if(p_175806_2_.func_175898_b(blockpos) && p_175806_1_.func_180495_p(blockpos).func_177230_c() != Blocks.field_150367_z) {
         p_175806_1_.func_180501_a(blockpos, Blocks.field_150367_z.func_176203_a(this.func_151555_a(Blocks.field_150367_z, p_175806_7_)), 2);
         TileEntity tileentity = p_175806_1_.func_175625_s(blockpos);
         if(tileentity instanceof TileEntityDispenser) {
            WeightedRandomChestContent.func_177631_a(p_175806_3_, p_175806_8_, (TileEntityDispenser)tileentity, p_175806_9_);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void func_175810_a(World p_175810_1_, StructureBoundingBox p_175810_2_, Random p_175810_3_, int p_175810_4_, int p_175810_5_, int p_175810_6_, EnumFacing p_175810_7_) {
      BlockPos blockpos = new BlockPos(this.func_74865_a(p_175810_4_, p_175810_6_), this.func_74862_a(p_175810_5_), this.func_74873_b(p_175810_4_, p_175810_6_));
      if(p_175810_2_.func_175898_b(blockpos)) {
         ItemDoor.func_179235_a(p_175810_1_, blockpos, p_175810_7_.func_176735_f(), Blocks.field_180413_ao);
      }

   }

   public void func_181138_a(int p_181138_1_, int p_181138_2_, int p_181138_3_) {
      this.field_74887_e.func_78886_a(p_181138_1_, p_181138_2_, p_181138_3_);
   }

   public abstract static class BlockSelector {
      protected IBlockState field_151562_a = Blocks.field_150350_a.func_176223_P();

      public abstract void func_75062_a(Random var1, int var2, int var3, int var4, boolean var5);

      public IBlockState func_180780_a() {
         return this.field_151562_a;
      }
   }
}
