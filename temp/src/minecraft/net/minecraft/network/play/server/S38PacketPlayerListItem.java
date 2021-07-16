package net.minecraft.network.play.server;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;

public class S38PacketPlayerListItem implements Packet<INetHandlerPlayClient> {
   private S38PacketPlayerListItem.Action field_179770_a;
   private final List<S38PacketPlayerListItem.AddPlayerData> field_179769_b = Lists.<S38PacketPlayerListItem.AddPlayerData>newArrayList();

   public S38PacketPlayerListItem() {
   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action p_i45967_1_, EntityPlayerMP... p_i45967_2_) {
      this.field_179770_a = p_i45967_1_;

      for(EntityPlayerMP entityplayermp : p_i45967_2_) {
         this.field_179769_b.add(new S38PacketPlayerListItem.AddPlayerData(entityplayermp.func_146103_bH(), entityplayermp.field_71138_i, entityplayermp.field_71134_c.func_73081_b(), entityplayermp.func_175396_E()));
      }

   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action p_i45968_1_, Iterable<EntityPlayerMP> p_i45968_2_) {
      this.field_179770_a = p_i45968_1_;

      for(EntityPlayerMP entityplayermp : p_i45968_2_) {
         this.field_179769_b.add(new S38PacketPlayerListItem.AddPlayerData(entityplayermp.func_146103_bH(), entityplayermp.field_71138_i, entityplayermp.field_71134_c.func_73081_b(), entityplayermp.func_175396_E()));
      }

   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179770_a = (S38PacketPlayerListItem.Action)p_148837_1_.func_179257_a(S38PacketPlayerListItem.Action.class);
      int i = p_148837_1_.func_150792_a();

      for(int j = 0; j < i; ++j) {
         GameProfile gameprofile = null;
         int k = 0;
         WorldSettings.GameType worldsettings$gametype = null;
         IChatComponent ichatcomponent = null;
         switch(this.field_179770_a) {
         case ADD_PLAYER:
            gameprofile = new GameProfile(p_148837_1_.func_179253_g(), p_148837_1_.func_150789_c(16));
            int l = p_148837_1_.func_150792_a();
            int i1 = 0;

            for(; i1 < l; ++i1) {
               String s = p_148837_1_.func_150789_c(32767);
               String s1 = p_148837_1_.func_150789_c(32767);
               if(p_148837_1_.readBoolean()) {
                  gameprofile.getProperties().put(s, new Property(s, s1, p_148837_1_.func_150789_c(32767)));
               } else {
                  gameprofile.getProperties().put(s, new Property(s, s1));
               }
            }

            worldsettings$gametype = WorldSettings.GameType.func_77146_a(p_148837_1_.func_150792_a());
            k = p_148837_1_.func_150792_a();
            if(p_148837_1_.readBoolean()) {
               ichatcomponent = p_148837_1_.func_179258_d();
            }
            break;
         case UPDATE_GAME_MODE:
            gameprofile = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
            worldsettings$gametype = WorldSettings.GameType.func_77146_a(p_148837_1_.func_150792_a());
            break;
         case UPDATE_LATENCY:
            gameprofile = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
            k = p_148837_1_.func_150792_a();
            break;
         case UPDATE_DISPLAY_NAME:
            gameprofile = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
            if(p_148837_1_.readBoolean()) {
               ichatcomponent = p_148837_1_.func_179258_d();
            }
            break;
         case REMOVE_PLAYER:
            gameprofile = new GameProfile(p_148837_1_.func_179253_g(), (String)null);
         }

         this.field_179769_b.add(new S38PacketPlayerListItem.AddPlayerData(gameprofile, k, worldsettings$gametype, ichatcomponent));
      }

   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_179249_a(this.field_179770_a);
      p_148840_1_.func_150787_b(this.field_179769_b.size());

      for(S38PacketPlayerListItem.AddPlayerData s38packetplayerlistitem$addplayerdata : this.field_179769_b) {
         switch(this.field_179770_a) {
         case ADD_PLAYER:
            p_148840_1_.func_179252_a(s38packetplayerlistitem$addplayerdata.func_179962_a().getId());
            p_148840_1_.func_180714_a(s38packetplayerlistitem$addplayerdata.func_179962_a().getName());
            p_148840_1_.func_150787_b(s38packetplayerlistitem$addplayerdata.func_179962_a().getProperties().size());

            for(Property property : s38packetplayerlistitem$addplayerdata.func_179962_a().getProperties().values()) {
               p_148840_1_.func_180714_a(property.getName());
               p_148840_1_.func_180714_a(property.getValue());
               if(property.hasSignature()) {
                  p_148840_1_.writeBoolean(true);
                  p_148840_1_.func_180714_a(property.getSignature());
               } else {
                  p_148840_1_.writeBoolean(false);
               }
            }

            p_148840_1_.func_150787_b(s38packetplayerlistitem$addplayerdata.func_179960_c().func_77148_a());
            p_148840_1_.func_150787_b(s38packetplayerlistitem$addplayerdata.func_179963_b());
            if(s38packetplayerlistitem$addplayerdata.func_179961_d() == null) {
               p_148840_1_.writeBoolean(false);
            } else {
               p_148840_1_.writeBoolean(true);
               p_148840_1_.func_179256_a(s38packetplayerlistitem$addplayerdata.func_179961_d());
            }
            break;
         case UPDATE_GAME_MODE:
            p_148840_1_.func_179252_a(s38packetplayerlistitem$addplayerdata.func_179962_a().getId());
            p_148840_1_.func_150787_b(s38packetplayerlistitem$addplayerdata.func_179960_c().func_77148_a());
            break;
         case UPDATE_LATENCY:
            p_148840_1_.func_179252_a(s38packetplayerlistitem$addplayerdata.func_179962_a().getId());
            p_148840_1_.func_150787_b(s38packetplayerlistitem$addplayerdata.func_179963_b());
            break;
         case UPDATE_DISPLAY_NAME:
            p_148840_1_.func_179252_a(s38packetplayerlistitem$addplayerdata.func_179962_a().getId());
            if(s38packetplayerlistitem$addplayerdata.func_179961_d() == null) {
               p_148840_1_.writeBoolean(false);
            } else {
               p_148840_1_.writeBoolean(true);
               p_148840_1_.func_179256_a(s38packetplayerlistitem$addplayerdata.func_179961_d());
            }
            break;
         case REMOVE_PLAYER:
            p_148840_1_.func_179252_a(s38packetplayerlistitem$addplayerdata.func_179962_a().getId());
         }
      }

   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147256_a(this);
   }

