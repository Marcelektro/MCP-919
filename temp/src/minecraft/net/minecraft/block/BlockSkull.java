package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSkull extends BlockContainer {
   public static final PropertyDirection field_176418_a = PropertyDirection.func_177714_a("facing");
   public static final PropertyBool field_176417_b = PropertyBool.func_177716_a("nodrop");
   private static final Predicate<BlockWorldState> field_176419_M = new Predicate<BlockWorldState>() {
      public boolean apply(BlockWorldState p_apply_1_) {
         return p_apply_1_.func_177509_a() != null && p_apply_1_.func_177509_a().func_177230_c() == Blocks.field_150465_bP && p_apply_1_.func_177507_b() instanceof TileEntitySkull && ((TileEntitySkull)p_apply_1_.func_177507_b()).func_145904_a() == 1;
      }
   };
   private BlockPattern field_176420_N;
   private BlockPattern field_176421_O;

   protected BlockSkull() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176418_a, EnumFacing.NORTH).func_177226_a(field_176417_b, Boolean.valueOf(false)));
      this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
   }

   public String func_149732_F() {
      return StatCollector.func_74838_a("tile.skull.skeleton.name");
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public void func_180654_a(IBlockAccess p_180654_1_, BlockPos p_180654_2_) {
      switch((EnumFacing)p_180654_1_.func_180495_p(p_180654_2_).func_177229_b(field_176418_a)) {
      case UP:
      default:
         this.func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
         break;
      case NORTH:
         this.func_149676_a(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
         break;
      case SOUTH:
         this.func_149676_a(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
         break;
      case WEST:
         this.func_149676_a(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
         break;
      case EAST:
         this.func_149676_a(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
      }

   }

   public AxisAlignedBB func_180640_a(World p_180640_1_, BlockPos p_180640_2_, IBlockState p_180640_3_) {
      this.func_180654_a(p_180640_1_, p_180640_2_);
      return super.func_180640_a(p_180640_1_, p_180640_2_, p_180640_3_);
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_176418_a, p_180642_8_.func_174811_aO()).func_177226_a(field_176417_b, Boolean.valueOf(false));
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntitySkull();
   }

   public Item func_180665_b(World p_180665_1_, BlockPos p_180665_2_) {
      return Items.field_151144_bL;
   }

   public int func_176222_j(World p_176222_1_, BlockPos p_176222_2_) {
      TileEntity tileentity = p_176222_1_.func_175625_s(p_176222_2_);
      return tileentity instanceof TileEntitySkull?((TileEntitySkull)tileentity).func_145904_a():super.func_176222_j(p_176222_1_, p_176222_2_);
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      if(p_176208_4_.field_71075_bZ.field_75098_d) {
         p_176208_3_ = p_176208_3_.func_177226_a(field_176417_b, Boolean.valueOf(true));
         p_176208_1_.func_180501_a(p_176208_2_, p_176208_3_, 4);
      }

      super.func_176208_a(p_176208_1_, p_176208_2_, p_176208_3_, p_176208_4_);
   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      if(!p_180663_1_.field_72995_K) {
         if(!((Boolean)p_180663_3_.func_177229_b(field_176417_b)).booleanValue()) {
            TileEntity tileentity = p_180663_1_.func_175625_s(p_180663_2_);
            if(tileentity instanceof TileEntitySkull) {
               TileEntitySkull tileentityskull = (TileEntitySkull)tileentity;
               ItemStack itemstack = new ItemStack(Items.field_151144_bL, 1, this.func_176222_j(p_180663_1_, p_180663_2_));
               if(tileentityskull.func_145904_a() == 3 && tileentityskull.func_152108_a() != null) {
                  itemstack.func_77982_d(new NBTTagCompound());
                  NBTTagCompound nbttagcompound = new NBTTagCompound();
                  NBTUtil.func_180708_a(nbttagcompound, tileentityskull.func_152108_a());
                  itemstack.func_77978_p().func_74782_a("SkullOwner", nbttagcompound);
               }

               func_180635_a(p_180663_1_, p_180663_2_, itemstack);
            }
         }

         super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
      }
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151144_bL;
   }

   public boolean func_176415_b(World p_176415_1_, BlockPos p_176415_2_, ItemStack p_176415_3_) {
      return p_176415_3_.func_77960_j() == 1 && p_176415_2_.func_177956_o() >= 2 && p_176415_1_.func_175659_aa() != EnumDifficulty.PEACEFUL && !p_176415_1_.field_72995_K?this.func_176414_j().func_177681_a(p_176415_1_, p_176415_2_) != null:false;
   }

   public void func_180679_a(World p_180679_1_, BlockPos p_180679_2_, TileEntitySkull p_180679_3_) {
      if(p_180679_3_.func_145904_a() == 1 && p_180679_2_.func_177956_o() >= 2 && p_180679_1_.func_175659_aa() != EnumDifficulty.PEACEFUL && !p_180679_1_.field_72995_K) {
         BlockPattern blockpattern = this.func_176416_l();
         BlockPattern.PatternHelper blockpattern$patternhelper = blockpattern.func_177681_a(p_180679_1_, p_180679_2_);
         if(blockpattern$patternhelper != null) {
            for(int i = 0; i < 3; ++i) {
               BlockWorldState blockworldstate = blockpattern$patternhelper.func_177670_a(i, 0, 0);
               p_180679_1_.func_180501_a(blockworldstate.func_177508_d(), blockworldstate.func_177509_a().func_177226_a(field_176417_b, Boolean.valueOf(true)), 2);
            }

            for(int j = 0; j < blockpattern.func_177684_c(); ++j) {
               for(int k = 0; k < blockpattern.func_177685_b(); ++k) {
                  BlockWorldState blockworldstate1 = blockpattern$patternhelper.func_177670_a(j, k, 0);
                  p_180679_1_.func_180501_a(blockworldstate1.func_177508_d(), Blocks.field_150350_a.func_176223_P(), 2);
               }
            }

            BlockPos blockpos = blockpattern$patternhelper.func_177670_a(1, 0, 0).func_177508_d();
            EntityWither entitywither = new EntityWither(p_180679_1_);
            BlockPos blockpos1 = blockpattern$patternhelper.func_177670_a(1, 2, 0).func_177508_d();
            entitywither.func_70012_b((double)blockpos1.func_177958_n() + 0.5D, (double)blockpos1.func_177956_o() + 0.55D, (double)blockpos1.func_177952_p() + 0.5D, blockpattern$patternhelper.func_177669_b().func_176740_k() == EnumFacing.Axis.X?0.0F:90.0F, 0.0F);
            entitywither.field_70761_aq = blockpattern$patternhelper.func_177669_b().func_176740_k() == EnumFacing.Axis.X?0.0F:90.0F;
            entitywither.func_82206_m();

            for(EntityPlayer entityplayer : p_180679_1_.func_72872_a(EntityPlayer.class, entitywither.func_174813_aQ().func_72314_b(50.0D, 50.0D, 50.0D))) {
               entityplayer.func_71029_a(AchievementList.field_150963_I);
            }

            p_180679_1_.func_72838_d(entitywither);

            for(int l = 0; l < 120; ++l) {
               p_180679_1_.func_175688_a(EnumParticleTypes.SNOWBALL, (double)blockpos.func_177958_n() + p_180679_1_.field_73012_v.nextDouble(), (double)(blockpos.func_177956_o() - 2) + p_180679_1_.field_73012_v.nextDouble() * 3.9D, (double)blockpos.func_177952_p() + p_180679_1_.field_73012_v.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
            }

            for(int i1 = 0; i1 < blockpattern.func_177684_c(); ++i1) {
               for(int j1 = 0; j1 < blockpattern.func_177685_b(); ++j1) {
                  BlockWorldState blockworldstate2 = blockpattern$patternhelper.func_177670_a(i1, j1, 0);
                  p_180679_1_.func_175722_b(blockworldstate2.func_177508_d(), Blocks.field_150350_a);
               }
            }

         }
      }
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176418_a, EnumFacing.func_82600_a(p_176203_1_ & 7)).func_177226_a(field_176417_b, Boolean.valueOf((p_176203_1_ & 8) > 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      i = i | ((EnumFacing)p_176201_1_.func_177229_b(field_176418_a)).func_176745_a();
      if(((Boolean)p_176201_1_.func_177229_b(field_176417_b)).booleanValue()) {
         i |= 8;
      }

      return i;
   }

   protected BlockState func_180661_e() {
      return new BlockState(this, new IProperty[]{field_176418_a, field_176417_b});
   }

   protected BlockPattern func_176414_j() {
      if(this.field_176420_N == null) {
         this.field_176420_N = FactoryBlockPattern.func_177660_a().func_177659_a(new String[]{"   ", "###", "~#~"}).func_177662_a('#', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150425_aM))).func_177662_a('~', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150350_a))).func_177661_b();
      }

      return this.field_176420_N;
   }

   protected BlockPattern func_176416_l() {
      if(this.field_176421_O == null) {
         this.field_176421_O = FactoryBlockPattern.func_177660_a().func_177659_a(new String[]{"^^^", "###", "~#~"}).func_177662_a('#', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150425_aM))).func_177662_a('^', field_176419_M).func_177662_a('~', BlockWorldState.func_177510_a(BlockStateHelper.func_177638_a(Blocks.field_150350_a))).func_177661_b();
      }

      return this.field_176421_O;
   }
}
