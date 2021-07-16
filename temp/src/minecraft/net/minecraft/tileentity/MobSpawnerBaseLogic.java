package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StringUtils;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

public abstract class MobSpawnerBaseLogic {
   private int field_98286_b = 20;
   private String field_98288_a = "Pig";
   private final List<MobSpawnerBaseLogic.WeightedRandomMinecart> field_98285_e = Lists.<MobSpawnerBaseLogic.WeightedRandomMinecart>newArrayList();
   private MobSpawnerBaseLogic.WeightedRandomMinecart field_98282_f;
   private double field_98287_c;
   private double field_98284_d;
   private int field_98283_g = 200;
   private int field_98293_h = 800;
   private int field_98294_i = 4;
   private Entity field_98291_j;
   private int field_98292_k = 6;
   private int field_98289_l = 16;
   private int field_98290_m = 4;

   private String func_98276_e() {
      if(this.func_98269_i() == null) {
         if(this.field_98288_a != null && this.field_98288_a.equals("Minecart")) {
            this.field_98288_a = "MinecartRideable";
         }

         return this.field_98288_a;
      } else {
         return this.func_98269_i().field_98223_c;
      }
   }

   public void func_98272_a(String p_98272_1_) {
      this.field_98288_a = p_98272_1_;
   }

   private boolean func_98279_f() {
      BlockPos blockpos = this.func_177221_b();
      return this.func_98271_a().func_175636_b((double)blockpos.func_177958_n() + 0.5D, (double)blockpos.func_177956_o() + 0.5D, (double)blockpos.func_177952_p() + 0.5D, (double)this.field_98289_l);
   }

