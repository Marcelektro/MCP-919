package net.minecraft.client.resources.model;

import java.util.List;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public interface IBakedModel {
   List<BakedQuad> func_177551_a(EnumFacing var1);

   List<BakedQuad> func_177550_a();

   boolean func_177555_b();

   boolean func_177556_c();

   boolean func_177553_d();

   TextureAtlasSprite func_177554_e();

   ItemCameraTransforms func_177552_f();
}
