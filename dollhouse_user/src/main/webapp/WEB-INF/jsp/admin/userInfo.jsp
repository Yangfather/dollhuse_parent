<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() ;
%>
<html>
<head>
<%@ page isELIgnored="false"%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/userinfo/userinfo.css">
<script type="text/javascript" src="<%=path %>/assets/js/jquery-1.9.1.min.js"></script>
<title>信息修改</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include><hr style="border: 1px solid red;"/>
	<div style="display: none;">
		<input type="text" value="${data.id }" id="userId">
	</div>
	<div class="name-change">
		<label>用&nbsp;户&nbsp;名：</label><input type="text" value="${data.userName }" disabled="disabled" id="baseName"><br>
		<label>更&nbsp;名&nbsp;为：</label><input type="text" value="" placeholder="请输入4-8位用户名" id="userName"><br>
		<input type="button" value="确认修改" onclick="nameChange()">
	</div>
	<div class="password-change">
		<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label><input type="password" value="${data.userPassword }" disabled="disabled"><br>
		<label>密码修改：</label><input type="password" value="" placeholder="请输入要修改的密码"  id="password1"><br>
		<label>确认密码：</label><input type="password" value="" placeholder="请确认密码"  id="password2"><br>
		<input type="button" value="获取验证码" onclick="sendEmail()">
		<input type="text" value="" placeholder="请填写邮箱验证码！" id="code" style="width: 150px;"><br>
		<input type="button" value="确认修改" onclick="passwordChange()">
	</div>
	<div class="email-change">
		<label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;:</label><input type="text" value="${data.userEmail }" id="emailAddress" disabled="disabled"><br>
		<label>新&nbsp;邮&nbsp;箱：</label><input type="text" value="" placeholder="请输入要修改为的邮箱" id="newEmail"><br>
		<input type="button" value="确认修改" onclick="emailChange()">
	</div>
	<div class="phone-change">
		<label>手&nbsp;机&nbsp;号：</label><input type="text" value="${data.userPhone }"><br>
		<label>更&nbsp;换&nbsp;为：</label><input type="text" value="" placeholder="请输入要修改为的手机号" disabled="disabled" id="newPhone"><br>
		<input type="button" value="确认修改" onclick="phoneChange()">
	</div>
	<div style="display: none;">
		<input type="${data.userIdentification }" disabled="disabled">
	</div>
	<hr style="border: 1px solid red;"/>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<script type="text/javascript">
	/* 用户名修改 */
		function nameChange() {
			var id=$("#userId").val();
			/* alert(id); */
			var baseName=$("#baseName").val();
			var name=$("#userName").val();
			if((/^[a-z0-9_-]{4,8}$/).test(name)){
				if(name!=baseName){
					$.ajax({
						url:"<%=path%>/member/userName/change",
						type:"post",
						data:{"id":id,"name":name}
					}).done(function(data){
						alert(data.description);
					})
				}else{
					alert("新用户名不能与原用户名相同！")
				}
				
			}else{
				alert("用户名输入有误，请核对并重新输入！")
			}
		}
	/* 获取邮箱验证码 */
		function sendEmail() {
			var id=$("#userId").val();
			var password1=$("#password1").val();
			var password2=$("#password2").val();
			var sendAddress=$("#emailAddress").val();
			if((/^[a-z0-9_-]{6,16}$/).test(password1)){
				if((password1==password2)){
					$.ajax({
						url:"<%=path%>/member/member/code",
						type:"post",
						data:{"sendAddress":sendAddress}
					}).done(function(data){
						
						alert(data.description);
					})
				}else{
					alert("两次密码输入不一致，请检查后输入！")
				}	
			}else{
				alert("密码格式不正确！")
			}
		}
	/* 密码修改 */
	function passwordChange() {
		var id=$("#userId").val();
		var password1=$("#password1").val();
		var password2=$("#password2").val();
		var code=$("#code").val();
		if(code.replace(/\s+/g, "")==""){
			alert("验证码不能为空！");
		}else{
			if((/^[a-z0-9_-]{6,16}$/).test(password1)){
				if((password1==password2)){
					$.ajax({
						url:"<%=path%>/member/userPassword/change",
						type:"post",
						data:{"id":id,"password":password1,"code":code}
					}).done(function(data){
						alert(data.description);
					})
				}else{
					alert("两次密码输入不一致，请检查后输入！")
				}	
			}else{
				alert("密码格式不正确！")
			}
		}
	}
	/* 邮箱修改 */
	function emailChange() {
		//Email正则
		var ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var id=$("#userId").val();
		var newEmail=$("#newEmail").val();
		if(ePattern.test(newEmail)){
			$.ajax({
				url:"<%=path%>/member/userEmail/change",
				type:"post",
				data:{"id":id,"email":newEmail}
			}).done(function(data){
				alert(data.description);
			})
		}
	}
	/* 手机号修改 */
	function phoneChange() {
		//手机号正则
		var mPattern = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
		var id=$("#userId").val();
		var newPhone=$("#newPhone").val();
		if(mPattern.test(newPhone)){
			$.ajax({
				url:"<%=path%>/member/userPassword/change",
				type:"post",
				data:{"id":id,"phone":newPhone}
			}).done(function(data){
				alert(data.description);
			})
		}
	}
	</script>
</body>
</html>