package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreDummyCriteria;
import net.minecraft.util.MathHelper;

public class ScoreHealthCriteria extends ScoreDummyCriteria {
   public ScoreHealthCriteria(String p_i2312_1_) {
      super(p_i2312_1_);
   }

   public int func_96635_a(List<EntityPlayer> p_96635_1_) {
      float f = 0.0F;

      for(EntityPlayer entityplayer : p_96635_1_) {
         f += entityplayer.func_110143_aJ() + entityplayer.func_110139_bj();
      }

      if(p_96635_1_.size() > 0) {
         f /= (float)p_96635_1_.size();
      }

      return MathHelper.func_76123_f(f);
   }

   public boolean func_96637_b() {
      return true;
   }

   public IScoreObjectiveCriteria.EnumRenderType func_178790_c() {
      return IScoreObjectiveCriteria.EnumRenderType.HEARTS;
   }
}
