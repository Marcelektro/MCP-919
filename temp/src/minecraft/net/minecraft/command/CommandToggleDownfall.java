package net.minecraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.WorldInfo;

public class CommandToggleDownfall extends CommandBase {
   public String func_71517_b() {
      return "toggledownfall";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.downfall.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      this.func_71554_c();
      func_152373_a(p_71515_1_, this, "commands.downfall.success", new Object[0]);
   }

   protected void func_71554_c() {
      WorldInfo worldinfo = MinecraftServer.func_71276_C().field_71305_c[0].func_72912_H();
      worldinfo.func_76084_b(!worldinfo.func_76059_o());
   }
}
