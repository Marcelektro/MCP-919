package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;

public class LayerBipedArmor extends LayerArmorBase<ModelBiped> {
   public LayerBipedArmor(RendererLivingEntity<?> p_i46116_1_) {
      super(p_i46116_1_);
   }

   protected void func_177177_a() {
      this.field_177189_c = new ModelBiped(0.5F);
      this.field_177186_d = new ModelBiped(1.0F);
   }

   protected void func_177179_a(ModelBiped p_177179_1_, int p_177179_2_) {
      this.func_177194_a(p_177179_1_);
      switch(p_177179_2_) {
      case 1:
         p_177179_1_.field_178721_j.field_78806_j = true;
         p_177179_1_.field_178722_k.field_78806_j = true;
         break;
      case 2:
         p_177179_1_.field_78115_e.field_78806_j = true;
         p_177179_1_.field_178721_j.field_78806_j = true;
         p_177179_1_.field_178722_k.field_78806_j = true;
         break;
      case 3:
         p_177179_1_.field_78115_e.field_78806_j = true;
         p_177179_1_.field_178723_h.field_78806_j = true;
         p_177179_1_.field_178724_i.field_78806_j = true;
         break;
      case 4:
         p_177179_1_.field_78116_c.field_78806_j = true;
         p_177179_1_.field_178720_f.field_78806_j = true;
      }

   }

   protected void func_177194_a(ModelBiped p_177194_1_) {
      p_177194_1_.func_178719_a(false);
   }
}
