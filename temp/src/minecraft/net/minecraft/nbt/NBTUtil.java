package net.minecraft.nbt;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StringUtils;

public final class NBTUtil {
   public static GameProfile func_152459_a(NBTTagCompound p_152459_0_) {
      String s = null;
      String s1 = null;
      if(p_152459_0_.func_150297_b("Name", 8)) {
         s = p_152459_0_.func_74779_i("Name");
      }

      if(p_152459_0_.func_150297_b("Id", 8)) {
         s1 = p_152459_0_.func_74779_i("Id");
      }

      if(StringUtils.func_151246_b(s) && StringUtils.func_151246_b(s1)) {
         return null;
      } else {
         UUID uuid;
         try {
            uuid = UUID.fromString(s1);
         } catch (Throwable var12) {
            uuid = null;
         }

         GameProfile gameprofile = new GameProfile(uuid, s);
         if(p_152459_0_.func_150297_b("Properties", 10)) {
            NBTTagCompound nbttagcompound = p_152459_0_.func_74775_l("Properties");

            for(String s2 : nbttagcompound.func_150296_c()) {
               NBTTagList nbttaglist = nbttagcompound.func_150295_c(s2, 10);

               for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                  NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
                  String s3 = nbttagcompound1.func_74779_i("Value");
                  if(nbttagcompound1.func_150297_b("Signature", 8)) {
                     gameprofile.getProperties().put(s2, new Property(s2, s3, nbttagcompound1.func_74779_i("Signature")));
                  } else {
                     gameprofile.getProperties().put(s2, new Property(s2, s3));
                  }
               }
            }
         }

         return gameprofile;
      }
   }

   public static NBTTagCompound func_180708_a(NBTTagCompound p_180708_0_, GameProfile p_180708_1_) {
      if(!StringUtils.func_151246_b(p_180708_1_.getName())) {
         p_180708_0_.func_74778_a("Name", p_180708_1_.getName());
      }

      if(p_180708_1_.getId() != null) {
         p_180708_0_.func_74778_a("Id", p_180708_1_.getId().toString());
      }

      if(!p_180708_1_.getProperties().isEmpty()) {
         NBTTagCompound nbttagcompound = new NBTTagCompound();

         for(String s : p_180708_1_.getProperties().keySet()) {
            NBTTagList nbttaglist = new NBTTagList();

            for(Property property : p_180708_1_.getProperties().get(s)) {
               NBTTagCompound nbttagcompound1 = new NBTTagCompound();
               nbttagcompound1.func_74778_a("Value", property.getValue());
               if(property.hasSignature()) {
                  nbttagcompound1.func_74778_a("Signature", property.getSignature());
               }

               nbttaglist.func_74742_a(nbttagcompound1);
            }

            nbttagcompound.func_74782_a(s, nbttaglist);
         }

         p_180708_0_.func_74782_a("Properties", nbttagcompound);
      }

      return p_180708_0_;
   }

   public static boolean func_181123_a(NBTBase p_181123_0_, NBTBase p_181123_1_, boolean p_181123_2_) {
      if(p_181123_0_ == p_181123_1_) {
         return true;
      } else if(p_181123_0_ == null) {
         return true;
      } else if(p_181123_1_ == null) {
         return false;
      } else if(!p_181123_0_.getClass().equals(p_181123_1_.getClass())) {
         return false;
      } else if(p_181123_0_ instanceof NBTTagCompound) {
         NBTTagCompound nbttagcompound = (NBTTagCompound)p_181123_0_;
         NBTTagCompound nbttagcompound1 = (NBTTagCompound)p_181123_1_;

         for(String s : nbttagcompound.func_150296_c()) {
            NBTBase nbtbase1 = nbttagcompound.func_74781_a(s);
            if(!func_181123_a(nbtbase1, nbttagcompound1.func_74781_a(s), p_181123_2_)) {
               return false;
            }
         }

         return true;
      } else if(p_181123_0_ instanceof NBTTagList && p_181123_2_) {
         NBTTagList nbttaglist = (NBTTagList)p_181123_0_;
         NBTTagList nbttaglist1 = (NBTTagList)p_181123_1_;
         if(nbttaglist.func_74745_c() == 0) {
            return nbttaglist1.func_74745_c() == 0;
         } else {
            for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
               NBTBase nbtbase = nbttaglist.func_179238_g(i);
               boolean flag = false;

               for(int j = 0; j < nbttaglist1.func_74745_c(); ++j) {
                  if(func_181123_a(nbtbase, nbttaglist1.func_179238_g(j), p_181123_2_)) {
                     flag = true;
                     break;
                  }
               }

               if(!flag) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return p_181123_0_.equals(p_181123_1_);
      }
   }
}
