package net.minecraft.client.gui;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiCommandBlock extends GuiScreen {
   private static final Logger field_146488_a = LogManager.getLogger();
   private GuiTextField field_146485_f;
   private GuiTextField field_146486_g;
   private final CommandBlockLogic field_146489_h;
   private GuiButton field_146490_i;
   private GuiButton field_146487_r;
   private GuiButton field_175390_s;
   private boolean field_175389_t;

   public GuiCommandBlock(CommandBlockLogic p_i45032_1_) {
      this.field_146489_h = p_i45032_1_;
   }

   public void func_73876_c() {
      this.field_146485_f.func_146178_a();
   }

   public void func_73866_w_() {
      Keyboard.enableRepeatEvents(true);
      this.field_146292_n.clear();
      this.field_146292_n.add(this.field_146490_i = new GuiButton(0, this.field_146294_l / 2 - 4 - 150, this.field_146295_m / 4 + 120 + 12, 150, 20, I18n.func_135052_a("gui.done", new Object[0])));
      this.field_146292_n.add(this.field_146487_r = new GuiButton(1, this.field_146294_l / 2 + 4, this.field_146295_m / 4 + 120 + 12, 150, 20, I18n.func_135052_a("gui.cancel", new Object[0])));
      this.field_146292_n.add(this.field_175390_s = new GuiButton(4, this.field_146294_l / 2 + 150 - 20, 150, 20, 20, "O"));
      this.field_146485_f = new GuiTextField(2, this.field_146289_q, this.field_146294_l / 2 - 150, 50, 300, 20);
      this.field_146485_f.func_146203_f(32767);
      this.field_146485_f.func_146195_b(true);
      this.field_146485_f.func_146180_a(this.field_146489_h.func_145753_i());
      this.field_146486_g = new GuiTextField(3, this.field_146289_q, this.field_146294_l / 2 - 150, 150, 276, 20);
      this.field_146486_g.func_146203_f(32767);
      this.field_146486_g.func_146184_c(false);
      this.field_146486_g.func_146180_a("-");
      this.field_175389_t = this.field_146489_h.func_175571_m();
      this.func_175388_a();
      this.field_146490_i.field_146124_l = this.field_146485_f.func_146179_b().trim().length() > 0;
   }

   public void func_146281_b() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(p_146284_1_.field_146124_l) {
         if(p_146284_1_.field_146127_k == 1) {
            this.field_146489_h.func_175573_a(this.field_175389_t);
            this.field_146297_k.func_147108_a((GuiScreen)null);
         } else if(p_146284_1_.field_146127_k == 0) {
            PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
            packetbuffer.writeByte(this.field_146489_h.func_145751_f());
            this.field_146489_h.func_145757_a(packetbuffer);
            packetbuffer.func_180714_a(this.field_146485_f.func_146179_b());
            packetbuffer.writeBoolean(this.field_146489_h.func_175571_m());
            this.field_146297_k.func_147114_u().func_147297_a(new C17PacketCustomPayload("MC|AdvCdm", packetbuffer));
            if(!this.field_146489_h.func_175571_m()) {
               this.field_146489_h.func_145750_b((IChatComponent)null);
            }

            this.field_146297_k.func_147108_a((GuiScreen)null);
         } else if(p_146284_1_.field_146127_k == 4) {
            this.field_146489_h.func_175573_a(!this.field_146489_h.func_175571_m());
            this.func_175388_a();
         }

      }
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      this.field_146485_f.func_146201_a(p_73869_1_, p_73869_2_);
      this.field_146486_g.func_146201_a(p_73869_1_, p_73869_2_);
      this.field_146490_i.field_146124_l = this.field_146485_f.func_146179_b().trim().length() > 0;
      if(p_73869_2_ != 28 && p_73869_2_ != 156) {
         if(p_73869_2_ == 1) {
            this.func_146284_a(this.field_146487_r);
         }
      } else {
         this.func_146284_a(this.field_146490_i);
      }

   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_146485_f.func_146192_a(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_146486_g.func_146192_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146276_q_();
      this.func_73732_a(this.field_146289_q, I18n.func_135052_a("advMode.setCommand", new Object[0]), this.field_146294_l / 2, 20, 16777215);
      this.func_73731_b(this.field_146289_q, I18n.func_135052_a("advMode.command", new Object[0]), this.field_146294_l / 2 - 150, 37, 10526880);
      this.field_146485_f.func_146194_f();
      int i = 75;
      int j = 0;
      this.func_73731_b(this.field_146289_q, I18n.func_135052_a("advMode.nearestPlayer", new Object[0]), this.field_146294_l / 2 - 150, i + j++ * this.field_146289_q.field_78288_b, 10526880);
      this.func_73731_b(this.field_146289_q, I18n.func_135052_a("advMode.randomPlayer", new Object[0]), this.field_146294_l / 2 - 150, i + j++ * this.field_146289_q.field_78288_b, 10526880);
      this.func_73731_b(this.field_146289_q, I18n.func_135052_a("advMode.allPlayers", new Object[0]), this.field_146294_l / 2 - 150, i + j++ * this.field_146289_q.field_78288_b, 10526880);
      this.func_73731_b(this.field_146289_q, I18n.func_135052_a("advMode.allEntities", new Object[0]), this.field_146294_l / 2 - 150, i + j++ * this.field_146289_q.field_78288_b, 10526880);
      this.func_73731_b(this.field_146289_q, "", this.field_146294_l / 2 - 150, i + j++ * this.field_146289_q.field_78288_b, 10526880);
      if(this.field_146486_g.func_146179_b().length() > 0) {
         i = i + j * this.field_146289_q.field_78288_b + 16;
         this.func_73731_b(this.field_146289_q, I18n.func_135052_a("advMode.previousOutput", new Object[0]), this.field_146294_l / 2 - 150, i, 10526880);
         this.field_146486_g.func_146194_f();
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   private void func_175388_a() {
      if(this.field_146489_h.func_175571_m()) {
         this.field_175390_s.field_146126_j = "O";
         if(this.field_146489_h.func_145749_h() != null) {
            this.field_146486_g.func_146180_a(this.field_146489_h.func_145749_h().func_150260_c());
         }
      } else {
         this.field_175390_s.field_146126_j = "X";
         this.field_146486_g.func_146180_a("-");
      }

   }
}
