/**
 * 项目名: java-code-tutorials-netty-delimiter-decoder
 * 包名:  net.fantesy84.client
 * 文件名: EchoClientHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月13日
 */
package net.fantesy84.client;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Andronicus
 * @since 2016年1月13日
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);
	private AtomicInteger counter;
	private static final String TEMPLATE = "Hi, Client!Welcome to netty.$_";
	/**
	 * 
	 */
	public EchoClientHandler() {
		counter = new AtomicInteger(0);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error(cause.getMessage(), new IllegalStateException(cause));
		ctx.close();
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 10; i++) {
			ctx.writeAndFlush(Unpooled.copiedBuffer(TEMPLATE.getBytes()));
		}
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		logger.info("This is {} times receive msg {} from server", counter.incrementAndGet(), body);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
}
