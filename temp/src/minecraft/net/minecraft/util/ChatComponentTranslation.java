package net.minecraft.util;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslationFormatException;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class ChatComponentTranslation extends ChatComponentStyle {
   private final String field_150276_d;
   private final Object[] field_150277_e;
   private final Object field_150274_f = new Object();
   private long field_150275_g = -1L;
   List<IChatComponent> field_150278_b = Lists.<IChatComponent>newArrayList();
   public static final Pattern field_150279_c = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

   public ChatComponentTranslation(String p_i45160_1_, Object... p_i45160_2_) {
      this.field_150276_d = p_i45160_1_;
      this.field_150277_e = p_i45160_2_;

      for(Object object : p_i45160_2_) {
         if(object instanceof IChatComponent) {
            ((IChatComponent)object).func_150256_b().func_150221_a(this.func_150256_b());
         }
      }

   }

   synchronized void func_150270_g() {
      synchronized(this.field_150274_f) {
         long i = StatCollector.func_150827_a();
         if(i == this.field_150275_g) {
            return;
         }

         this.field_150275_g = i;
         this.field_150278_b.clear();
      }

      try {
         this.func_150269_b(StatCollector.func_74838_a(this.field_150276_d));
      } catch (ChatComponentTranslationFormatException chatcomponenttranslationformatexception) {
         this.field_150278_b.clear();

         try {
            this.func_150269_b(StatCollector.func_150826_b(this.field_150276_d));
         } catch (ChatComponentTranslationFormatException var5) {
            throw chatcomponenttranslationformatexception;
         }
      }

   }

   protected void func_150269_b(String p_150269_1_) {
      boolean flag = false;
      Matcher matcher = field_150279_c.matcher(p_150269_1_);
      int i = 0;
      int j = 0;

      try {
         int l;
         for(; matcher.find(j); j = l) {
            int k = matcher.start();
            l = matcher.end();
            if(k > j) {
               ChatComponentText chatcomponenttext = new ChatComponentText(String.format(p_150269_1_.substring(j, k), new Object[0]));
               chatcomponenttext.func_150256_b().func_150221_a(this.func_150256_b());
               this.field_150278_b.add(chatcomponenttext);
            }

            String s2 = matcher.group(2);
            String s = p_150269_1_.substring(k, l);
            if("%".equals(s2) && "%%".equals(s)) {
               ChatComponentText chatcomponenttext2 = new ChatComponentText("%");
               chatcomponenttext2.func_150256_b().func_150221_a(this.func_150256_b());
               this.field_150278_b.add(chatcomponenttext2);
            } else {
               if(!"s".equals(s2)) {
                  throw new ChatComponentTranslationFormatException(this, "Unsupported format: \'" + s + "\'");
               }

               String s1 = matcher.group(1);
               int i1 = s1 != null?Integer.parseInt(s1) - 1:i++;
               if(i1 < this.field_150277_e.length) {
                  this.field_150278_b.add(this.func_150272_a(i1));
               }
            }
         }

         if(j < p_150269_1_.length()) {
            ChatComponentText chatcomponenttext1 = new ChatComponentText(String.format(p_150269_1_.substring(j), new Object[0]));
            chatcomponenttext1.func_150256_b().func_150221_a(this.func_150256_b());
            this.field_150278_b.add(chatcomponenttext1);
         }

      } catch (IllegalFormatException illegalformatexception) {
         throw new ChatComponentTranslationFormatException(this, illegalformatexception);
      }
   }

   private IChatComponent func_150272_a(int p_150272_1_) {
      if(p_150272_1_ >= this.field_150277_e.length) {
         throw new ChatComponentTranslationFormatException(this, p_150272_1_);
      } else {
         Object object = this.field_150277_e[p_150272_1_];
         IChatComponent ichatcomponent;
         if(object instanceof IChatComponent) {
            ichatcomponent = (IChatComponent)object;
         } else {
            ichatcomponent = new ChatComponentText(object == null?"null":object.toString());
            ichatcomponent.func_150256_b().func_150221_a(this.func_150256_b());
         }

         return ichatcomponent;
      }
   }

   public IChatComponent func_150255_a(ChatStyle p_150255_1_) {
      super.func_150255_a(p_150255_1_);

      for(Object object : this.field_150277_e) {
         if(object instanceof IChatComponent) {
            ((IChatComponent)object).func_150256_b().func_150221_a(this.func_150256_b());
         }
      }

      if(this.field_150275_g > -1L) {
         for(IChatComponent ichatcomponent : this.field_150278_b) {
            ichatcomponent.func_150256_b().func_150221_a(p_150255_1_);
         }
      }

      return this;
   }

   public Iterator<IChatComponent> iterator() {
      this.func_150270_g();
      return Iterators.<IChatComponent>concat(func_150262_a(this.field_150278_b), func_150262_a(this.field_150264_a));
   }

   public String func_150261_e() {
      this.func_150270_g();
      StringBuilder stringbuilder = new StringBuilder();

      for(IChatComponent ichatcomponent : this.field_150278_b) {
         stringbuilder.append(ichatcomponent.func_150261_e());
      }

      return stringbuilder.toString();
   }

   public ChatComponentTranslation func_150259_f() {
      Object[] aobject = new Object[this.field_150277_e.length];

      for(int i = 0; i < this.field_150277_e.length; ++i) {
         if(this.field_150277_e[i] instanceof IChatComponent) {
            aobject[i] = ((IChatComponent)this.field_150277_e[i]).func_150259_f();
         } else {
            aobject[i] = this.field_150277_e[i];
         }
      }

      ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(this.field_150276_d, aobject);
      chatcomponenttranslation.func_150255_a(this.func_150256_b().func_150232_l());

      for(IChatComponent ichatcomponent : this.func_150253_a()) {
         chatcomponenttranslation.func_150257_a(ichatcomponent.func_150259_f());
      }

      return chatcomponenttranslation;
   }

   public boolean equals(Object p_equals_1_) {
      if(this == p_equals_1_) {
         return true;
      } else if(!(p_equals_1_ instanceof ChatComponentTranslation)) {
         return false;
      } else {
         ChatComponentTranslation chatcomponenttranslation = (ChatComponentTranslation)p_equals_1_;
         return Arrays.equals(this.field_150277_e, chatcomponenttranslation.field_150277_e) && this.field_150276_d.equals(chatcomponenttranslation.field_150276_d) && super.equals(p_equals_1_);
      }
   }

   public int hashCode() {
      int i = super.hashCode();
      i = 31 * i + this.field_150276_d.hashCode();
      i = 31 * i + Arrays.hashCode(this.field_150277_e);
      return i;
   }

   public String toString() {
      return "TranslatableComponent{key=\'" + this.field_150276_d + '\'' + ", args=" + Arrays.toString(this.field_150277_e) + ", siblings=" + this.field_150264_a + ", style=" + this.func_150256_b() + '}';
   }

   public String func_150268_i() {
      return this.field_150276_d;
   }

   public Object[] func_150271_j() {
      return this.field_150277_e;
   }
}
