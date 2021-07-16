package net.minecraft.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityMooshroom extends EntityCow {
   public EntityMooshroom(World p_i1687_1_) {
      super(p_i1687_1_);
      this.func_70105_a(0.9F, 1.3F);
      this.field_175506_bl = Blocks.field_150391_bh;
   }

   public boolean func_70085_c(EntityPlayer p_70085_1_) {
      ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
      if(itemstack != null && itemstack.func_77973_b() == Items.field_151054_z && this.func_70874_b() >= 0) {
         if(itemstack.field_77994_a == 1) {
            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, new ItemStack(Items.field_151009_A));
            return true;
         }

         if(p_70085_1_.field_71071_by.func_70441_a(new ItemStack(Items.field_151009_A)) && !p_70085_1_.field_71075_bZ.field_75098_d) {
            p_70085_1_.field_71071_by.func_70298_a(p_70085_1_.field_71071_by.field_70461_c, 1);
            return true;
         }
      }

      if(itemstack != null && itemstack.func_77973_b() == Items.field_151097_aZ && this.func_70874_b() >= 0) {
         this.func_70106_y();
         this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 2.0F), this.field_70161_v, 0.0D, 0.0D, 0.0D, new int[0]);
         if(!this.field_70170_p.field_72995_K) {
            EntityCow entitycow = new EntityCow(this.field_70170_p);
            entitycow.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
            entitycow.func_70606_j(this.func_110143_aJ());
            entitycow.field_70761_aq = this.field_70761_aq;
            if(this.func_145818_k_()) {
               entitycow.func_96094_a(this.func_95999_t());
            }

            this.field_70170_p.func_72838_d(entitycow);

            for(int i = 0; i < 5; ++i) {
               this.field_70170_p.func_72838_d(new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)this.field_70131_O, this.field_70161_v, new ItemStack(Blocks.field_150337_Q)));
            }

            itemstack.func_77972_a(1, p_70085_1_);
            this.func_85030_a("mob.sheep.shear", 1.0F, 1.0F);
         }

         return true;
      } else {
         return super.func_70085_c(p_70085_1_);
      }
   }

   public EntityMooshroom func_90011_a(EntityAgeable p_90011_1_) {
      return new EntityMooshroom(this.field_70170_p);
   }
}
