package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.EnumDifficulty;

public class GuiOptions extends GuiScreen implements GuiYesNoCallback {
   private static final GameSettings.Options[] field_146440_f = new GameSettings.Options[]{GameSettings.Options.FOV};
   private final GuiScreen field_146441_g;
   private final GameSettings field_146443_h;
   private GuiButton field_175357_i;
   private GuiLockIconButton field_175356_r;
   protected String field_146442_a = "Options";

   public GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_) {
      this.field_146441_g = p_i1046_1_;
      this.field_146443_h = p_i1046_2_;
   }

   public void func_73866_w_() {
      int i = 0;
      this.field_146442_a = I18n.func_135052_a("options.title", new Object[0]);

      for(GameSettings.Options gamesettings$options : field_146440_f) {
         if(gamesettings$options.func_74380_a()) {
            this.field_146292_n.add(new GuiOptionSlider(gamesettings$options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 - 12 + 24 * (i >> 1), gamesettings$options));
         } else {
            GuiOptionButton guioptionbutton = new GuiOptionButton(gamesettings$options.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 - 12 + 24 * (i >> 1), gamesettings$options, this.field_146443_h.func_74297_c(gamesettings$options));
            this.field_146292_n.add(guioptionbutton);
         }

         ++i;
      }

      if(this.field_146297_k.field_71441_e != null) {
         EnumDifficulty enumdifficulty = this.field_146297_k.field_71441_e.func_175659_aa();
         this.field_175357_i = new GuiButton(108, this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 - 12 + 24 * (i >> 1), 150, 20, this.func_175355_a(enumdifficulty));
         this.field_146292_n.add(this.field_175357_i);
         if(this.field_146297_k.func_71356_B() && !this.field_146297_k.field_71441_e.func_72912_H().func_76093_s()) {
            this.field_175357_i.func_175211_a(this.field_175357_i.func_146117_b() - 20);
            this.field_175356_r = new GuiLockIconButton(109, this.field_175357_i.field_146128_h + this.field_175357_i.func_146117_b(), this.field_175357_i.field_146129_i);
            this.field_146292_n.add(this.field_175356_r);
            this.field_175356_r.func_175229_b(this.field_146297_k.field_71441_e.func_72912_H().func_176123_z());
            this.field_175356_r.field_146124_l = !this.field_175356_r.func_175230_c();
            this.field_175357_i.field_146124_l = !this.field_175356_r.func_175230_c();
         } else {
            this.field_175357_i.field_146124_l = false;
         }
      } else {
         GuiOptionButton guioptionbutton1 = new GuiOptionButton(GameSettings.Options.REALMS_NOTIFICATIONS.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, this.field_146295_m / 6 - 12 + 24 * (i >> 1), GameSettings.Options.REALMS_NOTIFICATIONS, this.field_146443_h.func_74297_c(GameSettings.Options.REALMS_NOTIFICATIONS));
         this.field_146292_n.add(guioptionbutton1);
      }

      this.field_146292_n.add(new GuiButton(110, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 48 - 6, 150, 20, I18n.func_135052_a("options.skinCustomisation", new Object[0])));
      this.field_146292_n.add(new GuiButton(8675309, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 48 - 6, 150, 20, "Super Secret Settings...") {
         public void func_146113_a(SoundHandler p_146113_1_) {
            SoundEventAccessorComposite soundeventaccessorcomposite = p_146113_1_.func_147686_a(new SoundCategory[]{SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER});
            if(soundeventaccessorcomposite != null) {
               p_146113_1_.func_147682_a(PositionedSoundRecord.func_147674_a(soundeventaccessorcomposite.func_148729_c(), 0.5F));
            }

         }
      });
      this.field_146292_n.add(new GuiButton(106, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 72 - 6, 150, 20, I18n.func_135052_a("options.sounds", new Object[0])));
      this.field_146292_n.add(new GuiButton(107, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 72 - 6, 150, 20, I18n.func_135052_a("options.stream", new Object[0])));
      this.field_146292_n.add(new GuiButton(101, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 96 - 6, 150, 20, I18n.func_135052_a("options.video", new Object[0])));
      this.field_146292_n.add(new GuiButton(100, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 96 - 6, 150, 20, I18n.func_135052_a("options.controls", new Object[0])));
      this.field_146292_n.add(new GuiButton(102, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 120 - 6, 150, 20, I18n.func_135052_a("options.language", new Object[0])));
      this.field_146292_n.add(new GuiButton(103, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 120 - 6, 150, 20, I18n.func_135052_a("options.chat.title", new Object[0])));
      this.field_146292_n.add(new GuiButton(105, this.field_146294_l / 2 - 155, this.field_146295_m / 6 + 144 - 6, 150, 20, I18n.func_135052_a("options.resourcepack", new Object[0])));
      this.field_146292_n.add(new GuiButton(104, this.field_146294_l / 2 + 5, this.field_146295_m / 6 + 144 - 6, 150, 20, I18n.func_135052_a("options.snooper.view", new Object[0])));
      this.field_146292_n.add(new GuiButton(200, this.field_146294_l / 2 - 100, this.field_146295_m / 6 + 168, I18n.func_135052_a("gui.done", new Object[0])));
   }

   public String func_175355_a(EnumDifficulty p_175355_1_) {
      IChatComponent ichatcomponent = new ChatComponentText("");
      ichatcomponent.func_150257_a(new ChatComponentTranslation("options.difficulty", new Object[0]));
      ichatcomponent.func_150258_a(": ");
      ichatcomponent.func_150257_a(new ChatComponentTranslation(p_175355_1_.func_151526_b(), new Object[0]));
      return ichatcomponent.func_150254_d();
   }

   public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
      this.field_146297_k.func_147108_a(this);
      if(p_73878_2_ == 109 && p_73878_1_ && this.field_146297_k.field_71441_e != null) {
         this.field_146297_k.field_71441_e.func_72912_H().func_180783_e(true);
         this.field_175356_r.func_175229_b(true);
         this.field_175356_r.field_146124_l = false;
         this.field_175357_i.field_146124_l = false;
      }

   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(p_146284_1_.field_146124_l) {
         if(p_146284_1_.field_146127_k < 100 && p_146284_1_ instanceof GuiOptionButton) {
            GameSettings.Options gamesettings$options = ((GuiOptionButton)p_146284_1_).func_146136_c();
            this.field_146443_h.func_74306_a(gamesettings$options, 1);
            p_146284_1_.field_146126_j = this.field_146443_h.func_74297_c(GameSettings.Options.func_74379_a(p_146284_1_.field_146127_k));
         }

         if(p_146284_1_.field_146127_k == 108) {
            this.field_146297_k.field_71441_e.func_72912_H().func_176144_a(EnumDifficulty.func_151523_a(this.field_146297_k.field_71441_e.func_175659_aa().func_151525_a() + 1));
            this.field_175357_i.field_146126_j = this.func_175355_a(this.field_146297_k.field_71441_e.func_175659_aa());
         }

         if(p_146284_1_.field_146127_k == 109) {
            this.field_146297_k.func_147108_a(new GuiYesNo(this, (new ChatComponentTranslation("difficulty.lock.title", new Object[0])).func_150254_d(), (new ChatComponentTranslation("difficulty.lock.question", new Object[]{new ChatComponentTranslation(this.field_146297_k.field_71441_e.func_72912_H().func_176130_y().func_151526_b(), new Object[0])})).func_150254_d(), 109));
         }

         if(p_146284_1_.field_146127_k == 110) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiCustomizeSkin(this));
         }

         if(p_146284_1_.field_146127_k == 8675309) {
            this.field_146297_k.field_71460_t.func_147705_c();
         }

         if(p_146284_1_.field_146127_k == 101) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiVideoSettings(this, this.field_146443_h));
         }

         if(p_146284_1_.field_146127_k == 100) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiControls(this, this.field_146443_h));
         }

         if(p_146284_1_.field_146127_k == 102) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiLanguage(this, this.field_146443_h, this.field_146297_k.func_135016_M()));
         }

         if(p_146284_1_.field_146127_k == 103) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new ScreenChatOptions(this, this.field_146443_h));
         }

         if(p_146284_1_.field_146127_k == 104) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiSnooper(this, this.field_146443_h));
         }

         if(p_146284_1_.field_146127_k == 200) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(this.field_146441_g);
         }

         if(p_146284_1_.field_146127_k == 105) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiScreenResourcePacks(this));
         }

         if(p_146284_1_.field_146127_k == 106) {
            this.field_146297_k.field_71474_y.func_74303_b();
            this.field_146297_k.func_147108_a(new GuiScreenOptionsSounds(this, this.field_146443_h));
         }

         if(p_146284_1_.field_146127_k == 107) {
            this.field_146297_k.field_71474_y.func_74303_b();
            IStream istream = this.field_146297_k.func_152346_Z();
            if(istream.func_152936_l() && istream.func_152928_D()) {
               this.field_146297_k.func_147108_a(new GuiStreamOptions(this, this.field_146443_h));
            } else {
               GuiStreamUnavailable.func_152321_a(this);
            }
         }

      }
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, this.field_146442_a, this.field_146294_l / 2, 15, 16777215);
      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
