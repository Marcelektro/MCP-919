package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S45PacketTitle implements Packet<INetHandlerPlayClient> {
   private S45PacketTitle.Type field_179812_a;
   private IChatComponent field_179810_b;
   private int field_179811_c;
   private int field_179808_d;
   private int field_179809_e;

   public S45PacketTitle() {
   }

   public S45PacketTitle(S45PacketTitle.Type p_i45953_1_, IChatComponent p_i45953_2_) {
      this(p_i45953_1_, p_i45953_2_, -1, -1, -1);
   }

   public S45PacketTitle(int p_i45954_1_, int p_i45954_2_, int p_i45954_3_) {
      this(S45PacketTitle.Type.TIMES, (IChatComponent)null, p_i45954_1_, p_i45954_2_, p_i45954_3_);
   }

   public S45PacketTitle(S45PacketTitle.Type p_i45955_1_, IChatComponent p_i45955_2_, int p_i45955_3_, int p_i45955_4_, int p_i45955_5_) {
      this.field_179812_a = p_i45955_1_;
      this.field_179810_b = p_i45955_2_;
      this.field_179811_c = p_i45955_3_;
      this.field_179808_d = p_i45955_4_;
      this.field_179809_e = p_i45955_5_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179812_a = (S45PacketTitle.Type)p_148837_1_.func_179257_a(S45PacketTitle.Type.class);
      if(this.field_179812_a == S45PacketTitle.Type.TITLE || this.field_179812_a == S45PacketTitle.Type.SUBTITLE) {
         this.field_179810_b = p_148837_1_.func_179258_d();
      }

      if(this.field_179812_a == S45PacketTitle.Type.TIMES) {
         this.field_179811_c = p_148837_1_.readInt();
         this.field_179808_d = p_148837_1_.readInt();
         this.field_179809_e = p_148837_1_.readInt();
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_179249_a(this.field_179812_a);
      if(this.field_179812_a == S45PacketTitle.Type.TITLE || this.field_179812_a == S45PacketTitle.Type.SUBTITLE) {
         p_148840_1_.func_179256_a(this.field_179810_b);
      }

      if(this.field_179812_a == S45PacketTitle.Type.TIMES) {
         p_148840_1_.writeInt(this.field_179811_c);
         p_148840_1_.writeInt(this.field_179808_d);
         p_148840_1_.writeInt(this.field_179809_e);
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_175099_a(this);
   }

   public S45PacketTitle.Type func_179807_a() {
      return this.field_179812_a;
   }

   public IChatComponent func_179805_b() {
      return this.field_179810_b;
   }

   public int func_179806_c() {
      return this.field_179811_c;
   }

   public int func_179804_d() {
      return this.field_179808_d;
   }

   public int func_179803_e() {
      return this.field_179809_e;
   }

   public static enum Type {
      TITLE,
      SUBTITLE,
      TIMES,
      CLEAR,
      RESET;

      public static S45PacketTitle.Type func_179969_a(String p_179969_0_) {
         for(S45PacketTitle.Type s45packettitle$type : values()) {
            if(s45packettitle$type.name().equalsIgnoreCase(p_179969_0_)) {
               return s45packettitle$type;
            }
         }

         return TITLE;
      }

      public static String[] func_179971_a() {
         String[] astring = new String[values().length];
         int i = 0;

         for(S45PacketTitle.Type s45packettitle$type : values()) {
            astring[i++] = s45packettitle$type.name().toLowerCase();
         }

         return astring;
      }
   }
}
