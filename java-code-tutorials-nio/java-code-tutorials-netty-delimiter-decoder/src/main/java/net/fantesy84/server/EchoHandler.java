/**
 * 项目名: java-code-tutorials-netty-delimiter-decoder
 * 包名:  net.fantesy84.server
 * 文件名: EchoHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月11日
 */
package net.fantesy84.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Andronicus
 * @since 2016年1月11日
 */
public class EchoHandler extends ChannelHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	private int counter;
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error(cause.getMessage(), cause);
		ctx.close();
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String) msg;
		logger.info("This is {} times receive {} from client", ++counter, body);
		body += "$_";
		ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
		ctx.writeAndFlush(echo);
	}
	
}
