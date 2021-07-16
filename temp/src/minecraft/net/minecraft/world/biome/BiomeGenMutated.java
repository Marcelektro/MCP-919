package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMutated extends BiomeGenBase {
   protected BiomeGenBase field_150611_aD;

   public BiomeGenMutated(int p_i45381_1_, BiomeGenBase p_i45381_2_) {
      super(p_i45381_1_);
      this.field_150611_aD = p_i45381_2_;
      this.func_150557_a(p_i45381_2_.field_76790_z, true);
      this.field_76791_y = p_i45381_2_.field_76791_y + " M";
      this.field_76752_A = p_i45381_2_.field_76752_A;
      this.field_76753_B = p_i45381_2_.field_76753_B;
      this.field_76754_C = p_i45381_2_.field_76754_C;
      this.field_76748_D = p_i45381_2_.field_76748_D;
      this.field_76749_E = p_i45381_2_.field_76749_E;
      this.field_76750_F = p_i45381_2_.field_76750_F;
      this.field_76751_G = p_i45381_2_.field_76751_G;
      this.field_76759_H = p_i45381_2_.field_76759_H;
      this.field_76766_R = p_i45381_2_.field_76766_R;
      this.field_76765_S = p_i45381_2_.field_76765_S;
      this.field_76762_K = Lists.newArrayList(p_i45381_2_.field_76762_K);
      this.field_76761_J = Lists.newArrayList(p_i45381_2_.field_76761_J);
      this.field_82914_M = Lists.newArrayList(p_i45381_2_.field_82914_M);
      this.field_76755_L = Lists.newArrayList(p_i45381_2_.field_76755_L);
      this.field_76750_F = p_i45381_2_.field_76750_F;
      this.field_76751_G = p_i45381_2_.field_76751_G;
      this.field_76748_D = p_i45381_2_.field_76748_D + 0.1F;
      this.field_76749_E = p_i45381_2_.field_76749_E + 0.2F;
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      this.field_150611_aD.field_76760_I.func_180292_a(p_180624_1_, p_180624_2_, this, p_180624_3_);
   }

   public void func_180622_a(World p_180622_1_, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
      this.field_150611_aD.func_180622_a(p_180622_1_, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
   }

   public float func_76741_f() {
      return this.field_150611_aD.func_76741_f();
   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      return this.field_150611_aD.func_150567_a(p_150567_1_);
   }

   public int func_180625_c(BlockPos p_180625_1_) {
      return this.field_150611_aD.func_180625_c(p_180625_1_);
   }

   public int func_180627_b(BlockPos p_180627_1_) {
      return this.field_150611_aD.func_180627_b(p_180627_1_);
   }

   public Class<? extends BiomeGenBase> func_150562_l() {
      return this.field_150611_aD.func_150562_l();
   }

   public boolean func_150569_a(BiomeGenBase p_150569_1_) {
      return this.field_150611_aD.func_150569_a(p_150569_1_);
   }

   public BiomeGenBase.TempCategory func_150561_m() {
      return this.field_150611_aD.func_150561_m();
   }
}
