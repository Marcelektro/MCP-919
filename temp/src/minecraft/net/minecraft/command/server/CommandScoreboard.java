package net.minecraft.command.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandScoreboard extends CommandBase {
   public String func_71517_b() {
      return "scoreboard";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.scoreboard.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(!this.func_175780_b(p_71515_1_, p_71515_2_)) {
         if(p_71515_2_.length < 1) {
            throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
         } else {
            if(p_71515_2_[0].equalsIgnoreCase("objectives")) {
               if(p_71515_2_.length == 1) {
                  throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
               }

               if(p_71515_2_[1].equalsIgnoreCase("list")) {
                  this.func_147196_d(p_71515_1_);
               } else if(p_71515_2_[1].equalsIgnoreCase("add")) {
                  if(p_71515_2_.length < 4) {
                     throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
                  }

                  this.func_147193_c(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("remove")) {
                  if(p_71515_2_.length != 3) {
                     throw new WrongUsageException("commands.scoreboard.objectives.remove.usage", new Object[0]);
                  }

                  this.func_147191_h(p_71515_1_, p_71515_2_[2]);
               } else {
                  if(!p_71515_2_[1].equalsIgnoreCase("setdisplay")) {
                     throw new WrongUsageException("commands.scoreboard.objectives.usage", new Object[0]);
                  }

                  if(p_71515_2_.length != 3 && p_71515_2_.length != 4) {
                     throw new WrongUsageException("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
                  }

                  this.func_147198_k(p_71515_1_, p_71515_2_, 2);
               }
            } else if(p_71515_2_[0].equalsIgnoreCase("players")) {
               if(p_71515_2_.length == 1) {
                  throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
               }

               if(p_71515_2_[1].equalsIgnoreCase("list")) {
                  if(p_71515_2_.length > 3) {
                     throw new WrongUsageException("commands.scoreboard.players.list.usage", new Object[0]);
                  }

                  this.func_147195_l(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("add")) {
                  if(p_71515_2_.length < 5) {
                     throw new WrongUsageException("commands.scoreboard.players.add.usage", new Object[0]);
                  }

                  this.func_147197_m(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("remove")) {
                  if(p_71515_2_.length < 5) {
                     throw new WrongUsageException("commands.scoreboard.players.remove.usage", new Object[0]);
                  }

                  this.func_147197_m(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("set")) {
                  if(p_71515_2_.length < 5) {
                     throw new WrongUsageException("commands.scoreboard.players.set.usage", new Object[0]);
                  }

                  this.func_147197_m(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("reset")) {
                  if(p_71515_2_.length != 3 && p_71515_2_.length != 4) {
                     throw new WrongUsageException("commands.scoreboard.players.reset.usage", new Object[0]);
                  }

                  this.func_147187_n(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("enable")) {
                  if(p_71515_2_.length != 4) {
                     throw new WrongUsageException("commands.scoreboard.players.enable.usage", new Object[0]);
                  }

                  this.func_175779_n(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("test")) {
                  if(p_71515_2_.length != 5 && p_71515_2_.length != 6) {
                     throw new WrongUsageException("commands.scoreboard.players.test.usage", new Object[0]);
                  }

                  this.func_175781_o(p_71515_1_, p_71515_2_, 2);
               } else {
                  if(!p_71515_2_[1].equalsIgnoreCase("operation")) {
                     throw new WrongUsageException("commands.scoreboard.players.usage", new Object[0]);
                  }

                  if(p_71515_2_.length != 7) {
                     throw new WrongUsageException("commands.scoreboard.players.operation.usage", new Object[0]);
                  }

                  this.func_175778_p(p_71515_1_, p_71515_2_, 2);
               }
            } else {
               if(!p_71515_2_[0].equalsIgnoreCase("teams")) {
                  throw new WrongUsageException("commands.scoreboard.usage", new Object[0]);
               }

               if(p_71515_2_.length == 1) {
                  throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
               }

               if(p_71515_2_[1].equalsIgnoreCase("list")) {
                  if(p_71515_2_.length > 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.list.usage", new Object[0]);
                  }

                  this.func_147186_g(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("add")) {
                  if(p_71515_2_.length < 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
                  }

                  this.func_147185_d(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("remove")) {
                  if(p_71515_2_.length != 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.remove.usage", new Object[0]);
                  }

                  this.func_147194_f(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("empty")) {
                  if(p_71515_2_.length != 3) {
                     throw new WrongUsageException("commands.scoreboard.teams.empty.usage", new Object[0]);
                  }

                  this.func_147188_j(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("join")) {
                  if(p_71515_2_.length < 4 && (p_71515_2_.length != 3 || !(p_71515_1_ instanceof EntityPlayer))) {
                     throw new WrongUsageException("commands.scoreboard.teams.join.usage", new Object[0]);
                  }

                  this.func_147190_h(p_71515_1_, p_71515_2_, 2);
               } else if(p_71515_2_[1].equalsIgnoreCase("leave")) {
                  if(p_71515_2_.length < 3 && !(p_71515_1_ instanceof EntityPlayer)) {
                     throw new WrongUsageException("commands.scoreboard.teams.leave.usage", new Object[0]);
                  }

                  this.func_147199_i(p_71515_1_, p_71515_2_, 2);
               } else {
                  if(!p_71515_2_[1].equalsIgnoreCase("option")) {
                     throw new WrongUsageException("commands.scoreboard.teams.usage", new Object[0]);
                  }

                  if(p_71515_2_.length != 4 && p_71515_2_.length != 5) {
                     throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
                  }

                  this.func_147200_e(p_71515_1_, p_71515_2_, 2);
               }
            }

         }
      }
   }

   private boolean func_175780_b(ICommandSender p_175780_1_, String[] p_175780_2_) throws CommandException {
      int i = -1;

      for(int j = 0; j < p_175780_2_.length; ++j) {
         if(this.func_82358_a(p_175780_2_, j) && "*".equals(p_175780_2_[j])) {
            if(i >= 0) {
               throw new CommandException("commands.scoreboard.noMultiWildcard", new Object[0]);
            }

            i = j;
         }
      }

      if(i < 0) {
         return false;
      } else {
         List<String> list1 = Lists.newArrayList(this.func_147192_d().func_96526_d());
         String s = p_175780_2_[i];
         List<String> list = Lists.<String>newArrayList();

         for(String s1 : list1) {
            p_175780_2_[i] = s1;

            try {
               this.func_71515_b(p_175780_1_, p_175780_2_);
               list.add(s1);
            } catch (CommandException commandexception) {
               ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(commandexception.getMessage(), commandexception.func_74844_a());
               chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
               p_175780_1_.func_145747_a(chatcomponenttranslation);
            }
         }

         p_175780_2_[i] = s;
         p_175780_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, list.size());
         if(list.size() == 0) {
            throw new WrongUsageException("commands.scoreboard.allMatchesFailed", new Object[0]);
         } else {
            return true;
         }
      }
   }

   protected Scoreboard func_147192_d() {
      return MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();
   }

   protected ScoreObjective func_147189_a(String p_147189_1_, boolean p_147189_2_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      ScoreObjective scoreobjective = scoreboard.func_96518_b(p_147189_1_);
      if(scoreobjective == null) {
         throw new CommandException("commands.scoreboard.objectiveNotFound", new Object[]{p_147189_1_});
      } else if(p_147189_2_ && scoreobjective.func_96680_c().func_96637_b()) {
         throw new CommandException("commands.scoreboard.objectiveReadOnly", new Object[]{p_147189_1_});
      } else {
         return scoreobjective;
      }
   }

   protected ScorePlayerTeam func_147183_a(String p_147183_1_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      ScorePlayerTeam scoreplayerteam = scoreboard.func_96508_e(p_147183_1_);
      if(scoreplayerteam == null) {
         throw new CommandException("commands.scoreboard.teamNotFound", new Object[]{p_147183_1_});
      } else {
         return scoreplayerteam;
      }
   }

   protected void func_147193_c(ICommandSender p_147193_1_, String[] p_147193_2_, int p_147193_3_) throws CommandException {
      String s = p_147193_2_[p_147193_3_++];
      String s1 = p_147193_2_[p_147193_3_++];
      Scoreboard scoreboard = this.func_147192_d();
      IScoreObjectiveCriteria iscoreobjectivecriteria = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.field_96643_a.get(s1);
      if(iscoreobjectivecriteria == null) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.wrongType", new Object[]{s1});
      } else if(scoreboard.func_96518_b(s) != null) {
         throw new CommandException("commands.scoreboard.objectives.add.alreadyExists", new Object[]{s});
      } else if(s.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.objectives.add.tooLong", new Object[]{s, Integer.valueOf(16)});
      } else if(s.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.objectives.add.usage", new Object[0]);
      } else {
         if(p_147193_2_.length > p_147193_3_) {
            String s2 = func_147178_a(p_147193_1_, p_147193_2_, p_147193_3_).func_150260_c();
            if(s2.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.objectives.add.displayTooLong", new Object[]{s2, Integer.valueOf(32)});
            }

            if(s2.length() > 0) {
               scoreboard.func_96535_a(s, iscoreobjectivecriteria).func_96681_a(s2);
            } else {
               scoreboard.func_96535_a(s, iscoreobjectivecriteria);
            }
         } else {
            scoreboard.func_96535_a(s, iscoreobjectivecriteria);
         }

         func_152373_a(p_147193_1_, this, "commands.scoreboard.objectives.add.success", new Object[]{s});
      }
   }

   protected void func_147185_d(ICommandSender p_147185_1_, String[] p_147185_2_, int p_147185_3_) throws CommandException {
      String s = p_147185_2_[p_147185_3_++];
      Scoreboard scoreboard = this.func_147192_d();
      if(scoreboard.func_96508_e(s) != null) {
         throw new CommandException("commands.scoreboard.teams.add.alreadyExists", new Object[]{s});
      } else if(s.length() > 16) {
         throw new SyntaxErrorException("commands.scoreboard.teams.add.tooLong", new Object[]{s, Integer.valueOf(16)});
      } else if(s.length() == 0) {
         throw new WrongUsageException("commands.scoreboard.teams.add.usage", new Object[0]);
      } else {
         if(p_147185_2_.length > p_147185_3_) {
            String s1 = func_147178_a(p_147185_1_, p_147185_2_, p_147185_3_).func_150260_c();
            if(s1.length() > 32) {
               throw new SyntaxErrorException("commands.scoreboard.teams.add.displayTooLong", new Object[]{s1, Integer.valueOf(32)});
            }

            if(s1.length() > 0) {
               scoreboard.func_96527_f(s).func_96664_a(s1);
            } else {
               scoreboard.func_96527_f(s);
            }
         } else {
            scoreboard.func_96527_f(s);
         }

         func_152373_a(p_147185_1_, this, "commands.scoreboard.teams.add.success", new Object[]{s});
      }
   }

   protected void func_147200_e(ICommandSender p_147200_1_, String[] p_147200_2_, int p_147200_3_) throws CommandException {
      ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147200_2_[p_147200_3_++]);
      if(scoreplayerteam != null) {
         String s = p_147200_2_[p_147200_3_++].toLowerCase();
         if(!s.equalsIgnoreCase("color") && !s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles") && !s.equalsIgnoreCase("nametagVisibility") && !s.equalsIgnoreCase("deathMessageVisibility")) {
            throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
         } else if(p_147200_2_.length == 4) {
            if(s.equalsIgnoreCase("color")) {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_96333_a(EnumChatFormatting.func_96296_a(true, false))});
            } else if(!s.equalsIgnoreCase("friendlyfire") && !s.equalsIgnoreCase("seeFriendlyInvisibles")) {
               if(!s.equalsIgnoreCase("nametagVisibility") && !s.equalsIgnoreCase("deathMessageVisibility")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.usage", new Object[0]);
               } else {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_71527_a(Team.EnumVisible.func_178825_a())});
               }
            } else {
               throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_96333_a(Arrays.asList(new String[]{"true", "false"}))});
            }
         } else {
            String s1 = p_147200_2_[p_147200_3_];
            if(s.equalsIgnoreCase("color")) {
               EnumChatFormatting enumchatformatting = EnumChatFormatting.func_96300_b(s1);
               if(enumchatformatting == null || enumchatformatting.func_96301_b()) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_96333_a(EnumChatFormatting.func_96296_a(true, false))});
               }

               scoreplayerteam.func_178774_a(enumchatformatting);
               scoreplayerteam.func_96666_b(enumchatformatting.toString());
               scoreplayerteam.func_96662_c(EnumChatFormatting.RESET.toString());
            } else if(s.equalsIgnoreCase("friendlyfire")) {
               if(!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_96333_a(Arrays.asList(new String[]{"true", "false"}))});
               }

               scoreplayerteam.func_96660_a(s1.equalsIgnoreCase("true"));
            } else if(s.equalsIgnoreCase("seeFriendlyInvisibles")) {
               if(!s1.equalsIgnoreCase("true") && !s1.equalsIgnoreCase("false")) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_96333_a(Arrays.asList(new String[]{"true", "false"}))});
               }

               scoreplayerteam.func_98300_b(s1.equalsIgnoreCase("true"));
            } else if(s.equalsIgnoreCase("nametagVisibility")) {
               Team.EnumVisible team$enumvisible = Team.EnumVisible.func_178824_a(s1);
               if(team$enumvisible == null) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_71527_a(Team.EnumVisible.func_178825_a())});
               }

               scoreplayerteam.func_178772_a(team$enumvisible);
            } else if(s.equalsIgnoreCase("deathMessageVisibility")) {
               Team.EnumVisible team$enumvisible1 = Team.EnumVisible.func_178824_a(s1);
               if(team$enumvisible1 == null) {
                  throw new WrongUsageException("commands.scoreboard.teams.option.noValue", new Object[]{s, func_71527_a(Team.EnumVisible.func_178825_a())});
               }

               scoreplayerteam.func_178773_b(team$enumvisible1);
            }

            func_152373_a(p_147200_1_, this, "commands.scoreboard.teams.option.success", new Object[]{s, scoreplayerteam.func_96661_b(), s1});
         }
      }
   }

   protected void func_147194_f(ICommandSender p_147194_1_, String[] p_147194_2_, int p_147194_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147194_2_[p_147194_3_]);
      if(scoreplayerteam != null) {
         scoreboard.func_96511_d(scoreplayerteam);
         func_152373_a(p_147194_1_, this, "commands.scoreboard.teams.remove.success", new Object[]{scoreplayerteam.func_96661_b()});
      }
   }

   protected void func_147186_g(ICommandSender p_147186_1_, String[] p_147186_2_, int p_147186_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      if(p_147186_2_.length > p_147186_3_) {
         ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147186_2_[p_147186_3_]);
         if(scoreplayerteam == null) {
            return;
         }

         Collection<String> collection = scoreplayerteam.func_96670_d();
         p_147186_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, collection.size());
         if(collection.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.player.empty", new Object[]{scoreplayerteam.func_96661_b()});
         }

         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.scoreboard.teams.list.player.count", new Object[]{Integer.valueOf(collection.size()), scoreplayerteam.func_96661_b()});
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147186_1_.func_145747_a(chatcomponenttranslation);
         p_147186_1_.func_145747_a(new ChatComponentText(func_71527_a(collection.toArray())));
      } else {
         Collection<ScorePlayerTeam> collection1 = scoreboard.func_96525_g();
         p_147186_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, collection1.size());
         if(collection1.size() <= 0) {
            throw new CommandException("commands.scoreboard.teams.list.empty", new Object[0]);
         }

         ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.scoreboard.teams.list.count", new Object[]{Integer.valueOf(collection1.size())});
         chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147186_1_.func_145747_a(chatcomponenttranslation1);

         for(ScorePlayerTeam scoreplayerteam1 : collection1) {
            p_147186_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.teams.list.entry", new Object[]{scoreplayerteam1.func_96661_b(), scoreplayerteam1.func_96669_c(), Integer.valueOf(scoreplayerteam1.func_96670_d().size())}));
         }
      }

   }

   protected void func_147190_h(ICommandSender p_147190_1_, String[] p_147190_2_, int p_147190_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      String s = p_147190_2_[p_147190_3_++];
      Set<String> set = Sets.<String>newHashSet();
      Set<String> set1 = Sets.<String>newHashSet();
      if(p_147190_1_ instanceof EntityPlayer && p_147190_3_ == p_147190_2_.length) {
         String s4 = func_71521_c(p_147190_1_).func_70005_c_();
         if(scoreboard.func_151392_a(s4, s)) {
            set.add(s4);
         } else {
            set1.add(s4);
         }
      } else {
         while(p_147190_3_ < p_147190_2_.length) {
            String s1 = p_147190_2_[p_147190_3_++];
            if(s1.startsWith("@")) {
               for(Entity entity : func_175763_c(p_147190_1_, s1)) {
                  String s3 = func_175758_e(p_147190_1_, entity.func_110124_au().toString());
                  if(scoreboard.func_151392_a(s3, s)) {
                     set.add(s3);
                  } else {
                     set1.add(s3);
                  }
               }
            } else {
               String s2 = func_175758_e(p_147190_1_, s1);
               if(scoreboard.func_151392_a(s2, s)) {
                  set.add(s2);
               } else {
                  set1.add(s2);
               }
            }
         }
      }

      if(!set.isEmpty()) {
         p_147190_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, set.size());
         func_152373_a(p_147190_1_, this, "commands.scoreboard.teams.join.success", new Object[]{Integer.valueOf(set.size()), s, func_71527_a(set.toArray(new String[set.size()]))});
      }

      if(!set1.isEmpty()) {
         throw new CommandException("commands.scoreboard.teams.join.failure", new Object[]{Integer.valueOf(set1.size()), s, func_71527_a(set1.toArray(new String[set1.size()]))});
      }
   }

   protected void func_147199_i(ICommandSender p_147199_1_, String[] p_147199_2_, int p_147199_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      Set<String> set = Sets.<String>newHashSet();
      Set<String> set1 = Sets.<String>newHashSet();
      if(p_147199_1_ instanceof EntityPlayer && p_147199_3_ == p_147199_2_.length) {
         String s3 = func_71521_c(p_147199_1_).func_70005_c_();
         if(scoreboard.func_96524_g(s3)) {
            set.add(s3);
         } else {
            set1.add(s3);
         }
      } else {
         while(p_147199_3_ < p_147199_2_.length) {
            String s = p_147199_2_[p_147199_3_++];
            if(s.startsWith("@")) {
               for(Entity entity : func_175763_c(p_147199_1_, s)) {
                  String s2 = func_175758_e(p_147199_1_, entity.func_110124_au().toString());
                  if(scoreboard.func_96524_g(s2)) {
                     set.add(s2);
                  } else {
                     set1.add(s2);
                  }
               }
            } else {
               String s1 = func_175758_e(p_147199_1_, s);
               if(scoreboard.func_96524_g(s1)) {
                  set.add(s1);
               } else {
                  set1.add(s1);
               }
            }
         }
      }

      if(!set.isEmpty()) {
         p_147199_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, set.size());
         func_152373_a(p_147199_1_, this, "commands.scoreboard.teams.leave.success", new Object[]{Integer.valueOf(set.size()), func_71527_a(set.toArray(new String[set.size()]))});
      }

      if(!set1.isEmpty()) {
         throw new CommandException("commands.scoreboard.teams.leave.failure", new Object[]{Integer.valueOf(set1.size()), func_71527_a(set1.toArray(new String[set1.size()]))});
      }
   }

   protected void func_147188_j(ICommandSender p_147188_1_, String[] p_147188_2_, int p_147188_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      ScorePlayerTeam scoreplayerteam = this.func_147183_a(p_147188_2_[p_147188_3_]);
      if(scoreplayerteam != null) {
         Collection<String> collection = Lists.newArrayList(scoreplayerteam.func_96670_d());
         p_147188_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, collection.size());
         if(collection.isEmpty()) {
            throw new CommandException("commands.scoreboard.teams.empty.alreadyEmpty", new Object[]{scoreplayerteam.func_96661_b()});
         } else {
            for(String s : collection) {
               scoreboard.func_96512_b(s, scoreplayerteam);
            }

            func_152373_a(p_147188_1_, this, "commands.scoreboard.teams.empty.success", new Object[]{Integer.valueOf(collection.size()), scoreplayerteam.func_96661_b()});
         }
      }
   }

   protected void func_147191_h(ICommandSender p_147191_1_, String p_147191_2_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      ScoreObjective scoreobjective = this.func_147189_a(p_147191_2_, false);
      scoreboard.func_96519_k(scoreobjective);
      func_152373_a(p_147191_1_, this, "commands.scoreboard.objectives.remove.success", new Object[]{p_147191_2_});
   }

   protected void func_147196_d(ICommandSender p_147196_1_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      Collection<ScoreObjective> collection = scoreboard.func_96514_c();
      if(collection.size() <= 0) {
         throw new CommandException("commands.scoreboard.objectives.list.empty", new Object[0]);
      } else {
         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.scoreboard.objectives.list.count", new Object[]{Integer.valueOf(collection.size())});
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147196_1_.func_145747_a(chatcomponenttranslation);

         for(ScoreObjective scoreobjective : collection) {
            p_147196_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.objectives.list.entry", new Object[]{scoreobjective.func_96679_b(), scoreobjective.func_96678_d(), scoreobjective.func_96680_c().func_96636_a()}));
         }

      }
   }

   protected void func_147198_k(ICommandSender p_147198_1_, String[] p_147198_2_, int p_147198_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      String s = p_147198_2_[p_147198_3_++];
      int i = Scoreboard.func_96537_j(s);
      ScoreObjective scoreobjective = null;
      if(p_147198_2_.length == 4) {
         scoreobjective = this.func_147189_a(p_147198_2_[p_147198_3_], false);
      }

      if(i < 0) {
         throw new CommandException("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[]{s});
      } else {
         scoreboard.func_96530_a(i, scoreobjective);
         if(scoreobjective != null) {
            func_152373_a(p_147198_1_, this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[]{Scoreboard.func_96517_b(i), scoreobjective.func_96679_b()});
         } else {
            func_152373_a(p_147198_1_, this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[]{Scoreboard.func_96517_b(i)});
         }

      }
   }

   protected void func_147195_l(ICommandSender p_147195_1_, String[] p_147195_2_, int p_147195_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      if(p_147195_2_.length > p_147195_3_) {
         String s = func_175758_e(p_147195_1_, p_147195_2_[p_147195_3_]);
         Map<ScoreObjective, Score> map = scoreboard.func_96510_d(s);
         p_147195_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, map.size());
         if(map.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.player.empty", new Object[]{s});
         }

         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.scoreboard.players.list.player.count", new Object[]{Integer.valueOf(map.size()), s});
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147195_1_.func_145747_a(chatcomponenttranslation);

         for(Score score : map.values()) {
            p_147195_1_.func_145747_a(new ChatComponentTranslation("commands.scoreboard.players.list.player.entry", new Object[]{Integer.valueOf(score.func_96652_c()), score.func_96645_d().func_96678_d(), score.func_96645_d().func_96679_b()}));
         }
      } else {
         Collection<String> collection = scoreboard.func_96526_d();
         p_147195_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, collection.size());
         if(collection.size() <= 0) {
            throw new CommandException("commands.scoreboard.players.list.empty", new Object[0]);
         }

         ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.scoreboard.players.list.count", new Object[]{Integer.valueOf(collection.size())});
         chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
         p_147195_1_.func_145747_a(chatcomponenttranslation1);
         p_147195_1_.func_145747_a(new ChatComponentText(func_71527_a(collection.toArray())));
      }

   }

   protected void func_147197_m(ICommandSender p_147197_1_, String[] p_147197_2_, int p_147197_3_) throws CommandException {
      String s = p_147197_2_[p_147197_3_ - 1];
      int i = p_147197_3_;
      String s1 = func_175758_e(p_147197_1_, p_147197_2_[p_147197_3_++]);
      if(s1.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{s1, Integer.valueOf(40)});
      } else {
         ScoreObjective scoreobjective = this.func_147189_a(p_147197_2_[p_147197_3_++], true);
         int j = s.equalsIgnoreCase("set")?func_175755_a(p_147197_2_[p_147197_3_++]):func_180528_a(p_147197_2_[p_147197_3_++], 0);
         if(p_147197_2_.length > p_147197_3_) {
            Entity entity = func_175768_b(p_147197_1_, p_147197_2_[i]);

            try {
               NBTTagCompound nbttagcompound = JsonToNBT.func_180713_a(func_180529_a(p_147197_2_, p_147197_3_));
               NBTTagCompound nbttagcompound1 = new NBTTagCompound();
               entity.func_70109_d(nbttagcompound1);
               if(!NBTUtil.func_181123_a(nbttagcompound, nbttagcompound1, true)) {
                  throw new CommandException("commands.scoreboard.players.set.tagMismatch", new Object[]{s1});
               }
            } catch (NBTException nbtexception) {
               throw new CommandException("commands.scoreboard.players.set.tagError", new Object[]{nbtexception.getMessage()});
            }
         }

         Scoreboard scoreboard = this.func_147192_d();
         Score score = scoreboard.func_96529_a(s1, scoreobjective);
         if(s.equalsIgnoreCase("set")) {
            score.func_96647_c(j);
         } else if(s.equalsIgnoreCase("add")) {
            score.func_96649_a(j);
         } else {
            score.func_96646_b(j);
         }

         func_152373_a(p_147197_1_, this, "commands.scoreboard.players.set.success", new Object[]{scoreobjective.func_96679_b(), s1, Integer.valueOf(score.func_96652_c())});
      }
   }

   protected void func_147187_n(ICommandSender p_147187_1_, String[] p_147187_2_, int p_147187_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      String s = func_175758_e(p_147187_1_, p_147187_2_[p_147187_3_++]);
      if(p_147187_2_.length > p_147187_3_) {
         ScoreObjective scoreobjective = this.func_147189_a(p_147187_2_[p_147187_3_++], false);
         scoreboard.func_178822_d(s, scoreobjective);
         func_152373_a(p_147187_1_, this, "commands.scoreboard.players.resetscore.success", new Object[]{scoreobjective.func_96679_b(), s});
      } else {
         scoreboard.func_178822_d(s, (ScoreObjective)null);
         func_152373_a(p_147187_1_, this, "commands.scoreboard.players.reset.success", new Object[]{s});
      }

   }

   protected void func_175779_n(ICommandSender p_175779_1_, String[] p_175779_2_, int p_175779_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      String s = func_96332_d(p_175779_1_, p_175779_2_[p_175779_3_++]);
      if(s.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{s, Integer.valueOf(40)});
      } else {
         ScoreObjective scoreobjective = this.func_147189_a(p_175779_2_[p_175779_3_], false);
         if(scoreobjective.func_96680_c() != IScoreObjectiveCriteria.field_178791_c) {
            throw new CommandException("commands.scoreboard.players.enable.noTrigger", new Object[]{scoreobjective.func_96679_b()});
         } else {
            Score score = scoreboard.func_96529_a(s, scoreobjective);
            score.func_178815_a(false);
            func_152373_a(p_175779_1_, this, "commands.scoreboard.players.enable.success", new Object[]{scoreobjective.func_96679_b(), s});
         }
      }
   }

   protected void func_175781_o(ICommandSender p_175781_1_, String[] p_175781_2_, int p_175781_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      String s = func_175758_e(p_175781_1_, p_175781_2_[p_175781_3_++]);
      if(s.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{s, Integer.valueOf(40)});
      } else {
         ScoreObjective scoreobjective = this.func_147189_a(p_175781_2_[p_175781_3_++], false);
         if(!scoreboard.func_178819_b(s, scoreobjective)) {
            throw new CommandException("commands.scoreboard.players.test.notFound", new Object[]{scoreobjective.func_96679_b(), s});
         } else {
            int i = p_175781_2_[p_175781_3_].equals("*")?Integer.MIN_VALUE:func_175755_a(p_175781_2_[p_175781_3_]);
            ++p_175781_3_;
            int j = p_175781_3_ < p_175781_2_.length && !p_175781_2_[p_175781_3_].equals("*")?func_180528_a(p_175781_2_[p_175781_3_], i):Integer.MAX_VALUE;
            Score score = scoreboard.func_96529_a(s, scoreobjective);
            if(score.func_96652_c() >= i && score.func_96652_c() <= j) {
               func_152373_a(p_175781_1_, this, "commands.scoreboard.players.test.success", new Object[]{Integer.valueOf(score.func_96652_c()), Integer.valueOf(i), Integer.valueOf(j)});
            } else {
               throw new CommandException("commands.scoreboard.players.test.failed", new Object[]{Integer.valueOf(score.func_96652_c()), Integer.valueOf(i), Integer.valueOf(j)});
            }
         }
      }
   }

   protected void func_175778_p(ICommandSender p_175778_1_, String[] p_175778_2_, int p_175778_3_) throws CommandException {
      Scoreboard scoreboard = this.func_147192_d();
      String s = func_175758_e(p_175778_1_, p_175778_2_[p_175778_3_++]);
      ScoreObjective scoreobjective = this.func_147189_a(p_175778_2_[p_175778_3_++], true);
      String s1 = p_175778_2_[p_175778_3_++];
      String s2 = func_175758_e(p_175778_1_, p_175778_2_[p_175778_3_++]);
      ScoreObjective scoreobjective1 = this.func_147189_a(p_175778_2_[p_175778_3_], false);
      if(s.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{s, Integer.valueOf(40)});
      } else if(s2.length() > 40) {
         throw new SyntaxErrorException("commands.scoreboard.players.name.tooLong", new Object[]{s2, Integer.valueOf(40)});
      } else {
         Score score = scoreboard.func_96529_a(s, scoreobjective);
         if(!scoreboard.func_178819_b(s2, scoreobjective1)) {
            throw new CommandException("commands.scoreboard.players.operation.notFound", new Object[]{scoreobjective1.func_96679_b(), s2});
         } else {
            Score score1 = scoreboard.func_96529_a(s2, scoreobjective1);
            if(s1.equals("+=")) {
               score.func_96647_c(score.func_96652_c() + score1.func_96652_c());
            } else if(s1.equals("-=")) {
               score.func_96647_c(score.func_96652_c() - score1.func_96652_c());
            } else if(s1.equals("*=")) {
               score.func_96647_c(score.func_96652_c() * score1.func_96652_c());
            } else if(s1.equals("/=")) {
               if(score1.func_96652_c() != 0) {
                  score.func_96647_c(score.func_96652_c() / score1.func_96652_c());
               }
            } else if(s1.equals("%=")) {
               if(score1.func_96652_c() != 0) {
                  score.func_96647_c(score.func_96652_c() % score1.func_96652_c());
               }
            } else if(s1.equals("=")) {
               score.func_96647_c(score1.func_96652_c());
            } else if(s1.equals("<")) {
               score.func_96647_c(Math.min(score.func_96652_c(), score1.func_96652_c()));
            } else if(s1.equals(">")) {
               score.func_96647_c(Math.max(score.func_96652_c(), score1.func_96652_c()));
            } else {
               if(!s1.equals("><")) {
                  throw new CommandException("commands.scoreboard.players.operation.invalidOperation", new Object[]{s1});
               }

               int i = score.func_96652_c();
               score.func_96647_c(score1.func_96652_c());
               score1.func_96647_c(i);
            }

            func_152373_a(p_175778_1_, this, "commands.scoreboard.players.operation.success", new Object[0]);
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if(p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, new String[]{"objectives", "players", "teams"});
      } else {
         if(p_180525_2_[0].equalsIgnoreCase("objectives")) {
            if(p_180525_2_.length == 2) {
               return func_71530_a(p_180525_2_, new String[]{"list", "add", "remove", "setdisplay"});
            }

            if(p_180525_2_[1].equalsIgnoreCase("add")) {
               if(p_180525_2_.length == 4) {
                  Set<String> set = IScoreObjectiveCriteria.field_96643_a.keySet();
                  return func_175762_a(p_180525_2_, set);
               }
            } else if(p_180525_2_[1].equalsIgnoreCase("remove")) {
               if(p_180525_2_.length == 3) {
                  return func_175762_a(p_180525_2_, this.func_147184_a(false));
               }
            } else if(p_180525_2_[1].equalsIgnoreCase("setdisplay")) {
               if(p_180525_2_.length == 3) {
                  return func_71530_a(p_180525_2_, Scoreboard.func_178821_h());
               }

               if(p_180525_2_.length == 4) {
                  return func_175762_a(p_180525_2_, this.func_147184_a(false));
               }
            }
         } else if(p_180525_2_[0].equalsIgnoreCase("players")) {
            if(p_180525_2_.length == 2) {
               return func_71530_a(p_180525_2_, new String[]{"set", "add", "remove", "reset", "list", "enable", "test", "operation"});
            }

            if(!p_180525_2_[1].equalsIgnoreCase("set") && !p_180525_2_[1].equalsIgnoreCase("add") && !p_180525_2_[1].equalsIgnoreCase("remove") && !p_180525_2_[1].equalsIgnoreCase("reset")) {
               if(p_180525_2_[1].equalsIgnoreCase("enable")) {
                  if(p_180525_2_.length == 3) {
                     return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
                  }

                  if(p_180525_2_.length == 4) {
                     return func_175762_a(p_180525_2_, this.func_175782_e());
                  }
               } else if(!p_180525_2_[1].equalsIgnoreCase("list") && !p_180525_2_[1].equalsIgnoreCase("test")) {
                  if(p_180525_2_[1].equalsIgnoreCase("operation")) {
                     if(p_180525_2_.length == 3) {
                        return func_175762_a(p_180525_2_, this.func_147192_d().func_96526_d());
                     }

                     if(p_180525_2_.length == 4) {
                        return func_175762_a(p_180525_2_, this.func_147184_a(true));
                     }

                     if(p_180525_2_.length == 5) {
                        return func_71530_a(p_180525_2_, new String[]{"+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><"});
                     }

                     if(p_180525_2_.length == 6) {
                        return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
                     }

                     if(p_180525_2_.length == 7) {
                        return func_175762_a(p_180525_2_, this.func_147184_a(false));
                     }
                  }
               } else {
                  if(p_180525_2_.length == 3) {
                     return func_175762_a(p_180525_2_, this.func_147192_d().func_96526_d());
                  }

                  if(p_180525_2_.length == 4 && p_180525_2_[1].equalsIgnoreCase("test")) {
                     return func_175762_a(p_180525_2_, this.func_147184_a(false));
                  }
               }
            } else {
               if(p_180525_2_.length == 3) {
                  return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
               }

               if(p_180525_2_.length == 4) {
                  return func_175762_a(p_180525_2_, this.func_147184_a(true));
               }
            }
         } else if(p_180525_2_[0].equalsIgnoreCase("teams")) {
            if(p_180525_2_.length == 2) {
               return func_71530_a(p_180525_2_, new String[]{"add", "remove", "join", "leave", "empty", "list", "option"});
            }

            if(p_180525_2_[1].equalsIgnoreCase("join")) {
               if(p_180525_2_.length == 3) {
                  return func_175762_a(p_180525_2_, this.func_147192_d().func_96531_f());
               }

               if(p_180525_2_.length >= 4) {
                  return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
               }
            } else {
               if(p_180525_2_[1].equalsIgnoreCase("leave")) {
                  return func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
               }

               if(!p_180525_2_[1].equalsIgnoreCase("empty") && !p_180525_2_[1].equalsIgnoreCase("list") && !p_180525_2_[1].equalsIgnoreCase("remove")) {
                  if(p_180525_2_[1].equalsIgnoreCase("option")) {
                     if(p_180525_2_.length == 3) {
                        return func_175762_a(p_180525_2_, this.func_147192_d().func_96531_f());
                     }

                     if(p_180525_2_.length == 4) {
                        return func_71530_a(p_180525_2_, new String[]{"color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility"});
                     }

                     if(p_180525_2_.length == 5) {
                        if(p_180525_2_[3].equalsIgnoreCase("color")) {
                           return func_175762_a(p_180525_2_, EnumChatFormatting.func_96296_a(true, false));
                        }

                        if(p_180525_2_[3].equalsIgnoreCase("nametagVisibility") || p_180525_2_[3].equalsIgnoreCase("deathMessageVisibility")) {
                           return func_71530_a(p_180525_2_, Team.EnumVisible.func_178825_a());
                        }

                        if(p_180525_2_[3].equalsIgnoreCase("friendlyfire") || p_180525_2_[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                           return func_71530_a(p_180525_2_, new String[]{"true", "false"});
                        }
                     }
                  }
               } else if(p_180525_2_.length == 3) {
                  return func_175762_a(p_180525_2_, this.func_147192_d().func_96531_f());
               }
            }
         }

         return null;
      }
   }

   protected List<String> func_147184_a(boolean p_147184_1_) {
      Collection<ScoreObjective> collection = this.func_147192_d().func_96514_c();
      List<String> list = Lists.<String>newArrayList();

      for(ScoreObjective scoreobjective : collection) {
         if(!p_147184_1_ || !scoreobjective.func_96680_c().func_96637_b()) {
            list.add(scoreobjective.func_96679_b());
         }
      }

      return list;
   }

   protected List<String> func_175782_e() {
      Collection<ScoreObjective> collection = this.func_147192_d().func_96514_c();
      List<String> list = Lists.<String>newArrayList();

      for(ScoreObjective scoreobjective : collection) {
         if(scoreobjective.func_96680_c() == IScoreObjectiveCriteria.field_178791_c) {
            list.add(scoreobjective.func_96679_b());
         }
      }

      return list;
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return !p_82358_1_[0].equalsIgnoreCase("players")?(p_82358_1_[0].equalsIgnoreCase("teams")?p_82358_2_ == 2:false):(p_82358_1_.length > 1 && p_82358_1_[1].equalsIgnoreCase("operation")?p_82358_2_ == 2 || p_82358_2_ == 5:p_82358_2_ == 2);
   }
}
