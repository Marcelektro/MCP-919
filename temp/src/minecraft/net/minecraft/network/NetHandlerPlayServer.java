package net.minecraft.network;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.material.Material;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListBansEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayServer implements INetHandlerPlayServer, ITickable {
   private static final Logger field_147370_c = LogManager.getLogger();
   public final NetworkManager field_147371_a;
   private final MinecraftServer field_147367_d;
   public EntityPlayerMP field_147369_b;
   private int field_147368_e;
   private int field_175090_f;
   private int field_147365_f;
   private boolean field_147366_g;
   private int field_147378_h;
   private long field_147379_i;
   private long field_147377_k;
   private int field_147374_l;
   private int field_147375_m;
   private IntHashMap<Short> field_147372_n = new IntHashMap();
   private double field_147373_o;
   private double field_147382_p;
   private double field_147381_q;
   private boolean field_147380_r = true;

   public NetHandlerPlayServer(MinecraftServer p_i1530_1_, NetworkManager p_i1530_2_, EntityPlayerMP p_i1530_3_) {
      this.field_147367_d = p_i1530_1_;
      this.field_147371_a = p_i1530_2_;
      p_i1530_2_.func_150719_a(this);
      this.field_147369_b = p_i1530_3_;
      p_i1530_3_.field_71135_a = this;
   }

   public void func_73660_a() {
      this.field_147366_g = false;
      ++this.field_147368_e;
      this.field_147367_d.field_71304_b.func_76320_a("keepAlive");
      if((long)this.field_147368_e - this.field_147377_k > 40L) {
         this.field_147377_k = (long)this.field_147368_e;
         this.field_147379_i = this.func_147363_d();
         this.field_147378_h = (int)this.field_147379_i;
         this.func_147359_a(new S00PacketKeepAlive(this.field_147378_h));
      }

      this.field_147367_d.field_71304_b.func_76319_b();
      if(this.field_147374_l > 0) {
         --this.field_147374_l;
      }

      if(this.field_147375_m > 0) {
         --this.field_147375_m;
      }

      if(this.field_147369_b.func_154331_x() > 0L && this.field_147367_d.func_143007_ar() > 0 && MinecraftServer.func_130071_aq() - this.field_147369_b.func_154331_x() > (long)(this.field_147367_d.func_143007_ar() * 1000 * 60)) {
         this.func_147360_c("You have been idle for too long!");
      }

   }

   public NetworkManager func_147362_b() {
      return this.field_147371_a;
   }

   public void func_147360_c(String p_147360_1_) {
      final ChatComponentText chatcomponenttext = new ChatComponentText(p_147360_1_);
      this.field_147371_a.func_179288_a(new S40PacketDisconnect(chatcomponenttext), new GenericFutureListener<Future<? super Void>>() {
         public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
            NetHandlerPlayServer.this.field_147371_a.func_150718_a(chatcomponenttext);
         }
      }, new GenericFutureListener[0]);
      this.field_147371_a.func_150721_g();
      Futures.getUnchecked(this.field_147367_d.func_152344_a(new Runnable() {
         public void run() {
            NetHandlerPlayServer.this.field_147371_a.func_179293_l();
         }
      }));
   }

   public void func_147358_a(C0CPacketInput p_147358_1_) {
      PacketThreadUtil.func_180031_a(p_147358_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_110430_a(p_147358_1_.func_149620_c(), p_147358_1_.func_149616_d(), p_147358_1_.func_149618_e(), p_147358_1_.func_149617_f());
   }

   private boolean func_183006_b(C03PacketPlayer p_183006_1_) {
      return !Doubles.isFinite(p_183006_1_.func_149464_c()) || !Doubles.isFinite(p_183006_1_.func_149467_d()) || !Doubles.isFinite(p_183006_1_.func_149472_e()) || !Floats.isFinite(p_183006_1_.func_149470_h()) || !Floats.isFinite(p_183006_1_.func_149462_g());
   }

   public void func_147347_a(C03PacketPlayer p_147347_1_) {
      PacketThreadUtil.func_180031_a(p_147347_1_, this, this.field_147369_b.func_71121_q());
      if(this.func_183006_b(p_147347_1_)) {
         this.func_147360_c("Invalid move packet received");
      } else {
         WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
         this.field_147366_g = true;
         if(!this.field_147369_b.field_71136_j) {
            double d0 = this.field_147369_b.field_70165_t;
            double d1 = this.field_147369_b.field_70163_u;
            double d2 = this.field_147369_b.field_70161_v;
            double d3 = 0.0D;
            double d4 = p_147347_1_.func_149464_c() - this.field_147373_o;
            double d5 = p_147347_1_.func_149467_d() - this.field_147382_p;
            double d6 = p_147347_1_.func_149472_e() - this.field_147381_q;
            if(p_147347_1_.func_149466_j()) {
               d3 = d4 * d4 + d5 * d5 + d6 * d6;
               if(!this.field_147380_r && d3 < 0.25D) {
                  this.field_147380_r = true;
               }
            }

            if(this.field_147380_r) {
               this.field_175090_f = this.field_147368_e;
               if(this.field_147369_b.field_70154_o != null) {
                  float f4 = this.field_147369_b.field_70177_z;
                  float f = this.field_147369_b.field_70125_A;
                  this.field_147369_b.field_70154_o.func_70043_V();
                  double d16 = this.field_147369_b.field_70165_t;
                  double d17 = this.field_147369_b.field_70163_u;
                  double d18 = this.field_147369_b.field_70161_v;
                  if(p_147347_1_.func_149463_k()) {
                     f4 = p_147347_1_.func_149462_g();
                     f = p_147347_1_.func_149470_h();
                  }

                  this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
                  this.field_147369_b.func_71127_g();
                  this.field_147369_b.func_70080_a(d16, d17, d18, f4, f);
                  if(this.field_147369_b.field_70154_o != null) {
                     this.field_147369_b.field_70154_o.func_70043_V();
                  }

                  this.field_147367_d.func_71203_ab().func_72358_d(this.field_147369_b);
                  if(this.field_147369_b.field_70154_o != null) {
                     if(d3 > 4.0D) {
                        Entity entity = this.field_147369_b.field_70154_o;
                        this.field_147369_b.field_71135_a.func_147359_a(new S18PacketEntityTeleport(entity));
                        this.func_147364_a(this.field_147369_b.field_70165_t, this.field_147369_b.field_70163_u, this.field_147369_b.field_70161_v, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                     }

                     this.field_147369_b.field_70154_o.field_70160_al = true;
                  }

                  if(this.field_147380_r) {
                     this.field_147373_o = this.field_147369_b.field_70165_t;
                     this.field_147382_p = this.field_147369_b.field_70163_u;
                     this.field_147381_q = this.field_147369_b.field_70161_v;
                  }

                  worldserver.func_72870_g(this.field_147369_b);
                  return;
               }

               if(this.field_147369_b.func_70608_bn()) {
                  this.field_147369_b.func_71127_g();
                  this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                  worldserver.func_72870_g(this.field_147369_b);
                  return;
               }

               double d7 = this.field_147369_b.field_70163_u;
               this.field_147373_o = this.field_147369_b.field_70165_t;
               this.field_147382_p = this.field_147369_b.field_70163_u;
               this.field_147381_q = this.field_147369_b.field_70161_v;
               double d8 = this.field_147369_b.field_70165_t;
               double d9 = this.field_147369_b.field_70163_u;
               double d10 = this.field_147369_b.field_70161_v;
               float f1 = this.field_147369_b.field_70177_z;
               float f2 = this.field_147369_b.field_70125_A;
               if(p_147347_1_.func_149466_j() && p_147347_1_.func_149467_d() == -999.0D) {
                  p_147347_1_.func_149469_a(false);
               }

               if(p_147347_1_.func_149466_j()) {
                  d8 = p_147347_1_.func_149464_c();
                  d9 = p_147347_1_.func_149467_d();
                  d10 = p_147347_1_.func_149472_e();
                  if(Math.abs(p_147347_1_.func_149464_c()) > 3.0E7D || Math.abs(p_147347_1_.func_149472_e()) > 3.0E7D) {
                     this.func_147360_c("Illegal position");
                     return;
                  }
               }

               if(p_147347_1_.func_149463_k()) {
                  f1 = p_147347_1_.func_149462_g();
                  f2 = p_147347_1_.func_149470_h();
               }

               this.field_147369_b.func_71127_g();
               this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, f1, f2);
               if(!this.field_147380_r) {
                  return;
               }

               double d11 = d8 - this.field_147369_b.field_70165_t;
               double d12 = d9 - this.field_147369_b.field_70163_u;
               double d13 = d10 - this.field_147369_b.field_70161_v;
               double d14 = this.field_147369_b.field_70159_w * this.field_147369_b.field_70159_w + this.field_147369_b.field_70181_x * this.field_147369_b.field_70181_x + this.field_147369_b.field_70179_y * this.field_147369_b.field_70179_y;
               double d15 = d11 * d11 + d12 * d12 + d13 * d13;
               if(d15 - d14 > 100.0D && (!this.field_147367_d.func_71264_H() || !this.field_147367_d.func_71214_G().equals(this.field_147369_b.func_70005_c_()))) {
                  field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved too quickly! " + d11 + "," + d12 + "," + d13 + " (" + d11 + ", " + d12 + ", " + d13 + ")");
                  this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                  return;
               }

               float f3 = 0.0625F;
               boolean flag = worldserver.func_72945_a(this.field_147369_b, this.field_147369_b.func_174813_aQ().func_72331_e((double)f3, (double)f3, (double)f3)).isEmpty();
               if(this.field_147369_b.field_70122_E && !p_147347_1_.func_149465_i() && d12 > 0.0D) {
                  this.field_147369_b.func_70664_aZ();
               }

               this.field_147369_b.func_70091_d(d11, d12, d13);
               this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
               d11 = d8 - this.field_147369_b.field_70165_t;
               d12 = d9 - this.field_147369_b.field_70163_u;
               if(d12 > -0.5D || d12 < 0.5D) {
                  d12 = 0.0D;
               }

               d13 = d10 - this.field_147369_b.field_70161_v;
               d15 = d11 * d11 + d12 * d12 + d13 * d13;
               boolean flag1 = false;
               if(d15 > 0.0625D && !this.field_147369_b.func_70608_bn() && !this.field_147369_b.field_71134_c.func_73083_d()) {
                  flag1 = true;
                  field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved wrongly!");
               }

               this.field_147369_b.func_70080_a(d8, d9, d10, f1, f2);
               this.field_147369_b.func_71000_j(this.field_147369_b.field_70165_t - d0, this.field_147369_b.field_70163_u - d1, this.field_147369_b.field_70161_v - d2);
               if(!this.field_147369_b.field_70145_X) {
                  boolean flag2 = worldserver.func_72945_a(this.field_147369_b, this.field_147369_b.func_174813_aQ().func_72331_e((double)f3, (double)f3, (double)f3)).isEmpty();
                  if(flag && (flag1 || !flag2) && !this.field_147369_b.func_70608_bn()) {
                     this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, f1, f2);
                     return;
                  }
               }

               AxisAlignedBB axisalignedbb = this.field_147369_b.func_174813_aQ().func_72314_b((double)f3, (double)f3, (double)f3).func_72321_a(0.0D, -0.55D, 0.0D);
               if(!this.field_147367_d.func_71231_X() && !this.field_147369_b.field_71075_bZ.field_75101_c && !worldserver.func_72829_c(axisalignedbb)) {
                  if(d12 >= -0.03125D) {
                     ++this.field_147365_f;
                     if(this.field_147365_f > 80) {
                        field_147370_c.warn(this.field_147369_b.func_70005_c_() + " was kicked for floating too long!");
                        this.func_147360_c("Flying is not enabled on this server");
                        return;
                     }
                  }
               } else {
                  this.field_147365_f = 0;
               }

               this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
               this.field_147367_d.func_71203_ab().func_72358_d(this.field_147369_b);
               this.field_147369_b.func_71122_b(this.field_147369_b.field_70163_u - d7, p_147347_1_.func_149465_i());
            } else if(this.field_147368_e - this.field_175090_f > 20) {
               this.func_147364_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
            }

         }
      }
   }

   public void func_147364_a(double p_147364_1_, double p_147364_3_, double p_147364_5_, float p_147364_7_, float p_147364_8_) {
      this.func_175089_a(p_147364_1_, p_147364_3_, p_147364_5_, p_147364_7_, p_147364_8_, Collections.<S08PacketPlayerPosLook.EnumFlags>emptySet());
   }

   public void func_175089_a(double p_175089_1_, double p_175089_3_, double p_175089_5_, float p_175089_7_, float p_175089_8_, Set<S08PacketPlayerPosLook.EnumFlags> p_175089_9_) {
      this.field_147380_r = false;
      this.field_147373_o = p_175089_1_;
      this.field_147382_p = p_175089_3_;
      this.field_147381_q = p_175089_5_;
      if(p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.X)) {
         this.field_147373_o += this.field_147369_b.field_70165_t;
      }

      if(p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
         this.field_147382_p += this.field_147369_b.field_70163_u;
      }

      if(p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
         this.field_147381_q += this.field_147369_b.field_70161_v;
      }

      float f = p_175089_7_;
      float f1 = p_175089_8_;
      if(p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
         f = p_175089_7_ + this.field_147369_b.field_70177_z;
      }

      if(p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.X_ROT)) {
         f1 = p_175089_8_ + this.field_147369_b.field_70125_A;
      }

      this.field_147369_b.func_70080_a(this.field_147373_o, this.field_147382_p, this.field_147381_q, f, f1);
      this.field_147369_b.field_71135_a.func_147359_a(new S08PacketPlayerPosLook(p_175089_1_, p_175089_3_, p_175089_5_, p_175089_7_, p_175089_8_, p_175089_9_));
   }

   public void func_147345_a(C07PacketPlayerDigging p_147345_1_) {
      PacketThreadUtil.func_180031_a(p_147345_1_, this, this.field_147369_b.func_71121_q());
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      BlockPos blockpos = p_147345_1_.func_179715_a();
      this.field_147369_b.func_143004_u();
      switch(p_147345_1_.func_180762_c()) {
      case DROP_ITEM:
         if(!this.field_147369_b.func_175149_v()) {
            this.field_147369_b.func_71040_bB(false);
         }

         return;
      case DROP_ALL_ITEMS:
         if(!this.field_147369_b.func_175149_v()) {
            this.field_147369_b.func_71040_bB(true);
         }

         return;
      case RELEASE_USE_ITEM:
         this.field_147369_b.func_71034_by();
         return;
      case START_DESTROY_BLOCK:
      case ABORT_DESTROY_BLOCK:
      case STOP_DESTROY_BLOCK:
         double d0 = this.field_147369_b.field_70165_t - ((double)blockpos.func_177958_n() + 0.5D);
         double d1 = this.field_147369_b.field_70163_u - ((double)blockpos.func_177956_o() + 0.5D) + 1.5D;
         double d2 = this.field_147369_b.field_70161_v - ((double)blockpos.func_177952_p() + 0.5D);
         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
         if(d3 > 36.0D) {
            return;
         } else if(blockpos.func_177956_o() >= this.field_147367_d.func_71207_Z()) {
            return;
         } else {
            if(p_147345_1_.func_180762_c() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
               if(!this.field_147367_d.func_175579_a(worldserver, blockpos, this.field_147369_b) && worldserver.func_175723_af().func_177746_a(blockpos)) {
                  this.field_147369_b.field_71134_c.func_180784_a(blockpos, p_147345_1_.func_179714_b());
               } else {
                  this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(worldserver, blockpos));
               }
            } else {
               if(p_147345_1_.func_180762_c() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                  this.field_147369_b.field_71134_c.func_180785_a(blockpos);
               } else if(p_147345_1_.func_180762_c() == C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK) {
                  this.field_147369_b.field_71134_c.func_180238_e();
               }

               if(worldserver.func_180495_p(blockpos).func_177230_c().func_149688_o() != Material.field_151579_a) {
                  this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(worldserver, blockpos));
               }
            }

            return;
         }
      default:
         throw new IllegalArgumentException("Invalid player action");
      }
   }

   public void func_147346_a(C08PacketPlayerBlockPlacement p_147346_1_) {
      PacketThreadUtil.func_180031_a(p_147346_1_, this, this.field_147369_b.func_71121_q());
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      ItemStack itemstack = this.field_147369_b.field_71071_by.func_70448_g();
      boolean flag = false;
      BlockPos blockpos = p_147346_1_.func_179724_a();
      EnumFacing enumfacing = EnumFacing.func_82600_a(p_147346_1_.func_149568_f());
      this.field_147369_b.func_143004_u();
      if(p_147346_1_.func_149568_f() == 255) {
         if(itemstack == null) {
            return;
         }

         this.field_147369_b.field_71134_c.func_73085_a(this.field_147369_b, worldserver, itemstack);
      } else if(blockpos.func_177956_o() < this.field_147367_d.func_71207_Z() - 1 || enumfacing != EnumFacing.UP && blockpos.func_177956_o() < this.field_147367_d.func_71207_Z()) {
         if(this.field_147380_r && this.field_147369_b.func_70092_e((double)blockpos.func_177958_n() + 0.5D, (double)blockpos.func_177956_o() + 0.5D, (double)blockpos.func_177952_p() + 0.5D) < 64.0D && !this.field_147367_d.func_175579_a(worldserver, blockpos, this.field_147369_b) && worldserver.func_175723_af().func_177746_a(blockpos)) {
            this.field_147369_b.field_71134_c.func_180236_a(this.field_147369_b, worldserver, itemstack, blockpos, enumfacing, p_147346_1_.func_149573_h(), p_147346_1_.func_149569_i(), p_147346_1_.func_149575_j());
         }

         flag = true;
      } else {
         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("build.tooHigh", new Object[]{Integer.valueOf(this.field_147367_d.func_71207_Z())});
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         this.field_147369_b.field_71135_a.func_147359_a(new S02PacketChat(chatcomponenttranslation));
         flag = true;
      }

      if(flag) {
         this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(worldserver, blockpos));
         this.field_147369_b.field_71135_a.func_147359_a(new S23PacketBlockChange(worldserver, blockpos.func_177972_a(enumfacing)));
      }

      itemstack = this.field_147369_b.field_71071_by.func_70448_g();
      if(itemstack != null && itemstack.field_77994_a == 0) {
         this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c] = null;
         itemstack = null;
      }

      if(itemstack == null || itemstack.func_77988_m() == 0) {
         this.field_147369_b.field_71137_h = true;
         this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c] = ItemStack.func_77944_b(this.field_147369_b.field_71071_by.field_70462_a[this.field_147369_b.field_71071_by.field_70461_c]);
         Slot slot = this.field_147369_b.field_71070_bA.func_75147_a(this.field_147369_b.field_71071_by, this.field_147369_b.field_71071_by.field_70461_c);
         this.field_147369_b.field_71070_bA.func_75142_b();
         this.field_147369_b.field_71137_h = false;
         if(!ItemStack.func_77989_b(this.field_147369_b.field_71071_by.func_70448_g(), p_147346_1_.func_149574_g())) {
            this.func_147359_a(new S2FPacketSetSlot(this.field_147369_b.field_71070_bA.field_75152_c, slot.field_75222_d, this.field_147369_b.field_71071_by.func_70448_g()));
         }
      }

   }

   public void func_175088_a(C18PacketSpectate p_175088_1_) {
      PacketThreadUtil.func_180031_a(p_175088_1_, this, this.field_147369_b.func_71121_q());
      if(this.field_147369_b.func_175149_v()) {
         Entity entity = null;

         for(WorldServer worldserver : this.field_147367_d.field_71305_c) {
            if(worldserver != null) {
               entity = p_175088_1_.func_179727_a(worldserver);
               if(entity != null) {
                  break;
               }
            }
         }

         if(entity != null) {
            this.field_147369_b.func_175399_e(this.field_147369_b);
            this.field_147369_b.func_70078_a((Entity)null);
            if(entity.field_70170_p != this.field_147369_b.field_70170_p) {
               WorldServer worldserver1 = this.field_147369_b.func_71121_q();
               WorldServer worldserver2 = (WorldServer)entity.field_70170_p;
               this.field_147369_b.field_71093_bK = entity.field_71093_bK;
               this.func_147359_a(new S07PacketRespawn(this.field_147369_b.field_71093_bK, worldserver1.func_175659_aa(), worldserver1.func_72912_H().func_76067_t(), this.field_147369_b.field_71134_c.func_73081_b()));
               worldserver1.func_72973_f(this.field_147369_b);
               this.field_147369_b.field_70128_L = false;
               this.field_147369_b.func_70012_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
               if(this.field_147369_b.func_70089_S()) {
                  worldserver1.func_72866_a(this.field_147369_b, false);
                  worldserver2.func_72838_d(this.field_147369_b);
                  worldserver2.func_72866_a(this.field_147369_b, false);
               }

               this.field_147369_b.func_70029_a(worldserver2);
               this.field_147367_d.func_71203_ab().func_72375_a(this.field_147369_b, worldserver1);
               this.field_147369_b.func_70634_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
               this.field_147369_b.field_71134_c.func_73080_a(worldserver2);
               this.field_147367_d.func_71203_ab().func_72354_b(this.field_147369_b, worldserver2);
               this.field_147367_d.func_71203_ab().func_72385_f(this.field_147369_b);
            } else {
               this.field_147369_b.func_70634_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
            }
         }
      }

   }

   public void func_175086_a(C19PacketResourcePackStatus p_175086_1_) {
   }

   public void func_147231_a(IChatComponent p_147231_1_) {
      field_147370_c.info(this.field_147369_b.func_70005_c_() + " lost connection: " + p_147231_1_);
      this.field_147367_d.func_147132_au();
      ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("multiplayer.player.left", new Object[]{this.field_147369_b.func_145748_c_()});
      chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.YELLOW);
      this.field_147367_d.func_71203_ab().func_148539_a(chatcomponenttranslation);
      this.field_147369_b.func_71123_m();
      this.field_147367_d.func_71203_ab().func_72367_e(this.field_147369_b);
      if(this.field_147367_d.func_71264_H() && this.field_147369_b.func_70005_c_().equals(this.field_147367_d.func_71214_G())) {
         field_147370_c.info("Stopping singleplayer server as player logged out");
         this.field_147367_d.func_71263_m();
      }

   }

   public void func_147359_a(final Packet p_147359_1_) {
      if(p_147359_1_ instanceof S02PacketChat) {
         S02PacketChat s02packetchat = (S02PacketChat)p_147359_1_;
         EntityPlayer.EnumChatVisibility entityplayer$enumchatvisibility = this.field_147369_b.func_147096_v();
         if(entityplayer$enumchatvisibility == EntityPlayer.EnumChatVisibility.HIDDEN) {
            return;
         }

         if(entityplayer$enumchatvisibility == EntityPlayer.EnumChatVisibility.SYSTEM && !s02packetchat.func_148916_d()) {
            return;
         }
      }

      try {
         this.field_147371_a.func_179290_a(p_147359_1_);
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Sending packet");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Packet being sent");
         crashreportcategory.func_71500_a("Packet class", new Callable<String>() {
            public String call() throws Exception {
               return p_147359_1_.getClass().getCanonicalName();
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   public void func_147355_a(C09PacketHeldItemChange p_147355_1_) {
      PacketThreadUtil.func_180031_a(p_147355_1_, this, this.field_147369_b.func_71121_q());
      if(p_147355_1_.func_149614_c() >= 0 && p_147355_1_.func_149614_c() < InventoryPlayer.func_70451_h()) {
         this.field_147369_b.field_71071_by.field_70461_c = p_147355_1_.func_149614_c();
         this.field_147369_b.func_143004_u();
      } else {
         field_147370_c.warn(this.field_147369_b.func_70005_c_() + " tried to set an invalid carried item");
      }
   }

   public void func_147354_a(C01PacketChatMessage p_147354_1_) {
      PacketThreadUtil.func_180031_a(p_147354_1_, this, this.field_147369_b.func_71121_q());
      if(this.field_147369_b.func_147096_v() == EntityPlayer.EnumChatVisibility.HIDDEN) {
         ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("chat.cannotSend", new Object[0]);
         chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.RED);
         this.func_147359_a(new S02PacketChat(chatcomponenttranslation));
      } else {
         this.field_147369_b.func_143004_u();
         String s = p_147354_1_.func_149439_c();
         s = StringUtils.normalizeSpace(s);

         for(int i = 0; i < s.length(); ++i) {
            if(!ChatAllowedCharacters.func_71566_a(s.charAt(i))) {
               this.func_147360_c("Illegal characters in chat");
               return;
            }
         }

         if(s.startsWith("/")) {
            this.func_147361_d(s);
         } else {
            IChatComponent ichatcomponent = new ChatComponentTranslation("chat.type.text", new Object[]{this.field_147369_b.func_145748_c_(), s});
            this.field_147367_d.func_71203_ab().func_148544_a(ichatcomponent, false);
         }

         this.field_147374_l += 20;
         if(this.field_147374_l > 200 && !this.field_147367_d.func_71203_ab().func_152596_g(this.field_147369_b.func_146103_bH())) {
            this.func_147360_c("disconnect.spam");
         }

      }
   }

   private void func_147361_d(String p_147361_1_) {
      this.field_147367_d.func_71187_D().func_71556_a(this.field_147369_b, p_147361_1_);
   }

   public void func_175087_a(C0APacketAnimation p_175087_1_) {
      PacketThreadUtil.func_180031_a(p_175087_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      this.field_147369_b.func_71038_i();
   }

   public void func_147357_a(C0BPacketEntityAction p_147357_1_) {
      PacketThreadUtil.func_180031_a(p_147357_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      switch(p_147357_1_.func_180764_b()) {
      case START_SNEAKING:
         this.field_147369_b.func_70095_a(true);
         break;
      case STOP_SNEAKING:
         this.field_147369_b.func_70095_a(false);
         break;
      case START_SPRINTING:
         this.field_147369_b.func_70031_b(true);
         break;
      case STOP_SPRINTING:
         this.field_147369_b.func_70031_b(false);
         break;
      case STOP_SLEEPING:
         this.field_147369_b.func_70999_a(false, true, true);
         this.field_147380_r = false;
         break;
      case RIDING_JUMP:
         if(this.field_147369_b.field_70154_o instanceof EntityHorse) {
            ((EntityHorse)this.field_147369_b.field_70154_o).func_110206_u(p_147357_1_.func_149512_e());
         }
         break;
      case OPEN_INVENTORY:
         if(this.field_147369_b.field_70154_o instanceof EntityHorse) {
            ((EntityHorse)this.field_147369_b.field_70154_o).func_110199_f(this.field_147369_b);
         }
         break;
      default:
         throw new IllegalArgumentException("Invalid client command!");
      }

   }

   public void func_147340_a(C02PacketUseEntity p_147340_1_) {
      PacketThreadUtil.func_180031_a(p_147340_1_, this, this.field_147369_b.func_71121_q());
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      Entity entity = p_147340_1_.func_149564_a(worldserver);
      this.field_147369_b.func_143004_u();
      if(entity != null) {
         boolean flag = this.field_147369_b.func_70685_l(entity);
         double d0 = 36.0D;
         if(!flag) {
            d0 = 9.0D;
         }

         if(this.field_147369_b.func_70068_e(entity) < d0) {
            if(p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.INTERACT) {
               this.field_147369_b.func_70998_m(entity);
            } else if(p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.INTERACT_AT) {
               entity.func_174825_a(this.field_147369_b, p_147340_1_.func_179712_b());
            } else if(p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.ATTACK) {
               if(entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow || entity == this.field_147369_b) {
                  this.func_147360_c("Attempting to attack an invalid entity");
                  this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " tried to attack an invalid entity");
                  return;
               }

               this.field_147369_b.func_71059_n(entity);
            }
         }
      }

   }

   public void func_147342_a(C16PacketClientStatus p_147342_1_) {
      PacketThreadUtil.func_180031_a(p_147342_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      C16PacketClientStatus.EnumState c16packetclientstatus$enumstate = p_147342_1_.func_149435_c();
      switch(c16packetclientstatus$enumstate) {
      case PERFORM_RESPAWN:
         if(this.field_147369_b.field_71136_j) {
            this.field_147369_b = this.field_147367_d.func_71203_ab().func_72368_a(this.field_147369_b, 0, true);
         } else if(this.field_147369_b.func_71121_q().func_72912_H().func_76093_s()) {
            if(this.field_147367_d.func_71264_H() && this.field_147369_b.func_70005_c_().equals(this.field_147367_d.func_71214_G())) {
               this.field_147369_b.field_71135_a.func_147360_c("You have died. Game over, man, it\'s game over!");
               this.field_147367_d.func_71272_O();
            } else {
               UserListBansEntry userlistbansentry = new UserListBansEntry(this.field_147369_b.func_146103_bH(), (Date)null, "(You just lost the game)", (Date)null, "Death in Hardcore");
               this.field_147367_d.func_71203_ab().func_152608_h().func_152687_a(userlistbansentry);
               this.field_147369_b.field_71135_a.func_147360_c("You have died. Game over, man, it\'s game over!");
            }
         } else {
            if(this.field_147369_b.func_110143_aJ() > 0.0F) {
               return;
            }

            this.field_147369_b = this.field_147367_d.func_71203_ab().func_72368_a(this.field_147369_b, 0, false);
         }
         break;
      case REQUEST_STATS:
         this.field_147369_b.func_147099_x().func_150876_a(this.field_147369_b);
         break;
      case OPEN_INVENTORY_ACHIEVEMENT:
         this.field_147369_b.func_71029_a(AchievementList.field_76004_f);
      }

   }

   public void func_147356_a(C0DPacketCloseWindow p_147356_1_) {
      PacketThreadUtil.func_180031_a(p_147356_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_71128_l();
   }

   public void func_147351_a(C0EPacketClickWindow p_147351_1_) {
      PacketThreadUtil.func_180031_a(p_147351_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      if(this.field_147369_b.field_71070_bA.field_75152_c == p_147351_1_.func_149548_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b)) {
         if(this.field_147369_b.func_175149_v()) {
            List<ItemStack> list = Lists.<ItemStack>newArrayList();

            for(int i = 0; i < this.field_147369_b.field_71070_bA.field_75151_b.size(); ++i) {
               list.add(((Slot)this.field_147369_b.field_71070_bA.field_75151_b.get(i)).func_75211_c());
            }

            this.field_147369_b.func_71110_a(this.field_147369_b.field_71070_bA, list);
         } else {
            ItemStack itemstack = this.field_147369_b.field_71070_bA.func_75144_a(p_147351_1_.func_149544_d(), p_147351_1_.func_149543_e(), p_147351_1_.func_149542_h(), this.field_147369_b);
            if(ItemStack.func_77989_b(p_147351_1_.func_149546_g(), itemstack)) {
               this.field_147369_b.field_71135_a.func_147359_a(new S32PacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), true));
               this.field_147369_b.field_71137_h = true;
               this.field_147369_b.field_71070_bA.func_75142_b();
               this.field_147369_b.func_71113_k();
               this.field_147369_b.field_71137_h = false;
            } else {
               this.field_147372_n.func_76038_a(this.field_147369_b.field_71070_bA.field_75152_c, Short.valueOf(p_147351_1_.func_149547_f()));
               this.field_147369_b.field_71135_a.func_147359_a(new S32PacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), false));
               this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, false);
               List<ItemStack> list1 = Lists.<ItemStack>newArrayList();

               for(int j = 0; j < this.field_147369_b.field_71070_bA.field_75151_b.size(); ++j) {
                  list1.add(((Slot)this.field_147369_b.field_71070_bA.field_75151_b.get(j)).func_75211_c());
               }

               this.field_147369_b.func_71110_a(this.field_147369_b.field_71070_bA, list1);
            }
         }
      }

   }

   public void func_147338_a(C11PacketEnchantItem p_147338_1_) {
      PacketThreadUtil.func_180031_a(p_147338_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      if(this.field_147369_b.field_71070_bA.field_75152_c == p_147338_1_.func_149539_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b) && !this.field_147369_b.func_175149_v()) {
         this.field_147369_b.field_71070_bA.func_75140_a(this.field_147369_b, p_147338_1_.func_149537_d());
         this.field_147369_b.field_71070_bA.func_75142_b();
      }

   }

   public void func_147344_a(C10PacketCreativeInventoryAction p_147344_1_) {
      PacketThreadUtil.func_180031_a(p_147344_1_, this, this.field_147369_b.func_71121_q());
      if(this.field_147369_b.field_71134_c.func_73083_d()) {
         boolean flag = p_147344_1_.func_149627_c() < 0;
         ItemStack itemstack = p_147344_1_.func_149625_d();
         if(itemstack != null && itemstack.func_77942_o() && itemstack.func_77978_p().func_150297_b("BlockEntityTag", 10)) {
            NBTTagCompound nbttagcompound = itemstack.func_77978_p().func_74775_l("BlockEntityTag");
            if(nbttagcompound.func_74764_b("x") && nbttagcompound.func_74764_b("y") && nbttagcompound.func_74764_b("z")) {
               BlockPos blockpos = new BlockPos(nbttagcompound.func_74762_e("x"), nbttagcompound.func_74762_e("y"), nbttagcompound.func_74762_e("z"));
               TileEntity tileentity = this.field_147369_b.field_70170_p.func_175625_s(blockpos);
               if(tileentity != null) {
                  NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                  tileentity.func_145841_b(nbttagcompound1);
                  nbttagcompound1.func_82580_o("x");
                  nbttagcompound1.func_82580_o("y");
                  nbttagcompound1.func_82580_o("z");
                  itemstack.func_77983_a("BlockEntityTag", nbttagcompound1);
               }
            }
         }

         boolean flag1 = p_147344_1_.func_149627_c() >= 1 && p_147344_1_.func_149627_c() < 36 + InventoryPlayer.func_70451_h();
         boolean flag2 = itemstack == null || itemstack.func_77973_b() != null;
         boolean flag3 = itemstack == null || itemstack.func_77960_j() >= 0 && itemstack.field_77994_a <= 64 && itemstack.field_77994_a > 0;
         if(flag1 && flag2 && flag3) {
            if(itemstack == null) {
               this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), (ItemStack)null);
            } else {
               this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), itemstack);
            }

            this.field_147369_b.field_71069_bz.func_75128_a(this.field_147369_b, true);
         } else if(flag && flag2 && flag3 && this.field_147375_m < 200) {
            this.field_147375_m += 20;
            EntityItem entityitem = this.field_147369_b.func_71019_a(itemstack, true);
            if(entityitem != null) {
               entityitem.func_70288_d();
            }
         }
      }

   }

   public void func_147339_a(C0FPacketConfirmTransaction p_147339_1_) {
      PacketThreadUtil.func_180031_a(p_147339_1_, this, this.field_147369_b.func_71121_q());
      Short oshort = (Short)this.field_147372_n.func_76041_a(this.field_147369_b.field_71070_bA.field_75152_c);
      if(oshort != null && p_147339_1_.func_149533_d() == oshort.shortValue() && this.field_147369_b.field_71070_bA.field_75152_c == p_147339_1_.func_149532_c() && !this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b) && !this.field_147369_b.func_175149_v()) {
         this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, true);
      }

   }

   public void func_147343_a(C12PacketUpdateSign p_147343_1_) {
      PacketThreadUtil.func_180031_a(p_147343_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      BlockPos blockpos = p_147343_1_.func_179722_a();
      if(worldserver.func_175667_e(blockpos)) {
         TileEntity tileentity = worldserver.func_175625_s(blockpos);
         if(!(tileentity instanceof TileEntitySign)) {
            return;
         }

         TileEntitySign tileentitysign = (TileEntitySign)tileentity;
         if(!tileentitysign.func_145914_a() || tileentitysign.func_145911_b() != this.field_147369_b) {
            this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " just tried to change non-editable sign");
            return;
         }

         IChatComponent[] aichatcomponent = p_147343_1_.func_180768_b();

         for(int i = 0; i < aichatcomponent.length; ++i) {
            tileentitysign.field_145915_a[i] = new ChatComponentText(EnumChatFormatting.func_110646_a(aichatcomponent[i].func_150260_c()));
         }

         tileentitysign.func_70296_d();
         worldserver.func_175689_h(blockpos);
      }

   }

   public void func_147353_a(C00PacketKeepAlive p_147353_1_) {
      if(p_147353_1_.func_149460_c() == this.field_147378_h) {
         int i = (int)(this.func_147363_d() - this.field_147379_i);
         this.field_147369_b.field_71138_i = (this.field_147369_b.field_71138_i * 3 + i) / 4;
      }

   }

   private long func_147363_d() {
      return System.nanoTime() / 1000000L;
   }

   public void func_147348_a(C13PacketPlayerAbilities p_147348_1_) {
      PacketThreadUtil.func_180031_a(p_147348_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.field_71075_bZ.field_75100_b = p_147348_1_.func_149488_d() && this.field_147369_b.field_71075_bZ.field_75101_c;
   }

   public void func_147341_a(C14PacketTabComplete p_147341_1_) {
      PacketThreadUtil.func_180031_a(p_147341_1_, this, this.field_147369_b.func_71121_q());
      List<String> list = Lists.<String>newArrayList();

      for(String s : this.field_147367_d.func_180506_a(this.field_147369_b, p_147341_1_.func_149419_c(), p_147341_1_.func_179709_b())) {
         list.add(s);
      }

      this.field_147369_b.field_71135_a.func_147359_a(new S3APacketTabComplete((String[])list.toArray(new String[list.size()])));
   }

   public void func_147352_a(C15PacketClientSettings p_147352_1_) {
      PacketThreadUtil.func_180031_a(p_147352_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_147100_a(p_147352_1_);
   }

   public void func_147349_a(C17PacketCustomPayload p_147349_1_) {
      PacketThreadUtil.func_180031_a(p_147349_1_, this, this.field_147369_b.func_71121_q());
      if("MC|BEdit".equals(p_147349_1_.func_149559_c())) {
         PacketBuffer packetbuffer3 = new PacketBuffer(Unpooled.wrappedBuffer((ByteBuf)p_147349_1_.func_180760_b()));

         try {
            ItemStack itemstack1 = packetbuffer3.func_150791_c();
            if(itemstack1 != null) {
               if(!ItemWritableBook.func_150930_a(itemstack1.func_77978_p())) {
                  throw new IOException("Invalid book tag!");
               }

               ItemStack itemstack3 = this.field_147369_b.field_71071_by.func_70448_g();
               if(itemstack3 == null) {
                  return;
               }

               if(itemstack1.func_77973_b() == Items.field_151099_bA && itemstack1.func_77973_b() == itemstack3.func_77973_b()) {
                  itemstack3.func_77983_a("pages", itemstack1.func_77978_p().func_150295_c("pages", 8));
               }

               return;
            }
         } catch (Exception exception3) {
            field_147370_c.error((String)"Couldn\'t handle book info", (Throwable)exception3);
            return;
         } finally {
            packetbuffer3.release();
         }

         return;
      } else if("MC|BSign".equals(p_147349_1_.func_149559_c())) {
         PacketBuffer packetbuffer2 = new PacketBuffer(Unpooled.wrappedBuffer((ByteBuf)p_147349_1_.func_180760_b()));

         try {
            ItemStack itemstack = packetbuffer2.func_150791_c();
            if(itemstack != null) {
               if(!ItemEditableBook.func_77828_a(itemstack.func_77978_p())) {
                  throw new IOException("Invalid book tag!");
               }

               ItemStack itemstack2 = this.field_147369_b.field_71071_by.func_70448_g();
               if(itemstack2 == null) {
                  return;
               }

               if(itemstack.func_77973_b() == Items.field_151164_bB && itemstack2.func_77973_b() == Items.field_151099_bA) {
                  itemstack2.func_77983_a("author", new NBTTagString(this.field_147369_b.func_70005_c_()));
                  itemstack2.func_77983_a("title", new NBTTagString(itemstack.func_77978_p().func_74779_i("title")));
                  itemstack2.func_77983_a("pages", itemstack.func_77978_p().func_150295_c("pages", 8));
                  itemstack2.func_150996_a(Items.field_151164_bB);
               }

               return;
            }
         } catch (Exception exception4) {
            field_147370_c.error((String)"Couldn\'t sign book", (Throwable)exception4);
            return;
         } finally {
            packetbuffer2.release();
         }

         return;
      } else if("MC|TrSel".equals(p_147349_1_.func_149559_c())) {
         try {
            int i = p_147349_1_.func_180760_b().readInt();
            Container container = this.field_147369_b.field_71070_bA;
            if(container instanceof ContainerMerchant) {
               ((ContainerMerchant)container).func_75175_c(i);
            }
         } catch (Exception exception2) {
            field_147370_c.error((String)"Couldn\'t select trade", (Throwable)exception2);
         }
      } else if("MC|AdvCdm".equals(p_147349_1_.func_149559_c())) {
         if(!this.field_147367_d.func_82356_Z()) {
            this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.notEnabled", new Object[0]));
         } else if(this.field_147369_b.func_70003_b(2, "") && this.field_147369_b.field_71075_bZ.field_75098_d) {
            PacketBuffer packetbuffer = p_147349_1_.func_180760_b();

            try {
               int j = packetbuffer.readByte();
               CommandBlockLogic commandblocklogic = null;
               if(j == 0) {
                  TileEntity tileentity = this.field_147369_b.field_70170_p.func_175625_s(new BlockPos(packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt()));
                  if(tileentity instanceof TileEntityCommandBlock) {
                     commandblocklogic = ((TileEntityCommandBlock)tileentity).func_145993_a();
                  }
               } else if(j == 1) {
                  Entity entity = this.field_147369_b.field_70170_p.func_73045_a(packetbuffer.readInt());
                  if(entity instanceof EntityMinecartCommandBlock) {
                     commandblocklogic = ((EntityMinecartCommandBlock)entity).func_145822_e();
                  }
               }

               String s1 = packetbuffer.func_150789_c(packetbuffer.readableBytes());
               boolean flag = packetbuffer.readBoolean();
               if(commandblocklogic != null) {
                  commandblocklogic.func_145752_a(s1);
                  commandblocklogic.func_175573_a(flag);
                  if(!flag) {
                     commandblocklogic.func_145750_b((IChatComponent)null);
                  }

                  commandblocklogic.func_145756_e();
                  this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.setCommand.success", new Object[]{s1}));
               }
            } catch (Exception exception1) {
               field_147370_c.error((String)"Couldn\'t set command block", (Throwable)exception1);
            } finally {
               packetbuffer.release();
            }
         } else {
            this.field_147369_b.func_145747_a(new ChatComponentTranslation("advMode.notAllowed", new Object[0]));
         }
      } else if("MC|Beacon".equals(p_147349_1_.func_149559_c())) {
         if(this.field_147369_b.field_71070_bA instanceof ContainerBeacon) {
            try {
               PacketBuffer packetbuffer1 = p_147349_1_.func_180760_b();
               int k = packetbuffer1.readInt();
               int l = packetbuffer1.readInt();
               ContainerBeacon containerbeacon = (ContainerBeacon)this.field_147369_b.field_71070_bA;
               Slot slot = containerbeacon.func_75139_a(0);
               if(slot.func_75216_d()) {
                  slot.func_75209_a(1);
                  IInventory iinventory = containerbeacon.func_180611_e();
                  iinventory.func_174885_b(1, k);
                  iinventory.func_174885_b(2, l);
                  iinventory.func_70296_d();
               }
            } catch (Exception exception) {
               field_147370_c.error((String)"Couldn\'t set beacon", (Throwable)exception);
            }
         }
      } else if("MC|ItemName".equals(p_147349_1_.func_149559_c()) && this.field_147369_b.field_71070_bA instanceof ContainerRepair) {
         ContainerRepair containerrepair = (ContainerRepair)this.field_147369_b.field_71070_bA;
         if(p_147349_1_.func_180760_b() != null && p_147349_1_.func_180760_b().readableBytes() >= 1) {
            String s = ChatAllowedCharacters.func_71565_a(p_147349_1_.func_180760_b().func_150789_c(32767));
            if(s.length() <= 30) {
               containerrepair.func_82850_a(s);
            }
         } else {
            containerrepair.func_82850_a("");
         }
      }

   }
}
