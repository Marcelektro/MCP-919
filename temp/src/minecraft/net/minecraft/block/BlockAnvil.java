package net.minecraft.block;

import java.util.List;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class BlockAnvil extends BlockFalling {
   public static final PropertyDirection field_176506_a = PropertyDirection.func_177712_a("facing", EnumFacing.Plane.HORIZONTAL);
   public static final PropertyInteger field_176505_b = PropertyInteger.func_177719_a("damage", 0, 2);

   protected BlockAnvil() {
      super(Material.field_151574_g);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176506_a, EnumFacing.NORTH).func_177226_a(field_176505_b, Integer.valueOf(0)));
      this.func_149713_g(0);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149662_c() {
      return false;
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      EnumFacing enumfacing = p_180642_8_.func_174811_aO().func_176746_e();
      return super.func_180642_a(p_180642_1_, p_180642_2_, p_180642_3_, p_180642_4_, p_180642_5_, p_180642_6_, p_180642_7_, p_180642_8_).func_177226_a(field_176506_a, enumfacing).func_177226_a(field_176505_b, Integer.valueOf(p_180642_7_ >> 2));
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumFacing p_180639_5_, float p_180639_6_, float p_180639_7_, float p_180639_8_) {
      if(!p_180639_1_.field_72995_K) {
         p_180639_4_.func_180468_a(new BlockAnvil.Anvil(p_180639_1_, p_180639_2_));
      }

      return true;
   }

   public int func_180651_a(IBlockState p_180651_1_) {
      return ((Integer)p_180651_1_.func_177229_b(field_176505_b)).intValue();
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      EnumFacing enumfacing = (EnumFacing)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176506_a);
      if(enumfacing.func_176740_k() == EnumFacing.Axis.X) {
         this.func_149676_a(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
      } else {
         this.func_149676_a(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
      }

   }

   public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List<ItemStack> p_149666_3_) {
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
      p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
   }

   protected void func_149829_a(EntityFallingBlock p_149829_1_) {
      p_149829_1_.func_145806_a(true);
   }

   public void func_176502_a_(World p_176502_1_, BlockPos p_176502_2_) {
      p_176502_1_.func_175718_b(1022, p_176502_2_, 0);
   }

   public boolean func_176225_a(IBlockAccess p_176225_1_, BlockPos p_176225_2_, EnumFacing p_176225_3_) {
      return true;
   }

   public IBlockState func_176217_b(IBlockState p_176217_1_) {
      return this.func_176223_P().func_177226_a(field_176506_a, EnumFacing.SOUTH);
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176506_a, EnumFacing.func_176731_b(p_176203_1_ & 3)).func_177226_a(field_176505_b, Integer.valueOf((p_176203_1_ & 15) >> 2));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176506_a)).func_176736_b();
      i = i | ((Integer)p_176201_1_.func_177229_b(field_176505_b)).intValue() << 2;
      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176506_a, field_176505_b});
   }

   public static class Anvil implements IInteractionObject {
      private final World field_175130_a;
      private final BlockPos field_175129_b;

      public Anvil(World p_i45741_1_, BlockPos p_i45741_2_) {
         this.field_175130_a = p_i45741_1_;
         this.field_175129_b = p_i45741_2_;
      }

      public String func_70005_c_() {
         return "anvil";
      }

      public boolean func_145818_k_() {
         return false;
      }

      public IChatComponent func_145748_c_() {
         return new ChatComponentTranslation(Blocks.field_150467_bQ.func_149739_a() + ".name", new Object[0]);
      }

      public Container func_174876_a(InventoryPlayer p_174876_1_, EntityPlayer p_174876_2_) {
         return new ContainerRepair(p_174876_1_, this.field_175130_a, this.field_175129_b, p_174876_2_);
      }

      public String func_174875_k() {
         return "minecraft:anvil";
      }
   }
}
