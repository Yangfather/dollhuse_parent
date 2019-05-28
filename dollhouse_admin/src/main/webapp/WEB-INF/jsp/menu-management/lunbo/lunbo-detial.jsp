<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<link rel="stylesheet" href="<%=path %>/assets/css/carts.css">
<jsp:include page="../../common/base.jsp"></jsp:include>
<style>
.count-tit{
  float:left;
  line-height: 40px;
  border-bottom: 1px solid #ebebeb;
  padding: 3px 0;
}

</style>
<body class="bodybg">
	<jsp:include page="../../common/top.jsp"></jsp:include>
	<c:if test="${data==null }">
		detial没数据
	</c:if>
	<div class="page" style="margin-top: 30px;height: 650px;">
		<div class="wrapper fn-clear">
			<div class="count-tit">
				<i class="iconfont">&#xe605;</i>
				<span class="title-con">导航菜单添加</span>
			</div><hr>
			<div class="count-con">
				<div class="tab-top">
					<c:if test="${isAdmin }">
						
					</c:if>
					<h2 style="color:white">商品基本信息</h2>
				</div>
				<div class="wrapper fn-clear" style="font:15px bold;">
					<input type="text" name="goodsId" id="goodsId" value="${data.id }" style="display: none;">
					<!-- 商品名称 -->
					<b class="ex-tit" style="float: left;">商品名称：</b>
					<p id="name">${data.name }</p>
					<!-- 商品编号 -->
					<b class="ex-tit" style="float: left; ">商品编号：</b>
					<p class="nowrap">${data.organCode }</p>
					<!-- 商品颜色 -->
					<b class="ex-tit" style="float: left; ">商品颜色：</b>
					<p class="nowrap">${data.color }</p>
					<!-- 最低价 -->
					<b class="ex-tit" style="float: left; ">最低价：</b>
					<p class="nowrap" id="lowerPrice">${data.lowerPrice }</p>
					<!-- 最高价 -->
					<b class="ex-tit" style="float: left; ">最高价：</b>
					<p class="nowrap">${data.heigherPrice }</p>
					<!-- 商品状态 -->
					<b class="ex-tit" style="float: left; ">商品状态：</b>
					<p class="nowrap">${data.status }</p>
				</div>
			</div>
			<div class="count-con">
				<div class="tab-top">
					<h2 style="color:white">商品类型信息</h2>
				</div>
				<div class="wrapper fn-clear" style="font:15px bold;">
					<c:if test="${data.types==null }">
						<div style="height: 80px;width: 100%;">
						<span style="font: 30px  bolder;color:#D2691E;margin-top: 5px;">商品类型信息为空，请到删除页面删除该商品信息并重新录入！</span>
						</div>
						
					</c:if>
					<c:if test="${data.types!=null }">
						<c:forEach var="type" items="${data.types }">
							<!-- 商品型号 -->
							<b class="ex-tit" style="float: left; ">商品型号：</b>
							<p class="nowrap">${type.type }</p> 
							<!-- 商品价格 -->
							<b class="ex-tit" style="float: left; ">商品价格：</b>
							<p class="nowrap">${type.price }</p> 
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
						<div style="height: 80px;width: 100%;">
						<span style="font: 30px  bolder;color:#D2691E;margin-top: 5px;">商品图片信息为空，请到删除页面删除该商品信息并重新录入！</span>
						</div>
					</c:if>
					<c:if test="${data.imgUrl!=null }">
						<c:forEach var="img" items="${data.imgUrl }">
							<!-- 商品图片-->
							<img alt="${data.name }" src="<%=path %>/${img.URL }" name="imgUrl" style="width: 100px;height: 100px;float: left;">
						</c:forEach>
					</c:if>
				</div>
				<div>
					<input type="button" value="确认添加" onclick="addMenu()">
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../../common/footer1.jsp"></jsp:include>
<script type="text/javascript">
	function addMenu() {
		
		var urls=document.getElementsByName("imgUrl");
		var url;
		var linkId=document.getElementById("goodsId").value;
		var name=document.getElementById("name").innerHTML;
		var lowerPrice=document.getElementById("lowerPrice").innerHTML;
		for(var i=0,j=urls.length;i < j;i++){
			url=urls[0].src.replace("http://localhost:8080/dollhouse_admin/","");
			url=url.replace("http://","");
			url=url.replace("localhost:8080/","");
			url=url.replace("dollhouse_admin//","");
			url=url.replace("dollhouse_admin/","");
			url=url.replace("dollhouse_user//","")
			url=url.replace("dollhouse_user/","")
			
		}
		/* alert(";url:"+url+";linkId:"+linkId+";name:"+name) */
		$.ajax({
			url:"<%=path%>/admin/lunbo/update",
			type:"post",
			data:{"url":url,"linkId":linkId,"name":name},
		}).done(function(data){
			alert("添加成功！")
			if(data==200){
				alert("添加成功！")
			}
		})
	}

</script>  
</body>
</html>