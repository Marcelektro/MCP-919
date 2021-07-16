package net.minecraft.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.WorldServer;

public class WorldManager implements IWorldAccess {
   private MinecraftServer field_72783_a;
   private WorldServer field_72782_b;

   public WorldManager(MinecraftServer p_i1517_1_, WorldServer p_i1517_2_) {
      this.field_72783_a = p_i1517_1_;
      this.field_72782_b = p_i1517_2_;
   }

   public void func_180442_a(int p_180442_1_, boolean p_180442_2_, double p_180442_3_, double p_180442_5_, double p_180442_7_, double p_180442_9_, double p_180442_11_, double p_180442_13_, int... p_180442_15_) {
   }

   public void func_72703_a(Entity p_72703_1_) {
      this.field_72782_b.func_73039_n().func_72786_a(p_72703_1_);
   }

   public void func_72709_b(Entity p_72709_1_) {
      this.field_72782_b.func_73039_n().func_72790_b(p_72709_1_);
      this.field_72782_b.func_96441_U().func_181140_a(p_72709_1_);
   }

   public void func_72704_a(String p_72704_1_, double p_72704_2_, double p_72704_4_, double p_72704_6_, float p_72704_8_, float p_72704_9_) {
      this.field_72783_a.func_71203_ab().func_148541_a(p_72704_2_, p_72704_4_, p_72704_6_, p_72704_8_ > 1.0F?(double)(16.0F * p_72704_8_):16.0D, this.field_72782_b.field_73011_w.func_177502_q(), new S29PacketSoundEffect(p_72704_1_, p_72704_2_, p_72704_4_, p_72704_6_, p_72704_8_, p_72704_9_));
   }

   public void func_85102_a(EntityPlayer p_85102_1_, String p_85102_2_, double p_85102_3_, double p_85102_5_, double p_85102_7_, float p_85102_9_, float p_85102_10_) {
      this.field_72783_a.func_71203_ab().func_148543_a(p_85102_1_, p_85102_3_, p_85102_5_, p_85102_7_, p_85102_9_ > 1.0F?(double)(16.0F * p_85102_9_):16.0D, this.field_72782_b.field_73011_w.func_177502_q(), new S29PacketSoundEffect(p_85102_2_, p_85102_3_, p_85102_5_, p_85102_7_, p_85102_9_, p_85102_10_));
   }

   public void func_147585_a(int p_147585_1_, int p_147585_2_, int p_147585_3_, int p_147585_4_, int p_147585_5_, int p_147585_6_) {
   }

   public void func_174960_a(BlockPos p_174960_1_) {
      this.field_72782_b.func_73040_p().func_180244_a(p_174960_1_);
   }

   public void func_174959_b(BlockPos p_174959_1_) {
   }

   public void func_174961_a(String p_174961_1_, BlockPos p_174961_2_) {
   }

   public void func_180439_a(EntityPlayer p_180439_1_, int p_180439_2_, BlockPos p_180439_3_, int p_180439_4_) {
      this.field_72783_a.func_71203_ab().func_148543_a(p_180439_1_, (double)p_180439_3_.func_177958_n(), (double)p_180439_3_.func_177956_o(), (double)p_180439_3_.func_177952_p(), 64.0D, this.field_72782_b.field_73011_w.func_177502_q(), new S28PacketEffect(p_180439_2_, p_180439_3_, p_180439_4_, false));
   }

   public void func_180440_a(int p_180440_1_, BlockPos p_180440_2_, int p_180440_3_) {
      this.field_72783_a.func_71203_ab().func_148540_a(new S28PacketEffect(p_180440_1_, p_180440_2_, p_180440_3_, true));
   }

   public void func_180441_b(int p_180441_1_, BlockPos p_180441_2_, int p_180441_3_) {
      for(EntityPlayerMP entityplayermp : this.field_72783_a.func_71203_ab().func_181057_v()) {
         if(entityplayermp != null && entityplayermp.field_70170_p == this.field_72782_b && entityplayermp.func_145782_y() != p_180441_1_) {
            double d0 = (double)p_180441_2_.func_177958_n() - entityplayermp.field_70165_t;
            double d1 = (double)p_180441_2_.func_177956_o() - entityplayermp.field_70163_u;
            double d2 = (double)p_180441_2_.func_177952_p() - entityplayermp.field_70161_v;
            if(d0 * d0 + d1 * d1 + d2 * d2 < 1024.0D) {
               entityplayermp.field_71135_a.func_147359_a(new S25PacketBlockBreakAnim(p_180441_1_, p_180441_2_, p_180441_3_));
            }
         }
      }

   }
}
