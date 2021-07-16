package net.minecraft.world.storage;

import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraft.world.storage.WorldInfo;

public interface ISaveFormat {
   String func_154333_a();

   ISaveHandler func_75804_a(String var1, boolean var2);

   List<SaveFormatComparator> func_75799_b() throws AnvilConverterException;

   void func_75800_d();

   WorldInfo func_75803_c(String var1);

   boolean func_154335_d(String var1);

   boolean func_75802_e(String var1);

   void func_75806_a(String var1, String var2);

   boolean func_154334_a(String var1);

   boolean func_75801_b(String var1);

   boolean func_75805_a(String var1, IProgressUpdate var2);

   boolean func_90033_f(String var1);
}
