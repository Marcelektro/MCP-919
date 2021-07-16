package net.minecraft.client.renderer.tileentity;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntitySkullRenderer extends TileEntitySpecialRenderer<TileEntitySkull> {
   private static final ResourceLocation field_147537_c = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   private static final ResourceLocation field_147534_d = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
   private static final ResourceLocation field_147535_e = new ResourceLocation("textures/entity/zombie/zombie.png");
   private static final ResourceLocation field_147532_f = new ResourceLocation("textures/entity/creeper/creeper.png");
   public static TileEntitySkullRenderer field_147536_b;
   private final ModelSkeletonHead field_178467_h = new ModelSkeletonHead(0, 0, 64, 32);
   private final ModelSkeletonHead field_178468_i = new ModelHumanoidHead();

   public void func_180535_a(TileEntitySkull p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      EnumFacing enumfacing = EnumFacing.func_82600_a(p_180535_1_.func_145832_p() & 7);
      this.func_180543_a((float)p_180535_2_, (float)p_180535_4_, (float)p_180535_6_, enumfacing, (float)(p_180535_1_.func_145906_b() * 360) / 16.0F, p_180535_1_.func_145904_a(), p_180535_1_.func_152108_a(), p_180535_9_);
   }

   public void func_147497_a(TileEntityRendererDispatcher p_147497_1_) {
      super.func_147497_a(p_147497_1_);
      field_147536_b = this;
   }

   public void func_180543_a(float p_180543_1_, float p_180543_2_, float p_180543_3_, EnumFacing p_180543_4_, float p_180543_5_, int p_180543_6_, GameProfile p_180543_7_, int p_180543_8_) {
      ModelBase modelbase = this.field_178467_h;
      if(p_180543_8_ >= 0) {
         this.func_147499_a(field_178460_a[p_180543_8_]);
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(4.0F, 2.0F, 1.0F);
         GlStateManager.func_179109_b(0.0625F, 0.0625F, 0.0625F);
         GlStateManager.func_179128_n(5888);
      } else {
         switch(p_180543_6_) {
         case 0:
         default:
            this.func_147499_a(field_147537_c);
            break;
         case 1:
            this.func_147499_a(field_147534_d);
            break;
         case 2:
            this.func_147499_a(field_147535_e);
            modelbase = this.field_178468_i;
            break;
         case 3:
            modelbase = this.field_178468_i;
            ResourceLocation resourcelocation = DefaultPlayerSkin.func_177335_a();
            if(p_180543_7_ != null) {
               Minecraft minecraft = Minecraft.func_71410_x();
               Map<Type, MinecraftProfileTexture> map = minecraft.func_152342_ad().func_152788_a(p_180543_7_);
               if(map.containsKey(Type.SKIN)) {
                  resourcelocation = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
               } else {
                  UUID uuid = EntityPlayer.func_146094_a(p_180543_7_);
                  resourcelocation = DefaultPlayerSkin.func_177334_a(uuid);
               }
            }

            this.func_147499_a(resourcelocation);
            break;
         case 4:
            this.func_147499_a(field_147532_f);
         }
      }

      GlStateManager.func_179094_E();
      GlStateManager.func_179129_p();
      if(p_180543_4_ != EnumFacing.UP) {
         switch(p_180543_4_) {
         case NORTH:
            GlStateManager.func_179109_b(p_180543_1_ + 0.5F, p_180543_2_ + 0.25F, p_180543_3_ + 0.74F);
            break;
         case SOUTH:
            GlStateManager.func_179109_b(p_180543_1_ + 0.5F, p_180543_2_ + 0.25F, p_180543_3_ + 0.26F);
            p_180543_5_ = 180.0F;
            break;
         case WEST:
            GlStateManager.func_179109_b(p_180543_1_ + 0.74F, p_180543_2_ + 0.25F, p_180543_3_ + 0.5F);
            p_180543_5_ = 270.0F;
            break;
         case EAST:
         default:
            GlStateManager.func_179109_b(p_180543_1_ + 0.26F, p_180543_2_ + 0.25F, p_180543_3_ + 0.5F);
            p_180543_5_ = 90.0F;
         }
      } else {
         GlStateManager.func_179109_b(p_180543_1_ + 0.5F, p_180543_2_, p_180543_3_ + 0.5F);
      }

      float f = 0.0625F;
      GlStateManager.func_179091_B();
      GlStateManager.func_179152_a(-1.0F, -1.0F, 1.0F);
      GlStateManager.func_179141_d();
      modelbase.func_78088_a((Entity)null, 0.0F, 0.0F, 0.0F, p_180543_5_, 0.0F, f);
      GlStateManager.func_179121_F();
      if(p_180543_8_ >= 0) {
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5888);
      }

   }
}
