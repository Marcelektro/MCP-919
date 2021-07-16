package net.minecraft.block.state.pattern;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

public class BlockStateHelper implements Predicate<IBlockState> {
   private final BlockState field_177641_a;
   private final Map<IProperty, Predicate> field_177640_b = Maps.<IProperty, Predicate>newHashMap();

   private BlockStateHelper(BlockState p_i45653_1_) {
      this.field_177641_a = p_i45653_1_;
   }

   public static BlockStateHelper func_177638_a(Block p_177638_0_) {
      return new BlockStateHelper(p_177638_0_.func_176194_O());
   }

   public boolean apply(IBlockState p_apply_1_) {
      if(p_apply_1_ != null && p_apply_1_.func_177230_c().equals(this.field_177641_a.func_177622_c())) {
         for(Entry<IProperty, Predicate> entry : this.field_177640_b.entrySet()) {
            Object object = p_apply_1_.func_177229_b((IProperty)entry.getKey());
            if(!((Predicate)entry.getValue()).apply(object)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public <V extends Comparable<V>> BlockStateHelper func_177637_a(IProperty<V> p_177637_1_, Predicate<? extends V> p_177637_2_) {
      if(!this.field_177641_a.func_177623_d().contains(p_177637_1_)) {
         throw new IllegalArgumentException(this.field_177641_a + " cannot support property " + p_177637_1_);
      } else {
         this.field_177640_b.put(p_177637_1_, p_177637_2_);
         return this;
      }
   }
}
