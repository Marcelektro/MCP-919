package net.minecraft.client.audio;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.netty.util.internal.ThreadLocalRandom;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.SoundSystemLogger;
import paulscode.sound.Source;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class SoundManager {
   private static final Marker field_148623_a = MarkerManager.getMarker("SOUNDS");
   private static final Logger field_148621_b = LogManager.getLogger();
   private final SoundHandler field_148622_c;
   private final GameSettings field_148619_d;
   private SoundManager.SoundSystemStarterThread field_148620_e;
   private boolean field_148617_f;
   private int field_148618_g = 0;
   private final Map<String, ISound> field_148629_h = HashBiMap.<String, ISound>create();
   private final Map<ISound, String> field_148630_i;
   private Map<ISound, SoundPoolEntry> field_148627_j;
   private final Multimap<SoundCategory, String> field_148628_k;
   private final List<ITickableSound> field_148625_l;
   private final Map<ISound, Integer> field_148626_m;
   private final Map<String, Integer> field_148624_n;

   public SoundManager(SoundHandler p_i45119_1_, GameSettings p_i45119_2_) {
      this.field_148630_i = ((BiMap)this.field_148629_h).inverse();
      this.field_148627_j = Maps.<ISound, SoundPoolEntry>newHashMap();
      this.field_148628_k = HashMultimap.<SoundCategory, String>create();
      this.field_148625_l = Lists.<ITickableSound>newArrayList();
      this.field_148626_m = Maps.<ISound, Integer>newHashMap();
      this.field_148624_n = Maps.<String, Integer>newHashMap();
      this.field_148622_c = p_i45119_1_;
      this.field_148619_d = p_i45119_2_;

      try {
         SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
         SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
      } catch (SoundSystemException soundsystemexception) {
         field_148621_b.error(field_148623_a, (String)"Error linking with the LibraryJavaSound plug-in", (Throwable)soundsystemexception);
      }

   }

   public void func_148596_a() {
      this.func_148613_b();
      this.func_148608_i();
   }

   private synchronized void func_148608_i() {
      if(!this.field_148617_f) {
         try {
            (new Thread(new Runnable() {
               public void run() {
                  SoundSystemConfig.setLogger(new SoundSystemLogger() {
                     public void message(String p_message_1_, int p_message_2_) {
                        if(!p_message_1_.isEmpty()) {
                           SoundManager.field_148621_b.info(p_message_1_);
                        }

                     }

                     public void importantMessage(String p_importantMessage_1_, int p_importantMessage_2_) {
                        if(!p_importantMessage_1_.isEmpty()) {
                           SoundManager.field_148621_b.warn(p_importantMessage_1_);
                        }

                     }

                     public void errorMessage(String p_errorMessage_1_, String p_errorMessage_2_, int p_errorMessage_3_) {
                        if(!p_errorMessage_2_.isEmpty()) {
                           SoundManager.field_148621_b.error("Error in class \'" + p_errorMessage_1_ + "\'");
                           SoundManager.field_148621_b.error(p_errorMessage_2_);
                        }

                     }
                  });
                  SoundManager.this.field_148620_e = SoundManager.this.new SoundSystemStarterThread();
                  SoundManager.this.field_148617_f = true;
                  SoundManager.this.field_148620_e.setMasterVolume(SoundManager.this.field_148619_d.func_151438_a(SoundCategory.MASTER));
                  SoundManager.field_148621_b.info(SoundManager.field_148623_a, "Sound engine started");
               }
            }, "Sound Library Loader")).start();
         } catch (RuntimeException runtimeexception) {
            field_148621_b.error(field_148623_a, (String)"Error starting SoundSystem. Turning off sounds & music", (Throwable)runtimeexception);
            this.field_148619_d.func_151439_a(SoundCategory.MASTER, 0.0F);
            this.field_148619_d.func_74303_b();
         }

      }
   }

   private float func_148595_a(SoundCategory p_148595_1_) {
      return p_148595_1_ != null && p_148595_1_ != SoundCategory.MASTER?this.field_148619_d.func_151438_a(p_148595_1_):1.0F;
   }

   public void func_148601_a(SoundCategory p_148601_1_, float p_148601_2_) {
      if(this.field_148617_f) {
         if(p_148601_1_ == SoundCategory.MASTER) {
            this.field_148620_e.setMasterVolume(p_148601_2_);
         } else {
            for(String s : this.field_148628_k.get(p_148601_1_)) {
               ISound isound = (ISound)this.field_148629_h.get(s);
               float f = this.func_148594_a(isound, (SoundPoolEntry)this.field_148627_j.get(isound), p_148601_1_);
               if(f <= 0.0F) {
                  this.func_148602_b(isound);
               } else {
                  this.field_148620_e.setVolume(s, f);
               }
            }

         }
      }
   }

   public void func_148613_b() {
      if(this.field_148617_f) {
         this.func_148614_c();
         this.field_148620_e.cleanup();
         this.field_148617_f = false;
      }

   }

   public void func_148614_c() {
      if(this.field_148617_f) {
         for(String s : this.field_148629_h.keySet()) {
            this.field_148620_e.stop(s);
         }

         this.field_148629_h.clear();
         this.field_148626_m.clear();
         this.field_148625_l.clear();
         this.field_148628_k.clear();
         this.field_148627_j.clear();
         this.field_148624_n.clear();
      }

   }

   public void func_148605_d() {
      ++this.field_148618_g;

      for(ITickableSound itickablesound : this.field_148625_l) {
         itickablesound.func_73660_a();
         if(itickablesound.func_147667_k()) {
            this.func_148602_b(itickablesound);
         } else {
            String s = (String)this.field_148630_i.get(itickablesound);
            this.field_148620_e.setVolume(s, this.func_148594_a(itickablesound, (SoundPoolEntry)this.field_148627_j.get(itickablesound), this.field_148622_c.func_147680_a(itickablesound.func_147650_b()).func_148728_d()));
            this.field_148620_e.setPitch(s, this.func_148606_a(itickablesound, (SoundPoolEntry)this.field_148627_j.get(itickablesound)));
            this.field_148620_e.setPosition(s, itickablesound.func_147649_g(), itickablesound.func_147654_h(), itickablesound.func_147651_i());
         }
      }

      Iterator<Entry<String, ISound>> iterator = this.field_148629_h.entrySet().iterator();

      while(iterator.hasNext()) {
         Entry<String, ISound> entry = (Entry)iterator.next();
         String s1 = (String)entry.getKey();
         ISound isound = (ISound)entry.getValue();
         if(!this.field_148620_e.playing(s1)) {
            int i = ((Integer)this.field_148624_n.get(s1)).intValue();
            if(i <= this.field_148618_g) {
               int j = isound.func_147652_d();
               if(isound.func_147657_c() && j > 0) {
                  this.field_148626_m.put(isound, Integer.valueOf(this.field_148618_g + j));
               }

               iterator.remove();
               field_148621_b.debug(field_148623_a, "Removed channel {} because it\'s not playing anymore", new Object[]{s1});
               this.field_148620_e.removeSource(s1);
               this.field_148624_n.remove(s1);
               this.field_148627_j.remove(isound);

               try {
                  this.field_148628_k.remove(this.field_148622_c.func_147680_a(isound.func_147650_b()).func_148728_d(), s1);
               } catch (RuntimeException var8) {
                  ;
               }

               if(isound instanceof ITickableSound) {
                  this.field_148625_l.remove(isound);
               }
            }
         }
      }

      Iterator<Entry<ISound, Integer>> iterator1 = this.field_148626_m.entrySet().iterator();

      while(iterator1.hasNext()) {
         Entry<ISound, Integer> entry1 = (Entry)iterator1.next();
         if(this.field_148618_g >= ((Integer)entry1.getValue()).intValue()) {
            ISound isound1 = (ISound)entry1.getKey();
            if(isound1 instanceof ITickableSound) {
               ((ITickableSound)isound1).func_73660_a();
            }

            this.func_148611_c(isound1);
            iterator1.remove();
         }
      }

   }

   public boolean func_148597_a(ISound p_148597_1_) {
      if(!this.field_148617_f) {
         return false;
      } else {
         String s = (String)this.field_148630_i.get(p_148597_1_);
         return s == null?false:this.field_148620_e.playing(s) || this.field_148624_n.containsKey(s) && ((Integer)this.field_148624_n.get(s)).intValue() <= this.field_148618_g;
      }
   }

   public void func_148602_b(ISound p_148602_1_) {
      if(this.field_148617_f) {
         String s = (String)this.field_148630_i.get(p_148602_1_);
         if(s != null) {
            this.field_148620_e.stop(s);
         }

      }
   }

   public void func_148611_c(ISound p_148611_1_) {
      if(this.field_148617_f) {
         if(this.field_148620_e.getMasterVolume() <= 0.0F) {
            field_148621_b.debug(field_148623_a, "Skipped playing soundEvent: {}, master volume was zero", new Object[]{p_148611_1_.func_147650_b()});
         } else {
            SoundEventAccessorComposite soundeventaccessorcomposite = this.field_148622_c.func_147680_a(p_148611_1_.func_147650_b());
            if(soundeventaccessorcomposite == null) {
               field_148621_b.warn(field_148623_a, "Unable to play unknown soundEvent: {}", new Object[]{p_148611_1_.func_147650_b()});
            } else {
               SoundPoolEntry soundpoolentry = soundeventaccessorcomposite.func_148720_g();
               if(soundpoolentry == SoundHandler.field_147700_a) {
                  field_148621_b.warn(field_148623_a, "Unable to play empty soundEvent: {}", new Object[]{soundeventaccessorcomposite.func_148729_c()});
               } else {
                  float f = p_148611_1_.func_147653_e();
                  float f1 = 16.0F;
                  if(f > 1.0F) {
                     f1 *= f;
                  }

                  SoundCategory soundcategory = soundeventaccessorcomposite.func_148728_d();
                  float f2 = this.func_148594_a(p_148611_1_, soundpoolentry, soundcategory);
                  double d0 = (double)this.func_148606_a(p_148611_1_, soundpoolentry);
                  ResourceLocation resourcelocation = soundpoolentry.func_148652_a();
                  if(f2 == 0.0F) {
                     field_148621_b.debug(field_148623_a, "Skipped playing sound {}, volume was zero.", new Object[]{resourcelocation});
                  } else {
                     boolean flag = p_148611_1_.func_147657_c() && p_148611_1_.func_147652_d() == 0;
                     String s = MathHelper.func_180182_a(ThreadLocalRandom.current()).toString();
                     if(soundpoolentry.func_148648_d()) {
                        this.field_148620_e.newStreamingSource(false, s, func_148612_a(resourcelocation), resourcelocation.toString(), flag, p_148611_1_.func_147649_g(), p_148611_1_.func_147654_h(), p_148611_1_.func_147651_i(), p_148611_1_.func_147656_j().func_148586_a(), f1);
                     } else {
                        this.field_148620_e.newSource(false, s, func_148612_a(resourcelocation), resourcelocation.toString(), flag, p_148611_1_.func_147649_g(), p_148611_1_.func_147654_h(), p_148611_1_.func_147651_i(), p_148611_1_.func_147656_j().func_148586_a(), f1);
                     }

                     field_148621_b.debug(field_148623_a, "Playing sound {} for event {} as channel {}", new Object[]{soundpoolentry.func_148652_a(), soundeventaccessorcomposite.func_148729_c(), s});
                     this.field_148620_e.setPitch(s, (float)d0);
                     this.field_148620_e.setVolume(s, f2);
                     this.field_148620_e.play(s);
                     this.field_148624_n.put(s, Integer.valueOf(this.field_148618_g + 20));
                     this.field_148629_h.put(s, p_148611_1_);
                     this.field_148627_j.put(p_148611_1_, soundpoolentry);
                     if(soundcategory != SoundCategory.MASTER) {
                        this.field_148628_k.put(soundcategory, s);
                     }

                     if(p_148611_1_ instanceof ITickableSound) {
                        this.field_148625_l.add((ITickableSound)p_148611_1_);
                     }

                  }
               }
            }
         }
      }
   }

   private float func_148606_a(ISound p_148606_1_, SoundPoolEntry p_148606_2_) {
      return (float)MathHelper.func_151237_a((double)p_148606_1_.func_147655_f() * p_148606_2_.func_148650_b(), 0.5D, 2.0D);
   }

   private float func_148594_a(ISound p_148594_1_, SoundPoolEntry p_148594_2_, SoundCategory p_148594_3_) {
      return (float)MathHelper.func_151237_a((double)p_148594_1_.func_147653_e() * p_148594_2_.func_148649_c(), 0.0D, 1.0D) * this.func_148595_a(p_148594_3_);
   }

   public void func_148610_e() {
      for(String s : this.field_148629_h.keySet()) {
         field_148621_b.debug(field_148623_a, "Pausing channel {}", new Object[]{s});
         this.field_148620_e.pause(s);
      }

   }

   public void func_148604_f() {
      for(String s : this.field_148629_h.keySet()) {
         field_148621_b.debug(field_148623_a, "Resuming channel {}", new Object[]{s});
         this.field_148620_e.play(s);
      }

   }

   public void func_148599_a(ISound p_148599_1_, int p_148599_2_) {
      this.field_148626_m.put(p_148599_1_, Integer.valueOf(this.field_148618_g + p_148599_2_));
   }

   private static URL func_148612_a(final ResourceLocation p_148612_0_) {
      String s = String.format("%s:%s:%s", new Object[]{"mcsounddomain", p_148612_0_.func_110624_b(), p_148612_0_.func_110623_a()});
      URLStreamHandler urlstreamhandler = new URLStreamHandler() {
         protected URLConnection openConnection(final URL p_openConnection_1_) {
            return new URLConnection(p_openConnection_1_) {
               public void connect() throws IOException {
               }

               public InputStream getInputStream() throws IOException {
                  return Minecraft.func_71410_x().func_110442_L().func_110536_a(p_148612_0_).func_110527_b();
               }
            };
         }
      };

      try {
         return new URL((URL)null, s, urlstreamhandler);
      } catch (MalformedURLException var4) {
         throw new Error("TODO: Sanely handle url exception! :D");
      }
   }

   public void func_148615_a(EntityPlayer p_148615_1_, float p_148615_2_) {
      if(this.field_148617_f && p_148615_1_ != null) {
         float f = p_148615_1_.field_70127_C + (p_148615_1_.field_70125_A - p_148615_1_.field_70127_C) * p_148615_2_;
         float f1 = p_148615_1_.field_70126_B + (p_148615_1_.field_70177_z - p_148615_1_.field_70126_B) * p_148615_2_;
         double d0 = p_148615_1_.field_70169_q + (p_148615_1_.field_70165_t - p_148615_1_.field_70169_q) * (double)p_148615_2_;
         double d1 = p_148615_1_.field_70167_r + (p_148615_1_.field_70163_u - p_148615_1_.field_70167_r) * (double)p_148615_2_ + (double)p_148615_1_.func_70047_e();
         double d2 = p_148615_1_.field_70166_s + (p_148615_1_.field_70161_v - p_148615_1_.field_70166_s) * (double)p_148615_2_;
         float f2 = MathHelper.func_76134_b((f1 + 90.0F) * 0.017453292F);
         float f3 = MathHelper.func_76126_a((f1 + 90.0F) * 0.017453292F);
         float f4 = MathHelper.func_76134_b(-f * 0.017453292F);
         float f5 = MathHelper.func_76126_a(-f * 0.017453292F);
         float f6 = MathHelper.func_76134_b((-f + 90.0F) * 0.017453292F);
         float f7 = MathHelper.func_76126_a((-f + 90.0F) * 0.017453292F);
         float f8 = f2 * f4;
         float f9 = f3 * f4;
         float f10 = f2 * f6;
         float f11 = f3 * f6;
         this.field_148620_e.setListenerPosition((float)d0, (float)d1, (float)d2);
         this.field_148620_e.setListenerOrientation(f8, f5, f9, f10, f7, f11);
      }
   }

   class SoundSystemStarterThread extends SoundSystem {
      private SoundSystemStarterThread() {
      }

      public boolean playing(String p_playing_1_) {
         synchronized(SoundSystemConfig.THREAD_SYNC) {
            if(this.soundLibrary == null) {
               return false;
            } else {
               Source source = (Source)this.soundLibrary.getSources().get(p_playing_1_);
               return source == null?false:source.playing() || source.paused() || source.preLoad;
            }
         }
      }
   }
}
