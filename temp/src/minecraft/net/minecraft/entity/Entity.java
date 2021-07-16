package net.minecraft.entity;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public abstract class Entity implements ICommandSender {
   private static final AxisAlignedBB field_174836_a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
   private static int field_70152_a;
   private int field_145783_c;
   public double field_70155_l;
   public boolean field_70156_m;
   public Entity field_70153_n;
   public Entity field_70154_o;
   public boolean field_98038_p;
   public World field_70170_p;
   public double field_70169_q;
   public double field_70167_r;
   public double field_70166_s;
   public double field_70165_t;
   public double field_70163_u;
   public double field_70161_v;
   public double field_70159_w;
   public double field_70181_x;
   public double field_70179_y;
   public float field_70177_z;
   public float field_70125_A;
   public float field_70126_B;
   public float field_70127_C;
   private AxisAlignedBB field_70121_D;
   public boolean field_70122_E;
   public boolean field_70123_F;
   public boolean field_70124_G;
   public boolean field_70132_H;
   public boolean field_70133_I;
   protected boolean field_70134_J;
   private boolean field_174835_g;
   public boolean field_70128_L;
   public float field_70130_N;
   public float field_70131_O;
   public float field_70141_P;
   public float field_70140_Q;
   public float field_82151_R;
   public float field_70143_R;
   private int field_70150_b;
   public double field_70142_S;
   public double field_70137_T;
   public double field_70136_U;
   public float field_70138_W;
   public boolean field_70145_X;
   public float field_70144_Y;
   protected Random field_70146_Z;
   public int field_70173_aa;
   public int field_70174_ab;
   private int field_70151_c;
   protected boolean field_70171_ac;
   public int field_70172_ad;
   protected boolean field_70148_d;
   protected boolean field_70178_ae;
   protected DataWatcher field_70180_af;
   private double field_70149_e;
   private double field_70147_f;
   public boolean field_70175_ag;
   public int field_70176_ah;
   public int field_70162_ai;
   public int field_70164_aj;
   public int field_70118_ct;
   public int field_70117_cu;
   public int field_70116_cv;
   public boolean field_70158_ak;
   public boolean field_70160_al;
   public int field_71088_bW;
   protected boolean field_71087_bX;
   protected int field_82153_h;
   public int field_71093_bK;
   protected BlockPos field_181016_an;
   protected Vec3 field_181017_ao;
   protected EnumFacing field_181018_ap;
   private boolean field_83001_bt;
   protected UUID field_96093_i;
   private final CommandResultStats field_174837_as;

   public int func_145782_y() {
      return this.field_145783_c;
   }

   public void func_145769_d(int p_145769_1_) {
      this.field_145783_c = p_145769_1_;
   }

   public void func_174812_G() {
      this.func_70106_y();
   }

   public Entity(World p_i1582_1_) {
      this.field_145783_c = field_70152_a++;
      this.field_70155_l = 1.0D;
      this.field_70121_D = field_174836_a;
      this.field_70130_N = 0.6F;
      this.field_70131_O = 1.8F;
      this.field_70150_b = 1;
      this.field_70146_Z = new Random();
      this.field_70174_ab = 1;
      this.field_70148_d = true;
      this.field_96093_i = MathHelper.func_180182_a(this.field_70146_Z);
      this.field_174837_as = new CommandResultStats();
      this.field_70170_p = p_i1582_1_;
      this.func_70107_b(0.0D, 0.0D, 0.0D);
      if(p_i1582_1_ != null) {
         this.field_71093_bK = p_i1582_1_.field_73011_w.func_177502_q();
      }

      this.field_70180_af = new DataWatcher(this);
      this.field_70180_af.func_75682_a(0, Byte.valueOf((byte)0));
      this.field_70180_af.func_75682_a(1, Short.valueOf((short)300));
      this.field_70180_af.func_75682_a(3, Byte.valueOf((byte)0));
      this.field_70180_af.func_75682_a(2, "");
      this.field_70180_af.func_75682_a(4, Byte.valueOf((byte)0));
      this.func_70088_a();
   }

   protected abstract void func_70088_a();

   public DataWatcher func_70096_w() {
      return this.field_70180_af;
   }

   public boolean equals(Object p_equals_1_) {
      return p_equals_1_ instanceof Entity?((Entity)p_equals_1_).field_145783_c == this.field_145783_c:false;
   }

   public int hashCode() {
      return this.field_145783_c;
   }

   protected void func_70065_x() {
      if(this.field_70170_p != null) {
         while(this.field_70163_u > 0.0D && this.field_70163_u < 256.0D) {
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            if(this.field_70170_p.func_72945_a(this, this.func_174813_aQ()).isEmpty()) {
               break;
            }

            ++this.field_70163_u;
         }

         this.field_70159_w = this.field_70181_x = this.field_70179_y = 0.0D;
         this.field_70125_A = 0.0F;
      }
   }

   public void func_70106_y() {
      this.field_70128_L = true;
   }

   protected void func_70105_a(float p_70105_1_, float p_70105_2_) {
      if(p_70105_1_ != this.field_70130_N || p_70105_2_ != this.field_70131_O) {
         float f = this.field_70130_N;
         this.field_70130_N = p_70105_1_;
         this.field_70131_O = p_70105_2_;
         this.func_174826_a(new AxisAlignedBB(this.func_174813_aQ().field_72340_a, this.func_174813_aQ().field_72338_b, this.func_174813_aQ().field_72339_c, this.func_174813_aQ().field_72340_a + (double)this.field_70130_N, this.func_174813_aQ().field_72338_b + (double)this.field_70131_O, this.func_174813_aQ().field_72339_c + (double)this.field_70130_N));
         if(this.field_70130_N > f && !this.field_70148_d && !this.field_70170_p.field_72995_K) {
            this.func_70091_d((double)(f - this.field_70130_N), 0.0D, (double)(f - this.field_70130_N));
         }
      }

   }

   protected void func_70101_b(float p_70101_1_, float p_70101_2_) {
      this.field_70177_z = p_70101_1_ % 360.0F;
      this.field_70125_A = p_70101_2_ % 360.0F;
   }

   public void func_70107_b(double p_70107_1_, double p_70107_3_, double p_70107_5_) {
      this.field_70165_t = p_70107_1_;
      this.field_70163_u = p_70107_3_;
      this.field_70161_v = p_70107_5_;
      float f = this.field_70130_N / 2.0F;
      float f1 = this.field_70131_O;
      this.func_174826_a(new AxisAlignedBB(p_70107_1_ - (double)f, p_70107_3_, p_70107_5_ - (double)f, p_70107_1_ + (double)f, p_70107_3_ + (double)f1, p_70107_5_ + (double)f));
   }

   public void func_70082_c(float p_70082_1_, float p_70082_2_) {
      float f = this.field_70125_A;
      float f1 = this.field_70177_z;
      this.field_70177_z = (float)((double)this.field_70177_z + (double)p_70082_1_ * 0.15D);
      this.field_70125_A = (float)((double)this.field_70125_A - (double)p_70082_2_ * 0.15D);
      this.field_70125_A = MathHelper.func_76131_a(this.field_70125_A, -90.0F, 90.0F);
      this.field_70127_C += this.field_70125_A - f;
      this.field_70126_B += this.field_70177_z - f1;
   }

   public void func_70071_h_() {
      this.func_70030_z();
   }

   public void func_70030_z() {
      this.field_70170_p.field_72984_F.func_76320_a("entityBaseTick");
      if(this.field_70154_o != null && this.field_70154_o.field_70128_L) {
         this.field_70154_o = null;
      }

      this.field_70141_P = this.field_70140_Q;
      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      this.field_70127_C = this.field_70125_A;
      this.field_70126_B = this.field_70177_z;
      if(!this.field_70170_p.field_72995_K && this.field_70170_p instanceof WorldServer) {
         this.field_70170_p.field_72984_F.func_76320_a("portal");
         MinecraftServer minecraftserver = ((WorldServer)this.field_70170_p).func_73046_m();
         int i = this.func_82145_z();
         if(this.field_71087_bX) {
            if(minecraftserver.func_71255_r()) {
               if(this.field_70154_o == null && this.field_82153_h++ >= i) {
                  this.field_82153_h = i;
                  this.field_71088_bW = this.func_82147_ab();
                  int j;
                  if(this.field_70170_p.field_73011_w.func_177502_q() == -1) {
                     j = 0;
                  } else {
                     j = -1;
                  }

                  this.func_71027_c(j);
               }

               this.field_71087_bX = false;
            }
         } else {
            if(this.field_82153_h > 0) {
               this.field_82153_h -= 4;
            }

            if(this.field_82153_h < 0) {
               this.field_82153_h = 0;
            }
         }

         if(this.field_71088_bW > 0) {
            --this.field_71088_bW;
         }

         this.field_70170_p.field_72984_F.func_76319_b();
      }

      this.func_174830_Y();
      this.func_70072_I();
      if(this.field_70170_p.field_72995_K) {
         this.field_70151_c = 0;
      } else if(this.field_70151_c > 0) {
         if(this.field_70178_ae) {
            this.field_70151_c -= 4;
            if(this.field_70151_c < 0) {
               this.field_70151_c = 0;
            }
         } else {
            if(this.field_70151_c % 20 == 0) {
               this.func_70097_a(DamageSource.field_76370_b, 1.0F);
            }

            --this.field_70151_c;
         }
      }

      if(this.func_180799_ab()) {
         this.func_70044_A();
         this.field_70143_R *= 0.5F;
      }

      if(this.field_70163_u < -64.0D) {
         this.func_70076_C();
      }

      if(!this.field_70170_p.field_72995_K) {
         this.func_70052_a(0, this.field_70151_c > 0);
      }

      this.field_70148_d = false;
      this.field_70170_p.field_72984_F.func_76319_b();
   }

   public int func_82145_z() {
      return 0;
   }

   protected void func_70044_A() {
      if(!this.field_70178_ae) {
         this.func_70097_a(DamageSource.field_76371_c, 4.0F);
         this.func_70015_d(15);
      }
   }

   public void func_70015_d(int p_70015_1_) {
      int i = p_70015_1_ * 20;
      i = EnchantmentProtection.func_92093_a(this, i);
      if(this.field_70151_c < i) {
         this.field_70151_c = i;
      }

   }

   public void func_70066_B() {
      this.field_70151_c = 0;
   }

   protected void func_70076_C() {
      this.func_70106_y();
   }

   public boolean func_70038_c(double p_70038_1_, double p_70038_3_, double p_70038_5_) {
      AxisAlignedBB axisalignedbb = this.func_174813_aQ().func_72317_d(p_70038_1_, p_70038_3_, p_70038_5_);
      return this.func_174809_b(axisalignedbb);
   }

   private boolean func_174809_b(AxisAlignedBB p_174809_1_) {
      return this.field_70170_p.func_72945_a(this, p_174809_1_).isEmpty() && !this.field_70170_p.func_72953_d(p_174809_1_);
   }

   public void func_70091_d(double p_70091_1_, double p_70091_3_, double p_70091_5_) {
      if(this.field_70145_X) {
         this.func_174826_a(this.func_174813_aQ().func_72317_d(p_70091_1_, p_70091_3_, p_70091_5_));
         this.func_174829_m();
      } else {
         this.field_70170_p.field_72984_F.func_76320_a("move");
         double d0 = this.field_70165_t;
         double d1 = this.field_70163_u;
         double d2 = this.field_70161_v;
         if(this.field_70134_J) {
            this.field_70134_J = false;
            p_70091_1_ *= 0.25D;
            p_70091_3_ *= 0.05000000074505806D;
            p_70091_5_ *= 0.25D;
            this.field_70159_w = 0.0D;
            this.field_70181_x = 0.0D;
            this.field_70179_y = 0.0D;
         }

         double d3 = p_70091_1_;
         double d4 = p_70091_3_;
         double d5 = p_70091_5_;
         boolean flag = this.field_70122_E && this.func_70093_af() && this instanceof EntityPlayer;
         if(flag) {
            double d6;
            for(d6 = 0.05D; p_70091_1_ != 0.0D && this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72317_d(p_70091_1_, -1.0D, 0.0D)).isEmpty(); d3 = p_70091_1_) {
               if(p_70091_1_ < d6 && p_70091_1_ >= -d6) {
                  p_70091_1_ = 0.0D;
               } else if(p_70091_1_ > 0.0D) {
                  p_70091_1_ -= d6;
               } else {
                  p_70091_1_ += d6;
               }
            }

            for(; p_70091_5_ != 0.0D && this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72317_d(0.0D, -1.0D, p_70091_5_)).isEmpty(); d5 = p_70091_5_) {
               if(p_70091_5_ < d6 && p_70091_5_ >= -d6) {
                  p_70091_5_ = 0.0D;
               } else if(p_70091_5_ > 0.0D) {
                  p_70091_5_ -= d6;
               } else {
                  p_70091_5_ += d6;
               }
            }

            for(; p_70091_1_ != 0.0D && p_70091_5_ != 0.0D && this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72317_d(p_70091_1_, -1.0D, p_70091_5_)).isEmpty(); d5 = p_70091_5_) {
               if(p_70091_1_ < d6 && p_70091_1_ >= -d6) {
                  p_70091_1_ = 0.0D;
               } else if(p_70091_1_ > 0.0D) {
                  p_70091_1_ -= d6;
               } else {
                  p_70091_1_ += d6;
               }

               d3 = p_70091_1_;
               if(p_70091_5_ < d6 && p_70091_5_ >= -d6) {
                  p_70091_5_ = 0.0D;
               } else if(p_70091_5_ > 0.0D) {
                  p_70091_5_ -= d6;
               } else {
                  p_70091_5_ += d6;
               }
            }
         }

         List<AxisAlignedBB> list1 = this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72321_a(p_70091_1_, p_70091_3_, p_70091_5_));
         AxisAlignedBB axisalignedbb = this.func_174813_aQ();

         for(AxisAlignedBB axisalignedbb1 : list1) {
            p_70091_3_ = axisalignedbb1.func_72323_b(this.func_174813_aQ(), p_70091_3_);
         }

         this.func_174826_a(this.func_174813_aQ().func_72317_d(0.0D, p_70091_3_, 0.0D));
         boolean flag1 = this.field_70122_E || d4 != p_70091_3_ && d4 < 0.0D;

         for(AxisAlignedBB axisalignedbb2 : list1) {
            p_70091_1_ = axisalignedbb2.func_72316_a(this.func_174813_aQ(), p_70091_1_);
         }

         this.func_174826_a(this.func_174813_aQ().func_72317_d(p_70091_1_, 0.0D, 0.0D));

         for(AxisAlignedBB axisalignedbb13 : list1) {
            p_70091_5_ = axisalignedbb13.func_72322_c(this.func_174813_aQ(), p_70091_5_);
         }

         this.func_174826_a(this.func_174813_aQ().func_72317_d(0.0D, 0.0D, p_70091_5_));
         if(this.field_70138_W > 0.0F && flag1 && (d3 != p_70091_1_ || d5 != p_70091_5_)) {
            double d11 = p_70091_1_;
            double d7 = p_70091_3_;
            double d8 = p_70091_5_;
            AxisAlignedBB axisalignedbb3 = this.func_174813_aQ();
            this.func_174826_a(axisalignedbb);
            p_70091_3_ = (double)this.field_70138_W;
            List<AxisAlignedBB> list = this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72321_a(d3, p_70091_3_, d5));
            AxisAlignedBB axisalignedbb4 = this.func_174813_aQ();
            AxisAlignedBB axisalignedbb5 = axisalignedbb4.func_72321_a(d3, 0.0D, d5);
            double d9 = p_70091_3_;

            for(AxisAlignedBB axisalignedbb6 : list) {
               d9 = axisalignedbb6.func_72323_b(axisalignedbb5, d9);
            }

            axisalignedbb4 = axisalignedbb4.func_72317_d(0.0D, d9, 0.0D);
            double d15 = d3;

            for(AxisAlignedBB axisalignedbb7 : list) {
               d15 = axisalignedbb7.func_72316_a(axisalignedbb4, d15);
            }

            axisalignedbb4 = axisalignedbb4.func_72317_d(d15, 0.0D, 0.0D);
            double d16 = d5;

            for(AxisAlignedBB axisalignedbb8 : list) {
               d16 = axisalignedbb8.func_72322_c(axisalignedbb4, d16);
            }

            axisalignedbb4 = axisalignedbb4.func_72317_d(0.0D, 0.0D, d16);
            AxisAlignedBB axisalignedbb14 = this.func_174813_aQ();
            double d17 = p_70091_3_;

            for(AxisAlignedBB axisalignedbb9 : list) {
               d17 = axisalignedbb9.func_72323_b(axisalignedbb14, d17);
            }

            axisalignedbb14 = axisalignedbb14.func_72317_d(0.0D, d17, 0.0D);
            double d18 = d3;

            for(AxisAlignedBB axisalignedbb10 : list) {
               d18 = axisalignedbb10.func_72316_a(axisalignedbb14, d18);
            }

            axisalignedbb14 = axisalignedbb14.func_72317_d(d18, 0.0D, 0.0D);
            double d19 = d5;

            for(AxisAlignedBB axisalignedbb11 : list) {
               d19 = axisalignedbb11.func_72322_c(axisalignedbb14, d19);
            }

            axisalignedbb14 = axisalignedbb14.func_72317_d(0.0D, 0.0D, d19);
            double d20 = d15 * d15 + d16 * d16;
            double d10 = d18 * d18 + d19 * d19;
            if(d20 > d10) {
               p_70091_1_ = d15;
               p_70091_5_ = d16;
               p_70091_3_ = -d9;
               this.func_174826_a(axisalignedbb4);
            } else {
               p_70091_1_ = d18;
               p_70091_5_ = d19;
               p_70091_3_ = -d17;
               this.func_174826_a(axisalignedbb14);
            }

            for(AxisAlignedBB axisalignedbb12 : list) {
               p_70091_3_ = axisalignedbb12.func_72323_b(this.func_174813_aQ(), p_70091_3_);
            }

            this.func_174826_a(this.func_174813_aQ().func_72317_d(0.0D, p_70091_3_, 0.0D));
            if(d11 * d11 + d8 * d8 >= p_70091_1_ * p_70091_1_ + p_70091_5_ * p_70091_5_) {
               p_70091_1_ = d11;
               p_70091_3_ = d7;
               p_70091_5_ = d8;
               this.func_174826_a(axisalignedbb3);
            }
         }

         this.field_70170_p.field_72984_F.func_76319_b();
         this.field_70170_p.field_72984_F.func_76320_a("rest");
         this.func_174829_m();
         this.field_70123_F = d3 != p_70091_1_ || d5 != p_70091_5_;
         this.field_70124_G = d4 != p_70091_3_;
         this.field_70122_E = this.field_70124_G && d4 < 0.0D;
         this.field_70132_H = this.field_70123_F || this.field_70124_G;
         int i = MathHelper.func_76128_c(this.field_70165_t);
         int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D);
         int k = MathHelper.func_76128_c(this.field_70161_v);
         BlockPos blockpos = new BlockPos(i, j, k);
         Block block1 = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
         if(block1.func_149688_o() == Material.field_151579_a) {
            Block block = this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
            if(block instanceof BlockFence || block instanceof BlockWall || block instanceof BlockFenceGate) {
               block1 = block;
               blockpos = blockpos.func_177977_b();
            }
         }

         this.func_180433_a(p_70091_3_, this.field_70122_E, block1, blockpos);
         if(d3 != p_70091_1_) {
            this.field_70159_w = 0.0D;
         }

         if(d5 != p_70091_5_) {
            this.field_70179_y = 0.0D;
         }

         if(d4 != p_70091_3_) {
            block1.func_176216_a(this.field_70170_p, this);
         }

         if(this.func_70041_e_() && !flag && this.field_70154_o == null) {
            double d12 = this.field_70165_t - d0;
            double d13 = this.field_70163_u - d1;
            double d14 = this.field_70161_v - d2;
            if(block1 != Blocks.field_150468_ap) {
               d13 = 0.0D;
            }

            if(block1 != null && this.field_70122_E) {
               block1.func_176199_a(this.field_70170_p, blockpos, this);
            }

            this.field_70140_Q = (float)((double)this.field_70140_Q + (double)MathHelper.func_76133_a(d12 * d12 + d14 * d14) * 0.6D);
            this.field_82151_R = (float)((double)this.field_82151_R + (double)MathHelper.func_76133_a(d12 * d12 + d13 * d13 + d14 * d14) * 0.6D);
            if(this.field_82151_R > (float)this.field_70150_b && block1.func_149688_o() != Material.field_151579_a) {
               this.field_70150_b = (int)this.field_82151_R + 1;
               if(this.func_70090_H()) {
                  float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w * 0.20000000298023224D + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y * 0.20000000298023224D) * 0.35F;
                  if(f > 1.0F) {
                     f = 1.0F;
                  }

                  this.func_85030_a(this.func_145776_H(), f, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
               }

               this.func_180429_a(blockpos, block1);
            }
         }

         try {
            this.func_145775_I();
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Checking entity block collision");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being checked for collision");
            this.func_85029_a(crashreportcategory);
            throw new ReportedException(crashreport);
         }

         boolean flag2 = this.func_70026_G();
         if(this.field_70170_p.func_147470_e(this.func_174813_aQ().func_72331_e(0.001D, 0.001D, 0.001D))) {
            this.func_70081_e(1);
            if(!flag2) {
               ++this.field_70151_c;
               if(this.field_70151_c == 0) {
                  this.func_70015_d(8);
               }
            }
         } else if(this.field_70151_c <= 0) {
            this.field_70151_c = -this.field_70174_ab;
         }

         if(flag2 && this.field_70151_c > 0) {
            this.func_85030_a("random.fizz", 0.7F, 1.6F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
            this.field_70151_c = -this.field_70174_ab;
         }

         this.field_70170_p.field_72984_F.func_76319_b();
      }
   }

   private void func_174829_m() {
      this.field_70165_t = (this.func_174813_aQ().field_72340_a + this.func_174813_aQ().field_72336_d) / 2.0D;
      this.field_70163_u = this.func_174813_aQ().field_72338_b;
      this.field_70161_v = (this.func_174813_aQ().field_72339_c + this.func_174813_aQ().field_72334_f) / 2.0D;
   }

   protected String func_145776_H() {
      return "game.neutral.swim";
   }

   protected void func_145775_I() {
      BlockPos blockpos = new BlockPos(this.func_174813_aQ().field_72340_a + 0.001D, this.func_174813_aQ().field_72338_b + 0.001D, this.func_174813_aQ().field_72339_c + 0.001D);
      BlockPos blockpos1 = new BlockPos(this.func_174813_aQ().field_72336_d - 0.001D, this.func_174813_aQ().field_72337_e - 0.001D, this.func_174813_aQ().field_72334_f - 0.001D);
      if(this.field_70170_p.func_175707_a(blockpos, blockpos1)) {
         for(int i = blockpos.func_177958_n(); i <= blockpos1.func_177958_n(); ++i) {
            for(int j = blockpos.func_177956_o(); j <= blockpos1.func_177956_o(); ++j) {
               for(int k = blockpos.func_177952_p(); k <= blockpos1.func_177952_p(); ++k) {
                  BlockPos blockpos2 = new BlockPos(i, j, k);
                  IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos2);

                  try {
                     iblockstate.func_177230_c().func_180634_a(this.field_70170_p, blockpos2, iblockstate, this);
                  } catch (Throwable throwable) {
                     CrashReport crashreport = CrashReport.func_85055_a(throwable, "Colliding entity with block");
                     CrashReportCategory crashreportcategory = crashreport.func_85058_a("Block being collided with");
                     CrashReportCategory.func_175750_a(crashreportcategory, blockpos2, iblockstate);
                     throw new ReportedException(crashreport);
                  }
               }
            }
         }
      }

   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      Block.SoundType block$soundtype = p_180429_2_.field_149762_H;
      if(this.field_70170_p.func_180495_p(p_180429_1_.func_177984_a()).func_177230_c() == Blocks.field_150431_aC) {
         block$soundtype = Blocks.field_150431_aC.field_149762_H;
         this.func_85030_a(block$soundtype.func_150498_e(), block$soundtype.func_150497_c() * 0.15F, block$soundtype.func_150494_d());
      } else if(!p_180429_2_.func_149688_o().func_76224_d()) {
         this.func_85030_a(block$soundtype.func_150498_e(), block$soundtype.func_150497_c() * 0.15F, block$soundtype.func_150494_d());
      }

   }

   public void func_85030_a(String p_85030_1_, float p_85030_2_, float p_85030_3_) {
      if(!this.func_174814_R()) {
         this.field_70170_p.func_72956_a(this, p_85030_1_, p_85030_2_, p_85030_3_);
      }

   }

   public boolean func_174814_R() {
      return this.field_70180_af.func_75683_a(4) == 1;
   }

   public void func_174810_b(boolean p_174810_1_) {
      this.field_70180_af.func_75692_b(4, Byte.valueOf((byte)(p_174810_1_?1:0)));
   }

   protected boolean func_70041_e_() {
      return true;
   }

   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
      if(p_180433_3_) {
         if(this.field_70143_R > 0.0F) {
            if(p_180433_4_ != null) {
               p_180433_4_.func_180658_a(this.field_70170_p, p_180433_5_, this, this.field_70143_R);
            } else {
               this.func_180430_e(this.field_70143_R, 1.0F);
            }

            this.field_70143_R = 0.0F;
         }
      } else if(p_180433_1_ < 0.0D) {
         this.field_70143_R = (float)((double)this.field_70143_R - p_180433_1_);
      }

   }

   public AxisAlignedBB func_70046_E() {
      return null;
   }

   protected void func_70081_e(int p_70081_1_) {
      if(!this.field_70178_ae) {
         this.func_70097_a(DamageSource.field_76372_a, (float)p_70081_1_);
      }

   }

   public final boolean func_70045_F() {
      return this.field_70178_ae;
   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
      if(this.field_70153_n != null) {
         this.field_70153_n.func_180430_e(p_180430_1_, p_180430_2_);
      }

   }

   public boolean func_70026_G() {
      return this.field_70171_ac || this.field_70170_p.func_175727_C(new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v)) || this.field_70170_p.func_175727_C(new BlockPos(this.field_70165_t, this.field_70163_u + (double)this.field_70131_O, this.field_70161_v));
   }

   public boolean func_70090_H() {
      return this.field_70171_ac;
   }

   public boolean func_70072_I() {
      if(this.field_70170_p.func_72918_a(this.func_174813_aQ().func_72314_b(0.0D, -0.4000000059604645D, 0.0D).func_72331_e(0.001D, 0.001D, 0.001D), Material.field_151586_h, this)) {
         if(!this.field_70171_ac && !this.field_70148_d) {
            this.func_71061_d_();
         }

         this.field_70143_R = 0.0F;
         this.field_70171_ac = true;
         this.field_70151_c = 0;
      } else {
         this.field_70171_ac = false;
      }

      return this.field_70171_ac;
   }

   protected void func_71061_d_() {
      float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w * 0.20000000298023224D + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y * 0.20000000298023224D) * 0.2F;
      if(f > 1.0F) {
         f = 1.0F;
      }

      this.func_85030_a(this.func_145777_O(), f, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.4F);
      float f1 = (float)MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b);

      for(int i = 0; (float)i < 1.0F + this.field_70130_N * 20.0F; ++i) {
         float f2 = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         float f3 = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t + (double)f2, (double)(f1 + 1.0F), this.field_70161_v + (double)f3, this.field_70159_w, this.field_70181_x - (double)(this.field_70146_Z.nextFloat() * 0.2F), this.field_70179_y, new int[0]);
      }

      for(int j = 0; (float)j < 1.0F + this.field_70130_N * 20.0F; ++j) {
         float f4 = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         float f5 = (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * this.field_70130_N;
         this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_SPLASH, this.field_70165_t + (double)f4, (double)(f1 + 1.0F), this.field_70161_v + (double)f5, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
      }

   }

   public void func_174830_Y() {
      if(this.func_70051_ag() && !this.func_70090_H()) {
         this.func_174808_Z();
      }

   }

   protected void func_174808_Z() {
      int i = MathHelper.func_76128_c(this.field_70165_t);
      int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D);
      int k = MathHelper.func_76128_c(this.field_70161_v);
      BlockPos blockpos = new BlockPos(i, j, k);
      IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
      Block block = iblockstate.func_177230_c();
      if(block.func_149645_b() != -1) {
         this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + ((double)this.field_70146_Z.nextFloat() - 0.5D) * (double)this.field_70130_N, this.func_174813_aQ().field_72338_b + 0.1D, this.field_70161_v + ((double)this.field_70146_Z.nextFloat() - 0.5D) * (double)this.field_70130_N, -this.field_70159_w * 4.0D, 1.5D, -this.field_70179_y * 4.0D, new int[]{Block.func_176210_f(iblockstate)});
      }

   }

   protected String func_145777_O() {
      return "game.neutral.swim.splash";
   }

   public boolean func_70055_a(Material p_70055_1_) {
      double d0 = this.field_70163_u + (double)this.func_70047_e();
      BlockPos blockpos = new BlockPos(this.field_70165_t, d0, this.field_70161_v);
      IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
      Block block = iblockstate.func_177230_c();
      if(block.func_149688_o() == p_70055_1_) {
         float f = BlockLiquid.func_149801_b(iblockstate.func_177230_c().func_176201_c(iblockstate)) - 0.11111111F;
         float f1 = (float)(blockpos.func_177956_o() + 1) - f;
         boolean flag = d0 < (double)f1;
         return !flag && this instanceof EntityPlayer?false:flag;
      } else {
         return false;
      }
   }

   public boolean func_180799_ab() {
      return this.field_70170_p.func_72875_a(this.func_174813_aQ().func_72314_b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.field_151587_i);
   }

   public void func_70060_a(float p_70060_1_, float p_70060_2_, float p_70060_3_) {
      float f = p_70060_1_ * p_70060_1_ + p_70060_2_ * p_70060_2_;
      if(f >= 1.0E-4F) {
         f = MathHelper.func_76129_c(f);
         if(f < 1.0F) {
            f = 1.0F;
         }

         f = p_70060_3_ / f;
         p_70060_1_ = p_70060_1_ * f;
         p_70060_2_ = p_70060_2_ * f;
         float f1 = MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F);
         float f2 = MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F);
         this.field_70159_w += (double)(p_70060_1_ * f2 - p_70060_2_ * f1);
         this.field_70179_y += (double)(p_70060_2_ * f2 + p_70060_1_ * f1);
      }
   }

   public int func_70070_b(float p_70070_1_) {
      BlockPos blockpos = new BlockPos(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
      return this.field_70170_p.func_175667_e(blockpos)?this.field_70170_p.func_175626_b(blockpos, 0):0;
   }

   public float func_70013_c(float p_70013_1_) {
      BlockPos blockpos = new BlockPos(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
      return this.field_70170_p.func_175667_e(blockpos)?this.field_70170_p.func_175724_o(blockpos):0.0F;
   }

   public void func_70029_a(World p_70029_1_) {
      this.field_70170_p = p_70029_1_;
   }

   public void func_70080_a(double p_70080_1_, double p_70080_3_, double p_70080_5_, float p_70080_7_, float p_70080_8_) {
      this.field_70169_q = this.field_70165_t = p_70080_1_;
      this.field_70167_r = this.field_70163_u = p_70080_3_;
      this.field_70166_s = this.field_70161_v = p_70080_5_;
      this.field_70126_B = this.field_70177_z = p_70080_7_;
      this.field_70127_C = this.field_70125_A = p_70080_8_;
      double d0 = (double)(this.field_70126_B - p_70080_7_);
      if(d0 < -180.0D) {
         this.field_70126_B += 360.0F;
      }

      if(d0 >= 180.0D) {
         this.field_70126_B -= 360.0F;
      }

      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.func_70101_b(p_70080_7_, p_70080_8_);
   }

   public void func_174828_a(BlockPos p_174828_1_, float p_174828_2_, float p_174828_3_) {
      this.func_70012_b((double)p_174828_1_.func_177958_n() + 0.5D, (double)p_174828_1_.func_177956_o(), (double)p_174828_1_.func_177952_p() + 0.5D, p_174828_2_, p_174828_3_);
   }

   public void func_70012_b(double p_70012_1_, double p_70012_3_, double p_70012_5_, float p_70012_7_, float p_70012_8_) {
      this.field_70142_S = this.field_70169_q = this.field_70165_t = p_70012_1_;
      this.field_70137_T = this.field_70167_r = this.field_70163_u = p_70012_3_;
      this.field_70136_U = this.field_70166_s = this.field_70161_v = p_70012_5_;
      this.field_70177_z = p_70012_7_;
      this.field_70125_A = p_70012_8_;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public float func_70032_d(Entity p_70032_1_) {
      float f = (float)(this.field_70165_t - p_70032_1_.field_70165_t);
      float f1 = (float)(this.field_70163_u - p_70032_1_.field_70163_u);
      float f2 = (float)(this.field_70161_v - p_70032_1_.field_70161_v);
      return MathHelper.func_76129_c(f * f + f1 * f1 + f2 * f2);
   }

   public double func_70092_e(double p_70092_1_, double p_70092_3_, double p_70092_5_) {
      double d0 = this.field_70165_t - p_70092_1_;
      double d1 = this.field_70163_u - p_70092_3_;
      double d2 = this.field_70161_v - p_70092_5_;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public double func_174818_b(BlockPos p_174818_1_) {
      return p_174818_1_.func_177954_c(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public double func_174831_c(BlockPos p_174831_1_) {
      return p_174831_1_.func_177957_d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public double func_70011_f(double p_70011_1_, double p_70011_3_, double p_70011_5_) {
      double d0 = this.field_70165_t - p_70011_1_;
      double d1 = this.field_70163_u - p_70011_3_;
      double d2 = this.field_70161_v - p_70011_5_;
      return (double)MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
   }

   public double func_70068_e(Entity p_70068_1_) {
      double d0 = this.field_70165_t - p_70068_1_.field_70165_t;
      double d1 = this.field_70163_u - p_70068_1_.field_70163_u;
      double d2 = this.field_70161_v - p_70068_1_.field_70161_v;
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public void func_70100_b_(EntityPlayer p_70100_1_) {
   }

   public void func_70108_f(Entity p_70108_1_) {
      if(p_70108_1_.field_70153_n != this && p_70108_1_.field_70154_o != this) {
         if(!p_70108_1_.field_70145_X && !this.field_70145_X) {
            double d0 = p_70108_1_.field_70165_t - this.field_70165_t;
            double d1 = p_70108_1_.field_70161_v - this.field_70161_v;
            double d2 = MathHelper.func_76132_a(d0, d1);
            if(d2 >= 0.009999999776482582D) {
               d2 = (double)MathHelper.func_76133_a(d2);
               d0 = d0 / d2;
               d1 = d1 / d2;
               double d3 = 1.0D / d2;
               if(d3 > 1.0D) {
                  d3 = 1.0D;
               }

               d0 = d0 * d3;
               d1 = d1 * d3;
               d0 = d0 * 0.05000000074505806D;
               d1 = d1 * 0.05000000074505806D;
               d0 = d0 * (double)(1.0F - this.field_70144_Y);
               d1 = d1 * (double)(1.0F - this.field_70144_Y);
               if(this.field_70153_n == null) {
                  this.func_70024_g(-d0, 0.0D, -d1);
               }

               if(p_70108_1_.field_70153_n == null) {
                  p_70108_1_.func_70024_g(d0, 0.0D, d1);
               }
            }

         }
      }
   }

   public void func_70024_g(double p_70024_1_, double p_70024_3_, double p_70024_5_) {
      this.field_70159_w += p_70024_1_;
      this.field_70181_x += p_70024_3_;
      this.field_70179_y += p_70024_5_;
      this.field_70160_al = true;
   }

   protected void func_70018_K() {
      this.field_70133_I = true;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_180431_b(p_70097_1_)) {
         return false;
      } else {
         this.func_70018_K();
         return false;
      }
   }

   public Vec3 func_70676_i(float p_70676_1_) {
      if(p_70676_1_ == 1.0F) {
         return this.func_174806_f(this.field_70125_A, this.field_70177_z);
      } else {
         float f = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * p_70676_1_;
         float f1 = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * p_70676_1_;
         return this.func_174806_f(f, f1);
      }
   }

   protected final Vec3 func_174806_f(float p_174806_1_, float p_174806_2_) {
      float f = MathHelper.func_76134_b(-p_174806_2_ * 0.017453292F - 3.1415927F);
      float f1 = MathHelper.func_76126_a(-p_174806_2_ * 0.017453292F - 3.1415927F);
      float f2 = -MathHelper.func_76134_b(-p_174806_1_ * 0.017453292F);
      float f3 = MathHelper.func_76126_a(-p_174806_1_ * 0.017453292F);
      return new Vec3((double)(f1 * f2), (double)f3, (double)(f * f2));
   }

   public Vec3 func_174824_e(float p_174824_1_) {
      if(p_174824_1_ == 1.0F) {
         return new Vec3(this.field_70165_t, this.field_70163_u + (double)this.func_70047_e(), this.field_70161_v);
      } else {
         double d0 = this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_174824_1_;
         double d1 = this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_174824_1_ + (double)this.func_70047_e();
         double d2 = this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_174824_1_;
         return new Vec3(d0, d1, d2);
      }
   }

   public MovingObjectPosition func_174822_a(double p_174822_1_, float p_174822_3_) {
      Vec3 vec3 = this.func_174824_e(p_174822_3_);
      Vec3 vec31 = this.func_70676_i(p_174822_3_);
      Vec3 vec32 = vec3.func_72441_c(vec31.field_72450_a * p_174822_1_, vec31.field_72448_b * p_174822_1_, vec31.field_72449_c * p_174822_1_);
      return this.field_70170_p.func_147447_a(vec3, vec32, false, false, true);
   }

   public boolean func_70067_L() {
      return false;
   }

   public boolean func_70104_M() {
      return false;
   }

   public void func_70084_c(Entity p_70084_1_, int p_70084_2_) {
   }

   public boolean func_145770_h(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
      double d0 = this.field_70165_t - p_145770_1_;
      double d1 = this.field_70163_u - p_145770_3_;
      double d2 = this.field_70161_v - p_145770_5_;
      double d3 = d0 * d0 + d1 * d1 + d2 * d2;
      return this.func_70112_a(d3);
   }

   public boolean func_70112_a(double p_70112_1_) {
      double d0 = this.func_174813_aQ().func_72320_b();
      if(Double.isNaN(d0)) {
         d0 = 1.0D;
      }

      d0 = d0 * 64.0D * this.field_70155_l;
      return p_70112_1_ < d0 * d0;
   }

   public boolean func_98035_c(NBTTagCompound p_98035_1_) {
      String s = this.func_70022_Q();
      if(!this.field_70128_L && s != null) {
         p_98035_1_.func_74778_a("id", s);
         this.func_70109_d(p_98035_1_);
         return true;
      } else {
         return false;
      }
   }

   public boolean func_70039_c(NBTTagCompound p_70039_1_) {
      String s = this.func_70022_Q();
      if(!this.field_70128_L && s != null && this.field_70153_n == null) {
         p_70039_1_.func_74778_a("id", s);
         this.func_70109_d(p_70039_1_);
         return true;
      } else {
         return false;
      }
   }

   public void func_70109_d(NBTTagCompound p_70109_1_) {
      try {
         p_70109_1_.func_74782_a("Pos", this.func_70087_a(new double[]{this.field_70165_t, this.field_70163_u, this.field_70161_v}));
         p_70109_1_.func_74782_a("Motion", this.func_70087_a(new double[]{this.field_70159_w, this.field_70181_x, this.field_70179_y}));
         p_70109_1_.func_74782_a("Rotation", this.func_70049_a(new float[]{this.field_70177_z, this.field_70125_A}));
         p_70109_1_.func_74776_a("FallDistance", this.field_70143_R);
         p_70109_1_.func_74777_a("Fire", (short)this.field_70151_c);
         p_70109_1_.func_74777_a("Air", (short)this.func_70086_ai());
         p_70109_1_.func_74757_a("OnGround", this.field_70122_E);
         p_70109_1_.func_74768_a("Dimension", this.field_71093_bK);
         p_70109_1_.func_74757_a("Invulnerable", this.field_83001_bt);
         p_70109_1_.func_74768_a("PortalCooldown", this.field_71088_bW);
         p_70109_1_.func_74772_a("UUIDMost", this.func_110124_au().getMostSignificantBits());
         p_70109_1_.func_74772_a("UUIDLeast", this.func_110124_au().getLeastSignificantBits());
         if(this.func_95999_t() != null && this.func_95999_t().length() > 0) {
            p_70109_1_.func_74778_a("CustomName", this.func_95999_t());
            p_70109_1_.func_74757_a("CustomNameVisible", this.func_174833_aM());
         }

         this.field_174837_as.func_179670_b(p_70109_1_);
         if(this.func_174814_R()) {
            p_70109_1_.func_74757_a("Silent", this.func_174814_R());
         }

         this.func_70014_b(p_70109_1_);
         if(this.field_70154_o != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            if(this.field_70154_o.func_98035_c(nbttagcompound)) {
               p_70109_1_.func_74782_a("Riding", nbttagcompound);
            }
         }

      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Saving entity NBT");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being saved");
         this.func_85029_a(crashreportcategory);
         throw new ReportedException(crashreport);
      }
   }

   public void func_70020_e(NBTTagCompound p_70020_1_) {
      try {
         NBTTagList nbttaglist = p_70020_1_.func_150295_c("Pos", 6);
         NBTTagList nbttaglist1 = p_70020_1_.func_150295_c("Motion", 6);
         NBTTagList nbttaglist2 = p_70020_1_.func_150295_c("Rotation", 5);
         this.field_70159_w = nbttaglist1.func_150309_d(0);
         this.field_70181_x = nbttaglist1.func_150309_d(1);
         this.field_70179_y = nbttaglist1.func_150309_d(2);
         if(Math.abs(this.field_70159_w) > 10.0D) {
            this.field_70159_w = 0.0D;
         }

         if(Math.abs(this.field_70181_x) > 10.0D) {
            this.field_70181_x = 0.0D;
         }

         if(Math.abs(this.field_70179_y) > 10.0D) {
            this.field_70179_y = 0.0D;
         }

         this.field_70169_q = this.field_70142_S = this.field_70165_t = nbttaglist.func_150309_d(0);
         this.field_70167_r = this.field_70137_T = this.field_70163_u = nbttaglist.func_150309_d(1);
         this.field_70166_s = this.field_70136_U = this.field_70161_v = nbttaglist.func_150309_d(2);
         this.field_70126_B = this.field_70177_z = nbttaglist2.func_150308_e(0);
         this.field_70127_C = this.field_70125_A = nbttaglist2.func_150308_e(1);
         this.func_70034_d(this.field_70177_z);
         this.func_181013_g(this.field_70177_z);
         this.field_70143_R = p_70020_1_.func_74760_g("FallDistance");
         this.field_70151_c = p_70020_1_.func_74765_d("Fire");
         this.func_70050_g(p_70020_1_.func_74765_d("Air"));
         this.field_70122_E = p_70020_1_.func_74767_n("OnGround");
         this.field_71093_bK = p_70020_1_.func_74762_e("Dimension");
         this.field_83001_bt = p_70020_1_.func_74767_n("Invulnerable");
         this.field_71088_bW = p_70020_1_.func_74762_e("PortalCooldown");
         if(p_70020_1_.func_150297_b("UUIDMost", 4) && p_70020_1_.func_150297_b("UUIDLeast", 4)) {
            this.field_96093_i = new UUID(p_70020_1_.func_74763_f("UUIDMost"), p_70020_1_.func_74763_f("UUIDLeast"));
         } else if(p_70020_1_.func_150297_b("UUID", 8)) {
            this.field_96093_i = UUID.fromString(p_70020_1_.func_74779_i("UUID"));
         }

         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
         if(p_70020_1_.func_150297_b("CustomName", 8) && p_70020_1_.func_74779_i("CustomName").length() > 0) {
            this.func_96094_a(p_70020_1_.func_74779_i("CustomName"));
         }

         this.func_174805_g(p_70020_1_.func_74767_n("CustomNameVisible"));
         this.field_174837_as.func_179668_a(p_70020_1_);
         this.func_174810_b(p_70020_1_.func_74767_n("Silent"));
         this.func_70037_a(p_70020_1_);
         if(this.func_142008_O()) {
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         }

      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Loading entity NBT");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being loaded");
         this.func_85029_a(crashreportcategory);
         throw new ReportedException(crashreport);
      }
   }

   protected boolean func_142008_O() {
      return true;
   }

   protected final String func_70022_Q() {
      return EntityList.func_75621_b(this);
   }

   protected abstract void func_70037_a(NBTTagCompound var1);

   protected abstract void func_70014_b(NBTTagCompound var1);

   public void func_110123_P() {
   }

   protected NBTTagList func_70087_a(double... p_70087_1_) {
      NBTTagList nbttaglist = new NBTTagList();

      for(double d0 : p_70087_1_) {
         nbttaglist.func_74742_a(new NBTTagDouble(d0));
      }

      return nbttaglist;
   }

   protected NBTTagList func_70049_a(float... p_70049_1_) {
      NBTTagList nbttaglist = new NBTTagList();

      for(float f : p_70049_1_) {
         nbttaglist.func_74742_a(new NBTTagFloat(f));
      }

      return nbttaglist;
   }

   public EntityItem func_145779_a(Item p_145779_1_, int p_145779_2_) {
      return this.func_145778_a(p_145779_1_, p_145779_2_, 0.0F);
   }

   public EntityItem func_145778_a(Item p_145778_1_, int p_145778_2_, float p_145778_3_) {
      return this.func_70099_a(new ItemStack(p_145778_1_, p_145778_2_, 0), p_145778_3_);
   }

   public EntityItem func_70099_a(ItemStack p_70099_1_, float p_70099_2_) {
      if(p_70099_1_.field_77994_a != 0 && p_70099_1_.func_77973_b() != null) {
         EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)p_70099_2_, this.field_70161_v, p_70099_1_);
         entityitem.func_174869_p();
         this.field_70170_p.func_72838_d(entityitem);
         return entityitem;
      } else {
         return null;
      }
   }

   public boolean func_70089_S() {
      return !this.field_70128_L;
   }

   public boolean func_70094_T() {
      if(this.field_70145_X) {
         return false;
      } else {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

         for(int i = 0; i < 8; ++i) {
            int j = MathHelper.func_76128_c(this.field_70163_u + (double)(((float)((i >> 0) % 2) - 0.5F) * 0.1F) + (double)this.func_70047_e());
            int k = MathHelper.func_76128_c(this.field_70165_t + (double)(((float)((i >> 1) % 2) - 0.5F) * this.field_70130_N * 0.8F));
            int l = MathHelper.func_76128_c(this.field_70161_v + (double)(((float)((i >> 2) % 2) - 0.5F) * this.field_70130_N * 0.8F));
            if(blockpos$mutableblockpos.func_177958_n() != k || blockpos$mutableblockpos.func_177956_o() != j || blockpos$mutableblockpos.func_177952_p() != l) {
               blockpos$mutableblockpos.func_181079_c(k, j, l);
               if(this.field_70170_p.func_180495_p(blockpos$mutableblockpos).func_177230_c().func_176214_u()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      return false;
   }

   public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
      return null;
   }

   public void func_70098_U() {
      if(this.field_70154_o.field_70128_L) {
         this.field_70154_o = null;
      } else {
         this.field_70159_w = 0.0D;
         this.field_70181_x = 0.0D;
         this.field_70179_y = 0.0D;
         this.func_70071_h_();
         if(this.field_70154_o != null) {
            this.field_70154_o.func_70043_V();
            this.field_70147_f += (double)(this.field_70154_o.field_70177_z - this.field_70154_o.field_70126_B);

            for(this.field_70149_e += (double)(this.field_70154_o.field_70125_A - this.field_70154_o.field_70127_C); this.field_70147_f >= 180.0D; this.field_70147_f -= 360.0D) {
               ;
            }

            while(this.field_70147_f < -180.0D) {
               this.field_70147_f += 360.0D;
            }

            while(this.field_70149_e >= 180.0D) {
               this.field_70149_e -= 360.0D;
            }

            while(this.field_70149_e < -180.0D) {
               this.field_70149_e += 360.0D;
            }

            double d0 = this.field_70147_f * 0.5D;
            double d1 = this.field_70149_e * 0.5D;
            float f = 10.0F;
            if(d0 > (double)f) {
               d0 = (double)f;
            }

            if(d0 < (double)(-f)) {
               d0 = (double)(-f);
            }

            if(d1 > (double)f) {
               d1 = (double)f;
            }

            if(d1 < (double)(-f)) {
               d1 = (double)(-f);
            }

            this.field_70147_f -= d0;
            this.field_70149_e -= d1;
         }
      }
   }

   public void func_70043_V() {
      if(this.field_70153_n != null) {
         this.field_70153_n.func_70107_b(this.field_70165_t, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v);
      }
   }

   public double func_70033_W() {
      return 0.0D;
   }

   public double func_70042_X() {
      return (double)this.field_70131_O * 0.75D;
   }

   public void func_70078_a(Entity p_70078_1_) {
      this.field_70149_e = 0.0D;
      this.field_70147_f = 0.0D;
      if(p_70078_1_ == null) {
         if(this.field_70154_o != null) {
            this.func_70012_b(this.field_70154_o.field_70165_t, this.field_70154_o.func_174813_aQ().field_72338_b + (double)this.field_70154_o.field_70131_O, this.field_70154_o.field_70161_v, this.field_70177_z, this.field_70125_A);
            this.field_70154_o.field_70153_n = null;
         }

         this.field_70154_o = null;
      } else {
         if(this.field_70154_o != null) {
            this.field_70154_o.field_70153_n = null;
         }

         if(p_70078_1_ != null) {
            for(Entity entity = p_70078_1_.field_70154_o; entity != null; entity = entity.field_70154_o) {
               if(entity == this) {
                  return;
               }
            }
         }

         this.field_70154_o = p_70078_1_;
         p_70078_1_.field_70153_n = this;
      }
   }

   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
      this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
      this.func_70101_b(p_180426_7_, p_180426_8_);
      List<AxisAlignedBB> list = this.field_70170_p.func_72945_a(this, this.func_174813_aQ().func_72331_e(0.03125D, 0.0D, 0.03125D));
      if(!list.isEmpty()) {
         double d0 = 0.0D;

         for(AxisAlignedBB axisalignedbb : list) {
            if(axisalignedbb.field_72337_e > d0) {
               d0 = axisalignedbb.field_72337_e;
            }
         }

         p_180426_3_ = p_180426_3_ + (d0 - this.func_174813_aQ().field_72338_b);
         this.func_70107_b(p_180426_1_, p_180426_3_, p_180426_5_);
      }

   }

   public float func_70111_Y() {
      return 0.1F;
   }

   public Vec3 func_70040_Z() {
      return null;
   }

   public void func_181015_d(BlockPos p_181015_1_) {
      if(this.field_71088_bW > 0) {
         this.field_71088_bW = this.func_82147_ab();
      } else {
         if(!this.field_70170_p.field_72995_K && !p_181015_1_.equals(this.field_181016_an)) {
            this.field_181016_an = p_181015_1_;
            BlockPattern.PatternHelper blockpattern$patternhelper = Blocks.field_150427_aO.func_181089_f(this.field_70170_p, p_181015_1_);
            double d0 = blockpattern$patternhelper.func_177669_b().func_176740_k() == EnumFacing.Axis.X?(double)blockpattern$patternhelper.func_181117_a().func_177952_p():(double)blockpattern$patternhelper.func_181117_a().func_177958_n();
            double d1 = blockpattern$patternhelper.func_177669_b().func_176740_k() == EnumFacing.Axis.X?this.field_70161_v:this.field_70165_t;
            d1 = Math.abs(MathHelper.func_181160_c(d1 - (double)(blockpattern$patternhelper.func_177669_b().func_176746_e().func_176743_c() == EnumFacing.AxisDirection.NEGATIVE?1:0), d0, d0 - (double)blockpattern$patternhelper.func_181118_d()));
            double d2 = MathHelper.func_181160_c(this.field_70163_u - 1.0D, (double)blockpattern$patternhelper.func_181117_a().func_177956_o(), (double)(blockpattern$patternhelper.func_181117_a().func_177956_o() - blockpattern$patternhelper.func_181119_e()));
            this.field_181017_ao = new Vec3(d1, d2, 0.0D);
            this.field_181018_ap = blockpattern$patternhelper.func_177669_b();
         }

         this.field_71087_bX = true;
      }
   }

   public int func_82147_ab() {
      return 300;
   }

   public void func_70016_h(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.field_70159_w = p_70016_1_;
      this.field_70181_x = p_70016_3_;
      this.field_70179_y = p_70016_5_;
   }

   public void func_70103_a(byte p_70103_1_) {
   }

   public void func_70057_ab() {
   }

   public ItemStack[] func_70035_c() {
      return null;
   }

   public void func_70062_b(int p_70062_1_, ItemStack p_70062_2_) {
   }

   public boolean func_70027_ad() {
      boolean flag = this.field_70170_p != null && this.field_70170_p.field_72995_K;
      return !this.field_70178_ae && (this.field_70151_c > 0 || flag && this.func_70083_f(0));
   }

   public boolean func_70115_ae() {
      return this.field_70154_o != null;
   }

   public boolean func_70093_af() {
      return this.func_70083_f(1);
   }

   public void func_70095_a(boolean p_70095_1_) {
      this.func_70052_a(1, p_70095_1_);
   }

   public boolean func_70051_ag() {
      return this.func_70083_f(3);
   }

   public void func_70031_b(boolean p_70031_1_) {
      this.func_70052_a(3, p_70031_1_);
   }

   public boolean func_82150_aj() {
      return this.func_70083_f(5);
   }

   public boolean func_98034_c(EntityPlayer p_98034_1_) {
      return p_98034_1_.func_175149_v()?false:this.func_82150_aj();
   }

   public void func_82142_c(boolean p_82142_1_) {
      this.func_70052_a(5, p_82142_1_);
   }

   public boolean func_70113_ah() {
      return this.func_70083_f(4);
   }

   public void func_70019_c(boolean p_70019_1_) {
      this.func_70052_a(4, p_70019_1_);
   }

   protected boolean func_70083_f(int p_70083_1_) {
      return (this.field_70180_af.func_75683_a(0) & 1 << p_70083_1_) != 0;
   }

   protected void func_70052_a(int p_70052_1_, boolean p_70052_2_) {
      byte b0 = this.field_70180_af.func_75683_a(0);
      if(p_70052_2_) {
         this.field_70180_af.func_75692_b(0, Byte.valueOf((byte)(b0 | 1 << p_70052_1_)));
      } else {
         this.field_70180_af.func_75692_b(0, Byte.valueOf((byte)(b0 & ~(1 << p_70052_1_))));
      }

   }

   public int func_70086_ai() {
      return this.field_70180_af.func_75693_b(1);
   }

   public void func_70050_g(int p_70050_1_) {
      this.field_70180_af.func_75692_b(1, Short.valueOf((short)p_70050_1_));
   }

   public void func_70077_a(EntityLightningBolt p_70077_1_) {
      this.func_70097_a(DamageSource.field_180137_b, 5.0F);
      ++this.field_70151_c;
      if(this.field_70151_c == 0) {
         this.func_70015_d(8);
      }

   }

   public void func_70074_a(EntityLivingBase p_70074_1_) {
   }

   protected boolean func_145771_j(double p_145771_1_, double p_145771_3_, double p_145771_5_) {
      BlockPos blockpos = new BlockPos(p_145771_1_, p_145771_3_, p_145771_5_);
      double d0 = p_145771_1_ - (double)blockpos.func_177958_n();
      double d1 = p_145771_3_ - (double)blockpos.func_177956_o();
      double d2 = p_145771_5_ - (double)blockpos.func_177952_p();
      List<AxisAlignedBB> list = this.field_70170_p.func_147461_a(this.func_174813_aQ());
      if(list.isEmpty() && !this.field_70170_p.func_175665_u(blockpos)) {
         return false;
      } else {
         int i = 3;
         double d3 = 9999.0D;
         if(!this.field_70170_p.func_175665_u(blockpos.func_177976_e()) && d0 < d3) {
            d3 = d0;
            i = 0;
         }

         if(!this.field_70170_p.func_175665_u(blockpos.func_177974_f()) && 1.0D - d0 < d3) {
            d3 = 1.0D - d0;
            i = 1;
         }

         if(!this.field_70170_p.func_175665_u(blockpos.func_177984_a()) && 1.0D - d1 < d3) {
            d3 = 1.0D - d1;
            i = 3;
         }

         if(!this.field_70170_p.func_175665_u(blockpos.func_177978_c()) && d2 < d3) {
            d3 = d2;
            i = 4;
         }

         if(!this.field_70170_p.func_175665_u(blockpos.func_177968_d()) && 1.0D - d2 < d3) {
            d3 = 1.0D - d2;
            i = 5;
         }

         float f = this.field_70146_Z.nextFloat() * 0.2F + 0.1F;
         if(i == 0) {
            this.field_70159_w = (double)(-f);
         }

         if(i == 1) {
            this.field_70159_w = (double)f;
         }

         if(i == 3) {
            this.field_70181_x = (double)f;
         }

         if(i == 4) {
            this.field_70179_y = (double)(-f);
         }

         if(i == 5) {
            this.field_70179_y = (double)f;
         }

         return true;
      }
   }

   public void func_70110_aj() {
      this.field_70134_J = true;
      this.field_70143_R = 0.0F;
   }

   public String func_70005_c_() {
      if(this.func_145818_k_()) {
         return this.func_95999_t();
      } else {
         String s = EntityList.func_75621_b(this);
         if(s == null) {
            s = "generic";
         }

         return StatCollector.func_74838_a("entity." + s + ".name");
      }
   }

   public Entity[] func_70021_al() {
      return null;
   }

   public boolean func_70028_i(Entity p_70028_1_) {
      return this == p_70028_1_;
   }

   public float func_70079_am() {
      return 0.0F;
   }

   public void func_70034_d(float p_70034_1_) {
   }

   public void func_181013_g(float p_181013_1_) {
   }

   public boolean func_70075_an() {
      return true;
   }

   public boolean func_85031_j(Entity p_85031_1_) {
      return false;
   }

   public String toString() {
      return String.format("%s[\'%s\'/%d, l=\'%s\', x=%.2f, y=%.2f, z=%.2f]", new Object[]{this.getClass().getSimpleName(), this.func_70005_c_(), Integer.valueOf(this.field_145783_c), this.field_70170_p == null?"~NULL~":this.field_70170_p.func_72912_H().func_76065_j(), Double.valueOf(this.field_70165_t), Double.valueOf(this.field_70163_u), Double.valueOf(this.field_70161_v)});
   }

   public boolean func_180431_b(DamageSource p_180431_1_) {
      return this.field_83001_bt && p_180431_1_ != DamageSource.field_76380_i && !p_180431_1_.func_180136_u();
   }

   public void func_82149_j(Entity p_82149_1_) {
      this.func_70012_b(p_82149_1_.field_70165_t, p_82149_1_.field_70163_u, p_82149_1_.field_70161_v, p_82149_1_.field_70177_z, p_82149_1_.field_70125_A);
   }

   public void func_180432_n(Entity p_180432_1_) {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      p_180432_1_.func_70109_d(nbttagcompound);
      this.func_70020_e(nbttagcompound);
      this.field_71088_bW = p_180432_1_.field_71088_bW;
      this.field_181016_an = p_180432_1_.field_181016_an;
      this.field_181017_ao = p_180432_1_.field_181017_ao;
      this.field_181018_ap = p_180432_1_.field_181018_ap;
   }

   public void func_71027_c(int p_71027_1_) {
      if(!this.field_70170_p.field_72995_K && !this.field_70128_L) {
         this.field_70170_p.field_72984_F.func_76320_a("changeDimension");
         MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
         int i = this.field_71093_bK;
         WorldServer worldserver = minecraftserver.func_71218_a(i);
         WorldServer worldserver1 = minecraftserver.func_71218_a(p_71027_1_);
         this.field_71093_bK = p_71027_1_;
         if(i == 1 && p_71027_1_ == 1) {
            worldserver1 = minecraftserver.func_71218_a(0);
            this.field_71093_bK = 0;
         }

         this.field_70170_p.func_72900_e(this);
         this.field_70128_L = false;
         this.field_70170_p.field_72984_F.func_76320_a("reposition");
         minecraftserver.func_71203_ab().func_82448_a(this, i, worldserver, worldserver1);
         this.field_70170_p.field_72984_F.func_76318_c("reloading");
         Entity entity = EntityList.func_75620_a(EntityList.func_75621_b(this), worldserver1);
         if(entity != null) {
            entity.func_180432_n(this);
            if(i == 1 && p_71027_1_ == 1) {
               BlockPos blockpos = this.field_70170_p.func_175672_r(worldserver1.func_175694_M());
               entity.func_174828_a(blockpos, entity.field_70177_z, entity.field_70125_A);
            }

            worldserver1.func_72838_d(entity);
         }

         this.field_70128_L = true;
         this.field_70170_p.field_72984_F.func_76319_b();
         worldserver.func_82742_i();
         worldserver1.func_82742_i();
         this.field_70170_p.field_72984_F.func_76319_b();
      }
   }

   public float func_180428_a(Explosion p_180428_1_, World p_180428_2_, BlockPos p_180428_3_, IBlockState p_180428_4_) {
      return p_180428_4_.func_177230_c().func_149638_a(this);
   }

   public boolean func_174816_a(Explosion p_174816_1_, World p_174816_2_, BlockPos p_174816_3_, IBlockState p_174816_4_, float p_174816_5_) {
      return true;
   }

   public int func_82143_as() {
      return 3;
   }

   public Vec3 func_181014_aG() {
      return this.field_181017_ao;
   }

   public EnumFacing func_181012_aH() {
      return this.field_181018_ap;
   }

   public boolean func_145773_az() {
      return false;
   }

   public void func_85029_a(CrashReportCategory p_85029_1_) {
      p_85029_1_.func_71500_a("Entity Type", new Callable<String>() {
         public String call() throws Exception {
            return EntityList.func_75621_b(Entity.this) + " (" + Entity.this.getClass().getCanonicalName() + ")";
         }
      });
      p_85029_1_.func_71507_a("Entity ID", Integer.valueOf(this.field_145783_c));
      p_85029_1_.func_71500_a("Entity Name", new Callable<String>() {
         public String call() throws Exception {
            return Entity.this.func_70005_c_();
         }
      });
      p_85029_1_.func_71507_a("Entity\'s Exact location", String.format("%.2f, %.2f, %.2f", new Object[]{Double.valueOf(this.field_70165_t), Double.valueOf(this.field_70163_u), Double.valueOf(this.field_70161_v)}));
      p_85029_1_.func_71507_a("Entity\'s Block location", CrashReportCategory.func_85074_a((double)MathHelper.func_76128_c(this.field_70165_t), (double)MathHelper.func_76128_c(this.field_70163_u), (double)MathHelper.func_76128_c(this.field_70161_v)));
      p_85029_1_.func_71507_a("Entity\'s Momentum", String.format("%.2f, %.2f, %.2f", new Object[]{Double.valueOf(this.field_70159_w), Double.valueOf(this.field_70181_x), Double.valueOf(this.field_70179_y)}));
      p_85029_1_.func_71500_a("Entity\'s Rider", new Callable<String>() {
         public String call() throws Exception {
            return Entity.this.field_70153_n.toString();
         }
      });
      p_85029_1_.func_71500_a("Entity\'s Vehicle", new Callable<String>() {
         public String call() throws Exception {
            return Entity.this.field_70154_o.toString();
         }
      });
   }

   public boolean func_90999_ad() {
      return this.func_70027_ad();
   }

   public UUID func_110124_au() {
      return this.field_96093_i;
   }

   public boolean func_96092_aw() {
      return true;
   }

   public IChatComponent func_145748_c_() {
      ChatComponentText chatcomponenttext = new ChatComponentText(this.func_70005_c_());
      chatcomponenttext.func_150256_b().func_150209_a(this.func_174823_aP());
      chatcomponenttext.func_150256_b().func_179989_a(this.func_110124_au().toString());
      return chatcomponenttext;
   }

   public void func_96094_a(String p_96094_1_) {
      this.field_70180_af.func_75692_b(2, p_96094_1_);
   }

   public String func_95999_t() {
      return this.field_70180_af.func_75681_e(2);
   }

   public boolean func_145818_k_() {
      return this.field_70180_af.func_75681_e(2).length() > 0;
   }

   public void func_174805_g(boolean p_174805_1_) {
      this.field_70180_af.func_75692_b(3, Byte.valueOf((byte)(p_174805_1_?1:0)));
   }

   public boolean func_174833_aM() {
      return this.field_70180_af.func_75683_a(3) == 1;
   }

   public void func_70634_a(double p_70634_1_, double p_70634_3_, double p_70634_5_) {
      this.func_70012_b(p_70634_1_, p_70634_3_, p_70634_5_, this.field_70177_z, this.field_70125_A);
   }

   public boolean func_94059_bO() {
      return this.func_174833_aM();
   }

   public void func_145781_i(int p_145781_1_) {
   }

   public EnumFacing func_174811_aO() {
      return EnumFacing.func_176731_b(MathHelper.func_76128_c((double)(this.field_70177_z * 4.0F / 360.0F) + 0.5D) & 3);
   }

   protected HoverEvent func_174823_aP() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      String s = EntityList.func_75621_b(this);
      nbttagcompound.func_74778_a("id", this.func_110124_au().toString());
      if(s != null) {
         nbttagcompound.func_74778_a("type", s);
      }

      nbttagcompound.func_74778_a("name", this.func_70005_c_());
      return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, new ChatComponentText(nbttagcompound.toString()));
   }

   public boolean func_174827_a(EntityPlayerMP p_174827_1_) {
      return true;
   }

   public AxisAlignedBB func_174813_aQ() {
      return this.field_70121_D;
   }

   public void func_174826_a(AxisAlignedBB p_174826_1_) {
      this.field_70121_D = p_174826_1_;
   }

   public float func_70047_e() {
      return this.field_70131_O * 0.85F;
   }

   public boolean func_174832_aS() {
      return this.field_174835_g;
   }

   public void func_174821_h(boolean p_174821_1_) {
      this.field_174835_g = p_174821_1_;
   }

   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
      return false;
   }

   public void func_145747_a(IChatComponent p_145747_1_) {
   }

   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
      return true;
   }

   public BlockPos func_180425_c() {
      return new BlockPos(this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v);
   }

   public Vec3 func_174791_d() {
      return new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
   }

   public World func_130014_f_() {
      return this.field_70170_p;
   }

   public Entity func_174793_f() {
      return this;
   }

   public boolean func_174792_t_() {
      return false;
   }

   public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
      this.field_174837_as.func_179672_a(this, p_174794_1_, p_174794_2_);
   }

   public CommandResultStats func_174807_aT() {
      return this.field_174837_as;
   }

   public void func_174817_o(Entity p_174817_1_) {
      this.field_174837_as.func_179671_a(p_174817_1_.func_174807_aT());
   }

   public NBTTagCompound func_174819_aU() {
      return null;
   }

   public void func_174834_g(NBTTagCompound p_174834_1_) {
   }

   public boolean func_174825_a(EntityPlayer p_174825_1_, Vec3 p_174825_2_) {
      return false;
   }

   public boolean func_180427_aV() {
      return false;
   }

   protected void func_174815_a(EntityLivingBase p_174815_1_, Entity p_174815_2_) {
      if(p_174815_2_ instanceof EntityLivingBase) {
         EnchantmentHelper.func_151384_a((EntityLivingBase)p_174815_2_, p_174815_1_);
      }

      EnchantmentHelper.func_151385_b(p_174815_1_, p_174815_2_);
   }
}
