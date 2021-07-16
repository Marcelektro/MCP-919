package net.minecraft.item.crafting;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class RecipesDyes {
   public void func_77607_a(CraftingManager p_77607_1_) {
      for(int i = 0; i < 16; ++i) {
         p_77607_1_.func_77596_b(new ItemStack(Blocks.field_150325_L, 1, i), new Object[]{new ItemStack(Items.field_151100_aR, 1, 15 - i), new ItemStack(Item.func_150898_a(Blocks.field_150325_L), 1, 0)});
         p_77607_1_.func_92103_a(new ItemStack(Blocks.field_150406_ce, 8, 15 - i), new Object[]{"###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.field_150405_ch), Character.valueOf('X'), new ItemStack(Items.field_151100_aR, 1, i)});
         p_77607_1_.func_92103_a(new ItemStack(Blocks.field_150399_cn, 8, 15 - i), new Object[]{"###", "#X#", "###", Character.valueOf('#'), new ItemStack(Blocks.field_150359_w), Character.valueOf('X'), new ItemStack(Items.field_151100_aR, 1, i)});
         p_77607_1_.func_92103_a(new ItemStack(Blocks.field_150397_co, 16, i), new Object[]{"###", "###", Character.valueOf('#'), new ItemStack(Blocks.field_150399_cn, 1, i)});
      }

      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.YELLOW.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150327_N, 1, BlockFlower.EnumFlowerType.DANDELION.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.POPPY.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 3, EnumDyeColor.WHITE.func_176767_b()), new Object[]{Items.field_151103_aS});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.PINK.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.ORANGE.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.YELLOW.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.LIME.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.GREEN.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.GRAY.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BLACK.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.SILVER.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.GRAY.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 3, EnumDyeColor.SILVER.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BLACK.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.LIGHT_BLUE.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BLUE.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.CYAN.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BLUE.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.GREEN.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.PURPLE.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BLUE.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.MAGENTA.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.PURPLE.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.PINK.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 3, EnumDyeColor.MAGENTA.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BLUE.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.PINK.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 4, EnumDyeColor.MAGENTA.func_176767_b()), new Object[]{new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.BLUE.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b()), new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.WHITE.func_176767_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.LIGHT_BLUE.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.BLUE_ORCHID.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.MAGENTA.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.ALLIUM.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.SILVER.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.HOUSTONIA.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.RED.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.RED_TULIP.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.ORANGE.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.ORANGE_TULIP.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.SILVER.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.WHITE_TULIP.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.PINK.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.PINK_TULIP.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 1, EnumDyeColor.SILVER.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.OXEYE_DAISY.func_176968_b())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.YELLOW.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150398_cm, 1, BlockDoublePlant.EnumPlantType.SUNFLOWER.func_176936_a())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.MAGENTA.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150398_cm, 1, BlockDoublePlant.EnumPlantType.SYRINGA.func_176936_a())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.RED.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150398_cm, 1, BlockDoublePlant.EnumPlantType.ROSE.func_176936_a())});
      p_77607_1_.func_77596_b(new ItemStack(Items.field_151100_aR, 2, EnumDyeColor.PINK.func_176767_b()), new Object[]{new ItemStack(Blocks.field_150398_cm, 1, BlockDoublePlant.EnumPlantType.PAEONIA.func_176936_a())});

      for(int j = 0; j < 16; ++j) {
         p_77607_1_.func_92103_a(new ItemStack(Blocks.field_150404_cg, 3, j), new Object[]{"##", Character.valueOf('#'), new ItemStack(Blocks.field_150325_L, 1, j)});
      }

   }
}
