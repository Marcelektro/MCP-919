package net.minecraft.entity.player;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.server.management.UserListOpsEntry;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityPlayerMP extends EntityPlayer implements ICrafting {
   private static final Logger field_147102_bM = LogManager.getLogger();
   private String field_71148_cg = "en_US";
   public NetHandlerPlayServer field_71135_a;
   public final MinecraftServer field_71133_b;
   public final ItemInWorldManager field_71134_c;
   public double field_71131_d;
   public double field_71132_e;
   public final List<ChunkCoordIntPair> field_71129_f = Lists.<ChunkCoordIntPair>newLinkedList();
   private final List<Integer> field_71130_g = Lists.<Integer>newLinkedList();
   private final StatisticsFile field_147103_bO;
   private float field_130068_bO = Float.MIN_VALUE;
   private float field_71149_ch = -1.0E8F;
   private int field_71146_ci = -99999999;
   private boolean field_71147_cj = true;
   private int field_71144_ck = -99999999;
   private int field_147101_bU = 60;
   private EntityPlayer.EnumChatVisibility field_71143_cn;
   private boolean field_71140_co = true;
   private long field_143005_bX = System.currentTimeMillis();
   private Entity field_175401_bS = null;
   private int field_71139_cq;
   public boolean field_71137_h;
   public int field_71138_i;
   public boolean field_71136_j;

   public EntityPlayerMP(MinecraftServer p_i45285_1_, WorldServer p_i45285_2_, GameProfile p_i45285_3_, ItemInWorldManager p_i45285_4_) {
      super(p_i45285_2_, p_i45285_3_);
      p_i45285_4_.field_73090_b = this;
      this.field_71134_c = p_i45285_4_;
      BlockPos blockpos = p_i45285_2_.func_175694_M();
      if(!p_i45285_2_.field_73011_w.func_177495_o() && p_i45285_2_.func_72912_H().func_76077_q() != WorldSettings.GameType.ADVENTURE) {
         int i = Math.max(5, p_i45285_1_.func_82357_ak() - 6);
         int j = MathHelper.func_76128_c(p_i45285_2_.func_175723_af().func_177729_b((double)blockpos.func_177958_n(), (double)blockpos.func_177952_p()));
         if(j < i) {
            i = j;
         }

         if(j <= 1) {
            i = 1;
         }

         blockpos = p_i45285_2_.func_175672_r(blockpos.func_177982_a(this.field_70146_Z.nextInt(i * 2) - i, 0, this.field_70146_Z.nextInt(i * 2) - i));
      }

      this.field_71133_b = p_i45285_1_;
      this.field_147103_bO = p_i45285_1_.func_71203_ab().func_152602_a(this);
      this.field_70138_W = 0.0F;
      this.func_174828_a(blockpos, 0.0F, 0.0F);

      while(!p_i45285_2_.func_72945_a(this, this.func_174813_aQ()).isEmpty() && this.field_70163_u < 255.0D) {
         this.func_70107_b(this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v);
      }

   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      if(p_70037_1_.func_150297_b("playerGameType", 99)) {
         if(MinecraftServer.func_71276_C().func_104056_am()) {
            this.field_71134_c.func_73076_a(MinecraftServer.func_71276_C().func_71265_f());
         } else {
            this.field_71134_c.func_73076_a(WorldSettings.GameType.func_77146_a(p_70037_1_.func_74762_e("playerGameType")));
         }
      }

   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74768_a("playerGameType", this.field_71134_c.func_73081_b().func_77148_a());
   }

   public void func_82242_a(int p_82242_1_) {
      super.func_82242_a(p_82242_1_);
      this.field_71144_ck = -1;
   }

   public void func_71013_b(int p_71013_1_) {
      super.func_71013_b(p_71013_1_);
      this.field_71144_ck = -1;
   }

   public void func_71116_b() {
      this.field_71070_bA.func_75132_a(this);
   }

   public void func_152111_bt() {
      super.func_152111_bt();
      this.field_71135_a.func_147359_a(new S42PacketCombatEvent(this.func_110142_aN(), S42PacketCombatEvent.Event.ENTER_COMBAT));
   }

   public void func_152112_bu() {
      super.func_152112_bu();
      this.field_71135_a.func_147359_a(new S42PacketCombatEvent(this.func_110142_aN(), S42PacketCombatEvent.Event.END_COMBAT));
   }

   public void func_70071_h_() {
      this.field_71134_c.func_73075_a();
      --this.field_147101_bU;
      if(this.field_70172_ad > 0) {
         --this.field_70172_ad;
      }

      this.field_71070_bA.func_75142_b();
      if(!this.field_70170_p.field_72995_K && !this.field_71070_bA.func_75145_c(this)) {
         this.func_71053_j();
         this.field_71070_bA = this.field_71069_bz;
      }

      while(!this.field_71130_g.isEmpty()) {
         int i = Math.min(this.field_71130_g.size(), Integer.MAX_VALUE);
         int[] aint = new int[i];
         Iterator<Integer> iterator = this.field_71130_g.iterator();
         int j = 0;

         while(iterator.hasNext() && j < i) {
            aint[j++] = ((Integer)iterator.next()).intValue();
            iterator.remove();
         }

         this.field_71135_a.func_147359_a(new S13PacketDestroyEntities(aint));
      }

      if(!this.field_71129_f.isEmpty()) {
         List<Chunk> list = Lists.<Chunk>newArrayList();
         Iterator<ChunkCoordIntPair> iterator1 = this.field_71129_f.iterator();
         List<TileEntity> list1 = Lists.<TileEntity>newArrayList();

         while(iterator1.hasNext() && ((List)list).size() < 10) {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)iterator1.next();
            if(chunkcoordintpair != null) {
               if(this.field_70170_p.func_175667_e(new BlockPos(chunkcoordintpair.field_77276_a << 4, 0, chunkcoordintpair.field_77275_b << 4))) {
                  Chunk chunk = this.field_70170_p.func_72964_e(chunkcoordintpair.field_77276_a, chunkcoordintpair.field_77275_b);
                  if(chunk.func_150802_k()) {
                     list.add(chunk);
                     list1.addAll(((WorldServer)this.field_70170_p).func_147486_a(chunkcoordintpair.field_77276_a * 16, 0, chunkcoordintpair.field_77275_b * 16, chunkcoordintpair.field_77276_a * 16 + 16, 256, chunkcoordintpair.field_77275_b * 16 + 16));
                     iterator1.remove();
                  }
               }
            } else {
               iterator1.remove();
            }
         }

         if(!list.isEmpty()) {
            if(list.size() == 1) {
               this.field_71135_a.func_147359_a(new S21PacketChunkData((Chunk)list.get(0), true, '\uffff'));
            } else {
               this.field_71135_a.func_147359_a(new S26PacketMapChunkBulk(list));
            }

            for(TileEntity tileentity : list1) {
               this.func_147097_b(tileentity);
            }

            for(Chunk chunk1 : list) {
               this.func_71121_q().func_73039_n().func_85172_a(this, chunk1);
            }
         }
      }

      Entity entity = this.func_175398_C();
      if(entity != this) {
         if(!entity.func_70089_S()) {
            this.func_175399_e(this);
         } else {
            this.func_70080_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
            this.field_71133_b.func_71203_ab().func_72358_d(this);
            if(this.func_70093_af()) {
               this.func_175399_e(this);
            }
         }
      }

   }

   public void func_71127_g() {
      try {
         super.func_70071_h_();

         for(int i = 0; i < this.field_71071_by.func_70302_i_(); ++i) {
            ItemStack itemstack = this.field_71071_by.func_70301_a(i);
            if(itemstack != null && itemstack.func_77973_b().func_77643_m_()) {
               Packet packet = ((ItemMapBase)itemstack.func_77973_b()).func_150911_c(itemstack, this.field_70170_p, this);
               if(packet != null) {
                  this.field_71135_a.func_147359_a(packet);
               }
            }
         }

         if(this.func_110143_aJ() != this.field_71149_ch || this.field_71146_ci != this.field_71100_bB.func_75116_a() || this.field_71100_bB.func_75115_e() == 0.0F != this.field_71147_cj) {
            this.field_71135_a.func_147359_a(new S06PacketUpdateHealth(this.func_110143_aJ(), this.field_71100_bB.func_75116_a(), this.field_71100_bB.func_75115_e()));
            this.field_71149_ch = this.func_110143_aJ();
            this.field_71146_ci = this.field_71100_bB.func_75116_a();
            this.field_71147_cj = this.field_71100_bB.func_75115_e() == 0.0F;
         }

         if(this.func_110143_aJ() + this.func_110139_bj() != this.field_130068_bO) {
            this.field_130068_bO = this.func_110143_aJ() + this.func_110139_bj();

            for(ScoreObjective scoreobjective : this.func_96123_co().func_96520_a(IScoreObjectiveCriteria.field_96638_f)) {
               this.func_96123_co().func_96529_a(this.func_70005_c_(), scoreobjective).func_96651_a(Arrays.<EntityPlayer>asList(new EntityPlayer[]{this}));
            }
         }

         if(this.field_71067_cb != this.field_71144_ck) {
            this.field_71144_ck = this.field_71067_cb;
            this.field_71135_a.func_147359_a(new S1FPacketSetExperience(this.field_71106_cc, this.field_71067_cb, this.field_71068_ca));
         }

         if(this.field_70173_aa % 20 * 5 == 0 && !this.func_147099_x().func_77443_a(AchievementList.field_150961_L)) {
            this.func_147098_j();
         }

      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Ticking player");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Player being ticked");
         this.func_85029_a(crashreportcategory);
         throw new ReportedException(crashreport);
      }
   }

   protected void func_147098_j() {
      BiomeGenBase biomegenbase = this.field_70170_p.func_180494_b(new BlockPos(MathHelper.func_76128_c(this.field_70165_t), 0, MathHelper.func_76128_c(this.field_70161_v)));
      String s = biomegenbase.field_76791_y;
      JsonSerializableSet jsonserializableset = (JsonSerializableSet)this.func_147099_x().func_150870_b(AchievementList.field_150961_L);
      if(jsonserializableset == null) {
         jsonserializableset = (JsonSerializableSet)this.func_147099_x().func_150872_a(AchievementList.field_150961_L, new JsonSerializableSet());
      }

      jsonserializableset.add(s);
      if(this.func_147099_x().func_77442_b(AchievementList.field_150961_L) && jsonserializableset.size() >= BiomeGenBase.field_150597_n.size()) {
         Set<BiomeGenBase> set = Sets.newHashSet(BiomeGenBase.field_150597_n);

         for(String s1 : jsonserializableset) {
            Iterator<BiomeGenBase> iterator = set.iterator();

            while(iterator.hasNext()) {
               BiomeGenBase biomegenbase1 = (BiomeGenBase)iterator.next();
               if(biomegenbase1.field_76791_y.equals(s1)) {
                  iterator.remove();
               }
            }

            if(set.isEmpty()) {
               break;
            }
         }

         if(set.isEmpty()) {
            this.func_71029_a(AchievementList.field_150961_L);
         }
      }

   }

   public void func_70645_a(DamageSource p_70645_1_) {
      if(this.field_70170_p.func_82736_K().func_82766_b("showDeathMessages")) {
         Team team = this.func_96124_cp();
         if(team != null && team.func_178771_j() != Team.EnumVisible.ALWAYS) {
            if(team.func_178771_j() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS) {
               this.field_71133_b.func_71203_ab().func_177453_a(this, this.func_110142_aN().func_151521_b());
            } else if(team.func_178771_j() == Team.EnumVisible.HIDE_FOR_OWN_TEAM) {
               this.field_71133_b.func_71203_ab().func_177452_b(this, this.func_110142_aN().func_151521_b());
            }
         } else {
            this.field_71133_b.func_71203_ab().func_148539_a(this.func_110142_aN().func_151521_b());
         }
      }

      if(!this.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
         this.field_71071_by.func_70436_m();
      }

      for(ScoreObjective scoreobjective : this.field_70170_p.func_96441_U().func_96520_a(IScoreObjectiveCriteria.field_96642_c)) {
         Score score = this.func_96123_co().func_96529_a(this.func_70005_c_(), scoreobjective);
         score.func_96648_a();
      }

      EntityLivingBase entitylivingbase = this.func_94060_bK();
      if(entitylivingbase != null) {
         EntityList.EntityEggInfo entitylist$entityegginfo = (EntityList.EntityEggInfo)EntityList.field_75627_a.get(Integer.valueOf(EntityList.func_75619_a(entitylivingbase)));
         if(entitylist$entityegginfo != null) {
            this.func_71029_a(entitylist$entityegginfo.field_151513_e);
         }

         entitylivingbase.func_70084_c(this, this.field_70744_aE);
      }

      this.func_71029_a(StatList.field_75960_y);
      this.func_175145_a(StatList.field_180209_h);
      this.func_110142_aN().func_94549_h();
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      if(this.func_180431_b(p_70097_1_)) {
         return false;
      } else {
         boolean flag = this.field_71133_b.func_71262_S() && this.func_175400_cq() && "fall".equals(p_70097_1_.field_76373_n);
         if(!flag && this.field_147101_bU > 0 && p_70097_1_ != DamageSource.field_76380_i) {
            return false;
         } else {
            if(p_70097_1_ instanceof EntityDamageSource) {
               Entity entity = p_70097_1_.func_76346_g();
               if(entity instanceof EntityPlayer && !this.func_96122_a((EntityPlayer)entity)) {
                  return false;
               }

               if(entity instanceof EntityArrow) {
                  EntityArrow entityarrow = (EntityArrow)entity;
                  if(entityarrow.field_70250_c instanceof EntityPlayer && !this.func_96122_a((EntityPlayer)entityarrow.field_70250_c)) {
                     return false;
                  }
               }
            }

            return super.func_70097_a(p_70097_1_, p_70097_2_);
         }
      }
   }

   public boolean func_96122_a(EntityPlayer p_96122_1_) {
      return !this.func_175400_cq()?false:super.func_96122_a(p_96122_1_);
   }

   private boolean func_175400_cq() {
      return this.field_71133_b.func_71219_W();
   }

   public void func_71027_c(int p_71027_1_) {
      if(this.field_71093_bK == 1 && p_71027_1_ == 1) {
         this.func_71029_a(AchievementList.field_76003_C);
         this.field_70170_p.func_72900_e(this);
         this.field_71136_j = true;
         this.field_71135_a.func_147359_a(new S2BPacketChangeGameState(4, 0.0F));
      } else {
         if(this.field_71093_bK == 0 && p_71027_1_ == 1) {
            this.func_71029_a(AchievementList.field_76002_B);
            BlockPos blockpos = this.field_71133_b.func_71218_a(p_71027_1_).func_180504_m();
            if(blockpos != null) {
               this.field_71135_a.func_147364_a((double)blockpos.func_177958_n(), (double)blockpos.func_177956_o(), (double)blockpos.func_177952_p(), 0.0F, 0.0F);
            }

            p_71027_1_ = 1;
         } else {
            this.func_71029_a(AchievementList.field_76029_x);
         }

         this.field_71133_b.func_71203_ab().func_72356_a(this, p_71027_1_);
         this.field_71144_ck = -1;
         this.field_71149_ch = -1.0F;
         this.field_71146_ci = -1;
      }

   }

   public boolean func_174827_a(EntityPlayerMP p_174827_1_) {
      return p_174827_1_.func_175149_v()?this.func_175398_C() == this:(this.func_175149_v()?false:super.func_174827_a(p_174827_1_));
   }

   private void func_147097_b(TileEntity p_147097_1_) {
      if(p_147097_1_ != null) {
         Packet packet = p_147097_1_.func_145844_m();
         if(packet != null) {
            this.field_71135_a.func_147359_a(packet);
         }
      }

   }

   public void func_71001_a(Entity p_71001_1_, int p_71001_2_) {
      super.func_71001_a(p_71001_1_, p_71001_2_);
      this.field_71070_bA.func_75142_b();
   }

   public EntityPlayer.EnumStatus func_180469_a(BlockPos p_180469_1_) {
      EntityPlayer.EnumStatus entityplayer$enumstatus = super.func_180469_a(p_180469_1_);
      if(entityplayer$enumstatus == EntityPlayer.EnumStatus.OK) {
         Packet packet = new S0APacketUseBed(this, p_180469_1_);
         this.func_71121_q().func_73039_n().func_151247_a(this, packet);
         this.field_71135_a.func_147364_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
         this.field_71135_a.func_147359_a(packet);
      }

      return entityplayer$enumstatus;
   }

   public void func_70999_a(boolean p_70999_1_, boolean p_70999_2_, boolean p_70999_3_) {
      if(this.func_70608_bn()) {
         this.func_71121_q().func_73039_n().func_151248_b(this, new S0BPacketAnimation(this, 2));
      }

      super.func_70999_a(p_70999_1_, p_70999_2_, p_70999_3_);
      if(this.field_71135_a != null) {
         this.field_71135_a.func_147364_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
      }

   }

   public void func_70078_a(Entity p_70078_1_) {
      Entity entity = this.field_70154_o;
      super.func_70078_a(p_70078_1_);
      if(p_70078_1_ != entity) {
         this.field_71135_a.func_147359_a(new S1BPacketEntityAttach(0, this, this.field_70154_o));
         this.field_71135_a.func_147364_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
      }

   }

   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
   }

   public void func_71122_b(double p_71122_1_, boolean p_71122_3_) {
      int i = MathHelper.func_76128_c(this.field_70165_t);
      int j = MathHelper.func_76128_c(this.field_70163_u - 0.20000000298023224D);
      int k = MathHelper.func_76128_c(this.field_70161_v);
      BlockPos blockpos = new BlockPos(i, j, k);
      Block block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
      if(block.func_149688_o() == Material.field_151579_a) {
         Block block1 = this.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
         if(block1 instanceof BlockFence || block1 instanceof BlockWall || block1 instanceof BlockFenceGate) {
            blockpos = blockpos.func_177977_b();
            block = this.field_70170_p.func_180495_p(blockpos).func_177230_c();
         }
      }

      super.func_180433_a(p_71122_1_, p_71122_3_, block, blockpos);
   }

   public void func_175141_a(TileEntitySign p_175141_1_) {
      p_175141_1_.func_145912_a(this);
      this.field_71135_a.func_147359_a(new S36PacketSignEditorOpen(p_175141_1_.func_174877_v()));
   }

   private void func_71117_bO() {
      this.field_71139_cq = this.field_71139_cq % 100 + 1;
   }

   public void func_180468_a(IInteractionObject p_180468_1_) {
      this.func_71117_bO();
      this.field_71135_a.func_147359_a(new S2DPacketOpenWindow(this.field_71139_cq, p_180468_1_.func_174875_k(), p_180468_1_.func_145748_c_()));
      this.field_71070_bA = p_180468_1_.func_174876_a(this.field_71071_by, this);
      this.field_71070_bA.field_75152_c = this.field_71139_cq;
      this.field_71070_bA.func_75132_a(this);
   }

   public void func_71007_a(IInventory p_71007_1_) {
      if(this.field_71070_bA != this.field_71069_bz) {
         this.func_71053_j();
      }

      if(p_71007_1_ instanceof ILockableContainer) {
         ILockableContainer ilockablecontainer = (ILockableContainer)p_71007_1_;
         if(ilockablecontainer.func_174893_q_() && !this.func_175146_a(ilockablecontainer.func_174891_i()) && !this.func_175149_v()) {
            this.field_71135_a.func_147359_a(new S02PacketChat(new ChatComponentTranslation("container.isLocked", new Object[]{p_71007_1_.func_145748_c_()}), (byte)2));
            this.field_71135_a.func_147359_a(new S29PacketSoundEffect("random.door_close", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, 1.0F));
            return;
         }
      }

      this.func_71117_bO();
      if(p_71007_1_ instanceof IInteractionObject) {
         this.field_71135_a.func_147359_a(new S2DPacketOpenWindow(this.field_71139_cq, ((IInteractionObject)p_71007_1_).func_174875_k(), p_71007_1_.func_145748_c_(), p_71007_1_.func_70302_i_()));
         this.field_71070_bA = ((IInteractionObject)p_71007_1_).func_174876_a(this.field_71071_by, this);
      } else {
         this.field_71135_a.func_147359_a(new S2DPacketOpenWindow(this.field_71139_cq, "minecraft:container", p_71007_1_.func_145748_c_(), p_71007_1_.func_70302_i_()));
         this.field_71070_bA = new ContainerChest(this.field_71071_by, p_71007_1_, this);
      }

      this.field_71070_bA.field_75152_c = this.field_71139_cq;
      this.field_71070_bA.func_75132_a(this);
   }

   public void func_180472_a(IMerchant p_180472_1_) {
      this.func_71117_bO();
      this.field_71070_bA = new ContainerMerchant(this.field_71071_by, p_180472_1_, this.field_70170_p);
      this.field_71070_bA.field_75152_c = this.field_71139_cq;
      this.field_71070_bA.func_75132_a(this);
      IInventory iinventory = ((ContainerMerchant)this.field_71070_bA).func_75174_d();
      IChatComponent ichatcomponent = p_180472_1_.func_145748_c_();
      this.field_71135_a.func_147359_a(new S2DPacketOpenWindow(this.field_71139_cq, "minecraft:villager", ichatcomponent, iinventory.func_70302_i_()));
      MerchantRecipeList merchantrecipelist = p_180472_1_.func_70934_b(this);
      if(merchantrecipelist != null) {
         PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
         packetbuffer.writeInt(this.field_71139_cq);
         merchantrecipelist.func_151391_a(packetbuffer);
         this.field_71135_a.func_147359_a(new S3FPacketCustomPayload("MC|TrList", packetbuffer));
      }

   }

   public void func_110298_a(EntityHorse p_110298_1_, IInventory p_110298_2_) {
      if(this.field_71070_bA != this.field_71069_bz) {
         this.func_71053_j();
      }

      this.func_71117_bO();
      this.field_71135_a.func_147359_a(new S2DPacketOpenWindow(this.field_71139_cq, "EntityHorse", p_110298_2_.func_145748_c_(), p_110298_2_.func_70302_i_(), p_110298_1_.func_145782_y()));
      this.field_71070_bA = new ContainerHorseInventory(this.field_71071_by, p_110298_2_, p_110298_1_, this);
      this.field_71070_bA.field_75152_c = this.field_71139_cq;
      this.field_71070_bA.func_75132_a(this);
   }

   public void func_71048_c(ItemStack p_71048_1_) {
      Item item = p_71048_1_.func_77973_b();
      if(item == Items.field_151164_bB) {
         this.field_71135_a.func_147359_a(new S3FPacketCustomPayload("MC|BOpen", new PacketBuffer(Unpooled.buffer())));
      }

   }

   public void func_71111_a(Container p_71111_1_, int p_71111_2_, ItemStack p_71111_3_) {
      if(!(p_71111_1_.func_75139_a(p_71111_2_) instanceof SlotCrafting)) {
         if(!this.field_71137_h) {
            this.field_71135_a.func_147359_a(new S2FPacketSetSlot(p_71111_1_.field_75152_c, p_71111_2_, p_71111_3_));
         }
      }
   }

   public void func_71120_a(Container p_71120_1_) {
      this.func_71110_a(p_71120_1_, p_71120_1_.func_75138_a());
   }

   public void func_71110_a(Container p_71110_1_, List<ItemStack> p_71110_2_) {
      this.field_71135_a.func_147359_a(new S30PacketWindowItems(p_71110_1_.field_75152_c, p_71110_2_));
      this.field_71135_a.func_147359_a(new S2FPacketSetSlot(-1, -1, this.field_71071_by.func_70445_o()));
   }

   public void func_71112_a(Container p_71112_1_, int p_71112_2_, int p_71112_3_) {
      this.field_71135_a.func_147359_a(new S31PacketWindowProperty(p_71112_1_.field_75152_c, p_71112_2_, p_71112_3_));
   }

   public void func_175173_a(Container p_175173_1_, IInventory p_175173_2_) {
      for(int i = 0; i < p_175173_2_.func_174890_g(); ++i) {
         this.field_71135_a.func_147359_a(new S31PacketWindowProperty(p_175173_1_.field_75152_c, i, p_175173_2_.func_174887_a_(i)));
      }

   }

   public void func_71053_j() {
      this.field_71135_a.func_147359_a(new S2EPacketCloseWindow(this.field_71070_bA.field_75152_c));
      this.func_71128_l();
   }

   public void func_71113_k() {
      if(!this.field_71137_h) {
         this.field_71135_a.func_147359_a(new S2FPacketSetSlot(-1, -1, this.field_71071_by.func_70445_o()));
      }
   }

   public void func_71128_l() {
      this.field_71070_bA.func_75134_a(this);
      this.field_71070_bA = this.field_71069_bz;
   }

   public void func_110430_a(float p_110430_1_, float p_110430_2_, boolean p_110430_3_, boolean p_110430_4_) {
      if(this.field_70154_o != null) {
         if(p_110430_1_ >= -1.0F && p_110430_1_ <= 1.0F) {
            this.field_70702_br = p_110430_1_;
         }

         if(p_110430_2_ >= -1.0F && p_110430_2_ <= 1.0F) {
            this.field_70701_bs = p_110430_2_;
         }

         this.field_70703_bu = p_110430_3_;
         this.func_70095_a(p_110430_4_);
      }

   }

   public void func_71064_a(StatBase p_71064_1_, int p_71064_2_) {
      if(p_71064_1_ != null) {
         this.field_147103_bO.func_150871_b(this, p_71064_1_, p_71064_2_);

         for(ScoreObjective scoreobjective : this.func_96123_co().func_96520_a(p_71064_1_.func_150952_k())) {
            this.func_96123_co().func_96529_a(this.func_70005_c_(), scoreobjective).func_96649_a(p_71064_2_);
         }

         if(this.field_147103_bO.func_150879_e()) {
            this.field_147103_bO.func_150876_a(this);
         }

      }
   }

   public void func_175145_a(StatBase p_175145_1_) {
      if(p_175145_1_ != null) {
         this.field_147103_bO.func_150873_a(this, p_175145_1_, 0);

         for(ScoreObjective scoreobjective : this.func_96123_co().func_96520_a(p_175145_1_.func_150952_k())) {
            this.func_96123_co().func_96529_a(this.func_70005_c_(), scoreobjective).func_96647_c(0);
         }

         if(this.field_147103_bO.func_150879_e()) {
            this.field_147103_bO.func_150876_a(this);
         }

      }
   }

   public void func_71123_m() {
      if(this.field_70153_n != null) {
         this.field_70153_n.func_70078_a(this);
      }

      if(this.field_71083_bS) {
         this.func_70999_a(true, false, false);
      }

   }

   public void func_71118_n() {
      this.field_71149_ch = -1.0E8F;
   }

   public void func_146105_b(IChatComponent p_146105_1_) {
      this.field_71135_a.func_147359_a(new S02PacketChat(p_146105_1_));
   }

   protected void func_71036_o() {
      this.field_71135_a.func_147359_a(new S19PacketEntityStatus(this, (byte)9));
      super.func_71036_o();
   }

   public void func_71008_a(ItemStack p_71008_1_, int p_71008_2_) {
      super.func_71008_a(p_71008_1_, p_71008_2_);
      if(p_71008_1_ != null && p_71008_1_.func_77973_b() != null && p_71008_1_.func_77973_b().func_77661_b(p_71008_1_) == EnumAction.EAT) {
         this.func_71121_q().func_73039_n().func_151248_b(this, new S0BPacketAnimation(this, 3));
      }

   }

   public void func_71049_a(EntityPlayer p_71049_1_, boolean p_71049_2_) {
      super.func_71049_a(p_71049_1_, p_71049_2_);
      this.field_71144_ck = -1;
      this.field_71149_ch = -1.0F;
      this.field_71146_ci = -1;
      this.field_71130_g.addAll(((EntityPlayerMP)p_71049_1_).field_71130_g);
   }

   protected void func_70670_a(PotionEffect p_70670_1_) {
      super.func_70670_a(p_70670_1_);
      this.field_71135_a.func_147359_a(new S1DPacketEntityEffect(this.func_145782_y(), p_70670_1_));
   }

   protected void func_70695_b(PotionEffect p_70695_1_, boolean p_70695_2_) {
      super.func_70695_b(p_70695_1_, p_70695_2_);
      this.field_71135_a.func_147359_a(new S1DPacketEntityEffect(this.func_145782_y(), p_70695_1_));
   }

   protected void func_70688_c(PotionEffect p_70688_1_) {
      super.func_70688_c(p_70688_1_);
      this.field_71135_a.func_147359_a(new S1EPacketRemoveEntityEffect(this.func_145782_y(), p_70688_1_));
   }

   public void func_70634_a(double p_70634_1_, double p_70634_3_, double p_70634_5_) {
      this.field_71135_a.func_147364_a(p_70634_1_, p_70634_3_, p_70634_5_, this.field_70177_z, this.field_70125_A);
   }

   public void func_71009_b(Entity p_71009_1_) {
      this.func_71121_q().func_73039_n().func_151248_b(this, new S0BPacketAnimation(p_71009_1_, 4));
   }

   public void func_71047_c(Entity p_71047_1_) {
      this.func_71121_q().func_73039_n().func_151248_b(this, new S0BPacketAnimation(p_71047_1_, 5));
   }

   public void func_71016_p() {
      if(this.field_71135_a != null) {
         this.field_71135_a.func_147359_a(new S39PacketPlayerAbilities(this.field_71075_bZ));
         this.func_175135_B();
      }
   }

   public WorldServer func_71121_q() {
      return (WorldServer)this.field_70170_p;
   }

   public void func_71033_a(WorldSettings.GameType p_71033_1_) {
      this.field_71134_c.func_73076_a(p_71033_1_);
      this.field_71135_a.func_147359_a(new S2BPacketChangeGameState(3, (float)p_71033_1_.func_77148_a()));
      if(p_71033_1_ == WorldSettings.GameType.SPECTATOR) {
         this.func_70078_a((Entity)null);
      } else {
         this.func_175399_e(this);
      }

      this.func_71016_p();
      this.func_175136_bO();
   }

   public boolean func_175149_v() {
      return this.field_71134_c.func_73081_b() == WorldSettings.GameType.SPECTATOR;
   }

   public void func_145747_a(IChatComponent p_145747_1_) {
      this.field_71135_a.func_147359_a(new S02PacketChat(p_145747_1_));
   }

   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
      if("seed".equals(p_70003_2_) && !this.field_71133_b.func_71262_S()) {
         return true;
      } else if(!"tell".equals(p_70003_2_) && !"help".equals(p_70003_2_) && !"me".equals(p_70003_2_) && !"trigger".equals(p_70003_2_)) {
         if(this.field_71133_b.func_71203_ab().func_152596_g(this.func_146103_bH())) {
            UserListOpsEntry userlistopsentry = (UserListOpsEntry)this.field_71133_b.func_71203_ab().func_152603_m().func_152683_b(this.func_146103_bH());
            return userlistopsentry != null?userlistopsentry.func_152644_a() >= p_70003_1_:this.field_71133_b.func_110455_j() >= p_70003_1_;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public String func_71114_r() {
      String s = this.field_71135_a.field_147371_a.func_74430_c().toString();
      s = s.substring(s.indexOf("/") + 1);
      s = s.substring(0, s.indexOf(":"));
      return s;
   }

   public void func_147100_a(C15PacketClientSettings p_147100_1_) {
      this.field_71148_cg = p_147100_1_.func_149524_c();
      this.field_71143_cn = p_147100_1_.func_149523_e();
      this.field_71140_co = p_147100_1_.func_149520_f();
      this.func_70096_w().func_75692_b(10, Byte.valueOf((byte)p_147100_1_.func_149521_d()));
   }

   public EntityPlayer.EnumChatVisibility func_147096_v() {
      return this.field_71143_cn;
   }

   public void func_175397_a(String p_175397_1_, String p_175397_2_) {
      this.field_71135_a.func_147359_a(new S48PacketResourcePackSend(p_175397_1_, p_175397_2_));
   }

   public BlockPos func_180425_c() {
      return new BlockPos(this.field_70165_t, this.field_70163_u + 0.5D, this.field_70161_v);
   }

   public void func_143004_u() {
      this.field_143005_bX = MinecraftServer.func_130071_aq();
   }

   public StatisticsFile func_147099_x() {
      return this.field_147103_bO;
   }

   public void func_152339_d(Entity p_152339_1_) {
      if(p_152339_1_ instanceof EntityPlayer) {
         this.field_71135_a.func_147359_a(new S13PacketDestroyEntities(new int[]{p_152339_1_.func_145782_y()}));
      } else {
         this.field_71130_g.add(Integer.valueOf(p_152339_1_.func_145782_y()));
      }

   }

   protected void func_175135_B() {
      if(this.func_175149_v()) {
         this.func_175133_bi();
         this.func_82142_c(true);
      } else {
         super.func_175135_B();
      }

      this.func_71121_q().func_73039_n().func_180245_a(this);
   }

   public Entity func_175398_C() {
      return (Entity)(this.field_175401_bS == null?this:this.field_175401_bS);
   }

   public void func_175399_e(Entity p_175399_1_) {
      Entity entity = this.func_175398_C();
      this.field_175401_bS = (Entity)(p_175399_1_ == null?this:p_175399_1_);
      if(entity != this.field_175401_bS) {
         this.field_71135_a.func_147359_a(new S43PacketCamera(this.field_175401_bS));
         this.func_70634_a(this.field_175401_bS.field_70165_t, this.field_175401_bS.field_70163_u, this.field_175401_bS.field_70161_v);
      }

   }

   public void func_71059_n(Entity p_71059_1_) {
      if(this.field_71134_c.func_73081_b() == WorldSettings.GameType.SPECTATOR) {
         this.func_175399_e(p_71059_1_);
      } else {
         super.func_71059_n(p_71059_1_);
      }

   }

   public long func_154331_x() {
      return this.field_143005_bX;
   }

   public IChatComponent func_175396_E() {
      return null;
   }
}
