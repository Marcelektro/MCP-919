package net.minecraft.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class CommandHelp extends CommandBase {
   public String func_71517_b() {
      return "help";
   }

   public int func_82362_a() {
      return 0;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.help.usage";
   }

   public List<String> func_71514_a() {
      return Arrays.<String>asList(new String[]{"?"});
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      List<ICommand> list = this.func_71534_d(p_71515_1_);
      int i = 7;
      int j = (list.size() - 1) / 7;
      int k = 0;

      try {
         k = p_71515_2_.length == 0?0:func_175764_a(p_71515_2_[0], 1, j + 1) - 1;
      } catch (NumberInvalidException numberinvalidexception) {
         Map<String, ICommand> map = this.func_71535_c();
         ICommand icommand = (ICommand)map.get(p_71515_2_[0]);
         if(icommand != null) {
            throw new WrongUsageException(icommand.func_71518_a(p_71515_1_), new Object[0]);
         }

         if(MathHelper.func_82715_a(p_71515_2_[0], -1) != -1) {
            throw numberinvalidexception;
         }

         throw new CommandNotFoundException();
      }

      int l = Math.min((k + 1) * 7, list.size());
      ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.help.header", new Object[]{Integer.valueOf(k + 1), Integer.valueOf(j + 1)});
      chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.DARK_GREEN);
      p_71515_1_.func_145747_a(chatcomponenttranslation1);

      for(int i1 = k * 7; i1 < l; ++i1) {
         ICommand icommand1 = (ICommand)list.get(i1);
         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(icommand1.func_71518_a(p_71515_1_), new Object[0]);
         chatcomponenttranslation.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + icommand1.func_71517_b() + " "));
         p_71515_1_.func_145747_a(chatcomponenttranslation);
      }

      if(k == 0 && p_71515_1_ instanceof EntityPlayer) {
         ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("commands.help.footer", new Object[0]);
         chatcomponenttranslation2.func_150256_b().func_150238_a(EnumChatFormatting.GREEN);
         p_71515_1_.func_145747_a(chatcomponenttranslation2);
      }

   }

   protected List<ICommand> func_71534_d(ICommandSender p_71534_1_) {
      List<ICommand> list = MinecraftServer.func_71276_C().func_71187_D().func_71557_a(p_71534_1_);
      Collections.sort(list);
      return list;
   }

   protected Map<String, ICommand> func_71535_c() {
      return MinecraftServer.func_71276_C().func_71187_D().func_71555_a();
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      if(p_180525_2_.length == 1) {
         Set<String> set = this.func_71535_c().keySet();
         return func_71530_a(p_180525_2_, (String[])set.toArray(new String[set.size()]));
      } else {
         return null;
      }
   }
}
