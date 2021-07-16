package net.minecraft.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonUtils;

public class ServerStatusResponse {
   private IChatComponent field_151326_a;
   private ServerStatusResponse.PlayerCountData field_151324_b;
   private ServerStatusResponse.MinecraftProtocolVersionIdentifier field_151325_c;
   private String field_151323_d;

   public IChatComponent func_151317_a() {
      return this.field_151326_a;
   }

   public void func_151315_a(IChatComponent p_151315_1_) {
      this.field_151326_a = p_151315_1_;
   }

   public ServerStatusResponse.PlayerCountData func_151318_b() {
      return this.field_151324_b;
   }

   public void func_151319_a(ServerStatusResponse.PlayerCountData p_151319_1_) {
      this.field_151324_b = p_151319_1_;
   }

   public ServerStatusResponse.MinecraftProtocolVersionIdentifier func_151322_c() {
      return this.field_151325_c;
   }

   public void func_151321_a(ServerStatusResponse.MinecraftProtocolVersionIdentifier p_151321_1_) {
      this.field_151325_c = p_151321_1_;
   }

   public void func_151320_a(String p_151320_1_) {
      this.field_151323_d = p_151320_1_;
   }

   public String func_151316_d() {
      return this.field_151323_d;
   }

   public static class MinecraftProtocolVersionIdentifier {
      private final String field_151306_a;
      private final int field_151305_b;

      public MinecraftProtocolVersionIdentifier(String p_i45275_1_, int p_i45275_2_) {
         this.field_151306_a = p_i45275_1_;
         this.field_151305_b = p_i45275_2_;
      }

      public String func_151303_a() {
         return this.field_151306_a;
      }

      public int func_151304_b() {
         return this.field_151305_b;
      }

      public static class Serializer implements JsonDeserializer<ServerStatusResponse.MinecraftProtocolVersionIdentifier>, JsonSerializer<ServerStatusResponse.MinecraftProtocolVersionIdentifier> {
         public ServerStatusResponse.MinecraftProtocolVersionIdentifier deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = JsonUtils.func_151210_l(p_deserialize_1_, "version");
            return new ServerStatusResponse.MinecraftProtocolVersionIdentifier(JsonUtils.func_151200_h(jsonobject, "name"), JsonUtils.func_151203_m(jsonobject, "protocol"));
         }

