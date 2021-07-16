package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;

public enum SoundCategory {
   MASTER("master", 0),
   MUSIC("music", 1),
   RECORDS("record", 2),
   WEATHER("weather", 3),
   BLOCKS("block", 4),
   MOBS("hostile", 5),
   ANIMALS("neutral", 6),
   PLAYERS("player", 7),
   AMBIENT("ambient", 8);

   private static final Map<String, SoundCategory> field_147168_j = Maps.<String, SoundCategory>newHashMap();
   private static final Map<Integer, SoundCategory> field_147169_k = Maps.<Integer, SoundCategory>newHashMap();
   private final String field_147166_l;
   private final int field_147167_m;

   private SoundCategory(String p_i45126_3_, int p_i45126_4_) {
      this.field_147166_l = p_i45126_3_;
      this.field_147167_m = p_i45126_4_;
   }

   public String func_147155_a() {
      return this.field_147166_l;
   }

   public int func_147156_b() {
      return this.field_147167_m;
   }

   public static SoundCategory func_147154_a(String p_147154_0_) {
      return (SoundCategory)field_147168_j.get(p_147154_0_);
   }

   static {
      for(SoundCategory soundcategory : values()) {
         if(field_147168_j.containsKey(soundcategory.func_147155_a()) || field_147169_k.containsKey(Integer.valueOf(soundcategory.func_147156_b()))) {
            throw new Error("Clash in Sound Category ID & Name pools! Cannot insert " + soundcategory);
         }

         field_147168_j.put(soundcategory.func_147155_a(), soundcategory);
         field_147169_k.put(Integer.valueOf(soundcategory.func_147156_b()), soundcategory);
      }

   }
}
