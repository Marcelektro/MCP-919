package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SoundHandler implements IResourceManagerReloadListener, ITickable {
   private static final Logger field_147698_b = LogManager.getLogger();
   private static final Gson field_147699_c = (new GsonBuilder()).registerTypeAdapter(SoundList.class, new SoundListSerializer()).create();
   private static final ParameterizedType field_147696_d = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{String.class, SoundList.class};
      }

      public Type getRawType() {
         return Map.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };
   public static final SoundPoolEntry field_147700_a = new SoundPoolEntry(new ResourceLocation("meta:missing_sound"), 0.0D, 0.0D, false);
   private final SoundRegistry field_147697_e = new SoundRegistry();
   private final SoundManager field_147694_f;
   private final IResourceManager field_147695_g;

   public SoundHandler(IResourceManager p_i45122_1_, GameSettings p_i45122_2_) {
      this.field_147695_g = p_i45122_1_;
      this.field_147694_f = new SoundManager(this, p_i45122_2_);
   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      this.field_147694_f.func_148596_a();
      this.field_147697_e.func_148763_c();

      for(String s : p_110549_1_.func_135055_a()) {
         try {
            for(IResource iresource : p_110549_1_.func_135056_b(new ResourceLocation(s, "sounds.json"))) {
               try {
                  Map<String, SoundList> map = this.func_175085_a(iresource.func_110527_b());

                  for(Entry<String, SoundList> entry : map.entrySet()) {
                     this.func_147693_a(new ResourceLocation(s, (String)entry.getKey()), (SoundList)entry.getValue());
                  }
               } catch (RuntimeException runtimeexception) {
                  field_147698_b.warn((String)"Invalid sounds.json", (Throwable)runtimeexception);
               }
            }
         } catch (IOException var11) {
            ;
         }
      }

   }

   protected Map<String, SoundList> func_175085_a(InputStream p_175085_1_) {
      Map map;
      try {
         map = (Map)field_147699_c.fromJson((Reader)(new InputStreamReader(p_175085_1_)), field_147696_d);
      } finally {
         IOUtils.closeQuietly(p_175085_1_);
      }

      return map;
   }

   private void func_147693_a(ResourceLocation p_147693_1_, SoundList p_147693_2_) {
      boolean flag = !this.field_147697_e.func_148741_d(p_147693_1_);
      SoundEventAccessorComposite soundeventaccessorcomposite;
      if(!flag && !p_147693_2_.func_148574_b()) {
         soundeventaccessorcomposite = (SoundEventAccessorComposite)this.field_147697_e.func_82594_a(p_147693_1_);
      } else {
         if(!flag) {
            field_147698_b.debug("Replaced sound event location {}", new Object[]{p_147693_1_});
         }

         soundeventaccessorcomposite = new SoundEventAccessorComposite(p_147693_1_, 1.0D, 1.0D, p_147693_2_.func_148573_c());
         this.field_147697_e.func_148762_a(soundeventaccessorcomposite);
      }

      for(final SoundList.SoundEntry soundlist$soundentry : p_147693_2_.func_148570_a()) {
         String s = soundlist$soundentry.func_148556_a();
         ResourceLocation resourcelocation = new ResourceLocation(s);
         final String s1 = s.contains(":")?resourcelocation.func_110624_b():p_147693_1_.func_110624_b();
         ISoundEventAccessor<SoundPoolEntry> isoundeventaccessor;
         switch(soundlist$soundentry.func_148563_e()) {
         case FILE:
            ResourceLocation resourcelocation1 = new ResourceLocation(s1, "sounds/" + resourcelocation.func_110623_a() + ".ogg");
            InputStream inputstream = null;

            try {
               inputstream = this.field_147695_g.func_110536_a(resourcelocation1).func_110527_b();
            } catch (FileNotFoundException var18) {
               field_147698_b.warn("File {} does not exist, cannot add it to event {}", new Object[]{resourcelocation1, p_147693_1_});
               continue;
            } catch (IOException ioexception) {
               field_147698_b.warn((String)("Could not load sound file " + resourcelocation1 + ", cannot add it to event " + p_147693_1_), (Throwable)ioexception);
               continue;
            } finally {
               IOUtils.closeQuietly(inputstream);
            }

            isoundeventaccessor = new SoundEventAccessor(new SoundPoolEntry(resourcelocation1, (double)soundlist$soundentry.func_148560_c(), (double)soundlist$soundentry.func_148558_b(), soundlist$soundentry.func_148552_f()), soundlist$soundentry.func_148555_d());
            break;
         case SOUND_EVENT:
            isoundeventaccessor = new ISoundEventAccessor<SoundPoolEntry>() {
               final ResourceLocation field_148726_a = new ResourceLocation(s1, soundlist$soundentry.func_148556_a());

               public int func_148721_a() {
                  SoundEventAccessorComposite soundeventaccessorcomposite1 = (SoundEventAccessorComposite)SoundHandler.this.field_147697_e.func_82594_a(this.field_148726_a);
                  return soundeventaccessorcomposite1 == null?0:soundeventaccessorcomposite1.func_148721_a();
               }

               public SoundPoolEntry func_148720_g() {
                  SoundEventAccessorComposite soundeventaccessorcomposite1 = (SoundEventAccessorComposite)SoundHandler.this.field_147697_e.func_82594_a(this.field_148726_a);
                  return soundeventaccessorcomposite1 == null?SoundHandler.field_147700_a:soundeventaccessorcomposite1.func_148720_g();
               }
            };
            break;
         default:
            throw new IllegalStateException("IN YOU FACE");
         }

         soundeventaccessorcomposite.func_148727_a(isoundeventaccessor);
      }

   }

   public SoundEventAccessorComposite func_147680_a(ResourceLocation p_147680_1_) {
      return (SoundEventAccessorComposite)this.field_147697_e.func_82594_a(p_147680_1_);
   }

   public void func_147682_a(ISound p_147682_1_) {
      this.field_147694_f.func_148611_c(p_147682_1_);
   }

   public void func_147681_a(ISound p_147681_1_, int p_147681_2_) {
      this.field_147694_f.func_148599_a(p_147681_1_, p_147681_2_);
   }

   public void func_147691_a(EntityPlayer p_147691_1_, float p_147691_2_) {
      this.field_147694_f.func_148615_a(p_147691_1_, p_147691_2_);
   }

   public void func_147689_b() {
      this.field_147694_f.func_148610_e();
   }

   public void func_147690_c() {
      this.field_147694_f.func_148614_c();
   }

   public void func_147685_d() {
      this.field_147694_f.func_148613_b();
   }

   public void func_73660_a() {
      this.field_147694_f.func_148605_d();
   }

   public void func_147687_e() {
      this.field_147694_f.func_148604_f();
   }

   public void func_147684_a(SoundCategory p_147684_1_, float p_147684_2_) {
      if(p_147684_1_ == SoundCategory.MASTER && p_147684_2_ <= 0.0F) {
         this.func_147690_c();
      }

      this.field_147694_f.func_148601_a(p_147684_1_, p_147684_2_);
   }

   public void func_147683_b(ISound p_147683_1_) {
      this.field_147694_f.func_148602_b(p_147683_1_);
   }

   public SoundEventAccessorComposite func_147686_a(SoundCategory... p_147686_1_) {
      List<SoundEventAccessorComposite> list = Lists.<SoundEventAccessorComposite>newArrayList();

      for(ResourceLocation resourcelocation : this.field_147697_e.func_148742_b()) {
         SoundEventAccessorComposite soundeventaccessorcomposite = (SoundEventAccessorComposite)this.field_147697_e.func_82594_a(resourcelocation);
         if(ArrayUtils.contains(p_147686_1_, soundeventaccessorcomposite.func_148728_d())) {
            list.add(soundeventaccessorcomposite);
         }
      }

      if(list.isEmpty()) {
         return null;
      } else {
         return (SoundEventAccessorComposite)list.get((new Random()).nextInt(list.size()));
      }
   }

   public boolean func_147692_c(ISound p_147692_1_) {
      return this.field_147694_f.func_148597_a(p_147692_1_);
   }
}
