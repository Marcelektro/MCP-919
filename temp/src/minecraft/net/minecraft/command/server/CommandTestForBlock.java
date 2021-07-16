package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandTestForBlock extends CommandBase {
   public String func_71517_b() {
      return "testforblock";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.testforblock.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 4) {
         throw new WrongUsageException("commands.testforblock.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         Block block = Block.func_149684_b(p_71515_2_[3]);
         if(block == null) {
            throw new NumberInvalidException("commands.setblock.notFound", new Object[]{p_71515_2_[3]});
         } else {
            int i = -1;
            if(p_71515_2_.length >= 5) {
               i = func_175764_a(p_71515_2_[4], -1, 15);
            }

            World world = p_71515_1_.func_130014_f_();
            if(!world.func_175667_e(blockpos)) {
               throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
            } else {
               NBTTagCompound nbttagcompound = new NBTTagCompound();
               boolean flag = false;
               if(p_71515_2_.length >= 6 && block.func_149716_u()) {
                  String s = func_147178_a(p_71515_1_, p_71515_2_, 5).func_150260_c();

                  try {
                     nbttagcompound = JsonToNBT.func_180713_a(s);
                     flag = true;
                  } catch (NBTException nbtexception) {
                     throw new CommandException("commands.setblock.tagError", new Object[]{nbtexception.getMessage()});
                  }
               }

               IBlockState iblockstate = world.func_180495_p(blockpos);
               Block block1 = iblockstate.func_177230_c();
               if(block1 != block) {
                  throw new CommandException("commands.testforblock.failed.tile", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p()), block1.func_149732_F(), block.func_149732_F()});
               } else {
                  if(i > -1) {
                     int j = iblockstate.func_177230_c().func_176201_c(iblockstate);
                     if(j != i) {
                        throw new CommandException("commands.testforblock.failed.data", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p()), Integer.valueOf(j), Integer.valueOf(i)});
                     }
                  }

                  if(flag) {
                     TileEntity tileentity = world.func_175625_s(blockpos);
                     if(tileentity == null) {
                        throw new CommandException("commands.testforblock.failed.tileEntity", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p())});
                     }

                     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                     tileentity.func_145841_b(nbttagcompound1);
                     if(!NBTUtil.func_181123_a(nbttagcompound, nbttagcompound1, true)) {
                        throw new CommandException("commands.testforblock.failed.nbt", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p())});
                     }
                  }

                  p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
                  func_152373_a(p_71515_1_, this, "commands.testforblock.success", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p())});
               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length > 0 && p_180525_2_.length <= 3?func_175771_a(p_180525_2_, 0, p_180525_3_):(p_180525_2_.length == 4?func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b()):null);
   }
}
