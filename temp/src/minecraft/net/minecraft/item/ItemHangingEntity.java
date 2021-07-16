package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemHangingEntity extends Item {
   private final Class<? extends EntityHanging> field_82811_a;

   public ItemHangingEntity(Class<? extends EntityHanging> p_i45342_1_) {
      this.field_82811_a = p_i45342_1_;
      this.func_77637_a(CreativeTabs.field_78031_c);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(p_180614_5_ == EnumFacing.DOWN) {
         return false;
      } else if(p_180614_5_ == EnumFacing.UP) {
         return false;
      } else {
         BlockPos blockpos = p_180614_4_.func_177972_a(p_180614_5_);
         if(!p_180614_2_.func_175151_a(blockpos, p_180614_5_, p_180614_1_)) {
            return false;
         } else {
            EntityHanging entityhanging = this.func_179233_a(p_180614_3_, blockpos, p_180614_5_);
            if(entityhanging != null && entityhanging.func_70518_d()) {
               if(!p_180614_3_.field_72995_K) {
                  p_180614_3_.func_72838_d(entityhanging);
               }

               --p_180614_1_.field_77994_a;
            }

            return true;
         }
      }
   }

   private EntityHanging func_179233_a(World p_179233_1_, BlockPos p_179233_2_, EnumFacing p_179233_3_) {
      return (EntityHanging)(this.field_82811_a == EntityPainting.class?new EntityPainting(p_179233_1_, p_179233_2_, p_179233_3_):(this.field_82811_a == EntityItemFrame.class?new EntityItemFrame(p_179233_1_, p_179233_2_, p_179233_3_):null));
   }
}
