package net.minecraft.tileentity;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBanner extends TileEntity {
   private int field_175120_a;
   private NBTTagList field_175118_f;
   private boolean field_175119_g;
   private List<TileEntityBanner.EnumBannerPattern> field_175122_h;
   private List<EnumDyeColor> field_175123_i;
   private String field_175121_j;

   public void func_175112_a(ItemStack p_175112_1_) {
      this.field_175118_f = null;
      if(p_175112_1_.func_77942_o() && p_175112_1_.func_77978_p().func_150297_b("BlockEntityTag", 10)) {
         NBTTagCompound nbttagcompound = p_175112_1_.func_77978_p().func_74775_l("BlockEntityTag");
         if(nbttagcompound.func_74764_b("Patterns")) {
            this.field_175118_f = (NBTTagList)nbttagcompound.func_150295_c("Patterns", 10).func_74737_b();
         }

         if(nbttagcompound.func_150297_b("Base", 99)) {
            this.field_175120_a = nbttagcompound.func_74762_e("Base");
         } else {
            this.field_175120_a = p_175112_1_.func_77960_j() & 15;
         }
      } else {
         this.field_175120_a = p_175112_1_.func_77960_j() & 15;
      }

      this.field_175122_h = null;
      this.field_175123_i = null;
      this.field_175121_j = "";
      this.field_175119_g = true;
   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      func_181020_a(p_145841_1_, this.field_175120_a, this.field_175118_f);
   }

   public static void func_181020_a(NBTTagCompound p_181020_0_, int p_181020_1_, NBTTagList p_181020_2_) {
      p_181020_0_.func_74768_a("Base", p_181020_1_);
      if(p_181020_2_ != null) {
         p_181020_0_.func_74782_a("Patterns", p_181020_2_);
      }

   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_175120_a = p_145839_1_.func_74762_e("Base");
      this.field_175118_f = p_145839_1_.func_150295_c("Patterns", 10);
      this.field_175122_h = null;
      this.field_175123_i = null;
      this.field_175121_j = null;
      this.field_175119_g = true;
   }

   public Packet func_145844_m() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      this.func_145841_b(nbttagcompound);
      return new S35PacketUpdateTileEntity(this.field_174879_c, 6, nbttagcompound);
   }

   public int func_175115_b() {
      return this.field_175120_a;
   }

   public static int func_175111_b(ItemStack p_175111_0_) {
      NBTTagCompound nbttagcompound = p_175111_0_.func_179543_a("BlockEntityTag", false);
      return nbttagcompound != null && nbttagcompound.func_74764_b("Base")?nbttagcompound.func_74762_e("Base"):p_175111_0_.func_77960_j();
   }

   public static int func_175113_c(ItemStack p_175113_0_) {
      NBTTagCompound nbttagcompound = p_175113_0_.func_179543_a("BlockEntityTag", false);
      return nbttagcompound != null && nbttagcompound.func_74764_b("Patterns")?nbttagcompound.func_150295_c("Patterns", 10).func_74745_c():0;
   }

   public List<TileEntityBanner.EnumBannerPattern> func_175114_c() {
      this.func_175109_g();
      return this.field_175122_h;
   }

   public NBTTagList func_181021_d() {
      return this.field_175118_f;
   }

   public List<EnumDyeColor> func_175110_d() {
      this.func_175109_g();
      return this.field_175123_i;
   }

   public String func_175116_e() {
      this.func_175109_g();
      return this.field_175121_j;
   }

   private void func_175109_g() {
      if(this.field_175122_h == null || this.field_175123_i == null || this.field_175121_j == null) {
         if(!this.field_175119_g) {
            this.field_175121_j = "";
         } else {
            this.field_175122_h = Lists.<TileEntityBanner.EnumBannerPattern>newArrayList();
            this.field_175123_i = Lists.<EnumDyeColor>newArrayList();
            this.field_175122_h.add(TileEntityBanner.EnumBannerPattern.BASE);
            this.field_175123_i.add(EnumDyeColor.func_176766_a(this.field_175120_a));
            this.field_175121_j = "b" + this.field_175120_a;
            if(this.field_175118_f != null) {
               for(int i = 0; i < this.field_175118_f.func_74745_c(); ++i) {
                  NBTTagCompound nbttagcompound = this.field_175118_f.func_150305_b(i);
                  TileEntityBanner.EnumBannerPattern tileentitybanner$enumbannerpattern = TileEntityBanner.EnumBannerPattern.func_177268_a(nbttagcompound.func_74779_i("Pattern"));
                  if(tileentitybanner$enumbannerpattern != null) {
                     this.field_175122_h.add(tileentitybanner$enumbannerpattern);
                     int j = nbttagcompound.func_74762_e("Color");
                     this.field_175123_i.add(EnumDyeColor.func_176766_a(j));
                     this.field_175121_j = this.field_175121_j + tileentitybanner$enumbannerpattern.func_177273_b() + j;
                  }
               }
            }

         }
      }
   }

   public static void func_175117_e(ItemStack p_175117_0_) {
      NBTTagCompound nbttagcompound = p_175117_0_.func_179543_a("BlockEntityTag", false);
      if(nbttagcompound != null && nbttagcompound.func_150297_b("Patterns", 9)) {
         NBTTagList nbttaglist = nbttagcompound.func_150295_c("Patterns", 10);
         if(nbttaglist.func_74745_c() > 0) {
            nbttaglist.func_74744_a(nbttaglist.func_74745_c() - 1);
            if(nbttaglist.func_82582_d()) {
               p_175117_0_.func_77978_p().func_82580_o("BlockEntityTag");
               if(p_175117_0_.func_77978_p().func_82582_d()) {
                  p_175117_0_.func_77982_d((NBTTagCompound)null);
               }
            }

         }
      }
   }

   public static enum EnumBannerPattern {
      BASE("base", "b"),
      SQUARE_BOTTOM_LEFT("square_bottom_left", "bl", "   ", "   ", "#  "),
      SQUARE_BOTTOM_RIGHT("square_bottom_right", "br", "   ", "   ", "  #"),
      SQUARE_TOP_LEFT("square_top_left", "tl", "#  ", "   ", "   "),
      SQUARE_TOP_RIGHT("square_top_right", "tr", "  #", "   ", "   "),
      STRIPE_BOTTOM("stripe_bottom", "bs", "   ", "   ", "###"),
      STRIPE_TOP("stripe_top", "ts", "###", "   ", "   "),
      STRIPE_LEFT("stripe_left", "ls", "#  ", "#  ", "#  "),
      STRIPE_RIGHT("stripe_right", "rs", "  #", "  #", "  #"),
      STRIPE_CENTER("stripe_center", "cs", " # ", " # ", " # "),
      STRIPE_MIDDLE("stripe_middle", "ms", "   ", "###", "   "),
      STRIPE_DOWNRIGHT("stripe_downright", "drs", "#  ", " # ", "  #"),
      STRIPE_DOWNLEFT("stripe_downleft", "dls", "  #", " # ", "#  "),
      STRIPE_SMALL("small_stripes", "ss", "# #", "# #", "   "),
      CROSS("cross", "cr", "# #", " # ", "# #"),
      STRAIGHT_CROSS("straight_cross", "sc", " # ", "###", " # "),
      TRIANGLE_BOTTOM("triangle_bottom", "bt", "   ", " # ", "# #"),
      TRIANGLE_TOP("triangle_top", "tt", "# #", " # ", "   "),
      TRIANGLES_BOTTOM("triangles_bottom", "bts", "   ", "# #", " # "),
      TRIANGLES_TOP("triangles_top", "tts", " # ", "# #", "   "),
      DIAGONAL_LEFT("diagonal_left", "ld", "## ", "#  ", "   "),
      DIAGONAL_RIGHT("diagonal_up_right", "rd", "   ", "  #", " ##"),
      DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud", "   ", "#  ", "## "),
      DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud", " ##", "  #", "   "),
      CIRCLE_MIDDLE("circle", "mc", "   ", " # ", "   "),
      RHOMBUS_MIDDLE("rhombus", "mr", " # ", "# #", " # "),
      HALF_VERTICAL("half_vertical", "vh", "## ", "## ", "## "),
      HALF_HORIZONTAL("half_horizontal", "hh", "###", "###", "   "),
      HALF_VERTICAL_MIRROR("half_vertical_right", "vhr", " ##", " ##", " ##"),
      HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb", "   ", "###", "###"),
      BORDER("border", "bo", "###", "# #", "###"),
      CURLY_BORDER("curly_border", "cbo", new ItemStack(Blocks.field_150395_bd)),
      CREEPER("creeper", "cre", new ItemStack(Items.field_151144_bL, 1, 4)),
      GRADIENT("gradient", "gra", "# #", " # ", " # "),
      GRADIENT_UP("gradient_up", "gru", " # ", " # ", "# #"),
      BRICKS("bricks", "bri", new ItemStack(Blocks.field_150336_V)),
      SKULL("skull", "sku", new ItemStack(Items.field_151144_bL, 1, 1)),
      FLOWER("flower", "flo", new ItemStack(Blocks.field_150328_O, 1, BlockFlower.EnumFlowerType.OXEYE_DAISY.func_176968_b())),
      MOJANG("mojang", "moj", new ItemStack(Items.field_151153_ao, 1, 1));

      private String field_177284_N;
      private String field_177285_O;
      private String[] field_177291_P;
      private ItemStack field_177290_Q;

      private EnumBannerPattern(String p_i45670_3_, String p_i45670_4_) {
         this.field_177291_P = new String[3];
         this.field_177284_N = p_i45670_3_;
         this.field_177285_O = p_i45670_4_;
      }

      private EnumBannerPattern(String p_i45671_3_, String p_i45671_4_, ItemStack p_i45671_5_) {
         this(p_i45671_3_, p_i45671_4_);
         this.field_177290_Q = p_i45671_5_;
      }

      private EnumBannerPattern(String p_i45672_3_, String p_i45672_4_, String p_i45672_5_, String p_i45672_6_, String p_i45672_7_) {
         this(p_i45672_3_, p_i45672_4_);
         this.field_177291_P[0] = p_i45672_5_;
         this.field_177291_P[1] = p_i45672_6_;
         this.field_177291_P[2] = p_i45672_7_;
      }

      public String func_177271_a() {
         return this.field_177284_N;
      }

      public String func_177273_b() {
         return this.field_177285_O;
      }

      public String[] func_177267_c() {
         return this.field_177291_P;
      }

      public boolean func_177270_d() {
         return this.field_177290_Q != null || this.field_177291_P[0] != null;
      }

      public boolean func_177269_e() {
         return this.field_177290_Q != null;
      }

      public ItemStack func_177272_f() {
         return this.field_177290_Q;
      }

      public static TileEntityBanner.EnumBannerPattern func_177268_a(String p_177268_0_) {
         for(TileEntityBanner.EnumBannerPattern tileentitybanner$enumbannerpattern : values()) {
            if(tileentitybanner$enumbannerpattern.field_177285_O.equals(p_177268_0_)) {
               return tileentitybanner$enumbannerpattern;
            }
         }

         return null;
      }
   }
}
