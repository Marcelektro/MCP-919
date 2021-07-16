package net.minecraft.item;

import java.util.List;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMonsterPlacer extends Item {
   public ItemMonsterPlacer() {
      this.func_77627_a(true);
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      String s = ("" + StatCollector.func_74838_a(this.func_77658_a() + ".name")).trim();
      String s1 = EntityList.func_75617_a(p_77653_1_.func_77960_j());
      if(s1 != null) {
         s = s + " " + StatCollector.func_74838_a("entity." + s1 + ".name");
      }

      return s;
   }

   public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
      EntityList.EntityEggInfo entitylist$entityegginfo = (EntityList.EntityEggInfo)EntityList.field_75627_a.get(Integer.valueOf(p_82790_1_.func_77960_j()));
      return entitylist$entityegginfo != null?(p_82790_2_ == 0?entitylist$entityegginfo.field_75611_b:entitylist$entityegginfo.field_75612_c):16777215;
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(p_180614_3_.field_72995_K) {
         return true;
      } else if(!p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_)) {
         return false;
      } else {
         IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
         if(iblockstate.func_177230_c() == Blocks.field_150474_ac) {
            TileEntity tileentity = p_180614_3_.func_175625_s(p_180614_4_);
            if(tileentity instanceof TileEntityMobSpawner) {
               MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner)tileentity).func_145881_a();
               mobspawnerbaselogic.func_98272_a(EntityList.func_75617_a(p_180614_1_.func_77960_j()));
               tileentity.func_70296_d();
               p_180614_3_.func_175689_h(p_180614_4_);
               if(!p_180614_2_.field_71075_bZ.field_75098_d) {
                  --p_180614_1_.field_77994_a;
               }

               return true;
            }
         }

         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_5_);
         double d0 = 0.0D;
         if(p_180614_5_ == EnumFacing.UP && iblockstate instanceof BlockFence) {
            d0 = 0.5D;
         }

         Entity entity = func_77840_a(p_180614_3_, p_180614_1_.func_77960_j(), (double)p_180614_4_.func_177958_n() + 0.5D, (double)p_180614_4_.func_177956_o() + d0, (double)p_180614_4_.func_177952_p() + 0.5D);
         if(entity != null) {
            if(entity instanceof EntityLivingBase && p_180614_1_.func_82837_s()) {
               entity.func_96094_a(p_180614_1_.func_82833_r());
            }

            if(!p_180614_2_.field_71075_bZ.field_75098_d) {
               --p_180614_1_.field_77994_a;
            }
         }

         return true;
      }
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if(p_77659_2_.field_72995_K) {
         return p_77659_1_;
      } else {
         MovingObjectPosition movingobjectposition = this.func_77621_a(p_77659_2_, p_77659_3_, true);
         if(movingobjectposition == null) {
            return p_77659_1_;
         } else {
            if(movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos blockpos = movingobjectposition.func_178782_a();
               if(!p_77659_2_.func_175660_a(p_77659_3_, blockpos)) {
                  return p_77659_1_;
               }

               if(!p_77659_3_.func_175151_a(blockpos, movingobjectposition.field_178784_b, p_77659_1_)) {
                  return p_77659_1_;
               }

               if(p_77659_2_.func_180495_p(blockpos).func_177230_c() instanceof BlockLiquid) {
                  Entity entity = func_77840_a(p_77659_2_, p_77659_1_.func_77960_j(), (double)blockpos.func_177958_n() + 0.5D, (double)blockpos.func_177956_o() + 0.5D, (double)blockpos.func_177952_p() + 0.5D);
                  if(entity != null) {
                     if(entity instanceof EntityLivingBase && p_77659_1_.func_82837_s()) {
                        ((EntityLiving)entity).func_96094_a(p_77659_1_.func_82833_r());
                     }

                     if(!p_77659_3_.field_71075_bZ.field_75098_d) {
                        --p_77659_1_.field_77994_a;
                     }

                     p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
                  }
               }
            }

            return p_77659_1_;
         }
      }
   }

   public static Entity func_77840_a(World p_77840_0_, int p_77840_1_, double p_77840_2_, double p_77840_4_, double p_77840_6_) {
      if(!EntityList.field_75627_a.containsKey(Integer.valueOf(p_77840_1_))) {
         return null;
      } else {
         Entity entity = null;

         for(int i = 0; i < 1; ++i) {
            entity = EntityList.func_75616_a(p_77840_1_, p_77840_0_);
            if(entity instanceof EntityLivingBase) {
               EntityLiving entityliving = (EntityLiving)entity;
               entity.func_70012_b(p_77840_2_, p_77840_4_, p_77840_6_, MathHelper.func_76142_g(p_77840_0_.field_73012_v.nextFloat() * 360.0F), 0.0F);
               entityliving.field_70759_as = entityliving.field_70177_z;
               entityliving.field_70761_aq = entityliving.field_70177_z;
               entityliving.func_180482_a(p_77840_0_.func_175649_E(new BlockPos(entityliving)), (IEntityLivingData)null);
               p_77840_0_.func_72838_d(entity);
               entityliving.func_70642_aH();
            }
         }

         return entity;
      }
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      for(EntityList.EntityEggInfo entitylist$entityegginfo : EntityList.field_75627_a.values()) {
         p_150895_3_.add(new ItemStack(p_150895_1_, 1, entitylist$entityegginfo.field_75613_a));
      }

   }
}
