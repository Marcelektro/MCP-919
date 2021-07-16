package net.minecraft.realms;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.realms.Realms;
import net.minecraft.realms.RealmsServerAddress;
import net.minecraft.realms.RealmsServerPing;
import net.minecraft.realms.RealmsSharedConstants;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsServerStatusPinger {
   private static final Logger LOGGER = LogManager.getLogger();
   private final List<NetworkManager> connections = Collections.<NetworkManager>synchronizedList(Lists.<NetworkManager>newArrayList());

   public void pingServer(final String p_pingServer_1_, final RealmsServerPing p_pingServer_2_) throws UnknownHostException {
      if(p_pingServer_1_ != null && !p_pingServer_1_.startsWith("0.0.0.0") && !p_pingServer_1_.isEmpty()) {
         RealmsServerAddress realmsserveraddress = RealmsServerAddress.parseString(p_pingServer_1_);
         final NetworkManager networkmanager = NetworkManager.func_181124_a(InetAddress.getByName(realmsserveraddress.getHost()), realmsserveraddress.getPort(), false);
         this.connections.add(networkmanager);
         networkmanager.func_150719_a(new INetHandlerStatusClient() {
            private boolean field_154345_e = false;

            public void func_147397_a(S00PacketServerInfo p_147397_1_) {
               ServerStatusResponse serverstatusresponse = p_147397_1_.func_149294_c();
               if(serverstatusresponse.func_151318_b() != null) {
                  p_pingServer_2_.nrOfPlayers = String.valueOf(serverstatusresponse.func_151318_b().func_151333_b());
                  if(ArrayUtils.isNotEmpty(serverstatusresponse.func_151318_b().func_151331_c())) {
                     StringBuilder stringbuilder = new StringBuilder();

                     for(GameProfile gameprofile : serverstatusresponse.func_151318_b().func_151331_c()) {
                        if(stringbuilder.length() > 0) {
                           stringbuilder.append("\n");
                        }

                        stringbuilder.append(gameprofile.getName());
                     }

                     if(serverstatusresponse.func_151318_b().func_151331_c().length < serverstatusresponse.func_151318_b().func_151333_b()) {
                        if(stringbuilder.length() > 0) {
                           stringbuilder.append("\n");
                        }

                        stringbuilder.append("... and ").append(serverstatusresponse.func_151318_b().func_151333_b() - serverstatusresponse.func_151318_b().func_151331_c().length).append(" more ...");
                     }

                     p_pingServer_2_.playerList = stringbuilder.toString();
                  }
               } else {
                  p_pingServer_2_.playerList = "";
               }

               networkmanager.func_179290_a(new C01PacketPing(Realms.currentTimeMillis()));
               this.field_154345_e = true;
            }

            public void func_147398_a(S01PacketPong p_147398_1_) {
               networkmanager.func_150718_a(new ChatComponentText("Finished"));
            }

            public void func_147231_a(IChatComponent p_147231_1_) {
               if(!this.field_154345_e) {
                  RealmsServerStatusPinger.LOGGER.error("Can\'t ping " + p_pingServer_1_ + ": " + p_147231_1_.func_150260_c());
               }

            }
         });

         try {
            networkmanager.func_179290_a(new C00Handshake(RealmsSharedConstants.NETWORK_PROTOCOL_VERSION, realmsserveraddress.getHost(), realmsserveraddress.getPort(), EnumConnectionState.STATUS));
            networkmanager.func_179290_a(new C00PacketServerQuery());
         } catch (Throwable throwable) {
            LOGGER.error((Object)throwable);
         }

      }
   }

   public void tick() {
      synchronized(this.connections) {
         Iterator<NetworkManager> iterator = this.connections.iterator();

         while(iterator.hasNext()) {
            NetworkManager networkmanager = (NetworkManager)iterator.next();
            if(networkmanager.func_150724_d()) {
               networkmanager.func_74428_b();
            } else {
               iterator.remove();
               networkmanager.func_179293_l();
            }
         }

      }
   }

   public void removeAll() {
      synchronized(this.connections) {
         Iterator<NetworkManager> iterator = this.connections.iterator();

         while(iterator.hasNext()) {
            NetworkManager networkmanager = (NetworkManager)iterator.next();
            if(networkmanager.func_150724_d()) {
               iterator.remove();
               networkmanager.func_150718_a(new ChatComponentText("Cancelled"));
            }
         }

      }
   }
}
