package net.minecraft.creativetab;

import java.util.List;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class CreativeTabs {
   public static final CreativeTabs[] field_78032_a = new CreativeTabs[12];
   public static final CreativeTabs field_78030_b = new CreativeTabs(0, "buildingBlocks") {
      public Item func_78016_d() {
         return Item.func_150898_a(Blocks.field_150336_V);
      }
   };
   public static final CreativeTabs field_78031_c = new CreativeTabs(1, "decorations") {
      public Item func_78016_d() {
         return Item.func_150898_a(Blocks.field_150398_cm);
      }

      public int func_151243_f() {
         return BlockDoublePlant.EnumPlantType.PAEONIA.func_176936_a();
      }
   };
   public static final CreativeTabs field_78028_d = new CreativeTabs(2, "redstone") {
      public Item func_78016_d() {
         return Items.field_151137_ax;
      }
   };
   public static final CreativeTabs field_78029_e = new CreativeTabs(3, "transportation") {
      public Item func_78016_d() {
         return Item.func_150898_a(Blocks.field_150318_D);
      }
   };
   public static final CreativeTabs field_78026_f = (new CreativeTabs(4, "misc") {
      public Item func_78016_d() {
         return Items.field_151129_at;
      }
   }).func_111229_a(new EnumEnchantmentType[]{EnumEnchantmentType.ALL});
   public static final CreativeTabs field_78027_g = (new CreativeTabs(5, "search") {
      public Item func_78016_d() {
         return Items.field_151111_aL;
      }
   }).func_78025_a("item_search.png");
   public static final CreativeTabs field_78039_h = new CreativeTabs(6, "food") {
      public Item func_78016_d() {
         return Items.field_151034_e;
      }
   };
   public static final CreativeTabs field_78040_i = (new CreativeTabs(7, "tools") {
      public Item func_78016_d() {
         return Items.field_151036_c;
      }
   }).func_111229_a(new EnumEnchantmentType[]{EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE});
   public static final CreativeTabs field_78037_j = (new CreativeTabs(8, "combat") {
      public Item func_78016_d() {
         return Items.field_151010_B;
      }
   }).func_111229_a(new EnumEnchantmentType[]{EnumEnchantmentType.ARMOR, EnumEnchantmentType.ARMOR_FEET, EnumEnchantmentType.ARMOR_HEAD, EnumEnchantmentType.ARMOR_LEGS, EnumEnchantmentType.ARMOR_TORSO, EnumEnchantmentType.BOW, EnumEnchantmentType.WEAPON});
   public static final CreativeTabs field_78038_k = new CreativeTabs(9, "brewing") {
      public Item func_78016_d() {
         return Items.field_151068_bn;
      }
   };
   public static final CreativeTabs field_78035_l = new CreativeTabs(10, "materials") {
      public Item func_78016_d() {
         return Items.field_151055_y;
      }
   };
   public static final CreativeTabs field_78036_m = (new CreativeTabs(11, "inventory") {
      public Item func_78016_d() {
         return Item.func_150898_a(Blocks.field_150486_ae);
      }
   }).func_78025_a("inventory.png").func_78022_j().func_78014_h();
   private final int field_78033_n;
   private final String field_78034_o;
   private String field_78043_p = "items.png";
   private boolean field_78042_q = true;
   private boolean field_78041_r = true;
   private EnumEnchantmentType[] field_111230_s;
   private ItemStack field_151245_t;

   public CreativeTabs(int p_i1853_1_, String p_i1853_2_) {
      this.field_78033_n = p_i1853_1_;
      this.field_78034_o = p_i1853_2_;
      field_78032_a[p_i1853_1_] = this;
   }

   public int func_78021_a() {
      return this.field_78033_n;
   }

   public String func_78013_b() {
      return this.field_78034_o;
   }

   public String func_78024_c() {
      return "itemGroup." + this.func_78013_b();
   }

   public ItemStack func_151244_d() {
      if(this.field_151245_t == null) {
         this.field_151245_t = new ItemStack(this.func_78016_d(), 1, this.func_151243_f());
      }

      return this.field_151245_t;
   }

   public abstract Item func_78016_d();

   public int func_151243_f() {
      return 0;
   }

   public String func_78015_f() {
      return this.field_78043_p;
   }

   public CreativeTabs func_78025_a(String p_78025_1_) {
      this.field_78043_p = p_78025_1_;
      return this;
   }

   public boolean func_78019_g() {
      return this.field_78041_r;
   }

   public CreativeTabs func_78014_h() {
      this.field_78041_r = false;
      return this;
   }

   public boolean func_78017_i() {
      return this.field_78042_q;
   }

   public CreativeTabs func_78022_j() {
      this.field_78042_q = false;
      return this;
   }

   public int func_78020_k() {
      return this.field_78033_n % 6;
   }

   public boolean func_78023_l() {
      return this.field_78033_n < 6;
   }

   public EnumEnchantmentType[] func_111225_m() {
      return this.field_111230_s;
   }

   public CreativeTabs func_111229_a(EnumEnchantmentType... p_111229_1_) {
      this.field_111230_s = p_111229_1_;
      return this;
   }

   public boolean func_111226_a(EnumEnchantmentType p_111226_1_) {
      if(this.field_111230_s == null) {
         return false;
      } else {
         for(EnumEnchantmentType enumenchantmenttype : this.field_111230_s) {
            if(enumenchantmenttype == p_111226_1_) {
               return true;
            }
         }

         return false;
      }
   }

   public void func_78018_a(List<ItemStack> p_78018_1_) {
      for(Item item : Item.field_150901_e) {
         if(item != null && item.func_77640_w() == this) {
            item.func_150895_a(item, this, p_78018_1_);
         }
      }

      if(this.func_111225_m() != null) {
         this.func_92116_a(p_78018_1_, this.func_111225_m());
      }

   }

   public void func_92116_a(List<ItemStack> p_92116_1_, EnumEnchantmentType... p_92116_2_) {
      for(Enchantment enchantment : Enchantment.field_77331_b) {
         if(enchantment != null && enchantment.field_77351_y != null) {
            boolean flag = false;

            for(int i = 0; i < p_92116_2_.length && !flag; ++i) {
               if(enchantment.field_77351_y == p_92116_2_[i]) {
                  flag = true;
               }
            }

            if(flag) {
               p_92116_1_.add(Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, enchantment.func_77325_b())));
            }
         }
      }

   }
}
