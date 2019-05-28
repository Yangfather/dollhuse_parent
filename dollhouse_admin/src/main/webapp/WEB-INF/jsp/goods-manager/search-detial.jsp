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
<body class="bodybg">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<c:if test="${data==null }">
		detial没数据
	</c:if>
	<div class="page">
		<div class="wrapper fn-clear">
			<div class="count-tit">
				<i class="iconfont">&#xe605;</i>
				<span class="title-con">商品修改</span>
			</div><hr>
			<div class="count-con">
				<div class="tab-top">
					<c:if test="${isAdmin }">
						
					</c:if>
					<h2 style="color:white">商品基本信息</h2>
				</div>
				<div class="wrapper fn-clear">
					<input type="text" name="goodsId" id="goodsId" value="${data.id }" >
					<!-- 商品名称 -->
					<label>商品名称：</label>
					<input type="text" name="name" id="name" value="${data.name }">
					<!-- 商品编号 -->
					<label>商品编号：</label>
					<input type="text" name="organCode" id="organCode" value="${data.organCode }" disabled="disabled">
					<!-- 商品颜色 -->
					<label>商品颜色：</label>
					<input type="text" name="color" id="color" value="${data.color }"><br>
					<!-- 最低价 -->
					<label>最低价：</label>
					<input type="text" name="lowerPrice" id="lowerPrice" value="${data.lowerPrice }">
					<!-- 最高价 -->
					<label>最高价：</label>
					<input type="text" name="heigherPrice" id="heigherPrice" value="${data.heigherPrice }">
					<!-- 商品状态 -->
					<label>商品状态：</label>
					<input type="text" name="status" id="status" value="${data.status }">
				</div>
			</div>
			<div class="count-con">
				<div class="tab-top">
					<h2 style="color:white">商品类型信息</h2>
				</div>
				<div class="wrapper fn-clear">
					<c:if test="${data.types==null }">
						商品类型信息为空，请到删除页面删除该商品信息并重新录入！
					</c:if>
					<c:if test="${data.types!=null }">
						<c:forEach var="type" items="${data.types }">
							<!-- 商品型号 -->
							<label>商品型号：</label>
							<input type="text" name="type" value="${type.type }">
							<!-- 商品价格 -->
							<label>商品价格：</label>
							<input type="text" name="price" value="${type.price }"><br>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<div class="count-con">
				<div class="tab-top">
					<h2 style="color:white">商品图片信息</h2>
				</div>
				<div class="wrapper fn-clear">
					<c:if test="${data.imgUrl==null }">
						商品类型信息为空，请到删除页面删除该商品信息并重新录入！
					</c:if>
					<c:if test="${data.imgUrl!=null }">
						<c:forEach var="img" items="${data.imgUrl }">
							<!-- 商品图片-->
							<img alt="${data.name }" src="<%=path %>/${img.URL }" name="imgUrl">
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../common/footer1.jsp"></jsp:include>  
</body>
</html>