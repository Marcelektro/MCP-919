package net.minecraft.util;

import net.minecraft.util.ChatComponentTranslation;

public class ChatComponentTranslationFormatException extends IllegalArgumentException {
   public ChatComponentTranslationFormatException(ChatComponentTranslation p_i45161_1_, String p_i45161_2_) {
      super(String.format("Error parsing: %s: %s", new Object[]{p_i45161_1_, p_i45161_2_}));
   }

   public ChatComponentTranslationFormatException(ChatComponentTranslation p_i45162_1_, int p_i45162_2_) {
      super(String.format("Invalid index %d requested for %s", new Object[]{Integer.valueOf(p_i45162_2_), p_i45162_1_}));
   }

   public ChatComponentTranslationFormatException(ChatComponentTranslation p_i45163_1_, Throwable p_i45163_2_) {
      super(String.format("Error while parsing: %s", new Object[]{p_i45163_1_}), p_i45163_2_);
   }
}
