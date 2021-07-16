package net.minecraft.item;

import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemSword extends Item {
   private float field_150934_a;
   private final Item.ToolMaterial field_150933_b;

   public ItemSword(Item.ToolMaterial p_i45356_1_) {
      this.field_150933_b = p_i45356_1_;
      this.field_77777_bU = 1;
      this.func_77656_e(p_i45356_1_.func_77997_a());
      this.func_77637_a(CreativeTabs.field_78037_j);
      this.field_150934_a = 4.0F + p_i45356_1_.func_78000_c();
   }

   public float func_150931_i() {
      return this.field_150933_b.func_78000_c();
   }

   public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
      if(p_150893_2_ == Blocks.field_150321_G) {
         return 15.0F;
      } else {
         Material material = p_150893_2_.func_149688_o();
         return material != Material.field_151585_k && material != Material.field_151582_l && material != Material.field_151589_v && material != Material.field_151584_j && material != Material.field_151572_C?1.0F:1.5F;
      }
   }

   public boolean func_77644_a(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
      p_77644_1_.func_77972_a(1, p_77644_3_);
      return true;
   }

   public boolean func_179218_a(ItemStack p_179218_1_, World p_179218_2_, Block p_179218_3_, BlockPos p_179218_4_, EntityLivingBase p_179218_5_) {
      if((double)p_179218_3_.func_176195_g(p_179218_2_, p_179218_4_) != 0.0D) {
         p_179218_1_.func_77972_a(2, p_179218_5_);
      }

      return true;
   }

   public boolean func_77662_d() {
      return true;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.BLOCK;
   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 72000;
   }

   public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
      p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
      return p_77659_1_;
   }

   public boolean func_150897_b(Block p_150897_1_) {
      return p_150897_1_ == Blocks.field_150321_G;
   }

   public int func_77619_b() {
      return this.field_150933_b.func_77995_e();
   }

   public String func_150932_j() {
      return this.field_150933_b.toString();
   }

   public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_) {
      return this.field_150933_b.func_150995_f() == p_82789_2_.func_77973_b()?true:super.func_82789_a(p_82789_1_, p_82789_2_);
   }

   public Multimap<String, AttributeModifier> func_111205_h() {
      Multimap<String, AttributeModifier> multimap = super.func_111205_h();
      multimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.field_150934_a, 0));
      return multimap;
   }
}
