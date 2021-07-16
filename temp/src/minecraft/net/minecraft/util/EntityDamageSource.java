package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class EntityDamageSource extends DamageSource {
   protected Entity field_76386_o;
   private boolean field_180140_r = false;

   public EntityDamageSource(String p_i1567_1_, Entity p_i1567_2_) {
      super(p_i1567_1_);
      this.field_76386_o = p_i1567_2_;
   }

   public EntityDamageSource func_180138_v() {
      this.field_180140_r = true;
      return this;
   }

   public boolean func_180139_w() {
      return this.field_180140_r;
   }

   public Entity func_76346_g() {
      return this.field_76386_o;
   }

   public IChatComponent func_151519_b(EntityLivingBase p_151519_1_) {
      ItemStack itemstack = this.field_76386_o instanceof EntityLivingBase?((EntityLivingBase)this.field_76386_o).func_70694_bm():null;
      String s = "death.attack." + this.field_76373_n;
      String s1 = s + ".item";
      return itemstack != null && itemstack.func_82837_s() && StatCollector.func_94522_b(s1)?new ChatComponentTranslation(s1, new Object[]{p_151519_1_.func_145748_c_(), this.field_76386_o.func_145748_c_(), itemstack.func_151000_E()}):new ChatComponentTranslation(s, new Object[]{p_151519_1_.func_145748_c_(), this.field_76386_o.func_145748_c_()});
   }

   public boolean func_76350_n() {
      return this.field_76386_o != null && this.field_76386_o instanceof EntityLivingBase && !(this.field_76386_o instanceof EntityPlayer);
   }
}
