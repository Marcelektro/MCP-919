package net.minecraft.client.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class EntityPlayerSP extends AbstractClientPlayer {
   public final NetHandlerPlayClient field_71174_a;
   private final StatFileWriter field_146108_bO;
   private double field_175172_bI;
   private double field_175166_bJ;
   private double field_175167_bK;
   private float field_175164_bL;
   private float field_175165_bM;
   private boolean field_175170_bN;
   private boolean field_175171_bO;
   private int field_175168_bP;
   private boolean field_175169_bQ;
   private String field_142022_ce;
   public MovementInput field_71158_b;
   protected Minecraft field_71159_c;
   protected int field_71156_d;
   public int field_71157_e;
   public float field_71154_f;
   public float field_71155_g;
   public float field_71163_h;
   public float field_71164_i;
   private int field_110320_a;
   private float field_110321_bQ;
   public float field_71086_bY;
   public float field_71080_cy;

   public EntityPlayerSP(Minecraft p_i46278_1_, World p_i46278_2_, NetHandlerPlayClient p_i46278_3_, StatFileWriter p_i46278_4_) {
      super(p_i46278_2_, p_i46278_3_.func_175105_e());
      this.field_71174_a = p_i46278_3_;
      this.field_146108_bO = p_i46278_4_;
      this.field_71159_c = p_i46278_1_;
      this.field_71093_bK = 0;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      return false;
   }

   public void func_70691_i(float p_70691_1_) {
   }

   public void func_70078_a(Entity p_70078_1_) {
      super.func_70078_a(p_70078_1_);
      if(p_70078_1_ instanceof EntityMinecart) {
         this.field_71159_c.func_147118_V().func_147682_a(new MovingSoundMinecartRiding(this, (EntityMinecart)p_70078_1_));
      }

   }

   public void func_70071_h_() {
      if(this.field_70170_p.func_175667_e(new BlockPos(this.field_70165_t, 0.0D, this.field_70161_v))) {
         super.func_70071_h_();
         if(this.func_70115_ae()) {
            this.field_71174_a.func_147297_a(new C03PacketPlayer.C05PacketPlayerLook(this.field_70177_z, this.field_70125_A, this.field_70122_E));
            this.field_71174_a.func_147297_a(new C0CPacketInput(this.field_70702_br, this.field_70701_bs, this.field_71158_b.field_78901_c, this.field_71158_b.field_78899_d));
         } else {
            this.func_175161_p();
         }

      }
   }

   public void func_175161_p() {
      boolean flag = this.func_70051_ag();
      if(flag != this.field_175171_bO) {
         if(flag) {
            this.field_71174_a.func_147297_a(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.START_SPRINTING));
         } else {
            this.field_71174_a.func_147297_a(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.STOP_SPRINTING));
         }

         this.field_175171_bO = flag;
      }

      boolean flag1 = this.func_70093_af();
      if(flag1 != this.field_175170_bN) {
         if(flag1) {
            this.field_71174_a.func_147297_a(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.START_SNEAKING));
         } else {
            this.field_71174_a.func_147297_a(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.STOP_SNEAKING));
         }

         this.field_175170_bN = flag1;
      }

      if(this.func_175160_A()) {
         double d0 = this.field_70165_t - this.field_175172_bI;
         double d1 = this.func_174813_aQ().field_72338_b - this.field_175166_bJ;
         double d2 = this.field_70161_v - this.field_175167_bK;
         double d3 = (double)(this.field_70177_z - this.field_175164_bL);
         double d4 = (double)(this.field_70125_A - this.field_175165_bM);
         boolean flag2 = d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D || this.field_175168_bP >= 20;
         boolean flag3 = d3 != 0.0D || d4 != 0.0D;
         if(this.field_70154_o == null) {
            if(flag2 && flag3) {
               this.field_71174_a.func_147297_a(new C03PacketPlayer.C06PacketPlayerPosLook(this.field_70165_t, this.func_174813_aQ().field_72338_b, this.field_70161_v, this.field_70177_z, this.field_70125_A, this.field_70122_E));
            } else if(flag2) {
               this.field_71174_a.func_147297_a(new C03PacketPlayer.C04PacketPlayerPosition(this.field_70165_t, this.func_174813_aQ().field_72338_b, this.field_70161_v, this.field_70122_E));
            } else if(flag3) {
               this.field_71174_a.func_147297_a(new C03PacketPlayer.C05PacketPlayerLook(this.field_70177_z, this.field_70125_A, this.field_70122_E));
            } else {
               this.field_71174_a.func_147297_a(new C03PacketPlayer(this.field_70122_E));
            }
         } else {
            this.field_71174_a.func_147297_a(new C03PacketPlayer.C06PacketPlayerPosLook(this.field_70159_w, -999.0D, this.field_70179_y, this.field_70177_z, this.field_70125_A, this.field_70122_E));
            flag2 = false;
         }

         ++this.field_175168_bP;
         if(flag2) {
            this.field_175172_bI = this.field_70165_t;
            this.field_175166_bJ = this.func_174813_aQ().field_72338_b;
            this.field_175167_bK = this.field_70161_v;
            this.field_175168_bP = 0;
         }

         if(flag3) {
            this.field_175164_bL = this.field_70177_z;
            this.field_175165_bM = this.field_70125_A;
         }
      }

   }

   public EntityItem func_71040_bB(boolean p_71040_1_) {
      C07PacketPlayerDigging.Action c07packetplayerdigging$action = p_71040_1_?C07PacketPlayerDigging.Action.DROP_ALL_ITEMS:C07PacketPlayerDigging.Action.DROP_ITEM;
      this.field_71174_a.func_147297_a(new C07PacketPlayerDigging(c07packetplayerdigging$action, BlockPos.field_177992_a, EnumFacing.DOWN));
      return null;
   }

   protected void func_71012_a(EntityItem p_71012_1_) {
   }

   public void func_71165_d(String p_71165_1_) {
      this.field_71174_a.func_147297_a(new C01PacketChatMessage(p_71165_1_));
   }

   public void func_71038_i() {
      super.func_71038_i();
      this.field_71174_a.func_147297_a(new C0APacketAnimation());
   }

   public void func_71004_bE() {
      this.field_71174_a.func_147297_a(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
   }

   protected void func_70665_d(DamageSource p_70665_1_, float p_70665_2_) {
      if(!this.func_180431_b(p_70665_1_)) {
         this.func_70606_j(this.func_110143_aJ() - p_70665_2_);
      }
   }

   public void func_71053_j() {
      this.field_71174_a.func_147297_a(new C0DPacketCloseWindow(this.field_71070_bA.field_75152_c));
      this.func_175159_q();
   }

   public void func_175159_q() {
      this.field_71071_by.func_70437_b((ItemStack)null);
      super.func_71053_j();
      this.field_71159_c.func_147108_a((GuiScreen)null);
   }

   public void func_71150_b(float p_71150_1_) {
      if(this.field_175169_bQ) {
         float f = this.func_110143_aJ() - p_71150_1_;
         if(f <= 0.0F) {
            this.func_70606_j(p_71150_1_);
            if(f < 0.0F) {
               this.field_70172_ad = this.field_70771_an / 2;
            }
         } else {
            this.field_110153_bc = f;
            this.func_70606_j(this.func_110143_aJ());
            this.field_70172_ad = this.field_70771_an;
            this.func_70665_d(DamageSource.field_76377_j, f);
            this.field_70737_aN = this.field_70738_aO = 10;
         }
      } else {
         this.func_70606_j(p_71150_1_);
         this.field_175169_bQ = true;
      }

   }

   public void func_71064_a(StatBase p_71064_1_, int p_71064_2_) {
      if(p_71064_1_ != null) {
         if(p_71064_1_.field_75972_f) {
            super.func_71064_a(p_71064_1_, p_71064_2_);
         }

      }
   }

   public void func_71016_p() {
      this.field_71174_a.func_147297_a(new C13PacketPlayerAbilities(this.field_71075_bZ));
   }

   public boolean func_175144_cb() {
      return true;
   }

   protected void func_110318_g() {
      this.field_71174_a.func_147297_a(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.RIDING_JUMP, (int)(this.func_110319_bJ() * 100.0F)));
   }

   public void func_175163_u() {
      this.field_71174_a.func_147297_a(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.OPEN_INVENTORY));
   }

   public void func_175158_f(String p_175158_1_) {
      this.field_142022_ce = p_175158_1_;
   }

   public String func_142021_k() {
      return this.field_142022_ce;
   }

   public StatFileWriter func_146107_m() {
      return this.field_146108_bO;
   }

   public void func_146105_b(IChatComponent p_146105_1_) {
      this.field_71159_c.field_71456_v.func_146158_b().func_146227_a(p_146105_1_);
   }

   protected boolean func_145771_j(double p_145771_1_, double p_145771_3_, double p_145771_5_) {
      if(this.field_70145_X) {
         return false;
      } else {
         BlockPos blockpos = new BlockPos(p_145771_1_, p_145771_3_, p_145771_5_);
         double d0 = p_145771_1_ - (double)blockpos.func_177958_n();
         double d1 = p_145771_5_ - (double)blockpos.func_177952_p();
         if(!this.func_175162_d(blockpos)) {
            int i = -1;
            double d2 = 9999.0D;
            if(this.func_175162_d(blockpos.func_177976_e()) && d0 < d2) {
               d2 = d0;
               i = 0;
            }

            if(this.func_175162_d(blockpos.func_177974_f()) && 1.0D - d0 < d2) {
               d2 = 1.0D - d0;
               i = 1;
            }

            if(this.func_175162_d(blockpos.func_177978_c()) && d1 < d2) {
               d2 = d1;
               i = 4;
            }

            if(this.func_175162_d(blockpos.func_177968_d()) && 1.0D - d1 < d2) {
               d2 = 1.0D - d1;
               i = 5;
            }

            float f = 0.1F;
            if(i == 0) {
               this.field_70159_w = (double)(-f);
            }

            if(i == 1) {
               this.field_70159_w = (double)f;
            }

            if(i == 4) {
               this.field_70179_y = (double)(-f);
            }

            if(i == 5) {
               this.field_70179_y = (double)f;
            }
         }

         return false;
      }
   }

   private boolean func_175162_d(BlockPos p_175162_1_) {
      return !this.field_70170_p.func_180495_p(p_175162_1_).func_177230_c().func_149721_r() && !this.field_70170_p.func_180495_p(p_175162_1_.func_177984_a()).func_177230_c().func_149721_r();
   }

   public void func_70031_b(boolean p_70031_1_) {
      super.func_70031_b(p_70031_1_);
      this.field_71157_e = p_70031_1_?600:0;
   }

   public void func_71152_a(float p_71152_1_, int p_71152_2_, int p_71152_3_) {
      this.field_71106_cc = p_71152_1_;
      this.field_71067_cb = p_71152_2_;
      this.field_71068_ca = p_71152_3_;
   }

   public void func_145747_a(IChatComponent p_145747_1_) {
      this.field_71159_c.field_71456_v.func_146158_b().func_146227_a(p_145747_1_);
   }

   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
      return p_70003_1_ <= 0;
   }

   public BlockPos func_180425_c() {
      return new BlockPos(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D);
   }

   public void func_85030_a(String p_85030_1_, float p_85030_2_, float p_85030_3_) {
      this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, p_85030_1_, p_85030_2_, p_85030_3_, false);
   }

   public boolean func_70613_aW() {
      return true;
   }

   public boolean func_110317_t() {
      return this.field_70154_o != null && this.field_70154_o instanceof EntityHorse && ((EntityHorse)this.field_70154_o).func_110257_ck();
   }

   public float func_110319_bJ() {
      return this.field_110321_bQ;
   }

   public void func_175141_a(TileEntitySign p_175141_1_) {
      this.field_71159_c.func_147108_a(new GuiEditSign(p_175141_1_));
   }

   public void func_146095_a(CommandBlockLogic p_146095_1_) {
      this.field_71159_c.func_147108_a(new GuiCommandBlock(p_146095_1_));
   }

   public void func_71048_c(ItemStack p_71048_1_) {
      Item item = p_71048_1_.func_77973_b();
      if(item == Items.field_151099_bA) {
         this.field_71159_c.func_147108_a(new GuiScreenBook(this, p_71048_1_, true));
      }

   }

   public void func_71007_a(IInventory p_71007_1_) {
      String s = p_71007_1_ instanceof IInteractionObject?((IInteractionObject)p_71007_1_).func_174875_k():"minecraft:container";
      if("minecraft:chest".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiChest(this.field_71071_by, p_71007_1_));
      } else if("minecraft:hopper".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiHopper(this.field_71071_by, p_71007_1_));
      } else if("minecraft:furnace".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiFurnace(this.field_71071_by, p_71007_1_));
      } else if("minecraft:brewing_stand".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiBrewingStand(this.field_71071_by, p_71007_1_));
      } else if("minecraft:beacon".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiBeacon(this.field_71071_by, p_71007_1_));
      } else if(!"minecraft:dispenser".equals(s) && !"minecraft:dropper".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiChest(this.field_71071_by, p_71007_1_));
      } else {
         this.field_71159_c.func_147108_a(new GuiDispenser(this.field_71071_by, p_71007_1_));
      }

   }

   public void func_110298_a(EntityHorse p_110298_1_, IInventory p_110298_2_) {
      this.field_71159_c.func_147108_a(new GuiScreenHorseInventory(this.field_71071_by, p_110298_2_, p_110298_1_));
   }

   public void func_180468_a(IInteractionObject p_180468_1_) {
      String s = p_180468_1_.func_174875_k();
      if("minecraft:crafting_table".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiCrafting(this.field_71071_by, this.field_70170_p));
      } else if("minecraft:enchanting_table".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiEnchantment(this.field_71071_by, this.field_70170_p, p_180468_1_));
      } else if("minecraft:anvil".equals(s)) {
         this.field_71159_c.func_147108_a(new GuiRepair(this.field_71071_by, this.field_70170_p));
      }

   }

   public void func_180472_a(IMerchant p_180472_1_) {
      this.field_71159_c.func_147108_a(new GuiMerchant(this.field_71071_by, p_180472_1_, this.field_70170_p));
   }

   public void func_71009_b(Entity p_71009_1_) {
      this.field_71159_c.field_71452_i.func_178926_a(p_71009_1_, EnumParticleTypes.CRIT);
   }

   public void func_71047_c(Entity p_71047_1_) {
      this.field_71159_c.field_71452_i.func_178926_a(p_71047_1_, EnumParticleTypes.CRIT_MAGIC);
   }

   public boolean func_70093_af() {
      boolean flag = this.field_71158_b != null?this.field_71158_b.field_78899_d:false;
      return flag && !this.field_71083_bS;
   }

   public void func_70626_be() {
      super.func_70626_be();
      if(this.func_175160_A()) {
         this.field_70702_br = this.field_71158_b.field_78902_a;
         this.field_70701_bs = this.field_71158_b.field_78900_b;
         this.field_70703_bu = this.field_71158_b.field_78901_c;
         this.field_71163_h = this.field_71154_f;
         this.field_71164_i = this.field_71155_g;
         this.field_71155_g = (float)((double)this.field_71155_g + (double)(this.field_70125_A - this.field_71155_g) * 0.5D);
         this.field_71154_f = (float)((double)this.field_71154_f + (double)(this.field_70177_z - this.field_71154_f) * 0.5D);
      }

   }

   protected boolean func_175160_A() {
      return this.field_71159_c.func_175606_aa() == this;
   }

   public void func_70636_d() {
      if(this.field_71157_e > 0) {
         --this.field_71157_e;
         if(this.field_71157_e == 0) {
            this.func_70031_b(false);
         }
      }

      if(this.field_71156_d > 0) {
         --this.field_71156_d;
      }

      this.field_71080_cy = this.field_71086_bY;
      if(this.field_71087_bX) {
         if(this.field_71159_c.field_71462_r != null && !this.field_71159_c.field_71462_r.func_73868_f()) {
            this.field_71159_c.func_147108_a((GuiScreen)null);
         }

         if(this.field_71086_bY == 0.0F) {
            this.field_71159_c.func_147118_V().func_147682_a(PositionedSoundRecord.func_147674_a(new ResourceLocation("portal.trigger"), this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
         }

         this.field_71086_bY += 0.0125F;
         if(this.field_71086_bY >= 1.0F) {
            this.field_71086_bY = 1.0F;
         }

         this.field_71087_bX = false;
      } else if(this.func_70644_a(Potion.field_76431_k) && this.func_70660_b(Potion.field_76431_k).func_76459_b() > 60) {
         this.field_71086_bY += 0.006666667F;
         if(this.field_71086_bY > 1.0F) {
            this.field_71086_bY = 1.0F;
         }
      } else {
         if(this.field_71086_bY > 0.0F) {
            this.field_71086_bY -= 0.05F;
         }

         if(this.field_71086_bY < 0.0F) {
            this.field_71086_bY = 0.0F;
         }
      }

      if(this.field_71088_bW > 0) {
         --this.field_71088_bW;
      }

      boolean flag = this.field_71158_b.field_78901_c;
      boolean flag1 = this.field_71158_b.field_78899_d;
      float f = 0.8F;
      boolean flag2 = this.field_71158_b.field_78900_b >= f;
      this.field_71158_b.func_78898_a();
      if(this.func_71039_bw() && !this.func_70115_ae()) {
         this.field_71158_b.field_78902_a *= 0.2F;
         this.field_71158_b.field_78900_b *= 0.2F;
         this.field_71156_d = 0;
      }

      this.func_145771_j(this.field_70165_t - (double)this.field_70130_N * 0.35D, this.func_174813_aQ().field_72338_b + 0.5D, this.field_70161_v + (double)this.field_70130_N * 0.35D);
      this.func_145771_j(this.field_70165_t - (double)this.field_70130_N * 0.35D, this.func_174813_aQ().field_72338_b + 0.5D, this.field_70161_v - (double)this.field_70130_N * 0.35D);
      this.func_145771_j(this.field_70165_t + (double)this.field_70130_N * 0.35D, this.func_174813_aQ().field_72338_b + 0.5D, this.field_70161_v - (double)this.field_70130_N * 0.35D);
      this.func_145771_j(this.field_70165_t + (double)this.field_70130_N * 0.35D, this.func_174813_aQ().field_72338_b + 0.5D, this.field_70161_v + (double)this.field_70130_N * 0.35D);
      boolean flag3 = (float)this.func_71024_bL().func_75116_a() > 6.0F || this.field_71075_bZ.field_75101_c;
      if(this.field_70122_E && !flag1 && !flag2 && this.field_71158_b.field_78900_b >= f && !this.func_70051_ag() && flag3 && !this.func_71039_bw() && !this.func_70644_a(Potion.field_76440_q)) {
         if(this.field_71156_d <= 0 && !this.field_71159_c.field_71474_y.field_151444_V.func_151470_d()) {
            this.field_71156_d = 7;
         } else {
            this.func_70031_b(true);
         }
      }

      if(!this.func_70051_ag() && this.field_71158_b.field_78900_b >= f && flag3 && !this.func_71039_bw() && !this.func_70644_a(Potion.field_76440_q) && this.field_71159_c.field_71474_y.field_151444_V.func_151470_d()) {
         this.func_70031_b(true);
      }

      if(this.func_70051_ag() && (this.field_71158_b.field_78900_b < f || this.field_70123_F || !flag3)) {
         this.func_70031_b(false);
      }

      if(this.field_71075_bZ.field_75101_c) {
         if(this.field_71159_c.field_71442_b.func_178887_k()) {
            if(!this.field_71075_bZ.field_75100_b) {
               this.field_71075_bZ.field_75100_b = true;
               this.func_71016_p();
            }
         } else if(!flag && this.field_71158_b.field_78901_c) {
            if(this.field_71101_bC == 0) {
               this.field_71101_bC = 7;
            } else {
               this.field_71075_bZ.field_75100_b = !this.field_71075_bZ.field_75100_b;
               this.func_71016_p();
               this.field_71101_bC = 0;
            }
         }
      }

      if(this.field_71075_bZ.field_75100_b && this.func_175160_A()) {
         if(this.field_71158_b.field_78899_d) {
            this.field_70181_x -= (double)(this.field_71075_bZ.func_75093_a() * 3.0F);
         }

         if(this.field_71158_b.field_78901_c) {
            this.field_70181_x += (double)(this.field_71075_bZ.func_75093_a() * 3.0F);
         }
      }

      if(this.func_110317_t()) {
         if(this.field_110320_a < 0) {
            ++this.field_110320_a;
            if(this.field_110320_a == 0) {
               this.field_110321_bQ = 0.0F;
            }
         }

         if(flag && !this.field_71158_b.field_78901_c) {
            this.field_110320_a = -10;
            this.func_110318_g();
         } else if(!flag && this.field_71158_b.field_78901_c) {
            this.field_110320_a = 0;
            this.field_110321_bQ = 0.0F;
         } else if(flag) {
            ++this.field_110320_a;
            if(this.field_110320_a < 10) {
               this.field_110321_bQ = (float)this.field_110320_a * 0.1F;
            } else {
               this.field_110321_bQ = 0.8F + 2.0F / (float)(this.field_110320_a - 9) * 0.1F;
            }
         }
      } else {
         this.field_110321_bQ = 0.0F;
      }

      super.func_70636_d();
      if(this.field_70122_E && this.field_71075_bZ.field_75100_b && !this.field_71159_c.field_71442_b.func_178887_k()) {
         this.field_71075_bZ.field_75100_b = false;
         this.func_71016_p();
      }

   }
}
