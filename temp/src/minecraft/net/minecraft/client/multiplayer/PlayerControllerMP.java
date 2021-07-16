package net.minecraft.client.multiplayer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class PlayerControllerMP {
   private final Minecraft field_78776_a;
   private final NetHandlerPlayClient field_78774_b;
   private BlockPos field_178895_c = new BlockPos(-1, -1, -1);
   private ItemStack field_85183_f;
   private float field_78770_f;
   private float field_78780_h;
   private int field_78781_i;
   private boolean field_78778_j;
   private WorldSettings.GameType field_78779_k = WorldSettings.GameType.SURVIVAL;
   private int field_78777_l;

   public PlayerControllerMP(Minecraft p_i45062_1_, NetHandlerPlayClient p_i45062_2_) {
      this.field_78776_a = p_i45062_1_;
      this.field_78774_b = p_i45062_2_;
   }

   public static void func_178891_a(Minecraft p_178891_0_, PlayerControllerMP p_178891_1_, BlockPos p_178891_2_, EnumFacing p_178891_3_) {
      if(!p_178891_0_.field_71441_e.func_175719_a(p_178891_0_.field_71439_g, p_178891_2_, p_178891_3_)) {
         p_178891_1_.func_178888_a(p_178891_2_, p_178891_3_);
      }

   }

   public void func_78748_a(EntityPlayer p_78748_1_) {
      this.field_78779_k.func_77147_a(p_78748_1_.field_71075_bZ);
   }

   public boolean func_78747_a() {
      return this.field_78779_k == WorldSettings.GameType.SPECTATOR;
   }

   public void func_78746_a(WorldSettings.GameType p_78746_1_) {
      this.field_78779_k = p_78746_1_;
      this.field_78779_k.func_77147_a(this.field_78776_a.field_71439_g.field_71075_bZ);
   }

   public void func_78745_b(EntityPlayer p_78745_1_) {
      p_78745_1_.field_70177_z = -180.0F;
   }

   public boolean func_78755_b() {
      return this.field_78779_k.func_77144_e();
   }

   public boolean func_178888_a(BlockPos p_178888_1_, EnumFacing p_178888_2_) {
      if(this.field_78779_k.func_82752_c()) {
         if(this.field_78779_k == WorldSettings.GameType.SPECTATOR) {
            return false;
         }

         if(!this.field_78776_a.field_71439_g.func_175142_cm()) {
            Block block = this.field_78776_a.field_71441_e.func_180495_p(p_178888_1_).func_177230_c();
            ItemStack itemstack = this.field_78776_a.field_71439_g.func_71045_bC();
            if(itemstack == null) {
               return false;
            }

            if(!itemstack.func_179544_c(block)) {
               return false;
            }
         }
      }

      if(this.field_78779_k.func_77145_d() && this.field_78776_a.field_71439_g.func_70694_bm() != null && this.field_78776_a.field_71439_g.func_70694_bm().func_77973_b() instanceof ItemSword) {
         return false;
      } else {
         World world = this.field_78776_a.field_71441_e;
         IBlockState iblockstate = world.func_180495_p(p_178888_1_);
         Block block1 = iblockstate.func_177230_c();
         if(block1.func_149688_o() == Material.field_151579_a) {
            return false;
         } else {
            world.func_175718_b(2001, p_178888_1_, Block.func_176210_f(iblockstate));
            boolean flag = world.func_175698_g(p_178888_1_);
            if(flag) {
               block1.func_176206_d(world, p_178888_1_, iblockstate);
            }

            this.field_178895_c = new BlockPos(this.field_178895_c.func_177958_n(), -1, this.field_178895_c.func_177952_p());
            if(!this.field_78779_k.func_77145_d()) {
               ItemStack itemstack1 = this.field_78776_a.field_71439_g.func_71045_bC();
               if(itemstack1 != null) {
                  itemstack1.func_179548_a(world, block1, p_178888_1_, this.field_78776_a.field_71439_g);
                  if(itemstack1.field_77994_a == 0) {
                     this.field_78776_a.field_71439_g.func_71028_bD();
                  }
               }
            }

            return flag;
         }
      }
   }

   public boolean func_180511_b(BlockPos p_180511_1_, EnumFacing p_180511_2_) {
      if(this.field_78779_k.func_82752_c()) {
         if(this.field_78779_k == WorldSettings.GameType.SPECTATOR) {
            return false;
         }

         if(!this.field_78776_a.field_71439_g.func_175142_cm()) {
            Block block = this.field_78776_a.field_71441_e.func_180495_p(p_180511_1_).func_177230_c();
            ItemStack itemstack = this.field_78776_a.field_71439_g.func_71045_bC();
            if(itemstack == null) {
               return false;
            }

            if(!itemstack.func_179544_c(block)) {
               return false;
            }
         }
      }

      if(!this.field_78776_a.field_71441_e.func_175723_af().func_177746_a(p_180511_1_)) {
         return false;
      } else {
         if(this.field_78779_k.func_77145_d()) {
            this.field_78774_b.func_147297_a(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180511_1_, p_180511_2_));
            func_178891_a(this.field_78776_a, this, p_180511_1_, p_180511_2_);
            this.field_78781_i = 5;
         } else if(!this.field_78778_j || !this.func_178893_a(p_180511_1_)) {
            if(this.field_78778_j) {
               this.field_78774_b.func_147297_a(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.field_178895_c, p_180511_2_));
            }

            this.field_78774_b.func_147297_a(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180511_1_, p_180511_2_));
            Block block1 = this.field_78776_a.field_71441_e.func_180495_p(p_180511_1_).func_177230_c();
            boolean flag = block1.func_149688_o() != Material.field_151579_a;
            if(flag && this.field_78770_f == 0.0F) {
               block1.func_180649_a(this.field_78776_a.field_71441_e, p_180511_1_, this.field_78776_a.field_71439_g);
            }

            if(flag && block1.func_180647_a(this.field_78776_a.field_71439_g, this.field_78776_a.field_71439_g.field_70170_p, p_180511_1_) >= 1.0F) {
               this.func_178888_a(p_180511_1_, p_180511_2_);
            } else {
               this.field_78778_j = true;
               this.field_178895_c = p_180511_1_;
               this.field_85183_f = this.field_78776_a.field_71439_g.func_70694_bm();
               this.field_78770_f = 0.0F;
               this.field_78780_h = 0.0F;
               this.field_78776_a.field_71441_e.func_175715_c(this.field_78776_a.field_71439_g.func_145782_y(), this.field_178895_c, (int)(this.field_78770_f * 10.0F) - 1);
            }
         }

         return true;
      }
   }

   public void func_78767_c() {
      if(this.field_78778_j) {
         this.field_78774_b.func_147297_a(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.field_178895_c, EnumFacing.DOWN));
         this.field_78778_j = false;
         this.field_78770_f = 0.0F;
         this.field_78776_a.field_71441_e.func_175715_c(this.field_78776_a.field_71439_g.func_145782_y(), this.field_178895_c, -1);
      }

   }

   public boolean func_180512_c(BlockPos p_180512_1_, EnumFacing p_180512_2_) {
      this.func_78750_j();
      if(this.field_78781_i > 0) {
         --this.field_78781_i;
         return true;
      } else if(this.field_78779_k.func_77145_d() && this.field_78776_a.field_71441_e.func_175723_af().func_177746_a(p_180512_1_)) {
         this.field_78781_i = 5;
         this.field_78774_b.func_147297_a(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180512_1_, p_180512_2_));
         func_178891_a(this.field_78776_a, this, p_180512_1_, p_180512_2_);
         return true;
      } else if(this.func_178893_a(p_180512_1_)) {
         Block block = this.field_78776_a.field_71441_e.func_180495_p(p_180512_1_).func_177230_c();
         if(block.func_149688_o() == Material.field_151579_a) {
            this.field_78778_j = false;
            return false;
         } else {
            this.field_78770_f += block.func_180647_a(this.field_78776_a.field_71439_g, this.field_78776_a.field_71439_g.field_70170_p, p_180512_1_);
            if(this.field_78780_h % 4.0F == 0.0F) {
               this.field_78776_a.func_147118_V().func_147682_a(new PositionedSoundRecord(new ResourceLocation(block.field_149762_H.func_150498_e()), (block.field_149762_H.func_150497_c() + 1.0F) / 8.0F, block.field_149762_H.func_150494_d() * 0.5F, (float)p_180512_1_.func_177958_n() + 0.5F, (float)p_180512_1_.func_177956_o() + 0.5F, (float)p_180512_1_.func_177952_p() + 0.5F));
            }

            ++this.field_78780_h;
            if(this.field_78770_f >= 1.0F) {
               this.field_78778_j = false;
               this.field_78774_b.func_147297_a(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, p_180512_1_, p_180512_2_));
               this.func_178888_a(p_180512_1_, p_180512_2_);
               this.field_78770_f = 0.0F;
               this.field_78780_h = 0.0F;
               this.field_78781_i = 5;
            }

            this.field_78776_a.field_71441_e.func_175715_c(this.field_78776_a.field_71439_g.func_145782_y(), this.field_178895_c, (int)(this.field_78770_f * 10.0F) - 1);
            return true;
         }
      } else {
         return this.func_180511_b(p_180512_1_, p_180512_2_);
      }
   }

   public float func_78757_d() {
      return this.field_78779_k.func_77145_d()?5.0F:4.5F;
   }

   public void func_78765_e() {
      this.func_78750_j();
      if(this.field_78774_b.func_147298_b().func_150724_d()) {
         this.field_78774_b.func_147298_b().func_74428_b();
      } else {
         this.field_78774_b.func_147298_b().func_179293_l();
      }

   }

   private boolean func_178893_a(BlockPos p_178893_1_) {
      ItemStack itemstack = this.field_78776_a.field_71439_g.func_70694_bm();
      boolean flag = this.field_85183_f == null && itemstack == null;
      if(this.field_85183_f != null && itemstack != null) {
         flag = itemstack.func_77973_b() == this.field_85183_f.func_77973_b() && ItemStack.func_77970_a(itemstack, this.field_85183_f) && (itemstack.func_77984_f() || itemstack.func_77960_j() == this.field_85183_f.func_77960_j());
      }

      return p_178893_1_.equals(this.field_178895_c) && flag;
   }

   private void func_78750_j() {
      int i = this.field_78776_a.field_71439_g.field_71071_by.field_70461_c;
      if(i != this.field_78777_l) {
         this.field_78777_l = i;
         this.field_78774_b.func_147297_a(new C09PacketHeldItemChange(this.field_78777_l));
      }

   }

   public boolean func_178890_a(EntityPlayerSP p_178890_1_, WorldClient p_178890_2_, ItemStack p_178890_3_, BlockPos p_178890_4_, EnumFacing p_178890_5_, Vec3 p_178890_6_) {
      this.func_78750_j();
      float f = (float)(p_178890_6_.field_72450_a - (double)p_178890_4_.func_177958_n());
      float f1 = (float)(p_178890_6_.field_72448_b - (double)p_178890_4_.func_177956_o());
      float f2 = (float)(p_178890_6_.field_72449_c - (double)p_178890_4_.func_177952_p());
      boolean flag = false;
      if(!this.field_78776_a.field_71441_e.func_175723_af().func_177746_a(p_178890_4_)) {
         return false;
      } else {
         if(this.field_78779_k != WorldSettings.GameType.SPECTATOR) {
            IBlockState iblockstate = p_178890_2_.func_180495_p(p_178890_4_);
            if((!p_178890_1_.func_70093_af() || p_178890_1_.func_70694_bm() == null) && iblockstate.func_177230_c().func_180639_a(p_178890_2_, p_178890_4_, iblockstate, p_178890_1_, p_178890_5_, f, f1, f2)) {
               flag = true;
            }

            if(!flag && p_178890_3_ != null && p_178890_3_.func_77973_b() instanceof ItemBlock) {
               ItemBlock itemblock = (ItemBlock)p_178890_3_.func_77973_b();
               if(!itemblock.func_179222_a(p_178890_2_, p_178890_4_, p_178890_5_, p_178890_1_, p_178890_3_)) {
                  return false;
               }
            }
         }

         this.field_78774_b.func_147297_a(new C08PacketPlayerBlockPlacement(p_178890_4_, p_178890_5_.func_176745_a(), p_178890_1_.field_71071_by.func_70448_g(), f, f1, f2));
         if(!flag && this.field_78779_k != WorldSettings.GameType.SPECTATOR) {
            if(p_178890_3_ == null) {
               return false;
            } else if(this.field_78779_k.func_77145_d()) {
               int i = p_178890_3_.func_77960_j();
               int j = p_178890_3_.field_77994_a;
               boolean flag1 = p_178890_3_.func_179546_a(p_178890_1_, p_178890_2_, p_178890_4_, p_178890_5_, f, f1, f2);
               p_178890_3_.func_77964_b(i);
               p_178890_3_.field_77994_a = j;
               return flag1;
            } else {
               return p_178890_3_.func_179546_a(p_178890_1_, p_178890_2_, p_178890_4_, p_178890_5_, f, f1, f2);
            }
         } else {
            return true;
         }
      }
   }

   public boolean func_78769_a(EntityPlayer p_78769_1_, World p_78769_2_, ItemStack p_78769_3_) {
      if(this.field_78779_k == WorldSettings.GameType.SPECTATOR) {
         return false;
      } else {
         this.func_78750_j();
         this.field_78774_b.func_147297_a(new C08PacketPlayerBlockPlacement(p_78769_1_.field_71071_by.func_70448_g()));
         int i = p_78769_3_.field_77994_a;
         ItemStack itemstack = p_78769_3_.func_77957_a(p_78769_2_, p_78769_1_);
         if(itemstack != p_78769_3_ || itemstack != null && itemstack.field_77994_a != i) {
            p_78769_1_.field_71071_by.field_70462_a[p_78769_1_.field_71071_by.field_70461_c] = itemstack;
            if(itemstack.field_77994_a == 0) {
               p_78769_1_.field_71071_by.field_70462_a[p_78769_1_.field_71071_by.field_70461_c] = null;
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public EntityPlayerSP func_178892_a(World p_178892_1_, StatFileWriter p_178892_2_) {
      return new EntityPlayerSP(this.field_78776_a, p_178892_1_, this.field_78774_b, p_178892_2_);
   }

   public void func_78764_a(EntityPlayer p_78764_1_, Entity p_78764_2_) {
      this.func_78750_j();
      this.field_78774_b.func_147297_a(new C02PacketUseEntity(p_78764_2_, C02PacketUseEntity.Action.ATTACK));
      if(this.field_78779_k != WorldSettings.GameType.SPECTATOR) {
         p_78764_1_.func_71059_n(p_78764_2_);
      }

   }

   public boolean func_78768_b(EntityPlayer p_78768_1_, Entity p_78768_2_) {
      this.func_78750_j();
      this.field_78774_b.func_147297_a(new C02PacketUseEntity(p_78768_2_, C02PacketUseEntity.Action.INTERACT));
      return this.field_78779_k != WorldSettings.GameType.SPECTATOR && p_78768_1_.func_70998_m(p_78768_2_);
   }

   public boolean func_178894_a(EntityPlayer p_178894_1_, Entity p_178894_2_, MovingObjectPosition p_178894_3_) {
      this.func_78750_j();
      Vec3 vec3 = new Vec3(p_178894_3_.field_72307_f.field_72450_a - p_178894_2_.field_70165_t, p_178894_3_.field_72307_f.field_72448_b - p_178894_2_.field_70163_u, p_178894_3_.field_72307_f.field_72449_c - p_178894_2_.field_70161_v);
      this.field_78774_b.func_147297_a(new C02PacketUseEntity(p_178894_2_, vec3));
      return this.field_78779_k != WorldSettings.GameType.SPECTATOR && p_178894_2_.func_174825_a(p_178894_1_, vec3);
   }

   public ItemStack func_78753_a(int p_78753_1_, int p_78753_2_, int p_78753_3_, int p_78753_4_, EntityPlayer p_78753_5_) {
      short short1 = p_78753_5_.field_71070_bA.func_75136_a(p_78753_5_.field_71071_by);
      ItemStack itemstack = p_78753_5_.field_71070_bA.func_75144_a(p_78753_2_, p_78753_3_, p_78753_4_, p_78753_5_);
      this.field_78774_b.func_147297_a(new C0EPacketClickWindow(p_78753_1_, p_78753_2_, p_78753_3_, p_78753_4_, itemstack, short1));
      return itemstack;
   }

   public void func_78756_a(int p_78756_1_, int p_78756_2_) {
      this.field_78774_b.func_147297_a(new C11PacketEnchantItem(p_78756_1_, p_78756_2_));
   }

   public void func_78761_a(ItemStack p_78761_1_, int p_78761_2_) {
      if(this.field_78779_k.func_77145_d()) {
         this.field_78774_b.func_147297_a(new C10PacketCreativeInventoryAction(p_78761_2_, p_78761_1_));
      }

   }

   public void func_78752_a(ItemStack p_78752_1_) {
      if(this.field_78779_k.func_77145_d() && p_78752_1_ != null) {
         this.field_78774_b.func_147297_a(new C10PacketCreativeInventoryAction(-1, p_78752_1_));
      }

   }

   public void func_78766_c(EntityPlayer p_78766_1_) {
      this.func_78750_j();
      this.field_78774_b.func_147297_a(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, EnumFacing.DOWN));
      p_78766_1_.func_71034_by();
   }

   public boolean func_78763_f() {
      return this.field_78779_k.func_77144_e();
   }

   public boolean func_78762_g() {
      return !this.field_78779_k.func_77145_d();
   }

   public boolean func_78758_h() {
      return this.field_78779_k.func_77145_d();
   }

   public boolean func_78749_i() {
      return this.field_78779_k.func_77145_d();
   }

   public boolean func_110738_j() {
      return this.field_78776_a.field_71439_g.func_70115_ae() && this.field_78776_a.field_71439_g.field_70154_o instanceof EntityHorse;
   }

   public boolean func_178887_k() {
      return this.field_78779_k == WorldSettings.GameType.SPECTATOR;
   }

   public WorldSettings.GameType func_178889_l() {
      return this.field_78779_k;
   }

   public boolean func_181040_m() {
      return this.field_78778_j;
   }
}
