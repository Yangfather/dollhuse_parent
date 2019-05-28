<%@ page language="java" pageEncoding="UTF-8"%>
    <%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">

 	<meta http-equiv="X-UA-Compatible" content="IE=edge">

  	<meta name="keywords" content="HTML,CSS,XML,JavaScript">
  	<script type="text/javascript" src="assets/js/jquery-1.9.1.min.js"></script>
  	<link rel="stylesheet" href="assets/css/login/style.css">
</head>
<body>
	<div style="width:305px;height: 70px">
		<input type="text" id="code_input" name="code" placeholder="请输入验证码" style="width: 110px;height:42px;float: left;" />
		<div id="v_container" style="width: 160px;height: 42px;float: left;margin-top: 27px;clear: right;"></div>
		<font id="worning" color="red" size="3" style="display: none;">验证码错误，请重新输入！</font>
	</div>
</body>
</html>