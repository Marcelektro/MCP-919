package net.minecraft.client.stream;

import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.client.stream.Metadata;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.chat.ChatUserInfo;

public interface IStream {
   void func_152923_i();

   void func_152935_j();

   void func_152922_k();

   boolean func_152936_l();

   boolean func_152924_m();

   boolean func_152934_n();

   void func_152911_a(Metadata var1, long var2);

   void func_176026_a(Metadata var1, long var2, long var4);

   boolean func_152919_o();

   void func_152931_p();

   void func_152916_q();

   void func_152933_r();

   void func_152915_s();

   void func_152930_t();

   void func_152914_u();

   IngestServer[] func_152925_v();

   void func_152909_x();

   IngestServerTester func_152932_y();

   boolean func_152908_z();

   int func_152920_A();

   boolean func_152927_B();

   String func_152921_C();

   ChatUserInfo func_152926_a(String var1);

   void func_152917_b(String var1);

   boolean func_152928_D();

   ErrorCode func_152912_E();

   boolean func_152913_F();

   void func_152910_a(boolean var1);

   boolean func_152929_G();

   IStream.AuthFailureReason func_152918_H();

   public static enum AuthFailureReason {
      ERROR,
      INVALID_TOKEN;
   }
}
