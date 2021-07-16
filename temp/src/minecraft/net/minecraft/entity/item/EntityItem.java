package net.minecraft.entity.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityItem extends Entity {
   private static final Logger field_145803_d = LogManager.getLogger();
   private int field_70292_b;
   private int field_145804_b;
   private int field_70291_e;
   private String field_145801_f;
   private String field_145802_g;
   public float field_70290_d;

   public EntityItem(World p_i1709_1_, double p_i1709_2_, double p_i1709_4_, double p_i1709_6_) {
      super(p_i1709_1_);
      this.field_70291_e = 5;
      this.field_70290_d = (float)(Math.random() * 3.141592653589793D * 2.0D);
      this.func_70105_a(0.25F, 0.25F);
      this.func_70107_b(p_i1709_2_, p_i1709_4_, p_i1709_6_);
      this.field_70177_z = (float)(Math.random() * 360.0D);
      this.field_70159_w = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
      this.field_70181_x = 0.20000000298023224D;
      this.field_70179_y = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
   }

   public EntityItem(World p_i1710_1_, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, ItemStack p_i1710_8_) {
      this(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_);
      this.func_92058_a(p_i1710_8_);
   }

   protected boolean func_70041_e_() {
      return false;
   }

   public EntityItem(World p_i1711_1_) {
      super(p_i1711_1_);
      this.field_70291_e = 5;
      this.field_70290_d = (float)(Math.random() * 3.141592653589793D * 2.0D);
      this.func_70105_a(0.25F, 0.25F);
      this.func_92058_a(new ItemStack(Blocks.field_150350_a, 0));
   }

   protected void func_70088_a() {
      this.func_70096_w().func_82709_a(10, 5);
   }

   public void func_70071_h_() {
      if(this.func_92059_d() == null) {
         this.func_70106_y();
      } else {
         super.func_70071_h_();
         if(this.field_145804_b > 0 && this.field_145804_b != 32767) {
            --this.field_145804_b;
         }

         this.field_70169_q = this.field_70165_t;
         this.field_70167_r = this.field_70163_u;
         this.field_70166_s = this.field_70161_v;
         this.field_70181_x -= 0.03999999910593033D;
         this.field_70145_X = this.func_145771_j(this.field_70165_t, (this.func_174813_aQ().field_72338_b + this.func_174813_aQ().field_72337_e) / 2.0D, this.field_70161_v);
         this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
         boolean flag = (int)this.field_70169_q != (int)this.field_70165_t || (int)this.field_70167_r != (int)this.field_70163_u || (int)this.field_70166_s != (int)this.field_70161_v;
         if(flag || this.field_70173_aa % 25 == 0) {
            if(this.field_70170_p.func_180495_p(new BlockPos(this)).func_177230_c().func_149688_o() == Material.field_151587_i) {
               this.field_70181_x = 0.20000000298023224D;
               this.field_70159_w = (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
               this.field_70179_y = (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
               this.func_85030_a("random.fizz", 0.4F, 2.0F + this.field_70146_Z.nextFloat() * 0.4F);
            }

            if(!this.field_70170_p.field_72995_K) {
               this.func_85054_d();
            }
         }

         float f = 0.98F;
         if(this.field_70122_E) {
            f = this.field_70170_p.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.func_174813_aQ().field_72338_b) - 1, MathHelper.func_76128_c(this.field_70161_v))).func_177230_c().field_149765_K * 0.98F;
         }

         this.field_70159_w *= (double)f;
         this.field_70181_x *= 0.9800000190734863D;
         this.field_70179_y *= (double)f;
         if(this.field_70122_E) {
            this.field_70181_x *= -0.5D;
         }

         if(this.field_70292_b != -32768) {
            ++this.field_70292_b;
         }

         this.func_70072_I();
         if(!this.field_70170_p.field_72995_K && this.field_70292_b >= 6000) {
            this.func_70106_y();
         }

      }
   }

   private void func_85054_d() {
      for(EntityItem entityitem : this.field_70170_p.func_72872_a(EntityItem.class, this.func_174813_aQ().func_72314_b(0.5D, 0.0D, 0.5D))) {
         this.func_70289_a(entityitem);
      }

   }

   private boolean func_70289_a(EntityItem p_70289_1_) {
      if(p_70289_1_ == this) {
         return false;
      } else if(p_70289_1_.func_70089_S() && this.func_70089_S()) {
         ItemStack itemstack = this.func_92059_d();
         ItemStack itemstack1 = p_70289_1_.func_92059_d();
         if(this.field_145804_b != 32767 && p_70289_1_.field_145804_b != 32767) {
            if(this.field_70292_b != -32768 && p_70289_1_.field_70292_b != -32768) {
               if(itemstack1.func_77973_b() != itemstack.func_77973_b()) {
                  return false;
               } else if(itemstack1.func_77942_o() ^ itemstack.func_77942_o()) {
                  return false;
               } else if(itemstack1.func_77942_o() && !itemstack1.func_77978_p().equals(itemstack.func_77978_p())) {
                  return false;
               } else if(itemstack1.func_77973_b() == null) {
                  return false;
               } else if(itemstack1.func_77973_b().func_77614_k() && itemstack1.func_77960_j() != itemstack.func_77960_j()) {
                  return false;
               } else if(itemstack1.field_77994_a < itemstack.field_77994_a) {
                  return p_70289_1_.func_70289_a(this);
               } else if(itemstack1.field_77994_a + itemstack.field_77994_a > itemstack1.func_77976_d()) {
                  return false;
               } else {
                  itemstack1.field_77994_a += itemstack.field_77994_a;
                  p_70289_1_.field_145804_b = Math.max(p_70289_1_.field_145804_b, this.field_145804_b);
                  p_70289_1_.field_70292_b = Math.min(p_70289_1_.field_70292_b, this.field_70292_b);
                  p_70289_1_.func_92058_a(itemstack1);
                  this.func_70106_y();
                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void func_70288_d() {
      this.field_70292_b = 4800;
   }

   public boolean func_70072_I() {
      if(this.field_70170_p.func_72918_a(this.func_174813_aQ(), Material.field_151586_h, this)) {
         if(!this.field_70171_ac && !this.field_70148_d) {
            this.func_71061_d_();
         }

         this.field_70171_ac = true;
      } else {
         this.field_70171_ac = false;
      }

      return this.field_70171_ac;
   }

   protected void func_70081_e(int p_70081_1_) {
      this.func_70097_a(DamageSource.field_76372_a, (float)p_70081_1_);
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_180431_b(p_70097_1_)) {
         return false;
      } else if(this.func_92059_d() != null && this.func_92059_d().func_77973_b() == Items.field_151156_bN && p_70097_1_.func_94541_c()) {
         return false;
      } else {
         this.func_70018_K();
         this.field_70291_e = (int)((float)this.field_70291_e - p_70097_2_);
         if(this.field_70291_e <= 0) {
            this.func_70106_y();
         }

         return false;
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      p_70014_1_.func_74777_a("Health", (short)((byte)this.field_70291_e));
      p_70014_1_.func_74777_a("Age", (short)this.field_70292_b);
      p_70014_1_.func_74777_a("PickupDelay", (short)this.field_145804_b);
      if(this.func_145800_j() != null) {
         p_70014_1_.func_74778_a("Thrower", this.field_145801_f);
      }

      if(this.func_145798_i() != null) {
         p_70014_1_.func_74778_a("Owner", this.field_145802_g);
      }

      if(this.func_92059_d() != null) {
         p_70014_1_.func_74782_a("Item", this.func_92059_d().func_77955_b(new NBTTagCompound()));
      }

   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      this.field_70291_e = p_70037_1_.func_74765_d("Health") & 255;
      this.field_70292_b = p_70037_1_.func_74765_d("Age");
      if(p_70037_1_.func_74764_b("PickupDelay")) {
         this.field_145804_b = p_70037_1_.func_74765_d("PickupDelay");
      }

      if(p_70037_1_.func_74764_b("Owner")) {
         this.field_145802_g = p_70037_1_.func_74779_i("Owner");
      }

      if(p_70037_1_.func_74764_b("Thrower")) {
         this.field_145801_f = p_70037_1_.func_74779_i("Thrower");
      }

      NBTTagCompound nbttagcompound = p_70037_1_.func_74775_l("Item");
      this.func_92058_a(ItemStack.func_77949_a(nbttagcompound));
      if(this.func_92059_d() == null) {
         this.func_70106_y();
      }

   }

   public void func_70100_b_(EntityPlayer p_70100_1_) {
      if(!this.field_70170_p.field_72995_K) {
         ItemStack itemstack = this.func_92059_d();
         int i = itemstack.field_77994_a;
         if(this.field_145804_b == 0 && (this.field_145802_g == null || 6000 - this.field_70292_b <= 200 || this.field_145802_g.equals(p_70100_1_.func_70005_c_())) && p_70100_1_.field_71071_by.func_70441_a(itemstack)) {
            if(itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150364_r)) {
               p_70100_1_.func_71029_a(AchievementList.field_76005_g);
            }

            if(itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150363_s)) {
               p_70100_1_.func_71029_a(AchievementList.field_76005_g);
            }

            if(itemstack.func_77973_b() == Items.field_151116_aA) {
               p_70100_1_.func_71029_a(AchievementList.field_76022_t);
            }

            if(itemstack.func_77973_b() == Items.field_151045_i) {
               p_70100_1_.func_71029_a(AchievementList.field_76019_w);
            }

            if(itemstack.func_77973_b() == Items.field_151072_bj) {
               p_70100_1_.func_71029_a(AchievementList.field_76027_z);
            }

            if(itemstack.func_77973_b() == Items.field_151045_i && this.func_145800_j() != null) {
               EntityPlayer entityplayer = this.field_70170_p.func_72924_a(this.func_145800_j());
               if(entityplayer != null && entityplayer != p_70100_1_) {
                  entityplayer.func_71029_a(AchievementList.field_150966_x);
               }
            }

            if(!this.func_174814_R()) {
               this.field_70170_p.func_72956_a(p_70100_1_, "random.pop", 0.2F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }

            p_70100_1_.func_71001_a(this, i);
            if(itemstack.field_77994_a <= 0) {
               this.func_70106_y();
            }
         }

      }
   }

   public String func_70005_c_() {
      return this.func_145818_k_()?this.func_95999_t():StatCollector.func_74838_a("item." + this.func_92059_d().func_77977_a());
   }

   public boolean func_70075_an() {
      return false;
   }

   public void func_71027_c(int p_71027_1_) {
      super.func_71027_c(p_71027_1_);
      if(!this.field_70170_p.field_72995_K) {
         this.func_85054_d();
      }

   }

   public ItemStack func_92059_d() {
      ItemStack itemstack = this.func_70096_w().func_82710_f(10);
      if(itemstack == null) {
         if(this.field_70170_p != null) {
            field_145803_d.error("Item entity " + this.func_145782_y() + " has no item?!");
         }

         return new ItemStack(Blocks.field_150348_b);
      } else {
         return itemstack;
      }
   }

   public void func_92058_a(ItemStack p_92058_1_) {
      this.func_70096_w().func_75692_b(10, p_92058_1_);
      this.func_70096_w().func_82708_h(10);
   }

   public String func_145798_i() {
      return this.field_145802_g;
   }

   public void func_145797_a(String p_145797_1_) {
      this.field_145802_g = p_145797_1_;
   }

   public String func_145800_j() {
      return this.field_145801_f;
   }

   public void func_145799_b(String p_145799_1_) {
      this.field_145801_f = p_145799_1_;
   }

   public int func_174872_o() {
      return this.field_70292_b;
   }

   public void func_174869_p() {
      this.field_145804_b = 10;
   }

   public void func_174868_q() {
      this.field_145804_b = 0;
   }

   public void func_174871_r() {
      this.field_145804_b = 32767;
   }

   public void func_174867_a(int p_174867_1_) {
      this.field_145804_b = p_174867_1_;
   }

   public boolean func_174874_s() {
      return this.field_145804_b > 0;
   }

   public void func_174873_u() {
      this.field_70292_b = -6000;
   }

   public void func_174870_v() {
      this.func_174871_r();
      this.field_70292_b = 5999;
   }
}
