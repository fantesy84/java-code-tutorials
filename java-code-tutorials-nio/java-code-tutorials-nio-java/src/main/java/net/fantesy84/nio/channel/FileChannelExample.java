/**
 * Project java-code-tutorials-nio-java
 * File: FileChannelExample.java
 * CreateTime: 2015年12月24日
 * Creator: junjie.ge
 * copy right ©2015 葛俊杰
 */
package net.fantesy84.nio.channel;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TypeName: FileChannelExample
 * <P>TODO
 * 
 * <P>CreateTime: 2015年12月24日
 * <P>UpdateTime: 
 * @author junjie.ge
 *
 */
public class FileChannelExample {

	public void read() throws Exception{
		RandomAccessFile aFile = null;
		try {
			aFile = new RandomAccessFile(new File("d:/nio-data.txt"), "rw");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {
				System.out.println("Read " + bytesRead);
				buf.flip();
				while (buf.hasRemaining()) {
					System.out.print((char)buf.get());
				}
				buf.clear();
				bytesRead = inChannel.read(buf);
				System.out.println();
			}
		} finally {
			if (aFile != null) {
				aFile.close();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new FileChannelExample().read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
