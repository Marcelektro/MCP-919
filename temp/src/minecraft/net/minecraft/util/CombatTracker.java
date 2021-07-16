package net.minecraft.util;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CombatEntry;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class CombatTracker {
   private final List<CombatEntry> field_94556_a = Lists.<CombatEntry>newArrayList();
   private final EntityLivingBase field_94554_b;
   private int field_94555_c;
   private int field_152775_d;
   private int field_152776_e;
   private boolean field_94552_d;
   private boolean field_94553_e;
   private String field_94551_f;

   public CombatTracker(EntityLivingBase p_i1565_1_) {
      this.field_94554_b = p_i1565_1_;
   }

   public void func_94545_a() {
      this.func_94542_g();
      if(this.field_94554_b.func_70617_f_()) {
         Block block = this.field_94554_b.field_70170_p.func_180495_p(new BlockPos(this.field_94554_b.field_70165_t, this.field_94554_b.func_174813_aQ().field_72338_b, this.field_94554_b.field_70161_v)).func_177230_c();
         if(block == Blocks.field_150468_ap) {
            this.field_94551_f = "ladder";
         } else if(block == Blocks.field_150395_bd) {
            this.field_94551_f = "vines";
         }
      } else if(this.field_94554_b.func_70090_H()) {
         this.field_94551_f = "water";
      }

   }

   public void func_94547_a(DamageSource p_94547_1_, float p_94547_2_, float p_94547_3_) {
      this.func_94549_h();
      this.func_94545_a();
      CombatEntry combatentry = new CombatEntry(p_94547_1_, this.field_94554_b.field_70173_aa, p_94547_2_, p_94547_3_, this.field_94551_f, this.field_94554_b.field_70143_R);
      this.field_94556_a.add(combatentry);
      this.field_94555_c = this.field_94554_b.field_70173_aa;
      this.field_94553_e = true;
      if(combatentry.func_94559_f() && !this.field_94552_d && this.field_94554_b.func_70089_S()) {
         this.field_94552_d = true;
         this.field_152775_d = this.field_94554_b.field_70173_aa;
         this.field_152776_e = this.field_152775_d;
         this.field_94554_b.func_152111_bt();
      }

   }

   public IChatComponent func_151521_b() {
      if(this.field_94556_a.size() == 0) {
         return new ChatComponentTranslation("death.attack.generic", new Object[]{this.field_94554_b.func_145748_c_()});
      } else {
         CombatEntry combatentry = this.func_94544_f();
         CombatEntry combatentry1 = (CombatEntry)this.field_94556_a.get(this.field_94556_a.size() - 1);
         IChatComponent ichatcomponent1 = combatentry1.func_151522_h();
         Entity entity = combatentry1.func_94560_a().func_76346_g();
         IChatComponent ichatcomponent;
         if(combatentry != null && combatentry1.func_94560_a() == DamageSource.field_76379_h) {
            IChatComponent ichatcomponent2 = combatentry.func_151522_h();
            if(combatentry.func_94560_a() != DamageSource.field_76379_h && combatentry.func_94560_a() != DamageSource.field_76380_i) {
               if(ichatcomponent2 != null && (ichatcomponent1 == null || !ichatcomponent2.equals(ichatcomponent1))) {
                  Entity entity1 = combatentry.func_94560_a().func_76346_g();
                  ItemStack itemstack1 = entity1 instanceof EntityLivingBase?((EntityLivingBase)entity1).func_70694_bm():null;
                  if(itemstack1 != null && itemstack1.func_82837_s()) {
                     ichatcomponent = new ChatComponentTranslation("death.fell.assist.item", new Object[]{this.field_94554_b.func_145748_c_(), ichatcomponent2, itemstack1.func_151000_E()});
                  } else {
                     ichatcomponent = new ChatComponentTranslation("death.fell.assist", new Object[]{this.field_94554_b.func_145748_c_(), ichatcomponent2});
                  }
               } else if(ichatcomponent1 != null) {
                  ItemStack itemstack = entity instanceof EntityLivingBase?((EntityLivingBase)entity).func_70694_bm():null;
                  if(itemstack != null && itemstack.func_82837_s()) {
                     ichatcomponent = new ChatComponentTranslation("death.fell.finish.item", new Object[]{this.field_94554_b.func_145748_c_(), ichatcomponent1, itemstack.func_151000_E()});
                  } else {
                     ichatcomponent = new ChatComponentTranslation("death.fell.finish", new Object[]{this.field_94554_b.func_145748_c_(), ichatcomponent1});
                  }
               } else {
                  ichatcomponent = new ChatComponentTranslation("death.fell.killer", new Object[]{this.field_94554_b.func_145748_c_()});
               }
            } else {
               ichatcomponent = new ChatComponentTranslation("death.fell.accident." + this.func_94548_b(combatentry), new Object[]{this.field_94554_b.func_145748_c_()});
            }
         } else {
            ichatcomponent = combatentry1.func_94560_a().func_151519_b(this.field_94554_b);
         }

         return ichatcomponent;
      }
   }

   public EntityLivingBase func_94550_c() {
      EntityLivingBase entitylivingbase = null;
      EntityPlayer entityplayer = null;
      float f = 0.0F;
      float f1 = 0.0F;

      for(CombatEntry combatentry : this.field_94556_a) {
         if(combatentry.func_94560_a().func_76346_g() instanceof EntityPlayer && (entityplayer == null || combatentry.func_94563_c() > f1)) {
            f1 = combatentry.func_94563_c();
            entityplayer = (EntityPlayer)combatentry.func_94560_a().func_76346_g();
         }

         if(combatentry.func_94560_a().func_76346_g() instanceof EntityLivingBase && (entitylivingbase == null || combatentry.func_94563_c() > f)) {
            f = combatentry.func_94563_c();
            entitylivingbase = (EntityLivingBase)combatentry.func_94560_a().func_76346_g();
         }
      }

      if(entityplayer != null && f1 >= f / 3.0F) {
         return entityplayer;
      } else {
         return entitylivingbase;
      }
   }

   private CombatEntry func_94544_f() {
      CombatEntry combatentry = null;
      CombatEntry combatentry1 = null;
      int i = 0;
      float f = 0.0F;

      for(int j = 0; j < this.field_94556_a.size(); ++j) {
         CombatEntry combatentry2 = (CombatEntry)this.field_94556_a.get(j);
         CombatEntry combatentry3 = j > 0?(CombatEntry)this.field_94556_a.get(j - 1):null;
         if((combatentry2.func_94560_a() == DamageSource.field_76379_h || combatentry2.func_94560_a() == DamageSource.field_76380_i) && combatentry2.func_94561_i() > 0.0F && (combatentry == null || combatentry2.func_94561_i() > f)) {
            if(j > 0) {
               combatentry = combatentry3;
            } else {
               combatentry = combatentry2;
            }

            f = combatentry2.func_94561_i();
         }

         if(combatentry2.func_94562_g() != null && (combatentry1 == null || combatentry2.func_94563_c() > (float)i)) {
            combatentry1 = combatentry2;
         }
      }

      if(f > 5.0F && combatentry != null) {
         return combatentry;
      } else if(i > 5 && combatentry1 != null) {
         return combatentry1;
      } else {
         return null;
      }
   }

   private String func_94548_b(CombatEntry p_94548_1_) {
      return p_94548_1_.func_94562_g() == null?"generic":p_94548_1_.func_94562_g();
   }

   public int func_180134_f() {
      return this.field_94552_d?this.field_94554_b.field_70173_aa - this.field_152775_d:this.field_152776_e - this.field_152775_d;
   }

   private void func_94542_g() {
      this.field_94551_f = null;
   }

   public void func_94549_h() {
      int i = this.field_94552_d?300:100;
      if(this.field_94553_e && (!this.field_94554_b.func_70089_S() || this.field_94554_b.field_70173_aa - this.field_94555_c > i)) {
         boolean flag = this.field_94552_d;
         this.field_94553_e = false;
         this.field_94552_d = false;
         this.field_152776_e = this.field_94554_b.field_70173_aa;
         if(flag) {
            this.field_94554_b.func_152112_bu();
         }

         this.field_94556_a.clear();
      }

   }

   public EntityLivingBase func_180135_h() {
      return this.field_94554_b;
   }
}
