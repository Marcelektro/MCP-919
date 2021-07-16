package net.minecraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandResultStats {
   private static final int field_179676_a = CommandResultStats.Type.values().length;
   private static final String[] field_179674_b = new String[field_179676_a];
   private String[] field_179675_c;
   private String[] field_179673_d;

   public CommandResultStats() {
      this.field_179675_c = field_179674_b;
      this.field_179673_d = field_179674_b;
   }

   public void func_179672_a(final ICommandSender p_179672_1_, CommandResultStats.Type p_179672_2_, int p_179672_3_) {
      String s = this.field_179675_c[p_179672_2_.func_179636_a()];
      if(s != null) {
         ICommandSender icommandsender = new ICommandSender() {
            public String func_70005_c_() {
               return p_179672_1_.func_70005_c_();
            }

            public IChatComponent func_145748_c_() {
               return p_179672_1_.func_145748_c_();
            }

            public void func_145747_a(IChatComponent p_145747_1_) {
               p_179672_1_.func_145747_a(p_145747_1_);
            }

            public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
               return true;
            }

            public BlockPos func_180425_c() {
               return p_179672_1_.func_180425_c();
            }

            public Vec3 func_174791_d() {
               return p_179672_1_.func_174791_d();
            }

            public World func_130014_f_() {
               return p_179672_1_.func_130014_f_();
            }

            public Entity func_174793_f() {
               return p_179672_1_.func_174793_f();
            }

            public boolean func_174792_t_() {
               return p_179672_1_.func_174792_t_();
            }

            public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
               p_179672_1_.func_174794_a(p_174794_1_, p_174794_2_);
            }
         };

         String s1;
         try {
            s1 = CommandBase.func_175758_e(icommandsender, s);
         } catch (EntityNotFoundException var11) {
            return;
         }

         String s2 = this.field_179673_d[p_179672_2_.func_179636_a()];
         if(s2 != null) {
            Scoreboard scoreboard = p_179672_1_.func_130014_f_().func_96441_U();
            ScoreObjective scoreobjective = scoreboard.func_96518_b(s2);
            if(scoreobjective != null) {
               if(scoreboard.func_178819_b(s1, scoreobjective)) {
                  Score score = scoreboard.func_96529_a(s1, scoreobjective);
                  score.func_96647_c(p_179672_3_);
               }
            }
         }
      }
   }

   public void func_179668_a(NBTTagCompound p_179668_1_) {
      if(p_179668_1_.func_150297_b("CommandStats", 10)) {
         NBTTagCompound nbttagcompound = p_179668_1_.func_74775_l("CommandStats");

         for(CommandResultStats.Type commandresultstats$type : CommandResultStats.Type.values()) {
            String s = commandresultstats$type.func_179637_b() + "Name";
            String s1 = commandresultstats$type.func_179637_b() + "Objective";
            if(nbttagcompound.func_150297_b(s, 8) && nbttagcompound.func_150297_b(s1, 8)) {
               String s2 = nbttagcompound.func_74779_i(s);
               String s3 = nbttagcompound.func_74779_i(s1);
               func_179667_a(this, commandresultstats$type, s2, s3);
            }
         }

      }
   }

   public void func_179670_b(NBTTagCompound p_179670_1_) {
      NBTTagCompound nbttagcompound = new NBTTagCompound();

      for(CommandResultStats.Type commandresultstats$type : CommandResultStats.Type.values()) {
         String s = this.field_179675_c[commandresultstats$type.func_179636_a()];
         String s1 = this.field_179673_d[commandresultstats$type.func_179636_a()];
         if(s != null && s1 != null) {
            nbttagcompound.func_74778_a(commandresultstats$type.func_179637_b() + "Name", s);
            nbttagcompound.func_74778_a(commandresultstats$type.func_179637_b() + "Objective", s1);
         }
      }

      if(!nbttagcompound.func_82582_d()) {
         p_179670_1_.func_74782_a("CommandStats", nbttagcompound);
      }

   }

   public static void func_179667_a(CommandResultStats p_179667_0_, CommandResultStats.Type p_179667_1_, String p_179667_2_, String p_179667_3_) {
      if(p_179667_2_ != null && p_179667_2_.length() != 0 && p_179667_3_ != null && p_179667_3_.length() != 0) {
         if(p_179667_0_.field_179675_c == field_179674_b || p_179667_0_.field_179673_d == field_179674_b) {
            p_179667_0_.field_179675_c = new String[field_179676_a];
            p_179667_0_.field_179673_d = new String[field_179676_a];
         }

         p_179667_0_.field_179675_c[p_179667_1_.func_179636_a()] = p_179667_2_;
         p_179667_0_.field_179673_d[p_179667_1_.func_179636_a()] = p_179667_3_;
      } else {
         func_179669_a(p_179667_0_, p_179667_1_);
      }
   }

   private static void func_179669_a(CommandResultStats p_179669_0_, CommandResultStats.Type p_179669_1_) {
      if(p_179669_0_.field_179675_c != field_179674_b && p_179669_0_.field_179673_d != field_179674_b) {
         p_179669_0_.field_179675_c[p_179669_1_.func_179636_a()] = null;
         p_179669_0_.field_179673_d[p_179669_1_.func_179636_a()] = null;
         boolean flag = true;

         for(CommandResultStats.Type commandresultstats$type : CommandResultStats.Type.values()) {
            if(p_179669_0_.field_179675_c[commandresultstats$type.func_179636_a()] != null && p_179669_0_.field_179673_d[commandresultstats$type.func_179636_a()] != null) {
               flag = false;
               break;
            }
         }

         if(flag) {
            p_179669_0_.field_179675_c = field_179674_b;
            p_179669_0_.field_179673_d = field_179674_b;
         }

      }
   }

   public void func_179671_a(CommandResultStats p_179671_1_) {
      for(CommandResultStats.Type commandresultstats$type : CommandResultStats.Type.values()) {
         func_179667_a(this, commandresultstats$type, p_179671_1_.field_179675_c[commandresultstats$type.func_179636_a()], p_179671_1_.field_179673_d[commandresultstats$type.func_179636_a()]);
      }

   }

   public static enum Type {
      SUCCESS_COUNT(0, "SuccessCount"),
      AFFECTED_BLOCKS(1, "AffectedBlocks"),
      AFFECTED_ENTITIES(2, "AffectedEntities"),
      AFFECTED_ITEMS(3, "AffectedItems"),
      QUERY_RESULT(4, "QueryResult");

      final int field_179639_f;
      final String field_179640_g;

      private Type(int p_i46050_3_, String p_i46050_4_) {
         this.field_179639_f = p_i46050_3_;
         this.field_179640_g = p_i46050_4_;
      }

      public int func_179636_a() {
         return this.field_179639_f;
      }

      public String func_179637_b() {
         return this.field_179640_g;
      }

      public static String[] func_179634_c() {
         String[] astring = new String[values().length];
         int i = 0;

         for(CommandResultStats.Type commandresultstats$type : values()) {
            astring[i++] = commandresultstats$type.func_179637_b();
         }

         return astring;
      }

      public static CommandResultStats.Type func_179635_a(String p_179635_0_) {
         for(CommandResultStats.Type commandresultstats$type : values()) {
            if(commandresultstats$type.func_179637_b().equals(p_179635_0_)) {
               return commandresultstats$type;
            }
         }

         return null;
      }
   }
}
