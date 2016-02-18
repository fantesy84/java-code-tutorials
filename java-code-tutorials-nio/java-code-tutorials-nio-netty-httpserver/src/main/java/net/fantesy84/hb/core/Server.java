/**
 * 项目名: Hummingbird
 * 包名:  net.fantesy84.hb.core
 * 文件名: Server.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年2月16日
 */
package net.fantesy84.hb.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Andronicus
 * @since 2016年2月16日
 */
public class Server {
	private static final Logger logger = LoggerFactory.getLogger(Server.class);
	
	private static final String DEFAULT_URL = "/src/main/resources/dir";
	public static void main(String[] args) {
		int port = 8080;
		String url = DEFAULT_URL;
		if (args.length > 1) {
			port = Integer.parseInt(args[0]);
			url = args[1];
		}
		try {
			new Server().run("127.0.0.1", port, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(final String host, final int port, final String url) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>(){

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
						ch.pipeline().addLast("http-aggregato", new HttpObjectAggregator(65536));
						ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
						ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
						ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
					}
					
				});
			ChannelFuture future = boot.bind(host, port).sync();
			logger.info("HTTP 文件目录服务器启动, 网址是:{}", "http://" + host + ":" + port);
			future.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
}
