package net.minecraft.command;

import net.minecraft.command.SyntaxErrorException;

public class WrongUsageException extends SyntaxErrorException {
   public WrongUsageException(String p_i1364_1_, Object... p_i1364_2_) {
      super(p_i1364_1_, p_i1364_2_);
   }
}
