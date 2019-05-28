package com.dollhouse.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.service.BaseService;


@ServerEndpoint(value = "/chatServer" ,configurator=HttpSessionConfigurator.class)
public class ChatServer {
	@Autowired
	private BaseService baseService;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Vector<Session> room = new Vector<Session>();
	 private static int onlineCount = 0; //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	    private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<ChatServer>();
	    private Session session;    //与某个客户端的连接会话，需要通过它来给客户端发送数据
	    private String userid;      //用户名
	    private String admin;
	    private HttpSession httpSession;    //request的session

	    private static List list = new ArrayList<>();   //在线列表,记录用户名称
	    private static Map routetab = new HashMap<>();  //用户名和websocket的session绑定的路由表

	    /**
	     * 连接建立成功调用的方法
	     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	     */
	    @OnOpen
	    public void onOpen(Session session, EndpointConfig config){
	        this.session = session;
	        webSocketSet.add(this);     //加入set中
	        addOnlineCount();           //在线数加1;
	        this.httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
	        this.userid=(String) httpSession.getAttribute(Constant.SESSION_NAME);    //获取当前用户
	        this.admin=(String)httpSession.getAttribute(Constant.SESSION_Admin);  
	        list.add(userid);           //将用户名加入在线列表
	        list.add(admin);
	        routetab.put(userid, session);   //将用户名和session绑定到路由表
	        routetab.put(admin, session);
	        String message = getMessage("[" + userid + "]加入聊天室,当前在线人数为"+getOnlineCount()+"位", "notice",  list);
	        broadcast(message);     //广播
	    }

	    /**
	     * 连接关闭调用的方法
	     */
	    @OnClose
	    public void onClose(){
	        webSocketSet.remove(this);  //从set中删除
	        subOnlineCount();           //在线数减1
	        list.remove(userid);        //从在线列表移除这个用户
	        routetab.remove(userid);
	        String message = getMessage("[" + userid +"]离开了聊天室,当前在线人数为"+getOnlineCount()+"位", "notice", list);
	        broadcast(message);         //广播
	    }

	    /**
	     * 接收客户端的message,判断是否有接收人而选择进行广播还是指定发送
	     * @param _message 客户端发送过来的消息
	     */
	    @OnMessage
	    public void onMessage(String _message) {
	    	System.out.println(_message);
	        JSONObject chat = JSON.parseObject(_message);
	        JSONObject message = JSON.parseObject(chat.get("message").toString());
	        System.out.println(message.get("to").toString());
	        if(message.get("to") == null || message.get("to").equals("")){      //如果to为空,则广播;如果不为空,则对指定的用户发送消息
	            broadcast(_message);
	        }else{
	            String [] userlist = message.get("to").toString().split(",");
	            singleSend(_message, (Session) routetab.get(message.get("from")));      //发送给自己,这个别忘了
	            for(String user : userlist){
	                if(!user.equals(message.get("from"))){
	                    singleSend(_message, (Session) routetab.get(user));     //分别发送给每个指定用户
	                }
	            }
	        }
	    }

	    /**
	     * 发生错误时调用
	     * @param error
	     */
	    @OnError
	    public void onError(Throwable error){
	        error.printStackTrace();
	    }

	    /**
	     * 广播消息
	     * @param message
	     */
	    public void broadcast(String message){
	        for(ChatServer chat: webSocketSet){
	            try {
	                chat.session.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	                continue;
	            }
	        }
	    }

	    /**
	     * 对特定用户发送消息
	     * @param message
	     * @param session
	     */
	    public void singleSend(String message, Session session){
	        try {
	            session.getBasicRemote().sendText(message);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 组装返回给前台的消息
	     * @param message   交互信息
	     * @param type      信息类型
	     * @param list      在线列表
	     * @return
	     */
	    public String getMessage(String message, String type, List list){
	        JSONObject member = new JSONObject();
	        member.put("message", message);
	        member.put("type", type);
	        member.put("list", list);
	        return member.toString();
	    }

	    public  int getOnlineCount() {
	        return onlineCount;
	    }

	    public  void addOnlineCount() {
	        ChatServer.onlineCount++;
	    }

	    public  void subOnlineCount() {
	        ChatServer.onlineCount--;
	    }
	
	
	/**
	 * 用户接入
	 * @param session 可选
	 *//*
	@OnOpen
	public void onOpen(Session session){
		room.addElement(session);
		System.out.println("chatServer");
	}
	
	*//**
	 * 接收到来自用户的消息
	 * @param message
	 * @param session
	 *//*
	@OnMessage
	public void onMessage(String message,Session session){
		System.out.println("OnMessage");

		//把用户发来的消息解析为JSON对象
		JSONObject obj = JSONObject.fromObject(message);
		//向JSON对象中添加发送时间
		obj.put("date", df.format(new Date()));
		//遍历聊天室中的所有会话
		for(Session se : room){
			//设置消息是否为自己的
			obj.put("isSelf", se.equals(session));
			//发送消息给远程用户
			se.getAsyncRemote().sendText(obj.toString());
		}
	}
	
	*//**
	 * 用户断开
	 * @param session
	 *//*
	@OnClose
	public void onClose(Session session){
		room.remove(session);
	}
	
	*//**
	 * 用户连接异常
	 * @param t
	 *//*
	@OnError
	public void onError(Throwable t){
		
	}*/

}
