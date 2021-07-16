package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandTrigger extends CommandBase {
   public String func_71517_b() {
      return "trigger";
   }

   public int func_82362_a() {
      return 0;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.trigger.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 3) {
         throw new WrongUsageException("commands.trigger.usage", new Object[0]);
      } else {
         EntityPlayerMP entityplayermp;
         if(p_71515_1_ instanceof EntityPlayerMP) {
            entityplayermp = (EntityPlayerMP)p_71515_1_;
         } else {
            Entity entity = p_71515_1_.func_174793_f();
            if(!(entity instanceof EntityPlayerMP)) {
               throw new CommandException("commands.trigger.invalidPlayer", new Object[0]);
            }

            entityplayermp = (EntityPlayerMP)entity;
         }

         Scoreboard scoreboard = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
         ScoreObjective scoreobjective = scoreboard.func_96518_b(p_71515_2_[0]);
         if(scoreobjective != null && scoreobjective.func_96680_c() == IScoreObjectiveCriteria.field_178791_c) {
            int i = func_175755_a(p_71515_2_[2]);
            if(!scoreboard.func_178819_b(entityplayermp.func_70005_c_(), scoreobjective)) {
               throw new CommandException("commands.trigger.invalidObjective", new Object[]{p_71515_2_[0]});
            } else {
               Score score = scoreboard.func_96529_a(entityplayermp.func_70005_c_(), scoreobjective);
               if(score.func_178816_g()) {
                  throw new CommandException("commands.trigger.disabled", new Object[]{p_71515_2_[0]});
               } else {
                  if("set".equals(p_71515_2_[1])) {
                     score.func_96647_c(i);
                  } else {
                     if(!"add".equals(p_71515_2_[1])) {
                        throw new CommandException("commands.trigger.invalidMode", new Object[]{p_71515_2_[1]});
                     }

                     score.func_96649_a(i);
                  }

                  score.func_178815_a(true);
                  if(entityplayermp.field_71134_c.func_73083_d()) {
                     func_152373_a(p_71515_1_, this, "commands.trigger.success", new Object[]{p_71515_2_[0], p_71515_2_[1], p_71515_2_[2]});
                  }

               }
            }
         } else {
            throw new CommandException("commands.trigger.invalidObjective", new Object[]{p_71515_2_[0]});
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if(p_180525_2_.length == 1) {
         Scoreboard scoreboard = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
         List<String> list = Lists.<String>newArrayList();

         for(ScoreObjective scoreobjective : scoreboard.func_96514_c()) {
            if(scoreobjective.func_96680_c() == IScoreObjectiveCriteria.field_178791_c) {
               list.add(scoreobjective.func_96679_b());
            }
         }

         return func_71530_a(p_180525_2_, (String[])list.toArray(new String[list.size()]));
      } else {
         return p_180525_2_.length == 2?func_71530_a(p_180525_2_, new String[]{"add", "set"}):null;
      }
   }
}
