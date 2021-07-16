package net.minecraft.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMinecartCommandBlock extends EntityMinecart {
   private final CommandBlockLogic field_145824_a = new CommandBlockLogic() {
      public void func_145756_e() {
         EntityMinecartCommandBlock.this.func_70096_w().func_75692_b(23, this.func_145753_i());
         EntityMinecartCommandBlock.this.func_70096_w().func_75692_b(24, IChatComponent.Serializer.func_150696_a(this.func_145749_h()));
      }

      public int func_145751_f() {
         return 1;
      }

      public void func_145757_a(ByteBuf p_145757_1_) {
         p_145757_1_.writeInt(EntityMinecartCommandBlock.this.func_145782_y());
      }

      public BlockPos func_180425_c() {
         return new BlockPos(EntityMinecartCommandBlock.this.field_70165_t, EntityMinecartCommandBlock.this.field_70163_u + 0.5D, EntityMinecartCommandBlock.this.field_70161_v);
      }

      public Vec3 func_174791_d() {
         return new Vec3(EntityMinecartCommandBlock.this.field_70165_t, EntityMinecartCommandBlock.this.field_70163_u, EntityMinecartCommandBlock.this.field_70161_v);
      }

      public World func_130014_f_() {
         return EntityMinecartCommandBlock.this.field_70170_p;
      }

      public Entity func_174793_f() {
         return EntityMinecartCommandBlock.this;
      }
   };
   private int field_145823_b = 0;

   public EntityMinecartCommandBlock(World p_i45321_1_) {
      super(p_i45321_1_);
   }

   public EntityMinecartCommandBlock(World p_i45322_1_, double p_i45322_2_, double p_i45322_4_, double p_i45322_6_) {
      super(p_i45322_1_, p_i45322_2_, p_i45322_4_, p_i45322_6_);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.func_70096_w().func_75682_a(23, "");
      this.func_70096_w().func_75682_a(24, "");
   }

   protected void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_145824_a.func_145759_b(p_70037_1_);
      this.func_70096_w().func_75692_b(23, this.func_145822_e().func_145753_i());
      this.func_70096_w().func_75692_b(24, IChatComponent.Serializer.func_150696_a(this.func_145822_e().func_145749_h()));
   }

   protected void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      this.field_145824_a.func_145758_a(p_70014_1_);
   }

   public EntityMinecart.EnumMinecartType func_180456_s() {
      return EntityMinecart.EnumMinecartType.COMMAND_BLOCK;
   }

   public IBlockState func_180457_u() {
      return Blocks.field_150483_bI.func_176223_P();
   }

   public CommandBlockLogic func_145822_e() {
      return this.field_145824_a;
   }

   public void func_96095_a(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
      if(p_96095_4_ && this.field_70173_aa - this.field_145823_b >= 4) {
         this.func_145822_e().func_145755_a(this.field_70170_p);
         this.field_145823_b = this.field_70173_aa;
      }

   }

   public boolean func_130002_c(EntityPlayer p_130002_1_) {
      this.field_145824_a.func_175574_a(p_130002_1_);
      return false;
   }

   public void func_145781_i(int p_145781_1_) {
      super.func_145781_i(p_145781_1_);
      if(p_145781_1_ == 24) {
         try {
            this.field_145824_a.func_145750_b(IChatComponent.Serializer.func_150699_a(this.func_70096_w().func_75681_e(24)));
         } catch (Throwable var3) {
            ;
         }
      } else if(p_145781_1_ == 23) {
         this.field_145824_a.func_145752_a(this.func_70096_w().func_75681_e(23));
      }

   }
}
