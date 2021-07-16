package net.minecraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.Packet;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTracker {
   private static final Logger field_151249_a = LogManager.getLogger();
   private final WorldServer field_72795_a;
   private Set<EntityTrackerEntry> field_72793_b = Sets.<EntityTrackerEntry>newHashSet();
   private IntHashMap<EntityTrackerEntry> field_72794_c = new IntHashMap();
   private int field_72792_d;

   public EntityTracker(WorldServer p_i1516_1_) {
      this.field_72795_a = p_i1516_1_;
      this.field_72792_d = p_i1516_1_.func_73046_m().func_71203_ab().func_72372_a();
   }

   public void func_72786_a(Entity p_72786_1_) {
      if(p_72786_1_ instanceof EntityPlayerMP) {
         this.func_72791_a(p_72786_1_, 512, 2);
         EntityPlayerMP entityplayermp = (EntityPlayerMP)p_72786_1_;

         for(EntityTrackerEntry entitytrackerentry : this.field_72793_b) {
            if(entitytrackerentry.field_73132_a != entityplayermp) {
               entitytrackerentry.func_73117_b(entityplayermp);
            }
         }
      } else if(p_72786_1_ instanceof EntityFishHook) {
         this.func_72785_a(p_72786_1_, 64, 5, true);
      } else if(p_72786_1_ instanceof EntityArrow) {
         this.func_72785_a(p_72786_1_, 64, 20, false);
      } else if(p_72786_1_ instanceof EntitySmallFireball) {
         this.func_72785_a(p_72786_1_, 64, 10, false);
      } else if(p_72786_1_ instanceof EntityFireball) {
         this.func_72785_a(p_72786_1_, 64, 10, false);
      } else if(p_72786_1_ instanceof EntitySnowball) {
         this.func_72785_a(p_72786_1_, 64, 10, true);
      } else if(p_72786_1_ instanceof EntityEnderPearl) {
         this.func_72785_a(p_72786_1_, 64, 10, true);
      } else if(p_72786_1_ instanceof EntityEnderEye) {
         this.func_72785_a(p_72786_1_, 64, 4, true);
      } else if(p_72786_1_ instanceof EntityEgg) {
         this.func_72785_a(p_72786_1_, 64, 10, true);
      } else if(p_72786_1_ instanceof EntityPotion) {
         this.func_72785_a(p_72786_1_, 64, 10, true);
      } else if(p_72786_1_ instanceof EntityExpBottle) {
         this.func_72785_a(p_72786_1_, 64, 10, true);
      } else if(p_72786_1_ instanceof EntityFireworkRocket) {
         this.func_72785_a(p_72786_1_, 64, 10, true);
      } else if(p_72786_1_ instanceof EntityItem) {
         this.func_72785_a(p_72786_1_, 64, 20, true);
      } else if(p_72786_1_ instanceof EntityMinecart) {
         this.func_72785_a(p_72786_1_, 80, 3, true);
      } else if(p_72786_1_ instanceof EntityBoat) {
         this.func_72785_a(p_72786_1_, 80, 3, true);
      } else if(p_72786_1_ instanceof EntitySquid) {
         this.func_72785_a(p_72786_1_, 64, 3, true);
      } else if(p_72786_1_ instanceof EntityWither) {
         this.func_72785_a(p_72786_1_, 80, 3, false);
      } else if(p_72786_1_ instanceof EntityBat) {
         this.func_72785_a(p_72786_1_, 80, 3, false);
      } else if(p_72786_1_ instanceof EntityDragon) {
         this.func_72785_a(p_72786_1_, 160, 3, true);
      } else if(p_72786_1_ instanceof IAnimals) {
         this.func_72785_a(p_72786_1_, 80, 3, true);
      } else if(p_72786_1_ instanceof EntityTNTPrimed) {
         this.func_72785_a(p_72786_1_, 160, 10, true);
      } else if(p_72786_1_ instanceof EntityFallingBlock) {
         this.func_72785_a(p_72786_1_, 160, 20, true);
      } else if(p_72786_1_ instanceof EntityHanging) {
         this.func_72785_a(p_72786_1_, 160, Integer.MAX_VALUE, false);
      } else if(p_72786_1_ instanceof EntityArmorStand) {
         this.func_72785_a(p_72786_1_, 160, 3, true);
      } else if(p_72786_1_ instanceof EntityXPOrb) {
         this.func_72785_a(p_72786_1_, 160, 20, true);
      } else if(p_72786_1_ instanceof EntityEnderCrystal) {
         this.func_72785_a(p_72786_1_, 256, Integer.MAX_VALUE, false);
      }

   }

   public void func_72791_a(Entity p_72791_1_, int p_72791_2_, int p_72791_3_) {
      this.func_72785_a(p_72791_1_, p_72791_2_, p_72791_3_, false);
   }

   public void func_72785_a(Entity p_72785_1_, int p_72785_2_, final int p_72785_3_, boolean p_72785_4_) {
      if(p_72785_2_ > this.field_72792_d) {
         p_72785_2_ = this.field_72792_d;
      }

      try {
         if(this.field_72794_c.func_76037_b(p_72785_1_.func_145782_y())) {
            throw new IllegalStateException("Entity is already tracked!");
         }

         EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(p_72785_1_, p_72785_2_, p_72785_3_, p_72785_4_);
         this.field_72793_b.add(entitytrackerentry);
         this.field_72794_c.func_76038_a(p_72785_1_.func_145782_y(), entitytrackerentry);
         entitytrackerentry.func_73125_b(this.field_72795_a.field_73010_i);
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding entity to track");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity To Track");
         crashreportcategory.func_71507_a("Tracking range", p_72785_2_ + " blocks");
         crashreportcategory.func_71500_a("Update interval", new Callable<String>() {
            public String call() throws Exception {
               String s = "Once per " + p_72785_3_ + " ticks";
               if(p_72785_3_ == Integer.MAX_VALUE) {
                  s = "Maximum (" + s + ")";
               }

               return s;
            }
         });
         p_72785_1_.func_85029_a(crashreportcategory);
         CrashReportCategory crashreportcategory1 = crashreport.func_85058_a("Entity That Is Already Tracked");
         ((EntityTrackerEntry)this.field_72794_c.func_76041_a(p_72785_1_.func_145782_y())).field_73132_a.func_85029_a(crashreportcategory1);

         try {
            throw new ReportedException(crashreport);
         } catch (ReportedException reportedexception) {
            field_151249_a.error((String)"\"Silently\" catching entity tracking error.", (Throwable)reportedexception);
         }
      }

   }

   public void func_72790_b(Entity p_72790_1_) {
      if(p_72790_1_ instanceof EntityPlayerMP) {
         EntityPlayerMP entityplayermp = (EntityPlayerMP)p_72790_1_;

         for(EntityTrackerEntry entitytrackerentry : this.field_72793_b) {
            entitytrackerentry.func_73118_a(entityplayermp);
         }
      }

      EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)this.field_72794_c.func_76049_d(p_72790_1_.func_145782_y());
      if(entitytrackerentry1 != null) {
         this.field_72793_b.remove(entitytrackerentry1);
         entitytrackerentry1.func_73119_a();
      }

   }

   public void func_72788_a() {
      List<EntityPlayerMP> list = Lists.<EntityPlayerMP>newArrayList();

      for(EntityTrackerEntry entitytrackerentry : this.field_72793_b) {
         entitytrackerentry.func_73122_a(this.field_72795_a.field_73010_i);
         if(entitytrackerentry.field_73133_n && entitytrackerentry.field_73132_a instanceof EntityPlayerMP) {
            list.add((EntityPlayerMP)entitytrackerentry.field_73132_a);
         }
      }

      for(int i = 0; i < ((List)list).size(); ++i) {
         EntityPlayerMP entityplayermp = (EntityPlayerMP)list.get(i);

         for(EntityTrackerEntry entitytrackerentry1 : this.field_72793_b) {
            if(entitytrackerentry1.field_73132_a != entityplayermp) {
               entitytrackerentry1.func_73117_b(entityplayermp);
            }
         }
      }

   }

   public void func_180245_a(EntityPlayerMP p_180245_1_) {
      for(EntityTrackerEntry entitytrackerentry : this.field_72793_b) {
         if(entitytrackerentry.field_73132_a == p_180245_1_) {
            entitytrackerentry.func_73125_b(this.field_72795_a.field_73010_i);
         } else {
            entitytrackerentry.func_73117_b(p_180245_1_);
         }
      }

   }

   public void func_151247_a(Entity p_151247_1_, Packet p_151247_2_) {
      EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.field_72794_c.func_76041_a(p_151247_1_.func_145782_y());
      if(entitytrackerentry != null) {
         entitytrackerentry.func_151259_a(p_151247_2_);
      }

   }

   public void func_151248_b(Entity p_151248_1_, Packet p_151248_2_) {
      EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.field_72794_c.func_76041_a(p_151248_1_.func_145782_y());
      if(entitytrackerentry != null) {
         entitytrackerentry.func_151261_b(p_151248_2_);
      }

   }

   public void func_72787_a(EntityPlayerMP p_72787_1_) {
      for(EntityTrackerEntry entitytrackerentry : this.field_72793_b) {
         entitytrackerentry.func_73123_c(p_72787_1_);
      }

   }

   public void func_85172_a(EntityPlayerMP p_85172_1_, Chunk p_85172_2_) {
      for(EntityTrackerEntry entitytrackerentry : this.field_72793_b) {
         if(entitytrackerentry.field_73132_a != p_85172_1_ && entitytrackerentry.field_73132_a.field_70176_ah == p_85172_2_.field_76635_g && entitytrackerentry.field_73132_a.field_70164_aj == p_85172_2_.field_76647_h) {
            entitytrackerentry.func_73117_b(p_85172_1_);
         }
      }

   }
}
