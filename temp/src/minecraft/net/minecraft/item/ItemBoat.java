package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBoat extends Item {
   public ItemBoat() {
      this.field_77777_bU = 1;
      this.func_77637_a(CreativeTabs.field_78029_e);
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      float f = 1.0F;
      float f1 = p_77659_3_.field_70127_C + (p_77659_3_.field_70125_A - p_77659_3_.field_70127_C) * f;
      float f2 = p_77659_3_.field_70126_B + (p_77659_3_.field_70177_z - p_77659_3_.field_70126_B) * f;
      double d0 = p_77659_3_.field_70169_q + (p_77659_3_.field_70165_t - p_77659_3_.field_70169_q) * (double)f;
      double d1 = p_77659_3_.field_70167_r + (p_77659_3_.field_70163_u - p_77659_3_.field_70167_r) * (double)f + (double)p_77659_3_.func_70047_e();
      double d2 = p_77659_3_.field_70166_s + (p_77659_3_.field_70161_v - p_77659_3_.field_70166_s) * (double)f;
      Vec3 vec3 = new Vec3(d0, d1, d2);
      float f3 = MathHelper.func_76134_b(-f2 * 0.017453292F - 3.1415927F);
      float f4 = MathHelper.func_76126_a(-f2 * 0.017453292F - 3.1415927F);
      float f5 = -MathHelper.func_76134_b(-f1 * 0.017453292F);
      float f6 = MathHelper.func_76126_a(-f1 * 0.017453292F);
      float f7 = f4 * f5;
      float f8 = f3 * f5;
      double d3 = 5.0D;
      Vec3 vec31 = vec3.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
      MovingObjectPosition movingobjectposition = p_77659_2_.func_72901_a(vec3, vec31, true);
      if(movingobjectposition == null) {
         return p_77659_1_;
      } else {
         Vec3 vec32 = p_77659_3_.func_70676_i(f);
         boolean flag = false;
         float f9 = 1.0F;
         List<Entity> list = p_77659_2_.func_72839_b(p_77659_3_, p_77659_3_.func_174813_aQ().func_72321_a(vec32.field_72450_a * d3, vec32.field_72448_b * d3, vec32.field_72449_c * d3).func_72314_b((double)f9, (double)f9, (double)f9));

         for(int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity)list.get(i);
            if(entity.func_70067_L()) {
               float f10 = entity.func_70111_Y();
               AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_72314_b((double)f10, (double)f10, (double)f10);
               if(axisalignedbb.func_72318_a(vec3)) {
                  flag = true;
               }
            }
         }

         if(flag) {
            return p_77659_1_;
         } else {
            if(movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos blockpos = movingobjectposition.func_178782_a();
               if(p_77659_2_.func_180495_p(blockpos).func_177230_c() == Blocks.field_150431_aC) {
                  blockpos = blockpos.func_177977_b();
               }

               EntityBoat entityboat = new EntityBoat(p_77659_2_, (double)((float)blockpos.func_177958_n() + 0.5F), (double)((float)blockpos.func_177956_o() + 1.0F), (double)((float)blockpos.func_177952_p() + 0.5F));
               entityboat.field_70177_z = (float)(((MathHelper.func_76128_c((double)(p_77659_3_.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);
               if(!p_77659_2_.func_72945_a(entityboat, entityboat.func_174813_aQ().func_72314_b(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                  return p_77659_1_;
               }

               if(!p_77659_2_.field_72995_K) {
                  p_77659_2_.func_72838_d(entityboat);
               }

               if(!p_77659_3_.field_71075_bZ.field_75098_d) {
                  --p_77659_1_.field_77994_a;
               }

               p_77659_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
            }

            return p_77659_1_;
         }
      }
   }
}
