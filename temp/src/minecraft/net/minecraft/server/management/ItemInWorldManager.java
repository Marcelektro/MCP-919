package net.minecraft.server.management;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;

public class ItemInWorldManager {
   public World field_73092_a;
   public EntityPlayerMP field_73090_b;
   private WorldSettings.GameType field_73091_c = WorldSettings.GameType.NOT_SET;
   private boolean field_73088_d;
   private int field_73089_e;
   private BlockPos field_180240_f = BlockPos.field_177992_a;
   private int field_73100_i;
   private boolean field_73097_j;
   private BlockPos field_180241_i = BlockPos.field_177992_a;
   private int field_73093_n;
   private int field_73094_o = -1;

   public ItemInWorldManager(World p_i1524_1_) {
      this.field_73092_a = p_i1524_1_;
   }

   public void func_73076_a(WorldSettings.GameType p_73076_1_) {
      this.field_73091_c = p_73076_1_;
      p_73076_1_.func_77147_a(this.field_73090_b.field_71075_bZ);
      this.field_73090_b.func_71016_p();
      this.field_73090_b.field_71133_b.func_71203_ab().func_148540_a(new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.UPDATE_GAME_MODE, new EntityPlayerMP[]{this.field_73090_b}));
   }

   public WorldSettings.GameType func_73081_b() {
      return this.field_73091_c;
   }

   public boolean func_180239_c() {
      return this.field_73091_c.func_77144_e();
   }

   public boolean func_73083_d() {
      return this.field_73091_c.func_77145_d();
   }

   public void func_73077_b(WorldSettings.GameType p_73077_1_) {
      if(this.field_73091_c == WorldSettings.GameType.NOT_SET) {
         this.field_73091_c = p_73077_1_;
      }

      this.func_73076_a(this.field_73091_c);
   }

   public void func_73075_a() {
      ++this.field_73100_i;
      if(this.field_73097_j) {
         int i = this.field_73100_i - this.field_73093_n;
         Block block = this.field_73092_a.func_180495_p(this.field_180241_i).func_177230_c();
         if(block.func_149688_o() == Material.field_151579_a) {
            this.field_73097_j = false;
         } else {
            float f = block.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, this.field_180241_i) * (float)(i + 1);
            int j = (int)(f * 10.0F);
            if(j != this.field_73094_o) {
               this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180241_i, j);
               this.field_73094_o = j;
            }

            if(f >= 1.0F) {
               this.field_73097_j = false;
               this.func_180237_b(this.field_180241_i);
            }
         }
      } else if(this.field_73088_d) {
         Block block1 = this.field_73092_a.func_180495_p(this.field_180240_f).func_177230_c();
         if(block1.func_149688_o() == Material.field_151579_a) {
            this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180240_f, -1);
            this.field_73094_o = -1;
            this.field_73088_d = false;
         } else {
            int k = this.field_73100_i - this.field_73089_e;
            float f1 = block1.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, this.field_180241_i) * (float)(k + 1);
            int l = (int)(f1 * 10.0F);
            if(l != this.field_73094_o) {
               this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180240_f, l);
               this.field_73094_o = l;
            }
         }
      }

   }

   public void func_180784_a(BlockPos p_180784_1_, EnumFacing p_180784_2_) {
      if(this.func_73083_d()) {
         if(!this.field_73092_a.func_175719_a((EntityPlayer)null, p_180784_1_, p_180784_2_)) {
            this.func_180237_b(p_180784_1_);
         }

      } else {
         Block block = this.field_73092_a.func_180495_p(p_180784_1_).func_177230_c();
         if(this.field_73091_c.func_82752_c()) {
            if(this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
               return;
            }

            if(!this.field_73090_b.func_175142_cm()) {
               ItemStack itemstack = this.field_73090_b.func_71045_bC();
               if(itemstack == null) {
                  return;
               }

               if(!itemstack.func_179544_c(block)) {
                  return;
               }
            }
         }

         this.field_73092_a.func_175719_a((EntityPlayer)null, p_180784_1_, p_180784_2_);
         this.field_73089_e = this.field_73100_i;
         float f = 1.0F;
         if(block.func_149688_o() != Material.field_151579_a) {
            block.func_180649_a(this.field_73092_a, p_180784_1_, this.field_73090_b);
            f = block.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, p_180784_1_);
         }

         if(block.func_149688_o() != Material.field_151579_a && f >= 1.0F) {
            this.func_180237_b(p_180784_1_);
         } else {
            this.field_73088_d = true;
            this.field_180240_f = p_180784_1_;
            int i = (int)(f * 10.0F);
            this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), p_180784_1_, i);
            this.field_73094_o = i;
         }

      }
   }

   public void func_180785_a(BlockPos p_180785_1_) {
      if(p_180785_1_.equals(this.field_180240_f)) {
         int i = this.field_73100_i - this.field_73089_e;
         Block block = this.field_73092_a.func_180495_p(p_180785_1_).func_177230_c();
         if(block.func_149688_o() != Material.field_151579_a) {
            float f = block.func_180647_a(this.field_73090_b, this.field_73090_b.field_70170_p, p_180785_1_) * (float)(i + 1);
            if(f >= 0.7F) {
               this.field_73088_d = false;
               this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), p_180785_1_, -1);
               this.func_180237_b(p_180785_1_);
            } else if(!this.field_73097_j) {
               this.field_73088_d = false;
               this.field_73097_j = true;
               this.field_180241_i = p_180785_1_;
               this.field_73093_n = this.field_73089_e;
            }
         }
      }

   }

   public void func_180238_e() {
      this.field_73088_d = false;
      this.field_73092_a.func_175715_c(this.field_73090_b.func_145782_y(), this.field_180240_f, -1);
   }

   private boolean func_180235_c(BlockPos p_180235_1_) {
      IBlockState iblockstate = this.field_73092_a.func_180495_p(p_180235_1_);
      iblockstate.func_177230_c().func_176208_a(this.field_73092_a, p_180235_1_, iblockstate, this.field_73090_b);
      boolean flag = this.field_73092_a.func_175698_g(p_180235_1_);
      if(flag) {
         iblockstate.func_177230_c().func_176206_d(this.field_73092_a, p_180235_1_, iblockstate);
      }

      return flag;
   }

   public boolean func_180237_b(BlockPos p_180237_1_) {
      if(this.field_73091_c.func_77145_d() && this.field_73090_b.func_70694_bm() != null && this.field_73090_b.func_70694_bm().func_77973_b() instanceof ItemSword) {
         return false;
      } else {
         IBlockState iblockstate = this.field_73092_a.func_180495_p(p_180237_1_);
         TileEntity tileentity = this.field_73092_a.func_175625_s(p_180237_1_);
         if(this.field_73091_c.func_82752_c()) {
            if(this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
               return false;
            }

            if(!this.field_73090_b.func_175142_cm()) {
               ItemStack itemstack = this.field_73090_b.func_71045_bC();
               if(itemstack == null) {
                  return false;
               }

               if(!itemstack.func_179544_c(iblockstate.func_177230_c())) {
                  return false;
               }
            }
         }

         this.field_73092_a.func_180498_a(this.field_73090_b, 2001, p_180237_1_, Block.func_176210_f(iblockstate));
         boolean flag1 = this.func_180235_c(p_180237_1_);
         if(this.func_73083_d()) {
            this.field_73090_b.field_71135_a.func_147359_a(new S23PacketBlockChange(this.field_73092_a, p_180237_1_));
         } else {
            ItemStack itemstack1 = this.field_73090_b.func_71045_bC();
            boolean flag = this.field_73090_b.func_146099_a(iblockstate.func_177230_c());
            if(itemstack1 != null) {
               itemstack1.func_179548_a(this.field_73092_a, iblockstate.func_177230_c(), p_180237_1_, this.field_73090_b);
               if(itemstack1.field_77994_a == 0) {
                  this.field_73090_b.func_71028_bD();
               }
            }

            if(flag1 && flag) {
               iblockstate.func_177230_c().func_180657_a(this.field_73092_a, this.field_73090_b, p_180237_1_, iblockstate, tileentity);
            }
         }

         return flag1;
      }
   }

   public boolean func_73085_a(EntityPlayer p_73085_1_, World p_73085_2_, ItemStack p_73085_3_) {
      if(this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
         return false;
      } else {
         int i = p_73085_3_.field_77994_a;
         int j = p_73085_3_.func_77960_j();
         ItemStack itemstack = p_73085_3_.func_77957_a(p_73085_2_, p_73085_1_);
         if(itemstack != p_73085_3_ || itemstack != null && (itemstack.field_77994_a != i || itemstack.func_77988_m() > 0 || itemstack.func_77960_j() != j)) {
            p_73085_1_.field_71071_by.field_70462_a[p_73085_1_.field_71071_by.field_70461_c] = itemstack;
            if(this.func_73083_d()) {
               itemstack.field_77994_a = i;
               if(itemstack.func_77984_f()) {
                  itemstack.func_77964_b(j);
               }
            }

            if(itemstack.field_77994_a == 0) {
               p_73085_1_.field_71071_by.field_70462_a[p_73085_1_.field_71071_by.field_70461_c] = null;
            }

            if(!p_73085_1_.func_71039_bw()) {
               ((EntityPlayerMP)p_73085_1_).func_71120_a(p_73085_1_.field_71069_bz);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean func_180236_a(EntityPlayer p_180236_1_, World p_180236_2_, ItemStack p_180236_3_, BlockPos p_180236_4_, EnumFacing p_180236_5_, float p_180236_6_, float p_180236_7_, float p_180236_8_) {
      if(this.field_73091_c == WorldSettings.GameType.SPECTATOR) {
         TileEntity tileentity = p_180236_2_.func_175625_s(p_180236_4_);
         if(tileentity instanceof ILockableContainer) {
            Block block = p_180236_2_.func_180495_p(p_180236_4_).func_177230_c();
            ILockableContainer ilockablecontainer = (ILockableContainer)tileentity;
            if(ilockablecontainer instanceof TileEntityChest && block instanceof BlockChest) {
               ilockablecontainer = ((BlockChest)block).func_180676_d(p_180236_2_, p_180236_4_);
            }

            if(ilockablecontainer != null) {
               p_180236_1_.func_71007_a(ilockablecontainer);
               return true;
            }
         } else if(tileentity instanceof IInventory) {
            p_180236_1_.func_71007_a((IInventory)tileentity);
            return true;
         }

         return false;
      } else {
         if(!p_180236_1_.func_70093_af() || p_180236_1_.func_70694_bm() == null) {
            IBlockState iblockstate = p_180236_2_.func_180495_p(p_180236_4_);
            if(iblockstate.func_177230_c().func_180639_a(p_180236_2_, p_180236_4_, iblockstate, p_180236_1_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_)) {
               return true;
            }
         }

         if(p_180236_3_ == null) {
            return false;
         } else if(this.func_73083_d()) {
            int j = p_180236_3_.func_77960_j();
            int i = p_180236_3_.field_77994_a;
            boolean flag = p_180236_3_.func_179546_a(p_180236_1_, p_180236_2_, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
            p_180236_3_.func_77964_b(j);
            p_180236_3_.field_77994_a = i;
            return flag;
         } else {
            return p_180236_3_.func_179546_a(p_180236_1_, p_180236_2_, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
         }
      }
   }

   public void func_73080_a(WorldServer p_73080_1_) {
      this.field_73092_a = p_73080_1_;
   }
}
