package net.minecraft.crash;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.BlockPos;

public class CrashReportCategory {
   private final CrashReport field_85078_a;
   private final String field_85076_b;
   private final List<CrashReportCategory.Entry> field_85077_c = Lists.<CrashReportCategory.Entry>newArrayList();
   private StackTraceElement[] field_85075_d = new StackTraceElement[0];

   public CrashReportCategory(CrashReport p_i1353_1_, String p_i1353_2_) {
      this.field_85078_a = p_i1353_1_;
      this.field_85076_b = p_i1353_2_;
   }

   public static String func_85074_a(double p_85074_0_, double p_85074_2_, double p_85074_4_) {
      return String.format("%.2f,%.2f,%.2f - %s", new Object[]{Double.valueOf(p_85074_0_), Double.valueOf(p_85074_2_), Double.valueOf(p_85074_4_), func_180522_a(new BlockPos(p_85074_0_, p_85074_2_, p_85074_4_))});
   }

   public static String func_180522_a(BlockPos p_180522_0_) {
      int i = p_180522_0_.func_177958_n();
      int j = p_180522_0_.func_177956_o();
      int k = p_180522_0_.func_177952_p();
      StringBuilder stringbuilder = new StringBuilder();

      try {
         stringbuilder.append(String.format("World: (%d,%d,%d)", new Object[]{Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k)}));
      } catch (Throwable var17) {
         stringbuilder.append("(Error finding world loc)");
      }

      stringbuilder.append(", ");

      try {
         int l = i >> 4;
         int i1 = k >> 4;
         int j1 = i & 15;
         int k1 = j >> 4;
         int l1 = k & 15;
         int i2 = l << 4;
         int j2 = i1 << 4;
         int k2 = (l + 1 << 4) - 1;
         int l2 = (i1 + 1 << 4) - 1;
         stringbuilder.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", new Object[]{Integer.valueOf(j1), Integer.valueOf(k1), Integer.valueOf(l1), Integer.valueOf(l), Integer.valueOf(i1), Integer.valueOf(i2), Integer.valueOf(j2), Integer.valueOf(k2), Integer.valueOf(l2)}));
      } catch (Throwable var16) {
         stringbuilder.append("(Error finding chunk loc)");
      }

      stringbuilder.append(", ");

      try {
         int j3 = i >> 9;
         int k3 = k >> 9;
         int l3 = j3 << 5;
         int i4 = k3 << 5;
         int j4 = (j3 + 1 << 5) - 1;
         int k4 = (k3 + 1 << 5) - 1;
         int l4 = j3 << 9;
         int i5 = k3 << 9;
         int j5 = (j3 + 1 << 9) - 1;
         int i3 = (k3 + 1 << 9) - 1;
         stringbuilder.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", new Object[]{Integer.valueOf(j3), Integer.valueOf(k3), Integer.valueOf(l3), Integer.valueOf(i4), Integer.valueOf(j4), Integer.valueOf(k4), Integer.valueOf(l4), Integer.valueOf(i5), Integer.valueOf(j5), Integer.valueOf(i3)}));
      } catch (Throwable var15) {
         stringbuilder.append("(Error finding world loc)");
      }

