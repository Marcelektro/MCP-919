package net.minecraft.client.resources;

import com.google.common.collect.Sets;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import net.minecraft.client.resources.AbstractResourcePack;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class FolderResourcePack extends AbstractResourcePack {
   public FolderResourcePack(File p_i1291_1_) {
      super(p_i1291_1_);
   }

   protected InputStream func_110591_a(String p_110591_1_) throws IOException {
      return new BufferedInputStream(new FileInputStream(new File(this.field_110597_b, p_110591_1_)));
   }

   protected boolean func_110593_b(String p_110593_1_) {
      return (new File(this.field_110597_b, p_110593_1_)).isFile();
   }

   public Set<String> func_110587_b() {
      Set<String> set = Sets.<String>newHashSet();
      File file1 = new File(this.field_110597_b, "assets/");
      if(file1.isDirectory()) {
         for(File file2 : file1.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY)) {
            String s = func_110595_a(file1, file2);
            if(!s.equals(s.toLowerCase())) {
               this.func_110594_c(s);
            } else {
               set.add(s.substring(0, s.length() - 1));
            }
         }
      }

      return set;
   }
}
