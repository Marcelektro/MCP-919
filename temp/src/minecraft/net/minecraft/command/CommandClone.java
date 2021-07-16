package net.minecraft.command;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class CommandClone extends CommandBase {
   public String func_71517_b() {
      return "clone";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.clone.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 9) {
         throw new WrongUsageException("commands.clone.usage", new Object[0]);
      } else {
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = func_175757_a(p_71515_1_, p_71515_2_, 0, false);
         BlockPos blockpos1 = func_175757_a(p_71515_1_, p_71515_2_, 3, false);
         BlockPos blockpos2 = func_175757_a(p_71515_1_, p_71515_2_, 6, false);
         StructureBoundingBox structureboundingbox = new StructureBoundingBox(blockpos, blockpos1);
         StructureBoundingBox structureboundingbox1 = new StructureBoundingBox(blockpos2, blockpos2.func_177971_a(structureboundingbox.func_175896_b()));
         int i = structureboundingbox.func_78883_b() * structureboundingbox.func_78882_c() * structureboundingbox.func_78880_d();
         if(i > '\u8000') {
            throw new CommandException("commands.clone.tooManyBlocks", new Object[]{Integer.valueOf(i), Integer.valueOf('\u8000')});
         } else {
            boolean flag = false;
            Block block = null;
            int j = -1;
            if((p_71515_2_.length < 11 || !p_71515_2_[10].equals("force") && !p_71515_2_[10].equals("move")) && structureboundingbox.func_78884_a(structureboundingbox1)) {
               throw new CommandException("commands.clone.noOverlap", new Object[0]);
            } else {
               if(p_71515_2_.length >= 11 && p_71515_2_[10].equals("move")) {
                  flag = true;
               }

               if(structureboundingbox.field_78895_b >= 0 && structureboundingbox.field_78894_e < 256 && structureboundingbox1.field_78895_b >= 0 && structureboundingbox1.field_78894_e < 256) {
                  World world = p_71515_1_.func_130014_f_();
                  if(world.func_175711_a(structureboundingbox) && world.func_175711_a(structureboundingbox1)) {
                     boolean flag1 = false;
                     if(p_71515_2_.length >= 10) {
                        if(p_71515_2_[9].equals("masked")) {
                           flag1 = true;
                        } else if(p_71515_2_[9].equals("filtered")) {
                           if(p_71515_2_.length < 12) {
                              throw new WrongUsageException("commands.clone.usage", new Object[0]);
                           }

                           block = func_147180_g(p_71515_1_, p_71515_2_[11]);
                           if(p_71515_2_.length >= 13) {
                              j = func_175764_a(p_71515_2_[12], 0, 15);
                           }
                        }
                     }

                     List<CommandClone.StaticCloneData> list = Lists.<CommandClone.StaticCloneData>newArrayList();
                     List<CommandClone.StaticCloneData> list1 = Lists.<CommandClone.StaticCloneData>newArrayList();
                     List<CommandClone.StaticCloneData> list2 = Lists.<CommandClone.StaticCloneData>newArrayList();
                     LinkedList<BlockPos> linkedlist = Lists.<BlockPos>newLinkedList();
                     BlockPos blockpos3 = new BlockPos(structureboundingbox1.field_78897_a - structureboundingbox.field_78897_a, structureboundingbox1.field_78895_b - structureboundingbox.field_78895_b, structureboundingbox1.field_78896_c - structureboundingbox.field_78896_c);

                     for(int k = structureboundingbox.field_78896_c; k <= structureboundingbox.field_78892_f; ++k) {
                        for(int l = structureboundingbox.field_78895_b; l <= structureboundingbox.field_78894_e; ++l) {
                           for(int i1 = structureboundingbox.field_78897_a; i1 <= structureboundingbox.field_78893_d; ++i1) {
                              BlockPos blockpos4 = new BlockPos(i1, l, k);
                              BlockPos blockpos5 = blockpos4.func_177971_a(blockpos3);
                              IBlockState iblockstate = world.func_180495_p(blockpos4);
                              if((!flag1 || iblockstate.func_177230_c() != Blocks.field_150350_a) && (block == null || iblockstate.func_177230_c() == block && (j < 0 || iblockstate.func_177230_c().func_176201_c(iblockstate) == j))) {
                                 TileEntity tileentity = world.func_175625_s(blockpos4);
                                 if(tileentity != null) {
                                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                                    tileentity.func_145841_b(nbttagcompound);
                                    list1.add(new CommandClone.StaticCloneData(blockpos5, iblockstate, nbttagcompound));
                                    linkedlist.addLast(blockpos4);
                                 } else if(!iblockstate.func_177230_c().func_149730_j() && !iblockstate.func_177230_c().func_149686_d()) {
                                    list2.add(new CommandClone.StaticCloneData(blockpos5, iblockstate, (NBTTagCompound)null));
                                    linkedlist.addFirst(blockpos4);
                                 } else {
                                    list.add(new CommandClone.StaticCloneData(blockpos5, iblockstate, (NBTTagCompound)null));
                                    linkedlist.addLast(blockpos4);
                                 }
                              }
                           }
                        }
                     }

                     if(flag) {
                        for(BlockPos blockpos6 : linkedlist) {
                           TileEntity tileentity1 = world.func_175625_s(blockpos6);
                           if(tileentity1 instanceof IInventory) {
                              ((IInventory)tileentity1).func_174888_l();
                           }

                           world.func_180501_a(blockpos6, Blocks.field_180401_cv.func_176223_P(), 2);
                        }

                        for(BlockPos blockpos7 : linkedlist) {
                           world.func_180501_a(blockpos7, Blocks.field_150350_a.func_176223_P(), 3);
                        }
                     }

                     List<CommandClone.StaticCloneData> list3 = Lists.<CommandClone.StaticCloneData>newArrayList();
                     list3.addAll(list);
                     list3.addAll(list1);
                     list3.addAll(list2);
                     List<CommandClone.StaticCloneData> list4 = Lists.<CommandClone.StaticCloneData>reverse(list3);

                     for(CommandClone.StaticCloneData commandclone$staticclonedata : list4) {
                        TileEntity tileentity2 = world.func_175625_s(commandclone$staticclonedata.field_179537_a);
                        if(tileentity2 instanceof IInventory) {
                           ((IInventory)tileentity2).func_174888_l();
                        }

                        world.func_180501_a(commandclone$staticclonedata.field_179537_a, Blocks.field_180401_cv.func_176223_P(), 2);
                     }

                     i = 0;

                     for(CommandClone.StaticCloneData commandclone$staticclonedata1 : list3) {
                        if(world.func_180501_a(commandclone$staticclonedata1.field_179537_a, commandclone$staticclonedata1.field_179535_b, 2)) {
                           ++i;
                        }
                     }

                     for(CommandClone.StaticCloneData commandclone$staticclonedata2 : list1) {
                        TileEntity tileentity3 = world.func_175625_s(commandclone$staticclonedata2.field_179537_a);
                        if(commandclone$staticclonedata2.field_179536_c != null && tileentity3 != null) {
                           commandclone$staticclonedata2.field_179536_c.func_74768_a("x", commandclone$staticclonedata2.field_179537_a.func_177958_n());
                           commandclone$staticclonedata2.field_179536_c.func_74768_a("y", commandclone$staticclonedata2.field_179537_a.func_177956_o());
                           commandclone$staticclonedata2.field_179536_c.func_74768_a("z", commandclone$staticclonedata2.field_179537_a.func_177952_p());
                           tileentity3.func_145839_a(commandclone$staticclonedata2.field_179536_c);
                           tileentity3.func_70296_d();
                        }

                        world.func_180501_a(commandclone$staticclonedata2.field_179537_a, commandclone$staticclonedata2.field_179535_b, 2);
                     }

                     for(CommandClone.StaticCloneData commandclone$staticclonedata3 : list4) {
                        world.func_175722_b(commandclone$staticclonedata3.field_179537_a, commandclone$staticclonedata3.field_179535_b.func_177230_c());
                     }

                     List<NextTickListEntry> list5 = world.func_175712_a(structureboundingbox, false);
                     if(list5 != null) {
                        for(NextTickListEntry nextticklistentry : list5) {
                           if(structureboundingbox.func_175898_b(nextticklistentry.field_180282_a)) {
                              BlockPos blockpos8 = nextticklistentry.field_180282_a.func_177971_a(blockpos3);
                              world.func_180497_b(blockpos8, nextticklistentry.func_151351_a(), (int)(nextticklistentry.field_77180_e - world.func_72912_H().func_82573_f()), nextticklistentry.field_82754_f);
                           }
                        }
                     }

                     if(i <= 0) {
                        throw new CommandException("commands.clone.failed", new Object[0]);
                     } else {
                        p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_BLOCKS, i);
                        func_152373_a(p_71515_1_, this, "commands.clone.success", new Object[]{Integer.valueOf(i)});
                     }
                  } else {
                     throw new CommandException("commands.clone.outOfWorld", new Object[0]);
                  }
               } else {
                  throw new CommandException("commands.clone.outOfWorld", new Object[0]);
               }
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length > 0 && p_180525_2_.length <= 3?func_175771_a(p_180525_2_, 0, p_180525_3_):(p_180525_2_.length > 3 && p_180525_2_.length <= 6?func_175771_a(p_180525_2_, 3, p_180525_3_):(p_180525_2_.length > 6 && p_180525_2_.length <= 9?func_175771_a(p_180525_2_, 6, p_180525_3_):(p_180525_2_.length == 10?func_71530_a(p_180525_2_, new String[]{"replace", "masked", "filtered"}):(p_180525_2_.length == 11?func_71530_a(p_180525_2_, new String[]{"normal", "force", "move"}):(p_180525_2_.length == 12 && "filtered".equals(p_180525_2_[9])?func_175762_a(p_180525_2_, Block.field_149771_c.func_148742_b()):null)))));
   }

   static class StaticCloneData {
      public final BlockPos field_179537_a;
      public final IBlockState field_179535_b;
      public final NBTTagCompound field_179536_c;

      public StaticCloneData(BlockPos p_i46037_1_, IBlockState p_i46037_2_, NBTTagCompound p_i46037_3_) {
         this.field_179537_a = p_i46037_1_;
         this.field_179535_b = p_i46037_2_;
         this.field_179536_c = p_i46037_3_;
      }
   }
}
