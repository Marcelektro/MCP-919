package net.minecraft.entity.player;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public abstract class EntityPlayer extends EntityLivingBase {
   public InventoryPlayer field_71071_by = new InventoryPlayer(this);
   private InventoryEnderChest field_71078_a = new InventoryEnderChest();
   public Container field_71069_bz;
   public Container field_71070_bA;
   protected FoodStats field_71100_bB = new FoodStats();
   protected int field_71101_bC;
   public float field_71107_bF;
   public float field_71109_bG;
   public int field_71090_bL;
   public double field_71091_bM;
   public double field_71096_bN;
   public double field_71097_bO;
   public double field_71094_bP;
   public double field_71095_bQ;
   public double field_71085_bR;
   protected boolean field_71083_bS;
   public BlockPos field_71081_bT;
   private int field_71076_b;
   public float field_71079_bU;
   public float field_71082_cx;
   public float field_71089_bV;
   private BlockPos field_71077_c;
   private boolean field_82248_d;
   private BlockPos field_71073_d;
   public PlayerCapabilities field_71075_bZ = new PlayerCapabilities();
   public int field_71068_ca;
   public int field_71067_cb;
   public float field_71106_cc;
   private int field_175152_f;
   private ItemStack field_71074_e;
   private int field_71072_f;
   protected float field_71108_cd = 0.1F;
   protected float field_71102_ce = 0.02F;
   private int field_82249_h;
   private final GameProfile field_146106_i;
   private boolean field_175153_bG = false;
   public EntityFishHook field_71104_cf;

   public EntityPlayer(World p_i45324_1_, GameProfile p_i45324_2_) {
      super(p_i45324_1_);
      this.field_96093_i = func_146094_a(p_i45324_2_);
      this.field_146106_i = p_i45324_2_;
      this.field_71069_bz = new ContainerPlayer(this.field_71071_by, !p_i45324_1_.field_72995_K, this);
      this.field_71070_bA = this.field_71069_bz;
      BlockPos blockpos = p_i45324_1_.func_175694_M();
      this.func_70012_b((double)blockpos.func_177958_n() + 0.5D, (double)(blockpos.func_177956_o() + 1), (double)blockpos.func_177952_p() + 0.5D, 0.0F, 0.0F);
      this.field_70741_aB = 180.0F;
      this.field_70174_ab = 20;
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.10000000149011612D);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)0));
      this.field_70180_af.func_75682_a(17, Float.valueOf(0.0F));
      this.field_70180_af.func_75682_a(18, Integer.valueOf(0));
      this.field_70180_af.func_75682_a(10, Byte.valueOf((byte)0));
   }

   public ItemStack func_71011_bu() {
      return this.field_71074_e;
   }

   public int func_71052_bv() {
      return this.field_71072_f;
   }

   public boolean func_71039_bw() {
      return this.field_71074_e != null;
   }

   public int func_71057_bx() {
      return this.func_71039_bw()?this.field_71074_e.func_77988_m() - this.field_71072_f:0;
   }

   public void func_71034_by() {
      if(this.field_71074_e != null) {
         this.field_71074_e.func_77974_b(this.field_70170_p, this, this.field_71072_f);
      }

      this.func_71041_bz();
   }

   public void func_71041_bz() {
      this.field_71074_e = null;
      this.field_71072_f = 0;
      if(!this.field_70170_p.field_72995_K) {
         this.func_70019_c(false);
      }

   }

   public boolean func_70632_aY() {
      return this.func_71039_bw() && this.field_71074_e.func_77973_b().func_77661_b(this.field_71074_e) == EnumAction.BLOCK;
   }

   public void func_70071_h_() {
      this.field_70145_X = this.func_175149_v();
      if(this.func_175149_v()) {
         this.field_70122_E = false;
      }

      if(this.field_71074_e != null) {
         ItemStack itemstack = this.field_71071_by.func_70448_g();
         if(itemstack == this.field_71074_e) {
            if(this.field_71072_f <= 25 && this.field_71072_f % 4 == 0) {
               this.func_71010_c(itemstack, 5);
            }

            if(--this.field_71072_f == 0 && !this.field_70170_p.field_72995_K) {
               this.func_71036_o();
            }
         } else {
            this.func_71041_bz();
         }
      }

      if(this.field_71090_bL > 0) {
         --this.field_71090_bL;
      }

      if(this.func_70608_bn()) {
         ++this.field_71076_b;
         if(this.field_71076_b > 100) {
            this.field_71076_b = 100;
         }

         if(!this.field_70170_p.field_72995_K) {
            if(!this.func_175143_p()) {
               this.func_70999_a(true, true, false);
            } else if(this.field_70170_p.func_72935_r()) {
               this.func_70999_a(false, true, true);
            }
         }
      } else if(this.field_71076_b > 0) {
         ++this.field_71076_b;
         if(this.field_71076_b >= 110) {
            this.field_71076_b = 0;
         }
      }

      super.func_70071_h_();
      if(!this.field_70170_p.field_72995_K && this.field_71070_bA != null && !this.field_71070_bA.func_75145_c(this)) {
         this.func_71053_j();
         this.field_71070_bA = this.field_71069_bz;
      }

      if(this.func_70027_ad() && this.field_71075_bZ.field_75102_a) {
         this.func_70066_B();
      }

      this.field_71091_bM = this.field_71094_bP;
      this.field_71096_bN = this.field_71095_bQ;
      this.field_71097_bO = this.field_71085_bR;
      double d5 = this.field_70165_t - this.field_71094_bP;
      double d0 = this.field_70163_u - this.field_71095_bQ;
      double d1 = this.field_70161_v - this.field_71085_bR;
      double d2 = 10.0D;
      if(d5 > d2) {
         this.field_71091_bM = this.field_71094_bP = this.field_70165_t;
      }

      if(d1 > d2) {
         this.field_71097_bO = this.field_71085_bR = this.field_70161_v;
      }

      if(d0 > d2) {
         this.field_71096_bN = this.field_71095_bQ = this.field_70163_u;
      }

      if(d5 < -d2) {
         this.field_71091_bM = this.field_71094_bP = this.field_70165_t;
      }

      if(d1 < -d2) {
         this.field_71097_bO = this.field_71085_bR = this.field_70161_v;
      }

      if(d0 < -d2) {
         this.field_71096_bN = this.field_71095_bQ = this.field_70163_u;
      }

      this.field_71094_bP += d5 * 0.25D;
      this.field_71085_bR += d1 * 0.25D;
      this.field_71095_bQ += d0 * 0.25D;
      if(this.field_70154_o == null) {
         this.field_71073_d = null;
      }

      if(!this.field_70170_p.field_72995_K) {
         this.field_71100_bB.func_75118_a(this);
         this.func_71029_a(StatList.field_75948_k);
         if(this.func_70089_S()) {
            this.func_71029_a(StatList.field_180209_h);
         }
      }

      int i = 29999999;
      double d3 = MathHelper.func_151237_a(this.field_70165_t, -2.9999999E7D, 2.9999999E7D);
      double d4 = MathHelper.func_151237_a(this.field_70161_v, -2.9999999E7D, 2.9999999E7D);
      if(d3 != this.field_70165_t || d4 != this.field_70161_v) {
         this.func_70107_b(d3, this.field_70163_u, d4);
      }

   }

   public int func_82145_z() {
      return this.field_71075_bZ.field_75102_a?0:80;
   }

   protected String func_145776_H() {
      return "game.player.swim";
   }

   protected String func_145777_O() {
      return "game.player.swim.splash";
   }

   public int func_82147_ab() {
      return 10;
   }

   public void func_85030_a(String p_85030_1_, float p_85030_2_, float p_85030_3_) {
      this.field_70170_p.func_85173_a(this, p_85030_1_, p_85030_2_, p_85030_3_);
   }

   protected void func_71010_c(ItemStack p_71010_1_, int p_71010_2_) {
      if(p_71010_1_.func_77975_n() == EnumAction.DRINK) {
         this.func_85030_a("random.drink", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
      }

      if(p_71010_1_.func_77975_n() == EnumAction.EAT) {
         for(int i = 0; i < p_71010_2_; ++i) {
            Vec3 vec3 = new Vec3(((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            vec3 = vec3.func_178789_a(-this.field_70125_A * 3.1415927F / 180.0F);
            vec3 = vec3.func_178785_b(-this.field_70177_z * 3.1415927F / 180.0F);
            double d0 = (double)(-this.field_70146_Z.nextFloat()) * 0.6D - 0.3D;
            Vec3 vec31 = new Vec3(((double)this.field_70146_Z.nextFloat() - 0.5D) * 0.3D, d0, 0.6D);
            vec31 = vec31.func_178789_a(-this.field_70125_A * 3.1415927F / 180.0F);
            vec31 = vec31.func_178785_b(-this.field_70177_z * 3.1415927F / 180.0F);
            vec31 = vec31.func_72441_c(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
            if(p_71010_1_.func_77981_g()) {
               this.field_70170_p.func_175688_a(EnumParticleTypes.ITEM_CRACK, vec31.field_72450_a, vec31.field_72448_b, vec31.field_72449_c, vec3.field_72450_a, vec3.field_72448_b + 0.05D, vec3.field_72449_c, new int[]{Item.func_150891_b(p_71010_1_.func_77973_b()), p_71010_1_.func_77960_j()});
            } else {
               this.field_70170_p.func_175688_a(EnumParticleTypes.ITEM_CRACK, vec31.field_72450_a, vec31.field_72448_b, vec31.field_72449_c, vec3.field_72450_a, vec3.field_72448_b + 0.05D, vec3.field_72449_c, new int[]{Item.func_150891_b(p_71010_1_.func_77973_b())});
            }
         }

         this.func_85030_a("random.eat", 0.5F + 0.5F * (float)this.field_70146_Z.nextInt(2), (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
      }

   }

   protected void func_71036_o() {
      if(this.field_71074_e != null) {
         this.func_71010_c(this.field_71074_e, 16);
         int i = this.field_71074_e.field_77994_a;
         ItemStack itemstack = this.field_71074_e.func_77950_b(this.field_70170_p, this);
         if(itemstack != this.field_71074_e || itemstack != null && itemstack.field_77994_a != i) {
            this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c] = itemstack;
            if(itemstack.field_77994_a == 0) {
               this.field_71071_by.field_70462_a[this.field_71071_by.field_70461_c] = null;
            }
         }

         this.func_71041_bz();
      }

   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 9) {
         this.func_71036_o();
      } else if(p_70103_1_ == 23) {
         this.field_175153_bG = false;
      } else if(p_70103_1_ == 22) {
         this.field_175153_bG = true;
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   protected boolean func_70610_aX() {
      return this.func_110143_aJ() <= 0.0F || this.func_70608_bn();
   }

   protected void func_71053_j() {
      this.field_71070_bA = this.field_71069_bz;
   }

   public void func_70098_U() {
      if(!this.field_70170_p.field_72995_K && this.func_70093_af()) {
         this.func_70078_a((Entity)null);
         this.func_70095_a(false);
      } else {
         double d0 = this.field_70165_t;
         double d1 = this.field_70163_u;
         double d2 = this.field_70161_v;
         float f = this.field_70177_z;
         float f1 = this.field_70125_A;
         super.func_70098_U();
         this.field_71107_bF = this.field_71109_bG;
         this.field_71109_bG = 0.0F;
         this.func_71015_k(this.field_70165_t - d0, this.field_70163_u - d1, this.field_70161_v - d2);
         if(this.field_70154_o instanceof EntityPig) {
            this.field_70125_A = f1;
            this.field_70177_z = f;
            this.field_70761_aq = ((EntityPig)this.field_70154_o).field_70761_aq;
         }

      }
   }

   public void func_70065_x() {
      this.func_70105_a(0.6F, 1.8F);
      super.func_70065_x();
      this.func_70606_j(this.func_110138_aP());
      this.field_70725_aQ = 0;
   }

   protected void func_70626_be() {
      super.func_70626_be();
      this.func_82168_bl();
      this.field_70759_as = this.field_70177_z;
   }

   public void func_70636_d() {
      if(this.field_71101_bC > 0) {
         --this.field_71101_bC;
      }

      if(this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL && this.field_70170_p.func_82736_K().func_82766_b("naturalRegeneration")) {
         if(this.func_110143_aJ() < this.func_110138_aP() && this.field_70173_aa % 20 == 0) {
            this.func_70691_i(1.0F);
         }

         if(this.field_71100_bB.func_75121_c() && this.field_70173_aa % 10 == 0) {
            this.field_71100_bB.func_75114_a(this.field_71100_bB.func_75116_a() + 1);
         }
      }

      this.field_71071_by.func_70429_k();
      this.field_71107_bF = this.field_71109_bG;
      super.func_70636_d();
      IAttributeInstance iattributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d);
      if(!this.field_70170_p.field_72995_K) {
         iattributeinstance.func_111128_a((double)this.field_71075_bZ.func_75094_b());
      }

      this.field_70747_aH = this.field_71102_ce;
      if(this.func_70051_ag()) {
         this.field_70747_aH = (float)((double)this.field_70747_aH + (double)this.field_71102_ce * 0.3D);
      }

      this.func_70659_e((float)iattributeinstance.func_111126_e());
      float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
      float f1 = (float)(Math.atan(-this.field_70181_x * 0.20000000298023224D) * 15.0D);
      if(f > 0.1F) {
         f = 0.1F;
      }

      if(!this.field_70122_E || this.func_110143_aJ() <= 0.0F) {
         f = 0.0F;
      }

      if(this.field_70122_E || this.func_110143_aJ() <= 0.0F) {
         f1 = 0.0F;
      }

      this.field_71109_bG += (f - this.field_71109_bG) * 0.4F;
      this.field_70726_aT += (f1 - this.field_70726_aT) * 0.8F;
      if(this.func_110143_aJ() > 0.0F && !this.func_175149_v()) {
         AxisAlignedBB axisalignedbb = null;
         if(this.field_70154_o != null && !this.field_70154_o.field_70128_L) {
            axisalignedbb = this.func_174813_aQ().func_111270_a(this.field_70154_o.func_174813_aQ()).func_72314_b(1.0D, 0.0D, 1.0D);
         } else {
            axisalignedbb = this.func_174813_aQ().func_72314_b(1.0D, 0.5D, 1.0D);
         }

         List<Entity> list = this.field_70170_p.func_72839_b(this, axisalignedbb);

         for(int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity)list.get(i);
            if(!entity.field_70128_L) {
               this.func_71044_o(entity);
            }
         }
      }

   }

   private void func_71044_o(Entity p_71044_1_) {
      p_71044_1_.func_70100_b_(this);
   }

   public int func_71037_bA() {
      return this.field_70180_af.func_75679_c(18);
   }

   public void func_85040_s(int p_85040_1_) {
      this.field_70180_af.func_75692_b(18, Integer.valueOf(p_85040_1_));
   }

   public void func_85039_t(int p_85039_1_) {
      int i = this.func_71037_bA();
      this.field_70180_af.func_75692_b(18, Integer.valueOf(i + p_85039_1_));
   }

   public void func_70645_a(DamageSource p_70645_1_) {
      super.func_70645_a(p_70645_1_);
      this.func_70105_a(0.2F, 0.2F);
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.field_70181_x = 0.10000000149011612D;
      if(this.func_70005_c_().equals("Notch")) {
         this.func_146097_a(new ItemStack(Items.field_151034_e, 1), true, false);
      }

      if(!this.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
         this.field_71071_by.func_70436_m();
      }

      if(p_70645_1_ != null) {
         this.field_70159_w = (double)(-MathHelper.func_76134_b((this.field_70739_aP + this.field_70177_z) * 3.1415927F / 180.0F) * 0.1F);
         this.field_70179_y = (double)(-MathHelper.func_76126_a((this.field_70739_aP + this.field_70177_z) * 3.1415927F / 180.0F) * 0.1F);
      } else {
         this.field_70159_w = this.field_70179_y = 0.0D;
      }

      this.func_71029_a(StatList.field_75960_y);
      this.func_175145_a(StatList.field_180209_h);
   }

   protected String func_70621_aR() {
      return "game.player.hurt";
   }

   protected String func_70673_aS() {
      return "game.player.die";
   }

   public void func_70084_c(Entity p_70084_1_, int p_70084_2_) {
      this.func_85039_t(p_70084_2_);
      Collection<ScoreObjective> collection = this.func_96123_co().func_96520_a(IScoreObjectiveCriteria.field_96640_e);
      if(p_70084_1_ instanceof EntityPlayer) {
         this.func_71029_a(StatList.field_75932_A);
         collection.addAll(this.func_96123_co().func_96520_a(IScoreObjectiveCriteria.field_96639_d));
         collection.addAll(this.func_175137_e(p_70084_1_));
      } else {
         this.func_71029_a(StatList.field_75959_z);
      }

      for(ScoreObjective scoreobjective : collection) {
         Score score = this.func_96123_co().func_96529_a(this.func_70005_c_(), scoreobjective);
         score.func_96648_a();
      }

   }

   private Collection<ScoreObjective> func_175137_e(Entity p_175137_1_) {
      ScorePlayerTeam scoreplayerteam = this.func_96123_co().func_96509_i(this.func_70005_c_());
      if(scoreplayerteam != null) {
         int i = scoreplayerteam.func_178775_l().func_175746_b();
         if(i >= 0 && i < IScoreObjectiveCriteria.field_178793_i.length) {
            for(ScoreObjective scoreobjective : this.func_96123_co().func_96520_a(IScoreObjectiveCriteria.field_178793_i[i])) {
               Score score = this.func_96123_co().func_96529_a(p_175137_1_.func_70005_c_(), scoreobjective);
               score.func_96648_a();
            }
         }
      }

      ScorePlayerTeam scoreplayerteam1 = this.func_96123_co().func_96509_i(p_175137_1_.func_70005_c_());
      if(scoreplayerteam1 != null) {
         int j = scoreplayerteam1.func_178775_l().func_175746_b();
         if(j >= 0 && j < IScoreObjectiveCriteria.field_178792_h.length) {
            return this.func_96123_co().func_96520_a(IScoreObjectiveCriteria.field_178792_h[j]);
         }
      }

      return Lists.<ScoreObjective>newArrayList();
   }

   public EntityItem func_71040_bB(boolean p_71040_1_) {
      return this.func_146097_a(this.field_71071_by.func_70298_a(this.field_71071_by.field_70461_c, p_71040_1_ && this.field_71071_by.func_70448_g() != null?this.field_71071_by.func_70448_g().field_77994_a:1), false, true);
   }

   public EntityItem func_71019_a(ItemStack p_71019_1_, boolean p_71019_2_) {
      return this.func_146097_a(p_71019_1_, false, false);
   }

   public EntityItem func_146097_a(ItemStack p_146097_1_, boolean p_146097_2_, boolean p_146097_3_) {
      if(p_146097_1_ == null) {
         return null;
      } else if(p_146097_1_.field_77994_a == 0) {
         return null;
      } else {
         double d0 = this.field_70163_u - 0.30000001192092896D + (double)this.func_70047_e();
         EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t, d0, this.field_70161_v, p_146097_1_);
         entityitem.func_174867_a(40);
         if(p_146097_3_) {
            entityitem.func_145799_b(this.func_70005_c_());
         }

         if(p_146097_2_) {
            float f = this.field_70146_Z.nextFloat() * 0.5F;
            float f1 = this.field_70146_Z.nextFloat() * 3.1415927F * 2.0F;
            entityitem.field_70159_w = (double)(-MathHelper.func_76126_a(f1) * f);
            entityitem.field_70179_y = (double)(MathHelper.func_76134_b(f1) * f);
            entityitem.field_70181_x = 0.20000000298023224D;
         } else {
            float f2 = 0.3F;
            entityitem.field_70159_w = (double)(-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f2);
            entityitem.field_70179_y = (double)(MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f2);
            entityitem.field_70181_x = (double)(-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F) * f2 + 0.1F);
            float f3 = this.field_70146_Z.nextFloat() * 3.1415927F * 2.0F;
            f2 = 0.02F * this.field_70146_Z.nextFloat();
            entityitem.field_70159_w += Math.cos((double)f3) * (double)f2;
            entityitem.field_70181_x += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1F);
            entityitem.field_70179_y += Math.sin((double)f3) * (double)f2;
         }

         this.func_71012_a(entityitem);
         if(p_146097_3_) {
            this.func_71029_a(StatList.field_75952_v);
         }

         return entityitem;
      }
   }

   protected void func_71012_a(EntityItem p_71012_1_) {
      this.field_70170_p.func_72838_d(p_71012_1_);
   }

   public float func_180471_a(Block p_180471_1_) {
      float f = this.field_71071_by.func_146023_a(p_180471_1_);
      if(f > 1.0F) {
         int i = EnchantmentHelper.func_77509_b(this);
         ItemStack itemstack = this.field_71071_by.func_70448_g();
         if(i > 0 && itemstack != null) {
            f += (float)(i * i + 1);
         }
      }

      if(this.func_70644_a(Potion.field_76422_e)) {
         f *= 1.0F + (float)(this.func_70660_b(Potion.field_76422_e).func_76458_c() + 1) * 0.2F;
      }

      if(this.func_70644_a(Potion.field_76419_f)) {
         float f1 = 1.0F;
         switch(this.func_70660_b(Potion.field_76419_f).func_76458_c()) {
         case 0:
            f1 = 0.3F;
            break;
         case 1:
            f1 = 0.09F;
            break;
         case 2:
            f1 = 0.0027F;
            break;
         case 3:
         default:
            f1 = 8.1E-4F;
         }

         f *= f1;
      }

      if(this.func_70055_a(Material.field_151586_h) && !EnchantmentHelper.func_77510_g(this)) {
         f /= 5.0F;
      }

      if(!this.field_70122_E) {
         f /= 5.0F;
      }

      return f;
   }

   public boolean func_146099_a(Block p_146099_1_) {
      return this.field_71071_by.func_146025_b(p_146099_1_);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_96093_i = func_146094_a(this.field_146106_i);
      NBTTagList nbttaglist = p_70037_1_.func_150295_c("Inventory", 10);
      this.field_71071_by.func_70443_b(nbttaglist);
      this.field_71071_by.field_70461_c = p_70037_1_.func_74762_e("SelectedItemSlot");
      this.field_71083_bS = p_70037_1_.func_74767_n("Sleeping");
      this.field_71076_b = p_70037_1_.func_74765_d("SleepTimer");
      this.field_71106_cc = p_70037_1_.func_74760_g("XpP");
      this.field_71068_ca = p_70037_1_.func_74762_e("XpLevel");
      this.field_71067_cb = p_70037_1_.func_74762_e("XpTotal");
      this.field_175152_f = p_70037_1_.func_74762_e("XpSeed");
      if(this.field_175152_f == 0) {
         this.field_175152_f = this.field_70146_Z.nextInt();
      }

      this.func_85040_s(p_70037_1_.func_74762_e("Score"));
      if(this.field_71083_bS) {
         this.field_71081_bT = new BlockPos(this);
         this.func_70999_a(true, true, false);
      }

      if(p_70037_1_.func_150297_b("SpawnX", 99) && p_70037_1_.func_150297_b("SpawnY", 99) && p_70037_1_.func_150297_b("SpawnZ", 99)) {
         this.field_71077_c = new BlockPos(p_70037_1_.func_74762_e("SpawnX"), p_70037_1_.func_74762_e("SpawnY"), p_70037_1_.func_74762_e("SpawnZ"));
         this.field_82248_d = p_70037_1_.func_74767_n("SpawnForced");
      }

      this.field_71100_bB.func_75112_a(p_70037_1_);
      this.field_71075_bZ.func_75095_b(p_70037_1_);
      if(p_70037_1_.func_150297_b("EnderItems", 9)) {
         NBTTagList nbttaglist1 = p_70037_1_.func_150295_c("EnderItems", 10);
         this.field_71078_a.func_70486_a(nbttaglist1);
      }

   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74782_a("Inventory", this.field_71071_by.func_70442_a(new NBTTagList()));
      p_70014_1_.func_74768_a("SelectedItemSlot", this.field_71071_by.field_70461_c);
      p_70014_1_.func_74757_a("Sleeping", this.field_71083_bS);
      p_70014_1_.func_74777_a("SleepTimer", (short)this.field_71076_b);
      p_70014_1_.func_74776_a("XpP", this.field_71106_cc);
      p_70014_1_.func_74768_a("XpLevel", this.field_71068_ca);
      p_70014_1_.func_74768_a("XpTotal", this.field_71067_cb);
      p_70014_1_.func_74768_a("XpSeed", this.field_175152_f);
      p_70014_1_.func_74768_a("Score", this.func_71037_bA());
      if(this.field_71077_c != null) {
         p_70014_1_.func_74768_a("SpawnX", this.field_71077_c.func_177958_n());
         p_70014_1_.func_74768_a("SpawnY", this.field_71077_c.func_177956_o());
         p_70014_1_.func_74768_a("SpawnZ", this.field_71077_c.func_177952_p());
         p_70014_1_.func_74757_a("SpawnForced", this.field_82248_d);
      }

      this.field_71100_bB.func_75117_b(p_70014_1_);
      this.field_71075_bZ.func_75091_a(p_70014_1_);
      p_70014_1_.func_74782_a("EnderItems", this.field_71078_a.func_70487_g());
      ItemStack itemstack = this.field_71071_by.func_70448_g();
      if(itemstack != null && itemstack.func_77973_b() != null) {
         p_70014_1_.func_74782_a("SelectedItem", itemstack.func_77955_b(new NBTTagCompound()));
      }

   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_180431_b(p_70097_1_)) {
         return false;
      } else if(this.field_71075_bZ.field_75102_a && !p_70097_1_.func_76357_e()) {
         return false;
      } else {
         this.field_70708_bq = 0;
         if(this.func_110143_aJ() <= 0.0F) {
            return false;
         } else {
            if(this.func_70608_bn() && !this.field_70170_p.field_72995_K) {
               this.func_70999_a(true, true, false);
            }

            if(p_70097_1_.func_76350_n()) {
               if(this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
                  p_70097_2_ = 0.0F;
               }

               if(this.field_70170_p.func_175659_aa() == EnumDifficulty.EASY) {
                  p_70097_2_ = p_70097_2_ / 2.0F + 1.0F;
               }

               if(this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD) {
                  p_70097_2_ = p_70097_2_ * 3.0F / 2.0F;
               }
            }

            if(p_70097_2_ == 0.0F) {
               return false;
            } else {
               Entity entity = p_70097_1_.func_76346_g();
               if(entity instanceof EntityArrow && ((EntityArrow)entity).field_70250_c != null) {
                  entity = ((EntityArrow)entity).field_70250_c;
               }

               return super.func_70097_a(p_70097_1_, p_70097_2_);
            }
         }
      }
   }

   public boolean func_96122_a(EntityPlayer p_96122_1_) {
      Team team = this.func_96124_cp();
      Team team1 = p_96122_1_.func_96124_cp();
      return team == null?true:(!team.func_142054_a(team1)?true:team.func_96665_g());
   }

   protected void func_70675_k(float p_70675_1_) {
      this.field_71071_by.func_70449_g(p_70675_1_);
   }

   public int func_70658_aO() {
      return this.field_71071_by.func_70430_l();
   }

   public float func_82243_bO() {
      int i = 0;

      for(ItemStack itemstack : this.field_71071_by.field_70460_b) {
         if(itemstack != null) {
            ++i;
         }
      }

      return (float)i / (float)this.field_71071_by.field_70460_b.length;
   }

   protected void func_70665_d(DamageSource p_70665_1_, float p_70665_2_) {
      if(!this.func_180431_b(p_70665_1_)) {
         if(!p_70665_1_.func_76363_c() && this.func_70632_aY() && p_70665_2_ > 0.0F) {
            p_70665_2_ = (1.0F + p_70665_2_) * 0.5F;
         }

         p_70665_2_ = this.func_70655_b(p_70665_1_, p_70665_2_);
         p_70665_2_ = this.func_70672_c(p_70665_1_, p_70665_2_);
         float f = p_70665_2_;
         p_70665_2_ = Math.max(p_70665_2_ - this.func_110139_bj(), 0.0F);
         this.func_110149_m(this.func_110139_bj() - (f - p_70665_2_));
         if(p_70665_2_ != 0.0F) {
            this.func_71020_j(p_70665_1_.func_76345_d());
            float f1 = this.func_110143_aJ();
            this.func_70606_j(this.func_110143_aJ() - p_70665_2_);
            this.func_110142_aN().func_94547_a(p_70665_1_, f1, p_70665_2_);
            if(p_70665_2_ < 3.4028235E37F) {
               this.func_71064_a(StatList.field_75961_x, Math.round(p_70665_2_ * 10.0F));
            }

         }
      }
   }

   public void func_175141_a(TileEntitySign p_175141_1_) {
   }

   public void func_146095_a(CommandBlockLogic p_146095_1_) {
   }

   public void func_180472_a(IMerchant p_180472_1_) {
   }

   public void func_71007_a(IInventory p_71007_1_) {
   }

   public void func_110298_a(EntityHorse p_110298_1_, IInventory p_110298_2_) {
   }

   public void func_180468_a(IInteractionObject p_180468_1_) {
   }

   public void func_71048_c(ItemStack p_71048_1_) {
   }

   public boolean func_70998_m(Entity p_70998_1_) {
      if(this.func_175149_v()) {
         if(p_70998_1_ instanceof IInventory) {
            this.func_71007_a((IInventory)p_70998_1_);
         }

         return false;
      } else {
         ItemStack itemstack = this.func_71045_bC();
         ItemStack itemstack1 = itemstack != null?itemstack.func_77946_l():null;
         if(!p_70998_1_.func_130002_c(this)) {
            if(itemstack != null && p_70998_1_ instanceof EntityLivingBase) {
               if(this.field_71075_bZ.field_75098_d) {
                  itemstack = itemstack1;
               }

               if(itemstack.func_111282_a(this, (EntityLivingBase)p_70998_1_)) {
                  if(itemstack.field_77994_a <= 0 && !this.field_71075_bZ.field_75098_d) {
                     this.func_71028_bD();
                  }

                  return true;
               }
            }

            return false;
         } else {
            if(itemstack != null && itemstack == this.func_71045_bC()) {
               if(itemstack.field_77994_a <= 0 && !this.field_71075_bZ.field_75098_d) {
                  this.func_71028_bD();
               } else if(itemstack.field_77994_a < itemstack1.field_77994_a && this.field_71075_bZ.field_75098_d) {
                  itemstack.field_77994_a = itemstack1.field_77994_a;
               }
            }

            return true;
         }
      }
   }

   public ItemStack func_71045_bC() {
      return this.field_71071_by.func_70448_g();
   }

   public void func_71028_bD() {
      this.field_71071_by.func_70299_a(this.field_71071_by.field_70461_c, (ItemStack)null);
   }

   public double func_70033_W() {
      return -0.35D;
   }

   public void func_71059_n(Entity p_71059_1_) {
      if(p_71059_1_.func_70075_an()) {
         if(!p_71059_1_.func_85031_j(this)) {
            float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
            int i = 0;
            float f1 = 0.0F;
            if(p_71059_1_ instanceof EntityLivingBase) {
               f1 = EnchantmentHelper.func_152377_a(this.func_70694_bm(), ((EntityLivingBase)p_71059_1_).func_70668_bt());
            } else {
               f1 = EnchantmentHelper.func_152377_a(this.func_70694_bm(), EnumCreatureAttribute.UNDEFINED);
            }

            i = i + EnchantmentHelper.func_77501_a(this);
            if(this.func_70051_ag()) {
               ++i;
            }

            if(f > 0.0F || f1 > 0.0F) {
               boolean flag = this.field_70143_R > 0.0F && !this.field_70122_E && !this.func_70617_f_() && !this.func_70090_H() && !this.func_70644_a(Potion.field_76440_q) && this.field_70154_o == null && p_71059_1_ instanceof EntityLivingBase;
               if(flag && f > 0.0F) {
                  f *= 1.5F;
               }

               f = f + f1;
               boolean flag1 = false;
               int j = EnchantmentHelper.func_90036_a(this);
               if(p_71059_1_ instanceof EntityLivingBase && j > 0 && !p_71059_1_.func_70027_ad()) {
                  flag1 = true;
                  p_71059_1_.func_70015_d(1);
               }

               double d0 = p_71059_1_.field_70159_w;
               double d1 = p_71059_1_.field_70181_x;
               double d2 = p_71059_1_.field_70179_y;
               boolean flag2 = p_71059_1_.func_70097_a(DamageSource.func_76365_a(this), f);
               if(flag2) {
                  if(i > 0) {
                     p_71059_1_.func_70024_g((double)(-MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * (float)i * 0.5F));
                     this.field_70159_w *= 0.6D;
                     this.field_70179_y *= 0.6D;
                     this.func_70031_b(false);
                  }

                  if(p_71059_1_ instanceof EntityPlayerMP && p_71059_1_.field_70133_I) {
                     ((EntityPlayerMP)p_71059_1_).field_71135_a.func_147359_a(new S12PacketEntityVelocity(p_71059_1_));
                     p_71059_1_.field_70133_I = false;
                     p_71059_1_.field_70159_w = d0;
                     p_71059_1_.field_70181_x = d1;
                     p_71059_1_.field_70179_y = d2;
                  }

                  if(flag) {
                     this.func_71009_b(p_71059_1_);
                  }

                  if(f1 > 0.0F) {
                     this.func_71047_c(p_71059_1_);
                  }

                  if(f >= 18.0F) {
                     this.func_71029_a(AchievementList.field_75999_E);
                  }

                  this.func_130011_c(p_71059_1_);
                  if(p_71059_1_ instanceof EntityLivingBase) {
                     EnchantmentHelper.func_151384_a((EntityLivingBase)p_71059_1_, this);
                  }

                  EnchantmentHelper.func_151385_b(this, p_71059_1_);
                  ItemStack itemstack = this.func_71045_bC();
                  Entity entity = p_71059_1_;
                  if(p_71059_1_ instanceof EntityDragonPart) {
                     IEntityMultiPart ientitymultipart = ((EntityDragonPart)p_71059_1_).field_70259_a;
                     if(ientitymultipart instanceof EntityLivingBase) {
                        entity = (EntityLivingBase)ientitymultipart;
                     }
                  }

                  if(itemstack != null && entity instanceof EntityLivingBase) {
                     itemstack.func_77961_a((EntityLivingBase)entity, this);
                     if(itemstack.field_77994_a <= 0) {
                        this.func_71028_bD();
                     }
                  }

                  if(p_71059_1_ instanceof EntityLivingBase) {
                     this.func_71064_a(StatList.field_75951_w, Math.round(f * 10.0F));
                     if(j > 0) {
                        p_71059_1_.func_70015_d(j * 4);
                     }
                  }

                  this.func_71020_j(0.3F);
               } else if(flag1) {
                  p_71059_1_.func_70066_B();
               }
            }

         }
      }
   }

   public void func_71009_b(Entity p_71009_1_) {
   }

   public void func_71047_c(Entity p_71047_1_) {
   }

   public void func_71004_bE() {
   }

   public void func_70106_y() {
      super.func_70106_y();
      this.field_71069_bz.func_75134_a(this);
      if(this.field_71070_bA != null) {
         this.field_71070_bA.func_75134_a(this);
      }

   }

   public boolean func_70094_T() {
      return !this.field_71083_bS && super.func_70094_T();
   }

   public boolean func_175144_cb() {
      return false;
   }

   public GameProfile func_146103_bH() {
      return this.field_146106_i;
   }

   public EntityPlayer.EnumStatus func_180469_a(BlockPos p_180469_1_) {
      if(!this.field_70170_p.field_72995_K) {
         if(this.func_70608_bn() || !this.func_70089_S()) {
            return EntityPlayer.EnumStatus.OTHER_PROBLEM;
         }

         if(!this.field_70170_p.field_73011_w.func_76569_d()) {
            return EntityPlayer.EnumStatus.NOT_POSSIBLE_HERE;
         }

         if(this.field_70170_p.func_72935_r()) {
            return EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW;
         }

         if(Math.abs(this.field_70165_t - (double)p_180469_1_.func_177958_n()) > 3.0D || Math.abs(this.field_70163_u - (double)p_180469_1_.func_177956_o()) > 2.0D || Math.abs(this.field_70161_v - (double)p_180469_1_.func_177952_p()) > 3.0D) {
            return EntityPlayer.EnumStatus.TOO_FAR_AWAY;
         }

         double d0 = 8.0D;
         double d1 = 5.0D;
         List<EntityMob> list = this.field_70170_p.<EntityMob>func_72872_a(EntityMob.class, new AxisAlignedBB((double)p_180469_1_.func_177958_n() - d0, (double)p_180469_1_.func_177956_o() - d1, (double)p_180469_1_.func_177952_p() - d0, (double)p_180469_1_.func_177958_n() + d0, (double)p_180469_1_.func_177956_o() + d1, (double)p_180469_1_.func_177952_p() + d0));
         if(!list.isEmpty()) {
            return EntityPlayer.EnumStatus.NOT_SAFE;
         }
      }

      if(this.func_70115_ae()) {
         this.func_70078_a((Entity)null);
      }

      this.func_70105_a(0.2F, 0.2F);
      if(this.field_70170_p.func_175667_e(p_180469_1_)) {
         EnumFacing enumfacing = (EnumFacing)this.field_70170_p.func_180495_p(p_180469_1_).func_177229_b(BlockDirectional.field_176387_N);
         float f = 0.5F;
         float f1 = 0.5F;
         switch(enumfacing) {
         case SOUTH:
            f1 = 0.9F;
            break;
         case NORTH:
            f1 = 0.1F;
            break;
         case WEST:
            f = 0.1F;
            break;
         case EAST:
            f = 0.9F;
         }

         this.func_175139_a(enumfacing);
         this.func_70107_b((double)((float)p_180469_1_.func_177958_n() + f), (double)((float)p_180469_1_.func_177956_o() + 0.6875F), (double)((float)p_180469_1_.func_177952_p() + f1));
      } else {
         this.func_70107_b((double)((float)p_180469_1_.func_177958_n() + 0.5F), (double)((float)p_180469_1_.func_177956_o() + 0.6875F), (double)((float)p_180469_1_.func_177952_p() + 0.5F));
      }

      this.field_71083_bS = true;
      this.field_71076_b = 0;
      this.field_71081_bT = p_180469_1_;
      this.field_70159_w = this.field_70179_y = this.field_70181_x = 0.0D;
      if(!this.field_70170_p.field_72995_K) {
         this.field_70170_p.func_72854_c();
      }

      return EntityPlayer.EnumStatus.OK;
   }

   private void func_175139_a(EnumFacing p_175139_1_) {
      this.field_71079_bU = 0.0F;
      this.field_71089_bV = 0.0F;
      switch(p_175139_1_) {
      case SOUTH:
         this.field_71089_bV = -1.8F;
         break;
      case NORTH:
         this.field_71089_bV = 1.8F;
         break;
      case WEST:
         this.field_71079_bU = 1.8F;
         break;
      case EAST:
         this.field_71079_bU = -1.8F;
      }

   }

   public void func_70999_a(boolean p_70999_1_, boolean p_70999_2_, boolean p_70999_3_) {
      this.func_70105_a(0.6F, 1.8F);
      IBlockState iblockstate = this.field_70170_p.func_180495_p(this.field_71081_bT);
      if(this.field_71081_bT != null && iblockstate.func_177230_c() == Blocks.field_150324_C) {
         this.field_70170_p.func_180501_a(this.field_71081_bT, iblockstate.func_177226_a(BlockBed.field_176471_b, Boolean.valueOf(false)), 4);
         BlockPos blockpos = BlockBed.func_176468_a(this.field_70170_p, this.field_71081_bT, 0);
         if(blockpos == null) {
            blockpos = this.field_71081_bT.func_177984_a();
         }

         this.func_70107_b((double)((float)blockpos.func_177958_n() + 0.5F), (double)((float)blockpos.func_177956_o() + 0.1F), (double)((float)blockpos.func_177952_p() + 0.5F));
      }

      this.field_71083_bS = false;
      if(!this.field_70170_p.field_72995_K && p_70999_2_) {
         this.field_70170_p.func_72854_c();
      }

      this.field_71076_b = p_70999_1_?0:100;
      if(p_70999_3_) {
         this.func_180473_a(this.field_71081_bT, false);
      }

   }

   private boolean func_175143_p() {
      return this.field_70170_p.func_180495_p(this.field_71081_bT).func_177230_c() == Blocks.field_150324_C;
   }

   public static BlockPos func_180467_a(World p_180467_0_, BlockPos p_180467_1_, boolean p_180467_2_) {
      Block block = p_180467_0_.func_180495_p(p_180467_1_).func_177230_c();
      if(block != Blocks.field_150324_C) {
         if(!p_180467_2_) {
            return null;
         } else {
            boolean flag = block.func_181623_g();
            boolean flag1 = p_180467_0_.func_180495_p(p_180467_1_.func_177984_a()).func_177230_c().func_181623_g();
            return flag && flag1?p_180467_1_:null;
         }
      } else {
         return BlockBed.func_176468_a(p_180467_0_, p_180467_1_, 0);
      }
   }

   public float func_71051_bG() {
      if(this.field_71081_bT != null) {
         EnumFacing enumfacing = (EnumFacing)this.field_70170_p.func_180495_p(this.field_71081_bT).func_177229_b(BlockDirectional.field_176387_N);
         switch(enumfacing) {
         case SOUTH:
            return 90.0F;
         case NORTH:
            return 270.0F;
         case WEST:
            return 0.0F;
         case EAST:
            return 180.0F;
         }
      }

      return 0.0F;
   }

   public boolean func_70608_bn() {
      return this.field_71083_bS;
   }

   public boolean func_71026_bH() {
      return this.field_71083_bS && this.field_71076_b >= 100;
   }

   public int func_71060_bI() {
      return this.field_71076_b;
   }

   public void func_146105_b(IChatComponent p_146105_1_) {
   }

   public BlockPos func_180470_cg() {
      return this.field_71077_c;
   }

   public boolean func_82245_bX() {
      return this.field_82248_d;
   }

   public void func_180473_a(BlockPos p_180473_1_, boolean p_180473_2_) {
      if(p_180473_1_ != null) {
         this.field_71077_c = p_180473_1_;
         this.field_82248_d = p_180473_2_;
      } else {
         this.field_71077_c = null;
         this.field_82248_d = false;
      }

   }

   public void func_71029_a(StatBase p_71029_1_) {
      this.func_71064_a(p_71029_1_, 1);
   }

   public void func_71064_a(StatBase p_71064_1_, int p_71064_2_) {
   }

   public void func_175145_a(StatBase p_175145_1_) {
   }

   public void func_70664_aZ() {
      super.func_70664_aZ();
      this.func_71029_a(StatList.field_75953_u);
      if(this.func_70051_ag()) {
         this.func_71020_j(0.8F);
      } else {
         this.func_71020_j(0.2F);
      }

   }

   public void func_70612_e(float p_70612_1_, float p_70612_2_) {
      double d0 = this.field_70165_t;
      double d1 = this.field_70163_u;
      double d2 = this.field_70161_v;
      if(this.field_71075_bZ.field_75100_b && this.field_70154_o == null) {
         double d3 = this.field_70181_x;
         float f = this.field_70747_aH;
         this.field_70747_aH = this.field_71075_bZ.func_75093_a() * (float)(this.func_70051_ag()?2:1);
         super.func_70612_e(p_70612_1_, p_70612_2_);
         this.field_70181_x = d3 * 0.6D;
         this.field_70747_aH = f;
      } else {
         super.func_70612_e(p_70612_1_, p_70612_2_);
      }

      this.func_71000_j(this.field_70165_t - d0, this.field_70163_u - d1, this.field_70161_v - d2);
   }

   public float func_70689_ay() {
      return (float)this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();
   }

   public void func_71000_j(double p_71000_1_, double p_71000_3_, double p_71000_5_) {
      if(this.field_70154_o == null) {
         if(this.func_70055_a(Material.field_151586_h)) {
            int i = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0F);
            if(i > 0) {
               this.func_71064_a(StatList.field_75957_q, i);
               this.func_71020_j(0.015F * (float)i * 0.01F);
            }
         } else if(this.func_70090_H()) {
            int j = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
            if(j > 0) {
               this.func_71064_a(StatList.field_75946_m, j);
               this.func_71020_j(0.015F * (float)j * 0.01F);
            }
         } else if(this.func_70617_f_()) {
            if(p_71000_3_ > 0.0D) {
               this.func_71064_a(StatList.field_75944_o, (int)Math.round(p_71000_3_ * 100.0D));
            }
         } else if(this.field_70122_E) {
            int k = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
            if(k > 0) {
               this.func_71064_a(StatList.field_75945_l, k);
               if(this.func_70051_ag()) {
                  this.func_71064_a(StatList.field_180208_k, k);
                  this.func_71020_j(0.099999994F * (float)k * 0.01F);
               } else {
                  if(this.func_70093_af()) {
                     this.func_71064_a(StatList.field_180207_j, k);
                  }

                  this.func_71020_j(0.01F * (float)k * 0.01F);
               }
            }
         } else {
            int l = Math.round(MathHelper.func_76133_a(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
            if(l > 25) {
               this.func_71064_a(StatList.field_75958_p, l);
            }
         }

      }
   }

   private void func_71015_k(double p_71015_1_, double p_71015_3_, double p_71015_5_) {
      if(this.field_70154_o != null) {
         int i = Math.round(MathHelper.func_76133_a(p_71015_1_ * p_71015_1_ + p_71015_3_ * p_71015_3_ + p_71015_5_ * p_71015_5_) * 100.0F);
         if(i > 0) {
            if(this.field_70154_o instanceof EntityMinecart) {
               this.func_71064_a(StatList.field_75956_r, i);
               if(this.field_71073_d == null) {
                  this.field_71073_d = new BlockPos(this);
               } else if(this.field_71073_d.func_177954_c((double)MathHelper.func_76128_c(this.field_70165_t), (double)MathHelper.func_76128_c(this.field_70163_u), (double)MathHelper.func_76128_c(this.field_70161_v)) >= 1000000.0D) {
                  this.func_71029_a(AchievementList.field_76025_q);
               }
            } else if(this.field_70154_o instanceof EntityBoat) {
               this.func_71064_a(StatList.field_75955_s, i);
            } else if(this.field_70154_o instanceof EntityPig) {
               this.func_71064_a(StatList.field_75954_t, i);
            } else if(this.field_70154_o instanceof EntityHorse) {
               this.func_71064_a(StatList.field_151185_q, i);
            }
         }
      }

   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
      if(!this.field_71075_bZ.field_75101_c) {
         if(p_180430_1_ >= 2.0F) {
            this.func_71064_a(StatList.field_75943_n, (int)Math.round((double)p_180430_1_ * 100.0D));
         }

         super.func_180430_e(p_180430_1_, p_180430_2_);
      }
   }

   protected void func_71061_d_() {
      if(!this.func_175149_v()) {
         super.func_71061_d_();
      }

   }

   protected String func_146067_o(int p_146067_1_) {
      return p_146067_1_ > 4?"game.player.hurt.fall.big":"game.player.hurt.fall.small";
   }

   public void func_70074_a(EntityLivingBase p_70074_1_) {
      if(p_70074_1_ instanceof IMob) {
         this.func_71029_a(AchievementList.field_76023_s);
      }

      EntityList.EntityEggInfo entitylist$entityegginfo = (EntityList.EntityEggInfo)EntityList.field_75627_a.get(Integer.valueOf(EntityList.func_75619_a(p_70074_1_)));
      if(entitylist$entityegginfo != null) {
         this.func_71029_a(entitylist$entityegginfo.field_151512_d);
      }

   }

   public void func_70110_aj() {
      if(!this.field_71075_bZ.field_75100_b) {
         super.func_70110_aj();
      }

   }

   public ItemStack func_82169_q(int p_82169_1_) {
      return this.field_71071_by.func_70440_f(p_82169_1_);
   }

   public void func_71023_q(int p_71023_1_) {
      this.func_85039_t(p_71023_1_);
      int i = Integer.MAX_VALUE - this.field_71067_cb;
      if(p_71023_1_ > i) {
         p_71023_1_ = i;
      }

      this.field_71106_cc += (float)p_71023_1_ / (float)this.func_71050_bK();

      for(this.field_71067_cb += p_71023_1_; this.field_71106_cc >= 1.0F; this.field_71106_cc /= (float)this.func_71050_bK()) {
         this.field_71106_cc = (this.field_71106_cc - 1.0F) * (float)this.func_71050_bK();
         this.func_82242_a(1);
      }

   }

   public int func_175138_ci() {
      return this.field_175152_f;
   }

   public void func_71013_b(int p_71013_1_) {
      this.field_71068_ca -= p_71013_1_;
      if(this.field_71068_ca < 0) {
         this.field_71068_ca = 0;
         this.field_71106_cc = 0.0F;
         this.field_71067_cb = 0;
      }

      this.field_175152_f = this.field_70146_Z.nextInt();
   }

   public void func_82242_a(int p_82242_1_) {
      this.field_71068_ca += p_82242_1_;
      if(this.field_71068_ca < 0) {
         this.field_71068_ca = 0;
         this.field_71106_cc = 0.0F;
         this.field_71067_cb = 0;
      }

      if(p_82242_1_ > 0 && this.field_71068_ca % 5 == 0 && (float)this.field_82249_h < (float)this.field_70173_aa - 100.0F) {
         float f = this.field_71068_ca > 30?1.0F:(float)this.field_71068_ca / 30.0F;
         this.field_70170_p.func_72956_a(this, "random.levelup", f * 0.75F, 1.0F);
         this.field_82249_h = this.field_70173_aa;
      }

   }

   public int func_71050_bK() {
      return this.field_71068_ca >= 30?112 + (this.field_71068_ca - 30) * 9:(this.field_71068_ca >= 15?37 + (this.field_71068_ca - 15) * 5:7 + this.field_71068_ca * 2);
   }

   public void func_71020_j(float p_71020_1_) {
      if(!this.field_71075_bZ.field_75102_a) {
         if(!this.field_70170_p.field_72995_K) {
            this.field_71100_bB.func_75113_a(p_71020_1_);
         }

      }
   }

   public FoodStats func_71024_bL() {
      return this.field_71100_bB;
   }

   public boolean func_71043_e(boolean p_71043_1_) {
      return (p_71043_1_ || this.field_71100_bB.func_75121_c()) && !this.field_71075_bZ.field_75102_a;
   }

   public boolean func_70996_bM() {
      return this.func_110143_aJ() > 0.0F && this.func_110143_aJ() < this.func_110138_aP();
   }

   public void func_71008_a(ItemStack p_71008_1_, int p_71008_2_) {
      if(p_71008_1_ != this.field_71074_e) {
         this.field_71074_e = p_71008_1_;
         this.field_71072_f = p_71008_2_;
         if(!this.field_70170_p.field_72995_K) {
            this.func_70019_c(true);
         }

      }
   }

   public boolean func_175142_cm() {
      return this.field_71075_bZ.field_75099_e;
   }

   public boolean func_175151_a(BlockPos p_175151_1_, EnumFacing p_175151_2_, ItemStack p_175151_3_) {
      if(this.field_71075_bZ.field_75099_e) {
         return true;
      } else if(p_175151_3_ == null) {
         return false;
      } else {
         BlockPos blockpos = p_175151_1_.func_177972_a(p_175151_2_.func_176734_d());
         Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
         return p_175151_3_.func_179547_d(block) || p_175151_3_.func_82835_x();
      }
   }

   protected int func_70693_a(EntityPlayer p_70693_1_) {
      if(this.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
         return 0;
      } else {
         int i = this.field_71068_ca * 7;
         return i > 100?100:i;
      }
   }

   protected boolean func_70684_aJ() {
      return true;
   }

   public boolean func_94059_bO() {
      return true;
   }

   public void func_71049_a(EntityPlayer p_71049_1_, boolean p_71049_2_) {
      if(p_71049_2_) {
         this.field_71071_by.func_70455_b(p_71049_1_.field_71071_by);
         this.func_70606_j(p_71049_1_.func_110143_aJ());
         this.field_71100_bB = p_71049_1_.field_71100_bB;
         this.field_71068_ca = p_71049_1_.field_71068_ca;
         this.field_71067_cb = p_71049_1_.field_71067_cb;
         this.field_71106_cc = p_71049_1_.field_71106_cc;
         this.func_85040_s(p_71049_1_.func_71037_bA());
         this.field_181016_an = p_71049_1_.field_181016_an;
         this.field_181017_ao = p_71049_1_.field_181017_ao;
         this.field_181018_ap = p_71049_1_.field_181018_ap;
      } else if(this.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
         this.field_71071_by.func_70455_b(p_71049_1_.field_71071_by);
         this.field_71068_ca = p_71049_1_.field_71068_ca;
         this.field_71067_cb = p_71049_1_.field_71067_cb;
         this.field_71106_cc = p_71049_1_.field_71106_cc;
         this.func_85040_s(p_71049_1_.func_71037_bA());
      }

      this.field_175152_f = p_71049_1_.field_175152_f;
      this.field_71078_a = p_71049_1_.field_71078_a;
      this.func_70096_w().func_75692_b(10, Byte.valueOf(p_71049_1_.func_70096_w().func_75683_a(10)));
   }

   protected boolean func_70041_e_() {
      return !this.field_71075_bZ.field_75100_b;
   }

   public void func_71016_p() {
   }

   public void func_71033_a(WorldSettings.GameType p_71033_1_) {
   }

   public String func_70005_c_() {
      return this.field_146106_i.getName();
   }

   public InventoryEnderChest func_71005_bN() {
      return this.field_71078_a;
   }

   public ItemStack func_71124_b(int p_71124_1_) {
      return p_71124_1_ == 0?this.field_71071_by.func_70448_g():this.field_71071_by.field_70460_b[p_71124_1_ - 1];
   }

   public ItemStack func_70694_bm() {
      return this.field_71071_by.func_70448_g();
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
      this.field_71071_by.field_70460_b[p_70062_1_] = p_70062_2_;
   }

   public boolean func_98034_c(EntityPlayer p_98034_1_) {
      if(!this.func_82150_aj()) {
         return false;
      } else if(p_98034_1_.func_175149_v()) {
         return false;
      } else {
         Team team = this.func_96124_cp();
         return team == null || p_98034_1_ == null || p_98034_1_.func_96124_cp() != team || !team.func_98297_h();
      }
   }

   public abstract boolean func_175149_v();

   public ItemStack[] func_70035_c() {
      return this.field_71071_by.field_70460_b;
   }

   public boolean func_96092_aw() {
      return !this.field_71075_bZ.field_75100_b;
   }

   public Scoreboard func_96123_co() {
      return this.field_70170_p.func_96441_U();
   }

   public Team func_96124_cp() {
      return this.func_96123_co().func_96509_i(this.func_70005_c_());
   }

   public IChatComponent func_145748_c_() {
      IChatComponent ichatcomponent = new ChatComponentText(ScorePlayerTeam.func_96667_a(this.func_96124_cp(), this.func_70005_c_()));
      ichatcomponent.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + this.func_70005_c_() + " "));
      ichatcomponent.func_150256_b().func_150209_a(this.func_174823_aP());
      ichatcomponent.func_150256_b().func_179989_a(this.func_70005_c_());
      return ichatcomponent;
   }

   public float func_70047_e() {
      float f = 1.62F;
      if(this.func_70608_bn()) {
         f = 0.2F;
      }

      if(this.func_70093_af()) {
         f -= 0.08F;
      }

      return f;
   }

   public void func_110149_m(float p_110149_1_) {
      if(p_110149_1_ < 0.0F) {
         p_110149_1_ = 0.0F;
      }

      this.func_70096_w().func_75692_b(17, Float.valueOf(p_110149_1_));
   }

   public float func_110139_bj() {
      return this.func_70096_w().func_111145_d(17);
   }

   public static UUID func_146094_a(GameProfile p_146094_0_) {
      UUID uuid = p_146094_0_.getId();
      if(uuid == null) {
         uuid = func_175147_b(p_146094_0_.getName());
      }

      return uuid;
   }

   public static UUID func_175147_b(String p_175147_0_) {
      return UUID.nameUUIDFromBytes(("OfflinePlayer:" + p_175147_0_).getBytes(Charsets.UTF_8));
   }

   public boolean func_175146_a(LockCode p_175146_1_) {
      if(p_175146_1_.func_180160_a()) {
         return true;
      } else {
         ItemStack itemstack = this.func_71045_bC();
         return itemstack != null && itemstack.func_82837_s()?itemstack.func_82833_r().equals(p_175146_1_.func_180159_b()):false;
      }
   }

   public boolean func_175148_a(EnumPlayerModelParts p_175148_1_) {
      return (this.func_70096_w().func_75683_a(10) & p_175148_1_.func_179327_a()) == p_175148_1_.func_179327_a();
   }

   public boolean func_174792_t_() {
      return MinecraftServer.func_71276_C().field_71305_c[0].func_82736_K().func_82766_b("sendCommandFeedback");
   }

   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
      if(p_174820_1_ >= 0 && p_174820_1_ < this.field_71071_by.field_70462_a.length) {
         this.field_71071_by.func_70299_a(p_174820_1_, p_174820_2_);
         return true;
      } else {
         int i = p_174820_1_ - 100;
         if(i >= 0 && i < this.field_71071_by.field_70460_b.length) {
            int k = i + 1;
            if(p_174820_2_ != null && p_174820_2_.func_77973_b() != null) {
               if(p_174820_2_.func_77973_b() instanceof ItemArmor) {
                  if(EntityLiving.func_82159_b(p_174820_2_) != k) {
                     return false;
                  }
               } else if(k != 4 || p_174820_2_.func_77973_b() != Items.field_151144_bL && !(p_174820_2_.func_77973_b() instanceof ItemBlock)) {
                  return false;
               }
            }

            this.field_71071_by.func_70299_a(i + this.field_71071_by.field_70462_a.length, p_174820_2_);
            return true;
         } else {
            int j = p_174820_1_ - 200;
            if(j >= 0 && j < this.field_71078_a.func_70302_i_()) {
               this.field_71078_a.func_70299_a(j, p_174820_2_);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public boolean func_175140_cp() {
      return this.field_175153_bG;
   }

   public void func_175150_k(boolean p_175150_1_) {
      this.field_175153_bG = p_175150_1_;
   }

   public static enum EnumChatVisibility {
      FULL(0, "options.chat.visibility.full"),
      SYSTEM(1, "options.chat.visibility.system"),
      HIDDEN(2, "options.chat.visibility.hidden");

      private static final EntityPlayer.EnumChatVisibility[] field_151432_d = new EntityPlayer.EnumChatVisibility[values().length];
      private final int field_151433_e;
      private final String field_151430_f;

      private EnumChatVisibility(int p_i45323_3_, String p_i45323_4_) {
         this.field_151433_e = p_i45323_3_;
         this.field_151430_f = p_i45323_4_;
      }

      public int func_151428_a() {
         return this.field_151433_e;
      }

      public static EntityPlayer.EnumChatVisibility func_151426_a(int p_151426_0_) {
         return field_151432_d[p_151426_0_ % field_151432_d.length];
      }

      public String func_151429_b() {
         return this.field_151430_f;
      }

      static {
         for(EntityPlayer.EnumChatVisibility entityplayer$enumchatvisibility : values()) {
            field_151432_d[entityplayer$enumchatvisibility.field_151433_e] = entityplayer$enumchatvisibility;
         }

      }
   }

   public static enum EnumStatus {
      OK,
      NOT_POSSIBLE_HERE,
      NOT_POSSIBLE_NOW,
      TOO_FAR_AWAY,
      OTHER_PROBLEM,
      NOT_SAFE;
   }
}
