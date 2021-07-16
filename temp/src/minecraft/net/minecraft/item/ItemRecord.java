package net.minecraft.item;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRecord extends Item {
   private static final Map<String, ItemRecord> field_150928_b = Maps.<String, ItemRecord>newHashMap();
   public final String field_150929_a;

   protected ItemRecord(String p_i45350_1_) {
      this.field_150929_a = p_i45350_1_;
      this.field_77777_bU = 1;
      this.func_77637_a(CreativeTabs.field_78026_f);
      field_150928_b.put("records." + p_i45350_1_, this);
   }

   public boolean func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
      if(iblockstate.func_177230_c() == Blocks.field_150421_aI && !((Boolean)iblockstate.func_177229_b(BlockJukebox.field_176432_a)).booleanValue()) {
         if(p_180614_3_.field_72995_K) {
            return true;
         } else {
            ((BlockJukebox)Blocks.field_150421_aI).func_176431_a(p_180614_3_, p_180614_4_, iblockstate, p_180614_1_);
            p_180614_3_.func_180498_a((EntityPlayer)null, 1005, p_180614_4_, Item.func_150891_b(this));
            --p_180614_1_.field_77994_a;
            p_180614_2_.func_71029_a(StatList.field_181740_X);
            return true;
         }
      } else {
         return false;
      }
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      p_77624_3_.add(this.func_150927_i());
   }

   public String func_150927_i() {
      return StatCollector.func_74838_a("item.record." + this.field_150929_a + ".desc");
   }

   public EnumRarity func_77613_e(ItemStack p_77613_1_) {
      return EnumRarity.RARE;
   }

   public static ItemRecord func_150926_b(String p_150926_0_) {
      return (ItemRecord)field_150928_b.get(p_150926_0_);
   }
}
