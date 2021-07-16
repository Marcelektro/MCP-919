package net.minecraft.client.particle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRainFX extends EntityFX {
   protected EntityRainFX(World p_i1235_1_, double p_i1235_2_, double p_i1235_4_, double p_i1235_6_) {
      super(p_i1235_1_, p_i1235_2_, p_i1235_4_, p_i1235_6_, 0.0D, 0.0D, 0.0D);
      this.field_70159_w *= 0.30000001192092896D;
      this.field_70181_x = Math.random() * 0.20000000298023224D + 0.10000000149011612D;
      this.field_70179_y *= 0.30000001192092896D;
      this.field_70552_h = 1.0F;
      this.field_70553_i = 1.0F;
      this.field_70551_j = 1.0F;
      this.func_70536_a(19 + this.field_70146_Z.nextInt(4));
      this.func_70105_a(0.01F, 0.01F);
      this.field_70545_g = 0.06F;
      this.field_70547_e = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
   }

   public void func_70071_h_() {
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      this.field_70181_x -= (double)this.field_70545_g;
      this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.9800000190734863D;
      this.field_70181_x *= 0.9800000190734863D;
      this.field_70179_y *= 0.9800000190734863D;
      if(this.field_70547_e-- <= 0) {
         this.func_70106_y();
      }

      if(this.field_70122_E) {
         if(Math.random() < 0.5D) {
            this.func_70106_y();
         }

         this.field_70159_w *= 0.699999988079071D;
         this.field_70179_y *= 0.699999988079071D;
      }

      BlockPos blockpos = new BlockPos(this);
      IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
      Block block = iblockstate.func_177230_c();
      block.func_180654_a(this.field_70170_p, blockpos);
      Material material = iblockstate.func_177230_c().func_149688_o();
      if(material.func_76224_d() || material.func_76220_a()) {
         double d0 = 0.0D;
         if(iblockstate.func_177230_c() instanceof BlockLiquid) {
            d0 = (double)(1.0F - BlockLiquid.func_149801_b(((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue()));
         } else {
            d0 = block.func_149669_A();
         }

         double d1 = (double)MathHelper.func_76128_c(this.field_70163_u) + d0;
         if(this.field_70163_u < d1) {
            this.func_70106_y();
         }
      }

   }

   public static class Factory implements IParticleFactory {
      public EntityFX func_178902_a(int p_178902_1_, World p_178902_2_, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
         return new EntityRainFX(p_178902_2_, p_178902_3_, p_178902_5_, p_178902_7_);
      }
   }
}
