package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CommandParticle extends CommandBase {
   public String func_71517_b() {
      return "particle";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.particle.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 8) {
         throw new WrongUsageException("commands.particle.usage", new Object[0]);
      } else {
         boolean flag = false;
         EnumParticleTypes enumparticletypes = null;

         for(EnumParticleTypes enumparticletypes1 : EnumParticleTypes.values()) {
            if(enumparticletypes1.func_179343_f()) {
               if(p_71515_2_[0].startsWith(enumparticletypes1.func_179346_b())) {
                  flag = true;
                  enumparticletypes = enumparticletypes1;
                  break;
               }
            } else if(p_71515_2_[0].equals(enumparticletypes1.func_179346_b())) {
               flag = true;
               enumparticletypes = enumparticletypes1;
               break;
            }
         }

         if(!flag) {
            throw new CommandException("commands.particle.notFound", new Object[]{p_71515_2_[0]});
         } else {
            String s = p_71515_2_[0];
            Vec3 vec3 = p_71515_1_.func_174791_d();
            double d6 = (double)((float)func_175761_b(vec3.field_72450_a, p_71515_2_[1], true));
            double d0 = (double)((float)func_175761_b(vec3.field_72448_b, p_71515_2_[2], true));
            double d1 = (double)((float)func_175761_b(vec3.field_72449_c, p_71515_2_[3], true));
            double d2 = (double)((float)func_175765_c(p_71515_2_[4]));
            double d3 = (double)((float)func_175765_c(p_71515_2_[5]));
            double d4 = (double)((float)func_175765_c(p_71515_2_[6]));
            double d5 = (double)((float)func_175765_c(p_71515_2_[7]));
            int i = 0;
            if(p_71515_2_.length > 8) {
               i = func_180528_a(p_71515_2_[8], 0);
            }

            boolean flag1 = false;
            if(p_71515_2_.length > 9 && "force".equals(p_71515_2_[9])) {
               flag1 = true;
            }

            World world = p_71515_1_.func_130014_f_();
            if(world instanceof WorldServer) {
               WorldServer worldserver = (WorldServer)world;
               int[] aint = new int[enumparticletypes.func_179345_d()];
               if(enumparticletypes.func_179343_f()) {
                  String[] astring = p_71515_2_[0].split("_", 3);

                  for(int j = 1; j < astring.length; ++j) {
                     try {
                        aint[j - 1] = Integer.parseInt(astring[j]);
                     } catch (NumberFormatException var29) {
                        throw new CommandException("commands.particle.notFound", new Object[]{p_71515_2_[0]});
                     }
                  }
               }

               worldserver.func_180505_a(enumparticletypes, flag1, d6, d0, d1, i, d2, d3, d4, d5, aint);
               func_152373_a(p_71515_1_, this, "commands.particle.success", new Object[]{s, Integer.valueOf(Math.max(i, 1))});
            }

         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, EnumParticleTypes.func_179349_a()):(p_180525_2_.length > 1 && p_180525_2_.length <= 4?func_175771_a(p_180525_2_, 1, p_180525_3_):(p_180525_2_.length == 10?func_71530_a(p_180525_2_, new String[]{"normal", "force"}):null));
   }
}
