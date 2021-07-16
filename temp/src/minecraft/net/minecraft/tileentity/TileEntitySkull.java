package net.minecraft.tileentity;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StringUtils;

public class TileEntitySkull extends TileEntity {
   private int field_145908_a;
   private int field_145910_i;
   private GameProfile field_152110_j = null;

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74774_a("SkullType", (byte)(this.field_145908_a & 255));
      p_145841_1_.func_74774_a("Rot", (byte)(this.field_145910_i & 255));
      if(this.field_152110_j != null) {
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         NBTUtil.func_180708_a(nbttagcompound, this.field_152110_j);
         p_145841_1_.func_74782_a("Owner", nbttagcompound);
      }

   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_145908_a = p_145839_1_.func_74771_c("SkullType");
      this.field_145910_i = p_145839_1_.func_74771_c("Rot");
      if(this.field_145908_a == 3) {
         if(p_145839_1_.func_150297_b("Owner", 10)) {
            this.field_152110_j = NBTUtil.func_152459_a(p_145839_1_.func_74775_l("Owner"));
         } else if(p_145839_1_.func_150297_b("ExtraType", 8)) {
            String s = p_145839_1_.func_74779_i("ExtraType");
            if(!StringUtils.func_151246_b(s)) {
               this.field_152110_j = new GameProfile((UUID)null, s);
               this.func_152109_d();
            }
         }
      }

   }

   public GameProfile func_152108_a() {
      return this.field_152110_j;
   }

   public Packet func_145844_m() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      this.func_145841_b(nbttagcompound);
      return new S35PacketUpdateTileEntity(this.field_174879_c, 4, nbttagcompound);
   }

   public void func_152107_a(int p_152107_1_) {
      this.field_145908_a = p_152107_1_;
      this.field_152110_j = null;
   }

   public void func_152106_a(GameProfile p_152106_1_) {
      this.field_145908_a = 3;
      this.field_152110_j = p_152106_1_;
      this.func_152109_d();
   }

   private void func_152109_d() {
      this.field_152110_j = func_174884_b(this.field_152110_j);
      this.func_70296_d();
   }

   public static GameProfile func_174884_b(GameProfile p_174884_0_) {
      if(p_174884_0_ != null && !StringUtils.func_151246_b(p_174884_0_.getName())) {
         if(p_174884_0_.isComplete() && p_174884_0_.getProperties().containsKey("textures")) {
            return p_174884_0_;
         } else if(MinecraftServer.func_71276_C() == null) {
            return p_174884_0_;
         } else {
            GameProfile gameprofile = MinecraftServer.func_71276_C().func_152358_ax().func_152655_a(p_174884_0_.getName());
            if(gameprofile == null) {
               return p_174884_0_;
            } else {
               Property property = (Property)Iterables.getFirst(gameprofile.getProperties().get("textures"), null);
               if(property == null) {
                  gameprofile = MinecraftServer.func_71276_C().func_147130_as().fillProfileProperties(gameprofile, true);
               }

               return gameprofile;
            }
         }
      } else {
         return p_174884_0_;
      }
   }

   public int func_145904_a() {
      return this.field_145908_a;
   }

   public int func_145906_b() {
      return this.field_145910_i;
   }

   public void func_145903_a(int p_145903_1_) {
      this.field_145910_i = p_145903_1_;
   }
}
