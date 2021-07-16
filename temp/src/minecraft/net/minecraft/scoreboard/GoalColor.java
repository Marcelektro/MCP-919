package net.minecraft.scoreboard;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.EnumChatFormatting;

public class GoalColor implements IScoreObjectiveCriteria {
   private final String field_178794_j;

   public GoalColor(String p_i45549_1_, EnumChatFormatting p_i45549_2_) {
      this.field_178794_j = p_i45549_1_ + p_i45549_2_.func_96297_d();
      IScoreObjectiveCriteria.field_96643_a.put(this.field_178794_j, this);
   }

   public String func_96636_a() {
      return this.field_178794_j;
   }

   public int func_96635_a(List<EntityPlayer> p_96635_1_) {
      return 0;
   }

   public boolean func_96637_b() {
      return false;
   }

   public IScoreObjectiveCriteria.EnumRenderType func_178790_c() {
      return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
   }
}
