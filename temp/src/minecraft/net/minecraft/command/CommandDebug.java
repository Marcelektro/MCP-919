package net.minecraft.command;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandDebug extends CommandBase {
   private static final Logger field_147208_a = LogManager.getLogger();
   private long field_147206_b;
   private int field_147207_c;

   public String func_71517_b() {
      return "debug";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.debug.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.debug.usage", new Object[0]);
      } else {
         if(p_71515_2_[0].equals("start")) {
            if(p_71515_2_.length != 1) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            func_152373_a(p_71515_1_, this, "commands.debug.start", new Object[0]);
            MinecraftServer.func_71276_C().func_71223_ag();
            this.field_147206_b = MinecraftServer.func_130071_aq();
            this.field_147207_c = MinecraftServer.func_71276_C().func_71259_af();
         } else {
            if(!p_71515_2_[0].equals("stop")) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            if(p_71515_2_.length != 1) {
               throw new WrongUsageException("commands.debug.usage", new Object[0]);
            }

            if(!MinecraftServer.func_71276_C().field_71304_b.field_76327_a) {
               throw new CommandException("commands.debug.notStarted", new Object[0]);
            }

            long i = MinecraftServer.func_130071_aq();
            int j = MinecraftServer.func_71276_C().func_71259_af();
            long k = i - this.field_147206_b;
            int l = j - this.field_147207_c;
            this.func_147205_a(k, l);
            MinecraftServer.func_71276_C().field_71304_b.field_76327_a = false;
            func_152373_a(p_71515_1_, this, "commands.debug.stop", new Object[]{Float.valueOf((float)k / 1000.0F), Integer.valueOf(l)});
         }

      }
   }

   private void func_147205_a(long p_147205_1_, int p_147205_3_) {
      File file1 = new File(MinecraftServer.func_71276_C().func_71209_f("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
      file1.getParentFile().mkdirs();

      try {
         FileWriter filewriter = new FileWriter(file1);
         filewriter.write(this.func_147204_b(p_147205_1_, p_147205_3_));
         filewriter.close();
      } catch (Throwable throwable) {
         field_147208_a.error("Could not save profiler results to " + file1, throwable);
      }

   }

   private String func_147204_b(long p_147204_1_, int p_147204_3_) {
      StringBuilder stringbuilder = new StringBuilder();
      stringbuilder.append("---- Minecraft Profiler Results ----\n");
      stringbuilder.append("// ");
      stringbuilder.append(func_147203_d());
      stringbuilder.append("\n\n");
      stringbuilder.append("Time span: ").append(p_147204_1_).append(" ms\n");
      stringbuilder.append("Tick span: ").append(p_147204_3_).append(" ticks\n");
      stringbuilder.append("// This is approximately ").append(String.format("%.2f", new Object[]{Float.valueOf((float)p_147204_3_ / ((float)p_147204_1_ / 1000.0F))})).append(" ticks per second. It should be ").append((int)20).append(" ticks per second\n\n");
      stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
      this.func_147202_a(0, "root", stringbuilder);
      stringbuilder.append("--- END PROFILE DUMP ---\n\n");
      return stringbuilder.toString();
   }

   private void func_147202_a(int p_147202_1_, String p_147202_2_, StringBuilder p_147202_3_) {
      List<Profiler.Result> list = MinecraftServer.func_71276_C().field_71304_b.func_76321_b(p_147202_2_);
      if(list != null && list.size() >= 3) {
         for(int i = 1; i < list.size(); ++i) {
            Profiler.Result profiler$result = (Profiler.Result)list.get(i);
            p_147202_3_.append(String.format("[%02d] ", new Object[]{Integer.valueOf(p_147202_1_)}));

            for(int j = 0; j < p_147202_1_; ++j) {
               p_147202_3_.append(" ");
            }

            p_147202_3_.append(profiler$result.field_76331_c).append(" - ").append(String.format("%.2f", new Object[]{Double.valueOf(profiler$result.field_76332_a)})).append("%/").append(String.format("%.2f", new Object[]{Double.valueOf(profiler$result.field_76330_b)})).append("%\n");
            if(!profiler$result.field_76331_c.equals("unspecified")) {
               try {
                  this.func_147202_a(p_147202_1_ + 1, p_147202_2_ + "." + profiler$result.field_76331_c, p_147202_3_);
               } catch (Exception exception) {
                  p_147202_3_.append("[[ EXCEPTION ").append((Object)exception).append(" ]]");
               }
            }
         }

      }
   }

   private static String func_147203_d() {
      String[] astring = new String[]{"Shiny numbers!", "Am I not running fast enough? :(", "I\'m working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it\'ll have more motivation to work faster! Poor server."};

      try {
         return astring[(int)(System.nanoTime() % (long)astring.length)];
      } catch (Throwable var2) {
         return "Witty comment unavailable :(";
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, new String[]{"start", "stop"}):null;
   }
}
