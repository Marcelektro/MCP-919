package net.minecraft.item;

import java.util.List;
import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Rotations;
import net.minecraft.world.World;

public class ItemArmorStand extends Item {
   public ItemArmorStand() {
      this.func_77637_a(CreativeTabs.field_78031_c);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(p_180614_5_ == EnumFacing.DOWN) {
         return false;
      } else {
         boolean flag = p_180614_3_.func_180495_p(p_180614_4_).func_177230_c().func_176200_f(p_180614_3_, p_180614_4_);
         BlockPos blockpos = flag?p_180614_4_:p_180614_4_.func_177972_a(p_180614_5_);
         if(!p_180614_2_.func_175151_a(blockpos, p_180614_5_, p_180614_1_)) {
            return false;
         } else {
            BlockPos blockpos1 = blockpos.func_177984_a();
            boolean flag1 = !p_180614_3_.func_175623_d(blockpos) && !p_180614_3_.func_180495_p(blockpos).func_177230_c().func_176200_f(p_180614_3_, blockpos);
            flag1 = flag1 | (!p_180614_3_.func_175623_d(blockpos1) && !p_180614_3_.func_180495_p(blockpos1).func_177230_c().func_176200_f(p_180614_3_, blockpos1));
            if(flag1) {
               return false;
            } else {
               double d0 = (double)blockpos.func_177958_n();
               double d1 = (double)blockpos.func_177956_o();
               double d2 = (double)blockpos.func_177952_p();
               List<Entity> list = p_180614_3_.func_72839_b((Entity)null, AxisAlignedBB.func_178781_a(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));
               if(list.size() > 0) {
                  return false;
               } else {
                  if(!p_180614_3_.field_72995_K) {
                     p_180614_3_.func_175698_g(blockpos);
                     p_180614_3_.func_175698_g(blockpos1);
                     EntityArmorStand entityarmorstand = new EntityArmorStand(p_180614_3_, d0 + 0.5D, d1, d2 + 0.5D);
                     float f = (float)MathHelper.func_76141_d((MathHelper.func_76142_g(p_180614_2_.field_70177_z - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                     entityarmorstand.func_70012_b(d0 + 0.5D, d1, d2 + 0.5D, f, 0.0F);
                     this.func_179221_a(entityarmorstand, p_180614_3_.field_73012_v);
                     NBTTagCompound nbttagcompound = p_180614_1_.func_77978_p();
                     if(nbttagcompound != null && nbttagcompound.func_150297_b("EntityTag", 10)) {
                        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                        entityarmorstand.func_70039_c(nbttagcompound1);
                        nbttagcompound1.func_179237_a(nbttagcompound.func_74775_l("EntityTag"));
                        entityarmorstand.func_70020_e(nbttagcompound1);
                     }

                     p_180614_3_.func_72838_d(entityarmorstand);
                  }

                  --p_180614_1_.field_77994_a;
                  return true;
               }
            }
         }
      }
   }

   private void func_179221_a(EntityArmorStand p_179221_1_, Random p_179221_2_) {
      Rotations rotations = p_179221_1_.func_175418_s();
      float f = p_179221_2_.nextFloat() * 5.0F;
      float f1 = p_179221_2_.nextFloat() * 20.0F - 10.0F;
      Rotations rotations1 = new Rotations(rotations.func_179415_b() + f, rotations.func_179416_c() + f1, rotations.func_179413_d());
      p_179221_1_.func_175415_a(rotations1);
      rotations = p_179221_1_.func_175408_t();
      f = p_179221_2_.nextFloat() * 10.0F - 5.0F;
      rotations1 = new Rotations(rotations.func_179415_b(), rotations.func_179416_c() + f, rotations.func_179413_d());
      p_179221_1_.func_175424_b(rotations1);
   }
}
