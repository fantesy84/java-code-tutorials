/**
 * 项目名: java-code-tutorials-netty-delimiter-decoder
 * 包名:  net.fantesy84.client
 * 文件名: Client.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月11日
 */
package net.fantesy84.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author Andronicus
 * @since 2016年1月11日
 */
public class EchoClient {
	public void connect(String host, int port) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ClientChannelHandler(Unpooled.copiedBuffer("$_".getBytes())));
			ChannelFuture future = boot.connect(new InetSocketAddress(host, port)).sync();
			future.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	private class ClientChannelHandler extends ChannelInitializer<SocketChannel> {
		private ByteBuf delimiter;
		
		/* (non-Javadoc)
		 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
		 */
		/**
		 * @param delimiter
		 */
		public ClientChannelHandler(ByteBuf delimiter) {
			super();
			this.delimiter = delimiter;
		}

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
			ch.pipeline().addLast(new StringDecoder());
			ch.pipeline().addLast(new EchoClientHandler());
		}
		
	}
	
	public static void main(String[] args) {
		int port = 18080;
		try {
			new EchoClient().connect("127.0.0.1", port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
