package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveFormatOld implements ISaveFormat {
   private static final Logger field_151479_b = LogManager.getLogger();
   protected final File field_75808_a;

   public SaveFormatOld(File p_i2147_1_) {
      if(!p_i2147_1_.exists()) {
         p_i2147_1_.mkdirs();
      }

      this.field_75808_a = p_i2147_1_;
   }

   public String func_154333_a() {
      return "Old Format";
   }

   public List<SaveFormatComparator> func_75799_b() throws AnvilConverterException {
      List<SaveFormatComparator> list = Lists.<SaveFormatComparator>newArrayList();

      for(int i = 0; i < 5; ++i) {
         String s = "World" + (i + 1);
         WorldInfo worldinfo = this.func_75803_c(s);
         if(worldinfo != null) {
            list.add(new SaveFormatComparator(s, "", worldinfo.func_76057_l(), worldinfo.func_76092_g(), worldinfo.func_76077_q(), false, worldinfo.func_76093_s(), worldinfo.func_76086_u()));
         }
      }

      return list;
   }

   public void func_75800_d() {
   }

   public WorldInfo func_75803_c(String p_75803_1_) {
      File file1 = new File(this.field_75808_a, p_75803_1_);
      if(!file1.exists()) {
         return null;
      } else {
         File file2 = new File(file1, "level.dat");
         if(file2.exists()) {
            try {
               NBTTagCompound nbttagcompound2 = CompressedStreamTools.func_74796_a(new FileInputStream(file2));
               NBTTagCompound nbttagcompound3 = nbttagcompound2.func_74775_l("Data");
               return new WorldInfo(nbttagcompound3);
            } catch (Exception exception1) {
               field_151479_b.error((String)("Exception reading " + file2), (Throwable)exception1);
            }
         }

         file2 = new File(file1, "level.dat_old");
         if(file2.exists()) {
            try {
               NBTTagCompound nbttagcompound = CompressedStreamTools.func_74796_a(new FileInputStream(file2));
               NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("Data");
               return new WorldInfo(nbttagcompound1);
            } catch (Exception exception) {
               field_151479_b.error((String)("Exception reading " + file2), (Throwable)exception);
            }
         }

         return null;
      }
   }

   public void func_75806_a(String p_75806_1_, String p_75806_2_) {
      File file1 = new File(this.field_75808_a, p_75806_1_);
      if(file1.exists()) {
         File file2 = new File(file1, "level.dat");
         if(file2.exists()) {
            try {
               NBTTagCompound nbttagcompound = CompressedStreamTools.func_74796_a(new FileInputStream(file2));
               NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("Data");
               nbttagcompound1.func_74778_a("LevelName", p_75806_2_);
               CompressedStreamTools.func_74799_a(nbttagcompound, new FileOutputStream(file2));
            } catch (Exception exception) {
               exception.printStackTrace();
            }
         }

      }
   }

   public boolean func_154335_d(String p_154335_1_) {
      File file1 = new File(this.field_75808_a, p_154335_1_);
      if(file1.exists()) {
         return false;
      } else {
         try {
            file1.mkdir();
            file1.delete();
            return true;
         } catch (Throwable throwable) {
            field_151479_b.warn("Couldn\'t make new level", throwable);
            return false;
         }
      }
   }

   public boolean func_75802_e(String p_75802_1_) {
      File file1 = new File(this.field_75808_a, p_75802_1_);
      if(!file1.exists()) {
         return true;
      } else {
         field_151479_b.info("Deleting level " + p_75802_1_);

         for(int i = 1; i <= 5; ++i) {
            field_151479_b.info("Attempt " + i + "...");
            if(func_75807_a(file1.listFiles())) {
               break;
            }

            field_151479_b.warn("Unsuccessful in deleting contents.");
            if(i < 5) {
               try {
                  Thread.sleep(500L);
               } catch (InterruptedException var5) {
                  ;
               }
            }
         }

         return file1.delete();
      }
   }

   protected static boolean func_75807_a(File[] p_75807_0_) {
      for(int i = 0; i < p_75807_0_.length; ++i) {
         File file1 = p_75807_0_[i];
         field_151479_b.debug("Deleting " + file1);
         if(file1.isDirectory() && !func_75807_a(file1.listFiles())) {
            field_151479_b.warn("Couldn\'t delete directory " + file1);
            return false;
         }

         if(!file1.delete()) {
            field_151479_b.warn("Couldn\'t delete file " + file1);
            return false;
         }
      }

      return true;
   }

   public ISaveHandler func_75804_a(String p_75804_1_, boolean p_75804_2_) {
      return new SaveHandler(this.field_75808_a, p_75804_1_, p_75804_2_);
   }

   public boolean func_154334_a(String p_154334_1_) {
      return false;
   }

   public boolean func_75801_b(String p_75801_1_) {
      return false;
   }

   public boolean func_75805_a(String p_75805_1_, IProgressUpdate p_75805_2_) {
      return false;
   }

   public boolean func_90033_f(String p_90033_1_) {
      File file1 = new File(this.field_75808_a, p_90033_1_);
      return file1.isDirectory();
   }
}
