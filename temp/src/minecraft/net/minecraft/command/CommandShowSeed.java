package net.minecraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class CommandShowSeed extends CommandBase {
   public boolean func_71519_b(ICommandSender p_71519_1_) {
      return MinecraftServer.func_71276_C().func_71264_H() || super.func_71519_b(p_71519_1_);
   }

   public String func_71517_b() {
      return "seed";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.seed.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      World world = (World)(p_71515_1_ instanceof EntityPlayer?((EntityPlayer)p_71515_1_).field_70170_p:MinecraftServer.func_71276_C().func_71218_a(0));
      p_71515_1_.func_145747_a(new ChatComponentTranslation("commands.seed.success", new Object[]{Long.valueOf(world.func_72905_C())}));
   }
}