      return stringbuilder.toString();
   }

   public void func_71500_a(String p_71500_1_, Callable<String> p_71500_2_) {
      try {
         this.func_71507_a(p_71500_1_, p_71500_2_.call());
      } catch (Throwable throwable) {
         this.func_71499_a(p_71500_1_, throwable);
      }

   }

   public void func_71507_a(String p_71507_1_, Object p_71507_2_) {
      this.field_85077_c.add(new CrashReportCategory.Entry(p_71507_1_, p_71507_2_));
   }

   public void func_71499_a(String p_71499_1_, Throwable p_71499_2_) {
      this.func_71507_a(p_71499_1_, p_71499_2_);
   }

   public int func_85073_a(int p_85073_1_) {
      StackTraceElement[] astacktraceelement = Thread.currentThread().getStackTrace();
      if(astacktraceelement.length <= 0) {
         return 0;
      } else {
         this.field_85075_d = new StackTraceElement[astacktraceelement.length - 3 - p_85073_1_];
         System.arraycopy(astacktraceelement, 3 + p_85073_1_, this.field_85075_d, 0, this.field_85075_d.length);
         return this.field_85075_d.length;
      }
   }

   public boolean func_85069_a(StackTraceElement p_85069_1_, StackTraceElement p_85069_2_) {
      if(this.field_85075_d.length != 0 && p_85069_1_ != null) {
         StackTraceElement stacktraceelement = this.field_85075_d[0];
         if(stacktraceelement.isNativeMethod() == p_85069_1_.isNativeMethod() && stacktraceelement.getClassName().equals(p_85069_1_.getClassName()) && stacktraceelement.getFileName().equals(p_85069_1_.getFileName()) && stacktraceelement.getMethodName().equals(p_85069_1_.getMethodName())) {
            if(p_85069_2_ != null != this.field_85075_d.length > 1) {
               return false;
            } else if(p_85069_2_ != null && !this.field_85075_d[1].equals(p_85069_2_)) {
               return false;
            } else {
               this.field_85075_d[0] = p_85069_1_;
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void func_85070_b(int p_85070_1_) {
      StackTraceElement[] astacktraceelement = new StackTraceElement[this.field_85075_d.length - p_85070_1_];
      System.arraycopy(this.field_85075_d, 0, astacktraceelement, 0, astacktraceelement.length);
      this.field_85075_d = astacktraceelement;
   }

   public void func_85072_a(StringBuilder p_85072_1_) {
      p_85072_1_.append("-- ").append(this.field_85076_b).append(" --\n");
      p_85072_1_.append("Details:");

      for(CrashReportCategory.Entry crashreportcategory$entry : this.field_85077_c) {
         p_85072_1_.append("\n\t");
         p_85072_1_.append(crashreportcategory$entry.func_85089_a());
         p_85072_1_.append(": ");
         p_85072_1_.append(crashreportcategory$entry.func_85090_b());
      }

      if(this.field_85075_d != null && this.field_85075_d.length > 0) {
         p_85072_1_.append("\nStacktrace:");

         for(StackTraceElement stacktraceelement : this.field_85075_d) {
            p_85072_1_.append("\n\tat ");
            p_85072_1_.append(stacktraceelement.toString());
         }
      }

   }

   public StackTraceElement[] func_147152_a() {
      return this.field_85075_d;
   }

   public static void func_180523_a(CrashReportCategory p_180523_0_, final BlockPos p_180523_1_, final Block p_180523_2_, final int p_180523_3_) {
      final int i = Block.func_149682_b(p_180523_2_);
      p_180523_0_.func_71500_a("Block type", new Callable<String>() {
         public String call() throws Exception {
            try {
               return String.format("ID #%d (%s // %s)", new Object[]{Integer.valueOf(i), p_180523_2_.func_149739_a(), p_180523_2_.getClass().getCanonicalName()});
            } catch (Throwable var2) {
               return "ID #" + i;
            }
         }
      });
      p_180523_0_.func_71500_a("Block data value", new Callable<String>() {
         public String call() throws Exception {
            if(p_180523_3_ < 0) {
               return "Unknown? (Got " + p_180523_3_ + ")";
            } else {
               String s = String.format("%4s", new Object[]{Integer.toBinaryString(p_180523_3_)}).replace(" ", "0");
               return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[]{Integer.valueOf(p_180523_3_), s});
            }
         }
      });
      p_180523_0_.func_71500_a("Block location", new Callable<String>() {
         public String call() throws Exception {
            return CrashReportCategory.func_180522_a(p_180523_1_);
         }
      });
   }

   public static void func_175750_a(CrashReportCategory p_175750_0_, final BlockPos p_175750_1_, final IBlockState p_175750_2_) {
      p_175750_0_.func_71500_a("Block", new Callable<String>() {
         public String call() throws Exception {
            return p_175750_2_.toString();
         }
      });
      p_175750_0_.func_71500_a("Block location", new Callable<String>() {
         public String call() throws Exception {
            return CrashReportCategory.func_180522_a(p_175750_1_);
         }
      });
   }

   static class Entry {
      private final String field_85092_a;
      private final String field_85091_b;

      public Entry(String p_i1352_1_, Object p_i1352_2_) {
         this.field_85092_a = p_i1352_1_;
         if(p_i1352_2_ == null) {
            this.field_85091_b = "~~NULL~~";
         } else if(p_i1352_2_ instanceof Throwable) {
            Throwable throwable = (Throwable)p_i1352_2_;
            this.field_85091_b = "~~ERROR~~ " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
         } else {
            this.field_85091_b = p_i1352_2_.toString();
         }

      }

      public String func_85089_a() {
         return this.field_85092_a;
      }

      public String func_85090_b() {
         return this.field_85091_b;
      }
   }
}
