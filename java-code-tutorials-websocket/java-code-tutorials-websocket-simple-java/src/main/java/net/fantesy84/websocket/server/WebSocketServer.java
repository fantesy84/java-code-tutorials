/**
 * 项目名: websocket-demo
 * 包名:  net.fantesy84.websocket.server
 * 文件名: WebSocketServer.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月30日
 */
package net.fantesy84.websocket.server;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Andronicus
 * @since 2015年12月30日
 */

@ServerEndpoint("/websocket_server")
public class WebSocketServer {
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
     
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
     
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    @OnOpen
	public void onOpen(Session session) {
    	this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}
	
    @OnClose
	public void onClose() {
    	webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1    
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}
	
    @OnMessage
	public void onMessage(Session session, String message) {
    	System.out.println("来自客户端的消息:" + message);
        
        //群发消息
        for(WebSocketServer item: webSocketSet){             
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
	}
	
    @OnError
	public void onError(Session session, Throwable error) {
    	System.out.println("发生错误");
        error.printStackTrace();
	}
	
	/**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    protected void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    
	public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
    	WebSocketServer.onlineCount++;
    }
     
    public static synchronized void subOnlineCount() {
    	WebSocketServer.onlineCount--;
    }
}
