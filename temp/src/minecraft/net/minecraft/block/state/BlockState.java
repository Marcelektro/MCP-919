package net.minecraft.block.state;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Cartesian;
import net.minecraft.util.MapPopulator;

public class BlockState {
   private static final Joiner field_177628_a = Joiner.on(", ");
   private static final Function<IProperty, String> field_177626_b = new Function<IProperty, String>() {
      public String apply(IProperty p_apply_1_) {
         return p_apply_1_ == null?"<NULL>":p_apply_1_.func_177701_a();
      }
   };
   private final Block field_177627_c;
   private final ImmutableList<IProperty> field_177624_d;
   private final ImmutableList<IBlockState> field_177625_e;

   public BlockState(Block p_i45663_1_, IProperty... p_i45663_2_) {
      this.field_177627_c = p_i45663_1_;
      Arrays.sort(p_i45663_2_, new Comparator<IProperty>() {
         public int compare(IProperty p_compare_1_, IProperty p_compare_2_) {
            return p_compare_1_.func_177701_a().compareTo(p_compare_2_.func_177701_a());
         }
      });
      this.field_177624_d = ImmutableList.copyOf(p_i45663_2_);
      Map<Map<IProperty, Comparable>, BlockState.StateImplementation> map = Maps.<Map<IProperty, Comparable>, BlockState.StateImplementation>newLinkedHashMap();
      List<BlockState.StateImplementation> list = Lists.<BlockState.StateImplementation>newArrayList();

      for(List<Comparable> list1 : Cartesian.func_179321_a(this.func_177620_e())) {
         Map<IProperty, Comparable> map1 = MapPopulator.<IProperty, Comparable>func_179400_b(this.field_177624_d, list1);
         BlockState.StateImplementation blockstate$stateimplementation = new BlockState.StateImplementation(p_i45663_1_, ImmutableMap.copyOf(map1));
         map.put(map1, blockstate$stateimplementation);
         list.add(blockstate$stateimplementation);
      }

      for(BlockState.StateImplementation blockstate$stateimplementation1 : list) {
         blockstate$stateimplementation1.func_177235_a(map);
      }

      this.field_177625_e = ImmutableList.copyOf(list);
   }

   public ImmutableList<IBlockState> func_177619_a() {
      return this.field_177625_e;
   }

   private List<Iterable<Comparable>> func_177620_e() {
      List<Iterable<Comparable>> list = Lists.<Iterable<Comparable>>newArrayList();

      for(int i = 0; i < this.field_177624_d.size(); ++i) {
         list.add(((IProperty)this.field_177624_d.get(i)).func_177700_c());
      }

      return list;
   }

   public IBlockState func_177621_b() {
      return (IBlockState)this.field_177625_e.get(0);
   }

   public Block func_177622_c() {
      return this.field_177627_c;
   }

   public Collection<IProperty> func_177623_d() {
      return this.field_177624_d;
   }

   public String toString() {
      return Objects.toStringHelper(this).add("block", Block.field_149771_c.func_177774_c(this.field_177627_c)).add("properties", Iterables.transform(this.field_177624_d, field_177626_b)).toString();
   }

   static class StateImplementation extends BlockStateBase {
      private final Block field_177239_a;
      private final ImmutableMap<IProperty, Comparable> field_177237_b;
      private ImmutableTable<IProperty, Comparable, IBlockState> field_177238_c;

      private StateImplementation(Block p_i45660_1_, ImmutableMap<IProperty, Comparable> p_i45660_2_) {
         this.field_177239_a = p_i45660_1_;
         this.field_177237_b = p_i45660_2_;
      }

      public Collection<IProperty> func_177227_a() {
         return Collections.<IProperty>unmodifiableCollection(this.field_177237_b.keySet());
      }

      public <T extends Comparable<T>> T func_177229_b(IProperty<T> p_177229_1_) {
         if(!this.field_177237_b.containsKey(p_177229_1_)) {
            throw new IllegalArgumentException("Cannot get property " + p_177229_1_ + " as it does not exist in " + this.field_177239_a.func_176194_O());
         } else {
            return (T)((Comparable)p_177229_1_.func_177699_b().cast(this.field_177237_b.get(p_177229_1_)));
         }
      }

      public <T extends Comparable<T>, V extends T> IBlockState func_177226_a(IProperty<T> p_177226_1_, V p_177226_2_) {
         if(!this.field_177237_b.containsKey(p_177226_1_)) {
            throw new IllegalArgumentException("Cannot set property " + p_177226_1_ + " as it does not exist in " + this.field_177239_a.func_176194_O());
         } else if(!p_177226_1_.func_177700_c().contains(p_177226_2_)) {
            throw new IllegalArgumentException("Cannot set property " + p_177226_1_ + " to " + p_177226_2_ + " on block " + Block.field_149771_c.func_177774_c(this.field_177239_a) + ", it is not an allowed value");
         } else {
            return (IBlockState)(this.field_177237_b.get(p_177226_1_) == p_177226_2_?this:(IBlockState)this.field_177238_c.get(p_177226_1_, p_177226_2_));
         }
      }

      public ImmutableMap<IProperty, Comparable> func_177228_b() {
         return this.field_177237_b;
      }

      public Block func_177230_c() {
         return this.field_177239_a;
      }

      public boolean equals(Object p_equals_1_) {
         return this == p_equals_1_;
      }

      public int hashCode() {
         return this.field_177237_b.hashCode();
      }

      public void func_177235_a(Map<Map<IProperty, Comparable>, BlockState.StateImplementation> p_177235_1_) {
         if(this.field_177238_c != null) {
            throw new IllegalStateException();
         } else {
            Table<IProperty, Comparable, IBlockState> table = HashBasedTable.<IProperty, Comparable, IBlockState>create();

            for(IProperty<? extends Comparable> iproperty : this.field_177237_b.keySet()) {
               for(Comparable comparable : iproperty.func_177700_c()) {
                  if(comparable != this.field_177237_b.get(iproperty)) {
                     table.put(iproperty, comparable, p_177235_1_.get(this.func_177236_b(iproperty, comparable)));
                  }
               }
            }

            this.field_177238_c = ImmutableTable.<IProperty, Comparable, IBlockState>copyOf(table);
         }
      }

      private Map<IProperty, Comparable> func_177236_b(IProperty p_177236_1_, Comparable p_177236_2_) {
         Map<IProperty, Comparable> map = Maps.<IProperty, Comparable>newHashMap(this.field_177237_b);
         map.put(p_177236_1_, p_177236_2_);
         return map;
      }
   }
}
