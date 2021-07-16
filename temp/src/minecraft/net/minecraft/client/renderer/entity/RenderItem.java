package net.minecraft.client.renderer.entity;

import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.BlockRedSandstone;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockWall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3i;

public class RenderItem implements IResourceManagerReloadListener {
   private static final ResourceLocation field_110798_h = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   private boolean field_175058_l = true;
   public float field_77023_b;
   private final ItemModelMesher field_175059_m;
   private final TextureManager field_175057_n;

   public RenderItem(TextureManager p_i46165_1_, ModelManager p_i46165_2_) {
      this.field_175057_n = p_i46165_1_;
      this.field_175059_m = new ItemModelMesher(p_i46165_2_);
      this.func_175041_b();
   }

   public void func_175039_a(boolean p_175039_1_) {
      this.field_175058_l = p_175039_1_;
   }

   public ItemModelMesher func_175037_a() {
      return this.field_175059_m;
   }

   protected void func_175048_a(Item p_175048_1_, int p_175048_2_, String p_175048_3_) {
      this.field_175059_m.func_178086_a(p_175048_1_, p_175048_2_, new ModelResourceLocation(p_175048_3_, "inventory"));
   }

   protected void func_175029_a(Block p_175029_1_, int p_175029_2_, String p_175029_3_) {
      this.func_175048_a(Item.func_150898_a(p_175029_1_), p_175029_2_, p_175029_3_);
   }

   private void func_175031_a(Block p_175031_1_, String p_175031_2_) {
      this.func_175029_a(p_175031_1_, 0, p_175031_2_);
   }

   private void func_175047_a(Item p_175047_1_, String p_175047_2_) {
      this.func_175048_a(p_175047_1_, 0, p_175047_2_);
   }

   private void func_175036_a(IBakedModel p_175036_1_, ItemStack p_175036_2_) {
      this.func_175045_a(p_175036_1_, -1, p_175036_2_);
   }

   private void func_175035_a(IBakedModel p_175035_1_, int p_175035_2_) {
      this.func_175045_a(p_175035_1_, p_175035_2_, (ItemStack)null);
   }

   private void func_175045_a(IBakedModel p_175045_1_, int p_175045_2_, ItemStack p_175045_3_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      worldrenderer.func_181668_a(7, DefaultVertexFormats.field_176599_b);

      for(EnumFacing enumfacing : EnumFacing.values()) {
         this.func_175032_a(worldrenderer, p_175045_1_.func_177551_a(enumfacing), p_175045_2_, p_175045_3_);
      }

      this.func_175032_a(worldrenderer, p_175045_1_.func_177550_a(), p_175045_2_, p_175045_3_);
      tessellator.func_78381_a();
   }

   public void func_180454_a(ItemStack p_180454_1_, IBakedModel p_180454_2_) {
      if(p_180454_1_ != null) {
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
         if(p_180454_2_.func_177553_d()) {
            GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179109_b(-0.5F, -0.5F, -0.5F);
            GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179091_B();
            TileEntityItemStackRenderer.field_147719_a.func_179022_a(p_180454_1_);
         } else {
            GlStateManager.func_179109_b(-0.5F, -0.5F, -0.5F);
            this.func_175036_a(p_180454_2_, p_180454_1_);
            if(p_180454_1_.func_77962_s()) {
               this.func_180451_a(p_180454_2_);
            }
         }

         GlStateManager.func_179121_F();
      }
   }

   private void func_180451_a(IBakedModel p_180451_1_) {
      GlStateManager.func_179132_a(false);
      GlStateManager.func_179143_c(514);
      GlStateManager.func_179140_f();
      GlStateManager.func_179112_b(768, 1);
      this.field_175057_n.func_110577_a(field_110798_h);
      GlStateManager.func_179128_n(5890);
      GlStateManager.func_179094_E();
      GlStateManager.func_179152_a(8.0F, 8.0F, 8.0F);
      float f = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F / 8.0F;
      GlStateManager.func_179109_b(f, 0.0F, 0.0F);
      GlStateManager.func_179114_b(-50.0F, 0.0F, 0.0F, 1.0F);
      this.func_175035_a(p_180451_1_, -8372020);
      GlStateManager.func_179121_F();
      GlStateManager.func_179094_E();
      GlStateManager.func_179152_a(8.0F, 8.0F, 8.0F);
      float f1 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0F / 8.0F;
      GlStateManager.func_179109_b(-f1, 0.0F, 0.0F);
      GlStateManager.func_179114_b(10.0F, 0.0F, 0.0F, 1.0F);
      this.func_175035_a(p_180451_1_, -8372020);
      GlStateManager.func_179121_F();
      GlStateManager.func_179128_n(5888);
      GlStateManager.func_179112_b(770, 771);
      GlStateManager.func_179145_e();
      GlStateManager.func_179143_c(515);
      GlStateManager.func_179132_a(true);
      this.field_175057_n.func_110577_a(TextureMap.field_110575_b);
   }

   private void func_175038_a(WorldRenderer p_175038_1_, BakedQuad p_175038_2_) {
      Vec3i vec3i = p_175038_2_.func_178210_d().func_176730_m();
      p_175038_1_.func_178975_e((float)vec3i.func_177958_n(), (float)vec3i.func_177956_o(), (float)vec3i.func_177952_p());
   }

   private void func_175033_a(WorldRenderer p_175033_1_, BakedQuad p_175033_2_, int p_175033_3_) {
      p_175033_1_.func_178981_a(p_175033_2_.func_178209_a());
      p_175033_1_.func_178968_d(p_175033_3_);
      this.func_175038_a(p_175033_1_, p_175033_2_);
   }

   private void func_175032_a(WorldRenderer p_175032_1_, List<BakedQuad> p_175032_2_, int p_175032_3_, ItemStack p_175032_4_) {
      boolean flag = p_175032_3_ == -1 && p_175032_4_ != null;
      int i = 0;

      for(int j = p_175032_2_.size(); i < j; ++i) {
         BakedQuad bakedquad = (BakedQuad)p_175032_2_.get(i);
         int k = p_175032_3_;
         if(flag && bakedquad.func_178212_b()) {
            k = p_175032_4_.func_77973_b().func_82790_a(p_175032_4_, bakedquad.func_178211_c());
            if(EntityRenderer.field_78517_a) {
               k = TextureUtil.func_177054_c(k);
            }

            k = k | -16777216;
         }

         this.func_175033_a(p_175032_1_, bakedquad, k);
      }

   }

   public boolean func_175050_a(ItemStack p_175050_1_) {
      IBakedModel ibakedmodel = this.field_175059_m.func_178089_a(p_175050_1_);
      return ibakedmodel == null?false:ibakedmodel.func_177556_c();
   }

