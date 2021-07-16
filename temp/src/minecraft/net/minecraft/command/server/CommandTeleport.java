package net.minecraft.command.server;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class CommandTeleport extends CommandBase {
   public String func_71517_b() {
      return "tp";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.tp.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.tp.usage", new Object[0]);
      } else {
         int i = 0;
         Entity entity;
         if(p_71515_2_.length != 2 && p_71515_2_.length != 4 && p_71515_2_.length != 6) {
            entity = func_71521_c(p_71515_1_);
         } else {
            entity = func_175768_b(p_71515_1_, p_71515_2_[0]);
            i = 1;
         }

         if(p_71515_2_.length != 1 && p_71515_2_.length != 2) {
            if(p_71515_2_.length < i + 3) {
               throw new WrongUsageException("commands.tp.usage", new Object[0]);
            } else if(entity.field_70170_p != null) {
               int lvt_5_2_ = i + 1;
               CommandBase.CoordinateArg commandbase$coordinatearg = func_175770_a(entity.field_70165_t, p_71515_2_[i], true);
               CommandBase.CoordinateArg commandbase$coordinatearg1 = func_175767_a(entity.field_70163_u, p_71515_2_[lvt_5_2_++], 0, 0, false);
               CommandBase.CoordinateArg commandbase$coordinatearg2 = func_175770_a(entity.field_70161_v, p_71515_2_[lvt_5_2_++], true);
               CommandBase.CoordinateArg commandbase$coordinatearg3 = func_175770_a((double)entity.field_70177_z, p_71515_2_.length > lvt_5_2_?p_71515_2_[lvt_5_2_++]:"~", false);
               CommandBase.CoordinateArg commandbase$coordinatearg4 = func_175770_a((double)entity.field_70125_A, p_71515_2_.length > lvt_5_2_?p_71515_2_[lvt_5_2_]:"~", false);
               if(entity instanceof EntityPlayerMP) {
                  Set<S08PacketPlayerPosLook.EnumFlags> set = EnumSet.<S08PacketPlayerPosLook.EnumFlags>noneOf(S08PacketPlayerPosLook.EnumFlags.class);
                  if(commandbase$coordinatearg.func_179630_c()) {
                     set.add(S08PacketPlayerPosLook.EnumFlags.X);
                  }

                  if(commandbase$coordinatearg1.func_179630_c()) {
                     set.add(S08PacketPlayerPosLook.EnumFlags.Y);
                  }

                  if(commandbase$coordinatearg2.func_179630_c()) {
                     set.add(S08PacketPlayerPosLook.EnumFlags.Z);
                  }

                  if(commandbase$coordinatearg4.func_179630_c()) {
                     set.add(S08PacketPlayerPosLook.EnumFlags.X_ROT);
                  }

                  if(commandbase$coordinatearg3.func_179630_c()) {
                     set.add(S08PacketPlayerPosLook.EnumFlags.Y_ROT);
                  }

                  float f = (float)commandbase$coordinatearg3.func_179629_b();
                  if(!commandbase$coordinatearg3.func_179630_c()) {
                     f = MathHelper.func_76142_g(f);
                  }

                  float f1 = (float)commandbase$coordinatearg4.func_179629_b();
                  if(!commandbase$coordinatearg4.func_179630_c()) {
                     f1 = MathHelper.func_76142_g(f1);
                  }

                  if(f1 > 90.0F || f1 < -90.0F) {
                     f1 = MathHelper.func_76142_g(180.0F - f1);
                     f = MathHelper.func_76142_g(f + 180.0F);
                  }

                  entity.func_70078_a((Entity)null);
                  ((EntityPlayerMP)entity).field_71135_a.func_175089_a(commandbase$coordinatearg.func_179629_b(), commandbase$coordinatearg1.func_179629_b(), commandbase$coordinatearg2.func_179629_b(), f, f1, set);
                  entity.func_70034_d(f);
               } else {
                  float f2 = (float)MathHelper.func_76138_g(commandbase$coordinatearg3.func_179628_a());
                  float f3 = (float)MathHelper.func_76138_g(commandbase$coordinatearg4.func_179628_a());
                  if(f3 > 90.0F || f3 < -90.0F) {
                     f3 = MathHelper.func_76142_g(180.0F - f3);
                     f2 = MathHelper.func_76142_g(f2 + 180.0F);
                  }

                  entity.func_70012_b(commandbase$coordinatearg.func_179628_a(), commandbase$coordinatearg1.func_179628_a(), commandbase$coordinatearg2.func_179628_a(), f2, f3);
                  entity.func_70034_d(f2);
               }

               func_152373_a(p_71515_1_, this, "commands.tp.success.coordinates", new Object[]{entity.func_70005_c_(), Double.valueOf(commandbase$coordinatearg.func_179628_a()), Double.valueOf(commandbase$coordinatearg1.func_179628_a()), Double.valueOf(commandbase$coordinatearg2.func_179628_a())});
            }
         } else {
            Entity entity1 = func_175768_b(p_71515_1_, p_71515_2_[p_71515_2_.length - 1]);
            if(entity1.field_70170_p != entity.field_70170_p) {
               throw new CommandException("commands.tp.notSameDimension", new Object[0]);
            } else {
               entity.func_70078_a((Entity)null);
               if(entity instanceof EntityPlayerMP) {
                  ((EntityPlayerMP)entity).field_71135_a.func_147364_a(entity1.field_70165_t, entity1.field_70163_u, entity1.field_70161_v, entity1.field_70177_z, entity1.field_70125_A);
               } else {
                  entity.func_70012_b(entity1.field_70165_t, entity1.field_70163_u, entity1.field_70161_v, entity1.field_70177_z, entity1.field_70125_A);
               }

               func_152373_a(p_71515_1_, this, "commands.tp.success", new Object[]{entity.func_70005_c_(), entity1.func_70005_c_()});
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length != 1 && p_180525_2_.length != 2?null:func_71530_a(p_180525_2_, MinecraftServer.func_71276_C().func_71213_z());
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 0;
   }
}
