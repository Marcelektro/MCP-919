package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class GuiUtilRenderComponents {
   public static String func_178909_a(String p_178909_0_, boolean p_178909_1_) {
      return !p_178909_1_ && !Minecraft.func_71410_x().field_71474_y.field_74344_o?EnumChatFormatting.func_110646_a(p_178909_0_):p_178909_0_;
   }

   public static List<IChatComponent> func_178908_a(IChatComponent p_178908_0_, int p_178908_1_, FontRenderer p_178908_2_, boolean p_178908_3_, boolean p_178908_4_) {
      int i = 0;
      IChatComponent ichatcomponent = new ChatComponentText("");
      List<IChatComponent> list = Lists.<IChatComponent>newArrayList();
      List<IChatComponent> list1 = Lists.newArrayList(p_178908_0_);

      for(int j = 0; j < ((List)list1).size(); ++j) {
         IChatComponent ichatcomponent1 = (IChatComponent)list1.get(j);
         String s = ichatcomponent1.func_150261_e();
         boolean flag = false;
         if(s.contains("\n")) {
            int k = s.indexOf(10);
            String s1 = s.substring(k + 1);
            s = s.substring(0, k + 1);
            ChatComponentText chatcomponenttext = new ChatComponentText(s1);
            chatcomponenttext.func_150255_a(ichatcomponent1.func_150256_b().func_150232_l());
            list1.add(j + 1, chatcomponenttext);
            flag = true;
         }

         String s4 = func_178909_a(ichatcomponent1.func_150256_b().func_150218_j() + s, p_178908_4_);
         String s5 = s4.endsWith("\n")?s4.substring(0, s4.length() - 1):s4;
         int i1 = p_178908_2_.func_78256_a(s5);
         ChatComponentText chatcomponenttext1 = new ChatComponentText(s5);
         chatcomponenttext1.func_150255_a(ichatcomponent1.func_150256_b().func_150232_l());
         if(i + i1 > p_178908_1_) {
            String s2 = p_178908_2_.func_78262_a(s4, p_178908_1_ - i, false);
            String s3 = s2.length() < s4.length()?s4.substring(s2.length()):null;
            if(s3 != null && s3.length() > 0) {
               int l = s2.lastIndexOf(" ");
               if(l >= 0 && p_178908_2_.func_78256_a(s4.substring(0, l)) > 0) {
                  s2 = s4.substring(0, l);
                  if(p_178908_3_) {
                     ++l;
                  }

                  s3 = s4.substring(l);
               } else if(i > 0 && !s4.contains(" ")) {
                  s2 = "";
                  s3 = s4;
               }

               ChatComponentText chatcomponenttext2 = new ChatComponentText(s3);
               chatcomponenttext2.func_150255_a(ichatcomponent1.func_150256_b().func_150232_l());
               list1.add(j + 1, chatcomponenttext2);
            }

            i1 = p_178908_2_.func_78256_a(s2);
            chatcomponenttext1 = new ChatComponentText(s2);
            chatcomponenttext1.func_150255_a(ichatcomponent1.func_150256_b().func_150232_l());
            flag = true;
         }

         if(i + i1 <= p_178908_1_) {
            i += i1;
            ichatcomponent.func_150257_a(chatcomponenttext1);
         } else {
            flag = true;
         }

         if(flag) {
            list.add(ichatcomponent);
            i = 0;
            ichatcomponent = new ChatComponentText("");
         }
      }

      list.add(ichatcomponent);
      return list;
   }
}
