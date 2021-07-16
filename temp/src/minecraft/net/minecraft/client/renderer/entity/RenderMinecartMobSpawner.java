package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.init.Blocks;

public class RenderMinecartMobSpawner extends RenderMinecart<EntityMinecartMobSpawner> {
   public RenderMinecartMobSpawner(RenderManager p_i46154_1_) {
      super(p_i46154_1_);
   }

   protected void func_180560_a(EntityMinecartMobSpawner p_180560_1_, float p_180560_2_, IBlockState p_180560_3_) {
      super.func_180560_a(p_180560_1_, p_180560_2_, p_180560_3_);
      if(p_180560_3_.func_177230_c() == Blocks.field_150474_ac) {
         TileEntityMobSpawnerRenderer.func_147517_a(p_180560_1_.func_98039_d(), p_180560_1_.field_70165_t, p_180560_1_.field_70163_u, p_180560_1_.field_70161_v, p_180560_2_);
      }

   }
}
