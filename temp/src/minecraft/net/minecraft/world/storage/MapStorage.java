package net.minecraft.world.storage;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.ISaveHandler;

public class MapStorage {
   private ISaveHandler field_75751_a;
   protected Map<String, WorldSavedData> field_75749_b = Maps.<String, WorldSavedData>newHashMap();
   private List<WorldSavedData> field_75750_c = Lists.<WorldSavedData>newArrayList();
   private Map<String, Short> field_75748_d = Maps.<String, Short>newHashMap();

   public MapStorage(ISaveHandler p_i2162_1_) {
      this.field_75751_a = p_i2162_1_;
      this.func_75746_b();
   }

   public WorldSavedData func_75742_a(Class<? extends WorldSavedData> p_75742_1_, String p_75742_2_) {
      WorldSavedData worldsaveddata = (WorldSavedData)this.field_75749_b.get(p_75742_2_);
      if(worldsaveddata != null) {
         return worldsaveddata;
      } else {
         if(this.field_75751_a != null) {
            try {
               File file1 = this.field_75751_a.func_75758_b(p_75742_2_);
               if(file1 != null && file1.exists()) {
                  try {
                     worldsaveddata = (WorldSavedData)p_75742_1_.getConstructor(new Class[]{String.class}).newInstance(new Object[]{p_75742_2_});
                  } catch (Exception exception) {
                     throw new RuntimeException("Failed to instantiate " + p_75742_1_.toString(), exception);
                  }

                  FileInputStream fileinputstream = new FileInputStream(file1);
                  NBTTagCompound nbttagcompound = CompressedStreamTools.func_74796_a(fileinputstream);
                  fileinputstream.close();
                  worldsaveddata.func_76184_a(nbttagcompound.func_74775_l("data"));
               }
            } catch (Exception exception1) {
               exception1.printStackTrace();
            }
         }

         if(worldsaveddata != null) {
            this.field_75749_b.put(p_75742_2_, worldsaveddata);
            this.field_75750_c.add(worldsaveddata);
         }

         return worldsaveddata;
      }
   }

   public void func_75745_a(String p_75745_1_, WorldSavedData p_75745_2_) {
      if(this.field_75749_b.containsKey(p_75745_1_)) {
         this.field_75750_c.remove(this.field_75749_b.remove(p_75745_1_));
      }

      this.field_75749_b.put(p_75745_1_, p_75745_2_);
      this.field_75750_c.add(p_75745_2_);
   }

   public void func_75744_a() {
      for(int i = 0; i < this.field_75750_c.size(); ++i) {
         WorldSavedData worldsaveddata = (WorldSavedData)this.field_75750_c.get(i);
         if(worldsaveddata.func_76188_b()) {
            this.func_75747_a(worldsaveddata);
            worldsaveddata.func_76186_a(false);
         }
      }

   }

   private void func_75747_a(WorldSavedData p_75747_1_) {
      if(this.field_75751_a != null) {
         try {
            File file1 = this.field_75751_a.func_75758_b(p_75747_1_.field_76190_i);
            if(file1 != null) {
               NBTTagCompound nbttagcompound = new NBTTagCompound();
               p_75747_1_.func_76187_b(nbttagcompound);
               NBTTagCompound nbttagcompound1 = new NBTTagCompound();
               nbttagcompound1.func_74782_a("data", nbttagcompound);
               FileOutputStream fileoutputstream = new FileOutputStream(file1);
               CompressedStreamTools.func_74799_a(nbttagcompound1, fileoutputstream);
               fileoutputstream.close();
            }
         } catch (Exception exception) {
            exception.printStackTrace();
         }

      }
   }

   private void func_75746_b() {
      try {
         this.field_75748_d.clear();
         if(this.field_75751_a == null) {
            return;
         }

         File file1 = this.field_75751_a.func_75758_b("idcounts");
         if(file1 != null && file1.exists()) {
            DataInputStream datainputstream = new DataInputStream(new FileInputStream(file1));
            NBTTagCompound nbttagcompound = CompressedStreamTools.func_74794_a(datainputstream);
            datainputstream.close();

            for(String s : nbttagcompound.func_150296_c()) {
               NBTBase nbtbase = nbttagcompound.func_74781_a(s);
               if(nbtbase instanceof NBTTagShort) {
                  NBTTagShort nbttagshort = (NBTTagShort)nbtbase;
                  short short1 = nbttagshort.func_150289_e();
                  this.field_75748_d.put(s, Short.valueOf(short1));
               }
            }
         }
      } catch (Exception exception) {
         exception.printStackTrace();
      }

   }

   public int func_75743_a(String p_75743_1_) {
      Short oshort = (Short)this.field_75748_d.get(p_75743_1_);
      if(oshort == null) {
         oshort = Short.valueOf((short)0);
      } else {
         oshort = Short.valueOf((short)(oshort.shortValue() + 1));
      }

      this.field_75748_d.put(p_75743_1_, oshort);
      if(this.field_75751_a == null) {
         return oshort.shortValue();
      } else {
         try {
            File file1 = this.field_75751_a.func_75758_b("idcounts");
            if(file1 != null) {
               NBTTagCompound nbttagcompound = new NBTTagCompound();

               for(String s : this.field_75748_d.keySet()) {
                  short short1 = ((Short)this.field_75748_d.get(s)).shortValue();
                  nbttagcompound.func_74777_a(s, short1);
               }

               DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(file1));
               CompressedStreamTools.func_74800_a(nbttagcompound, dataoutputstream);
               dataoutputstream.close();
            }
         } catch (Exception exception) {
            exception.printStackTrace();
         }

         return oshort.shortValue();
      }
   }
}
