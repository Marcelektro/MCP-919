package net.minecraft.stats;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;

public class StatFileWriter {
   protected final Map<StatBase, TupleIntJsonSerializable> field_150875_a = Maps.<StatBase, TupleIntJsonSerializable>newConcurrentMap();

   public boolean func_77443_a(Achievement p_77443_1_) {
      return this.func_77444_a(p_77443_1_) > 0;
   }

   public boolean func_77442_b(Achievement p_77442_1_) {
      return p_77442_1_.field_75992_c == null || this.func_77443_a(p_77442_1_.field_75992_c);
   }

   public int func_150874_c(Achievement p_150874_1_) {
      if(this.func_77443_a(p_150874_1_)) {
         return 0;
      } else {
         int i = 0;

         for(Achievement achievement = p_150874_1_.field_75992_c; achievement != null && !this.func_77443_a(achievement); ++i) {
            achievement = achievement.field_75992_c;
         }

         return i;
      }
   }

   public void func_150871_b(EntityPlayer p_150871_1_, StatBase p_150871_2_, int p_150871_3_) {
      if(!p_150871_2_.func_75967_d() || this.func_77442_b((Achievement)p_150871_2_)) {
         this.func_150873_a(p_150871_1_, p_150871_2_, this.func_77444_a(p_150871_2_) + p_150871_3_);
      }
   }

   public void func_150873_a(EntityPlayer p_150873_1_, StatBase p_150873_2_, int p_150873_3_) {
      TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable)this.field_150875_a.get(p_150873_2_);
      if(tupleintjsonserializable == null) {
         tupleintjsonserializable = new TupleIntJsonSerializable();
         this.field_150875_a.put(p_150873_2_, tupleintjsonserializable);
      }

      tupleintjsonserializable.func_151188_a(p_150873_3_);
   }

   public int func_77444_a(StatBase p_77444_1_) {
      TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable)this.field_150875_a.get(p_77444_1_);
      return tupleintjsonserializable == null?0:tupleintjsonserializable.func_151189_a();
   }

   public <T extends IJsonSerializable> T func_150870_b(StatBase p_150870_1_) {
      TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable)this.field_150875_a.get(p_150870_1_);
      return (T)(tupleintjsonserializable != null?tupleintjsonserializable.func_151187_b():null);
   }

   public <T extends IJsonSerializable> T func_150872_a(StatBase p_150872_1_, T p_150872_2_) {
      TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable)this.field_150875_a.get(p_150872_1_);
      if(tupleintjsonserializable == null) {
         tupleintjsonserializable = new TupleIntJsonSerializable();
         this.field_150875_a.put(p_150872_1_, tupleintjsonserializable);
      }

      tupleintjsonserializable.func_151190_a(p_150872_2_);
      return (T)p_150872_2_;
   }
}
