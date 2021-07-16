package net.minecraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBoat extends ModelBase {
   public ModelRenderer[] field_78103_a = new ModelRenderer[5];

   public ModelBoat() {
      this.field_78103_a[0] = new ModelRenderer(this, 0, 8);
      this.field_78103_a[1] = new ModelRenderer(this, 0, 0);
      this.field_78103_a[2] = new ModelRenderer(this, 0, 0);
      this.field_78103_a[3] = new ModelRenderer(this, 0, 0);
      this.field_78103_a[4] = new ModelRenderer(this, 0, 0);
      int i = 24;
      int j = 6;
      int k = 20;
      int l = 4;
      this.field_78103_a[0].func_78790_a((float)(-i / 2), (float)(-k / 2 + 2), -3.0F, i, k - 4, 4, 0.0F);
      this.field_78103_a[0].func_78793_a(0.0F, (float)l, 0.0F);
      this.field_78103_a[1].func_78790_a((float)(-i / 2 + 2), (float)(-j - 1), -1.0F, i - 4, j, 2, 0.0F);
      this.field_78103_a[1].func_78793_a((float)(-i / 2 + 1), (float)l, 0.0F);
      this.field_78103_a[2].func_78790_a((float)(-i / 2 + 2), (float)(-j - 1), -1.0F, i - 4, j, 2, 0.0F);
      this.field_78103_a[2].func_78793_a((float)(i / 2 - 1), (float)l, 0.0F);
      this.field_78103_a[3].func_78790_a((float)(-i / 2 + 2), (float)(-j - 1), -1.0F, i - 4, j, 2, 0.0F);
      this.field_78103_a[3].func_78793_a(0.0F, (float)l, (float)(-k / 2 + 1));
      this.field_78103_a[4].func_78790_a((float)(-i / 2 + 2), (float)(-j - 1), -1.0F, i - 4, j, 2, 0.0F);
      this.field_78103_a[4].func_78793_a(0.0F, (float)l, (float)(k / 2 - 1));
      this.field_78103_a[0].field_78795_f = 1.5707964F;
      this.field_78103_a[1].field_78796_g = 4.712389F;
      this.field_78103_a[2].field_78796_g = 1.5707964F;
      this.field_78103_a[3].field_78796_g = 3.1415927F;
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      for(int i = 0; i < 5; ++i) {
         this.field_78103_a[i].func_78785_a(p_78088_7_);
      }

   }
}
