package net.minecraft.world.storage;

import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;

public class SaveDataMemoryStorage extends MapStorage {
   public SaveDataMemoryStorage() {
      super((ISaveHandler)null);
   }

   public WorldSavedData func_75742_a(Class<? extends WorldSavedData> p_75742_1_, String p_75742_2_) {
      return (WorldSavedData)this.field_75749_b.get(p_75742_2_);
   }

   public void func_75745_a(String p_75745_1_, WorldSavedData p_75745_2_) {
      this.field_75749_b.put(p_75745_1_, p_75745_2_);
   }

   public void func_75744_a() {
   }

   public int func_75743_a(String p_75743_1_) {
      return 0;
   }
}
