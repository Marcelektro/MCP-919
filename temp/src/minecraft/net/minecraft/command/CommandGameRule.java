package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;

public class CommandGameRule extends CommandBase {
   public String func_71517_b() {
      return "gamerule";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.gamerule.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      GameRules gamerules = this.func_82366_d();
      String s = p_71515_2_.length > 0?p_71515_2_[0]:"";
      String s1 = p_71515_2_.length > 1?func_180529_a(p_71515_2_, 1):"";
      switch(p_71515_2_.length) {
      case 0:
         p_71515_1_.func_145747_a(new ChatComponentText(func_71527_a(gamerules.func_82763_b())));
         break;
      case 1:
         if(!gamerules.func_82765_e(s)) {
            throw new CommandException("commands.gamerule.norule", new Object[]{s});
         }

         String s2 = gamerules.func_82767_a(s);
         p_71515_1_.func_145747_a((new ChatComponentText(s)).func_150258_a(" = ").func_150258_a(s2));
         p_71515_1_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, gamerules.func_180263_c(s));
         break;
      default:
         if(gamerules.func_180264_a(s, GameRules.ValueType.BOOLEAN_VALUE) && !"true".equals(s1) && !"false".equals(s1)) {
            throw new CommandException("commands.generic.boolean.invalid", new Object[]{s1});
         }

         gamerules.func_82764_b(s, s1);
         func_175773_a(gamerules, s);
         func_152373_a(p_71515_1_, this, "commands.gamerule.success", new Object[0]);
      }

   }

   public static void func_175773_a(GameRules p_175773_0_, String p_175773_1_) {
      if("reducedDebugInfo".equals(p_175773_1_)) {
         byte b0 = (byte)(p_175773_0_.func_82766_b(p_175773_1_)?22:23);

         for(EntityPlayerMP entityplayermp : MinecraftServer.func_71276_C().func_71203_ab().func_181057_v()) {
            entityplayermp.field_71135_a.func_147359_a(new S19PacketEntityStatus(entityplayermp, b0));
         }
      }

   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if(p_180525_2_.length == 1) {
         return func_71530_a(p_180525_2_, this.func_82366_d().func_82763_b());
      } else {
         if(p_180525_2_.length == 2) {
            GameRules gamerules = this.func_82366_d();
            if(gamerules.func_180264_a(p_180525_2_[0], GameRules.ValueType.BOOLEAN_VALUE)) {
               return func_71530_a(p_180525_2_, new String[]{"true", "false"});
            }
         }

         return null;
      }
   }

   private GameRules func_82366_d() {
      return MinecraftServer.func_71276_C().func_71218_a(0).func_82736_K();
   }
}
