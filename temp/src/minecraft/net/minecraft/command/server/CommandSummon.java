package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandSummon extends CommandBase {
   public String func_71517_b() {
      return "summon";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.summon.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length < 1) {
         throw new WrongUsageException("commands.summon.usage", new Object[0]);
      } else {
         String s = p_71515_2_[0];
         BlockPos blockpos = p_71515_1_.func_180425_c();
         Vec3 vec3 = p_71515_1_.func_174791_d();
         double d0 = vec3.field_72450_a;
         double d1 = vec3.field_72448_b;
         double d2 = vec3.field_72449_c;
         if(p_71515_2_.length >= 4) {
            d0 = func_175761_b(d0, p_71515_2_[1], true);
            d1 = func_175761_b(d1, p_71515_2_[2], false);
            d2 = func_175761_b(d2, p_71515_2_[3], true);
            blockpos = new BlockPos(d0, d1, d2);
         }

         World world = p_71515_1_.func_130014_f_();
         if(!world.func_175667_e(blockpos)) {
            throw new CommandException("commands.summon.outOfWorld", new Object[0]);
         } else if("LightningBolt".equals(s)) {
            world.func_72942_c(new EntityLightningBolt(world, d0, d1, d2));
            func_152373_a(p_71515_1_, this, "commands.summon.success", new Object[0]);
         } else {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            boolean flag = false;
            if(p_71515_2_.length >= 5) {
               IChatComponent ichatcomponent = func_147178_a(p_71515_1_, p_71515_2_, 4);

               try {
                  nbttagcompound = JsonToNBT.func_180713_a(ichatcomponent.func_150260_c());
                  flag = true;
               } catch (NBTException nbtexception) {
                  throw new CommandException("commands.summon.tagError", new Object[]{nbtexception.getMessage()});
               }
            }

            nbttagcompound.func_74778_a("id", s);

            Entity entity2;
            try {
               entity2 = EntityList.func_75615_a(nbttagcompound, world);
            } catch (RuntimeException var19) {
               throw new CommandException("commands.summon.failed", new Object[0]);
            }

            if(entity2 == null) {
               throw new CommandException("commands.summon.failed", new Object[0]);
            } else {
               entity2.func_70012_b(d0, d1, d2, entity2.field_70177_z, entity2.field_70125_A);
               if(!flag && entity2 instanceof EntityLiving) {
                  ((EntityLiving)entity2).func_180482_a(world.func_175649_E(new BlockPos(entity2)), (IEntityLivingData)null);
               }

               world.func_72838_d(entity2);
               Entity entity = entity2;

               for(NBTTagCompound nbttagcompound1 = nbttagcompound; entity != null && nbttagcompound1.func_150297_b("Riding", 10); nbttagcompound1 = nbttagcompound1.func_74775_l("Riding")) {
                  Entity entity1 = EntityList.func_75615_a(nbttagcompound1.func_74775_l("Riding"), world);
                  if(entity1 != null) {
                     entity1.func_70012_b(d0, d1, d2, entity1.field_70177_z, entity1.field_70125_A);
                     world.func_72838_d(entity1);
                     entity.func_70078_a(entity1);
                  }

                  entity = entity1;
               }

               func_152373_a(p_71515_1_, this, "commands.summon.success", new Object[0]);
            }
         }
      }
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_175762_a(p_180525_2_, EntityList.func_180124_b()):(p_180525_2_.length > 1 && p_180525_2_.length <= 4?func_175771_a(p_180525_2_, 1, p_180525_3_):null);
   }
}
