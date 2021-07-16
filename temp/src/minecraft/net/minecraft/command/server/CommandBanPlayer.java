package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.Date;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.util.BlockPos;

public class CommandBanPlayer extends CommandBase {
   public String func_71517_b() {
      return "ban";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.ban.usage";
   }

   public boolean func_71519_b(ICommandSender p_71519_1_) {
      return MinecraftServer.func_71276_C().func_71203_ab().func_152608_h().func_152689_b() && super.func_71519_b(p_71519_1_);
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length >= 1 && p_71515_2_[0].length() > 0) {
         MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
         GameProfile gameprofile = minecraftserver.func_152358_ax().func_152655_a(p_71515_2_[0]);
         if(gameprofile == null) {
            throw new CommandException("commands.ban.failed", new Object[]{p_71515_2_[0]});
         } else {
            String s = null;
            if(p_71515_2_.length >= 2) {
               s = func_147178_a(p_71515_1_, p_71515_2_, 1).func_150260_c();
            }

            UserListBansEntry userlistbansentry = new UserListBansEntry(gameprofile, (Date)null, p_71515_1_.func_70005_c_(), (Date)null, s);
            minecraftserver.func_71203_ab().func_152608_h().func_152687_a(userlistbansentry);
            EntityPlayerMP entityplayermp = minecraftserver.func_71203_ab().func_152612_a(p_71515_2_[0]);
            if(entityplayermp != null) {
               entityplayermp.field_71135_a.func_147360_c("You are banned from this server.");
            }

            func_152373_a(p_71515_1_, this, "commands.ban.success", new Object[]{p_71515_2_[0]});
         }
      } else {
         throw new WrongUsageException("commands.ban.usage", new Object[0]);
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length >= 1?func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()):null;
   }
}