   public void func_98278_g() {
      if(this.func_98279_f()) {
         BlockPos blockpos = this.func_177221_b();
         if(this.func_98271_a().field_72995_K) {
            double d3 = (double)((float)blockpos.func_177958_n() + this.func_98271_a().field_73012_v.nextFloat());
            double d4 = (double)((float)blockpos.func_177956_o() + this.func_98271_a().field_73012_v.nextFloat());
            double d5 = (double)((float)blockpos.func_177952_p() + this.func_98271_a().field_73012_v.nextFloat());
            this.func_98271_a().func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d3, d4, d5, 0.0D, 0.0D, 0.0D, new int[0]);
            this.func_98271_a().func_175688_a(EnumParticleTypes.FLAME, d3, d4, d5, 0.0D, 0.0D, 0.0D, new int[0]);
            if(this.field_98286_b > 0) {
               --this.field_98286_b;
            }

            this.field_98284_d = this.field_98287_c;
            this.field_98287_c = (this.field_98287_c + (double)(1000.0F / ((float)this.field_98286_b + 200.0F))) % 360.0D;
         } else {
            if(this.field_98286_b == -1) {
               this.func_98273_j();
            }

            if(this.field_98286_b > 0) {
               --this.field_98286_b;
               return;
            }

            boolean flag = false;

            for(int i = 0; i < this.field_98294_i; ++i) {
               Entity entity = EntityList.func_75620_a(this.func_98276_e(), this.func_98271_a());
               if(entity == null) {
                  return;
               }

               int j = this.func_98271_a().func_72872_a(entity.getClass(), (new AxisAlignedBB((double)blockpos.func_177958_n(), (double)blockpos.func_177956_o(), (double)blockpos.func_177952_p(), (double)(blockpos.func_177958_n() + 1), (double)(blockpos.func_177956_o() + 1), (double)(blockpos.func_177952_p() + 1))).func_72314_b((double)this.field_98290_m, (double)this.field_98290_m, (double)this.field_98290_m)).size();
               if(j >= this.field_98292_k) {
                  this.func_98273_j();
                  return;
               }

               double d0 = (double)blockpos.func_177958_n() + (this.func_98271_a().field_73012_v.nextDouble() - this.func_98271_a().field_73012_v.nextDouble()) * (double)this.field_98290_m + 0.5D;
               double d1 = (double)(blockpos.func_177956_o() + this.func_98271_a().field_73012_v.nextInt(3) - 1);
               double d2 = (double)blockpos.func_177952_p() + (this.func_98271_a().field_73012_v.nextDouble() - this.func_98271_a().field_73012_v.nextDouble()) * (double)this.field_98290_m + 0.5D;
               EntityLiving entityliving = entity instanceof EntityLiving?(EntityLiving)entity:null;
               entity.func_70012_b(d0, d1, d2, this.func_98271_a().field_73012_v.nextFloat() * 360.0F, 0.0F);
               if(entityliving == null || entityliving.func_70601_bi() && entityliving.func_70058_J()) {
                  this.func_180613_a(entity, true);
                  this.func_98271_a().func_175718_b(2004, blockpos, 0);
                  if(entityliving != null) {
                     entityliving.func_70656_aK();
                  }

                  flag = true;
               }
            }

            if(flag) {
               this.func_98273_j();
            }
         }

      }
   }

   private Entity func_180613_a(Entity p_180613_1_, boolean p_180613_2_) {
      if(this.func_98269_i() != null) {
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         p_180613_1_.func_70039_c(nbttagcompound);

         for(String s : this.func_98269_i().field_98222_b.func_150296_c()) {
            NBTBase nbtbase = this.func_98269_i().field_98222_b.func_74781_a(s);
            nbttagcompound.func_74782_a(s, nbtbase.func_74737_b());
         }

         p_180613_1_.func_70020_e(nbttagcompound);
         if(p_180613_1_.field_70170_p != null && p_180613_2_) {
            p_180613_1_.field_70170_p.func_72838_d(p_180613_1_);
         }

         NBTTagCompound nbttagcompound2;
         for(Entity entity = p_180613_1_; nbttagcompound.func_150297_b("Riding", 10); nbttagcompound = nbttagcompound2) {
            nbttagcompound2 = nbttagcompound.func_74775_l("Riding");
            Entity entity1 = EntityList.func_75620_a(nbttagcompound2.func_74779_i("id"), p_180613_1_.field_70170_p);
            if(entity1 != null) {
               NBTTagCompound nbttagcompound1 = new NBTTagCompound();
               entity1.func_70039_c(nbttagcompound1);

               for(String s1 : nbttagcompound2.func_150296_c()) {
                  NBTBase nbtbase1 = nbttagcompound2.func_74781_a(s1);
                  nbttagcompound1.func_74782_a(s1, nbtbase1.func_74737_b());
               }

               entity1.func_70020_e(nbttagcompound1);
               entity1.func_70012_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
               if(p_180613_1_.field_70170_p != null && p_180613_2_) {
                  p_180613_1_.field_70170_p.func_72838_d(entity1);
               }

               entity.func_70078_a(entity1);
            }

            entity = entity1;
         }
      } else if(p_180613_1_ instanceof EntityLivingBase && p_180613_1_.field_70170_p != null && p_180613_2_) {
         if(p_180613_1_ instanceof EntityLiving) {
            ((EntityLiving)p_180613_1_).func_180482_a(p_180613_1_.field_70170_p.func_175649_E(new BlockPos(p_180613_1_)), (IEntityLivingData)null);
         }

         p_180613_1_.field_70170_p.func_72838_d(p_180613_1_);
      }

      return p_180613_1_;
   }

   private void func_98273_j() {
      if(this.field_98293_h <= this.field_98283_g) {
         this.field_98286_b = this.field_98283_g;
      } else {
         int i = this.field_98293_h - this.field_98283_g;
         this.field_98286_b = this.field_98283_g + this.func_98271_a().field_73012_v.nextInt(i);
      }

      if(this.field_98285_e.size() > 0) {
         this.func_98277_a((MobSpawnerBaseLogic.WeightedRandomMinecart)WeightedRandom.func_76271_a(this.func_98271_a().field_73012_v, this.field_98285_e));
      }

      this.func_98267_a(1);
   }

   public void func_98270_a(NBTTagCompound p_98270_1_) {
      this.field_98288_a = p_98270_1_.func_74779_i("EntityId");
      this.field_98286_b = p_98270_1_.func_74765_d("Delay");
      this.field_98285_e.clear();
      if(p_98270_1_.func_150297_b("SpawnPotentials", 9)) {
         NBTTagList nbttaglist = p_98270_1_.func_150295_c("SpawnPotentials", 10);

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            this.field_98285_e.add(new MobSpawnerBaseLogic.WeightedRandomMinecart(nbttaglist.func_150305_b(i)));
         }
      }

      if(p_98270_1_.func_150297_b("SpawnData", 10)) {
         this.func_98277_a(new MobSpawnerBaseLogic.WeightedRandomMinecart(p_98270_1_.func_74775_l("SpawnData"), this.field_98288_a));
      } else {
         this.func_98277_a((MobSpawnerBaseLogic.WeightedRandomMinecart)null);
      }

      if(p_98270_1_.func_150297_b("MinSpawnDelay", 99)) {
         this.field_98283_g = p_98270_1_.func_74765_d("MinSpawnDelay");
         this.field_98293_h = p_98270_1_.func_74765_d("MaxSpawnDelay");
         this.field_98294_i = p_98270_1_.func_74765_d("SpawnCount");
      }

      if(p_98270_1_.func_150297_b("MaxNearbyEntities", 99)) {
         this.field_98292_k = p_98270_1_.func_74765_d("MaxNearbyEntities");
         this.field_98289_l = p_98270_1_.func_74765_d("RequiredPlayerRange");
      }

      if(p_98270_1_.func_150297_b("SpawnRange", 99)) {
         this.field_98290_m = p_98270_1_.func_74765_d("SpawnRange");
      }

      if(this.func_98271_a() != null) {
         this.field_98291_j = null;
      }

   }

   public void func_98280_b(NBTTagCompound p_98280_1_) {
      String s = this.func_98276_e();
      if(!StringUtils.func_151246_b(s)) {
         p_98280_1_.func_74778_a("EntityId", s);
         p_98280_1_.func_74777_a("Delay", (short)this.field_98286_b);
         p_98280_1_.func_74777_a("MinSpawnDelay", (short)this.field_98283_g);
         p_98280_1_.func_74777_a("MaxSpawnDelay", (short)this.field_98293_h);
         p_98280_1_.func_74777_a("SpawnCount", (short)this.field_98294_i);
         p_98280_1_.func_74777_a("MaxNearbyEntities", (short)this.field_98292_k);
         p_98280_1_.func_74777_a("RequiredPlayerRange", (short)this.field_98289_l);
         p_98280_1_.func_74777_a("SpawnRange", (short)this.field_98290_m);
         if(this.func_98269_i() != null) {
            p_98280_1_.func_74782_a("SpawnData", this.func_98269_i().field_98222_b.func_74737_b());
         }

         if(this.func_98269_i() != null || this.field_98285_e.size() > 0) {
            NBTTagList nbttaglist = new NBTTagList();
            if(this.field_98285_e.size() > 0) {
               for(MobSpawnerBaseLogic.WeightedRandomMinecart mobspawnerbaselogic$weightedrandomminecart : this.field_98285_e) {
                  nbttaglist.func_74742_a(mobspawnerbaselogic$weightedrandomminecart.func_98220_a());
               }
            } else {
               nbttaglist.func_74742_a(this.func_98269_i().func_98220_a());
            }

            p_98280_1_.func_74782_a("SpawnPotentials", nbttaglist);
         }

      }
   }

   public Entity func_180612_a(World p_180612_1_) {
      if(this.field_98291_j == null) {
         Entity entity = EntityList.func_75620_a(this.func_98276_e(), p_180612_1_);
         if(entity != null) {
            entity = this.func_180613_a(entity, false);
            this.field_98291_j = entity;
         }
      }

      return this.field_98291_j;
   }

   public boolean func_98268_b(int p_98268_1_) {
      if(p_98268_1_ == 1 && this.func_98271_a().field_72995_K) {
         this.field_98286_b = this.field_98283_g;
         return true;
      } else {
         return false;
      }
   }

   private MobSpawnerBaseLogic.WeightedRandomMinecart func_98269_i() {
      return this.field_98282_f;
   }

   public void func_98277_a(MobSpawnerBaseLogic.WeightedRandomMinecart p_98277_1_) {
      this.field_98282_f = p_98277_1_;
   }

   public abstract void func_98267_a(int var1);

   public abstract World func_98271_a();

   public abstract BlockPos func_177221_b();

   public double func_177222_d() {
      return this.field_98287_c;
   }

   public double func_177223_e() {
      return this.field_98284_d;
   }

   public class WeightedRandomMinecart extends WeightedRandom.Item {
      private final NBTTagCompound field_98222_b;
      private final String field_98223_c;

      public WeightedRandomMinecart(NBTTagCompound p_i1945_2_) {
         this(p_i1945_2_.func_74775_l("Properties"), p_i1945_2_.func_74779_i("Type"), p_i1945_2_.func_74762_e("Weight"));
      }

      public WeightedRandomMinecart(NBTTagCompound p_i1946_2_, String p_i1946_3_) {
         this(p_i1946_2_, p_i1946_3_, 1);
      }

      private WeightedRandomMinecart(NBTTagCompound p_i45757_2_, String p_i45757_3_, int p_i45757_4_) {
         super(p_i45757_4_);
         if(p_i45757_3_.equals("Minecart")) {
            if(p_i45757_2_ != null) {
               p_i45757_3_ = EntityMinecart.EnumMinecartType.func_180038_a(p_i45757_2_.func_74762_e("Type")).func_180040_b();
            } else {
               p_i45757_3_ = "MinecartRideable";
            }
         }

         this.field_98222_b = p_i45757_2_;
         this.field_98223_c = p_i45757_3_;
      }

      public NBTTagCompound func_98220_a() {
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         nbttagcompound.func_74782_a("Properties", this.field_98222_b);
         nbttagcompound.func_74778_a("Type", this.field_98223_c);
         nbttagcompound.func_74768_a("Weight", this.field_76292_a);
         return nbttagcompound;
      }
   }
}
