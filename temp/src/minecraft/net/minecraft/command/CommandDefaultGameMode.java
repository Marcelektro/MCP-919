package net.minecraft.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandGameMode;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandDefaultGameMode extends CommandGameMode {
   public String func_71517_b() {
      return "defaultgamemode";
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.defaultgamemode.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length <= 0) {
         throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
      } else {
         WorldSettings.GameType worldsettings$gametype = this.func_71539_b(p_71515_1_, p_71515_2_[0]);
         this.func_71541_a(worldsettings$gametype);
         func_152373_a(p_71515_1_, this, "commands.defaultgamemode.success", new Object[]{new ChatComponentTranslation("gameMode." + worldsettings$gametype.func_77149_b(), new Object[0])});
      }
   }

   protected void func_71541_a(WorldSettings.GameType p_71541_1_) {
      MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
      minecraftserver.func_71235_a(p_71541_1_);
      if(minecraftserver.func_104056_am()) {
         for(EntityPlayerMP entityplayermp : MinecraftServer.func_71276_C().func_71203_ab().func_181057_v()) {
            entityplayermp.func_71033_a(p_71541_1_);
            entityplayermp.field_70143_R = 0.0F;
         }
      }

   }
}
