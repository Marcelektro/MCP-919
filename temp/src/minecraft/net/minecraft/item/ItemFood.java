package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFood extends Item {
   public final int field_77855_a;
   private final int field_77853_b;
   private final float field_77854_c;
   private final boolean field_77856_bY;
   private boolean field_77852_bZ;
   private int field_77851_ca;
   private int field_77850_cb;
   private int field_77857_cc;
   private float field_77858_cd;

   public ItemFood(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
      this.field_77855_a = 32;
      this.field_77853_b = p_i45339_1_;
      this.field_77856_bY = p_i45339_3_;
      this.field_77854_c = p_i45339_2_;
      this.func_77637_a(CreativeTabs.field_78039_h);
   }

   public ItemFood(int p_i45340_1_, boolean p_i45340_2_) {
      this(p_i45340_1_, 0.6F, p_i45340_2_);
   }

   public ItemStack func_77654_b(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
      --p_77654_1_.field_77994_a;
      p_77654_3_.func_71024_bL().func_151686_a(this, p_77654_1_);
      p_77654_2_.func_72956_a(p_77654_3_, "random.burp", 0.5F, p_77654_2_.field_73012_v.nextFloat() * 0.1F + 0.9F);
      this.func_77849_c(p_77654_1_, p_77654_2_, p_77654_3_);
      p_77654_3_.func_71029_a(StatList.field_75929_E[Item.func_150891_b(this)]);
      return p_77654_1_;
   }

   protected void func_77849_c(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_) {
      if(!p_77849_2_.field_72995_K && this.field_77851_ca > 0 && p_77849_2_.field_73012_v.nextFloat() < this.field_77858_cd) {
         p_77849_3_.func_70690_d(new PotionEffect(this.field_77851_ca, this.field_77850_cb * 20, this.field_77857_cc));
      }

   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 32;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.EAT;
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      if(p_77659_3_.func_71043_e(this.field_77852_bZ)) {
         p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
      }

      return p_77659_1_;
   }

   public int func_150905_g(ItemStack p_150905_1_) {
      return this.field_77853_b;
   }

   public float func_150906_h(ItemStack p_150906_1_) {
      return this.field_77854_c;
   }

   public boolean func_77845_h() {
      return this.field_77856_bY;
   }

   public ItemFood func_77844_a(int p_77844_1_, int p_77844_2_, int p_77844_3_, float p_77844_4_) {
      this.field_77851_ca = p_77844_1_;
      this.field_77850_cb = p_77844_2_;
      this.field_77857_cc = p_77844_3_;
      this.field_77858_cd = p_77844_4_;
      return this;
   }

   public ItemFood func_77848_i() {
      this.field_77852_bZ = true;
      return this;
   }
}
