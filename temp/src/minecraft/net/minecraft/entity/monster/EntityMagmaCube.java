package net.minecraft.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityMagmaCube extends EntitySlime {
   public EntityMagmaCube(World p_i1737_1_) {
      super(p_i1737_1_);
      this.field_70178_ae = true;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
   }

   public boolean func_70601_bi() {
      return this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL;
   }

   public boolean func_70058_J() {
      return this.field_70170_p.func_72917_a(this.func_174813_aQ(), this) && this.field_70170_p.func_72945_a(this, this.func_174813_aQ()).isEmpty() && !this.field_70170_p.func_72953_d(this.func_174813_aQ());
   }

   public int func_70658_aO() {
      return this.func_70809_q() * 3;
   }

   public int func_70070_b(float p_70070_1_) {
      return 15728880;
   }

   public float func_70013_c(float p_70013_1_) {
      return 1.0F;
   }

   protected EnumParticleTypes func_180487_n() {
      return EnumParticleTypes.FLAME;
   }

   protected EntitySlime func_70802_j() {
      return new EntityMagmaCube(this.field_70170_p);
   }

   protected Item func_146068_u() {
      return Items.field_151064_bs;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      Item item = this.func_146068_u();
      if(item != null && this.func_70809_q() > 1) {
         int i = this.field_70146_Z.nextInt(4) - 2;
         if(p_70628_2_ > 0) {
            i += this.field_70146_Z.nextInt(p_70628_2_ + 1);
         }

         for(int j = 0; j < i; ++j) {
            this.func_145779_a(item, 1);
         }
      }

   }

   public boolean func_70027_ad() {
      return false;
   }

   protected int func_70806_k() {
      return super.func_70806_k() * 4;
   }

   protected void func_70808_l() {
      this.field_70813_a *= 0.9F;
   }

   protected void func_70664_aZ() {
      this.field_70181_x = (double)(0.42F + (float)this.func_70809_q() * 0.1F);
      this.field_70160_al = true;
   }

   protected void func_180466_bG() {
      this.field_70181_x = (double)(0.22F + (float)this.func_70809_q() * 0.05F);
      this.field_70160_al = true;
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
   }

   protected boolean func_70800_m() {
      return true;
   }

   protected int func_70805_n() {
      return super.func_70805_n() + 2;
   }

   protected String func_70803_o() {
      return this.func_70809_q() > 1?"mob.magmacube.big":"mob.magmacube.small";
   }

   protected boolean func_70804_p() {
      return true;
   }
}
