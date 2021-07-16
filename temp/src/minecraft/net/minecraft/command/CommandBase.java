package net.minecraft.command;

import com.google.common.base.Functions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.IAdminCommand;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public abstract class CommandBase implements ICommand {
   private static IAdminCommand field_71533_a;

   public int func_82362_a() {
      return 4;
   }

   public List<String> func_71514_a() {
      return Collections.<String>emptyList();
   }

   public boolean func_71519_b(ICommandSender p_71519_1_) {
      return p_71519_1_.func_70003_b(this.func_82362_a(), this.func_71517_b());
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return null;
   }

   public static int func_175755_a(String p_175755_0_) throws NumberInvalidException {
      try {
         return Integer.parseInt(p_175755_0_);
      } catch (NumberFormatException var2) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175755_0_});
      }
   }

   public static int func_180528_a(String p_180528_0_, int p_180528_1_) throws NumberInvalidException {
      return func_175764_a(p_180528_0_, p_180528_1_, Integer.MAX_VALUE);
   }

   public static int func_175764_a(String p_175764_0_, int p_175764_1_, int p_175764_2_) throws NumberInvalidException {
      int i = func_175755_a(p_175764_0_);
      if(i < p_175764_1_) {
         throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[]{Integer.valueOf(i), Integer.valueOf(p_175764_1_)});
      } else if(i > p_175764_2_) {
         throw new NumberInvalidException("commands.generic.num.tooBig", new Object[]{Integer.valueOf(i), Integer.valueOf(p_175764_2_)});
      } else {
         return i;
      }
   }

   public static long func_175766_b(String p_175766_0_) throws NumberInvalidException {
      try {
         return Long.parseLong(p_175766_0_);
      } catch (NumberFormatException var2) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175766_0_});
      }
   }

   public static long func_175760_a(String p_175760_0_, long p_175760_1_, long p_175760_3_) throws NumberInvalidException {
      long i = func_175766_b(p_175760_0_);
      if(i < p_175760_1_) {
         throw new NumberInvalidException("commands.generic.num.tooSmall", new Object[]{Long.valueOf(i), Long.valueOf(p_175760_1_)});
      } else if(i > p_175760_3_) {
         throw new NumberInvalidException("commands.generic.num.tooBig", new Object[]{Long.valueOf(i), Long.valueOf(p_175760_3_)});
      } else {
         return i;
      }
   }

   public static BlockPos func_175757_a(ICommandSender p_175757_0_, String[] p_175757_1_, int p_175757_2_, boolean p_175757_3_) throws NumberInvalidException {
      BlockPos blockpos = p_175757_0_.func_180425_c();
      return new BlockPos(func_175769_b((double)blockpos.func_177958_n(), p_175757_1_[p_175757_2_], -30000000, 30000000, p_175757_3_), func_175769_b((double)blockpos.func_177956_o(), p_175757_1_[p_175757_2_ + 1], 0, 256, false), func_175769_b((double)blockpos.func_177952_p(), p_175757_1_[p_175757_2_ + 2], -30000000, 30000000, p_175757_3_));
   }

   public static double func_175765_c(String p_175765_0_) throws NumberInvalidException {
      try {
         double d0 = Double.parseDouble(p_175765_0_);
         if(!Doubles.isFinite(d0)) {
            throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175765_0_});
         } else {
            return d0;
         }
      } catch (NumberFormatException var3) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{p_175765_0_});
      }
   }

   public static double func_180526_a(String p_180526_0_, double p_180526_1_) throws NumberInvalidException {
      return func_175756_a(p_180526_0_, p_180526_1_, Double.MAX_VALUE);
   }

   public static double func_175756_a(String p_175756_0_, double p_175756_1_, double p_175756_3_) throws NumberInvalidException {
      double d0 = func_175765_c(p_175756_0_);
      if(d0 < p_175756_1_) {
         throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{Double.valueOf(d0), Double.valueOf(p_175756_1_)});
      } else if(d0 > p_175756_3_) {
         throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{Double.valueOf(d0), Double.valueOf(p_175756_3_)});
      } else {
         return d0;
      }
   }

   public static boolean func_180527_d(String p_180527_0_) throws CommandException {
      if(!p_180527_0_.equals("true") && !p_180527_0_.equals("1")) {
         if(!p_180527_0_.equals("false") && !p_180527_0_.equals("0")) {
            throw new CommandException("commands.generic.boolean.invalid", new Object[]{p_180527_0_});
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public static EntityPlayerMP func_71521_c(ICommandSender p_71521_0_) throws PlayerNotFoundException {
      if(p_71521_0_ instanceof EntityPlayerMP) {
         return (EntityPlayerMP)p_71521_0_;
      } else {
         throw new PlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
      }
   }

   public static EntityPlayerMP func_82359_c(ICommandSender p_82359_0_, String p_82359_1_) throws PlayerNotFoundException {
      EntityPlayerMP entityplayermp = PlayerSelector.func_82386_a(p_82359_0_, p_82359_1_);
      if(entityplayermp == null) {
         try {
            entityplayermp = MinecraftServer.func_71276_C().func_71203_ab().func_177451_a(UUID.fromString(p_82359_1_));
         } catch (IllegalArgumentException var4) {
            ;
         }
      }

      if(entityplayermp == null) {
         entityplayermp = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(p_82359_1_);
      }

      if(entityplayermp == null) {
         throw new PlayerNotFoundException();
      } else {
         return entityplayermp;
      }
   }

   public static Entity func_175768_b(ICommandSender p_175768_0_, String p_175768_1_) throws EntityNotFoundException {
      return func_175759_a(p_175768_0_, p_175768_1_, Entity.class);
   }

   public static <T extends Entity> T func_175759_a(ICommandSender p_175759_0_, String p_175759_1_, Class<? extends T> p_175759_2_) throws EntityNotFoundException {
      Entity entity = PlayerSelector.func_179652_a(p_175759_0_, p_175759_1_, p_175759_2_);
      MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
      if(entity == null) {
         entity = minecraftserver.func_71203_ab().func_152612_a(p_175759_1_);
      }

      if(entity == null) {
         try {
            UUID uuid = UUID.fromString(p_175759_1_);
            entity = minecraftserver.func_175576_a(uuid);
            if(entity == null) {
               entity = minecraftserver.func_71203_ab().func_177451_a(uuid);
            }
         } catch (IllegalArgumentException var6) {
            throw new EntityNotFoundException("commands.generic.entity.invalidUuid", new Object[0]);
         }
      }

      if(entity != null && p_175759_2_.isAssignableFrom(entity.getClass())) {
         return (T)entity;
      } else {
         throw new EntityNotFoundException();
      }
   }

   public static List<Entity> func_175763_c(ICommandSender p_175763_0_, String p_175763_1_) throws EntityNotFoundException {
      return (List<Entity>)(PlayerSelector.func_82378_b(p_175763_1_)?PlayerSelector.func_179656_b(p_175763_0_, p_175763_1_, Entity.class):Lists.newArrayList(new Entity[]{func_175768_b(p_175763_0_, p_175763_1_)}));
   }

   public static String func_96332_d(ICommandSender p_96332_0_, String p_96332_1_) throws PlayerNotFoundException {
      try {
         return func_82359_c(p_96332_0_, p_96332_1_).func_70005_c_();
      } catch (PlayerNotFoundException playernotfoundexception) {
         if(PlayerSelector.func_82378_b(p_96332_1_)) {
            throw playernotfoundexception;
         } else {
            return p_96332_1_;
         }
      }
   }

   public static String func_175758_e(ICommandSender p_175758_0_, String p_175758_1_) throws EntityNotFoundException {
      try {
         return func_82359_c(p_175758_0_, p_175758_1_).func_70005_c_();
      } catch (PlayerNotFoundException var5) {
         try {
            return func_175768_b(p_175758_0_, p_175758_1_).func_110124_au().toString();
         } catch (EntityNotFoundException entitynotfoundexception) {
            if(PlayerSelector.func_82378_b(p_175758_1_)) {
               throw entitynotfoundexception;
            } else {
               return p_175758_1_;
            }
         }
      }
   }

   public static IChatComponent func_147178_a(ICommandSender p_147178_0_, String[] p_147178_1_, int p_147178_2_) throws CommandException, PlayerNotFoundException {
      return func_147176_a(p_147178_0_, p_147178_1_, p_147178_2_, false);
   }

   public static IChatComponent func_147176_a(ICommandSender p_147176_0_, String[] p_147176_1_, int p_147176_2_, boolean p_147176_3_) throws PlayerNotFoundException {
      IChatComponent ichatcomponent = new ChatComponentText("");

      for(int i = p_147176_2_; i < p_147176_1_.length; ++i) {
         if(i > p_147176_2_) {
            ichatcomponent.func_150258_a(" ");
         }

         IChatComponent ichatcomponent1 = new ChatComponentText(p_147176_1_[i]);
         if(p_147176_3_) {
            IChatComponent ichatcomponent2 = PlayerSelector.func_150869_b(p_147176_0_, p_147176_1_[i]);
            if(ichatcomponent2 == null) {
               if(PlayerSelector.func_82378_b(p_147176_1_[i])) {
                  throw new PlayerNotFoundException();
               }
            } else {
               ichatcomponent1 = ichatcomponent2;
            }
         }

         ichatcomponent.func_150257_a(ichatcomponent1);
      }

      return ichatcomponent;
   }

   public static String func_180529_a(String[] p_180529_0_, int p_180529_1_) {
      StringBuilder stringbuilder = new StringBuilder();

      for(int i = p_180529_1_; i < p_180529_0_.length; ++i) {
         if(i > p_180529_1_) {
            stringbuilder.append(" ");
         }

         String s = p_180529_0_[i];
         stringbuilder.append(s);
      }

      return stringbuilder.toString();
   }

   public static CommandBase.CoordinateArg func_175770_a(double p_175770_0_, String p_175770_2_, boolean p_175770_3_) throws NumberInvalidException {
      return func_175767_a(p_175770_0_, p_175770_2_, -30000000, 30000000, p_175770_3_);
   }

   public static CommandBase.CoordinateArg func_175767_a(double p_175767_0_, String p_175767_2_, int p_175767_3_, int p_175767_4_, boolean p_175767_5_) throws NumberInvalidException {
      boolean flag = p_175767_2_.startsWith("~");
      if(flag && Double.isNaN(p_175767_0_)) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{Double.valueOf(p_175767_0_)});
      } else {
         double d0 = 0.0D;
         if(!flag || p_175767_2_.length() > 1) {
            boolean flag1 = p_175767_2_.contains(".");
            if(flag) {
               p_175767_2_ = p_175767_2_.substring(1);
            }

            d0 += func_175765_c(p_175767_2_);
            if(!flag1 && !flag && p_175767_5_) {
               d0 += 0.5D;
            }
         }

         if(p_175767_3_ != 0 || p_175767_4_ != 0) {
            if(d0 < (double)p_175767_3_) {
               throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{Double.valueOf(d0), Integer.valueOf(p_175767_3_)});
            }

            if(d0 > (double)p_175767_4_) {
               throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{Double.valueOf(d0), Integer.valueOf(p_175767_4_)});
            }
         }

         return new CommandBase.CoordinateArg(d0 + (flag?p_175767_0_:0.0D), d0, flag);
      }
   }

   public static double func_175761_b(double p_175761_0_, String p_175761_2_, boolean p_175761_3_) throws NumberInvalidException {
      return func_175769_b(p_175761_0_, p_175761_2_, -30000000, 30000000, p_175761_3_);
   }

   public static double func_175769_b(double p_175769_0_, String p_175769_2_, int p_175769_3_, int p_175769_4_, boolean p_175769_5_) throws NumberInvalidException {
      boolean flag = p_175769_2_.startsWith("~");
      if(flag && Double.isNaN(p_175769_0_)) {
         throw new NumberInvalidException("commands.generic.num.invalid", new Object[]{Double.valueOf(p_175769_0_)});
      } else {
         double d0 = flag?p_175769_0_:0.0D;
         if(!flag || p_175769_2_.length() > 1) {
            boolean flag1 = p_175769_2_.contains(".");
            if(flag) {
               p_175769_2_ = p_175769_2_.substring(1);
            }

            d0 += func_175765_c(p_175769_2_);
            if(!flag1 && !flag && p_175769_5_) {
               d0 += 0.5D;
            }
         }

         if(p_175769_3_ != 0 || p_175769_4_ != 0) {
            if(d0 < (double)p_175769_3_) {
               throw new NumberInvalidException("commands.generic.double.tooSmall", new Object[]{Double.valueOf(d0), Integer.valueOf(p_175769_3_)});
            }

            if(d0 > (double)p_175769_4_) {
               throw new NumberInvalidException("commands.generic.double.tooBig", new Object[]{Double.valueOf(d0), Integer.valueOf(p_175769_4_)});
            }
         }

         return d0;
      }
   }

   public static Item func_147179_f(ICommandSender p_147179_0_, String p_147179_1_) throws NumberInvalidException {
      ResourceLocation resourcelocation = new ResourceLocation(p_147179_1_);
      Item item = (Item)Item.field_150901_e.func_82594_a(resourcelocation);
      if(item == null) {
         throw new NumberInvalidException("commands.give.item.notFound", new Object[]{resourcelocation});
      } else {
         return item;
      }
   }

   public static Block func_147180_g(ICommandSender p_147180_0_, String p_147180_1_) throws NumberInvalidException {
      ResourceLocation resourcelocation = new ResourceLocation(p_147180_1_);
      if(!Block.field_149771_c.func_148741_d(resourcelocation)) {
         throw new NumberInvalidException("commands.give.block.notFound", new Object[]{resourcelocation});
      } else {
         Block block = (Block)Block.field_149771_c.func_82594_a(resourcelocation);
         if(block == null) {
            throw new NumberInvalidException("commands.give.block.notFound", new Object[]{resourcelocation});
         } else {
            return block;
         }
      }
   }

   public static String func_71527_a(Object[] p_71527_0_) {
      StringBuilder stringbuilder = new StringBuilder();

      for(int i = 0; i < p_71527_0_.length; ++i) {
         String s = p_71527_0_[i].toString();
         if(i > 0) {
            if(i == p_71527_0_.length - 1) {
               stringbuilder.append(" and ");
            } else {
               stringbuilder.append(", ");
            }
         }

         stringbuilder.append(s);
      }

      return stringbuilder.toString();
   }

   public static IChatComponent func_180530_a(List<IChatComponent> p_180530_0_) {
      IChatComponent ichatcomponent = new ChatComponentText("");

      for(int i = 0; i < p_180530_0_.size(); ++i) {
         if(i > 0) {
            if(i == p_180530_0_.size() - 1) {
               ichatcomponent.func_150258_a(" and ");
            } else if(i > 0) {
               ichatcomponent.func_150258_a(", ");
            }
         }

         ichatcomponent.func_150257_a((IChatComponent)p_180530_0_.get(i));
      }

      return ichatcomponent;
   }

   public static String func_96333_a(Collection<String> p_96333_0_) {
      return func_71527_a(p_96333_0_.toArray(new String[p_96333_0_.size()]));
   }

   public static List<String> func_175771_a(String[] p_175771_0_, int p_175771_1_, BlockPos p_175771_2_) {
      if(p_175771_2_ == null) {
         return null;
      } else {
         int i = p_175771_0_.length - 1;
         String s;
         if(i == p_175771_1_) {
            s = Integer.toString(p_175771_2_.func_177958_n());
         } else if(i == p_175771_1_ + 1) {
            s = Integer.toString(p_175771_2_.func_177956_o());
         } else {
            if(i != p_175771_1_ + 2) {
               return null;
            }

            s = Integer.toString(p_175771_2_.func_177952_p());
         }

         return Lists.newArrayList(new String[]{s});
      }
   }

   public static List<String> func_181043_b(String[] p_181043_0_, int p_181043_1_, BlockPos p_181043_2_) {
      if(p_181043_2_ == null) {
         return null;
      } else {
         int i = p_181043_0_.length - 1;
         String s;
         if(i == p_181043_1_) {
            s = Integer.toString(p_181043_2_.func_177958_n());
         } else {
            if(i != p_181043_1_ + 1) {
               return null;
            }

            s = Integer.toString(p_181043_2_.func_177952_p());
         }

         return Lists.newArrayList(new String[]{s});
      }
   }

   public static boolean func_71523_a(String p_71523_0_, String p_71523_1_) {
      return p_71523_1_.regionMatches(true, 0, p_71523_0_, 0, p_71523_0_.length());
   }

   public static List<String> func_71530_a(String[] p_71530_0_, String... p_71530_1_) {
      return func_175762_a(p_71530_0_, Arrays.asList(p_71530_1_));
   }

   public static List<String> func_175762_a(String[] p_175762_0_, Collection<?> p_175762_1_) {
      String s = p_175762_0_[p_175762_0_.length - 1];
      List<String> list = Lists.<String>newArrayList();
      if(!p_175762_1_.isEmpty()) {
         for(String s1 : Iterables.transform(p_175762_1_, Functions.toStringFunction())) {
            if(func_71523_a(s, s1)) {
               list.add(s1);
            }
         }

         if(list.isEmpty()) {
            for(Object object : p_175762_1_) {
               if(object instanceof ResourceLocation && func_71523_a(s, ((ResourceLocation)object).func_110623_a())) {
                  list.add(String.valueOf(object));
               }
            }
         }
      }

      return list;
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return false;
   }

   public static void func_152373_a(ICommandSender p_152373_0_, ICommand p_152373_1_, String p_152373_2_, Object... p_152373_3_) {
      func_152374_a(p_152373_0_, p_152373_1_, 0, p_152373_2_, p_152373_3_);
   }

   public static void func_152374_a(ICommandSender p_152374_0_, ICommand p_152374_1_, int p_152374_2_, String p_152374_3_, Object... p_152374_4_) {
      if(field_71533_a != null) {
         field_71533_a.func_152372_a(p_152374_0_, p_152374_1_, p_152374_2_, p_152374_3_, p_152374_4_);
      }

   }

   public static void func_71529_a(IAdminCommand p_71529_0_) {
      field_71533_a = p_71529_0_;
   }

   public int compareTo(ICommand p_compareTo_1_) {
      return this.func_71517_b().compareTo(p_compareTo_1_.func_71517_b());
   }

   public static class CoordinateArg {
      private final double field_179633_a;
      private final double field_179631_b;
      private final boolean field_179632_c;

      protected CoordinateArg(double p_i46051_1_, double p_i46051_3_, boolean p_i46051_5_) {
         this.field_179633_a = p_i46051_1_;
         this.field_179631_b = p_i46051_3_;
         this.field_179632_c = p_i46051_5_;
      }

      public double func_179628_a() {
         return this.field_179633_a;
      }

      public double func_179629_b() {
         return this.field_179631_b;
      }

      public boolean func_179630_c() {
         return this.field_179632_c;
      }
   }
}
