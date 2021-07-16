package net.minecraft.client.particle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Barrier;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityDropParticleFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFirework;
import net.minecraft.client.particle.EntityFishWakeFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.particle.EntityParticleEmitter;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySnowShovelFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.MobAppearance;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EffectRenderer {
   private static final ResourceLocation field_110737_b = new ResourceLocation("textures/particle/particles.png");
   protected World field_78878_a;
   private List<EntityFX>[][] field_78876_b = new List[4][];
   private List<EntityParticleEmitter> field_178933_d = Lists.<EntityParticleEmitter>newArrayList();
   private TextureManager field_78877_c;
   private Random field_78875_d = new Random();
   private Map<Integer, IParticleFactory> field_178932_g = Maps.<Integer, IParticleFactory>newHashMap();

   public EffectRenderer(World p_i1220_1_, TextureManager p_i1220_2_) {
      this.field_78878_a = p_i1220_1_;
      this.field_78877_c = p_i1220_2_;

      for(int i = 0; i < 4; ++i) {
         this.field_78876_b[i] = new List[2];

         for(int j = 0; j < 2; ++j) {
            this.field_78876_b[i][j] = Lists.newArrayList();
         }
      }

      this.func_178930_c();
   }

   private void func_178930_c() {
      this.func_178929_a(EnumParticleTypes.EXPLOSION_NORMAL.func_179348_c(), new EntityExplodeFX.Factory());
      this.func_178929_a(EnumParticleTypes.WATER_BUBBLE.func_179348_c(), new EntityBubbleFX.Factory());
      this.func_178929_a(EnumParticleTypes.WATER_SPLASH.func_179348_c(), new EntitySplashFX.Factory());
      this.func_178929_a(EnumParticleTypes.WATER_WAKE.func_179348_c(), new EntityFishWakeFX.Factory());
      this.func_178929_a(EnumParticleTypes.WATER_DROP.func_179348_c(), new EntityRainFX.Factory());
      this.func_178929_a(EnumParticleTypes.SUSPENDED.func_179348_c(), new EntitySuspendFX.Factory());
      this.func_178929_a(EnumParticleTypes.SUSPENDED_DEPTH.func_179348_c(), new EntityAuraFX.Factory());
      this.func_178929_a(EnumParticleTypes.CRIT.func_179348_c(), new EntityCrit2FX.Factory());
      this.func_178929_a(EnumParticleTypes.CRIT_MAGIC.func_179348_c(), new EntityCrit2FX.MagicFactory());
      this.func_178929_a(EnumParticleTypes.SMOKE_NORMAL.func_179348_c(), new EntitySmokeFX.Factory());
      this.func_178929_a(EnumParticleTypes.SMOKE_LARGE.func_179348_c(), new EntityCritFX.Factory());
      this.func_178929_a(EnumParticleTypes.SPELL.func_179348_c(), new EntitySpellParticleFX.Factory());
      this.func_178929_a(EnumParticleTypes.SPELL_INSTANT.func_179348_c(), new EntitySpellParticleFX.InstantFactory());
      this.func_178929_a(EnumParticleTypes.SPELL_MOB.func_179348_c(), new EntitySpellParticleFX.MobFactory());
      this.func_178929_a(EnumParticleTypes.SPELL_MOB_AMBIENT.func_179348_c(), new EntitySpellParticleFX.AmbientMobFactory());
      this.func_178929_a(EnumParticleTypes.SPELL_WITCH.func_179348_c(), new EntitySpellParticleFX.WitchFactory());
      this.func_178929_a(EnumParticleTypes.DRIP_WATER.func_179348_c(), new EntityDropParticleFX.WaterFactory());
      this.func_178929_a(EnumParticleTypes.DRIP_LAVA.func_179348_c(), new EntityDropParticleFX.LavaFactory());
      this.func_178929_a(EnumParticleTypes.VILLAGER_ANGRY.func_179348_c(), new EntityHeartFX.AngryVillagerFactory());
      this.func_178929_a(EnumParticleTypes.VILLAGER_HAPPY.func_179348_c(), new EntityAuraFX.HappyVillagerFactory());
      this.func_178929_a(EnumParticleTypes.TOWN_AURA.func_179348_c(), new EntityAuraFX.Factory());
      this.func_178929_a(EnumParticleTypes.NOTE.func_179348_c(), new EntityNoteFX.Factory());
      this.func_178929_a(EnumParticleTypes.PORTAL.func_179348_c(), new EntityPortalFX.Factory());
      this.func_178929_a(EnumParticleTypes.ENCHANTMENT_TABLE.func_179348_c(), new EntityEnchantmentTableParticleFX.EnchantmentTable());
      this.func_178929_a(EnumParticleTypes.FLAME.func_179348_c(), new EntityFlameFX.Factory());
      this.func_178929_a(EnumParticleTypes.LAVA.func_179348_c(), new EntityLavaFX.Factory());
      this.func_178929_a(EnumParticleTypes.FOOTSTEP.func_179348_c(), new EntityFootStepFX.Factory());
      this.func_178929_a(EnumParticleTypes.CLOUD.func_179348_c(), new EntityCloudFX.Factory());
      this.func_178929_a(EnumParticleTypes.REDSTONE.func_179348_c(), new EntityReddustFX.Factory());
      this.func_178929_a(EnumParticleTypes.SNOWBALL.func_179348_c(), new EntityBreakingFX.SnowballFactory());
      this.func_178929_a(EnumParticleTypes.SNOW_SHOVEL.func_179348_c(), new EntitySnowShovelFX.Factory());
      this.func_178929_a(EnumParticleTypes.SLIME.func_179348_c(), new EntityBreakingFX.SlimeFactory());
      this.func_178929_a(EnumParticleTypes.HEART.func_179348_c(), new EntityHeartFX.Factory());
      this.func_178929_a(EnumParticleTypes.BARRIER.func_179348_c(), new Barrier.Factory());
      this.func_178929_a(EnumParticleTypes.ITEM_CRACK.func_179348_c(), new EntityBreakingFX.Factory());
      this.func_178929_a(EnumParticleTypes.BLOCK_CRACK.func_179348_c(), new EntityDiggingFX.Factory());
      this.func_178929_a(EnumParticleTypes.BLOCK_DUST.func_179348_c(), new EntityBlockDustFX.Factory());
      this.func_178929_a(EnumParticleTypes.EXPLOSION_HUGE.func_179348_c(), new EntityHugeExplodeFX.Factory());
      this.func_178929_a(EnumParticleTypes.EXPLOSION_LARGE.func_179348_c(), new EntityLargeExplodeFX.Factory());
      this.func_178929_a(EnumParticleTypes.FIREWORKS_SPARK.func_179348_c(), new EntityFirework.Factory());
      this.func_178929_a(EnumParticleTypes.MOB_APPEARANCE.func_179348_c(), new MobAppearance.Factory());
   }

   public void func_178929_a(int p_178929_1_, IParticleFactory p_178929_2_) {
      this.field_178932_g.put(Integer.valueOf(p_178929_1_), p_178929_2_);
   }

   public void func_178926_a(Entity p_178926_1_, EnumParticleTypes p_178926_2_) {
      this.field_178933_d.add(new EntityParticleEmitter(this.field_78878_a, p_178926_1_, p_178926_2_));
   }

   public EntityFX func_178927_a(int p_178927_1_, double p_178927_2_, double p_178927_4_, double p_178927_6_, double p_178927_8_, double p_178927_10_, double p_178927_12_, int... p_178927_14_) {
      IParticleFactory iparticlefactory = (IParticleFactory)this.field_178932_g.get(Integer.valueOf(p_178927_1_));
      if(iparticlefactory != null) {
         EntityFX entityfx = iparticlefactory.func_178902_a(p_178927_1_, this.field_78878_a, p_178927_2_, p_178927_4_, p_178927_6_, p_178927_8_, p_178927_10_, p_178927_12_, p_178927_14_);
         if(entityfx != null) {
            this.func_78873_a(entityfx);
            return entityfx;
         }
      }

      return null;
   }

   public void func_78873_a(EntityFX p_78873_1_) {
      int i = p_78873_1_.func_70537_b();
      int j = p_78873_1_.func_174838_j() != 1.0F?0:1;
      if(this.field_78876_b[i][j].size() >= 4000) {
         this.field_78876_b[i][j].remove(0);
      }

      this.field_78876_b[i][j].add(p_78873_1_);
   }

   public void func_78868_a() {
      for(int i = 0; i < 4; ++i) {
         this.func_178922_a(i);
      }

      List<EntityParticleEmitter> list = Lists.<EntityParticleEmitter>newArrayList();

      for(EntityParticleEmitter entityparticleemitter : this.field_178933_d) {
         entityparticleemitter.func_70071_h_();
         if(entityparticleemitter.field_70128_L) {
            list.add(entityparticleemitter);
         }
      }

      this.field_178933_d.removeAll(list);
   }

   private void func_178922_a(int p_178922_1_) {
      for(int i = 0; i < 2; ++i) {
         this.func_178925_a(this.field_78876_b[p_178922_1_][i]);
      }

   }

   private void func_178925_a(List<EntityFX> p_178925_1_) {
      List<EntityFX> list = Lists.<EntityFX>newArrayList();

      for(int i = 0; i < p_178925_1_.size(); ++i) {
         EntityFX entityfx = (EntityFX)p_178925_1_.get(i);
         this.func_178923_d(entityfx);
         if(entityfx.field_70128_L) {
            list.add(entityfx);
         }
      }

      p_178925_1_.removeAll(list);
   }

   private void func_178923_d(final EntityFX p_178923_1_) {
      try {
         p_178923_1_.func_70071_h_();
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Ticking Particle");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being ticked");
         final int i = p_178923_1_.func_70537_b();
         crashreportcategory.func_71500_a("Particle", new Callable<String>() {
            public String call() throws Exception {
               return p_178923_1_.toString();
            }
         });
         crashreportcategory.func_71500_a("Particle Type", new Callable<String>() {
            public String call() throws Exception {
               return i == 0?"MISC_TEXTURE":(i == 1?"TERRAIN_TEXTURE":(i == 3?"ENTITY_PARTICLE_TEXTURE":"Unknown - " + i));
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   public void func_78874_a(Entity p_78874_1_, float p_78874_2_) {
      float f = ActiveRenderInfo.func_178808_b();
      float f1 = ActiveRenderInfo.func_178803_d();
      float f2 = ActiveRenderInfo.func_178805_e();
      float f3 = ActiveRenderInfo.func_178807_f();
      float f4 = ActiveRenderInfo.func_178809_c();
      EntityFX.field_70556_an = p_78874_1_.field_70142_S + (p_78874_1_.field_70165_t - p_78874_1_.field_70142_S) * (double)p_78874_2_;
      EntityFX.field_70554_ao = p_78874_1_.field_70137_T + (p_78874_1_.field_70163_u - p_78874_1_.field_70137_T) * (double)p_78874_2_;
      EntityFX.field_70555_ap = p_78874_1_.field_70136_U + (p_78874_1_.field_70161_v - p_78874_1_.field_70136_U) * (double)p_78874_2_;
      GlStateManager.func_179147_l();
      GlStateManager.func_179112_b(770, 771);
      GlStateManager.func_179092_a(516, 0.003921569F);

      for(final int i = 0; i < 3; ++i) {
         for(int j = 0; j < 2; ++j) {
            if(!this.field_78876_b[i][j].isEmpty()) {
               switch(j) {
               case 0:
                  GlStateManager.func_179132_a(false);
                  break;
               case 1:
                  GlStateManager.func_179132_a(true);
               }

               switch(i) {
               case 0:
               default:
                  this.field_78877_c.func_110577_a(field_110737_b);
                  break;
               case 1:
                  this.field_78877_c.func_110577_a(TextureMap.field_110575_b);
               }

               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               Tessellator tessellator = Tessellator.func_178181_a();
               WorldRenderer worldrenderer = tessellator.func_178180_c();
               worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181704_d);

               for(int k = 0; k < this.field_78876_b[i][j].size(); ++k) {
                  final EntityFX entityfx = (EntityFX)this.field_78876_b[i][j].get(k);

                  try {
                     entityfx.func_180434_a(worldrenderer, p_78874_1_, p_78874_2_, f, f4, f1, f2, f3);
                  } catch (Throwable throwable) {
                     CrashReport crashreport = CrashReport.func_85055_a(throwable, "Rendering Particle");
                     CrashReportCategory crashreportcategory = crashreport.func_85058_a("Particle being rendered");
                     crashreportcategory.func_71500_a("Particle", new Callable<String>() {
                        public String call() throws Exception {
                           return entityfx.toString();
                        }
                     });
                     crashreportcategory.func_71500_a("Particle Type", new Callable<String>() {
                        public String call() throws Exception {
                           return i == 0?"MISC_TEXTURE":(i == 1?"TERRAIN_TEXTURE":(i == 3?"ENTITY_PARTICLE_TEXTURE":"Unknown - " + i));
                        }
                     });
                     throw new ReportedException(crashreport);
                  }
               }

               tessellator.func_78381_a();
            }
         }
      }

      GlStateManager.func_179132_a(true);
      GlStateManager.func_179084_k();
      GlStateManager.func_179092_a(516, 0.1F);
   }

   public void func_78872_b(Entity p_78872_1_, float p_78872_2_) {
      float f = 0.017453292F;
      float f1 = MathHelper.func_76134_b(p_78872_1_.field_70177_z * 0.017453292F);
      float f2 = MathHelper.func_76126_a(p_78872_1_.field_70177_z * 0.017453292F);
      float f3 = -f2 * MathHelper.func_76126_a(p_78872_1_.field_70125_A * 0.017453292F);
      float f4 = f1 * MathHelper.func_76126_a(p_78872_1_.field_70125_A * 0.017453292F);
      float f5 = MathHelper.func_76134_b(p_78872_1_.field_70125_A * 0.017453292F);

      for(int i = 0; i < 2; ++i) {
         List<EntityFX> list = this.field_78876_b[3][i];
         if(!list.isEmpty()) {
            Tessellator tessellator = Tessellator.func_178181_a();
            WorldRenderer worldrenderer = tessellator.func_178180_c();

            for(int j = 0; j < list.size(); ++j) {
               EntityFX entityfx = (EntityFX)list.get(j);
               entityfx.func_180434_a(worldrenderer, p_78872_1_, p_78872_2_, f1, f5, f2, f3, f4);
            }
         }
      }

   }

   public void func_78870_a(World p_78870_1_) {
      this.field_78878_a = p_78870_1_;

      for(int i = 0; i < 4; ++i) {
         for(int j = 0; j < 2; ++j) {
            this.field_78876_b[i][j].clear();
         }
      }

      this.field_178933_d.clear();
   }

   public void func_180533_a(BlockPos p_180533_1_, IBlockState p_180533_2_) {
      if(p_180533_2_.func_177230_c().func_149688_o() != Material.field_151579_a) {
         p_180533_2_ = p_180533_2_.func_177230_c().func_176221_a(p_180533_2_, this.field_78878_a, p_180533_1_);
         int i = 4;

         for(int j = 0; j < i; ++j) {
            for(int k = 0; k < i; ++k) {
               for(int l = 0; l < i; ++l) {
                  double d0 = (double)p_180533_1_.func_177958_n() + ((double)j + 0.5D) / (double)i;
                  double d1 = (double)p_180533_1_.func_177956_o() + ((double)k + 0.5D) / (double)i;
                  double d2 = (double)p_180533_1_.func_177952_p() + ((double)l + 0.5D) / (double)i;
                  this.func_78873_a((new EntityDiggingFX(this.field_78878_a, d0, d1, d2, d0 - (double)p_180533_1_.func_177958_n() - 0.5D, d1 - (double)p_180533_1_.func_177956_o() - 0.5D, d2 - (double)p_180533_1_.func_177952_p() - 0.5D, p_180533_2_)).func_174846_a(p_180533_1_));
               }
            }
         }

      }
   }

   public void func_180532_a(BlockPos p_180532_1_, EnumFacing p_180532_2_) {
      IBlockState iblockstate = this.field_78878_a.func_180495_p(p_180532_1_);
      Block block = iblockstate.func_177230_c();
      if(block.func_149645_b() != -1) {
         int i = p_180532_1_.func_177958_n();
         int j = p_180532_1_.func_177956_o();
         int k = p_180532_1_.func_177952_p();
         float f = 0.1F;
         double d0 = (double)i + this.field_78875_d.nextDouble() * (block.func_149753_y() - block.func_149704_x() - (double)(f * 2.0F)) + (double)f + block.func_149704_x();
         double d1 = (double)j + this.field_78875_d.nextDouble() * (block.func_149669_A() - block.func_149665_z() - (double)(f * 2.0F)) + (double)f + block.func_149665_z();
         double d2 = (double)k + this.field_78875_d.nextDouble() * (block.func_149693_C() - block.func_149706_B() - (double)(f * 2.0F)) + (double)f + block.func_149706_B();
         if(p_180532_2_ == EnumFacing.DOWN) {
            d1 = (double)j + block.func_149665_z() - (double)f;
         }

         if(p_180532_2_ == EnumFacing.UP) {
            d1 = (double)j + block.func_149669_A() + (double)f;
         }

         if(p_180532_2_ == EnumFacing.NORTH) {
            d2 = (double)k + block.func_149706_B() - (double)f;
         }

         if(p_180532_2_ == EnumFacing.SOUTH) {
            d2 = (double)k + block.func_149693_C() + (double)f;
         }

         if(p_180532_2_ == EnumFacing.WEST) {
            d0 = (double)i + block.func_149704_x() - (double)f;
         }

         if(p_180532_2_ == EnumFacing.EAST) {
            d0 = (double)i + block.func_149753_y() + (double)f;
         }

         this.func_78873_a((new EntityDiggingFX(this.field_78878_a, d0, d1, d2, 0.0D, 0.0D, 0.0D, iblockstate)).func_174846_a(p_180532_1_).func_70543_e(0.2F).func_70541_f(0.6F));
      }
   }

   public void func_178928_b(EntityFX p_178928_1_) {
      this.func_178924_a(p_178928_1_, 1, 0);
   }

   public void func_178931_c(EntityFX p_178931_1_) {
      this.func_178924_a(p_178931_1_, 0, 1);
   }

   private void func_178924_a(EntityFX p_178924_1_, int p_178924_2_, int p_178924_3_) {
      for(int i = 0; i < 4; ++i) {
         if(this.field_78876_b[i][p_178924_2_].contains(p_178924_1_)) {
            this.field_78876_b[i][p_178924_2_].remove(p_178924_1_);
            this.field_78876_b[i][p_178924_3_].add(p_178924_1_);
         }
      }

   }

   public String func_78869_b() {
      int i = 0;

      for(int j = 0; j < 4; ++j) {
         for(int k = 0; k < 2; ++k) {
            i += this.field_78876_b[j][k].size();
         }
      }

      return "" + i;
   }
}
