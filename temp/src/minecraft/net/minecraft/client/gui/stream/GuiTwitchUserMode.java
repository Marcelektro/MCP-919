package net.minecraft.client.gui.stream;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class GuiTwitchUserMode extends GuiScreen {
   private static final EnumChatFormatting field_152331_a = EnumChatFormatting.DARK_GREEN;
   private static final EnumChatFormatting field_152335_f = EnumChatFormatting.RED;
   private static final EnumChatFormatting field_152336_g = EnumChatFormatting.DARK_PURPLE;
   private final ChatUserInfo field_152337_h;
   private final IChatComponent field_152338_i;
   private final List<IChatComponent> field_152332_r = Lists.<IChatComponent>newArrayList();
   private final IStream field_152333_s;
   private int field_152334_t;

   public GuiTwitchUserMode(IStream p_i1064_1_, ChatUserInfo p_i1064_2_) {
      this.field_152333_s = p_i1064_1_;
      this.field_152337_h = p_i1064_2_;
      this.field_152338_i = new ChatComponentText(p_i1064_2_.displayName);
      this.field_152332_r.addAll(func_152328_a(p_i1064_2_.modes, p_i1064_2_.subscriptions, p_i1064_1_));
   }

   public static List<IChatComponent> func_152328_a(Set<ChatUserMode> p_152328_0_, Set<ChatUserSubscription> p_152328_1_, IStream p_152328_2_) {
      String s = p_152328_2_ == null?null:p_152328_2_.func_152921_C();
      boolean flag = p_152328_2_ != null && p_152328_2_.func_152927_B();
      List<IChatComponent> list = Lists.<IChatComponent>newArrayList();

      for(ChatUserMode chatusermode : p_152328_0_) {
         IChatComponent ichatcomponent = func_152329_a(chatusermode, s, flag);
         if(ichatcomponent != null) {
            IChatComponent ichatcomponent1 = new ChatComponentText("- ");
            ichatcomponent1.func_150257_a(ichatcomponent);
            list.add(ichatcomponent1);
         }
      }

      for(ChatUserSubscription chatusersubscription : p_152328_1_) {
         IChatComponent ichatcomponent2 = func_152330_a(chatusersubscription, s, flag);
         if(ichatcomponent2 != null) {
            IChatComponent ichatcomponent3 = new ChatComponentText("- ");
            ichatcomponent3.func_150257_a(ichatcomponent2);
            list.add(ichatcomponent3);
         }
      }

      return list;
   }

   public static IChatComponent func_152330_a(ChatUserSubscription p_152330_0_, String p_152330_1_, boolean p_152330_2_) {
      IChatComponent ichatcomponent = null;
      if(p_152330_0_ == ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER) {
         if(p_152330_1_ == null) {
            ichatcomponent = new ChatComponentTranslation("stream.user.subscription.subscriber", new Object[0]);
         } else if(p_152330_2_) {
            ichatcomponent = new ChatComponentTranslation("stream.user.subscription.subscriber.self", new Object[0]);
         } else {
            ichatcomponent = new ChatComponentTranslation("stream.user.subscription.subscriber.other", new Object[]{p_152330_1_});
         }

         ichatcomponent.func_150256_b().func_150238_a(field_152331_a);
      } else if(p_152330_0_ == ChatUserSubscription.TTV_CHAT_USERSUB_TURBO) {
         ichatcomponent = new ChatComponentTranslation("stream.user.subscription.turbo", new Object[0]);
         ichatcomponent.func_150256_b().func_150238_a(field_152336_g);
      }

      return ichatcomponent;
   }

   public static IChatComponent func_152329_a(ChatUserMode p_152329_0_, String p_152329_1_, boolean p_152329_2_) {
      IChatComponent ichatcomponent = null;
      if(p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR) {
         ichatcomponent = new ChatComponentTranslation("stream.user.mode.administrator", new Object[0]);
         ichatcomponent.func_150256_b().func_150238_a(field_152336_g);
      } else if(p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_BANNED) {
         if(p_152329_1_ == null) {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.banned", new Object[0]);
         } else if(p_152329_2_) {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.banned.self", new Object[0]);
         } else {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.banned.other", new Object[]{p_152329_1_});
         }

         ichatcomponent.func_150256_b().func_150238_a(field_152335_f);
      } else if(p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_BROADCASTER) {
         if(p_152329_1_ == null) {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.broadcaster", new Object[0]);
         } else if(p_152329_2_) {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.broadcaster.self", new Object[0]);
         } else {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.broadcaster.other", new Object[0]);
         }

         ichatcomponent.func_150256_b().func_150238_a(field_152331_a);
      } else if(p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_MODERATOR) {
         if(p_152329_1_ == null) {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.moderator", new Object[0]);
         } else if(p_152329_2_) {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.moderator.self", new Object[0]);
         } else {
            ichatcomponent = new ChatComponentTranslation("stream.user.mode.moderator.other", new Object[]{p_152329_1_});
         }

         ichatcomponent.func_150256_b().func_150238_a(field_152331_a);
      } else if(p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_STAFF) {
         ichatcomponent = new ChatComponentTranslation("stream.user.mode.staff", new Object[0]);
         ichatcomponent.func_150256_b().func_150238_a(field_152336_g);
      }

      return ichatcomponent;
   }

   public void func_73866_w_() {
      int i = this.field_146294_l / 3;
      int j = i - 130;
      this.field_146292_n.add(new GuiButton(1, i * 0 + j / 2, this.field_146295_m - 70, 130, 20, I18n.func_135052_a("stream.userinfo.timeout", new Object[0])));
      this.field_146292_n.add(new GuiButton(0, i * 1 + j / 2, this.field_146295_m - 70, 130, 20, I18n.func_135052_a("stream.userinfo.ban", new Object[0])));
      this.field_146292_n.add(new GuiButton(2, i * 2 + j / 2, this.field_146295_m - 70, 130, 20, I18n.func_135052_a("stream.userinfo.mod", new Object[0])));
      this.field_146292_n.add(new GuiButton(5, i * 0 + j / 2, this.field_146295_m - 45, 130, 20, I18n.func_135052_a("gui.cancel", new Object[0])));
      this.field_146292_n.add(new GuiButton(3, i * 1 + j / 2, this.field_146295_m - 45, 130, 20, I18n.func_135052_a("stream.userinfo.unban", new Object[0])));
      this.field_146292_n.add(new GuiButton(4, i * 2 + j / 2, this.field_146295_m - 45, 130, 20, I18n.func_135052_a("stream.userinfo.unmod", new Object[0])));
      int k = 0;

      for(IChatComponent ichatcomponent : this.field_152332_r) {
         k = Math.max(k, this.field_146289_q.func_78256_a(ichatcomponent.func_150254_d()));
      }

      this.field_152334_t = this.field_146294_l / 2 - k / 2;
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(p_146284_1_.field_146124_l) {
         if(p_146284_1_.field_146127_k == 0) {
            this.field_152333_s.func_152917_b("/ban " + this.field_152337_h.displayName);
         } else if(p_146284_1_.field_146127_k == 3) {
            this.field_152333_s.func_152917_b("/unban " + this.field_152337_h.displayName);
         } else if(p_146284_1_.field_146127_k == 2) {
            this.field_152333_s.func_152917_b("/mod " + this.field_152337_h.displayName);
         } else if(p_146284_1_.field_146127_k == 4) {
            this.field_152333_s.func_152917_b("/unmod " + this.field_152337_h.displayName);
         } else if(p_146284_1_.field_146127_k == 1) {
            this.field_152333_s.func_152917_b("/timeout " + this.field_152337_h.displayName);
         }

         this.field_146297_k.func_147108_a((GuiScreen)null);
      }
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.field_152338_i.func_150260_c(), this.field_146294_l / 2, 70, 16777215);
      int i = 80;

      for(IChatComponent ichatcomponent : this.field_152332_r) {
         this.func_73731_b(this.field_146289_q, ichatcomponent.func_150254_d(), this.field_152334_t, i, 16777215);
         i += this.field_146289_q.field_78288_b;
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
