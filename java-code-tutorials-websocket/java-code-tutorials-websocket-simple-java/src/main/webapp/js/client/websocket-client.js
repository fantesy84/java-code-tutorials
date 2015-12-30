/**
 * 
 */
var websocket = null;
var websocket_uri = "ws://127.0.0.1:8080/websocket-demo/websocket_server";
if ("WebSocket" in window) {
	websocket = new WebSocket(websocket_uri);
} else {
	alert('Not support websocket');
}

websocket.onopen = function(event) {
	console.log("open");
};
//收到消息的回调方法
websocket.onmessage = function(event) {
	setMessageIntoHtml("message_area", event.data);
};
websocket.onclose = function(event) {
	console.log("WebSocketClosed!");
};
websocket.onerror = function(event) {
	alert("WebSocketError!");
};

window.onbeforeunload = function() {
	websocket.close();
}

/**
 * 将消息显示在指定ID标签内
 * @param {String} id 标签ID
 * @param {Object} message 消息对象
 */
function setMessageIntoHtml(id, message) {
	document.getElementById(id).innerHTML += message + "</br>";
}

/**
 * close the websocket instance
 */
function closeWebSocket() {
	websocket.close();
}

function send(id) {
	var message = document.getElementById(id).value;
	websocket.send(message);
}