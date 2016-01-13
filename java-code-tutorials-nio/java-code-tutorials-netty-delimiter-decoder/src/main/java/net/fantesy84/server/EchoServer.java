/**
 * 项目名: java-code-tutorials-netty-delimiter-decoder
 * 包名:  net.fantesy84.server
 * 文件名: EchoServer.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月11日
 */
package net.fantesy84.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Andronicus
 * @since 2016年1月11日
 */
public class EchoServer {
	private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);
	public void bind(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024).handler(new LoggingHandler(LogLevel.DEBUG))
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							byte[] prefix = "$_".getBytes();
							ByteBuf delimiter = Unpooled.copiedBuffer(prefix);

							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(2, delimiter));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new EchoHandler());
						}
					});
			ChannelFuture f = boot.bind(port).sync();
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}
	
	public static void main(String[] args) {
		int port = 18080;
		try {
			new EchoServer().bind(port);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
