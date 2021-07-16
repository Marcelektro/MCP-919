package net.minecraft.world;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;

public final class WorldSettings {
   private final long field_77174_a;
   private final WorldSettings.GameType field_77172_b;
   private final boolean field_77173_c;
   private final boolean field_77170_d;
   private final WorldType field_77171_e;
   private boolean field_77168_f;
   private boolean field_77169_g;
   private String field_82751_h;

   public WorldSettings(long p_i1957_1_, WorldSettings.GameType p_i1957_3_, boolean p_i1957_4_, boolean p_i1957_5_, WorldType p_i1957_6_) {
      this.field_82751_h = "";
      this.field_77174_a = p_i1957_1_;
      this.field_77172_b = p_i1957_3_;
      this.field_77173_c = p_i1957_4_;
      this.field_77170_d = p_i1957_5_;
      this.field_77171_e = p_i1957_6_;
   }

   public WorldSettings(WorldInfo p_i1958_1_) {
      this(p_i1958_1_.func_76063_b(), p_i1958_1_.func_76077_q(), p_i1958_1_.func_76089_r(), p_i1958_1_.func_76093_s(), p_i1958_1_.func_76067_t());
   }

   public WorldSettings func_77159_a() {
      this.field_77169_g = true;
      return this;
   }

   public WorldSettings func_77166_b() {
      this.field_77168_f = true;
      return this;
   }

   public WorldSettings func_82750_a(String p_82750_1_) {
      this.field_82751_h = p_82750_1_;
      return this;
   }

   public boolean func_77167_c() {
      return this.field_77169_g;
   }

   public long func_77160_d() {
      return this.field_77174_a;
   }

   public WorldSettings.GameType func_77162_e() {
      return this.field_77172_b;
   }

   public boolean func_77158_f() {
      return this.field_77170_d;
   }

   public boolean func_77164_g() {
      return this.field_77173_c;
   }

   public WorldType func_77165_h() {
      return this.field_77171_e;
   }

   public boolean func_77163_i() {
      return this.field_77168_f;
   }

   public static WorldSettings.GameType func_77161_a(int p_77161_0_) {
      return WorldSettings.GameType.func_77146_a(p_77161_0_);
   }

   public String func_82749_j() {
      return this.field_82751_h;
   }

   public static enum GameType {
      NOT_SET(-1, ""),
      SURVIVAL(0, "survival"),
      CREATIVE(1, "creative"),
      ADVENTURE(2, "adventure"),
      SPECTATOR(3, "spectator");

      int field_77154_e;
      String field_77151_f;

      private GameType(int p_i1956_3_, String p_i1956_4_) {
         this.field_77154_e = p_i1956_3_;
         this.field_77151_f = p_i1956_4_;
      }

      public int func_77148_a() {
         return this.field_77154_e;
      }

      public String func_77149_b() {
         return this.field_77151_f;
      }

      public void func_77147_a(PlayerCapabilities p_77147_1_) {
         if(this == CREATIVE) {
            p_77147_1_.field_75101_c = true;
            p_77147_1_.field_75098_d = true;
            p_77147_1_.field_75102_a = true;
         } else if(this == SPECTATOR) {
            p_77147_1_.field_75101_c = true;
            p_77147_1_.field_75098_d = false;
            p_77147_1_.field_75102_a = true;
            p_77147_1_.field_75100_b = true;
         } else {
            p_77147_1_.field_75101_c = false;
            p_77147_1_.field_75098_d = false;
            p_77147_1_.field_75102_a = false;
            p_77147_1_.field_75100_b = false;
         }

         p_77147_1_.field_75099_e = !this.func_82752_c();
      }

      public boolean func_82752_c() {
         return this == ADVENTURE || this == SPECTATOR;
      }

      public boolean func_77145_d() {
         return this == CREATIVE;
      }

      public boolean func_77144_e() {
         return this == SURVIVAL || this == ADVENTURE;
      }

      public static WorldSettings.GameType func_77146_a(int p_77146_0_) {
         for(WorldSettings.GameType worldsettings$gametype : values()) {
            if(worldsettings$gametype.field_77154_e == p_77146_0_) {
               return worldsettings$gametype;
            }
         }

         return SURVIVAL;
      }

      public static WorldSettings.GameType func_77142_a(String p_77142_0_) {
         for(WorldSettings.GameType worldsettings$gametype : values()) {
            if(worldsettings$gametype.field_77151_f.equals(p_77142_0_)) {
               return worldsettings$gametype;
            }
         }

         return SURVIVAL;
      }
   }
}
