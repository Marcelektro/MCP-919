package net.minecraft.client.renderer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.ChunkRenderContainer;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderList;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VboRenderList;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
import net.minecraft.client.renderer.chunk.ListChunkFactory;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemRecord;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Matrix4f;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vector3d;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class RenderGlobal implements IWorldAccess, IResourceManagerReloadListener {
   private static final Logger field_147599_m = LogManager.getLogger();
   private static final ResourceLocation field_110927_h = new ResourceLocation("textures/environment/moon_phases.png");
   private static final ResourceLocation field_110928_i = new ResourceLocation("textures/environment/sun.png");
   private static final ResourceLocation field_110925_j = new ResourceLocation("textures/environment/clouds.png");
   private static final ResourceLocation field_110926_k = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation field_175006_g = new ResourceLocation("textures/misc/forcefield.png");
   private final Minecraft field_72777_q;
   private final TextureManager field_72770_i;
   private final RenderManager field_175010_j;
   private WorldClient field_72769_h;
   private Set<RenderChunk> field_175009_l = Sets.<RenderChunk>newLinkedHashSet();
   private List<RenderGlobal.ContainerLocalRenderInformation> field_72755_R = Lists.<RenderGlobal.ContainerLocalRenderInformation>newArrayListWithCapacity(69696);
   private final Set<TileEntity> field_181024_n = Sets.<TileEntity>newHashSet();
   private ViewFrustum field_175008_n;
   private int field_72772_v = -1;
   private int field_72771_w = -1;
   private int field_72781_x = -1;
   private VertexFormat field_175014_r;
   private VertexBuffer field_175013_s;
   private VertexBuffer field_175012_t;
   private VertexBuffer field_175011_u;
   private int field_72773_u;
   private final Map<Integer, DestroyBlockProgress> field_72738_E = Maps.<Integer, DestroyBlockProgress>newHashMap();
   private final Map<BlockPos, ISound> field_147593_P = Maps.<BlockPos, ISound>newHashMap();
   private final TextureAtlasSprite[] field_94141_F = new TextureAtlasSprite[10];
   private Framebuffer field_175015_z;
   private ShaderGroup field_174991_A;
   private double field_174992_B = Double.MIN_VALUE;
   private double field_174993_C = Double.MIN_VALUE;
   private double field_174987_D = Double.MIN_VALUE;
   private int field_174988_E = Integer.MIN_VALUE;
   private int field_174989_F = Integer.MIN_VALUE;
   private int field_174990_G = Integer.MIN_VALUE;
   private double field_174997_H = Double.MIN_VALUE;
   private double field_174998_I = Double.MIN_VALUE;
   private double field_174999_J = Double.MIN_VALUE;
   private double field_175000_K = Double.MIN_VALUE;
   private double field_174994_L = Double.MIN_VALUE;
   private final ChunkRenderDispatcher field_174995_M = new ChunkRenderDispatcher();
   private ChunkRenderContainer field_174996_N;
   private int field_72739_F = -1;
   private int field_72740_G = 2;
   private int field_72748_H;
   private int field_72749_I;
   private int field_72750_J;
   private boolean field_175002_T = false;
   private ClippingHelper field_175001_U;
   private final Vector4f[] field_175004_V = new Vector4f[8];
   private final Vector3d field_175003_W = new Vector3d();
   private boolean field_175005_X = false;
   IRenderChunkFactory field_175007_a;
   private double field_147596_f;
   private double field_147597_g;
   private double field_147602_h;
   private boolean field_147595_R = true;

   public RenderGlobal(Minecraft p_i1249_1_) {
      this.field_72777_q = p_i1249_1_;
      this.field_175010_j = p_i1249_1_.func_175598_ae();
      this.field_72770_i = p_i1249_1_.func_110434_K();
      this.field_72770_i.func_110577_a(field_175006_g);
      GL11.glTexParameteri(3553, 10242, 10497);
      GL11.glTexParameteri(3553, 10243, 10497);
      GlStateManager.func_179144_i(0);
      this.func_174971_n();
      this.field_175005_X = OpenGlHelper.func_176075_f();
      if(this.field_175005_X) {
         this.field_174996_N = new VboRenderList();
         this.field_175007_a = new VboChunkFactory();
      } else {
         this.field_174996_N = new RenderList();
         this.field_175007_a = new ListChunkFactory();
      }

      this.field_175014_r = new VertexFormat();
      this.field_175014_r.func_181721_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.POSITION, 3));
      this.func_174963_q();
      this.func_174980_p();
      this.func_174964_o();
   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      this.func_174971_n();
   }

   private void func_174971_n() {
      TextureMap texturemap = this.field_72777_q.func_147117_R();

      for(int i = 0; i < this.field_94141_F.length; ++i) {
         this.field_94141_F[i] = texturemap.func_110572_b("minecraft:blocks/destroy_stage_" + i);
      }

   }

   public void func_174966_b() {
      if(OpenGlHelper.field_148824_g) {
         if(ShaderLinkHelper.func_148074_b() == null) {
            ShaderLinkHelper.func_148076_a();
         }

         ResourceLocation resourcelocation = new ResourceLocation("shaders/post/entity_outline.json");

         try {
            this.field_174991_A = new ShaderGroup(this.field_72777_q.func_110434_K(), this.field_72777_q.func_110442_L(), this.field_72777_q.func_147110_a(), resourcelocation);
            this.field_174991_A.func_148026_a(this.field_72777_q.field_71443_c, this.field_72777_q.field_71440_d);
            this.field_175015_z = this.field_174991_A.func_177066_a("final");
         } catch (IOException ioexception) {
            field_147599_m.warn((String)("Failed to load shader: " + resourcelocation), (Throwable)ioexception);
            this.field_174991_A = null;
            this.field_175015_z = null;
         } catch (JsonSyntaxException jsonsyntaxexception) {
            field_147599_m.warn((String)("Failed to load shader: " + resourcelocation), (Throwable)jsonsyntaxexception);
            this.field_174991_A = null;
            this.field_175015_z = null;
         }
      } else {
         this.field_174991_A = null;
         this.field_175015_z = null;
      }

   }

   public void func_174975_c() {
      if(this.func_174985_d()) {
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 0, 1);
         this.field_175015_z.func_178038_a(this.field_72777_q.field_71443_c, this.field_72777_q.field_71440_d, false);
         GlStateManager.func_179084_k();
      }

   }

   protected boolean func_174985_d() {
      return this.field_175015_z != null && this.field_174991_A != null && this.field_72777_q.field_71439_g != null && this.field_72777_q.field_71439_g.func_175149_v() && this.field_72777_q.field_71474_y.field_178883_an.func_151470_d();
   }

   private void func_174964_o() {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      if(this.field_175011_u != null) {
         this.field_175011_u.func_177362_c();
      }

      if(this.field_72781_x >= 0) {
         GLAllocation.func_74523_b(this.field_72781_x);
         this.field_72781_x = -1;
      }

      if(this.field_175005_X) {
         this.field_175011_u = new VertexBuffer(this.field_175014_r);
         this.func_174968_a(worldrenderer, -16.0F, true);
         worldrenderer.func_178977_d();
         worldrenderer.func_178965_a();
         this.field_175011_u.func_181722_a(worldrenderer.func_178966_f());
      } else {
         this.field_72781_x = GLAllocation.func_74526_a(1);
         GL11.glNewList(this.field_72781_x, 4864);
         this.func_174968_a(worldrenderer, -16.0F, true);
         tessellator.func_78381_a();
         GL11.glEndList();
      }

   }

   private void func_174980_p() {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      if(this.field_175012_t != null) {
         this.field_175012_t.func_177362_c();
      }

      if(this.field_72771_w >= 0) {
         GLAllocation.func_74523_b(this.field_72771_w);
         this.field_72771_w = -1;
      }

      if(this.field_175005_X) {
         this.field_175012_t = new VertexBuffer(this.field_175014_r);
         this.func_174968_a(worldrenderer, 16.0F, false);
         worldrenderer.func_178977_d();
         worldrenderer.func_178965_a();
         this.field_175012_t.func_181722_a(worldrenderer.func_178966_f());
      } else {
         this.field_72771_w = GLAllocation.func_74526_a(1);
         GL11.glNewList(this.field_72771_w, 4864);
         this.func_174968_a(worldrenderer, 16.0F, false);
         tessellator.func_78381_a();
         GL11.glEndList();
      }

   }

   private void func_174968_a(WorldRenderer p_174968_1_, float p_174968_2_, boolean p_174968_3_) {
      int i = 64;
      int j = 6;
      p_174968_1_.func_181668_a(7, DefaultVertexFormats.field_181705_e);

      for(int k = -384; k <= 384; k += 64) {
         for(int l = -384; l <= 384; l += 64) {
            float f = (float)k;
            float f1 = (float)(k + 64);
            if(p_174968_3_) {
               f1 = (float)k;
               f = (float)(k + 64);
            }

            p_174968_1_.func_181662_b((double)f, (double)p_174968_2_, (double)l).func_181675_d();
            p_174968_1_.func_181662_b((double)f1, (double)p_174968_2_, (double)l).func_181675_d();
            p_174968_1_.func_181662_b((double)f1, (double)p_174968_2_, (double)(l + 64)).func_181675_d();
            p_174968_1_.func_181662_b((double)f, (double)p_174968_2_, (double)(l + 64)).func_181675_d();
         }
      }

   }

   private void func_174963_q() {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      if(this.field_175013_s != null) {
         this.field_175013_s.func_177362_c();
      }

      if(this.field_72772_v >= 0) {
         GLAllocation.func_74523_b(this.field_72772_v);
         this.field_72772_v = -1;
      }

      if(this.field_175005_X) {
         this.field_175013_s = new VertexBuffer(this.field_175014_r);
         this.func_180444_a(worldrenderer);
         worldrenderer.func_178977_d();
         worldrenderer.func_178965_a();
         this.field_175013_s.func_181722_a(worldrenderer.func_178966_f());
      } else {
         this.field_72772_v = GLAllocation.func_74526_a(1);
         GlStateManager.func_179094_E();
         GL11.glNewList(this.field_72772_v, 4864);
         this.func_180444_a(worldrenderer);
         tessellator.func_78381_a();
         GL11.glEndList();
         GlStateManager.func_179121_F();
      }

   }

   private void func_180444_a(WorldRenderer p_180444_1_) {
      Random random = new Random(10842L);
      p_180444_1_.func_181668_a(7, DefaultVertexFormats.field_181705_e);

      for(int i = 0; i < 1500; ++i) {
         double d0 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d1 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d2 = (double)(random.nextFloat() * 2.0F - 1.0F);
         double d3 = (double)(0.15F + random.nextFloat() * 0.1F);
         double d4 = d0 * d0 + d1 * d1 + d2 * d2;
         if(d4 < 1.0D && d4 > 0.01D) {
            d4 = 1.0D / Math.sqrt(d4);
            d0 = d0 * d4;
            d1 = d1 * d4;
            d2 = d2 * d4;
            double d5 = d0 * 100.0D;
            double d6 = d1 * 100.0D;
            double d7 = d2 * 100.0D;
            double d8 = Math.atan2(d0, d2);
            double d9 = Math.sin(d8);
            double d10 = Math.cos(d8);
            double d11 = Math.atan2(Math.sqrt(d0 * d0 + d2 * d2), d1);
            double d12 = Math.sin(d11);
            double d13 = Math.cos(d11);
            double d14 = random.nextDouble() * 3.141592653589793D * 2.0D;
            double d15 = Math.sin(d14);
            double d16 = Math.cos(d14);

            for(int j = 0; j < 4; ++j) {
               double d17 = 0.0D;
               double d18 = (double)((j & 2) - 1) * d3;
               double d19 = (double)((j + 1 & 2) - 1) * d3;
               double d20 = 0.0D;
               double d21 = d18 * d16 - d19 * d15;
               double d22 = d19 * d16 + d18 * d15;
               double d23 = d21 * d12 + 0.0D * d13;
               double d24 = 0.0D * d12 - d21 * d13;
               double d25 = d24 * d9 - d22 * d10;
               double d26 = d22 * d9 + d24 * d10;
               p_180444_1_.func_181662_b(d5 + d25, d6 + d23, d7 + d26).func_181675_d();
            }
         }
      }

   }

   public void func_72732_a(WorldClient p_72732_1_) {
      if(this.field_72769_h != null) {
         this.field_72769_h.func_72848_b(this);
      }

      this.field_174992_B = Double.MIN_VALUE;
      this.field_174993_C = Double.MIN_VALUE;
      this.field_174987_D = Double.MIN_VALUE;
      this.field_174988_E = Integer.MIN_VALUE;
      this.field_174989_F = Integer.MIN_VALUE;
      this.field_174990_G = Integer.MIN_VALUE;
      this.field_175010_j.func_78717_a(p_72732_1_);
      this.field_72769_h = p_72732_1_;
      if(p_72732_1_ != null) {
         p_72732_1_.func_72954_a(this);
         this.func_72712_a();
      }

   }

   public void func_72712_a() {
      if(this.field_72769_h != null) {
         this.field_147595_R = true;
         Blocks.field_150362_t.func_150122_b(this.field_72777_q.field_71474_y.field_74347_j);
         Blocks.field_150361_u.func_150122_b(this.field_72777_q.field_71474_y.field_74347_j);
         this.field_72739_F = this.field_72777_q.field_71474_y.field_151451_c;
         boolean flag = this.field_175005_X;
         this.field_175005_X = OpenGlHelper.func_176075_f();
         if(flag && !this.field_175005_X) {
            this.field_174996_N = new RenderList();
            this.field_175007_a = new ListChunkFactory();
         } else if(!flag && this.field_175005_X) {
            this.field_174996_N = new VboRenderList();
            this.field_175007_a = new VboChunkFactory();
         }

         if(flag != this.field_175005_X) {
            this.func_174963_q();
            this.func_174980_p();
            this.func_174964_o();
         }

         if(this.field_175008_n != null) {
            this.field_175008_n.func_178160_a();
         }

         this.func_174986_e();
         synchronized(this.field_181024_n) {
            this.field_181024_n.clear();
         }

         this.field_175008_n = new ViewFrustum(this.field_72769_h, this.field_72777_q.field_71474_y.field_151451_c, this, this.field_175007_a);
         if(this.field_72769_h != null) {
            Entity entity = this.field_72777_q.func_175606_aa();
            if(entity != null) {
               this.field_175008_n.func_178163_a(entity.field_70165_t, entity.field_70161_v);
            }
         }

         this.field_72740_G = 2;
      }
   }

   protected void func_174986_e() {
      this.field_175009_l.clear();
      this.field_174995_M.func_178514_b();
   }

   public void func_72720_a(int p_72720_1_, int p_72720_2_) {
      if(OpenGlHelper.field_148824_g) {
         if(this.field_174991_A != null) {
            this.field_174991_A.func_148026_a(p_72720_1_, p_72720_2_);
         }

      }
   }

   public void func_180446_a(Entity p_180446_1_, ICamera p_180446_2_, float p_180446_3_) {
      if(this.field_72740_G > 0) {
         --this.field_72740_G;
      } else {
         double d0 = p_180446_1_.field_70169_q + (p_180446_1_.field_70165_t - p_180446_1_.field_70169_q) * (double)p_180446_3_;
         double d1 = p_180446_1_.field_70167_r + (p_180446_1_.field_70163_u - p_180446_1_.field_70167_r) * (double)p_180446_3_;
         double d2 = p_180446_1_.field_70166_s + (p_180446_1_.field_70161_v - p_180446_1_.field_70166_s) * (double)p_180446_3_;
         this.field_72769_h.field_72984_F.func_76320_a("prepare");
         TileEntityRendererDispatcher.field_147556_a.func_178470_a(this.field_72769_h, this.field_72777_q.func_110434_K(), this.field_72777_q.field_71466_p, this.field_72777_q.func_175606_aa(), p_180446_3_);
         this.field_175010_j.func_180597_a(this.field_72769_h, this.field_72777_q.field_71466_p, this.field_72777_q.func_175606_aa(), this.field_72777_q.field_147125_j, this.field_72777_q.field_71474_y, p_180446_3_);
         this.field_72748_H = 0;
         this.field_72749_I = 0;
         this.field_72750_J = 0;
         Entity entity = this.field_72777_q.func_175606_aa();
         double d3 = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)p_180446_3_;
         double d4 = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)p_180446_3_;
         double d5 = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)p_180446_3_;
         TileEntityRendererDispatcher.field_147554_b = d3;
         TileEntityRendererDispatcher.field_147555_c = d4;
         TileEntityRendererDispatcher.field_147552_d = d5;
         this.field_175010_j.func_178628_a(d3, d4, d5);
         this.field_72777_q.field_71460_t.func_180436_i();
         this.field_72769_h.field_72984_F.func_76318_c("global");
         List<Entity> list = this.field_72769_h.func_72910_y();
         this.field_72748_H = list.size();

         for(int i = 0; i < this.field_72769_h.field_73007_j.size(); ++i) {
            Entity entity1 = (Entity)this.field_72769_h.field_73007_j.get(i);
            ++this.field_72749_I;
            if(entity1.func_145770_h(d0, d1, d2)) {
               this.field_175010_j.func_147937_a(entity1, p_180446_3_);
            }
         }

         if(this.func_174985_d()) {
            GlStateManager.func_179143_c(519);
            GlStateManager.func_179106_n();
            this.field_175015_z.func_147614_f();
            this.field_175015_z.func_147610_a(false);
            this.field_72769_h.field_72984_F.func_76318_c("entityOutlines");
            RenderHelper.func_74518_a();
            this.field_175010_j.func_178632_c(true);

            for(int j = 0; j < list.size(); ++j) {
               Entity entity3 = (Entity)list.get(j);
               boolean flag = this.field_72777_q.func_175606_aa() instanceof EntityLivingBase && ((EntityLivingBase)this.field_72777_q.func_175606_aa()).func_70608_bn();
               boolean flag1 = entity3.func_145770_h(d0, d1, d2) && (entity3.field_70158_ak || p_180446_2_.func_78546_a(entity3.func_174813_aQ()) || entity3.field_70153_n == this.field_72777_q.field_71439_g) && entity3 instanceof EntityPlayer;
               if((entity3 != this.field_72777_q.func_175606_aa() || this.field_72777_q.field_71474_y.field_74320_O != 0 || flag) && flag1) {
                  this.field_175010_j.func_147937_a(entity3, p_180446_3_);
               }
            }

            this.field_175010_j.func_178632_c(false);
            RenderHelper.func_74519_b();
            GlStateManager.func_179132_a(false);
            this.field_174991_A.func_148018_a(p_180446_3_);
            GlStateManager.func_179145_e();
            GlStateManager.func_179132_a(true);
            this.field_72777_q.func_147110_a().func_147610_a(false);
            GlStateManager.func_179127_m();
            GlStateManager.func_179147_l();
            GlStateManager.func_179142_g();
            GlStateManager.func_179143_c(515);
            GlStateManager.func_179126_j();
            GlStateManager.func_179141_d();
         }

         this.field_72769_h.field_72984_F.func_76318_c("entities");

         label738:
         for(RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation : this.field_72755_R) {
            Chunk chunk = this.field_72769_h.func_175726_f(renderglobal$containerlocalrenderinformation.field_178036_a.func_178568_j());
            ClassInheritanceMultiMap<Entity> classinheritancemultimap = chunk.func_177429_s()[renderglobal$containerlocalrenderinformation.field_178036_a.func_178568_j().func_177956_o() / 16];
            if(!classinheritancemultimap.isEmpty()) {
               Iterator iterator = classinheritancemultimap.iterator();

               while(true) {
                  Entity entity2;
                  boolean flag2;
                  while(true) {
                     if(!iterator.hasNext()) {
                        continue label738;
                     }

                     entity2 = (Entity)iterator.next();
                     flag2 = this.field_175010_j.func_178635_a(entity2, p_180446_2_, d0, d1, d2) || entity2.field_70153_n == this.field_72777_q.field_71439_g;
                     if(!flag2) {
                        break;
                     }

                     boolean flag3 = this.field_72777_q.func_175606_aa() instanceof EntityLivingBase?((EntityLivingBase)this.field_72777_q.func_175606_aa()).func_70608_bn():false;
                     if((entity2 != this.field_72777_q.func_175606_aa() || this.field_72777_q.field_71474_y.field_74320_O != 0 || flag3) && (entity2.field_70163_u < 0.0D || entity2.field_70163_u >= 256.0D || this.field_72769_h.func_175667_e(new BlockPos(entity2)))) {
                        ++this.field_72749_I;
                        this.field_175010_j.func_147937_a(entity2, p_180446_3_);
                        break;
                     }
                  }

                  if(!flag2 && entity2 instanceof EntityWitherSkull) {
                     this.field_72777_q.func_175598_ae().func_178630_b(entity2, p_180446_3_);
                  }
               }
            }
         }

         this.field_72769_h.field_72984_F.func_76318_c("blockentities");
         RenderHelper.func_74519_b();

         for(RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1 : this.field_72755_R) {
            List<TileEntity> list1 = renderglobal$containerlocalrenderinformation1.field_178036_a.func_178571_g().func_178485_b();
            if(!list1.isEmpty()) {
               for(TileEntity tileentity2 : list1) {
                  TileEntityRendererDispatcher.field_147556_a.func_180546_a(tileentity2, p_180446_3_, -1);
               }
            }
         }

         synchronized(this.field_181024_n) {
            for(TileEntity tileentity : this.field_181024_n) {
               TileEntityRendererDispatcher.field_147556_a.func_180546_a(tileentity, p_180446_3_, -1);
            }
         }

         this.func_180443_s();

         for(DestroyBlockProgress destroyblockprogress : this.field_72738_E.values()) {
            BlockPos blockpos = destroyblockprogress.func_180246_b();
            TileEntity tileentity1 = this.field_72769_h.func_175625_s(blockpos);
            if(tileentity1 instanceof TileEntityChest) {
               TileEntityChest tileentitychest = (TileEntityChest)tileentity1;
               if(tileentitychest.field_145991_k != null) {
                  blockpos = blockpos.func_177972_a(EnumFacing.WEST);
                  tileentity1 = this.field_72769_h.func_175625_s(blockpos);
               } else if(tileentitychest.field_145992_i != null) {
                  blockpos = blockpos.func_177972_a(EnumFacing.NORTH);
                  tileentity1 = this.field_72769_h.func_175625_s(blockpos);
               }
            }

            Block block = this.field_72769_h.func_180495_p(blockpos).func_177230_c();
            if(tileentity1 != null && (block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockSign || block instanceof BlockSkull)) {
               TileEntityRendererDispatcher.field_147556_a.func_180546_a(tileentity1, p_180446_3_, destroyblockprogress.func_73106_e());
            }
         }

         this.func_174969_t();
         this.field_72777_q.field_71460_t.func_175072_h();
         this.field_72777_q.field_71424_I.func_76319_b();
      }
   }

   public String func_72735_c() {
      int i = this.field_175008_n.field_178164_f.length;
      int j = 0;

      for(RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation : this.field_72755_R) {
         CompiledChunk compiledchunk = renderglobal$containerlocalrenderinformation.field_178036_a.field_178590_b;
         if(compiledchunk != CompiledChunk.field_178502_a && !compiledchunk.func_178489_a()) {
            ++j;
         }
      }

      return String.format("C: %d/%d %sD: %d, %s", new Object[]{Integer.valueOf(j), Integer.valueOf(i), this.field_72777_q.field_175612_E?"(s) ":"", Integer.valueOf(this.field_72739_F), this.field_174995_M.func_178504_a()});
   }

   public String func_72723_d() {
      return "E: " + this.field_72749_I + "/" + this.field_72748_H + ", B: " + this.field_72750_J + ", I: " + (this.field_72748_H - this.field_72750_J - this.field_72749_I);
   }

   public void func_174970_a(Entity p_174970_1_, double p_174970_2_, ICamera p_174970_4_, int p_174970_5_, boolean p_174970_6_) {
      if(this.field_72777_q.field_71474_y.field_151451_c != this.field_72739_F) {
         this.func_72712_a();
      }

      this.field_72769_h.field_72984_F.func_76320_a("camera");
      double d0 = p_174970_1_.field_70165_t - this.field_174992_B;
      double d1 = p_174970_1_.field_70163_u - this.field_174993_C;
      double d2 = p_174970_1_.field_70161_v - this.field_174987_D;
      if(this.field_174988_E != p_174970_1_.field_70176_ah || this.field_174989_F != p_174970_1_.field_70162_ai || this.field_174990_G != p_174970_1_.field_70164_aj || d0 * d0 + d1 * d1 + d2 * d2 > 16.0D) {
         this.field_174992_B = p_174970_1_.field_70165_t;
         this.field_174993_C = p_174970_1_.field_70163_u;
         this.field_174987_D = p_174970_1_.field_70161_v;
         this.field_174988_E = p_174970_1_.field_70176_ah;
         this.field_174989_F = p_174970_1_.field_70162_ai;
         this.field_174990_G = p_174970_1_.field_70164_aj;
         this.field_175008_n.func_178163_a(p_174970_1_.field_70165_t, p_174970_1_.field_70161_v);
      }

      this.field_72769_h.field_72984_F.func_76318_c("renderlistcamera");
      double d3 = p_174970_1_.field_70142_S + (p_174970_1_.field_70165_t - p_174970_1_.field_70142_S) * p_174970_2_;
      double d4 = p_174970_1_.field_70137_T + (p_174970_1_.field_70163_u - p_174970_1_.field_70137_T) * p_174970_2_;
      double d5 = p_174970_1_.field_70136_U + (p_174970_1_.field_70161_v - p_174970_1_.field_70136_U) * p_174970_2_;
      this.field_174996_N.func_178004_a(d3, d4, d5);
      this.field_72769_h.field_72984_F.func_76318_c("cull");
      if(this.field_175001_U != null) {
         Frustum frustum = new Frustum(this.field_175001_U);
         frustum.func_78547_a(this.field_175003_W.field_181059_a, this.field_175003_W.field_181060_b, this.field_175003_W.field_181061_c);
         p_174970_4_ = frustum;
      }

      this.field_72777_q.field_71424_I.func_76318_c("culling");
      BlockPos blockpos1 = new BlockPos(d3, d4 + (double)p_174970_1_.func_70047_e(), d5);
      RenderChunk renderchunk = this.field_175008_n.func_178161_a(blockpos1);
      BlockPos blockpos = new BlockPos(MathHelper.func_76128_c(d3 / 16.0D) * 16, MathHelper.func_76128_c(d4 / 16.0D) * 16, MathHelper.func_76128_c(d5 / 16.0D) * 16);
      this.field_147595_R = this.field_147595_R || !this.field_175009_l.isEmpty() || p_174970_1_.field_70165_t != this.field_174997_H || p_174970_1_.field_70163_u != this.field_174998_I || p_174970_1_.field_70161_v != this.field_174999_J || (double)p_174970_1_.field_70125_A != this.field_175000_K || (double)p_174970_1_.field_70177_z != this.field_174994_L;
      this.field_174997_H = p_174970_1_.field_70165_t;
      this.field_174998_I = p_174970_1_.field_70163_u;
      this.field_174999_J = p_174970_1_.field_70161_v;
      this.field_175000_K = (double)p_174970_1_.field_70125_A;
      this.field_174994_L = (double)p_174970_1_.field_70177_z;
      boolean flag = this.field_175001_U != null;
      if(!flag && this.field_147595_R) {
         this.field_147595_R = false;
         this.field_72755_R = Lists.<RenderGlobal.ContainerLocalRenderInformation>newArrayList();
         Queue<RenderGlobal.ContainerLocalRenderInformation> queue = Lists.<RenderGlobal.ContainerLocalRenderInformation>newLinkedList();
         boolean flag1 = this.field_72777_q.field_175612_E;
         if(renderchunk != null) {
            boolean flag2 = false;
            RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation3 = new RenderGlobal.ContainerLocalRenderInformation(renderchunk, (EnumFacing)null, 0);
            Set<EnumFacing> set1 = this.func_174978_c(blockpos1);
            if(set1.size() == 1) {
               Vector3f vector3f = this.func_174962_a(p_174970_1_, p_174970_2_);
               EnumFacing enumfacing = EnumFacing.func_176737_a(vector3f.x, vector3f.y, vector3f.z).func_176734_d();
               set1.remove(enumfacing);
            }

            if(set1.isEmpty()) {
               flag2 = true;
            }

            if(flag2 && !p_174970_6_) {
               this.field_72755_R.add(renderglobal$containerlocalrenderinformation3);
            } else {
               if(p_174970_6_ && this.field_72769_h.func_180495_p(blockpos1).func_177230_c().func_149662_c()) {
                  flag1 = false;
               }

               renderchunk.func_178577_a(p_174970_5_);
               queue.add(renderglobal$containerlocalrenderinformation3);
            }
         } else {
            int i = blockpos1.func_177956_o() > 0?248:8;

            for(int j = -this.field_72739_F; j <= this.field_72739_F; ++j) {
               for(int k = -this.field_72739_F; k <= this.field_72739_F; ++k) {
                  RenderChunk renderchunk1 = this.field_175008_n.func_178161_a(new BlockPos((j << 4) + 8, i, (k << 4) + 8));
                  if(renderchunk1 != null && ((ICamera)p_174970_4_).func_78546_a(renderchunk1.field_178591_c)) {
                     renderchunk1.func_178577_a(p_174970_5_);
                     queue.add(new RenderGlobal.ContainerLocalRenderInformation(renderchunk1, (EnumFacing)null, 0));
                  }
               }
            }
         }

         while(!((Queue)queue).isEmpty()) {
            RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation1 = (RenderGlobal.ContainerLocalRenderInformation)queue.poll();
            RenderChunk renderchunk3 = renderglobal$containerlocalrenderinformation1.field_178036_a;
            EnumFacing enumfacing2 = renderglobal$containerlocalrenderinformation1.field_178034_b;
            BlockPos blockpos2 = renderchunk3.func_178568_j();
            this.field_72755_R.add(renderglobal$containerlocalrenderinformation1);

            for(EnumFacing enumfacing1 : EnumFacing.values()) {
               RenderChunk renderchunk2 = this.func_181562_a(blockpos, renderchunk3, enumfacing1);
               if((!flag1 || !renderglobal$containerlocalrenderinformation1.field_178035_c.contains(enumfacing1.func_176734_d())) && (!flag1 || enumfacing2 == null || renderchunk3.func_178571_g().func_178495_a(enumfacing2.func_176734_d(), enumfacing1)) && renderchunk2 != null && renderchunk2.func_178577_a(p_174970_5_) && ((ICamera)p_174970_4_).func_78546_a(renderchunk2.field_178591_c)) {
                  RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation = new RenderGlobal.ContainerLocalRenderInformation(renderchunk2, enumfacing1, renderglobal$containerlocalrenderinformation1.field_178032_d + 1);
                  renderglobal$containerlocalrenderinformation.field_178035_c.addAll(renderglobal$containerlocalrenderinformation1.field_178035_c);
                  renderglobal$containerlocalrenderinformation.field_178035_c.add(enumfacing1);
                  queue.add(renderglobal$containerlocalrenderinformation);
               }
            }
         }
      }

      if(this.field_175002_T) {
         this.func_174984_a(d3, d4, d5);
         this.field_175002_T = false;
      }

      this.field_174995_M.func_178513_e();
      Set<RenderChunk> set = this.field_175009_l;
      this.field_175009_l = Sets.<RenderChunk>newLinkedHashSet();

      for(RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation2 : this.field_72755_R) {
         RenderChunk renderchunk4 = renderglobal$containerlocalrenderinformation2.field_178036_a;
         if(renderchunk4.func_178569_m() || set.contains(renderchunk4)) {
            this.field_147595_R = true;
            if(this.func_174983_a(blockpos, renderglobal$containerlocalrenderinformation2.field_178036_a)) {
               this.field_72777_q.field_71424_I.func_76320_a("build near");
               this.field_174995_M.func_178505_b(renderchunk4);
               renderchunk4.func_178575_a(false);
               this.field_72777_q.field_71424_I.func_76319_b();
            } else {
               this.field_175009_l.add(renderchunk4);
            }
         }
      }

      this.field_175009_l.addAll(set);
      this.field_72777_q.field_71424_I.func_76319_b();
   }

   private boolean func_174983_a(BlockPos p_174983_1_, RenderChunk p_174983_2_) {
      BlockPos blockpos = p_174983_2_.func_178568_j();
      return MathHelper.func_76130_a(p_174983_1_.func_177958_n() - blockpos.func_177958_n()) > 16?false:(MathHelper.func_76130_a(p_174983_1_.func_177956_o() - blockpos.func_177956_o()) > 16?false:MathHelper.func_76130_a(p_174983_1_.func_177952_p() - blockpos.func_177952_p()) <= 16);
   }

   private Set<EnumFacing> func_174978_c(BlockPos p_174978_1_) {
      VisGraph visgraph = new VisGraph();
      BlockPos blockpos = new BlockPos(p_174978_1_.func_177958_n() >> 4 << 4, p_174978_1_.func_177956_o() >> 4 << 4, p_174978_1_.func_177952_p() >> 4 << 4);
      Chunk chunk = this.field_72769_h.func_175726_f(blockpos);

      for(BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.func_177975_b(blockpos, blockpos.func_177982_a(15, 15, 15))) {
         if(chunk.func_177428_a(blockpos$mutableblockpos).func_149662_c()) {
            visgraph.func_178606_a(blockpos$mutableblockpos);
         }
      }

      return visgraph.func_178609_b(p_174978_1_);
   }

   private RenderChunk func_181562_a(BlockPos p_181562_1_, RenderChunk p_181562_2_, EnumFacing p_181562_3_) {
      BlockPos blockpos = p_181562_2_.func_181701_a(p_181562_3_);
      return MathHelper.func_76130_a(p_181562_1_.func_177958_n() - blockpos.func_177958_n()) > this.field_72739_F * 16?null:(blockpos.func_177956_o() >= 0 && blockpos.func_177956_o() < 256?(MathHelper.func_76130_a(p_181562_1_.func_177952_p() - blockpos.func_177952_p()) > this.field_72739_F * 16?null:this.field_175008_n.func_178161_a(blockpos)):null);
   }

   private void func_174984_a(double p_174984_1_, double p_174984_3_, double p_174984_5_) {
      this.field_175001_U = new ClippingHelperImpl();
      ((ClippingHelperImpl)this.field_175001_U).func_78560_b();
      Matrix4f matrix4f = new Matrix4f(this.field_175001_U.field_178626_c);
      matrix4f.transpose();
      Matrix4f matrix4f1 = new Matrix4f(this.field_175001_U.field_178625_b);
      matrix4f1.transpose();
      Matrix4f matrix4f2 = new Matrix4f();
      Matrix4f.mul(matrix4f1, matrix4f, matrix4f2);
      matrix4f2.invert();
      this.field_175003_W.field_181059_a = p_174984_1_;
      this.field_175003_W.field_181060_b = p_174984_3_;
      this.field_175003_W.field_181061_c = p_174984_5_;
      this.field_175004_V[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
      this.field_175004_V[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
      this.field_175004_V[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
      this.field_175004_V[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
      this.field_175004_V[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
      this.field_175004_V[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
      this.field_175004_V[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_175004_V[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);

      for(int i = 0; i < 8; ++i) {
         Matrix4f.transform(matrix4f2, this.field_175004_V[i], this.field_175004_V[i]);
         this.field_175004_V[i].x /= this.field_175004_V[i].w;
         this.field_175004_V[i].y /= this.field_175004_V[i].w;
         this.field_175004_V[i].z /= this.field_175004_V[i].w;
         this.field_175004_V[i].w = 1.0F;
      }

   }

   protected Vector3f func_174962_a(Entity p_174962_1_, double p_174962_2_) {
      float f = (float)((double)p_174962_1_.field_70127_C + (double)(p_174962_1_.field_70125_A - p_174962_1_.field_70127_C) * p_174962_2_);
      float f1 = (float)((double)p_174962_1_.field_70126_B + (double)(p_174962_1_.field_70177_z - p_174962_1_.field_70126_B) * p_174962_2_);
      if(Minecraft.func_71410_x().field_71474_y.field_74320_O == 2) {
         f += 180.0F;
      }

      float f2 = MathHelper.func_76134_b(-f1 * 0.017453292F - 3.1415927F);
      float f3 = MathHelper.func_76126_a(-f1 * 0.017453292F - 3.1415927F);
      float f4 = -MathHelper.func_76134_b(-f * 0.017453292F);
      float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
      return new Vector3f(f3 * f4, f5, f2 * f4);
   }

   public int func_174977_a(EnumWorldBlockLayer p_174977_1_, double p_174977_2_, int p_174977_4_, Entity p_174977_5_) {
      RenderHelper.func_74518_a();
      if(p_174977_1_ == EnumWorldBlockLayer.TRANSLUCENT) {
         this.field_72777_q.field_71424_I.func_76320_a("translucent_sort");
         double d0 = p_174977_5_.field_70165_t - this.field_147596_f;
         double d1 = p_174977_5_.field_70163_u - this.field_147597_g;
         double d2 = p_174977_5_.field_70161_v - this.field_147602_h;
         if(d0 * d0 + d1 * d1 + d2 * d2 > 1.0D) {
            this.field_147596_f = p_174977_5_.field_70165_t;
            this.field_147597_g = p_174977_5_.field_70163_u;
            this.field_147602_h = p_174977_5_.field_70161_v;
            int k = 0;

            for(RenderGlobal.ContainerLocalRenderInformation renderglobal$containerlocalrenderinformation : this.field_72755_R) {
               if(renderglobal$containerlocalrenderinformation.field_178036_a.field_178590_b.func_178492_d(p_174977_1_) && k++ < 15) {
                  this.field_174995_M.func_178509_c(renderglobal$containerlocalrenderinformation.field_178036_a);
               }
            }
         }

         this.field_72777_q.field_71424_I.func_76319_b();
      }

      this.field_72777_q.field_71424_I.func_76320_a("filterempty");
      int l = 0;
      boolean flag = p_174977_1_ == EnumWorldBlockLayer.TRANSLUCENT;
      int i1 = flag?this.field_72755_R.size() - 1:0;
      int i = flag?-1:this.field_72755_R.size();
      int j1 = flag?-1:1;

      for(int j = i1; j != i; j += j1) {
         RenderChunk renderchunk = ((RenderGlobal.ContainerLocalRenderInformation)this.field_72755_R.get(j)).field_178036_a;
         if(!renderchunk.func_178571_g().func_178491_b(p_174977_1_)) {
            ++l;
            this.field_174996_N.func_178002_a(renderchunk, p_174977_1_);
         }
      }

      this.field_72777_q.field_71424_I.func_76318_c("render_" + p_174977_1_);
      this.func_174982_a(p_174977_1_);
      this.field_72777_q.field_71424_I.func_76319_b();
      return l;
   }

   private void func_174982_a(EnumWorldBlockLayer p_174982_1_) {
      this.field_72777_q.field_71460_t.func_180436_i();
      if(OpenGlHelper.func_176075_f()) {
         GL11.glEnableClientState('\u8074');
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
         GL11.glEnableClientState('\u8078');
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77476_b);
         GL11.glEnableClientState('\u8078');
         OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
         GL11.glEnableClientState('\u8076');
      }

      this.field_174996_N.func_178001_a(p_174982_1_);
      if(OpenGlHelper.func_176075_f()) {
         for(VertexFormatElement vertexformatelement : DefaultVertexFormats.field_176600_a.func_177343_g()) {
            VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.func_177375_c();
            int i = vertexformatelement.func_177369_e();
            switch(vertexformatelement$enumusage) {
            case POSITION:
               GL11.glDisableClientState('\u8074');
               break;
            case UV:
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a + i);
               GL11.glDisableClientState('\u8078');
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
               break;
            case COLOR:
               GL11.glDisableClientState('\u8076');
               GlStateManager.func_179117_G();
            }
         }
      }

      this.field_72777_q.field_71460_t.func_175072_h();
   }

   private void func_174965_a(Iterator<DestroyBlockProgress> p_174965_1_) {
      while(p_174965_1_.hasNext()) {
         DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)p_174965_1_.next();
         int i = destroyblockprogress.func_82743_f();
         if(this.field_72773_u - i > 400) {
            p_174965_1_.remove();
         }
      }

   }

   public void func_72734_e() {
      ++this.field_72773_u;
      if(this.field_72773_u % 20 == 0) {
         this.func_174965_a(this.field_72738_E.values().iterator());
      }

   }

   private void func_180448_r() {
      GlStateManager.func_179106_n();
      GlStateManager.func_179118_c();
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      RenderHelper.func_74518_a();
      GlStateManager.func_179132_a(false);
      this.field_72770_i.func_110577_a(field_110926_k);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();

      for(int i = 0; i < 6; ++i) {
         GlStateManager.func_179094_E();
         if(i == 1) {
            GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
         }

         if(i == 2) {
            GlStateManager.func_179114_b(-90.0F, 1.0F, 0.0F, 0.0F);
         }

         if(i == 3) {
            GlStateManager.func_179114_b(180.0F, 1.0F, 0.0F, 0.0F);
         }

         if(i == 4) {
            GlStateManager.func_179114_b(90.0F, 0.0F, 0.0F, 1.0F);
         }

         if(i == 5) {
            GlStateManager.func_179114_b(-90.0F, 0.0F, 0.0F, 1.0F);
         }

         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
         worldrenderer.func_181662_b(-100.0D, -100.0D, -100.0D).func_181673_a(0.0D, 0.0D).func_181669_b(40, 40, 40, 255).func_181675_d();
         worldrenderer.func_181662_b(-100.0D, -100.0D, 100.0D).func_181673_a(0.0D, 16.0D).func_181669_b(40, 40, 40, 255).func_181675_d();
         worldrenderer.func_181662_b(100.0D, -100.0D, 100.0D).func_181673_a(16.0D, 16.0D).func_181669_b(40, 40, 40, 255).func_181675_d();
         worldrenderer.func_181662_b(100.0D, -100.0D, -100.0D).func_181673_a(16.0D, 0.0D).func_181669_b(40, 40, 40, 255).func_181675_d();
         tessellator.func_78381_a();
         GlStateManager.func_179121_F();
      }

      GlStateManager.func_179132_a(true);
      GlStateManager.func_179098_w();
      GlStateManager.func_179141_d();
   }

   public void func_174976_a(float p_174976_1_, int p_174976_2_) {
      if(this.field_72777_q.field_71441_e.field_73011_w.func_177502_q() == 1) {
         this.func_180448_r();
      } else if(this.field_72777_q.field_71441_e.field_73011_w.func_76569_d()) {
         GlStateManager.func_179090_x();
         Vec3 vec3 = this.field_72769_h.func_72833_a(this.field_72777_q.func_175606_aa(), p_174976_1_);
         float f = (float)vec3.field_72450_a;
         float f1 = (float)vec3.field_72448_b;
         float f2 = (float)vec3.field_72449_c;
         if(p_174976_2_ != 2) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
         }

         GlStateManager.func_179124_c(f, f1, f2);
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer worldrenderer = tessellator.func_178180_c();
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179127_m();
         GlStateManager.func_179124_c(f, f1, f2);
         if(this.field_175005_X) {
            this.field_175012_t.func_177359_a();
            GL11.glEnableClientState('\u8074');
            GL11.glVertexPointer(3, 5126, 12, 0L);
            this.field_175012_t.func_177358_a(7);
            this.field_175012_t.func_177361_b();
            GL11.glDisableClientState('\u8074');
         } else {
            GlStateManager.func_179148_o(this.field_72771_w);
         }

         GlStateManager.func_179106_n();
         GlStateManager.func_179118_c();
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         RenderHelper.func_74518_a();
         float[] afloat = this.field_72769_h.field_73011_w.func_76560_a(this.field_72769_h.func_72826_c(p_174976_1_), p_174976_1_);
         if(afloat != null) {
            GlStateManager.func_179090_x();
            GlStateManager.func_179103_j(7425);
            GlStateManager.func_179094_E();
            GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(MathHelper.func_76126_a(this.field_72769_h.func_72929_e(p_174976_1_)) < 0.0F?180.0F:0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.func_179114_b(90.0F, 0.0F, 0.0F, 1.0F);
            float f6 = afloat[0];
            float f7 = afloat[1];
            float f8 = afloat[2];
            if(p_174976_2_ != 2) {
               float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
               float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
               float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
               f6 = f9;
               f7 = f10;
               f8 = f11;
            }

            worldrenderer.func_181668_a(6, DefaultVertexFormats.field_181706_f);
            worldrenderer.func_181662_b(0.0D, 100.0D, 0.0D).func_181666_a(f6, f7, f8, afloat[3]).func_181675_d();
            int j = 16;

            for(int l = 0; l <= 16; ++l) {
               float f21 = (float)l * 3.1415927F * 2.0F / 16.0F;
               float f12 = MathHelper.func_76126_a(f21);
               float f13 = MathHelper.func_76134_b(f21);
               worldrenderer.func_181662_b((double)(f12 * 120.0F), (double)(f13 * 120.0F), (double)(-f13 * 40.0F * afloat[3])).func_181666_a(afloat[0], afloat[1], afloat[2], 0.0F).func_181675_d();
            }

            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
            GlStateManager.func_179103_j(7424);
         }

         GlStateManager.func_179098_w();
         GlStateManager.func_179120_a(770, 1, 1, 0);
         GlStateManager.func_179094_E();
         float f16 = 1.0F - this.field_72769_h.func_72867_j(p_174976_1_);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, f16);
         GlStateManager.func_179114_b(-90.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(this.field_72769_h.func_72826_c(p_174976_1_) * 360.0F, 1.0F, 0.0F, 0.0F);
         float f17 = 30.0F;
         this.field_72770_i.func_110577_a(field_110928_i);
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         worldrenderer.func_181662_b((double)(-f17), 100.0D, (double)(-f17)).func_181673_a(0.0D, 0.0D).func_181675_d();
         worldrenderer.func_181662_b((double)f17, 100.0D, (double)(-f17)).func_181673_a(1.0D, 0.0D).func_181675_d();
         worldrenderer.func_181662_b((double)f17, 100.0D, (double)f17).func_181673_a(1.0D, 1.0D).func_181675_d();
         worldrenderer.func_181662_b((double)(-f17), 100.0D, (double)f17).func_181673_a(0.0D, 1.0D).func_181675_d();
         tessellator.func_78381_a();
         f17 = 20.0F;
         this.field_72770_i.func_110577_a(field_110927_h);
         int i = this.field_72769_h.func_72853_d();
         int k = i % 4;
         int i1 = i / 4 % 2;
         float f22 = (float)(k + 0) / 4.0F;
         float f23 = (float)(i1 + 0) / 2.0F;
         float f24 = (float)(k + 1) / 4.0F;
         float f14 = (float)(i1 + 1) / 2.0F;
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         worldrenderer.func_181662_b((double)(-f17), -100.0D, (double)f17).func_181673_a((double)f24, (double)f14).func_181675_d();
         worldrenderer.func_181662_b((double)f17, -100.0D, (double)f17).func_181673_a((double)f22, (double)f14).func_181675_d();
         worldrenderer.func_181662_b((double)f17, -100.0D, (double)(-f17)).func_181673_a((double)f22, (double)f23).func_181675_d();
         worldrenderer.func_181662_b((double)(-f17), -100.0D, (double)(-f17)).func_181673_a((double)f24, (double)f23).func_181675_d();
         tessellator.func_78381_a();
         GlStateManager.func_179090_x();
         float f15 = this.field_72769_h.func_72880_h(p_174976_1_) * f16;
         if(f15 > 0.0F) {
            GlStateManager.func_179131_c(f15, f15, f15, f15);
            if(this.field_175005_X) {
               this.field_175013_s.func_177359_a();
               GL11.glEnableClientState('\u8074');
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.field_175013_s.func_177358_a(7);
               this.field_175013_s.func_177361_b();
               GL11.glDisableClientState('\u8074');
            } else {
               GlStateManager.func_179148_o(this.field_72772_v);
            }
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.func_179084_k();
         GlStateManager.func_179141_d();
         GlStateManager.func_179127_m();
         GlStateManager.func_179121_F();
         GlStateManager.func_179090_x();
         GlStateManager.func_179124_c(0.0F, 0.0F, 0.0F);
         double d0 = this.field_72777_q.field_71439_g.func_174824_e(p_174976_1_).field_72448_b - this.field_72769_h.func_72919_O();
         if(d0 < 0.0D) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(0.0F, 12.0F, 0.0F);
            if(this.field_175005_X) {
               this.field_175011_u.func_177359_a();
               GL11.glEnableClientState('\u8074');
               GL11.glVertexPointer(3, 5126, 12, 0L);
               this.field_175011_u.func_177358_a(7);
               this.field_175011_u.func_177361_b();
               GL11.glDisableClientState('\u8074');
            } else {
               GlStateManager.func_179148_o(this.field_72781_x);
            }

            GlStateManager.func_179121_F();
            float f18 = 1.0F;
            float f19 = -((float)(d0 + 65.0D));
            float f20 = -1.0F;
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
            worldrenderer.func_181662_b(-1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, (double)f19, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, (double)f19, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(-1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, -1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            worldrenderer.func_181662_b(1.0D, -1.0D, -1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            tessellator.func_78381_a();
         }

         if(this.field_72769_h.field_73011_w.func_76561_g()) {
            GlStateManager.func_179124_c(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
         } else {
            GlStateManager.func_179124_c(f, f1, f2);
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b(0.0F, -((float)(d0 - 16.0D)), 0.0F);
         GlStateManager.func_179148_o(this.field_72781_x);
         GlStateManager.func_179121_F();
         GlStateManager.func_179098_w();
         GlStateManager.func_179132_a(true);
      }
   }

   public void func_180447_b(float p_180447_1_, int p_180447_2_) {
      if(this.field_72777_q.field_71441_e.field_73011_w.func_76569_d()) {
         if(this.field_72777_q.field_71474_y.func_181147_e() == 2) {
            this.func_180445_c(p_180447_1_, p_180447_2_);
         } else {
            GlStateManager.func_179129_p();
            float f = (float)(this.field_72777_q.func_175606_aa().field_70137_T + (this.field_72777_q.func_175606_aa().field_70163_u - this.field_72777_q.func_175606_aa().field_70137_T) * (double)p_180447_1_);
            int i = 32;
            int j = 8;
            Tessellator tessellator = Tessellator.func_178181_a();
            WorldRenderer worldrenderer = tessellator.func_178180_c();
            this.field_72770_i.func_110577_a(field_110925_j);
            GlStateManager.func_179147_l();
            GlStateManager.func_179120_a(770, 771, 1, 0);
            Vec3 vec3 = this.field_72769_h.func_72824_f(p_180447_1_);
            float f1 = (float)vec3.field_72450_a;
            float f2 = (float)vec3.field_72448_b;
            float f3 = (float)vec3.field_72449_c;
            if(p_180447_2_ != 2) {
               float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
               float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
               float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
               f1 = f4;
               f2 = f5;
               f3 = f6;
            }

            float f10 = 4.8828125E-4F;
            double d2 = (double)((float)this.field_72773_u + p_180447_1_);
            double d0 = this.field_72777_q.func_175606_aa().field_70169_q + (this.field_72777_q.func_175606_aa().field_70165_t - this.field_72777_q.func_175606_aa().field_70169_q) * (double)p_180447_1_ + d2 * 0.029999999329447746D;
            double d1 = this.field_72777_q.func_175606_aa().field_70166_s + (this.field_72777_q.func_175606_aa().field_70161_v - this.field_72777_q.func_175606_aa().field_70166_s) * (double)p_180447_1_;
            int k = MathHelper.func_76128_c(d0 / 2048.0D);
            int l = MathHelper.func_76128_c(d1 / 2048.0D);
            d0 = d0 - (double)(k * 2048);
            d1 = d1 - (double)(l * 2048);
            float f7 = this.field_72769_h.field_73011_w.func_76571_f() - f + 0.33F;
            float f8 = (float)(d0 * 4.8828125E-4D);
            float f9 = (float)(d1 * 4.8828125E-4D);
            worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);

            for(int i1 = -256; i1 < 256; i1 += 32) {
               for(int j1 = -256; j1 < 256; j1 += 32) {
                  worldrenderer.func_181662_b((double)(i1 + 0), (double)f7, (double)(j1 + 32)).func_181673_a((double)((float)(i1 + 0) * 4.8828125E-4F + f8), (double)((float)(j1 + 32) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
                  worldrenderer.func_181662_b((double)(i1 + 32), (double)f7, (double)(j1 + 32)).func_181673_a((double)((float)(i1 + 32) * 4.8828125E-4F + f8), (double)((float)(j1 + 32) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
                  worldrenderer.func_181662_b((double)(i1 + 32), (double)f7, (double)(j1 + 0)).func_181673_a((double)((float)(i1 + 32) * 4.8828125E-4F + f8), (double)((float)(j1 + 0) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
                  worldrenderer.func_181662_b((double)(i1 + 0), (double)f7, (double)(j1 + 0)).func_181673_a((double)((float)(i1 + 0) * 4.8828125E-4F + f8), (double)((float)(j1 + 0) * 4.8828125E-4F + f9)).func_181666_a(f1, f2, f3, 0.8F).func_181675_d();
               }
            }

            tessellator.func_78381_a();
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179084_k();
            GlStateManager.func_179089_o();
         }
      }
   }

   public boolean func_72721_a(double p_72721_1_, double p_72721_3_, double p_72721_5_, float p_72721_7_) {
      return false;
   }

   private void func_180445_c(float p_180445_1_, int p_180445_2_) {
      GlStateManager.func_179129_p();
      float f = (float)(this.field_72777_q.func_175606_aa().field_70137_T + (this.field_72777_q.func_175606_aa().field_70163_u - this.field_72777_q.func_175606_aa().field_70137_T) * (double)p_180445_1_);
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      float f1 = 12.0F;
      float f2 = 4.0F;
      double d0 = (double)((float)this.field_72773_u + p_180445_1_);
      double d1 = (this.field_72777_q.func_175606_aa().field_70169_q + (this.field_72777_q.func_175606_aa().field_70165_t - this.field_72777_q.func_175606_aa().field_70169_q) * (double)p_180445_1_ + d0 * 0.029999999329447746D) / 12.0D;
      double d2 = (this.field_72777_q.func_175606_aa().field_70166_s + (this.field_72777_q.func_175606_aa().field_70161_v - this.field_72777_q.func_175606_aa().field_70166_s) * (double)p_180445_1_) / 12.0D + 0.33000001311302185D;
      float f3 = this.field_72769_h.field_73011_w.func_76571_f() - f + 0.33F;
      int i = MathHelper.func_76128_c(d1 / 2048.0D);
      int j = MathHelper.func_76128_c(d2 / 2048.0D);
      d1 = d1 - (double)(i * 2048);
      d2 = d2 - (double)(j * 2048);
      this.field_72770_i.func_110577_a(field_110925_j);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      Vec3 vec3 = this.field_72769_h.func_72824_f(p_180445_1_);
      float f4 = (float)vec3.field_72450_a;
      float f5 = (float)vec3.field_72448_b;
      float f6 = (float)vec3.field_72449_c;
      if(p_180445_2_ != 2) {
         float f7 = (f4 * 30.0F + f5 * 59.0F + f6 * 11.0F) / 100.0F;
         float f8 = (f4 * 30.0F + f5 * 70.0F) / 100.0F;
         float f9 = (f4 * 30.0F + f6 * 70.0F) / 100.0F;
         f4 = f7;
         f5 = f8;
         f6 = f9;
      }

      float f26 = f4 * 0.9F;
      float f27 = f5 * 0.9F;
      float f28 = f6 * 0.9F;
      float f10 = f4 * 0.7F;
      float f11 = f5 * 0.7F;
      float f12 = f6 * 0.7F;
      float f13 = f4 * 0.8F;
      float f14 = f5 * 0.8F;
      float f15 = f6 * 0.8F;
      float f16 = 0.00390625F;
      float f17 = (float)MathHelper.func_76128_c(d1) * 0.00390625F;
      float f18 = (float)MathHelper.func_76128_c(d2) * 0.00390625F;
      float f19 = (float)(d1 - (double)MathHelper.func_76128_c(d1));
      float f20 = (float)(d2 - (double)MathHelper.func_76128_c(d2));
      int k = 8;
      int l = 4;
      float f21 = 9.765625E-4F;
      GlStateManager.func_179152_a(12.0F, 1.0F, 12.0F);

      for(int i1 = 0; i1 < 2; ++i1) {
         if(i1 == 0) {
            GlStateManager.func_179135_a(false, false, false, false);
         } else {
            switch(p_180445_2_) {
            case 0:
               GlStateManager.func_179135_a(false, true, true, true);
               break;
            case 1:
               GlStateManager.func_179135_a(true, false, false, true);
               break;
            case 2:
               GlStateManager.func_179135_a(true, true, true, true);
            }
         }

         for(int j1 = -3; j1 <= 4; ++j1) {
            for(int k1 = -3; k1 <= 4; ++k1) {
               worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181712_l);
               float f22 = (float)(j1 * 8);
               float f23 = (float)(k1 * 8);
               float f24 = f22 - f19;
               float f25 = f23 - f20;
               if(f3 > -5.0F) {
                  worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
                  worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
                  worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
                  worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f10, f11, f12, 0.8F).func_181663_c(0.0F, -1.0F, 0.0F).func_181675_d();
               }

               if(f3 <= 5.0F) {
                  worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
                  worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
                  worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
                  worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F - 9.765625E-4F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f4, f5, f6, 0.8F).func_181663_c(0.0F, 1.0F, 0.0F).func_181675_d();
               }

               if(j1 > -1) {
                  for(int l1 = 0; l1 < 8; ++l1) {
                     worldrenderer.func_181662_b((double)(f24 + (float)l1 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)l1 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + (float)l1 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)l1 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + (float)l1 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)l1 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + (float)l1 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)l1 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(-1.0F, 0.0F, 0.0F).func_181675_d();
                  }
               }

               if(j1 <= 1) {
                  for(int i2 = 0; i2 < 8; ++i2) {
                     worldrenderer.func_181662_b((double)(f24 + (float)i2 + 1.0F - 9.765625E-4F), (double)(f3 + 0.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)i2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + (float)i2 + 1.0F - 9.765625E-4F), (double)(f3 + 4.0F), (double)(f25 + 8.0F)).func_181673_a((double)((f22 + (float)i2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 8.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + (float)i2 + 1.0F - 9.765625E-4F), (double)(f3 + 4.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)i2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + (float)i2 + 1.0F - 9.765625E-4F), (double)(f3 + 0.0F), (double)(f25 + 0.0F)).func_181673_a((double)((f22 + (float)i2 + 0.5F) * 0.00390625F + f17), (double)((f23 + 0.0F) * 0.00390625F + f18)).func_181666_a(f26, f27, f28, 0.8F).func_181663_c(1.0F, 0.0F, 0.0F).func_181675_d();
                  }
               }

               if(k1 > -1) {
                  for(int j2 = 0; j2 < 8; ++j2) {
                     worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + (float)j2 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)j2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F), (double)(f25 + (float)j2 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)j2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + (float)j2 + 0.0F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)j2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + (float)j2 + 0.0F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)j2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, -1.0F).func_181675_d();
                  }
               }

               if(k1 <= 1) {
                  for(int k2 = 0; k2 < 8; ++k2) {
                     worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 4.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 4.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + 8.0F), (double)(f3 + 0.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 8.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                     worldrenderer.func_181662_b((double)(f24 + 0.0F), (double)(f3 + 0.0F), (double)(f25 + (float)k2 + 1.0F - 9.765625E-4F)).func_181673_a((double)((f22 + 0.0F) * 0.00390625F + f17), (double)((f23 + (float)k2 + 0.5F) * 0.00390625F + f18)).func_181666_a(f13, f14, f15, 0.8F).func_181663_c(0.0F, 0.0F, 1.0F).func_181675_d();
                  }
               }

               tessellator.func_78381_a();
            }
         }
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179084_k();
      GlStateManager.func_179089_o();
   }

   public void func_174967_a(long p_174967_1_) {
      this.field_147595_R |= this.field_174995_M.func_178516_a(p_174967_1_);
      if(!this.field_175009_l.isEmpty()) {
         Iterator<RenderChunk> iterator = this.field_175009_l.iterator();

         while(iterator.hasNext()) {
            RenderChunk renderchunk = (RenderChunk)iterator.next();
            if(!this.field_174995_M.func_178507_a(renderchunk)) {
               break;
            }

            renderchunk.func_178575_a(false);
            iterator.remove();
            long i = p_174967_1_ - System.nanoTime();
            if(i < 0L) {
               break;
            }
         }
      }

   }

   public void func_180449_a(Entity p_180449_1_, float p_180449_2_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      WorldBorder worldborder = this.field_72769_h.func_175723_af();
      double d0 = (double)(this.field_72777_q.field_71474_y.field_151451_c * 16);
      if(p_180449_1_.field_70165_t >= worldborder.func_177728_d() - d0 || p_180449_1_.field_70165_t <= worldborder.func_177726_b() + d0 || p_180449_1_.field_70161_v >= worldborder.func_177733_e() - d0 || p_180449_1_.field_70161_v <= worldborder.func_177736_c() + d0) {
         double d1 = 1.0D - worldborder.func_177745_a(p_180449_1_) / d0;
         d1 = Math.pow(d1, 4.0D);
         double d2 = p_180449_1_.field_70142_S + (p_180449_1_.field_70165_t - p_180449_1_.field_70142_S) * (double)p_180449_2_;
         double d3 = p_180449_1_.field_70137_T + (p_180449_1_.field_70163_u - p_180449_1_.field_70137_T) * (double)p_180449_2_;
         double d4 = p_180449_1_.field_70136_U + (p_180449_1_.field_70161_v - p_180449_1_.field_70136_U) * (double)p_180449_2_;
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 1, 1, 0);
         this.field_72770_i.func_110577_a(field_175006_g);
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179094_E();
         int i = worldborder.func_177734_a().func_177766_a();
         float f = (float)(i >> 16 & 255) / 255.0F;
         float f1 = (float)(i >> 8 & 255) / 255.0F;
         float f2 = (float)(i & 255) / 255.0F;
         GlStateManager.func_179131_c(f, f1, f2, (float)d1);
         GlStateManager.func_179136_a(-3.0F, -3.0F);
         GlStateManager.func_179088_q();
         GlStateManager.func_179092_a(516, 0.1F);
         GlStateManager.func_179141_d();
         GlStateManager.func_179129_p();
         float f3 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F;
         float f4 = 0.0F;
         float f5 = 0.0F;
         float f6 = 128.0F;
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
         worldrenderer.func_178969_c(-d2, -d3, -d4);
         double d5 = Math.max((double)MathHelper.func_76128_c(d4 - d0), worldborder.func_177736_c());
         double d6 = Math.min((double)MathHelper.func_76143_f(d4 + d0), worldborder.func_177733_e());
         if(d2 > worldborder.func_177728_d() - d0) {
            float f7 = 0.0F;

            for(double d7 = d5; d7 < d6; f7 += 0.5F) {
               double d8 = Math.min(1.0D, d6 - d7);
               float f8 = (float)d8 * 0.5F;
               worldrenderer.func_181662_b(worldborder.func_177728_d(), 256.0D, d7).func_181673_a((double)(f3 + f7), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(worldborder.func_177728_d(), 256.0D, d7 + d8).func_181673_a((double)(f3 + f8 + f7), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(worldborder.func_177728_d(), 0.0D, d7 + d8).func_181673_a((double)(f3 + f8 + f7), (double)(f3 + 128.0F)).func_181675_d();
               worldrenderer.func_181662_b(worldborder.func_177728_d(), 0.0D, d7).func_181673_a((double)(f3 + f7), (double)(f3 + 128.0F)).func_181675_d();
               ++d7;
            }
         }

         if(d2 < worldborder.func_177726_b() + d0) {
            float f9 = 0.0F;

            for(double d9 = d5; d9 < d6; f9 += 0.5F) {
               double d12 = Math.min(1.0D, d6 - d9);
               float f12 = (float)d12 * 0.5F;
               worldrenderer.func_181662_b(worldborder.func_177726_b(), 256.0D, d9).func_181673_a((double)(f3 + f9), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(worldborder.func_177726_b(), 256.0D, d9 + d12).func_181673_a((double)(f3 + f12 + f9), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(worldborder.func_177726_b(), 0.0D, d9 + d12).func_181673_a((double)(f3 + f12 + f9), (double)(f3 + 128.0F)).func_181675_d();
               worldrenderer.func_181662_b(worldborder.func_177726_b(), 0.0D, d9).func_181673_a((double)(f3 + f9), (double)(f3 + 128.0F)).func_181675_d();
               ++d9;
            }
         }

         d5 = Math.max((double)MathHelper.func_76128_c(d2 - d0), worldborder.func_177726_b());
         d6 = Math.min((double)MathHelper.func_76143_f(d2 + d0), worldborder.func_177728_d());
         if(d4 > worldborder.func_177733_e() - d0) {
            float f10 = 0.0F;

            for(double d10 = d5; d10 < d6; f10 += 0.5F) {
               double d13 = Math.min(1.0D, d6 - d10);
               float f13 = (float)d13 * 0.5F;
               worldrenderer.func_181662_b(d10, 256.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f10), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(d10 + d13, 256.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f13 + f10), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(d10 + d13, 0.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f13 + f10), (double)(f3 + 128.0F)).func_181675_d();
               worldrenderer.func_181662_b(d10, 0.0D, worldborder.func_177733_e()).func_181673_a((double)(f3 + f10), (double)(f3 + 128.0F)).func_181675_d();
               ++d10;
            }
         }

         if(d4 < worldborder.func_177736_c() + d0) {
            float f11 = 0.0F;

            for(double d11 = d5; d11 < d6; f11 += 0.5F) {
               double d14 = Math.min(1.0D, d6 - d11);
               float f14 = (float)d14 * 0.5F;
               worldrenderer.func_181662_b(d11, 256.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f11), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(d11 + d14, 256.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 0.0F)).func_181675_d();
               worldrenderer.func_181662_b(d11 + d14, 0.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f14 + f11), (double)(f3 + 128.0F)).func_181675_d();
               worldrenderer.func_181662_b(d11, 0.0D, worldborder.func_177736_c()).func_181673_a((double)(f3 + f11), (double)(f3 + 128.0F)).func_181675_d();
               ++d11;
            }
         }

         tessellator.func_78381_a();
         worldrenderer.func_178969_c(0.0D, 0.0D, 0.0D);
         GlStateManager.func_179089_o();
         GlStateManager.func_179118_c();
         GlStateManager.func_179136_a(0.0F, 0.0F);
         GlStateManager.func_179113_r();
         GlStateManager.func_179141_d();
         GlStateManager.func_179084_k();
         GlStateManager.func_179121_F();
         GlStateManager.func_179132_a(true);
      }
   }

   private void func_180443_s() {
      GlStateManager.func_179120_a(774, 768, 1, 0);
      GlStateManager.func_179147_l();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 0.5F);
      GlStateManager.func_179136_a(-3.0F, -3.0F);
      GlStateManager.func_179088_q();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179141_d();
      GlStateManager.func_179094_E();
   }

   private void func_174969_t() {
      GlStateManager.func_179118_c();
      GlStateManager.func_179136_a(0.0F, 0.0F);
      GlStateManager.func_179113_r();
      GlStateManager.func_179141_d();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179121_F();
   }

   public void func_174981_a(Tessellator p_174981_1_, WorldRenderer p_174981_2_, Entity p_174981_3_, float p_174981_4_) {
      double d0 = p_174981_3_.field_70142_S + (p_174981_3_.field_70165_t - p_174981_3_.field_70142_S) * (double)p_174981_4_;
      double d1 = p_174981_3_.field_70137_T + (p_174981_3_.field_70163_u - p_174981_3_.field_70137_T) * (double)p_174981_4_;
      double d2 = p_174981_3_.field_70136_U + (p_174981_3_.field_70161_v - p_174981_3_.field_70136_U) * (double)p_174981_4_;
      if(!this.field_72738_E.isEmpty()) {
         this.field_72770_i.func_110577_a(TextureMap.field_110575_b);
         this.func_180443_s();
         p_174981_2_.func_181668_a(7, DefaultVertexFormats.field_176600_a);
         p_174981_2_.func_178969_c(-d0, -d1, -d2);
         p_174981_2_.func_78914_f();
         Iterator<DestroyBlockProgress> iterator = this.field_72738_E.values().iterator();

         while(iterator.hasNext()) {
            DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)iterator.next();
            BlockPos blockpos = destroyblockprogress.func_180246_b();
            double d3 = (double)blockpos.func_177958_n() - d0;
            double d4 = (double)blockpos.func_177956_o() - d1;
            double d5 = (double)blockpos.func_177952_p() - d2;
            Block block = this.field_72769_h.func_180495_p(blockpos).func_177230_c();
            if(!(block instanceof BlockChest) && !(block instanceof BlockEnderChest) && !(block instanceof BlockSign) && !(block instanceof BlockSkull)) {
               if(d3 * d3 + d4 * d4 + d5 * d5 > 1024.0D) {
                  iterator.remove();
               } else {
                  IBlockState iblockstate = this.field_72769_h.func_180495_p(blockpos);
                  if(iblockstate.func_177230_c().func_149688_o() != Material.field_151579_a) {
                     int i = destroyblockprogress.func_73106_e();
                     TextureAtlasSprite textureatlassprite = this.field_94141_F[i];
                     BlockRendererDispatcher blockrendererdispatcher = this.field_72777_q.func_175602_ab();
                     blockrendererdispatcher.func_175020_a(iblockstate, blockpos, textureatlassprite, this.field_72769_h);
                  }
               }
            }
         }

         p_174981_1_.func_78381_a();
         p_174981_2_.func_178969_c(0.0D, 0.0D, 0.0D);
         this.func_174969_t();
      }

   }

   public void func_72731_b(EntityPlayer p_72731_1_, MovingObjectPosition p_72731_2_, int p_72731_3_, float p_72731_4_) {
      if(p_72731_3_ == 0 && p_72731_2_.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         GlStateManager.func_179131_c(0.0F, 0.0F, 0.0F, 0.4F);
         GL11.glLineWidth(2.0F);
         GlStateManager.func_179090_x();
         GlStateManager.func_179132_a(false);
         float f = 0.002F;
         BlockPos blockpos = p_72731_2_.func_178782_a();
         Block block = this.field_72769_h.func_180495_p(blockpos).func_177230_c();
         if(block.func_149688_o() != Material.field_151579_a && this.field_72769_h.func_175723_af().func_177746_a(blockpos)) {
            block.func_180654_a(this.field_72769_h, blockpos);
            double d0 = p_72731_1_.field_70142_S + (p_72731_1_.field_70165_t - p_72731_1_.field_70142_S) * (double)p_72731_4_;
            double d1 = p_72731_1_.field_70137_T + (p_72731_1_.field_70163_u - p_72731_1_.field_70137_T) * (double)p_72731_4_;
            double d2 = p_72731_1_.field_70136_U + (p_72731_1_.field_70161_v - p_72731_1_.field_70136_U) * (double)p_72731_4_;
            func_181561_a(block.func_180646_a(this.field_72769_h, blockpos).func_72314_b(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).func_72317_d(-d0, -d1, -d2));
         }

         GlStateManager.func_179132_a(true);
         GlStateManager.func_179098_w();
         GlStateManager.func_179084_k();
      }

   }

   public static void func_181561_a(AxisAlignedBB p_181561_0_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      worldrenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(1, DefaultVertexFormats.field_181705_e);
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72339_c).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72336_d, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72338_b, p_181561_0_.field_72334_f).func_181675_d();
      worldrenderer.func_181662_b(p_181561_0_.field_72340_a, p_181561_0_.field_72337_e, p_181561_0_.field_72334_f).func_181675_d();
      tessellator.func_78381_a();
   }

   public static void func_181563_a(AxisAlignedBB p_181563_0_, int p_181563_1_, int p_181563_2_, int p_181563_3_, int p_181563_4_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      worldrenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      tessellator.func_78381_a();
      worldrenderer.func_181668_a(1, DefaultVertexFormats.field_181706_f);
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72339_c).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72336_d, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72338_b, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      worldrenderer.func_181662_b(p_181563_0_.field_72340_a, p_181563_0_.field_72337_e, p_181563_0_.field_72334_f).func_181669_b(p_181563_1_, p_181563_2_, p_181563_3_, p_181563_4_).func_181675_d();
      tessellator.func_78381_a();
   }

   private void func_72725_b(int p_72725_1_, int p_72725_2_, int p_72725_3_, int p_72725_4_, int p_72725_5_, int p_72725_6_) {
      this.field_175008_n.func_178162_a(p_72725_1_, p_72725_2_, p_72725_3_, p_72725_4_, p_72725_5_, p_72725_6_);
   }

   public void func_174960_a(BlockPos p_174960_1_) {
      int i = p_174960_1_.func_177958_n();
      int j = p_174960_1_.func_177956_o();
      int k = p_174960_1_.func_177952_p();
      this.func_72725_b(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1);
   }

   public void func_174959_b(BlockPos p_174959_1_) {
      int i = p_174959_1_.func_177958_n();
      int j = p_174959_1_.func_177956_o();
      int k = p_174959_1_.func_177952_p();
      this.func_72725_b(i - 1, j - 1, k - 1, i + 1, j + 1, k + 1);
   }

   public void func_147585_a(int p_147585_1_, int p_147585_2_, int p_147585_3_, int p_147585_4_, int p_147585_5_, int p_147585_6_) {
      this.func_72725_b(p_147585_1_ - 1, p_147585_2_ - 1, p_147585_3_ - 1, p_147585_4_ + 1, p_147585_5_ + 1, p_147585_6_ + 1);
   }

   public void func_174961_a(String p_174961_1_, BlockPos p_174961_2_) {
      ISound isound = (ISound)this.field_147593_P.get(p_174961_2_);
      if(isound != null) {
         this.field_72777_q.func_147118_V().func_147683_b(isound);
         this.field_147593_P.remove(p_174961_2_);
      }

      if(p_174961_1_ != null) {
         ItemRecord itemrecord = ItemRecord.func_150926_b(p_174961_1_);
         if(itemrecord != null) {
            this.field_72777_q.field_71456_v.func_73833_a(itemrecord.func_150927_i());
         }

         PositionedSoundRecord positionedsoundrecord = PositionedSoundRecord.func_147675_a(new ResourceLocation(p_174961_1_), (float)p_174961_2_.func_177958_n(), (float)p_174961_2_.func_177956_o(), (float)p_174961_2_.func_177952_p());
         this.field_147593_P.put(p_174961_2_, positionedsoundrecord);
         this.field_72777_q.func_147118_V().func_147682_a(positionedsoundrecord);
      }

   }

   public void func_72704_a(String p_72704_1_, double p_72704_2_, double p_72704_4_, double p_72704_6_, float p_72704_8_, float p_72704_9_) {
   }

   public void func_85102_a(EntityPlayer p_85102_1_, String p_85102_2_, double p_85102_3_, double p_85102_5_, double p_85102_7_, float p_85102_9_, float p_85102_10_) {
   }

   public void func_180442_a(int p_180442_1_, boolean p_180442_2_, final double p_180442_3_, final double p_180442_5_, final double p_180442_7_, double p_180442_9_, double p_180442_11_, double p_180442_13_, int... p_180442_15_) {
      try {
         this.func_174974_b(p_180442_1_, p_180442_2_, p_180442_3_, p_180442_5_, p_180442_7_, p_180442_9_, p_180442_11_, p_180442_13_, p_180442_15_);
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception while adding particle");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being added");
         crashreportcategory.func_71507_a("ID", Integer.valueOf(p_180442_1_));
         if(p_180442_15_ != null) {
            crashreportcategory.func_71507_a("Parameters", p_180442_15_);
         }

         crashreportcategory.func_71500_a("Position", new Callable<String>() {
            public String call() throws Exception {
               return CrashReportCategory.func_85074_a(p_180442_3_, p_180442_5_, p_180442_7_);
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   private void func_174972_a(EnumParticleTypes p_174972_1_, double p_174972_2_, double p_174972_4_, double p_174972_6_, double p_174972_8_, double p_174972_10_, double p_174972_12_, int... p_174972_14_) {
      this.func_180442_a(p_174972_1_.func_179348_c(), p_174972_1_.func_179344_e(), p_174972_2_, p_174972_4_, p_174972_6_, p_174972_8_, p_174972_10_, p_174972_12_, p_174972_14_);
   }

   private EntityFX func_174974_b(int p_174974_1_, boolean p_174974_2_, double p_174974_3_, double p_174974_5_, double p_174974_7_, double p_174974_9_, double p_174974_11_, double p_174974_13_, int... p_174974_15_) {
      if(this.field_72777_q != null && this.field_72777_q.func_175606_aa() != null && this.field_72777_q.field_71452_i != null) {
         int i = this.field_72777_q.field_71474_y.field_74362_aa;
         if(i == 1 && this.field_72769_h.field_73012_v.nextInt(3) == 0) {
            i = 2;
         }

         double d0 = this.field_72777_q.func_175606_aa().field_70165_t - p_174974_3_;
         double d1 = this.field_72777_q.func_175606_aa().field_70163_u - p_174974_5_;
         double d2 = this.field_72777_q.func_175606_aa().field_70161_v - p_174974_7_;
         if(p_174974_2_) {
            return this.field_72777_q.field_71452_i.func_178927_a(p_174974_1_, p_174974_3_, p_174974_5_, p_174974_7_, p_174974_9_, p_174974_11_, p_174974_13_, p_174974_15_);
         } else {
            double d3 = 16.0D;
            return d0 * d0 + d1 * d1 + d2 * d2 > 256.0D?null:(i > 1?null:this.field_72777_q.field_71452_i.func_178927_a(p_174974_1_, p_174974_3_, p_174974_5_, p_174974_7_, p_174974_9_, p_174974_11_, p_174974_13_, p_174974_15_));
         }
      } else {
         return null;
      }
   }

   public void func_72703_a(Entity p_72703_1_) {
   }

   public void func_72709_b(Entity p_72709_1_) {
   }

   public void func_72728_f() {
   }

   public void func_180440_a(int p_180440_1_, BlockPos p_180440_2_, int p_180440_3_) {
      switch(p_180440_1_) {
      case 1013:
      case 1018:
         if(this.field_72777_q.func_175606_aa() != null) {
            double d0 = (double)p_180440_2_.func_177958_n() - this.field_72777_q.func_175606_aa().field_70165_t;
            double d1 = (double)p_180440_2_.func_177956_o() - this.field_72777_q.func_175606_aa().field_70163_u;
            double d2 = (double)p_180440_2_.func_177952_p() - this.field_72777_q.func_175606_aa().field_70161_v;
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            double d4 = this.field_72777_q.func_175606_aa().field_70165_t;
            double d5 = this.field_72777_q.func_175606_aa().field_70163_u;
            double d6 = this.field_72777_q.func_175606_aa().field_70161_v;
            if(d3 > 0.0D) {
               d4 += d0 / d3 * 2.0D;
               d5 += d1 / d3 * 2.0D;
               d6 += d2 / d3 * 2.0D;
            }

            if(p_180440_1_ == 1013) {
               this.field_72769_h.func_72980_b(d4, d5, d6, "mob.wither.spawn", 1.0F, 1.0F, false);
            } else {
               this.field_72769_h.func_72980_b(d4, d5, d6, "mob.enderdragon.end", 5.0F, 1.0F, false);
            }
         }
      default:
      }
   }

   public void func_180439_a(EntityPlayer p_180439_1_, int p_180439_2_, BlockPos p_180439_3_, int p_180439_4_) {
      Random random = this.field_72769_h.field_73012_v;
      switch(p_180439_2_) {
      case 1000:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.click", 1.0F, 1.0F, false);
         break;
      case 1001:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.click", 1.0F, 1.2F, false);
         break;
      case 1002:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.bow", 1.0F, 1.2F, false);
         break;
      case 1003:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.door_open", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1004:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.fizz", 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F, false);
         break;
      case 1005:
         if(Item.func_150899_d(p_180439_4_) instanceof ItemRecord) {
            this.field_72769_h.func_175717_a(p_180439_3_, "records." + ((ItemRecord)Item.func_150899_d(p_180439_4_)).field_150929_a);
         } else {
            this.field_72769_h.func_175717_a(p_180439_3_, (String)null);
         }
         break;
      case 1006:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.door_close", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1007:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.ghast.charge", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1008:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.ghast.fireball", 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1009:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.ghast.fireball", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1010:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.wood", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1011:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.metal", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1012:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.woodbreak", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1014:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.wither.shoot", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1015:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.bat.takeoff", 0.05F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1016:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.infect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1017:
         this.field_72769_h.func_175731_a(p_180439_3_, "mob.zombie.unfect", 2.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F, false);
         break;
      case 1020:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.anvil_break", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1021:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.anvil_use", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 1022:
         this.field_72769_h.func_175731_a(p_180439_3_, "random.anvil_land", 0.3F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 2000:
         int l = p_180439_4_ % 3 - 1;
         int i = p_180439_4_ / 3 % 3 - 1;
         double d15 = (double)p_180439_3_.func_177958_n() + (double)l * 0.6D + 0.5D;
         double d17 = (double)p_180439_3_.func_177956_o() + 0.5D;
         double d19 = (double)p_180439_3_.func_177952_p() + (double)i * 0.6D + 0.5D;

         for(int k1 = 0; k1 < 10; ++k1) {
            double d20 = random.nextDouble() * 0.2D + 0.01D;
            double d21 = d15 + (double)l * 0.01D + (random.nextDouble() - 0.5D) * (double)i * 0.5D;
            double d4 = d17 + (random.nextDouble() - 0.5D) * 0.5D;
            double d6 = d19 + (double)i * 0.01D + (random.nextDouble() - 0.5D) * (double)l * 0.5D;
            double d8 = (double)l * d20 + random.nextGaussian() * 0.01D;
            double d10 = -0.03D + random.nextGaussian() * 0.01D;
            double d12 = (double)i * d20 + random.nextGaussian() * 0.01D;
            this.func_174972_a(EnumParticleTypes.SMOKE_NORMAL, d21, d4, d6, d8, d10, d12, new int[0]);
         }

         return;
      case 2001:
         Block block = Block.func_149729_e(p_180439_4_ & 4095);
         if(block.func_149688_o() != Material.field_151579_a) {
            this.field_72777_q.func_147118_V().func_147682_a(new PositionedSoundRecord(new ResourceLocation(block.field_149762_H.func_150495_a()), (block.field_149762_H.func_150497_c() + 1.0F) / 2.0F, block.field_149762_H.func_150494_d() * 0.8F, (float)p_180439_3_.func_177958_n() + 0.5F, (float)p_180439_3_.func_177956_o() + 0.5F, (float)p_180439_3_.func_177952_p() + 0.5F));
         }

         this.field_72777_q.field_71452_i.func_180533_a(p_180439_3_, block.func_176203_a(p_180439_4_ >> 12 & 255));
         break;
      case 2002:
         double d13 = (double)p_180439_3_.func_177958_n();
         double d14 = (double)p_180439_3_.func_177956_o();
         double d16 = (double)p_180439_3_.func_177952_p();

         for(int i1 = 0; i1 < 8; ++i1) {
            this.func_174972_a(EnumParticleTypes.ITEM_CRACK, d13, d14, d16, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, new int[]{Item.func_150891_b(Items.field_151068_bn), p_180439_4_});
         }

         int j1 = Items.field_151068_bn.func_77620_a(p_180439_4_);
         float f = (float)(j1 >> 16 & 255) / 255.0F;
         float f1 = (float)(j1 >> 8 & 255) / 255.0F;
         float f2 = (float)(j1 >> 0 & 255) / 255.0F;
         EnumParticleTypes enumparticletypes = EnumParticleTypes.SPELL;
         if(Items.field_151068_bn.func_77833_h(p_180439_4_)) {
            enumparticletypes = EnumParticleTypes.SPELL_INSTANT;
         }

         for(int l1 = 0; l1 < 100; ++l1) {
            double d22 = random.nextDouble() * 4.0D;
            double d23 = random.nextDouble() * 3.141592653589793D * 2.0D;
            double d24 = Math.cos(d23) * d22;
            double d9 = 0.01D + random.nextDouble() * 0.5D;
            double d11 = Math.sin(d23) * d22;
            EntityFX entityfx = this.func_174974_b(enumparticletypes.func_179348_c(), enumparticletypes.func_179344_e(), d13 + d24 * 0.1D, d14 + 0.3D, d16 + d11 * 0.1D, d24, d9, d11, new int[0]);
            if(entityfx != null) {
               float f3 = 0.75F + random.nextFloat() * 0.25F;
               entityfx.func_70538_b(f * f3, f1 * f3, f2 * f3);
               entityfx.func_70543_e((float)d22);
            }
         }

         this.field_72769_h.func_175731_a(p_180439_3_, "game.potion.smash", 1.0F, this.field_72769_h.field_73012_v.nextFloat() * 0.1F + 0.9F, false);
         break;
      case 2003:
         double d0 = (double)p_180439_3_.func_177958_n() + 0.5D;
         double d1 = (double)p_180439_3_.func_177956_o();
         double d2 = (double)p_180439_3_.func_177952_p() + 0.5D;

         for(int j = 0; j < 8; ++j) {
            this.func_174972_a(EnumParticleTypes.ITEM_CRACK, d0, d1, d2, random.nextGaussian() * 0.15D, random.nextDouble() * 0.2D, random.nextGaussian() * 0.15D, new int[]{Item.func_150891_b(Items.field_151061_bv)});
         }

         for(double d18 = 0.0D; d18 < 6.283185307179586D; d18 += 0.15707963267948966D) {
            this.func_174972_a(EnumParticleTypes.PORTAL, d0 + Math.cos(d18) * 5.0D, d1 - 0.4D, d2 + Math.sin(d18) * 5.0D, Math.cos(d18) * -5.0D, 0.0D, Math.sin(d18) * -5.0D, new int[0]);
            this.func_174972_a(EnumParticleTypes.PORTAL, d0 + Math.cos(d18) * 5.0D, d1 - 0.4D, d2 + Math.sin(d18) * 5.0D, Math.cos(d18) * -7.0D, 0.0D, Math.sin(d18) * -7.0D, new int[0]);
         }

         return;
      case 2004:
         for(int k = 0; k < 20; ++k) {
            double d3 = (double)p_180439_3_.func_177958_n() + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
            double d5 = (double)p_180439_3_.func_177956_o() + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
            double d7 = (double)p_180439_3_.func_177952_p() + 0.5D + ((double)this.field_72769_h.field_73012_v.nextFloat() - 0.5D) * 2.0D;
            this.field_72769_h.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d3, d5, d7, 0.0D, 0.0D, 0.0D, new int[0]);
            this.field_72769_h.func_175688_a(EnumParticleTypes.FLAME, d3, d5, d7, 0.0D, 0.0D, 0.0D, new int[0]);
         }

         return;
      case 2005:
         ItemDye.func_180617_a(this.field_72769_h, p_180439_3_, p_180439_4_);
      }

   }

   public void func_180441_b(int p_180441_1_, BlockPos p_180441_2_, int p_180441_3_) {
      if(p_180441_3_ >= 0 && p_180441_3_ < 10) {
         DestroyBlockProgress destroyblockprogress = (DestroyBlockProgress)this.field_72738_E.get(Integer.valueOf(p_180441_1_));
         if(destroyblockprogress == null || destroyblockprogress.func_180246_b().func_177958_n() != p_180441_2_.func_177958_n() || destroyblockprogress.func_180246_b().func_177956_o() != p_180441_2_.func_177956_o() || destroyblockprogress.func_180246_b().func_177952_p() != p_180441_2_.func_177952_p()) {
            destroyblockprogress = new DestroyBlockProgress(p_180441_1_, p_180441_2_);
            this.field_72738_E.put(Integer.valueOf(p_180441_1_), destroyblockprogress);
         }

         destroyblockprogress.func_73107_a(p_180441_3_);
         destroyblockprogress.func_82744_b(this.field_72773_u);
      } else {
         this.field_72738_E.remove(Integer.valueOf(p_180441_1_));
      }

   }

   public void func_174979_m() {
      this.field_147595_R = true;
   }

   public void func_181023_a(Collection<TileEntity> p_181023_1_, Collection<TileEntity> p_181023_2_) {
      synchronized(this.field_181024_n) {
         this.field_181024_n.removeAll(p_181023_1_);
         this.field_181024_n.addAll(p_181023_2_);
      }
   }

   class ContainerLocalRenderInformation {
      final RenderChunk field_178036_a;
      final EnumFacing field_178034_b;
      final Set<EnumFacing> field_178035_c;
      final int field_178032_d;

      private ContainerLocalRenderInformation(RenderChunk p_i46248_2_, EnumFacing p_i46248_3_, int p_i46248_4_) {
         this.field_178035_c = EnumSet.<EnumFacing>noneOf(EnumFacing.class);
         this.field_178036_a = p_i46248_2_;
         this.field_178034_b = p_i46248_3_;
         this.field_178032_d = p_i46248_4_;
      }
   }
}
