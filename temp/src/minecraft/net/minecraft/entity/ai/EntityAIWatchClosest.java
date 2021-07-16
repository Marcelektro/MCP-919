package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIWatchClosest extends EntityAIBase {
   protected EntityLiving field_75332_b;
   protected Entity field_75334_a;
   protected float field_75333_c;
   private int field_75330_d;
   private float field_75331_e;
   protected Class<? extends Entity> field_75329_f;

   public EntityAIWatchClosest(EntityLiving p_i1631_1_, Class<? extends Entity> p_i1631_2_, float p_i1631_3_) {
      this.field_75332_b = p_i1631_1_;
      this.field_75329_f = p_i1631_2_;
      this.field_75333_c = p_i1631_3_;
      this.field_75331_e = 0.02F;
      this.func_75248_a(2);
   }

   public EntityAIWatchClosest(EntityLiving p_i1632_1_, Class<? extends Entity> p_i1632_2_, float p_i1632_3_, float p_i1632_4_) {
      this.field_75332_b = p_i1632_1_;
      this.field_75329_f = p_i1632_2_;
      this.field_75333_c = p_i1632_3_;
      this.field_75331_e = p_i1632_4_;
      this.func_75248_a(2);
   }

   public boolean func_75250_a() {
      if(this.field_75332_b.func_70681_au().nextFloat() >= this.field_75331_e) {
         return false;
      } else {
         if(this.field_75332_b.func_70638_az() != null) {
            this.field_75334_a = this.field_75332_b.func_70638_az();
         }

         if(this.field_75329_f == EntityPlayer.class) {
            this.field_75334_a = this.field_75332_b.field_70170_p.func_72890_a(this.field_75332_b, (double)this.field_75333_c);
         } else {
            this.field_75334_a = this.field_75332_b.field_70170_p.func_72857_a(this.field_75329_f, this.field_75332_b.func_174813_aQ().func_72314_b((double)this.field_75333_c, 3.0D, (double)this.field_75333_c), this.field_75332_b);
         }

         return this.field_75334_a != null;
      }
   }

   public boolean func_75253_b() {
      return !this.field_75334_a.func_70089_S()?false:(this.field_75332_b.func_70068_e(this.field_75334_a) > (double)(this.field_75333_c * this.field_75333_c)?false:this.field_75330_d > 0);
   }

   public void func_75249_e() {
      this.field_75330_d = 40 + this.field_75332_b.func_70681_au().nextInt(40);
   }

   public void func_75251_c() {
      this.field_75334_a = null;
   }

   public void func_75246_d() {
      this.field_75332_b.func_70671_ap().func_75650_a(this.field_75334_a.field_70165_t, this.field_75334_a.field_70163_u + (double)this.field_75334_a.func_70047_e(), this.field_75334_a.field_70161_v, 10.0F, (float)this.field_75332_b.func_70646_bf());
      --this.field_75330_d;
   }
}