   public List<S38PacketPlayerListItem.AddPlayerData> func_179767_a() {
      return this.field_179769_b;
   }

   public S38PacketPlayerListItem.Action func_179768_b() {
      return this.field_179770_a;
   }

   public String toString() {
      return Objects.toStringHelper(this).add("action", this.field_179770_a).add("entries", this.field_179769_b).toString();
   }

   public static enum Action {
      ADD_PLAYER,
      UPDATE_GAME_MODE,
      UPDATE_LATENCY,
      UPDATE_DISPLAY_NAME,
      REMOVE_PLAYER;
   }

   public class AddPlayerData {
      private final int field_179966_b;
      private final WorldSettings.GameType field_179967_c;
      private final GameProfile field_179964_d;
      private final IChatComponent field_179965_e;

      public AddPlayerData(GameProfile p_i45965_2_, int p_i45965_3_, WorldSettings.GameType p_i45965_4_, IChatComponent p_i45965_5_) {
         this.field_179964_d = p_i45965_2_;
         this.field_179966_b = p_i45965_3_;
         this.field_179967_c = p_i45965_4_;
         this.field_179965_e = p_i45965_5_;
      }

      public GameProfile func_179962_a() {
         return this.field_179964_d;
      }

      public int func_179963_b() {
         return this.field_179966_b;
      }

      public WorldSettings.GameType func_179960_c() {
         return this.field_179967_c;
      }

      public IChatComponent func_179961_d() {
         return this.field_179965_e;
      }

      public String toString() {
         return Objects.toStringHelper(this).add("latency", this.field_179966_b).add("gameMode", this.field_179967_c).add("profile", this.field_179964_d).add("displayName", this.field_179965_e == null?null:IChatComponent.Serializer.func_150696_a(this.field_179965_e)).toString();
      }
   }
}
