package net.minecraft.client.gui.inventory;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiContainerCreative extends InventoryEffectRenderer {
   private static final ResourceLocation field_147061_u = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   private static InventoryBasic field_147060_v = new InventoryBasic("tmp", true, 45);
   private static int field_147058_w = CreativeTabs.field_78030_b.func_78021_a();
   private float field_147067_x;
   private boolean field_147066_y;
   private boolean field_147065_z;
   private GuiTextField field_147062_A;
   private List<Slot> field_147063_B;
   private Slot field_147064_C;
   private boolean field_147057_D;
   private CreativeCrafting field_147059_E;

   public GuiContainerCreative(EntityPlayer p_i1088_1_) {
      super(new GuiContainerCreative.ContainerCreative(p_i1088_1_));
      p_i1088_1_.field_71070_bA = this.field_147002_h;
      this.field_146291_p = true;
      this.field_147000_g = 136;
      this.field_146999_f = 195;
   }

   public void func_73876_c() {
      if(!this.field_146297_k.field_71442_b.func_78758_h()) {
         this.field_146297_k.func_147108_a(new GuiInventory(this.field_146297_k.field_71439_g));
      }

      this.func_175378_g();
   }

   protected void func_146984_a(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, int p_146984_4_) {
      this.field_147057_D = true;
      boolean flag = p_146984_4_ == 1;
      p_146984_4_ = p_146984_2_ == -999 && p_146984_4_ == 0?4:p_146984_4_;
      if(p_146984_1_ == null && field_147058_w != CreativeTabs.field_78036_m.func_78021_a() && p_146984_4_ != 5) {
         InventoryPlayer inventoryplayer1 = this.field_146297_k.field_71439_g.field_71071_by;
         if(inventoryplayer1.func_70445_o() != null) {
            if(p_146984_3_ == 0) {
               this.field_146297_k.field_71439_g.func_71019_a(inventoryplayer1.func_70445_o(), true);
               this.field_146297_k.field_71442_b.func_78752_a(inventoryplayer1.func_70445_o());
               inventoryplayer1.func_70437_b((ItemStack)null);
            }

            if(p_146984_3_ == 1) {
               ItemStack itemstack5 = inventoryplayer1.func_70445_o().func_77979_a(1);
               this.field_146297_k.field_71439_g.func_71019_a(itemstack5, true);
               this.field_146297_k.field_71442_b.func_78752_a(itemstack5);
               if(inventoryplayer1.func_70445_o().field_77994_a == 0) {
                  inventoryplayer1.func_70437_b((ItemStack)null);
               }
            }
         }
      } else if(p_146984_1_ == this.field_147064_C && flag) {
         for(int j = 0; j < this.field_146297_k.field_71439_g.field_71069_bz.func_75138_a().size(); ++j) {
            this.field_146297_k.field_71442_b.func_78761_a((ItemStack)null, j);
         }
      } else if(field_147058_w == CreativeTabs.field_78036_m.func_78021_a()) {
         if(p_146984_1_ == this.field_147064_C) {
            this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
         } else if(p_146984_4_ == 4 && p_146984_1_ != null && p_146984_1_.func_75216_d()) {
            ItemStack itemstack = p_146984_1_.func_75209_a(p_146984_3_ == 0?1:p_146984_1_.func_75211_c().func_77976_d());
            this.field_146297_k.field_71439_g.func_71019_a(itemstack, true);
            this.field_146297_k.field_71442_b.func_78752_a(itemstack);
         } else if(p_146984_4_ == 4 && this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null) {
            this.field_146297_k.field_71439_g.func_71019_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o(), true);
            this.field_146297_k.field_71442_b.func_78752_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o());
            this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
         } else {
            this.field_146297_k.field_71439_g.field_71069_bz.func_75144_a(p_146984_1_ == null?p_146984_2_:((GuiContainerCreative.CreativeSlot)p_146984_1_).field_148332_b.field_75222_d, p_146984_3_, p_146984_4_, this.field_146297_k.field_71439_g);
            this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
         }
      } else if(p_146984_4_ != 5 && p_146984_1_.field_75224_c == field_147060_v) {
         InventoryPlayer inventoryplayer = this.field_146297_k.field_71439_g.field_71071_by;
         ItemStack itemstack1 = inventoryplayer.func_70445_o();
         ItemStack itemstack2 = p_146984_1_.func_75211_c();
         if(p_146984_4_ == 2) {
            if(itemstack2 != null && p_146984_3_ >= 0 && p_146984_3_ < 9) {
               ItemStack itemstack7 = itemstack2.func_77946_l();
               itemstack7.field_77994_a = itemstack7.func_77976_d();
               this.field_146297_k.field_71439_g.field_71071_by.func_70299_a(p_146984_3_, itemstack7);
               this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
            }

            return;
         }

         if(p_146984_4_ == 3) {
            if(inventoryplayer.func_70445_o() == null && p_146984_1_.func_75216_d()) {
               ItemStack itemstack6 = p_146984_1_.func_75211_c().func_77946_l();
               itemstack6.field_77994_a = itemstack6.func_77976_d();
               inventoryplayer.func_70437_b(itemstack6);
            }

            return;
         }

         if(p_146984_4_ == 4) {
            if(itemstack2 != null) {
               ItemStack itemstack3 = itemstack2.func_77946_l();
               itemstack3.field_77994_a = p_146984_3_ == 0?1:itemstack3.func_77976_d();
               this.field_146297_k.field_71439_g.func_71019_a(itemstack3, true);
               this.field_146297_k.field_71442_b.func_78752_a(itemstack3);
            }

            return;
         }

         if(itemstack1 != null && itemstack2 != null && itemstack1.func_77969_a(itemstack2)) {
            if(p_146984_3_ == 0) {
               if(flag) {
                  itemstack1.field_77994_a = itemstack1.func_77976_d();
               } else if(itemstack1.field_77994_a < itemstack1.func_77976_d()) {
                  ++itemstack1.field_77994_a;
               }
            } else if(itemstack1.field_77994_a <= 1) {
               inventoryplayer.func_70437_b((ItemStack)null);
            } else {
               --itemstack1.field_77994_a;
            }
         } else if(itemstack2 != null && itemstack1 == null) {
            inventoryplayer.func_70437_b(ItemStack.func_77944_b(itemstack2));
            itemstack1 = inventoryplayer.func_70445_o();
            if(flag) {
               itemstack1.field_77994_a = itemstack1.func_77976_d();
            }
         } else {
            inventoryplayer.func_70437_b((ItemStack)null);
         }
      } else {
         this.field_147002_h.func_75144_a(p_146984_1_ == null?p_146984_2_:p_146984_1_.field_75222_d, p_146984_3_, p_146984_4_, this.field_146297_k.field_71439_g);
         if(Container.func_94532_c(p_146984_3_) == 2) {
            for(int i = 0; i < 9; ++i) {
               this.field_146297_k.field_71442_b.func_78761_a(this.field_147002_h.func_75139_a(45 + i).func_75211_c(), 36 + i);
            }
         } else if(p_146984_1_ != null) {
            ItemStack itemstack4 = this.field_147002_h.func_75139_a(p_146984_1_.field_75222_d).func_75211_c();
            this.field_146297_k.field_71442_b.func_78761_a(itemstack4, p_146984_1_.field_75222_d - this.field_147002_h.field_75151_b.size() + 9 + 36);
         }
      }

   }

   protected void func_175378_g() {
      int i = this.field_147003_i;
      super.func_175378_g();
      if(this.field_147062_A != null && this.field_147003_i != i) {
         this.field_147062_A.field_146209_f = this.field_147003_i + 82;
      }

   }

   public void func_73866_w_() {
      if(this.field_146297_k.field_71442_b.func_78758_h()) {
         super.func_73866_w_();
         this.field_146292_n.clear();
         Keyboard.enableRepeatEvents(true);
         this.field_147062_A = new GuiTextField(0, this.field_146289_q, this.field_147003_i + 82, this.field_147009_r + 6, 89, this.field_146289_q.field_78288_b);
         this.field_147062_A.func_146203_f(15);
         this.field_147062_A.func_146185_a(false);
         this.field_147062_A.func_146189_e(false);
         this.field_147062_A.func_146193_g(16777215);
         int i = field_147058_w;
         field_147058_w = -1;
         this.func_147050_b(CreativeTabs.field_78032_a[i]);
         this.field_147059_E = new CreativeCrafting(this.field_146297_k);
         this.field_146297_k.field_71439_g.field_71069_bz.func_75132_a(this.field_147059_E);
      } else {
         this.field_146297_k.func_147108_a(new GuiInventory(this.field_146297_k.field_71439_g));
      }

   }

   public void func_146281_b() {
      super.func_146281_b();
      if(this.field_146297_k.field_71439_g != null && this.field_146297_k.field_71439_g.field_71071_by != null) {
         this.field_146297_k.field_71439_g.field_71069_bz.func_82847_b(this.field_147059_E);
      }

      Keyboard.enableRepeatEvents(false);
   }

   protected void func_73869_a(char p_73869_1_, int p_73869_2_) throws IOException {
      if(field_147058_w != CreativeTabs.field_78027_g.func_78021_a()) {
         if(GameSettings.func_100015_a(this.field_146297_k.field_71474_y.field_74310_D)) {
            this.func_147050_b(CreativeTabs.field_78027_g);
         } else {
            super.func_73869_a(p_73869_1_, p_73869_2_);
         }

      } else {
         if(this.field_147057_D) {
            this.field_147057_D = false;
            this.field_147062_A.func_146180_a("");
         }

         if(!this.func_146983_a(p_73869_2_)) {
            if(this.field_147062_A.func_146201_a(p_73869_1_, p_73869_2_)) {
               this.func_147053_i();
            } else {
               super.func_73869_a(p_73869_1_, p_73869_2_);
            }

         }
      }
   }

   private void func_147053_i() {
      GuiContainerCreative.ContainerCreative guicontainercreative$containercreative = (GuiContainerCreative.ContainerCreative)this.field_147002_h;
      guicontainercreative$containercreative.field_148330_a.clear();

      for(Item item : Item.field_150901_e) {
         if(item != null && item.func_77640_w() != null) {
            item.func_150895_a(item, (CreativeTabs)null, guicontainercreative$containercreative.field_148330_a);
         }
      }

      for(Enchantment enchantment : Enchantment.field_77331_b) {
         if(enchantment != null && enchantment.field_77351_y != null) {
            Items.field_151134_bR.func_92113_a(enchantment, guicontainercreative$containercreative.field_148330_a);
         }
      }

      Iterator<ItemStack> iterator = guicontainercreative$containercreative.field_148330_a.iterator();
      String s1 = this.field_147062_A.func_146179_b().toLowerCase();

      while(iterator.hasNext()) {
         ItemStack itemstack = (ItemStack)iterator.next();
         boolean flag = false;

         for(String s : itemstack.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x)) {
            if(EnumChatFormatting.func_110646_a(s).toLowerCase().contains(s1)) {
               flag = true;
               break;
            }
         }

         if(!flag) {
            iterator.remove();
         }
      }

      this.field_147067_x = 0.0F;
      guicontainercreative$containercreative.func_148329_a(0.0F);
   }

   protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
      CreativeTabs creativetabs = CreativeTabs.field_78032_a[field_147058_w];
      if(creativetabs.func_78019_g()) {
         GlStateManager.func_179084_k();
         this.field_146289_q.func_78276_b(I18n.func_135052_a(creativetabs.func_78024_c(), new Object[0]), 8, 6, 4210752);
      }

   }

   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) throws IOException {
      if(p_73864_3_ == 0) {
         int i = p_73864_1_ - this.field_147003_i;
         int j = p_73864_2_ - this.field_147009_r;

         for(CreativeTabs creativetabs : CreativeTabs.field_78032_a) {
            if(this.func_147049_a(creativetabs, i, j)) {
               return;
            }
         }
      }

      super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      if(p_146286_3_ == 0) {
         int i = p_146286_1_ - this.field_147003_i;
         int j = p_146286_2_ - this.field_147009_r;

         for(CreativeTabs creativetabs : CreativeTabs.field_78032_a) {
            if(this.func_147049_a(creativetabs, i, j)) {
               this.func_147050_b(creativetabs);
               return;
            }
         }
      }

      super.func_146286_b(p_146286_1_, p_146286_2_, p_146286_3_);
   }

   private boolean func_147055_p() {
      return field_147058_w != CreativeTabs.field_78036_m.func_78021_a() && CreativeTabs.field_78032_a[field_147058_w].func_78017_i() && ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148328_e();
   }

   private void func_147050_b(CreativeTabs p_147050_1_) {
      int i = field_147058_w;
      field_147058_w = p_147050_1_.func_78021_a();
      GuiContainerCreative.ContainerCreative guicontainercreative$containercreative = (GuiContainerCreative.ContainerCreative)this.field_147002_h;
      this.field_147008_s.clear();
      guicontainercreative$containercreative.field_148330_a.clear();
      p_147050_1_.func_78018_a(guicontainercreative$containercreative.field_148330_a);
      if(p_147050_1_ == CreativeTabs.field_78036_m) {
         Container container = this.field_146297_k.field_71439_g.field_71069_bz;
         if(this.field_147063_B == null) {
            this.field_147063_B = guicontainercreative$containercreative.field_75151_b;
         }

         guicontainercreative$containercreative.field_75151_b = Lists.<Slot>newArrayList();

         for(int j = 0; j < container.field_75151_b.size(); ++j) {
            Slot slot = new GuiContainerCreative.CreativeSlot((Slot)container.field_75151_b.get(j), j);
            guicontainercreative$containercreative.field_75151_b.add(slot);
            if(j >= 5 && j < 9) {
               int j1 = j - 5;
               int k1 = j1 / 2;
               int l1 = j1 % 2;
               slot.field_75223_e = 9 + k1 * 54;
               slot.field_75221_f = 6 + l1 * 27;
            } else if(j >= 0 && j < 5) {
               slot.field_75221_f = -2000;
               slot.field_75223_e = -2000;
            } else if(j < container.field_75151_b.size()) {
               int k = j - 9;
               int l = k % 9;
               int i1 = k / 9;
               slot.field_75223_e = 9 + l * 18;
               if(j >= 36) {
                  slot.field_75221_f = 112;
               } else {
                  slot.field_75221_f = 54 + i1 * 18;
               }
            }
         }

         this.field_147064_C = new Slot(field_147060_v, 0, 173, 112);
         guicontainercreative$containercreative.field_75151_b.add(this.field_147064_C);
      } else if(i == CreativeTabs.field_78036_m.func_78021_a()) {
         guicontainercreative$containercreative.field_75151_b = this.field_147063_B;
         this.field_147063_B = null;
      }

      if(this.field_147062_A != null) {
         if(p_147050_1_ == CreativeTabs.field_78027_g) {
            this.field_147062_A.func_146189_e(true);
            this.field_147062_A.func_146205_d(false);
            this.field_147062_A.func_146195_b(true);
            this.field_147062_A.func_146180_a("");
            this.func_147053_i();
         } else {
            this.field_147062_A.func_146189_e(false);
            this.field_147062_A.func_146205_d(true);
            this.field_147062_A.func_146195_b(false);
         }
      }

      this.field_147067_x = 0.0F;
      guicontainercreative$containercreative.func_148329_a(0.0F);
   }

   public void func_146274_d() throws IOException {
      super.func_146274_d();
      int i = Mouse.getEventDWheel();
      if(i != 0 && this.func_147055_p()) {
         int j = ((GuiContainerCreative.ContainerCreative)this.field_147002_h).field_148330_a.size() / 9 - 5;
         if(i > 0) {
            i = 1;
         }

         if(i < 0) {
            i = -1;
         }

         this.field_147067_x = (float)((double)this.field_147067_x - (double)i / (double)j);
         this.field_147067_x = MathHelper.func_76131_a(this.field_147067_x, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148329_a(this.field_147067_x);
      }

   }

   public void func_73863_a(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      boolean flag = Mouse.isButtonDown(0);
      int i = this.field_147003_i;
      int j = this.field_147009_r;
      int k = i + 175;
      int l = j + 18;
      int i1 = k + 14;
      int j1 = l + 112;
      if(!this.field_147065_z && flag && p_73863_1_ >= k && p_73863_2_ >= l && p_73863_1_ < i1 && p_73863_2_ < j1) {
         this.field_147066_y = this.func_147055_p();
      }

      if(!flag) {
         this.field_147066_y = false;
      }

      this.field_147065_z = flag;
      if(this.field_147066_y) {
         this.field_147067_x = ((float)(p_73863_2_ - l) - 7.5F) / ((float)(j1 - l) - 15.0F);
         this.field_147067_x = MathHelper.func_76131_a(this.field_147067_x, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.field_147002_h).func_148329_a(this.field_147067_x);
      }

      super.func_73863_a(p_73863_1_, p_73863_2_, p_73863_3_);

      for(CreativeTabs creativetabs : CreativeTabs.field_78032_a) {
         if(this.func_147052_b(creativetabs, p_73863_1_, p_73863_2_)) {
            break;
         }
      }

      if(this.field_147064_C != null && field_147058_w == CreativeTabs.field_78036_m.func_78021_a() && this.func_146978_c(this.field_147064_C.field_75223_e, this.field_147064_C.field_75221_f, 16, 16, p_73863_1_, p_73863_2_)) {
         this.func_146279_a(I18n.func_135052_a("inventory.binSlot", new Object[0]), p_73863_1_, p_73863_2_);
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179140_f();
   }

   protected void func_146285_a(ItemStack p_146285_1_, int p_146285_2_, int p_146285_3_) {
      if(field_147058_w == CreativeTabs.field_78027_g.func_78021_a()) {
         List<String> list = p_146285_1_.func_82840_a(this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
         CreativeTabs creativetabs = p_146285_1_.func_77973_b().func_77640_w();
         if(creativetabs == null && p_146285_1_.func_77973_b() == Items.field_151134_bR) {
            Map<Integer, Integer> map = EnchantmentHelper.func_82781_a(p_146285_1_);
            if(map.size() == 1) {
               Enchantment enchantment = Enchantment.func_180306_c(((Integer)map.keySet().iterator().next()).intValue());

               for(CreativeTabs creativetabs1 : CreativeTabs.field_78032_a) {
                  if(creativetabs1.func_111226_a(enchantment.field_77351_y)) {
                     creativetabs = creativetabs1;
                     break;
                  }
               }
            }
         }

         if(creativetabs != null) {
            list.add(1, "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.func_135052_a(creativetabs.func_78024_c(), new Object[0]));
         }

         for(int i = 0; i < list.size(); ++i) {
            if(i == 0) {
               list.set(i, p_146285_1_.func_77953_t().field_77937_e + (String)list.get(i));
            } else {
               list.set(i, EnumChatFormatting.GRAY + (String)list.get(i));
            }
         }

         this.func_146283_a(list, p_146285_2_, p_146285_3_);
      } else {
         super.func_146285_a(p_146285_1_, p_146285_2_, p_146285_3_);
      }

   }

   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.func_74520_c();
      CreativeTabs creativetabs = CreativeTabs.field_78032_a[field_147058_w];

      for(CreativeTabs creativetabs1 : CreativeTabs.field_78032_a) {
         this.field_146297_k.func_110434_K().func_110577_a(field_147061_u);
         if(creativetabs1.func_78021_a() != field_147058_w) {
            this.func_147051_a(creativetabs1);
         }
      }

      this.field_146297_k.func_110434_K().func_110577_a(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + creativetabs.func_78015_f()));
      this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
      this.field_147062_A.func_146194_f();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      int i = this.field_147003_i + 175;
      int j = this.field_147009_r + 18;
      int k = j + 112;
      this.field_146297_k.func_110434_K().func_110577_a(field_147061_u);
      if(creativetabs.func_78017_i()) {
         this.func_73729_b(i, j + (int)((float)(k - j - 17) * this.field_147067_x), 232 + (this.func_147055_p()?0:12), 0, 12, 15);
      }

      this.func_147051_a(creativetabs);
      if(creativetabs == CreativeTabs.field_78036_m) {
         GuiInventory.func_147046_a(this.field_147003_i + 43, this.field_147009_r + 45, 20, (float)(this.field_147003_i + 43 - p_146976_2_), (float)(this.field_147009_r + 45 - 30 - p_146976_3_), this.field_146297_k.field_71439_g);
      }

   }

   protected boolean func_147049_a(CreativeTabs p_147049_1_, int p_147049_2_, int p_147049_3_) {
      int i = p_147049_1_.func_78020_k();
      int j = 28 * i;
      int k = 0;
      if(i == 5) {
         j = this.field_146999_f - 28 + 2;
      } else if(i > 0) {
         j += i;
      }

      if(p_147049_1_.func_78023_l()) {
         k = k - 32;
      } else {
         k = k + this.field_147000_g;
      }

      return p_147049_2_ >= j && p_147049_2_ <= j + 28 && p_147049_3_ >= k && p_147049_3_ <= k + 32;
   }

   protected boolean func_147052_b(CreativeTabs p_147052_1_, int p_147052_2_, int p_147052_3_) {
      int i = p_147052_1_.func_78020_k();
      int j = 28 * i;
      int k = 0;
      if(i == 5) {
         j = this.field_146999_f - 28 + 2;
      } else if(i > 0) {
         j += i;
      }

      if(p_147052_1_.func_78023_l()) {
         k = k - 32;
      } else {
         k = k + this.field_147000_g;
      }

      if(this.func_146978_c(j + 3, k + 3, 23, 27, p_147052_2_, p_147052_3_)) {
         this.func_146279_a(I18n.func_135052_a(p_147052_1_.func_78024_c(), new Object[0]), p_147052_2_, p_147052_3_);
         return true;
      } else {
         return false;
      }
   }

   protected void func_147051_a(CreativeTabs p_147051_1_) {
      boolean flag = p_147051_1_.func_78021_a() == field_147058_w;
      boolean flag1 = p_147051_1_.func_78023_l();
      int i = p_147051_1_.func_78020_k();
      int j = i * 28;
      int k = 0;
      int l = this.field_147003_i + 28 * i;
      int i1 = this.field_147009_r;
      int j1 = 32;
      if(flag) {
         k += 32;
      }

      if(i == 5) {
         l = this.field_147003_i + this.field_146999_f - 28;
      } else if(i > 0) {
         l += i;
      }

      if(flag1) {
         i1 = i1 - 28;
      } else {
         k += 64;
         i1 = i1 + (this.field_147000_g - 4);
      }

      GlStateManager.func_179140_f();
      this.func_73729_b(l, i1, j, k, 28, j1);
      this.field_73735_i = 100.0F;
      this.field_146296_j.field_77023_b = 100.0F;
      l = l + 6;
      i1 = i1 + 8 + (flag1?1:-1);
      GlStateManager.func_179145_e();
      GlStateManager.func_179091_B();
      ItemStack itemstack = p_147051_1_.func_151244_d();
      this.field_146296_j.func_180450_b(itemstack, l, i1);
      this.field_146296_j.func_175030_a(this.field_146289_q, itemstack, l, i1);
      GlStateManager.func_179140_f();
      this.field_146296_j.field_77023_b = 0.0F;
      this.field_73735_i = 0.0F;
   }

   protected void func_146284_a(GuiButton p_146284_1_) throws IOException {
      if(p_146284_1_.field_146127_k == 0) {
         this.field_146297_k.func_147108_a(new GuiAchievements(this, this.field_146297_k.field_71439_g.func_146107_m()));
      }

      if(p_146284_1_.field_146127_k == 1) {
         this.field_146297_k.func_147108_a(new GuiStats(this, this.field_146297_k.field_71439_g.func_146107_m()));
      }

   }

   public int func_147056_g() {
      return field_147058_w;
   }

   static class ContainerCreative extends Container {
      public List<ItemStack> field_148330_a = Lists.<ItemStack>newArrayList();

      public ContainerCreative(EntityPlayer p_i1086_1_) {
         InventoryPlayer inventoryplayer = p_i1086_1_.field_71071_by;

         for(int i = 0; i < 5; ++i) {
            for(int j = 0; j < 9; ++j) {
               this.func_75146_a(new Slot(GuiContainerCreative.field_147060_v, i * 9 + j, 9 + j * 18, 18 + i * 18));
            }
         }

         for(int k = 0; k < 9; ++k) {
            this.func_75146_a(new Slot(inventoryplayer, k, 9 + k * 18, 112));
         }

         this.func_148329_a(0.0F);
      }

      public boolean func_75145_c(EntityPlayer p_75145_1_) {
         return true;
      }

      public void func_148329_a(float p_148329_1_) {
         int i = (this.field_148330_a.size() + 9 - 1) / 9 - 5;
         int j = (int)((double)(p_148329_1_ * (float)i) + 0.5D);
         if(j < 0) {
            j = 0;
         }

         for(int k = 0; k < 5; ++k) {
            for(int l = 0; l < 9; ++l) {
               int i1 = l + (k + j) * 9;
               if(i1 >= 0 && i1 < this.field_148330_a.size()) {
                  GuiContainerCreative.field_147060_v.func_70299_a(l + k * 9, (ItemStack)this.field_148330_a.get(i1));
               } else {
                  GuiContainerCreative.field_147060_v.func_70299_a(l + k * 9, (ItemStack)null);
               }
            }
         }

      }

      public boolean func_148328_e() {
         return this.field_148330_a.size() > 45;
      }

      protected void func_75133_b(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {
      }

      public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
         if(p_82846_2_ >= this.field_75151_b.size() - 9 && p_82846_2_ < this.field_75151_b.size()) {
            Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
            if(slot != null && slot.func_75216_d()) {
               slot.func_75215_d((ItemStack)null);
            }
         }

         return null;
      }

      public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
         return p_94530_2_.field_75221_f > 90;
      }

      public boolean func_94531_b(Slot p_94531_1_) {
         return p_94531_1_.field_75224_c instanceof InventoryPlayer || p_94531_1_.field_75221_f > 90 && p_94531_1_.field_75223_e <= 162;
      }
   }

   class CreativeSlot extends Slot {
      private final Slot field_148332_b;

      public CreativeSlot(Slot p_i46313_2_, int p_i46313_3_) {
         super(p_i46313_2_.field_75224_c, p_i46313_3_, 0, 0);
         this.field_148332_b = p_i46313_2_;
      }

      public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
         this.field_148332_b.func_82870_a(p_82870_1_, p_82870_2_);
      }

      public boolean func_75214_a(ItemStack p_75214_1_) {
         return this.field_148332_b.func_75214_a(p_75214_1_);
      }

      public ItemStack func_75211_c() {
         return this.field_148332_b.func_75211_c();
      }

      public boolean func_75216_d() {
         return this.field_148332_b.func_75216_d();
      }

      public void func_75215_d(ItemStack p_75215_1_) {
         this.field_148332_b.func_75215_d(p_75215_1_);
      }

      public void func_75218_e() {
         this.field_148332_b.func_75218_e();
      }

      public int func_75219_a() {
         return this.field_148332_b.func_75219_a();
      }

      public int func_178170_b(ItemStack p_178170_1_) {
         return this.field_148332_b.func_178170_b(p_178170_1_);
      }

      public String func_178171_c() {
         return this.field_148332_b.func_178171_c();
      }

      public ItemStack func_75209_a(int p_75209_1_) {
         return this.field_148332_b.func_75209_a(p_75209_1_);
      }

      public boolean func_75217_a(IInventory p_75217_1_, int p_75217_2_) {
         return this.field_148332_b.func_75217_a(p_75217_1_, p_75217_2_);
      }
   }
}
