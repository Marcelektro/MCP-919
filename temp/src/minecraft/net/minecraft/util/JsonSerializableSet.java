package net.minecraft.util;

import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.Set;
import net.minecraft.util.IJsonSerializable;

public class JsonSerializableSet extends ForwardingSet<String> implements IJsonSerializable {
   private final Set<String> field_151004_a = Sets.<String>newHashSet();

   public void func_152753_a(JsonElement p_152753_1_) {
      if(p_152753_1_.isJsonArray()) {
         for(JsonElement jsonelement : p_152753_1_.getAsJsonArray()) {
            this.add(jsonelement.getAsString());
         }
      }

   }

   public JsonElement func_151003_a() {
      JsonArray jsonarray = new JsonArray();

      for(String s : this) {
         jsonarray.add(new JsonPrimitive(s));
      }

      return jsonarray;
   }

   protected Set<String> delegate() {
      return this.field_151004_a;
   }
}
