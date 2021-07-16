package net.minecraft.util;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public abstract class ChatComponentStyle implements IChatComponent {
   protected List<IChatComponent> field_150264_a = Lists.<IChatComponent>newArrayList();
   private ChatStyle field_150263_b;

   public IChatComponent func_150257_a(IChatComponent p_150257_1_) {
      p_150257_1_.func_150256_b().func_150221_a(this.func_150256_b());
      this.field_150264_a.add(p_150257_1_);
      return this;
   }

   public List<IChatComponent> func_150253_a() {
      return this.field_150264_a;
   }

   public IChatComponent func_150258_a(String p_150258_1_) {
      return this.func_150257_a(new ChatComponentText(p_150258_1_));
   }

   public IChatComponent func_150255_a(ChatStyle p_150255_1_) {
      this.field_150263_b = p_150255_1_;

      for(IChatComponent ichatcomponent : this.field_150264_a) {
         ichatcomponent.func_150256_b().func_150221_a(this.func_150256_b());
      }

      return this;
   }

   public ChatStyle func_150256_b() {
      if(this.field_150263_b == null) {
         this.field_150263_b = new ChatStyle();

         for(IChatComponent ichatcomponent : this.field_150264_a) {
            ichatcomponent.func_150256_b().func_150221_a(this.field_150263_b);
         }
      }

      return this.field_150263_b;
   }

   public Iterator<IChatComponent> iterator() {
      return Iterators.<IChatComponent>concat(Iterators.<IChatComponent>forArray(new ChatComponentStyle[]{this}), func_150262_a(this.field_150264_a));
   }

   public final String func_150260_c() {
      StringBuilder stringbuilder = new StringBuilder();

      for(IChatComponent ichatcomponent : this) {
         stringbuilder.append(ichatcomponent.func_150261_e());
      }

      return stringbuilder.toString();
   }

   public final String func_150254_d() {
      StringBuilder stringbuilder = new StringBuilder();

      for(IChatComponent ichatcomponent : this) {
         stringbuilder.append(ichatcomponent.func_150256_b().func_150218_j());
         stringbuilder.append(ichatcomponent.func_150261_e());
         stringbuilder.append((Object)EnumChatFormatting.RESET);
      }

      return stringbuilder.toString();
   }

   public static Iterator<IChatComponent> func_150262_a(Iterable<IChatComponent> p_150262_0_) {
      Iterator<IChatComponent> iterator = Iterators.concat(Iterators.transform(p_150262_0_.iterator(), new Function<IChatComponent, Iterator<IChatComponent>>() {
         public Iterator<IChatComponent> apply(IChatComponent p_apply_1_) {
            return p_apply_1_.iterator();
         }
      }));
      iterator = Iterators.transform(iterator, new Function<IChatComponent, IChatComponent>() {
         public IChatComponent apply(IChatComponent p_apply_1_) {
            IChatComponent ichatcomponent = p_apply_1_.func_150259_f();
            ichatcomponent.func_150255_a(ichatcomponent.func_150256_b().func_150206_m());
            return ichatcomponent;
         }
      });
      return iterator;
   }

   public boolean equals(Object p_equals_1_) {
      if(this == p_equals_1_) {
         return true;
      } else if(!(p_equals_1_ instanceof ChatComponentStyle)) {
         return false;
      } else {
         ChatComponentStyle chatcomponentstyle = (ChatComponentStyle)p_equals_1_;
         return this.field_150264_a.equals(chatcomponentstyle.field_150264_a) && this.func_150256_b().equals(chatcomponentstyle.func_150256_b());
      }
   }

   public int hashCode() {
      return 31 * this.field_150263_b.hashCode() + this.field_150264_a.hashCode();
   }

   public String toString() {
      return "BaseComponent{style=" + this.field_150263_b + ", siblings=" + this.field_150264_a + '}';
   }
}
