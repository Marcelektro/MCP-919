package net.minecraft.entity;

import java.util.Collection;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SharedMonsterAttributes {
   private static final Logger field_151476_f = LogManager.getLogger();
   public static final IAttribute field_111267_a = (new RangedAttribute((IAttribute)null, "generic.maxHealth", 20.0D, 0.0D, 1024.0D)).func_111117_a("Max Health").func_111112_a(true);
   public static final IAttribute field_111265_b = (new RangedAttribute((IAttribute)null, "generic.followRange", 32.0D, 0.0D, 2048.0D)).func_111117_a("Follow Range");
   public static final IAttribute field_111266_c = (new RangedAttribute((IAttribute)null, "generic.knockbackResistance", 0.0D, 0.0D, 1.0D)).func_111117_a("Knockback Resistance");
   public static final IAttribute field_111263_d = (new RangedAttribute((IAttribute)null, "generic.movementSpeed", 0.699999988079071D, 0.0D, 1024.0D)).func_111117_a("Movement Speed").func_111112_a(true);
   public static final IAttribute field_111264_e = new RangedAttribute((IAttribute)null, "generic.attackDamage", 2.0D, 0.0D, 2048.0D);

   public static NBTTagList func_111257_a(BaseAttributeMap p_111257_0_) {
      NBTTagList nbttaglist = new NBTTagList();

      for(IAttributeInstance iattributeinstance : p_111257_0_.func_111146_a()) {
         nbttaglist.func_74742_a(func_111261_a(iattributeinstance));
      }

      return nbttaglist;
   }

   private static NBTTagCompound func_111261_a(IAttributeInstance p_111261_0_) {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      IAttribute iattribute = p_111261_0_.func_111123_a();
      nbttagcompound.func_74778_a("Name", iattribute.func_111108_a());
      nbttagcompound.func_74780_a("Base", p_111261_0_.func_111125_b());
      Collection<AttributeModifier> collection = p_111261_0_.func_111122_c();
      if(collection != null && !collection.isEmpty()) {
         NBTTagList nbttaglist = new NBTTagList();

         for(AttributeModifier attributemodifier : collection) {
            if(attributemodifier.func_111165_e()) {
               nbttaglist.func_74742_a(func_111262_a(attributemodifier));
            }
         }

         nbttagcompound.func_74782_a("Modifiers", nbttaglist);
      }

      return nbttagcompound;
   }

   private static NBTTagCompound func_111262_a(AttributeModifier p_111262_0_) {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      nbttagcompound.func_74778_a("Name", p_111262_0_.func_111166_b());
      nbttagcompound.func_74780_a("Amount", p_111262_0_.func_111164_d());
      nbttagcompound.func_74768_a("Operation", p_111262_0_.func_111169_c());
      nbttagcompound.func_74772_a("UUIDMost", p_111262_0_.func_111167_a().getMostSignificantBits());
      nbttagcompound.func_74772_a("UUIDLeast", p_111262_0_.func_111167_a().getLeastSignificantBits());
      return nbttagcompound;
   }

   public static void func_151475_a(BaseAttributeMap p_151475_0_, NBTTagList p_151475_1_) {
      for(int i = 0; i < p_151475_1_.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = p_151475_1_.func_150305_b(i);
         IAttributeInstance iattributeinstance = p_151475_0_.func_111152_a(nbttagcompound.func_74779_i("Name"));
         if(iattributeinstance != null) {
            func_111258_a(iattributeinstance, nbttagcompound);
         } else {
            field_151476_f.warn("Ignoring unknown attribute \'" + nbttagcompound.func_74779_i("Name") + "\'");
         }
      }

   }

   private static void func_111258_a(IAttributeInstance p_111258_0_, NBTTagCompound p_111258_1_) {
      p_111258_0_.func_111128_a(p_111258_1_.func_74769_h("Base"));
      if(p_111258_1_.func_150297_b("Modifiers", 9)) {
         NBTTagList nbttaglist = p_111258_1_.func_150295_c("Modifiers", 10);

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            AttributeModifier attributemodifier = func_111259_a(nbttaglist.func_150305_b(i));
            if(attributemodifier != null) {
               AttributeModifier attributemodifier1 = p_111258_0_.func_111127_a(attributemodifier.func_111167_a());
               if(attributemodifier1 != null) {
                  p_111258_0_.func_111124_b(attributemodifier1);
               }

               p_111258_0_.func_111121_a(attributemodifier);
            }
         }
      }

   }

   public static AttributeModifier func_111259_a(NBTTagCompound p_111259_0_) {
      UUID uuid = new UUID(p_111259_0_.func_74763_f("UUIDMost"), p_111259_0_.func_74763_f("UUIDLeast"));

      try {
         return new AttributeModifier(uuid, p_111259_0_.func_74779_i("Name"), p_111259_0_.func_74769_h("Amount"), p_111259_0_.func_74762_e("Operation"));
      } catch (Exception exception) {
         field_151476_f.warn("Unable to create attribute: " + exception.getMessage());
         return null;
      }
   }
}
