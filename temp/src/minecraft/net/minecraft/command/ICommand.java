package net.minecraft.command;

import java.util.List;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public interface ICommand extends Comparable<ICommand> {
   String func_71517_b();

   String func_71518_a(ICommandSender var1);

   List<String> func_71514_a();

   void func_71515_b(ICommandSender var1, String[] var2) throws CommandException;

   boolean func_71519_b(ICommandSender var1);

   List<String> func_180525_a(ICommandSender var1, String[] var2, BlockPos var3);

   boolean func_82358_a(String[] var1, int var2);
}
