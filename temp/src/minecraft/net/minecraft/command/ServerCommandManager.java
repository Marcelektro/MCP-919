package net.minecraft.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandBlockData;
import net.minecraft.command.CommandClearInventory;
import net.minecraft.command.CommandClone;
import net.minecraft.command.CommandCompare;
import net.minecraft.command.CommandDebug;
import net.minecraft.command.CommandDefaultGameMode;
import net.minecraft.command.CommandDifficulty;
import net.minecraft.command.CommandEffect;
import net.minecraft.command.CommandEnchant;
import net.minecraft.command.CommandEntityData;
import net.minecraft.command.CommandExecuteAt;
import net.minecraft.command.CommandFill;
import net.minecraft.command.CommandGameMode;
import net.minecraft.command.CommandGameRule;
import net.minecraft.command.CommandGive;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.CommandHelp;
import net.minecraft.command.CommandKill;
import net.minecraft.command.CommandParticle;
import net.minecraft.command.CommandPlaySound;
import net.minecraft.command.CommandReplaceItem;
import net.minecraft.command.CommandServerKick;
import net.minecraft.command.CommandSetPlayerTimeout;
import net.minecraft.command.CommandSetSpawnpoint;
import net.minecraft.command.CommandShowSeed;
import net.minecraft.command.CommandSpreadPlayers;
import net.minecraft.command.CommandStats;
import net.minecraft.command.CommandTime;
import net.minecraft.command.CommandTitle;
import net.minecraft.command.CommandToggleDownfall;
import net.minecraft.command.CommandTrigger;
import net.minecraft.command.CommandWeather;
import net.minecraft.command.CommandWorldBorder;
import net.minecraft.command.CommandXP;
import net.minecraft.command.IAdminCommand;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandAchievement;
import net.minecraft.command.server.CommandBanIp;
import net.minecraft.command.server.CommandBanPlayer;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.command.server.CommandBroadcast;
import net.minecraft.command.server.CommandDeOp;
import net.minecraft.command.server.CommandEmote;
import net.minecraft.command.server.CommandListBans;
import net.minecraft.command.server.CommandListPlayers;
import net.minecraft.command.server.CommandMessage;
import net.minecraft.command.server.CommandMessageRaw;
import net.minecraft.command.server.CommandOp;
import net.minecraft.command.server.CommandPardonIp;
import net.minecraft.command.server.CommandPardonPlayer;
import net.minecraft.command.server.CommandPublishLocalServer;
import net.minecraft.command.server.CommandSaveAll;
import net.minecraft.command.server.CommandSaveOff;
import net.minecraft.command.server.CommandSaveOn;
import net.minecraft.command.server.CommandScoreboard;
import net.minecraft.command.server.CommandSetBlock;
import net.minecraft.command.server.CommandSetDefaultSpawnpoint;
import net.minecraft.command.server.CommandStop;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.command.server.CommandTestFor;
import net.minecraft.command.server.CommandTestForBlock;
import net.minecraft.command.server.CommandWhitelist;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ServerCommandManager extends CommandHandler implements IAdminCommand {
   public ServerCommandManager() {
      this.func_71560_a(new CommandTime());
      this.func_71560_a(new CommandGameMode());
      this.func_71560_a(new CommandDifficulty());
      this.func_71560_a(new CommandDefaultGameMode());
      this.func_71560_a(new CommandKill());
      this.func_71560_a(new CommandToggleDownfall());
      this.func_71560_a(new CommandWeather());
      this.func_71560_a(new CommandXP());
      this.func_71560_a(new CommandTeleport());
      this.func_71560_a(new CommandGive());
      this.func_71560_a(new CommandReplaceItem());
      this.func_71560_a(new CommandStats());
      this.func_71560_a(new CommandEffect());
      this.func_71560_a(new CommandEnchant());
      this.func_71560_a(new CommandParticle());
      this.func_71560_a(new CommandEmote());
      this.func_71560_a(new CommandShowSeed());
      this.func_71560_a(new CommandHelp());
      this.func_71560_a(new CommandDebug());
      this.func_71560_a(new CommandMessage());
      this.func_71560_a(new CommandBroadcast());
      this.func_71560_a(new CommandSetSpawnpoint());
      this.func_71560_a(new CommandSetDefaultSpawnpoint());
      this.func_71560_a(new CommandGameRule());
      this.func_71560_a(new CommandClearInventory());
      this.func_71560_a(new CommandTestFor());
      this.func_71560_a(new CommandSpreadPlayers());
      this.func_71560_a(new CommandPlaySound());
      this.func_71560_a(new CommandScoreboard());
      this.func_71560_a(new CommandExecuteAt());
      this.func_71560_a(new CommandTrigger());
      this.func_71560_a(new CommandAchievement());
      this.func_71560_a(new CommandSummon());
      this.func_71560_a(new CommandSetBlock());
      this.func_71560_a(new CommandFill());
      this.func_71560_a(new CommandClone());
      this.func_71560_a(new CommandCompare());
      this.func_71560_a(new CommandBlockData());
      this.func_71560_a(new CommandTestForBlock());
      this.func_71560_a(new CommandMessageRaw());
      this.func_71560_a(new CommandWorldBorder());
      this.func_71560_a(new CommandTitle());
      this.func_71560_a(new CommandEntityData());
      if(MinecraftServer.func_71276_C().func_71262_S()) {
         this.func_71560_a(new CommandOp());
         this.func_71560_a(new CommandDeOp());
         this.func_71560_a(new CommandStop());
         this.func_71560_a(new CommandSaveAll());
         this.func_71560_a(new CommandSaveOff());
         this.func_71560_a(new CommandSaveOn());
         this.func_71560_a(new CommandBanIp());
         this.func_71560_a(new CommandPardonIp());
         this.func_71560_a(new CommandBanPlayer());
         this.func_71560_a(new CommandListBans());
         this.func_71560_a(new CommandPardonPlayer());
         this.func_71560_a(new CommandServerKick());
         this.func_71560_a(new CommandListPlayers());
         this.func_71560_a(new CommandWhitelist());
         this.func_71560_a(new CommandSetPlayerTimeout());
      } else {
         this.func_71560_a(new CommandPublishLocalServer());
      }

      CommandBase.func_71529_a(this);
   }

   public void func_152372_a(ICommandSender p_152372_1_, ICommand p_152372_2_, int p_152372_3_, String p_152372_4_, Object... p_152372_5_) {
      boolean flag = true;
      MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
      if(!p_152372_1_.func_174792_t_()) {
         flag = false;
      }

      IChatComponent ichatcomponent = new ChatComponentTranslation("chat.type.admin", new Object[]{p_152372_1_.func_70005_c_(), new ChatComponentTranslation(p_152372_4_, p_152372_5_)});
      ichatcomponent.func_150256_b().func_150238_a(EnumChatFormatting.GRAY);
      ichatcomponent.func_150256_b().func_150217_b(Boolean.valueOf(true));
      if(flag) {
         for(EntityPlayer entityplayer : minecraftserver.func_71203_ab().func_181057_v()) {
            if(entityplayer != p_152372_1_ && minecraftserver.func_71203_ab().func_152596_g(entityplayer.func_146103_bH()) && p_152372_2_.func_71519_b(p_152372_1_)) {
               boolean flag1 = p_152372_1_ instanceof MinecraftServer && MinecraftServer.func_71276_C().func_183002_r();
               boolean flag2 = p_152372_1_ instanceof RConConsoleSource && MinecraftServer.func_71276_C().func_181034_q();
               if(flag1 || flag2 || !(p_152372_1_ instanceof RConConsoleSource) && !(p_152372_1_ instanceof MinecraftServer)) {
                  entityplayer.func_145747_a(ichatcomponent);
               }
            }
         }
      }

      if(p_152372_1_ != minecraftserver && minecraftserver.field_71305_c[0].func_82736_K().func_82766_b("logAdminCommands")) {
         minecraftserver.func_145747_a(ichatcomponent);
      }

      boolean flag3 = minecraftserver.field_71305_c[0].func_82736_K().func_82766_b("sendCommandFeedback");
      if(p_152372_1_ instanceof CommandBlockLogic) {
         flag3 = ((CommandBlockLogic)p_152372_1_).func_175571_m();
      }

      if((p_152372_3_ & 1) != 1 && flag3 || p_152372_1_ instanceof MinecraftServer) {
         p_152372_1_.func_145747_a(new ChatComponentTranslation(p_152372_4_, p_152372_5_));
      }

   }
}
