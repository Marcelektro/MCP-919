package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandSetBlock extends CommandBase {
   public String func_71517_b() {
      return "setblock";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.setblock.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 4) {
         throw new WrongUsageException("commands.setblock.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         Block block = CommandBase.func_147180_g(p_71515_1_, p_71515_2_[3]);
         int i = 0;
         if(p_71515_2_.length >= 5) {
            i = func_175764_a(p_71515_2_[4], 0, 15);
         }

         World world = p_71515_1_.func_130014_f_();
         if(!world.func_175667_e(blockpos)) {
            throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
         } else {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            boolean flag = false;
            if(p_71515_2_.length >= 7 && block.func_149716_u()) {
               String s = func_147178_a(p_71515_1_, p_71515_2_, 6).func_150260_c();

               try {
                  nbttagcompound = JsonToNBT.func_180713_a(s);
                  flag = true;
               } catch (NBTException nbtexception) {
                  throw new CommandException("commands.setblock.tagError", new Object[]{nbtexception.getMessage()});
               }
            }

            if(p_71515_2_.length >= 6) {
               if(p_71515_2_[5].equals("destroy")) {
                  world.func_175655_b(blockpos, true);
                  if(block == Blocks.field_150350_a) {
                     func_152373_a(p_71515_1_, this, "commands.setblock.success", new Object[0]);
                     return;
                  }
               } else if(p_71515_2_[5].equals("keep") && !world.func_175623_d(blockpos)) {
                  throw new CommandException("commands.setblock.noChange", new Object[0]);
               }
            }

            TileEntity tileentity1 = world.func_175625_s(blockpos);
            if(tileentity1 != null) {
               if(tileentity1 instanceof IInventory) {
                  ((IInventory)tileentity1).func_174888_l();
               }

               world.func_180501_a(blockpos, Blocks.field_150350_a.func_176223_P(), block == Blocks.field_150350_a?2:4);
            }

            IBlockState iblockstate = block.func_176203_a(i);
            if(!world.func_180501_a(blockpos, iblockstate, 2)) {
               throw new CommandException("commands.setblock.noChange", new Object[0]);
            } else {
               if(flag) {
                  TileEntity tileentity = world.func_175625_s(blockpos);
                  if(tileentity != null) {
                     nbttagcompound.func_74768_a("x", blockpos.func_177958_n());
                     nbttagcompound.func_74768_a("y", blockpos.func_177956_o());
                     nbttagcompound.func_74768_a("z", blockpos.func_177952_p());
                     tileentity.func_145839_a(nbttagcompound);
                  }
               }

               world.func_175722_b(blockpos, iblockstate.func_177230_c());
               p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
               func_152373_a(p_71515_1_, this, "commands.setblock.success", new Object[0]);
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length > 0 && p_180525_2_.length <= 3?func_175771_a(p_180525_2_, 0, p_180525_3_):(p_180525_2_.length == 4?func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b()):(p_180525_2_.length == 6?func_71530_a(p_180525_2_, new String[]{"replace", "destroy", "keep"}):null));
   }
}
