package net.minecraft.stats;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatisticsFile extends StatFileWriter {
   private static final Logger field_150889_b = LogManager.getLogger();
   private final MinecraftServer field_150890_c;
   private final File field_150887_d;
   private final Set<StatBase> field_150888_e = Sets.<StatBase>newHashSet();
   private int field_150885_f = -300;
   private boolean field_150886_g = false;

   public StatisticsFile(MinecraftServer p_i45306_1_, File p_i45306_2_) {
      this.field_150890_c = p_i45306_1_;
      this.field_150887_d = p_i45306_2_;
   }

   public void func_150882_a() {
      if(this.field_150887_d.isFile()) {
         try {
            this.field_150875_a.clear();
            this.field_150875_a.putAll(this.func_150881_a(FileUtils.readFileToString(this.field_150887_d)));
         } catch (IOException ioexception) {
            field_150889_b.error((String)("Couldn\'t read statistics file " + this.field_150887_d), (Throwable)ioexception);
         } catch (JsonParseException jsonparseexception) {
            field_150889_b.error((String)("Couldn\'t parse statistics file " + this.field_150887_d), (Throwable)jsonparseexception);
         }
      }

   }

   public void func_150883_b() {
      try {
         FileUtils.writeStringToFile(this.field_150887_d, func_150880_a(this.field_150875_a));
      } catch (IOException ioexception) {
         field_150889_b.error((String)"Couldn\'t save stats", (Throwable)ioexception);
      }

   }

   public void func_150873_a(EntityPlayer p_150873_1_, StatBase p_150873_2_, int p_150873_3_) {
      int i = p_150873_2_.func_75967_d()?this.func_77444_a(p_150873_2_):0;
      super.func_150873_a(p_150873_1_, p_150873_2_, p_150873_3_);
      this.field_150888_e.add(p_150873_2_);
      if(p_150873_2_.func_75967_d() && i == 0 && p_150873_3_ > 0) {
         this.field_150886_g = true;
         if(this.field_150890_c.func_147136_ar()) {
            this.field_150890_c.func_71203_ab().func_148539_a(new ChatComponentTranslation("chat.type.achievement", new Object[]{p_150873_1_.func_145748_c_(), p_150873_2_.func_150955_j()}));
         }
      }

      if(p_150873_2_.func_75967_d() && i > 0 && p_150873_3_ == 0) {
         this.field_150886_g = true;
         if(this.field_150890_c.func_147136_ar()) {
            this.field_150890_c.func_71203_ab().func_148539_a(new ChatComponentTranslation("chat.type.achievement.taken", new Object[]{p_150873_1_.func_145748_c_(), p_150873_2_.func_150955_j()}));
         }
      }

   }

   public Set<StatBase> func_150878_c() {
      Set<StatBase> set = Sets.newHashSet(this.field_150888_e);
      this.field_150888_e.clear();
      this.field_150886_g = false;
      return set;
   }

   public Map<StatBase, TupleIntJsonSerializable> func_150881_a(String p_150881_1_) {
      JsonElement jsonelement = (new JsonParser()).parse(p_150881_1_);
      if(!jsonelement.isJsonObject()) {
         return Maps.<StatBase, TupleIntJsonSerializable>newHashMap();
      } else {
         JsonObject jsonobject = jsonelement.getAsJsonObject();
         Map<StatBase, TupleIntJsonSerializable> map = Maps.<StatBase, TupleIntJsonSerializable>newHashMap();

         for(Entry<String, JsonElement> entry : jsonobject.entrySet()) {
            StatBase statbase = StatList.func_151177_a((String)entry.getKey());
            if(statbase != null) {
               TupleIntJsonSerializable tupleintjsonserializable = new TupleIntJsonSerializable();
               if(((JsonElement)entry.getValue()).isJsonPrimitive() && ((JsonElement)entry.getValue()).getAsJsonPrimitive().isNumber()) {
                  tupleintjsonserializable.func_151188_a(((JsonElement)entry.getValue()).getAsInt());
               } else if(((JsonElement)entry.getValue()).isJsonObject()) {
                  JsonObject jsonobject1 = ((JsonElement)entry.getValue()).getAsJsonObject();
                  if(jsonobject1.has("value") && jsonobject1.get("value").isJsonPrimitive() && jsonobject1.get("value").getAsJsonPrimitive().isNumber()) {
                     tupleintjsonserializable.func_151188_a(jsonobject1.getAsJsonPrimitive("value").getAsInt());
                  }

                  if(jsonobject1.has("progress") && statbase.func_150954_l() != null) {
                     try {
                        Constructor<? extends IJsonSerializable> constructor = statbase.func_150954_l().getConstructor(new Class[0]);
                        IJsonSerializable ijsonserializable = (IJsonSerializable)constructor.newInstance(new Object[0]);
                        ijsonserializable.func_152753_a(jsonobject1.get("progress"));
                        tupleintjsonserializable.func_151190_a(ijsonserializable);
                     } catch (Throwable throwable) {
                        field_150889_b.warn("Invalid statistic progress in " + this.field_150887_d, throwable);
                     }
                  }
               }

               map.put(statbase, tupleintjsonserializable);
            } else {
               field_150889_b.warn("Invalid statistic in " + this.field_150887_d + ": Don\'t know what " + (String)entry.getKey() + " is");
            }
         }

         return map;
      }
   }

   public static String func_150880_a(Map<StatBase, TupleIntJsonSerializable> p_150880_0_) {
      JsonObject jsonobject = new JsonObject();

      for(Entry<StatBase, TupleIntJsonSerializable> entry : p_150880_0_.entrySet()) {
         if(((TupleIntJsonSerializable)entry.getValue()).func_151187_b() != null) {
            JsonObject jsonobject1 = new JsonObject();
            jsonobject1.addProperty("value", (Number)Integer.valueOf(((TupleIntJsonSerializable)entry.getValue()).func_151189_a()));

            try {
               jsonobject1.add("progress", ((TupleIntJsonSerializable)entry.getValue()).func_151187_b().func_151003_a());
            } catch (Throwable throwable) {
               field_150889_b.warn("Couldn\'t save statistic " + ((StatBase)entry.getKey()).func_150951_e() + ": error serializing progress", throwable);
            }

            jsonobject.add(((StatBase)entry.getKey()).field_75975_e, jsonobject1);
         } else {
            jsonobject.addProperty(((StatBase)entry.getKey()).field_75975_e, (Number)Integer.valueOf(((TupleIntJsonSerializable)entry.getValue()).func_151189_a()));
         }
      }

      return jsonobject.toString();
   }

   public void func_150877_d() {
      for(StatBase statbase : this.field_150875_a.keySet()) {
         this.field_150888_e.add(statbase);
      }

   }

   public void func_150876_a(EntityPlayerMP p_150876_1_) {
      int i = this.field_150890_c.func_71259_af();
      Map<StatBase, Integer> map = Maps.<StatBase, Integer>newHashMap();
      if(this.field_150886_g || i - this.field_150885_f > 300) {
         this.field_150885_f = i;

         for(StatBase statbase : this.func_150878_c()) {
            map.put(statbase, Integer.valueOf(this.func_77444_a(statbase)));
         }
      }

      p_150876_1_.field_71135_a.func_147359_a(new S37PacketStatistics(map));
   }

   public void func_150884_b(EntityPlayerMP p_150884_1_) {
      Map<StatBase, Integer> map = Maps.<StatBase, Integer>newHashMap();

      for(Achievement achievement : AchievementList.field_76007_e) {
         if(this.func_77443_a(achievement)) {
            map.put(achievement, Integer.valueOf(this.func_77444_a(achievement)));
            this.field_150888_e.remove(achievement);
         }
      }

      p_150884_1_.field_71135_a.func_147359_a(new S37PacketStatistics(map));
   }

   public boolean func_150879_e() {
      return this.field_150886_g;
   }
}
