package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandWhitelist extends CommandBase {
   public String func_71517_b() {
      return "whitelist";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.whitelist.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.whitelist.usage", new Object[0]);
      } else {
         MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
         if(p_71515_2_[0].equals("on")) {
            minecraftserver.func_71203_ab().func_72371_a(true);
            func_152373_a(p_71515_1_, this, "commands.whitelist.enabled", new Object[0]);
         } else if(p_71515_2_[0].equals("off")) {
            minecraftserver.func_71203_ab().func_72371_a(false);
            func_152373_a(p_71515_1_, this, "commands.whitelist.disabled", new Object[0]);
         } else if(p_71515_2_[0].equals("list")) {
            p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.whitelist.list", new Object[]{Integer.valueOf(minecraftserver.func_71203_ab().func_152598_l().length), Integer.valueOf(minecraftserver.func_71203_ab().func_72373_m().length)}));
            String[] astring = minecraftserver.func_71203_ab().func_152598_l();
            p_71515_1_.func_145747_a(new ChatComponentText(func_71527_a(astring)));
         } else if(p_71515_2_[0].equals("add")) {
            if(p_71515_2_.length < 2) {
               throw new WrongUsageException("commands.whitelist.add.usage", new Object[0]);
            }

            GameProfile gameprofile = minecraftserver.func_152358_ax().func_152655_a(p_71515_2_[1]);
            if(gameprofile == null) {
               throw new CommandException("commands.whitelist.add.failed", new Object[]{p_71515_2_[1]});
            }

            minecraftserver.func_71203_ab().func_152601_d(gameprofile);
            func_152373_a(p_71515_1_, this, "commands.whitelist.add.success", new Object[]{p_71515_2_[1]});
         } else if(p_71515_2_[0].equals("remove")) {
            if(p_71515_2_.length < 2) {
               throw new WrongUsageException("commands.whitelist.remove.usage", new Object[0]);
            }

            GameProfile gameprofile1 = minecraftserver.func_71203_ab().func_152599_k().func_152706_a(p_71515_2_[1]);
            if(gameprofile1 == null) {
               throw new CommandException("commands.whitelist.remove.failed", new Object[]{p_71515_2_[1]});
            }

            minecraftserver.func_71203_ab().func_152597_c(gameprofile1);
            func_152373_a(p_71515_1_, this, "commands.whitelist.remove.success", new Object[]{p_71515_2_[1]});
         } else if(p_71515_2_[0].equals("reload")) {
            minecraftserver.func_71203_ab().func_72362_j();
            func_152373_a(p_71515_1_, this, "commands.whitelist.reloaded", new Object[0]);
         }

      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if(p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"on", "off", "list", "add", "remove", "reload"});
      } else {
         if(p_180525_2_.length == 2) {
            if(p_180525_2_[0].equals("remove")) {
               return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71203_ab().func_152598_l());
            }

            if(p_180525_2_[0].equals("add")) {
               return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_152358_ax().func_152654_a());
            }
         }

         return null;
      }
   }
}
