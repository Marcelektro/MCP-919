package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.resources.model.ModelResourceLocation;

public abstract class StateMapperBase implements IStateMapper {
   protected Map<IBlockState, ModelResourceLocation> field_178133_b = Maps.<IBlockState, ModelResourceLocation>newLinkedHashMap();

   public String func_178131_a(Map<IProperty, Comparable> p_178131_1_) {
      StringBuilder stringbuilder = new StringBuilder();

      for(Entry<IProperty, Comparable> entry : p_178131_1_.entrySet()) {
         if(stringbuilder.length() != 0) {
            stringbuilder.append(",");
         }

         IProperty iproperty = (IProperty)entry.getKey();
         Comparable comparable = (Comparable)entry.getValue();
         stringbuilder.append(iproperty.func_177701_a());
         stringbuilder.append("=");
         stringbuilder.append(iproperty.func_177702_a(comparable));
      }

      if(stringbuilder.length() == 0) {
         stringbuilder.append("normal");
      }

      return stringbuilder.toString();
   }

   public Map<IBlockState, ModelResourceLocation> func_178130_a(Block p_178130_1_) {
      for(IBlockState iblockstate : p_178130_1_.func_176194_O().func_177619_a()) {
         this.field_178133_b.put(iblockstate, this.func_178132_a(iblockstate));
      }

      return this.field_178133_b;
   }

   protected abstract ModelResourceLocation func_178132_a(IBlockState var1);
}
