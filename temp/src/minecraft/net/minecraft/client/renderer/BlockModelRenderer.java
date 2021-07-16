package net.minecraft.client.renderer;

import java.util.BitSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;

public class BlockModelRenderer {
   public boolean func_178259_a(IBlockAccess p_178259_1_, IBakedModel p_178259_2_, IBlockState p_178259_3_, BlockPos p_178259_4_, WorldRenderer p_178259_5_) {
      Block block = p_178259_3_.func_177230_c();
      block.func_180654_a(p_178259_1_, p_178259_4_);
      return this.func_178267_a(p_178259_1_, p_178259_2_, p_178259_3_, p_178259_4_, p_178259_5_, true);
   }

   public boolean func_178267_a(IBlockAccess p_178267_1_, IBakedModel p_178267_2_, IBlockState p_178267_3_, BlockPos p_178267_4_, WorldRenderer p_178267_5_, boolean p_178267_6_) {
      boolean flag = Minecraft.func_71379_u() && p_178267_3_.func_177230_c().func_149750_m() == 0 && p_178267_2_.func_177555_b();

      try {
         Block block = p_178267_3_.func_177230_c();
         return flag?this.func_178265_a(p_178267_1_, p_178267_2_, block, p_178267_4_, p_178267_5_, p_178267_6_):this.func_178258_b(p_178267_1_, p_178267_2_, block, p_178267_4_, p_178267_5_, p_178267_6_);
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Tesselating block model");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Block model being tesselated");
         CrashReportCategory.func_175750_a(crashreportcategory, p_178267_4_, p_178267_3_);
         crashreportcategory.func_71507_a("Using AO", Boolean.valueOf(flag));
         throw new ReportedException(crashreport);
      }
   }

   public boolean func_178265_a(IBlockAccess p_178265_1_, IBakedModel p_178265_2_, Block p_178265_3_, BlockPos p_178265_4_, WorldRenderer p_178265_5_, boolean p_178265_6_) {
      boolean flag = false;
      float[] afloat = new float[EnumFacing.values().length * 2];
      BitSet bitset = new BitSet(3);
      BlockModelRenderer.AmbientOcclusionFace blockmodelrenderer$ambientocclusionface = new BlockModelRenderer.AmbientOcclusionFace();

      for(EnumFacing enumfacing : EnumFacing.values()) {
         List<BakedQuad> list = p_178265_2_.func_177551_a(enumfacing);
         if(!list.isEmpty()) {
            BlockPos blockpos = p_178265_4_.func_177972_a(enumfacing);
            if(!p_178265_6_ || p_178265_3_.func_176225_a(p_178265_1_, blockpos, enumfacing)) {
               this.func_178263_a(p_178265_1_, p_178265_3_, p_178265_4_, p_178265_5_, list, afloat, bitset, blockmodelrenderer$ambientocclusionface);
               flag = true;
            }
         }
      }

      List<BakedQuad> list1 = p_178265_2_.func_177550_a();
      if(list1.size() > 0) {
         this.func_178263_a(p_178265_1_, p_178265_3_, p_178265_4_, p_178265_5_, list1, afloat, bitset, blockmodelrenderer$ambientocclusionface);
         flag = true;
      }

      return flag;
   }

   public boolean func_178258_b(IBlockAccess p_178258_1_, IBakedModel p_178258_2_, Block p_178258_3_, BlockPos p_178258_4_, WorldRenderer p_178258_5_, boolean p_178258_6_) {
      boolean flag = false;
      BitSet bitset = new BitSet(3);

      for(EnumFacing enumfacing : EnumFacing.values()) {
         List<BakedQuad> list = p_178258_2_.func_177551_a(enumfacing);
         if(!list.isEmpty()) {
            BlockPos blockpos = p_178258_4_.func_177972_a(enumfacing);
            if(!p_178258_6_ || p_178258_3_.func_176225_a(p_178258_1_, blockpos, enumfacing)) {
               int i = p_178258_3_.func_176207_c(p_178258_1_, blockpos);
               this.func_178260_a(p_178258_1_, p_178258_3_, p_178258_4_, enumfacing, i, false, p_178258_5_, list, bitset);
               flag = true;
            }
         }
      }

      List<BakedQuad> list1 = p_178258_2_.func_177550_a();
      if(list1.size() > 0) {
         this.func_178260_a(p_178258_1_, p_178258_3_, p_178258_4_, (EnumFacing)null, -1, true, p_178258_5_, list1, bitset);
         flag = true;
      }

      return flag;
   }

   private void func_178263_a(IBlockAccess p_178263_1_, Block p_178263_2_, BlockPos p_178263_3_, WorldRenderer p_178263_4_, List<BakedQuad> p_178263_5_, float[] p_178263_6_, BitSet p_178263_7_, BlockModelRenderer.AmbientOcclusionFace p_178263_8_) {
      double d0 = (double)p_178263_3_.func_177958_n();
      double d1 = (double)p_178263_3_.func_177956_o();
      double d2 = (double)p_178263_3_.func_177952_p();
      Block.EnumOffsetType block$enumoffsettype = p_178263_2_.func_176218_Q();
      if(block$enumoffsettype != Block.EnumOffsetType.NONE) {
         long i = MathHelper.func_180186_a(p_178263_3_);
         d0 += ((double)((float)(i >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
         d2 += ((double)((float)(i >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
         if(block$enumoffsettype == Block.EnumOffsetType.XYZ) {
            d1 += ((double)((float)(i >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
         }
      }

      for(BakedQuad bakedquad : p_178263_5_) {
         this.func_178261_a(p_178263_2_, bakedquad.func_178209_a(), bakedquad.func_178210_d(), p_178263_6_, p_178263_7_);
         p_178263_8_.func_178204_a(p_178263_1_, p_178263_2_, p_178263_3_, bakedquad.func_178210_d(), p_178263_6_, p_178263_7_);
         p_178263_4_.func_178981_a(bakedquad.func_178209_a());
         p_178263_4_.func_178962_a(p_178263_8_.field_178207_c[0], p_178263_8_.field_178207_c[1], p_178263_8_.field_178207_c[2], p_178263_8_.field_178207_c[3]);
         if(bakedquad.func_178212_b()) {
            int j = p_178263_2_.func_180662_a(p_178263_1_, p_178263_3_, bakedquad.func_178211_c());
            if(EntityRenderer.field_78517_a) {
               j = TextureUtil.func_177054_c(j);
            }

            float f = (float)(j >> 16 & 255) / 255.0F;
            float f1 = (float)(j >> 8 & 255) / 255.0F;
            float f2 = (float)(j & 255) / 255.0F;
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[0] * f, p_178263_8_.field_178206_b[0] * f1, p_178263_8_.field_178206_b[0] * f2, 4);
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[1] * f, p_178263_8_.field_178206_b[1] * f1, p_178263_8_.field_178206_b[1] * f2, 3);
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[2] * f, p_178263_8_.field_178206_b[2] * f1, p_178263_8_.field_178206_b[2] * f2, 2);
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[3] * f, p_178263_8_.field_178206_b[3] * f1, p_178263_8_.field_178206_b[3] * f2, 1);
         } else {
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[0], p_178263_8_.field_178206_b[0], p_178263_8_.field_178206_b[0], 4);
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[1], p_178263_8_.field_178206_b[1], p_178263_8_.field_178206_b[1], 3);
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[2], p_178263_8_.field_178206_b[2], p_178263_8_.field_178206_b[2], 2);
            p_178263_4_.func_178978_a(p_178263_8_.field_178206_b[3], p_178263_8_.field_178206_b[3], p_178263_8_.field_178206_b[3], 1);
         }

         p_178263_4_.func_178987_a(d0, d1, d2);
      }

   }

   private void func_178261_a(Block p_178261_1_, int[] p_178261_2_, EnumFacing p_178261_3_, float[] p_178261_4_, BitSet p_178261_5_) {
      float f = 32.0F;
      float f1 = 32.0F;
      float f2 = 32.0F;
      float f3 = -32.0F;
      float f4 = -32.0F;
      float f5 = -32.0F;

      for(int i = 0; i < 4; ++i) {
         float f6 = Float.intBitsToFloat(p_178261_2_[i * 7]);
         float f7 = Float.intBitsToFloat(p_178261_2_[i * 7 + 1]);
         float f8 = Float.intBitsToFloat(p_178261_2_[i * 7 + 2]);
         f = Math.min(f, f6);
         f1 = Math.min(f1, f7);
         f2 = Math.min(f2, f8);
         f3 = Math.max(f3, f6);
         f4 = Math.max(f4, f7);
         f5 = Math.max(f5, f8);
      }

      if(p_178261_4_ != null) {
         p_178261_4_[EnumFacing.WEST.func_176745_a()] = f;
         p_178261_4_[EnumFacing.EAST.func_176745_a()] = f3;
         p_178261_4_[EnumFacing.DOWN.func_176745_a()] = f1;
         p_178261_4_[EnumFacing.UP.func_176745_a()] = f4;
         p_178261_4_[EnumFacing.NORTH.func_176745_a()] = f2;
         p_178261_4_[EnumFacing.SOUTH.func_176745_a()] = f5;
         p_178261_4_[EnumFacing.WEST.func_176745_a() + EnumFacing.values().length] = 1.0F - f;
         p_178261_4_[EnumFacing.EAST.func_176745_a() + EnumFacing.values().length] = 1.0F - f3;
         p_178261_4_[EnumFacing.DOWN.func_176745_a() + EnumFacing.values().length] = 1.0F - f1;
         p_178261_4_[EnumFacing.UP.func_176745_a() + EnumFacing.values().length] = 1.0F - f4;
         p_178261_4_[EnumFacing.NORTH.func_176745_a() + EnumFacing.values().length] = 1.0F - f2;
         p_178261_4_[EnumFacing.SOUTH.func_176745_a() + EnumFacing.values().length] = 1.0F - f5;
      }

      float f9 = 1.0E-4F;
      float f10 = 0.9999F;
      switch(p_178261_3_) {
      case DOWN:
         p_178261_5_.set(1, f >= 1.0E-4F || f2 >= 1.0E-4F || f3 <= 0.9999F || f5 <= 0.9999F);
         p_178261_5_.set(0, (f1 < 1.0E-4F || p_178261_1_.func_149686_d()) && f1 == f4);
         break;
      case UP:
         p_178261_5_.set(1, f >= 1.0E-4F || f2 >= 1.0E-4F || f3 <= 0.9999F || f5 <= 0.9999F);
         p_178261_5_.set(0, (f4 > 0.9999F || p_178261_1_.func_149686_d()) && f1 == f4);
         break;
      case NORTH:
         p_178261_5_.set(1, f >= 1.0E-4F || f1 >= 1.0E-4F || f3 <= 0.9999F || f4 <= 0.9999F);
         p_178261_5_.set(0, (f2 < 1.0E-4F || p_178261_1_.func_149686_d()) && f2 == f5);
         break;
      case SOUTH:
         p_178261_5_.set(1, f >= 1.0E-4F || f1 >= 1.0E-4F || f3 <= 0.9999F || f4 <= 0.9999F);
         p_178261_5_.set(0, (f5 > 0.9999F || p_178261_1_.func_149686_d()) && f2 == f5);
         break;
      case WEST:
         p_178261_5_.set(1, f1 >= 1.0E-4F || f2 >= 1.0E-4F || f4 <= 0.9999F || f5 <= 0.9999F);
         p_178261_5_.set(0, (f < 1.0E-4F || p_178261_1_.func_149686_d()) && f == f3);
         break;
      case EAST:
         p_178261_5_.set(1, f1 >= 1.0E-4F || f2 >= 1.0E-4F || f4 <= 0.9999F || f5 <= 0.9999F);
         p_178261_5_.set(0, (f3 > 0.9999F || p_178261_1_.func_149686_d()) && f == f3);
      }

   }

   private void func_178260_a(IBlockAccess p_178260_1_, Block p_178260_2_, BlockPos p_178260_3_, EnumFacing p_178260_4_, int p_178260_5_, boolean p_178260_6_, WorldRenderer p_178260_7_, List<BakedQuad> p_178260_8_, BitSet p_178260_9_) {
      double d0 = (double)p_178260_3_.func_177958_n();
      double d1 = (double)p_178260_3_.func_177956_o();
      double d2 = (double)p_178260_3_.func_177952_p();
      Block.EnumOffsetType block$enumoffsettype = p_178260_2_.func_176218_Q();
      if(block$enumoffsettype != Block.EnumOffsetType.NONE) {
         int i = p_178260_3_.func_177958_n();
         int j = p_178260_3_.func_177952_p();
         long k = (long)(i * 3129871) ^ (long)j * 116129781L;
         k = k * k * 42317861L + k * 11L;
         d0 += ((double)((float)(k >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
         d2 += ((double)((float)(k >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
         if(block$enumoffsettype == Block.EnumOffsetType.XYZ) {
            d1 += ((double)((float)(k >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
         }
      }

      for(BakedQuad bakedquad : p_178260_8_) {
         if(p_178260_6_) {
            this.func_178261_a(p_178260_2_, bakedquad.func_178209_a(), bakedquad.func_178210_d(), (float[])null, p_178260_9_);
            p_178260_5_ = p_178260_9_.get(0)?p_178260_2_.func_176207_c(p_178260_1_, p_178260_3_.func_177972_a(bakedquad.func_178210_d())):p_178260_2_.func_176207_c(p_178260_1_, p_178260_3_);
         }

         p_178260_7_.func_178981_a(bakedquad.func_178209_a());
         p_178260_7_.func_178962_a(p_178260_5_, p_178260_5_, p_178260_5_, p_178260_5_);
         if(bakedquad.func_178212_b()) {
            int l = p_178260_2_.func_180662_a(p_178260_1_, p_178260_3_, bakedquad.func_178211_c());
            if(EntityRenderer.field_78517_a) {
               l = TextureUtil.func_177054_c(l);
            }

            float f = (float)(l >> 16 & 255) / 255.0F;
            float f1 = (float)(l >> 8 & 255) / 255.0F;
            float f2 = (float)(l & 255) / 255.0F;
            p_178260_7_.func_178978_a(f, f1, f2, 4);
            p_178260_7_.func_178978_a(f, f1, f2, 3);
            p_178260_7_.func_178978_a(f, f1, f2, 2);
            p_178260_7_.func_178978_a(f, f1, f2, 1);
         }

         p_178260_7_.func_178987_a(d0, d1, d2);
      }

   }

   public void func_178262_a(IBakedModel p_178262_1_, float p_178262_2_, float p_178262_3_, float p_178262_4_, float p_178262_5_) {
      for(EnumFacing enumfacing : EnumFacing.values()) {
         this.func_178264_a(p_178262_2_, p_178262_3_, p_178262_4_, p_178262_5_, p_178262_1_.func_177551_a(enumfacing));
      }

      this.func_178264_a(p_178262_2_, p_178262_3_, p_178262_4_, p_178262_5_, p_178262_1_.func_177550_a());
   }

   public void func_178266_a(IBakedModel p_178266_1_, IBlockState p_178266_2_, float p_178266_3_, boolean p_178266_4_) {
      Block block = p_178266_2_.func_177230_c();
      block.func_149683_g();
      GlStateManager.func_179114_b(90.0F, 0.0F, 1.0F, 0.0F);
      int i = block.func_180644_h(block.func_176217_b(p_178266_2_));
      if(EntityRenderer.field_78517_a) {
         i = TextureUtil.func_177054_c(i);
      }

      float f = (float)(i >> 16 & 255) / 255.0F;
      float f1 = (float)(i >> 8 & 255) / 255.0F;
      float f2 = (float)(i & 255) / 255.0F;
      if(!p_178266_4_) {
         GlStateManager.func_179131_c(p_178266_3_, p_178266_3_, p_178266_3_, 1.0F);
      }

      this.func_178262_a(p_178266_1_, p_178266_3_, f, f1, f2);
   }

   private void func_178264_a(float p_178264_1_, float p_178264_2_, float p_178264_3_, float p_178264_4_, List<BakedQuad> p_178264_5_) {
      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();

      for(BakedQuad bakedquad : p_178264_5_) {
         worldrenderer.func_181668_a(7, DefaultVertexFormats.field_176599_b);
         worldrenderer.func_178981_a(bakedquad.func_178209_a());
         if(bakedquad.func_178212_b()) {
            worldrenderer.func_178990_f(p_178264_2_ * p_178264_1_, p_178264_3_ * p_178264_1_, p_178264_4_ * p_178264_1_);
         } else {
            worldrenderer.func_178990_f(p_178264_1_, p_178264_1_, p_178264_1_);
         }

         Vec3i vec3i = bakedquad.func_178210_d().func_176730_m();
         worldrenderer.func_178975_e((float)vec3i.func_177958_n(), (float)vec3i.func_177956_o(), (float)vec3i.func_177952_p());
         tessellator.func_78381_a();
      }

   }

   class AmbientOcclusionFace {
      private final float[] field_178206_b = new float[4];
      private final int[] field_178207_c = new int[4];

      public void func_178204_a(IBlockAccess p_178204_1_, Block p_178204_2_, BlockPos p_178204_3_, EnumFacing p_178204_4_, float[] p_178204_5_, BitSet p_178204_6_) {
         BlockPos blockpos = p_178204_6_.get(0)?p_178204_3_.func_177972_a(p_178204_4_):p_178204_3_;
         BlockModelRenderer.EnumNeighborInfo blockmodelrenderer$enumneighborinfo = BlockModelRenderer.EnumNeighborInfo.func_178273_a(p_178204_4_);
         BlockPos blockpos1 = blockpos.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[0]);
         BlockPos blockpos2 = blockpos.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[1]);
         BlockPos blockpos3 = blockpos.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[2]);
         BlockPos blockpos4 = blockpos.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[3]);
         int i = p_178204_2_.func_176207_c(p_178204_1_, blockpos1);
         int j = p_178204_2_.func_176207_c(p_178204_1_, blockpos2);
         int k = p_178204_2_.func_176207_c(p_178204_1_, blockpos3);
         int l = p_178204_2_.func_176207_c(p_178204_1_, blockpos4);
         float f = p_178204_1_.func_180495_p(blockpos1).func_177230_c().func_149685_I();
         float f1 = p_178204_1_.func_180495_p(blockpos2).func_177230_c().func_149685_I();
         float f2 = p_178204_1_.func_180495_p(blockpos3).func_177230_c().func_149685_I();
         float f3 = p_178204_1_.func_180495_p(blockpos4).func_177230_c().func_149685_I();
         boolean flag = p_178204_1_.func_180495_p(blockpos1.func_177972_a(p_178204_4_)).func_177230_c().func_149751_l();
         boolean flag1 = p_178204_1_.func_180495_p(blockpos2.func_177972_a(p_178204_4_)).func_177230_c().func_149751_l();
         boolean flag2 = p_178204_1_.func_180495_p(blockpos3.func_177972_a(p_178204_4_)).func_177230_c().func_149751_l();
         boolean flag3 = p_178204_1_.func_180495_p(blockpos4.func_177972_a(p_178204_4_)).func_177230_c().func_149751_l();
         float f4;
         int i1;
         if(!flag2 && !flag) {
            f4 = f;
            i1 = i;
         } else {
            BlockPos blockpos5 = blockpos1.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[2]);
            f4 = p_178204_1_.func_180495_p(blockpos5).func_177230_c().func_149685_I();
            i1 = p_178204_2_.func_176207_c(p_178204_1_, blockpos5);
         }

         float f5;
         int j1;
         if(!flag3 && !flag) {
            f5 = f;
            j1 = i;
         } else {
            BlockPos blockpos6 = blockpos1.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[3]);
            f5 = p_178204_1_.func_180495_p(blockpos6).func_177230_c().func_149685_I();
            j1 = p_178204_2_.func_176207_c(p_178204_1_, blockpos6);
         }

         float f6;
         int k1;
         if(!flag2 && !flag1) {
            f6 = f1;
            k1 = j;
         } else {
            BlockPos blockpos7 = blockpos2.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[2]);
            f6 = p_178204_1_.func_180495_p(blockpos7).func_177230_c().func_149685_I();
            k1 = p_178204_2_.func_176207_c(p_178204_1_, blockpos7);
         }

         float f7;
         int l1;
         if(!flag3 && !flag1) {
            f7 = f1;
            l1 = j;
         } else {
            BlockPos blockpos8 = blockpos2.func_177972_a(blockmodelrenderer$enumneighborinfo.field_178276_g[3]);
            f7 = p_178204_1_.func_180495_p(blockpos8).func_177230_c().func_149685_I();
            l1 = p_178204_2_.func_176207_c(p_178204_1_, blockpos8);
         }

         int i3 = p_178204_2_.func_176207_c(p_178204_1_, p_178204_3_);
         if(p_178204_6_.get(0) || !p_178204_1_.func_180495_p(p_178204_3_.func_177972_a(p_178204_4_)).func_177230_c().func_149662_c()) {
            i3 = p_178204_2_.func_176207_c(p_178204_1_, p_178204_3_.func_177972_a(p_178204_4_));
         }

         float f8 = p_178204_6_.get(0)?p_178204_1_.func_180495_p(blockpos).func_177230_c().func_149685_I():p_178204_1_.func_180495_p(p_178204_3_).func_177230_c().func_149685_I();
         BlockModelRenderer.VertexTranslations blockmodelrenderer$vertextranslations = BlockModelRenderer.VertexTranslations.func_178184_a(p_178204_4_);
         if(p_178204_6_.get(1) && blockmodelrenderer$enumneighborinfo.field_178289_i) {
            float f29 = (f3 + f + f5 + f8) * 0.25F;
            float f30 = (f2 + f + f4 + f8) * 0.25F;
            float f31 = (f2 + f1 + f6 + f8) * 0.25F;
            float f32 = (f3 + f1 + f7 + f8) * 0.25F;
            float f13 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[0].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[1].field_178229_m];
            float f14 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[2].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[3].field_178229_m];
            float f15 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[4].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[5].field_178229_m];
            float f16 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[6].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178286_j[7].field_178229_m];
            float f17 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[0].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[1].field_178229_m];
            float f18 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[2].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[3].field_178229_m];
            float f19 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[4].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[5].field_178229_m];
            float f20 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[6].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178287_k[7].field_178229_m];
            float f21 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[0].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[1].field_178229_m];
            float f22 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[2].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[3].field_178229_m];
            float f23 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[4].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[5].field_178229_m];
            float f24 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[6].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178284_l[7].field_178229_m];
            float f25 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[0].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[1].field_178229_m];
            float f26 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[2].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[3].field_178229_m];
            float f27 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[4].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[5].field_178229_m];
            float f28 = p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[6].field_178229_m] * p_178204_5_[blockmodelrenderer$enumneighborinfo.field_178285_m[7].field_178229_m];
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178191_g] = f29 * f13 + f30 * f14 + f31 * f15 + f32 * f16;
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178200_h] = f29 * f17 + f30 * f18 + f31 * f19 + f32 * f20;
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178201_i] = f29 * f21 + f30 * f22 + f31 * f23 + f32 * f24;
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178198_j] = f29 * f25 + f30 * f26 + f31 * f27 + f32 * f28;
            int i2 = this.func_147778_a(l, i, j1, i3);
            int j2 = this.func_147778_a(k, i, i1, i3);
            int k2 = this.func_147778_a(k, j, k1, i3);
            int l2 = this.func_147778_a(l, j, l1, i3);
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178191_g] = this.func_178203_a(i2, j2, k2, l2, f13, f14, f15, f16);
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178200_h] = this.func_178203_a(i2, j2, k2, l2, f17, f18, f19, f20);
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178201_i] = this.func_178203_a(i2, j2, k2, l2, f21, f22, f23, f24);
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178198_j] = this.func_178203_a(i2, j2, k2, l2, f25, f26, f27, f28);
         } else {
            float f9 = (f3 + f + f5 + f8) * 0.25F;
            float f10 = (f2 + f + f4 + f8) * 0.25F;
            float f11 = (f2 + f1 + f6 + f8) * 0.25F;
            float f12 = (f3 + f1 + f7 + f8) * 0.25F;
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178191_g] = this.func_147778_a(l, i, j1, i3);
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178200_h] = this.func_147778_a(k, i, i1, i3);
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178201_i] = this.func_147778_a(k, j, k1, i3);
            this.field_178207_c[blockmodelrenderer$vertextranslations.field_178198_j] = this.func_147778_a(l, j, l1, i3);
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178191_g] = f9;
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178200_h] = f10;
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178201_i] = f11;
            this.field_178206_b[blockmodelrenderer$vertextranslations.field_178198_j] = f12;
         }

      }

      private int func_147778_a(int p_147778_1_, int p_147778_2_, int p_147778_3_, int p_147778_4_) {
         if(p_147778_1_ == 0) {
            p_147778_1_ = p_147778_4_;
         }

         if(p_147778_2_ == 0) {
            p_147778_2_ = p_147778_4_;
         }

         if(p_147778_3_ == 0) {
            p_147778_3_ = p_147778_4_;
         }

         return p_147778_1_ + p_147778_2_ + p_147778_3_ + p_147778_4_ >> 2 & 16711935;
      }

      private int func_178203_a(int p_178203_1_, int p_178203_2_, int p_178203_3_, int p_178203_4_, float p_178203_5_, float p_178203_6_, float p_178203_7_, float p_178203_8_) {
         int i = (int)((float)(p_178203_1_ >> 16 & 255) * p_178203_5_ + (float)(p_178203_2_ >> 16 & 255) * p_178203_6_ + (float)(p_178203_3_ >> 16 & 255) * p_178203_7_ + (float)(p_178203_4_ >> 16 & 255) * p_178203_8_) & 255;
         int j = (int)((float)(p_178203_1_ & 255) * p_178203_5_ + (float)(p_178203_2_ & 255) * p_178203_6_ + (float)(p_178203_3_ & 255) * p_178203_7_ + (float)(p_178203_4_ & 255) * p_178203_8_) & 255;
         return i << 16 | j;
      }
   }

   public static enum EnumNeighborInfo {
      DOWN(new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.5F, false, new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0]),
      UP(new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH}, 1.0F, false, new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0], new BlockModelRenderer.Orientation[0]),
      NORTH(new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST}, 0.8F, true, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_WEST}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_EAST}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_EAST}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_WEST}),
      SOUTH(new EnumFacing[]{EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.UP}, 0.8F, true, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.WEST}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.WEST}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.EAST}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.EAST}),
      WEST(new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.6F, true, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.SOUTH}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.NORTH}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.NORTH}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.SOUTH}),
      EAST(new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH}, 0.6F, true, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.SOUTH}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.NORTH}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.NORTH}, new BlockModelRenderer.Orientation[]{BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.SOUTH});

      protected final EnumFacing[] field_178276_g;
      protected final float field_178288_h;
      protected final boolean field_178289_i;
      protected final BlockModelRenderer.Orientation[] field_178286_j;
      protected final BlockModelRenderer.Orientation[] field_178287_k;
      protected final BlockModelRenderer.Orientation[] field_178284_l;
      protected final BlockModelRenderer.Orientation[] field_178285_m;
      private static final BlockModelRenderer.EnumNeighborInfo[] field_178282_n = new BlockModelRenderer.EnumNeighborInfo[6];

      private EnumNeighborInfo(EnumFacing[] p_i46236_3_, float p_i46236_4_, boolean p_i46236_5_, BlockModelRenderer.Orientation[] p_i46236_6_, BlockModelRenderer.Orientation[] p_i46236_7_, BlockModelRenderer.Orientation[] p_i46236_8_, BlockModelRenderer.Orientation[] p_i46236_9_) {
         this.field_178276_g = p_i46236_3_;
         this.field_178288_h = p_i46236_4_;
         this.field_178289_i = p_i46236_5_;
         this.field_178286_j = p_i46236_6_;
         this.field_178287_k = p_i46236_7_;
         this.field_178284_l = p_i46236_8_;
         this.field_178285_m = p_i46236_9_;
      }

      public static BlockModelRenderer.EnumNeighborInfo func_178273_a(EnumFacing p_178273_0_) {
         return field_178282_n[p_178273_0_.func_176745_a()];
      }

      static {
         field_178282_n[EnumFacing.DOWN.func_176745_a()] = DOWN;
         field_178282_n[EnumFacing.UP.func_176745_a()] = UP;
         field_178282_n[EnumFacing.NORTH.func_176745_a()] = NORTH;
         field_178282_n[EnumFacing.SOUTH.func_176745_a()] = SOUTH;
         field_178282_n[EnumFacing.WEST.func_176745_a()] = WEST;
         field_178282_n[EnumFacing.EAST.func_176745_a()] = EAST;
      }
   }

   public static enum Orientation {
      DOWN(EnumFacing.DOWN, false),
      UP(EnumFacing.UP, false),
      NORTH(EnumFacing.NORTH, false),
      SOUTH(EnumFacing.SOUTH, false),
      WEST(EnumFacing.WEST, false),
      EAST(EnumFacing.EAST, false),
      FLIP_DOWN(EnumFacing.DOWN, true),
      FLIP_UP(EnumFacing.UP, true),
      FLIP_NORTH(EnumFacing.NORTH, true),
      FLIP_SOUTH(EnumFacing.SOUTH, true),
      FLIP_WEST(EnumFacing.WEST, true),
      FLIP_EAST(EnumFacing.EAST, true);

      protected final int field_178229_m;

      private Orientation(EnumFacing p_i46233_3_, boolean p_i46233_4_) {
         this.field_178229_m = p_i46233_3_.func_176745_a() + (p_i46233_4_?EnumFacing.values().length:0);
      }
   }

   static enum VertexTranslations {
      DOWN(0, 1, 2, 3),
      UP(2, 3, 0, 1),
      NORTH(3, 0, 1, 2),
      SOUTH(0, 1, 2, 3),
      WEST(3, 0, 1, 2),
      EAST(1, 2, 3, 0);

      private final int field_178191_g;
      private final int field_178200_h;
      private final int field_178201_i;
      private final int field_178198_j;
      private static final BlockModelRenderer.VertexTranslations[] field_178199_k = new BlockModelRenderer.VertexTranslations[6];

      private VertexTranslations(int p_i46234_3_, int p_i46234_4_, int p_i46234_5_, int p_i46234_6_) {
         this.field_178191_g = p_i46234_3_;
         this.field_178200_h = p_i46234_4_;
         this.field_178201_i = p_i46234_5_;
         this.field_178198_j = p_i46234_6_;
      }

      public static BlockModelRenderer.VertexTranslations func_178184_a(EnumFacing p_178184_0_) {
         return field_178199_k[p_178184_0_.func_176745_a()];
      }

      static {
         field_178199_k[EnumFacing.DOWN.func_176745_a()] = DOWN;
         field_178199_k[EnumFacing.UP.func_176745_a()] = UP;
         field_178199_k[EnumFacing.NORTH.func_176745_a()] = NORTH;
         field_178199_k[EnumFacing.SOUTH.func_176745_a()] = SOUTH;
         field_178199_k[EnumFacing.WEST.func_176745_a()] = WEST;
         field_178199_k[EnumFacing.EAST.func_176745_a()] = EAST;
      }
   }
}
