/**
 * Project java-code-tutorials-nio-netty
 * File: HelloServer.java
 * CreateTime: 2015年12月24日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * TypeName: HelloServer
 * <P>
 * TODO
 * 
 * <P>
 * CreateTime: 2015年12月24日
 * <P>
 * UpdateTime:
 * 
 * @author junjie.ge
 *
 */
public class Server {
	private int port;

	/**
	 * @param port
	 */
	public Server(int port) {
		super();
		this.port = port;
	}

	public void bind() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChildChannelHandler())
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childOption(ChannelOption.SO_KEEPALIVE, true);

			// Bind and start to accept incoming connections.
			ChannelFuture f = boot.bind(port).sync();

			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to
			// gracefully
			// shut down your server.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 18080;
		}
		new Server(port).bind();
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.
		 * Channel)
		 */
		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			// 解决粘包/拆包问题
			/*
			 * 处理器的顺序很重要
			 */
			ch.pipeline().addLast(new LineBasedFrameDecoder(10240));
			ch.pipeline().addLast(new StringDecoder());
			ch.pipeline().addLast(new ServerHandler());
		}

	}
}
