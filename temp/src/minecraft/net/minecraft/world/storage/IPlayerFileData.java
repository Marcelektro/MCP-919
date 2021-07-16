package net.minecraft.world.storage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IPlayerFileData {
   void func_75753_a(EntityPlayer var1);

   NBTTagCompound func_75752_b(EntityPlayer var1);

   String[] func_75754_f();
}
