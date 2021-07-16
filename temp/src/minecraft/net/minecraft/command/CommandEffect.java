package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;

public class CommandEffect extends CommandBase {
   public String func_71517_b() {
      return "effect";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.effect.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 2) {
         throw new WrongUsageException("commands.effect.usage", new Object[0]);
      } else {
         EntityLivingBase entitylivingbase = (EntityLivingBase)func_175759_a(p_71515_1_, p_71515_2_[0], EntityLivingBase.class);
         if(p_71515_2_[1].equals("clear")) {
            if(entitylivingbase.func_70651_bq().isEmpty()) {
               throw new CommandException("commands.effect.failure.notActive.all", new Object[]{entitylivingbase.func_70005_c_()});
            } else {
               entitylivingbase.func_70674_bp();
               func_152373_a(p_71515_1_, this, "commands.effect.success.removed.all", new Object[]{entitylivingbase.func_70005_c_()});
            }
         } else {
            int i;
            try {
               i = func_180528_a(p_71515_2_[1], 1);
            } catch (NumberInvalidException numberinvalidexception) {
               Potion potion = Potion.func_180142_b(p_71515_2_[1]);
               if(potion == null) {
                  throw numberinvalidexception;
               }

               i = potion.field_76415_H;
            }

            int j = 600;
            int l = 30;
            int k = 0;
            if(i >= 0 && i < Potion.field_76425_a.length && Potion.field_76425_a[i] != null) {
               Potion potion1 = Potion.field_76425_a[i];
               if(p_71515_2_.length >= 3) {
                  l = func_175764_a(p_71515_2_[2], 0, 1000000);
                  if(potion1.func_76403_b()) {
                     j = l;
                  } else {
                     j = l * 20;
                  }
               } else if(potion1.func_76403_b()) {
                  j = 1;
               }

               if(p_71515_2_.length >= 4) {
                  k = func_175764_a(p_71515_2_[3], 0, 255);
               }

               boolean flag = true;
               if(p_71515_2_.length >= 5 && "true".equalsIgnoreCase(p_71515_2_[4])) {
                  flag = false;
               }

               if(l > 0) {
                  PotionEffect potioneffect = new PotionEffect(i, j, k, false, flag);
                  entitylivingbase.func_70690_d(potioneffect);
                  func_152373_a(p_71515_1_, this, "commands.effect.success", new Object[]{new ChatComponentTranslation(potioneffect.func_76453_d(), new Object[0]), Integer.valueOf(i), Integer.valueOf(k), entitylivingbase.func_70005_c_(), Integer.valueOf(l)});
               } else if(entitylivingbase.func_82165_m(i)) {
                  entitylivingbase.func_82170_o(i);
                  func_152373_a(p_71515_1_, this, "commands.effect.success.removed", new Object[]{new ChatComponentTranslation(potion1.func_76393_a(), new Object[0]), entitylivingbase.func_70005_c_()});
               } else {
                  throw new CommandException("commands.effect.failure.notActive", new Object[]{new ChatComponentTranslation(potion1.func_76393_a(), new Object[0]), entitylivingbase.func_70005_c_()});
               }
            } else {
               throw new NumberInvalidException("commands.effect.notFound", new Object[]{Integer.valueOf(i)});
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, this.func_98152_d()):(p_180525_2_.length == 2?func_175762_a(p_180525_2_, Potion.func_181168_c()):(p_180525_2_.length == 5?func_71530_a(p_180525_2_, new String[]{"true", "false"}):null));
   }

   protected String[] func_98152_d() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
