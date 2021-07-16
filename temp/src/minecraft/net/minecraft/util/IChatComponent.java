package net.minecraft.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.util.ChatComponentScore;
import net.minecraft.util.ChatComponentSelector;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.JsonUtils;

public interface IChatComponent extends Iterable<IChatComponent> {
   IChatComponent func_150255_a(ChatStyle var1);

   ChatStyle func_150256_b();

   IChatComponent func_150258_a(String var1);

   IChatComponent func_150257_a(IChatComponent var1);

   String func_150261_e();

   String func_150260_c();

   String func_150254_d();

   List<IChatComponent> func_150253_a();

   IChatComponent func_150259_f();

   public static class Serializer implements JsonDeserializer<IChatComponent>, JsonSerializer<IChatComponent> {
      private static final Gson field_150700_a;

      public IChatComponent deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         if(p_deserialize_1_.isJsonPrimitive()) {
            return new ChatComponentText(p_deserialize_1_.getAsString());
         } else if(!p_deserialize_1_.isJsonObject()) {
            if(p_deserialize_1_.isJsonArray()) {
               JsonArray jsonarray1 = p_deserialize_1_.getAsJsonArray();
               IChatComponent ichatcomponent1 = null;

               for(JsonElement jsonelement : jsonarray1) {
                  IChatComponent ichatcomponent2 = this.deserialize(jsonelement, jsonelement.getClass(), p_deserialize_3_);
                  if(ichatcomponent1 == null) {
                     ichatcomponent1 = ichatcomponent2;
                  } else {
                     ichatcomponent1.func_150257_a(ichatcomponent2);
                  }
               }

               return ichatcomponent1;
            } else {
               throw new JsonParseException("Don\'t know how to turn " + p_deserialize_1_.toString() + " into a Component");
            }
         } else {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            IChatComponent ichatcomponent;
            if(jsonobject.has("text")) {
               ichatcomponent = new ChatComponentText(jsonobject.get("text").getAsString());
            } else if(jsonobject.has("translate")) {
               String s = jsonobject.get("translate").getAsString();
               if(jsonobject.has("with")) {
                  JsonArray jsonarray = jsonobject.getAsJsonArray("with");
                  Object[] aobject = new Object[jsonarray.size()];

                  for(int i = 0; i < aobject.length; ++i) {
                     aobject[i] = this.deserialize(jsonarray.get(i), p_deserialize_2_, p_deserialize_3_);
                     if(aobject[i] instanceof ChatComponentText) {
                        ChatComponentText chatcomponenttext = (ChatComponentText)aobject[i];
                        if(chatcomponenttext.func_150256_b().func_150229_g() && chatcomponenttext.func_150253_a().isEmpty()) {
                           aobject[i] = chatcomponenttext.func_150265_g();
                        }
                     }
                  }

                  ichatcomponent = new ChatComponentTranslation(s, aobject);
               } else {
                  ichatcomponent = new ChatComponentTranslation(s, new Object[0]);
               }
            } else if(jsonobject.has("score")) {
               JsonObject jsonobject1 = jsonobject.getAsJsonObject("score");
               if(!jsonobject1.has("name") || !jsonobject1.has("objective")) {
                  throw new JsonParseException("A score component needs a least a name and an objective");
               }

               ichatcomponent = new ChatComponentScore(JsonUtils.func_151200_h(jsonobject1, "name"), JsonUtils.func_151200_h(jsonobject1, "objective"));
               if(jsonobject1.has("value")) {
                  ((ChatComponentScore)ichatcomponent).func_179997_b(JsonUtils.func_151200_h(jsonobject1, "value"));
               }
            } else {
               if(!jsonobject.has("selector")) {
                  throw new JsonParseException("Don\'t know how to turn " + p_deserialize_1_.toString() + " into a Component");
               }

               ichatcomponent = new ChatComponentSelector(JsonUtils.func_151200_h(jsonobject, "selector"));
            }

            if(jsonobject.has("extra")) {
               JsonArray jsonarray2 = jsonobject.getAsJsonArray("extra");
               if(jsonarray2.size() <= 0) {
                  throw new JsonParseException("Unexpected empty array of components");
               }

               for(int j = 0; j < jsonarray2.size(); ++j) {
                  ichatcomponent.func_150257_a(this.deserialize(jsonarray2.get(j), p_deserialize_2_, p_deserialize_3_));
               }
            }

            ichatcomponent.func_150255_a((ChatStyle)p_deserialize_3_.deserialize(p_deserialize_1_, ChatStyle.class));
            return ichatcomponent;
         }
      }

