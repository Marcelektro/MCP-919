package net.minecraft.entity;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.storage.MapData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTrackerEntry {
   private static final Logger field_151262_p = LogManager.getLogger();
   public Entity field_73132_a;
   public int field_73130_b;
   public int field_73131_c;
   public int field_73128_d;
   public int field_73129_e;
   public int field_73126_f;
   public int field_73127_g;
   public int field_73139_h;
   public int field_73140_i;
   public double field_73137_j;
   public double field_73138_k;
   public double field_73135_l;
   public int field_73136_m;
   private double field_73147_p;
   private double field_73146_q;
   private double field_73145_r;
   private boolean field_73144_s;
   private boolean field_73143_t;
   private int field_73142_u;
   private Entity field_85178_v;
   private boolean field_73141_v;
   private boolean field_180234_y;
   public boolean field_73133_n;
   public Set<EntityPlayerMP> field_73134_o = Sets.<EntityPlayerMP>newHashSet();

   public EntityTrackerEntry(Entity p_i1525_1_, int p_i1525_2_, int p_i1525_3_, boolean p_i1525_4_) {
      this.field_73132_a = p_i1525_1_;
      this.field_73130_b = p_i1525_2_;
      this.field_73131_c = p_i1525_3_;
      this.field_73143_t = p_i1525_4_;
      this.field_73128_d = MathHelper.func_76128_c(p_i1525_1_.field_70165_t * 32.0D);
      this.field_73129_e = MathHelper.func_76128_c(p_i1525_1_.field_70163_u * 32.0D);
      this.field_73126_f = MathHelper.func_76128_c(p_i1525_1_.field_70161_v * 32.0D);
      this.field_73127_g = MathHelper.func_76141_d(p_i1525_1_.field_70177_z * 256.0F / 360.0F);
      this.field_73139_h = MathHelper.func_76141_d(p_i1525_1_.field_70125_A * 256.0F / 360.0F);
      this.field_73140_i = MathHelper.func_76141_d(p_i1525_1_.func_70079_am() * 256.0F / 360.0F);
      this.field_180234_y = p_i1525_1_.field_70122_E;
   }

   public boolean equals(Object p_equals_1_) {
      return p_equals_1_ instanceof EntityTrackerEntry?((EntityTrackerEntry)p_equals_1_).field_73132_a.func_145782_y() == this.field_73132_a.func_145782_y():false;
   }

   public int hashCode() {
      return this.field_73132_a.func_145782_y();
   }

   public void func_73122_a(List<EntityPlayer> p_73122_1_) {
      this.field_73133_n = false;
      if(!this.field_73144_s || this.field_73132_a.func_70092_e(this.field_73147_p, this.field_73146_q, this.field_73145_r) > 16.0D) {
         this.field_73147_p = this.field_73132_a.field_70165_t;
         this.field_73146_q = this.field_73132_a.field_70163_u;
         this.field_73145_r = this.field_73132_a.field_70161_v;
         this.field_73144_s = true;
         this.field_73133_n = true;
         this.func_73125_b(p_73122_1_);
      }

      if(this.field_85178_v != this.field_73132_a.field_70154_o || this.field_73132_a.field_70154_o != null && this.field_73136_m % 60 == 0) {
         this.field_85178_v = this.field_73132_a.field_70154_o;
         this.func_151259_a(new S1BPacketEntityAttach(0, this.field_73132_a, this.field_73132_a.field_70154_o));
      }

      if(this.field_73132_a instanceof EntityItemFrame && this.field_73136_m % 10 == 0) {
         EntityItemFrame entityitemframe = (EntityItemFrame)this.field_73132_a;
         ItemStack itemstack = entityitemframe.func_82335_i();
         if(itemstack != null && itemstack.func_77973_b() instanceof ItemMap) {
            MapData mapdata = Items.field_151098_aY.func_77873_a(itemstack, this.field_73132_a.field_70170_p);

            for(EntityPlayer entityplayer : p_73122_1_) {
               EntityPlayerMP entityplayermp = (EntityPlayerMP)entityplayer;
               mapdata.func_76191_a(entityplayermp, itemstack);
               Packet packet = Items.field_151098_aY.func_150911_c(itemstack, this.field_73132_a.field_70170_p, entityplayermp);
               if(packet != null) {
                  entityplayermp.field_71135_a.func_147359_a(packet);
               }
            }
         }

         this.func_111190_b();
      }

      if(this.field_73136_m % this.field_73131_c == 0 || this.field_73132_a.field_70160_al || this.field_73132_a.func_70096_w().func_75684_a()) {
         if(this.field_73132_a.field_70154_o == null) {
            ++this.field_73142_u;
            int k = MathHelper.func_76128_c(this.field_73132_a.field_70165_t * 32.0D);
            int j1 = MathHelper.func_76128_c(this.field_73132_a.field_70163_u * 32.0D);
            int k1 = MathHelper.func_76128_c(this.field_73132_a.field_70161_v * 32.0D);
            int l1 = MathHelper.func_76141_d(this.field_73132_a.field_70177_z * 256.0F / 360.0F);
            int i2 = MathHelper.func_76141_d(this.field_73132_a.field_70125_A * 256.0F / 360.0F);
            int j2 = k - this.field_73128_d;
            int k2 = j1 - this.field_73129_e;
            int i = k1 - this.field_73126_f;
            Packet packet1 = null;
            boolean flag = Math.abs(j2) >= 4 || Math.abs(k2) >= 4 || Math.abs(i) >= 4 || this.field_73136_m % 60 == 0;
            boolean flag1 = Math.abs(l1 - this.field_73127_g) >= 4 || Math.abs(i2 - this.field_73139_h) >= 4;
            if(this.field_73136_m > 0 || this.field_73132_a instanceof EntityArrow) {
               if(j2 >= -128 && j2 < 128 && k2 >= -128 && k2 < 128 && i >= -128 && i < 128 && this.field_73142_u <= 400 && !this.field_73141_v && this.field_180234_y == this.field_73132_a.field_70122_E) {
                  if((!flag || !flag1) && !(this.field_73132_a instanceof EntityArrow)) {
                     if(flag) {
                        packet1 = new S14PacketEntity.S15PacketEntityRelMove(this.field_73132_a.func_145782_y(), (byte)j2, (byte)k2, (byte)i, this.field_73132_a.field_70122_E);
                     } else if(flag1) {
                        packet1 = new S14PacketEntity.S16PacketEntityLook(this.field_73132_a.func_145782_y(), (byte)l1, (byte)i2, this.field_73132_a.field_70122_E);
                     }
                  } else {
                     packet1 = new S14PacketEntity.S17PacketEntityLookMove(this.field_73132_a.func_145782_y(), (byte)j2, (byte)k2, (byte)i, (byte)l1, (byte)i2, this.field_73132_a.field_70122_E);
                  }
               } else {
                  this.field_180234_y = this.field_73132_a.field_70122_E;
                  this.field_73142_u = 0;
                  packet1 = new S18PacketEntityTeleport(this.field_73132_a.func_145782_y(), k, j1, k1, (byte)l1, (byte)i2, this.field_73132_a.field_70122_E);
               }
            }

            if(this.field_73143_t) {
               double d0 = this.field_73132_a.field_70159_w - this.field_73137_j;
               double d1 = this.field_73132_a.field_70181_x - this.field_73138_k;
               double d2 = this.field_73132_a.field_70179_y - this.field_73135_l;
               double d3 = 0.02D;
               double d4 = d0 * d0 + d1 * d1 + d2 * d2;
               if(d4 > d3 * d3 || d4 > 0.0D && this.field_73132_a.field_70159_w == 0.0D && this.field_73132_a.field_70181_x == 0.0D && this.field_73132_a.field_70179_y == 0.0D) {
                  this.field_73137_j = this.field_73132_a.field_70159_w;
                  this.field_73138_k = this.field_73132_a.field_70181_x;
                  this.field_73135_l = this.field_73132_a.field_70179_y;
                  this.func_151259_a(new S12PacketEntityVelocity(this.field_73132_a.func_145782_y(), this.field_73137_j, this.field_73138_k, this.field_73135_l));
               }
            }

            if(packet1 != null) {
               this.func_151259_a(packet1);
            }

            this.func_111190_b();
            if(flag) {
               this.field_73128_d = k;
               this.field_73129_e = j1;
               this.field_73126_f = k1;
            }

            if(flag1) {
               this.field_73127_g = l1;
               this.field_73139_h = i2;
            }

            this.field_73141_v = false;
         } else {
            int j = MathHelper.func_76141_d(this.field_73132_a.field_70177_z * 256.0F / 360.0F);
            int i1 = MathHelper.func_76141_d(this.field_73132_a.field_70125_A * 256.0F / 360.0F);
            boolean flag2 = Math.abs(j - this.field_73127_g) >= 4 || Math.abs(i1 - this.field_73139_h) >= 4;
            if(flag2) {
               this.func_151259_a(new S14PacketEntity.S16PacketEntityLook(this.field_73132_a.func_145782_y(), (byte)j, (byte)i1, this.field_73132_a.field_70122_E));
               this.field_73127_g = j;
               this.field_73139_h = i1;
            }

            this.field_73128_d = MathHelper.func_76128_c(this.field_73132_a.field_70165_t * 32.0D);
            this.field_73129_e = MathHelper.func_76128_c(this.field_73132_a.field_70163_u * 32.0D);
            this.field_73126_f = MathHelper.func_76128_c(this.field_73132_a.field_70161_v * 32.0D);
            this.func_111190_b();
            this.field_73141_v = true;
         }

         int l = MathHelper.func_76141_d(this.field_73132_a.func_70079_am() * 256.0F / 360.0F);
         if(Math.abs(l - this.field_73140_i) >= 4) {
            this.func_151259_a(new S19PacketEntityHeadLook(this.field_73132_a, (byte)l));
            this.field_73140_i = l;
         }

         this.field_73132_a.field_70160_al = false;
      }

      ++this.field_73136_m;
      if(this.field_73132_a.field_70133_I) {
         this.func_151261_b(new S12PacketEntityVelocity(this.field_73132_a));
         this.field_73132_a.field_70133_I = false;
      }

   }

   private void func_111190_b() {
      DataWatcher datawatcher = this.field_73132_a.func_70096_w();
      if(datawatcher.func_75684_a()) {
         this.func_151261_b(new S1CPacketEntityMetadata(this.field_73132_a.func_145782_y(), datawatcher, false));
      }

      if(this.field_73132_a instanceof EntityLivingBase) {
         ServersideAttributeMap serversideattributemap = (ServersideAttributeMap)((EntityLivingBase)this.field_73132_a).func_110140_aT();
         Set<IAttributeInstance> set = serversideattributemap.func_111161_b();
         if(!set.isEmpty()) {
            this.func_151261_b(new S20PacketEntityProperties(this.field_73132_a.func_145782_y(), set));
         }

         set.clear();
      }

   }

   public void func_151259_a(Packet p_151259_1_) {
      for(EntityPlayerMP entityplayermp : this.field_73134_o) {
         entityplayermp.field_71135_a.func_147359_a(p_151259_1_);
      }

   }

   public void func_151261_b(Packet p_151261_1_) {
      this.func_151259_a(p_151261_1_);
      if(this.field_73132_a instanceof EntityPlayerMP) {
         ((EntityPlayerMP)this.field_73132_a).field_71135_a.func_147359_a(p_151261_1_);
      }

   }

   public void func_73119_a() {
      for(EntityPlayerMP entityplayermp : this.field_73134_o) {
         entityplayermp.func_152339_d(this.field_73132_a);
      }

   }

   public void func_73118_a(EntityPlayerMP p_73118_1_) {
      if(this.field_73134_o.contains(p_73118_1_)) {
         p_73118_1_.func_152339_d(this.field_73132_a);
         this.field_73134_o.remove(p_73118_1_);
      }

   }

   public void func_73117_b(EntityPlayerMP p_73117_1_) {
      if(p_73117_1_ != this.field_73132_a) {
         if(this.func_180233_c(p_73117_1_)) {
            if(!this.field_73134_o.contains(p_73117_1_) && (this.func_73121_d(p_73117_1_) || this.field_73132_a.field_98038_p)) {
               this.field_73134_o.add(p_73117_1_);
               Packet packet = this.func_151260_c();
               p_73117_1_.field_71135_a.func_147359_a(packet);
               if(!this.field_73132_a.func_70096_w().func_92085_d()) {
                  p_73117_1_.field_71135_a.func_147359_a(new S1CPacketEntityMetadata(this.field_73132_a.func_145782_y(), this.field_73132_a.func_70096_w(), true));
               }

               NBTTagCompound nbttagcompound = this.field_73132_a.func_174819_aU();
               if(nbttagcompound != null) {
                  p_73117_1_.field_71135_a.func_147359_a(new S49PacketUpdateEntityNBT(this.field_73132_a.func_145782_y(), nbttagcompound));
               }

               if(this.field_73132_a instanceof EntityLivingBase) {
                  ServersideAttributeMap serversideattributemap = (ServersideAttributeMap)((EntityLivingBase)this.field_73132_a).func_110140_aT();
                  Collection<IAttributeInstance> collection = serversideattributemap.func_111160_c();
                  if(!collection.isEmpty()) {
                     p_73117_1_.field_71135_a.func_147359_a(new S20PacketEntityProperties(this.field_73132_a.func_145782_y(), collection));
                  }
               }

               this.field_73137_j = this.field_73132_a.field_70159_w;
               this.field_73138_k = this.field_73132_a.field_70181_x;
               this.field_73135_l = this.field_73132_a.field_70179_y;
               if(this.field_73143_t && !(packet instanceof S0FPacketSpawnMob)) {
                  p_73117_1_.field_71135_a.func_147359_a(new S12PacketEntityVelocity(this.field_73132_a.func_145782_y(), this.field_73132_a.field_70159_w, this.field_73132_a.field_70181_x, this.field_73132_a.field_70179_y));
               }

               if(this.field_73132_a.field_70154_o != null) {
                  p_73117_1_.field_71135_a.func_147359_a(new S1BPacketEntityAttach(0, this.field_73132_a, this.field_73132_a.field_70154_o));
               }

               if(this.field_73132_a instanceof EntityLiving && ((EntityLiving)this.field_73132_a).func_110166_bE() != null) {
                  p_73117_1_.field_71135_a.func_147359_a(new S1BPacketEntityAttach(1, this.field_73132_a, ((EntityLiving)this.field_73132_a).func_110166_bE()));
               }

               if(this.field_73132_a instanceof EntityLivingBase) {
                  for(int i = 0; i < 5; ++i) {
                     ItemStack itemstack = ((EntityLivingBase)this.field_73132_a).func_71124_b(i);
                     if(itemstack != null) {
                        p_73117_1_.field_71135_a.func_147359_a(new S04PacketEntityEquipment(this.field_73132_a.func_145782_y(), i, itemstack));
                     }
                  }
               }

               if(this.field_73132_a instanceof EntityPlayer) {
                  EntityPlayer entityplayer = (EntityPlayer)this.field_73132_a;
                  if(entityplayer.func_70608_bn()) {
                     p_73117_1_.field_71135_a.func_147359_a(new S0APacketUseBed(entityplayer, new BlockPos(this.field_73132_a)));
                  }
               }

               if(this.field_73132_a instanceof EntityLivingBase) {
                  EntityLivingBase entitylivingbase = (EntityLivingBase)this.field_73132_a;

                  for(PotionEffect potioneffect : entitylivingbase.func_70651_bq()) {
                     p_73117_1_.field_71135_a.func_147359_a(new S1DPacketEntityEffect(this.field_73132_a.func_145782_y(), potioneffect));
                  }
               }
            }
         } else if(this.field_73134_o.contains(p_73117_1_)) {
            this.field_73134_o.remove(p_73117_1_);
            p_73117_1_.func_152339_d(this.field_73132_a);
         }

      }
   }

   public boolean func_180233_c(EntityPlayerMP p_180233_1_) {
      double d0 = p_180233_1_.field_70165_t - (double)(this.field_73128_d / 32);
      double d1 = p_180233_1_.field_70161_v - (double)(this.field_73126_f / 32);
      return d0 >= (double)(-this.field_73130_b) && d0 <= (double)this.field_73130_b && d1 >= (double)(-this.field_73130_b) && d1 <= (double)this.field_73130_b && this.field_73132_a.func_174827_a(p_180233_1_);
   }

   private boolean func_73121_d(EntityPlayerMP p_73121_1_) {
      return p_73121_1_.func_71121_q().func_73040_p().func_72694_a(p_73121_1_, this.field_73132_a.field_70176_ah, this.field_73132_a.field_70164_aj);
   }

   public void func_73125_b(List<EntityPlayer> p_73125_1_) {
      for(int i = 0; i < p_73125_1_.size(); ++i) {
         this.func_73117_b((EntityPlayerMP)p_73125_1_.get(i));
      }

   }

   private Packet func_151260_c() {
      if(this.field_73132_a.field_70128_L) {
         field_151262_p.warn("Fetching addPacket for removed entity");
      }

      if(this.field_73132_a instanceof EntityItem) {
         return new S0EPacketSpawnObject(this.field_73132_a, 2, 1);
      } else if(this.field_73132_a instanceof EntityPlayerMP) {
         return new S0CPacketSpawnPlayer((EntityPlayer)this.field_73132_a);
      } else if(this.field_73132_a instanceof EntityMinecart) {
         EntityMinecart entityminecart = (EntityMinecart)this.field_73132_a;
         return new S0EPacketSpawnObject(this.field_73132_a, 10, entityminecart.func_180456_s().func_180039_a());
      } else if(this.field_73132_a instanceof EntityBoat) {
         return new S0EPacketSpawnObject(this.field_73132_a, 1);
      } else if(this.field_73132_a instanceof IAnimals) {
         this.field_73140_i = MathHelper.func_76141_d(this.field_73132_a.func_70079_am() * 256.0F / 360.0F);
         return new S0FPacketSpawnMob((EntityLivingBase)this.field_73132_a);
      } else if(this.field_73132_a instanceof EntityFishHook) {
         Entity entity1 = ((EntityFishHook)this.field_73132_a).field_146042_b;
         return new S0EPacketSpawnObject(this.field_73132_a, 90, entity1 != null?entity1.func_145782_y():this.field_73132_a.func_145782_y());
      } else if(this.field_73132_a instanceof EntityArrow) {
         Entity entity = ((EntityArrow)this.field_73132_a).field_70250_c;
         return new S0EPacketSpawnObject(this.field_73132_a, 60, entity != null?entity.func_145782_y():this.field_73132_a.func_145782_y());
      } else if(this.field_73132_a instanceof EntitySnowball) {
         return new S0EPacketSpawnObject(this.field_73132_a, 61);
      } else if(this.field_73132_a instanceof EntityPotion) {
         return new S0EPacketSpawnObject(this.field_73132_a, 73, ((EntityPotion)this.field_73132_a).func_70196_i());
      } else if(this.field_73132_a instanceof EntityExpBottle) {
         return new S0EPacketSpawnObject(this.field_73132_a, 75);
      } else if(this.field_73132_a instanceof EntityEnderPearl) {
         return new S0EPacketSpawnObject(this.field_73132_a, 65);
      } else if(this.field_73132_a instanceof EntityEnderEye) {
         return new S0EPacketSpawnObject(this.field_73132_a, 72);
      } else if(this.field_73132_a instanceof EntityFireworkRocket) {
         return new S0EPacketSpawnObject(this.field_73132_a, 76);
      } else if(this.field_73132_a instanceof EntityFireball) {
         EntityFireball entityfireball = (EntityFireball)this.field_73132_a;
         S0EPacketSpawnObject s0epacketspawnobject2 = null;
         int i = 63;
         if(this.field_73132_a instanceof EntitySmallFireball) {
            i = 64;
         } else if(this.field_73132_a instanceof EntityWitherSkull) {
            i = 66;
         }

         if(entityfireball.field_70235_a != null) {
            s0epacketspawnobject2 = new S0EPacketSpawnObject(this.field_73132_a, i, ((EntityFireball)this.field_73132_a).field_70235_a.func_145782_y());
         } else {
            s0epacketspawnobject2 = new S0EPacketSpawnObject(this.field_73132_a, i, 0);
         }

         s0epacketspawnobject2.func_149003_d((int)(entityfireball.field_70232_b * 8000.0D));
         s0epacketspawnobject2.func_149000_e((int)(entityfireball.field_70233_c * 8000.0D));
         s0epacketspawnobject2.func_149007_f((int)(entityfireball.field_70230_d * 8000.0D));
         return s0epacketspawnobject2;
      } else if(this.field_73132_a instanceof EntityEgg) {
         return new S0EPacketSpawnObject(this.field_73132_a, 62);
      } else if(this.field_73132_a instanceof EntityTNTPrimed) {
         return new S0EPacketSpawnObject(this.field_73132_a, 50);
      } else if(this.field_73132_a instanceof EntityEnderCrystal) {
         return new S0EPacketSpawnObject(this.field_73132_a, 51);
      } else if(this.field_73132_a instanceof EntityFallingBlock) {
         EntityFallingBlock entityfallingblock = (EntityFallingBlock)this.field_73132_a;
         return new S0EPacketSpawnObject(this.field_73132_a, 70, Block.func_176210_f(entityfallingblock.func_175131_l()));
      } else if(this.field_73132_a instanceof EntityArmorStand) {
         return new S0EPacketSpawnObject(this.field_73132_a, 78);
      } else if(this.field_73132_a instanceof EntityPainting) {
         return new S10PacketSpawnPainting((EntityPainting)this.field_73132_a);
      } else if(this.field_73132_a instanceof EntityItemFrame) {
         EntityItemFrame entityitemframe = (EntityItemFrame)this.field_73132_a;
         S0EPacketSpawnObject s0epacketspawnobject1 = new S0EPacketSpawnObject(this.field_73132_a, 71, entityitemframe.field_174860_b.func_176736_b());
         BlockPos blockpos1 = entityitemframe.func_174857_n();
         s0epacketspawnobject1.func_148996_a(MathHelper.func_76141_d((float)(blockpos1.func_177958_n() * 32)));
         s0epacketspawnobject1.func_148995_b(MathHelper.func_76141_d((float)(blockpos1.func_177956_o() * 32)));
         s0epacketspawnobject1.func_149005_c(MathHelper.func_76141_d((float)(blockpos1.func_177952_p() * 32)));
         return s0epacketspawnobject1;
      } else if(this.field_73132_a instanceof EntityLeashKnot) {
         EntityLeashKnot entityleashknot = (EntityLeashKnot)this.field_73132_a;
         S0EPacketSpawnObject s0epacketspawnobject = new S0EPacketSpawnObject(this.field_73132_a, 77);
         BlockPos blockpos = entityleashknot.func_174857_n();
         s0epacketspawnobject.func_148996_a(MathHelper.func_76141_d((float)(blockpos.func_177958_n() * 32)));
         s0epacketspawnobject.func_148995_b(MathHelper.func_76141_d((float)(blockpos.func_177956_o() * 32)));
         s0epacketspawnobject.func_149005_c(MathHelper.func_76141_d((float)(blockpos.func_177952_p() * 32)));
         return s0epacketspawnobject;
      } else if(this.field_73132_a instanceof EntityXPOrb) {
         return new S11PacketSpawnExperienceOrb((EntityXPOrb)this.field_73132_a);
      } else {
         throw new IllegalArgumentException("Don\'t know how to add " + this.field_73132_a.getClass() + "!");
      }
   }

   public void func_73123_c(EntityPlayerMP p_73123_1_) {
      if(this.field_73134_o.contains(p_73123_1_)) {
         this.field_73134_o.remove(p_73123_1_);
         p_73123_1_.func_152339_d(this.field_73132_a);
      }

   }
}
