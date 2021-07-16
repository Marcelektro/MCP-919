package net.minecraft.client.network;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldServerPinger {
   private static final Splitter field_147230_a = Splitter.on('\u0000').limit(6);
   private static final Logger field_147228_b = LogManager.getLogger();
   private final List<NetworkManager> field_147229_c = Collections.<NetworkManager>synchronizedList(Lists.<NetworkManager>newArrayList());

   public void func_147224_a(final ServerData p_147224_1_) throws UnknownHostException {
      ServerAddress serveraddress = ServerAddress.func_78860_a(p_147224_1_.field_78845_b);
      final NetworkManager networkmanager = NetworkManager.func_181124_a(InetAddress.getByName(serveraddress.func_78861_a()), serveraddress.func_78864_b(), false);
      this.field_147229_c.add(networkmanager);
      p_147224_1_.field_78843_d = "Pinging...";
      p_147224_1_.field_78844_e = -1L;
      p_147224_1_.field_147412_i = null;
      networkmanager.func_150719_a(new INetHandlerStatusClient() {
         private boolean field_147403_d = false;
         private boolean field_183009_e = false;
         private long field_175092_e = 0L;

         public void func_147397_a(S00PacketServerInfo p_147397_1_) {
            if(this.field_183009_e) {
               networkmanager.func_150718_a(new ChatComponentText("Received unrequested status"));
            } else {
               this.field_183009_e = true;
               ServerStatusResponse serverstatusresponse = p_147397_1_.func_149294_c();
               if(serverstatusresponse.func_151317_a() != null) {
                  p_147224_1_.field_78843_d = serverstatusresponse.func_151317_a().func_150254_d();
               } else {
                  p_147224_1_.field_78843_d = "";
               }

               if(serverstatusresponse.func_151322_c() != null) {
                  p_147224_1_.field_82822_g = serverstatusresponse.func_151322_c().func_151303_a();
                  p_147224_1_.field_82821_f = serverstatusresponse.func_151322_c().func_151304_b();
               } else {
                  p_147224_1_.field_82822_g = "Old";
                  p_147224_1_.field_82821_f = 0;
               }

               if(serverstatusresponse.func_151318_b() != null) {
                  p_147224_1_.field_78846_c = EnumChatFormatting.GRAY + "" + serverstatusresponse.func_151318_b().func_151333_b() + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + serverstatusresponse.func_151318_b().func_151332_a();
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

                     p_147224_1_.field_147412_i = stringbuilder.toString();
                  }
               } else {
                  p_147224_1_.field_78846_c = EnumChatFormatting.DARK_GRAY + "???";
               }

               if(serverstatusresponse.func_151316_d() != null) {
                  String s = serverstatusresponse.func_151316_d();
                  if(s.startsWith("data:image/png;base64,")) {
                     p_147224_1_.func_147407_a(s.substring("data:image/png;base64,".length()));
                  } else {
                     OldServerPinger.field_147228_b.error("Invalid server icon (unknown format)");
                  }
               } else {
                  p_147224_1_.func_147407_a((String)null);
               }

               this.field_175092_e = Minecraft.func_71386_F();
               networkmanager.func_179290_a(new C01PacketPing(this.field_175092_e));
               this.field_147403_d = true;
            }
         }

         public void func_147398_a(S01PacketPong p_147398_1_) {
            long i = this.field_175092_e;
            long j = Minecraft.func_71386_F();
            p_147224_1_.field_78844_e = j - i;
            networkmanager.func_150718_a(new ChatComponentText("Finished"));
         }

         public void func_147231_a(IChatComponent p_147231_1_) {
            if(!this.field_147403_d) {
               OldServerPinger.field_147228_b.error("Can\'t ping " + p_147224_1_.field_78845_b + ": " + p_147231_1_.func_150260_c());
               p_147224_1_.field_78843_d = EnumChatFormatting.DARK_RED + "Can\'t connect to server.";
               p_147224_1_.field_78846_c = "";
               OldServerPinger.this.func_147225_b(p_147224_1_);
            }

         }
      });

      try {
         networkmanager.func_179290_a(new C00Handshake(47, serveraddress.func_78861_a(), serveraddress.func_78864_b(), EnumConnectionState.STATUS));
         networkmanager.func_179290_a(new C00PacketServerQuery());
      } catch (Throwable throwable) {
         field_147228_b.error((Object)throwable);
      }

   }

   private void func_147225_b(final ServerData p_147225_1_) {
      final ServerAddress serveraddress = ServerAddress.func_78860_a(p_147225_1_.field_78845_b);
      ((Bootstrap)((Bootstrap)((Bootstrap)(new Bootstrap()).group((EventLoopGroup)NetworkManager.field_179295_d.func_179281_c())).handler(new ChannelInitializer<Channel>() {
         protected void initChannel(Channel p_initChannel_1_) throws Exception {
            try {
               p_initChannel_1_.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
            } catch (ChannelException var3) {
               ;
            }

            p_initChannel_1_.pipeline().addLast(new ChannelHandler[]{new SimpleChannelInboundHandler<ByteBuf>() {
               public void channelActive(ChannelHandlerContext p_channelActive_1_) throws Exception {
                  super.channelActive(p_channelActive_1_);
                  ByteBuf bytebuf = Unpooled.buffer();

                  try {
                     bytebuf.writeByte(254);
                     bytebuf.writeByte(1);
                     bytebuf.writeByte(250);
                     char[] achar = "MC|PingHost".toCharArray();
                     bytebuf.writeShort(achar.length);

                     for(char c0 : achar) {
                        bytebuf.writeChar(c0);
                     }

                     bytebuf.writeShort(7 + 2 * serveraddress.func_78861_a().length());
                     bytebuf.writeByte(127);
                     achar = serveraddress.func_78861_a().toCharArray();
                     bytebuf.writeShort(achar.length);

                     for(char c1 : achar) {
                        bytebuf.writeChar(c1);
                     }

                     bytebuf.writeInt(serveraddress.func_78864_b());
                     p_channelActive_1_.channel().writeAndFlush(bytebuf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                  } finally {
                     bytebuf.release();
                  }

               }

               protected void channelRead0(ChannelHandlerContext p_channelRead0_1_, ByteBuf p_channelRead0_2_) throws Exception {
                  short short1 = p_channelRead0_2_.readUnsignedByte();
                  if(short1 == 255) {
                     String s = new String(p_channelRead0_2_.readBytes(p_channelRead0_2_.readShort() * 2).array(), Charsets.UTF_16BE);
                     String[] astring = (String[])Iterables.toArray(OldServerPinger.field_147230_a.split(s), String.class);
                     if("\u00a71".equals(astring[0])) {
                        int i = MathHelper.func_82715_a(astring[1], 0);
                        String s1 = astring[2];
                        String s2 = astring[3];
                        int j = MathHelper.func_82715_a(astring[4], -1);
                        int k = MathHelper.func_82715_a(astring[5], -1);
                        p_147225_1_.field_82821_f = -1;
                        p_147225_1_.field_82822_g = s1;
                        p_147225_1_.field_78843_d = s2;
                        p_147225_1_.field_78846_c = EnumChatFormatting.GRAY + "" + j + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + k;
                     }
                  }

                  p_channelRead0_1_.close();
               }

               public void exceptionCaught(ChannelHandlerContext p_exceptionCaught_1_, Throwable p_exceptionCaught_2_) throws Exception {
                  p_exceptionCaught_1_.close();
               }
            }});
         }
      })).channel(NioSocketChannel.class)).connect(serveraddress.func_78861_a(), serveraddress.func_78864_b());
   }

   public void func_147223_a() {
      synchronized(this.field_147229_c) {
         Iterator<NetworkManager> iterator = this.field_147229_c.iterator();

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

   public void func_147226_b() {
      synchronized(this.field_147229_c) {
         Iterator<NetworkManager> iterator = this.field_147229_c.iterator();

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
