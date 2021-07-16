package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandSetDefaultSpawnpoint extends CommandBase {
   public String func_71517_b() {
      return "setworldspawn";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.setworldspawn.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      BlockPos blockpos;
      if(p_71515_2_.length == 0) {
         blockpos = func_71521_c(p_71515_1_).func_180425_c();
      } else {
         if(p_71515_2_.length != 3 || p_71515_1_.func_130014_f_() == null) {
            throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
         }

         blockpos = func_175757_a(p_71515_1_, p_71515_2_, 0, true);
      }

      p_71515_1_.func_130014_f_().func_175652_B(blockpos);
      MinecraftServer.func_71276_C().func_71203_ab().func_148540_a(new S05PacketSpawnPosition(blockpos));
      func_152373_a(p_71515_1_, this, "commands.setworldspawn.success", new Object[]{Integer.valueOf(blockpos.func_177958_n()), Integer.valueOf(blockpos.func_177956_o()), Integer.valueOf(blockpos.func_177952_p())});
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length > 0 && p_180525_2_.length <= 3?func_175771_a(p_180525_2_, 0, p_180525_3_):null;
   }
}