         public JsonElement serialize(ServerStatusResponse.MinecraftProtocolVersionIdentifier p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("name", p_serialize_1_.func_151303_a());
            jsonobject.addProperty("protocol", (Number)Integer.valueOf(p_serialize_1_.func_151304_b()));
            return jsonobject;
         }
      }
   }

   public static class PlayerCountData {
      private final int field_151336_a;
      private final int field_151334_b;
      private GameProfile[] field_151335_c;

      public PlayerCountData(int p_i45274_1_, int p_i45274_2_) {
         this.field_151336_a = p_i45274_1_;
         this.field_151334_b = p_i45274_2_;
      }

      public int func_151332_a() {
         return this.field_151336_a;
      }

      public int func_151333_b() {
         return this.field_151334_b;
      }

      public GameProfile[] func_151331_c() {
         return this.field_151335_c;
      }

      public void func_151330_a(GameProfile[] p_151330_1_) {
         this.field_151335_c = p_151330_1_;
      }

      public static class Serializer implements JsonDeserializer<ServerStatusResponse.PlayerCountData>, JsonSerializer<ServerStatusResponse.PlayerCountData> {
         public ServerStatusResponse.PlayerCountData deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = JsonUtils.func_151210_l(p_deserialize_1_, "players");
            ServerStatusResponse.PlayerCountData serverstatusresponse$playercountdata = new ServerStatusResponse.PlayerCountData(JsonUtils.func_151203_m(jsonobject, "max"), JsonUtils.func_151203_m(jsonobject, "online"));
            if(JsonUtils.func_151202_d(jsonobject, "sample")) {
               JsonArray jsonarray = JsonUtils.func_151214_t(jsonobject, "sample");
               if(jsonarray.size() > 0) {
                  GameProfile[] agameprofile = new GameProfile[jsonarray.size()];

                  for(int i = 0; i < agameprofile.length; ++i) {
                     JsonObject jsonobject1 = JsonUtils.func_151210_l(jsonarray.get(i), "player[" + i + "]");
                     String s = JsonUtils.func_151200_h(jsonobject1, "id");
                     agameprofile[i] = new GameProfile(UUID.fromString(s), JsonUtils.func_151200_h(jsonobject1, "name"));
                  }

                  serverstatusresponse$playercountdata.func_151330_a(agameprofile);
               }
            }

            return serverstatusresponse$playercountdata;
         }

         public JsonElement serialize(ServerStatusResponse.PlayerCountData p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("max", (Number)Integer.valueOf(p_serialize_1_.func_151332_a()));
            jsonobject.addProperty("online", (Number)Integer.valueOf(p_serialize_1_.func_151333_b()));
            if(p_serialize_1_.func_151331_c() != null && p_serialize_1_.func_151331_c().length > 0) {
               JsonArray jsonarray = new JsonArray();

               for(int i = 0; i < p_serialize_1_.func_151331_c().length; ++i) {
                  JsonObject jsonobject1 = new JsonObject();
                  UUID uuid = p_serialize_1_.func_151331_c()[i].getId();
                  jsonobject1.addProperty("id", uuid == null?"":uuid.toString());
                  jsonobject1.addProperty("name", p_serialize_1_.func_151331_c()[i].getName());
                  jsonarray.add(jsonobject1);
               }

               jsonobject.add("sample", jsonarray);
            }

            return jsonobject;
         }
      }
   }

   public static class Serializer implements JsonDeserializer<ServerStatusResponse>, JsonSerializer<ServerStatusResponse> {
      public ServerStatusResponse deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         JsonObject jsonobject = JsonUtils.func_151210_l(p_deserialize_1_, "status");
         ServerStatusResponse serverstatusresponse = new ServerStatusResponse();
         if(jsonobject.has("description")) {
            serverstatusresponse.func_151315_a((IChatComponent)p_deserialize_3_.deserialize(jsonobject.get("description"), IChatComponent.class));
         }

         if(jsonobject.has("players")) {
            serverstatusresponse.func_151319_a((ServerStatusResponse.PlayerCountData)p_deserialize_3_.deserialize(jsonobject.get("players"), ServerStatusResponse.PlayerCountData.class));
         }

         if(jsonobject.has("version")) {
            serverstatusresponse.func_151321_a((ServerStatusResponse.MinecraftProtocolVersionIdentifier)p_deserialize_3_.deserialize(jsonobject.get("version"), ServerStatusResponse.MinecraftProtocolVersionIdentifier.class));
         }

         if(jsonobject.has("favicon")) {
            serverstatusresponse.func_151320_a(JsonUtils.func_151200_h(jsonobject, "favicon"));
         }

         return serverstatusresponse;
      }

      public JsonElement serialize(ServerStatusResponse p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         JsonObject jsonobject = new JsonObject();
         if(p_serialize_1_.func_151317_a() != null) {
            jsonobject.add("description", p_serialize_3_.serialize(p_serialize_1_.func_151317_a()));
         }

         if(p_serialize_1_.func_151318_b() != null) {
            jsonobject.add("players", p_serialize_3_.serialize(p_serialize_1_.func_151318_b()));
         }

         if(p_serialize_1_.func_151322_c() != null) {
            jsonobject.add("version", p_serialize_3_.serialize(p_serialize_1_.func_151322_c()));
         }

         if(p_serialize_1_.func_151316_d() != null) {
            jsonobject.addProperty("favicon", p_serialize_1_.func_151316_d());
         }

         return jsonobject;
      }
   }
}
