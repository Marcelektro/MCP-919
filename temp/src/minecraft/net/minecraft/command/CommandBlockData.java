package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandBlockData extends CommandBase {
   public String func_71517_b() {
      return "blockdata";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.blockdata.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 4) {
         throw new WrongUsageException("commands.blockdata.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         World world = p_71515_1_.func_130014_f_();
         if(!world.func_175667_e(blockpos)) {
            throw new CommandException("commands.blockdata.outOfWorld", new Object[0]);
         } else {
            TileEntity tileentity = world.func_175625_s(blockpos);
            if(tileentity == null) {
               throw new CommandException("commands.blockdata.notValid", new Object[0]);
            } else {
               NBTTagCompound nbttagcompound = new NBTTagCompound();
               tileentity.func_145841_b(nbttagcompound);
               NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttagcompound.func_74737_b();

               NBTTagCompound nbttagcompound2;
               try {
                  nbttagcompound2 = JsonToNBT.func_180713_a(func_147178_a(p_71515_1_, p_71515_2_, 3).func_150260_c());
               } catch (NBTException nbtexception) {
                  throw new CommandException("commands.blockdata.tagError", new Object[]{nbtexception.getMessage()});
               }

               nbttagcompound.func_179237_a(nbttagcompound2);
               nbttagcompound.func_74768_a("x", blockpos.func_177958_n());
               nbttagcompound.func_74768_a("y", blockpos.func_177956_o());
               nbttagcompound.func_74768_a("z", blockpos.func_177952_p());
               if(nbttagcompound.equals(nbttagcompound1)) {
                  throw new CommandException("commands.blockdata.failed", new Object[]{nbttagcompound.toString()});
               } else {
                  tileentity.func_145839_a(nbttagcompound);
                  tileentity.func_70296_d();
                  world.func_175689_h(blockpos);
                  p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
                  func_152373_a(p_71515_1_, this, "commands.blockdata.success", new Object[]{nbttagcompound.toString()});
               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length > 0 && p_180525_2_.length <= 3?func_175771_a(p_180525_2_, 0, p_180525_3_):null;
   }
}
