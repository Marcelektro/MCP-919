package net.minecraft.command.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;

public class CommandAchievement extends CommandBase {
   public String func_71517_b() {
      return "achievement";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.achievement.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.achievement.usage", new Object[0]);
      } else {
         final StatBase statbase = StatList.func_151177_a(p_71515_2_[1]);
         if(statbase == null && !p_71515_2_[1].equals("*")) {
            throw new CommandException("commands.achievement.unknownAchievement", new Object[]{p_71515_2_[1]});
         } else {
            final EntityPlayerMP entityplayermp = p_71515_2_.length >= 3?func_82359_c(p_71515_1_, p_71515_2_[2]):func_71521_c(p_71515_1_);
            boolean flag = p_71515_2_[0].equalsIgnoreCase("give");
            boolean flag1 = p_71515_2_[0].equalsIgnoreCase("take");
            if(flag || flag1) {
               if(statbase == null) {
                  if(flag) {
                     for(Achievement achievement4 : AchievementList.field_76007_e) {
                        entityplayermp.func_71029_a(achievement4);
                     }

                     func_152373_a(p_71515_1_, this, "commands.achievement.give.success.all", new Object[]{entityplayermp.func_70005_c_()});
                  } else if(flag1) {
                     for(Achievement achievement5 : Lists.reverse(AchievementList.field_76007_e)) {
                        entityplayermp.func_175145_a(achievement5);
                     }

                     func_152373_a(p_71515_1_, this, "commands.achievement.take.success.all", new Object[]{entityplayermp.func_70005_c_()});
                  }

               } else {
                  if(statbase instanceof Achievement) {
                     Achievement achievement = (Achievement)statbase;
                     if(flag) {
                        if(entityplayermp.func_147099_x().func_77443_a(achievement)) {
                           throw new CommandException("commands.achievement.alreadyHave", new Object[]{entityplayermp.func_70005_c_(), statbase.func_150955_j()});
                        }

                        List<Achievement> list;
                        for(list = Lists.<Achievement>newArrayList(); achievement.field_75992_c != null && !entityplayermp.func_147099_x().func_77443_a(achievement.field_75992_c); achievement = achievement.field_75992_c) {
                           list.add(achievement.field_75992_c);
                        }

                        for(Achievement achievement1 : Lists.reverse(list)) {
                           entityplayermp.func_71029_a(achievement1);
                        }
                     } else if(flag1) {
                        if(!entityplayermp.func_147099_x().func_77443_a(achievement)) {
                           throw new CommandException("commands.achievement.dontHave", new Object[]{entityplayermp.func_70005_c_(), statbase.func_150955_j()});
                        }

                        List<Achievement> list1 = Lists.newArrayList(Iterators.filter(AchievementList.field_76007_e.iterator(), new Predicate<Achievement>() {
                           public boolean apply(Achievement p_apply_1_) {
                              return entityplayermp.func_147099_x().func_77443_a(p_apply_1_) && p_apply_1_ != statbase;
                           }
                        }));
                        List<Achievement> list2 = Lists.newArrayList(list1);

                        for(Achievement achievement2 : list1) {
                           Achievement achievement3 = achievement2;

                           boolean flag2;
                           for(flag2 = false; achievement3 != null; achievement3 = achievement3.field_75992_c) {
                              if(achievement3 == statbase) {
                                 flag2 = true;
                              }
                           }

                           if(!flag2) {
                              for(achievement3 = achievement2; achievement3 != null; achievement3 = achievement3.field_75992_c) {
                                 list2.remove(achievement2);
                              }
                           }
                        }

                        for(Achievement achievement6 : list2) {
                           entityplayermp.func_175145_a(achievement6);
                        }
                     }
                  }

                  if(flag) {
                     entityplayermp.func_71029_a(statbase);
                     func_152373_a(p_71515_1_, this, "commands.achievement.give.success.one", new Object[]{entityplayermp.func_70005_c_(), statbase.func_150955_j()});
                  } else if(flag1) {
                     entityplayermp.func_175145_a(statbase);
                     func_152373_a(p_71515_1_, this, "commands.achievement.take.success.one", new Object[]{statbase.func_150955_j(), entityplayermp.func_70005_c_()});
                  }

               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if(p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"give", "take"});
      } else if(p_180525_2_.length != 2) {
         return p_180525_2_.length == 3?func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()):null;
      } else {
         List<String> list = Lists.<String>newArrayList();

         for(StatBase statbase : StatList.field_75940_b) {
            list.add(statbase.field_75975_e);
         }

         return func_175762_a(p_180525_2_, list);
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 2;
   }
}
