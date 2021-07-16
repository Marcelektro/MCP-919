package net.minecraft.client.renderer.chunk;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator;
import net.minecraft.client.renderer.chunk.ChunkRenderWorker;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.EnumWorldBlockLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class ChunkRenderDispatcher {
   private static final Logger field_178523_a = LogManager.getLogger();
   private static final ThreadFactory field_178521_b = (new ThreadFactoryBuilder()).setNameFormat("Chunk Batcher %d").setDaemon(true).build();
   private final List<ChunkRenderWorker> field_178522_c = Lists.<ChunkRenderWorker>newArrayList();
   private final BlockingQueue<ChunkCompileTaskGenerator> field_178519_d = Queues.<ChunkCompileTaskGenerator>newArrayBlockingQueue(100);
   private final BlockingQueue<RegionRenderCacheBuilder> field_178520_e = Queues.<RegionRenderCacheBuilder>newArrayBlockingQueue(5);
   private final WorldVertexBufferUploader field_178517_f = new WorldVertexBufferUploader();
   private final VertexBufferUploader field_178518_g = new VertexBufferUploader();
   private final Queue<ListenableFutureTask<?>> field_178524_h = Queues.<ListenableFutureTask<?>>newArrayDeque();
   private final ChunkRenderWorker field_178525_i;

   public ChunkRenderDispatcher() {
      for(int i = 0; i < 2; ++i) {
         ChunkRenderWorker chunkrenderworker = new ChunkRenderWorker(this);
         Thread thread = field_178521_b.newThread(chunkrenderworker);
         thread.start();
         this.field_178522_c.add(chunkrenderworker);
      }

      for(int j = 0; j < 5; ++j) {
         this.field_178520_e.add(new RegionRenderCacheBuilder());
      }

      this.field_178525_i = new ChunkRenderWorker(this, new RegionRenderCacheBuilder());
   }

   public String func_178504_a() {
      return String.format("pC: %03d, pU: %1d, aB: %1d", new Object[]{Integer.valueOf(this.field_178519_d.size()), Integer.valueOf(this.field_178524_h.size()), Integer.valueOf(this.field_178520_e.size())});
   }

   public boolean func_178516_a(long p_178516_1_) {
      boolean flag = false;

      while(true) {
         boolean flag1 = false;
         synchronized(this.field_178524_h) {
            if(!this.field_178524_h.isEmpty()) {
               ((ListenableFutureTask)this.field_178524_h.poll()).run();
               flag1 = true;
               flag = true;
            }
         }

         if(p_178516_1_ == 0L || !flag1) {
            break;
         }

         long i = p_178516_1_ - System.nanoTime();
         if(i < 0L) {
            break;
         }
      }

      return flag;
   }

   public boolean func_178507_a(RenderChunk p_178507_1_) {
      p_178507_1_.func_178579_c().lock();

      boolean flag1;
      try {
         final ChunkCompileTaskGenerator chunkcompiletaskgenerator = p_178507_1_.func_178574_d();
         chunkcompiletaskgenerator.func_178539_a(new Runnable() {
            public void run() {
               ChunkRenderDispatcher.this.field_178519_d.remove(chunkcompiletaskgenerator);
            }
         });
         boolean flag = this.field_178519_d.offer(chunkcompiletaskgenerator);
         if(!flag) {
            chunkcompiletaskgenerator.func_178542_e();
         }

         flag1 = flag;
      } finally {
         p_178507_1_.func_178579_c().unlock();
      }

      return flag1;
   }

   public boolean func_178505_b(RenderChunk p_178505_1_) {
      p_178505_1_.func_178579_c().lock();

      boolean flag;
      try {
         ChunkCompileTaskGenerator chunkcompiletaskgenerator = p_178505_1_.func_178574_d();

         try {
            this.field_178525_i.func_178474_a(chunkcompiletaskgenerator);
         } catch (InterruptedException var7) {
            ;
         }

         flag = true;
      } finally {
         p_178505_1_.func_178579_c().unlock();
      }

      return flag;
   }

   public void func_178514_b() {
      this.func_178513_e();

      while(this.func_178516_a(0L)) {
         ;
      }

      List<RegionRenderCacheBuilder> list = Lists.<RegionRenderCacheBuilder>newArrayList();

      while(((List)list).size() != 5) {
         try {
            list.add(this.func_178515_c());
         } catch (InterruptedException var3) {
            ;
         }
      }

      this.field_178520_e.addAll(list);
   }

   public void func_178512_a(RegionRenderCacheBuilder p_178512_1_) {
      this.field_178520_e.add(p_178512_1_);
   }

   public RegionRenderCacheBuilder func_178515_c() throws InterruptedException {
      return (RegionRenderCacheBuilder)this.field_178520_e.take();
   }

   public ChunkCompileTaskGenerator func_178511_d() throws InterruptedException {
      return (ChunkCompileTaskGenerator)this.field_178519_d.take();
   }

   public boolean func_178509_c(RenderChunk p_178509_1_) {
      p_178509_1_.func_178579_c().lock();

      boolean flag;
      try {
         final ChunkCompileTaskGenerator chunkcompiletaskgenerator = p_178509_1_.func_178582_e();
         if(chunkcompiletaskgenerator == null) {
            flag = true;
            return flag;
         }

         chunkcompiletaskgenerator.func_178539_a(new Runnable() {
            public void run() {
               ChunkRenderDispatcher.this.field_178519_d.remove(chunkcompiletaskgenerator);
            }
         });
         flag = this.field_178519_d.offer(chunkcompiletaskgenerator);
      } finally {
         p_178509_1_.func_178579_c().unlock();
      }

      return flag;
   }

   public ListenableFuture<Object> func_178503_a(final EnumWorldBlockLayer p_178503_1_, final WorldRenderer p_178503_2_, final RenderChunk p_178503_3_, final CompiledChunk p_178503_4_) {
      if(Minecraft.func_71410_x().func_152345_ab()) {
         if(OpenGlHelper.func_176075_f()) {
            this.func_178506_a(p_178503_2_, p_178503_3_.func_178565_b(p_178503_1_.ordinal()));
         } else {
            this.func_178510_a(p_178503_2_, ((ListedRenderChunk)p_178503_3_).func_178600_a(p_178503_1_, p_178503_4_), p_178503_3_);
         }

         p_178503_2_.func_178969_c(0.0D, 0.0D, 0.0D);
         return Futures.<Object>immediateFuture((Object)null);
      } else {
         ListenableFutureTask<Object> listenablefuturetask = ListenableFutureTask.<Object>create(new Runnable() {
            public void run() {
               ChunkRenderDispatcher.this.func_178503_a(p_178503_1_, p_178503_2_, p_178503_3_, p_178503_4_);
            }
         }, (Object)null);
         synchronized(this.field_178524_h) {
            this.field_178524_h.add(listenablefuturetask);
            return listenablefuturetask;
         }
      }
   }

   private void func_178510_a(WorldRenderer p_178510_1_, int p_178510_2_, RenderChunk p_178510_3_) {
      GL11.glNewList(p_178510_2_, 4864);
      GlStateManager.func_179094_E();
      p_178510_3_.func_178572_f();
      this.field_178517_f.func_181679_a(p_178510_1_);
      GlStateManager.func_179121_F();
      GL11.glEndList();
   }

   private void func_178506_a(WorldRenderer p_178506_1_, VertexBuffer p_178506_2_) {
      this.field_178518_g.func_178178_a(p_178506_2_);
      this.field_178518_g.func_181679_a(p_178506_1_);
   }

   public void func_178513_e() {
      while(!this.field_178519_d.isEmpty()) {
         ChunkCompileTaskGenerator chunkcompiletaskgenerator = (ChunkCompileTaskGenerator)this.field_178519_d.poll();
         if(chunkcompiletaskgenerator != null) {
            chunkcompiletaskgenerator.func_178542_e();
         }
      }

   }
}
