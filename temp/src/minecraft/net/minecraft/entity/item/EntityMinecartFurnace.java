package net.minecraft.entity.item;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMinecartFurnace extends EntityMinecart {
   private int field_94110_c;
   public double field_94111_a;
   public double field_94109_b;

   public EntityMinecartFurnace(World p_i1718_1_) {
      super(p_i1718_1_);
   }

   public EntityMinecartFurnace(World p_i1719_1_, double p_i1719_2_, double p_i1719_4_, double p_i1719_6_) {
      super(p_i1719_1_, p_i1719_2_, p_i1719_4_, p_i1719_6_);
   }

   public EntityMinecart.EnumMinecartType func_180456_s() {
      return EntityMinecart.EnumMinecartType.FURNACE;
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, new Byte((byte)0));
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_94110_c > 0) {
         --this.field_94110_c;
      }

      if(this.field_94110_c <= 0) {
         this.field_94111_a = this.field_94109_b = 0.0D;
      }

      this.func_94107_f(this.field_94110_c > 0);
      if(this.func_94108_c() && this.field_70146_Z.nextInt(4) == 0) {
         this.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_LARGE, this.field_70165_t, this.field_70163_u + 0.8D, this.field_70161_v, 0.0D, 0.0D, 0.0D, new int[0]);
      }

   }

   protected double func_174898_m() {
      return 0.2D;
   }

   public void func_94095_a(DamageSource p_94095_1_) {
      super.func_94095_a(p_94095_1_);
      if(!p_94095_1_.func_94541_c() && this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
         this.func_70099_a(new ItemStack(Blocks.field_150460_al, 1), 0.0F);
      }

   }

   protected void func_180460_a(BlockPos p_180460_1_, IBlockState p_180460_2_) {
      super.func_180460_a(p_180460_1_, p_180460_2_);
      double d0 = this.field_94111_a * this.field_94111_a + this.field_94109_b * this.field_94109_b;
      if(d0 > 1.0E-4D && this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 0.001D) {
         d0 = (double)MathHelper.func_76133_a(d0);
         this.field_94111_a /= d0;
         this.field_94109_b /= d0;
         if(this.field_94111_a * this.field_70159_w + this.field_94109_b * this.field_70179_y < 0.0D) {
            this.field_94111_a = 0.0D;
            this.field_94109_b = 0.0D;
         } else {
            double d1 = d0 / this.func_174898_m();
            this.field_94111_a *= d1;
            this.field_94109_b *= d1;
         }
      }

   }

   protected void func_94101_h() {
      double d0 = this.field_94111_a * this.field_94111_a + this.field_94109_b * this.field_94109_b;
      if(d0 > 1.0E-4D) {
         d0 = (double)MathHelper.func_76133_a(d0);
         this.field_94111_a /= d0;
         this.field_94109_b /= d0;
         double d1 = 1.0D;
         this.field_70159_w *= 0.800000011920929D;
         this.field_70181_x *= 0.0D;
         this.field_70179_y *= 0.800000011920929D;
         this.field_70159_w += this.field_94111_a * d1;
         this.field_70179_y += this.field_94109_b * d1;
      } else {
         this.field_70159_w *= 0.9800000190734863D;
         this.field_70181_x *= 0.0D;
         this.field_70179_y *= 0.9800000190734863D;
      }

      super.func_94101_h();
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      ItemStack itemstack = p_130002_1_.field_71071_by.func_70448_g();
      if(itemstack != null && itemstack.func_77973_b() == Items.field_151044_h) {
         if(!p_130002_1_.field_71075_bZ.field_75098_d && --itemstack.field_77994_a == 0) {
            p_130002_1_.field_71071_by.func_70299_a(p_130002_1_.field_71071_by.field_70461_c, (ItemStack)null);
         }

         this.field_94110_c += 3600;
      }

      this.field_94111_a = this.field_70165_t - p_130002_1_.field_70165_t;
      this.field_94109_b = this.field_70161_v - p_130002_1_.field_70161_v;
      return true;
   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74780_a("PushX", this.field_94111_a);
      p_70014_1_.func_74780_a("PushZ", this.field_94109_b);
      p_70014_1_.func_74777_a("Fuel", (short)this.field_94110_c);
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_94111_a = p_70037_1_.func_74769_h("PushX");
      this.field_94109_b = p_70037_1_.func_74769_h("PushZ");
      this.field_94110_c = p_70037_1_.func_74765_d("Fuel");
   }

   protected boolean func_94108_c() {
      return (this.field_70180_af.func_75683_a(16) & 1) != 0;
   }

   protected void func_94107_f(boolean p_94107_1_) {
      if(p_94107_1_) {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(this.field_70180_af.func_75683_a(16) | 1)));
      } else {
         this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)(this.field_70180_af.func_75683_a(16) & -2)));
      }

   }

   public IBlockState func_180457_u() {
      return (this.func_94108_c()?Blocks.field_150470_am:Blocks.field_150460_al).func_176223_P().func_177226_a(BlockFurnace.field_176447_a, EnumFacing.NORTH);
   }
}
