/**
 * 项目名: java-code-tutorials-nio-netty
 * 包名:  net.fantesy84.client.netty
 * 文件名: ClientHandler.java
 * Copy Right © 2016 Andronicus Ge
 * 时间: 2016年1月7日
 */
package net.fantesy84.client.netty;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Andronicus
 * @since 2016年1月7日
 */
public class ClientHandler extends ChannelHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		byte[] data = "query_time".getBytes();
		ByteBuf writeBuf = Unpooled.buffer(data.length);
		writeBuf.writeBytes(data);
		ctx.writeAndFlush(writeBuf);
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
	 * @see io.netty.channel.ChannelHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf readBuf = (ByteBuf) msg;
		byte[] data = new byte[readBuf.readableBytes()];
		readBuf.readBytes(data);
		String response = new String(data, "UTF-8");
		logger.info("Now is [{}]", response);
	}

	/* (non-Javadoc)
	 * @see io.netty.channel.ChannelHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}
	
}
