package net.minecraft.command;

import com.google.gson.JsonParseException;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandTitle extends CommandBase {
   private static final Logger field_175774_a = LogManager.getLogger();

   public String func_71517_b() {
      return "title";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.title.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.title.usage", new Object[0]);
      } else {
         if(p_71515_2_.length < 3) {
            if("title".equals(p_71515_2_[1]) || "subtitle".equals(p_71515_2_[1])) {
               throw new WrongUsageException("commands.title.usage.title", new Object[0]);
            }

            if("times".equals(p_71515_2_[1])) {
               throw new WrongUsageException("commands.title.usage.times", new Object[0]);
            }
         }

         EntityPlayerMP entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[0]);
         S45PacketTitle.Type s45packettitle$type = S45PacketTitle.Type.func_179969_a(p_71515_2_[1]);
         if(s45packettitle$type != S45PacketTitle.Type.CLEAR && s45packettitle$type != S45PacketTitle.Type.RESET) {
            if(s45packettitle$type == S45PacketTitle.Type.TIMES) {
               if(p_71515_2_.length != 5) {
                  throw new WrongUsageException("commands.title.usage", new Object[0]);
               } else {
                  int i = func_175755_a(p_71515_2_[2]);
                  int j = func_175755_a(p_71515_2_[3]);
                  int k = func_175755_a(p_71515_2_[4]);
                  S45PacketTitle s45packettitle2 = new S45PacketTitle(i, j, k);
                  entityplayermp.field_71135_a.func_147359_a(s45packettitle2);
                  func_152373_a(p_71515_1_, this, "commands.title.success", new Object[0]);
               }
            } else if(p_71515_2_.length < 3) {
               throw new WrongUsageException("commands.title.usage", new Object[0]);
            } else {
               String s = func_180529_a(p_71515_2_, 2);

               IChatComponent ichatcomponent;
               try {
                  ichatcomponent = IChatComponent.Serializer.func_150699_a(s);
               } catch (JsonParseException jsonparseexception) {
                  Throwable throwable = ExceptionUtils.getRootCause(jsonparseexception);
                  throw new SyntaxErrorException("commands.tellraw.jsonException", new Object[]{throwable == null?"":throwable.getMessage()});
               }

               S45PacketTitle s45packettitle1 = new S45PacketTitle(s45packettitle$type, ChatComponentProcessor.func_179985_a(p_71515_1_, ichatcomponent, entityplayermp));
               entityplayermp.field_71135_a.func_147359_a(s45packettitle1);
               func_152373_a(p_71515_1_, this, "commands.title.success", new Object[0]);
            }
         } else if(p_71515_2_.length != 2) {
            throw new WrongUsageException("commands.title.usage", new Object[0]);
         } else {
            S45PacketTitle s45packettitle = new S45PacketTitle(s45packettitle$type, (IChatComponent)null);
            entityplayermp.field_71135_a.func_147359_a(s45packettitle);
            func_152373_a(p_71515_1_, this, "commands.title.success", new Object[0]);
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()):(p_180525_2_.length == 2?func_71530_a(p_180525_2_, S45PacketTitle.Type.func_179971_a()):null);
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
