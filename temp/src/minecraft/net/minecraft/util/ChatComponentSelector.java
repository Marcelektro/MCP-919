package net.minecraft.util;

import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.IChatComponent;

public class ChatComponentSelector extends ChatComponentStyle {
   private final String field_179993_b;

   public ChatComponentSelector(String p_i45996_1_) {
      this.field_179993_b = p_i45996_1_;
   }

   public String func_179992_g() {
      return this.field_179993_b;
   }

   public String func_150261_e() {
      return this.field_179993_b;
   }

   public ChatComponentSelector func_150259_f() {
      ChatComponentSelector chatcomponentselector = new ChatComponentSelector(this.field_179993_b);
      chatcomponentselector.func_150255_a(this.func_150256_b().func_150232_l());

      for(IChatComponent ichatcomponent : this.func_150253_a()) {
         chatcomponentselector.func_150257_a(ichatcomponent.func_150259_f());
      }

      return chatcomponentselector;
   }

   public boolean equals(Object p_equals_1_) {
      if(this == p_equals_1_) {
         return true;
      } else if(!(p_equals_1_ instanceof ChatComponentSelector)) {
         return false;
      } else {
         ChatComponentSelector chatcomponentselector = (ChatComponentSelector)p_equals_1_;
         return this.field_179993_b.equals(chatcomponentselector.field_179993_b) && super.equals(p_equals_1_);
      }
   }

   public String toString() {
      return "SelectorComponent{pattern=\'" + this.field_179993_b + '\'' + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
   }
}
