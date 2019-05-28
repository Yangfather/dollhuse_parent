<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<jsp:include page="../common/base.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/lunbo.css">
<body class="bodybg">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="page" style="margin-top: 20px">
		<div class="wrapper fn-clear">
			<div class="count-tit">
				<i class="iconfont">&#xe605;</i>
				<span class="title-con">轮播图片管理</span>
			</div><hr>
			<div class="pic-container">
				<c:forEach var="lunbo" items="${data }">
					<form action="<%=path %>/admin/menuManager/lunboPage">
						<div class="pic-list">
							<input type="text" value="${lunbo.id }" name="picId" class="pic-id">
							<%-- <img alt="${lunbo.name }" src="<%=path%>/${lunbo.menuImgUrl}" class="pic-url"><br> --%>
							<img alt="${lunbo.name }" src="dollhouse_user/${lunbo.menuImgUrl}" class="pic-url"><br>
							<input type="submit" value="修改" onclick="picChange(this.id)" class="pic-btn" id="${lunbo.id }" >
						</div>
					</form>
					
				</c:forEach>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer1.jsp"></jsp:include>
</body>
</html>