package net.minecraft.init;

import com.mojang.authlib.GameProfile;
import java.io.PrintStream;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LoggingPrintStream;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bootstrap {
   private static final PrintStream field_179872_a = System.out;
   private static boolean field_151355_a = false;
   private static final Logger field_179871_c = LogManager.getLogger();

   public static boolean func_179869_a() {
      return field_151355_a;
   }

   static void func_151353_a() {
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151032_g, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            EntityArrow entityarrow = new EntityArrow(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
            entityarrow.field_70251_a = 1;
            return entityarrow;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151110_aK, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            return new EntityEgg(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151126_ay, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            return new EntitySnowball(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151062_by, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            return new EntityExpBottle(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
         }

         protected float func_82498_a() {
            return super.func_82498_a() * 0.5F;
         }

         protected float func_82500_b() {
            return super.func_82500_b() * 1.25F;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151068_bn, new IBehaviorDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150843_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82482_a(IBlockSource p_82482_1_, final ItemStack p_82482_2_) {
            return ItemPotion.func_77831_g(p_82482_2_.func_77960_j())?(new BehaviorProjectileDispense() {
               protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
                  return new EntityPotion(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c(), p_82482_2_.func_77946_l());
               }

               protected float func_82498_a() {
                  return super.func_82498_a() * 0.5F;
               }

               protected float func_82500_b() {
                  return super.func_82500_b() * 1.25F;
               }
            }).func_82482_a(p_82482_1_, p_82482_2_):this.field_150843_b.func_82482_a(p_82482_1_, p_82482_2_);
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151063_bx, new BehaviorDefaultDispenseItem() {
         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            double d0 = p_82487_1_.func_82615_a() + (double)enumfacing.func_82601_c();
            double d1 = (double)((float)p_82487_1_.func_180699_d().func_177956_o() + 0.2F);
            double d2 = p_82487_1_.func_82616_c() + (double)enumfacing.func_82599_e();
            Entity entity = ItemMonsterPlacer.func_77840_a(p_82487_1_.func_82618_k(), p_82487_2_.func_77960_j(), d0, d1, d2);
            if(entity instanceof EntityLivingBase && p_82487_2_.func_82837_s()) {
               ((EntityLiving)entity).func_96094_a(p_82487_2_.func_82833_r());
            }

            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151152_bP, new BehaviorDefaultDispenseItem() {
         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            double d0 = p_82487_1_.func_82615_a() + (double)enumfacing.func_82601_c();
            double d1 = (double)((float)p_82487_1_.func_180699_d().func_177956_o() + 0.2F);
            double d2 = p_82487_1_.func_82616_c() + (double)enumfacing.func_82599_e();
            EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(p_82487_1_.func_82618_k(), d0, d1, d2, p_82487_2_);
            p_82487_1_.func_82618_k().func_72838_d(entityfireworkrocket);
            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            p_82485_1_.func_82618_k().func_175718_b(1002, p_82485_1_.func_180699_d(), 0);
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151059_bz, new BehaviorDefaultDispenseItem() {
         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            IPosition iposition = BlockDispenser.func_149939_a(p_82487_1_);
            double d0 = iposition.func_82615_a() + (double)((float)enumfacing.func_82601_c() * 0.3F);
            double d1 = iposition.func_82617_b() + (double)((float)enumfacing.func_96559_d() * 0.3F);
            double d2 = iposition.func_82616_c() + (double)((float)enumfacing.func_82599_e() * 0.3F);
            World world = p_82487_1_.func_82618_k();
            Random random = world.field_73012_v;
            double d3 = random.nextGaussian() * 0.05D + (double)enumfacing.func_82601_c();
            double d4 = random.nextGaussian() * 0.05D + (double)enumfacing.func_96559_d();
            double d5 = random.nextGaussian() * 0.05D + (double)enumfacing.func_82599_e();
            world.func_72838_d(new EntitySmallFireball(world, d0, d1, d2, d3, d4, d5));
            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            p_82485_1_.func_82618_k().func_175718_b(1009, p_82485_1_.func_180699_d(), 0);
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151124_az, new BehaviorDefaultDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150842_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            World world = p_82487_1_.func_82618_k();
            double d0 = p_82487_1_.func_82615_a() + (double)((float)enumfacing.func_82601_c() * 1.125F);
            double d1 = p_82487_1_.func_82617_b() + (double)((float)enumfacing.func_96559_d() * 1.125F);
            double d2 = p_82487_1_.func_82616_c() + (double)((float)enumfacing.func_82599_e() * 1.125F);
            BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(enumfacing);
            Material material = world.func_180495_p(blockpos).func_177230_c().func_149688_o();
            double d3;
            if(Material.field_151586_h.equals(material)) {
               d3 = 1.0D;
            } else {
               if(!Material.field_151579_a.equals(material) || !Material.field_151586_h.equals(world.func_180495_p(blockpos.func_177977_b()).func_177230_c().func_149688_o())) {
                  return this.field_150842_b.func_82482_a(p_82487_1_, p_82487_2_);
               }

               d3 = 0.0D;
            }

            EntityBoat entityboat = new EntityBoat(world, d0, d1 + d3, d2);
            world.func_72838_d(entityboat);
            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
         }
      });
      IBehaviorDispenseItem ibehaviordispenseitem = new BehaviorDefaultDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150841_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            ItemBucket itembucket = (ItemBucket)p_82487_2_.func_77973_b();
            BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            if(itembucket.func_180616_a(p_82487_1_.func_82618_k(), blockpos)) {
               p_82487_2_.func_150996_a(Items.field_151133_ar);
               p_82487_2_.field_77994_a = 1;
               return p_82487_2_;
            } else {
               return this.field_150841_b.func_82482_a(p_82487_1_, p_82487_2_);
            }
         }
      };
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151129_at, ibehaviordispenseitem);
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151131_as, ibehaviordispenseitem);
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151133_ar, new BehaviorDefaultDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150840_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World world = p_82487_1_.func_82618_k();
            BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            IBlockState iblockstate = world.func_180495_p(blockpos);
            Block block = iblockstate.func_177230_c();
            Material material = block.func_149688_o();
            Item item;
            if(Material.field_151586_h.equals(material) && block instanceof BlockLiquid && ((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue() == 0) {
               item = Items.field_151131_as;
            } else {
               if(!Material.field_151587_i.equals(material) || !(block instanceof BlockLiquid) || ((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue() != 0) {
                  return super.func_82487_b(p_82487_1_, p_82487_2_);
               }

               item = Items.field_151129_at;
            }

            world.func_175698_g(blockpos);
            if(--p_82487_2_.field_77994_a == 0) {
               p_82487_2_.func_150996_a(item);
               p_82487_2_.field_77994_a = 1;
            } else if(((TileEntityDispenser)p_82487_1_.func_150835_j()).func_146019_a(new ItemStack(item)) < 0) {
               this.field_150840_b.func_82482_a(p_82487_1_, new ItemStack(item));
            }

            return p_82487_2_;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151033_d, new BehaviorDefaultDispenseItem() {
         private boolean field_150839_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World world = p_82487_1_.func_82618_k();
            BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            if(world.func_175623_d(blockpos)) {
               world.func_175656_a(blockpos, Blocks.field_150480_ab.func_176223_P());
               if(p_82487_2_.func_96631_a(1, world.field_73012_v)) {
                  p_82487_2_.field_77994_a = 0;
               }
            } else if(world.func_180495_p(blockpos).func_177230_c() == Blocks.field_150335_W) {
               Blocks.field_150335_W.func_176206_d(world, blockpos, Blocks.field_150335_W.func_176223_P().func_177226_a(BlockTNT.field_176246_a, Boolean.valueOf(true)));
               world.func_175698_g(blockpos);
            } else {
               this.field_150839_b = false;
            }

            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if(this.field_150839_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151100_aR, new BehaviorDefaultDispenseItem() {
         private boolean field_150838_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            if(EnumDyeColor.WHITE == EnumDyeColor.func_176766_a(p_82487_2_.func_77960_j())) {
               World world = p_82487_1_.func_82618_k();
               BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
               if(ItemDye.func_179234_a(p_82487_2_, world, blockpos)) {
                  if(!world.field_72995_K) {
                     world.func_175718_b(2005, blockpos, 0);
                  }
               } else {
                  this.field_150838_b = false;
               }

               return p_82487_2_;
            } else {
               return super.func_82487_b(p_82487_1_, p_82487_2_);
            }
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if(this.field_150838_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Item.func_150898_a(Blocks.field_150335_W), new BehaviorDefaultDispenseItem() {
         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World world = p_82487_1_.func_82618_k();
            BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double)blockpos.func_177958_n() + 0.5D, (double)blockpos.func_177956_o(), (double)blockpos.func_177952_p() + 0.5D, (EntityLivingBase)null);
            world.func_72838_d(entitytntprimed);
            world.func_72956_a(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
            --p_82487_2_.field_77994_a;
            return p_82487_2_;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151144_bL, new BehaviorDefaultDispenseItem() {
         private boolean field_179240_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World world = p_82487_1_.func_82618_k();
            EnumFacing enumfacing = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(enumfacing);
            BlockSkull blockskull = Blocks.field_150465_bP;
            if(world.func_175623_d(blockpos) && blockskull.func_176415_b(world, blockpos, p_82487_2_)) {
               if(!world.field_72995_K) {
                  world.func_180501_a(blockpos, blockskull.func_176223_P().func_177226_a(BlockSkull.field_176418_a, EnumFacing.UP), 3);
                  TileEntity tileentity = world.func_175625_s(blockpos);
                  if(tileentity instanceof TileEntitySkull) {
                     if(p_82487_2_.func_77960_j() == 3) {
                        GameProfile gameprofile = null;
                        if(p_82487_2_.func_77942_o()) {
                           NBTTagCompound nbttagcompound = p_82487_2_.func_77978_p();
                           if(nbttagcompound.func_150297_b("SkullOwner", 10)) {
                              gameprofile = NBTUtil.func_152459_a(nbttagcompound.func_74775_l("SkullOwner"));
                           } else if(nbttagcompound.func_150297_b("SkullOwner", 8)) {
                              String s = nbttagcompound.func_74779_i("SkullOwner");
                              if(!StringUtils.func_151246_b(s)) {
                                 gameprofile = new GameProfile((UUID)null, s);
                              }
                           }
                        }

                        ((TileEntitySkull)tileentity).func_152106_a(gameprofile);
                     } else {
                        ((TileEntitySkull)tileentity).func_152107_a(p_82487_2_.func_77960_j());
                     }

                     ((TileEntitySkull)tileentity).func_145903_a(enumfacing.func_176734_d().func_176736_b() * 4);
                     Blocks.field_150465_bP.func_180679_a(world, blockpos, (TileEntitySkull)tileentity);
                  }

                  --p_82487_2_.field_77994_a;
               }
            } else {
               this.field_179240_b = false;
            }

            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if(this.field_179240_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Item.func_150898_a(Blocks.field_150423_aK), new BehaviorDefaultDispenseItem() {
         private boolean field_179241_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World world = p_82487_1_.func_82618_k();
            BlockPos blockpos = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            BlockPumpkin blockpumpkin = (BlockPumpkin)Blocks.field_150423_aK;
            if(world.func_175623_d(blockpos) && blockpumpkin.func_176390_d(world, blockpos)) {
               if(!world.field_72995_K) {
                  world.func_180501_a(blockpos, blockpumpkin.func_176223_P(), 3);
               }

               --p_82487_2_.field_77994_a;
            } else {
               this.field_179241_b = false;
            }

            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if(this.field_179241_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
   }

   public static void func_151354_b() {
      if(!field_151355_a) {
         field_151355_a = true;
         if(field_179871_c.isDebugEnabled()) {
            func_179868_d();
         }

         Block.func_149671_p();
         BlockFire.func_149843_e();
         Item.func_150900_l();
         StatList.func_151178_a();
         func_151353_a();
      }
   }

   private static void func_179868_d() {
      System.setErr(new LoggingPrintStream("STDERR", System.err));
      System.setOut(new LoggingPrintStream("STDOUT", field_179872_a));
   }

   public static void func_179870_a(String p_179870_0_) {
      field_179872_a.println(p_179870_0_);
   }
}
