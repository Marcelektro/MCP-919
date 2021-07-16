package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandEnchant extends CommandBase {
   public String func_71517_b() {
      return "enchant";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.enchant.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.enchant.usage", new Object[0]);
      } else {
         EntityPlayer entityplayer = func_82359_c(p_71515_1_, p_71515_2_[0]);
         p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 0);

         int i;
         try {
            i = func_180528_a(p_71515_2_[1], 0);
         } catch (NumberInvalidException numberinvalidexception) {
            Enchantment enchantment = Enchantment.func_180305_b(p_71515_2_[1]);
            if(enchantment == null) {
               throw numberinvalidexception;
            }

            i = enchantment.field_77352_x;
         }

         int j = 1;
         ItemStack itemstack = entityplayer.func_71045_bC();
         if(itemstack == null) {
            throw new CommandException("commands.enchant.noItem", new Object[0]);
         } else {
            Enchantment enchantment1 = Enchantment.func_180306_c(i);
            if(enchantment1 == null) {
               throw new NumberInvalidException("commands.enchant.notFound", new Object[]{Integer.valueOf(i)});
            } else if(!enchantment1.func_92089_a(itemstack)) {
               throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
            } else {
               if(p_71515_2_.length >= 3) {
                  j = func_175764_a(p_71515_2_[2], enchantment1.func_77319_d(), enchantment1.func_77325_b());
               }

               if(itemstack.func_77942_o()) {
                  NBTTagList nbttaglist = itemstack.func_77986_q();
                  if(nbttaglist != null) {
                     for(int k = 0; k < nbttaglist.func_74745_c(); ++k) {
                        int l = nbttaglist.func_150305_b(k).func_74765_d("id");
                        if(Enchantment.func_180306_c(l) != null) {
                           Enchantment enchantment2 = Enchantment.func_180306_c(l);
                           if(!enchantment2.func_77326_a(enchantment1)) {
                              throw new CommandException("commands.enchant.cantCombine", new Object[]{enchantment1.func_77316_c(j), enchantment2.func_77316_c(nbttaglist.func_150305_b(k).func_74765_d("lvl"))});
                           }
                        }
                     }
                  }
               }

               itemstack.func_77966_a(enchantment1, j);
               func_152373_a(p_71515_1_, this, "commands.enchant.success", new Object[0]);
               p_71515_1_.func_174794_a(CommandResultStats.Type.AFFECTED_ITEMS, 1);
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, this.func_90022_d()):(p_180525_2_.length == 2?func_175762_a(p_180525_2_, Enchantment.func_181077_c()):null);
   }

   protected String[] func_90022_d() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
