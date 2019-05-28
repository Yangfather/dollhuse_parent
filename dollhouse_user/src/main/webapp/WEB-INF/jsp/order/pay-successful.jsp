<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path=request.getContextPath();
   String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=path%>/assets/js/jquery-1.9.1.js"></script>
<title>支付成功</title>
<style type="text/css">
	.bgbody {
		  width: 100%;
		  height: 600px;
		  background: url(assets/images/login/login1.jpg) no-repeat center;
		  background-size: 100% 100%;
		  position: relative;
		  z-index: -10;
		}
</style>
</head>
<body class="bgbody">
	<div style="width: 100%;height: 600px;">
		<h1>恭喜您，购买成功！</h1>
		<h1>页面将在</h1><span id="time"></span><h1>秒后回到首页</h1>
	</div>
	
	<script type="text/javascript">
		var sss='${data}';
		
		var wait=5;
		
		function time(o) {
		
		        if (wait == 0) {
		
		           window.location.href="common/returnIndex";
		
		            } 
		
				else {
		
					document.getElementById("time").innerHTML=wait;
		
		            wait--;
		
		            setTimeout(function() {
		
		                time(o)
		
		            },
		
		            1000)
		
		        }
		
		    }
		alert(sss=='200')
		if(sss=='200'){
			$(function(){time(this);})
		}
</script>
</body>
</html>