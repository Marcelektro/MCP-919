package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;

public class BuiltInModel implements IBakedModel {
   private ItemCameraTransforms field_177557_a;

   public BuiltInModel(ItemCameraTransforms p_i46086_1_) {
      this.field_177557_a = p_i46086_1_;
   }

   public List<BakedQuad> func_177551_a(EnumFacing p_177551_1_) {
      return null;
   }

   public List<BakedQuad> func_177550_a() {
      return null;
   }

   public boolean func_177555_b() {
      return false;
   }

   public boolean func_177556_c() {
      return true;
   }

   public boolean func_177553_d() {
      return true;
   }

   public TextureAtlasSprite func_177554_e() {
      return null;
   }

   public ItemCameraTransforms func_177552_f() {
      return this.field_177557_a;
   }
}
