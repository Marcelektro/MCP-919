package net.minecraft.stats;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.JsonSerializableSet;

public class AchievementList {
   public static int field_76010_a;
   public static int field_76008_b;
   public static int field_76009_c;
   public static int field_76006_d;
   public static List<Achievement> field_76007_e = Lists.<Achievement>newArrayList();
   public static Achievement field_76004_f = (new Achievement("achievement.openInventory", "openInventory", 0, 0, Items.field_151122_aG, (Achievement)null)).func_75966_h().func_75971_g();
   public static Achievement field_76005_g = (new Achievement("achievement.mineWood", "mineWood", 2, 1, Blocks.field_150364_r, field_76004_f)).func_75971_g();
   public static Achievement field_76017_h = (new Achievement("achievement.buildWorkBench", "buildWorkBench", 4, -1, Blocks.field_150462_ai, field_76005_g)).func_75971_g();
   public static Achievement field_76018_i = (new Achievement("achievement.buildPickaxe", "buildPickaxe", 4, 2, Items.field_151039_o, field_76017_h)).func_75971_g();
   public static Achievement field_76015_j = (new Achievement("achievement.buildFurnace", "buildFurnace", 3, 4, Blocks.field_150460_al, field_76018_i)).func_75971_g();
   public static Achievement field_76016_k = (new Achievement("achievement.acquireIron", "acquireIron", 1, 4, Items.field_151042_j, field_76015_j)).func_75971_g();
   public static Achievement field_76013_l = (new Achievement("achievement.buildHoe", "buildHoe", 2, -3, Items.field_151017_I, field_76017_h)).func_75971_g();
   public static Achievement field_76014_m = (new Achievement("achievement.makeBread", "makeBread", -1, -3, Items.field_151025_P, field_76013_l)).func_75971_g();
   public static Achievement field_76011_n = (new Achievement("achievement.bakeCake", "bakeCake", 0, -5, Items.field_151105_aU, field_76013_l)).func_75971_g();
   public static Achievement field_76012_o = (new Achievement("achievement.buildBetterPickaxe", "buildBetterPickaxe", 6, 2, Items.field_151050_s, field_76018_i)).func_75971_g();
   public static Achievement field_76026_p = (new Achievement("achievement.cookFish", "cookFish", 2, 6, Items.field_179566_aV, field_76015_j)).func_75971_g();
   public static Achievement field_76025_q = (new Achievement("achievement.onARail", "onARail", 2, 3, Blocks.field_150448_aq, field_76016_k)).func_75987_b().func_75971_g();
   public static Achievement field_76024_r = (new Achievement("achievement.buildSword", "buildSword", 6, -1, Items.field_151041_m, field_76017_h)).func_75971_g();
   public static Achievement field_76023_s = (new Achievement("achievement.killEnemy", "killEnemy", 8, -1, Items.field_151103_aS, field_76024_r)).func_75971_g();
   public static Achievement field_76022_t = (new Achievement("achievement.killCow", "killCow", 7, -3, Items.field_151116_aA, field_76024_r)).func_75971_g();
   public static Achievement field_76021_u = (new Achievement("achievement.flyPig", "flyPig", 9, -3, Items.field_151141_av, field_76022_t)).func_75987_b().func_75971_g();
   public static Achievement field_76020_v = (new Achievement("achievement.snipeSkeleton", "snipeSkeleton", 7, 0, Items.field_151031_f, field_76023_s)).func_75987_b().func_75971_g();
   public static Achievement field_76019_w = (new Achievement("achievement.diamonds", "diamonds", -1, 5, Blocks.field_150482_ag, field_76016_k)).func_75971_g();
   public static Achievement field_150966_x = (new Achievement("achievement.diamondsToYou", "diamondsToYou", -1, 2, Items.field_151045_i, field_76019_w)).func_75971_g();
   public static Achievement field_76029_x = (new Achievement("achievement.portal", "portal", -1, 7, Blocks.field_150343_Z, field_76019_w)).func_75971_g();
   public static Achievement field_76028_y = (new Achievement("achievement.ghast", "ghast", -4, 8, Items.field_151073_bk, field_76029_x)).func_75987_b().func_75971_g();
   public static Achievement field_76027_z = (new Achievement("achievement.blazeRod", "blazeRod", 0, 9, Items.field_151072_bj, field_76029_x)).func_75971_g();
   public static Achievement field_76001_A = (new Achievement("achievement.potion", "potion", 2, 8, Items.field_151068_bn, field_76027_z)).func_75971_g();
   public static Achievement field_76002_B = (new Achievement("achievement.theEnd", "theEnd", 3, 10, Items.field_151061_bv, field_76027_z)).func_75987_b().func_75971_g();
   public static Achievement field_76003_C = (new Achievement("achievement.theEnd2", "theEnd2", 4, 13, Blocks.field_150380_bt, field_76002_B)).func_75987_b().func_75971_g();
   public static Achievement field_75998_D = (new Achievement("achievement.enchantments", "enchantments", -4, 4, Blocks.field_150381_bn, field_76019_w)).func_75971_g();
   public static Achievement field_75999_E = (new Achievement("achievement.overkill", "overkill", -4, 1, Items.field_151048_u, field_75998_D)).func_75987_b().func_75971_g();
   public static Achievement field_76000_F = (new Achievement("achievement.bookcase", "bookcase", -3, 6, Blocks.field_150342_X, field_75998_D)).func_75971_g();
   public static Achievement field_150962_H = (new Achievement("achievement.breedCow", "breedCow", 7, -5, Items.field_151015_O, field_76022_t)).func_75971_g();
   public static Achievement field_150963_I = (new Achievement("achievement.spawnWither", "spawnWither", 7, 12, new ItemStack(Items.field_151144_bL, 1, 1), field_76003_C)).func_75971_g();
   public static Achievement field_150964_J = (new Achievement("achievement.killWither", "killWither", 7, 10, Items.field_151156_bN, field_150963_I)).func_75971_g();
   public static Achievement field_150965_K = (new Achievement("achievement.fullBeacon", "fullBeacon", 7, 8, Blocks.field_150461_bJ, field_150964_J)).func_75987_b().func_75971_g();
   public static Achievement field_150961_L = (new Achievement("achievement.exploreAllBiomes", "exploreAllBiomes", 4, 8, Items.field_151175_af, field_76002_B)).func_150953_b(JsonSerializableSet.class).func_75987_b().func_75971_g();
   public static Achievement field_180219_M = (new Achievement("achievement.overpowered", "overpowered", 6, 4, new ItemStack(Items.field_151153_ao, 1, 1), field_76012_o)).func_75987_b().func_75971_g();

   public static void func_75997_a() {
   }
}
