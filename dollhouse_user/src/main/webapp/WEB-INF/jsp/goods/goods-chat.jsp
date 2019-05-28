<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客服聊天</title>
<jsp:include page="../common/commonfile.jsp"></jsp:include>
<link href="<%=path %>/assets/css/chat/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/assets/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/assets/js/chat/umeditor.config.js"></script>
<script type="text/javascript" src="<%=path%>/assets/js/chat/umeditor.min.js"></script>
<script type="text/javascript" src="<%=path%>/assets/js/chat/zh-cn.js"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
</head>
<body>
<jsp:include page="../common/header.jsp"/><hr style="border: 1px solid red;"/>
<div class="am-cf admin-main">

    <!-- content start -->
    <div class="admin-content">
        <div class="" style="width: 80%;float:left;">
            <!-- 聊天区 -->
            <div class="am-scrollable-vertical" id="chat-view" style="height: 510px;">
                <ul class="am-comments-list am-comments-list-flip" id="chat">
                </ul>
            </div>
            <!-- 输入区 -->
            <div class="am-form-group am-form">
                <textarea class="" id="message" name="message" rows="5"  placeholder="这里输入你想发送的信息..."></textarea>
            </div>
            <!-- 接收者 -->
            <div class="" style="float: left">
                <p class="am-kai">发送给 : <span id="sendto">全体成员</span><button class="am-btn am-btn-xs am-btn-danger" onclick="$('#sendto').text('全体成员')">复位</button></p>
            </div>
            <!-- 按钮区 -->
            <div class="am-btn-group am-btn-group-xs" style="float:right;">
                <button class="am-btn am-btn-default" type="button" onclick="getConnection()"><span class="am-icon-plug"></span> 连接</button>
                <button class="am-btn am-btn-default" type="button" onclick="closeConnection()"><span class="am-icon-remove"></span> 断开</button>
                <button class="am-btn am-btn-default" type="button" onclick="checkConnection()"><span class="am-icon-bug"></span> 检查</button>
                <button class="am-btn am-btn-default" type="button" onclick="clearConsole()"><span class="am-icon-trash-o"></span> 清屏</button>
                <button class="am-btn am-btn-default" type="button" onclick="sendMessage()"><span class="am-icon-commenting"></span> 发送</button>
            </div>
        </div>
        <!-- 列表区 -->
        <div class="am-panel am-panel-default" style="float:right;width: 20%;">
            <div class="am-panel-hd">
                <h3 class="am-panel-title">在线列表 [<span id="onlinenum"></span>]</h3>
            </div>
            <ul class="am-list am-list-static am-list-striped" >
                <li>图灵机器人 <button class="am-btn am-btn-xs am-btn-danger" id="tuling" data-am-button>未上线</button></li>
            </ul>
            <ul class="am-list am-list-static am-list-striped" id="list">
            </ul>
        </div>
    </div>
    <!-- content end -->
</div>
<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
    <span class="am-icon-btn am-icon-th-list"></span>
</a>
<jsp:include page="../common/footer.jsp"/>

