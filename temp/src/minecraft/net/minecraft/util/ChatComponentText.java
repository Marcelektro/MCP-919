package net.minecraft.util;

import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.IChatComponent;

public class ChatComponentText extends ChatComponentStyle {
   private final String field_150267_b;

   public ChatComponentText(String p_i45159_1_) {
      this.field_150267_b = p_i45159_1_;
   }

   public String func_150265_g() {
      return this.field_150267_b;
   }

   public String func_150261_e() {
      return this.field_150267_b;
   }

   public ChatComponentText func_150259_f() {
      ChatComponentText chatcomponenttext = new ChatComponentText(this.field_150267_b);
      chatcomponenttext.func_150255_a(this.func_150256_b().func_150232_l());

      for(IChatComponent ichatcomponent : this.func_150253_a()) {
         chatcomponenttext.func_150257_a(ichatcomponent.func_150259_f());
      }

      return chatcomponenttext;
   }

   public boolean equals(Object p_equals_1_) {
      if(this == p_equals_1_) {
         return true;
      } else if(!(p_equals_1_ instanceof ChatComponentText)) {
         return false;
      } else {
         ChatComponentText chatcomponenttext = (ChatComponentText)p_equals_1_;
         return this.field_150267_b.equals(chatcomponenttext.func_150265_g()) && super.equals(p_equals_1_);
      }
   }

   public String toString() {
      return "TextComponent{text=\'" + this.field_150267_b + '\'' + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
   }
}
