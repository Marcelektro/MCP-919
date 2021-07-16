package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandPardonPlayer extends CommandBase {
   public String func_71517_b() {
      return "pardon";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.unban.usage";
   }

   public boolean func_71519_b(ICommandSender p_71519_1_) {
      return MinecraftServer.func_71276_C().func_71203_ab().func_152608_h().func_152689_b() && super.func_71519_b(p_71519_1_);
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length == 1 && p_71515_2_[0].length() > 0) {
         MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
         GameProfile gameprofile = minecraftserver.func_71203_ab().func_152608_h().func_152703_a(p_71515_2_[0]);
         if(gameprofile == null) {
            throw new CommandException("commands.unban.failed", new Object[]{p_71515_2_[0]});
         } else {
            minecraftserver.func_71203_ab().func_152608_h().func_152684_c(gameprofile);
            func_152373_a(p_71515_1_, this, "commands.unban.success", new Object[]{p_71515_2_[0]});
         }
      } else {
         throw new WrongUsageException("commands.unban.usage", new Object[0]);
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71203_ab().func_152608_h().func_152685_a()):null;
   }
}
