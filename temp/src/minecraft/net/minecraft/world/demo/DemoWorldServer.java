package net.minecraft.world.demo;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class DemoWorldServer extends WorldServer {
   private static final long field_73072_L = (long)"North Carolina".hashCode();
   public static final WorldSettings field_73071_a = (new WorldSettings(field_73072_L, WorldSettings.GameType.SURVIVAL, true, false, WorldType.field_77137_b)).func_77159_a();

   public DemoWorldServer(MinecraftServer p_i45924_1_, ISaveHandler p_i45924_2_, WorldInfo p_i45924_3_, int p_i45924_4_, Profiler p_i45924_5_) {
      super(p_i45924_1_, p_i45924_2_, p_i45924_3_, p_i45924_4_, p_i45924_5_);
      this.field_72986_A.func_176127_a(field_73071_a);
   }
}
