package net.minecraft.util;

import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentScore;
import net.minecraft.util.ChatComponentSelector;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class ChatComponentProcessor {
   public static IChatComponent func_179985_a(ICommandSender p_179985_0_, IChatComponent p_179985_1_, Entity p_179985_2_) throws CommandException {
      IChatComponent ichatcomponent = null;
      if(p_179985_1_ instanceof ChatComponentScore) {
         ChatComponentScore chatcomponentscore = (ChatComponentScore)p_179985_1_;
         String s = chatcomponentscore.func_179995_g();
         if(PlayerSelector.func_82378_b(s)) {
            List<Entity> list = PlayerSelector.<Entity>func_179656_b(p_179985_0_, s, Entity.class);
            if(list.size() != 1) {
               throw new EntityNotFoundException();
            }

            s = ((Entity)list.get(0)).func_70005_c_();
         }

         ichatcomponent = p_179985_2_ != null && s.equals("*")?new ChatComponentScore(p_179985_2_.func_70005_c_(), chatcomponentscore.func_179994_h()):new ChatComponentScore(s, chatcomponentscore.func_179994_h());
         ((ChatComponentScore)ichatcomponent).func_179997_b(chatcomponentscore.func_150261_e());
      } else if(p_179985_1_ instanceof ChatComponentSelector) {
         String s1 = ((ChatComponentSelector)p_179985_1_).func_179992_g();
         ichatcomponent = PlayerSelector.func_150869_b(p_179985_0_, s1);
         if(ichatcomponent == null) {
            ichatcomponent = new ChatComponentText("");
         }
      } else if(p_179985_1_ instanceof ChatComponentText) {
         ichatcomponent = new ChatComponentText(((ChatComponentText)p_179985_1_).func_150265_g());
      } else {
         if(!(p_179985_1_ instanceof ChatComponentTranslation)) {
            return p_179985_1_;
         }

         Object[] aobject = ((ChatComponentTranslation)p_179985_1_).func_150271_j();

         for(int i = 0; i < aobject.length; ++i) {
            Object object = aobject[i];
            if(object instanceof IChatComponent) {
               aobject[i] = func_179985_a(p_179985_0_, (IChatComponent)object, p_179985_2_);
            }
         }

         ichatcomponent = new ChatComponentTranslation(((ChatComponentTranslation)p_179985_1_).func_150268_i(), aobject);
      }

      ChatStyle chatstyle = p_179985_1_.func_150256_b();
      if(chatstyle != null) {
         ichatcomponent.func_150255_a(chatstyle.func_150232_l());
      }

      for(IChatComponent ichatcomponent1 : p_179985_1_.func_150253_a()) {
         ichatcomponent.func_150257_a(func_179985_a(p_179985_0_, ichatcomponent1, p_179985_2_));
      }

      return ichatcomponent;
   }
}
