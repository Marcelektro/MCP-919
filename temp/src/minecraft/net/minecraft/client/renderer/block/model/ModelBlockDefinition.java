package net.minecraft.client.renderer.block.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class ModelBlockDefinition {
   static final Gson field_178333_a = (new GsonBuilder()).registerTypeAdapter(ModelBlockDefinition.class, new ModelBlockDefinition.Deserializer()).registerTypeAdapter(ModelBlockDefinition.Variant.class, new ModelBlockDefinition.Variant.Deserializer()).create();
   private final Map<String, ModelBlockDefinition.Variants> field_178332_b = Maps.<String, ModelBlockDefinition.Variants>newHashMap();

   public static ModelBlockDefinition func_178331_a(Reader p_178331_0_) {
      return (ModelBlockDefinition)field_178333_a.fromJson(p_178331_0_, ModelBlockDefinition.class);
   }

   public ModelBlockDefinition(Collection<ModelBlockDefinition.Variants> p_i46221_1_) {
      for(ModelBlockDefinition.Variants modelblockdefinition$variants : p_i46221_1_) {
         this.field_178332_b.put(modelblockdefinition$variants.field_178423_a, modelblockdefinition$variants);
      }

   }

   public ModelBlockDefinition(List<ModelBlockDefinition> p_i46222_1_) {
      for(ModelBlockDefinition modelblockdefinition : p_i46222_1_) {
         this.field_178332_b.putAll(modelblockdefinition.field_178332_b);
      }

   }

   public ModelBlockDefinition.Variants func_178330_b(String p_178330_1_) {
      ModelBlockDefinition.Variants modelblockdefinition$variants = (ModelBlockDefinition.Variants)this.field_178332_b.get(p_178330_1_);
      if(modelblockdefinition$variants == null) {
         throw new ModelBlockDefinition.MissingVariantException();
      } else {
         return modelblockdefinition$variants;
      }
   }

   public boolean equals(Object p_equals_1_) {
      if(this == p_equals_1_) {
         return true;
      } else if(p_equals_1_ instanceof ModelBlockDefinition) {
         ModelBlockDefinition modelblockdefinition = (ModelBlockDefinition)p_equals_1_;
         return this.field_178332_b.equals(modelblockdefinition.field_178332_b);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.field_178332_b.hashCode();
   }

   public static class Deserializer implements JsonDeserializer<ModelBlockDefinition> {
      public ModelBlockDefinition deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
         List<ModelBlockDefinition.Variants> list = this.func_178334_a(p_deserialize_3_, jsonobject);
         return new ModelBlockDefinition(list);
      }

      protected List<ModelBlockDefinition.Variants> func_178334_a(JsonDeserializationContext p_178334_1_, JsonObject p_178334_2_) {
         JsonObject jsonobject = JsonUtils.func_152754_s(p_178334_2_, "variants");
         List<ModelBlockDefinition.Variants> list = Lists.<ModelBlockDefinition.Variants>newArrayList();

         for(Entry<String, JsonElement> entry : jsonobject.entrySet()) {
            list.add(this.func_178335_a(p_178334_1_, entry));
         }

         return list;
      }

      protected ModelBlockDefinition.Variants func_178335_a(JsonDeserializationContext p_178335_1_, Entry<String, JsonElement> p_178335_2_) {
         String s = (String)p_178335_2_.getKey();
         List<ModelBlockDefinition.Variant> list = Lists.<ModelBlockDefinition.Variant>newArrayList();
         JsonElement jsonelement = (JsonElement)p_178335_2_.getValue();
         if(jsonelement.isJsonArray()) {
            for(JsonElement jsonelement1 : jsonelement.getAsJsonArray()) {
               list.add((ModelBlockDefinition.Variant)p_178335_1_.deserialize(jsonelement1, ModelBlockDefinition.Variant.class));
            }
         } else {
            list.add((ModelBlockDefinition.Variant)p_178335_1_.deserialize(jsonelement, ModelBlockDefinition.Variant.class));
         }

         return new ModelBlockDefinition.Variants(s, list);
      }
   }

   public class MissingVariantException extends RuntimeException {
   }

   public static class Variant {
      private final ResourceLocation field_178437_a;
      private final ModelRotation field_178435_b;
      private final boolean field_178436_c;
      private final int field_178434_d;

      public Variant(ResourceLocation p_i46219_1_, ModelRotation p_i46219_2_, boolean p_i46219_3_, int p_i46219_4_) {
         this.field_178437_a = p_i46219_1_;
         this.field_178435_b = p_i46219_2_;
         this.field_178436_c = p_i46219_3_;
         this.field_178434_d = p_i46219_4_;
      }

      public ResourceLocation func_178431_a() {
         return this.field_178437_a;
      }

      public ModelRotation func_178432_b() {
         return this.field_178435_b;
      }

      public boolean func_178433_c() {
         return this.field_178436_c;
      }

      public int func_178430_d() {
         return this.field_178434_d;
      }

      public boolean equals(Object p_equals_1_) {
         if(this == p_equals_1_) {
            return true;
         } else if(!(p_equals_1_ instanceof ModelBlockDefinition.Variant)) {
            return false;
         } else {
            ModelBlockDefinition.Variant modelblockdefinition$variant = (ModelBlockDefinition.Variant)p_equals_1_;
            return this.field_178437_a.equals(modelblockdefinition$variant.field_178437_a) && this.field_178435_b == modelblockdefinition$variant.field_178435_b && this.field_178436_c == modelblockdefinition$variant.field_178436_c;
         }
      }

      public int hashCode() {
         int i = this.field_178437_a.hashCode();
         i = 31 * i + (this.field_178435_b != null?this.field_178435_b.hashCode():0);
         i = 31 * i + (this.field_178436_c?1:0);
         return i;
      }

      public static class Deserializer implements JsonDeserializer<ModelBlockDefinition.Variant> {
         public ModelBlockDefinition.Variant deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            String s = this.func_178424_b(jsonobject);
            ModelRotation modelrotation = this.func_178428_a(jsonobject);
            boolean flag = this.func_178429_d(jsonobject);
            int i = this.func_178427_c(jsonobject);
            return new ModelBlockDefinition.Variant(this.func_178426_a(s), modelrotation, flag, i);
         }

         private ResourceLocation func_178426_a(String p_178426_1_) {
            ResourceLocation resourcelocation = new ResourceLocation(p_178426_1_);
            resourcelocation = new ResourceLocation(resourcelocation.func_110624_b(), "block/" + resourcelocation.func_110623_a());
            return resourcelocation;
         }

         private boolean func_178429_d(JsonObject p_178429_1_) {
            return JsonUtils.func_151209_a(p_178429_1_, "uvlock", false);
         }

         protected ModelRotation func_178428_a(JsonObject p_178428_1_) {
            int i = JsonUtils.func_151208_a(p_178428_1_, "x", 0);
            int j = JsonUtils.func_151208_a(p_178428_1_, "y", 0);
            ModelRotation modelrotation = ModelRotation.func_177524_a(i, j);
            if(modelrotation == null) {
               throw new JsonParseException("Invalid BlockModelRotation x: " + i + ", y: " + j);
            } else {
               return modelrotation;
            }
         }

         protected String func_178424_b(JsonObject p_178424_1_) {
            return JsonUtils.func_151200_h(p_178424_1_, "model");
         }

         protected int func_178427_c(JsonObject p_178427_1_) {
            return JsonUtils.func_151208_a(p_178427_1_, "weight", 1);
         }
      }
   }

   public static class Variants {
      private final String field_178423_a;
      private final List<ModelBlockDefinition.Variant> field_178422_b;

      public Variants(String p_i46218_1_, List<ModelBlockDefinition.Variant> p_i46218_2_) {
         this.field_178423_a = p_i46218_1_;
         this.field_178422_b = p_i46218_2_;
      }

      public List<ModelBlockDefinition.Variant> func_178420_b() {
         return this.field_178422_b;
      }

      public boolean equals(Object p_equals_1_) {
         if(this == p_equals_1_) {
            return true;
         } else if(!(p_equals_1_ instanceof ModelBlockDefinition.Variants)) {
            return false;
         } else {
            ModelBlockDefinition.Variants modelblockdefinition$variants = (ModelBlockDefinition.Variants)p_equals_1_;
            return !this.field_178423_a.equals(modelblockdefinition$variants.field_178423_a)?false:this.field_178422_b.equals(modelblockdefinition$variants.field_178422_b);
         }
      }

      public int hashCode() {
         int i = this.field_178423_a.hashCode();
         i = 31 * i + this.field_178422_b.hashCode();
         return i;
      }
   }
}
