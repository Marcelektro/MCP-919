package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldSettings;

public class CommandPublishLocalServer extends CommandBase {
   public String func_71517_b() {
      return "publish";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.publish.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      String s = MinecraftServer.func_71276_C().func_71206_a(WorldSettings.GameType.SURVIVAL, false);
      if(s != null) {
         func_152373_a(p_71515_1_, this, "commands.publish.started", new Object[]{s});
      } else {
         func_152373_a(p_71515_1_, this, "commands.publish.failed", new Object[0]);
      }

   }
}
