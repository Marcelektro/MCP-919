package net.minecraft.command.server;

import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class CommandMessageRaw extends CommandBase {
   public String func_71517_b() {
      return "tellraw";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.tellraw.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.tellraw.usage", new Object[0]);
      } else {
         EntityPlayer entityplayer = func_82359_c(p_71515_1_, p_71515_2_[0]);
         String s = func_180529_a(p_71515_2_, 1);

         try {
            IChatComponent ichatcomponent = IChatComponent.Serializer.func_150699_a(s);
            entityplayer.func_145747_a(ChatComponentProcessor.func_179985_a(p_71515_1_, ichatcomponent, entityplayer));
         } catch (JsonParseException jsonparseexception) {
            Throwable throwable = ExceptionUtils.getRootCause(jsonparseexception);
            throw new SyntaxErrorException("commands.tellraw.jsonException", new Object[]{throwable == null?"":throwable.getMessage()});
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()):null;
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
