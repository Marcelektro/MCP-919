package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenNetherBridge extends MapGenStructure {
   private List<BiomeGenBase.SpawnListEntry> field_75060_e = Lists.<BiomeGenBase.SpawnListEntry>newArrayList();

   public MapGenNetherBridge() {
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntityBlaze.class, 10, 2, 3));
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntityPigZombie.class, 5, 4, 4));
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
      this.field_75060_e.add(new BiomeGenBase.SpawnListEntry(EntityMagmaCube.class, 3, 4, 4));
   }

   public String func_143025_a() {
      return "Fortress";
   }

   public List<BiomeGenBase.SpawnListEntry> func_75059_a() {
      return this.field_75060_e;
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      int i = p_75047_1_ >> 4;
      int j = p_75047_2_ >> 4;
      this.field_75038_b.setSeed((long)(i ^ j << 4) ^ this.field_75039_c.func_72905_C());
      this.field_75038_b.nextInt();
      return this.field_75038_b.nextInt(3) != 0?false:(p_75047_1_ != (i << 4) + 4 + this.field_75038_b.nextInt(8)?false:p_75047_2_ == (j << 4) + 4 + this.field_75038_b.nextInt(8));
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      return new MapGenNetherBridge.Start(this.field_75039_c, this.field_75038_b, p_75049_1_, p_75049_2_);
   }

   public static class Start extends StructureStart {
      public Start() {
      }

      public Start(World p_i2040_1_, Random p_i2040_2_, int p_i2040_3_, int p_i2040_4_) {
         super(p_i2040_3_, p_i2040_4_);
         StructureNetherBridgePieces.Start structurenetherbridgepieces$start = new StructureNetherBridgePieces.Start(p_i2040_2_, (p_i2040_3_ << 4) + 2, (p_i2040_4_ << 4) + 2);
         this.field_75075_a.add(structurenetherbridgepieces$start);
         structurenetherbridgepieces$start.func_74861_a(structurenetherbridgepieces$start, this.field_75075_a, p_i2040_2_);
         List<StructureComponent> list = structurenetherbridgepieces$start.field_74967_d;

         while(!list.isEmpty()) {
            int i = p_i2040_2_.nextInt(list.size());
            StructureComponent structurecomponent = (StructureComponent)list.remove(i);
            structurecomponent.func_74861_a(structurenetherbridgepieces$start, this.field_75075_a, p_i2040_2_);
         }

         this.func_75072_c();
         this.func_75070_a(p_i2040_1_, p_i2040_2_, 48, 70);
      }
   }
}
