package net.minecraft.client.renderer;

import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.lwjgl.opengl.GL11;

public class WorldVertexBufferUploader {
   public void func_181679_a(WorldRenderer p_181679_1_) {
      if(p_181679_1_.func_178989_h() > 0) {
         VertexFormat vertexformat = p_181679_1_.func_178973_g();
         int i = vertexformat.func_177338_f();
         ByteBuffer bytebuffer = p_181679_1_.func_178966_f();
         List<VertexFormatElement> list = vertexformat.func_177343_g();

         for(int j = 0; j < list.size(); ++j) {
            VertexFormatElement vertexformatelement = (VertexFormatElement)list.get(j);
            VertexFormatElement.EnumUsage vertexformatelement$enumusage = vertexformatelement.func_177375_c();
            int k = vertexformatelement.func_177367_b().func_177397_c();
            int l = vertexformatelement.func_177369_e();
            bytebuffer.position(vertexformat.func_181720_d(j));
            switch(vertexformatelement$enumusage) {
            case POSITION:
               GL11.glVertexPointer(vertexformatelement.func_177370_d(), k, i, bytebuffer);
               GL11.glEnableClientState('\u8074');
               break;
            case UV:
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a + l);
               GL11.glTexCoordPointer(vertexformatelement.func_177370_d(), k, i, bytebuffer);
               GL11.glEnableClientState('\u8078');
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
               break;
            case COLOR:
               GL11.glColorPointer(vertexformatelement.func_177370_d(), k, i, bytebuffer);
               GL11.glEnableClientState('\u8076');
               break;
            case NORMAL:
               GL11.glNormalPointer(k, i, bytebuffer);
               GL11.glEnableClientState('\u8075');
            }
         }

         GL11.glDrawArrays(p_181679_1_.func_178979_i(), 0, p_181679_1_.func_178989_h());
         int i1 = 0;

         for(int j1 = list.size(); i1 < j1; ++i1) {
            VertexFormatElement vertexformatelement1 = (VertexFormatElement)list.get(i1);
            VertexFormatElement.EnumUsage vertexformatelement$enumusage1 = vertexformatelement1.func_177375_c();
            int k1 = vertexformatelement1.func_177369_e();
            switch(vertexformatelement$enumusage1) {
            case POSITION:
               GL11.glDisableClientState('\u8074');
               break;
            case UV:
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a + k1);
               GL11.glDisableClientState('\u8078');
               OpenGlHelper.func_77472_b(OpenGlHelper.field_77478_a);
               break;
            case COLOR:
               GL11.glDisableClientState('\u8076');
               GlStateManager.func_179117_G();
               break;
            case NORMAL:
               GL11.glDisableClientState('\u8075');
            }
         }
      }

      p_181679_1_.func_178965_a();
   }
}
