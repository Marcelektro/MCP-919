package net.minecraft.client.stream;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.stream.BroadcastController;
import net.minecraft.client.stream.ChatController;
import net.minecraft.client.stream.IStream;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.client.stream.Metadata;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.opengl.GL11;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.VideoParams;
import tv.twitch.chat.ChatRawMessage;
import tv.twitch.chat.ChatTokenizedMessage;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class TwitchStream implements BroadcastController.BroadcastListener, ChatController.ChatListener, IngestServerTester.IngestTestListener, IStream {
   private static final Logger field_152950_b = LogManager.getLogger();
   public static final Marker field_152949_a = MarkerManager.getMarker("STREAM");
   private final BroadcastController field_152951_c;
   private final ChatController field_152952_d;
   private String field_176029_e;
   private final Minecraft field_152953_e;
   private final IChatComponent field_152954_f = new ChatComponentText("Twitch");
   private final Map<String, ChatUserInfo> field_152955_g = Maps.<String, ChatUserInfo>newHashMap();
   private Framebuffer field_152956_h;
   private boolean field_152957_i;
   private int field_152958_j = 30;
   private long field_152959_k = 0L;
   private boolean field_152960_l = false;
   private boolean field_152961_m;
   private boolean field_152962_n;
   private boolean field_152963_o;
   private IStream.AuthFailureReason field_152964_p = IStream.AuthFailureReason.ERROR;
   private static boolean field_152965_q;

   public TwitchStream(Minecraft p_i46057_1_, final Property p_i46057_2_) {
      this.field_152953_e = p_i46057_1_;
      this.field_152951_c = new BroadcastController();
      this.field_152952_d = new ChatController();
      this.field_152951_c.func_152841_a(this);
      this.field_152952_d.func_152990_a(this);
      this.field_152951_c.func_152842_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.field_152952_d.func_152984_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.field_152954_f.func_150256_b().func_150238_a(EnumChatFormatting.DARK_PURPLE);
      if(p_i46057_2_ != null && !Strings.isNullOrEmpty(p_i46057_2_.getValue()) && OpenGlHelper.field_148823_f) {
         Thread thread = new Thread("Twitch authenticator") {
            public void run() {
               try {
                  URL url = new URL("https://api.twitch.tv/kraken?oauth_token=" + URLEncoder.encode(p_i46057_2_.getValue(), "UTF-8"));
                  String s = HttpUtil.func_152755_a(url);
                  JsonObject jsonobject = JsonUtils.func_151210_l((new JsonParser()).parse(s), "Response");
                  JsonObject jsonobject1 = JsonUtils.func_152754_s(jsonobject, "token");
                  if(JsonUtils.func_151212_i(jsonobject1, "valid")) {
                     String s1 = JsonUtils.func_151200_h(jsonobject1, "user_name");
                     TwitchStream.field_152950_b.debug(TwitchStream.field_152949_a, "Authenticated with twitch; username is {}", new Object[]{s1});
                     AuthToken authtoken = new AuthToken();
                     authtoken.data = p_i46057_2_.getValue();
                     TwitchStream.this.field_152951_c.func_152818_a(s1, authtoken);
                     TwitchStream.this.field_152952_d.func_152998_c(s1);
                     TwitchStream.this.field_152952_d.func_152994_a(authtoken);
                     Runtime.getRuntime().addShutdownHook(new Thread("Twitch shutdown hook") {
                        public void run() {
                           TwitchStream.this.func_152923_i();
                        }
                     });
                     TwitchStream.this.field_152951_c.func_152817_A();
                     TwitchStream.this.field_152952_d.func_175984_n();
                  } else {
                     TwitchStream.this.field_152964_p = IStream.AuthFailureReason.INVALID_TOKEN;
                     TwitchStream.field_152950_b.error(TwitchStream.field_152949_a, "Given twitch access token is invalid");
                  }
               } catch (IOException ioexception) {
                  TwitchStream.this.field_152964_p = IStream.AuthFailureReason.ERROR;
                  TwitchStream.field_152950_b.error(TwitchStream.field_152949_a, (String)"Could not authenticate with twitch", (Throwable)ioexception);
               }

            }
         };
         thread.setDaemon(true);
         thread.start();
      }

   }

   public void func_152923_i() {
      field_152950_b.debug(field_152949_a, "Shutdown streaming");
      this.field_152951_c.statCallback();
      this.field_152952_d.func_175988_p();
   }

   public void func_152935_j() {
      int i = this.field_152953_e.field_71474_y.field_152408_R;
      boolean flag = this.field_176029_e != null && this.field_152952_d.func_175990_d(this.field_176029_e);
      boolean flag1 = this.field_152952_d.func_153000_j() == ChatController.ChatState.Initialized && (this.field_176029_e == null || this.field_152952_d.func_175989_e(this.field_176029_e) == ChatController.EnumChannelState.Disconnected);
      if(i == 2) {
         if(flag) {
            field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat per user options");
            this.field_152952_d.func_175991_l(this.field_176029_e);
         }
      } else if(i == 1) {
         if(flag1 && this.field_152951_c.func_152849_q()) {
            field_152950_b.debug(field_152949_a, "Connecting to twitch chat per user options");
            this.func_152942_I();
         }
      } else if(i == 0) {
         if(flag && !this.func_152934_n()) {
            field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat as user is no longer streaming");
            this.field_152952_d.func_175991_l(this.field_176029_e);
         } else if(flag1 && this.func_152934_n()) {
            field_152950_b.debug(field_152949_a, "Connecting to twitch chat as user is streaming");
            this.func_152942_I();
         }
      }

      this.field_152951_c.func_152821_H();
      this.field_152952_d.func_152997_n();
   }

   protected void func_152942_I() {
      ChatController.ChatState chatcontroller$chatstate = this.field_152952_d.func_153000_j();
      String s = this.field_152951_c.func_152843_l().name;
      this.field_176029_e = s;
      if(chatcontroller$chatstate != ChatController.ChatState.Initialized) {
         field_152950_b.warn("Invalid twitch chat state {}", new Object[]{chatcontroller$chatstate});
      } else if(this.field_152952_d.func_175989_e(this.field_176029_e) == ChatController.EnumChannelState.Disconnected) {
         this.field_152952_d.func_152986_d(s);
      } else {
         field_152950_b.warn("Invalid twitch chat state {}", new Object[]{chatcontroller$chatstate});
      }

   }

   public void func_152922_k() {
      if(this.field_152951_c.func_152850_m() && !this.field_152951_c.func_152839_p()) {
         long i = System.nanoTime();
         long j = (long)(1000000000 / this.field_152958_j);
         long k = i - this.field_152959_k;
         boolean flag = k >= j;
         if(flag) {
            FrameBuffer framebuffer = this.field_152951_c.func_152822_N();
            Framebuffer framebuffer1 = this.field_152953_e.func_147110_a();
            this.field_152956_h.func_147610_a(true);
            GlStateManager.func_179128_n(5889);
            GlStateManager.func_179094_E();
            GlStateManager.func_179096_D();
            GlStateManager.func_179130_a(0.0D, (double)this.field_152956_h.field_147621_c, (double)this.field_152956_h.field_147618_d, 0.0D, 1000.0D, 3000.0D);
            GlStateManager.func_179128_n(5888);
            GlStateManager.func_179094_E();
            GlStateManager.func_179096_D();
            GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179083_b(0, 0, this.field_152956_h.field_147621_c, this.field_152956_h.field_147618_d);
            GlStateManager.func_179098_w();
            GlStateManager.func_179118_c();
            GlStateManager.func_179084_k();
            float f = (float)this.field_152956_h.field_147621_c;
            float f1 = (float)this.field_152956_h.field_147618_d;
            float f2 = (float)framebuffer1.field_147621_c / (float)framebuffer1.field_147622_a;
            float f3 = (float)framebuffer1.field_147618_d / (float)framebuffer1.field_147620_b;
            framebuffer1.func_147612_c();
            GL11.glTexParameterf(3553, 10241, 9729.0F);
            GL11.glTexParameterf(3553, 10240, 9729.0F);
            Tessellator tessellator = Tessellator.func_178181_a();
            WorldRenderer worldrenderer = tessellator.func_178180_c();
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
            worldrenderer.func_181662_b(0.0D, (double)f1, 0.0D).func_181673_a(0.0D, (double)f3).func_181675_d();
            worldrenderer.func_181662_b((double)f, (double)f1, 0.0D).func_181673_a((double)f2, (double)f3).func_181675_d();
            worldrenderer.func_181662_b((double)f, 0.0D, 0.0D).func_181673_a((double)f2, 0.0D).func_181675_d();
            worldrenderer.func_181662_b(0.0D, 0.0D, 0.0D).func_181673_a(0.0D, 0.0D).func_181675_d();
            tessellator.func_78381_a();
            framebuffer1.func_147606_d();
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5889);
            GlStateManager.func_179121_F();
            GlStateManager.func_179128_n(5888);
            this.field_152951_c.func_152846_a(framebuffer);
            this.field_152956_h.func_147609_e();
            this.field_152951_c.func_152859_b(framebuffer);
            this.field_152959_k = i;
         }

      }
   }

   public boolean func_152936_l() {
      return this.field_152951_c.func_152849_q();
   }

   public boolean func_152924_m() {
      return this.field_152951_c.func_152857_n();
   }

   public boolean func_152934_n() {
      return this.field_152951_c.func_152850_m();
   }

   public void func_152911_a(Metadata p_152911_1_, long p_152911_2_) {
      if(this.func_152934_n() && this.field_152957_i) {
         long i = this.field_152951_c.func_152844_x();
         if(!this.field_152951_c.func_152840_a(p_152911_1_.func_152810_c(), i + p_152911_2_, p_152911_1_.func_152809_a(), p_152911_1_.func_152806_b())) {
            field_152950_b.warn(field_152949_a, "Couldn\'t send stream metadata action at {}: {}", new Object[]{Long.valueOf(i + p_152911_2_), p_152911_1_});
         } else {
            field_152950_b.debug(field_152949_a, "Sent stream metadata action at {}: {}", new Object[]{Long.valueOf(i + p_152911_2_), p_152911_1_});
         }

      }
   }

   public void func_176026_a(Metadata p_176026_1_, long p_176026_2_, long p_176026_4_) {
      if(this.func_152934_n() && this.field_152957_i) {
         long i = this.field_152951_c.func_152844_x();
         String s = p_176026_1_.func_152809_a();
         String s1 = p_176026_1_.func_152806_b();
         long j = this.field_152951_c.func_177946_b(p_176026_1_.func_152810_c(), i + p_176026_2_, s, s1);
         if(j < 0L) {
            field_152950_b.warn(field_152949_a, "Could not send stream metadata sequence from {} to {}: {}", new Object[]{Long.valueOf(i + p_176026_2_), Long.valueOf(i + p_176026_4_), p_176026_1_});
         } else if(this.field_152951_c.func_177947_a(p_176026_1_.func_152810_c(), i + p_176026_4_, j, s, s1)) {
            field_152950_b.debug(field_152949_a, "Sent stream metadata sequence from {} to {}: {}", new Object[]{Long.valueOf(i + p_176026_2_), Long.valueOf(i + p_176026_4_), p_176026_1_});
         } else {
            field_152950_b.warn(field_152949_a, "Half-sent stream metadata sequence from {} to {}: {}", new Object[]{Long.valueOf(i + p_176026_2_), Long.valueOf(i + p_176026_4_), p_176026_1_});
         }

      }
   }

   public boolean func_152919_o() {
      return this.field_152951_c.func_152839_p();
   }

   public void func_152931_p() {
      if(this.field_152951_c.func_152830_D()) {
         field_152950_b.debug(field_152949_a, "Requested commercial from Twitch");
      } else {
         field_152950_b.warn(field_152949_a, "Could not request commercial from Twitch");
      }

   }

   public void func_152916_q() {
      this.field_152951_c.func_152847_F();
      this.field_152962_n = true;
      this.func_152915_s();
   }

   public void func_152933_r() {
      this.field_152951_c.func_152854_G();
      this.field_152962_n = false;
      this.func_152915_s();
   }

   public void func_152915_s() {
      if(this.func_152934_n()) {
         float f = this.field_152953_e.field_71474_y.field_152402_L;
         boolean flag = this.field_152962_n || f <= 0.0F;
         this.field_152951_c.func_152837_b(flag?0.0F:f);
         this.field_152951_c.func_152829_a(this.func_152929_G()?0.0F:this.field_152953_e.field_71474_y.field_152401_K);
      }

   }

   public void func_152930_t() {
      GameSettings gamesettings = this.field_152953_e.field_71474_y;
      VideoParams videoparams = this.field_152951_c.func_152834_a(func_152946_b(gamesettings.field_152403_M), func_152948_a(gamesettings.field_152404_N), func_152947_c(gamesettings.field_152400_J), (float)this.field_152953_e.field_71443_c / (float)this.field_152953_e.field_71440_d);
      switch(gamesettings.field_152405_O) {
      case 0:
         videoparams.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_LOW;
         break;
      case 1:
         videoparams.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_MEDIUM;
         break;
      case 2:
         videoparams.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
      }

      if(this.field_152956_h == null) {
         this.field_152956_h = new Framebuffer(videoparams.outputWidth, videoparams.outputHeight, false);
      } else {
         this.field_152956_h.func_147613_a(videoparams.outputWidth, videoparams.outputHeight);
      }

      if(gamesettings.field_152407_Q != null && gamesettings.field_152407_Q.length() > 0) {
         for(IngestServer ingestserver : this.func_152925_v()) {
            if(ingestserver.serverUrl.equals(gamesettings.field_152407_Q)) {
               this.field_152951_c.func_152824_a(ingestserver);
               break;
            }
         }
      }

      this.field_152958_j = videoparams.targetFps;
      this.field_152957_i = gamesettings.field_152406_P;
      this.field_152951_c.func_152836_a(videoparams);
      field_152950_b.info(field_152949_a, "Streaming at {}/{} at {} kbps to {}", new Object[]{Integer.valueOf(videoparams.outputWidth), Integer.valueOf(videoparams.outputHeight), Integer.valueOf(videoparams.maxKbps), this.field_152951_c.func_152833_s().serverUrl});
      this.field_152951_c.func_152828_a((String)null, "Minecraft", (String)null);
   }

   public void func_152914_u() {
      if(this.field_152951_c.func_152819_E()) {
         field_152950_b.info(field_152949_a, "Stopped streaming to Twitch");
      } else {
         field_152950_b.warn(field_152949_a, "Could not stop streaming to Twitch");
      }

   }

   public void func_152900_a(ErrorCode p_152900_1_, AuthToken p_152900_2_) {
   }

   public void func_152897_a(ErrorCode p_152897_1_) {
      if(ErrorCode.succeeded(p_152897_1_)) {
         field_152950_b.debug(field_152949_a, "Login attempt successful");
         this.field_152961_m = true;
      } else {
         field_152950_b.warn(field_152949_a, "Login attempt unsuccessful: {} (error code {})", new Object[]{ErrorCode.getString(p_152897_1_), Integer.valueOf(p_152897_1_.getValue())});
         this.field_152961_m = false;
      }

   }

   public void func_152898_a(ErrorCode p_152898_1_, GameInfo[] p_152898_2_) {
   }

   public void func_152891_a(BroadcastController.BroadcastState p_152891_1_) {
      field_152950_b.debug(field_152949_a, "Broadcast state changed to {}", new Object[]{p_152891_1_});
      if(p_152891_1_ == BroadcastController.BroadcastState.Initialized) {
         this.field_152951_c.func_152827_a(BroadcastController.BroadcastState.Authenticated);
      }

   }

   public void func_152895_a() {
      field_152950_b.info(field_152949_a, "Logged out of twitch");
   }

   public void func_152894_a(StreamInfo p_152894_1_) {
      field_152950_b.debug(field_152949_a, "Stream info updated; {} viewers on stream ID {}", new Object[]{Integer.valueOf(p_152894_1_.viewers), Long.valueOf(p_152894_1_.streamId)});
   }

   public void func_152896_a(IngestList p_152896_1_) {
   }

   public void func_152893_b(ErrorCode p_152893_1_) {
      field_152950_b.warn(field_152949_a, "Issue submitting frame: {} (Error code {})", new Object[]{ErrorCode.getString(p_152893_1_), Integer.valueOf(p_152893_1_.getValue())});
      this.field_152953_e.field_71456_v.func_146158_b().func_146234_a(new ChatComponentText("Issue streaming frame: " + p_152893_1_ + " (" + ErrorCode.getString(p_152893_1_) + ")"), 2);
   }

   public void func_152899_b() {
      this.func_152915_s();
      field_152950_b.info(field_152949_a, "Broadcast to Twitch has started");
   }

   public void func_152901_c() {
      field_152950_b.info(field_152949_a, "Broadcast to Twitch has stopped");
   }

   public void func_152892_c(ErrorCode p_152892_1_) {
      if(p_152892_1_ == ErrorCode.TTV_EC_SOUNDFLOWER_NOT_INSTALLED) {
         IChatComponent ichatcomponent = new ChatComponentTranslation("stream.unavailable.soundflower.chat.link", new Object[0]);
         ichatcomponent.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://help.mojang.com/customer/portal/articles/1374877-configuring-soundflower-for-streaming-on-apple-computers"));
         ichatcomponent.func_150256_b().func_150228_d(Boolean.valueOf(true));
         IChatComponent ichatcomponent1 = new ChatComponentTranslation("stream.unavailable.soundflower.chat", new Object[]{ichatcomponent});
         ichatcomponent1.func_150256_b().func_150238_a(EnumChatFormatting.DARK_RED);
         this.field_152953_e.field_71456_v.func_146158_b().func_146227_a(ichatcomponent1);
      } else {
         IChatComponent ichatcomponent2 = new ChatComponentTranslation("stream.unavailable.unknown.chat", new Object[]{ErrorCode.getString(p_152892_1_)});
         ichatcomponent2.func_150256_b().func_150238_a(EnumChatFormatting.DARK_RED);
         this.field_152953_e.field_71456_v.func_146158_b().func_146227_a(ichatcomponent2);
      }

   }

   public void func_152907_a(IngestServerTester p_152907_1_, IngestServerTester.IngestTestState p_152907_2_) {
      field_152950_b.debug(field_152949_a, "Ingest test state changed to {}", new Object[]{p_152907_2_});
      if(p_152907_2_ == IngestServerTester.IngestTestState.Finished) {
         this.field_152960_l = true;
      }

   }

   public static int func_152948_a(float p_152948_0_) {
      return MathHelper.func_76141_d(10.0F + p_152948_0_ * 50.0F);
   }

   public static int func_152946_b(float p_152946_0_) {
      return MathHelper.func_76141_d(230.0F + p_152946_0_ * 3270.0F);
   }

   public static float func_152947_c(float p_152947_0_) {
      return 0.1F + p_152947_0_ * 0.1F;
   }

   public IngestServer[] func_152925_v() {
      return this.field_152951_c.func_152855_t().getServers();
   }

   public void func_152909_x() {
      IngestServerTester ingestservertester = this.field_152951_c.func_152838_J();
      if(ingestservertester != null) {
         ingestservertester.func_153042_a(this);
      }

   }

   public IngestServerTester func_152932_y() {
      return this.field_152951_c.func_152856_w();
   }

   public boolean func_152908_z() {
      return this.field_152951_c.func_152825_o();
   }

   public int func_152920_A() {
      return this.func_152934_n()?this.field_152951_c.func_152816_j().viewers:0;
   }

   public void func_176023_d(ErrorCode p_176023_1_) {
      if(ErrorCode.failed(p_176023_1_)) {
         field_152950_b.error(field_152949_a, "Chat failed to initialize");
      }

   }

   public void func_176022_e(ErrorCode p_176022_1_) {
      if(ErrorCode.failed(p_176022_1_)) {
         field_152950_b.error(field_152949_a, "Chat failed to shutdown");
      }

   }

   public void func_176017_a(ChatController.ChatState p_176017_1_) {
   }

   public void func_180605_a(String p_180605_1_, ChatRawMessage[] p_180605_2_) {
      for(ChatRawMessage chatrawmessage : p_180605_2_) {
         this.func_176027_a(chatrawmessage.userName, chatrawmessage);
         if(this.func_176028_a(chatrawmessage.modes, chatrawmessage.subscriptions, this.field_152953_e.field_71474_y.field_152409_S)) {
            IChatComponent ichatcomponent = new ChatComponentText(chatrawmessage.userName);
            IChatComponent ichatcomponent1 = new ChatComponentTranslation("chat.stream." + (chatrawmessage.action?"emote":"text"), new Object[]{this.field_152954_f, ichatcomponent, EnumChatFormatting.func_110646_a(chatrawmessage.message)});
            if(chatrawmessage.action) {
               ichatcomponent1.func_150256_b().func_150217_b(Boolean.valueOf(true));
            }

            IChatComponent ichatcomponent2 = new ChatComponentText("");
            ichatcomponent2.func_150257_a(new ChatComponentTranslation("stream.userinfo.chatTooltip", new Object[0]));

            for(IChatComponent ichatcomponent3 : GuiTwitchUserMode.func_152328_a(chatrawmessage.modes, chatrawmessage.subscriptions, (IStream)null)) {
               ichatcomponent2.func_150258_a("\n");
               ichatcomponent2.func_150257_a(ichatcomponent3);
            }

            ichatcomponent.func_150256_b().func_150209_a(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ichatcomponent2));
            ichatcomponent.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.TWITCH_USER_INFO, chatrawmessage.userName));
            this.field_152953_e.field_71456_v.func_146158_b().func_146227_a(ichatcomponent1);
         }
      }

   }

   public void func_176025_a(String p_176025_1_, ChatTokenizedMessage[] p_176025_2_) {
   }

   private void func_176027_a(String p_176027_1_, ChatRawMessage p_176027_2_) {
      ChatUserInfo chatuserinfo = (ChatUserInfo)this.field_152955_g.get(p_176027_1_);
      if(chatuserinfo == null) {
         chatuserinfo = new ChatUserInfo();
         chatuserinfo.displayName = p_176027_1_;
         this.field_152955_g.put(p_176027_1_, chatuserinfo);
      }

      chatuserinfo.subscriptions = p_176027_2_.subscriptions;
      chatuserinfo.modes = p_176027_2_.modes;
      chatuserinfo.nameColorARGB = p_176027_2_.nameColorARGB;
   }

   private boolean func_176028_a(Set<ChatUserMode> p_176028_1_, Set<ChatUserSubscription> p_176028_2_, int p_176028_3_) {
      return p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_BANNED)?false:(p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR)?true:(p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_MODERATOR)?true:(p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_STAFF)?true:(p_176028_3_ == 0?true:(p_176028_3_ == 1?p_176028_2_.contains(ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER):false)))));
   }

   public void func_176018_a(String p_176018_1_, ChatUserInfo[] p_176018_2_, ChatUserInfo[] p_176018_3_, ChatUserInfo[] p_176018_4_) {
      for(ChatUserInfo chatuserinfo : p_176018_3_) {
         this.field_152955_g.remove(chatuserinfo.displayName);
      }

      for(ChatUserInfo chatuserinfo1 : p_176018_4_) {
         this.field_152955_g.put(chatuserinfo1.displayName, chatuserinfo1);
      }

      for(ChatUserInfo chatuserinfo2 : p_176018_2_) {
         this.field_152955_g.put(chatuserinfo2.displayName, chatuserinfo2);
      }

   }

   public void func_180606_a(String p_180606_1_) {
      field_152950_b.debug(field_152949_a, "Chat connected");
   }

   public void func_180607_b(String p_180607_1_) {
      field_152950_b.debug(field_152949_a, "Chat disconnected");
      this.field_152955_g.clear();
   }

   public void func_176019_a(String p_176019_1_, String p_176019_2_) {
   }

   public void func_176021_d() {
   }

   public void func_176024_e() {
   }

   public void func_176016_c(String p_176016_1_) {
   }

   public void func_176020_d(String p_176020_1_) {
   }

   public boolean func_152927_B() {
      return this.field_176029_e != null && this.field_176029_e.equals(this.field_152951_c.func_152843_l().name);
   }

   public String func_152921_C() {
      return this.field_176029_e;
   }

   public ChatUserInfo func_152926_a(String p_152926_1_) {
      return (ChatUserInfo)this.field_152955_g.get(p_152926_1_);
   }

   public void func_152917_b(String p_152917_1_) {
      this.field_152952_d.func_175986_a(this.field_176029_e, p_152917_1_);
   }

   public boolean func_152928_D() {
      return field_152965_q && this.field_152951_c.func_152858_b();
   }

   public ErrorCode func_152912_E() {
      return !field_152965_q?ErrorCode.TTV_EC_OS_TOO_OLD:this.field_152951_c.func_152852_P();
   }

   public boolean func_152913_F() {
      return this.field_152961_m;
   }

   public void func_152910_a(boolean p_152910_1_) {
      this.field_152963_o = p_152910_1_;
      this.func_152915_s();
   }

   public boolean func_152929_G() {
      boolean flag = this.field_152953_e.field_71474_y.field_152410_T == 1;
      return this.field_152962_n || this.field_152953_e.field_71474_y.field_152401_K <= 0.0F || flag != this.field_152963_o;
   }

   public IStream.AuthFailureReason func_152918_H() {
      return this.field_152964_p;
   }

   static {
      try {
         if(Util.func_110647_a() == Util.EnumOS.WINDOWS) {
            System.loadLibrary("avutil-ttv-51");
            System.loadLibrary("swresample-ttv-0");
            System.loadLibrary("libmp3lame-ttv");
            if(System.getProperty("os.arch").contains("64")) {
               System.loadLibrary("libmfxsw64");
            } else {
               System.loadLibrary("libmfxsw32");
            }
         }

         field_152965_q = true;
      } catch (Throwable var1) {
         field_152965_q = false;
      }

   }
}
