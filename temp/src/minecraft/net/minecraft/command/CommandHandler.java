package net.minecraft.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandHandler implements ICommandManager {
   private static final Logger field_147175_a = LogManager.getLogger();
   private final Map<String, ICommand> field_71562_a = Maps.<String, ICommand>newHashMap();
   private final Set<ICommand> field_71561_b = Sets.<ICommand>newHashSet();

   public int func_71556_a(ICommandSender p_71556_1_, String p_71556_2_) {
      p_71556_2_ = p_71556_2_.trim();
      if(p_71556_2_.startsWith("/")) {
         p_71556_2_ = p_71556_2_.substring(1);
      }

      String[] astring = p_71556_2_.split(" ");
      String s = astring[0];
      astring = func_71559_a(astring);
      ICommand icommand = (ICommand)this.field_71562_a.get(s);
      int i = this.func_82370_a(icommand, astring);
      int j = 0;
      if(icommand == null) {
         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.generic.notFound", new Object[0]);
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_71556_1_.func_145747_a(chatcomponenttranslation);
      } else if(icommand.func_71519_b(p_71556_1_)) {
         if(i > -1) {
            List<Entity> list = PlayerSelector.<Entity>func_179656_b(p_71556_1_, astring[i], Entity.class);
            String s1 = astring[i];
            p_71556_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, list.size());

            for(Entity entity : list) {
               astring[i] = entity.func_110124_au().toString();
               if(this.func_175786_a(p_71556_1_, astring, icommand, p_71556_2_)) {
                  ++j;
               }
            }

            astring[i] = s1;
         } else {
            p_71556_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, 1);
            if(this.func_175786_a(p_71556_1_, astring, icommand, p_71556_2_)) {
               ++j;
            }
         }
      } else {
         ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
         chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_71556_1_.func_145747_a(chatcomponenttranslation1);
      }

      p_71556_1_.func_174794_a(CommandResultStats.Type.SUCCESS_COUNT, j);
      return j;
   }

   protected boolean func_175786_a(ICommandSender p_175786_1_, String[] p_175786_2_, ICommand p_175786_3_, String p_175786_4_) {
      try {
         p_175786_3_.func_71515_b(p_175786_1_, p_175786_2_);
         return true;
      } catch (WrongUsageException wrongusageexception) {
         ChatComponentTranslation chatcomponenttranslation2 = new ChatComponentTranslation("commands.generic.usage", new Object[]{new ChatComponentTranslation(wrongusageexception.getMessage(), wrongusageexception.func_74844_a())});
         chatcomponenttranslation2.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_175786_1_.func_145747_a(chatcomponenttranslation2);
      } catch (CommandException commandexception) {
         ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation(commandexception.getMessage(), commandexception.func_74844_a());
         chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_175786_1_.func_145747_a(chatcomponenttranslation1);
      } catch (Throwable var9) {
         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.generic.exception", new Object[0]);
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_175786_1_.func_145747_a(chatcomponenttranslation);
         field_147175_a.warn("Couldn\'t process command: \'" + p_175786_4_ + "\'");
      }

      return false;
   }

   public ICommand func_71560_a(ICommand p_71560_1_) {
      this.field_71562_a.put(p_71560_1_.func_71517_b(), p_71560_1_);
      this.field_71561_b.add(p_71560_1_);

      for(String s : p_71560_1_.func_71514_a()) {
         ICommand icommand = (ICommand)this.field_71562_a.get(s);
         if(icommand == null || !icommand.func_71517_b().equals(s)) {
            this.field_71562_a.put(s, p_71560_1_);
         }
      }

      return p_71560_1_;
   }

   private static String[] func_71559_a(String[] p_71559_0_) {
      String[] astring = new String[p_71559_0_.length - 1];
      System.arraycopy(p_71559_0_, 1, astring, 0, p_71559_0_.length - 1);
      return astring;
   }

   public List<String> func_180524_a(ICommandSender p_180524_1_, String p_180524_2_, BlockPos p_180524_3_) {
      String[] astring = p_180524_2_.split(" ", -1);
      String s = astring[0];
      if(astring.length == 1) {
         List<String> list = Lists.<String>newArrayList();

         for(Entry<String, ICommand> entry : this.field_71562_a.entrySet()) {
            if(CommandBase.func_71523_a(s, (String)entry.getKey()) && ((ICommand)entry.getValue()).func_71519_b(p_180524_1_)) {
               list.add(entry.getKey());
            }
         }

         return list;
      } else {
         if(astring.length > 1) {
            ICommand icommand = (ICommand)this.field_71562_a.get(s);
            if(icommand != null && icommand.func_71519_b(p_180524_1_)) {
               return icommand.func_180525_a(p_180524_1_, func_71559_a(astring), p_180524_3_);
            }
         }

         return null;
      }
   }

   public List<ICommand> func_71557_a(ICommandSender p_71557_1_) {
      List<ICommand> list = Lists.<ICommand>newArrayList();

      for(ICommand icommand : this.field_71561_b) {
         if(icommand.func_71519_b(p_71557_1_)) {
            list.add(icommand);
         }
      }

      return list;
   }

   public Map<String, ICommand> func_71555_a() {
      return this.field_71562_a;
   }

   private int func_82370_a(ICommand p_82370_1_, String[] p_82370_2_) {
      if(p_82370_1_ == null) {
         return -1;
      } else {
         for(int i = 0; i < p_82370_2_.length; ++i) {
            if(p_82370_1_.func_82358_a(p_82370_2_, i) && PlayerSelector.func_82377_a(p_82370_2_[i])) {
               return i;
            }
         }

         return -1;
      }
   }
}
