package net.minecraft.client;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMemoryErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.FoliageColorReloadListener;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourceIndex;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.stream.IStream;
import net.minecraft.client.stream.NullStream;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.profiler.IPlayerUsage;
import net.minecraft.profiler.PlayerUsageSnooper;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.FrameTimer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class Minecraft implements IThreadListener, IPlayerUsage {
   private static final Logger field_147123_G = LogManager.getLogger();
   private static final ResourceLocation field_110444_H = new ResourceLocation("textures/gui/title/mojang.png");
   public static final boolean field_142025_a = Util.func_110647_a() == Util.EnumOS.OSX;
   public static byte[] field_71444_a = new byte[10485760];
   private static final List<DisplayMode> field_110445_I = Lists.newArrayList(new DisplayMode[]{new DisplayMode(2560, 1600), new DisplayMode(2880, 1800)});
   private final File field_130070_K;
   private final PropertyMap field_152356_J;
   private final PropertyMap field_181038_N;
   private ServerData field_71422_O;
   private TextureManager field_71446_o;
   private static Minecraft field_71432_P;
   public PlayerControllerMP field_71442_b;
   private boolean field_71431_Q;
   private boolean field_175619_R = true;
   private boolean field_71434_R;
   private CrashReport field_71433_S;
   public int field_71443_c;
   public int field_71440_d;
   private boolean field_181541_X = false;
   private Timer field_71428_T = new Timer(20.0F);
   private PlayerUsageSnooper field_71427_U = new PlayerUsageSnooper("client", this, MinecraftServer.func_130071_aq());
   public WorldClient field_71441_e;
   public RenderGlobal field_71438_f;
   private RenderManager field_175616_W;
   private RenderItem field_175621_X;
   private ItemRenderer field_175620_Y;
   public EntityPlayerSP field_71439_g;
   private Entity field_175622_Z;
   public Entity field_147125_j;
   public EffectRenderer field_71452_i;
   private final Session field_71449_j;
   private boolean field_71445_n;
   public FontRenderer field_71466_p;
   public FontRenderer field_71464_q;
   public GuiScreen field_71462_r;
   public LoadingScreenRenderer field_71461_s;
   public EntityRenderer field_71460_t;
   private int field_71429_W;
   private int field_71436_X;
   private int field_71435_Y;
   private IntegratedServer field_71437_Z;
   public GuiAchievement field_71458_u;
   public GuiIngame field_71456_v;
   public boolean field_71454_w;
   public MovingObjectPosition field_71476_x;
   public GameSettings field_71474_y;
   public MouseHelper field_71417_B;
   public final File field_71412_D;
   private final File field_110446_Y;
   private final String field_110447_Z;
   private final Proxy field_110453_aa;
   private ISaveFormat field_71469_aa;
   private static int field_71470_ab;
   private int field_71467_ac;
   private String field_71475_ae;
   private int field_71477_af;
   public boolean field_71415_G;
   long field_71423_H = func_71386_F();
   private int field_71457_ai;
   public final FrameTimer field_181542_y = new FrameTimer();
   long field_181543_z = System.nanoTime();
   private final boolean field_147129_ai;
   private final boolean field_71459_aj;
   private NetworkManager field_71453_ak;
   private boolean field_71455_al;
   public final Profiler field_71424_I = new Profiler();
   private long field_83002_am = -1L;
   private IReloadableResourceManager field_110451_am;
   private final IMetadataSerializer field_110452_an = new IMetadataSerializer();
   private final List<IResourcePack> field_110449_ao = Lists.<IResourcePack>newArrayList();
   private final DefaultResourcePack field_110450_ap;
   private ResourcePackRepository field_110448_aq;
   private LanguageManager field_135017_as;
   private IStream field_152353_at;
   private Framebuffer field_147124_at;
   private TextureMap field_147128_au;
   private SoundHandler field_147127_av;
   private MusicTicker field_147126_aw;
   private ResourceLocation field_152354_ay;
   private final MinecraftSessionService field_152355_az;
   private SkinManager field_152350_aA;
   private final Queue<FutureTask<?>> field_152351_aB = Queues.<FutureTask<?>>newArrayDeque();
   private long field_175615_aJ = 0L;
   private final Thread field_152352_aC = Thread.currentThread();
   private ModelManager field_175617_aL;
   private BlockRendererDispatcher field_175618_aM;
   volatile boolean field_71425_J = true;
   public String field_71426_K = "";
   public boolean field_175613_B = false;
   public boolean field_175614_C = false;
   public boolean field_175611_D = false;
   public boolean field_175612_E = true;
   long field_71419_L = func_71386_F();
   int field_71420_M;
   long field_71421_N = -1L;
   private String field_71465_an = "root";

   public Minecraft(GameConfiguration p_i45547_1_) {
      field_71432_P = this;
      this.field_71412_D = p_i45547_1_.field_178744_c.field_178760_a;
      this.field_110446_Y = p_i45547_1_.field_178744_c.field_178759_c;
      this.field_130070_K = p_i45547_1_.field_178744_c.field_178758_b;
      this.field_110447_Z = p_i45547_1_.field_178741_d.field_178755_b;
      this.field_152356_J = p_i45547_1_.field_178745_a.field_178750_b;
      this.field_181038_N = p_i45547_1_.field_178745_a.field_181172_c;
      this.field_110450_ap = new DefaultResourcePack((new ResourceIndex(p_i45547_1_.field_178744_c.field_178759_c, p_i45547_1_.field_178744_c.field_178757_d)).func_152782_a());
      this.field_110453_aa = p_i45547_1_.field_178745_a.field_178751_c == null?Proxy.NO_PROXY:p_i45547_1_.field_178745_a.field_178751_c;
      this.field_152355_az = (new YggdrasilAuthenticationService(p_i45547_1_.field_178745_a.field_178751_c, UUID.randomUUID().toString())).createMinecraftSessionService();
      this.field_71449_j = p_i45547_1_.field_178745_a.field_178752_a;
      field_147123_G.info("Setting user: " + this.field_71449_j.func_111285_a());
      field_147123_G.info("(Session ID is " + this.field_71449_j.func_111286_b() + ")");
      this.field_71459_aj = p_i45547_1_.field_178741_d.field_178756_a;
      this.field_71443_c = p_i45547_1_.field_178743_b.field_178764_a > 0?p_i45547_1_.field_178743_b.field_178764_a:1;
      this.field_71440_d = p_i45547_1_.field_178743_b.field_178762_b > 0?p_i45547_1_.field_178743_b.field_178762_b:1;
      this.field_71436_X = p_i45547_1_.field_178743_b.field_178764_a;
      this.field_71435_Y = p_i45547_1_.field_178743_b.field_178762_b;
      this.field_71431_Q = p_i45547_1_.field_178743_b.field_178763_c;
      this.field_147129_ai = func_147122_X();
      this.field_71437_Z = new IntegratedServer(this);
      if(p_i45547_1_.field_178742_e.field_178754_a != null) {
         this.field_71475_ae = p_i45547_1_.field_178742_e.field_178754_a;
         this.field_71477_af = p_i45547_1_.field_178742_e.field_178753_b;
      }

      ImageIO.setUseCache(false);
      Bootstrap.func_151354_b();
   }

   public void func_99999_d() {
      this.field_71425_J = true;

      try {
         this.func_71384_a();
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Initializing game");
         crashreport.func_85058_a("Initialization");
         this.func_71377_b(this.func_71396_d(crashreport));
         return;
      }

      while(true) {
         try {
            if(!this.field_71425_J) {
               break;
            }

            if(!this.field_71434_R || this.field_71433_S == null) {
               try {
                  this.func_71411_J();
               } catch (OutOfMemoryError var10) {
                  this.func_71398_f();
                  this.func_147108_a(new GuiMemoryErrorScreen());
                  System.gc();
               }
               continue;
            }

            this.func_71377_b(this.field_71433_S);
         } catch (MinecraftError var12) {
            break;
         } catch (ReportedException reportedexception) {
            this.func_71396_d(reportedexception.func_71575_a());
            this.func_71398_f();
            field_147123_G.fatal((String)"Reported exception thrown!", (Throwable)reportedexception);
            this.func_71377_b(reportedexception.func_71575_a());
            break;
         } catch (Throwable throwable1) {
            CrashReport crashreport1 = this.func_71396_d(new CrashReport("Unexpected error", throwable1));
            this.func_71398_f();
            field_147123_G.fatal("Unreported exception thrown!", throwable1);
            this.func_71377_b(crashreport1);
            break;
         } finally {
            this.func_71405_e();
         }

         return;
      }

   }

   private void func_71384_a() throws LWJGLException, IOException {
      this.field_71474_y = new GameSettings(this, this.field_71412_D);
      this.field_110449_ao.add(this.field_110450_ap);
      this.func_71389_H();
      if(this.field_71474_y.field_92119_C > 0 && this.field_71474_y.field_92118_B > 0) {
         this.field_71443_c = this.field_71474_y.field_92118_B;
         this.field_71440_d = this.field_71474_y.field_92119_C;
      }

      field_147123_G.info("LWJGL Version: " + Sys.getVersion());
      this.func_175594_ao();
      this.func_175605_an();
      this.func_175609_am();
      OpenGlHelper.func_77474_a();
      this.field_147124_at = new Framebuffer(this.field_71443_c, this.field_71440_d, true);
      this.field_147124_at.func_147604_a(0.0F, 0.0F, 0.0F, 0.0F);
      this.func_175608_ak();
      this.field_110448_aq = new ResourcePackRepository(this.field_130070_K, new File(this.field_71412_D, "server-resource-packs"), this.field_110450_ap, this.field_110452_an, this.field_71474_y);
      this.field_110451_am = new SimpleReloadableResourceManager(this.field_110452_an);
      this.field_135017_as = new LanguageManager(this.field_110452_an, this.field_71474_y.field_74363_ab);
      this.field_110451_am.func_110542_a(this.field_135017_as);
      this.func_110436_a();
      this.field_71446_o = new TextureManager(this.field_110451_am);
      this.field_110451_am.func_110542_a(this.field_71446_o);
      this.func_180510_a(this.field_71446_o);
      this.func_175595_al();
      this.field_152350_aA = new SkinManager(this.field_71446_o, new File(this.field_110446_Y, "skins"), this.field_152355_az);
      this.field_71469_aa = new AnvilSaveConverter(new File(this.field_71412_D, "saves"));
      this.field_147127_av = new SoundHandler(this.field_110451_am, this.field_71474_y);
      this.field_110451_am.func_110542_a(this.field_147127_av);
      this.field_147126_aw = new MusicTicker(this);
      this.field_71466_p = new FontRenderer(this.field_71474_y, new ResourceLocation("textures/font/ascii.png"), this.field_71446_o, false);
      if(this.field_71474_y.field_74363_ab != null) {
         this.field_71466_p.func_78264_a(this.func_152349_b());
         this.field_71466_p.func_78275_b(this.field_135017_as.func_135044_b());
      }

      this.field_71464_q = new FontRenderer(this.field_71474_y, new ResourceLocation("textures/font/ascii_sga.png"), this.field_71446_o, false);
      this.field_110451_am.func_110542_a(this.field_71466_p);
      this.field_110451_am.func_110542_a(this.field_71464_q);
      this.field_110451_am.func_110542_a(new GrassColorReloadListener());
      this.field_110451_am.func_110542_a(new FoliageColorReloadListener());
      AchievementList.field_76004_f.func_75988_a(new IStatStringFormat() {
         public String func_74535_a(String p_74535_1_) {
            try {
               return String.format(p_74535_1_, new Object[]{GameSettings.func_74298_c(Minecraft.this.field_71474_y.field_151445_Q.func_151463_i())});
            } catch (Exception exception) {
               return "Error: " + exception.getLocalizedMessage();
            }
         }
      });
      this.field_71417_B = new MouseHelper();
      this.func_71361_d("Pre startup");
      GlStateManager.func_179098_w();
      GlStateManager.func_179103_j(7425);
      GlStateManager.func_179151_a(1.0D);
      GlStateManager.func_179126_j();
      GlStateManager.func_179143_c(515);
      GlStateManager.func_179141_d();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179107_e(1029);
      GlStateManager.func_179128_n(5889);
      GlStateManager.func_179096_D();
      GlStateManager.func_179128_n(5888);
      this.func_71361_d("Startup");
      this.field_147128_au = new TextureMap("textures");
      this.field_147128_au.func_147633_a(this.field_71474_y.field_151442_I);
      this.field_71446_o.func_110580_a(TextureMap.field_110575_b, this.field_147128_au);
      this.field_71446_o.func_110577_a(TextureMap.field_110575_b);
      this.field_147128_au.func_174937_a(false, this.field_71474_y.field_151442_I > 0);
      this.field_175617_aL = new ModelManager(this.field_147128_au);
      this.field_110451_am.func_110542_a(this.field_175617_aL);
      this.field_175621_X = new RenderItem(this.field_71446_o, this.field_175617_aL);
      this.field_175616_W = new RenderManager(this.field_71446_o, this.field_175621_X);
      this.field_175620_Y = new ItemRenderer(this);
      this.field_110451_am.func_110542_a(this.field_175621_X);
      this.field_71460_t = new EntityRenderer(this, this.field_110451_am);
      this.field_110451_am.func_110542_a(this.field_71460_t);
      this.field_175618_aM = new BlockRendererDispatcher(this.field_175617_aL.func_174954_c(), this.field_71474_y);
      this.field_110451_am.func_110542_a(this.field_175618_aM);
      this.field_71438_f = new RenderGlobal(this);
      this.field_110451_am.func_110542_a(this.field_71438_f);
      this.field_71458_u = new GuiAchievement(this);
      GlStateManager.func_179083_b(0, 0, this.field_71443_c, this.field_71440_d);
      this.field_71452_i = new EffectRenderer(this.field_71441_e, this.field_71446_o);
      this.func_71361_d("Post startup");
      this.field_71456_v = new GuiIngame(this);
      if(this.field_71475_ae != null) {
         this.func_147108_a(new GuiConnecting(new GuiMainMenu(), this, this.field_71475_ae, this.field_71477_af));
      } else {
         this.func_147108_a(new GuiMainMenu());
      }

      this.field_71446_o.func_147645_c(this.field_152354_ay);
      this.field_152354_ay = null;
      this.field_71461_s = new LoadingScreenRenderer(this);
      if(this.field_71474_y.field_74353_u && !this.field_71431_Q) {
         this.func_71352_k();
      }

      try {
         Display.setVSyncEnabled(this.field_71474_y.field_74352_v);
      } catch (OpenGLException var2) {
         this.field_71474_y.field_74352_v = false;
         this.field_71474_y.func_74303_b();
      }

      this.field_71438_f.func_174966_b();
   }

   private void func_175608_ak() {
      this.field_110452_an.func_110504_a(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
      this.field_110452_an.func_110504_a(new FontMetadataSectionSerializer(), FontMetadataSection.class);
      this.field_110452_an.func_110504_a(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
      this.field_110452_an.func_110504_a(new PackMetadataSectionSerializer(), PackMetadataSection.class);
      this.field_110452_an.func_110504_a(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
   }

   private void func_175595_al() {
      try {
         this.field_152353_at = new TwitchStream(this, (Property)Iterables.getFirst(this.field_152356_J.get("twitch_access_token"), null));
      } catch (Throwable throwable) {
         this.field_152353_at = new NullStream(throwable);
         field_147123_G.error("Couldn\'t initialize twitch stream");
      }

   }

   private void func_175609_am() throws LWJGLException {
      Display.setResizable(true);
      Display.setTitle("Minecraft 1.8.9");

      try {
         Display.create((new PixelFormat()).withDepthBits(24));
      } catch (LWJGLException lwjglexception) {
         field_147123_G.error((String)"Couldn\'t set pixel format", (Throwable)lwjglexception);

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var3) {
            ;
         }

         if(this.field_71431_Q) {
            this.func_110441_Q();
         }

         Display.create();
      }

   }

   private void func_175605_an() throws LWJGLException {
      if(this.field_71431_Q) {
         Display.setFullscreen(true);
         DisplayMode displaymode = Display.getDisplayMode();
         this.field_71443_c = Math.max(1, displaymode.getWidth());
         this.field_71440_d = Math.max(1, displaymode.getHeight());
      } else {
         Display.setDisplayMode(new DisplayMode(this.field_71443_c, this.field_71440_d));
      }

   }

   private void func_175594_ao() {
      Util.EnumOS util$enumos = Util.func_110647_a();
      if(util$enumos != Util.EnumOS.OSX) {
         InputStream inputstream = null;
         InputStream inputstream1 = null;

         try {
            inputstream = this.field_110450_ap.func_152780_c(new ResourceLocation("icons/icon_16x16.png"));
            inputstream1 = this.field_110450_ap.func_152780_c(new ResourceLocation("icons/icon_32x32.png"));
            if(inputstream != null && inputstream1 != null) {
               Display.setIcon(new ByteBuffer[]{this.func_152340_a(inputstream), this.func_152340_a(inputstream1)});
            }
         } catch (IOException ioexception) {
            field_147123_G.error((String)"Couldn\'t set icon", (Throwable)ioexception);
         } finally {
            IOUtils.closeQuietly(inputstream);
            IOUtils.closeQuietly(inputstream1);
         }
      }

   }

   private static boolean func_147122_X() {
      String[] astring = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};

      for(String s : astring) {
         String s1 = System.getProperty(s);
         if(s1 != null && s1.contains("64")) {
            return true;
         }
      }

      return false;
   }

   public Framebuffer func_147110_a() {
      return this.field_147124_at;
   }

   public String func_175600_c() {
      return this.field_110447_Z;
   }

   private void func_71389_H() {
      Thread thread = new Thread("Timer hack thread") {
         public void run() {
            while(Minecraft.this.field_71425_J) {
               try {
                  Thread.sleep(2147483647L);
               } catch (InterruptedException var2) {
                  ;
               }
            }

         }
      };
      thread.setDaemon(true);
      thread.start();
   }

   public void func_71404_a(CrashReport p_71404_1_) {
      this.field_71434_R = true;
      this.field_71433_S = p_71404_1_;
   }

   public void func_71377_b(CrashReport p_71377_1_) {
      File file1 = new File(func_71410_x().field_71412_D, "crash-reports");
      File file2 = new File(file1, "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-client.txt");
      Bootstrap.func_179870_a(p_71377_1_.func_71502_e());
      if(p_71377_1_.func_71497_f() != null) {
         Bootstrap.func_179870_a("#@!@# Game crashed! Crash report saved to: #@!@# " + p_71377_1_.func_71497_f());
         System.exit(-1);
      } else if(p_71377_1_.func_147149_a(file2)) {
         Bootstrap.func_179870_a("#@!@# Game crashed! Crash report saved to: #@!@# " + file2.getAbsolutePath());
         System.exit(-1);
      } else {
         Bootstrap.func_179870_a("#@?@# Game crashed! Crash report could not be saved. #@?@#");
         System.exit(-2);
      }

   }

   public boolean func_152349_b() {
      return this.field_135017_as.func_135042_a() || this.field_71474_y.field_151455_aw;
   }

   public void func_110436_a() {
      List<IResourcePack> list = Lists.newArrayList(this.field_110449_ao);

      for(ResourcePackRepository.Entry resourcepackrepository$entry : this.field_110448_aq.func_110613_c()) {
         list.add(resourcepackrepository$entry.func_110514_c());
      }

      if(this.field_110448_aq.func_148530_e() != null) {
         list.add(this.field_110448_aq.func_148530_e());
      }

      try {
         this.field_110451_am.func_110541_a(list);
      } catch (RuntimeException runtimeexception) {
         field_147123_G.info((String)"Caught error stitching, removing all assigned resourcepacks", (Throwable)runtimeexception);
         list.clear();
         list.addAll(this.field_110449_ao);
         this.field_110448_aq.func_148527_a(Collections.<ResourcePackRepository.Entry>emptyList());
         this.field_110451_am.func_110541_a(list);
         this.field_71474_y.field_151453_l.clear();
         this.field_71474_y.field_183018_l.clear();
         this.field_71474_y.func_74303_b();
      }

      this.field_135017_as.func_135043_a(list);
      if(this.field_71438_f != null) {
         this.field_71438_f.func_72712_a();
      }

   }

   private ByteBuffer func_152340_a(InputStream p_152340_1_) throws IOException {
      BufferedImage bufferedimage = ImageIO.read(p_152340_1_);
      int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
      ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);

      for(int i : aint) {
         bytebuffer.putInt(i << 8 | i >> 24 & 255);
      }

      bytebuffer.flip();
      return bytebuffer;
   }

   private void func_110441_Q() throws LWJGLException {
      Set<DisplayMode> set = Sets.<DisplayMode>newHashSet();
      Collections.addAll(set, Display.getAvailableDisplayModes());
      DisplayMode displaymode = Display.getDesktopDisplayMode();
      if(!set.contains(displaymode) && Util.func_110647_a() == Util.EnumOS.OSX) {
         label53:
         for(DisplayMode displaymode1 : field_110445_I) {
            boolean flag = true;

            for(DisplayMode displaymode2 : set) {
               if(displaymode2.getBitsPerPixel() == 32 && displaymode2.getWidth() == displaymode1.getWidth() && displaymode2.getHeight() == displaymode1.getHeight()) {
                  flag = false;
                  break;
               }
            }

            if(!flag) {
               Iterator iterator = set.iterator();

               DisplayMode displaymode3;
               while(true) {
                  if(!iterator.hasNext()) {
                     continue label53;
                  }

                  displaymode3 = (DisplayMode)iterator.next();
                  if(displaymode3.getBitsPerPixel() == 32 && displaymode3.getWidth() == displaymode1.getWidth() / 2 && displaymode3.getHeight() == displaymode1.getHeight() / 2) {
                     break;
                  }
               }

               displaymode = displaymode3;
            }
         }
      }

      Display.setDisplayMode(displaymode);
      this.field_71443_c = displaymode.getWidth();
      this.field_71440_d = displaymode.getHeight();
   }

   private void func_180510_a(TextureManager p_180510_1_) throws LWJGLException {
      ScaledResolution scaledresolution = new ScaledResolution(this);
      int i = scaledresolution.func_78325_e();
      Framebuffer framebuffer = new Framebuffer(scaledresolution.func_78326_a() * i, scaledresolution.func_78328_b() * i, true);
      framebuffer.func_147610_a(false);
      GlStateManager.func_179128_n(5889);
      GlStateManager.func_179096_D();
      GlStateManager.func_179130_a(0.0D, (double)scaledresolution.func_78326_a(), (double)scaledresolution.func_78328_b(), 0.0D, 1000.0D, 3000.0D);
      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179096_D();
      GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
      GlStateManager.func_179140_f();
      GlStateManager.func_179106_n();
      GlStateManager.func_179097_i();
      GlStateManager.func_179098_w();
      InputStream inputstream = null;

      try {
         inputstream = this.field_110450_ap.func_110590_a(field_110444_H);
         this.field_152354_ay = p_180510_1_.func_110578_a("logo", new DynamicTexture(ImageIO.read(inputstream)));
         p_180510_1_.func_110577_a(this.field_152354_ay);
      } catch (IOException ioexception) {
         field_147123_G.error((String)("Unable to load logo: " + field_110444_H), (Throwable)ioexception);
      } finally {
         IOUtils.closeQuietly(inputstream);
      }

      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      worldrenderer.func_181662_b(0.0D, (double)this.field_71440_d, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      worldrenderer.func_181662_b((double)this.field_71443_c, (double)this.field_71440_d, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      worldrenderer.func_181662_b((double)this.field_71443_c, 0.0D, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      worldrenderer.func_181662_b(0.0D, 0.0D, 0.0D).func_181673_a(0.0D, 0.0D).func_181669_b(255, 255, 255, 255).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      int j = 256;
      int k = 256;
      this.func_181536_a((scaledresolution.func_78326_a() - j) / 2, (scaledresolution.func_78328_b() - k) / 2, 0, 0, j, k, 255, 255, 255, 255);
      GlStateManager.func_179140_f();
      GlStateManager.func_179106_n();
      framebuffer.func_147609_e();
      framebuffer.func_147615_c(scaledresolution.func_78326_a() * i, scaledresolution.func_78328_b() * i);
      GlStateManager.func_179141_d();
      GlStateManager.func_179092_a(516, 0.1F);
      this.func_175601_h();
   }

   public void func_181536_a(int p_181536_1_, int p_181536_2_, int p_181536_3_, int p_181536_4_, int p_181536_5_, int p_181536_6_, int p_181536_7_, int p_181536_8_, int p_181536_9_, int p_181536_10_) {
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      WorldRenderer worldrenderer = Tessellator.func_178181_a().func_178180_c();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
      worldrenderer.func_181662_b((double)p_181536_1_, (double)(p_181536_2_ + p_181536_6_), 0.0D).func_181673_a((double)((float)p_181536_3_ * f), (double)((float)(p_181536_4_ + p_181536_6_) * f1)).func_181669_b(p_181536_7_, p_181536_8_, p_181536_9_, p_181536_10_).func_181675_d();
      worldrenderer.func_181662_b((double)(p_181536_1_ + p_181536_5_), (double)(p_181536_2_ + p_181536_6_), 0.0D).func_181673_a((double)((float)(p_181536_3_ + p_181536_5_) * f), (double)((float)(p_181536_4_ + p_181536_6_) * f1)).func_181669_b(p_181536_7_, p_181536_8_, p_181536_9_, p_181536_10_).func_181675_d();
      worldrenderer.func_181662_b((double)(p_181536_1_ + p_181536_5_), (double)p_181536_2_, 0.0D).func_181673_a((double)((float)(p_181536_3_ + p_181536_5_) * f), (double)((float)p_181536_4_ * f1)).func_181669_b(p_181536_7_, p_181536_8_, p_181536_9_, p_181536_10_).func_181675_d();
      worldrenderer.func_181662_b((double)p_181536_1_, (double)p_181536_2_, 0.0D).func_181673_a((double)((float)p_181536_3_ * f), (double)((float)p_181536_4_ * f1)).func_181669_b(p_181536_7_, p_181536_8_, p_181536_9_, p_181536_10_).func_181675_d();
      Tessellator.func_178181_a().func_78381_a();
   }

   public ISaveFormat func_71359_d() {
      return this.field_71469_aa;
   }

   public void func_147108_a(GuiScreen p_147108_1_) {
      if(this.field_71462_r != null) {
         this.field_71462_r.func_146281_b();
      }

      if(p_147108_1_ == null && this.field_71441_e == null) {
         p_147108_1_ = new GuiMainMenu();
      } else if(p_147108_1_ == null && this.field_71439_g.func_110143_aJ() <= 0.0F) {
         p_147108_1_ = new GuiGameOver();
      }

      if(p_147108_1_ instanceof GuiMainMenu) {
         this.field_71474_y.field_74330_P = false;
         this.field_71456_v.func_146158_b().func_146231_a();
      }

      this.field_71462_r = (GuiScreen)p_147108_1_;
      if(p_147108_1_ != null) {
         this.func_71364_i();
         ScaledResolution scaledresolution = new ScaledResolution(this);
         int i = scaledresolution.func_78326_a();
         int j = scaledresolution.func_78328_b();
         ((GuiScreen)p_147108_1_).func_146280_a(this, i, j);
         this.field_71454_w = false;
      } else {
         this.field_147127_av.func_147687_e();
         this.func_71381_h();
      }

   }

   private void func_71361_d(String p_71361_1_) {
      if(this.field_175619_R) {
         int i = GL11.glGetError();
         if(i != 0) {
            String s = GLU.gluErrorString(i);
            field_147123_G.error("########## GL ERROR ##########");
            field_147123_G.error("@ " + p_71361_1_);
            field_147123_G.error(i + ": " + s);
         }

      }
   }

   public void func_71405_e() {
      try {
         this.field_152353_at.func_152923_i();
         field_147123_G.info("Stopping!");

         try {
            this.func_71403_a((WorldClient)null);
         } catch (Throwable var5) {
            ;
         }

         this.field_147127_av.func_147685_d();
      } finally {
         Display.destroy();
         if(!this.field_71434_R) {
            System.exit(0);
         }

      }

      System.gc();
   }

   private void func_71411_J() throws IOException {
      long i = System.nanoTime();
      this.field_71424_I.func_76320_a("root");
      if(Display.isCreated() && Display.isCloseRequested()) {
         this.func_71400_g();
      }

      if(this.field_71445_n && this.field_71441_e != null) {
         float f = this.field_71428_T.field_74281_c;
         this.field_71428_T.func_74275_a();
         this.field_71428_T.field_74281_c = f;
      } else {
         this.field_71428_T.func_74275_a();
      }

      this.field_71424_I.func_76320_a("scheduledExecutables");
      synchronized(this.field_152351_aB) {
         while(!this.field_152351_aB.isEmpty()) {
            Util.func_181617_a((FutureTask)this.field_152351_aB.poll(), field_147123_G);
         }
      }

      this.field_71424_I.func_76319_b();
      long l = System.nanoTime();
      this.field_71424_I.func_76320_a("tick");

      for(int j = 0; j < this.field_71428_T.field_74280_b; ++j) {
         this.func_71407_l();
      }

      this.field_71424_I.func_76318_c("preRenderErrors");
      long i1 = System.nanoTime() - l;
      this.func_71361_d("Pre render");
      this.field_71424_I.func_76318_c("sound");
      this.field_147127_av.func_147691_a(this.field_71439_g, this.field_71428_T.field_74281_c);
      this.field_71424_I.func_76319_b();
      this.field_71424_I.func_76320_a("render");
      GlStateManager.func_179094_E();
      GlStateManager.func_179086_m(16640);
      this.field_147124_at.func_147610_a(true);
      this.field_71424_I.func_76320_a("display");
      GlStateManager.func_179098_w();
      if(this.field_71439_g != null && this.field_71439_g.func_70094_T()) {
         this.field_71474_y.field_74320_O = 0;
      }

      this.field_71424_I.func_76319_b();
      if(!this.field_71454_w) {
         this.field_71424_I.func_76318_c("gameRenderer");
         this.field_71460_t.func_181560_a(this.field_71428_T.field_74281_c, i);
         this.field_71424_I.func_76319_b();
      }

      this.field_71424_I.func_76319_b();
      if(this.field_71474_y.field_74330_P && this.field_71474_y.field_74329_Q && !this.field_71474_y.field_74319_N) {
         if(!this.field_71424_I.field_76327_a) {
            this.field_71424_I.func_76317_a();
         }

         this.field_71424_I.field_76327_a = true;
         this.func_71366_a(i1);
      } else {
         this.field_71424_I.field_76327_a = false;
         this.field_71421_N = System.nanoTime();
      }

      this.field_71458_u.func_146254_a();
      this.field_147124_at.func_147609_e();
      GlStateManager.func_179121_F();
      GlStateManager.func_179094_E();
      this.field_147124_at.func_147615_c(this.field_71443_c, this.field_71440_d);
      GlStateManager.func_179121_F();
      GlStateManager.func_179094_E();
      this.field_71460_t.func_152430_c(this.field_71428_T.field_74281_c);
      GlStateManager.func_179121_F();
      this.field_71424_I.func_76320_a("root");
      this.func_175601_h();
      Thread.yield();
      this.field_71424_I.func_76320_a("stream");
      this.field_71424_I.func_76320_a("update");
      this.field_152353_at.func_152935_j();
      this.field_71424_I.func_76318_c("submit");
      this.field_152353_at.func_152922_k();
      this.field_71424_I.func_76319_b();
      this.field_71424_I.func_76319_b();
      this.func_71361_d("Post render");
      ++this.field_71420_M;
      this.field_71445_n = this.func_71356_B() && this.field_71462_r != null && this.field_71462_r.func_73868_f() && !this.field_71437_Z.func_71344_c();
      long k = System.nanoTime();
      this.field_181542_y.func_181747_a(k - this.field_181543_z);
      this.field_181543_z = k;

      while(func_71386_F() >= this.field_71419_L + 1000L) {
         field_71470_ab = this.field_71420_M;
         this.field_71426_K = String.format("%d fps (%d chunk update%s) T: %s%s%s%s%s", new Object[]{Integer.valueOf(field_71470_ab), Integer.valueOf(RenderChunk.field_178592_a), RenderChunk.field_178592_a != 1?"s":"", (float)this.field_71474_y.field_74350_i == GameSettings.Options.FRAMERATE_LIMIT.func_148267_f()?"inf":Integer.valueOf(this.field_71474_y.field_74350_i), this.field_71474_y.field_74352_v?" vsync":"", this.field_71474_y.field_74347_j?"":" fast", this.field_71474_y.field_74345_l == 0?"":(this.field_71474_y.field_74345_l == 1?" fast-clouds":" fancy-clouds"), OpenGlHelper.func_176075_f()?" vbo":""});
         RenderChunk.field_178592_a = 0;
         this.field_71419_L += 1000L;
         this.field_71420_M = 0;
         this.field_71427_U.func_76471_b();
         if(!this.field_71427_U.func_76468_d()) {
            this.field_71427_U.func_76463_a();
         }
      }

      if(this.func_147107_h()) {
         this.field_71424_I.func_76320_a("fpslimit_wait");
         Display.sync(this.func_90020_K());
         this.field_71424_I.func_76319_b();
      }

      this.field_71424_I.func_76319_b();
   }

   public void func_175601_h() {
      this.field_71424_I.func_76320_a("display_update");
      Display.update();
      this.field_71424_I.func_76319_b();
      this.func_175604_i();
   }

   protected void func_175604_i() {
      if(!this.field_71431_Q && Display.wasResized()) {
         int i = this.field_71443_c;
         int j = this.field_71440_d;
         this.field_71443_c = Display.getWidth();
         this.field_71440_d = Display.getHeight();
         if(this.field_71443_c != i || this.field_71440_d != j) {
            if(this.field_71443_c <= 0) {
               this.field_71443_c = 1;
            }

            if(this.field_71440_d <= 0) {
               this.field_71440_d = 1;
            }

            this.func_71370_a(this.field_71443_c, this.field_71440_d);
         }
      }

   }

   public int func_90020_K() {
      return this.field_71441_e == null && this.field_71462_r != null?30:this.field_71474_y.field_74350_i;
   }

   public boolean func_147107_h() {
      return (float)this.func_90020_K() < GameSettings.Options.FRAMERATE_LIMIT.func_148267_f();
   }

   public void func_71398_f() {
      try {
         field_71444_a = new byte[0];
         this.field_71438_f.func_72728_f();
      } catch (Throwable var3) {
         ;
      }

      try {
         System.gc();
         this.func_71403_a((WorldClient)null);
      } catch (Throwable var2) {
         ;
      }

      System.gc();
   }

   private void func_71383_b(int p_71383_1_) {
      List<Profiler.Result> list = this.field_71424_I.func_76321_b(this.field_71465_an);
      if(list != null && !list.isEmpty()) {
         Profiler.Result profiler$result = (Profiler.Result)list.remove(0);
         if(p_71383_1_ == 0) {
            if(profiler$result.field_76331_c.length() > 0) {
               int i = this.field_71465_an.lastIndexOf(".");
               if(i >= 0) {
                  this.field_71465_an = this.field_71465_an.substring(0, i);
               }
            }
         } else {
            --p_71383_1_;
            if(p_71383_1_ < list.size() && !((Profiler.Result)list.get(p_71383_1_)).field_76331_c.equals("unspecified")) {
               if(this.field_71465_an.length() > 0) {
                  this.field_71465_an = this.field_71465_an + ".";
               }

               this.field_71465_an = this.field_71465_an + ((Profiler.Result)list.get(p_71383_1_)).field_76331_c;
            }
         }

      }
   }

   private void func_71366_a(long p_71366_1_) {
      if(this.field_71424_I.field_76327_a) {
         List<Profiler.Result> list = this.field_71424_I.func_76321_b(this.field_71465_an);
         Profiler.Result profiler$result = (Profiler.Result)list.remove(0);
         GlStateManager.func_179086_m(256);
         GlStateManager.func_179128_n(5889);
         GlStateManager.func_179142_g();
         GlStateManager.func_179096_D();
         GlStateManager.func_179130_a(0.0D, (double)this.field_71443_c, (double)this.field_71440_d, 0.0D, 1000.0D, 3000.0D);
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179096_D();
         GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
         GL11.glLineWidth(1.0F);
         GlStateManager.func_179090_x();
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer worldrenderer = tessellator.func_178180_c();
         int i = 160;
         int j = this.field_71443_c - i - 10;
         int k = this.field_71440_d - i * 2;
         GlStateManager.func_179147_l();
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
         worldrenderer.func_181662_b((double)((float)j - (float)i * 1.1F), (double)((float)k - (float)i * 0.6F - 16.0F), 0.0D).func_181669_b(200, 0, 0, 0).func_181675_d();
         worldrenderer.func_181662_b((double)((float)j - (float)i * 1.1F), (double)(k + i * 2), 0.0D).func_181669_b(200, 0, 0, 0).func_181675_d();
         worldrenderer.func_181662_b((double)((float)j + (float)i * 1.1F), (double)(k + i * 2), 0.0D).func_181669_b(200, 0, 0, 0).func_181675_d();
         worldrenderer.func_181662_b((double)((float)j + (float)i * 1.1F), (double)((float)k - (float)i * 0.6F - 16.0F), 0.0D).func_181669_b(200, 0, 0, 0).func_181675_d();
         tessellator.func_78381_a();
         GlStateManager.func_179084_k();
         double d0 = 0.0D;

         for(int l = 0; l < list.size(); ++l) {
            Profiler.Result profiler$result1 = (Profiler.Result)list.get(l);
            int i1 = MathHelper.func_76128_c(profiler$result1.field_76332_a / 4.0D) + 1;
            worldrenderer.func_181668_a(6, DefaultVertexFormats.field_181706_f);
            int j1 = profiler$result1.func_76329_a();
            int k1 = j1 >> 16 & 255;
            int l1 = j1 >> 8 & 255;
            int i2 = j1 & 255;
            worldrenderer.func_181662_b((double)j, (double)k, 0.0D).func_181669_b(k1, l1, i2, 255).func_181675_d();

            for(int j2 = i1; j2 >= 0; --j2) {
               float f = (float)((d0 + profiler$result1.field_76332_a * (double)j2 / (double)i1) * 3.1415927410125732D * 2.0D / 100.0D);
               float f1 = MathHelper.func_76126_a(f) * (float)i;
               float f2 = MathHelper.func_76134_b(f) * (float)i * 0.5F;
               worldrenderer.func_181662_b((double)((float)j + f1), (double)((float)k - f2), 0.0D).func_181669_b(k1, l1, i2, 255).func_181675_d();
            }

            tessellator.func_78381_a();
            worldrenderer.func_181668_a(5, DefaultVertexFormats.field_181706_f);

            for(int i3 = i1; i3 >= 0; --i3) {
               float f3 = (float)((d0 + profiler$result1.field_76332_a * (double)i3 / (double)i1) * 3.1415927410125732D * 2.0D / 100.0D);
               float f4 = MathHelper.func_76126_a(f3) * (float)i;
               float f5 = MathHelper.func_76134_b(f3) * (float)i * 0.5F;
               worldrenderer.func_181662_b((double)((float)j + f4), (double)((float)k - f5), 0.0D).func_181669_b(k1 >> 1, l1 >> 1, i2 >> 1, 255).func_181675_d();
               worldrenderer.func_181662_b((double)((float)j + f4), (double)((float)k - f5 + 10.0F), 0.0D).func_181669_b(k1 >> 1, l1 >> 1, i2 >> 1, 255).func_181675_d();
            }

            tessellator.func_78381_a();
            d0 += profiler$result1.field_76332_a;
         }

         DecimalFormat decimalformat = new DecimalFormat("##0.00");
         GlStateManager.func_179098_w();
         String s = "";
         if(!profiler$result.field_76331_c.equals("unspecified")) {
            s = s + "[0] ";
         }

         if(profiler$result.field_76331_c.length() == 0) {
            s = s + "ROOT ";
         } else {
            s = s + profiler$result.field_76331_c + " ";
         }

         int l2 = 16777215;
         this.field_71466_p.func_175063_a(s, (float)(j - i), (float)(k - i / 2 - 16), l2);
         this.field_71466_p.func_175063_a(s = decimalformat.format(profiler$result.field_76330_b) + "%", (float)(j + i - this.field_71466_p.func_78256_a(s)), (float)(k - i / 2 - 16), l2);

         for(int k2 = 0; k2 < list.size(); ++k2) {
            Profiler.Result profiler$result2 = (Profiler.Result)list.get(k2);
            String s1 = "";
            if(profiler$result2.field_76331_c.equals("unspecified")) {
               s1 = s1 + "[?] ";
            } else {
               s1 = s1 + "[" + (k2 + 1) + "] ";
            }

            s1 = s1 + profiler$result2.field_76331_c;
            this.field_71466_p.func_175063_a(s1, (float)(j - i), (float)(k + i / 2 + k2 * 8 + 20), profiler$result2.func_76329_a());
            this.field_71466_p.func_175063_a(s1 = decimalformat.format(profiler$result2.field_76332_a) + "%", (float)(j + i - 50 - this.field_71466_p.func_78256_a(s1)), (float)(k + i / 2 + k2 * 8 + 20), profiler$result2.func_76329_a());
            this.field_71466_p.func_175063_a(s1 = decimalformat.format(profiler$result2.field_76330_b) + "%", (float)(j + i - this.field_71466_p.func_78256_a(s1)), (float)(k + i / 2 + k2 * 8 + 20), profiler$result2.func_76329_a());
         }

      }
   }

   public void func_71400_g() {
      this.field_71425_J = false;
   }

   public void func_71381_h() {
      if(Display.isActive()) {
         if(!this.field_71415_G) {
            this.field_71415_G = true;
            this.field_71417_B.func_74372_a();
            this.func_147108_a((GuiScreen)null);
            this.field_71429_W = 10000;
         }
      }
   }

   public void func_71364_i() {
      if(this.field_71415_G) {
         KeyBinding.func_74506_a();
         this.field_71415_G = false;
         this.field_71417_B.func_74373_b();
      }
   }

   public void func_71385_j() {
      if(this.field_71462_r == null) {
         this.func_147108_a(new GuiIngameMenu());
         if(this.func_71356_B() && !this.field_71437_Z.func_71344_c()) {
            this.field_147127_av.func_147689_b();
         }

      }
   }

   private void func_147115_a(boolean p_147115_1_) {
      if(!p_147115_1_) {
         this.field_71429_W = 0;
      }

      if(this.field_71429_W <= 0 && !this.field_71439_g.func_71039_bw()) {
         if(p_147115_1_ && this.field_71476_x != null && this.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos blockpos = this.field_71476_x.func_178782_a();
            if(this.field_71441_e.func_180495_p(blockpos).func_177230_c().func_149688_o() != Material.field_151579_a && this.field_71442_b.func_180512_c(blockpos, this.field_71476_x.field_178784_b)) {
               this.field_71452_i.func_180532_a(blockpos, this.field_71476_x.field_178784_b);
               this.field_71439_g.func_71038_i();
            }

         } else {
            this.field_71442_b.func_78767_c();
         }
      }
   }

   private void func_147116_af() {
      if(this.field_71429_W <= 0) {
         this.field_71439_g.func_71038_i();
         if(this.field_71476_x == null) {
            field_147123_G.error("Null returned as \'hitResult\', this shouldn\'t happen!");
            if(this.field_71442_b.func_78762_g()) {
               this.field_71429_W = 10;
            }

         } else {
            switch(this.field_71476_x.field_72313_a) {
            case ENTITY:
               this.field_71442_b.func_78764_a(this.field_71439_g, this.field_71476_x.field_72308_g);
               break;
            case BLOCK:
               BlockPos blockpos = this.field_71476_x.func_178782_a();
               if(this.field_71441_e.func_180495_p(blockpos).func_177230_c().func_149688_o() != Material.field_151579_a) {
                  this.field_71442_b.func_180511_b(blockpos, this.field_71476_x.field_178784_b);
                  break;
               }
            case MISS:
            default:
               if(this.field_71442_b.func_78762_g()) {
                  this.field_71429_W = 10;
               }
            }

         }
      }
   }

   private void func_147121_ag() {
      if(!this.field_71442_b.func_181040_m()) {
         this.field_71467_ac = 4;
         boolean flag = true;
         ItemStack itemstack = this.field_71439_g.field_71071_by.func_70448_g();
         if(this.field_71476_x == null) {
            field_147123_G.warn("Null returned as \'hitResult\', this shouldn\'t happen!");
         } else {
            switch(this.field_71476_x.field_72313_a) {
            case ENTITY:
               if(this.field_71442_b.func_178894_a(this.field_71439_g, this.field_71476_x.field_72308_g, this.field_71476_x)) {
                  flag = false;
               } else if(this.field_71442_b.func_78768_b(this.field_71439_g, this.field_71476_x.field_72308_g)) {
                  flag = false;
               }
               break;
            case BLOCK:
               BlockPos blockpos = this.field_71476_x.func_178782_a();
               if(this.field_71441_e.func_180495_p(blockpos).func_177230_c().func_149688_o() != Material.field_151579_a) {
                  int i = itemstack != null?itemstack.field_77994_a:0;
                  if(this.field_71442_b.func_178890_a(this.field_71439_g, this.field_71441_e, itemstack, blockpos, this.field_71476_x.field_178784_b, this.field_71476_x.field_72307_f)) {
                     flag = false;
                     this.field_71439_g.func_71038_i();
                  }

                  if(itemstack == null) {
                     return;
                  }

                  if(itemstack.field_77994_a == 0) {
                     this.field_71439_g.field_71071_by.field_70462_a[this.field_71439_g.field_71071_by.field_70461_c] = null;
                  } else if(itemstack.field_77994_a != i || this.field_71442_b.func_78758_h()) {
                     this.field_71460_t.field_78516_c.func_78444_b();
                  }
               }
            }
         }

         if(flag) {
            ItemStack itemstack1 = this.field_71439_g.field_71071_by.func_70448_g();
            if(itemstack1 != null && this.field_71442_b.func_78769_a(this.field_71439_g, this.field_71441_e, itemstack1)) {
               this.field_71460_t.field_78516_c.func_78445_c();
            }
         }

      }
   }

   public void func_71352_k() {
      try {
         this.field_71431_Q = !this.field_71431_Q;
         this.field_71474_y.field_74353_u = this.field_71431_Q;
         if(this.field_71431_Q) {
            this.func_110441_Q();
            this.field_71443_c = Display.getDisplayMode().getWidth();
            this.field_71440_d = Display.getDisplayMode().getHeight();
            if(this.field_71443_c <= 0) {
               this.field_71443_c = 1;
            }

            if(this.field_71440_d <= 0) {
               this.field_71440_d = 1;
            }
         } else {
            Display.setDisplayMode(new DisplayMode(this.field_71436_X, this.field_71435_Y));
            this.field_71443_c = this.field_71436_X;
            this.field_71440_d = this.field_71435_Y;
            if(this.field_71443_c <= 0) {
               this.field_71443_c = 1;
            }

            if(this.field_71440_d <= 0) {
               this.field_71440_d = 1;
            }
         }

         if(this.field_71462_r != null) {
            this.func_71370_a(this.field_71443_c, this.field_71440_d);
         } else {
            this.func_147119_ah();
         }

         Display.setFullscreen(this.field_71431_Q);
         Display.setVSyncEnabled(this.field_71474_y.field_74352_v);
         this.func_175601_h();
      } catch (Exception exception) {
         field_147123_G.error((String)"Couldn\'t toggle fullscreen", (Throwable)exception);
      }

   }

   private void func_71370_a(int p_71370_1_, int p_71370_2_) {
      this.field_71443_c = Math.max(1, p_71370_1_);
      this.field_71440_d = Math.max(1, p_71370_2_);
      if(this.field_71462_r != null) {
         ScaledResolution scaledresolution = new ScaledResolution(this);
         this.field_71462_r.func_175273_b(this, scaledresolution.func_78326_a(), scaledresolution.func_78328_b());
      }

      this.field_71461_s = new LoadingScreenRenderer(this);
      this.func_147119_ah();
   }

   private void func_147119_ah() {
      this.field_147124_at.func_147613_a(this.field_71443_c, this.field_71440_d);
      if(this.field_71460_t != null) {
         this.field_71460_t.func_147704_a(this.field_71443_c, this.field_71440_d);
      }

   }

   public MusicTicker func_181535_r() {
      return this.field_147126_aw;
   }

   public void func_71407_l() throws IOException {
      if(this.field_71467_ac > 0) {
         --this.field_71467_ac;
      }

      this.field_71424_I.func_76320_a("gui");
      if(!this.field_71445_n) {
         this.field_71456_v.func_73831_a();
      }

      this.field_71424_I.func_76319_b();
      this.field_71460_t.func_78473_a(1.0F);
      this.field_71424_I.func_76320_a("gameMode");
      if(!this.field_71445_n && this.field_71441_e != null) {
         this.field_71442_b.func_78765_e();
      }

      this.field_71424_I.func_76318_c("textures");
      if(!this.field_71445_n) {
         this.field_71446_o.func_110550_d();
      }

      if(this.field_71462_r == null && this.field_71439_g != null) {
         if(this.field_71439_g.func_110143_aJ() <= 0.0F) {
            this.func_147108_a((GuiScreen)null);
         } else if(this.field_71439_g.func_70608_bn() && this.field_71441_e != null) {
            this.func_147108_a(new GuiSleepMP());
         }
      } else if(this.field_71462_r != null && this.field_71462_r instanceof GuiSleepMP && !this.field_71439_g.func_70608_bn()) {
         this.func_147108_a((GuiScreen)null);
      }

      if(this.field_71462_r != null) {
         this.field_71429_W = 10000;
      }

      if(this.field_71462_r != null) {
         try {
            this.field_71462_r.func_146269_k();
         } catch (Throwable throwable1) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable1, "Updating screen events");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Affected screen");
            crashreportcategory.func_71500_a("Screen name", new Callable<String>() {
               public String call() throws Exception {
                  return Minecraft.this.field_71462_r.getClass().getCanonicalName();
               }
            });
            throw new ReportedException(crashreport);
         }

         if(this.field_71462_r != null) {
            try {
               this.field_71462_r.func_73876_c();
            } catch (Throwable throwable) {
               CrashReport crashreport1 = CrashReport.func_85055_a(throwable, "Ticking screen");
               CrashReportCategory crashreportcategory1 = crashreport1.func_85058_a("Affected screen");
               crashreportcategory1.func_71500_a("Screen name", new Callable<String>() {
                  public String call() throws Exception {
                     return Minecraft.this.field_71462_r.getClass().getCanonicalName();
                  }
               });
               throw new ReportedException(crashreport1);
            }
         }
      }

      if(this.field_71462_r == null || this.field_71462_r.field_146291_p) {
         this.field_71424_I.func_76318_c("mouse");

         while(Mouse.next()) {
            int i = Mouse.getEventButton();
            KeyBinding.func_74510_a(i - 100, Mouse.getEventButtonState());
            if(Mouse.getEventButtonState()) {
               if(this.field_71439_g.func_175149_v() && i == 2) {
                  this.field_71456_v.func_175187_g().func_175261_b();
               } else {
                  KeyBinding.func_74507_a(i - 100);
               }
            }

            long i1 = func_71386_F() - this.field_71423_H;
            if(i1 <= 200L) {
               int j = Mouse.getEventDWheel();
               if(j != 0) {
                  if(this.field_71439_g.func_175149_v()) {
                     j = j < 0?-1:1;
                     if(this.field_71456_v.func_175187_g().func_175262_a()) {
                        this.field_71456_v.func_175187_g().func_175259_b(-j);
                     } else {
                        float f = MathHelper.func_76131_a(this.field_71439_g.field_71075_bZ.func_75093_a() + (float)j * 0.005F, 0.0F, 0.2F);
                        this.field_71439_g.field_71075_bZ.func_75092_a(f);
                     }
                  } else {
                     this.field_71439_g.field_71071_by.func_70453_c(j);
                  }
               }

               if(this.field_71462_r == null) {
                  if(!this.field_71415_G && Mouse.getEventButtonState()) {
                     this.func_71381_h();
                  }
               } else if(this.field_71462_r != null) {
                  this.field_71462_r.func_146274_d();
               }
            }
         }

         if(this.field_71429_W > 0) {
            --this.field_71429_W;
         }

         this.field_71424_I.func_76318_c("keyboard");

         while(Keyboard.next()) {
            int k = Keyboard.getEventKey() == 0?Keyboard.getEventCharacter() + 256:Keyboard.getEventKey();
            KeyBinding.func_74510_a(k, Keyboard.getEventKeyState());
            if(Keyboard.getEventKeyState()) {
               KeyBinding.func_74507_a(k);
            }

            if(this.field_83002_am > 0L) {
               if(func_71386_F() - this.field_83002_am >= 6000L) {
                  throw new ReportedException(new CrashReport("Manually triggered debug crash", new Throwable()));
               }

               if(!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61)) {
                  this.field_83002_am = -1L;
               }
            } else if(Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
               this.field_83002_am = func_71386_F();
            }

            this.func_152348_aa();
            if(Keyboard.getEventKeyState()) {
               if(k == 62 && this.field_71460_t != null) {
                  this.field_71460_t.func_175071_c();
               }

               if(this.field_71462_r != null) {
                  this.field_71462_r.func_146282_l();
               } else {
                  if(k == 1) {
                     this.func_71385_j();
                  }

                  if(k == 32 && Keyboard.isKeyDown(61) && this.field_71456_v != null) {
                     this.field_71456_v.func_146158_b().func_146231_a();
                  }

                  if(k == 31 && Keyboard.isKeyDown(61)) {
                     this.func_110436_a();
                  }

                  if(k == 17 && Keyboard.isKeyDown(61)) {
                     ;
                  }

                  if(k == 18 && Keyboard.isKeyDown(61)) {
                     ;
                  }

                  if(k == 47 && Keyboard.isKeyDown(61)) {
                     ;
                  }

                  if(k == 38 && Keyboard.isKeyDown(61)) {
                     ;
                  }

                  if(k == 22 && Keyboard.isKeyDown(61)) {
                     ;
                  }

                  if(k == 20 && Keyboard.isKeyDown(61)) {
                     this.func_110436_a();
                  }

                  if(k == 33 && Keyboard.isKeyDown(61)) {
                     this.field_71474_y.func_74306_a(GameSettings.Options.RENDER_DISTANCE, GuiScreen.func_146272_n()?-1:1);
                  }

                  if(k == 30 && Keyboard.isKeyDown(61)) {
                     this.field_71438_f.func_72712_a();
                  }

                  if(k == 35 && Keyboard.isKeyDown(61)) {
                     this.field_71474_y.field_82882_x = !this.field_71474_y.field_82882_x;
                     this.field_71474_y.func_74303_b();
                  }

                  if(k == 48 && Keyboard.isKeyDown(61)) {
                     this.field_175616_W.func_178629_b(!this.field_175616_W.func_178634_b());
                  }

                  if(k == 25 && Keyboard.isKeyDown(61)) {
                     this.field_71474_y.field_82881_y = !this.field_71474_y.field_82881_y;
                     this.field_71474_y.func_74303_b();
                  }

                  if(k == 59) {
                     this.field_71474_y.field_74319_N = !this.field_71474_y.field_74319_N;
                  }

                  if(k == 61) {
                     this.field_71474_y.field_74330_P = !this.field_71474_y.field_74330_P;
                     this.field_71474_y.field_74329_Q = GuiScreen.func_146272_n();
                     this.field_71474_y.field_181657_aC = GuiScreen.func_175283_s();
                  }

                  if(this.field_71474_y.field_151457_aa.func_151468_f()) {
                     ++this.field_71474_y.field_74320_O;
                     if(this.field_71474_y.field_74320_O > 2) {
                        this.field_71474_y.field_74320_O = 0;
                     }

                     if(this.field_71474_y.field_74320_O == 0) {
                        this.field_71460_t.func_175066_a(this.func_175606_aa());
                     } else if(this.field_71474_y.field_74320_O == 1) {
                        this.field_71460_t.func_175066_a((Entity)null);
                     }

                     this.field_71438_f.func_174979_m();
                  }

                  if(this.field_71474_y.field_151458_ab.func_151468_f()) {
                     this.field_71474_y.field_74326_T = !this.field_71474_y.field_74326_T;
                  }
               }

               if(this.field_71474_y.field_74330_P && this.field_71474_y.field_74329_Q) {
                  if(k == 11) {
                     this.func_71383_b(0);
                  }

                  for(int j1 = 0; j1 < 9; ++j1) {
                     if(k == 2 + j1) {
                        this.func_71383_b(j1 + 1);
                     }
                  }
               }
            }
         }

         for(int l = 0; l < 9; ++l) {
            if(this.field_71474_y.field_151456_ac[l].func_151468_f()) {
               if(this.field_71439_g.func_175149_v()) {
                  this.field_71456_v.func_175187_g().func_175260_a(l);
               } else {
                  this.field_71439_g.field_71071_by.field_70461_c = l;
               }
            }
         }

         boolean flag = this.field_71474_y.field_74343_n != EntityPlayer.EnumChatVisibility.HIDDEN;

         while(this.field_71474_y.field_151445_Q.func_151468_f()) {
            if(this.field_71442_b.func_110738_j()) {
               this.field_71439_g.func_175163_u();
            } else {
               this.func_147114_u().func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
               this.func_147108_a(new GuiInventory(this.field_71439_g));
            }
         }

         while(this.field_71474_y.field_74316_C.func_151468_f()) {
            if(!this.field_71439_g.func_175149_v()) {
               this.field_71439_g.func_71040_bB(GuiScreen.func_146271_m());
            }
         }

         while(this.field_71474_y.field_74310_D.func_151468_f() && flag) {
            this.func_147108_a(new GuiChat());
         }

         if(this.field_71462_r == null && this.field_71474_y.field_74323_J.func_151468_f() && flag) {
            this.func_147108_a(new GuiChat("/"));
         }

         if(this.field_71439_g.func_71039_bw()) {
            if(!this.field_71474_y.field_74313_G.func_151470_d()) {
               this.field_71442_b.func_78766_c(this.field_71439_g);
            }

            while(this.field_71474_y.field_74312_F.func_151468_f()) {
               ;
            }

            while(this.field_71474_y.field_74313_G.func_151468_f()) {
               ;
            }

            while(this.field_71474_y.field_74322_I.func_151468_f()) {
               ;
            }
         } else {
            while(this.field_71474_y.field_74312_F.func_151468_f()) {
               this.func_147116_af();
            }

            while(this.field_71474_y.field_74313_G.func_151468_f()) {
               this.func_147121_ag();
            }

            while(this.field_71474_y.field_74322_I.func_151468_f()) {
               this.func_147112_ai();
            }
         }

         if(this.field_71474_y.field_74313_G.func_151470_d() && this.field_71467_ac == 0 && !this.field_71439_g.func_71039_bw()) {
            this.func_147121_ag();
         }

         this.func_147115_a(this.field_71462_r == null && this.field_71474_y.field_74312_F.func_151470_d() && this.field_71415_G);
      }

      if(this.field_71441_e != null) {
         if(this.field_71439_g != null) {
            ++this.field_71457_ai;
            if(this.field_71457_ai == 30) {
               this.field_71457_ai = 0;
               this.field_71441_e.func_72897_h(this.field_71439_g);
            }
         }

         this.field_71424_I.func_76318_c("gameRenderer");
         if(!this.field_71445_n) {
            this.field_71460_t.func_78464_a();
         }

         this.field_71424_I.func_76318_c("levelRenderer");
         if(!this.field_71445_n) {
            this.field_71438_f.func_72734_e();
         }

         this.field_71424_I.func_76318_c("level");
         if(!this.field_71445_n) {
            if(this.field_71441_e.func_175658_ac() > 0) {
               this.field_71441_e.func_175702_c(this.field_71441_e.func_175658_ac() - 1);
            }

            this.field_71441_e.func_72939_s();
         }
      } else if(this.field_71460_t.func_147702_a()) {
         this.field_71460_t.func_181022_b();
      }

      if(!this.field_71445_n) {
         this.field_147126_aw.func_73660_a();
         this.field_147127_av.func_73660_a();
      }

      if(this.field_71441_e != null) {
         if(!this.field_71445_n) {
            this.field_71441_e.func_72891_a(this.field_71441_e.func_175659_aa() != EnumDifficulty.PEACEFUL, true);

            try {
               this.field_71441_e.func_72835_b();
            } catch (Throwable throwable2) {
               CrashReport crashreport2 = CrashReport.func_85055_a(throwable2, "Exception in world tick");
               if(this.field_71441_e == null) {
                  CrashReportCategory crashreportcategory2 = crashreport2.func_85058_a("Affected level");
                  crashreportcategory2.func_71507_a("Problem", "Level is null!");
               } else {
                  this.field_71441_e.func_72914_a(crashreport2);
               }

               throw new ReportedException(crashreport2);
            }
         }

         this.field_71424_I.func_76318_c("animateTick");
         if(!this.field_71445_n && this.field_71441_e != null) {
            this.field_71441_e.func_73029_E(MathHelper.func_76128_c(this.field_71439_g.field_70165_t), MathHelper.func_76128_c(this.field_71439_g.field_70163_u), MathHelper.func_76128_c(this.field_71439_g.field_70161_v));
         }

         this.field_71424_I.func_76318_c("particles");
         if(!this.field_71445_n) {
            this.field_71452_i.func_78868_a();
         }
      } else if(this.field_71453_ak != null) {
         this.field_71424_I.func_76318_c("pendingConnection");
         this.field_71453_ak.func_74428_b();
      }

      this.field_71424_I.func_76319_b();
      this.field_71423_H = func_71386_F();
   }

   public void func_71371_a(String p_71371_1_, String p_71371_2_, WorldSettings p_71371_3_) {
      this.func_71403_a((WorldClient)null);
      System.gc();
      ISaveHandler isavehandler = this.field_71469_aa.func_75804_a(p_71371_1_, false);
      WorldInfo worldinfo = isavehandler.func_75757_d();
      if(worldinfo == null && p_71371_3_ != null) {
         worldinfo = new WorldInfo(p_71371_3_, p_71371_1_);
         isavehandler.func_75761_a(worldinfo);
      }

      if(p_71371_3_ == null) {
         p_71371_3_ = new WorldSettings(worldinfo);
      }

      try {
         this.field_71437_Z = new IntegratedServer(this, p_71371_1_, p_71371_2_, p_71371_3_);
         this.field_71437_Z.func_71256_s();
         this.field_71455_al = true;
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Starting integrated server");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Starting integrated server");
         crashreportcategory.func_71507_a("Level ID", p_71371_1_);
         crashreportcategory.func_71507_a("Level Name", p_71371_2_);
         throw new ReportedException(crashreport);
      }

      this.field_71461_s.func_73720_a(I18n.func_135052_a("menu.loadingLevel", new Object[0]));

      while(!this.field_71437_Z.func_71200_ad()) {
         String s = this.field_71437_Z.func_71195_b_();
         if(s != null) {
            this.field_71461_s.func_73719_c(I18n.func_135052_a(s, new Object[0]));
         } else {
            this.field_71461_s.func_73719_c("");
         }

         try {
            Thread.sleep(200L);
         } catch (InterruptedException var9) {
            ;
         }
      }

      this.func_147108_a((GuiScreen)null);
      SocketAddress socketaddress = this.field_71437_Z.func_147137_ag().func_151270_a();
      NetworkManager networkmanager = NetworkManager.func_150722_a(socketaddress);
      networkmanager.func_150719_a(new NetHandlerLoginClient(networkmanager, this, (GuiScreen)null));
      networkmanager.func_179290_a(new C00Handshake(47, socketaddress.toString(), 0, EnumConnectionState.LOGIN));
      networkmanager.func_179290_a(new C00PacketLoginStart(this.func_110432_I().func_148256_e()));
      this.field_71453_ak = networkmanager;
   }

   public void func_71403_a(WorldClient p_71403_1_) {
      this.func_71353_a(p_71403_1_, "");
   }

   public void func_71353_a(WorldClient p_71353_1_, String p_71353_2_) {
      if(p_71353_1_ == null) {
         NetHandlerPlayClient nethandlerplayclient = this.func_147114_u();
         if(nethandlerplayclient != null) {
            nethandlerplayclient.func_147296_c();
         }

         if(this.field_71437_Z != null && this.field_71437_Z.func_175578_N()) {
            this.field_71437_Z.func_71263_m();
            this.field_71437_Z.func_175592_a();
         }

         this.field_71437_Z = null;
         this.field_71458_u.func_146257_b();
         this.field_71460_t.func_147701_i().func_148249_a();
      }

      this.field_175622_Z = null;
      this.field_71453_ak = null;
      if(this.field_71461_s != null) {
         this.field_71461_s.func_73721_b(p_71353_2_);
         this.field_71461_s.func_73719_c("");
      }

      if(p_71353_1_ == null && this.field_71441_e != null) {
         this.field_110448_aq.func_148529_f();
         this.field_71456_v.func_181029_i();
         this.func_71351_a((ServerData)null);
         this.field_71455_al = false;
      }

      this.field_147127_av.func_147690_c();
      this.field_71441_e = p_71353_1_;
      if(p_71353_1_ != null) {
         if(this.field_71438_f != null) {
            this.field_71438_f.func_72732_a(p_71353_1_);
         }

         if(this.field_71452_i != null) {
            this.field_71452_i.func_78870_a(p_71353_1_);
         }

         if(this.field_71439_g == null) {
            this.field_71439_g = this.field_71442_b.func_178892_a(p_71353_1_, new StatFileWriter());
            this.field_71442_b.func_78745_b(this.field_71439_g);
         }

         this.field_71439_g.func_70065_x();
         p_71353_1_.func_72838_d(this.field_71439_g);
         this.field_71439_g.field_71158_b = new MovementInputFromOptions(this.field_71474_y);
         this.field_71442_b.func_78748_a(this.field_71439_g);
         this.field_175622_Z = this.field_71439_g;
      } else {
         this.field_71469_aa.func_75800_d();
         this.field_71439_g = null;
      }

      System.gc();
      this.field_71423_H = 0L;
   }

   public void func_71354_a(int p_71354_1_) {
      this.field_71441_e.func_72974_f();
      this.field_71441_e.func_73022_a();
      int i = 0;
      String s = null;
      if(this.field_71439_g != null) {
         i = this.field_71439_g.func_145782_y();
         this.field_71441_e.func_72900_e(this.field_71439_g);
         s = this.field_71439_g.func_142021_k();
      }

      this.field_175622_Z = null;
      EntityPlayerSP entityplayersp = this.field_71439_g;
      this.field_71439_g = this.field_71442_b.func_178892_a(this.field_71441_e, this.field_71439_g == null?new StatFileWriter():this.field_71439_g.func_146107_m());
      this.field_71439_g.func_70096_w().func_75687_a(entityplayersp.func_70096_w().func_75685_c());
      this.field_71439_g.field_71093_bK = p_71354_1_;
      this.field_175622_Z = this.field_71439_g;
      this.field_71439_g.func_70065_x();
      this.field_71439_g.func_175158_f(s);
      this.field_71441_e.func_72838_d(this.field_71439_g);
      this.field_71442_b.func_78745_b(this.field_71439_g);
      this.field_71439_g.field_71158_b = new MovementInputFromOptions(this.field_71474_y);
      this.field_71439_g.func_145769_d(i);
      this.field_71442_b.func_78748_a(this.field_71439_g);
      this.field_71439_g.func_175150_k(entityplayersp.func_175140_cp());
      if(this.field_71462_r instanceof GuiGameOver) {
         this.func_147108_a((GuiScreen)null);
      }

   }

   public final boolean func_71355_q() {
      return this.field_71459_aj;
   }

   public NetHandlerPlayClient func_147114_u() {
      return this.field_71439_g != null?this.field_71439_g.field_71174_a:null;
   }

   public static boolean func_71382_s() {
      return field_71432_P == null || !field_71432_P.field_71474_y.field_74319_N;
   }

   public static boolean func_71375_t() {
      return field_71432_P != null && field_71432_P.field_71474_y.field_74347_j;
   }

   public static boolean func_71379_u() {
      return field_71432_P != null && field_71432_P.field_71474_y.field_74348_k != 0;
   }

   private void func_147112_ai() {
      if(this.field_71476_x != null) {
         boolean flag = this.field_71439_g.field_71075_bZ.field_75098_d;
         int i = 0;
         boolean flag1 = false;
         TileEntity tileentity = null;
         Item item;
         if(this.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            BlockPos blockpos = this.field_71476_x.func_178782_a();
            Block block = this.field_71441_e.func_180495_p(blockpos).func_177230_c();
            if(block.func_149688_o() == Material.field_151579_a) {
               return;
            }

            item = block.func_180665_b(this.field_71441_e, blockpos);
            if(item == null) {
               return;
            }

            if(flag && GuiScreen.func_146271_m()) {
               tileentity = this.field_71441_e.func_175625_s(blockpos);
            }

            Block block1 = item instanceof ItemBlock && !block.func_149648_K()?Block.func_149634_a(item):block;
            i = block1.func_176222_j(this.field_71441_e, blockpos);
            flag1 = item.func_77614_k();
         } else {
            if(this.field_71476_x.field_72313_a != MovingObjectPosition.MovingObjectType.ENTITY || this.field_71476_x.field_72308_g == null || !flag) {
               return;
            }

            if(this.field_71476_x.field_72308_g instanceof EntityPainting) {
               item = Items.field_151159_an;
            } else if(this.field_71476_x.field_72308_g instanceof EntityLeashKnot) {
               item = Items.field_151058_ca;
            } else if(this.field_71476_x.field_72308_g instanceof EntityItemFrame) {
               EntityItemFrame entityitemframe = (EntityItemFrame)this.field_71476_x.field_72308_g;
               ItemStack itemstack = entityitemframe.func_82335_i();
               if(itemstack == null) {
                  item = Items.field_151160_bD;
               } else {
                  item = itemstack.func_77973_b();
                  i = itemstack.func_77960_j();
                  flag1 = true;
               }
            } else if(this.field_71476_x.field_72308_g instanceof EntityMinecart) {
               EntityMinecart entityminecart = (EntityMinecart)this.field_71476_x.field_72308_g;
               switch(entityminecart.func_180456_s()) {
               case FURNACE:
                  item = Items.field_151109_aJ;
                  break;
               case CHEST:
                  item = Items.field_151108_aI;
                  break;
               case TNT:
                  item = Items.field_151142_bV;
                  break;
               case HOPPER:
                  item = Items.field_151140_bW;
                  break;
               case COMMAND_BLOCK:
                  item = Items.field_151095_cc;
                  break;
               default:
                  item = Items.field_151143_au;
               }
            } else if(this.field_71476_x.field_72308_g instanceof EntityBoat) {
               item = Items.field_151124_az;
            } else if(this.field_71476_x.field_72308_g instanceof EntityArmorStand) {
               item = Items.field_179565_cj;
            } else {
               item = Items.field_151063_bx;
               i = EntityList.func_75619_a(this.field_71476_x.field_72308_g);
               flag1 = true;
               if(!EntityList.field_75627_a.containsKey(Integer.valueOf(i))) {
                  return;
               }
            }
         }

         InventoryPlayer inventoryplayer = this.field_71439_g.field_71071_by;
         if(tileentity == null) {
            inventoryplayer.func_146030_a(item, i, flag1, flag);
         } else {
            ItemStack itemstack1 = this.func_181036_a(item, i, tileentity);
            inventoryplayer.func_70299_a(inventoryplayer.field_70461_c, itemstack1);
         }

         if(flag) {
            int j = this.field_71439_g.field_71069_bz.field_75151_b.size() - 9 + inventoryplayer.field_70461_c;
            this.field_71442_b.func_78761_a(inventoryplayer.func_70301_a(inventoryplayer.field_70461_c), j);
         }

      }
   }

   private ItemStack func_181036_a(Item p_181036_1_, int p_181036_2_, TileEntity p_181036_3_) {
      ItemStack itemstack = new ItemStack(p_181036_1_, 1, p_181036_2_);
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      p_181036_3_.func_145841_b(nbttagcompound);
      if(p_181036_1_ == Items.field_151144_bL && nbttagcompound.func_74764_b("Owner")) {
         NBTTagCompound nbttagcompound2 = nbttagcompound.func_74775_l("Owner");
         NBTTagCompound nbttagcompound3 = new NBTTagCompound();
         nbttagcompound3.func_74782_a("SkullOwner", nbttagcompound2);
         itemstack.func_77982_d(nbttagcompound3);
         return itemstack;
      } else {
         itemstack.func_77983_a("BlockEntityTag", nbttagcompound);
         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
         NBTTagList nbttaglist = new NBTTagList();
         nbttaglist.func_74742_a(new NBTTagString("(+NBT)"));
         nbttagcompound1.func_74782_a("Lore", nbttaglist);
         itemstack.func_77983_a("display", nbttagcompound1);
         return itemstack;
      }
   }

   public CrashReport func_71396_d(CrashReport p_71396_1_) {
      p_71396_1_.func_85056_g().func_71500_a("Launched Version", new Callable<String>() {
         public String call() throws Exception {
            return Minecraft.this.field_110447_Z;
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("LWJGL", new Callable<String>() {
         public String call() {
            return Sys.getVersion();
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("OpenGL", new Callable<String>() {
         public String call() {
            return GL11.glGetString(7937) + " GL version " + GL11.glGetString(7938) + ", " + GL11.glGetString(7936);
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("GL Caps", new Callable<String>() {
         public String call() {
            return OpenGlHelper.func_153172_c();
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("Using VBOs", new Callable<String>() {
         public String call() {
            return Minecraft.this.field_71474_y.field_178881_t?"Yes":"No";
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("Is Modded", new Callable<String>() {
         public String call() throws Exception {
            String s = ClientBrandRetriever.getClientModName();
            return !s.equals("vanilla")?"Definitely; Client brand changed to \'" + s + "\'":(Minecraft.class.getSigners() == null?"Very likely; Jar signature invalidated":"Probably not. Jar signature remains and client brand is untouched.");
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("Type", new Callable<String>() {
         public String call() throws Exception {
            return "Client (map_client.txt)";
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("Resource Packs", new Callable<String>() {
         public String call() throws Exception {
            StringBuilder stringbuilder = new StringBuilder();

            for(String s : Minecraft.this.field_71474_y.field_151453_l) {
               if(stringbuilder.length() > 0) {
                  stringbuilder.append(", ");
               }

               stringbuilder.append(s);
               if(Minecraft.this.field_71474_y.field_183018_l.contains(s)) {
                  stringbuilder.append(" (incompatible)");
               }
            }

            return stringbuilder.toString();
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("Current Language", new Callable<String>() {
         public String call() throws Exception {
            return Minecraft.this.field_135017_as.func_135041_c().toString();
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("Profiler Position", new Callable<String>() {
         public String call() throws Exception {
            return Minecraft.this.field_71424_I.field_76327_a?Minecraft.this.field_71424_I.func_76322_c():"N/A (disabled)";
         }
      });
      p_71396_1_.func_85056_g().func_71500_a("CPU", new Callable<String>() {
         public String call() {
            return OpenGlHelper.func_183029_j();
         }
      });
      if(this.field_71441_e != null) {
         this.field_71441_e.func_72914_a(p_71396_1_);
      }

      return p_71396_1_;
   }

   public static Minecraft func_71410_x() {
      return field_71432_P;
   }

   public ListenableFuture<Object> func_175603_A() {
      return this.func_152344_a(new Runnable() {
         public void run() {
            Minecraft.this.func_110436_a();
         }
      });
   }

   public void func_70000_a(PlayerUsageSnooper p_70000_1_) {
      p_70000_1_.func_152768_a("fps", Integer.valueOf(field_71470_ab));
      p_70000_1_.func_152768_a("vsync_enabled", Boolean.valueOf(this.field_71474_y.field_74352_v));
      p_70000_1_.func_152768_a("display_frequency", Integer.valueOf(Display.getDisplayMode().getFrequency()));
      p_70000_1_.func_152768_a("display_type", this.field_71431_Q?"fullscreen":"windowed");
      p_70000_1_.func_152768_a("run_time", Long.valueOf((MinecraftServer.func_130071_aq() - p_70000_1_.func_130105_g()) / 60L * 1000L));
      p_70000_1_.func_152768_a("current_action", this.func_181538_aA());
      String s = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN?"little":"big";
      p_70000_1_.func_152768_a("endianness", s);
      p_70000_1_.func_152768_a("resource_packs", Integer.valueOf(this.field_110448_aq.func_110613_c().size()));
      int i = 0;

      for(ResourcePackRepository.Entry resourcepackrepository$entry : this.field_110448_aq.func_110613_c()) {
         p_70000_1_.func_152768_a("resource_pack[" + i++ + "]", resourcepackrepository$entry.func_110515_d());
      }

      if(this.field_71437_Z != null && this.field_71437_Z.func_80003_ah() != null) {
         p_70000_1_.func_152768_a("snooper_partner", this.field_71437_Z.func_80003_ah().func_80006_f());
      }

   }

   private String func_181538_aA() {
      return this.field_71437_Z != null?(this.field_71437_Z.func_71344_c()?"hosting_lan":"singleplayer"):(this.field_71422_O != null?(this.field_71422_O.func_181041_d()?"playing_lan":"multiplayer"):"out_of_game");
   }

   public void func_70001_b(PlayerUsageSnooper p_70001_1_) {
      p_70001_1_.func_152767_b("opengl_version", GL11.glGetString(7938));
      p_70001_1_.func_152767_b("opengl_vendor", GL11.glGetString(7936));
      p_70001_1_.func_152767_b("client_brand", ClientBrandRetriever.getClientModName());
      p_70001_1_.func_152767_b("launched_version", this.field_110447_Z);
      ContextCapabilities contextcapabilities = GLContext.getCapabilities();
      p_70001_1_.func_152767_b("gl_caps[ARB_arrays_of_arrays]", Boolean.valueOf(contextcapabilities.GL_ARB_arrays_of_arrays));
      p_70001_1_.func_152767_b("gl_caps[ARB_base_instance]", Boolean.valueOf(contextcapabilities.GL_ARB_base_instance));
      p_70001_1_.func_152767_b("gl_caps[ARB_blend_func_extended]", Boolean.valueOf(contextcapabilities.GL_ARB_blend_func_extended));
      p_70001_1_.func_152767_b("gl_caps[ARB_clear_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_clear_buffer_object));
      p_70001_1_.func_152767_b("gl_caps[ARB_color_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_color_buffer_float));
      p_70001_1_.func_152767_b("gl_caps[ARB_compatibility]", Boolean.valueOf(contextcapabilities.GL_ARB_compatibility));
      p_70001_1_.func_152767_b("gl_caps[ARB_compressed_texture_pixel_storage]", Boolean.valueOf(contextcapabilities.GL_ARB_compressed_texture_pixel_storage));
      p_70001_1_.func_152767_b("gl_caps[ARB_compute_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_compute_shader));
      p_70001_1_.func_152767_b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_buffer));
      p_70001_1_.func_152767_b("gl_caps[ARB_copy_image]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_image));
      p_70001_1_.func_152767_b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_buffer_float));
      p_70001_1_.func_152767_b("gl_caps[ARB_compute_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_compute_shader));
      p_70001_1_.func_152767_b("gl_caps[ARB_copy_buffer]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_buffer));
      p_70001_1_.func_152767_b("gl_caps[ARB_copy_image]", Boolean.valueOf(contextcapabilities.GL_ARB_copy_image));
      p_70001_1_.func_152767_b("gl_caps[ARB_depth_buffer_float]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_buffer_float));
      p_70001_1_.func_152767_b("gl_caps[ARB_depth_clamp]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_clamp));
      p_70001_1_.func_152767_b("gl_caps[ARB_depth_texture]", Boolean.valueOf(contextcapabilities.GL_ARB_depth_texture));
      p_70001_1_.func_152767_b("gl_caps[ARB_draw_buffers]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_buffers));
      p_70001_1_.func_152767_b("gl_caps[ARB_draw_buffers_blend]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_buffers_blend));
      p_70001_1_.func_152767_b("gl_caps[ARB_draw_elements_base_vertex]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_elements_base_vertex));
      p_70001_1_.func_152767_b("gl_caps[ARB_draw_indirect]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_indirect));
      p_70001_1_.func_152767_b("gl_caps[ARB_draw_instanced]", Boolean.valueOf(contextcapabilities.GL_ARB_draw_instanced));
      p_70001_1_.func_152767_b("gl_caps[ARB_explicit_attrib_location]", Boolean.valueOf(contextcapabilities.GL_ARB_explicit_attrib_location));
      p_70001_1_.func_152767_b("gl_caps[ARB_explicit_uniform_location]", Boolean.valueOf(contextcapabilities.GL_ARB_explicit_uniform_location));
      p_70001_1_.func_152767_b("gl_caps[ARB_fragment_layer_viewport]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_layer_viewport));
      p_70001_1_.func_152767_b("gl_caps[ARB_fragment_program]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_program));
      p_70001_1_.func_152767_b("gl_caps[ARB_fragment_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_shader));
      p_70001_1_.func_152767_b("gl_caps[ARB_fragment_program_shadow]", Boolean.valueOf(contextcapabilities.GL_ARB_fragment_program_shadow));
      p_70001_1_.func_152767_b("gl_caps[ARB_framebuffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_framebuffer_object));
      p_70001_1_.func_152767_b("gl_caps[ARB_framebuffer_sRGB]", Boolean.valueOf(contextcapabilities.GL_ARB_framebuffer_sRGB));
      p_70001_1_.func_152767_b("gl_caps[ARB_geometry_shader4]", Boolean.valueOf(contextcapabilities.GL_ARB_geometry_shader4));
      p_70001_1_.func_152767_b("gl_caps[ARB_gpu_shader5]", Boolean.valueOf(contextcapabilities.GL_ARB_gpu_shader5));
      p_70001_1_.func_152767_b("gl_caps[ARB_half_float_pixel]", Boolean.valueOf(contextcapabilities.GL_ARB_half_float_pixel));
      p_70001_1_.func_152767_b("gl_caps[ARB_half_float_vertex]", Boolean.valueOf(contextcapabilities.GL_ARB_half_float_vertex));
      p_70001_1_.func_152767_b("gl_caps[ARB_instanced_arrays]", Boolean.valueOf(contextcapabilities.GL_ARB_instanced_arrays));
      p_70001_1_.func_152767_b("gl_caps[ARB_map_buffer_alignment]", Boolean.valueOf(contextcapabilities.GL_ARB_map_buffer_alignment));
      p_70001_1_.func_152767_b("gl_caps[ARB_map_buffer_range]", Boolean.valueOf(contextcapabilities.GL_ARB_map_buffer_range));
      p_70001_1_.func_152767_b("gl_caps[ARB_multisample]", Boolean.valueOf(contextcapabilities.GL_ARB_multisample));
      p_70001_1_.func_152767_b("gl_caps[ARB_multitexture]", Boolean.valueOf(contextcapabilities.GL_ARB_multitexture));
      p_70001_1_.func_152767_b("gl_caps[ARB_occlusion_query2]", Boolean.valueOf(contextcapabilities.GL_ARB_occlusion_query2));
      p_70001_1_.func_152767_b("gl_caps[ARB_pixel_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_pixel_buffer_object));
      p_70001_1_.func_152767_b("gl_caps[ARB_seamless_cube_map]", Boolean.valueOf(contextcapabilities.GL_ARB_seamless_cube_map));
      p_70001_1_.func_152767_b("gl_caps[ARB_shader_objects]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_objects));
      p_70001_1_.func_152767_b("gl_caps[ARB_shader_stencil_export]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_stencil_export));
      p_70001_1_.func_152767_b("gl_caps[ARB_shader_texture_lod]", Boolean.valueOf(contextcapabilities.GL_ARB_shader_texture_lod));
      p_70001_1_.func_152767_b("gl_caps[ARB_shadow]", Boolean.valueOf(contextcapabilities.GL_ARB_shadow));
      p_70001_1_.func_152767_b("gl_caps[ARB_shadow_ambient]", Boolean.valueOf(contextcapabilities.GL_ARB_shadow_ambient));
      p_70001_1_.func_152767_b("gl_caps[ARB_stencil_texturing]", Boolean.valueOf(contextcapabilities.GL_ARB_stencil_texturing));
      p_70001_1_.func_152767_b("gl_caps[ARB_sync]", Boolean.valueOf(contextcapabilities.GL_ARB_sync));
      p_70001_1_.func_152767_b("gl_caps[ARB_tessellation_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_tessellation_shader));
      p_70001_1_.func_152767_b("gl_caps[ARB_texture_border_clamp]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_border_clamp));
      p_70001_1_.func_152767_b("gl_caps[ARB_texture_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_buffer_object));
      p_70001_1_.func_152767_b("gl_caps[ARB_texture_cube_map]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_cube_map));
      p_70001_1_.func_152767_b("gl_caps[ARB_texture_cube_map_array]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_cube_map_array));
      p_70001_1_.func_152767_b("gl_caps[ARB_texture_non_power_of_two]", Boolean.valueOf(contextcapabilities.GL_ARB_texture_non_power_of_two));
      p_70001_1_.func_152767_b("gl_caps[ARB_uniform_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_uniform_buffer_object));
      p_70001_1_.func_152767_b("gl_caps[ARB_vertex_blend]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_blend));
      p_70001_1_.func_152767_b("gl_caps[ARB_vertex_buffer_object]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_buffer_object));
      p_70001_1_.func_152767_b("gl_caps[ARB_vertex_program]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_program));
      p_70001_1_.func_152767_b("gl_caps[ARB_vertex_shader]", Boolean.valueOf(contextcapabilities.GL_ARB_vertex_shader));
      p_70001_1_.func_152767_b("gl_caps[EXT_bindable_uniform]", Boolean.valueOf(contextcapabilities.GL_EXT_bindable_uniform));
      p_70001_1_.func_152767_b("gl_caps[EXT_blend_equation_separate]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_equation_separate));
      p_70001_1_.func_152767_b("gl_caps[EXT_blend_func_separate]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_func_separate));
      p_70001_1_.func_152767_b("gl_caps[EXT_blend_minmax]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_minmax));
      p_70001_1_.func_152767_b("gl_caps[EXT_blend_subtract]", Boolean.valueOf(contextcapabilities.GL_EXT_blend_subtract));
      p_70001_1_.func_152767_b("gl_caps[EXT_draw_instanced]", Boolean.valueOf(contextcapabilities.GL_EXT_draw_instanced));
      p_70001_1_.func_152767_b("gl_caps[EXT_framebuffer_multisample]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_multisample));
      p_70001_1_.func_152767_b("gl_caps[EXT_framebuffer_object]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_object));
      p_70001_1_.func_152767_b("gl_caps[EXT_framebuffer_sRGB]", Boolean.valueOf(contextcapabilities.GL_EXT_framebuffer_sRGB));
      p_70001_1_.func_152767_b("gl_caps[EXT_geometry_shader4]", Boolean.valueOf(contextcapabilities.GL_EXT_geometry_shader4));
      p_70001_1_.func_152767_b("gl_caps[EXT_gpu_program_parameters]", Boolean.valueOf(contextcapabilities.GL_EXT_gpu_program_parameters));
      p_70001_1_.func_152767_b("gl_caps[EXT_gpu_shader4]", Boolean.valueOf(contextcapabilities.GL_EXT_gpu_shader4));
      p_70001_1_.func_152767_b("gl_caps[EXT_multi_draw_arrays]", Boolean.valueOf(contextcapabilities.GL_EXT_multi_draw_arrays));
      p_70001_1_.func_152767_b("gl_caps[EXT_packed_depth_stencil]", Boolean.valueOf(contextcapabilities.GL_EXT_packed_depth_stencil));
      p_70001_1_.func_152767_b("gl_caps[EXT_paletted_texture]", Boolean.valueOf(contextcapabilities.GL_EXT_paletted_texture));
      p_70001_1_.func_152767_b("gl_caps[EXT_rescale_normal]", Boolean.valueOf(contextcapabilities.GL_EXT_rescale_normal));
      p_70001_1_.func_152767_b("gl_caps[EXT_separate_shader_objects]", Boolean.valueOf(contextcapabilities.GL_EXT_separate_shader_objects));
      p_70001_1_.func_152767_b("gl_caps[EXT_shader_image_load_store]", Boolean.valueOf(contextcapabilities.GL_EXT_shader_image_load_store));
      p_70001_1_.func_152767_b("gl_caps[EXT_shadow_funcs]", Boolean.valueOf(contextcapabilities.GL_EXT_shadow_funcs));
      p_70001_1_.func_152767_b("gl_caps[EXT_shared_texture_palette]", Boolean.valueOf(contextcapabilities.GL_EXT_shared_texture_palette));
      p_70001_1_.func_152767_b("gl_caps[EXT_stencil_clear_tag]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_clear_tag));
      p_70001_1_.func_152767_b("gl_caps[EXT_stencil_two_side]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_two_side));
      p_70001_1_.func_152767_b("gl_caps[EXT_stencil_wrap]", Boolean.valueOf(contextcapabilities.GL_EXT_stencil_wrap));
      p_70001_1_.func_152767_b("gl_caps[EXT_texture_3d]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_3d));
      p_70001_1_.func_152767_b("gl_caps[EXT_texture_array]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_array));
      p_70001_1_.func_152767_b("gl_caps[EXT_texture_buffer_object]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_buffer_object));
      p_70001_1_.func_152767_b("gl_caps[EXT_texture_integer]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_integer));
      p_70001_1_.func_152767_b("gl_caps[EXT_texture_lod_bias]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_lod_bias));
      p_70001_1_.func_152767_b("gl_caps[EXT_texture_sRGB]", Boolean.valueOf(contextcapabilities.GL_EXT_texture_sRGB));
      p_70001_1_.func_152767_b("gl_caps[EXT_vertex_shader]", Boolean.valueOf(contextcapabilities.GL_EXT_vertex_shader));
      p_70001_1_.func_152767_b("gl_caps[EXT_vertex_weighting]", Boolean.valueOf(contextcapabilities.GL_EXT_vertex_weighting));
      p_70001_1_.func_152767_b("gl_caps[gl_max_vertex_uniforms]", Integer.valueOf(GL11.glGetInteger('\u8b4a')));
      GL11.glGetError();
      p_70001_1_.func_152767_b("gl_caps[gl_max_fragment_uniforms]", Integer.valueOf(GL11.glGetInteger('\u8b49')));
      GL11.glGetError();
      p_70001_1_.func_152767_b("gl_caps[gl_max_vertex_attribs]", Integer.valueOf(GL11.glGetInteger('\u8869')));
      GL11.glGetError();
      p_70001_1_.func_152767_b("gl_caps[gl_max_vertex_texture_image_units]", Integer.valueOf(GL11.glGetInteger('\u8b4c')));
      GL11.glGetError();
      p_70001_1_.func_152767_b("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger('\u8872')));
      GL11.glGetError();
      p_70001_1_.func_152767_b("gl_caps[gl_max_texture_image_units]", Integer.valueOf(GL11.glGetInteger('\u88ff')));
      GL11.glGetError();
      p_70001_1_.func_152767_b("gl_max_texture_size", Integer.valueOf(func_71369_N()));
   }

   public static int func_71369_N() {
      for(int i = 16384; i > 0; i >>= 1) {
         GL11.glTexImage2D('\u8064', 0, 6408, i, i, 0, 6408, 5121, (ByteBuffer)((ByteBuffer)null));
         int j = GL11.glGetTexLevelParameteri('\u8064', 0, 4096);
         if(j != 0) {
            return i;
         }
      }

      return -1;
   }

   public boolean func_70002_Q() {
      return this.field_71474_y.field_74355_t;
   }

   public void func_71351_a(ServerData p_71351_1_) {
      this.field_71422_O = p_71351_1_;
   }

   public ServerData func_147104_D() {
      return this.field_71422_O;
   }

   public boolean func_71387_A() {
      return this.field_71455_al;
   }

   public boolean func_71356_B() {
      return this.field_71455_al && this.field_71437_Z != null;
   }

   public IntegratedServer func_71401_C() {
      return this.field_71437_Z;
   }

   public static void func_71363_D() {
      if(field_71432_P != null) {
         IntegratedServer integratedserver = field_71432_P.func_71401_C();
         if(integratedserver != null) {
            integratedserver.func_71260_j();
         }

      }
   }

   public PlayerUsageSnooper func_71378_E() {
      return this.field_71427_U;
   }

   public static long func_71386_F() {
      return Sys.getTime() * 1000L / Sys.getTimerResolution();
   }

   public boolean func_71372_G() {
      return this.field_71431_Q;
   }

   public Session func_110432_I() {
      return this.field_71449_j;
   }

   public PropertyMap func_180509_L() {
      return this.field_152356_J;
   }

   public PropertyMap func_181037_M() {
      if(this.field_181038_N.isEmpty()) {
         GameProfile gameprofile = this.func_152347_ac().fillProfileProperties(this.field_71449_j.func_148256_e(), false);
         this.field_181038_N.putAll(gameprofile.getProperties());
      }

      return this.field_181038_N;
   }

   public Proxy func_110437_J() {
      return this.field_110453_aa;
   }

   public TextureManager func_110434_K() {
      return this.field_71446_o;
   }

   public IResourceManager func_110442_L() {
      return this.field_110451_am;
   }

   public ResourcePackRepository func_110438_M() {
      return this.field_110448_aq;
   }

   public LanguageManager func_135016_M() {
      return this.field_135017_as;
   }

   public TextureMap func_147117_R() {
      return this.field_147128_au;
   }

   public boolean func_147111_S() {
      return this.field_147129_ai;
   }

   public boolean func_147113_T() {
      return this.field_71445_n;
   }

   public SoundHandler func_147118_V() {
      return this.field_147127_av;
   }

   public MusicTicker.MusicType func_147109_W() {
      return this.field_71439_g != null?(this.field_71439_g.field_70170_p.field_73011_w instanceof WorldProviderHell?MusicTicker.MusicType.NETHER:(this.field_71439_g.field_70170_p.field_73011_w instanceof WorldProviderEnd?(BossStatus.field_82827_c != null && BossStatus.field_82826_b > 0?MusicTicker.MusicType.END_BOSS:MusicTicker.MusicType.END):(this.field_71439_g.field_71075_bZ.field_75098_d && this.field_71439_g.field_71075_bZ.field_75101_c?MusicTicker.MusicType.CREATIVE:MusicTicker.MusicType.GAME))):MusicTicker.MusicType.MENU;
   }

   public IStream func_152346_Z() {
      return this.field_152353_at;
   }

   public void func_152348_aa() {
      int i = Keyboard.getEventKey() == 0?Keyboard.getEventCharacter():Keyboard.getEventKey();
      if(i != 0 && !Keyboard.isRepeatEvent()) {
         if(!(this.field_71462_r instanceof GuiControls) || ((GuiControls)this.field_71462_r).field_152177_g <= func_71386_F() - 20L) {
            if(Keyboard.getEventKeyState()) {
               if(i == this.field_71474_y.field_152396_an.func_151463_i()) {
                  if(this.func_152346_Z().func_152934_n()) {
                     this.func_152346_Z().func_152914_u();
                  } else if(this.func_152346_Z().func_152924_m()) {
                     this.func_147108_a(new GuiYesNo(new GuiYesNoCallback() {
                        public void func_73878_a(boolean p_73878_1_, int p_73878_2_) {
                           if(p_73878_1_) {
                              Minecraft.this.func_152346_Z().func_152930_t();
                           }

                           Minecraft.this.func_147108_a((GuiScreen)null);
                        }
                     }, I18n.func_135052_a("stream.confirm_start", new Object[0]), "", 0));
                  } else if(this.func_152346_Z().func_152928_D() && this.func_152346_Z().func_152936_l()) {
                     if(this.field_71441_e != null) {
                        this.field_71456_v.func_146158_b().func_146227_a(new ChatComponentText("Not ready to start streaming yet!"));
                     }
                  } else {
                     GuiStreamUnavailable.func_152321_a(this.field_71462_r);
                  }
               } else if(i == this.field_71474_y.field_152397_ao.func_151463_i()) {
                  if(this.func_152346_Z().func_152934_n()) {
                     if(this.func_152346_Z().func_152919_o()) {
                        this.func_152346_Z().func_152933_r();
                     } else {
                        this.func_152346_Z().func_152916_q();
                     }
                  }
               } else if(i == this.field_71474_y.field_152398_ap.func_151463_i()) {
                  if(this.func_152346_Z().func_152934_n()) {
                     this.func_152346_Z().func_152931_p();
                  }
               } else if(i == this.field_71474_y.field_152399_aq.func_151463_i()) {
                  this.field_152353_at.func_152910_a(true);
               } else if(i == this.field_71474_y.field_152395_am.func_151463_i()) {
                  this.func_71352_k();
               } else if(i == this.field_71474_y.field_151447_Z.func_151463_i()) {
                  this.field_71456_v.func_146158_b().func_146227_a(ScreenShotHelper.func_148260_a(this.field_71412_D, this.field_71443_c, this.field_71440_d, this.field_147124_at));
               }
            } else if(i == this.field_71474_y.field_152399_aq.func_151463_i()) {
               this.field_152353_at.func_152910_a(false);
            }

         }
      }
   }

   public MinecraftSessionService func_152347_ac() {
      return this.field_152355_az;
   }

   public SkinManager func_152342_ad() {
      return this.field_152350_aA;
   }

   public Entity func_175606_aa() {
      return this.field_175622_Z;
   }

   public void func_175607_a(Entity p_175607_1_) {
      this.field_175622_Z = p_175607_1_;
      this.field_71460_t.func_175066_a(p_175607_1_);
   }

   public <V> ListenableFuture<V> func_152343_a(Callable<V> p_152343_1_) {
      Validate.notNull(p_152343_1_);
      if(!this.func_152345_ab()) {
         ListenableFutureTask<V> listenablefuturetask = ListenableFutureTask.<V>create(p_152343_1_);
         synchronized(this.field_152351_aB) {
            this.field_152351_aB.add(listenablefuturetask);
            return listenablefuturetask;
         }
      } else {
         try {
            return Futures.<V>immediateFuture(p_152343_1_.call());
         } catch (Exception exception) {
            return Futures.immediateFailedCheckedFuture(exception);
         }
      }
   }

   public ListenableFuture<Object> func_152344_a(Runnable p_152344_1_) {
      Validate.notNull(p_152344_1_);
      return this.<Object>func_152343_a(Executors.callable(p_152344_1_));
   }

   public boolean func_152345_ab() {
      return Thread.currentThread() == this.field_152352_aC;
   }

   public BlockRendererDispatcher func_175602_ab() {
      return this.field_175618_aM;
   }

   public RenderManager func_175598_ae() {
      return this.field_175616_W;
   }

   public RenderItem func_175599_af() {
      return this.field_175621_X;
   }

   public ItemRenderer func_175597_ag() {
      return this.field_175620_Y;
   }

   public static int func_175610_ah() {
      return field_71470_ab;
   }

   public FrameTimer func_181539_aj() {
      return this.field_181542_y;
   }

   public static Map<String, String> func_175596_ai() {
      Map<String, String> map = Maps.<String, String>newHashMap();
      map.put("X-Minecraft-Username", func_71410_x().func_110432_I().func_111285_a());
      map.put("X-Minecraft-UUID", func_71410_x().func_110432_I().func_148255_b());
      map.put("X-Minecraft-Version", "1.8.9");
      return map;
   }

   public boolean func_181540_al() {
      return this.field_181541_X;
   }

   public void func_181537_a(boolean p_181537_1_) {
      this.field_181541_X = p_181537_1_;
   }
}
