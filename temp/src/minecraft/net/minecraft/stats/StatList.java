package net.minecraft.stats;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatBasic;
import net.minecraft.stats.StatCrafting;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ResourceLocation;

public class StatList {
   protected static Map<String, StatBase> field_75942_a = Maps.<String, StatBase>newHashMap();
   public static List<StatBase> field_75940_b = Lists.<StatBase>newArrayList();
   public static List<StatBase> field_75941_c = Lists.<StatBase>newArrayList();
   public static List<StatCrafting> field_75938_d = Lists.<StatCrafting>newArrayList();
   public static List<StatCrafting> field_75939_e = Lists.<StatCrafting>newArrayList();
   public static StatBase field_75947_j = (new StatBasic("stat.leaveGame", new ChatComponentTranslation("stat.leaveGame", new Object[0]))).func_75966_h().func_75971_g();
   public static StatBase field_75948_k = (new StatBasic("stat.playOneMinute", new ChatComponentTranslation("stat.playOneMinute", new Object[0]), StatBase.field_75981_i)).func_75966_h().func_75971_g();
   public static StatBase field_180209_h = (new StatBasic("stat.timeSinceDeath", new ChatComponentTranslation("stat.timeSinceDeath", new Object[0]), StatBase.field_75981_i)).func_75966_h().func_75971_g();
   public static StatBase field_75945_l = (new StatBasic("stat.walkOneCm", new ChatComponentTranslation("stat.walkOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_180207_j = (new StatBasic("stat.crouchOneCm", new ChatComponentTranslation("stat.crouchOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_180208_k = (new StatBasic("stat.sprintOneCm", new ChatComponentTranslation("stat.sprintOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75946_m = (new StatBasic("stat.swimOneCm", new ChatComponentTranslation("stat.swimOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75943_n = (new StatBasic("stat.fallOneCm", new ChatComponentTranslation("stat.fallOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75944_o = (new StatBasic("stat.climbOneCm", new ChatComponentTranslation("stat.climbOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75958_p = (new StatBasic("stat.flyOneCm", new ChatComponentTranslation("stat.flyOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75957_q = (new StatBasic("stat.diveOneCm", new ChatComponentTranslation("stat.diveOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75956_r = (new StatBasic("stat.minecartOneCm", new ChatComponentTranslation("stat.minecartOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75955_s = (new StatBasic("stat.boatOneCm", new ChatComponentTranslation("stat.boatOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75954_t = (new StatBasic("stat.pigOneCm", new ChatComponentTranslation("stat.pigOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_151185_q = (new StatBasic("stat.horseOneCm", new ChatComponentTranslation("stat.horseOneCm", new Object[0]), StatBase.field_75979_j)).func_75966_h().func_75971_g();
   public static StatBase field_75953_u = (new StatBasic("stat.jump", new ChatComponentTranslation("stat.jump", new Object[0]))).func_75966_h().func_75971_g();
   public static StatBase field_75952_v = (new StatBasic("stat.drop", new ChatComponentTranslation("stat.drop", new Object[0]))).func_75966_h().func_75971_g();
   public static StatBase field_75951_w = (new StatBasic("stat.damageDealt", new ChatComponentTranslation("stat.damageDealt", new Object[0]), StatBase.field_111202_k)).func_75971_g();
   public static StatBase field_75961_x = (new StatBasic("stat.damageTaken", new ChatComponentTranslation("stat.damageTaken", new Object[0]), StatBase.field_111202_k)).func_75971_g();
   public static StatBase field_75960_y = (new StatBasic("stat.deaths", new ChatComponentTranslation("stat.deaths", new Object[0]))).func_75971_g();
   public static StatBase field_75959_z = (new StatBasic("stat.mobKills", new ChatComponentTranslation("stat.mobKills", new Object[0]))).func_75971_g();
   public static StatBase field_151186_x = (new StatBasic("stat.animalsBred", new ChatComponentTranslation("stat.animalsBred", new Object[0]))).func_75971_g();
   public static StatBase field_75932_A = (new StatBasic("stat.playerKills", new ChatComponentTranslation("stat.playerKills", new Object[0]))).func_75971_g();
   public static StatBase field_75933_B = (new StatBasic("stat.fishCaught", new ChatComponentTranslation("stat.fishCaught", new Object[0]))).func_75971_g();
   public static StatBase field_151183_A = (new StatBasic("stat.junkFished", new ChatComponentTranslation("stat.junkFished", new Object[0]))).func_75971_g();
   public static StatBase field_151184_B = (new StatBasic("stat.treasureFished", new ChatComponentTranslation("stat.treasureFished", new Object[0]))).func_75971_g();
   public static StatBase field_180205_F = (new StatBasic("stat.talkedToVillager", new ChatComponentTranslation("stat.talkedToVillager", new Object[0]))).func_75971_g();
   public static StatBase field_180206_G = (new StatBasic("stat.tradedWithVillager", new ChatComponentTranslation("stat.tradedWithVillager", new Object[0]))).func_75971_g();
   public static StatBase field_181724_H = (new StatBasic("stat.cakeSlicesEaten", new ChatComponentTranslation("stat.cakeSlicesEaten", new Object[0]))).func_75971_g();
   public static StatBase field_181725_I = (new StatBasic("stat.cauldronFilled", new ChatComponentTranslation("stat.cauldronFilled", new Object[0]))).func_75971_g();
   public static StatBase field_181726_J = (new StatBasic("stat.cauldronUsed", new ChatComponentTranslation("stat.cauldronUsed", new Object[0]))).func_75971_g();
   public static StatBase field_181727_K = (new StatBasic("stat.armorCleaned", new ChatComponentTranslation("stat.armorCleaned", new Object[0]))).func_75971_g();
   public static StatBase field_181728_L = (new StatBasic("stat.bannerCleaned", new ChatComponentTranslation("stat.bannerCleaned", new Object[0]))).func_75971_g();
   public static StatBase field_181729_M = (new StatBasic("stat.brewingstandInteraction", new ChatComponentTranslation("stat.brewingstandInteraction", new Object[0]))).func_75971_g();
   public static StatBase field_181730_N = (new StatBasic("stat.beaconInteraction", new ChatComponentTranslation("stat.beaconInteraction", new Object[0]))).func_75971_g();
   public static StatBase field_181731_O = (new StatBasic("stat.dropperInspected", new ChatComponentTranslation("stat.dropperInspected", new Object[0]))).func_75971_g();
   public static StatBase field_181732_P = (new StatBasic("stat.hopperInspected", new ChatComponentTranslation("stat.hopperInspected", new Object[0]))).func_75971_g();
   public static StatBase field_181733_Q = (new StatBasic("stat.dispenserInspected", new ChatComponentTranslation("stat.dispenserInspected", new Object[0]))).func_75971_g();
   public static StatBase field_181734_R = (new StatBasic("stat.noteblockPlayed", new ChatComponentTranslation("stat.noteblockPlayed", new Object[0]))).func_75971_g();
   public static StatBase field_181735_S = (new StatBasic("stat.noteblockTuned", new ChatComponentTranslation("stat.noteblockTuned", new Object[0]))).func_75971_g();
   public static StatBase field_181736_T = (new StatBasic("stat.flowerPotted", new ChatComponentTranslation("stat.flowerPotted", new Object[0]))).func_75971_g();
   public static StatBase field_181737_U = (new StatBasic("stat.trappedChestTriggered", new ChatComponentTranslation("stat.trappedChestTriggered", new Object[0]))).func_75971_g();
   public static StatBase field_181738_V = (new StatBasic("stat.enderchestOpened", new ChatComponentTranslation("stat.enderchestOpened", new Object[0]))).func_75971_g();
   public static StatBase field_181739_W = (new StatBasic("stat.itemEnchanted", new ChatComponentTranslation("stat.itemEnchanted", new Object[0]))).func_75971_g();
   public static StatBase field_181740_X = (new StatBasic("stat.recordPlayed", new ChatComponentTranslation("stat.recordPlayed", new Object[0]))).func_75971_g();
   public static StatBase field_181741_Y = (new StatBasic("stat.furnaceInteraction", new ChatComponentTranslation("stat.furnaceInteraction", new Object[0]))).func_75971_g();
   public static StatBase field_181742_Z = (new StatBasic("stat.craftingTableInteraction", new ChatComponentTranslation("stat.workbenchInteraction", new Object[0]))).func_75971_g();
   public static StatBase field_181723_aa = (new StatBasic("stat.chestOpened", new ChatComponentTranslation("stat.chestOpened", new Object[0]))).func_75971_g();
   public static final StatBase[] field_75934_C = new StatBase[4096];
   public static final StatBase[] field_75928_D = new StatBase[32000];
   public static final StatBase[] field_75929_E = new StatBase[32000];
   public static final StatBase[] field_75930_F = new StatBase[32000];

   public static void func_151178_a() {
      func_151181_c();
      func_75925_c();
      func_151179_e();
      func_75918_d();
      AchievementList.func_75997_a();
      EntityList.func_151514_a();
   }

   private static void func_75918_d() {
      Set<Item> set = Sets.<Item>newHashSet();

      for(IRecipe irecipe : CraftingManager.func_77594_a().func_77592_b()) {
         if(irecipe.func_77571_b() != null) {
            set.add(irecipe.func_77571_b().func_77973_b());
         }
      }

      for(ItemStack itemstack : FurnaceRecipes.func_77602_a().func_77599_b().values()) {
         set.add(itemstack.func_77973_b());
      }

      for(Item item : set) {
         if(item != null) {
            int i = Item.func_150891_b(item);
            String s = func_180204_a(item);
            if(s != null) {
               field_75928_D[i] = (new StatCrafting("stat.craftItem.", s, new ChatComponentTranslation("stat.craftItem", new Object[]{(new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
            }
         }
      }

      func_75924_a(field_75928_D);
   }

   private static void func_151181_c() {
      for(Block block : Block.field_149771_c) {
         Item item = Item.func_150898_a(block);
         if(item != null) {
            int i = Block.func_149682_b(block);
            String s = func_180204_a(item);
            if(s != null && block.func_149652_G()) {
               field_75934_C[i] = (new StatCrafting("stat.mineBlock.", s, new ChatComponentTranslation("stat.mineBlock", new Object[]{(new ItemStack(block)).func_151000_E()}), item)).func_75971_g();
               field_75939_e.add((StatCrafting)field_75934_C[i]);
            }
         }
      }

      func_75924_a(field_75934_C);
   }

   private static void func_75925_c() {
      for(Item item : Item.field_150901_e) {
         if(item != null) {
            int i = Item.func_150891_b(item);
            String s = func_180204_a(item);
            if(s != null) {
               field_75929_E[i] = (new StatCrafting("stat.useItem.", s, new ChatComponentTranslation("stat.useItem", new Object[]{(new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
               if(!(item instanceof ItemBlock)) {
                  field_75938_d.add((StatCrafting)field_75929_E[i]);
               }
            }
         }
      }

      func_75924_a(field_75929_E);
   }

   private static void func_151179_e() {
      for(Item item : Item.field_150901_e) {
         if(item != null) {
            int i = Item.func_150891_b(item);
            String s = func_180204_a(item);
            if(s != null && item.func_77645_m()) {
               field_75930_F[i] = (new StatCrafting("stat.breakItem.", s, new ChatComponentTranslation("stat.breakItem", new Object[]{(new ItemStack(item)).func_151000_E()}), item)).func_75971_g();
            }
         }
      }

      func_75924_a(field_75930_F);
   }

   private static String func_180204_a(Item p_180204_0_) {
      ResourceLocation resourcelocation = (ResourceLocation)Item.field_150901_e.func_177774_c(p_180204_0_);
      return resourcelocation != null?resourcelocation.toString().replace(':', '.'):null;
   }

   private static void func_75924_a(StatBase[] p_75924_0_) {
      func_151180_a(p_75924_0_, Blocks.field_150355_j, Blocks.field_150358_i);
      func_151180_a(p_75924_0_, Blocks.field_150353_l, Blocks.field_150356_k);
      func_151180_a(p_75924_0_, Blocks.field_150428_aP, Blocks.field_150423_aK);
      func_151180_a(p_75924_0_, Blocks.field_150470_am, Blocks.field_150460_al);
      func_151180_a(p_75924_0_, Blocks.field_150439_ay, Blocks.field_150450_ax);
      func_151180_a(p_75924_0_, Blocks.field_150416_aS, Blocks.field_150413_aR);
      func_151180_a(p_75924_0_, Blocks.field_150455_bV, Blocks.field_150441_bU);
      func_151180_a(p_75924_0_, Blocks.field_150429_aA, Blocks.field_150437_az);
      func_151180_a(p_75924_0_, Blocks.field_150374_bv, Blocks.field_150379_bu);
      func_151180_a(p_75924_0_, Blocks.field_150334_T, Blocks.field_150333_U);
      func_151180_a(p_75924_0_, Blocks.field_150373_bw, Blocks.field_150376_bx);
      func_151180_a(p_75924_0_, Blocks.field_180388_cO, Blocks.field_180389_cP);
      func_151180_a(p_75924_0_, Blocks.field_150349_c, Blocks.field_150346_d);
      func_151180_a(p_75924_0_, Blocks.field_150458_ak, Blocks.field_150346_d);
   }

   private static void func_151180_a(StatBase[] p_151180_0_, Block p_151180_1_, Block p_151180_2_) {
      int i = Block.func_149682_b(p_151180_1_);
      int j = Block.func_149682_b(p_151180_2_);
      if(p_151180_0_[i] != null && p_151180_0_[j] == null) {
         p_151180_0_[j] = p_151180_0_[i];
      } else {
         field_75940_b.remove(p_151180_0_[i]);
         field_75939_e.remove(p_151180_0_[i]);
         field_75941_c.remove(p_151180_0_[i]);
         p_151180_0_[i] = p_151180_0_[j];
      }
   }

   public static StatBase func_151182_a(EntityList.EntityEggInfo p_151182_0_) {
      String s = EntityList.func_75617_a(p_151182_0_.field_75613_a);
      return s == null?null:(new StatBase("stat.killEntity." + s, new ChatComponentTranslation("stat.entityKill", new Object[]{new ChatComponentTranslation("entity." + s + ".name", new Object[0])}))).func_75971_g();
   }

   public static StatBase func_151176_b(EntityList.EntityEggInfo p_151176_0_) {
      String s = EntityList.func_75617_a(p_151176_0_.field_75613_a);
      return s == null?null:(new StatBase("stat.entityKilledBy." + s, new ChatComponentTranslation("stat.entityKilledBy", new Object[]{new ChatComponentTranslation("entity." + s + ".name", new Object[0])}))).func_75971_g();
   }

   public static StatBase func_151177_a(String p_151177_0_) {
      return (StatBase)field_75942_a.get(p_151177_0_);
   }
}
