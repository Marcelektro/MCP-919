package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityCow extends EntityAnimal {
   public EntityCow(World p_i1683_1_) {
      super(p_i1683_1_);
      this.func_70105_a(0.9F, 1.3F);
      ((PathNavigateGround)this.func_70661_as()).func_179690_a(true);
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 2.0D));
      this.field_70714_bg.func_75776_a(2, new EntityAIMate(this, 1.0D));
      this.field_70714_bg.func_75776_a(3, new EntityAITempt(this, 1.25D, Items.field_151015_O, false));
      this.field_70714_bg.func_75776_a(4, new EntityAIFollowParent(this, 1.25D));
      this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.field_70714_bg.func_75776_a(7, new EntityAILookIdle(this));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
   }

   protected String func_70639_aQ() {
      return "mob.cow.say";
   }

   protected String func_70621_aR() {
      return "mob.cow.hurt";
   }

   protected String func_70673_aS() {
      return "mob.cow.hurt";
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_85030_a("mob.cow.step", 0.15F, 1.0F);
   }

   protected float func_70599_aP() {
      return 0.4F;
   }

   protected Item func_146068_u() {
      return Items.field_151116_aA;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      int i = this.field_70146_Z.nextInt(3) + this.field_70146_Z.nextInt(1 + p_70628_2_);

      for(int j = 0; j < i; ++j) {
         this.func_145779_a(Items.field_151116_aA, 1);
      }

      i = this.field_70146_Z.nextInt(3) + 1 + this.field_70146_Z.nextInt(1 + p_70628_2_);

      for(int k = 0; k < i; ++k) {
         if(this.func_70027_ad()) {
            this.func_145779_a(Items.field_151083_be, 1);
         } else {
            this.func_145779_a(Items.field_151082_bd, 1);
         }
      }

   }

   public boolean func_70085_c(EntityPlayer p_70085_1_) {
      ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
      if(itemstack != null && itemstack.func_77973_b() == Items.field_151133_ar && !p_70085_1_.field_71075_bZ.field_75098_d && !this.func_70631_g_()) {
         if(itemstack.field_77994_a-- == 1) {
            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, new ItemStack(Items.field_151117_aB));
         } else if(!p_70085_1_.field_71071_by.func_70441_a(new ItemStack(Items.field_151117_aB))) {
            p_70085_1_.func_71019_a(new ItemStack(Items.field_151117_aB, 1, 0), false);
         }

         return true;
      } else {
         return super.func_70085_c(p_70085_1_);
      }
   }

   public EntityCow func_90011_a(EntityAgeable p_90011_1_) {
      return new EntityCow(this.field_70170_p);
   }

   public float func_70047_e() {
      return this.field_70131_O;
   }
}