      private void func_150695_a(ChatStyle p_150695_1_, JsonObject p_150695_2_, JsonSerializationContext p_150695_3_) {
         JsonElement jsonelement = p_150695_3_.serialize(p_150695_1_);
         if(jsonelement.isJsonObject()) {
            JsonObject jsonobject = (JsonObject)jsonelement;

            for(Entry<String, JsonElement> entry : jsonobject.entrySet()) {
               p_150695_2_.add((String)entry.getKey(), (JsonElement)entry.getValue());
            }
         }

      }

      public JsonElement serialize(IChatComponent p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         if(p_serialize_1_ instanceof ChatComponentText && p_serialize_1_.func_150256_b().func_150229_g() && p_serialize_1_.func_150253_a().isEmpty()) {
            return new JsonPrimitive(((ChatComponentText)p_serialize_1_).func_150265_g());
         } else {
            JsonObject jsonobject = new JsonObject();
            if(!p_serialize_1_.func_150256_b().func_150229_g()) {
               this.func_150695_a(p_serialize_1_.func_150256_b(), jsonobject, p_serialize_3_);
            }

            if(!p_serialize_1_.func_150253_a().isEmpty()) {
               JsonArray jsonarray = new JsonArray();

               for(IChatComponent ichatcomponent : p_serialize_1_.func_150253_a()) {
                  jsonarray.add(this.serialize((IChatComponent)ichatcomponent, ichatcomponent.getClass(), p_serialize_3_));
               }

               jsonobject.add("extra", jsonarray);
            }

            if(p_serialize_1_ instanceof ChatComponentText) {
               jsonobject.addProperty("text", ((ChatComponentText)p_serialize_1_).func_150265_g());
            } else if(p_serialize_1_ instanceof ChatComponentTranslation) {
               ChatComponentTranslation chatcomponenttranslation = (ChatComponentTranslation)p_serialize_1_;
               jsonobject.addProperty("translate", chatcomponenttranslation.func_150268_i());
               if(chatcomponenttranslation.func_150271_j() != null && chatcomponenttranslation.func_150271_j().length > 0) {
                  JsonArray jsonarray1 = new JsonArray();

                  for(Object object : chatcomponenttranslation.func_150271_j()) {
                     if(object instanceof IChatComponent) {
                        jsonarray1.add(this.serialize((IChatComponent)((IChatComponent)object), object.getClass(), p_serialize_3_));
                     } else {
                        jsonarray1.add(new JsonPrimitive(String.valueOf(object)));
                     }
                  }

                  jsonobject.add("with", jsonarray1);
               }
            } else if(p_serialize_1_ instanceof ChatComponentScore) {
               ChatComponentScore chatcomponentscore = (ChatComponentScore)p_serialize_1_;
               JsonObject jsonobject1 = new JsonObject();
               jsonobject1.addProperty("name", chatcomponentscore.func_179995_g());
               jsonobject1.addProperty("objective", chatcomponentscore.func_179994_h());
               jsonobject1.addProperty("value", chatcomponentscore.func_150261_e());
               jsonobject.add("score", jsonobject1);
            } else {
               if(!(p_serialize_1_ instanceof ChatComponentSelector)) {
                  throw new IllegalArgumentException("Don\'t know how to serialize " + p_serialize_1_ + " as a Component");
               }

               ChatComponentSelector chatcomponentselector = (ChatComponentSelector)p_serialize_1_;
               jsonobject.addProperty("selector", chatcomponentselector.func_179992_g());
            }

            return jsonobject;
         }
      }

      public static String func_150696_a(IChatComponent p_150696_0_) {
         return field_150700_a.toJson((Object)p_150696_0_);
      }

      public static IChatComponent func_150699_a(String p_150699_0_) {
         return (IChatComponent)field_150700_a.fromJson(p_150699_0_, IChatComponent.class);
      }

      static {
         GsonBuilder gsonbuilder = new GsonBuilder();
         gsonbuilder.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent.Serializer());
         gsonbuilder.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
         gsonbuilder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
         field_150700_a = gsonbuilder.create();
      }
   }
}
