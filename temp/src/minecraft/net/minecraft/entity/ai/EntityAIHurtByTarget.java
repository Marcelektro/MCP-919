package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTarget extends EntityAITarget {
   private boolean field_75312_a;
   private int field_142052_b;
   private final Class[] field_179447_c;

   public EntityAIHurtByTarget(EntityCreature p_i45885_1_, boolean p_i45885_2_, Class... p_i45885_3_) {
      super(p_i45885_1_, false);
      this.field_75312_a = p_i45885_2_;
      this.field_179447_c = p_i45885_3_;
      this.func_75248_a(1);
   }

   public boolean func_75250_a() {
      int i = this.field_75299_d.func_142015_aE();
      return i != this.field_142052_b && this.func_75296_a(this.field_75299_d.func_70643_av(), false);
   }

   public void func_75249_e() {
      this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
      this.field_142052_b = this.field_75299_d.func_142015_aE();
      if(this.field_75312_a) {
         double d0 = this.func_111175_f();

         for(EntityCreature entitycreature : this.field_75299_d.field_70170_p.func_72872_a(this.field_75299_d.getClass(), (new AxisAlignedBB(this.field_75299_d.field_70165_t, this.field_75299_d.field_70163_u, this.field_75299_d.field_70161_v, this.field_75299_d.field_70165_t + 1.0D, this.field_75299_d.field_70163_u + 1.0D, this.field_75299_d.field_70161_v + 1.0D)).func_72314_b(d0, 10.0D, d0))) {
            if(this.field_75299_d != entitycreature && entitycreature.func_70638_az() == null && !entitycreature.func_142014_c(this.field_75299_d.func_70643_av())) {
               boolean flag = false;

               for(Class oclass : this.field_179447_c) {
                  if(entitycreature.getClass() == oclass) {
                     flag = true;
                     break;
                  }
               }

               if(!flag) {
                  this.func_179446_a(entitycreature, this.field_75299_d.func_70643_av());
               }
            }
         }
      }

      super.func_75249_e();
   }

   protected void func_179446_a(EntityCreature p_179446_1_, EntityLivingBase p_179446_2_) {
      p_179446_1_.func_70624_b(p_179446_2_);
   }
}
