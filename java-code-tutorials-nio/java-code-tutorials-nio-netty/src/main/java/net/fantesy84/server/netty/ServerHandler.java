/**
 * Project java-code-tutorials-nio-netty
 * File: HelloServerHandler.java
 * CreateTime: 2015年12月24日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.server.netty;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * TypeName: HelloServerHandler
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月24日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class ServerHandler extends ChannelHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	private int counter;
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf readBuf = (ByteBuf) msg;
		byte[] data = new byte[readBuf.readableBytes()];
		readBuf.readBytes(data);
		String body = new String(data, "UTF-8");
		body = body.substring(0, (body.length() - System.getProperty("line.separator").length()));
		counter++;
		logger.info("The counter is:{}", counter);
		logger.info("Server receive request msg:[{}]", body);
		String response = "query_time".equalsIgnoreCase(body) ? new Date().toString() : "Bad request!";
		response = response + System.getProperty("line.separator");
		ByteBuf writeBuf = Unpooled.copiedBuffer(response.getBytes());
		ctx.write(writeBuf);
	}
	
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error(cause.getMessage(), cause);
        ctx.close();
	}
	
}
