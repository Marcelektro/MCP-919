package net.minecraft.client.renderer;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class EntityRenderer implements IResourceManagerReloadListener {
   private static final Logger field_147710_q = LogManager.getLogger();
   private static final ResourceLocation field_110924_q = new ResourceLocation("textures/environment/rain.png");
   private static final ResourceLocation field_110923_r = new ResourceLocation("textures/environment/snow.png");
   public static boolean field_78517_a;
   public static int field_78515_b;
   private Minecraft field_78531_r;
   private final IResourceManager field_147711_ac;
   private Random field_78537_ab = new Random();
   private float field_78530_s;
   public final ItemRenderer field_78516_c;
   private final MapItemRenderer field_147709_v;
   private int field_78529_t;
   private Entity field_78528_u;
   private MouseFilter field_78527_v = new MouseFilter();
   private MouseFilter field_78526_w = new MouseFilter();
   private float field_78490_B = 4.0F;
   private float field_78491_C = 4.0F;
   private float field_78496_H;
   private float field_78497_I;
   private float field_78498_J;
   private float field_78499_K;
   private float field_78492_L;
   private float field_78507_R;
   private float field_78506_S;
   private float field_82831_U;
   private float field_82832_V;
   private boolean field_78500_U;
   private boolean field_175074_C = true;
   private boolean field_175073_D = true;
   private long field_78508_Y = Minecraft.func_71386_F();
   private long field_78510_Z;
   private final DynamicTexture field_78513_d;
   private final int[] field_78504_Q;
   private final ResourceLocation field_110922_T;
   private boolean field_78536_aa;
   private float field_78514_e;
   private float field_175075_L;
   private int field_78534_ac;
   private float[] field_175076_N = new float[1024];
   private float[] field_175077_O = new float[1024];
   private FloatBuffer field_78521_m = GLAllocation.func_74529_h(16);
   private float field_175080_Q;
   private float field_175082_R;
   private float field_175081_S;
   private float field_78535_ad;
   private float field_78539_ae;
   private int field_175079_V = 0;
   private boolean field_175078_W = false;
   private double field_78503_V = 1.0D;
   private double field_78502_W;
   private double field_78509_X;
   private ShaderGroup field_147707_d;
   private static final ResourceLocation[] field_147712_ad = new ResourceLocation[]{new ResourceLocation("shaders/post/notch.json"), new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json"), new ResourceLocation("shaders/post/creeper.json"), new ResourceLocation("shaders/post/spider.json")};
   public static final int field_147708_e = field_147712_ad.length;
   private int field_147713_ae;
   private boolean field_175083_ad;
   private int field_175084_ae;

   public EntityRenderer(Minecraft p_i45076_1_, IResourceManager p_i45076_2_) {
      this.field_147713_ae = field_147708_e;
      this.field_175083_ad = false;
      this.field_175084_ae = 0;
      this.field_78531_r = p_i45076_1_;
      this.field_147711_ac = p_i45076_2_;
      this.field_78516_c = p_i45076_1_.func_175597_ag();
      this.field_147709_v = new MapItemRenderer(p_i45076_1_.func_110434_K());
      this.field_78513_d = new DynamicTexture(16, 16);
      this.field_110922_T = p_i45076_1_.func_110434_K().func_110578_a("lightMap", this.field_78513_d);
      this.field_78504_Q = this.field_78513_d.func_110565_c();
      this.field_147707_d = null;

      for(int i = 0; i < 32; ++i) {
         for(int j = 0; j < 32; ++j) {
            float f = (float)(j - 16);
            float f1 = (float)(i - 16);
            float f2 = MathHelper.func_76129_c(f * f + f1 * f1);
            this.field_175076_N[i << 5 | j] = -f1 / f2;
            this.field_175077_O[i << 5 | j] = f / f2;
         }
      }

   }

   public boolean func_147702_a() {
      return OpenGlHelper.field_148824_g && this.field_147707_d != null;
   }

   public void func_181022_b() {
      if(this.field_147707_d != null) {
         this.field_147707_d.func_148021_a();
      }

      this.field_147707_d = null;
      this.field_147713_ae = field_147708_e;
   }

   public void func_175071_c() {
      this.field_175083_ad = !this.field_175083_ad;
   }

   public void func_175066_a(Entity p_175066_1_) {
      if(OpenGlHelper.field_148824_g) {
         if(this.field_147707_d != null) {
            this.field_147707_d.func_148021_a();
         }

         this.field_147707_d = null;
         if(p_175066_1_ instanceof EntityCreeper) {
            this.func_175069_a(new ResourceLocation("shaders/post/creeper.json"));
         } else if(p_175066_1_ instanceof EntitySpider) {
            this.func_175069_a(new ResourceLocation("shaders/post/spider.json"));
         } else if(p_175066_1_ instanceof EntityEnderman) {
            this.func_175069_a(new ResourceLocation("shaders/post/invert.json"));
         }

      }
   }

   public void func_147705_c() {
      if(OpenGlHelper.field_148824_g) {
         if(this.field_78531_r.func_175606_aa() instanceof EntityPlayer) {
            if(this.field_147707_d != null) {
               this.field_147707_d.func_148021_a();
            }

            this.field_147713_ae = (this.field_147713_ae + 1) % (field_147712_ad.length + 1);
            if(this.field_147713_ae != field_147708_e) {
               this.func_175069_a(field_147712_ad[this.field_147713_ae]);
            } else {
               this.field_147707_d = null;
            }

         }
      }
   }

   private void func_175069_a(ResourceLocation p_175069_1_) {
      try {
         this.field_147707_d = new ShaderGroup(this.field_78531_r.func_110434_K(), this.field_147711_ac, this.field_78531_r.func_147110_a(), p_175069_1_);
         this.field_147707_d.func_148026_a(this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
         this.field_175083_ad = true;
      } catch (IOException ioexception) {
         field_147710_q.warn((String)("Failed to load shader: " + p_175069_1_), (Throwable)ioexception);
         this.field_147713_ae = field_147708_e;
         this.field_175083_ad = false;
      } catch (JsonSyntaxException jsonsyntaxexception) {
         field_147710_q.warn((String)("Failed to load shader: " + p_175069_1_), (Throwable)jsonsyntaxexception);
         this.field_147713_ae = field_147708_e;
         this.field_175083_ad = false;
      }

   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      if(this.field_147707_d != null) {
         this.field_147707_d.func_148021_a();
      }

      this.field_147707_d = null;
      if(this.field_147713_ae != field_147708_e) {
         this.func_175069_a(field_147712_ad[this.field_147713_ae]);
      } else {
         this.func_175066_a(this.field_78531_r.func_175606_aa());
      }

   }

   public void func_78464_a() {
      if(OpenGlHelper.field_148824_g && ShaderLinkHelper.func_148074_b() == null) {
         ShaderLinkHelper.func_148076_a();
      }

      this.func_78477_e();
      this.func_78470_f();
      this.field_78535_ad = this.field_78539_ae;
      this.field_78491_C = this.field_78490_B;
      if(this.field_78531_r.field_71474_y.field_74326_T) {
         float f = this.field_78531_r.field_71474_y.field_74341_c * 0.6F + 0.2F;
         float f1 = f * f * f * 8.0F;
         this.field_78498_J = this.field_78527_v.func_76333_a(this.field_78496_H, 0.05F * f1);
         this.field_78499_K = this.field_78526_w.func_76333_a(this.field_78497_I, 0.05F * f1);
         this.field_78492_L = 0.0F;
         this.field_78496_H = 0.0F;
         this.field_78497_I = 0.0F;
      } else {
         this.field_78498_J = 0.0F;
         this.field_78499_K = 0.0F;
         this.field_78527_v.func_180179_a();
         this.field_78526_w.func_180179_a();
      }

      if(this.field_78531_r.func_175606_aa() == null) {
         this.field_78531_r.func_175607_a(this.field_78531_r.field_71439_g);
      }

      float f3 = this.field_78531_r.field_71441_e.func_175724_o(new BlockPos(this.field_78531_r.func_175606_aa()));
      float f4 = (float)this.field_78531_r.field_71474_y.field_151451_c / 32.0F;
      float f2 = f3 * (1.0F - f4) + f4;
      this.field_78539_ae += (f2 - this.field_78539_ae) * 0.1F;
      ++this.field_78529_t;
      this.field_78516_c.func_78441_a();
      this.func_78484_h();
      this.field_82832_V = this.field_82831_U;
      if(BossStatus.field_82825_d) {
         this.field_82831_U += 0.05F;
         if(this.field_82831_U > 1.0F) {
            this.field_82831_U = 1.0F;
         }

         BossStatus.field_82825_d = false;
      } else if(this.field_82831_U > 0.0F) {
         this.field_82831_U -= 0.0125F;
      }

   }

   public ShaderGroup func_147706_e() {
      return this.field_147707_d;
   }

   public void func_147704_a(int p_147704_1_, int p_147704_2_) {
      if(OpenGlHelper.field_148824_g) {
         if(this.field_147707_d != null) {
            this.field_147707_d.func_148026_a(p_147704_1_, p_147704_2_);
         }

         this.field_78531_r.field_71438_f.func_72720_a(p_147704_1_, p_147704_2_);
      }
   }

   public void func_78473_a(float p_78473_1_) {
      Entity entity = this.field_78531_r.func_175606_aa();
      if(entity != null) {
         if(this.field_78531_r.field_71441_e != null) {
            this.field_78531_r.field_71424_I.func_76320_a("pick");
            this.field_78531_r.field_147125_j = null;
            double d0 = (double)this.field_78531_r.field_71442_b.func_78757_d();
            this.field_78531_r.field_71476_x = entity.func_174822_a(d0, p_78473_1_);
            double d1 = d0;
            Vec3 vec3 = entity.func_174824_e(p_78473_1_);
            boolean flag = false;
            int i = 3;
            if(this.field_78531_r.field_71442_b.func_78749_i()) {
               d0 = 6.0D;
               d1 = 6.0D;
            } else {
               if(d0 > 3.0D) {
                  flag = true;
               }

               d0 = d0;
            }

            if(this.field_78531_r.field_71476_x != null) {
               d1 = this.field_78531_r.field_71476_x.field_72307_f.func_72438_d(vec3);
            }

            Vec3 vec31 = entity.func_70676_i(p_78473_1_);
            Vec3 vec32 = vec3.func_72441_c(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0);
            this.field_78528_u = null;
            Vec3 vec33 = null;
            float f = 1.0F;
            List<Entity> list = this.field_78531_r.field_71441_e.func_175674_a(entity, entity.func_174813_aQ().func_72321_a(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0).func_72314_b((double)f, (double)f, (double)f), Predicates.and(EntitySelectors.field_180132_d, new Predicate<Entity>() {
               public boolean apply(Entity p_apply_1_) {
                  return p_apply_1_.func_70067_L();
               }
            }));
            double d2 = d1;

            for(int j = 0; j < list.size(); ++j) {
               Entity entity1 = (Entity)list.get(j);
               float f1 = entity1.func_70111_Y();
               AxisAlignedBB axisalignedbb = entity1.func_174813_aQ().func_72314_b((double)f1, (double)f1, (double)f1);
               MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3, vec32);
               if(axisalignedbb.func_72318_a(vec3)) {
                  if(d2 >= 0.0D) {
                     this.field_78528_u = entity1;
                     vec33 = movingobjectposition == null?vec3:movingobjectposition.field_72307_f;
                     d2 = 0.0D;
                  }
               } else if(movingobjectposition != null) {
                  double d3 = vec3.func_72438_d(movingobjectposition.field_72307_f);
                  if(d3 < d2 || d2 == 0.0D) {
                     if(entity1 == entity.field_70154_o) {
                        if(d2 == 0.0D) {
                           this.field_78528_u = entity1;
                           vec33 = movingobjectposition.field_72307_f;
                        }
                     } else {
                        this.field_78528_u = entity1;
                        vec33 = movingobjectposition.field_72307_f;
                        d2 = d3;
                     }
                  }
               }
            }

            if(this.field_78528_u != null && flag && vec3.func_72438_d(vec33) > 3.0D) {
               this.field_78528_u = null;
               this.field_78531_r.field_71476_x = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec33, (EnumFacing)null, new BlockPos(vec33));
            }

            if(this.field_78528_u != null && (d2 < d1 || this.field_78531_r.field_71476_x == null)) {
               this.field_78531_r.field_71476_x = new MovingObjectPosition(this.field_78528_u, vec33);
               if(this.field_78528_u instanceof EntityLivingBase || this.field_78528_u instanceof EntityItemFrame) {
                  this.field_78531_r.field_147125_j = this.field_78528_u;
               }
            }

            this.field_78531_r.field_71424_I.func_76319_b();
         }
      }
   }

   private void func_78477_e() {
      float f = 1.0F;
      if(this.field_78531_r.func_175606_aa() instanceof AbstractClientPlayer) {
         AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer)this.field_78531_r.func_175606_aa();
         f = abstractclientplayer.func_175156_o();
      }

      this.field_78506_S = this.field_78507_R;
      this.field_78507_R += (f - this.field_78507_R) * 0.5F;
      if(this.field_78507_R > 1.5F) {
         this.field_78507_R = 1.5F;
      }

      if(this.field_78507_R < 0.1F) {
         this.field_78507_R = 0.1F;
      }

   }

   private float func_78481_a(float p_78481_1_, boolean p_78481_2_) {
      if(this.field_175078_W) {
         return 90.0F;
      } else {
         Entity entity = this.field_78531_r.func_175606_aa();
         float f = 70.0F;
         if(p_78481_2_) {
            f = this.field_78531_r.field_71474_y.field_74334_X;
            f = f * (this.field_78506_S + (this.field_78507_R - this.field_78506_S) * p_78481_1_);
         }

         if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_110143_aJ() <= 0.0F) {
            float f1 = (float)((EntityLivingBase)entity).field_70725_aQ + p_78481_1_;
            f /= (1.0F - 500.0F / (f1 + 500.0F)) * 2.0F + 1.0F;
         }

         Block block = ActiveRenderInfo.func_180786_a(this.field_78531_r.field_71441_e, entity, p_78481_1_);
         if(block.func_149688_o() == Material.field_151586_h) {
            f = f * 60.0F / 70.0F;
         }

         return f;
      }
   }

   private void func_78482_e(float p_78482_1_) {
      if(this.field_78531_r.func_175606_aa() instanceof EntityLivingBase) {
         EntityLivingBase entitylivingbase = (EntityLivingBase)this.field_78531_r.func_175606_aa();
         float f = (float)entitylivingbase.field_70737_aN - p_78482_1_;
         if(entitylivingbase.func_110143_aJ() <= 0.0F) {
            float f1 = (float)entitylivingbase.field_70725_aQ + p_78482_1_;
            GlStateManager.func_179114_b(40.0F - 8000.0F / (f1 + 200.0F), 0.0F, 0.0F, 1.0F);
         }

         if(f < 0.0F) {
            return;
         }

         f = f / (float)entitylivingbase.field_70738_aO;
         f = MathHelper.func_76126_a(f * f * f * f * 3.1415927F);
         float f2 = entitylivingbase.field_70739_aP;
         GlStateManager.func_179114_b(-f2, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(-f * 14.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179114_b(f2, 0.0F, 1.0F, 0.0F);
      }

   }

   private void func_78475_f(float p_78475_1_) {
      if(this.field_78531_r.func_175606_aa() instanceof EntityPlayer) {
         EntityPlayer entityplayer = (EntityPlayer)this.field_78531_r.func_175606_aa();
         float f = entityplayer.field_70140_Q - entityplayer.field_70141_P;
         float f1 = -(entityplayer.field_70140_Q + f * p_78475_1_);
         float f2 = entityplayer.field_71107_bF + (entityplayer.field_71109_bG - entityplayer.field_71107_bF) * p_78475_1_;
         float f3 = entityplayer.field_70727_aS + (entityplayer.field_70726_aT - entityplayer.field_70727_aS) * p_78475_1_;
         GlStateManager.func_179109_b(MathHelper.func_76126_a(f1 * 3.1415927F) * f2 * 0.5F, -Math.abs(MathHelper.func_76134_b(f1 * 3.1415927F) * f2), 0.0F);
         GlStateManager.func_179114_b(MathHelper.func_76126_a(f1 * 3.1415927F) * f2 * 3.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179114_b(Math.abs(MathHelper.func_76134_b(f1 * 3.1415927F - 0.2F) * f2) * 5.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179114_b(f3, 1.0F, 0.0F, 0.0F);
      }
   }

   private void func_78467_g(float p_78467_1_) {
      Entity entity = this.field_78531_r.func_175606_aa();
      float f = entity.func_70047_e();
      double d0 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * (double)p_78467_1_;
      double d1 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * (double)p_78467_1_ + (double)f;
      double d2 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * (double)p_78467_1_;
      if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70608_bn()) {
         f = (float)((double)f + 1.0D);
         GlStateManager.func_179109_b(0.0F, 0.3F, 0.0F);
         if(!this.field_78531_r.field_71474_y.field_74325_U) {
            BlockPos blockpos = new BlockPos(entity);
            IBlockState iblockstate = this.field_78531_r.field_71441_e.func_180495_p(blockpos);
            Block block = iblockstate.func_177230_c();
            if(block == Blocks.field_150324_C) {
               int j = ((EnumFacing)iblockstate.func_177229_b(BlockBed.field_176387_N)).func_176736_b();
               GlStateManager.func_179114_b((float)(j * 90), 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.func_179114_b(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * p_78467_1_ + 180.0F, 0.0F, -1.0F, 0.0F);
            GlStateManager.func_179114_b(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * p_78467_1_, -1.0F, 0.0F, 0.0F);
         }
      } else if(this.field_78531_r.field_71474_y.field_74320_O > 0) {
         double d3 = (double)(this.field_78491_C + (this.field_78490_B - this.field_78491_C) * p_78467_1_);
         if(this.field_78531_r.field_71474_y.field_74325_U) {
            GlStateManager.func_179109_b(0.0F, 0.0F, (float)(-d3));
         } else {
            float f1 = entity.field_70177_z;
            float f2 = entity.field_70125_A;
            if(this.field_78531_r.field_71474_y.field_74320_O == 2) {
               f2 += 180.0F;
            }

            double d4 = (double)(-MathHelper.func_76126_a(f1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(f2 / 180.0F * 3.1415927F)) * d3;
            double d5 = (double)(MathHelper.func_76134_b(f1 / 180.0F * 3.1415927F) * MathHelper.func_76134_b(f2 / 180.0F * 3.1415927F)) * d3;
            double d6 = (double)(-MathHelper.func_76126_a(f2 / 180.0F * 3.1415927F)) * d3;

            for(int i = 0; i < 8; ++i) {
               float f3 = (float)((i & 1) * 2 - 1);
               float f4 = (float)((i >> 1 & 1) * 2 - 1);
               float f5 = (float)((i >> 2 & 1) * 2 - 1);
               f3 = f3 * 0.1F;
               f4 = f4 * 0.1F;
               f5 = f5 * 0.1F;
               MovingObjectPosition movingobjectposition = this.field_78531_r.field_71441_e.func_72933_a(new Vec3(d0 + (double)f3, d1 + (double)f4, d2 + (double)f5), new Vec3(d0 - d4 + (double)f3 + (double)f5, d1 - d6 + (double)f4, d2 - d5 + (double)f5));
               if(movingobjectposition != null) {
                  double d7 = movingobjectposition.field_72307_f.func_72438_d(new Vec3(d0, d1, d2));
                  if(d7 < d3) {
                     d3 = d7;
                  }
               }
            }

            if(this.field_78531_r.field_71474_y.field_74320_O == 2) {
               GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GlStateManager.func_179114_b(entity.field_70125_A - f2, 1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(entity.field_70177_z - f1, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179109_b(0.0F, 0.0F, (float)(-d3));
            GlStateManager.func_179114_b(f1 - entity.field_70177_z, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179114_b(f2 - entity.field_70125_A, 1.0F, 0.0F, 0.0F);
         }
      } else {
         GlStateManager.func_179109_b(0.0F, 0.0F, -0.1F);
      }

      if(!this.field_78531_r.field_71474_y.field_74325_U) {
         GlStateManager.func_179114_b(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * p_78467_1_, 1.0F, 0.0F, 0.0F);
         if(entity instanceof EntityAnimal) {
            EntityAnimal entityanimal = (EntityAnimal)entity;
            GlStateManager.func_179114_b(entityanimal.field_70758_at + (entityanimal.field_70759_as - entityanimal.field_70758_at) * p_78467_1_ + 180.0F, 0.0F, 1.0F, 0.0F);
         } else {
            GlStateManager.func_179114_b(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * p_78467_1_ + 180.0F, 0.0F, 1.0F, 0.0F);
         }
      }

      GlStateManager.func_179109_b(0.0F, -f, 0.0F);
      d0 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * (double)p_78467_1_;
      d1 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * (double)p_78467_1_ + (double)f;
      d2 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * (double)p_78467_1_;
      this.field_78500_U = this.field_78531_r.field_71438_f.func_72721_a(d0, d1, d2, p_78467_1_);
   }

   private void func_78479_a(float p_78479_1_, int p_78479_2_) {
      this.field_78530_s = (float)(this.field_78531_r.field_71474_y.field_151451_c * 16);
      GlStateManager.func_179128_n(5889);
      GlStateManager.func_179096_D();
      float f = 0.07F;
      if(this.field_78531_r.field_71474_y.field_74337_g) {
         GlStateManager.func_179109_b((float)(-(p_78479_2_ * 2 - 1)) * f, 0.0F, 0.0F);
      }

      if(this.field_78503_V != 1.0D) {
         GlStateManager.func_179109_b((float)this.field_78502_W, (float)(-this.field_78509_X), 0.0F);
         GlStateManager.func_179139_a(this.field_78503_V, this.field_78503_V, 1.0D);
      }

      Project.gluPerspective(this.func_78481_a(p_78479_1_, true), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * MathHelper.field_180189_a);
      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179096_D();
      if(this.field_78531_r.field_71474_y.field_74337_g) {
         GlStateManager.func_179109_b((float)(p_78479_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
      }

      this.func_78482_e(p_78479_1_);
      if(this.field_78531_r.field_71474_y.field_74336_f) {
         this.func_78475_f(p_78479_1_);
      }

      float f1 = this.field_78531_r.field_71439_g.field_71080_cy + (this.field_78531_r.field_71439_g.field_71086_bY - this.field_78531_r.field_71439_g.field_71080_cy) * p_78479_1_;
      if(f1 > 0.0F) {
         int i = 20;
         if(this.field_78531_r.field_71439_g.func_70644_a(Potion.field_76431_k)) {
            i = 7;
         }

         float f2 = 5.0F / (f1 * f1 + 5.0F) - f1 * 0.04F;
         f2 = f2 * f2;
         GlStateManager.func_179114_b(((float)this.field_78529_t + p_78479_1_) * (float)i, 0.0F, 1.0F, 1.0F);
         GlStateManager.func_179152_a(1.0F / f2, 1.0F, 1.0F);
         GlStateManager.func_179114_b(-((float)this.field_78529_t + p_78479_1_) * (float)i, 0.0F, 1.0F, 1.0F);
      }

      this.func_78467_g(p_78479_1_);
      if(this.field_175078_W) {
         switch(this.field_175079_V) {
         case 0:
            GlStateManager.func_179114_b(90.0F, 0.0F, 1.0F, 0.0F);
            break;
         case 1:
            GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
            break;
         case 2:
            GlStateManager.func_179114_b(-90.0F, 0.0F, 1.0F, 0.0F);
            break;
         case 3:
            GlStateManager.func_179114_b(90.0F, 1.0F, 0.0F, 0.0F);
            break;
         case 4:
            GlStateManager.func_179114_b(-90.0F, 1.0F, 0.0F, 0.0F);
         }
      }

   }

   private void func_78476_b(float p_78476_1_, int p_78476_2_) {
      if(!this.field_175078_W) {
         GlStateManager.func_179128_n(5889);
         GlStateManager.func_179096_D();
         float f = 0.07F;
         if(this.field_78531_r.field_71474_y.field_74337_g) {
            GlStateManager.func_179109_b((float)(-(p_78476_2_ * 2 - 1)) * f, 0.0F, 0.0F);
         }

         Project.gluPerspective(this.func_78481_a(p_78476_1_, false), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * 2.0F);
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179096_D();
         if(this.field_78531_r.field_71474_y.field_74337_g) {
            GlStateManager.func_179109_b((float)(p_78476_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
         }

         GlStateManager.func_179094_E();
         this.func_78482_e(p_78476_1_);
         if(this.field_78531_r.field_71474_y.field_74336_f) {
            this.func_78475_f(p_78476_1_);
         }

         boolean flag = this.field_78531_r.func_175606_aa() instanceof EntityLivingBase && ((EntityLivingBase)this.field_78531_r.func_175606_aa()).func_70608_bn();
         if(this.field_78531_r.field_71474_y.field_74320_O == 0 && !flag && !this.field_78531_r.field_71474_y.field_74319_N && !this.field_78531_r.field_71442_b.func_78747_a()) {
            this.func_180436_i();
            this.field_78516_c.func_78440_a(p_78476_1_);
            this.func_175072_h();
         }

         GlStateManager.func_179121_F();
         if(this.field_78531_r.field_71474_y.field_74320_O == 0 && !flag) {
            this.field_78516_c.func_78447_b(p_78476_1_);
            this.func_78482_e(p_78476_1_);
         }

         if(this.field_78531_r.field_71474_y.field_74336_f) {
            this.func_78475_f(p_78476_1_);
         }

      }
   }

   public void func_175072_h() {
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179090_x();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
   }

   public void func_180436_i() {
      GlStateManager.func_179138_g(OpenGlHelper.field_77476_b);
      GlStateManager.func_179128_n(5890);
      GlStateManager.func_179096_D();
      float f = 0.00390625F;
      GlStateManager.func_179152_a(f, f, f);
      GlStateManager.func_179109_b(8.0F, 8.0F, 8.0F);
      GlStateManager.func_179128_n(5888);
      this.field_78531_r.func_110434_K().func_110577_a(this.field_110922_T);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10242, 10496);
      GL11.glTexParameteri(3553, 10243, 10496);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179098_w();
      GlStateManager.func_179138_g(OpenGlHelper.field_77478_a);
   }

   private void func_78470_f() {
      this.field_175075_L = (float)((double)this.field_175075_L + (Math.random() - Math.random()) * Math.random() * Math.random());
      this.field_175075_L = (float)((double)this.field_175075_L * 0.9D);
      this.field_78514_e += (this.field_175075_L - this.field_78514_e) * 1.0F;
      this.field_78536_aa = true;
   }

   private void func_78472_g(float p_78472_1_) {
      if(this.field_78536_aa) {
         this.field_78531_r.field_71424_I.func_76320_a("lightTex");
         World world = this.field_78531_r.field_71441_e;
         if(world != null) {
            float f = world.func_72971_b(1.0F);
            float f1 = f * 0.95F + 0.05F;

            for(int i = 0; i < 256; ++i) {
               float f2 = world.field_73011_w.func_177497_p()[i / 16] * f1;
               float f3 = world.field_73011_w.func_177497_p()[i % 16] * (this.field_78514_e * 0.1F + 1.5F);
               if(world.func_175658_ac() > 0) {
                  f2 = world.field_73011_w.func_177497_p()[i / 16];
               }

               float f4 = f2 * (f * 0.65F + 0.35F);
               float f5 = f2 * (f * 0.65F + 0.35F);
               float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
               float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
               float f8 = f4 + f3;
               float f9 = f5 + f6;
               float f10 = f2 + f7;
               f8 = f8 * 0.96F + 0.03F;
               f9 = f9 * 0.96F + 0.03F;
               f10 = f10 * 0.96F + 0.03F;
               if(this.field_82831_U > 0.0F) {
                  float f11 = this.field_82832_V + (this.field_82831_U - this.field_82832_V) * p_78472_1_;
                  f8 = f8 * (1.0F - f11) + f8 * 0.7F * f11;
                  f9 = f9 * (1.0F - f11) + f9 * 0.6F * f11;
                  f10 = f10 * (1.0F - f11) + f10 * 0.6F * f11;
               }

               if(world.field_73011_w.func_177502_q() == 1) {
                  f8 = 0.22F + f3 * 0.75F;
                  f9 = 0.28F + f6 * 0.75F;
                  f10 = 0.25F + f7 * 0.75F;
               }

               if(this.field_78531_r.field_71439_g.func_70644_a(Potion.field_76439_r)) {
                  float f15 = this.func_180438_a(this.field_78531_r.field_71439_g, p_78472_1_);
                  float f12 = 1.0F / f8;
                  if(f12 > 1.0F / f9) {
                     f12 = 1.0F / f9;
                  }

                  if(f12 > 1.0F / f10) {
                     f12 = 1.0F / f10;
                  }

                  f8 = f8 * (1.0F - f15) + f8 * f12 * f15;
                  f9 = f9 * (1.0F - f15) + f9 * f12 * f15;
                  f10 = f10 * (1.0F - f15) + f10 * f12 * f15;
               }

               if(f8 > 1.0F) {
                  f8 = 1.0F;
               }

               if(f9 > 1.0F) {
                  f9 = 1.0F;
               }

               if(f10 > 1.0F) {
                  f10 = 1.0F;
               }

               float f16 = this.field_78531_r.field_71474_y.field_74333_Y;
               float f17 = 1.0F - f8;
               float f13 = 1.0F - f9;
               float f14 = 1.0F - f10;
               f17 = 1.0F - f17 * f17 * f17 * f17;
               f13 = 1.0F - f13 * f13 * f13 * f13;
               f14 = 1.0F - f14 * f14 * f14 * f14;
               f8 = f8 * (1.0F - f16) + f17 * f16;
               f9 = f9 * (1.0F - f16) + f13 * f16;
               f10 = f10 * (1.0F - f16) + f14 * f16;
               f8 = f8 * 0.96F + 0.03F;
               f9 = f9 * 0.96F + 0.03F;
               f10 = f10 * 0.96F + 0.03F;
               if(f8 > 1.0F) {
                  f8 = 1.0F;
               }

               if(f9 > 1.0F) {
                  f9 = 1.0F;
               }

               if(f10 > 1.0F) {
                  f10 = 1.0F;
               }

               if(f8 < 0.0F) {
                  f8 = 0.0F;
               }

               if(f9 < 0.0F) {
                  f9 = 0.0F;
               }

               if(f10 < 0.0F) {
                  f10 = 0.0F;
               }

               int j = 255;
               int k = (int)(f8 * 255.0F);
               int l = (int)(f9 * 255.0F);
               int i1 = (int)(f10 * 255.0F);
               this.field_78504_Q[i] = j << 24 | k << 16 | l << 8 | i1;
            }

            this.field_78513_d.func_110564_a();
            this.field_78536_aa = false;
            this.field_78531_r.field_71424_I.func_76319_b();
         }
      }
   }

   private float func_180438_a(EntityLivingBase p_180438_1_, float p_180438_2_) {
      int i = p_180438_1_.func_70660_b(Potion.field_76439_r).func_76459_b();
      return i > 200?1.0F:0.7F + MathHelper.func_76126_a(((float)i - p_180438_2_) * 3.1415927F * 0.2F) * 0.3F;
   }

   public void func_181560_a(float p_181560_1_, long p_181560_2_) {
      boolean flag = Display.isActive();
      if(!flag && this.field_78531_r.field_71474_y.field_82881_y && (!this.field_78531_r.field_71474_y.field_85185_A || !Mouse.isButtonDown(1))) {
         if(Minecraft.func_71386_F() - this.field_78508_Y > 500L) {
            this.field_78531_r.func_71385_j();
         }
      } else {
         this.field_78508_Y = Minecraft.func_71386_F();
      }

      this.field_78531_r.field_71424_I.func_76320_a("mouse");
      if(flag && Minecraft.field_142025_a && this.field_78531_r.field_71415_G && !Mouse.isInsideWindow()) {
         Mouse.setGrabbed(false);
         Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
         Mouse.setGrabbed(true);
      }

      if(this.field_78531_r.field_71415_G && flag) {
         this.field_78531_r.field_71417_B.func_74374_c();
         float f = this.field_78531_r.field_71474_y.field_74341_c * 0.6F + 0.2F;
         float f1 = f * f * f * 8.0F;
         float f2 = (float)this.field_78531_r.field_71417_B.field_74377_a * f1;
         float f3 = (float)this.field_78531_r.field_71417_B.field_74375_b * f1;
         int i = 1;
         if(this.field_78531_r.field_71474_y.field_74338_d) {
            i = -1;
         }

         if(this.field_78531_r.field_71474_y.field_74326_T) {
            this.field_78496_H += f2;
            this.field_78497_I += f3;
            float f4 = p_181560_1_ - this.field_78492_L;
            this.field_78492_L = p_181560_1_;
            f2 = this.field_78498_J * f4;
            f3 = this.field_78499_K * f4;
            this.field_78531_r.field_71439_g.func_70082_c(f2, f3 * (float)i);
         } else {
            this.field_78496_H = 0.0F;
            this.field_78497_I = 0.0F;
            this.field_78531_r.field_71439_g.func_70082_c(f2, f3 * (float)i);
         }
      }

      this.field_78531_r.field_71424_I.func_76319_b();
      if(!this.field_78531_r.field_71454_w) {
         field_78517_a = this.field_78531_r.field_71474_y.field_74337_g;
         final ScaledResolution scaledresolution = new ScaledResolution(this.field_78531_r);
         int i1 = scaledresolution.func_78326_a();
         int j1 = scaledresolution.func_78328_b();
         final int k1 = Mouse.getX() * i1 / this.field_78531_r.field_71443_c;
         final int l1 = j1 - Mouse.getY() * j1 / this.field_78531_r.field_71440_d - 1;
         int i2 = this.field_78531_r.field_71474_y.field_74350_i;
         if(this.field_78531_r.field_71441_e != null) {
            this.field_78531_r.field_71424_I.func_76320_a("level");
            int j = Math.min(Minecraft.func_175610_ah(), i2);
            j = Math.max(j, 60);
            long k = System.nanoTime() - p_181560_2_;
            long l = Math.max((long)(1000000000 / j / 4) - k, 0L);
            this.func_78471_a(p_181560_1_, System.nanoTime() + l);
            if(OpenGlHelper.field_148824_g) {
               this.field_78531_r.field_71438_f.func_174975_c();
               if(this.field_147707_d != null && this.field_175083_ad) {
                  GlStateManager.func_179128_n(5890);
                  GlStateManager.func_179094_E();
                  GlStateManager.func_179096_D();
                  this.field_147707_d.func_148018_a(p_181560_1_);
                  GlStateManager.func_179121_F();
               }

               this.field_78531_r.func_147110_a().func_147610_a(true);
            }

            this.field_78510_Z = System.nanoTime();
            this.field_78531_r.field_71424_I.func_76318_c("gui");
            if(!this.field_78531_r.field_71474_y.field_74319_N || this.field_78531_r.field_71462_r != null) {
               GlStateManager.func_179092_a(516, 0.1F);
               this.field_78531_r.field_71456_v.func_175180_a(p_181560_1_);
            }

            this.field_78531_r.field_71424_I.func_76319_b();
         } else {
            GlStateManager.func_179083_b(0, 0, this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
            GlStateManager.func_179128_n(5889);
            GlStateManager.func_179096_D();
            GlStateManager.func_179128_n(5888);
            GlStateManager.func_179096_D();
            this.func_78478_c();
            this.field_78510_Z = System.nanoTime();
         }

         if(this.field_78531_r.field_71462_r != null) {
            GlStateManager.func_179086_m(256);

            try {
               this.field_78531_r.field_71462_r.func_73863_a(k1, l1, p_181560_1_);
            } catch (Throwable throwable) {
               CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering screen");
               CrashReportCategory crashreportcategory = crashreport.func_85058_a("Screen render details");
               crashreportcategory.func_71500_a("Screen name", new Callable<String>() {
                  public String call() throws Exception {
                     return EntityRenderer.this.field_78531_r.field_71462_r.getClass().getCanonicalName();
                  }
               });
               crashreportcategory.func_71500_a("Mouse location", new Callable<String>() {
                  public String call() throws Exception {
                     return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[]{Integer.valueOf(k1), Integer.valueOf(l1), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY())});
                  }
               });
               crashreportcategory.func_71500_a("Screen size", new Callable<String>() {
                  public String call() throws Exception {
                     return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", new Object[]{Integer.valueOf(scaledresolution.func_78326_a()), Integer.valueOf(scaledresolution.func_78328_b()), Integer.valueOf(EntityRenderer.this.field_78531_r.field_71443_c), Integer.valueOf(EntityRenderer.this.field_78531_r.field_71440_d), Integer.valueOf(scaledresolution.func_78325_e())});
                  }
               });
               throw new ReportedException(crashreport);
            }
         }

      }
   }

   public void func_152430_c(float p_152430_1_) {
      this.func_78478_c();
      this.field_78531_r.field_71456_v.func_180478_c(new ScaledResolution(this.field_78531_r));
   }

   private boolean func_175070_n() {
      if(!this.field_175073_D) {
         return false;
      } else {
         Entity entity = this.field_78531_r.func_175606_aa();
         boolean flag = entity instanceof EntityPlayer && !this.field_78531_r.field_71474_y.field_74319_N;
         if(flag && !((EntityPlayer)entity).field_71075_bZ.field_75099_e) {
            ItemStack itemstack = ((EntityPlayer)entity).func_71045_bC();
            if(this.field_78531_r.field_71476_x != null && this.field_78531_r.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
               BlockPos blockpos = this.field_78531_r.field_71476_x.func_178782_a();
               Block block = this.field_78531_r.field_71441_e.func_180495_p(blockpos).func_177230_c();
               if(this.field_78531_r.field_71442_b.func_178889_l() == WorldSettings.GameType.SPECTATOR) {
                  flag = block.func_149716_u() && this.field_78531_r.field_71441_e.func_175625_s(blockpos) instanceof IInventory;
               } else {
                  flag = itemstack != null && (itemstack.func_179544_c(block) || itemstack.func_179547_d(block));
               }
            }
         }

         return flag;
      }
   }

   private void func_175067_i(float p_175067_1_) {
      if(this.field_78531_r.field_71474_y.field_74330_P && !this.field_78531_r.field_71474_y.field_74319_N && !this.field_78531_r.field_71439_g.func_175140_cp() && !this.field_78531_r.field_71474_y.field_178879_v) {
         Entity entity = this.field_78531_r.func_175606_aa();
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         GL11.glLineWidth(1.0F);
         GlStateManager.func_179090_x();
         GlStateManager.func_179132_a(false);
         GlStateManager.func_179094_E();
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179096_D();
         this.func_78467_g(p_175067_1_);
         GlStateManager.func_179109_b(0.0F, entity.func_70047_e(), 0.0F);
         RenderGlobal.func_181563_a(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.005D, 1.0E-4D, 1.0E-4D), 255, 0, 0, 255);
         RenderGlobal.func_181563_a(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0E-4D, 1.0E-4D, 0.005D), 0, 0, 255, 255);
         RenderGlobal.func_181563_a(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0E-4D, 0.0033D, 1.0E-4D), 0, 255, 0, 255);
         GlStateManager.func_179121_F();
         GlStateManager.func_179132_a(true);
         GlStateManager.func_179098_w();
         GlStateManager.func_179084_k();
      }

   }

   public void func_78471_a(float p_78471_1_, long p_78471_2_) {
      this.func_78472_g(p_78471_1_);
      if(this.field_78531_r.func_175606_aa() == null) {
         this.field_78531_r.func_175607_a(this.field_78531_r.field_71439_g);
      }

      this.func_78473_a(p_78471_1_);
      GlStateManager.func_179126_j();
      GlStateManager.func_179141_d();
      GlStateManager.func_179092_a(516, 0.5F);
      this.field_78531_r.field_71424_I.func_76320_a("center");
      if(this.field_78531_r.field_71474_y.field_74337_g) {
         field_78515_b = 0;
         GlStateManager.func_179135_a(false, true, true, false);
         this.func_175068_a(0, p_78471_1_, p_78471_2_);
         field_78515_b = 1;
         GlStateManager.func_179135_a(true, false, false, false);
         this.func_175068_a(1, p_78471_1_, p_78471_2_);
         GlStateManager.func_179135_a(true, true, true, false);
      } else {
         this.func_175068_a(2, p_78471_1_, p_78471_2_);
      }

      this.field_78531_r.field_71424_I.func_76319_b();
   }

   private void func_175068_a(int p_175068_1_, float p_175068_2_, long p_175068_3_) {
      RenderGlobal renderglobal = this.field_78531_r.field_71438_f;
      EffectRenderer effectrenderer = this.field_78531_r.field_71452_i;
      boolean flag = this.func_175070_n();
      GlStateManager.func_179089_o();
      this.field_78531_r.field_71424_I.func_76318_c("clear");
      GlStateManager.func_179083_b(0, 0, this.field_78531_r.field_71443_c, this.field_78531_r.field_71440_d);
      this.func_78466_h(p_175068_2_);
      GlStateManager.func_179086_m(16640);
      this.field_78531_r.field_71424_I.func_76318_c("camera");
      this.func_78479_a(p_175068_2_, p_175068_1_);
      ActiveRenderInfo.func_74583_a(this.field_78531_r.field_71439_g, this.field_78531_r.field_71474_y.field_74320_O == 2);
      this.field_78531_r.field_71424_I.func_76318_c("frustum");
      ClippingHelperImpl.func_78558_a();
      this.field_78531_r.field_71424_I.func_76318_c("culling");
      ICamera icamera = new Frustum();
      Entity entity = this.field_78531_r.func_175606_aa();
      double d0 = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)p_175068_2_;
      double d1 = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)p_175068_2_;
      double d2 = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)p_175068_2_;
      icamera.func_78547_a(d0, d1, d2);
      if(this.field_78531_r.field_71474_y.field_151451_c >= 4) {
         this.func_78468_a(-1, p_175068_2_);
         this.field_78531_r.field_71424_I.func_76318_c("sky");
         GlStateManager.func_179128_n(5889);
         GlStateManager.func_179096_D();
         Project.gluPerspective(this.func_78481_a(p_175068_2_, true), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * 2.0F);
         GlStateManager.func_179128_n(5888);
         renderglobal.func_174976_a(p_175068_2_, p_175068_1_);
         GlStateManager.func_179128_n(5889);
         GlStateManager.func_179096_D();
         Project.gluPerspective(this.func_78481_a(p_175068_2_, true), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * MathHelper.field_180189_a);
         GlStateManager.func_179128_n(5888);
      }

      this.func_78468_a(0, p_175068_2_);
      GlStateManager.func_179103_j(7425);
      if(entity.field_70163_u + (double)entity.func_70047_e() < 128.0D) {
         this.func_180437_a(renderglobal, p_175068_2_, p_175068_1_);
      }

      this.field_78531_r.field_71424_I.func_76318_c("prepareterrain");
      this.func_78468_a(0, p_175068_2_);
      this.field_78531_r.func_110434_K().func_110577_a(TextureMap.field_110575_b);
      RenderHelper.func_74518_a();
      this.field_78531_r.field_71424_I.func_76318_c("terrain_setup");
      renderglobal.func_174970_a(entity, (double)p_175068_2_, icamera, this.field_175084_ae++, this.field_78531_r.field_71439_g.func_175149_v());
      if(p_175068_1_ == 0 || p_175068_1_ == 2) {
         this.field_78531_r.field_71424_I.func_76318_c("updatechunks");
         this.field_78531_r.field_71438_f.func_174967_a(p_175068_3_);
      }

      this.field_78531_r.field_71424_I.func_76318_c("terrain");
      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179094_E();
      GlStateManager.func_179118_c();
      renderglobal.func_174977_a(EnumWorldBlockLayer.SOLID, (double)p_175068_2_, p_175068_1_, entity);
      GlStateManager.func_179141_d();
      renderglobal.func_174977_a(EnumWorldBlockLayer.CUTOUT_MIPPED, (double)p_175068_2_, p_175068_1_, entity);
      this.field_78531_r.func_110434_K().func_110581_b(TextureMap.field_110575_b).func_174936_b(false, false);
      renderglobal.func_174977_a(EnumWorldBlockLayer.CUTOUT, (double)p_175068_2_, p_175068_1_, entity);
      this.field_78531_r.func_110434_K().func_110581_b(TextureMap.field_110575_b).func_174935_a();
      GlStateManager.func_179103_j(7424);
      GlStateManager.func_179092_a(516, 0.1F);
      if(!this.field_175078_W) {
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179121_F();
         GlStateManager.func_179094_E();
         RenderHelper.func_74519_b();
         this.field_78531_r.field_71424_I.func_76318_c("entities");
         renderglobal.func_180446_a(entity, icamera, p_175068_2_);
         RenderHelper.func_74518_a();
         this.func_175072_h();
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179121_F();
         GlStateManager.func_179094_E();
         if(this.field_78531_r.field_71476_x != null && entity.func_70055_a(Material.field_151586_h) && flag) {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            GlStateManager.func_179118_c();
            this.field_78531_r.field_71424_I.func_76318_c("outline");
            renderglobal.func_72731_b(entityplayer, this.field_78531_r.field_71476_x, 0, p_175068_2_);
            GlStateManager.func_179141_d();
         }
      }

      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179121_F();
      if(flag && this.field_78531_r.field_71476_x != null && !entity.func_70055_a(Material.field_151586_h)) {
         EntityPlayer entityplayer1 = (EntityPlayer)entity;
         GlStateManager.func_179118_c();
         this.field_78531_r.field_71424_I.func_76318_c("outline");
         renderglobal.func_72731_b(entityplayer1, this.field_78531_r.field_71476_x, 0, p_175068_2_);
         GlStateManager.func_179141_d();
      }

      this.field_78531_r.field_71424_I.func_76318_c("destroyProgress");
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 1, 1, 0);
      this.field_78531_r.func_110434_K().func_110581_b(TextureMap.field_110575_b).func_174936_b(false, false);
      renderglobal.func_174981_a(Tessellator.func_178181_a(), Tessellator.func_178181_a().func_178180_c(), entity, p_175068_2_);
      this.field_78531_r.func_110434_K().func_110581_b(TextureMap.field_110575_b).func_174935_a();
      GlStateManager.func_179084_k();
      if(!this.field_175078_W) {
         this.func_180436_i();
         this.field_78531_r.field_71424_I.func_76318_c("litParticles");
         effectrenderer.func_78872_b(entity, p_175068_2_);
         RenderHelper.func_74518_a();
         this.func_78468_a(0, p_175068_2_);
         this.field_78531_r.field_71424_I.func_76318_c("particles");
         effectrenderer.func_78874_a(entity, p_175068_2_);
         this.func_175072_h();
      }

      GlStateManager.func_179132_a(false);
      GlStateManager.func_179089_o();
      this.field_78531_r.field_71424_I.func_76318_c("weather");
      this.func_78474_d(p_175068_2_);
      GlStateManager.func_179132_a(true);
      renderglobal.func_180449_a(entity, p_175068_2_);
      GlStateManager.func_179084_k();
      GlStateManager.func_179089_o();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179092_a(516, 0.1F);
      this.func_78468_a(0, p_175068_2_);
      GlStateManager.func_179147_l();
      GlStateManager.func_179132_a(false);
      this.field_78531_r.func_110434_K().func_110577_a(TextureMap.field_110575_b);
      GlStateManager.func_179103_j(7425);
      this.field_78531_r.field_71424_I.func_76318_c("translucent");
      renderglobal.func_174977_a(EnumWorldBlockLayer.TRANSLUCENT, (double)p_175068_2_, p_175068_1_, entity);
      GlStateManager.func_179103_j(7424);
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179089_o();
      GlStateManager.func_179084_k();
      GlStateManager.func_179106_n();
      if(entity.field_70163_u + (double)entity.func_70047_e() >= 128.0D) {
         this.field_78531_r.field_71424_I.func_76318_c("aboveClouds");
         this.func_180437_a(renderglobal, p_175068_2_, p_175068_1_);
      }

      this.field_78531_r.field_71424_I.func_76318_c("hand");
      if(this.field_175074_C) {
         GlStateManager.func_179086_m(256);
         this.func_78476_b(p_175068_2_, p_175068_1_);
         this.func_175067_i(p_175068_2_);
      }

   }

   private void func_180437_a(RenderGlobal p_180437_1_, float p_180437_2_, int p_180437_3_) {
      if(this.field_78531_r.field_71474_y.func_181147_e() != 0) {
         this.field_78531_r.field_71424_I.func_76318_c("clouds");
         GlStateManager.func_179128_n(5889);
         GlStateManager.func_179096_D();
         Project.gluPerspective(this.func_78481_a(p_180437_2_, true), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * 4.0F);
         GlStateManager.func_179128_n(5888);
         GlStateManager.func_179094_E();
         this.func_78468_a(0, p_180437_2_);
         p_180437_1_.func_180447_b(p_180437_2_, p_180437_3_);
         GlStateManager.func_179106_n();
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5889);
         GlStateManager.func_179096_D();
         Project.gluPerspective(this.func_78481_a(p_180437_2_, true), (float)this.field_78531_r.field_71443_c / (float)this.field_78531_r.field_71440_d, 0.05F, this.field_78530_s * MathHelper.field_180189_a);
         GlStateManager.func_179128_n(5888);
      }

   }

   private void func_78484_h() {
      float f = this.field_78531_r.field_71441_e.func_72867_j(1.0F);
      if(!this.field_78531_r.field_71474_y.field_74347_j) {
         f /= 2.0F;
      }

      if(f != 0.0F) {
         this.field_78537_ab.setSeed((long)this.field_78529_t * 312987231L);
         Entity entity = this.field_78531_r.func_175606_aa();
         World world = this.field_78531_r.field_71441_e;
         BlockPos blockpos = new BlockPos(entity);
         int i = 10;
         double d0 = 0.0D;
         double d1 = 0.0D;
         double d2 = 0.0D;
         int j = 0;
         int k = (int)(100.0F * f * f);
         if(this.field_78531_r.field_71474_y.field_74362_aa == 1) {
            k >>= 1;
         } else if(this.field_78531_r.field_71474_y.field_74362_aa == 2) {
            k = 0;
         }

         for(int l = 0; l < k; ++l) {
            BlockPos blockpos1 = world.func_175725_q(blockpos.func_177982_a(this.field_78537_ab.nextInt(i) - this.field_78537_ab.nextInt(i), 0, this.field_78537_ab.nextInt(i) - this.field_78537_ab.nextInt(i)));
            BiomeGenBase biomegenbase = world.func_180494_b(blockpos1);
            BlockPos blockpos2 = blockpos1.func_177977_b();
            Block block = world.func_180495_p(blockpos2).func_177230_c();
            if(blockpos1.func_177956_o() <= blockpos.func_177956_o() + i && blockpos1.func_177956_o() >= blockpos.func_177956_o() - i && biomegenbase.func_76738_d() && biomegenbase.func_180626_a(blockpos1) >= 0.15F) {
               double d3 = this.field_78537_ab.nextDouble();
               double d4 = this.field_78537_ab.nextDouble();
               if(block.func_149688_o() == Material.field_151587_i) {
                  this.field_78531_r.field_71441_e.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, (double)blockpos1.func_177958_n() + d3, (double)((float)blockpos1.func_177956_o() + 0.1F) - block.func_149665_z(), (double)blockpos1.func_177952_p() + d4, 0.0D, 0.0D, 0.0D, new int[0]);
               } else if(block.func_149688_o() != Material.field_151579_a) {
                  block.func_180654_a(world, blockpos2);
                  ++j;
                  if(this.field_78537_ab.nextInt(j) == 0) {
                     d0 = (double)blockpos2.func_177958_n() + d3;
                     d1 = (double)((float)blockpos2.func_177956_o() + 0.1F) + block.func_149669_A() - 1.0D;
                     d2 = (double)blockpos2.func_177952_p() + d4;
                  }

                  this.field_78531_r.field_71441_e.func_175688_a(EnumParticleTypes.WATER_DROP, (double)blockpos2.func_177958_n() + d3, (double)((float)blockpos2.func_177956_o() + 0.1F) + block.func_149669_A(), (double)blockpos2.func_177952_p() + d4, 0.0D, 0.0D, 0.0D, new int[0]);
               }
            }
         }

         if(j > 0 && this.field_78537_ab.nextInt(3) < this.field_78534_ac++) {
            this.field_78534_ac = 0;
            if(d1 > (double)(blockpos.func_177956_o() + 1) && world.func_175725_q(blockpos).func_177956_o() > MathHelper.func_76141_d((float)blockpos.func_177956_o())) {
               this.field_78531_r.field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.1F, 0.5F, false);
            } else {
               this.field_78531_r.field_71441_e.func_72980_b(d0, d1, d2, "ambient.weather.rain", 0.2F, 1.0F, false);
            }
         }

      }
   }

   protected void func_78474_d(float p_78474_1_) {
      float f = this.field_78531_r.field_71441_e.func_72867_j(p_78474_1_);
      if(f > 0.0F) {
         this.func_180436_i();
         Entity entity = this.field_78531_r.func_175606_aa();
         World world = this.field_78531_r.field_71441_e;
         int i = MathHelper.func_76128_c(entity.field_70165_t);
         int j = MathHelper.func_76128_c(entity.field_70163_u);
         int k = MathHelper.func_76128_c(entity.field_70161_v);
         Tessellator tessellator = Tessellator.func_178181_a();
         WorldRenderer worldrenderer = tessellator.func_178180_c();
         GlStateManager.func_179129_p();
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.func_179147_l();
         GlStateManager.func_179120_a(770, 771, 1, 0);
         GlStateManager.func_179092_a(516, 0.1F);
         double d0 = entity.field_70142_S + (entity.field_70165_t - entity.field_70142_S) * (double)p_78474_1_;
         double d1 = entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)p_78474_1_;
         double d2 = entity.field_70136_U + (entity.field_70161_v - entity.field_70136_U) * (double)p_78474_1_;
         int l = MathHelper.func_76128_c(d1);
         int i1 = 5;
         if(this.field_78531_r.field_71474_y.field_74347_j) {
            i1 = 10;
         }

         int j1 = -1;
         float f1 = (float)this.field_78529_t + p_78474_1_;
         worldrenderer.func_178969_c(-d0, -d1, -d2);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

         for(int k1 = k - i1; k1 <= k + i1; ++k1) {
            for(int l1 = i - i1; l1 <= i + i1; ++l1) {
               int i2 = (k1 - k + 16) * 32 + l1 - i + 16;
               double d3 = (double)this.field_175076_N[i2] * 0.5D;
               double d4 = (double)this.field_175077_O[i2] * 0.5D;
               blockpos$mutableblockpos.func_181079_c(l1, 0, k1);
               BiomeGenBase biomegenbase = world.func_180494_b(blockpos$mutableblockpos);
               if(biomegenbase.func_76738_d() || biomegenbase.func_76746_c()) {
                  int j2 = world.func_175725_q(blockpos$mutableblockpos).func_177956_o();
                  int k2 = j - i1;
                  int l2 = j + i1;
                  if(k2 < j2) {
                     k2 = j2;
                  }

                  if(l2 < j2) {
                     l2 = j2;
                  }

                  int i3 = j2;
                  if(j2 < l) {
                     i3 = l;
                  }

                  if(k2 != l2) {
                     this.field_78537_ab.setSeed((long)(l1 * l1 * 3121 + l1 * 45238971 ^ k1 * k1 * 418711 + k1 * 13761));
                     blockpos$mutableblockpos.func_181079_c(l1, k2, k1);
                     float f2 = biomegenbase.func_180626_a(blockpos$mutableblockpos);
                     if(world.func_72959_q().func_76939_a(f2, j2) >= 0.15F) {
                        if(j1 != 0) {
                           if(j1 >= 0) {
                              tessellator.func_78381_a();
                           }

                           j1 = 0;
                           this.field_78531_r.func_110434_K().func_110577_a(field_110924_q);
                           worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181704_d);
                        }

                        double d5 = ((double)(this.field_78529_t + l1 * l1 * 3121 + l1 * 45238971 + k1 * k1 * 418711 + k1 * 13761 & 31) + (double)p_78474_1_) / 32.0D * (3.0D + this.field_78537_ab.nextDouble());
                        double d6 = (double)((float)l1 + 0.5F) - entity.field_70165_t;
                        double d7 = (double)((float)k1 + 0.5F) - entity.field_70161_v;
                        float f3 = MathHelper.func_76133_a(d6 * d6 + d7 * d7) / (float)i1;
                        float f4 = ((1.0F - f3 * f3) * 0.5F + 0.5F) * f;
                        blockpos$mutableblockpos.func_181079_c(l1, i3, k1);
                        int j3 = world.func_175626_b(blockpos$mutableblockpos, 0);
                        int k3 = j3 >> 16 & '\uffff';
                        int l3 = j3 & '\uffff';
                        worldrenderer.func_181662_b((double)l1 - d3 + 0.5D, (double)k2, (double)k1 - d4 + 0.5D).func_181673_a(0.0D, (double)k2 * 0.25D + d5).func_181666_a(1.0F, 1.0F, 1.0F, f4).func_181671_a(k3, l3).func_181675_d();
                        worldrenderer.func_181662_b((double)l1 + d3 + 0.5D, (double)k2, (double)k1 + d4 + 0.5D).func_181673_a(1.0D, (double)k2 * 0.25D + d5).func_181666_a(1.0F, 1.0F, 1.0F, f4).func_181671_a(k3, l3).func_181675_d();
                        worldrenderer.func_181662_b((double)l1 + d3 + 0.5D, (double)l2, (double)k1 + d4 + 0.5D).func_181673_a(1.0D, (double)l2 * 0.25D + d5).func_181666_a(1.0F, 1.0F, 1.0F, f4).func_181671_a(k3, l3).func_181675_d();
                        worldrenderer.func_181662_b((double)l1 - d3 + 0.5D, (double)l2, (double)k1 - d4 + 0.5D).func_181673_a(0.0D, (double)l2 * 0.25D + d5).func_181666_a(1.0F, 1.0F, 1.0F, f4).func_181671_a(k3, l3).func_181675_d();
                     } else {
                        if(j1 != 1) {
                           if(j1 >= 0) {
                              tessellator.func_78381_a();
                           }

                           j1 = 1;
                           this.field_78531_r.func_110434_K().func_110577_a(field_110923_r);
                           worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181704_d);
                        }

                        double d8 = (double)(((float)(this.field_78529_t & 511) + p_78474_1_) / 512.0F);
                        double d9 = this.field_78537_ab.nextDouble() + (double)f1 * 0.01D * (double)((float)this.field_78537_ab.nextGaussian());
                        double d10 = this.field_78537_ab.nextDouble() + (double)(f1 * (float)this.field_78537_ab.nextGaussian()) * 0.001D;
                        double d11 = (double)((float)l1 + 0.5F) - entity.field_70165_t;
                        double d12 = (double)((float)k1 + 0.5F) - entity.field_70161_v;
                        float f6 = MathHelper.func_76133_a(d11 * d11 + d12 * d12) / (float)i1;
                        float f5 = ((1.0F - f6 * f6) * 0.3F + 0.5F) * f;
                        blockpos$mutableblockpos.func_181079_c(l1, i3, k1);
                        int i4 = (world.func_175626_b(blockpos$mutableblockpos, 0) * 3 + 15728880) / 4;
                        int j4 = i4 >> 16 & '\uffff';
                        int k4 = i4 & '\uffff';
                        worldrenderer.func_181662_b((double)l1 - d3 + 0.5D, (double)k2, (double)k1 - d4 + 0.5D).func_181673_a(0.0D + d9, (double)k2 * 0.25D + d8 + d10).func_181666_a(1.0F, 1.0F, 1.0F, f5).func_181671_a(j4, k4).func_181675_d();
                        worldrenderer.func_181662_b((double)l1 + d3 + 0.5D, (double)k2, (double)k1 + d4 + 0.5D).func_181673_a(1.0D + d9, (double)k2 * 0.25D + d8 + d10).func_181666_a(1.0F, 1.0F, 1.0F, f5).func_181671_a(j4, k4).func_181675_d();
                        worldrenderer.func_181662_b((double)l1 + d3 + 0.5D, (double)l2, (double)k1 + d4 + 0.5D).func_181673_a(1.0D + d9, (double)l2 * 0.25D + d8 + d10).func_181666_a(1.0F, 1.0F, 1.0F, f5).func_181671_a(j4, k4).func_181675_d();
                        worldrenderer.func_181662_b((double)l1 - d3 + 0.5D, (double)l2, (double)k1 - d4 + 0.5D).func_181673_a(0.0D + d9, (double)l2 * 0.25D + d8 + d10).func_181666_a(1.0F, 1.0F, 1.0F, f5).func_181671_a(j4, k4).func_181675_d();
                     }
                  }
               }
            }
         }

         if(j1 >= 0) {
            tessellator.func_78381_a();
         }

         worldrenderer.func_178969_c(0.0D, 0.0D, 0.0D);
         GlStateManager.func_179089_o();
         GlStateManager.func_179084_k();
         GlStateManager.func_179092_a(516, 0.1F);
         this.func_175072_h();
      }
   }

   public void func_78478_c() {
      ScaledResolution scaledresolution = new ScaledResolution(this.field_78531_r);
      GlStateManager.func_179086_m(256);
      GlStateManager.func_179128_n(5889);
      GlStateManager.func_179096_D();
      GlStateManager.func_179130_a(0.0D, scaledresolution.func_78327_c(), scaledresolution.func_78324_d(), 0.0D, 1000.0D, 3000.0D);
      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179096_D();
      GlStateManager.func_179109_b(0.0F, 0.0F, -2000.0F);
   }

   private void func_78466_h(float p_78466_1_) {
      World world = this.field_78531_r.field_71441_e;
      Entity entity = this.field_78531_r.func_175606_aa();
      float f = 0.25F + 0.75F * (float)this.field_78531_r.field_71474_y.field_151451_c / 32.0F;
      f = 1.0F - (float)Math.pow((double)f, 0.25D);
      Vec3 vec3 = world.func_72833_a(this.field_78531_r.func_175606_aa(), p_78466_1_);
      float f1 = (float)vec3.field_72450_a;
      float f2 = (float)vec3.field_72448_b;
      float f3 = (float)vec3.field_72449_c;
      Vec3 vec31 = world.func_72948_g(p_78466_1_);
      this.field_175080_Q = (float)vec31.field_72450_a;
      this.field_175082_R = (float)vec31.field_72448_b;
      this.field_175081_S = (float)vec31.field_72449_c;
      if(this.field_78531_r.field_71474_y.field_151451_c >= 4) {
         double d0 = -1.0D;
         Vec3 vec32 = MathHelper.func_76126_a(world.func_72929_e(p_78466_1_)) > 0.0F?new Vec3(d0, 0.0D, 0.0D):new Vec3(1.0D, 0.0D, 0.0D);
         float f5 = (float)entity.func_70676_i(p_78466_1_).func_72430_b(vec32);
         if(f5 < 0.0F) {
            f5 = 0.0F;
         }

         if(f5 > 0.0F) {
            float[] afloat = world.field_73011_w.func_76560_a(world.func_72826_c(p_78466_1_), p_78466_1_);
            if(afloat != null) {
               f5 = f5 * afloat[3];
               this.field_175080_Q = this.field_175080_Q * (1.0F - f5) + afloat[0] * f5;
               this.field_175082_R = this.field_175082_R * (1.0F - f5) + afloat[1] * f5;
               this.field_175081_S = this.field_175081_S * (1.0F - f5) + afloat[2] * f5;
            }
         }
      }

      this.field_175080_Q += (f1 - this.field_175080_Q) * f;
      this.field_175082_R += (f2 - this.field_175082_R) * f;
      this.field_175081_S += (f3 - this.field_175081_S) * f;
      float f8 = world.func_72867_j(p_78466_1_);
      if(f8 > 0.0F) {
         float f4 = 1.0F - f8 * 0.5F;
         float f10 = 1.0F - f8 * 0.4F;
         this.field_175080_Q *= f4;
         this.field_175082_R *= f4;
         this.field_175081_S *= f10;
      }

      float f9 = world.func_72819_i(p_78466_1_);
      if(f9 > 0.0F) {
         float f11 = 1.0F - f9 * 0.5F;
         this.field_175080_Q *= f11;
         this.field_175082_R *= f11;
         this.field_175081_S *= f11;
      }

      Block block = ActiveRenderInfo.func_180786_a(this.field_78531_r.field_71441_e, entity, p_78466_1_);
      if(this.field_78500_U) {
         Vec3 vec33 = world.func_72824_f(p_78466_1_);
         this.field_175080_Q = (float)vec33.field_72450_a;
         this.field_175082_R = (float)vec33.field_72448_b;
         this.field_175081_S = (float)vec33.field_72449_c;
      } else if(block.func_149688_o() == Material.field_151586_h) {
         float f12 = (float)EnchantmentHelper.func_180319_a(entity) * 0.2F;
         if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70644_a(Potion.field_76427_o)) {
            f12 = f12 * 0.3F + 0.6F;
         }

         this.field_175080_Q = 0.02F + f12;
         this.field_175082_R = 0.02F + f12;
         this.field_175081_S = 0.2F + f12;
      } else if(block.func_149688_o() == Material.field_151587_i) {
         this.field_175080_Q = 0.6F;
         this.field_175082_R = 0.1F;
         this.field_175081_S = 0.0F;
      }

      float f13 = this.field_78535_ad + (this.field_78539_ae - this.field_78535_ad) * p_78466_1_;
      this.field_175080_Q *= f13;
      this.field_175082_R *= f13;
      this.field_175081_S *= f13;
      double d1 = (entity.field_70137_T + (entity.field_70163_u - entity.field_70137_T) * (double)p_78466_1_) * world.field_73011_w.func_76565_k();
      if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70644_a(Potion.field_76440_q)) {
         int i = ((EntityLivingBase)entity).func_70660_b(Potion.field_76440_q).func_76459_b();
         if(i < 20) {
            d1 *= (double)(1.0F - (float)i / 20.0F);
         } else {
            d1 = 0.0D;
         }
      }

      if(d1 < 1.0D) {
         if(d1 < 0.0D) {
            d1 = 0.0D;
         }

         d1 = d1 * d1;
         this.field_175080_Q = (float)((double)this.field_175080_Q * d1);
         this.field_175082_R = (float)((double)this.field_175082_R * d1);
         this.field_175081_S = (float)((double)this.field_175081_S * d1);
      }

      if(this.field_82831_U > 0.0F) {
         float f14 = this.field_82832_V + (this.field_82831_U - this.field_82832_V) * p_78466_1_;
         this.field_175080_Q = this.field_175080_Q * (1.0F - f14) + this.field_175080_Q * 0.7F * f14;
         this.field_175082_R = this.field_175082_R * (1.0F - f14) + this.field_175082_R * 0.6F * f14;
         this.field_175081_S = this.field_175081_S * (1.0F - f14) + this.field_175081_S * 0.6F * f14;
      }

      if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70644_a(Potion.field_76439_r)) {
         float f15 = this.func_180438_a((EntityLivingBase)entity, p_78466_1_);
         float f6 = 1.0F / this.field_175080_Q;
         if(f6 > 1.0F / this.field_175082_R) {
            f6 = 1.0F / this.field_175082_R;
         }

         if(f6 > 1.0F / this.field_175081_S) {
            f6 = 1.0F / this.field_175081_S;
         }

         this.field_175080_Q = this.field_175080_Q * (1.0F - f15) + this.field_175080_Q * f6 * f15;
         this.field_175082_R = this.field_175082_R * (1.0F - f15) + this.field_175082_R * f6 * f15;
         this.field_175081_S = this.field_175081_S * (1.0F - f15) + this.field_175081_S * f6 * f15;
      }

      if(this.field_78531_r.field_71474_y.field_74337_g) {
         float f16 = (this.field_175080_Q * 30.0F + this.field_175082_R * 59.0F + this.field_175081_S * 11.0F) / 100.0F;
         float f17 = (this.field_175080_Q * 30.0F + this.field_175082_R * 70.0F) / 100.0F;
         float f7 = (this.field_175080_Q * 30.0F + this.field_175081_S * 70.0F) / 100.0F;
         this.field_175080_Q = f16;
         this.field_175082_R = f17;
         this.field_175081_S = f7;
      }

      GlStateManager.func_179082_a(this.field_175080_Q, this.field_175082_R, this.field_175081_S, 0.0F);
   }

   private void func_78468_a(int p_78468_1_, float p_78468_2_) {
      Entity entity = this.field_78531_r.func_175606_aa();
      boolean flag = false;
      if(entity instanceof EntityPlayer) {
         flag = ((EntityPlayer)entity).field_71075_bZ.field_75098_d;
      }

      GL11.glFog(2918, (FloatBuffer)this.func_78469_a(this.field_175080_Q, this.field_175082_R, this.field_175081_S, 1.0F));
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      Block block = ActiveRenderInfo.func_180786_a(this.field_78531_r.field_71441_e, entity, p_78468_2_);
      if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70644_a(Potion.field_76440_q)) {
         float f1 = 5.0F;
         int i = ((EntityLivingBase)entity).func_70660_b(Potion.field_76440_q).func_76459_b();
         if(i < 20) {
            f1 = 5.0F + (this.field_78530_s - 5.0F) * (1.0F - (float)i / 20.0F);
         }

         GlStateManager.func_179093_d(9729);
         if(p_78468_1_ == -1) {
            GlStateManager.func_179102_b(0.0F);
            GlStateManager.func_179153_c(f1 * 0.8F);
         } else {
            GlStateManager.func_179102_b(f1 * 0.25F);
            GlStateManager.func_179153_c(f1);
         }

         if(GLContext.getCapabilities().GL_NV_fog_distance) {
            GL11.glFogi('\u855a', '\u855b');
         }
      } else if(this.field_78500_U) {
         GlStateManager.func_179093_d(2048);
         GlStateManager.func_179095_a(0.1F);
      } else if(block.func_149688_o() == Material.field_151586_h) {
         GlStateManager.func_179093_d(2048);
         if(entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70644_a(Potion.field_76427_o)) {
            GlStateManager.func_179095_a(0.01F);
         } else {
            GlStateManager.func_179095_a(0.1F - (float)EnchantmentHelper.func_180319_a(entity) * 0.03F);
         }
      } else if(block.func_149688_o() == Material.field_151587_i) {
         GlStateManager.func_179093_d(2048);
         GlStateManager.func_179095_a(2.0F);
      } else {
         float f = this.field_78530_s;
         GlStateManager.func_179093_d(9729);
         if(p_78468_1_ == -1) {
            GlStateManager.func_179102_b(0.0F);
            GlStateManager.func_179153_c(f);
         } else {
            GlStateManager.func_179102_b(f * 0.75F);
            GlStateManager.func_179153_c(f);
         }

         if(GLContext.getCapabilities().GL_NV_fog_distance) {
            GL11.glFogi('\u855a', '\u855b');
         }

         if(this.field_78531_r.field_71441_e.field_73011_w.func_76568_b((int)entity.field_70165_t, (int)entity.field_70161_v)) {
            GlStateManager.func_179102_b(f * 0.05F);
            GlStateManager.func_179153_c(Math.min(f, 192.0F) * 0.5F);
         }
      }

      GlStateManager.func_179142_g();
      GlStateManager.func_179127_m();
      GlStateManager.func_179104_a(1028, 4608);
   }

   private FloatBuffer func_78469_a(float p_78469_1_, float p_78469_2_, float p_78469_3_, float p_78469_4_) {
      this.field_78521_m.clear();
      this.field_78521_m.put(p_78469_1_).put(p_78469_2_).put(p_78469_3_).put(p_78469_4_);
      this.field_78521_m.flip();
      return this.field_78521_m;
   }

   public MapItemRenderer func_147701_i() {
      return this.field_147709_v;
   }
}