   private void func_175046_c(ItemStack p_175046_1_) {
      IBakedModel ibakedmodel = this.field_175059_m.func_178089_a(p_175046_1_);
      Item item = p_175046_1_.func_77973_b();
      if(item != null) {
         boolean flag = ibakedmodel.func_177556_c();
         if(!flag) {
            GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
         }

         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public void func_181564_a(ItemStack p_181564_1_, ItemCameraTransforms.TransformType p_181564_2_) {
      if(p_181564_1_ != null) {
         IBakedModel ibakedmodel = this.field_175059_m.func_178089_a(p_181564_1_);
         this.func_175040_a(p_181564_1_, ibakedmodel, p_181564_2_);
      }
   }

   public void func_175049_a(ItemStack p_175049_1_, EntityLivingBase p_175049_2_, ItemCameraTransforms.TransformType p_175049_3_) {
      if(p_175049_1_ != null && p_175049_2_ != null) {
         IBakedModel ibakedmodel = this.field_175059_m.func_178089_a(p_175049_1_);
         if(p_175049_2_ instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)p_175049_2_;
            Item item = p_175049_1_.func_77973_b();
            ModelResourceLocation modelresourcelocation = null;
            if(item == Items.field_151112_aM && entityplayer.field_71104_cf != null) {
               modelresourcelocation = new ModelResourceLocation("fishing_rod_cast", "inventory");
            } else if(item == Items.field_151031_f && entityplayer.func_71011_bu() != null) {
               int i = p_175049_1_.func_77988_m() - entityplayer.func_71052_bv();
               if(i >= 18) {
                  modelresourcelocation = new ModelResourceLocation("bow_pulling_2", "inventory");
               } else if(i > 13) {
                  modelresourcelocation = new ModelResourceLocation("bow_pulling_1", "inventory");
               } else if(i > 0) {
                  modelresourcelocation = new ModelResourceLocation("bow_pulling_0", "inventory");
               }
            }

            if(modelresourcelocation != null) {
               ibakedmodel = this.field_175059_m.func_178083_a().func_174953_a(modelresourcelocation);
            }
         }

         this.func_175040_a(p_175049_1_, ibakedmodel, p_175049_3_);
      }
   }

   protected void func_175040_a(ItemStack p_175040_1_, IBakedModel p_175040_2_, ItemCameraTransforms.TransformType p_175040_3_) {
      this.field_175057_n.func_110577_a(TextureMap.field_110575_b);
      this.field_175057_n.func_110581_b(TextureMap.field_110575_b).func_174936_b(false, false);
      this.func_175046_c(p_175040_1_);
      GlStateManager.func_179091_B();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179094_E();
      ItemCameraTransforms itemcameratransforms = p_175040_2_.func_177552_f();
      itemcameratransforms.func_181689_a(p_175040_3_);
      if(this.func_183005_a(itemcameratransforms.func_181688_b(p_175040_3_))) {
         GlStateManager.func_179107_e(1028);
      }

      this.func_180454_a(p_175040_1_, p_175040_2_);
      GlStateManager.func_179107_e(1029);
      GlStateManager.func_179121_F();
      GlStateManager.func_179101_C();
      GlStateManager.func_179084_k();
      this.field_175057_n.func_110577_a(TextureMap.field_110575_b);
      this.field_175057_n.func_110581_b(TextureMap.field_110575_b).func_174935_a();
   }

   private boolean func_183005_a(ItemTransformVec3f p_183005_1_) {
      return p_183005_1_.field_178363_d.x < 0.0F ^ p_183005_1_.field_178363_d.y < 0.0F ^ p_183005_1_.field_178363_d.z < 0.0F;
   }

   public void func_175042_a(ItemStack p_175042_1_, int p_175042_2_, int p_175042_3_) {
      IBakedModel ibakedmodel = this.field_175059_m.func_178089_a(p_175042_1_);
      GlStateManager.func_179094_E();
      this.field_175057_n.func_110577_a(TextureMap.field_110575_b);
      this.field_175057_n.func_110581_b(TextureMap.field_110575_b).func_174936_b(false, false);
      GlStateManager.func_179091_B();
      GlStateManager.func_179141_d();
      GlStateManager.func_179092_a(516, 0.1F);
      GlStateManager.func_179147_l();
      GlStateManager.func_179112_b(770, 771);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.func_180452_a(p_175042_2_, p_175042_3_, ibakedmodel.func_177556_c());
      ibakedmodel.func_177552_f().func_181689_a(ItemCameraTransforms.TransformType.GUI);
      this.func_180454_a(p_175042_1_, ibakedmodel);
      GlStateManager.func_179118_c();
      GlStateManager.func_179101_C();
      GlStateManager.func_179140_f();
      GlStateManager.func_179121_F();
      this.field_175057_n.func_110577_a(TextureMap.field_110575_b);
      this.field_175057_n.func_110581_b(TextureMap.field_110575_b).func_174935_a();
   }

   private void func_180452_a(int p_180452_1_, int p_180452_2_, boolean p_180452_3_) {
      GlStateManager.func_179109_b((float)p_180452_1_, (float)p_180452_2_, 100.0F + this.field_77023_b);
      GlStateManager.func_179109_b(8.0F, 8.0F, 0.0F);
      GlStateManager.func_179152_a(1.0F, 1.0F, -1.0F);
      GlStateManager.func_179152_a(0.5F, 0.5F, 0.5F);
      if(p_180452_3_) {
         GlStateManager.func_179152_a(40.0F, 40.0F, 40.0F);
         GlStateManager.func_179114_b(210.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179114_b(-135.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179145_e();
      } else {
         GlStateManager.func_179152_a(64.0F, 64.0F, 64.0F);
         GlStateManager.func_179114_b(180.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179140_f();
      }

   }

   public void func_180450_b(final ItemStack p_180450_1_, int p_180450_2_, int p_180450_3_) {
      if(p_180450_1_ != null && p_180450_1_.func_77973_b() != null) {
         this.field_77023_b += 50.0F;

         try {
            this.func_175042_a(p_180450_1_, p_180450_2_, p_180450_3_);
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering item");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being rendered");
            crashreportcategory.func_71500_a("Item Type", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf((Object)p_180450_1_.func_77973_b());
               }
            });
            crashreportcategory.func_71500_a("Item Aux", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf(p_180450_1_.func_77960_j());
               }
            });
            crashreportcategory.func_71500_a("Item NBT", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf((Object)p_180450_1_.func_77978_p());
               }
            });
            crashreportcategory.func_71500_a("Item Foil", new Callable<String>() {
               public String call() throws Exception {
                  return String.valueOf(p_180450_1_.func_77962_s());
               }
            });
            throw new ReportedException(crashreport);
         }

         this.field_77023_b -= 50.0F;
      }
   }

   public void func_175030_a(FontRenderer p_175030_1_, ItemStack p_175030_2_, int p_175030_3_, int p_175030_4_) {
      this.func_180453_a(p_175030_1_, p_175030_2_, p_175030_3_, p_175030_4_, (String)null);
   }

   public void func_180453_a(FontRenderer p_180453_1_, ItemStack p_180453_2_, int p_180453_3_, int p_180453_4_, String p_180453_5_) {
      if(p_180453_2_ != null) {
         if(p_180453_2_.field_77994_a != 1 || p_180453_5_ != null) {
            String s = p_180453_5_ == null?String.valueOf(p_180453_2_.field_77994_a):p_180453_5_;
            if(p_180453_5_ == null && p_180453_2_.field_77994_a < 1) {
               s = EnumChatFormatting.RED + String.valueOf(p_180453_2_.field_77994_a);
            }

            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            GlStateManager.func_179084_k();
            p_180453_1_.func_175063_a(s, (float)(p_180453_3_ + 19 - 2 - p_180453_1_.func_78256_a(s)), (float)(p_180453_4_ + 6 + 3), 16777215);
            GlStateManager.func_179145_e();
            GlStateManager.func_179126_j();
         }

         if(p_180453_2_.func_77951_h()) {
            int j = (int)Math.round(13.0D - (double)p_180453_2_.func_77952_i() * 13.0D / (double)p_180453_2_.func_77958_k());
            int i = (int)Math.round(255.0D - (double)p_180453_2_.func_77952_i() * 255.0D / (double)p_180453_2_.func_77958_k());
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            GlStateManager.func_179090_x();
            GlStateManager.func_179118_c();
            GlStateManager.func_179084_k();
            Tessellator tessellator = Tessellator.func_178181_a();
            WorldRenderer worldrenderer = tessellator.func_178180_c();
            this.func_181565_a(worldrenderer, p_180453_3_ + 2, p_180453_4_ + 13, 13, 2, 0, 0, 0, 255);
            this.func_181565_a(worldrenderer, p_180453_3_ + 2, p_180453_4_ + 13, 12, 1, (255 - i) / 4, 64, 0, 255);
            this.func_181565_a(worldrenderer, p_180453_3_ + 2, p_180453_4_ + 13, j, 1, 255 - i, i, 0, 255);
            GlStateManager.func_179147_l();
            GlStateManager.func_179141_d();
            GlStateManager.func_179098_w();
            GlStateManager.func_179145_e();
            GlStateManager.func_179126_j();
         }

      }
   }

   private void func_181565_a(WorldRenderer p_181565_1_, int p_181565_2_, int p_181565_3_, int p_181565_4_, int p_181565_5_, int p_181565_6_, int p_181565_7_, int p_181565_8_, int p_181565_9_) {
      p_181565_1_.func_181668_a(7, DefaultVertexFormats.field_181706_f);
      p_181565_1_.func_181662_b((double)(p_181565_2_ + 0), (double)(p_181565_3_ + 0), 0.0D).func_181669_b(p_181565_6_, p_181565_7_, p_181565_8_, p_181565_9_).func_181675_d();
      p_181565_1_.func_181662_b((double)(p_181565_2_ + 0), (double)(p_181565_3_ + p_181565_5_), 0.0D).func_181669_b(p_181565_6_, p_181565_7_, p_181565_8_, p_181565_9_).func_181675_d();
      p_181565_1_.func_181662_b((double)(p_181565_2_ + p_181565_4_), (double)(p_181565_3_ + p_181565_5_), 0.0D).func_181669_b(p_181565_6_, p_181565_7_, p_181565_8_, p_181565_9_).func_181675_d();
      p_181565_1_.func_181662_b((double)(p_181565_2_ + p_181565_4_), (double)(p_181565_3_ + 0), 0.0D).func_181669_b(p_181565_6_, p_181565_7_, p_181565_8_, p_181565_9_).func_181675_d();
      Tessellator.func_178181_a().func_78381_a();
   }

   private void func_175041_b() {
      this.func_175031_a(Blocks.field_150467_bQ, "anvil_intact");
      this.func_175029_a(Blocks.field_150467_bQ, 1, "anvil_slightly_damaged");
      this.func_175029_a(Blocks.field_150467_bQ, 2, "anvil_very_damaged");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.BLACK.func_176765_a(), "black_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.BLUE.func_176765_a(), "blue_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.BROWN.func_176765_a(), "brown_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.CYAN.func_176765_a(), "cyan_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.GRAY.func_176765_a(), "gray_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.GREEN.func_176765_a(), "green_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.LIME.func_176765_a(), "lime_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.ORANGE.func_176765_a(), "orange_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.PINK.func_176765_a(), "pink_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.PURPLE.func_176765_a(), "purple_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.RED.func_176765_a(), "red_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.SILVER.func_176765_a(), "silver_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.WHITE.func_176765_a(), "white_carpet");
      this.func_175029_a(Blocks.field_150404_cg, EnumDyeColor.YELLOW.func_176765_a(), "yellow_carpet");
      this.func_175029_a(Blocks.field_150463_bK, BlockWall.EnumType.MOSSY.func_176657_a(), "mossy_cobblestone_wall");
      this.func_175029_a(Blocks.field_150463_bK, BlockWall.EnumType.NORMAL.func_176657_a(), "cobblestone_wall");
      this.func_175029_a(Blocks.field_150346_d, BlockDirt.DirtType.COARSE_DIRT.func_176925_a(), "coarse_dirt");
      this.func_175029_a(Blocks.field_150346_d, BlockDirt.DirtType.DIRT.func_176925_a(), "dirt");
      this.func_175029_a(Blocks.field_150346_d, BlockDirt.DirtType.PODZOL.func_176925_a(), "podzol");
      this.func_175029_a(Blocks.field_150398_cm, BlockDoublePlant.EnumPlantType.FERN.func_176936_a(), "double_fern");
      this.func_175029_a(Blocks.field_150398_cm, BlockDoublePlant.EnumPlantType.GRASS.func_176936_a(), "double_grass");
      this.func_175029_a(Blocks.field_150398_cm, BlockDoublePlant.EnumPlantType.PAEONIA.func_176936_a(), "paeonia");
      this.func_175029_a(Blocks.field_150398_cm, BlockDoublePlant.EnumPlantType.ROSE.func_176936_a(), "double_rose");
      this.func_175029_a(Blocks.field_150398_cm, BlockDoublePlant.EnumPlantType.SUNFLOWER.func_176936_a(), "sunflower");
      this.func_175029_a(Blocks.field_150398_cm, BlockDoublePlant.EnumPlantType.SYRINGA.func_176936_a(), "syringa");
      this.func_175029_a(Blocks.field_150362_t, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_leaves");
      this.func_175029_a(Blocks.field_150362_t, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_leaves");
      this.func_175029_a(Blocks.field_150362_t, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_leaves");
      this.func_175029_a(Blocks.field_150362_t, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_leaves");
      this.func_175029_a(Blocks.field_150361_u, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4, "acacia_leaves");
      this.func_175029_a(Blocks.field_150361_u, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4, "dark_oak_leaves");
      this.func_175029_a(Blocks.field_150364_r, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_log");
      this.func_175029_a(Blocks.field_150364_r, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_log");
      this.func_175029_a(Blocks.field_150364_r, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_log");
      this.func_175029_a(Blocks.field_150364_r, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_log");
      this.func_175029_a(Blocks.field_150363_s, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4, "acacia_log");
      this.func_175029_a(Blocks.field_150363_s, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4, "dark_oak_log");
      this.func_175029_a(Blocks.field_150418_aU, BlockSilverfish.EnumType.CHISELED_STONEBRICK.func_176881_a(), "chiseled_brick_monster_egg");
      this.func_175029_a(Blocks.field_150418_aU, BlockSilverfish.EnumType.COBBLESTONE.func_176881_a(), "cobblestone_monster_egg");
      this.func_175029_a(Blocks.field_150418_aU, BlockSilverfish.EnumType.CRACKED_STONEBRICK.func_176881_a(), "cracked_brick_monster_egg");
      this.func_175029_a(Blocks.field_150418_aU, BlockSilverfish.EnumType.MOSSY_STONEBRICK.func_176881_a(), "mossy_brick_monster_egg");
      this.func_175029_a(Blocks.field_150418_aU, BlockSilverfish.EnumType.STONE.func_176881_a(), "stone_monster_egg");
      this.func_175029_a(Blocks.field_150418_aU, BlockSilverfish.EnumType.STONEBRICK.func_176881_a(), "stone_brick_monster_egg");
      this.func_175029_a(Blocks.field_150344_f, BlockPlanks.EnumType.ACACIA.func_176839_a(), "acacia_planks");
      this.func_175029_a(Blocks.field_150344_f, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_planks");
      this.func_175029_a(Blocks.field_150344_f, BlockPlanks.EnumType.DARK_OAK.func_176839_a(), "dark_oak_planks");
      this.func_175029_a(Blocks.field_150344_f, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_planks");
      this.func_175029_a(Blocks.field_150344_f, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_planks");
      this.func_175029_a(Blocks.field_150344_f, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_planks");
      this.func_175029_a(Blocks.field_180397_cI, BlockPrismarine.EnumType.BRICKS.func_176807_a(), "prismarine_bricks");
      this.func_175029_a(Blocks.field_180397_cI, BlockPrismarine.EnumType.DARK.func_176807_a(), "dark_prismarine");
      this.func_175029_a(Blocks.field_180397_cI, BlockPrismarine.EnumType.ROUGH.func_176807_a(), "prismarine");
      this.func_175029_a(Blocks.field_150371_ca, BlockQuartz.EnumType.CHISELED.func_176796_a(), "chiseled_quartz_block");
      this.func_175029_a(Blocks.field_150371_ca, BlockQuartz.EnumType.DEFAULT.func_176796_a(), "quartz_block");
      this.func_175029_a(Blocks.field_150371_ca, BlockQuartz.EnumType.LINES_Y.func_176796_a(), "quartz_column");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.ALLIUM.func_176968_b(), "allium");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.BLUE_ORCHID.func_176968_b(), "blue_orchid");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.HOUSTONIA.func_176968_b(), "houstonia");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.ORANGE_TULIP.func_176968_b(), "orange_tulip");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.OXEYE_DAISY.func_176968_b(), "oxeye_daisy");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.PINK_TULIP.func_176968_b(), "pink_tulip");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.POPPY.func_176968_b(), "poppy");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.RED_TULIP.func_176968_b(), "red_tulip");
      this.func_175029_a(Blocks.field_150328_O, BlockFlower.EnumFlowerType.WHITE_TULIP.func_176968_b(), "white_tulip");
      this.func_175029_a(Blocks.field_150354_m, BlockSand.EnumType.RED_SAND.func_176688_a(), "red_sand");
      this.func_175029_a(Blocks.field_150354_m, BlockSand.EnumType.SAND.func_176688_a(), "sand");
      this.func_175029_a(Blocks.field_150322_A, BlockSandStone.EnumType.CHISELED.func_176675_a(), "chiseled_sandstone");
      this.func_175029_a(Blocks.field_150322_A, BlockSandStone.EnumType.DEFAULT.func_176675_a(), "sandstone");
      this.func_175029_a(Blocks.field_150322_A, BlockSandStone.EnumType.SMOOTH.func_176675_a(), "smooth_sandstone");
      this.func_175029_a(Blocks.field_180395_cM, BlockRedSandstone.EnumType.CHISELED.func_176827_a(), "chiseled_red_sandstone");
      this.func_175029_a(Blocks.field_180395_cM, BlockRedSandstone.EnumType.DEFAULT.func_176827_a(), "red_sandstone");
      this.func_175029_a(Blocks.field_180395_cM, BlockRedSandstone.EnumType.SMOOTH.func_176827_a(), "smooth_red_sandstone");
      this.func_175029_a(Blocks.field_150345_g, BlockPlanks.EnumType.ACACIA.func_176839_a(), "acacia_sapling");
      this.func_175029_a(Blocks.field_150345_g, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_sapling");
      this.func_175029_a(Blocks.field_150345_g, BlockPlanks.EnumType.DARK_OAK.func_176839_a(), "dark_oak_sapling");
      this.func_175029_a(Blocks.field_150345_g, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_sapling");
      this.func_175029_a(Blocks.field_150345_g, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_sapling");
      this.func_175029_a(Blocks.field_150345_g, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_sapling");
      this.func_175029_a(Blocks.field_150360_v, 0, "sponge");
      this.func_175029_a(Blocks.field_150360_v, 1, "sponge_wet");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.BLACK.func_176765_a(), "black_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.BLUE.func_176765_a(), "blue_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.BROWN.func_176765_a(), "brown_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.CYAN.func_176765_a(), "cyan_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.GRAY.func_176765_a(), "gray_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.GREEN.func_176765_a(), "green_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.LIME.func_176765_a(), "lime_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.ORANGE.func_176765_a(), "orange_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.PINK.func_176765_a(), "pink_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.PURPLE.func_176765_a(), "purple_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.RED.func_176765_a(), "red_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.SILVER.func_176765_a(), "silver_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.WHITE.func_176765_a(), "white_stained_glass");
      this.func_175029_a(Blocks.field_150399_cn, EnumDyeColor.YELLOW.func_176765_a(), "yellow_stained_glass");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.BLACK.func_176765_a(), "black_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.BLUE.func_176765_a(), "blue_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.BROWN.func_176765_a(), "brown_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.CYAN.func_176765_a(), "cyan_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.GRAY.func_176765_a(), "gray_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.GREEN.func_176765_a(), "green_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.LIME.func_176765_a(), "lime_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.ORANGE.func_176765_a(), "orange_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.PINK.func_176765_a(), "pink_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.PURPLE.func_176765_a(), "purple_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.RED.func_176765_a(), "red_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.SILVER.func_176765_a(), "silver_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.WHITE.func_176765_a(), "white_stained_glass_pane");
      this.func_175029_a(Blocks.field_150397_co, EnumDyeColor.YELLOW.func_176765_a(), "yellow_stained_glass_pane");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.BLACK.func_176765_a(), "black_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.BLUE.func_176765_a(), "blue_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.BROWN.func_176765_a(), "brown_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.CYAN.func_176765_a(), "cyan_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.GRAY.func_176765_a(), "gray_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.GREEN.func_176765_a(), "green_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.LIME.func_176765_a(), "lime_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.ORANGE.func_176765_a(), "orange_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.PINK.func_176765_a(), "pink_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.PURPLE.func_176765_a(), "purple_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.RED.func_176765_a(), "red_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.SILVER.func_176765_a(), "silver_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.WHITE.func_176765_a(), "white_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150406_ce, EnumDyeColor.YELLOW.func_176765_a(), "yellow_stained_hardened_clay");
      this.func_175029_a(Blocks.field_150348_b, BlockStone.EnumType.ANDESITE.func_176642_a(), "andesite");
      this.func_175029_a(Blocks.field_150348_b, BlockStone.EnumType.ANDESITE_SMOOTH.func_176642_a(), "andesite_smooth");
      this.func_175029_a(Blocks.field_150348_b, BlockStone.EnumType.DIORITE.func_176642_a(), "diorite");
      this.func_175029_a(Blocks.field_150348_b, BlockStone.EnumType.DIORITE_SMOOTH.func_176642_a(), "diorite_smooth");
      this.func_175029_a(Blocks.field_150348_b, BlockStone.EnumType.GRANITE.func_176642_a(), "granite");
      this.func_175029_a(Blocks.field_150348_b, BlockStone.EnumType.GRANITE_SMOOTH.func_176642_a(), "granite_smooth");
      this.func_175029_a(Blocks.field_150348_b, BlockStone.EnumType.STONE.func_176642_a(), "stone");
      this.func_175029_a(Blocks.field_150417_aV, BlockStoneBrick.EnumType.CRACKED.func_176612_a(), "cracked_stonebrick");
      this.func_175029_a(Blocks.field_150417_aV, BlockStoneBrick.EnumType.DEFAULT.func_176612_a(), "stonebrick");
      this.func_175029_a(Blocks.field_150417_aV, BlockStoneBrick.EnumType.CHISELED.func_176612_a(), "chiseled_stonebrick");
      this.func_175029_a(Blocks.field_150417_aV, BlockStoneBrick.EnumType.MOSSY.func_176612_a(), "mossy_stonebrick");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.BRICK.func_176624_a(), "brick_slab");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.COBBLESTONE.func_176624_a(), "cobblestone_slab");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.WOOD.func_176624_a(), "old_wood_slab");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.NETHERBRICK.func_176624_a(), "nether_brick_slab");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.QUARTZ.func_176624_a(), "quartz_slab");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.SAND.func_176624_a(), "sandstone_slab");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a(), "stone_brick_slab");
      this.func_175029_a(Blocks.field_150333_U, BlockStoneSlab.EnumType.STONE.func_176624_a(), "stone_slab");
      this.func_175029_a(Blocks.field_180389_cP, BlockStoneSlabNew.EnumType.RED_SANDSTONE.func_176915_a(), "red_sandstone_slab");
      this.func_175029_a(Blocks.field_150329_H, BlockTallGrass.EnumType.DEAD_BUSH.func_177044_a(), "dead_bush");
      this.func_175029_a(Blocks.field_150329_H, BlockTallGrass.EnumType.FERN.func_177044_a(), "fern");
      this.func_175029_a(Blocks.field_150329_H, BlockTallGrass.EnumType.GRASS.func_177044_a(), "tall_grass");
      this.func_175029_a(Blocks.field_150376_bx, BlockPlanks.EnumType.ACACIA.func_176839_a(), "acacia_slab");
      this.func_175029_a(Blocks.field_150376_bx, BlockPlanks.EnumType.BIRCH.func_176839_a(), "birch_slab");
      this.func_175029_a(Blocks.field_150376_bx, BlockPlanks.EnumType.DARK_OAK.func_176839_a(), "dark_oak_slab");
      this.func_175029_a(Blocks.field_150376_bx, BlockPlanks.EnumType.JUNGLE.func_176839_a(), "jungle_slab");
      this.func_175029_a(Blocks.field_150376_bx, BlockPlanks.EnumType.OAK.func_176839_a(), "oak_slab");
      this.func_175029_a(Blocks.field_150376_bx, BlockPlanks.EnumType.SPRUCE.func_176839_a(), "spruce_slab");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.BLACK.func_176765_a(), "black_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.BLUE.func_176765_a(), "blue_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.BROWN.func_176765_a(), "brown_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.CYAN.func_176765_a(), "cyan_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.GRAY.func_176765_a(), "gray_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.GREEN.func_176765_a(), "green_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.LIGHT_BLUE.func_176765_a(), "light_blue_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.LIME.func_176765_a(), "lime_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.MAGENTA.func_176765_a(), "magenta_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.ORANGE.func_176765_a(), "orange_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.PINK.func_176765_a(), "pink_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.PURPLE.func_176765_a(), "purple_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.RED.func_176765_a(), "red_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.SILVER.func_176765_a(), "silver_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.WHITE.func_176765_a(), "white_wool");
      this.func_175029_a(Blocks.field_150325_L, EnumDyeColor.YELLOW.func_176765_a(), "yellow_wool");
      this.func_175031_a(Blocks.field_150400_ck, "acacia_stairs");
      this.func_175031_a(Blocks.field_150408_cc, "activator_rail");
      this.func_175031_a(Blocks.field_150461_bJ, "beacon");
      this.func_175031_a(Blocks.field_150357_h, "bedrock");
      this.func_175031_a(Blocks.field_150487_bG, "birch_stairs");
      this.func_175031_a(Blocks.field_150342_X, "bookshelf");
      this.func_175031_a(Blocks.field_150336_V, "brick_block");
      this.func_175031_a(Blocks.field_150336_V, "brick_block");
      this.func_175031_a(Blocks.field_150389_bf, "brick_stairs");
      this.func_175031_a(Blocks.field_150338_P, "brown_mushroom");
      this.func_175031_a(Blocks.field_150434_aF, "cactus");
      this.func_175031_a(Blocks.field_150435_aG, "clay");
      this.func_175031_a(Blocks.field_150402_ci, "coal_block");
      this.func_175031_a(Blocks.field_150365_q, "coal_ore");
      this.func_175031_a(Blocks.field_150347_e, "cobblestone");
      this.func_175031_a(Blocks.field_150462_ai, "crafting_table");
      this.func_175031_a(Blocks.field_150401_cl, "dark_oak_stairs");
      this.func_175031_a(Blocks.field_150453_bW, "daylight_detector");
      this.func_175031_a(Blocks.field_150330_I, "dead_bush");
      this.func_175031_a(Blocks.field_150319_E, "detector_rail");
      this.func_175031_a(Blocks.field_150484_ah, "diamond_block");
      this.func_175031_a(Blocks.field_150482_ag, "diamond_ore");
      this.func_175031_a(Blocks.field_150367_z, "dispenser");
      this.func_175031_a(Blocks.field_150409_cd, "dropper");
      this.func_175031_a(Blocks.field_150475_bE, "emerald_block");
      this.func_175031_a(Blocks.field_150412_bA, "emerald_ore");
      this.func_175031_a(Blocks.field_150381_bn, "enchanting_table");
      this.func_175031_a(Blocks.field_150378_br, "end_portal_frame");
      this.func_175031_a(Blocks.field_150377_bs, "end_stone");
      this.func_175031_a(Blocks.field_180407_aO, "oak_fence");
      this.func_175031_a(Blocks.field_180408_aP, "spruce_fence");
      this.func_175031_a(Blocks.field_180404_aQ, "birch_fence");
      this.func_175031_a(Blocks.field_180403_aR, "jungle_fence");
      this.func_175031_a(Blocks.field_180406_aS, "dark_oak_fence");
      this.func_175031_a(Blocks.field_180405_aT, "acacia_fence");
      this.func_175031_a(Blocks.field_180390_bo, "oak_fence_gate");
      this.func_175031_a(Blocks.field_180391_bp, "spruce_fence_gate");
      this.func_175031_a(Blocks.field_180392_bq, "birch_fence_gate");
      this.func_175031_a(Blocks.field_180386_br, "jungle_fence_gate");
      this.func_175031_a(Blocks.field_180385_bs, "dark_oak_fence_gate");
      this.func_175031_a(Blocks.field_180387_bt, "acacia_fence_gate");
      this.func_175031_a(Blocks.field_150460_al, "furnace");
      this.func_175031_a(Blocks.field_150359_w, "glass");
      this.func_175031_a(Blocks.field_150410_aZ, "glass_pane");
      this.func_175031_a(Blocks.field_150426_aN, "glowstone");
      this.func_175031_a(Blocks.field_150318_D, "golden_rail");
      this.func_175031_a(Blocks.field_150340_R, "gold_block");
      this.func_175031_a(Blocks.field_150352_o, "gold_ore");
      this.func_175031_a(Blocks.field_150349_c, "grass");
      this.func_175031_a(Blocks.field_150351_n, "gravel");
      this.func_175031_a(Blocks.field_150405_ch, "hardened_clay");
      this.func_175031_a(Blocks.field_150407_cf, "hay_block");
      this.func_175031_a(Blocks.field_150443_bT, "heavy_weighted_pressure_plate");
      this.func_175031_a(Blocks.field_150438_bZ, "hopper");
      this.func_175031_a(Blocks.field_150432_aD, "ice");
      this.func_175031_a(Blocks.field_150411_aY, "iron_bars");
      this.func_175031_a(Blocks.field_150339_S, "iron_block");
      this.func_175031_a(Blocks.field_150366_p, "iron_ore");
      this.func_175031_a(Blocks.field_180400_cw, "iron_trapdoor");
      this.func_175031_a(Blocks.field_150421_aI, "jukebox");
      this.func_175031_a(Blocks.field_150481_bH, "jungle_stairs");
      this.func_175031_a(Blocks.field_150468_ap, "ladder");
      this.func_175031_a(Blocks.field_150368_y, "lapis_block");
      this.func_175031_a(Blocks.field_150369_x, "lapis_ore");
      this.func_175031_a(Blocks.field_150442_at, "lever");
      this.func_175031_a(Blocks.field_150445_bS, "light_weighted_pressure_plate");
      this.func_175031_a(Blocks.field_150428_aP, "lit_pumpkin");
      this.func_175031_a(Blocks.field_150440_ba, "melon_block");
      this.func_175031_a(Blocks.field_150341_Y, "mossy_cobblestone");
      this.func_175031_a(Blocks.field_150391_bh, "mycelium");
      this.func_175031_a(Blocks.field_150424_aL, "netherrack");
      this.func_175031_a(Blocks.field_150385_bj, "nether_brick");
      this.func_175031_a(Blocks.field_150386_bk, "nether_brick_fence");
      this.func_175031_a(Blocks.field_150387_bl, "nether_brick_stairs");
      this.func_175031_a(Blocks.field_150323_B, "noteblock");
      this.func_175031_a(Blocks.field_150476_ad, "oak_stairs");
      this.func_175031_a(Blocks.field_150343_Z, "obsidian");
      this.func_175031_a(Blocks.field_150403_cj, "packed_ice");
      this.func_175031_a(Blocks.field_150331_J, "piston");
      this.func_175031_a(Blocks.field_150423_aK, "pumpkin");
      this.func_175031_a(Blocks.field_150449_bY, "quartz_ore");
      this.func_175031_a(Blocks.field_150370_cb, "quartz_stairs");
      this.func_175031_a(Blocks.field_150448_aq, "rail");
      this.func_175031_a(Blocks.field_150451_bX, "redstone_block");
      this.func_175031_a(Blocks.field_150379_bu, "redstone_lamp");
      this.func_175031_a(Blocks.field_150450_ax, "redstone_ore");
      this.func_175031_a(Blocks.field_150429_aA, "redstone_torch");
      this.func_175031_a(Blocks.field_150337_Q, "red_mushroom");
      this.func_175031_a(Blocks.field_150372_bz, "sandstone_stairs");
      this.func_175031_a(Blocks.field_180396_cN, "red_sandstone_stairs");
      this.func_175031_a(Blocks.field_180398_cJ, "sea_lantern");
      this.func_175031_a(Blocks.field_180399_cE, "slime");
      this.func_175031_a(Blocks.field_150433_aE, "snow");
      this.func_175031_a(Blocks.field_150431_aC, "snow_layer");
      this.func_175031_a(Blocks.field_150425_aM, "soul_sand");
      this.func_175031_a(Blocks.field_150485_bF, "spruce_stairs");
      this.func_175031_a(Blocks.field_150320_F, "sticky_piston");
      this.func_175031_a(Blocks.field_150390_bg, "stone_brick_stairs");
      this.func_175031_a(Blocks.field_150430_aB, "stone_button");
      this.func_175031_a(Blocks.field_150456_au, "stone_pressure_plate");
      this.func_175031_a(Blocks.field_150446_ar, "stone_stairs");
      this.func_175031_a(Blocks.field_150335_W, "tnt");
      this.func_175031_a(Blocks.field_150478_aa, "torch");
      this.func_175031_a(Blocks.field_150415_aT, "trapdoor");
      this.func_175031_a(Blocks.field_150479_bC, "tripwire_hook");
      this.func_175031_a(Blocks.field_150395_bd, "vine");
      this.func_175031_a(Blocks.field_150392_bi, "waterlily");
      this.func_175031_a(Blocks.field_150321_G, "web");
      this.func_175031_a(Blocks.field_150471_bO, "wooden_button");
      this.func_175031_a(Blocks.field_150452_aw, "wooden_pressure_plate");
      this.func_175029_a(Blocks.field_150327_N, BlockFlower.EnumFlowerType.DANDELION.func_176968_b(), "dandelion");
      this.func_175031_a(Blocks.field_150486_ae, "chest");
      this.func_175031_a(Blocks.field_150447_bR, "trapped_chest");
      this.func_175031_a(Blocks.field_150477_bB, "ender_chest");
      this.func_175047_a(Items.field_151037_a, "iron_shovel");
      this.func_175047_a(Items.field_151035_b, "iron_pickaxe");
      this.func_175047_a(Items.field_151036_c, "iron_axe");
      this.func_175047_a(Items.field_151033_d, "flint_and_steel");
      this.func_175047_a(Items.field_151034_e, "apple");
      this.func_175048_a(Items.field_151031_f, 0, "bow");
      this.func_175048_a(Items.field_151031_f, 1, "bow_pulling_0");
      this.func_175048_a(Items.field_151031_f, 2, "bow_pulling_1");
      this.func_175048_a(Items.field_151031_f, 3, "bow_pulling_2");
      this.func_175047_a(Items.field_151032_g, "arrow");
      this.func_175048_a(Items.field_151044_h, 0, "coal");
      this.func_175048_a(Items.field_151044_h, 1, "charcoal");
      this.func_175047_a(Items.field_151045_i, "diamond");
      this.func_175047_a(Items.field_151042_j, "iron_ingot");
      this.func_175047_a(Items.field_151043_k, "gold_ingot");
      this.func_175047_a(Items.field_151040_l, "iron_sword");
      this.func_175047_a(Items.field_151041_m, "wooden_sword");
      this.func_175047_a(Items.field_151038_n, "wooden_shovel");
      this.func_175047_a(Items.field_151039_o, "wooden_pickaxe");
      this.func_175047_a(Items.field_151053_p, "wooden_axe");
      this.func_175047_a(Items.field_151052_q, "stone_sword");
      this.func_175047_a(Items.field_151051_r, "stone_shovel");
      this.func_175047_a(Items.field_151050_s, "stone_pickaxe");
      this.func_175047_a(Items.field_151049_t, "stone_axe");
      this.func_175047_a(Items.field_151048_u, "diamond_sword");
      this.func_175047_a(Items.field_151047_v, "diamond_shovel");
      this.func_175047_a(Items.field_151046_w, "diamond_pickaxe");
      this.func_175047_a(Items.field_151056_x, "diamond_axe");
      this.func_175047_a(Items.field_151055_y, "stick");
      this.func_175047_a(Items.field_151054_z, "bowl");
      this.func_175047_a(Items.field_151009_A, "mushroom_stew");
      this.func_175047_a(Items.field_151010_B, "golden_sword");
      this.func_175047_a(Items.field_151011_C, "golden_shovel");
      this.func_175047_a(Items.field_151005_D, "golden_pickaxe");
      this.func_175047_a(Items.field_151006_E, "golden_axe");
      this.func_175047_a(Items.field_151007_F, "string");
      this.func_175047_a(Items.field_151008_G, "feather");
      this.func_175047_a(Items.field_151016_H, "gunpowder");
      this.func_175047_a(Items.field_151017_I, "wooden_hoe");
      this.func_175047_a(Items.field_151018_J, "stone_hoe");
      this.func_175047_a(Items.field_151019_K, "iron_hoe");
      this.func_175047_a(Items.field_151012_L, "diamond_hoe");
      this.func_175047_a(Items.field_151013_M, "golden_hoe");
      this.func_175047_a(Items.field_151014_N, "wheat_seeds");
      this.func_175047_a(Items.field_151015_O, "wheat");
      this.func_175047_a(Items.field_151025_P, "bread");
      this.func_175047_a(Items.field_151024_Q, "leather_helmet");
      this.func_175047_a(Items.field_151027_R, "leather_chestplate");
      this.func_175047_a(Items.field_151026_S, "leather_leggings");
      this.func_175047_a(Items.field_151021_T, "leather_boots");
      this.func_175047_a(Items.field_151020_U, "chainmail_helmet");
      this.func_175047_a(Items.field_151023_V, "chainmail_chestplate");
      this.func_175047_a(Items.field_151022_W, "chainmail_leggings");
      this.func_175047_a(Items.field_151029_X, "chainmail_boots");
      this.func_175047_a(Items.field_151028_Y, "iron_helmet");
      this.func_175047_a(Items.field_151030_Z, "iron_chestplate");
      this.func_175047_a(Items.field_151165_aa, "iron_leggings");
      this.func_175047_a(Items.field_151167_ab, "iron_boots");
      this.func_175047_a(Items.field_151161_ac, "diamond_helmet");
      this.func_175047_a(Items.field_151163_ad, "diamond_chestplate");
      this.func_175047_a(Items.field_151173_ae, "diamond_leggings");
      this.func_175047_a(Items.field_151175_af, "diamond_boots");
      this.func_175047_a(Items.field_151169_ag, "golden_helmet");
      this.func_175047_a(Items.field_151171_ah, "golden_chestplate");
      this.func_175047_a(Items.field_151149_ai, "golden_leggings");
      this.func_175047_a(Items.field_151151_aj, "golden_boots");
      this.func_175047_a(Items.field_151145_ak, "flint");
      this.func_175047_a(Items.field_151147_al, "porkchop");
      this.func_175047_a(Items.field_151157_am, "cooked_porkchop");
      this.func_175047_a(Items.field_151159_an, "painting");
      this.func_175047_a(Items.field_151153_ao, "golden_apple");
      this.func_175048_a(Items.field_151153_ao, 1, "golden_apple");
      this.func_175047_a(Items.field_151155_ap, "sign");
      this.func_175047_a(Items.field_179570_aq, "oak_door");
      this.func_175047_a(Items.field_179569_ar, "spruce_door");
      this.func_175047_a(Items.field_179568_as, "birch_door");
      this.func_175047_a(Items.field_179567_at, "jungle_door");
      this.func_175047_a(Items.field_179572_au, "acacia_door");
      this.func_175047_a(Items.field_179571_av, "dark_oak_door");
      this.func_175047_a(Items.field_151133_ar, "bucket");
      this.func_175047_a(Items.field_151131_as, "water_bucket");
      this.func_175047_a(Items.field_151129_at, "lava_bucket");
      this.func_175047_a(Items.field_151143_au, "minecart");
      this.func_175047_a(Items.field_151141_av, "saddle");
      this.func_175047_a(Items.field_151139_aw, "iron_door");
      this.func_175047_a(Items.field_151137_ax, "redstone");
      this.func_175047_a(Items.field_151126_ay, "snowball");
      this.func_175047_a(Items.field_151124_az, "boat");
      this.func_175047_a(Items.field_151116_aA, "leather");
      this.func_175047_a(Items.field_151117_aB, "milk_bucket");
      this.func_175047_a(Items.field_151118_aC, "brick");
      this.func_175047_a(Items.field_151119_aD, "clay_ball");
      this.func_175047_a(Items.field_151120_aE, "reeds");
      this.func_175047_a(Items.field_151121_aF, "paper");
      this.func_175047_a(Items.field_151122_aG, "book");
      this.func_175047_a(Items.field_151123_aH, "slime_ball");
      this.func_175047_a(Items.field_151108_aI, "chest_minecart");
      this.func_175047_a(Items.field_151109_aJ, "furnace_minecart");
      this.func_175047_a(Items.field_151110_aK, "egg");
      this.func_175047_a(Items.field_151111_aL, "compass");
      this.func_175047_a(Items.field_151112_aM, "fishing_rod");
      this.func_175048_a(Items.field_151112_aM, 1, "fishing_rod_cast");
      this.func_175047_a(Items.field_151113_aN, "clock");
      this.func_175047_a(Items.field_151114_aO, "glowstone_dust");
      this.func_175048_a(Items.field_151115_aP, ItemFishFood.FishType.COD.func_150976_a(), "cod");
      this.func_175048_a(Items.field_151115_aP, ItemFishFood.FishType.SALMON.func_150976_a(), "salmon");
      this.func_175048_a(Items.field_151115_aP, ItemFishFood.FishType.CLOWNFISH.func_150976_a(), "clownfish");
      this.func_175048_a(Items.field_151115_aP, ItemFishFood.FishType.PUFFERFISH.func_150976_a(), "pufferfish");
      this.func_175048_a(Items.field_179566_aV, ItemFishFood.FishType.COD.func_150976_a(), "cooked_cod");
      this.func_175048_a(Items.field_179566_aV, ItemFishFood.FishType.SALMON.func_150976_a(), "cooked_salmon");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.BLACK.func_176767_b(), "dye_black");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.RED.func_176767_b(), "dye_red");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.GREEN.func_176767_b(), "dye_green");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.BROWN.func_176767_b(), "dye_brown");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.BLUE.func_176767_b(), "dye_blue");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.PURPLE.func_176767_b(), "dye_purple");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.CYAN.func_176767_b(), "dye_cyan");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.SILVER.func_176767_b(), "dye_silver");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.GRAY.func_176767_b(), "dye_gray");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.PINK.func_176767_b(), "dye_pink");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.LIME.func_176767_b(), "dye_lime");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.YELLOW.func_176767_b(), "dye_yellow");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.LIGHT_BLUE.func_176767_b(), "dye_light_blue");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.MAGENTA.func_176767_b(), "dye_magenta");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.ORANGE.func_176767_b(), "dye_orange");
      this.func_175048_a(Items.field_151100_aR, EnumDyeColor.WHITE.func_176767_b(), "dye_white");
      this.func_175047_a(Items.field_151103_aS, "bone");
      this.func_175047_a(Items.field_151102_aT, "sugar");
      this.func_175047_a(Items.field_151105_aU, "cake");
      this.func_175047_a(Items.field_151104_aV, "bed");
      this.func_175047_a(Items.field_151107_aW, "repeater");
      this.func_175047_a(Items.field_151106_aX, "cookie");
      this.func_175047_a(Items.field_151097_aZ, "shears");
      this.func_175047_a(Items.field_151127_ba, "melon");
      this.func_175047_a(Items.field_151080_bb, "pumpkin_seeds");
      this.func_175047_a(Items.field_151081_bc, "melon_seeds");
      this.func_175047_a(Items.field_151082_bd, "beef");
      this.func_175047_a(Items.field_151083_be, "cooked_beef");
      this.func_175047_a(Items.field_151076_bf, "chicken");
      this.func_175047_a(Items.field_151077_bg, "cooked_chicken");
      this.func_175047_a(Items.field_179558_bo, "rabbit");
      this.func_175047_a(Items.field_179559_bp, "cooked_rabbit");
      this.func_175047_a(Items.field_179561_bm, "mutton");
      this.func_175047_a(Items.field_179557_bn, "cooked_mutton");
      this.func_175047_a(Items.field_179556_br, "rabbit_foot");
      this.func_175047_a(Items.field_179555_bs, "rabbit_hide");
      this.func_175047_a(Items.field_179560_bq, "rabbit_stew");
      this.func_175047_a(Items.field_151078_bh, "rotten_flesh");
      this.func_175047_a(Items.field_151079_bi, "ender_pearl");
      this.func_175047_a(Items.field_151072_bj, "blaze_rod");
      this.func_175047_a(Items.field_151073_bk, "ghast_tear");
      this.func_175047_a(Items.field_151074_bl, "gold_nugget");
      this.func_175047_a(Items.field_151075_bm, "nether_wart");
      this.field_175059_m.func_178080_a(Items.field_151068_bn, new ItemMeshDefinition() {
         public ModelResourceLocation func_178113_a(ItemStack p_178113_1_) {
            return ItemPotion.func_77831_g(p_178113_1_.func_77960_j())?new ModelResourceLocation("bottle_splash", "inventory"):new ModelResourceLocation("bottle_drinkable", "inventory");
         }
      });
      this.func_175047_a(Items.field_151069_bo, "glass_bottle");
      this.func_175047_a(Items.field_151070_bp, "spider_eye");
      this.func_175047_a(Items.field_151071_bq, "fermented_spider_eye");
      this.func_175047_a(Items.field_151065_br, "blaze_powder");
      this.func_175047_a(Items.field_151064_bs, "magma_cream");
      this.func_175047_a(Items.field_151067_bt, "brewing_stand");
      this.func_175047_a(Items.field_151066_bu, "cauldron");
      this.func_175047_a(Items.field_151061_bv, "ender_eye");
      this.func_175047_a(Items.field_151060_bw, "speckled_melon");
      this.field_175059_m.func_178080_a(Items.field_151063_bx, new ItemMeshDefinition() {
         public ModelResourceLocation func_178113_a(ItemStack p_178113_1_) {
            return new ModelResourceLocation("spawn_egg", "inventory");
         }
      });
      this.func_175047_a(Items.field_151062_by, "experience_bottle");
      this.func_175047_a(Items.field_151059_bz, "fire_charge");
      this.func_175047_a(Items.field_151099_bA, "writable_book");
      this.func_175047_a(Items.field_151166_bC, "emerald");
      this.func_175047_a(Items.field_151160_bD, "item_frame");
      this.func_175047_a(Items.field_151162_bE, "flower_pot");
      this.func_175047_a(Items.field_151172_bF, "carrot");
      this.func_175047_a(Items.field_151174_bG, "potato");
      this.func_175047_a(Items.field_151168_bH, "baked_potato");
      this.func_175047_a(Items.field_151170_bI, "poisonous_potato");
      this.func_175047_a(Items.field_151148_bJ, "map");
      this.func_175047_a(Items.field_151150_bK, "golden_carrot");
      this.func_175048_a(Items.field_151144_bL, 0, "skull_skeleton");
      this.func_175048_a(Items.field_151144_bL, 1, "skull_wither");
      this.func_175048_a(Items.field_151144_bL, 2, "skull_zombie");
      this.func_175048_a(Items.field_151144_bL, 3, "skull_char");
      this.func_175048_a(Items.field_151144_bL, 4, "skull_creeper");
      this.func_175047_a(Items.field_151146_bM, "carrot_on_a_stick");
      this.func_175047_a(Items.field_151156_bN, "nether_star");
      this.func_175047_a(Items.field_151158_bO, "pumpkin_pie");
      this.func_175047_a(Items.field_151154_bQ, "firework_charge");
      this.func_175047_a(Items.field_151132_bS, "comparator");
      this.func_175047_a(Items.field_151130_bT, "netherbrick");
      this.func_175047_a(Items.field_151128_bU, "quartz");
      this.func_175047_a(Items.field_151142_bV, "tnt_minecart");
      this.func_175047_a(Items.field_151140_bW, "hopper_minecart");
      this.func_175047_a(Items.field_179565_cj, "armor_stand");
      this.func_175047_a(Items.field_151138_bX, "iron_horse_armor");
      this.func_175047_a(Items.field_151136_bY, "golden_horse_armor");
      this.func_175047_a(Items.field_151125_bZ, "diamond_horse_armor");
      this.func_175047_a(Items.field_151058_ca, "lead");
      this.func_175047_a(Items.field_151057_cb, "name_tag");
      this.field_175059_m.func_178080_a(Items.field_179564_cE, new ItemMeshDefinition() {
         public ModelResourceLocation func_178113_a(ItemStack p_178113_1_) {
            return new ModelResourceLocation("banner", "inventory");
         }
      });
      this.func_175047_a(Items.field_151096_cd, "record_13");
      this.func_175047_a(Items.field_151093_ce, "record_cat");
      this.func_175047_a(Items.field_151094_cf, "record_blocks");
      this.func_175047_a(Items.field_151091_cg, "record_chirp");
      this.func_175047_a(Items.field_151092_ch, "record_far");
      this.func_175047_a(Items.field_151089_ci, "record_mall");
      this.func_175047_a(Items.field_151090_cj, "record_mellohi");
      this.func_175047_a(Items.field_151087_ck, "record_stal");
      this.func_175047_a(Items.field_151088_cl, "record_strad");
      this.func_175047_a(Items.field_151085_cm, "record_ward");
      this.func_175047_a(Items.field_151086_cn, "record_11");
      this.func_175047_a(Items.field_151084_co, "record_wait");
      this.func_175047_a(Items.field_179562_cC, "prismarine_shard");
      this.func_175047_a(Items.field_179563_cD, "prismarine_crystals");
      this.field_175059_m.func_178080_a(Items.field_151134_bR, new ItemMeshDefinition() {
         public ModelResourceLocation func_178113_a(ItemStack p_178113_1_) {
            return new ModelResourceLocation("enchanted_book", "inventory");
         }
      });
      this.field_175059_m.func_178080_a(Items.field_151098_aY, new ItemMeshDefinition() {
         public ModelResourceLocation func_178113_a(ItemStack p_178113_1_) {
            return new ModelResourceLocation("filled_map", "inventory");
         }
      });
      this.func_175031_a(Blocks.field_150483_bI, "command_block");
      this.func_175047_a(Items.field_151152_bP, "fireworks");
      this.func_175047_a(Items.field_151095_cc, "command_block_minecart");
      this.func_175031_a(Blocks.field_180401_cv, "barrier");
      this.func_175031_a(Blocks.field_150474_ac, "mob_spawner");
      this.func_175047_a(Items.field_151164_bB, "written_book");
      this.func_175029_a(Blocks.field_150420_aW, BlockHugeMushroom.EnumType.ALL_INSIDE.func_176896_a(), "brown_mushroom_block");
      this.func_175029_a(Blocks.field_150419_aX, BlockHugeMushroom.EnumType.ALL_INSIDE.func_176896_a(), "red_mushroom_block");
      this.func_175031_a(Blocks.field_150380_bt, "dragon_egg");
   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      this.field_175059_m.func_178085_b();
   }
}
