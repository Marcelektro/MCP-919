package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.IIconCreator;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureMap extends AbstractTexture implements ITickableTextureObject {
   private static final Logger field_147635_d = LogManager.getLogger();
   public static final ResourceLocation field_174945_f = new ResourceLocation("missingno");
   public static final ResourceLocation field_110575_b = new ResourceLocation("textures/atlas/blocks.png");
   private final List<TextureAtlasSprite> field_94258_i;
   private final Map<String, TextureAtlasSprite> field_110574_e;
   private final Map<String, TextureAtlasSprite> field_94252_e;
   private final String field_94254_c;
   private final IIconCreator field_174946_m;
   private int field_147636_j;
   private final TextureAtlasSprite field_94249_f;

   public TextureMap(String p_i46099_1_) {
      this(p_i46099_1_, (IIconCreator)null);
   }

   public TextureMap(String p_i46100_1_, IIconCreator p_i46100_2_) {
      this.field_94258_i = Lists.<TextureAtlasSprite>newArrayList();
      this.field_110574_e = Maps.<String, TextureAtlasSprite>newHashMap();
      this.field_94252_e = Maps.<String, TextureAtlasSprite>newHashMap();
      this.field_94249_f = new TextureAtlasSprite("missingno");
      this.field_94254_c = p_i46100_1_;
      this.field_174946_m = p_i46100_2_;
   }

   private void func_110569_e() {
      int[] aint = TextureUtil.field_110999_b;
      this.field_94249_f.func_110966_b(16);
      this.field_94249_f.func_110969_c(16);
      int[][] aint1 = new int[this.field_147636_j + 1][];
      aint1[0] = aint;
      this.field_94249_f.func_110968_a(Lists.newArrayList(new int[][][]{aint1}));
   }

   public void func_110551_a(IResourceManager p_110551_1_) throws IOException {
      if(this.field_174946_m != null) {
         this.func_174943_a(p_110551_1_, this.field_174946_m);
      }

   }

   public void func_174943_a(IResourceManager p_174943_1_, IIconCreator p_174943_2_) {
      this.field_110574_e.clear();
      p_174943_2_.func_177059_a(this);
      this.func_110569_e();
      this.func_147631_c();
      this.func_110571_b(p_174943_1_);
   }

   public void func_110571_b(IResourceManager p_110571_1_) {
      int i = Minecraft.func_71369_N();
      Stitcher stitcher = new Stitcher(i, i, true, 0, this.field_147636_j);
      this.field_94252_e.clear();
      this.field_94258_i.clear();
      int j = Integer.MAX_VALUE;
      int k = 1 << this.field_147636_j;

      for(Entry<String, TextureAtlasSprite> entry : this.field_110574_e.entrySet()) {
         TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)entry.getValue();
         ResourceLocation resourcelocation = new ResourceLocation(textureatlassprite.func_94215_i());
         ResourceLocation resourcelocation1 = this.func_147634_a(resourcelocation, 0);

         try {
            IResource iresource = p_110571_1_.func_110536_a(resourcelocation1);
            BufferedImage[] abufferedimage = new BufferedImage[1 + this.field_147636_j];
            abufferedimage[0] = TextureUtil.func_177053_a(iresource.func_110527_b());
            TextureMetadataSection texturemetadatasection = (TextureMetadataSection)iresource.func_110526_a("texture");
            if(texturemetadatasection != null) {
               List<Integer> list = texturemetadatasection.func_148535_c();
               if(!list.isEmpty()) {
                  int l = abufferedimage[0].getWidth();
                  int i1 = abufferedimage[0].getHeight();
                  if(MathHelper.func_151236_b(l) != l || MathHelper.func_151236_b(i1) != i1) {
                     throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                  }
               }

               Iterator iterator = list.iterator();

               while(iterator.hasNext()) {
                  int i2 = ((Integer)iterator.next()).intValue();
                  if(i2 > 0 && i2 < abufferedimage.length - 1 && abufferedimage[i2] == null) {
                     ResourceLocation resourcelocation2 = this.func_147634_a(resourcelocation, i2);

                     try {
                        abufferedimage[i2] = TextureUtil.func_177053_a(p_110571_1_.func_110536_a(resourcelocation2).func_110527_b());
                     } catch (IOException ioexception) {
                        field_147635_d.error("Unable to load miplevel {} from: {}", new Object[]{Integer.valueOf(i2), resourcelocation2, ioexception});
                     }
                  }
               }
            }

            AnimationMetadataSection animationmetadatasection = (AnimationMetadataSection)iresource.func_110526_a("animation");
            textureatlassprite.func_180598_a(abufferedimage, animationmetadatasection);
         } catch (RuntimeException runtimeexception) {
            field_147635_d.error((String)("Unable to parse metadata from " + resourcelocation1), (Throwable)runtimeexception);
            continue;
         } catch (IOException ioexception1) {
            field_147635_d.error((String)("Using missing texture, unable to load " + resourcelocation1), (Throwable)ioexception1);
            continue;
         }

         j = Math.min(j, Math.min(textureatlassprite.func_94211_a(), textureatlassprite.func_94216_b()));
         int l1 = Math.min(Integer.lowestOneBit(textureatlassprite.func_94211_a()), Integer.lowestOneBit(textureatlassprite.func_94216_b()));
         if(l1 < k) {
            field_147635_d.warn("Texture {} with size {}x{} limits mip level from {} to {}", new Object[]{resourcelocation1, Integer.valueOf(textureatlassprite.func_94211_a()), Integer.valueOf(textureatlassprite.func_94216_b()), Integer.valueOf(MathHelper.func_151239_c(k)), Integer.valueOf(MathHelper.func_151239_c(l1))});
            k = l1;
         }

         stitcher.func_110934_a(textureatlassprite);
      }

      int j1 = Math.min(j, k);
      int k1 = MathHelper.func_151239_c(j1);
      if(k1 < this.field_147636_j) {
         field_147635_d.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", new Object[]{this.field_94254_c, Integer.valueOf(this.field_147636_j), Integer.valueOf(k1), Integer.valueOf(j1)});
         this.field_147636_j = k1;
      }

      for(final TextureAtlasSprite textureatlassprite1 : this.field_110574_e.values()) {
         try {
            textureatlassprite1.func_147963_d(this.field_147636_j);
         } catch (Throwable throwable1) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable1, "Applying mipmap");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Sprite being mipmapped");
            crashreportcategory.func_71500_a("Sprite name", new Callable<String>() {
               public String call() throws Exception {
                  return textureatlassprite1.func_94215_i();
               }
            });
            crashreportcategory.func_71500_a("Sprite size", new Callable<String>() {
               public String call() throws Exception {
                  return textureatlassprite1.func_94211_a() + " x " + textureatlassprite1.func_94216_b();
               }
            });
            crashreportcategory.func_71500_a("Sprite frames", new Callable<String>() {
               public String call() throws Exception {
                  return textureatlassprite1.func_110970_k() + " frames";
               }
            });
            crashreportcategory.func_71507_a("Mipmap levels", Integer.valueOf(this.field_147636_j));
            throw new ReportedException(crashreport);
         }
      }

      this.field_94249_f.func_147963_d(this.field_147636_j);
      stitcher.func_110934_a(this.field_94249_f);

      try {
         stitcher.func_94305_f();
      } catch (StitcherException stitcherexception) {
         throw stitcherexception;
      }

      field_147635_d.info("Created: {}x{} {}-atlas", new Object[]{Integer.valueOf(stitcher.func_110935_a()), Integer.valueOf(stitcher.func_110936_b()), this.field_94254_c});
      TextureUtil.func_180600_a(this.func_110552_b(), this.field_147636_j, stitcher.func_110935_a(), stitcher.func_110936_b());
      Map<String, TextureAtlasSprite> map = Maps.<String, TextureAtlasSprite>newHashMap(this.field_110574_e);

      for(TextureAtlasSprite textureatlassprite2 : stitcher.func_94309_g()) {
         String s = textureatlassprite2.func_94215_i();
         map.remove(s);
         this.field_94252_e.put(s, textureatlassprite2);

         try {
            TextureUtil.func_147955_a(textureatlassprite2.func_147965_a(0), textureatlassprite2.func_94211_a(), textureatlassprite2.func_94216_b(), textureatlassprite2.func_130010_a(), textureatlassprite2.func_110967_i(), false, false);
         } catch (Throwable throwable) {
            CrashReport crashreport1 = CrashReport.func_85055_a(throwable, "Stitching texture atlas");
            CrashReportCategory crashreportcategory1 = crashreport1.func_85058_a("Texture being stitched together");
            crashreportcategory1.func_71507_a("Atlas path", this.field_94254_c);
            crashreportcategory1.func_71507_a("Sprite", textureatlassprite2);
            throw new ReportedException(crashreport1);
         }

         if(textureatlassprite2.func_130098_m()) {
            this.field_94258_i.add(textureatlassprite2);
         }
      }

      for(TextureAtlasSprite textureatlassprite3 : map.values()) {
         textureatlassprite3.func_94217_a(this.field_94249_f);
      }

   }

   private ResourceLocation func_147634_a(ResourceLocation p_147634_1_, int p_147634_2_) {
      return p_147634_2_ == 0?new ResourceLocation(p_147634_1_.func_110624_b(), String.format("%s/%s%s", new Object[]{this.field_94254_c, p_147634_1_.func_110623_a(), ".png"})):new ResourceLocation(p_147634_1_.func_110624_b(), String.format("%s/mipmaps/%s.%d%s", new Object[]{this.field_94254_c, p_147634_1_.func_110623_a(), Integer.valueOf(p_147634_2_), ".png"}));
   }

   public TextureAtlasSprite func_110572_b(String p_110572_1_) {
      TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)this.field_94252_e.get(p_110572_1_);
      if(textureatlassprite == null) {
         textureatlassprite = this.field_94249_f;
      }

      return textureatlassprite;
   }

   public void func_94248_c() {
      TextureUtil.func_94277_a(this.func_110552_b());

      for(TextureAtlasSprite textureatlassprite : this.field_94258_i) {
         textureatlassprite.func_94219_l();
      }

   }

   public TextureAtlasSprite func_174942_a(ResourceLocation p_174942_1_) {
      if(p_174942_1_ == null) {
         throw new IllegalArgumentException("Location cannot be null!");
      } else {
         TextureAtlasSprite textureatlassprite = (TextureAtlasSprite)this.field_110574_e.get(p_174942_1_);
         if(textureatlassprite == null) {
            textureatlassprite = TextureAtlasSprite.func_176604_a(p_174942_1_);
            this.field_110574_e.put(p_174942_1_.toString(), textureatlassprite);
         }

         return textureatlassprite;
      }
   }

   public void func_110550_d() {
      this.func_94248_c();
   }

   public void func_147633_a(int p_147633_1_) {
      this.field_147636_j = p_147633_1_;
   }

   public TextureAtlasSprite func_174944_f() {
      return this.field_94249_f;
   }
}
