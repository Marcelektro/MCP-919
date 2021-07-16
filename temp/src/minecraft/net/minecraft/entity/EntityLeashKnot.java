package net.minecraft.entity;

import net.minecraft.block.BlockFence;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityLeashKnot extends EntityHanging {
   public EntityLeashKnot(World p_i1592_1_) {
      super(p_i1592_1_);
   }

   public EntityLeashKnot(World p_i45851_1_, BlockPos p_i45851_2_) {
      super(p_i45851_1_, p_i45851_2_);
      this.func_70107_b((double)p_i45851_2_.func_177958_n() + 0.5D, (double)p_i45851_2_.func_177956_o() + 0.5D, (double)p_i45851_2_.func_177952_p() + 0.5D);
      float f = 0.125F;
      float f1 = 0.1875F;
      float f2 = 0.25F;
      this.func_174826_a(new AxisAlignedBB(this.field_70165_t - 0.1875D, this.field_70163_u - 0.25D + 0.125D, this.field_70161_v - 0.1875D, this.field_70165_t + 0.1875D, this.field_70163_u + 0.25D + 0.125D, this.field_70161_v + 0.1875D));
   }

   protected void func_70088_a() {
      super.func_70088_a();
   }

   public void func_174859_a(EnumFacing p_174859_1_) {
   }

   public int func_82329_d() {
      return 9;
   }

   public int func_82330_g() {
      return 9;
   }

   public float func_70047_e() {
      return -0.0625F;
   }

   public boolean func_70112_a(double p_70112_1_) {
      return p_70112_1_ < 1024.0D;
   }

   public void func_110128_b(Entity p_110128_1_) {
   }

   public boolean func_70039_c(NBTTagCompound p_70039_1_) {
      return false;
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      ItemStack itemstack = p_130002_1_.func_70694_bm();
      boolean flag = false;
      if(itemstack != null && itemstack.func_77973_b() == Items.field_151058_ca && !this.field_70170_p.field_72995_K) {
         double d0 = 7.0D;

         for(EntityLiving entityliving : this.field_70170_p.func_72872_a(EntityLiving.class, new AxisAlignedBB(this.field_70165_t - d0, this.field_70163_u - d0, this.field_70161_v - d0, this.field_70165_t + d0, this.field_70163_u + d0, this.field_70161_v + d0))) {
            if(entityliving.func_110167_bD() && entityliving.func_110166_bE() == p_130002_1_) {
               entityliving.func_110162_b(this, true);
               flag = true;
            }
         }
      }

      if(!this.field_70170_p.field_72995_K && !flag) {
         this.func_70106_y();
         if(p_130002_1_.field_71075_bZ.field_75098_d) {
            double d1 = 7.0D;

            for(EntityLiving entityliving1 : this.field_70170_p.func_72872_a(EntityLiving.class, new AxisAlignedBB(this.field_70165_t - d1, this.field_70163_u - d1, this.field_70161_v - d1, this.field_70165_t + d1, this.field_70163_u + d1, this.field_70161_v + d1))) {
               if(entityliving1.func_110167_bD() && entityliving1.func_110166_bE() == this) {
                  entityliving1.func_110160_i(true, false);
               }
            }
         }
      }

      return true;
   }

   public boolean func_70518_d() {
      return this.field_70170_p.func_180495_p(this.field_174861_a).func_177230_c() instanceof BlockFence;
   }

   public static EntityLeashKnot func_174862_a(World p_174862_0_, BlockPos p_174862_1_) {
      EntityLeashKnot entityleashknot = new EntityLeashKnot(p_174862_0_, p_174862_1_);
      entityleashknot.field_98038_p = true;
      p_174862_0_.func_72838_d(entityleashknot);
      return entityleashknot;
   }

   public static EntityLeashKnot func_174863_b(World p_174863_0_, BlockPos p_174863_1_) {
      int i = p_174863_1_.func_177958_n();
      int j = p_174863_1_.func_177956_o();
      int k = p_174863_1_.func_177952_p();

      for(EntityLeashKnot entityleashknot : p_174863_0_.func_72872_a(EntityLeashKnot.class, new AxisAlignedBB((double)i - 1.0D, (double)j - 1.0D, (double)k - 1.0D, (double)i + 1.0D, (double)j + 1.0D, (double)k + 1.0D))) {
         if(entityleashknot.func_174857_n().equals(p_174863_1_)) {
            return entityleashknot;
         }
      }

      return null;
   }
}
