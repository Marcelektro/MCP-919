package net.minecraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

public class CommandEntityData extends CommandBase {
   public String func_71517_b() {
      return "entitydata";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.entitydata.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.entitydata.usage", new Object[0]);
      } else {
         Entity entity = func_175768_b(p_71515_1_, p_71515_2_[0]);
         if(entity instanceof EntityPlayer) {
            throw new CommandException("commands.entitydata.noPlayers", new Object[]{entity.func_145748_c_()});
         } else {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            entity.func_70109_d(nbttagcompound);
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttagcompound.func_74737_b();

            NBTTagCompound nbttagcompound2;
            try {
               nbttagcompound2 = JsonToNBT.func_180713_a(func_147178_a(p_71515_1_, p_71515_2_, 1).func_150260_c());
            } catch (NBTException nbtexception) {
               throw new CommandException("commands.entitydata.tagError", new Object[]{nbtexception.getMessage()});
            }

            nbttagcompound2.func_82580_o("UUIDMost");
            nbttagcompound2.func_82580_o("UUIDLeast");
            nbttagcompound.func_179237_a(nbttagcompound2);
            if(nbttagcompound.equals(nbttagcompound1)) {
               throw new CommandException("commands.entitydata.failed", new Object[]{nbttagcompound.toString()});
            } else {
               entity.func_70020_e(nbttagcompound);
               func_152373_a(p_71515_1_, this, "commands.entitydata.success", new Object[]{nbttagcompound.toString()});
            }
         }
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
