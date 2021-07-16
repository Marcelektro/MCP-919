package net.minecraft.client.renderer;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockFluidRenderer {
   private TextureAtlasSprite[] field_178272_a = new TextureAtlasSprite[2];
   private TextureAtlasSprite[] field_178271_b = new TextureAtlasSprite[2];

   public BlockFluidRenderer() {
      this.func_178268_a();
   }

   protected void func_178268_a() {
      TextureMap texturemap = Minecraft.func_71410_x().func_147117_R();
      this.field_178272_a[0] = texturemap.func_110572_b("minecraft:blocks/lava_still");
      this.field_178272_a[1] = texturemap.func_110572_b("minecraft:blocks/lava_flow");
      this.field_178271_b[0] = texturemap.func_110572_b("minecraft:blocks/water_still");
      this.field_178271_b[1] = texturemap.func_110572_b("minecraft:blocks/water_flow");
   }

   public boolean func_178270_a(IBlockAccess p_178270_1_, IBlockState p_178270_2_, BlockPos p_178270_3_, WorldRenderer p_178270_4_) {
      BlockLiquid blockliquid = (BlockLiquid)p_178270_2_.func_177230_c();
      blockliquid.func_180654_a(p_178270_1_, p_178270_3_);
      TextureAtlasSprite[] atextureatlassprite = blockliquid.func_149688_o() == Material.field_151587_i?this.field_178272_a:this.field_178271_b;
      int i = blockliquid.func_176202_d(p_178270_1_, p_178270_3_);
      float f = (float)(i >> 16 & 255) / 255.0F;
      float f1 = (float)(i >> 8 & 255) / 255.0F;
      float f2 = (float)(i & 255) / 255.0F;
      boolean flag = blockliquid.func_176225_a(p_178270_1_, p_178270_3_.func_177984_a(), EnumFacing.UP);
      boolean flag1 = blockliquid.func_176225_a(p_178270_1_, p_178270_3_.func_177977_b(), EnumFacing.DOWN);
      boolean[] aboolean = new boolean[]{blockliquid.func_176225_a(p_178270_1_, p_178270_3_.func_177978_c(), EnumFacing.NORTH), blockliquid.func_176225_a(p_178270_1_, p_178270_3_.func_177968_d(), EnumFacing.SOUTH), blockliquid.func_176225_a(p_178270_1_, p_178270_3_.func_177976_e(), EnumFacing.WEST), blockliquid.func_176225_a(p_178270_1_, p_178270_3_.func_177974_f(), EnumFacing.EAST)};
      if(!flag && !flag1 && !aboolean[0] && !aboolean[1] && !aboolean[2] && !aboolean[3]) {
         return false;
      } else {
         boolean flag2 = false;
         float f3 = 0.5F;
         float f4 = 1.0F;
         float f5 = 0.8F;
         float f6 = 0.6F;
         Material material = blockliquid.func_149688_o();
         float f7 = this.func_178269_a(p_178270_1_, p_178270_3_, material);
         float f8 = this.func_178269_a(p_178270_1_, p_178270_3_.func_177968_d(), material);
         float f9 = this.func_178269_a(p_178270_1_, p_178270_3_.func_177974_f().func_177968_d(), material);
         float f10 = this.func_178269_a(p_178270_1_, p_178270_3_.func_177974_f(), material);
         double d0 = (double)p_178270_3_.func_177958_n();
         double d1 = (double)p_178270_3_.func_177956_o();
         double d2 = (double)p_178270_3_.func_177952_p();
         float f11 = 0.001F;
         if(flag) {
            flag2 = true;
            TextureAtlasSprite textureatlassprite = atextureatlassprite[0];
            float f12 = (float)BlockLiquid.func_180689_a(p_178270_1_, p_178270_3_, material);
            if(f12 > -999.0F) {
               textureatlassprite = atextureatlassprite[1];
            }

            f7 -= f11;
            f8 -= f11;
            f9 -= f11;
            f10 -= f11;
            float f13;
            float f14;
            float f15;
            float f16;
            float f17;
            float f18;
            float f19;
            float f20;
            if(f12 < -999.0F) {
               f13 = textureatlassprite.func_94214_a(0.0D);
               f17 = textureatlassprite.func_94207_b(0.0D);
               f14 = f13;
               f18 = textureatlassprite.func_94207_b(16.0D);
               f15 = textureatlassprite.func_94214_a(16.0D);
               f19 = f18;
               f16 = f15;
               f20 = f17;
            } else {
               float f21 = MathHelper.func_76126_a(f12) * 0.25F;
               float f22 = MathHelper.func_76134_b(f12) * 0.25F;
               float f23 = 8.0F;
               f13 = textureatlassprite.func_94214_a((double)(8.0F + (-f22 - f21) * 16.0F));
               f17 = textureatlassprite.func_94207_b((double)(8.0F + (-f22 + f21) * 16.0F));
               f14 = textureatlassprite.func_94214_a((double)(8.0F + (-f22 + f21) * 16.0F));
               f18 = textureatlassprite.func_94207_b((double)(8.0F + (f22 + f21) * 16.0F));
               f15 = textureatlassprite.func_94214_a((double)(8.0F + (f22 + f21) * 16.0F));
               f19 = textureatlassprite.func_94207_b((double)(8.0F + (f22 - f21) * 16.0F));
               f16 = textureatlassprite.func_94214_a((double)(8.0F + (f22 - f21) * 16.0F));
               f20 = textureatlassprite.func_94207_b((double)(8.0F + (-f22 - f21) * 16.0F));
            }

            int k2 = blockliquid.func_176207_c(p_178270_1_, p_178270_3_);
            int l2 = k2 >> 16 & '\uffff';
            int i3 = k2 & '\uffff';
            float f24 = f4 * f;
            float f25 = f4 * f1;
            float f26 = f4 * f2;
            p_178270_4_.func_181662_b(d0 + 0.0D, d1 + (double)f7, d2 + 0.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f13, (double)f17).func_181671_a(l2, i3).func_181675_d();
            p_178270_4_.func_181662_b(d0 + 0.0D, d1 + (double)f8, d2 + 1.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f14, (double)f18).func_181671_a(l2, i3).func_181675_d();
            p_178270_4_.func_181662_b(d0 + 1.0D, d1 + (double)f9, d2 + 1.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f15, (double)f19).func_181671_a(l2, i3).func_181675_d();
            p_178270_4_.func_181662_b(d0 + 1.0D, d1 + (double)f10, d2 + 0.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f16, (double)f20).func_181671_a(l2, i3).func_181675_d();
            if(blockliquid.func_176364_g(p_178270_1_, p_178270_3_.func_177984_a())) {
               p_178270_4_.func_181662_b(d0 + 0.0D, d1 + (double)f7, d2 + 0.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f13, (double)f17).func_181671_a(l2, i3).func_181675_d();
               p_178270_4_.func_181662_b(d0 + 1.0D, d1 + (double)f10, d2 + 0.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f16, (double)f20).func_181671_a(l2, i3).func_181675_d();
               p_178270_4_.func_181662_b(d0 + 1.0D, d1 + (double)f9, d2 + 1.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f15, (double)f19).func_181671_a(l2, i3).func_181675_d();
               p_178270_4_.func_181662_b(d0 + 0.0D, d1 + (double)f8, d2 + 1.0D).func_181666_a(f24, f25, f26, 1.0F).func_181673_a((double)f14, (double)f18).func_181671_a(l2, i3).func_181675_d();
            }
         }

         if(flag1) {
            float f35 = atextureatlassprite[0].func_94209_e();
            float f36 = atextureatlassprite[0].func_94212_f();
            float f37 = atextureatlassprite[0].func_94206_g();
            float f38 = atextureatlassprite[0].func_94210_h();
            int l1 = blockliquid.func_176207_c(p_178270_1_, p_178270_3_.func_177977_b());
            int i2 = l1 >> 16 & '\uffff';
            int j2 = l1 & '\uffff';
            p_178270_4_.func_181662_b(d0, d1, d2 + 1.0D).func_181666_a(f3, f3, f3, 1.0F).func_181673_a((double)f35, (double)f38).func_181671_a(i2, j2).func_181675_d();
            p_178270_4_.func_181662_b(d0, d1, d2).func_181666_a(f3, f3, f3, 1.0F).func_181673_a((double)f35, (double)f37).func_181671_a(i2, j2).func_181675_d();
            p_178270_4_.func_181662_b(d0 + 1.0D, d1, d2).func_181666_a(f3, f3, f3, 1.0F).func_181673_a((double)f36, (double)f37).func_181671_a(i2, j2).func_181675_d();
            p_178270_4_.func_181662_b(d0 + 1.0D, d1, d2 + 1.0D).func_181666_a(f3, f3, f3, 1.0F).func_181673_a((double)f36, (double)f38).func_181671_a(i2, j2).func_181675_d();
            flag2 = true;
         }

         for(int i1 = 0; i1 < 4; ++i1) {
            int j1 = 0;
            int k1 = 0;
            if(i1 == 0) {
               --k1;
            }

            if(i1 == 1) {
               ++k1;
            }

            if(i1 == 2) {
               --j1;
            }

            if(i1 == 3) {
               ++j1;
            }

            BlockPos blockpos = p_178270_3_.func_177982_a(j1, 0, k1);
            TextureAtlasSprite textureatlassprite1 = atextureatlassprite[1];
            if(aboolean[i1]) {
               float f39;
               float f40;
               double d3;
               double d4;
               double d5;
               double d6;
               if(i1 == 0) {
                  f39 = f7;
                  f40 = f10;
                  d3 = d0;
                  d5 = d0 + 1.0D;
                  d4 = d2 + (double)f11;
                  d6 = d2 + (double)f11;
               } else if(i1 == 1) {
                  f39 = f9;
                  f40 = f8;
                  d3 = d0 + 1.0D;
                  d5 = d0;
                  d4 = d2 + 1.0D - (double)f11;
                  d6 = d2 + 1.0D - (double)f11;
               } else if(i1 == 2) {
                  f39 = f8;
                  f40 = f7;
                  d3 = d0 + (double)f11;
                  d5 = d0 + (double)f11;
                  d4 = d2 + 1.0D;
                  d6 = d2;
               } else {
                  f39 = f10;
                  f40 = f9;
                  d3 = d0 + 1.0D - (double)f11;
                  d5 = d0 + 1.0D - (double)f11;
                  d4 = d2;
                  d6 = d2 + 1.0D;
               }

               flag2 = true;
               float f41 = textureatlassprite1.func_94214_a(0.0D);
               float f27 = textureatlassprite1.func_94214_a(8.0D);
               float f28 = textureatlassprite1.func_94207_b((double)((1.0F - f39) * 16.0F * 0.5F));
               float f29 = textureatlassprite1.func_94207_b((double)((1.0F - f40) * 16.0F * 0.5F));
               float f30 = textureatlassprite1.func_94207_b(8.0D);
               int j = blockliquid.func_176207_c(p_178270_1_, blockpos);
               int k = j >> 16 & '\uffff';
               int l = j & '\uffff';
               float f31 = i1 < 2?f5:f6;
               float f32 = f4 * f31 * f;
               float f33 = f4 * f31 * f1;
               float f34 = f4 * f31 * f2;
               p_178270_4_.func_181662_b(d3, d1 + (double)f39, d4).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f41, (double)f28).func_181671_a(k, l).func_181675_d();
               p_178270_4_.func_181662_b(d5, d1 + (double)f40, d6).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f27, (double)f29).func_181671_a(k, l).func_181675_d();
               p_178270_4_.func_181662_b(d5, d1 + 0.0D, d6).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f27, (double)f30).func_181671_a(k, l).func_181675_d();
               p_178270_4_.func_181662_b(d3, d1 + 0.0D, d4).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f41, (double)f30).func_181671_a(k, l).func_181675_d();
               p_178270_4_.func_181662_b(d3, d1 + 0.0D, d4).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f41, (double)f30).func_181671_a(k, l).func_181675_d();
               p_178270_4_.func_181662_b(d5, d1 + 0.0D, d6).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f27, (double)f30).func_181671_a(k, l).func_181675_d();
               p_178270_4_.func_181662_b(d5, d1 + (double)f40, d6).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f27, (double)f29).func_181671_a(k, l).func_181675_d();
               p_178270_4_.func_181662_b(d3, d1 + (double)f39, d4).func_181666_a(f32, f33, f34, 1.0F).func_181673_a((double)f41, (double)f28).func_181671_a(k, l).func_181675_d();
            }
         }

         return flag2;
      }
   }

   private float func_178269_a(IBlockAccess p_178269_1_, BlockPos p_178269_2_, Material p_178269_3_) {
      int i = 0;
      float f = 0.0F;

      for(int j = 0; j < 4; ++j) {
         BlockPos blockpos = p_178269_2_.func_177982_a(-(j & 1), 0, -(j >> 1 & 1));
         if(p_178269_1_.func_180495_p(blockpos.func_177984_a()).func_177230_c().func_149688_o() == p_178269_3_) {
            return 1.0F;
         }

         IBlockState iblockstate = p_178269_1_.func_180495_p(blockpos);
         Material material = iblockstate.func_177230_c().func_149688_o();
         if(material != p_178269_3_) {
            if(!material.func_76220_a()) {
               ++f;
               ++i;
            }
         } else {
            int k = ((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue();
            if(k >= 8 || k == 0) {
               f += BlockLiquid.func_149801_b(k) * 10.0F;
               i += 10;
            }

            f += BlockLiquid.func_149801_b(k);
            ++i;
         }
      }

      return 1.0F - f / (float)i;
   }
}
