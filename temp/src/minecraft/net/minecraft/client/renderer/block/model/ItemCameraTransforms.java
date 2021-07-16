package net.minecraft.client.renderer.block.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;

public class ItemCameraTransforms {
   public static final ItemCameraTransforms field_178357_a = new ItemCameraTransforms();
   public static float field_181690_b = 0.0F;
   public static float field_181691_c = 0.0F;
   public static float field_181692_d = 0.0F;
   public static float field_181693_e = 0.0F;
   public static float field_181694_f = 0.0F;
   public static float field_181695_g = 0.0F;
   public static float field_181696_h = 0.0F;
   public static float field_181697_i = 0.0F;
   public static float field_181698_j = 0.0F;
   public final ItemTransformVec3f field_178355_b;
   public final ItemTransformVec3f field_178356_c;
   public final ItemTransformVec3f field_178353_d;
   public final ItemTransformVec3f field_178354_e;
   public final ItemTransformVec3f field_181699_o;
   public final ItemTransformVec3f field_181700_p;

   private ItemCameraTransforms() {
      this(ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a);
   }

   public ItemCameraTransforms(ItemCameraTransforms p_i46443_1_) {
      this.field_178355_b = p_i46443_1_.field_178355_b;
      this.field_178356_c = p_i46443_1_.field_178356_c;
      this.field_178353_d = p_i46443_1_.field_178353_d;
      this.field_178354_e = p_i46443_1_.field_178354_e;
      this.field_181699_o = p_i46443_1_.field_181699_o;
      this.field_181700_p = p_i46443_1_.field_181700_p;
   }

   public ItemCameraTransforms(ItemTransformVec3f p_i46444_1_, ItemTransformVec3f p_i46444_2_, ItemTransformVec3f p_i46444_3_, ItemTransformVec3f p_i46444_4_, ItemTransformVec3f p_i46444_5_, ItemTransformVec3f p_i46444_6_) {
      this.field_178355_b = p_i46444_1_;
      this.field_178356_c = p_i46444_2_;
      this.field_178353_d = p_i46444_3_;
      this.field_178354_e = p_i46444_4_;
      this.field_181699_o = p_i46444_5_;
      this.field_181700_p = p_i46444_6_;
   }

   public void func_181689_a(ItemCameraTransforms.TransformType p_181689_1_) {
      ItemTransformVec3f itemtransformvec3f = this.func_181688_b(p_181689_1_);
      if(itemtransformvec3f != ItemTransformVec3f.field_178366_a) {
         GlStateManager.func_179109_b(itemtransformvec3f.field_178365_c.x + field_181690_b, itemtransformvec3f.field_178365_c.y + field_181691_c, itemtransformvec3f.field_178365_c.z + field_181692_d);
         GlStateManager.func_179114_b(itemtransformvec3f.field_178364_b.y + field_181694_f, 0.0F, 1.0F, 0.0F);
         GlStateManager.func_179114_b(itemtransformvec3f.field_178364_b.x + field_181693_e, 1.0F, 0.0F, 0.0F);
         GlStateManager.func_179114_b(itemtransformvec3f.field_178364_b.z + field_181695_g, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179152_a(itemtransformvec3f.field_178363_d.x + field_181696_h, itemtransformvec3f.field_178363_d.y + field_181697_i, itemtransformvec3f.field_178363_d.z + field_181698_j);
      }

   }

   public ItemTransformVec3f func_181688_b(ItemCameraTransforms.TransformType p_181688_1_) {
      switch(p_181688_1_) {
      case THIRD_PERSON:
         return this.field_178355_b;
      case FIRST_PERSON:
         return this.field_178356_c;
      case HEAD:
         return this.field_178353_d;
      case GUI:
         return this.field_178354_e;
      case GROUND:
         return this.field_181699_o;
      case FIXED:
         return this.field_181700_p;
      default:
         return ItemTransformVec3f.field_178366_a;
      }
   }

   public boolean func_181687_c(ItemCameraTransforms.TransformType p_181687_1_) {
      return !this.func_181688_b(p_181687_1_).equals(ItemTransformVec3f.field_178366_a);
   }

   static class Deserializer implements JsonDeserializer<ItemCameraTransforms> {
      public ItemCameraTransforms deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
         ItemTransformVec3f itemtransformvec3f = this.func_181683_a(p_deserialize_3_, jsonobject, "thirdperson");
         ItemTransformVec3f itemtransformvec3f1 = this.func_181683_a(p_deserialize_3_, jsonobject, "firstperson");
         ItemTransformVec3f itemtransformvec3f2 = this.func_181683_a(p_deserialize_3_, jsonobject, "head");
         ItemTransformVec3f itemtransformvec3f3 = this.func_181683_a(p_deserialize_3_, jsonobject, "gui");
         ItemTransformVec3f itemtransformvec3f4 = this.func_181683_a(p_deserialize_3_, jsonobject, "ground");
         ItemTransformVec3f itemtransformvec3f5 = this.func_181683_a(p_deserialize_3_, jsonobject, "fixed");
         return new ItemCameraTransforms(itemtransformvec3f, itemtransformvec3f1, itemtransformvec3f2, itemtransformvec3f3, itemtransformvec3f4, itemtransformvec3f5);
      }

      private ItemTransformVec3f func_181683_a(JsonDeserializationContext p_181683_1_, JsonObject p_181683_2_, String p_181683_3_) {
         return p_181683_2_.has(p_181683_3_)?(ItemTransformVec3f)p_181683_1_.deserialize(p_181683_2_.get(p_181683_3_), ItemTransformVec3f.class):ItemTransformVec3f.field_178366_a;
      }
   }

   public static enum TransformType {
      NONE,
      THIRD_PERSON,
      FIRST_PERSON,
      HEAD,
      GUI,
      GROUND,
      FIXED;
   }
}
