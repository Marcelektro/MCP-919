package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class Slot {
   private final int field_75225_a;
   public final IInventory field_75224_c;
   public int field_75222_d;
   public int field_75223_e;
   public int field_75221_f;

   public Slot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
      this.field_75224_c = p_i1824_1_;
      this.field_75225_a = p_i1824_2_;
      this.field_75223_e = p_i1824_3_;
      this.field_75221_f = p_i1824_4_;
   }

   public void func_75220_a(ItemStack p_75220_1_, ItemStack p_75220_2_) {
      if(p_75220_1_ != null && p_75220_2_ != null) {
         if(p_75220_1_.func_77973_b() == p_75220_2_.func_77973_b()) {
            int i = p_75220_2_.field_77994_a - p_75220_1_.field_77994_a;
            if(i > 0) {
               this.func_75210_a(p_75220_1_, i);
            }

         }
      }
   }

   protected void func_75210_a(ItemStack p_75210_1_, int p_75210_2_) {
   }

   protected void func_75208_c(ItemStack p_75208_1_) {
   }

   public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
      this.func_75218_e();
   }

   public boolean func_75214_a(ItemStack p_75214_1_) {
      return true;
   }

   public ItemStack func_75211_c() {
      return this.field_75224_c.func_70301_a(this.field_75225_a);
   }

   public boolean func_75216_d() {
      return this.func_75211_c() != null;
   }

   public void func_75215_d(ItemStack p_75215_1_) {
      this.field_75224_c.func_70299_a(this.field_75225_a, p_75215_1_);
      this.func_75218_e();
   }

   public void func_75218_e() {
      this.field_75224_c.func_70296_d();
   }

   public int func_75219_a() {
      return this.field_75224_c.func_70297_j_();
   }

   public int func_178170_b(ItemStack p_178170_1_) {
      return this.func_75219_a();
   }

   public String func_178171_c() {
      return null;
   }

   public ItemStack func_75209_a(int p_75209_1_) {
      return this.field_75224_c.func_70298_a(this.field_75225_a, p_75209_1_);
   }

   public boolean func_75217_a(IInventory p_75217_1_, int p_75217_2_) {
      return p_75217_1_ == this.field_75224_c && p_75217_2_ == this.field_75225_a;
   }

   public boolean func_82869_a(EntityPlayer p_82869_1_) {
      return true;
   }

   public boolean func_111238_b() {
      return true;
   }
}
