package net.minecraft.entity;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class NpcMerchant implements IMerchant {
   private InventoryMerchant field_70937_a;
   private EntityPlayer field_70935_b;
   private MerchantRecipeList field_70936_c;
   private IChatComponent field_175548_d;

   public NpcMerchant(EntityPlayer p_i45817_1_, IChatComponent p_i45817_2_) {
      this.field_70935_b = p_i45817_1_;
      this.field_175548_d = p_i45817_2_;
      this.field_70937_a = new InventoryMerchant(p_i45817_1_, this);
   }

   public EntityPlayer func_70931_l_() {
      return this.field_70935_b;
   }

   public void func_70932_a_(EntityPlayer p_70932_1_) {
   }

   public MerchantRecipeList func_70934_b(EntityPlayer p_70934_1_) {
      return this.field_70936_c;
   }

   public void func_70930_a(MerchantRecipeList p_70930_1_) {
      this.field_70936_c = p_70930_1_;
   }

   public void func_70933_a(MerchantRecipe p_70933_1_) {
      p_70933_1_.func_77399_f();
   }

   public void func_110297_a_(ItemStack p_110297_1_) {
   }

   public IChatComponent func_145748_c_() {
      return (IChatComponent)(this.field_175548_d != null?this.field_175548_d:new ChatComponentTranslation("entity.Villager.name", new Object[0]));
   }
}
