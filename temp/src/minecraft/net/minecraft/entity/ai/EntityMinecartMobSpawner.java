package net.minecraft.entity.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityMinecartMobSpawner extends EntityMinecart {
   private final MobSpawnerBaseLogic field_98040_a = new MobSpawnerBaseLogic() {
      public void func_98267_a(int p_98267_1_) {
         EntityMinecartMobSpawner.this.field_70170_p.func_72960_a(EntityMinecartMobSpawner.this, (byte)p_98267_1_);
      }

      public World func_98271_a() {
         return EntityMinecartMobSpawner.this.field_70170_p;
      }

      public BlockPos func_177221_b() {
         return new BlockPos(EntityMinecartMobSpawner.this);
      }
   };

   public EntityMinecartMobSpawner(World p_i1725_1_) {
      super(p_i1725_1_);
   }

   public EntityMinecartMobSpawner(World p_i1726_1_, double p_i1726_2_, double p_i1726_4_, double p_i1726_6_) {
      super(p_i1726_1_, p_i1726_2_, p_i1726_4_, p_i1726_6_);
   }

   public EntityMinecart.EnumMinecartType func_180456_s() {
      return EntityMinecart.EnumMinecartType.SPAWNER;
   }

   public IBlockState func_180457_u() {
      return Blocks.field_150474_ac.func_176223_P();
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_98040_a.func_98270_a(p_70037_1_);
   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      this.field_98040_a.func_98280_b(p_70014_1_);
   }

   public void func_70103_a(byte p_70103_1_) {
      this.field_98040_a.func_98268_b(p_70103_1_);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
      this.field_98040_a.func_98278_g();
   }

   public MobSpawnerBaseLogic func_98039_d() {
      return this.field_98040_a;
   }
}
