/**
 * 项目名: java-code-tutorials-websocket-spring
 * 包名:  net.fantesy84.sys.websocket.handler
 * 文件名: TextMessageHandler.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月31日
 */
package net.fantesy84.sys.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.fantesy84.sys.domain.WebSocketHistory;
import net.fantesy84.sys.websocket.mapper.WebSocketHistoryMapper;

/**
 * @author Andronicus
 * @since 2015年12月31日
 */
public class TextMessageHandler extends TextWebSocketHandler {
	private static final Logger logger = LoggerFactory.getLogger(TextMessageHandler.class);
	
	@Autowired
	private WebSocketHistoryMapper webSocketHistoryMapper;
	/* (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionEstablished(org.springframework.web.socket.WebSocketSession)
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#handleTextMessage(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.TextMessage)
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		String msg = message.getPayload();
		logger.debug("Incoming messgea contents: {}", msg);
		ObjectMapper mapper = new ObjectMapper();
		WebSocketHistory record = mapper.readValue(msg, WebSocketHistory.class);
		webSocketHistoryMapper.insert(record);
		if (record.getId() != null) {
			session.sendMessage(new TextMessage("success!"));
		} else {
			session.sendMessage(new TextMessage("error!"));
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#handleTransportError(org.springframework.web.socket.WebSocketSession, java.lang.Throwable)
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		super.handleTransportError(session, exception);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionClosed(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.CloseStatus)
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
}
