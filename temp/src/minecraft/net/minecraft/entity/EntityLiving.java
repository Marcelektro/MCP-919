package net.minecraft.entity;

import java.util.UUID;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class EntityLiving extends EntityLivingBase {
   public int field_70757_a;
   protected int field_70728_aV;
   private EntityLookHelper field_70749_g;
   protected EntityMoveHelper field_70765_h;
   protected EntityJumpHelper field_70767_i;
   private EntityBodyHelper field_70762_j;
   protected PathNavigate field_70699_by;
   protected final EntityAITasks field_70714_bg;
   protected final EntityAITasks field_70715_bh;
   private EntityLivingBase field_70696_bz;
   private EntitySenses field_70723_bA;
   private ItemStack[] field_82182_bS = new ItemStack[5];
   protected float[] field_82174_bp = new float[5];
   private boolean field_82172_bs;
   private boolean field_82179_bU;
   private boolean field_110169_bv;
   private Entity field_110168_bw;
   private NBTTagCompound field_110170_bx;

   public EntityLiving(World p_i1595_1_) {
      super(p_i1595_1_);
      this.field_70714_bg = new EntityAITasks(p_i1595_1_ != null && p_i1595_1_.field_72984_F != null?p_i1595_1_.field_72984_F:null);
      this.field_70715_bh = new EntityAITasks(p_i1595_1_ != null && p_i1595_1_.field_72984_F != null?p_i1595_1_.field_72984_F:null);
      this.field_70749_g = new EntityLookHelper(this);
      this.field_70765_h = new EntityMoveHelper(this);
      this.field_70767_i = new EntityJumpHelper(this);
      this.field_70762_j = new EntityBodyHelper(this);
      this.field_70699_by = this.func_175447_b(p_i1595_1_);
      this.field_70723_bA = new EntitySenses(this);

      for(int i = 0; i < this.field_82174_bp.length; ++i) {
         this.field_82174_bp[i] = 0.085F;
      }

   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0D);
   }

   protected PathNavigate func_175447_b(World p_175447_1_) {
      return new PathNavigateGround(this, p_175447_1_);
   }

   public EntityLookHelper func_70671_ap() {
      return this.field_70749_g;
   }

   public EntityMoveHelper func_70605_aq() {
      return this.field_70765_h;
   }

   public EntityJumpHelper func_70683_ar() {
      return this.field_70767_i;
   }

   public PathNavigate func_70661_as() {
      return this.field_70699_by;
   }

   public EntitySenses func_70635_at() {
      return this.field_70723_bA;
   }

   public EntityLivingBase func_70638_az() {
      return this.field_70696_bz;
   }

   public void func_70624_b(EntityLivingBase p_70624_1_) {
      this.field_70696_bz = p_70624_1_;
   }

   public boolean func_70686_a(Class<? extends EntityLivingBase> p_70686_1_) {
      return p_70686_1_ != EntityGhast.class;
   }

   public void func_70615_aA() {
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_75682_a(15, Byte.valueOf((byte)0));
   }

   public int func_70627_aG() {
      return 80;
   }

   public void func_70642_aH() {
      String s = this.func_70639_aQ();
      if(s != null) {
         this.func_85030_a(s, this.func_70599_aP(), this.func_70647_i());
      }

   }

   public void func_70030_z() {
      super.func_70030_z();
      this.field_70170_p.field_72984_F.func_76320_a("mobBaseTick");
      if(this.func_70089_S() && this.field_70146_Z.nextInt(1000) < this.field_70757_a++) {
         this.field_70757_a = -this.func_70627_aG();
         this.func_70642_aH();
      }

      this.field_70170_p.field_72984_F.func_76319_b();
   }

   protected int func_70693_a(EntityPlayer p_70693_1_) {
      if(this.field_70728_aV > 0) {
         int i = this.field_70728_aV;
         ItemStack[] aitemstack = this.func_70035_c();

         for(int j = 0; j < aitemstack.length; ++j) {
            if(aitemstack[j] != null && this.field_82174_bp[j] <= 1.0F) {
               i += 1 + this.field_70146_Z.nextInt(3);
            }
         }

         return i;
      } else {
         return this.field_70728_aV;
      }
   }

   public void func_70656_aK() {
      if(this.field_70170_p.field_72995_K) {
         for(int i = 0; i < 20; ++i) {
            double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
            double d3 = 10.0D;
            this.field_70170_p.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N - d0 * d3, this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O) - d1 * d3, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N - d2 * d3, d0, d1, d2, new int[0]);
         }
      } else {
         this.field_70170_p.func_72960_a(this, (byte)20);
      }

   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 20) {
         this.func_70656_aK();
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(!this.field_70170_p.field_72995_K) {
         this.func_110159_bB();
      }

   }

   protected float func_110146_f(float p_110146_1_, float p_110146_2_) {
      this.field_70762_j.func_75664_a();
      return p_110146_2_;
   }

   protected String func_70639_aQ() {
      return null;
   }

   protected Item func_146068_u() {
      return null;
   }

   protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
      Item item = this.func_146068_u();
      if(item != null) {
         int i = this.field_70146_Z.nextInt(3);
         if(p_70628_2_ > 0) {
            i += this.field_70146_Z.nextInt(p_70628_2_ + 1);
         }

         for(int j = 0; j < i; ++j) {
            this.func_145779_a(item, 1);
         }
      }

   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("CanPickUpLoot", this.func_98052_bS());
      p_70014_1_.func_74757_a("PersistenceRequired", this.field_82179_bU);
      NBTTagList nbttaglist = new NBTTagList();

      for(int i = 0; i < this.field_82182_bS.length; ++i) {
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         if(this.field_82182_bS[i] != null) {
            this.field_82182_bS[i].func_77955_b(nbttagcompound);
         }

         nbttaglist.func_74742_a(nbttagcompound);
      }

      p_70014_1_.func_74782_a("Equipment", nbttaglist);
      NBTTagList nbttaglist1 = new NBTTagList();

      for(int j = 0; j < this.field_82174_bp.length; ++j) {
         nbttaglist1.func_74742_a(new NBTTagFloat(this.field_82174_bp[j]));
      }

      p_70014_1_.func_74782_a("DropChances", nbttaglist1);
      p_70014_1_.func_74757_a("Leashed", this.field_110169_bv);
      if(this.field_110168_bw != null) {
         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
         if(this.field_110168_bw instanceof EntityLivingBase) {
            nbttagcompound1.func_74772_a("UUIDMost", this.field_110168_bw.func_110124_au().getMostSignificantBits());
            nbttagcompound1.func_74772_a("UUIDLeast", this.field_110168_bw.func_110124_au().getLeastSignificantBits());
         } else if(this.field_110168_bw instanceof EntityHanging) {
            BlockPos blockpos = ((EntityHanging)this.field_110168_bw).func_174857_n();
            nbttagcompound1.func_74768_a("X", blockpos.func_177958_n());
            nbttagcompound1.func_74768_a("Y", blockpos.func_177956_o());
            nbttagcompound1.func_74768_a("Z", blockpos.func_177952_p());
         }

         p_70014_1_.func_74782_a("Leash", nbttagcompound1);
      }

      if(this.func_175446_cd()) {
         p_70014_1_.func_74757_a("NoAI", this.func_175446_cd());
      }

   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      if(p_70037_1_.func_150297_b("CanPickUpLoot", 1)) {
         this.func_98053_h(p_70037_1_.func_74767_n("CanPickUpLoot"));
      }

      this.field_82179_bU = p_70037_1_.func_74767_n("PersistenceRequired");
      if(p_70037_1_.func_150297_b("Equipment", 9)) {
         NBTTagList nbttaglist = p_70037_1_.func_150295_c("Equipment", 10);

         for(int i = 0; i < this.field_82182_bS.length; ++i) {
            this.field_82182_bS[i] = ItemStack.func_77949_a(nbttaglist.func_150305_b(i));
         }
      }

      if(p_70037_1_.func_150297_b("DropChances", 9)) {
         NBTTagList nbttaglist1 = p_70037_1_.func_150295_c("DropChances", 5);

         for(int j = 0; j < nbttaglist1.func_74745_c(); ++j) {
            this.field_82174_bp[j] = nbttaglist1.func_150308_e(j);
         }
      }

      this.field_110169_bv = p_70037_1_.func_74767_n("Leashed");
      if(this.field_110169_bv && p_70037_1_.func_150297_b("Leash", 10)) {
         this.field_110170_bx = p_70037_1_.func_74775_l("Leash");
      }

      this.func_94061_f(p_70037_1_.func_74767_n("NoAI"));
   }

   public void func_70657_f(float p_70657_1_) {
      this.field_70701_bs = p_70657_1_;
   }

   public void func_70659_e(float p_70659_1_) {
      super.func_70659_e(p_70659_1_);
      this.func_70657_f(p_70659_1_);
   }

   public void func_70636_d() {
      super.func_70636_d();
      this.field_70170_p.field_72984_F.func_76320_a("looting");
      if(!this.field_70170_p.field_72995_K && this.func_98052_bS() && !this.field_70729_aU && this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
         for(EntityItem entityitem : this.field_70170_p.func_72872_a(EntityItem.class, this.func_174813_aQ().func_72314_b(1.0D, 0.0D, 1.0D))) {
            if(!entityitem.field_70128_L && entityitem.func_92059_d() != null && !entityitem.func_174874_s()) {
               this.func_175445_a(entityitem);
            }
         }
      }

      this.field_70170_p.field_72984_F.func_76319_b();
   }

   protected void func_175445_a(EntityItem p_175445_1_) {
      ItemStack itemstack = p_175445_1_.func_92059_d();
      int i = func_82159_b(itemstack);
      if(i > -1) {
         boolean flag = true;
         ItemStack itemstack1 = this.func_71124_b(i);
         if(itemstack1 != null) {
            if(i == 0) {
               if(itemstack.func_77973_b() instanceof ItemSword && !(itemstack1.func_77973_b() instanceof ItemSword)) {
                  flag = true;
               } else if(itemstack.func_77973_b() instanceof ItemSword && itemstack1.func_77973_b() instanceof ItemSword) {
                  ItemSword itemsword = (ItemSword)itemstack.func_77973_b();
                  ItemSword itemsword1 = (ItemSword)itemstack1.func_77973_b();
                  if(itemsword.func_150931_i() != itemsword1.func_150931_i()) {
                     flag = itemsword.func_150931_i() > itemsword1.func_150931_i();
                  } else {
                     flag = itemstack.func_77960_j() > itemstack1.func_77960_j() || itemstack.func_77942_o() && !itemstack1.func_77942_o();
                  }
               } else if(itemstack.func_77973_b() instanceof ItemBow && itemstack1.func_77973_b() instanceof ItemBow) {
                  flag = itemstack.func_77942_o() && !itemstack1.func_77942_o();
               } else {
                  flag = false;
               }
            } else if(itemstack.func_77973_b() instanceof ItemArmor && !(itemstack1.func_77973_b() instanceof ItemArmor)) {
               flag = true;
            } else if(itemstack.func_77973_b() instanceof ItemArmor && itemstack1.func_77973_b() instanceof ItemArmor) {
               ItemArmor itemarmor = (ItemArmor)itemstack.func_77973_b();
               ItemArmor itemarmor1 = (ItemArmor)itemstack1.func_77973_b();
               if(itemarmor.field_77879_b != itemarmor1.field_77879_b) {
                  flag = itemarmor.field_77879_b > itemarmor1.field_77879_b;
               } else {
                  flag = itemstack.func_77960_j() > itemstack1.func_77960_j() || itemstack.func_77942_o() && !itemstack1.func_77942_o();
               }
            } else {
               flag = false;
            }
         }

         if(flag && this.func_175448_a(itemstack)) {
            if(itemstack1 != null && this.field_70146_Z.nextFloat() - 0.1F < this.field_82174_bp[i]) {
               this.func_70099_a(itemstack1, 0.0F);
            }

            if(itemstack.func_77973_b() == Items.field_151045_i && p_175445_1_.func_145800_j() != null) {
               EntityPlayer entityplayer = this.field_70170_p.func_72924_a(p_175445_1_.func_145800_j());
               if(entityplayer != null) {
                  entityplayer.func_71029_a(AchievementList.field_150966_x);
               }
            }

            this.func_70062_b(i, itemstack);
            this.field_82174_bp[i] = 2.0F;
            this.field_82179_bU = true;
            this.func_71001_a(p_175445_1_, 1);
            p_175445_1_.func_70106_y();
         }
      }

   }

   protected boolean func_175448_a(ItemStack p_175448_1_) {
      return true;
   }

   protected boolean func_70692_ba() {
      return true;
   }

   protected void func_70623_bb() {
      if(this.field_82179_bU) {
         this.field_70708_bq = 0;
      } else {
         Entity entity = this.field_70170_p.func_72890_a(this, -1.0D);
         if(entity != null) {
            double d0 = entity.field_70165_t - this.field_70165_t;
            double d1 = entity.field_70163_u - this.field_70163_u;
            double d2 = entity.field_70161_v - this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if(this.func_70692_ba() && d3 > 16384.0D) {
               this.func_70106_y();
            }

            if(this.field_70708_bq > 600 && this.field_70146_Z.nextInt(800) == 0 && d3 > 1024.0D && this.func_70692_ba()) {
               this.func_70106_y();
            } else if(d3 < 1024.0D) {
               this.field_70708_bq = 0;
            }
         }

      }
   }

   protected final void func_70626_be() {
      ++this.field_70708_bq;
      this.field_70170_p.field_72984_F.func_76320_a("checkDespawn");
      this.func_70623_bb();
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("sensing");
      this.field_70723_bA.func_75523_a();
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("targetSelector");
      this.field_70715_bh.func_75774_a();
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("goalSelector");
      this.field_70714_bg.func_75774_a();
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("navigation");
      this.field_70699_by.func_75501_e();
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("mob tick");
      this.func_70619_bc();
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("controls");
      this.field_70170_p.field_72984_F.func_76320_a("move");
      this.field_70765_h.func_75641_c();
      this.field_70170_p.field_72984_F.func_76318_c("look");
      this.field_70749_g.func_75649_a();
      this.field_70170_p.field_72984_F.func_76318_c("jump");
      this.field_70767_i.func_75661_b();
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76319_b();
   }

   protected void func_70619_bc() {
   }

   public int func_70646_bf() {
      return 40;
   }

   public void func_70625_a(Entity p_70625_1_, float p_70625_2_, float p_70625_3_) {
      double d0 = p_70625_1_.field_70165_t - this.field_70165_t;
      double d2 = p_70625_1_.field_70161_v - this.field_70161_v;
      double d1;
      if(p_70625_1_ instanceof EntityLivingBase) {
         EntityLivingBase entitylivingbase = (EntityLivingBase)p_70625_1_;
         d1 = entitylivingbase.field_70163_u + (double)entitylivingbase.func_70047_e() - (this.field_70163_u + (double)this.func_70047_e());
      } else {
         d1 = (p_70625_1_.func_174813_aQ().field_72338_b + p_70625_1_.func_174813_aQ().field_72337_e) / 2.0D - (this.field_70163_u + (double)this.func_70047_e());
      }

      double d3 = (double)MathHelper.func_76133_a(d0 * d0 + d2 * d2);
      float f = (float)(MathHelper.func_181159_b(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
      float f1 = (float)(-(MathHelper.func_181159_b(d1, d3) * 180.0D / 3.1415927410125732D));
      this.field_70125_A = this.func_70663_b(this.field_70125_A, f1, p_70625_3_);
      this.field_70177_z = this.func_70663_b(this.field_70177_z, f, p_70625_2_);
   }

   private float func_70663_b(float p_70663_1_, float p_70663_2_, float p_70663_3_) {
      float f = MathHelper.func_76142_g(p_70663_2_ - p_70663_1_);
      if(f > p_70663_3_) {
         f = p_70663_3_;
      }

      if(f < -p_70663_3_) {
         f = -p_70663_3_;
      }

      return p_70663_1_ + f;
   }

   public boolean func_70601_bi() {
      return true;
   }

   public boolean func_70058_J() {
      return this.field_70170_p.func_72917_a(this.func_174813_aQ(), this) && this.field_70170_p.func_72945_a(this, this.func_174813_aQ()).isEmpty() && !this.field_70170_p.func_72953_d(this.func_174813_aQ());
   }

   public float func_70603_bj() {
      return 1.0F;
   }

   public int func_70641_bl() {
      return 4;
   }

   public int func_82143_as() {
      if(this.func_70638_az() == null) {
         return 3;
      } else {
         int i = (int)(this.func_110143_aJ() - this.func_110138_aP() * 0.33F);
         i = i - (3 - this.field_70170_p.func_175659_aa().func_151525_a()) * 4;
         if(i < 0) {
            i = 0;
         }

         return i + 3;
      }
   }

   public ItemStack func_70694_bm() {
      return this.field_82182_bS[0];
   }

   public ItemStack func_71124_b(int p_71124_1_) {
      return this.field_82182_bS[p_71124_1_];
   }

   public ItemStack func_82169_q(int p_82169_1_) {
      return this.field_82182_bS[p_82169_1_ + 1];
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
      this.field_82182_bS[p_70062_1_] = p_70062_2_;
   }

   public ItemStack[] func_70035_c() {
      return this.field_82182_bS;
   }

   protected void func_82160_b(boolean p_82160_1_, int p_82160_2_) {
      for(int i = 0; i < this.func_70035_c().length; ++i) {
         ItemStack itemstack = this.func_71124_b(i);
         boolean flag = this.field_82174_bp[i] > 1.0F;
         if(itemstack != null && (p_82160_1_ || flag) && this.field_70146_Z.nextFloat() - (float)p_82160_2_ * 0.01F < this.field_82174_bp[i]) {
            if(!flag && itemstack.func_77984_f()) {
               int j = Math.max(itemstack.func_77958_k() - 25, 1);
               int k = itemstack.func_77958_k() - this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(j) + 1);
               if(k > j) {
                  k = j;
               }

               if(k < 1) {
                  k = 1;
               }

               itemstack.func_77964_b(k);
            }

            this.func_70099_a(itemstack, 0.0F);
         }
      }

   }

   protected void func_180481_a(DifficultyInstance p_180481_1_) {
      if(this.field_70146_Z.nextFloat() < 0.15F * p_180481_1_.func_180170_c()) {
         int i = this.field_70146_Z.nextInt(2);
         float f = this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD?0.1F:0.25F;
         if(this.field_70146_Z.nextFloat() < 0.095F) {
            ++i;
         }

         if(this.field_70146_Z.nextFloat() < 0.095F) {
            ++i;
         }

         if(this.field_70146_Z.nextFloat() < 0.095F) {
            ++i;
         }

         for(int j = 3; j >= 0; --j) {
            ItemStack itemstack = this.func_82169_q(j);
            if(j < 3 && this.field_70146_Z.nextFloat() < f) {
               break;
            }

            if(itemstack == null) {
               Item item = func_82161_a(j + 1, i);
               if(item != null) {
                  this.func_70062_b(j + 1, new ItemStack(item));
               }
            }
         }
      }

   }

   public static int func_82159_b(ItemStack p_82159_0_) {
      if(p_82159_0_.func_77973_b() != Item.func_150898_a(Blocks.field_150423_aK) && p_82159_0_.func_77973_b() != Items.field_151144_bL) {
         if(p_82159_0_.func_77973_b() instanceof ItemArmor) {
            switch(((ItemArmor)p_82159_0_.func_77973_b()).field_77881_a) {
            case 0:
               return 4;
            case 1:
               return 3;
            case 2:
               return 2;
            case 3:
               return 1;
            }
         }

         return 0;
      } else {
         return 4;
      }
   }

   public static Item func_82161_a(int p_82161_0_, int p_82161_1_) {
      switch(p_82161_0_) {
      case 4:
         if(p_82161_1_ == 0) {
            return Items.field_151024_Q;
         } else if(p_82161_1_ == 1) {
            return Items.field_151169_ag;
         } else if(p_82161_1_ == 2) {
            return Items.field_151020_U;
         } else if(p_82161_1_ == 3) {
            return Items.field_151028_Y;
         } else if(p_82161_1_ == 4) {
            return Items.field_151161_ac;
         }
      case 3:
         if(p_82161_1_ == 0) {
            return Items.field_151027_R;
         } else if(p_82161_1_ == 1) {
            return Items.field_151171_ah;
         } else if(p_82161_1_ == 2) {
            return Items.field_151023_V;
         } else if(p_82161_1_ == 3) {
            return Items.field_151030_Z;
         } else if(p_82161_1_ == 4) {
            return Items.field_151163_ad;
         }
      case 2:
         if(p_82161_1_ == 0) {
            return Items.field_151026_S;
         } else if(p_82161_1_ == 1) {
            return Items.field_151149_ai;
         } else if(p_82161_1_ == 2) {
            return Items.field_151022_W;
         } else if(p_82161_1_ == 3) {
            return Items.field_151165_aa;
         } else if(p_82161_1_ == 4) {
            return Items.field_151173_ae;
         }
      case 1:
         if(p_82161_1_ == 0) {
            return Items.field_151021_T;
         } else if(p_82161_1_ == 1) {
            return Items.field_151151_aj;
         } else if(p_82161_1_ == 2) {
            return Items.field_151029_X;
         } else if(p_82161_1_ == 3) {
            return Items.field_151167_ab;
         } else if(p_82161_1_ == 4) {
            return Items.field_151175_af;
         }
      default:
         return null;
      }
   }

   protected void func_180483_b(DifficultyInstance p_180483_1_) {
      float f = p_180483_1_.func_180170_c();
      if(this.func_70694_bm() != null && this.field_70146_Z.nextFloat() < 0.25F * f) {
         EnchantmentHelper.func_77504_a(this.field_70146_Z, this.func_70694_bm(), (int)(5.0F + f * (float)this.field_70146_Z.nextInt(18)));
      }

      for(int i = 0; i < 4; ++i) {
         ItemStack itemstack = this.func_82169_q(i);
         if(itemstack != null && this.field_70146_Z.nextFloat() < 0.5F * f) {
            EnchantmentHelper.func_77504_a(this.field_70146_Z, itemstack, (int)(5.0F + f * (float)this.field_70146_Z.nextInt(18)));
         }
      }

   }

   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111121_a(new AttributeModifier("Random spawn bonus", this.field_70146_Z.nextGaussian() * 0.05D, 1));
      return p_180482_2_;
   }

   public boolean func_82171_bF() {
      return false;
   }

   public void func_110163_bv() {
      this.field_82179_bU = true;
   }

   public void func_96120_a(int p_96120_1_, float p_96120_2_) {
      this.field_82174_bp[p_96120_1_] = p_96120_2_;
   }

   public boolean func_98052_bS() {
      return this.field_82172_bs;
   }

   public void func_98053_h(boolean p_98053_1_) {
      this.field_82172_bs = p_98053_1_;
   }

   public boolean func_104002_bU() {
      return this.field_82179_bU;
   }

   public final boolean func_130002_c(EntityPlayer p_130002_1_) {
      if(this.func_110167_bD() && this.func_110166_bE() == p_130002_1_) {
         this.func_110160_i(true, !p_130002_1_.field_71075_bZ.field_75098_d);
         return true;
      } else {
         ItemStack itemstack = p_130002_1_.field_71071_by.func_70448_g();
         if(itemstack != null && itemstack.func_77973_b() == Items.field_151058_ca && this.func_110164_bC()) {
            if(!(this instanceof EntityTameable) || !((EntityTameable)this).func_70909_n()) {
               this.func_110162_b(p_130002_1_, true);
               --itemstack.field_77994_a;
               return true;
            }

            if(((EntityTameable)this).func_152114_e(p_130002_1_)) {
               this.func_110162_b(p_130002_1_, true);
               --itemstack.field_77994_a;
               return true;
            }
         }

         if(this.func_70085_c(p_130002_1_)) {
            return true;
         } else {
            return super.func_130002_c(p_130002_1_);
         }
      }
   }

   protected boolean func_70085_c(EntityPlayer p_70085_1_) {
      return false;
   }

   protected void func_110159_bB() {
      if(this.field_110170_bx != null) {
         this.func_110165_bF();
      }

      if(this.field_110169_bv) {
         if(!this.func_70089_S()) {
            this.func_110160_i(true, true);
         }

         if(this.field_110168_bw == null || this.field_110168_bw.field_70128_L) {
            this.func_110160_i(true, true);
         }
      }
   }

   public void func_110160_i(boolean p_110160_1_, boolean p_110160_2_) {
      if(this.field_110169_bv) {
         this.field_110169_bv = false;
         this.field_110168_bw = null;
         if(!this.field_70170_p.field_72995_K && p_110160_2_) {
            this.func_145779_a(Items.field_151058_ca, 1);
         }

         if(!this.field_70170_p.field_72995_K && p_110160_1_ && this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p).func_73039_n().func_151247_a(this, new S1BPacketEntityAttach(1, this, (Entity)null));
         }
      }

   }

   public boolean func_110164_bC() {
      return !this.func_110167_bD() && !(this instanceof IMob);
   }

   public boolean func_110167_bD() {
      return this.field_110169_bv;
   }

   public Entity func_110166_bE() {
      return this.field_110168_bw;
   }

   public void func_110162_b(Entity p_110162_1_, boolean p_110162_2_) {
      this.field_110169_bv = true;
      this.field_110168_bw = p_110162_1_;
      if(!this.field_70170_p.field_72995_K && p_110162_2_ && this.field_70170_p instanceof WorldServer) {
         ((WorldServer)this.field_70170_p).func_73039_n().func_151247_a(this, new S1BPacketEntityAttach(1, this, this.field_110168_bw));
      }

   }

   private void func_110165_bF() {
      if(this.field_110169_bv && this.field_110170_bx != null) {
         if(this.field_110170_bx.func_150297_b("UUIDMost", 4) && this.field_110170_bx.func_150297_b("UUIDLeast", 4)) {
            UUID uuid = new UUID(this.field_110170_bx.func_74763_f("UUIDMost"), this.field_110170_bx.func_74763_f("UUIDLeast"));

            for(EntityLivingBase entitylivingbase : this.field_70170_p.func_72872_a(EntityLivingBase.class, this.func_174813_aQ().func_72314_b(10.0D, 10.0D, 10.0D))) {
               if(entitylivingbase.func_110124_au().equals(uuid)) {
                  this.field_110168_bw = entitylivingbase;
                  break;
               }
            }
         } else if(this.field_110170_bx.func_150297_b("X", 99) && this.field_110170_bx.func_150297_b("Y", 99) && this.field_110170_bx.func_150297_b("Z", 99)) {
            BlockPos blockpos = new BlockPos(this.field_110170_bx.func_74762_e("X"), this.field_110170_bx.func_74762_e("Y"), this.field_110170_bx.func_74762_e("Z"));
            EntityLeashKnot entityleashknot = EntityLeashKnot.func_174863_b(this.field_70170_p, blockpos);
            if(entityleashknot == null) {
               entityleashknot = EntityLeashKnot.func_174862_a(this.field_70170_p, blockpos);
            }

            this.field_110168_bw = entityleashknot;
         } else {
            this.func_110160_i(false, true);
         }
      }

      this.field_110170_bx = null;
   }

   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
      int i;
      if(p_174820_1_ == 99) {
         i = 0;
      } else {
         i = p_174820_1_ - 100 + 1;
         if(i < 0 || i >= this.field_82182_bS.length) {
            return false;
         }
      }

      if(p_174820_2_ != null && func_82159_b(p_174820_2_) != i && (i != 4 || !(p_174820_2_.func_77973_b() instanceof ItemBlock))) {
         return false;
      } else {
         this.func_70062_b(i, p_174820_2_);
         return true;
      }
   }

   public boolean func_70613_aW() {
      return super.func_70613_aW() && !this.func_175446_cd();
   }

   public void func_94061_f(boolean p_94061_1_) {
      this.field_70180_af.func_75692_b(15, Byte.valueOf((byte)(p_94061_1_?1:0)));
   }

   public boolean func_175446_cd() {
      return this.field_70180_af.func_75683_a(15) != 0;
   }

   public static enum SpawnPlacementType {
      ON_GROUND,
      IN_AIR,
      IN_WATER;
   }
}
