package net.minecraft.client.resources.data;

import com.google.gson.JsonDeserializer;
import net.minecraft.client.resources.data.IMetadataSection;

public interface IMetadataSectionSerializer<T extends IMetadataSection> extends JsonDeserializer<T> {
   String func_110483_a();
}
