package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAITasks {
   private static final Logger field_151506_a = LogManager.getLogger();
   private List<EntityAITasks.EntityAITaskEntry> field_75782_a = Lists.<EntityAITasks.EntityAITaskEntry>newArrayList();
   private List<EntityAITasks.EntityAITaskEntry> field_75780_b = Lists.<EntityAITasks.EntityAITaskEntry>newArrayList();
   private final Profiler field_75781_c;
   private int field_75778_d;
   private int field_75779_e = 3;

   public EntityAITasks(Profiler p_i1628_1_) {
      this.field_75781_c = p_i1628_1_;
   }

   public void func_75776_a(int p_75776_1_, EntityAIBase p_75776_2_) {
      this.field_75782_a.add(new EntityAITasks.EntityAITaskEntry(p_75776_1_, p_75776_2_));
   }

   public void func_85156_a(EntityAIBase p_85156_1_) {
      Iterator<EntityAITasks.EntityAITaskEntry> iterator = this.field_75782_a.iterator();

      while(iterator.hasNext()) {
         EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
         EntityAIBase entityaibase = entityaitasks$entityaitaskentry.field_75733_a;
         if(entityaibase == p_85156_1_) {
            if(this.field_75780_b.contains(entityaitasks$entityaitaskentry)) {
               entityaibase.func_75251_c();
               this.field_75780_b.remove(entityaitasks$entityaitaskentry);
            }

            iterator.remove();
         }
      }

   }

   public void func_75774_a() {
      this.field_75781_c.func_76320_a("goalSetup");
      if(this.field_75778_d++ % this.field_75779_e == 0) {
         Iterator iterator = this.field_75782_a.iterator();

         label38:
         while(true) {
            EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry;
            while(true) {
               if(!iterator.hasNext()) {
                  break label38;
               }

               entityaitasks$entityaitaskentry = (EntityAITasks.EntityAITaskEntry)iterator.next();
               boolean flag = this.field_75780_b.contains(entityaitasks$entityaitaskentry);
               if(!flag) {
                  break;
               }

               if(!this.func_75775_b(entityaitasks$entityaitaskentry) || !this.func_75773_a(entityaitasks$entityaitaskentry)) {
                  entityaitasks$entityaitaskentry.field_75733_a.func_75251_c();
                  this.field_75780_b.remove(entityaitasks$entityaitaskentry);
                  break;
               }
            }

            if(this.func_75775_b(entityaitasks$entityaitaskentry) && entityaitasks$entityaitaskentry.field_75733_a.func_75250_a()) {
               entityaitasks$entityaitaskentry.field_75733_a.func_75249_e();
               this.field_75780_b.add(entityaitasks$entityaitaskentry);
            }
         }
      } else {
         Iterator<EntityAITasks.EntityAITaskEntry> iterator1 = this.field_75780_b.iterator();

         while(iterator1.hasNext()) {
            EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry1 = (EntityAITasks.EntityAITaskEntry)iterator1.next();
            if(!this.func_75773_a(entityaitasks$entityaitaskentry1)) {
               entityaitasks$entityaitaskentry1.field_75733_a.func_75251_c();
               iterator1.remove();
            }
         }
      }

      this.field_75781_c.func_76319_b();
      this.field_75781_c.func_76320_a("goalTick");

      for(EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry2 : this.field_75780_b) {
         entityaitasks$entityaitaskentry2.field_75733_a.func_75246_d();
      }

      this.field_75781_c.func_76319_b();
   }

   private boolean func_75773_a(EntityAITasks.EntityAITaskEntry p_75773_1_) {
      boolean flag = p_75773_1_.field_75733_a.func_75253_b();
      return flag;
   }

   private boolean func_75775_b(EntityAITasks.EntityAITaskEntry p_75775_1_) {
      for(EntityAITasks.EntityAITaskEntry entityaitasks$entityaitaskentry : this.field_75782_a) {
         if(entityaitasks$entityaitaskentry != p_75775_1_) {
            if(p_75775_1_.field_75731_b >= entityaitasks$entityaitaskentry.field_75731_b) {
               if(!this.func_75777_a(p_75775_1_, entityaitasks$entityaitaskentry) && this.field_75780_b.contains(entityaitasks$entityaitaskentry)) {
                  return false;
               }
            } else if(!entityaitasks$entityaitaskentry.field_75733_a.func_75252_g() && this.field_75780_b.contains(entityaitasks$entityaitaskentry)) {
               return false;
            }
         }
      }

      return true;
   }

   private boolean func_75777_a(EntityAITasks.EntityAITaskEntry p_75777_1_, EntityAITasks.EntityAITaskEntry p_75777_2_) {
      return (p_75777_1_.field_75733_a.func_75247_h() & p_75777_2_.field_75733_a.func_75247_h()) == 0;
   }

   class EntityAITaskEntry {
      public EntityAIBase field_75733_a;
      public int field_75731_b;

      public EntityAITaskEntry(int p_i1627_2_, EntityAIBase p_i1627_3_) {
         this.field_75731_b = p_i1627_2_;
         this.field_75733_a = p_i1627_3_;
      }
   }
}
