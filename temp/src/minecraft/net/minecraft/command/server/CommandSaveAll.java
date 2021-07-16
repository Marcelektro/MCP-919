package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandSaveAll extends CommandBase {
   public String func_71517_b() {
      return "save-all";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.save.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
      p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.save.start", new Object[0]));
      if(minecraftserver.func_71203_ab() != null) {
         minecraftserver.func_71203_ab().func_72389_g();
      }

      try {
         for(int i = 0; i < minecraftserver.field_71305_c.length; ++i) {
            if(minecraftserver.field_71305_c[i] != null) {
               WorldServer worldserver = minecraftserver.field_71305_c[i];
               boolean flag = worldserver.field_73058_d;
               worldserver.field_73058_d = false;
               worldserver.func_73044_a(true, (IProgressUpdate)null);
               worldserver.field_73058_d = flag;
            }
         }

         if(p_71515_2_.length > 0 && "flush".equals(p_71515_2_[0])) {
            p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.save.flushStart", new Object[0]));

            for(int j = 0; j < minecraftserver.field_71305_c.length; ++j) {
               if(minecraftserver.field_71305_c[j] != null) {
                  WorldServer worldserver1 = minecraftserver.field_71305_c[j];
                  boolean flag1 = worldserver1.field_73058_d;
                  worldserver1.field_73058_d = false;
                  worldserver1.func_104140_m();
                  worldserver1.field_73058_d = flag1;
               }
            }

            p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.save.flushEnd", new Object[0]));
         }
      } catch (MinecraftException minecraftexception) {
         func_152373_a(p_71515_1_, this, "commands.save.failed", new Object[]{minecraftexception.getMessage()});
         return;
      }

      func_152373_a(p_71515_1_, this, "commands.save.success", new Object[0]);
   }
}
