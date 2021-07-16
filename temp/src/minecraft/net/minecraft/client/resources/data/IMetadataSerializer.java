package net.minecraft.client.resources.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSectionSerializer;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;

public class IMetadataSerializer {
   private final IRegistry<String, IMetadataSerializer.Registration<? extends IMetadataSection>> field_110508_a = new RegistrySimple();
   private final GsonBuilder field_110506_b = new GsonBuilder();
   private Gson field_110507_c;

   public IMetadataSerializer() {
      this.field_110506_b.registerTypeHierarchyAdapter(IChatComponent.class, new IChatComponent.Serializer());
      this.field_110506_b.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
      this.field_110506_b.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
   }

   public <T extends IMetadataSection> void func_110504_a(IMetadataSectionSerializer<T> p_110504_1_, Class<T> p_110504_2_) {
      this.field_110508_a.func_82595_a(p_110504_1_.func_110483_a(), new IMetadataSerializer.Registration(p_110504_1_, p_110504_2_));
      this.field_110506_b.registerTypeAdapter(p_110504_2_, p_110504_1_);
      this.field_110507_c = null;
   }

   public <T extends IMetadataSection> T func_110503_a(String p_110503_1_, JsonObject p_110503_2_) {
      if(p_110503_1_ == null) {
         throw new IllegalArgumentException("Metadata section name cannot be null");
      } else if(!p_110503_2_.has(p_110503_1_)) {
         return (T)null;
      } else if(!p_110503_2_.get(p_110503_1_).isJsonObject()) {
         throw new IllegalArgumentException("Invalid metadata for \'" + p_110503_1_ + "\' - expected object, found " + p_110503_2_.get(p_110503_1_));
      } else {
         IMetadataSerializer.Registration<?> registration = (IMetadataSerializer.Registration)this.field_110508_a.func_82594_a(p_110503_1_);
         if(registration == null) {
            throw new IllegalArgumentException("Don\'t know how to handle metadata section \'" + p_110503_1_ + "\'");
         } else {
            return (T)((IMetadataSection)this.func_110505_a().fromJson((JsonElement)p_110503_2_.getAsJsonObject(p_110503_1_), registration.field_110500_b));
         }
      }
   }

   private Gson func_110505_a() {
      if(this.field_110507_c == null) {
         this.field_110507_c = this.field_110506_b.create();
      }

      return this.field_110507_c;
   }

   class Registration<T extends IMetadataSection> {
      final IMetadataSectionSerializer<T> field_110502_a;
      final Class<T> field_110500_b;

      private Registration(IMetadataSectionSerializer<T> p_i1305_2_, Class<T> p_i1305_3_) {
         this.field_110502_a = p_i1305_2_;
         this.field_110500_b = p_i1305_3_;
      }
   }
}
