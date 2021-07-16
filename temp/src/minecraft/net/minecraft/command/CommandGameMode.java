package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;

public class CommandGameMode extends CommandBase {
   public String func_71517_b() {
      return "gamemode";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.gamemode.usage";
   }

   public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) throws CommandException {
      if(p_71515_2_.length <= 0) {
         throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
      } else {
         WorldSettings.GameType worldsettings$gametype = this.func_71539_b(p_71515_1_, p_71515_2_[0]);
         EntityPlayer entityplayer = p_71515_2_.length >= 2?func_82359_c(p_71515_1_, p_71515_2_[1]):func_71521_c(p_71515_1_);
         entityplayer.func_71033_a(worldsettings$gametype);
         entityplayer.field_70143_R = 0.0F;
         if(p_71515_1_.func_130014_f_().func_82736_K().func_82766_b("sendCommandFeedback")) {
            entityplayer.func_145747_a(new ChatComponentTranslation("gameMode.changed", new Object[0]));
         }

         IChatComponent ichatcomponent = new ChatComponentTranslation("gameMode." + worldsettings$gametype.func_77149_b(), new Object[0]);
         if(entityplayer != p_71515_1_) {
            func_152374_a(p_71515_1_, this, 1, "commands.gamemode.success.other", new Object[]{entityplayer.func_70005_c_(), ichatcomponent});
         } else {
            func_152374_a(p_71515_1_, this, 1, "commands.gamemode.success.self", new Object[]{ichatcomponent});
         }

      }
   }

   protected WorldSettings.GameType func_71539_b(ICommandSender p_71539_1_, String p_71539_2_) throws CommandException, NumberInvalidException {
      return !p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.SURVIVAL.func_77149_b()) && !p_71539_2_.equalsIgnoreCase("s")?(!p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.CREATIVE.func_77149_b()) && !p_71539_2_.equalsIgnoreCase("c")?(!p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.ADVENTURE.func_77149_b()) && !p_71539_2_.equalsIgnoreCase("a")?(!p_71539_2_.equalsIgnoreCase(WorldSettings.GameType.SPECTATOR.func_77149_b()) && !p_71539_2_.equalsIgnoreCase("sp")?WorldSettings.func_77161_a(func_175764_a(p_71539_2_, 0, WorldSettings.GameType.values().length - 2)):WorldSettings.GameType.SPECTATOR):WorldSettings.GameType.ADVENTURE):WorldSettings.GameType.CREATIVE):WorldSettings.GameType.SURVIVAL;
   }

   public List<String> func_180525_a(ICommandSender p_180525_1_, String[] p_180525_2_, BlockPos p_180525_3_) {
      return p_180525_2_.length == 1?func_71530_a(p_180525_2_, new String[]{"survival", "creative", "adventure", "spectator"}):(p_180525_2_.length == 2?func_71530_a(p_180525_2_, this.func_71538_c()):null);
   }

   protected String[] func_71538_c() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 1;
   }
}
