package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class EntityItemFrame extends EntityHanging {
   private float field_82337_e = 1.0F;

   public EntityItemFrame(World p_i1590_1_) {
      super(p_i1590_1_);
   }

   public EntityItemFrame(World p_i45852_1_, BlockPos p_i45852_2_, EnumFacing p_i45852_3_) {
      super(p_i45852_1_, p_i45852_2_);
      this.func_174859_a(p_i45852_3_);
   }

   protected void func_70088_a() {
      this.func_70096_w().func_82709_a(8, 5);
      this.func_70096_w().func_75682_a(9, Byte.valueOf((byte)0));
   }

   public float func_70111_Y() {
      return 0.0F;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_180431_b(p_70097_1_)) {
         return false;
      } else if(!p_70097_1_.func_94541_c() && this.func_82335_i() != null) {
         if(!this.field_70170_p.field_72995_K) {
            this.func_146065_b(p_70097_1_.func_76346_g(), false);
            this.func_82334_a((ItemStack)null);
         }

         return true;
      } else {
         return super.func_70097_a(p_70097_1_, p_70097_2_);
      }
   }

   public int func_82329_d() {
      return 12;
   }

   public int func_82330_g() {
      return 12;
   }

   public boolean func_70112_a(double p_70112_1_) {
      double d0 = 16.0D;
      d0 = d0 * 64.0D * this.field_70155_l;
      return p_70112_1_ < d0 * d0;
   }

   public void func_110128_b(Entity p_110128_1_) {
      this.func_146065_b(p_110128_1_, true);
   }

   public void func_146065_b(Entity p_146065_1_, boolean p_146065_2_) {
      if(this.field_70170_p.func_82736_K().func_82766_b("doEntityDrops")) {
         ItemStack itemstack = this.func_82335_i();
         if(p_146065_1_ instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)p_146065_1_;
            if(entityplayer.field_71075_bZ.field_75098_d) {
               this.func_110131_b(itemstack);
               return;
            }
         }

         if(p_146065_2_) {
            this.func_70099_a(new ItemStack(Items.field_151160_bD), 0.0F);
         }

         if(itemstack != null && this.field_70146_Z.nextFloat() < this.field_82337_e) {
            itemstack = itemstack.func_77946_l();
            this.func_110131_b(itemstack);
            this.func_70099_a(itemstack, 0.0F);
         }

      }
   }

   private void func_110131_b(ItemStack p_110131_1_) {
      if(p_110131_1_ != null) {
         if(p_110131_1_.func_77973_b() == Items.field_151098_aY) {
            MapData mapdata = ((ItemMap)p_110131_1_.func_77973_b()).func_77873_a(p_110131_1_, this.field_70170_p);
            mapdata.field_76203_h.remove("frame-" + this.func_145782_y());
         }

         p_110131_1_.func_82842_a((EntityItemFrame)null);
      }
   }

   public ItemStack func_82335_i() {
      return this.func_70096_w().func_82710_f(8);
   }

   public void func_82334_a(ItemStack p_82334_1_) {
      this.func_174864_a(p_82334_1_, true);
   }

   private void func_174864_a(ItemStack p_174864_1_, boolean p_174864_2_) {
      if(p_174864_1_ != null) {
         p_174864_1_ = p_174864_1_.func_77946_l();
         p_174864_1_.field_77994_a = 1;
         p_174864_1_.func_82842_a(this);
      }

      this.func_70096_w().func_75692_b(8, p_174864_1_);
      this.func_70096_w().func_82708_h(8);
      if(p_174864_2_ && this.field_174861_a != null) {
         this.field_70170_p.func_175666_e(this.field_174861_a, Blocks.field_150350_a);
      }

   }

   public int func_82333_j() {
      return this.func_70096_w().func_75683_a(9);
   }

   public void func_82336_g(int p_82336_1_) {
      this.func_174865_a(p_82336_1_, true);
   }

   private void func_174865_a(int p_174865_1_, boolean p_174865_2_) {
      this.func_70096_w().func_75692_b(9, Byte.valueOf((byte)(p_174865_1_ % 8)));
      if(p_174865_2_ && this.field_174861_a != null) {
         this.field_70170_p.func_175666_e(this.field_174861_a, Blocks.field_150350_a);
      }

   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      if(this.func_82335_i() != null) {
         p_70014_1_.func_74782_a("Item", this.func_82335_i().func_77955_b(new NBTTagCompound()));
         p_70014_1_.func_74774_a("ItemRotation", (byte)this.func_82333_j());
         p_70014_1_.func_74776_a("ItemDropChance", this.field_82337_e);
      }

      super.func_70014_b(p_70014_1_);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      NBTTagCompound nbttagcompound = p_70037_1_.func_74775_l("Item");
      if(nbttagcompound != null && !nbttagcompound.func_82582_d()) {
         this.func_174864_a(ItemStack.func_77949_a(nbttagcompound), false);
         this.func_174865_a(p_70037_1_.func_74771_c("ItemRotation"), false);
         if(p_70037_1_.func_150297_b("ItemDropChance", 99)) {
            this.field_82337_e = p_70037_1_.func_74760_g("ItemDropChance");
         }

         if(p_70037_1_.func_74764_b("Direction")) {
            this.func_174865_a(this.func_82333_j() * 2, false);
         }
      }

      super.func_70037_a(p_70037_1_);
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      if(this.func_82335_i() == null) {
         ItemStack itemstack = p_130002_1_.func_70694_bm();
         if(itemstack != null && !this.field_70170_p.field_72995_K) {
            this.func_82334_a(itemstack);
            if(!p_130002_1_.field_71075_bZ.field_75098_d && --itemstack.field_77994_a <= 0) {
               p_130002_1_.field_71071_by.func_70299_a(p_130002_1_.field_71071_by.field_70461_c, (ItemStack)null);
            }
         }
      } else if(!this.field_70170_p.field_72995_K) {
         this.func_82336_g(this.func_82333_j() + 1);
      }

      return true;
   }

   public int func_174866_q() {
      return this.func_82335_i() == null?0:this.func_82333_j() % 8 + 1;
   }
}
