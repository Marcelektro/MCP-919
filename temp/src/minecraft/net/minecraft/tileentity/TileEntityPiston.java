package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileEntityPiston extends TileEntity implements ITickable {
   private IBlockState field_174932_a;
   private EnumFacing field_174931_f;
   private boolean field_145875_k;
   private boolean field_145872_l;
   private float field_145873_m;
   private float field_145870_n;
   private List<Entity> field_174933_k = Lists.<Entity>newArrayList();

   public TileEntityPiston() {
   }

   public TileEntityPiston(IBlockState p_i45665_1_, EnumFacing p_i45665_2_, boolean p_i45665_3_, boolean p_i45665_4_) {
      this.field_174932_a = p_i45665_1_;
      this.field_174931_f = p_i45665_2_;
      this.field_145875_k = p_i45665_3_;
      this.field_145872_l = p_i45665_4_;
   }

   public IBlockState func_174927_b() {
      return this.field_174932_a;
   }

   public int func_145832_p() {
      return 0;
   }

   public boolean func_145868_b() {
      return this.field_145875_k;
   }

   public EnumFacing func_174930_e() {
      return this.field_174931_f;
   }

   public boolean func_145867_d() {
      return this.field_145872_l;
   }

   public float func_145860_a(float p_145860_1_) {
      if(p_145860_1_ > 1.0F) {
         p_145860_1_ = 1.0F;
      }

      return this.field_145870_n + (this.field_145873_m - this.field_145870_n) * p_145860_1_;
   }

   public float func_174929_b(float p_174929_1_) {
      return this.field_145875_k?(this.func_145860_a(p_174929_1_) - 1.0F) * (float)this.field_174931_f.func_82601_c():(1.0F - this.func_145860_a(p_174929_1_)) * (float)this.field_174931_f.func_82601_c();
   }

   public float func_174928_c(float p_174928_1_) {
      return this.field_145875_k?(this.func_145860_a(p_174928_1_) - 1.0F) * (float)this.field_174931_f.func_96559_d():(1.0F - this.func_145860_a(p_174928_1_)) * (float)this.field_174931_f.func_96559_d();
   }

   public float func_174926_d(float p_174926_1_) {
      return this.field_145875_k?(this.func_145860_a(p_174926_1_) - 1.0F) * (float)this.field_174931_f.func_82599_e():(1.0F - this.func_145860_a(p_174926_1_)) * (float)this.field_174931_f.func_82599_e();
   }

   private void func_145863_a(float p_145863_1_, float p_145863_2_) {
      if(this.field_145875_k) {
         p_145863_1_ = 1.0F - p_145863_1_;
      } else {
         --p_145863_1_;
      }

      AxisAlignedBB axisalignedbb = Blocks.field_180384_M.func_176424_a(this.field_145850_b, this.field_174879_c, this.field_174932_a, p_145863_1_, this.field_174931_f);
      if(axisalignedbb != null) {
         List<Entity> list = this.field_145850_b.func_72839_b((Entity)null, axisalignedbb);
         if(!list.isEmpty()) {
            this.field_174933_k.addAll(list);

            for(Entity entity : this.field_174933_k) {
               if(this.field_174932_a.func_177230_c() == Blocks.field_180399_cE && this.field_145875_k) {
                  switch(this.field_174931_f.func_176740_k()) {
                  case X:
                     entity.field_70159_w = (double)this.field_174931_f.func_82601_c();
                     break;
                  case Y:
                     entity.field_70181_x = (double)this.field_174931_f.func_96559_d();
                     break;
                  case Z:
                     entity.field_70179_y = (double)this.field_174931_f.func_82599_e();
                  }
               } else {
                  entity.func_70091_d((double)(p_145863_2_ * (float)this.field_174931_f.func_82601_c()), (double)(p_145863_2_ * (float)this.field_174931_f.func_96559_d()), (double)(p_145863_2_ * (float)this.field_174931_f.func_82599_e()));
               }
            }

            this.field_174933_k.clear();
         }
      }

   }

   public void func_145866_f() {
      if(this.field_145870_n < 1.0F && this.field_145850_b != null) {
         this.field_145870_n = this.field_145873_m = 1.0F;
         this.field_145850_b.func_175713_t(this.field_174879_c);
         this.func_145843_s();
         if(this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c() == Blocks.field_180384_M) {
            this.field_145850_b.func_180501_a(this.field_174879_c, this.field_174932_a, 3);
            this.field_145850_b.func_180496_d(this.field_174879_c, this.field_174932_a.func_177230_c());
         }
      }

   }

   public void func_73660_a() {
      this.field_145870_n = this.field_145873_m;
      if(this.field_145870_n >= 1.0F) {
         this.func_145863_a(1.0F, 0.25F);
         this.field_145850_b.func_175713_t(this.field_174879_c);
         this.func_145843_s();
         if(this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c() == Blocks.field_180384_M) {
            this.field_145850_b.func_180501_a(this.field_174879_c, this.field_174932_a, 3);
            this.field_145850_b.func_180496_d(this.field_174879_c, this.field_174932_a.func_177230_c());
         }

      } else {
         this.field_145873_m += 0.5F;
         if(this.field_145873_m >= 1.0F) {
            this.field_145873_m = 1.0F;
         }

         if(this.field_145875_k) {
            this.func_145863_a(this.field_145873_m, this.field_145873_m - this.field_145870_n + 0.0625F);
         }

      }
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_174932_a = Block.func_149729_e(p_145839_1_.func_74762_e("blockId")).func_176203_a(p_145839_1_.func_74762_e("blockData"));
      this.field_174931_f = EnumFacing.func_82600_a(p_145839_1_.func_74762_e("facing"));
      this.field_145870_n = this.field_145873_m = p_145839_1_.func_74760_g("progress");
      this.field_145875_k = p_145839_1_.func_74767_n("extending");
   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74768_a("blockId", Block.func_149682_b(this.field_174932_a.func_177230_c()));
      p_145841_1_.func_74768_a("blockData", this.field_174932_a.func_177230_c().func_176201_c(this.field_174932_a));
      p_145841_1_.func_74768_a("facing", this.field_174931_f.func_176745_a());
      p_145841_1_.func_74776_a("progress", this.field_145870_n);
      p_145841_1_.func_74757_a("extending", this.field_145875_k);
   }
}
