package net.minecraft.client.renderer.block.statemap;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class BlockStateMapper {
   private Map<Block, IStateMapper> field_178450_a = Maps.<Block, IStateMapper>newIdentityHashMap();
   private Set<Block> field_178449_b = Sets.<Block>newIdentityHashSet();

   public void func_178447_a(Block p_178447_1_, IStateMapper p_178447_2_) {
      this.field_178450_a.put(p_178447_1_, p_178447_2_);
   }

   public void func_178448_a(Block... p_178448_1_) {
      Collections.addAll(this.field_178449_b, p_178448_1_);
   }

   public Map<IBlockState, ModelResourceLocation> func_178446_a() {
      Map<IBlockState, ModelResourceLocation> map = Maps.<IBlockState, ModelResourceLocation>newIdentityHashMap();

      for(Block block : Block.field_149771_c) {
         if(!this.field_178449_b.contains(block)) {
            map.putAll(((IStateMapper)Objects.firstNonNull(this.field_178450_a.get(block), new DefaultStateMapper())).func_178130_a(block));
         }
      }

      return map;
   }
}
