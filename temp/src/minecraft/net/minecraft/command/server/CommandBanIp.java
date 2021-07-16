package net.minecraft.command.server;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.IPBanEntry;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class CommandBanIp extends CommandBase {
   public static final Pattern field_147211_a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

   public String func_71517_b() {
      return "ban-ip";
   }

   public int func_82362_a() {
      return 3;
   }

   public boolean func_71519_b(ICommandSender p_71519_1_) {
      return MinecraftServer.func_71276_C().func_71203_ab().func_72363_f().func_152689_b() && super.func_71519_b(p_71519_1_);
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.banip.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length >= 1 && p_71515_2_[0].length() > 1) {
         IChatComponent ichatcomponent = p_71515_2_.length >= 2?func_147178_a(p_71515_1_, p_71515_2_, 1):null;
         Matcher matcher = field_147211_a.matcher(p_71515_2_[0]);
         if(matcher.matches()) {
            this.func_147210_a(p_71515_1_, p_71515_2_[0], ichatcomponent == null?null:ichatcomponent.func_150260_c());
         } else {
            EntityPlayerMP entityplayermp = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(p_71515_2_[0]);
            if(entityplayermp == null) {
               throw new PlayerNotFoundException("commands.banip.invalid", new Object[0]);
            }

            this.func_147210_a(p_71515_1_, entityplayermp.func_71114_r(), ichatcomponent == null?null:ichatcomponent.func_150260_c());
         }

      } else {
         throw new WrongUsageException("commands.banip.usage", new Object[0]);
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()):null;
   }

   protected void func_147210_a(ICommandSender p_147210_1_, String p_147210_2_, String p_147210_3_) {
      IPBanEntry ipbanentry = new IPBanEntry(p_147210_2_, (Date)null, p_147210_1_.func_70005_c_(), (Date)null, p_147210_3_);
      MinecraftServer.func_71276_C().func_71203_ab().func_72363_f().func_152687_a(ipbanentry);
      List<EntityPlayerMP> list = MinecraftServer.func_71276_C().func_71203_ab().func_72382_j(p_147210_2_);
      String[] astring = new String[list.size()];
      int i = 0;

      for(EntityPlayerMP entityplayermp : list) {
         entityplayermp.field_71135_a.func_147360_c("You have been IP banned.");
         astring[i++] = entityplayermp.func_70005_c_();
      }

      if(list.isEmpty()) {
         func_152373_a(p_147210_1_, this, "commands.banip.success", new Object[]{p_147210_2_});
      } else {
         func_152373_a(p_147210_1_, this, "commands.banip.success.players", new Object[]{p_147210_2_, func_71527_a(astring)});
      }

   }
}
