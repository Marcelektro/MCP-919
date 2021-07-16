package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class CommandPlaySound extends CommandBase {
   public String func_71517_b() {
      return "playsound";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.playsound.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 2) {
         throw new WrongUsageException(this.func_71518_a(p_71515_1_), new Object[0]);
      } else {
         int i = 0;
         String s = p_71515_2_[i++];
         EntityPlayerMP entityplayermp = func_82359_c(p_71515_1_, p_71515_2_[i++]);
         Vec3 vec3 = p_71515_1_.func_174791_d();
         double d0 = vec3.field_72450_a;
         if(p_71515_2_.length > i) {
            d0 = func_175761_b(d0, p_71515_2_[i++], true);
         }

         double d1 = vec3.field_72448_b;
         if(p_71515_2_.length > i) {
            d1 = func_175769_b(d1, p_71515_2_[i++], 0, 0, false);
         }

         double d2 = vec3.field_72449_c;
         if(p_71515_2_.length > i) {
            d2 = func_175761_b(d2, p_71515_2_[i++], true);
         }

         double d3 = 1.0D;
         if(p_71515_2_.length > i) {
            d3 = func_175756_a(p_71515_2_[i++], 0.0D, 3.4028234663852886E38D);
         }

         double d4 = 1.0D;
         if(p_71515_2_.length > i) {
            d4 = func_175756_a(p_71515_2_[i++], 0.0D, 2.0D);
         }

         double d5 = 0.0D;
         if(p_71515_2_.length > i) {
            d5 = func_175756_a(p_71515_2_[i], 0.0D, 1.0D);
         }

         double d6 = d3 > 1.0D?d3 * 16.0D:16.0D;
         double d7 = entityplayermp.func_70011_f(d0, d1, d2);
         if(d7 > d6) {
            if(d5 <= 0.0D) {
               throw new CommandException("commands.playsound.playerTooFar", new Object[]{entityplayermp.func_70005_c_()});
            }

            double d8 = d0 - entityplayermp.field_70165_t;
            double d9 = d1 - entityplayermp.field_70163_u;
            double d10 = d2 - entityplayermp.field_70161_v;
            double d11 = Math.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
            if(d11 > 0.0D) {
               d0 = entityplayermp.field_70165_t + d8 / d11 * 2.0D;
               d1 = entityplayermp.field_70163_u + d9 / d11 * 2.0D;
               d2 = entityplayermp.field_70161_v + d10 / d11 * 2.0D;
            }

            d3 = d5;
         }

         entityplayermp.field_71135_a.func_147359_a(new S29PacketSoundEffect(s, d0, d1, d2, (float)d3, (float)d4));
         func_152373_a(p_71515_1_, this, "commands.playsound.success", new Object[]{s, entityplayermp.func_70005_c_()});
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 2?func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z()):(p_180525_2_.length > 2 && p_180525_2_.length <= 5?func_175771_a(p_180525_2_, 2, p_180525_3_):null);
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 1;
   }
}
