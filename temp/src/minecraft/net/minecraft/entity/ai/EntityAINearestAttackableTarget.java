package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;

public class EntityAINearestAttackableTarget<T extends EntityLivingBase> extends EntityAITarget {
   protected final Class<T> field_75307_b;
   private final int field_75308_c;
   protected final EntityAINearestAttackableTarget.Sorter field_75306_g;
   protected Predicate<? super T> field_82643_g;
   protected EntityLivingBase field_75309_a;

   public EntityAINearestAttackableTarget(EntityCreature p_i45878_1_, Class<T> p_i45878_2_, boolean p_i45878_3_) {
      this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false);
   }

   public EntityAINearestAttackableTarget(EntityCreature p_i45879_1_, Class<T> p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_) {
      this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate<? super T>)null);
   }

   public EntityAINearestAttackableTarget(EntityCreature p_i45880_1_, Class<T> p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate<? super T> p_i45880_6_) {
      super(p_i45880_1_, p_i45880_4_, p_i45880_5_);
      this.field_75307_b = p_i45880_2_;
      this.field_75308_c = p_i45880_3_;
      this.field_75306_g = new EntityAINearestAttackableTarget.Sorter(p_i45880_1_);
      this.func_75248_a(1);
      this.field_82643_g = new Predicate<T>() {
         public boolean apply(T p_apply_1_) {
            if(p_i45880_6_ != null && !p_i45880_6_.apply(p_apply_1_)) {
               return false;
            } else {
               if(p_apply_1_ instanceof EntityPlayer) {
                  double d0 = EntityAINearestAttackableTarget.this.func_111175_f();
                  if(p_apply_1_.func_70093_af()) {
                     d0 *= 0.800000011920929D;
                  }

                  if(p_apply_1_.func_82150_aj()) {
                     float f = ((EntityPlayer)p_apply_1_).func_82243_bO();
                     if(f < 0.1F) {
                        f = 0.1F;
                     }

                     d0 *= (double)(0.7F * f);
                  }

                  if((double)p_apply_1_.func_70032_d(EntityAINearestAttackableTarget.this.field_75299_d) > d0) {
                     return false;
                  }
               }

               return EntityAINearestAttackableTarget.this.func_75296_a(p_apply_1_, false);
            }
         }
      };
   }

   public boolean func_75250_a() {
      if(this.field_75308_c > 0 && this.field_75299_d.func_70681_au().nextInt(this.field_75308_c) != 0) {
         return false;
      } else {
         double d0 = this.func_111175_f();
         List<T> list = this.field_75299_d.field_70170_p.<T>func_175647_a(this.field_75307_b, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), Predicates.<T>and(this.field_82643_g, EntitySelectors.field_180132_d));
         Collections.sort(list, this.field_75306_g);
         if(list.isEmpty()) {
            return false;
         } else {
            this.field_75309_a = (EntityLivingBase)list.get(0);
            return true;
         }
      }
   }

   public void func_75249_e() {
      this.field_75299_d.func_70624_b(this.field_75309_a);
      super.func_75249_e();
   }

   public static class Sorter implements Comparator<Entity> {
      private final Entity field_75459_b;

      public Sorter(Entity p_i1662_1_) {
         this.field_75459_b = p_i1662_1_;
      }

      public int compare(Entity p_compare_1_, Entity p_compare_2_) {
         double d0 = this.field_75459_b.func_70068_e(p_compare_1_);
         double d1 = this.field_75459_b.func_70068_e(p_compare_2_);
         return d0 < d1?-1:(d0 > d1?1:0);
      }
   }
}
