package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderCaveSpider;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderEndermite;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderFallingBlock;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderFish;
import net.minecraft.client.renderer.entity.RenderGhast;
import net.minecraft.client.renderer.entity.RenderGiantZombie;
import net.minecraft.client.renderer.entity.RenderGuardian;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderLeashKnot;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.entity.RenderMinecartMobSpawner;
import net.minecraft.client.renderer.entity.RenderMooshroom;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.client.renderer.entity.RenderPigZombie;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderPotion;
import net.minecraft.client.renderer.entity.RenderRabbit;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RenderManager {
   private Map<Class<? extends Entity>, Render<? extends Entity>> field_78729_o = Maps.<Class<? extends Entity>, Render<? extends Entity>>newHashMap();
   private Map<String, RenderPlayer> field_178636_l = Maps.<String, RenderPlayer>newHashMap();
   private RenderPlayer field_178637_m;
   private FontRenderer field_78736_p;
   private double field_78725_b;
   private double field_78726_c;
   private double field_78723_d;
   public TextureManager field_78724_e;
   public World field_78722_g;
   public Entity field_78734_h;
   public Entity field_147941_i;
   public float field_78735_i;
   public float field_78732_j;
   public GameSettings field_78733_k;
   public double field_78730_l;
   public double field_78731_m;
   public double field_78728_n;
   private boolean field_178639_r = false;
   private boolean field_178638_s = true;
   private boolean field_85095_o = false;

   public RenderManager(TextureManager p_i46180_1_, RenderItem p_i46180_2_) {
      this.field_78724_e = p_i46180_1_;
      this.field_78729_o.put(EntityCaveSpider.class, new RenderCaveSpider(this));
      this.field_78729_o.put(EntitySpider.class, new RenderSpider(this));
      this.field_78729_o.put(EntityPig.class, new RenderPig(this, new ModelPig(), 0.7F));
      this.field_78729_o.put(EntitySheep.class, new RenderSheep(this, new ModelSheep2(), 0.7F));
      this.field_78729_o.put(EntityCow.class, new RenderCow(this, new ModelCow(), 0.7F));
      this.field_78729_o.put(EntityMooshroom.class, new RenderMooshroom(this, new ModelCow(), 0.7F));
      this.field_78729_o.put(EntityWolf.class, new RenderWolf(this, new ModelWolf(), 0.5F));
      this.field_78729_o.put(EntityChicken.class, new RenderChicken(this, new ModelChicken(), 0.3F));
      this.field_78729_o.put(EntityOcelot.class, new RenderOcelot(this, new ModelOcelot(), 0.4F));
      this.field_78729_o.put(EntityRabbit.class, new RenderRabbit(this, new ModelRabbit(), 0.3F));
      this.field_78729_o.put(EntitySilverfish.class, new RenderSilverfish(this));
      this.field_78729_o.put(EntityEndermite.class, new RenderEndermite(this));
      this.field_78729_o.put(EntityCreeper.class, new RenderCreeper(this));
      this.field_78729_o.put(EntityEnderman.class, new RenderEnderman(this));
      this.field_78729_o.put(EntitySnowman.class, new RenderSnowMan(this));
      this.field_78729_o.put(EntitySkeleton.class, new RenderSkeleton(this));
      this.field_78729_o.put(EntityWitch.class, new RenderWitch(this));
      this.field_78729_o.put(EntityBlaze.class, new RenderBlaze(this));
      this.field_78729_o.put(EntityPigZombie.class, new RenderPigZombie(this));
      this.field_78729_o.put(EntityZombie.class, new RenderZombie(this));
      this.field_78729_o.put(EntitySlime.class, new RenderSlime(this, new ModelSlime(16), 0.25F));
      this.field_78729_o.put(EntityMagmaCube.class, new RenderMagmaCube(this));
      this.field_78729_o.put(EntityGiantZombie.class, new RenderGiantZombie(this, new ModelZombie(), 0.5F, 6.0F));
      this.field_78729_o.put(EntityGhast.class, new RenderGhast(this));
      this.field_78729_o.put(EntitySquid.class, new RenderSquid(this, new ModelSquid(), 0.7F));
      this.field_78729_o.put(EntityVillager.class, new RenderVillager(this));
      this.field_78729_o.put(EntityIronGolem.class, new RenderIronGolem(this));
      this.field_78729_o.put(EntityBat.class, new RenderBat(this));
      this.field_78729_o.put(EntityGuardian.class, new RenderGuardian(this));
      this.field_78729_o.put(EntityDragon.class, new RenderDragon(this));
      this.field_78729_o.put(EntityEnderCrystal.class, new RenderEnderCrystal(this));
      this.field_78729_o.put(EntityWither.class, new RenderWither(this));
      this.field_78729_o.put(Entity.class, new RenderEntity(this));
      this.field_78729_o.put(EntityPainting.class, new RenderPainting(this));
      this.field_78729_o.put(EntityItemFrame.class, new RenderItemFrame(this, p_i46180_2_));
      this.field_78729_o.put(EntityLeashKnot.class, new RenderLeashKnot(this));
      this.field_78729_o.put(EntityArrow.class, new RenderArrow(this));
      this.field_78729_o.put(EntitySnowball.class, new RenderSnowball(this, Items.field_151126_ay, p_i46180_2_));
      this.field_78729_o.put(EntityEnderPearl.class, new RenderSnowball(this, Items.field_151079_bi, p_i46180_2_));
      this.field_78729_o.put(EntityEnderEye.class, new RenderSnowball(this, Items.field_151061_bv, p_i46180_2_));
      this.field_78729_o.put(EntityEgg.class, new RenderSnowball(this, Items.field_151110_aK, p_i46180_2_));
      this.field_78729_o.put(EntityPotion.class, new RenderPotion(this, p_i46180_2_));
      this.field_78729_o.put(EntityExpBottle.class, new RenderSnowball(this, Items.field_151062_by, p_i46180_2_));
      this.field_78729_o.put(EntityFireworkRocket.class, new RenderSnowball(this, Items.field_151152_bP, p_i46180_2_));
      this.field_78729_o.put(EntityLargeFireball.class, new RenderFireball(this, 2.0F));
      this.field_78729_o.put(EntitySmallFireball.class, new RenderFireball(this, 0.5F));
      this.field_78729_o.put(EntityWitherSkull.class, new RenderWitherSkull(this));
      this.field_78729_o.put(EntityItem.class, new RenderEntityItem(this, p_i46180_2_));
      this.field_78729_o.put(EntityXPOrb.class, new RenderXPOrb(this));
      this.field_78729_o.put(EntityTNTPrimed.class, new RenderTNTPrimed(this));
      this.field_78729_o.put(EntityFallingBlock.class, new RenderFallingBlock(this));
      this.field_78729_o.put(EntityArmorStand.class, new ArmorStandRenderer(this));
      this.field_78729_o.put(EntityMinecartTNT.class, new RenderTntMinecart(this));
      this.field_78729_o.put(EntityMinecartMobSpawner.class, new RenderMinecartMobSpawner(this));
      this.field_78729_o.put(EntityMinecart.class, new RenderMinecart(this));
      this.field_78729_o.put(EntityBoat.class, new RenderBoat(this));
      this.field_78729_o.put(EntityFishHook.class, new RenderFish(this));
      this.field_78729_o.put(EntityHorse.class, new RenderHorse(this, new ModelHorse(), 0.75F));
      this.field_78729_o.put(EntityLightningBolt.class, new RenderLightningBolt(this));
      this.field_178637_m = new RenderPlayer(this);
      this.field_178636_l.put("default", this.field_178637_m);
      this.field_178636_l.put("slim", new RenderPlayer(this, true));
   }

   public void func_178628_a(double p_178628_1_, double p_178628_3_, double p_178628_5_) {
      this.field_78725_b = p_178628_1_;
      this.field_78726_c = p_178628_3_;
      this.field_78723_d = p_178628_5_;
   }

   public <T extends Entity> Render<T> func_78715_a(Class<? extends Entity> p_78715_1_) {
      Render<? extends Entity> render = (Render)this.field_78729_o.get(p_78715_1_);
      if(render == null && p_78715_1_ != Entity.class) {
         render = this.<Entity>func_78715_a(p_78715_1_.getSuperclass());
         this.field_78729_o.put(p_78715_1_, render);
      }

      return render;
   }

   public <T extends Entity> Render<T> func_78713_a(Entity p_78713_1_) {
      if(p_78713_1_ instanceof AbstractClientPlayer) {
         String s = ((AbstractClientPlayer)p_78713_1_).func_175154_l();
         RenderPlayer renderplayer = (RenderPlayer)this.field_178636_l.get(s);
         return renderplayer != null?renderplayer:this.field_178637_m;
      } else {
         return this.<T>func_78715_a(p_78713_1_.getClass());
      }
   }

   public void func_180597_a(World p_180597_1_, FontRenderer p_180597_2_, Entity p_180597_3_, Entity p_180597_4_, GameSettings p_180597_5_, float p_180597_6_) {
      this.field_78722_g = p_180597_1_;
      this.field_78733_k = p_180597_5_;
      this.field_78734_h = p_180597_3_;
      this.field_147941_i = p_180597_4_;
      this.field_78736_p = p_180597_2_;
      if(p_180597_3_ instanceof EntityLivingBase && ((EntityLivingBase)p_180597_3_).func_70608_bn()) {
         IBlockState iblockstate = p_180597_1_.func_180495_p(new BlockPos(p_180597_3_));
         Block block = iblockstate.func_177230_c();
         if(block == Blocks.field_150324_C) {
            int i = ((EnumFacing)iblockstate.func_177229_b(BlockBed.field_176387_N)).func_176736_b();
            this.field_78735_i = (float)(i * 90 + 180);
            this.field_78732_j = 0.0F;
         }
      } else {
         this.field_78735_i = p_180597_3_.field_70126_B + (p_180597_3_.field_70177_z - p_180597_3_.field_70126_B) * p_180597_6_;
         this.field_78732_j = p_180597_3_.field_70127_C + (p_180597_3_.field_70125_A - p_180597_3_.field_70127_C) * p_180597_6_;
      }

      if(p_180597_5_.field_74320_O == 2) {
         this.field_78735_i += 180.0F;
      }

      this.field_78730_l = p_180597_3_.field_70142_S + (p_180597_3_.field_70165_t - p_180597_3_.field_70142_S) * (double)p_180597_6_;
      this.field_78731_m = p_180597_3_.field_70137_T + (p_180597_3_.field_70163_u - p_180597_3_.field_70137_T) * (double)p_180597_6_;
      this.field_78728_n = p_180597_3_.field_70136_U + (p_180597_3_.field_70161_v - p_180597_3_.field_70136_U) * (double)p_180597_6_;
   }

   public void func_178631_a(float p_178631_1_) {
      this.field_78735_i = p_178631_1_;
   }

   public boolean func_178627_a() {
      return this.field_178638_s;
   }

   public void func_178633_a(boolean p_178633_1_) {
      this.field_178638_s = p_178633_1_;
   }

   public void func_178629_b(boolean p_178629_1_) {
      this.field_85095_o = p_178629_1_;
   }

   public boolean func_178634_b() {
      return this.field_85095_o;
   }

   public boolean func_147937_a(Entity p_147937_1_, float p_147937_2_) {
      return this.func_147936_a(p_147937_1_, p_147937_2_, false);
   }

   public boolean func_178635_a(Entity p_178635_1_, ICamera p_178635_2_, double p_178635_3_, double p_178635_5_, double p_178635_7_) {
      Render<Entity> render = this.<Entity>func_78713_a(p_178635_1_);
      return render != null && render.func_177071_a(p_178635_1_, p_178635_2_, p_178635_3_, p_178635_5_, p_178635_7_);
   }

   public boolean func_147936_a(Entity p_147936_1_, float p_147936_2_, boolean p_147936_3_) {
      if(p_147936_1_.field_70173_aa == 0) {
         p_147936_1_.field_70142_S = p_147936_1_.field_70165_t;
         p_147936_1_.field_70137_T = p_147936_1_.field_70163_u;
         p_147936_1_.field_70136_U = p_147936_1_.field_70161_v;
      }

      double d0 = p_147936_1_.field_70142_S + (p_147936_1_.field_70165_t - p_147936_1_.field_70142_S) * (double)p_147936_2_;
      double d1 = p_147936_1_.field_70137_T + (p_147936_1_.field_70163_u - p_147936_1_.field_70137_T) * (double)p_147936_2_;
      double d2 = p_147936_1_.field_70136_U + (p_147936_1_.field_70161_v - p_147936_1_.field_70136_U) * (double)p_147936_2_;
      float f = p_147936_1_.field_70126_B + (p_147936_1_.field_70177_z - p_147936_1_.field_70126_B) * p_147936_2_;
      int i = p_147936_1_.func_70070_b(p_147936_2_);
      if(p_147936_1_.func_70027_ad()) {
         i = 15728880;
      }

      int j = i % 65536;
      int k = i / 65536;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)j / 1.0F, (float)k / 1.0F);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      return this.func_147939_a(p_147936_1_, d0 - this.field_78725_b, d1 - this.field_78726_c, d2 - this.field_78723_d, f, p_147936_2_, p_147936_3_);
   }

   public void func_178630_b(Entity p_178630_1_, float p_178630_2_) {
      double d0 = p_178630_1_.field_70142_S + (p_178630_1_.field_70165_t - p_178630_1_.field_70142_S) * (double)p_178630_2_;
      double d1 = p_178630_1_.field_70137_T + (p_178630_1_.field_70163_u - p_178630_1_.field_70137_T) * (double)p_178630_2_;
      double d2 = p_178630_1_.field_70136_U + (p_178630_1_.field_70161_v - p_178630_1_.field_70136_U) * (double)p_178630_2_;
      Render<Entity> render = this.<Entity>func_78713_a(p_178630_1_);
      if(render != null && this.field_78724_e != null) {
         int i = p_178630_1_.func_70070_b(p_178630_2_);
         int j = i % 65536;
         int k = i / 65536;
         OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, (float)j / 1.0F, (float)k / 1.0F);
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         render.func_177067_a(p_178630_1_, d0 - this.field_78725_b, d1 - this.field_78726_c, d2 - this.field_78723_d);
      }

   }

   public boolean func_147940_a(Entity p_147940_1_, double p_147940_2_, double p_147940_4_, double p_147940_6_, float p_147940_8_, float p_147940_9_) {
      return this.func_147939_a(p_147940_1_, p_147940_2_, p_147940_4_, p_147940_6_, p_147940_8_, p_147940_9_, false);
   }

   public boolean func_147939_a(Entity p_147939_1_, double p_147939_2_, double p_147939_4_, double p_147939_6_, float p_147939_8_, float p_147939_9_, boolean p_147939_10_) {
      Render<Entity> render = null;

      try {
         render = this.<Entity>func_78713_a(p_147939_1_);
         if(render != null && this.field_78724_e != null) {
            try {
               if(render instanceof RendererLivingEntity) {
                  ((RendererLivingEntity)render).func_177086_a(this.field_178639_r);
               }

               render.func_76986_a(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);
            } catch (Throwable throwable2) {
               throw new ReportedException(CrashReport.func_85055_a(throwable2, "Rendering entity in world"));
            }

            try {
               if(!this.field_178639_r) {
                  render.func_76979_b(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);
               }
            } catch (Throwable throwable1) {
               throw new ReportedException(CrashReport.func_85055_a(throwable1, "Post-rendering entity in world"));
            }

            if(this.field_85095_o && !p_147939_1_.func_82150_aj() && !p_147939_10_) {
               try {
                  this.func_85094_b(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);
               } catch (Throwable throwable) {
                  throw new ReportedException(CrashReport.func_85055_a(throwable, "Rendering entity hitbox in world"));
               }
            }
         } else if(this.field_78724_e != null) {
            return false;
         }

         return true;
      } catch (Throwable throwable3) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable3, "Rendering entity in world");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being rendered");
         p_147939_1_.func_85029_a(crashreportcategory);
         CrashReportCategory crashreportcategory1 = crashreport.func_85058_a("Renderer details");
         crashreportcategory1.func_71507_a("Assigned renderer", render);
         crashreportcategory1.func_71507_a("Location", CrashReportCategory.func_85074_a(p_147939_2_, p_147939_4_, p_147939_6_));
         crashreportcategory1.func_71507_a("Rotation", Float.valueOf(p_147939_8_));
         crashreportcategory1.func_71507_a("Delta", Float.valueOf(p_147939_9_));
         throw new ReportedException(crashreport);
      }
   }

   private void func_85094_b(Entity p_85094_1_, double p_85094_2_, double p_85094_4_, double p_85094_6_, float p_85094_8_, float p_85094_9_) {
      GlStateManager.func_179132_a(false);
      GlStateManager.func_179090_x();
      GlStateManager.func_179140_f();
      GlStateManager.func_179129_p();
      GlStateManager.func_179084_k();
      float f = p_85094_1_.field_70130_N / 2.0F;
      AxisAlignedBB axisalignedbb = p_85094_1_.func_174813_aQ();
      AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.field_72340_a - p_85094_1_.field_70165_t + p_85094_2_, axisalignedbb.field_72338_b - p_85094_1_.field_70163_u + p_85094_4_, axisalignedbb.field_72339_c - p_85094_1_.field_70161_v + p_85094_6_, axisalignedbb.field_72336_d - p_85094_1_.field_70165_t + p_85094_2_, axisalignedbb.field_72337_e - p_85094_1_.field_70163_u + p_85094_4_, axisalignedbb.field_72334_f - p_85094_1_.field_70161_v + p_85094_6_);
      RenderGlobal.func_181563_a(axisalignedbb1, 255, 255, 255, 255);
      if(p_85094_1_ instanceof EntityLivingBase) {
         float f1 = 0.01F;
         RenderGlobal.func_181563_a(new AxisAlignedBB(p_85094_2_ - (double)f, p_85094_4_ + (double)p_85094_1_.func_70047_e() - 0.009999999776482582D, p_85094_6_ - (double)f, p_85094_2_ + (double)f, p_85094_4_ + (double)p_85094_1_.func_70047_e() + 0.009999999776482582D, p_85094_6_ + (double)f), 255, 0, 0, 255);
      }

      Tessellator tessellator = Tessellator.func_178181_a();
      WorldRenderer worldrenderer = tessellator.func_178180_c();
      Vec3 vec3 = p_85094_1_.func_70676_i(p_85094_9_);
      worldrenderer.func_181668_a(3, DefaultVertexFormats.field_181706_f);
      worldrenderer.func_181662_b(p_85094_2_, p_85094_4_ + (double)p_85094_1_.func_70047_e(), p_85094_6_).func_181669_b(0, 0, 255, 255).func_181675_d();
      worldrenderer.func_181662_b(p_85094_2_ + vec3.field_72450_a * 2.0D, p_85094_4_ + (double)p_85094_1_.func_70047_e() + vec3.field_72448_b * 2.0D, p_85094_6_ + vec3.field_72449_c * 2.0D).func_181669_b(0, 0, 255, 255).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179098_w();
      GlStateManager.func_179145_e();
      GlStateManager.func_179089_o();
      GlStateManager.func_179084_k();
      GlStateManager.func_179132_a(true);
   }

   public void func_78717_a(World p_78717_1_) {
      this.field_78722_g = p_78717_1_;
   }

   public double func_78714_a(double p_78714_1_, double p_78714_3_, double p_78714_5_) {
      double d0 = p_78714_1_ - this.field_78730_l;
      double d1 = p_78714_3_ - this.field_78731_m;
      double d2 = p_78714_5_ - this.field_78728_n;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public FontRenderer func_78716_a() {
      return this.field_78736_p;
   }

   public void func_178632_c(boolean p_178632_1_) {
      this.field_178639_r = p_178632_1_;
   }
}
