package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSeeds extends Item {
   private Block field_150925_a;
   private Block field_77838_b;

   public ItemSeeds(Block p_i45352_1_, Block p_i45352_2_) {
      this.field_150925_a = p_i45352_1_;
      this.field_77838_b = p_i45352_2_;
      this.func_77637_a(CreativeTabs.field_78035_l);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(p_180614_5_ != EnumFacing.UP) {
         return false;
      } else if(!p_180614_2_.func_175151_a(p_180614_4_.func_177972_a(p_180614_5_), p_180614_5_, p_180614_1_)) {
         return false;
      } else if(p_180614_3_.func_180495_p(p_180614_4_).func_177230_c() == this.field_77838_b && p_180614_3_.func_175623_d(p_180614_4_.func_177984_a())) {
         p_180614_3_.func_175656_a(p_180614_4_.func_177984_a(), this.field_150925_a.func_176223_P());
         --p_180614_1_.field_77994_a;
         return true;
      } else {
         return false;
      }
   }
}
