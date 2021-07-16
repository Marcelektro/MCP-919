package net.minecraft.entity.item;

import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

public class EntityMinecartHopper extends EntityMinecartContainer implements IHopper {
   private boolean field_96113_a = true;
   private int field_98044_b = -1;
   private BlockPos field_174900_c = BlockPos.field_177992_a;

   public EntityMinecartHopper(World p_i1720_1_) {
      super(p_i1720_1_);
   }

   public EntityMinecartHopper(World p_i1721_1_, double p_i1721_2_, double p_i1721_4_, double p_i1721_6_) {
      super(p_i1721_1_, p_i1721_2_, p_i1721_4_, p_i1721_6_);
   }

   public EntityMinecart.EnumMinecartType func_180456_s() {
      return EntityMinecart.EnumMinecartType.HOPPER;
   }

   public IBlockState func_180457_u() {
      return Blocks.field_150438_bZ.func_176223_P();
   }

   public int func_94085_r() {
      return 1;
   }

   public int func_70302_i_() {
      return 5;
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      if(!this.field_70170_p.field_72995_K) {
         p_130002_1_.func_71007_a(this);
      }

      return true;
   }

   public void func_96095_a(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
      boolean flag = !p_96095_4_;
      if(flag != this.func_96111_ay()) {
         this.func_96110_f(flag);
      }

   }

   public boolean func_96111_ay() {
      return this.field_96113_a;
   }

   public void func_96110_f(boolean p_96110_1_) {
      this.field_96113_a = p_96110_1_;
   }

   public World func_145831_w() {
      return this.field_70170_p;
   }

   public double func_96107_aA() {
      return this.field_70165_t;
   }

   public double func_96109_aB() {
      return this.field_70163_u + 0.5D;
   }

   public double func_96108_aC() {
      return this.field_70161_v;
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(!this.field_70170_p.field_72995_K && this.func_70089_S() && this.func_96111_ay()) {
         BlockPos blockpos = new BlockPos(this);
         if(blockpos.equals(this.field_174900_c)) {
            --this.field_98044_b;
         } else {
            this.func_98042_n(0);
         }

         if(!this.func_98043_aE()) {
            this.func_98042_n(0);
            if(this.func_96112_aD()) {
               this.func_98042_n(4);
               this.func_70296_d();
            }
         }
      }

   }

   public boolean func_96112_aD() {
      if(TileEntityHopper.func_145891_a(this)) {
         return true;
      } else {
         List<EntityItem> list = this.field_70170_p.<EntityItem>func_175647_a(EntityItem.class, this.func_174813_aQ().func_72314_b(0.25D, 0.0D, 0.25D), EntitySelectors.field_94557_a);
         if(list.size() > 0) {
            TileEntityHopper.func_145898_a(this, (EntityItem)list.get(0));
         }

         return false;
      }
   }

   public void func_94095_a(DamageSource p_94095_1_) {
      super.func_94095_a(p_94095_1_);
      if(this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
         this.func_145778_a(Item.func_150898_a(Blocks.field_150438_bZ), 1, 0.0F);
      }

   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74768_a("TransferCooldown", this.field_98044_b);
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_98044_b = p_70037_1_.func_74762_e("TransferCooldown");
   }

   public void func_98042_n(int p_98042_1_) {
      this.field_98044_b = p_98042_1_;
   }

   public boolean func_98043_aE() {
      return this.field_98044_b > 0;
   }

   public String func_174875_k() {
      return "minecraft:hopper";
   }

   public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
      return new ContainerHopper(p_174876_1_, this, p_174876_2_);
   }
}
