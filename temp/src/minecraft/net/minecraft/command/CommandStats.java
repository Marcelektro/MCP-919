package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandStats extends CommandBase {
   public String func_71517_b() {
      return "stats";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.stats.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.stats.usage", new Object[0]);
      } else {
         boolean flag;
         if(p_71515_2_[0].equals("entity")) {
            flag = false;
         } else {
            if(!p_71515_2_[0].equals("block")) {
               throw new WrongUsageException("commands.stats.usage", new Object[0]);
            }

            flag = true;
         }

         int i;
         if(flag) {
            if(p_71515_2_.length < 5) {
               throw new WrongUsageException("commands.stats.block.usage", new Object[0]);
            }

            i = 4;
         } else {
            if(p_71515_2_.length < 3) {
               throw new WrongUsageException("commands.stats.entity.usage", new Object[0]);
            }

            i = 2;
         }

         String s = p_71515_2_[i++];
         if("set".equals(s)) {
            if(p_71515_2_.length < i + 3) {
               if(i == 5) {
                  throw new WrongUsageException("commands.stats.block.set.usage", new Object[0]);
               }

               throw new WrongUsageException("commands.stats.entity.set.usage", new Object[0]);
            }
         } else {
            if(!"clear".equals(s)) {
               throw new WrongUsageException("commands.stats.usage", new Object[0]);
            }

            if(p_71515_2_.length < i + 1) {
               if(i == 5) {
                  throw new WrongUsageException("commands.stats.block.clear.usage", new Object[0]);
               }

               throw new WrongUsageException("commands.stats.entity.clear.usage", new Object[0]);
            }
         }

         CommandResultStats.Type commandresultstats$type = CommandResultStats.Type.func_179635_a(p_71515_2_[i++]);
         if(commandresultstats$type == null) {
            throw new CommandException("commands.stats.failed", new Object[0]);
         } else {
            World world = p_71515_1_.func_130014_f_();
            CommandResultStats commandresultstats;
            if(flag) {
               BlockPos blockpos = func_175757_a(p_71515_1_, p_71515_2_, 1, false);
               TileEntity tileentity = world.func_175625_s(blockpos);
               if(tileentity == null) {
                  throw new CommandException("commands.stats.noCompatibleBlock", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p())});
               }

               if(tileentity instanceof TileEntityCommandBlock) {
                  commandresultstats = ((TileEntityCommandBlock)tileentity).func_175124_c();
               } else {
                  if(!(tileentity instanceof TileEntitySign)) {
                     throw new CommandException("commands.stats.noCompatibleBlock", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p())});
                  }

                  commandresultstats = ((TileEntitySign)tileentity).func_174880_d();
               }
            } else {
               Entity entity = func_175768_b(p_71515_1_, p_71515_2_[1]);
               commandresultstats = entity.func_174807_aT();
            }

            if("set".equals(s)) {
               String s1 = p_71515_2_[i++];
               String s2 = p_71515_2_[i];
               if(s1.length() == 0 || s2.length() == 0) {
                  throw new CommandException("commands.stats.failed", new Object[0]);
               }

               CommandResultStats.func_179667_a(commandresultstats, commandresultstats$type, s1, s2);
               func_152373_a(p_71515_1_, this, "commands.stats.success", new Object[]{commandresultstats$type.func_179637_b(), s2, s1});
            } else if("clear".equals(s)) {
               CommandResultStats.func_179667_a(commandresultstats, commandresultstats$type, (String)null, (String)null);
               func_152373_a(p_71515_1_, this, "commands.stats.cleared", new Object[]{commandresultstats$type.func_179637_b()});
            }

            if(flag) {
               BlockPos blockpos1 = func_175757_a(p_71515_1_, p_71515_2_, 1, false);
               TileEntity tileentity1 = world.func_175625_s(blockpos1);
               tileentity1.func_70296_d();
            }

         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, new String[]{"entity", "block"}):(p_180525_2_.length == 2 && p_180525_2_[0].equals("entity")?func_71530_a(p_180525_2_, this.func_175776_d()):(p_180525_2_.length >= 2 && p_180525_2_.length <= 4 && p_180525_2_[0].equals("block")?func_175771_a(p_180525_2_, 1, p_180525_3_):((p_180525_2_.length != 3 || !p_180525_2_[0].equals("entity")) && (p_180525_2_.length != 5 || !p_180525_2_[0].equals("block"))?((p_180525_2_.length != 4 || !p_180525_2_[0].equals("entity")) && (p_180525_2_.length != 6 || !p_180525_2_[0].equals("block"))?((p_180525_2_.length != 6 || !p_180525_2_[0].equals("entity")) && (p_180525_2_.length != 8 || !p_180525_2_[0].equals("block"))?null:func_175762_a(p_180525_2_, this.func_175777_e())):func_71530_a(p_180525_2_, CommandResultStats.Type.func_179634_c())):func_71530_a(p_180525_2_, new String[]{"set", "clear"}))));
   }

   protected String[] func_175776_d() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   protected List<String> func_175777_e() {
      Collection<ScoreObjective> collection = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U().func_96514_c();
      List<String> list = Lists.<String>newArrayList();

      for(ScoreObjective scoreobjective : collection) {
         if(!scoreobjective.func_96680_c().func_96637_b()) {
            list.add(scoreobjective.func_96679_b());
         }
      }

      return list;
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_1_.length > 0 && p_82358_1_[0].equals("entity") && p_82358_2_ == 1;
   }
}
