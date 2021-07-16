package net.minecraft.server.management;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerManager {
   private static final Logger field_152627_a = LogManager.getLogger();
   private final WorldServer field_72701_a;
   private final List<EntityPlayerMP> field_72699_b = Lists.<EntityPlayerMP>newArrayList();
   private final LongHashMap<PlayerManager.PlayerInstance> field_72700_c = new LongHashMap();
   private final List<PlayerManager.PlayerInstance> field_72697_d = Lists.<PlayerManager.PlayerInstance>newArrayList();
   private final List<PlayerManager.PlayerInstance> field_111193_e = Lists.<PlayerManager.PlayerInstance>newArrayList();
   private int field_72698_e;
   private long field_111192_g;
   private final int[][] field_72696_f = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

   public PlayerManager(WorldServer p_i1176_1_) {
      this.field_72701_a = p_i1176_1_;
      this.func_152622_a(p_i1176_1_.func_73046_m().func_71203_ab().func_72395_o());
   }

   public WorldServer func_72688_a() {
      return this.field_72701_a;
   }

   public void func_72693_b() {
      long i = this.field_72701_a.func_82737_E();
      if(i - this.field_111192_g > 8000L) {
         this.field_111192_g = i;

         for(int j = 0; j < this.field_111193_e.size(); ++j) {
            PlayerManager.PlayerInstance playermanager$playerinstance = (PlayerManager.PlayerInstance)this.field_111193_e.get(j);
            playermanager$playerinstance.func_73254_a();
            playermanager$playerinstance.func_111194_a();
         }
      } else {
         for(int k = 0; k < this.field_72697_d.size(); ++k) {
            PlayerManager.PlayerInstance playermanager$playerinstance1 = (PlayerManager.PlayerInstance)this.field_72697_d.get(k);
            playermanager$playerinstance1.func_73254_a();
         }
      }

      this.field_72697_d.clear();
      if(this.field_72699_b.isEmpty()) {
         WorldProvider worldprovider = this.field_72701_a.field_73011_w;
         if(!worldprovider.func_76567_e()) {
            this.field_72701_a.field_73059_b.func_73240_a();
         }
      }

   }

   public boolean func_152621_a(int p_152621_1_, int p_152621_2_) {
      long i = (long)p_152621_1_ + 2147483647L | (long)p_152621_2_ + 2147483647L << 32;
      return this.field_72700_c.func_76164_a(i) != null;
   }

   private PlayerManager.PlayerInstance func_72690_a(int p_72690_1_, int p_72690_2_, boolean p_72690_3_) {
      long i = (long)p_72690_1_ + 2147483647L | (long)p_72690_2_ + 2147483647L << 32;
      PlayerManager.PlayerInstance playermanager$playerinstance = (PlayerManager.PlayerInstance)this.field_72700_c.func_76164_a(i);
      if(playermanager$playerinstance == null && p_72690_3_) {
         playermanager$playerinstance = new PlayerManager.PlayerInstance(p_72690_1_, p_72690_2_);
         this.field_72700_c.func_76163_a(i, playermanager$playerinstance);
         this.field_111193_e.add(playermanager$playerinstance);
      }

      return playermanager$playerinstance;
   }

   public void func_180244_a(BlockPos p_180244_1_) {
      int i = p_180244_1_.func_177958_n() >> 4;
      int j = p_180244_1_.func_177952_p() >> 4;
      PlayerManager.PlayerInstance playermanager$playerinstance = this.func_72690_a(i, j, false);
      if(playermanager$playerinstance != null) {
         playermanager$playerinstance.func_151253_a(p_180244_1_.func_177958_n() & 15, p_180244_1_.func_177956_o(), p_180244_1_.func_177952_p() & 15);
      }

   }

   public void func_72683_a(EntityPlayerMP p_72683_1_) {
      int i = (int)p_72683_1_.field_70165_t >> 4;
      int j = (int)p_72683_1_.field_70161_v >> 4;
      p_72683_1_.field_71131_d = p_72683_1_.field_70165_t;
      p_72683_1_.field_71132_e = p_72683_1_.field_70161_v;

      for(int k = i - this.field_72698_e; k <= i + this.field_72698_e; ++k) {
         for(int l = j - this.field_72698_e; l <= j + this.field_72698_e; ++l) {
            this.func_72690_a(k, l, true).func_73255_a(p_72683_1_);
         }
      }

      this.field_72699_b.add(p_72683_1_);
      this.func_72691_b(p_72683_1_);
   }

   public void func_72691_b(EntityPlayerMP p_72691_1_) {
      List<ChunkCoordIntPair> list = Lists.newArrayList(p_72691_1_.field_71129_f);
      int i = 0;
      int j = this.field_72698_e;
      int k = (int)p_72691_1_.field_70165_t >> 4;
      int l = (int)p_72691_1_.field_70161_v >> 4;
      int i1 = 0;
      int j1 = 0;
      ChunkCoordIntPair chunkcoordintpair = this.func_72690_a(k, l, true).field_73264_c;
      p_72691_1_.field_71129_f.clear();
      if(list.contains(chunkcoordintpair)) {
         p_72691_1_.field_71129_f.add(chunkcoordintpair);
      }

      for(int k1 = 1; k1 <= j * 2; ++k1) {
         for(int l1 = 0; l1 < 2; ++l1) {
            int[] aint = this.field_72696_f[i++ % 4];

            for(int i2 = 0; i2 < k1; ++i2) {
               i1 += aint[0];
               j1 += aint[1];
               chunkcoordintpair = this.func_72690_a(k + i1, l + j1, true).field_73264_c;
               if(list.contains(chunkcoordintpair)) {
                  p_72691_1_.field_71129_f.add(chunkcoordintpair);
               }
            }
         }
      }

      i = i % 4;

      for(int j2 = 0; j2 < j * 2; ++j2) {
         i1 += this.field_72696_f[i][0];
         j1 += this.field_72696_f[i][1];
         chunkcoordintpair = this.func_72690_a(k + i1, l + j1, true).field_73264_c;
         if(list.contains(chunkcoordintpair)) {
            p_72691_1_.field_71129_f.add(chunkcoordintpair);
         }
      }

   }

   public void func_72695_c(EntityPlayerMP p_72695_1_) {
      int i = (int)p_72695_1_.field_71131_d >> 4;
      int j = (int)p_72695_1_.field_71132_e >> 4;

      for(int k = i - this.field_72698_e; k <= i + this.field_72698_e; ++k) {
         for(int l = j - this.field_72698_e; l <= j + this.field_72698_e; ++l) {
            PlayerManager.PlayerInstance playermanager$playerinstance = this.func_72690_a(k, l, false);
            if(playermanager$playerinstance != null) {
               playermanager$playerinstance.func_73252_b(p_72695_1_);
            }
         }
      }

      this.field_72699_b.remove(p_72695_1_);
   }

   private boolean func_72684_a(int p_72684_1_, int p_72684_2_, int p_72684_3_, int p_72684_4_, int p_72684_5_) {
      int i = p_72684_1_ - p_72684_3_;
      int j = p_72684_2_ - p_72684_4_;
      return i >= -p_72684_5_ && i <= p_72684_5_?j >= -p_72684_5_ && j <= p_72684_5_:false;
   }

   public void func_72685_d(EntityPlayerMP p_72685_1_) {
      int i = (int)p_72685_1_.field_70165_t >> 4;
      int j = (int)p_72685_1_.field_70161_v >> 4;
      double d0 = p_72685_1_.field_71131_d - p_72685_1_.field_70165_t;
      double d1 = p_72685_1_.field_71132_e - p_72685_1_.field_70161_v;
      double d2 = d0 * d0 + d1 * d1;
      if(d2 >= 64.0D) {
         int k = (int)p_72685_1_.field_71131_d >> 4;
         int l = (int)p_72685_1_.field_71132_e >> 4;
         int i1 = this.field_72698_e;
         int j1 = i - k;
         int k1 = j - l;
         if(j1 != 0 || k1 != 0) {
            for(int l1 = i - i1; l1 <= i + i1; ++l1) {
               for(int i2 = j - i1; i2 <= j + i1; ++i2) {
                  if(!this.func_72684_a(l1, i2, k, l, i1)) {
                     this.func_72690_a(l1, i2, true).func_73255_a(p_72685_1_);
                  }

                  if(!this.func_72684_a(l1 - j1, i2 - k1, i, j, i1)) {
                     PlayerManager.PlayerInstance playermanager$playerinstance = this.func_72690_a(l1 - j1, i2 - k1, false);
                     if(playermanager$playerinstance != null) {
                        playermanager$playerinstance.func_73252_b(p_72685_1_);
                     }
                  }
               }
            }

            this.func_72691_b(p_72685_1_);
            p_72685_1_.field_71131_d = p_72685_1_.field_70165_t;
            p_72685_1_.field_71132_e = p_72685_1_.field_70161_v;
         }
      }
   }

   public boolean func_72694_a(EntityPlayerMP p_72694_1_, int p_72694_2_, int p_72694_3_) {
      PlayerManager.PlayerInstance playermanager$playerinstance = this.func_72690_a(p_72694_2_, p_72694_3_, false);
      return playermanager$playerinstance != null && playermanager$playerinstance.field_73263_b.contains(p_72694_1_) && !p_72694_1_.field_71129_f.contains(playermanager$playerinstance.field_73264_c);
   }

   public void func_152622_a(int p_152622_1_) {
      p_152622_1_ = MathHelper.func_76125_a(p_152622_1_, 3, 32);
      if(p_152622_1_ != this.field_72698_e) {
         int i = p_152622_1_ - this.field_72698_e;

         for(EntityPlayerMP entityplayermp : Lists.newArrayList(this.field_72699_b)) {
            int j = (int)entityplayermp.field_70165_t >> 4;
            int k = (int)entityplayermp.field_70161_v >> 4;
            if(i > 0) {
               for(int j1 = j - p_152622_1_; j1 <= j + p_152622_1_; ++j1) {
                  for(int k1 = k - p_152622_1_; k1 <= k + p_152622_1_; ++k1) {
                     PlayerManager.PlayerInstance playermanager$playerinstance = this.func_72690_a(j1, k1, true);
                     if(!playermanager$playerinstance.field_73263_b.contains(entityplayermp)) {
                        playermanager$playerinstance.func_73255_a(entityplayermp);
                     }
                  }
               }
            } else {
               for(int l = j - this.field_72698_e; l <= j + this.field_72698_e; ++l) {
                  for(int i1 = k - this.field_72698_e; i1 <= k + this.field_72698_e; ++i1) {
                     if(!this.func_72684_a(l, i1, j, k, p_152622_1_)) {
                        this.func_72690_a(l, i1, true).func_73252_b(entityplayermp);
                     }
                  }
               }
            }
         }

         this.field_72698_e = p_152622_1_;
      }
   }

   public static int func_72686_a(int p_72686_0_) {
      return p_72686_0_ * 16 - 16;
   }

   class PlayerInstance {
      private final List<EntityPlayerMP> field_73263_b = Lists.<EntityPlayerMP>newArrayList();
      private final ChunkCoordIntPair field_73264_c;
      private short[] field_151254_d = new short[64];
      private int field_73262_e;
      private int field_73260_f;
      private long field_111198_g;

      public PlayerInstance(int p_i1518_2_, int p_i1518_3_) {
         this.field_73264_c = new ChunkCoordIntPair(p_i1518_2_, p_i1518_3_);
         PlayerManager.this.func_72688_a().field_73059_b.func_73158_c(p_i1518_2_, p_i1518_3_);
      }

      public void func_73255_a(EntityPlayerMP p_73255_1_) {
         if(this.field_73263_b.contains(p_73255_1_)) {
            PlayerManager.field_152627_a.debug("Failed to add player. {} already is in chunk {}, {}", new Object[]{p_73255_1_, Integer.valueOf(this.field_73264_c.field_77276_a), Integer.valueOf(this.field_73264_c.field_77275_b)});
         } else {
            if(this.field_73263_b.isEmpty()) {
               this.field_111198_g = PlayerManager.this.field_72701_a.func_82737_E();
            }

            this.field_73263_b.add(p_73255_1_);
            p_73255_1_.field_71129_f.add(this.field_73264_c);
         }
      }

      public void func_73252_b(EntityPlayerMP p_73252_1_) {
         if(this.field_73263_b.contains(p_73252_1_)) {
            Chunk chunk = PlayerManager.this.field_72701_a.func_72964_e(this.field_73264_c.field_77276_a, this.field_73264_c.field_77275_b);
            if(chunk.func_150802_k()) {
               p_73252_1_.field_71135_a.func_147359_a(new S21PacketChunkData(chunk, true, 0));
            }

            this.field_73263_b.remove(p_73252_1_);
            p_73252_1_.field_71129_f.remove(this.field_73264_c);
            if(this.field_73263_b.isEmpty()) {
               long i = (long)this.field_73264_c.field_77276_a + 2147483647L | (long)this.field_73264_c.field_77275_b + 2147483647L << 32;
               this.func_111196_a(chunk);
               PlayerManager.this.field_72700_c.func_76159_d(i);
               PlayerManager.this.field_111193_e.remove(this);
               if(this.field_73262_e > 0) {
                  PlayerManager.this.field_72697_d.remove(this);
               }

               PlayerManager.this.func_72688_a().field_73059_b.func_73241_b(this.field_73264_c.field_77276_a, this.field_73264_c.field_77275_b);
            }

         }
      }

      public void func_111194_a() {
         this.func_111196_a(PlayerManager.this.field_72701_a.func_72964_e(this.field_73264_c.field_77276_a, this.field_73264_c.field_77275_b));
      }

      private void func_111196_a(Chunk p_111196_1_) {
         p_111196_1_.func_177415_c(p_111196_1_.func_177416_w() + PlayerManager.this.field_72701_a.func_82737_E() - this.field_111198_g);
         this.field_111198_g = PlayerManager.this.field_72701_a.func_82737_E();
      }

      public void func_151253_a(int p_151253_1_, int p_151253_2_, int p_151253_3_) {
         if(this.field_73262_e == 0) {
            PlayerManager.this.field_72697_d.add(this);
         }

         this.field_73260_f |= 1 << (p_151253_2_ >> 4);
         if(this.field_73262_e < 64) {
            short short1 = (short)(p_151253_1_ << 12 | p_151253_3_ << 8 | p_151253_2_);

            for(int i = 0; i < this.field_73262_e; ++i) {
               if(this.field_151254_d[i] == short1) {
                  return;
               }
            }

            this.field_151254_d[this.field_73262_e++] = short1;
         }

      }

      public void func_151251_a(Packet p_151251_1_) {
         for(int i = 0; i < this.field_73263_b.size(); ++i) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)this.field_73263_b.get(i);
            if(!entityplayermp.field_71129_f.contains(this.field_73264_c)) {
               entityplayermp.field_71135_a.func_147359_a(p_151251_1_);
            }
         }

      }

      public void func_73254_a() {
         if(this.field_73262_e != 0) {
            if(this.field_73262_e == 1) {
               int i = (this.field_151254_d[0] >> 12 & 15) + this.field_73264_c.field_77276_a * 16;
               int j = this.field_151254_d[0] & 255;
               int k = (this.field_151254_d[0] >> 8 & 15) + this.field_73264_c.field_77275_b * 16;
               BlockPos blockpos = new BlockPos(i, j, k);
               this.func_151251_a(new S23PacketBlockChange(PlayerManager.this.field_72701_a, blockpos));
               if(PlayerManager.this.field_72701_a.func_180495_p(blockpos).func_177230_c().func_149716_u()) {
                  this.func_151252_a(PlayerManager.this.field_72701_a.func_175625_s(blockpos));
               }
            } else if(this.field_73262_e == 64) {
               int i1 = this.field_73264_c.field_77276_a * 16;
               int k1 = this.field_73264_c.field_77275_b * 16;
               this.func_151251_a(new S21PacketChunkData(PlayerManager.this.field_72701_a.func_72964_e(this.field_73264_c.field_77276_a, this.field_73264_c.field_77275_b), false, this.field_73260_f));

               for(int i2 = 0; i2 < 16; ++i2) {
                  if((this.field_73260_f & 1 << i2) != 0) {
                     int k2 = i2 << 4;
                     List<TileEntity> list = PlayerManager.this.field_72701_a.func_147486_a(i1, k2, k1, i1 + 16, k2 + 16, k1 + 16);

                     for(int l = 0; l < list.size(); ++l) {
                        this.func_151252_a((TileEntity)list.get(l));
                     }
                  }
               }
            } else {
               this.func_151251_a(new S22PacketMultiBlockChange(this.field_73262_e, this.field_151254_d, PlayerManager.this.field_72701_a.func_72964_e(this.field_73264_c.field_77276_a, this.field_73264_c.field_77275_b)));

               for(int j1 = 0; j1 < this.field_73262_e; ++j1) {
                  int l1 = (this.field_151254_d[j1] >> 12 & 15) + this.field_73264_c.field_77276_a * 16;
                  int j2 = this.field_151254_d[j1] & 255;
                  int l2 = (this.field_151254_d[j1] >> 8 & 15) + this.field_73264_c.field_77275_b * 16;
                  BlockPos blockpos1 = new BlockPos(l1, j2, l2);
                  if(PlayerManager.this.field_72701_a.func_180495_p(blockpos1).func_177230_c().func_149716_u()) {
                     this.func_151252_a(PlayerManager.this.field_72701_a.func_175625_s(blockpos1));
                  }
               }
            }

            this.field_73262_e = 0;
            this.field_73260_f = 0;
         }
      }

      private void func_151252_a(TileEntity p_151252_1_) {
         if(p_151252_1_ != null) {
            Packet packet = p_151252_1_.func_145844_m();
            if(packet != null) {
               this.func_151251_a(packet);
            }
         }

      }
   }
}
