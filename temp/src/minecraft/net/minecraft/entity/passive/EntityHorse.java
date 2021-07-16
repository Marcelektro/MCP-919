package net.minecraft.entity.passive;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityHorse extends EntityAnimal implements IInvBasic {
   private static final Predicate<Entity> field_110276_bu = new Predicate<Entity>() {
      public boolean apply(Entity p_apply_1_) {
         return p_apply_1_ instanceof EntityHorse && ((EntityHorse)p_apply_1_).func_110205_ce();
      }
   };
   private static final IAttribute field_110271_bv = (new RangedAttribute((IAttribute)null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump Strength").func_111112_a(true);
   private static final String[] field_110270_bw = new String[]{null, "textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse/armor/horse_armor_diamond.png"};
   private static final String[] field_110273_bx = new String[]{"", "meo", "goo", "dio"};
   private static final int[] field_110272_by = new int[]{0, 5, 7, 11};
   private static final String[] field_110268_bz = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
   private static final String[] field_110269_bA = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
   private static final String[] field_110291_bB = new String[]{null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
   private static final String[] field_110292_bC = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
   private int field_110289_bD;
   private int field_110290_bE;
   private int field_110295_bF;
   public int field_110278_bp;
   public int field_110279_bq;
   protected boolean field_110275_br;
   private AnimalChest field_110296_bG;
   private boolean field_110293_bH;
   protected int field_110274_bs;
   protected float field_110277_bt;
   private boolean field_110294_bI;
   private float field_110283_bJ;
   private float field_110284_bK;
   private float field_110281_bL;
   private float field_110282_bM;
   private float field_110287_bN;
   private float field_110288_bO;
   private int field_110285_bP;
   private String field_110286_bQ;
   private String[] field_110280_bR = new String[3];
   private boolean field_175508_bO = false;

   public EntityHorse(World p_i1685_1_) {
      super(p_i1685_1_);
      this.func_70105_a(1.4F, 1.6F);
      this.field_70178_ae = false;
      this.func_110207_m(false);
      ((PathNavigateGround)this.func_70661_as()).func_179690_a(true);
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.2D));
      this.field_70714_bg.func_75776_a(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
      this.field_70714_bg.func_75776_a(2, new EntityAIMate(this, 1.0D));
      this.field_70714_bg.func_75776_a(4, new EntityAIFollowParent(this, 1.0D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 0.7D));
      this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
      this.func_110226_cD();
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, Integer.valueOf(0));
      this.field_70180_af.func_75682_a(19, Byte.valueOf((byte)0));
      this.field_70180_af.func_75682_a(20, Integer.valueOf(0));
      this.field_70180_af.func_75682_a(21, String.valueOf((Object)""));
      this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
   }

   public void func_110214_p(int p_110214_1_) {
      this.field_70180_af.func_75692_b(19, Byte.valueOf((byte)p_110214_1_));
      this.func_110230_cF();
   }

   public int func_110265_bP() {
      return this.field_70180_af.func_75683_a(19);
   }

   public void func_110235_q(int p_110235_1_) {
      this.field_70180_af.func_75692_b(20, Integer.valueOf(p_110235_1_));
      this.func_110230_cF();
   }

   public int func_110202_bQ() {
      return this.field_70180_af.func_75679_c(20);
   }

   public String func_70005_c_() {
      if(this.func_145818_k_()) {
         return this.func_95999_t();
      } else {
         int i = this.func_110265_bP();
         switch(i) {
         case 0:
         default:
            return StatCollector.func_74838_a("entity.horse.name");
         case 1:
            return StatCollector.func_74838_a("entity.donkey.name");
         case 2:
            return StatCollector.func_74838_a("entity.mule.name");
         case 3:
            return StatCollector.func_74838_a("entity.zombiehorse.name");
         case 4:
            return StatCollector.func_74838_a("entity.skeletonhorse.name");
         }
      }
   }

   private boolean func_110233_w(int p_110233_1_) {
      return (this.field_70180_af.func_75679_c(16) & p_110233_1_) != 0;
   }

   private void func_110208_b(int p_110208_1_, boolean p_110208_2_) {
      int i = this.field_70180_af.func_75679_c(16);
      if(p_110208_2_) {
         this.field_70180_af.func_75692_b(16, Integer.valueOf(i | p_110208_1_));
      } else {
         this.field_70180_af.func_75692_b(16, Integer.valueOf(i & ~p_110208_1_));
      }

   }

   public boolean func_110228_bR() {
      return !this.func_70631_g_();
   }

   public boolean func_110248_bS() {
      return this.func_110233_w(2);
   }

   public boolean func_110253_bW() {
      return this.func_110228_bR();
   }

   public String func_152119_ch() {
      return this.field_70180_af.func_75681_e(21);
   }

   public void func_152120_b(String p_152120_1_) {
      this.field_70180_af.func_75692_b(21, p_152120_1_);
   }

   public float func_110254_bY() {
      return 0.5F;
   }

   public void func_98054_a(boolean p_98054_1_) {
      if(p_98054_1_) {
         this.func_98055_j(this.func_110254_bY());
      } else {
         this.func_98055_j(1.0F);
      }

   }

   public boolean func_110246_bZ() {
      return this.field_110275_br;
   }

   public void func_110234_j(boolean p_110234_1_) {
      this.func_110208_b(2, p_110234_1_);
   }

   public void func_110255_k(boolean p_110255_1_) {
      this.field_110275_br = p_110255_1_;
   }

   public boolean func_110164_bC() {
      return !this.func_110256_cu() && super.func_110164_bC();
   }

   protected void func_142017_o(float p_142017_1_) {
      if(p_142017_1_ > 6.0F && this.func_110204_cc()) {
         this.func_110227_p(false);
      }

   }

   public boolean func_110261_ca() {
      return this.func_110233_w(8);
   }

   public int func_110241_cb() {
      return this.field_70180_af.func_75679_c(22);
   }

   private int func_110260_d(ItemStack p_110260_1_) {
      if(p_110260_1_ == null) {
         return 0;
      } else {
         Item item = p_110260_1_.func_77973_b();
         return item == Items.field_151138_bX?1:(item == Items.field_151136_bY?2:(item == Items.field_151125_bZ?3:0));
      }
   }

   public boolean func_110204_cc() {
      return this.func_110233_w(32);
   }

   public boolean func_110209_cd() {
      return this.func_110233_w(64);
   }

   public boolean func_110205_ce() {
      return this.func_110233_w(16);
   }

   public boolean func_110243_cf() {
      return this.field_110293_bH;
   }

   public void func_146086_d(ItemStack p_146086_1_) {
      this.field_70180_af.func_75692_b(22, Integer.valueOf(this.func_110260_d(p_146086_1_)));
      this.func_110230_cF();
   }

   public void func_110242_l(boolean p_110242_1_) {
      this.func_110208_b(16, p_110242_1_);
   }

   public void func_110207_m(boolean p_110207_1_) {
      this.func_110208_b(8, p_110207_1_);
   }

   public void func_110221_n(boolean p_110221_1_) {
      this.field_110293_bH = p_110221_1_;
   }

   public void func_110251_o(boolean p_110251_1_) {
      this.func_110208_b(4, p_110251_1_);
   }

   public int func_110252_cg() {
      return this.field_110274_bs;
   }

   public void func_110238_s(int p_110238_1_) {
      this.field_110274_bs = p_110238_1_;
   }

   public int func_110198_t(int p_110198_1_) {
      int i = MathHelper.func_76125_a(this.func_110252_cg() + p_110198_1_, 0, this.func_110218_cm());
      this.func_110238_s(i);
      return i;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      Entity entity = p_70097_1_.func_76346_g();
      return this.field_70153_n != null && this.field_70153_n.equals(entity)?false:super.func_70097_a(p_70097_1_, p_70097_2_);
   }

   public int func_70658_aO() {
      return field_110272_by[this.func_110241_cb()];
   }

   public boolean func_70104_M() {
      return this.field_70153_n == null;
   }

   public boolean func_110262_ch() {
      int i = MathHelper.func_76128_c(this.field_70165_t);
      int j = MathHelper.func_76128_c(this.field_70161_v);
      this.field_70170_p.func_180494_b(new BlockPos(i, 0, j));
      return true;
   }

   public void func_110224_ci() {
      if(!this.field_70170_p.field_72995_K && this.func_110261_ca()) {
         this.func_145779_a(Item.func_150898_a(Blocks.field_150486_ae), 1);
         this.func_110207_m(false);
      }
   }

   private void func_110266_cB() {
      this.func_110249_cI();
      if(!this.func_174814_R()) {
         this.field_70170_p.func_72956_a(this, "eating", 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
      }

   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
      if(p_180430_1_ > 1.0F) {
         this.func_85030_a("mob.horse.land", 0.4F, 1.0F);
      }

      int i = MathHelper.func_76123_f((p_180430_1_ * 0.5F - 3.0F) * p_180430_2_);
      if(i > 0) {
         this.func_70097_a(DamageSource.field_76379_h, (float)i);
         if(this.field_70153_n != null) {
            this.field_70153_n.func_70097_a(DamageSource.field_76379_h, (float)i);
         }

         Block block = this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t, this.field_70163_u - 0.2D - (double)this.field_70126_B, this.field_70161_v)).func_177230_c();
         if(block.func_149688_o() != Material.field_151579_a && !this.func_174814_R()) {
            Block.SoundType block$soundtype = block.field_149762_H;
            this.field_70170_p.func_72956_a(this, block$soundtype.func_150498_e(), block$soundtype.func_150497_c() * 0.5F, block$soundtype.func_150494_d() * 0.75F);
         }

      }
   }

   private int func_110225_cC() {
      int i = this.func_110265_bP();
      return !this.func_110261_ca() || i != 1 && i != 2?2:17;
   }

   private void func_110226_cD() {
      AnimalChest animalchest = this.field_110296_bG;
      this.field_110296_bG = new AnimalChest("HorseChest", this.func_110225_cC());
      this.field_110296_bG.func_110133_a(this.func_70005_c_());
      if(animalchest != null) {
         animalchest.func_110132_b(this);
         int i = Math.min(animalchest.func_70302_i_(), this.field_110296_bG.func_70302_i_());

         for(int j = 0; j < i; ++j) {
            ItemStack itemstack = animalchest.func_70301_a(j);
            if(itemstack != null) {
               this.field_110296_bG.func_70299_a(j, itemstack.func_77946_l());
            }
         }
      }

      this.field_110296_bG.func_110134_a(this);
      this.func_110232_cE();
   }

   private void func_110232_cE() {
      if(!this.field_70170_p.field_72995_K) {
         this.func_110251_o(this.field_110296_bG.func_70301_a(0) != null);
         if(this.func_110259_cr()) {
            this.func_146086_d(this.field_110296_bG.func_70301_a(1));
         }
      }

   }

   public void func_76316_a(InventoryBasic p_76316_1_) {
      int i = this.func_110241_cb();
      boolean flag = this.func_110257_ck();
      this.func_110232_cE();
      if(this.field_70173_aa > 20) {
         if(i == 0 && i != this.func_110241_cb()) {
            this.func_85030_a("mob.horse.armor", 0.5F, 1.0F);
         } else if(i != this.func_110241_cb()) {
            this.func_85030_a("mob.horse.armor", 0.5F, 1.0F);
         }

         if(!flag && this.func_110257_ck()) {
            this.func_85030_a("mob.horse.leather", 0.5F, 1.0F);
         }
      }

   }

   public boolean func_70601_bi() {
      this.func_110262_ch();
      return super.func_70601_bi();
   }

   protected EntityHorse func_110250_a(Entity p_110250_1_, double p_110250_2_) {
      double d0 = Double.MAX_VALUE;
      Entity entity = null;

      for(Entity entity1 : this.field_70170_p.func_175674_a(p_110250_1_, p_110250_1_.func_174813_aQ().func_72321_a(p_110250_2_, p_110250_2_, p_110250_2_), field_110276_bu)) {
         double d1 = entity1.func_70092_e(p_110250_1_.field_70165_t, p_110250_1_.field_70163_u, p_110250_1_.field_70161_v);
         if(d1 < d0) {
            entity = entity1;
            d0 = d1;
         }
      }

      return (EntityHorse)entity;
   }

   public double func_110215_cj() {
      return this.func_110148_a(field_110271_bv).func_111126_e();
   }

   protected String func_70673_aS() {
      this.func_110249_cI();
      int i = this.func_110265_bP();
      return i == 3?"mob.horse.zombie.death":(i == 4?"mob.horse.skeleton.death":(i != 1 && i != 2?"mob.horse.death":"mob.horse.donkey.death"));
   }

   protected Item func_146068_u() {
      boolean flag = this.field_70146_Z.nextInt(4) == 0;
      int i = this.func_110265_bP();
      return i == 4?Items.field_151103_aS:(i == 3?(flag?null:Items.field_151078_bh):Items.field_151116_aA);
   }

   protected String func_70621_aR() {
      this.func_110249_cI();
      if(this.field_70146_Z.nextInt(3) == 0) {
         this.func_110220_cK();
      }

      int i = this.func_110265_bP();
      return i == 3?"mob.horse.zombie.hit":(i == 4?"mob.horse.skeleton.hit":(i != 1 && i != 2?"mob.horse.hit":"mob.horse.donkey.hit"));
   }

   public boolean func_110257_ck() {
      return this.func_110233_w(4);
   }

   protected String func_70639_aQ() {
      this.func_110249_cI();
      if(this.field_70146_Z.nextInt(10) == 0 && !this.func_70610_aX()) {
         this.func_110220_cK();
      }

      int i = this.func_110265_bP();
      return i == 3?"mob.horse.zombie.idle":(i == 4?"mob.horse.skeleton.idle":(i != 1 && i != 2?"mob.horse.idle":"mob.horse.donkey.idle"));
   }

   protected String func_110217_cl() {
      this.func_110249_cI();
      this.func_110220_cK();
      int i = this.func_110265_bP();
      return i != 3 && i != 4?(i != 1 && i != 2?"mob.horse.angry":"mob.horse.donkey.angry"):null;
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      Block.SoundType block$soundtype = p_180429_2_.field_149762_H;
      if(this.field_70170_p.func_180495_p(p_180429_1_.func_177984_a()).func_177230_c() == Blocks.field_150431_aC) {
         block$soundtype = Blocks.field_150431_aC.field_149762_H;
      }

      if(!p_180429_2_.func_149688_o().func_76224_d()) {
         int i = this.func_110265_bP();
         if(this.field_70153_n != null && i != 1 && i != 2) {
            ++this.field_110285_bP;
            if(this.field_110285_bP > 5 && this.field_110285_bP % 3 == 0) {
               this.func_85030_a("mob.horse.gallop", block$soundtype.func_150497_c() * 0.15F, block$soundtype.func_150494_d());
               if(i == 0 && this.field_70146_Z.nextInt(10) == 0) {
                  this.func_85030_a("mob.horse.breathe", block$soundtype.func_150497_c() * 0.6F, block$soundtype.func_150494_d());
               }
            } else if(this.field_110285_bP <= 5) {
               this.func_85030_a("mob.horse.wood", block$soundtype.func_150497_c() * 0.15F, block$soundtype.func_150494_d());
            }
         } else if(block$soundtype == Block.field_149766_f) {
            this.func_85030_a("mob.horse.wood", block$soundtype.func_150497_c() * 0.15F, block$soundtype.func_150494_d());
         } else {
            this.func_85030_a("mob.horse.soft", block$soundtype.func_150497_c() * 0.15F, block$soundtype.func_150494_d());
         }
      }

   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110140_aT().func_111150_b(field_110271_bv);
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(53.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22499999403953552D);
   }

   public int func_70641_bl() {
      return 6;
   }

   public int func_110218_cm() {
      return 100;
   }

   protected float func_70599_aP() {
      return 0.8F;
   }

   public int func_70627_aG() {
      return 400;
   }

   public boolean func_110239_cn() {
      return this.func_110265_bP() == 0 || this.func_110241_cb() > 0;
   }

   private void func_110230_cF() {
      this.field_110286_bQ = null;
   }

   public boolean func_175507_cI() {
      return this.field_175508_bO;
   }

   private void func_110247_cG() {
      this.field_110286_bQ = "horse/";
      this.field_110280_bR[0] = null;
      this.field_110280_bR[1] = null;
      this.field_110280_bR[2] = null;
      int i = this.func_110265_bP();
      int j = this.func_110202_bQ();
      if(i == 0) {
         int k = j & 255;
         int l = (j & '\uff00') >> 8;
         if(k >= field_110268_bz.length) {
            this.field_175508_bO = false;
            return;
         }

         this.field_110280_bR[0] = field_110268_bz[k];
         this.field_110286_bQ = this.field_110286_bQ + field_110269_bA[k];
         if(l >= field_110291_bB.length) {
            this.field_175508_bO = false;
            return;
         }

         this.field_110280_bR[1] = field_110291_bB[l];
         this.field_110286_bQ = this.field_110286_bQ + field_110292_bC[l];
      } else {
         this.field_110280_bR[0] = "";
         this.field_110286_bQ = this.field_110286_bQ + "_" + i + "_";
      }

      int i1 = this.func_110241_cb();
      if(i1 >= field_110270_bw.length) {
         this.field_175508_bO = false;
      } else {
         this.field_110280_bR[2] = field_110270_bw[i1];
         this.field_110286_bQ = this.field_110286_bQ + field_110273_bx[i1];
         this.field_175508_bO = true;
      }
   }

   public String func_110264_co() {
      if(this.field_110286_bQ == null) {
         this.func_110247_cG();
      }

      return this.field_110286_bQ;
   }

   public String[] func_110212_cp() {
      if(this.field_110286_bQ == null) {
         this.func_110247_cG();
      }

      return this.field_110280_bR;
   }

   public void func_110199_f(EntityPlayer p_110199_1_) {
      if(!this.field_70170_p.field_72995_K && (this.field_70153_n == null || this.field_70153_n == p_110199_1_) && this.func_110248_bS()) {
         this.field_110296_bG.func_110133_a(this.func_70005_c_());
         p_110199_1_.func_110298_a(this, this.field_110296_bG);
      }

   }

   public boolean func_70085_c(EntityPlayer p_70085_1_) {
      ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
      if(itemstack != null && itemstack.func_77973_b() == Items.field_151063_bx) {
         return super.func_70085_c(p_70085_1_);
      } else if(!this.func_110248_bS() && this.func_110256_cu()) {
         return false;
      } else if(this.func_110248_bS() && this.func_110228_bR() && p_70085_1_.func_70093_af()) {
         this.func_110199_f(p_70085_1_);
         return true;
      } else if(this.func_110253_bW() && this.field_70153_n != null) {
         return super.func_70085_c(p_70085_1_);
      } else {
         if(itemstack != null) {
            boolean flag = false;
            if(this.func_110259_cr()) {
               int i = -1;
               if(itemstack.func_77973_b() == Items.field_151138_bX) {
                  i = 1;
               } else if(itemstack.func_77973_b() == Items.field_151136_bY) {
                  i = 2;
               } else if(itemstack.func_77973_b() == Items.field_151125_bZ) {
                  i = 3;
               }

               if(i >= 0) {
                  if(!this.func_110248_bS()) {
                     this.func_110231_cz();
                     return true;
                  }

                  this.func_110199_f(p_70085_1_);
                  return true;
               }
            }

            if(!flag && !this.func_110256_cu()) {
               float f = 0.0F;
               int j = 0;
               int k = 0;
               if(itemstack.func_77973_b() == Items.field_151015_O) {
                  f = 2.0F;
                  j = 20;
                  k = 3;
               } else if(itemstack.func_77973_b() == Items.field_151102_aT) {
                  f = 1.0F;
                  j = 30;
                  k = 3;
               } else if(Block.func_149634_a(itemstack.func_77973_b()) == Blocks.field_150407_cf) {
                  f = 20.0F;
                  j = 180;
               } else if(itemstack.func_77973_b() == Items.field_151034_e) {
                  f = 3.0F;
                  j = 60;
                  k = 3;
               } else if(itemstack.func_77973_b() == Items.field_151150_bK) {
                  f = 4.0F;
                  j = 60;
                  k = 5;
                  if(this.func_110248_bS() && this.func_70874_b() == 0) {
                     flag = true;
                     this.func_146082_f(p_70085_1_);
                  }
               } else if(itemstack.func_77973_b() == Items.field_151153_ao) {
                  f = 10.0F;
                  j = 240;
                  k = 10;
                  if(this.func_110248_bS() && this.func_70874_b() == 0) {
                     flag = true;
                     this.func_146082_f(p_70085_1_);
                  }
               }

               if(this.func_110143_aJ() < this.func_110138_aP() && f > 0.0F) {
                  this.func_70691_i(f);
                  flag = true;
               }

               if(!this.func_110228_bR() && j > 0) {
                  this.func_110195_a(j);
                  flag = true;
               }

               if(k > 0 && (flag || !this.func_110248_bS()) && k < this.func_110218_cm()) {
                  flag = true;
                  this.func_110198_t(k);
               }

               if(flag) {
                  this.func_110266_cB();
               }
            }

            if(!this.func_110248_bS() && !flag) {
               if(itemstack != null && itemstack.func_111282_a(p_70085_1_, this)) {
                  return true;
               }

               this.func_110231_cz();
               return true;
            }

            if(!flag && this.func_110229_cs() && !this.func_110261_ca() && itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150486_ae)) {
               this.func_110207_m(true);
               this.func_85030_a("mob.chickenplop", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
               flag = true;
               this.func_110226_cD();
            }

            if(!flag && this.func_110253_bW() && !this.func_110257_ck() && itemstack.func_77973_b() == Items.field_151141_av) {
               this.func_110199_f(p_70085_1_);
               return true;
            }

            if(flag) {
               if(!p_70085_1_.field_71075_bZ.field_75098_d && --itemstack.field_77994_a == 0) {
                  p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
               }

               return true;
            }
         }

         if(this.func_110253_bW() && this.field_70153_n == null) {
            if(itemstack != null && itemstack.func_111282_a(p_70085_1_, this)) {
               return true;
            } else {
               this.func_110237_h(p_70085_1_);
               return true;
            }
         } else {
            return super.func_70085_c(p_70085_1_);
         }
      }
   }

   private void func_110237_h(EntityPlayer p_110237_1_) {
      p_110237_1_.field_70177_z = this.field_70177_z;
      p_110237_1_.field_70125_A = this.field_70125_A;
      this.func_110227_p(false);
      this.func_110219_q(false);
      if(!this.field_70170_p.field_72995_K) {
         p_110237_1_.func_70078_a(this);
      }

   }

   public boolean func_110259_cr() {
      return this.func_110265_bP() == 0;
   }

   public boolean func_110229_cs() {
      int i = this.func_110265_bP();
      return i == 2 || i == 1;
   }

   protected boolean func_70610_aX() {
      return this.field_70153_n != null && this.func_110257_ck()?true:this.func_110204_cc() || this.func_110209_cd();
   }

   public boolean func_110256_cu() {
      int i = this.func_110265_bP();
      return i == 3 || i == 4;
   }

   public boolean func_110222_cv() {
      return this.func_110256_cu() || this.func_110265_bP() == 2;
   }

   public boolean func_70877_b(ItemStack p_70877_1_) {
      return false;
   }

   private void func_110210_cH() {
      this.field_110278_bp = 1;
   }

   public void func_70645_a(DamageSource p_70645_1_) {
      super.func_70645_a(p_70645_1_);
      if(!this.field_70170_p.field_72995_K) {
         this.func_110244_cA();
      }

   }

   public void func_70636_d() {
      if(this.field_70146_Z.nextInt(200) == 0) {
         this.func_110210_cH();
      }

      super.func_70636_d();
      if(!this.field_70170_p.field_72995_K) {
         if(this.field_70146_Z.nextInt(900) == 0 && this.field_70725_aQ == 0) {
            this.func_70691_i(1.0F);
         }

         if(!this.func_110204_cc() && this.field_70153_n == null && this.field_70146_Z.nextInt(300) == 0 && this.field_70170_p.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u) - 1, MathHelper.func_76128_c(this.field_70161_v))).func_177230_c() == Blocks.field_150349_c) {
            this.func_110227_p(true);
         }

         if(this.func_110204_cc() && ++this.field_110289_bD > 50) {
            this.field_110289_bD = 0;
            this.func_110227_p(false);
         }

         if(this.func_110205_ce() && !this.func_110228_bR() && !this.func_110204_cc()) {
            EntityHorse entityhorse = this.func_110250_a(this, 16.0D);
            if(entityhorse != null && this.func_70068_e(entityhorse) > 4.0D) {
               this.field_70699_by.func_75494_a(entityhorse);
            }
         }
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_70170_p.field_72995_K && this.field_70180_af.func_75684_a()) {
         this.field_70180_af.func_111144_e();
         this.func_110230_cF();
      }

      if(this.field_110290_bE > 0 && ++this.field_110290_bE > 30) {
         this.field_110290_bE = 0;
         this.func_110208_b(128, false);
      }

      if(!this.field_70170_p.field_72995_K && this.field_110295_bF > 0 && ++this.field_110295_bF > 20) {
         this.field_110295_bF = 0;
         this.func_110219_q(false);
      }

      if(this.field_110278_bp > 0 && ++this.field_110278_bp > 8) {
         this.field_110278_bp = 0;
      }

      if(this.field_110279_bq > 0) {
         ++this.field_110279_bq;
         if(this.field_110279_bq > 300) {
            this.field_110279_bq = 0;
         }
      }

      this.field_110284_bK = this.field_110283_bJ;
      if(this.func_110204_cc()) {
         this.field_110283_bJ += (1.0F - this.field_110283_bJ) * 0.4F + 0.05F;
         if(this.field_110283_bJ > 1.0F) {
            this.field_110283_bJ = 1.0F;
         }
      } else {
         this.field_110283_bJ += (0.0F - this.field_110283_bJ) * 0.4F - 0.05F;
         if(this.field_110283_bJ < 0.0F) {
            this.field_110283_bJ = 0.0F;
         }
      }

      this.field_110282_bM = this.field_110281_bL;
      if(this.func_110209_cd()) {
         this.field_110284_bK = this.field_110283_bJ = 0.0F;
         this.field_110281_bL += (1.0F - this.field_110281_bL) * 0.4F + 0.05F;
         if(this.field_110281_bL > 1.0F) {
            this.field_110281_bL = 1.0F;
         }
      } else {
         this.field_110294_bI = false;
         this.field_110281_bL += (0.8F * this.field_110281_bL * this.field_110281_bL * this.field_110281_bL - this.field_110281_bL) * 0.6F - 0.05F;
         if(this.field_110281_bL < 0.0F) {
            this.field_110281_bL = 0.0F;
         }
      }

      this.field_110288_bO = this.field_110287_bN;
      if(this.func_110233_w(128)) {
         this.field_110287_bN += (1.0F - this.field_110287_bN) * 0.7F + 0.05F;
         if(this.field_110287_bN > 1.0F) {
            this.field_110287_bN = 1.0F;
         }
      } else {
         this.field_110287_bN += (0.0F - this.field_110287_bN) * 0.7F - 0.05F;
         if(this.field_110287_bN < 0.0F) {
            this.field_110287_bN = 0.0F;
         }
      }

   }

   private void func_110249_cI() {
      if(!this.field_70170_p.field_72995_K) {
         this.field_110290_bE = 1;
         this.func_110208_b(128, true);
      }

   }

   private boolean func_110200_cJ() {
      return this.field_70153_n == null && this.field_70154_o == null && this.func_110248_bS() && this.func_110228_bR() && !this.func_110222_cv() && this.func_110143_aJ() >= this.func_110138_aP() && this.func_70880_s();
   }

   public void func_70019_c(boolean p_70019_1_) {
      this.func_110208_b(32, p_70019_1_);
   }

   public void func_110227_p(boolean p_110227_1_) {
      this.func_70019_c(p_110227_1_);
   }

   public void func_110219_q(boolean p_110219_1_) {
      if(p_110219_1_) {
         this.func_110227_p(false);
      }

      this.func_110208_b(64, p_110219_1_);
   }

   private void func_110220_cK() {
      if(!this.field_70170_p.field_72995_K) {
         this.field_110295_bF = 1;
         this.func_110219_q(true);
      }

   }

   public void func_110231_cz() {
      this.func_110220_cK();
      String s = this.func_110217_cl();
      if(s != null) {
         this.func_85030_a(s, this.func_70599_aP(), this.func_70647_i());
      }

   }

   public void func_110244_cA() {
      this.func_110240_a(this, this.field_110296_bG);
      this.func_110224_ci();
   }

   private void func_110240_a(Entity p_110240_1_, AnimalChest p_110240_2_) {
      if(p_110240_2_ != null && !this.field_70170_p.field_72995_K) {
         for(int i = 0; i < p_110240_2_.func_70302_i_(); ++i) {
            ItemStack itemstack = p_110240_2_.func_70301_a(i);
            if(itemstack != null) {
               this.func_70099_a(itemstack, 0.0F);
            }
         }

      }
   }

   public boolean func_110263_g(EntityPlayer p_110263_1_) {
      this.func_152120_b(p_110263_1_.func_110124_au().toString());
      this.func_110234_j(true);
      return true;
   }

   public void func_70612_e(float p_70612_1_, float p_70612_2_) {
      if(this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase && this.func_110257_ck()) {
         this.field_70126_B = this.field_70177_z = this.field_70153_n.field_70177_z;
         this.field_70125_A = this.field_70153_n.field_70125_A * 0.5F;
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
         this.field_70759_as = this.field_70761_aq = this.field_70177_z;
         p_70612_1_ = ((EntityLivingBase)this.field_70153_n).field_70702_br * 0.5F;
         p_70612_2_ = ((EntityLivingBase)this.field_70153_n).field_70701_bs;
         if(p_70612_2_ <= 0.0F) {
            p_70612_2_ *= 0.25F;
            this.field_110285_bP = 0;
         }

         if(this.field_70122_E && this.field_110277_bt == 0.0F && this.func_110209_cd() && !this.field_110294_bI) {
            p_70612_1_ = 0.0F;
            p_70612_2_ = 0.0F;
         }

         if(this.field_110277_bt > 0.0F && !this.func_110246_bZ() && this.field_70122_E) {
            this.field_70181_x = this.func_110215_cj() * (double)this.field_110277_bt;
            if(this.func_70644_a(Potion.field_76430_j)) {
               this.field_70181_x += (double)((float)(this.func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1F);
            }

            this.func_110255_k(true);
            this.field_70160_al = true;
            if(p_70612_2_ > 0.0F) {
               float f = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
               float f1 = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
               this.field_70159_w += (double)(-0.4F * f * this.field_110277_bt);
               this.field_70179_y += (double)(0.4F * f1 * this.field_110277_bt);
               this.func_85030_a("mob.horse.jump", 0.4F, 1.0F);
            }

            this.field_110277_bt = 0.0F;
         }

         this.field_70138_W = 1.0F;
         this.field_70747_aH = this.func_70689_ay() * 0.1F;
         if(!this.field_70170_p.field_72995_K) {
            this.func_70659_e((float)this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
            super.func_70612_e(p_70612_1_, p_70612_2_);
         }

         if(this.field_70122_E) {
            this.field_110277_bt = 0.0F;
            this.func_110255_k(false);
         }

         this.field_70722_aY = this.field_70721_aZ;
         double d1 = this.field_70165_t - this.field_70169_q;
         double d0 = this.field_70161_v - this.field_70166_s;
         float f2 = MathHelper.func_76133_a(d1 * d1 + d0 * d0) * 4.0F;
         if(f2 > 1.0F) {
            f2 = 1.0F;
         }

         this.field_70721_aZ += (f2 - this.field_70721_aZ) * 0.4F;
         this.field_70754_ba += this.field_70721_aZ;
      } else {
         this.field_70138_W = 0.5F;
         this.field_70747_aH = 0.02F;
         super.func_70612_e(p_70612_1_, p_70612_2_);
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("EatingHaystack", this.func_110204_cc());
      p_70014_1_.func_74757_a("ChestedHorse", this.func_110261_ca());
      p_70014_1_.func_74757_a("HasReproduced", this.func_110243_cf());
      p_70014_1_.func_74757_a("Bred", this.func_110205_ce());
      p_70014_1_.func_74768_a("Type", this.func_110265_bP());
      p_70014_1_.func_74768_a("Variant", this.func_110202_bQ());
      p_70014_1_.func_74768_a("Temper", this.func_110252_cg());
      p_70014_1_.func_74757_a("Tame", this.func_110248_bS());
      p_70014_1_.func_74778_a("OwnerUUID", this.func_152119_ch());
      if(this.func_110261_ca()) {
         NBTTagList nbttaglist = new NBTTagList();

         for(int i = 2; i < this.field_110296_bG.func_70302_i_(); ++i) {
            ItemStack itemstack = this.field_110296_bG.func_70301_a(i);
            if(itemstack != null) {
               NBTTagCompound nbttagcompound = new NBTTagCompound();
               nbttagcompound.func_74774_a("Slot", (byte)i);
               itemstack.func_77955_b(nbttagcompound);
               nbttaglist.func_74742_a(nbttagcompound);
            }
         }

         p_70014_1_.func_74782_a("Items", nbttaglist);
      }

      if(this.field_110296_bG.func_70301_a(1) != null) {
         p_70014_1_.func_74782_a("ArmorItem", this.field_110296_bG.func_70301_a(1).func_77955_b(new NBTTagCompound()));
      }

      if(this.field_110296_bG.func_70301_a(0) != null) {
         p_70014_1_.func_74782_a("SaddleItem", this.field_110296_bG.func_70301_a(0).func_77955_b(new NBTTagCompound()));
      }

   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_110227_p(p_70037_1_.func_74767_n("EatingHaystack"));
      this.func_110242_l(p_70037_1_.func_74767_n("Bred"));
      this.func_110207_m(p_70037_1_.func_74767_n("ChestedHorse"));
      this.func_110221_n(p_70037_1_.func_74767_n("HasReproduced"));
      this.func_110214_p(p_70037_1_.func_74762_e("Type"));
      this.func_110235_q(p_70037_1_.func_74762_e("Variant"));
      this.func_110238_s(p_70037_1_.func_74762_e("Temper"));
      this.func_110234_j(p_70037_1_.func_74767_n("Tame"));
      String s = "";
      if(p_70037_1_.func_150297_b("OwnerUUID", 8)) {
         s = p_70037_1_.func_74779_i("OwnerUUID");
      } else {
         String s1 = p_70037_1_.func_74779_i("Owner");
         s = PreYggdrasilConverter.func_152719_a(s1);
      }

      if(s.length() > 0) {
         this.func_152120_b(s);
      }

      IAttributeInstance iattributeinstance = this.func_110140_aT().func_111152_a("Speed");
      if(iattributeinstance != null) {
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(iattributeinstance.func_111125_b() * 0.25D);
      }

      if(this.func_110261_ca()) {
         NBTTagList nbttaglist = p_70037_1_.func_150295_c("Items", 10);
         this.func_110226_cD();

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            int j = nbttagcompound.func_74771_c("Slot") & 255;
            if(j >= 2 && j < this.field_110296_bG.func_70302_i_()) {
               this.field_110296_bG.func_70299_a(j, ItemStack.func_77949_a(nbttagcompound));
            }
         }
      }

      if(p_70037_1_.func_150297_b("ArmorItem", 10)) {
         ItemStack itemstack = ItemStack.func_77949_a(p_70037_1_.func_74775_l("ArmorItem"));
         if(itemstack != null && func_146085_a(itemstack.func_77973_b())) {
            this.field_110296_bG.func_70299_a(1, itemstack);
         }
      }

      if(p_70037_1_.func_150297_b("SaddleItem", 10)) {
         ItemStack itemstack1 = ItemStack.func_77949_a(p_70037_1_.func_74775_l("SaddleItem"));
         if(itemstack1 != null && itemstack1.func_77973_b() == Items.field_151141_av) {
            this.field_110296_bG.func_70299_a(0, itemstack1);
         }
      } else if(p_70037_1_.func_74767_n("Saddle")) {
         this.field_110296_bG.func_70299_a(0, new ItemStack(Items.field_151141_av));
      }

      this.func_110232_cE();
   }

   public boolean func_70878_b(EntityAnimal p_70878_1_) {
      if(p_70878_1_ == this) {
         return false;
      } else if(p_70878_1_.getClass() != this.getClass()) {
         return false;
      } else {
         EntityHorse entityhorse = (EntityHorse)p_70878_1_;
         if(this.func_110200_cJ() && entityhorse.func_110200_cJ()) {
            int i = this.func_110265_bP();
            int j = entityhorse.func_110265_bP();
            return i == j || i == 0 && j == 1 || i == 1 && j == 0;
         } else {
            return false;
         }
      }
   }

   public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
      EntityHorse entityhorse = (EntityHorse)p_90011_1_;
      EntityHorse entityhorse1 = new EntityHorse(this.field_70170_p);
      int i = this.func_110265_bP();
      int j = entityhorse.func_110265_bP();
      int k = 0;
      if(i == j) {
         k = i;
      } else if(i == 0 && j == 1 || i == 1 && j == 0) {
         k = 2;
      }

      if(k == 0) {
         int i1 = this.field_70146_Z.nextInt(9);
         int l;
         if(i1 < 4) {
            l = this.func_110202_bQ() & 255;
         } else if(i1 < 8) {
            l = entityhorse.func_110202_bQ() & 255;
         } else {
            l = this.field_70146_Z.nextInt(7);
         }

         int j1 = this.field_70146_Z.nextInt(5);
         if(j1 < 2) {
            l = l | this.func_110202_bQ() & '\uff00';
         } else if(j1 < 4) {
            l = l | entityhorse.func_110202_bQ() & '\uff00';
         } else {
            l = l | this.field_70146_Z.nextInt(5) << 8 & '\uff00';
         }

         entityhorse1.func_110235_q(l);
      }

      entityhorse1.func_110214_p(k);
      double d1 = this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + p_90011_1_.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + (double)this.func_110267_cL();
      entityhorse1.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(d1 / 3.0D);
      double d2 = this.func_110148_a(field_110271_bv).func_111125_b() + p_90011_1_.func_110148_a(field_110271_bv).func_111125_b() + this.func_110245_cM();
      entityhorse1.func_110148_a(field_110271_bv).func_111128_a(d2 / 3.0D);
      double d0 = this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() + p_90011_1_.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() + this.func_110203_cN();
      entityhorse1.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(d0 / 3.0D);
      return entityhorse1;
   }

   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
      p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
      int i = 0;
      int j = 0;
      if(p_180482_2_ instanceof EntityHorse.GroupData) {
         i = ((EntityHorse.GroupData)p_180482_2_).field_111107_a;
         j = ((EntityHorse.GroupData)p_180482_2_).field_111106_b & 255 | this.field_70146_Z.nextInt(5) << 8;
      } else {
         if(this.field_70146_Z.nextInt(10) == 0) {
            i = 1;
         } else {
            int k = this.field_70146_Z.nextInt(7);
            int l = this.field_70146_Z.nextInt(5);
            i = 0;
            j = k | l << 8;
         }

         p_180482_2_ = new EntityHorse.GroupData(i, j);
      }

      this.func_110214_p(i);
      this.func_110235_q(j);
      if(this.field_70146_Z.nextInt(5) == 0) {
         this.func_70873_a(-24000);
      }

      if(i != 4 && i != 3) {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)this.func_110267_cL());
         if(i == 0) {
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.func_110203_cN());
         } else {
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.17499999701976776D);
         }
      } else {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0D);
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
      }

      if(i != 2 && i != 1) {
         this.func_110148_a(field_110271_bv).func_111128_a(this.func_110245_cM());
      } else {
         this.func_110148_a(field_110271_bv).func_111128_a(0.5D);
      }

      this.func_70606_j(this.func_110138_aP());
      return p_180482_2_;
   }

   public float func_110258_o(float p_110258_1_) {
      return this.field_110284_bK + (this.field_110283_bJ - this.field_110284_bK) * p_110258_1_;
   }

   public float func_110223_p(float p_110223_1_) {
      return this.field_110282_bM + (this.field_110281_bL - this.field_110282_bM) * p_110223_1_;
   }

   public float func_110201_q(float p_110201_1_) {
      return this.field_110288_bO + (this.field_110287_bN - this.field_110288_bO) * p_110201_1_;
   }

   public void func_110206_u(int p_110206_1_) {
      if(this.func_110257_ck()) {
         if(p_110206_1_ < 0) {
            p_110206_1_ = 0;
         } else {
            this.field_110294_bI = true;
            this.func_110220_cK();
         }

         if(p_110206_1_ >= 90) {
            this.field_110277_bt = 1.0F;
         } else {
            this.field_110277_bt = 0.4F + 0.4F * (float)p_110206_1_ / 90.0F;
         }
      }

   }

   protected void func_110216_r(boolean p_110216_1_) {
      EnumParticleTypes enumparticletypes = p_110216_1_?EnumParticleTypes.HEART:EnumParticleTypes.SMOKE_NORMAL;

      for(int i = 0; i < 7; ++i) {
         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
         this.field_70170_p.func_175688_a(enumparticletypes, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, d0, d1, d2, new int[0]);
      }

   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 7) {
         this.func_110216_r(true);
      } else if(p_70103_1_ == 6) {
         this.func_110216_r(false);
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   public void func_70043_V() {
      super.func_70043_V();
      if(this.field_110282_bM > 0.0F) {
         float f = MathHelper.func_76126_a(this.field_70761_aq * 3.1415927F / 180.0F);
         float f1 = MathHelper.func_76134_b(this.field_70761_aq * 3.1415927F / 180.0F);
         float f2 = 0.7F * this.field_110282_bM;
         float f3 = 0.15F * this.field_110282_bM;
         this.field_70153_n.func_70107_b(this.field_70165_t + (double)(f2 * f), this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W() + (double)f3, this.field_70161_v - (double)(f2 * f1));
         if(this.field_70153_n instanceof EntityLivingBase) {
            ((EntityLivingBase)this.field_70153_n).field_70761_aq = this.field_70761_aq;
         }
      }

   }

   private float func_110267_cL() {
      return 15.0F + (float)this.field_70146_Z.nextInt(8) + (float)this.field_70146_Z.nextInt(9);
   }

   private double func_110245_cM() {
      return 0.4000000059604645D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D;
   }

   private double func_110203_cN() {
      return (0.44999998807907104D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D) * 0.25D;
   }

   public static boolean func_146085_a(Item p_146085_0_) {
      return p_146085_0_ == Items.field_151138_bX || p_146085_0_ == Items.field_151136_bY || p_146085_0_ == Items.field_151125_bZ;
   }

   public boolean func_70617_f_() {
      return false;
   }

   public float func_70047_e() {
      return this.field_70131_O;
   }

   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
      if(p_174820_1_ == 499 && this.func_110229_cs()) {
         if(p_174820_2_ == null && this.func_110261_ca()) {
            this.func_110207_m(false);
            this.func_110226_cD();
            return true;
         }

         if(p_174820_2_ != null && p_174820_2_.func_77973_b() == Item.func_150898_a(Blocks.field_150486_ae) && !this.func_110261_ca()) {
            this.func_110207_m(true);
            this.func_110226_cD();
            return true;
         }
      }

      int i = p_174820_1_ - 400;
      if(i >= 0 && i < 2 && i < this.field_110296_bG.func_70302_i_()) {
         if(i == 0 && p_174820_2_ != null && p_174820_2_.func_77973_b() != Items.field_151141_av) {
            return false;
         } else if(i != 1 || (p_174820_2_ == null || func_146085_a(p_174820_2_.func_77973_b())) && this.func_110259_cr()) {
            this.field_110296_bG.func_70299_a(i, p_174820_2_);
            this.func_110232_cE();
            return true;
         } else {
            return false;
         }
      } else {
         int j = p_174820_1_ - 500 + 2;
         if(j >= 2 && j < this.field_110296_bG.func_70302_i_()) {
            this.field_110296_bG.func_70299_a(j, p_174820_2_);
            return true;
         } else {
            return false;
         }
      }
   }

   public static class GroupData implements IEntityLivingData {
      public int field_111107_a;
      public int field_111106_b;

      public GroupData(int p_i1684_1_, int p_i1684_2_) {
         this.field_111107_a = p_i1684_1_;
         this.field_111106_b = p_i1684_2_;
      }
   }
}
