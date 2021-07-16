package net.minecraft.entity.passive;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityRabbit extends EntityAnimal {
   private EntityRabbit.AIAvoidEntity<EntityWolf> field_175539_bk;
   private int field_175540_bm = 0;
   private int field_175535_bn = 0;
   private boolean field_175536_bo = false;
   private boolean field_175537_bp = false;
   private int field_175538_bq = 0;
   private EntityRabbit.EnumMoveType field_175542_br = EntityRabbit.EnumMoveType.HOP;
   private int field_175541_bs = 0;
   private EntityPlayer field_175543_bt = null;

   public EntityRabbit(World p_i45869_1_) {
      super(p_i45869_1_);
      this.func_70105_a(0.6F, 0.7F);
      this.field_70767_i = new EntityRabbit.RabbitJumpHelper(this);
      this.field_70765_h = new EntityRabbit.RabbitMoveHelper(this);
      ((PathNavigateGround)this.func_70661_as()).func_179690_a(true);
      this.field_70699_by.func_179678_a(2.5F);
      this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(1, new EntityRabbit.AIPanic(this, 1.33D));
      this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 1.0D, Items.field_151172_bF, false));
      this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 1.0D, Items.field_151150_bK, false));
      this.field_70714_bg.func_75776_a(2, new EntityAITempt(this, 1.0D, Item.func_150898_a(Blocks.field_150327_N), false));
      this.field_70714_bg.func_75776_a(3, new EntityAIMate(this, 0.8D));
      this.field_70714_bg.func_75776_a(5, new EntityRabbit.AIRaidFarm(this));
      this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 0.6D));
      this.field_70714_bg.func_75776_a(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
      this.field_175539_bk = new EntityRabbit.AIAvoidEntity(this, EntityWolf.class, 16.0F, 1.33D, 1.33D);
      this.field_70714_bg.func_75776_a(4, this.field_175539_bk);
      this.func_175515_b(0.0D);
   }

   protected float func_175134_bD() {
      return this.field_70765_h.func_75640_a() && this.field_70765_h.func_179919_e() > this.field_70163_u + 0.5D?0.5F:this.field_175542_br.func_180074_b();
   }

   public void func_175522_a(EntityRabbit.EnumMoveType p_175522_1_) {
      this.field_175542_br = p_175522_1_;
   }

   public float func_175521_o(float p_175521_1_) {
      return this.field_175535_bn == 0?0.0F:((float)this.field_175540_bm + p_175521_1_) / (float)this.field_175535_bn;
   }

   public void func_175515_b(double p_175515_1_) {
      this.func_70661_as().func_75489_a(p_175515_1_);
      this.field_70765_h.func_75642_a(this.field_70765_h.func_179917_d(), this.field_70765_h.func_179919_e(), this.field_70765_h.func_179918_f(), p_175515_1_);
   }

   public void func_175519_a(boolean p_175519_1_, EntityRabbit.EnumMoveType p_175519_2_) {
      super.func_70637_d(p_175519_1_);
      if(!p_175519_1_) {
         if(this.field_175542_br == EntityRabbit.EnumMoveType.ATTACK) {
            this.field_175542_br = EntityRabbit.EnumMoveType.HOP;
         }
      } else {
         this.func_175515_b(1.5D * (double)p_175519_2_.func_180072_a());
         this.func_85030_a(this.func_175516_ck(), this.func_70599_aP(), ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
      }

      this.field_175536_bo = p_175519_1_;
   }

   public void func_175524_b(EntityRabbit.EnumMoveType p_175524_1_) {
      this.func_175519_a(true, p_175524_1_);
      this.field_175535_bn = p_175524_1_.func_180073_d();
      this.field_175540_bm = 0;
   }

   public boolean func_175523_cj() {
      return this.field_175536_bo;
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
   }

   public void func_70619_bc() {
      if(this.field_70765_h.func_75638_b() > 0.8D) {
         this.func_175522_a(EntityRabbit.EnumMoveType.SPRINT);
      } else if(this.field_175542_br != EntityRabbit.EnumMoveType.ATTACK) {
         this.func_175522_a(EntityRabbit.EnumMoveType.HOP);
      }

      if(this.field_175538_bq > 0) {
         --this.field_175538_bq;
      }

      if(this.field_175541_bs > 0) {
         this.field_175541_bs -= this.field_70146_Z.nextInt(3);
         if(this.field_175541_bs < 0) {
            this.field_175541_bs = 0;
         }
      }

      if(this.field_70122_E) {
         if(!this.field_175537_bp) {
            this.func_175519_a(false, EntityRabbit.EnumMoveType.NONE);
            this.func_175517_cu();
         }

         if(this.func_175531_cl() == 99 && this.field_175538_bq == 0) {
            EntityLivingBase entitylivingbase = this.func_70638_az();
            if(entitylivingbase != null && this.func_70068_e(entitylivingbase) < 16.0D) {
               this.func_175533_a(entitylivingbase.field_70165_t, entitylivingbase.field_70161_v);
               this.field_70765_h.func_75642_a(entitylivingbase.field_70165_t, entitylivingbase.field_70163_u, entitylivingbase.field_70161_v, this.field_70765_h.func_75638_b());
               this.func_175524_b(EntityRabbit.EnumMoveType.ATTACK);
               this.field_175537_bp = true;
            }
         }

         EntityRabbit.RabbitJumpHelper entityrabbit$rabbitjumphelper = (EntityRabbit.RabbitJumpHelper)this.field_70767_i;
         if(!entityrabbit$rabbitjumphelper.func_180067_c()) {
            if(this.field_70765_h.func_75640_a() && this.field_175538_bq == 0) {
               PathEntity pathentity = this.field_70699_by.func_75505_d();
               Vec3 vec3 = new Vec3(this.field_70765_h.func_179917_d(), this.field_70765_h.func_179919_e(), this.field_70765_h.func_179918_f());
               if(pathentity != null && pathentity.func_75873_e() < pathentity.func_75874_d()) {
                  vec3 = pathentity.func_75878_a(this);
               }

               this.func_175533_a(vec3.field_72450_a, vec3.field_72449_c);
               this.func_175524_b(this.field_175542_br);
            }
         } else if(!entityrabbit$rabbitjumphelper.func_180065_d()) {
            this.func_175518_cr();
         }
      }

      this.field_175537_bp = this.field_70122_E;
   }

   public void func_174830_Y() {
   }

   private void func_175533_a(double p_175533_1_, double p_175533_3_) {
      this.field_70177_z = (float)(MathHelper.func_181159_b(p_175533_3_ - this.field_70161_v, p_175533_1_ - this.field_70165_t) * 180.0D / 3.1415927410125732D) - 90.0F;
   }

   private void func_175518_cr() {
      ((EntityRabbit.RabbitJumpHelper)this.field_70767_i).func_180066_a(true);
   }

   private void func_175520_cs() {
      ((EntityRabbit.RabbitJumpHelper)this.field_70767_i).func_180066_a(false);
   }

   private void func_175530_ct() {
      this.field_175538_bq = this.func_175532_cm();
   }

   private void func_175517_cu() {
      this.func_175530_ct();
      this.func_175520_cs();
   }

   public void func_70636_d() {
      super.func_70636_d();
      if(this.field_175540_bm != this.field_175535_bn) {
         if(this.field_175540_bm == 0 && !this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72960_a(this, (byte)1);
         }

         ++this.field_175540_bm;
      } else if(this.field_175535_bn != 0) {
         this.field_175540_bm = 0;
         this.field_175535_bn = 0;
      }

   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.30000001192092896D);
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74768_a("RabbitType", this.func_175531_cl());
      p_70014_1_.func_74768_a("MoreCarrotTicks", this.field_175541_bs);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_175529_r(p_70037_1_.func_74762_e("RabbitType"));
      this.field_175541_bs = p_70037_1_.func_74762_e("MoreCarrotTicks");
   }

   protected String func_175516_ck() {
      return "mob.rabbit.hop";
   }

   protected String func_70639_aQ() {
      return "mob.rabbit.idle";
   }

   protected String func_70621_aR() {
      return "mob.rabbit.hurt";
   }

   protected String func_70673_aS() {
      return "mob.rabbit.death";
   }

   public boolean func_70652_k(Entity p_70652_1_) {
      if(this.func_175531_cl() == 99) {
         this.func_85030_a("mob.attack", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
         return p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), 8.0F);
      } else {
         return p_70652_1_.func_70097_a(DamageSource.func_76358_a(this), 3.0F);
      }
   }

   public int func_70658_aO() {
      return this.func_175531_cl() == 99?8:super.func_70658_aO();
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      return this.func_180431_b(p_70097_1_)?false:super.func_70097_a(p_70097_1_, p_70097_2_);
   }

   protected void func_82164_bB() {
      this.func_70099_a(new ItemStack(Items.field_179556_br, 1), 0.0F);
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      int i = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + p_70628_2_);

      for(int j = 0; j < i; ++j) {
         this.func_145779_a(Items.field_179555_bs, 1);
      }

      i = this.field_70146_Z.nextInt(2);

      for(int k = 0; k < i; ++k) {
         if(this.func_70027_ad()) {
            this.func_145779_a(Items.field_179559_bp, 1);
         } else {
            this.func_145779_a(Items.field_179558_bo, 1);
         }
      }

   }

   private boolean func_175525_a(Item p_175525_1_) {
      return p_175525_1_ == Items.field_151172_bF || p_175525_1_ == Items.field_151150_bK || p_175525_1_ == Item.func_150898_a(Blocks.field_150327_N);
   }

   public EntityRabbit func_90011_a(EntityAgeable p_90011_1_) {
      EntityRabbit entityrabbit = new EntityRabbit(this.field_70170_p);
      if(p_90011_1_ instanceof EntityRabbit) {
         entityrabbit.func_175529_r(this.field_70146_Z.nextBoolean()?this.func_175531_cl():((EntityRabbit)p_90011_1_).func_175531_cl());
      }

      return entityrabbit;
   }

   public boolean func_70877_b(ItemStack p_70877_1_) {
      return p_70877_1_ != null && this.func_175525_a(p_70877_1_.func_77973_b());
   }

   public int func_175531_cl() {
      return this.field_70180_af.func_75683_a(18);
   }

   public void func_175529_r(int p_175529_1_) {
      if(p_175529_1_ == 99) {
         this.field_70714_bg.func_85156_a(this.field_175539_bk);
         this.field_70714_bg.func_75776_a(4, new EntityRabbit.AIEvilAttack(this));
         this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
         this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
         this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityWolf.class, true));
         if(!this.func_145818_k_()) {
            this.func_96094_a(StatCollector.func_74838_a("entity.KillerBunny.name"));
         }
      }

      this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)p_175529_1_));
   }

   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
      p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
      int i = this.field_70146_Z.nextInt(6);
      boolean flag = false;
      if(p_180482_2_ instanceof EntityRabbit.RabbitTypeData) {
         i = ((EntityRabbit.RabbitTypeData)p_180482_2_).field_179427_a;
         flag = true;
      } else {
         p_180482_2_ = new EntityRabbit.RabbitTypeData(i);
      }

      this.func_175529_r(i);
      if(flag) {
         this.func_70873_a(-24000);
      }

      return p_180482_2_;
   }

   private boolean func_175534_cv() {
      return this.field_175541_bs == 0;
   }

   protected int func_175532_cm() {
      return this.field_175542_br.func_180075_c();
   }

   protected void func_175528_cn() {
      this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_DUST, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, 0.0D, 0.0D, 0.0D, new int[]{Block.func_176210_f(Blocks.field_150459_bM.func_176203_a(7))});
      this.field_175541_bs = 100;
   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 1) {
         this.func_174808_Z();
         this.field_175535_bn = 10;
         this.field_175540_bm = 0;
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   static class AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T> {
      private EntityRabbit field_179511_d;

      public AIAvoidEntity(EntityRabbit p_i46403_1_, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
         super(p_i46403_1_, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
         this.field_179511_d = p_i46403_1_;
      }

      public void func_75246_d() {
         super.func_75246_d();
      }
   }

   static class AIEvilAttack extends EntityAIAttackOnCollide {
      public AIEvilAttack(EntityRabbit p_i45867_1_) {
         super(p_i45867_1_, EntityLivingBase.class, 1.4D, true);
      }

      protected double func_179512_a(EntityLivingBase p_179512_1_) {
         return (double)(4.0F + p_179512_1_.field_70130_N);
      }
   }

   static class AIPanic extends EntityAIPanic {
      private EntityRabbit field_179486_b;

      public AIPanic(EntityRabbit p_i45861_1_, double p_i45861_2_) {
         super(p_i45861_1_, p_i45861_2_);
         this.field_179486_b = p_i45861_1_;
      }

      public void func_75246_d() {
         super.func_75246_d();
         this.field_179486_b.func_175515_b(this.field_75265_b);
      }
   }

   static class AIRaidFarm extends EntityAIMoveToBlock {
      private final EntityRabbit field_179500_c;
      private boolean field_179498_d;
      private boolean field_179499_e = false;

      public AIRaidFarm(EntityRabbit p_i45860_1_) {
         super(p_i45860_1_, 0.699999988079071D, 16);
         this.field_179500_c = p_i45860_1_;
      }

      public boolean func_75250_a() {
         if(this.field_179496_a <= 0) {
            if(!this.field_179500_c.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
               return false;
            }

            this.field_179499_e = false;
            this.field_179498_d = this.field_179500_c.func_175534_cv();
         }

         return super.func_75250_a();
      }

      public boolean func_75253_b() {
         return this.field_179499_e && super.func_75253_b();
      }

      public void func_75249_e() {
         super.func_75249_e();
      }

      public void func_75251_c() {
         super.func_75251_c();
      }

      public void func_75246_d() {
         super.func_75246_d();
         this.field_179500_c.func_70671_ap().func_75650_a((double)this.field_179494_b.func_177958_n() + 0.5D, (double)(this.field_179494_b.func_177956_o() + 1), (double)this.field_179494_b.func_177952_p() + 0.5D, 10.0F, (float)this.field_179500_c.func_70646_bf());
         if(this.func_179487_f()) {
            World world = this.field_179500_c.field_70170_p;
            BlockPos blockpos = this.field_179494_b.func_177984_a();
            IBlockState iblockstate = world.func_180495_p(blockpos);
            Block block = iblockstate.func_177230_c();
            if(this.field_179499_e && block instanceof BlockCarrot && ((Integer)iblockstate.func_177229_b(BlockCarrot.field_176488_a)).intValue() == 7) {
               world.func_180501_a(blockpos, Blocks.field_150350_a.func_176223_P(), 2);
               world.func_175655_b(blockpos, true);
               this.field_179500_c.func_175528_cn();
            }

            this.field_179499_e = false;
            this.field_179496_a = 10;
         }

      }

      protected boolean func_179488_a(World p_179488_1_, BlockPos p_179488_2_) {
         Block block = p_179488_1_.func_180495_p(p_179488_2_).func_177230_c();
         if(block == Blocks.field_150458_ak) {
            p_179488_2_ = p_179488_2_.func_177984_a();
            IBlockState iblockstate = p_179488_1_.func_180495_p(p_179488_2_);
            block = iblockstate.func_177230_c();
            if(block instanceof BlockCarrot && ((Integer)iblockstate.func_177229_b(BlockCarrot.field_176488_a)).intValue() == 7 && this.field_179498_d && !this.field_179499_e) {
               this.field_179499_e = true;
               return true;
            }
         }

         return false;
      }
   }

   static enum EnumMoveType {
      NONE(0.0F, 0.0F, 30, 1),
      HOP(0.8F, 0.2F, 20, 10),
      STEP(1.0F, 0.45F, 14, 14),
      SPRINT(1.75F, 0.4F, 1, 8),
      ATTACK(2.0F, 0.7F, 7, 8);

      private final float field_180076_f;
      private final float field_180077_g;
      private final int field_180084_h;
      private final int field_180085_i;

      private EnumMoveType(float p_i45866_3_, float p_i45866_4_, int p_i45866_5_, int p_i45866_6_) {
         this.field_180076_f = p_i45866_3_;
         this.field_180077_g = p_i45866_4_;
         this.field_180084_h = p_i45866_5_;
         this.field_180085_i = p_i45866_6_;
      }

      public float func_180072_a() {
         return this.field_180076_f;
      }

      public float func_180074_b() {
         return this.field_180077_g;
      }

      public int func_180075_c() {
         return this.field_180084_h;
      }

      public int func_180073_d() {
         return this.field_180085_i;
      }
   }

   public class RabbitJumpHelper extends EntityJumpHelper {
      private EntityRabbit field_180070_c;
      private boolean field_180068_d = false;

      public RabbitJumpHelper(EntityRabbit p_i45863_2_) {
         super(p_i45863_2_);
         this.field_180070_c = p_i45863_2_;
      }

      public boolean func_180067_c() {
         return this.field_75662_b;
      }

      public boolean func_180065_d() {
         return this.field_180068_d;
      }

      public void func_180066_a(boolean p_180066_1_) {
         this.field_180068_d = p_180066_1_;
      }

      public void func_75661_b() {
         if(this.field_75662_b) {
            this.field_180070_c.func_175524_b(EntityRabbit.EnumMoveType.STEP);
            this.field_75662_b = false;
         }

      }
   }

   static class RabbitMoveHelper extends EntityMoveHelper {
      private EntityRabbit field_179929_g;

      public RabbitMoveHelper(EntityRabbit p_i45862_1_) {
         super(p_i45862_1_);
         this.field_179929_g = p_i45862_1_;
      }

      public void func_75641_c() {
         if(this.field_179929_g.field_70122_E && !this.field_179929_g.func_175523_cj()) {
            this.field_179929_g.func_175515_b(0.0D);
         }

         super.func_75641_c();
      }
   }

   public static class RabbitTypeData implements IEntityLivingData {
      public int field_179427_a;

      public RabbitTypeData(int p_i45864_1_) {
         this.field_179427_a = p_i45864_1_;
      }
   }
}
