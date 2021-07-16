package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnderman extends EntityMob {
   private static final UUID field_110192_bp = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
   private static final AttributeModifier field_110193_bq = (new AttributeModifier(field_110192_bp, "Attacking speed boost", 0.15000000596046448D, 0)).func_111168_a(false);
   private static final Set<Block> field_70827_d = Sets.<Block>newIdentityHashSet();
   private boolean field_104003_g;

   public EntityEnderman(World p_i1734_1_) {
      super(p_i1734_1_);
      this.func_70105_a(0.6F, 2.9F);
      this.field_70138_W = 1.0F;
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, false));
      this.field_70714_bg.func_75776_a(7, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.field_70714_bg.func_75776_a(10, new EntityEnderman.AIPlaceBlock(this));
      this.field_70714_bg.func_75776_a(11, new EntityEnderman.AITakeBlock(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70715_bh.func_75776_a(2, new EntityEnderman.AIFindPlayer(this));
      this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityEndermite.class, 10, true, false, new Predicate<EntityEndermite>() {
         public boolean apply(EntityEndermite p_apply_1_) {
            return p_apply_1_.func_175495_n();
         }
      }));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(64.0D);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, new Short((short)0));
      this.field_70180_af.func_75682_a(17, new Byte((byte)0));
      this.field_70180_af.func_75682_a(18, new Byte((byte)0));
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      IBlockState iblockstate = this.func_175489_ck();
      p_70014_1_.func_74777_a("carried", (short)Block.func_149682_b(iblockstate.func_177230_c()));
      p_70014_1_.func_74777_a("carriedData", (short)iblockstate.func_177230_c().func_176201_c(iblockstate));
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      IBlockState iblockstate;
      if(p_70037_1_.func_150297_b("carried", 8)) {
         iblockstate = Block.func_149684_b(p_70037_1_.func_74779_i("carried")).func_176203_a(p_70037_1_.func_74765_d("carriedData") & '\uffff');
      } else {
         iblockstate = Block.func_149729_e(p_70037_1_.func_74765_d("carried")).func_176203_a(p_70037_1_.func_74765_d("carriedData") & '\uffff');
      }

      this.func_175490_a(iblockstate);
   }

   private boolean func_70821_d(EntityPlayer p_70821_1_) {
      ItemStack itemstack = p_70821_1_.field_71071_by.field_70460_b[3];
      if(itemstack != null && itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150423_aK)) {
         return false;
      } else {
         Vec3 vec3 = p_70821_1_.func_70676_i(1.0F).func_72432_b();
         Vec3 vec31 = new Vec3(this.field_70165_t - p_70821_1_.field_70165_t, this.func_174813_aQ().field_72338_b + (double)(this.field_70131_O / 2.0F) - (p_70821_1_.field_70163_u + (double)p_70821_1_.func_70047_e()), this.field_70161_v - p_70821_1_.field_70161_v);
         double d0 = vec31.func_72433_c();
         vec31 = vec31.func_72432_b();
         double d1 = vec3.func_72430_b(vec31);
         return d1 > 1.0D - 0.025D / d0?p_70821_1_.func_70685_l(this):false;
      }
   }

   public float func_70047_e() {
      return 2.55F;
   }

   public void func_70636_d() {
      if(this.field_70170_p.field_72995_K) {
         for(int i = 0; i < 2; ++i) {
            this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, new int[0]);
         }
      }

      this.field_70703_bu = false;
      super.func_70636_d();
   }

   protected void func_70619_bc() {
      if(this.func_70026_G()) {
         this.func_70097_a(DamageSource.field_76369_e, 1.0F);
      }

      if(this.func_70823_r() && !this.field_104003_g && this.field_70146_Z.nextInt(100) == 0) {
         this.func_70819_e(false);
      }

      if(this.field_70170_p.func_72935_r()) {
         float f = this.func_70013_c(1.0F);
         if(f > 0.5F && this.field_70170_p.func_175678_i(new BlockPos(this)) && this.field_70146_Z.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
            this.func_70624_b((EntityLivingBase)null);
            this.func_70819_e(false);
            this.field_104003_g = false;
            this.func_70820_n();
         }
      }

      super.func_70619_bc();
   }

   protected boolean func_70820_n() {
      double d0 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 64.0D;
      double d1 = this.field_70163_u + (double)(this.field_70146_Z.nextInt(64) - 32);
      double d2 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 64.0D;
      return this.func_70825_j(d0, d1, d2);
   }

   protected boolean func_70816_c(Entity p_70816_1_) {
      Vec3 vec3 = new Vec3(this.field_70165_t - p_70816_1_.field_70165_t, this.func_174813_aQ().field_72338_b + (double)(this.field_70131_O / 2.0F) - p_70816_1_.field_70163_u + (double)p_70816_1_.func_70047_e(), this.field_70161_v - p_70816_1_.field_70161_v);
      vec3 = vec3.func_72432_b();
      double d0 = 16.0D;
      double d1 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72450_a * d0;
      double d2 = this.field_70163_u + (double)(this.field_70146_Z.nextInt(16) - 8) - vec3.field_72448_b * d0;
      double d3 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3.field_72449_c * d0;
      return this.func_70825_j(d1, d2, d3);
   }

   protected boolean func_70825_j(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
      double d0 = this.field_70165_t;
      double d1 = this.field_70163_u;
      double d2 = this.field_70161_v;
      this.field_70165_t = p_70825_1_;
      this.field_70163_u = p_70825_3_;
      this.field_70161_v = p_70825_5_;
      boolean flag = false;
      BlockPos blockpos = new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      if(this.field_70170_p.func_175667_e(blockpos)) {
         boolean flag1 = false;

         while(!flag1 && blockpos.func_177956_o() > 0) {
            BlockPos blockpos1 = blockpos.func_177977_b();
            Block block = this.field_70170_p.func_180495_p(blockpos1).func_177230_c();
            if(block.func_149688_o().func_76230_c()) {
               flag1 = true;
            } else {
               --this.field_70163_u;
               blockpos = blockpos1;
            }
         }

         if(flag1) {
            super.func_70634_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            if(this.field_70170_p.func_72945_a(this, this.func_174813_aQ()).isEmpty() && !this.field_70170_p.func_72953_d(this.func_174813_aQ())) {
               flag = true;
            }
         }
      }

      if(!flag) {
         this.func_70107_b(d0, d1, d2);
         return false;
      } else {
         int i = 128;

         for(int j = 0; j < i; ++j) {
            double d6 = (double)j / ((double)i - 1.0D);
            float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            double d3 = d0 + (this.field_70165_t - d0) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N * 2.0D;
            double d4 = d1 + (this.field_70163_u - d1) * d6 + this.field_70146_Z.nextDouble() * (double)this.field_70131_O;
            double d5 = d2 + (this.field_70161_v - d2) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N * 2.0D;
            this.field_70170_p.func_175688_a(EnumParticleTypes.PORTAL, d3, d4, d5, (double)f, (double)f1, (double)f2, new int[0]);
         }

         this.field_70170_p.func_72908_a(d0, d1, d2, "mob.endermen.portal", 1.0F, 1.0F);
         this.func_85030_a("mob.endermen.portal", 1.0F, 1.0F);
         return true;
      }
   }

   protected String func_70639_aQ() {
      return this.func_70823_r()?"mob.endermen.scream":"mob.endermen.idle";
   }

   protected String func_70621_aR() {
      return "mob.endermen.hit";
   }

   protected String func_70673_aS() {
      return "mob.endermen.death";
   }

   protected Item func_146068_u() {
      return Items.field_151079_bi;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      Item item = this.func_146068_u();
      if(item != null) {
         int i = this.field_70146_Z.nextInt(2 + p_70628_2_);

         for(int j = 0; j < i; ++j) {
            this.func_145779_a(item, 1);
         }
      }

   }

   public void func_175490_a(IBlockState p_175490_1_) {
      this.field_70180_af.func_75692_b(16, Short.valueOf((short)(Block.func_176210_f(p_175490_1_) & '\uffff')));
   }

   public IBlockState func_175489_ck() {
      return Block.func_176220_d(this.field_70180_af.func_75693_b(16) & '\uffff');
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_180431_b(p_70097_1_)) {
         return false;
      } else {
         if(p_70097_1_.func_76346_g() == null || !(p_70097_1_.func_76346_g() instanceof EntityEndermite)) {
            if(!this.field_70170_p.field_72995_K) {
               this.func_70819_e(true);
            }

            if(p_70097_1_ instanceof EntityDamageSource && p_70097_1_.func_76346_g() instanceof EntityPlayer) {
               if(p_70097_1_.func_76346_g() instanceof EntityPlayerMP && ((EntityPlayerMP)p_70097_1_.func_76346_g()).field_71134_c.func_73083_d()) {
                  this.func_70819_e(false);
               } else {
                  this.field_104003_g = true;
               }
            }

            if(p_70097_1_ instanceof EntityDamageSourceIndirect) {
               this.field_104003_g = false;

               for(int i = 0; i < 64; ++i) {
                  if(this.func_70820_n()) {
                     return true;
                  }
               }

               return false;
            }
         }

         boolean flag = super.func_70097_a(p_70097_1_, p_70097_2_);
         if(p_70097_1_.func_76363_c() && this.field_70146_Z.nextInt(10) != 0) {
            this.func_70820_n();
         }

         return flag;
      }
   }

   public boolean func_70823_r() {
      return this.field_70180_af.func_75683_a(18) > 0;
   }

   public void func_70819_e(boolean p_70819_1_) {
      this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)(p_70819_1_?1:0)));
   }

   static {
      field_70827_d.add(Blocks.field_150349_c);
      field_70827_d.add(Blocks.field_150346_d);
      field_70827_d.add(Blocks.field_150354_m);
      field_70827_d.add(Blocks.field_150351_n);
      field_70827_d.add(Blocks.field_150327_N);
      field_70827_d.add(Blocks.field_150328_O);
      field_70827_d.add(Blocks.field_150338_P);
      field_70827_d.add(Blocks.field_150337_Q);
      field_70827_d.add(Blocks.field_150335_W);
      field_70827_d.add(Blocks.field_150434_aF);
      field_70827_d.add(Blocks.field_150435_aG);
      field_70827_d.add(Blocks.field_150423_aK);
      field_70827_d.add(Blocks.field_150440_ba);
      field_70827_d.add(Blocks.field_150391_bh);
   }

   static class AIFindPlayer extends EntityAINearestAttackableTarget {
      private EntityPlayer field_179448_g;
      private int field_179450_h;
      private int field_179451_i;
      private EntityEnderman field_179449_j;

      public AIFindPlayer(EntityEnderman p_i45842_1_) {
         super(p_i45842_1_, EntityPlayer.class, true);
         this.field_179449_j = p_i45842_1_;
      }

      public boolean func_75250_a() {
         double d0 = this.func_111175_f();
         List<EntityPlayer> list = this.field_75299_d.field_70170_p.<EntityPlayer>func_175647_a(EntityPlayer.class, this.field_75299_d.func_174813_aQ().func_72314_b(d0, 4.0D, d0), this.field_82643_g);
         Collections.sort(list, this.field_75306_g);
         if(list.isEmpty()) {
            return false;
         } else {
            this.field_179448_g = (EntityPlayer)list.get(0);
            return true;
         }
      }

      public void func_75249_e() {
         this.field_179450_h = 5;
         this.field_179451_i = 0;
      }

      public void func_75251_c() {
         this.field_179448_g = null;
         this.field_179449_j.func_70819_e(false);
         IAttributeInstance iattributeinstance = this.field_179449_j.func_110148_a(SharedMonsterAttributes.field_111263_d);
         iattributeinstance.func_111124_b(EntityEnderman.field_110193_bq);
         super.func_75251_c();
      }

      public boolean func_75253_b() {
         if(this.field_179448_g != null) {
            if(!this.field_179449_j.func_70821_d(this.field_179448_g)) {
               return false;
            } else {
               this.field_179449_j.field_104003_g = true;
               this.field_179449_j.func_70625_a(this.field_179448_g, 10.0F, 10.0F);
               return true;
            }
         } else {
            return super.func_75253_b();
         }
      }

      public void func_75246_d() {
         if(this.field_179448_g != null) {
            if(--this.field_179450_h <= 0) {
               this.field_75309_a = this.field_179448_g;
               this.field_179448_g = null;
               super.func_75249_e();
               this.field_179449_j.func_85030_a("mob.endermen.stare", 1.0F, 1.0F);
               this.field_179449_j.func_70819_e(true);
               IAttributeInstance iattributeinstance = this.field_179449_j.func_110148_a(SharedMonsterAttributes.field_111263_d);
               iattributeinstance.func_111121_a(EntityEnderman.field_110193_bq);
            }
         } else {
            if(this.field_75309_a != null) {
               if(this.field_75309_a instanceof EntityPlayer && this.field_179449_j.func_70821_d((EntityPlayer)this.field_75309_a)) {
                  if(this.field_75309_a.func_70068_e(this.field_179449_j) < 16.0D) {
                     this.field_179449_j.func_70820_n();
                  }

                  this.field_179451_i = 0;
               } else if(this.field_75309_a.func_70068_e(this.field_179449_j) > 256.0D && this.field_179451_i++ >= 30 && this.field_179449_j.func_70816_c(this.field_75309_a)) {
                  this.field_179451_i = 0;
               }
            }

            super.func_75246_d();
         }

      }
   }

   static class AIPlaceBlock extends EntityAIBase {
      private EntityEnderman field_179475_a;

      public AIPlaceBlock(EntityEnderman p_i45843_1_) {
         this.field_179475_a = p_i45843_1_;
      }

      public boolean func_75250_a() {
         return !this.field_179475_a.field_70170_p.func_82736_K().func_82766_b("mobGriefing")?false:(this.field_179475_a.func_175489_ck().func_177230_c().func_149688_o() == Material.field_151579_a?false:this.field_179475_a.func_70681_au().nextInt(2000) == 0);
      }

      public void func_75246_d() {
         Random random = this.field_179475_a.func_70681_au();
         World world = this.field_179475_a.field_70170_p;
         int i = MathHelper.func_76128_c(this.field_179475_a.field_70165_t - 1.0D + random.nextDouble() * 2.0D);
         int j = MathHelper.func_76128_c(this.field_179475_a.field_70163_u + random.nextDouble() * 2.0D);
         int k = MathHelper.func_76128_c(this.field_179475_a.field_70161_v - 1.0D + random.nextDouble() * 2.0D);
         BlockPos blockpos = new BlockPos(i, j, k);
         Block block = world.func_180495_p(blockpos).func_177230_c();
         Block block1 = world.func_180495_p(blockpos.func_177977_b()).func_177230_c();
         if(this.func_179474_a(world, blockpos, this.field_179475_a.func_175489_ck().func_177230_c(), block, block1)) {
            world.func_180501_a(blockpos, this.field_179475_a.func_175489_ck(), 3);
            this.field_179475_a.func_175490_a(Blocks.field_150350_a.func_176223_P());
         }

      }

      private boolean func_179474_a(World p_179474_1_, BlockPos p_179474_2_, Block p_179474_3_, Block p_179474_4_, Block p_179474_5_) {
         return !p_179474_3_.func_176196_c(p_179474_1_, p_179474_2_)?false:(p_179474_4_.func_149688_o() != Material.field_151579_a?false:(p_179474_5_.func_149688_o() == Material.field_151579_a?false:p_179474_5_.func_149686_d()));
      }
   }

   static class AITakeBlock extends EntityAIBase {
      private EntityEnderman field_179473_a;

      public AITakeBlock(EntityEnderman p_i45841_1_) {
         this.field_179473_a = p_i45841_1_;
      }

      public boolean func_75250_a() {
         return !this.field_179473_a.field_70170_p.func_82736_K().func_82766_b("mobGriefing")?false:(this.field_179473_a.func_175489_ck().func_177230_c().func_149688_o() != Material.field_151579_a?false:this.field_179473_a.func_70681_au().nextInt(20) == 0);
      }

      public void func_75246_d() {
         Random random = this.field_179473_a.func_70681_au();
         World world = this.field_179473_a.field_70170_p;
         int i = MathHelper.func_76128_c(this.field_179473_a.field_70165_t - 2.0D + random.nextDouble() * 4.0D);
         int j = MathHelper.func_76128_c(this.field_179473_a.field_70163_u + random.nextDouble() * 3.0D);
         int k = MathHelper.func_76128_c(this.field_179473_a.field_70161_v - 2.0D + random.nextDouble() * 4.0D);
         BlockPos blockpos = new BlockPos(i, j, k);
         IBlockState iblockstate = world.func_180495_p(blockpos);
         Block block = iblockstate.func_177230_c();
         if(EntityEnderman.field_70827_d.contains(block)) {
            this.field_179473_a.func_175490_a(iblockstate);
            world.func_175656_a(blockpos, Blocks.field_150350_a.func_176223_P());
         }

      }
   }
}
