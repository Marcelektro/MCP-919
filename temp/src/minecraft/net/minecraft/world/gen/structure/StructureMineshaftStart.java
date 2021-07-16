package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;
import net.minecraft.world.gen.structure.StructureStart;

public class StructureMineshaftStart extends StructureStart {
   public StructureMineshaftStart() {
   }

   public StructureMineshaftStart(World p_i2039_1_, Random p_i2039_2_, int p_i2039_3_, int p_i2039_4_) {
      super(p_i2039_3_, p_i2039_4_);
      StructureMineshaftPieces.Room structuremineshaftpieces$room = new StructureMineshaftPieces.Room(0, p_i2039_2_, (p_i2039_3_ << 4) + 2, (p_i2039_4_ << 4) + 2);
      this.field_75075_a.add(structuremineshaftpieces$room);
      structuremineshaftpieces$room.func_74861_a(structuremineshaftpieces$room, this.field_75075_a, p_i2039_2_);
      this.func_75072_c();
      this.func_75067_a(p_i2039_1_, p_i2039_2_, 10);
   }
}
