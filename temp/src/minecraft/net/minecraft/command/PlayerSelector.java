package net.minecraft.command;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class PlayerSelector {
   private static final Pattern field_82389_a = Pattern.compile("^@([pare])(?:\\[([\\w=,!-]*)\\])?$");
   private static final Pattern field_82387_b = Pattern.compile("\\G([-!]?[\\w-]*)(?:$|,)");
   private static final Pattern field_82388_c = Pattern.compile("\\G(\\w+)=([-!]?[\\w-]*)(?:$|,)");
   private static final Set<String> field_179666_d = Sets.newHashSet(new String[]{"x", "y", "z", "dx", "dy", "dz", "rm", "r"});

   public static EntityPlayerMP func_82386_a(ICommandSender p_82386_0_, String p_82386_1_) {
      return (EntityPlayerMP)func_179652_a(p_82386_0_, p_82386_1_, EntityPlayerMP.class);
   }

   public static <T extends Entity> T func_179652_a(ICommandSender p_179652_0_, String p_179652_1_, Class<? extends T> p_179652_2_) {
      List<T> list = func_179656_b(p_179652_0_, p_179652_1_, p_179652_2_);
      return (T)(list.size() == 1?(Entity)list.get(0):null);
   }

   public static IChatComponent func_150869_b(ICommandSender p_150869_0_, String p_150869_1_) {
      List<Entity> list = func_179656_b(p_150869_0_, p_150869_1_, Entity.class);
      if(list.isEmpty()) {
         return null;
      } else {
         List<IChatComponent> list1 = Lists.<IChatComponent>newArrayList();

         for(Entity entity : list) {
            list1.add(entity.func_145748_c_());
         }

         return CommandBase.func_180530_a(list1);
      }
   }

   public static <T extends Entity> List<T> func_179656_b(ICommandSender p_179656_0_, String p_179656_1_, Class<? extends T> p_179656_2_) {
      Matcher matcher = field_82389_a.matcher(p_179656_1_);
      if(matcher.matches() && p_179656_0_.func_70003_b(1, "@")) {
         Map<String, String> map = func_82381_h(matcher.group(2));
         if(!func_179655_b(p_179656_0_, map)) {
            return Collections.<T>emptyList();
         } else {
            String s = matcher.group(1);
            BlockPos blockpos = func_179664_b(map, p_179656_0_.func_180425_c());
            List<World> list = func_179654_a(p_179656_0_, map);
            List<T> list1 = Lists.<T>newArrayList();

            for(World world : list) {
               if(world != null) {
                  List<Predicate<Entity>> list2 = Lists.<Predicate<Entity>>newArrayList();
                  list2.addAll(func_179663_a(map, s));
                  list2.addAll(func_179648_b(map));
                  list2.addAll(func_179649_c(map));
                  list2.addAll(func_179659_d(map));
                  list2.addAll(func_179657_e(map));
                  list2.addAll(func_179647_f(map));
                  list2.addAll(func_180698_a(map, blockpos));
                  list2.addAll(func_179662_g(map));
                  list1.addAll(func_179660_a(map, p_179656_2_, list2, s, world, blockpos));
               }
            }

            return func_179658_a(list1, map, p_179656_0_, p_179656_2_, s, blockpos);
         }
      } else {
         return Collections.<T>emptyList();
      }
   }

   private static List<World> func_179654_a(ICommandSender p_179654_0_, Map<String, String> p_179654_1_) {
      List<World> list = Lists.<World>newArrayList();
      if(func_179665_h(p_179654_1_)) {
         list.add(p_179654_0_.func_130014_f_());
      } else {
         Collections.addAll(list, MinecraftServer.func_71276_C().field_71305_c);
      }

      return list;
   }

   private static <T extends Entity> boolean func_179655_b(ICommandSender p_179655_0_, Map<String, String> p_179655_1_) {
      String s = func_179651_b(p_179655_1_, "type");
      s = s != null && s.startsWith("!")?s.substring(1):s;
      if(s != null && !EntityList.func_180125_b(s)) {
         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.generic.entity.invalidType", new Object[]{s});
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         p_179655_0_.func_145747_a(chatcomponenttranslation);
         return false;
      } else {
         return true;
      }
   }

   private static List<Predicate<Entity>> func_179663_a(Map<String, String> p_179663_0_, String p_179663_1_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      final String s = func_179651_b(p_179663_0_, "type");
      final boolean flag = s != null && s.startsWith("!");
      if(flag) {
         s = s.substring(1);
      }

      boolean flag1 = !p_179663_1_.equals("e");
      boolean flag2 = p_179663_1_.equals("r") && s != null;
      if((s == null || !p_179663_1_.equals("e")) && !flag2) {
         if(flag1) {
            list.add(new Predicate<Entity>() {
               public boolean apply(Entity p_apply_1_) {
                  return p_apply_1_ instanceof EntityPlayer;
               }
            });
         }
      } else {
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               return EntityList.func_180123_a(p_apply_1_, s) != flag;
            }
         });
      }

      return list;
   }

   private static List<Predicate<Entity>> func_179648_b(Map<String, String> p_179648_0_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      final int i = func_179653_a(p_179648_0_, "lm", -1);
      final int j = func_179653_a(p_179648_0_, "l", -1);
      if(i > -1 || j > -1) {
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               if(!(p_apply_1_ instanceof EntityPlayerMP)) {
                  return false;
               } else {
                  EntityPlayerMP entityplayermp = (EntityPlayerMP)p_apply_1_;
                  return (i <= -1 || entityplayermp.field_71068_ca >= i) && (j <= -1 || entityplayermp.field_71068_ca <= j);
               }
            }
         });
      }

      return list;
   }

   private static List<Predicate<Entity>> func_179649_c(Map<String, String> p_179649_0_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      final int i = func_179653_a(p_179649_0_, "m", WorldSettings.GameType.NOT_SET.func_77148_a());
      if(i != WorldSettings.GameType.NOT_SET.func_77148_a()) {
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               if(!(p_apply_1_ instanceof EntityPlayerMP)) {
                  return false;
               } else {
                  EntityPlayerMP entityplayermp = (EntityPlayerMP)p_apply_1_;
                  return entityplayermp.field_71134_c.func_73081_b().func_77148_a() == i;
               }
            }
         });
      }

      return list;
   }

   private static List<Predicate<Entity>> func_179659_d(Map<String, String> p_179659_0_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      final String s = func_179651_b(p_179659_0_, "team");
      final boolean flag = s != null && s.startsWith("!");
      if(flag) {
         s = s.substring(1);
      }

      if(s != null) {
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               if(!(p_apply_1_ instanceof EntityLivingBase)) {
                  return false;
               } else {
                  EntityLivingBase entitylivingbase = (EntityLivingBase)p_apply_1_;
                  Team team = entitylivingbase.func_96124_cp();
                  String s1 = team == null?"":team.func_96661_b();
                  return s1.equals(s) != flag;
               }
            }
         });
      }

      return list;
   }

   private static List<Predicate<Entity>> func_179657_e(Map<String, String> p_179657_0_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      final Map<String, Integer> map = func_96560_a(p_179657_0_);
      if(map != null && map.size() > 0) {
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               Scoreboard scoreboard = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();

               for(Entry<String, Integer> entry : map.entrySet()) {
                  String s = (String)entry.getKey();
                  boolean flag = false;
                  if(s.endsWith("_min") && s.length() > 4) {
                     flag = true;
                     s = s.substring(0, s.length() - 4);
                  }

                  ScoreObjective scoreobjective = scoreboard.func_96518_b(s);
                  if(scoreobjective == null) {
                     return false;
                  }

                  String s1 = p_apply_1_ instanceof EntityPlayerMP?p_apply_1_.func_70005_c_():p_apply_1_.func_110124_au().toString();
                  if(!scoreboard.func_178819_b(s1, scoreobjective)) {
                     return false;
                  }

                  Score score = scoreboard.func_96529_a(s1, scoreobjective);
                  int i = score.func_96652_c();
                  if(i < ((Integer)entry.getValue()).intValue() && flag) {
                     return false;
                  }

                  if(i > ((Integer)entry.getValue()).intValue() && !flag) {
                     return false;
                  }
               }

               return true;
            }
         });
      }

      return list;
   }

   private static List<Predicate<Entity>> func_179647_f(Map<String, String> p_179647_0_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      final String s = func_179651_b(p_179647_0_, "name");
      final boolean flag = s != null && s.startsWith("!");
      if(flag) {
         s = s.substring(1);
      }

      if(s != null) {
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               return p_apply_1_.func_70005_c_().equals(s) != flag;
            }
         });
      }

      return list;
   }

   private static List<Predicate<Entity>> func_180698_a(Map<String, String> p_180698_0_, final BlockPos p_180698_1_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      final int i = func_179653_a(p_180698_0_, "rm", -1);
      final int j = func_179653_a(p_180698_0_, "r", -1);
      if(p_180698_1_ != null && (i >= 0 || j >= 0)) {
         final int k = i * i;
         final int l = j * j;
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               int i1 = (int)p_apply_1_.func_174831_c(p_180698_1_);
               return (i < 0 || i1 >= k) && (j < 0 || i1 <= l);
            }
         });
      }

      return list;
   }

   private static List<Predicate<Entity>> func_179662_g(Map<String, String> p_179662_0_) {
      List<Predicate<Entity>> list = Lists.<Predicate<Entity>>newArrayList();
      if(p_179662_0_.containsKey("rym") || p_179662_0_.containsKey("ry")) {
         final int i = func_179650_a(func_179653_a(p_179662_0_, "rym", 0));
         final int j = func_179650_a(func_179653_a(p_179662_0_, "ry", 359));
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               int i1 = PlayerSelector.func_179650_a((int)Math.floor((double)p_apply_1_.field_70177_z));
               return i > j?i1 >= i || i1 <= j:i1 >= i && i1 <= j;
            }
         });
      }

      if(p_179662_0_.containsKey("rxm") || p_179662_0_.containsKey("rx")) {
         final int k = func_179650_a(func_179653_a(p_179662_0_, "rxm", 0));
         final int l = func_179650_a(func_179653_a(p_179662_0_, "rx", 359));
         list.add(new Predicate<Entity>() {
            public boolean apply(Entity p_apply_1_) {
               int i1 = PlayerSelector.func_179650_a((int)Math.floor((double)p_apply_1_.field_70125_A));
               return k > l?i1 >= k || i1 <= l:i1 >= k && i1 <= l;
            }
         });
      }

      return list;
   }

   private static <T extends Entity> List<T> func_179660_a(Map<String, String> p_179660_0_, Class<? extends T> p_179660_1_, List<Predicate<Entity>> p_179660_2_, String p_179660_3_, World p_179660_4_, BlockPos p_179660_5_) {
      List<T> list = Lists.<T>newArrayList();
      String s = func_179651_b(p_179660_0_, "type");
      s = s != null && s.startsWith("!")?s.substring(1):s;
      boolean flag = !p_179660_3_.equals("e");
      boolean flag1 = p_179660_3_.equals("r") && s != null;
      int i = func_179653_a(p_179660_0_, "dx", 0);
      int j = func_179653_a(p_179660_0_, "dy", 0);
      int k = func_179653_a(p_179660_0_, "dz", 0);
      int l = func_179653_a(p_179660_0_, "r", -1);
      Predicate<Entity> predicate = Predicates.and(p_179660_2_);
      Predicate<Entity> predicate1 = Predicates.<Entity>and(EntitySelectors.field_94557_a, predicate);
      if(p_179660_5_ != null) {
         int i1 = p_179660_4_.field_73010_i.size();
         int j1 = p_179660_4_.field_72996_f.size();
         boolean flag2 = i1 < j1 / 16;
         if(!p_179660_0_.containsKey("dx") && !p_179660_0_.containsKey("dy") && !p_179660_0_.containsKey("dz")) {
            if(l >= 0) {
               AxisAlignedBB axisalignedbb1 = new AxisAlignedBB((double)(p_179660_5_.func_177958_n() - l), (double)(p_179660_5_.func_177956_o() - l), (double)(p_179660_5_.func_177952_p() - l), (double)(p_179660_5_.func_177958_n() + l + 1), (double)(p_179660_5_.func_177956_o() + l + 1), (double)(p_179660_5_.func_177952_p() + l + 1));
               if(flag && flag2 && !flag1) {
                  list.addAll(p_179660_4_.<T>func_175661_b(p_179660_1_, predicate1));
               } else {
                  list.addAll(p_179660_4_.<T>func_175647_a(p_179660_1_, axisalignedbb1, predicate1));
               }
            } else if(p_179660_3_.equals("a")) {
               list.addAll(p_179660_4_.<T>func_175661_b(p_179660_1_, predicate));
            } else if(!p_179660_3_.equals("p") && (!p_179660_3_.equals("r") || flag1)) {
               list.addAll(p_179660_4_.<T>func_175644_a(p_179660_1_, predicate1));
            } else {
               list.addAll(p_179660_4_.<T>func_175661_b(p_179660_1_, predicate1));
            }
         } else {
            final AxisAlignedBB axisalignedbb = func_179661_a(p_179660_5_, i, j, k);
            if(flag && flag2 && !flag1) {
               Predicate<Entity> predicate2 = new Predicate<Entity>() {
                  public boolean apply(Entity p_apply_1_) {
                     return p_apply_1_.field_70165_t >= axisalignedbb.field_72340_a && p_apply_1_.field_70163_u >= axisalignedbb.field_72338_b && p_apply_1_.field_70161_v >= axisalignedbb.field_72339_c?p_apply_1_.field_70165_t < axisalignedbb.field_72336_d && p_apply_1_.field_70163_u < axisalignedbb.field_72337_e && p_apply_1_.field_70161_v < axisalignedbb.field_72334_f:false;
                  }
               };
               list.addAll(p_179660_4_.<T>func_175661_b(p_179660_1_, Predicates.<T>and(predicate1, predicate2)));
            } else {
               list.addAll(p_179660_4_.<T>func_175647_a(p_179660_1_, axisalignedbb, predicate1));
            }
         }
      } else if(p_179660_3_.equals("a")) {
         list.addAll(p_179660_4_.<T>func_175661_b(p_179660_1_, predicate));
      } else if(!p_179660_3_.equals("p") && (!p_179660_3_.equals("r") || flag1)) {
         list.addAll(p_179660_4_.<T>func_175644_a(p_179660_1_, predicate1));
      } else {
         list.addAll(p_179660_4_.<T>func_175661_b(p_179660_1_, predicate1));
      }

      return list;
   }

   private static <T extends Entity> List<T> func_179658_a(List<T> p_179658_0_, Map<String, String> p_179658_1_, ICommandSender p_179658_2_, Class<? extends T> p_179658_3_, String p_179658_4_, final BlockPos p_179658_5_) {
      int i = func_179653_a(p_179658_1_, "c", !p_179658_4_.equals("a") && !p_179658_4_.equals("e")?1:0);
      if(!p_179658_4_.equals("p") && !p_179658_4_.equals("a") && !p_179658_4_.equals("e")) {
         if(p_179658_4_.equals("r")) {
            Collections.shuffle((List<?>)p_179658_0_);
         }
      } else if(p_179658_5_ != null) {
         Collections.sort((List<T>)p_179658_0_, new Comparator<Entity>() {
            public int compare(Entity p_compare_1_, Entity p_compare_2_) {
               return ComparisonChain.start().compare(p_compare_1_.func_174818_b(p_179658_5_), p_compare_2_.func_174818_b(p_179658_5_)).result();
            }
         });
      }

      Entity entity = p_179658_2_.func_174793_f();
      if(entity != null && p_179658_3_.isAssignableFrom(entity.getClass()) && i == 1 && ((List)p_179658_0_).contains(entity) && !"r".equals(p_179658_4_)) {
         p_179658_0_ = Lists.newArrayList(new Entity[]{entity});
      }

      if(i != 0) {
         if(i < 0) {
            Collections.reverse((List<?>)p_179658_0_);
         }

         p_179658_0_ = ((List)p_179658_0_).subList(0, Math.min(Math.abs(i), ((List)p_179658_0_).size()));
      }

      return (List)p_179658_0_;
   }

   private static AxisAlignedBB func_179661_a(BlockPos p_179661_0_, int p_179661_1_, int p_179661_2_, int p_179661_3_) {
      boolean flag = p_179661_1_ < 0;
      boolean flag1 = p_179661_2_ < 0;
      boolean flag2 = p_179661_3_ < 0;
      int i = p_179661_0_.func_177958_n() + (flag?p_179661_1_:0);
      int j = p_179661_0_.func_177956_o() + (flag1?p_179661_2_:0);
      int k = p_179661_0_.func_177952_p() + (flag2?p_179661_3_:0);
      int l = p_179661_0_.func_177958_n() + (flag?0:p_179661_1_) + 1;
      int i1 = p_179661_0_.func_177956_o() + (flag1?0:p_179661_2_) + 1;
      int j1 = p_179661_0_.func_177952_p() + (flag2?0:p_179661_3_) + 1;
      return new AxisAlignedBB((double)i, (double)j, (double)k, (double)l, (double)i1, (double)j1);
   }

   public static int func_179650_a(int p_179650_0_) {
      p_179650_0_ = p_179650_0_ % 360;
      if(p_179650_0_ >= 160) {
         p_179650_0_ -= 360;
      }

      if(p_179650_0_ < 0) {
         p_179650_0_ += 360;
      }

      return p_179650_0_;
   }

   private static BlockPos func_179664_b(Map<String, String> p_179664_0_, BlockPos p_179664_1_) {
      return new BlockPos(func_179653_a(p_179664_0_, "x", p_179664_1_.func_177958_n()), func_179653_a(p_179664_0_, "y", p_179664_1_.func_177956_o()), func_179653_a(p_179664_0_, "z", p_179664_1_.func_177952_p()));
   }

   private static boolean func_179665_h(Map<String, String> p_179665_0_) {
      for(String s : field_179666_d) {
         if(p_179665_0_.containsKey(s)) {
            return true;
         }
      }

      return false;
   }

   private static int func_179653_a(Map<String, String> p_179653_0_, String p_179653_1_, int p_179653_2_) {
      return p_179653_0_.containsKey(p_179653_1_)?MathHelper.func_82715_a((String)p_179653_0_.get(p_179653_1_), p_179653_2_):p_179653_2_;
   }

   private static String func_179651_b(Map<String, String> p_179651_0_, String p_179651_1_) {
      return (String)p_179651_0_.get(p_179651_1_);
   }

   public static Map<String, Integer> func_96560_a(Map<String, String> p_96560_0_) {
      Map<String, Integer> map = Maps.<String, Integer>newHashMap();

      for(String s : p_96560_0_.keySet()) {
         if(s.startsWith("score_") && s.length() > "score_".length()) {
            map.put(s.substring("score_".length()), Integer.valueOf(MathHelper.func_82715_a((String)p_96560_0_.get(s), 1)));
         }
      }

      return map;
   }

   public static boolean func_82377_a(String p_82377_0_) {
      Matcher matcher = field_82389_a.matcher(p_82377_0_);
      if(!matcher.matches()) {
         return false;
      } else {
         Map<String, String> map = func_82381_h(matcher.group(2));
         String s = matcher.group(1);
         int i = !"a".equals(s) && !"e".equals(s)?1:0;
         return func_179653_a(map, "c", i) != 1;
      }
   }

   public static boolean func_82378_b(String p_82378_0_) {
      return field_82389_a.matcher(p_82378_0_).matches();
   }

   private static Map<String, String> func_82381_h(String p_82381_0_) {
      Map<String, String> map = Maps.<String, String>newHashMap();
      if(p_82381_0_ == null) {
         return map;
      } else {
         int i = 0;
         int j = -1;

         for(Matcher matcher = field_82387_b.matcher(p_82381_0_); matcher.find(); j = matcher.end()) {
            String s = null;
            switch(i++) {
            case 0:
               s = "x";
               break;
            case 1:
               s = "y";
               break;
            case 2:
               s = "z";
               break;
            case 3:
               s = "r";
            }

            if(s != null && matcher.group(1).length() > 0) {
               map.put(s, matcher.group(1));
            }
         }

         if(j < p_82381_0_.length()) {
            Matcher matcher1 = field_82388_c.matcher(j == -1?p_82381_0_:p_82381_0_.substring(j));

            while(matcher1.find()) {
               map.put(matcher1.group(1), matcher1.group(2));
            }
         }

         return map;
      }
   }
}
