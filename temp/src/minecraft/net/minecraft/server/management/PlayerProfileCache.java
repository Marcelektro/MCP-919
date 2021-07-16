package net.minecraft.server.management;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;

public class PlayerProfileCache {
   public static final SimpleDateFormat field_152659_a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
   private final Map<String, PlayerProfileCache.ProfileEntry> field_152661_c = Maps.<String, PlayerProfileCache.ProfileEntry>newHashMap();
   private final Map<UUID, PlayerProfileCache.ProfileEntry> field_152662_d = Maps.<UUID, PlayerProfileCache.ProfileEntry>newHashMap();
   private final LinkedList<GameProfile> field_152663_e = Lists.<GameProfile>newLinkedList();
   private final MinecraftServer field_152664_f;
   protected final Gson field_152660_b;
   private final File field_152665_g;
   private static final ParameterizedType field_152666_h = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{PlayerProfileCache.ProfileEntry.class};
      }

      public Type getRawType() {
         return List.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };

   public PlayerProfileCache(MinecraftServer p_i1171_1_, File p_i1171_2_) {
      this.field_152664_f = p_i1171_1_;
      this.field_152665_g = p_i1171_2_;
      GsonBuilder gsonbuilder = new GsonBuilder();
      gsonbuilder.registerTypeHierarchyAdapter(PlayerProfileCache.ProfileEntry.class, new PlayerProfileCache.Serializer());
      this.field_152660_b = gsonbuilder.create();
      this.func_152657_b();
   }

   private static GameProfile func_152650_a(MinecraftServer p_152650_0_, String p_152650_1_) {
      final GameProfile[] agameprofile = new GameProfile[1];
      ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
         public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
            agameprofile[0] = p_onProfileLookupSucceeded_1_;
         }

         public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
            agameprofile[0] = null;
         }
      };
      p_152650_0_.func_152359_aw().findProfilesByNames(new String[]{p_152650_1_}, Agent.MINECRAFT, profilelookupcallback);
      if(!p_152650_0_.func_71266_T() && agameprofile[0] == null) {
         UUID uuid = EntityPlayer.func_146094_a(new GameProfile((UUID)null, p_152650_1_));
         GameProfile gameprofile = new GameProfile(uuid, p_152650_1_);
         profilelookupcallback.onProfileLookupSucceeded(gameprofile);
      }

      return agameprofile[0];
   }

   public void func_152649_a(GameProfile p_152649_1_) {
      this.func_152651_a(p_152649_1_, (Date)null);
   }

   private void func_152651_a(GameProfile p_152651_1_, Date p_152651_2_) {
      UUID uuid = p_152651_1_.getId();
      if(p_152651_2_ == null) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         calendar.add(2, 1);
         p_152651_2_ = calendar.getTime();
      }

      String s = p_152651_1_.getName().toLowerCase(Locale.ROOT);
      PlayerProfileCache.ProfileEntry playerprofilecache$profileentry = new PlayerProfileCache.ProfileEntry(p_152651_1_, p_152651_2_);
      if(this.field_152662_d.containsKey(uuid)) {
         PlayerProfileCache.ProfileEntry playerprofilecache$profileentry1 = (PlayerProfileCache.ProfileEntry)this.field_152662_d.get(uuid);
         this.field_152661_c.remove(playerprofilecache$profileentry1.func_152668_a().getName().toLowerCase(Locale.ROOT));
         this.field_152663_e.remove(p_152651_1_);
      }

      this.field_152661_c.put(p_152651_1_.getName().toLowerCase(Locale.ROOT), playerprofilecache$profileentry);
      this.field_152662_d.put(uuid, playerprofilecache$profileentry);
      this.field_152663_e.addFirst(p_152651_1_);
      this.func_152658_c();
   }

   public GameProfile func_152655_a(String p_152655_1_) {
      String s = p_152655_1_.toLowerCase(Locale.ROOT);
      PlayerProfileCache.ProfileEntry playerprofilecache$profileentry = (PlayerProfileCache.ProfileEntry)this.field_152661_c.get(s);
      if(playerprofilecache$profileentry != null && (new Date()).getTime() >= playerprofilecache$profileentry.field_152673_c.getTime()) {
         this.field_152662_d.remove(playerprofilecache$profileentry.func_152668_a().getId());
         this.field_152661_c.remove(playerprofilecache$profileentry.func_152668_a().getName().toLowerCase(Locale.ROOT));
         this.field_152663_e.remove(playerprofilecache$profileentry.func_152668_a());
         playerprofilecache$profileentry = null;
      }

      if(playerprofilecache$profileentry != null) {
         GameProfile gameprofile = playerprofilecache$profileentry.func_152668_a();
         this.field_152663_e.remove(gameprofile);
         this.field_152663_e.addFirst(gameprofile);
      } else {
         GameProfile gameprofile1 = func_152650_a(this.field_152664_f, s);
         if(gameprofile1 != null) {
            this.func_152649_a(gameprofile1);
            playerprofilecache$profileentry = (PlayerProfileCache.ProfileEntry)this.field_152661_c.get(s);
         }
      }

      this.func_152658_c();
      return playerprofilecache$profileentry == null?null:playerprofilecache$profileentry.func_152668_a();
   }

   public String[] func_152654_a() {
      List<String> list = Lists.newArrayList(this.field_152661_c.keySet());
      return (String[])list.toArray(new String[list.size()]);
   }

   public GameProfile func_152652_a(UUID p_152652_1_) {
      PlayerProfileCache.ProfileEntry playerprofilecache$profileentry = (PlayerProfileCache.ProfileEntry)this.field_152662_d.get(p_152652_1_);
      return playerprofilecache$profileentry == null?null:playerprofilecache$profileentry.func_152668_a();
   }

   private PlayerProfileCache.ProfileEntry func_152653_b(UUID p_152653_1_) {
      PlayerProfileCache.ProfileEntry playerprofilecache$profileentry = (PlayerProfileCache.ProfileEntry)this.field_152662_d.get(p_152653_1_);
      if(playerprofilecache$profileentry != null) {
         GameProfile gameprofile = playerprofilecache$profileentry.func_152668_a();
         this.field_152663_e.remove(gameprofile);
         this.field_152663_e.addFirst(gameprofile);
      }

      return playerprofilecache$profileentry;
   }

   public void func_152657_b() {
      BufferedReader bufferedreader = null;

      try {
         bufferedreader = Files.newReader(this.field_152665_g, Charsets.UTF_8);
         List<PlayerProfileCache.ProfileEntry> list = (List)this.field_152660_b.fromJson((Reader)bufferedreader, field_152666_h);
         this.field_152661_c.clear();
         this.field_152662_d.clear();
         this.field_152663_e.clear();

         for(PlayerProfileCache.ProfileEntry playerprofilecache$profileentry : Lists.reverse(list)) {
            if(playerprofilecache$profileentry != null) {
               this.func_152651_a(playerprofilecache$profileentry.func_152668_a(), playerprofilecache$profileentry.func_152670_b());
            }
         }
      } catch (FileNotFoundException var9) {
         ;
      } catch (JsonParseException var10) {
         ;
      } finally {
         IOUtils.closeQuietly((Reader)bufferedreader);
      }

   }

   public void func_152658_c() {
      String s = this.field_152660_b.toJson((Object)this.func_152656_a(1000));
      BufferedWriter bufferedwriter = null;

      try {
         bufferedwriter = Files.newWriter(this.field_152665_g, Charsets.UTF_8);
         bufferedwriter.write(s);
         return;
      } catch (FileNotFoundException var8) {
         ;
      } catch (IOException var9) {
         return;
      } finally {
         IOUtils.closeQuietly((Writer)bufferedwriter);
      }

   }

   private List<PlayerProfileCache.ProfileEntry> func_152656_a(int p_152656_1_) {
      ArrayList<PlayerProfileCache.ProfileEntry> arraylist = Lists.<PlayerProfileCache.ProfileEntry>newArrayList();

      for(GameProfile gameprofile : Lists.newArrayList(Iterators.limit(this.field_152663_e.iterator(), p_152656_1_))) {
         PlayerProfileCache.ProfileEntry playerprofilecache$profileentry = this.func_152653_b(gameprofile.getId());
         if(playerprofilecache$profileentry != null) {
            arraylist.add(playerprofilecache$profileentry);
         }
      }

      return arraylist;
   }

   class ProfileEntry {
      private final GameProfile field_152672_b;
      private final Date field_152673_c;

      private ProfileEntry(GameProfile p_i46333_2_, Date p_i46333_3_) {
         this.field_152672_b = p_i46333_2_;
         this.field_152673_c = p_i46333_3_;
      }

      public GameProfile func_152668_a() {
         return this.field_152672_b;
      }

      public Date func_152670_b() {
         return this.field_152673_c;
      }
   }

   class Serializer implements JsonDeserializer<PlayerProfileCache.ProfileEntry>, JsonSerializer<PlayerProfileCache.ProfileEntry> {
      private Serializer() {
      }

      public JsonElement serialize(PlayerProfileCache.ProfileEntry p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
         JsonObject jsonobject = new JsonObject();
         jsonobject.addProperty("name", p_serialize_1_.func_152668_a().getName());
         UUID uuid = p_serialize_1_.func_152668_a().getId();
         jsonobject.addProperty("uuid", uuid == null?"":uuid.toString());
         jsonobject.addProperty("expiresOn", PlayerProfileCache.field_152659_a.format(p_serialize_1_.func_152670_b()));
         return jsonobject;
      }

      public PlayerProfileCache.ProfileEntry deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
         if(p_deserialize_1_.isJsonObject()) {
            JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
            JsonElement jsonelement = jsonobject.get("name");
            JsonElement jsonelement1 = jsonobject.get("uuid");
            JsonElement jsonelement2 = jsonobject.get("expiresOn");
            if(jsonelement != null && jsonelement1 != null) {
               String s = jsonelement1.getAsString();
               String s1 = jsonelement.getAsString();
               Date date = null;
               if(jsonelement2 != null) {
                  try {
                     date = PlayerProfileCache.field_152659_a.parse(jsonelement2.getAsString());
                  } catch (ParseException var14) {
                     date = null;
                  }
               }

               if(s1 != null && s != null) {
                  UUID uuid;
                  try {
                     uuid = UUID.fromString(s);
                  } catch (Throwable var13) {
                     return null;
                  }

                  PlayerProfileCache.ProfileEntry playerprofilecache$profileentry = PlayerProfileCache.this.new ProfileEntry(new GameProfile(uuid, s1), date);
                  return playerprofilecache$profileentry;
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }
}