<script>
    $(function () {
        context.init({preventDoubleContext: false});
        context.settings({compress: true});
        context.attach('#chat-view', [
            {header: '操作菜单',},
            {text: '清理', action: clearConsole},
            {divider: true},
            {
                text: '选项', subMenu: [
                {header: '连接选项'},
                {text: '检查', action: checkConnection},
                {text: '连接', action: getConnection},
                {text: '断开', action: closeConnection}
            ]
            },
            {
                text: '销毁菜单', action: function (e) {
                e.preventDefault();
                context.destroy('#chat-view');
            }
            }
        ]);
    });
    if("${message}"){
        layer.msg('${message}', {
            offset: 0
        });
    }
    if("${error}"){
        layer.msg('${error}', {
            offset: 0,
            shift: 6
        });
    }
    var wsServer = null;
    var ws = null;
    wsServer = "ws://" + location.host+"${pageContext.request.contextPath}" + "/chatServer";
    ws = new WebSocket(wsServer); //创建WebSocket对象
    ws.onopen = function (evt) {
        layer.msg("已经建立连接", { offset: 0});
    };
    ws.onmessage = function (evt) {
        analysisMessage(evt.data);  //解析后台传回的消息,并予以展示
    };
    ws.onerror = function (evt) {
        layer.msg("产生异常", { offset: 0});
    };
    ws.onclose = function (evt) {
        layer.msg("已经关闭连接", { offset: 0});
    };

    /**
     * 连接
     */
    function getConnection(){
        if(ws == null){
            ws = new WebSocket(wsServer); //创建WebSocket对象
            ws.onopen = function (evt) {
                layer.msg("成功建立连接!", { offset: 0});
            };
            ws.onmessage = function (evt) {
                analysisMessage(evt.data);  //解析后台传回的消息,并予以展示
            };
            ws.onerror = function (evt) {
                layer.msg("产生异常", { offset: 0});
            };
            ws.onclose = function (evt) {
                layer.msg("已经关闭连接", { offset: 0});
            };
        }else{
            layer.msg("连接已存在!", { offset: 0, shift: 6 });
        }
    }

    /**
     * 关闭连接
     */
    function closeConnection(){
        if(ws != null){
            ws.close();
            ws = null;
            $("#list").html("");    //清空在线列表
            layer.msg("已经关闭连接", { offset: 0});
        }else{
            layer.msg("未开启连接", { offset: 0, shift: 6 });
        }
    }

    /**
     * 检查连接
     */
    function checkConnection(){
        if(ws != null){
            layer.msg(ws.readyState == 0? "连接异常":"连接正常", { offset: 0});
        }else{
            layer.msg("连接未开启!", { offset: 0, shift: 6 });
        }
    }

    /**
     * 发送信息给后台
     */
    function sendMessage(){
        if(ws == null){
            layer.msg("连接未开启!", { offset: 0, shift: 6 });
            return;
        }
        var message = $("#message").val();
        var to = $("#sendto").text() == "全体成员"? "": $("#sendto").text();
        if(message == null || message == ""){
            layer.msg("请不要惜字如金!", { offset: 0, shift: 6 });
            return;
        }
        /* alert('${sessionUser.userName}'); */
        //$("#tuling").text() == "已上线"? tuling(message):console.log("图灵机器人未开启");  //检测是否加入图灵机器人
        ws.send(JSON.stringify({
            message : {
                content : message,
                from : '${sessionUser.userName}',
                to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔
                time : getDateFull()
            },
            type : "message"
        }));
    }

    /**
     * 解析后台传来的消息
     * "massage" : {
     *              "from" : "xxx",
     *              "to" : "xxx",
     *              "content" : "xxx",
     *              "time" : "xxxx.xx.xx"
     *          },
     * "type" : {notice|message},
     * "list" : {[xx],[xx],[xx]}
     */
    function analysisMessage(message){
        message = JSON.parse(message);
        if(message.type == "message"){      //会话消息
            showChat(message.message);
        }
        if(message.type == "notice"){       //提示消息
            showNotice(message.message);
        }
        if(message.list != null && message.list != undefined){      //在线列表
            showOnline(message.list);
        }
    }

    /**
     * 展示提示信息
     */
    function showNotice(notice){
        $("#chat").append("<div><p class=\"am-text-success\" style=\"text-align:center\"><span class=\"am-icon-bell\"></span> "+notice+"</p></div>");
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    }

    /**
     * 展示会话信息
     */
    function showChat(message){
        var to = message.to == null || message.to == ""? "全体成员" : message.to;   //获取接收人
        var isSef = '${userid}' == message.from ? "am-comment-flip" : "";   //如果是自己则显示在右边,他人信息显示在左边
        var html = "<li class=\"am-comment "+isSef+" am-comment-primary\"><a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\" alt=\"\" src=\"${ctx}/"+message.from+"/head\"></a><div class=\"am-comment-main\">\n" +
                "<header class=\"am-comment-hd\"><div class=\"am-comment-meta\">   <a class=\"am-comment-author\" href=\"#link-to-user\">"+message.from+"</a> 发表于<time> "+message.time+"</time> 发送给: "+to+" </div></header><div class=\"am-comment-bd\"> <p>"+message.content+"</p></div></div></li>";
        $("#chat").append(html);
        $("#message").val("");  //清空输入区
        var chat = $("#chat-view");
        chat.scrollTop(chat[0].scrollHeight);   //让聊天区始终滚动到最下面
    }

    /**
     * 展示在线列表
     */
    function showOnline(list){
        $("#list").html("");    //清空在线列表
        $.each(list, function(index, item){     //添加私聊按钮
            var li = "<li>"+item+"</li>";
            if('${userid}' != item){    //排除自己
                li = "<li>"+item+" <button type=\"button\" class=\"am-btn am-btn-xs am-btn-primary am-round\" onclick=\"addChat('"+item+"');\"><span class=\"am-icon-phone\"><span> 私聊</button></li>";
            }
            $("#list").append(li);
        });
        $("#onlinenum").text($("#list li").length);     //获取在线人数
    }

    /**
     * 图灵机器人
     * @param message
     */
    /* function tuling(message){
        var html;
        $.getJSON("http://www.tuling123.com/openapi/api?key=6ad8b4d96861f17d68270216c880d5e3&info=" + message,function(data){
            if(data.code == 100000){
                html = "<li class=\"am-comment am-comment-primary\"><a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\" alt=\"\" src=\"${ctx}/static/img/robot.jpg\"></a><div class=\"am-comment-main\">\n" +
                        "<header class=\"am-comment-hd\"><div class=\"am-comment-meta\">   <a class=\"am-comment-author\" href=\"#link-to-user\">Robot</a> 发表于<time> "+getDateFull()+"</time> 发送给: ${userid}</div></header><div class=\"am-comment-bd\"> <p>"+data.text+"</p></div></div></li>";
            }
            if(data.code == 200000){
                html = "<li class=\"am-comment am-comment-primary\"><a href=\"#link-to-user-home\"><img width=\"48\" height=\"48\" class=\"am-comment-avatar\" alt=\"\" src=\"${ctx}/static/img/robot.jpg\"></a><div class=\"am-comment-main\">\n" +
                        "<header class=\"am-comment-hd\"><div class=\"am-comment-meta\">   <a class=\"am-comment-author\" href=\"#link-to-user\">Robot</a> 发表于<time> "+getDateFull()+"</time> 发送给: ${userid}</div></header><div class=\"am-comment-bd\"> <p>"+data.text+"</p><a href=\""+data.url+"\" target=\"_blank\">"+data.url+"</a></div></div></li>";
            }
            $("#chat").append(html);
            var chat = $("#chat-view");
            chat.scrollTop(chat[0].scrollHeight);
            $("#message").val("");  //清空输入区
        });
    } */

    /**
     * 添加接收人
     */
    function addChat(user){
        var sendto = $("#sendto");
        var receive = sendto.text() == "全体成员" ? "" : sendto.text() + ",";
        if(receive.indexOf(user) == -1){    //排除重复
            sendto.text(receive + user);
        }
    }

    /**
     * 清空聊天区
     */
    function clearConsole(){
        $("#chat").html("");
    }

    function appendZero(s){return ("00"+ s).substr((s+"").length);}  //补0函数

    function getDateFull(){
        var date = new Date();
        var currentdate = date.getFullYear() + "-" + appendZero(date.getMonth() + 1) + "-" + appendZero(date.getDate()) + " " + appendZero(date.getHours()) + ":" + appendZero(date.getMinutes()) + ":" + appendZero(date.getSeconds());
        return currentdate;
    }
