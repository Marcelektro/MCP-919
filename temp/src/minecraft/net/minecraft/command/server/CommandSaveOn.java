package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandSaveOn extends CommandBase {
   public String func_71517_b() {
      return "save-on";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.save-on.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
      boolean flag = false;

      for(int i = 0; i < minecraftserver.field_71305_c.length; ++i) {
         if(minecraftserver.field_71305_c[i] != null) {
            WorldServer worldserver = minecraftserver.field_71305_c[i];
            if(worldserver.field_73058_d) {
               worldserver.field_73058_d = false;
               flag = true;
            }
         }
      }

      if(flag) {
         func_152373_a(p_71515_1_, this, "commands.save.enabled", new Object[0]);
      } else {
         throw new CommandException("commands.save-on.alreadyOn", new Object[0]);
      }
   }
}
