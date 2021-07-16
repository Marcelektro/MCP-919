package net.minecraft.command;

import net.minecraft.command.CommandException;

public class PlayerNotFoundException extends CommandException {
   public PlayerNotFoundException() {
      this("commands.generic.player.notFound", new Object[0]);
   }

   public PlayerNotFoundException(String p_i1362_1_, Object... p_i1362_2_) {
      super(p_i1362_1_, p_i1362_2_);
   }
}
