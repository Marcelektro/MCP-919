package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandListBans extends CommandBase {
   public String func_71517_b() {
      return "banlist";
   }

   public int func_82362_a() {
      return 3;
   }

   public boolean func_71519_b(ICommandSender p_71519_1_) {
      return (MinecraftServer.func_71276_C().func_71203_ab().func_72363_f().func_152689_b() || MinecraftServer.func_71276_C().func_71203_ab().func_152608_h().func_152689_b()) && super.func_71519_b(p_71519_1_);
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.banlist.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length >= 1 && p_71515_2_[0].equalsIgnoreCase("ips")) {
         p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.banlist.ips", new Object[]{Integer.valueOf(MinecraftServer.func_71276_C().func_71203_ab().func_72363_f().func_152685_a().length)}));
         p_71515_1_.func_145747_a(new ChatComponentText(func_71527_a(MinecraftServer.func_71276_C().func_71203_ab().func_72363_f().func_152685_a())));
      } else {
         p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.banlist.players", new Object[]{Integer.valueOf(MinecraftServer.func_71276_C().func_71203_ab().func_152608_h().func_152685_a().length)}));
         p_71515_1_.func_145747_a(new ChatComponentText(func_71527_a(MinecraftServer.func_71276_C().func_71203_ab().func_152608_h().func_152685_a())));
      }

   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, new String[]{"players", "ips"}):null;
   }
}