</script>
</body>
</html>


<%-- <body>
	<jsp:include page="../common/header.jsp"></jsp:include><hr style="border: 1px solid red;"/>
	<div id="main">
		<!-- 聊天内容展示区 -->
		<div id="ChatBox" class="am-g am-g-fixed">
			<div class="am-u-lg-12" style="height:400px;border:1px solid #999;overflow-y:scroll;">
				<ul id="chatContent" class="am-comments-list am-comments-list-flip">
					<li id="msgtmp" class="am-comment" style="display:none;">
						<a href="">
					        <img class="am-comment-avatar" style="width: 50px;height: 50px;border-radius:50px;" src="<%=path %>/assets/images/chat/other.jpg" alt="用户"/>
					    </a>
					    <div class="am-comment-main" >
					        <header class="am-comment-hd">
					            <div class="am-comment-meta">
					              <a ff="nickname" href="#link-to-user" class="am-comment-author">某人</a>
					              <time ff="msgdate" datetime="" title="">2014-7-12 15:30</time>
					            </div>
					        </header>
					     	<div ff="content" class="am-comment-bd">此处是消息内容</div>
					    </div>
					</li>
				</ul>
			</div>
		</div>
		<!-- 聊天内容发送区域 -->
		<div id="EditBox" class="am-g am-g-fixed">
			<!--style给定宽度可以影响编辑器的最终宽度-->
			<script type="text/plain" id="myEditor" style="width:100%;height:140px;"></script>
			<button id="send" type="button" class="am-btn am-btn-primary am-btn-block">发送</button>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			//实例化编辑器
			var um=UM.getEditor('myEditor',{
				initialContent:"请输入聊天信息...",
				autoHeightEnabled:false,
				toolbar:[
		            'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
		            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
		            '| justifyleft justifycenter justifyright justifyjustify |',
		            'link unlink | emotion image video  | map'
		        ]
			});
			var message="你好";
			var nickname="风清扬";
			var socket = new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/websocket");
			socket.send(JSON.stringify({
	            message : {
	                content : message,
	                /* from : '${userid}', */
	                from:"顾客1",
	                to : "客服",      //接收人,如果没有则置空,如果有多个接收人则用,分隔
	                time : getDateFull()
	            },
	            type : "message"
	        }));
			//接收服务器消息
			socket.onmessage=function(ev){
				var obj=eval('('+ev.data+')');
				addMessage(obj);
			}
			$("#send").bind("click",function(){
				if(!um.hasContents()){// 判断消息输入框是否为空
					// 消息输入框获取焦点
					um.focus();
					//添加抖动效果
					$('.edui-container').addClass('am-animation-shake');
					setTimeout("$('.edui-container').removeClass('am-animation-shake')",1000);
				}else{
					//获取输入框的内容
		        	var txt = um.getContent();
		        	//构建一个标准格式的JSON对象
		        	var obj = JSON.stringify({
			    		nickname:nickname,
			    		content:txt
			    	});
		            // 发送消息
		            socket.send(obj);
		            // 清空消息输入框
		            um.setContent('');
		            // 消息输入框获取焦点
		            um.focus();
				}
			});
		});
		//人名nickname，时间date，是否自己isSelf，内容content
		function addMessage(msg){

			var box = $("#msgtmp").clone(); 	//复制一份模板，取名为box
			box.show();							//设置box状态为显示
			box.appendTo("#chatContent");		//把box追加到聊天面板中
			box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
			box.find('[ff="msgdate"]').html(msg.date); 		//在box中设置时间
			box.find('[ff="content"]').html(msg.content); 	//在box中设置内容
			box.addClass(msg.isSelf? 'am-comment-flip':'');	//右侧显示
			box.addClass(msg.isSelf? 'am-comment-warning':'am-comment-success');//颜色
			box.css((msg.isSelf? 'margin-left':'margin-right'),"20%");//外边距
			
			$("#ChatBox div:eq(0)").scrollTop(999999); 	//滚动条移动至最底部
			
		}
	
	</script>
</body>
</html> --%>