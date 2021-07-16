package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public abstract class Team {
   public boolean func_142054_a(Team p_142054_1_) {
      return p_142054_1_ == null?false:this == p_142054_1_;
   }

   public abstract String func_96661_b();

   public abstract String func_142053_d(String var1);

   public abstract boolean func_98297_h();

   public abstract boolean func_96665_g();

   public abstract Team.EnumVisible func_178770_i();

   public abstract Collection<String> func_96670_d();

   public abstract Team.EnumVisible func_178771_j();

   public static enum EnumVisible {
      ALWAYS("always", 0),
      NEVER("never", 1),
      HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
      HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);

      private static Map<String, Team.EnumVisible> field_178828_g = Maps.<String, Team.EnumVisible>newHashMap();
      public final String field_178830_e;
      public final int field_178827_f;

      public static String[] func_178825_a() {
         return (String[])field_178828_g.keySet().toArray(new String[field_178828_g.size()]);
      }

      public static Team.EnumVisible func_178824_a(String p_178824_0_) {
         return (Team.EnumVisible)field_178828_g.get(p_178824_0_);
      }

      private EnumVisible(String p_i45550_3_, int p_i45550_4_) {
         this.field_178830_e = p_i45550_3_;
         this.field_178827_f = p_i45550_4_;
      }

      static {
         for(Team.EnumVisible team$enumvisible : values()) {
            field_178828_g.put(team$enumvisible.field_178830_e, team$enumvisible);
         }

      }
   }
}
