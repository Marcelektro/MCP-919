package net.minecraft.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.server.MinecraftServer;

public class ServerScoreboard extends Scoreboard {
   private final MinecraftServer field_96555_a;
   private final Set<ScoreObjective> field_96553_b = Sets.<ScoreObjective>newHashSet();
   private ScoreboardSaveData field_96554_c;

   public ServerScoreboard(MinecraftServer p_i1501_1_) {
      this.field_96555_a = p_i1501_1_;
   }

   public void func_96536_a(Score p_96536_1_) {
      super.func_96536_a(p_96536_1_);
      if(this.field_96553_b.contains(p_96536_1_.func_96645_d())) {
         this.field_96555_a.func_71203_ab().func_148540_a(new S3CPacketUpdateScore(p_96536_1_));
      }

      this.func_96551_b();
   }

   public void func_96516_a(String p_96516_1_) {
      super.func_96516_a(p_96516_1_);
      this.field_96555_a.func_71203_ab().func_148540_a(new S3CPacketUpdateScore(p_96516_1_));
      this.func_96551_b();
   }

   public void func_178820_a(String p_178820_1_, ScoreObjective p_178820_2_) {
      super.func_178820_a(p_178820_1_, p_178820_2_);
      this.field_96555_a.func_71203_ab().func_148540_a(new S3CPacketUpdateScore(p_178820_1_, p_178820_2_));
      this.func_96551_b();
   }

   public void func_96530_a(int p_96530_1_, ScoreObjective p_96530_2_) {
      ScoreObjective scoreobjective = this.func_96539_a(p_96530_1_);
      super.func_96530_a(p_96530_1_, p_96530_2_);
      if(scoreobjective != p_96530_2_ && scoreobjective != null) {
         if(this.func_96552_h(scoreobjective) > 0) {
            this.field_96555_a.func_71203_ab().func_148540_a(new S3DPacketDisplayScoreboard(p_96530_1_, p_96530_2_));
         } else {
            this.func_96546_g(scoreobjective);
         }
      }

      if(p_96530_2_ != null) {
         if(this.field_96553_b.contains(p_96530_2_)) {
            this.field_96555_a.func_71203_ab().func_148540_a(new S3DPacketDisplayScoreboard(p_96530_1_, p_96530_2_));
         } else {
            this.func_96549_e(p_96530_2_);
         }
      }

      this.func_96551_b();
   }

   public boolean func_151392_a(String p_151392_1_, String p_151392_2_) {
      if(super.func_151392_a(p_151392_1_, p_151392_2_)) {
         ScorePlayerTeam scoreplayerteam = this.func_96508_e(p_151392_2_);
         this.field_96555_a.func_71203_ab().func_148540_a(new S3EPacketTeams(scoreplayerteam, Arrays.asList(new String[]{p_151392_1_}), 3));
         this.func_96551_b();
         return true;
      } else {
         return false;
      }
   }

   public void func_96512_b(String p_96512_1_, ScorePlayerTeam p_96512_2_) {
      super.func_96512_b(p_96512_1_, p_96512_2_);
      this.field_96555_a.func_71203_ab().func_148540_a(new S3EPacketTeams(p_96512_2_, Arrays.asList(new String[]{p_96512_1_}), 4));
      this.func_96551_b();
   }

   public void func_96522_a(ScoreObjective p_96522_1_) {
      super.func_96522_a(p_96522_1_);
      this.func_96551_b();
   }

   public void func_96532_b(ScoreObjective p_96532_1_) {
      super.func_96532_b(p_96532_1_);
      if(this.field_96553_b.contains(p_96532_1_)) {
         this.field_96555_a.func_71203_ab().func_148540_a(new S3BPacketScoreboardObjective(p_96532_1_, 2));
      }

      this.func_96551_b();
   }

   public void func_96533_c(ScoreObjective p_96533_1_) {
      super.func_96533_c(p_96533_1_);
      if(this.field_96553_b.contains(p_96533_1_)) {
         this.func_96546_g(p_96533_1_);
      }

      this.func_96551_b();
   }

   public void func_96523_a(ScorePlayerTeam p_96523_1_) {
      super.func_96523_a(p_96523_1_);
      this.field_96555_a.func_71203_ab().func_148540_a(new S3EPacketTeams(p_96523_1_, 0));
      this.func_96551_b();
   }

   public void func_96538_b(ScorePlayerTeam p_96538_1_) {
      super.func_96538_b(p_96538_1_);
      this.field_96555_a.func_71203_ab().func_148540_a(new S3EPacketTeams(p_96538_1_, 2));
      this.func_96551_b();
   }

   public void func_96513_c(ScorePlayerTeam p_96513_1_) {
      super.func_96513_c(p_96513_1_);
      this.field_96555_a.func_71203_ab().func_148540_a(new S3EPacketTeams(p_96513_1_, 1));
      this.func_96551_b();
   }

   public void func_96547_a(ScoreboardSaveData p_96547_1_) {
      this.field_96554_c = p_96547_1_;
   }

   protected void func_96551_b() {
      if(this.field_96554_c != null) {
         this.field_96554_c.func_76185_a();
      }

   }

   public List<Packet> func_96550_d(ScoreObjective p_96550_1_) {
      List<Packet> list = Lists.<Packet>newArrayList();
      list.add(new S3BPacketScoreboardObjective(p_96550_1_, 0));

      for(int i = 0; i < 19; ++i) {
         if(this.func_96539_a(i) == p_96550_1_) {
            list.add(new S3DPacketDisplayScoreboard(i, p_96550_1_));
         }
      }

      for(Score score : this.func_96534_i(p_96550_1_)) {
         list.add(new S3CPacketUpdateScore(score));
      }

      return list;
   }

   public void func_96549_e(ScoreObjective p_96549_1_) {
      List<Packet> list = this.func_96550_d(p_96549_1_);

      for(EntityPlayerMP entityplayermp : this.field_96555_a.func_71203_ab().func_181057_v()) {
         for(Packet packet : list) {
            entityplayermp.field_71135_a.func_147359_a(packet);
         }
      }

      this.field_96553_b.add(p_96549_1_);
   }

   public List<Packet> func_96548_f(ScoreObjective p_96548_1_) {
      List<Packet> list = Lists.<Packet>newArrayList();
      list.add(new S3BPacketScoreboardObjective(p_96548_1_, 1));

      for(int i = 0; i < 19; ++i) {
         if(this.func_96539_a(i) == p_96548_1_) {
            list.add(new S3DPacketDisplayScoreboard(i, p_96548_1_));
         }
      }

      return list;
   }

   public void func_96546_g(ScoreObjective p_96546_1_) {
      List<Packet> list = this.func_96548_f(p_96546_1_);

      for(EntityPlayerMP entityplayermp : this.field_96555_a.func_71203_ab().func_181057_v()) {
         for(Packet packet : list) {
            entityplayermp.field_71135_a.func_147359_a(packet);
         }
      }

      this.field_96553_b.remove(p_96546_1_);
   }

   public int func_96552_h(ScoreObjective p_96552_1_) {
      int i = 0;

      for(int j = 0; j < 19; ++j) {
         if(this.func_96539_a(j) == p_96552_1_) {
            ++i;
         }
      }

      return i;
   }
}
