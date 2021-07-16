package net.minecraft.client.resources.model;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class SimpleBakedModel implements IBakedModel {
   protected final List<BakedQuad> field_177563_a;
   protected final List<List<BakedQuad>> field_177561_b;
   protected final boolean field_177562_c;
   protected final boolean field_177559_d;
   protected final TextureAtlasSprite field_177560_e;
   protected final ItemCameraTransforms field_177558_f;

   public SimpleBakedModel(List<BakedQuad> p_i46077_1_, List<List<BakedQuad>> p_i46077_2_, boolean p_i46077_3_, boolean p_i46077_4_, TextureAtlasSprite p_i46077_5_, ItemCameraTransforms p_i46077_6_) {
      this.field_177563_a = p_i46077_1_;
      this.field_177561_b = p_i46077_2_;
      this.field_177562_c = p_i46077_3_;
      this.field_177559_d = p_i46077_4_;
      this.field_177560_e = p_i46077_5_;
      this.field_177558_f = p_i46077_6_;
   }

   public List<BakedQuad> func_177551_a(EnumFacing p_177551_1_) {
      return (List)this.field_177561_b.get(p_177551_1_.ordinal());
   }

   public List<BakedQuad> func_177550_a() {
      return this.field_177563_a;
   }

   public boolean func_177555_b() {
      return this.field_177562_c;
   }

   public boolean func_177556_c() {
      return this.field_177559_d;
   }

   public boolean func_177553_d() {
      return false;
   }

   public TextureAtlasSprite func_177554_e() {
      return this.field_177560_e;
   }

   public ItemCameraTransforms func_177552_f() {
      return this.field_177558_f;
   }

   public static class Builder {
      private final List<BakedQuad> field_177656_a;
      private final List<List<BakedQuad>> field_177654_b;
      private final boolean field_177655_c;
      private TextureAtlasSprite field_177652_d;
      private boolean field_177653_e;
      private ItemCameraTransforms field_177651_f;

      public Builder(ModelBlock p_i46074_1_) {
         this(p_i46074_1_.func_178309_b(), p_i46074_1_.func_178311_c(), p_i46074_1_.func_181682_g());
      }

      public Builder(IBakedModel p_i46075_1_, TextureAtlasSprite p_i46075_2_) {
         this(p_i46075_1_.func_177555_b(), p_i46075_1_.func_177556_c(), p_i46075_1_.func_177552_f());
         this.field_177652_d = p_i46075_1_.func_177554_e();

         for(EnumFacing enumfacing : EnumFacing.values()) {
            this.func_177649_a(p_i46075_1_, p_i46075_2_, enumfacing);
         }

         this.func_177647_a(p_i46075_1_, p_i46075_2_);
      }

      private void func_177649_a(IBakedModel p_177649_1_, TextureAtlasSprite p_177649_2_, EnumFacing p_177649_3_) {
         for(BakedQuad bakedquad : p_177649_1_.func_177551_a(p_177649_3_)) {
            this.func_177650_a(p_177649_3_, new BreakingFour(bakedquad, p_177649_2_));
         }

      }

      private void func_177647_a(IBakedModel p_177647_1_, TextureAtlasSprite p_177647_2_) {
         for(BakedQuad bakedquad : p_177647_1_.func_177550_a()) {
            this.func_177648_a(new BreakingFour(bakedquad, p_177647_2_));
         }

      }

      private Builder(boolean p_i46076_1_, boolean p_i46076_2_, ItemCameraTransforms p_i46076_3_) {
         this.field_177656_a = Lists.<BakedQuad>newArrayList();
         this.field_177654_b = Lists.<List<BakedQuad>>newArrayListWithCapacity(6);

         for(EnumFacing enumfacing : EnumFacing.values()) {
            this.field_177654_b.add(Lists.<BakedQuad>newArrayList());
         }

         this.field_177655_c = p_i46076_1_;
         this.field_177653_e = p_i46076_2_;
         this.field_177651_f = p_i46076_3_;
      }

      public SimpleBakedModel.Builder func_177650_a(EnumFacing p_177650_1_, BakedQuad p_177650_2_) {
         ((List)this.field_177654_b.get(p_177650_1_.ordinal())).add(p_177650_2_);
         return this;
      }

      public SimpleBakedModel.Builder func_177648_a(BakedQuad p_177648_1_) {
         this.field_177656_a.add(p_177648_1_);
         return this;
      }

      public SimpleBakedModel.Builder func_177646_a(TextureAtlasSprite p_177646_1_) {
         this.field_177652_d = p_177646_1_;
         return this;
      }

      public IBakedModel func_177645_b() {
         if(this.field_177652_d == null) {
            throw new RuntimeException("Missing particle!");
         } else {
            return new SimpleBakedModel(this.field_177656_a, this.field_177654_b, this.field_177655_c, this.field_177653_e, this.field_177652_d, this.field_177651_f);
         }
      }
   }
}
