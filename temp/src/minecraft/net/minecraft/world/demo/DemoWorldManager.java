package net.minecraft.world.demo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DemoWorldManager extends ItemInWorldManager {
   private boolean field_73105_c;
   private boolean field_73103_d;
   private int field_73104_e;
   private int field_73102_f;

   public DemoWorldManager(World p_i1513_1_) {
      super(p_i1513_1_);
   }

   public void func_73075_a() {
      super.func_73075_a();
      ++this.field_73102_f;
      long i = this.field_73092_a.func_82737_E();
      long j = i / 24000L + 1L;
      if(!this.field_73105_c && this.field_73102_f > 20) {
         this.field_73105_c = true;
         this.field_73090_b.field_71135_a.func_147359_a(new S2BPacketChangeGameState(5, 0.0F));
      }

      this.field_73103_d = i > 120500L;
      if(this.field_73103_d) {
         ++this.field_73104_e;
      }

      if(i % 24000L == 500L) {
         if(j <= 6L) {
            this.field_73090_b.func_145747_a(new ChatComponentTranslation("demo.day." + j, new Object[0]));
         }
      } else if(j == 1L) {
         if(i == 100L) {
            this.field_73090_b.field_71135_a.func_147359_a(new S2BPacketChangeGameState(5, 101.0F));
         } else if(i == 175L) {
            this.field_73090_b.field_71135_a.func_147359_a(new S2BPacketChangeGameState(5, 102.0F));
         } else if(i == 250L) {
            this.field_73090_b.field_71135_a.func_147359_a(new S2BPacketChangeGameState(5, 103.0F));
         }
      } else if(j == 5L && i % 24000L == 22000L) {
         this.field_73090_b.func_145747_a(new ChatComponentTranslation("demo.day.warning", new Object[0]));
      }

   }

   private void func_73101_e() {
      if(this.field_73104_e > 100) {
         this.field_73090_b.func_145747_a(new ChatComponentTranslation("demo.reminder", new Object[0]));
         this.field_73104_e = 0;
      }

   }

   public void func_180784_a(BlockPos p_180784_1_, EnumFacing p_180784_2_) {
      if(this.field_73103_d) {
         this.func_73101_e();
      } else {
         super.func_180784_a(p_180784_1_, p_180784_2_);
      }
   }

   public void func_180785_a(BlockPos p_180785_1_) {
      if(!this.field_73103_d) {
         super.func_180785_a(p_180785_1_);
      }
   }

   public boolean func_180237_b(BlockPos p_180237_1_) {
      return this.field_73103_d?false:super.func_180237_b(p_180237_1_);
   }

   public boolean func_73085_a(EntityPlayer p_73085_1_, World p_73085_2_, ItemStack p_73085_3_) {
      if(this.field_73103_d) {
         this.func_73101_e();
         return false;
      } else {
         return super.func_73085_a(p_73085_1_, p_73085_2_, p_73085_3_);
      }
   }

   public boolean func_180236_a(EntityPlayer p_180236_1_, World p_180236_2_, ItemStack p_180236_3_, BlockPos p_180236_4_, EnumFacing p_180236_5_, float p_180236_6_, float p_180236_7_, float p_180236_8_) {
      if(this.field_73103_d) {
         this.func_73101_e();
         return false;
      } else {
         return super.func_180236_a(p_180236_1_, p_180236_2_, p_180236_3_, p_180236_4_, p_180236_5_, p_180236_6_, p_180236_7_, p_180236_8_);
      }
   }
}
