<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>

<!DOCTYPE html>
<html>

<head>
	<base href="<%=basePath%>">
	
    <meta charset="utf-8">
    <title>登录</title>
    
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="assets/css/login.css">
    <link rel="stylesheet" type="text/css" href="assets/css/datepicker/css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="assets/js/widgets/layer/skin/layer.css">
    
    <script type="text/javascript" src="assets/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="assets/js/diyou.js"></script>
    <script type="text/javascript" src="assets/js/base.js"></script>
</head>
<body>
    <div class="login">
    	<div class="login-box">
    		<form id="loginForm" role="form" method="post">
				<div class="login-cont">
					<div class="login-item fn-clear">
						<label for="" class="login-label"><i class="user"></i></label>
						<input class="login-input" type="text" id="userName" name="userName" placeholder="用户名" autocomplete="off">
					</div>
					<div class="login-item">
						<label for="" class="login-label"><i class="pwd"></i></label>
						<input class="login-input ng-pristine" type="password" id="password" name="password" placeholder="输入密码" autocomplete="off">
					</div>
					<div class="login-item">
							<label class="login-label" style="float:left;"><i class="user"></i></label> 
							<input type="text" class="login-input" name="verifycode" id="verifycode"
								placeholder="请输入验证码" style="width:105px;float:left;"> <img style="float:left;margin-left:6px;"
								src="common/public/verifycode1/1"
								onclick="this.src='common/public/verifycode1/'+Math.random();" />
					</div>
					<div class="login-item">
						<input class="login-btn" type="submit" value="登录" style="margin-top:20px;">
					</div>
					
					<span class="error" id="errorMessage"></span>
				</div>
				<div id="loading" class="layui-layer layui-anim layui-layer-loading " style="z-index: 19891015;position:absolute;top:100px;left:200px;display:none;"><div class="layui-layer-content layui-layer-loading0"></div><span class="layui-layer-setwin"></span></div>
			</form>
    	</div>
    	<div class="copyright">
              Copyright 天津市滨海新区高新技术开发区金融办  ©2015-2018
	    </div>
    </div>
    
    <script type="text/javascript">
    $(function(){
    	$("#userName,#password,#verifycode").on("keyup",function(e){
    		var ev = document.all ? window.event : e;
    		if(ev.keyCode != 13){
    			$('#errorMessage').html("");
    		}
    	});
    	$("#loginForm").on("submit",function(){
    		if($('#userName').val()=="" || $('#password').val()==""){
    			$('#errorMessage').html("用户名,密码不能为空");
    		}else{
    			$("#loading").show();
	    		$.ajax({
	    			url:"public/login",
	    			data:{
	    				adminName: $('#userName').val(),
		                adminPassword: $('#password').val(),
		                code: $('#verifycode').val()
	    			},
	    			type:"post",
	    			dataType:"json"
	    		}).done(function(data){
	    			$("#loading").hide();
	    			 if(data.status == 200) {
		                    location.href = "system/index";
		                } else {
		                    $('#errorMessage').html(data.description);
		                }
	    		});
    		}
    		return false;
    	});
    });
      
    </script>
</body>
</html>