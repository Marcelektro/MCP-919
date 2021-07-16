package net.minecraft.client.stream;

import net.minecraft.client.stream.Metadata;
import net.minecraft.stats.Achievement;

public class MetadataAchievement extends Metadata {
   public MetadataAchievement(Achievement p_i1032_1_) {
      super("achievement");
      this.func_152808_a("achievement_id", p_i1032_1_.field_75975_e);
      this.func_152808_a("achievement_name", p_i1032_1_.func_150951_e().func_150260_c());
      this.func_152808_a("achievement_description", p_i1032_1_.func_75989_e());
      this.func_152807_a("Achievement \'" + p_i1032_1_.func_150951_e().func_150260_c() + "\' obtained!");
   }
}
