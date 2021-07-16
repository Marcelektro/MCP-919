package net.minecraft.client.resources;

import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;

public interface IReloadableResourceManager extends IResourceManager {
   void func_110541_a(List<IResourcePack> var1);

   void func_110542_a(IResourceManagerReloadListener var1);
}
