package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandStop extends CommandBase {
   public String func_71517_b() {
      return "stop";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.stop.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(MinecraftServer.func_71276_C().field_71305_c != null) {
         func_152373_a(p_71515_1_, this, "commands.stop.start", new Object[0]);
      }

      MinecraftServer.func_71276_C().func_71263_m();
   }
}
