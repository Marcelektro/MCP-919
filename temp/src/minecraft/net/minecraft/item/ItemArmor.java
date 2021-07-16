package net.minecraft.item;

import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.world.World;

public class ItemArmor extends Item {
   private static final int[] field_77882_bY = new int[]{11, 16, 15, 13};
   public static final String[] field_94603_a = new String[]{"minecraft:items/empty_armor_slot_helmet", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_boots"};
   private static final IBehaviorDispenseItem field_96605_cw = new BehaviorDefaultDispenseItem() {
      protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
         BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
         int i = blockpos.func_177958_n();
         int j = blockpos.func_177956_o();
         int k = blockpos.func_177952_p();
         AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
         List<EntityLivingBase> list = p_82487_1_.func_82618_k().<EntityLivingBase>func_175647_a(EntityLivingBase.class, axisalignedbb, Predicates.<EntityLivingBase>and(EntitySelectors.field_180132_d, new EntitySelectors.ArmoredMob(p_82487_2_)));
         if(list.size() > 0) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(0);
            int l = entitylivingbase instanceof EntityPlayer?1:0;
            int i1 = EntityLiving.func_82159_b(p_82487_2_);
            ItemStack itemstack = p_82487_2_.func_77946_l();
            itemstack.field_77994_a = 1;
            entitylivingbase.func_70062_b(i1 - l, itemstack);
            if(entitylivingbase instanceof EntityLiving) {
               ((EntityLiving)entitylivingbase).func_96120_a(i1, 2.0F);
            }

            --p_82487_2_.field_77994_a;
            return p_82487_2_;
         } else {
            return super.func_82487_b(p_82487_1_, p_82487_2_);
         }
      }
   };
   public final int field_77881_a;
   public final int field_77879_b;
   public final int field_77880_c;
   private final ItemArmor.ArmorMaterial field_77878_bZ;

   public ItemArmor(ItemArmor.ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
      this.field_77878_bZ = p_i45325_1_;
      this.field_77881_a = p_i45325_3_;
      this.field_77880_c = p_i45325_2_;
      this.field_77879_b = p_i45325_1_.func_78044_b(p_i45325_3_);
      this.func_77656_e(p_i45325_1_.func_78046_a(p_i45325_3_));
      this.field_77777_bU = 1;
      this.func_77637_a(CreativeTabs.field_78037_j);
      BlockDispenser.field_149943_a.func_82595_a(this, field_96605_cw);
   }

   public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
      if(p_82790_2_ > 0) {
         return 16777215;
      } else {
         int i = this.func_82814_b(p_82790_1_);
         if(i < 0) {
            i = 16777215;
         }

         return i;
      }
   }

   public int func_77619_b() {
      return this.field_77878_bZ.func_78045_a();
   }

   public ItemArmor.ArmorMaterial func_82812_d() {
      return this.field_77878_bZ;
   }

   public boolean func_82816_b_(ItemStack p_82816_1_) {
      return this.field_77878_bZ != ItemArmor.ArmorMaterial.LEATHER?false:(!p_82816_1_.func_77942_o()?false:(!p_82816_1_.func_77978_p().func_150297_b("display", 10)?false:p_82816_1_.func_77978_p().func_74775_l("display").func_150297_b("color", 3)));
   }

   public int func_82814_b(ItemStack p_82814_1_) {
      if(this.field_77878_bZ != ItemArmor.ArmorMaterial.LEATHER) {
         return -1;
      } else {
         NBTTagCompound nbttagcompound = p_82814_1_.func_77978_p();
         if(nbttagcompound != null) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
            if(nbttagcompound1 != null && nbttagcompound1.func_150297_b("color", 3)) {
               return nbttagcompound1.func_74762_e("color");
            }
         }

         return 10511680;
      }
   }

   public void func_82815_c(ItemStack p_82815_1_) {
      if(this.field_77878_bZ == ItemArmor.ArmorMaterial.LEATHER) {
         NBTTagCompound nbttagcompound = p_82815_1_.func_77978_p();
         if(nbttagcompound != null) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
            if(nbttagcompound1.func_74764_b("color")) {
               nbttagcompound1.func_82580_o("color");
            }

         }
      }
   }

   public void func_82813_b(ItemStack p_82813_1_, int p_82813_2_) {
      if(this.field_77878_bZ != ItemArmor.ArmorMaterial.LEATHER) {
         throw new UnsupportedOperationException("Can\'t dye non-leather!");
      } else {
         NBTTagCompound nbttagcompound = p_82813_1_.func_77978_p();
         if(nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            p_82813_1_.func_77982_d(nbttagcompound);
         }

         NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
         if(!nbttagcompound.func_150297_b("display", 10)) {
            nbttagcompound.func_74782_a("display", nbttagcompound1);
         }

         nbttagcompound1.func_74768_a("color", p_82813_2_);
      }
   }

   public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_) {
      return this.field_77878_bZ.func_151685_b() == p_82789_2_.func_77973_b()?true:super.func_82789_a(p_82789_1_, p_82789_2_);
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      int i = EntityLiving.func_82159_b(p_77659_1_) - 1;
      ItemStack itemstack = p_77659_3_.func_82169_q(i);
      if(itemstack == null) {
         p_77659_3_.func_70062_b(i, p_77659_1_.func_77946_l());
         p_77659_1_.field_77994_a = 0;
      }

      return p_77659_1_;
   }

   public static enum ArmorMaterial {
      LEATHER("leather", 5, new int[]{1, 3, 2, 1}, 15),
      CHAIN("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
      IRON("iron", 15, new int[]{2, 6, 5, 2}, 9),
      GOLD("gold", 7, new int[]{2, 5, 3, 1}, 25),
      DIAMOND("diamond", 33, new int[]{3, 8, 6, 3}, 10);

      private final String field_179243_f;
      private final int field_78048_f;
      private final int[] field_78049_g;
      private final int field_78055_h;

      private ArmorMaterial(String p_i45789_3_, int p_i45789_4_, int[] p_i45789_5_, int p_i45789_6_) {
         this.field_179243_f = p_i45789_3_;
         this.field_78048_f = p_i45789_4_;
         this.field_78049_g = p_i45789_5_;
         this.field_78055_h = p_i45789_6_;
      }

      public int func_78046_a(int p_78046_1_) {
         return ItemArmor.field_77882_bY[p_78046_1_] * this.field_78048_f;
      }

      public int func_78044_b(int p_78044_1_) {
         return this.field_78049_g[p_78044_1_];
      }

      public int func_78045_a() {
         return this.field_78055_h;
      }

      public Item func_151685_b() {
         return this == LEATHER?Items.field_151116_aA:(this == CHAIN?Items.field_151042_j:(this == GOLD?Items.field_151043_k:(this == IRON?Items.field_151042_j:(this == DIAMOND?Items.field_151045_i:null))));
      }

      public String func_179242_c() {
         return this.field_179243_f;
      }
   }
}
