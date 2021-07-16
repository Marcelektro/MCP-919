package net.minecraft.item;

import net.minecraft.util.EnumChatFormatting;

public enum EnumRarity {
   COMMON(EnumChatFormatting.WHITE, "Common"),
   UNCOMMON(EnumChatFormatting.YELLOW, "Uncommon"),
   RARE(EnumChatFormatting.AQUA, "Rare"),
   EPIC(EnumChatFormatting.LIGHT_PURPLE, "Epic");

   public final EnumChatFormatting field_77937_e;
   public final String field_77934_f;

   private EnumRarity(EnumChatFormatting p_i45349_3_, String p_i45349_4_) {
      this.field_77937_e = p_i45349_3_;
      this.field_77934_f = p_i45349_4_;
   }
}
