package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandXP extends CommandBase {
   public String func_71517_b() {
      return "xp";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.xp.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length <= 0) {
         throw new WrongUsageException("commands.xp.usage", new Object[0]);
      } else {
         String s = p_71515_2_[0];
         boolean flag = s.endsWith("l") || s.endsWith("L");
         if(flag && s.length() > 1) {
            s = s.substring(0, s.length() - 1);
         }

         int i = func_175755_a(s);
         boolean flag1 = i < 0;
         if(flag1) {
            i *= -1;
         }

         EntityPlayer entityplayer = p_71515_2_.length > 1?func_82359_c(p_71515_1_, p_71515_2_[1]):func_71521_c(p_71515_1_);
         if(flag) {
            p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, entityplayer.field_71068_ca);
            if(flag1) {
               entityplayer.func_82242_a(-i);
               func_152373_a(p_71515_1_, this, "commands.xp.success.negative.levels", new Object[]{Integer.valueOf(i), entityplayer.func_70005_c_()});
            } else {
               entityplayer.func_82242_a(i);
               func_152373_a(p_71515_1_, this, "commands.xp.success.levels", new Object[]{Integer.valueOf(i), entityplayer.func_70005_c_()});
            }
         } else {
            p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, entityplayer.field_71067_cb);
            if(flag1) {
               throw new CommandException("commands.xp.failure.widthdrawXp", new Object[0]);
            }

            entityplayer.func_71023_q(i);
            func_152373_a(p_71515_1_, this, "commands.xp.success", new Object[]{Integer.valueOf(i), entityplayer.func_70005_c_()});
         }

      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 2?func_71530_a(p_180525_2_, this.func_71542_c()):null;
   }

   protected String[] func_71542_c() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 1;
   }
}
