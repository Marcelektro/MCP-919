package net.minecraft.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityNote extends TileEntity {
   public byte field_145879_a;
   public boolean field_145880_i;

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74774_a("note", this.field_145879_a);
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_145879_a = p_145839_1_.func_74771_c("note");
      this.field_145879_a = (byte)MathHelper.func_76125_a(this.field_145879_a, 0, 24);
   }

   public void func_145877_a() {
      this.field_145879_a = (byte)((this.field_145879_a + 1) % 25);
      this.func_70296_d();
   }

   public void func_175108_a(World p_175108_1_, BlockPos p_175108_2_) {
      if(p_175108_1_.func_180495_p(p_175108_2_.func_177984_a()).func_177230_c().func_149688_o() == Material.field_151579_a) {
         Material material = p_175108_1_.func_180495_p(p_175108_2_.func_177977_b()).func_177230_c().func_149688_o();
         int i = 0;
         if(material == Material.field_151576_e) {
            i = 1;
         }

         if(material == Material.field_151595_p) {
            i = 2;
         }

         if(material == Material.field_151592_s) {
            i = 3;
         }

         if(material == Material.field_151575_d) {
            i = 4;
         }

         p_175108_1_.func_175641_c(p_175108_2_, Blocks.field_150323_B, i, this.field_145879_a);
      }
   }
}
