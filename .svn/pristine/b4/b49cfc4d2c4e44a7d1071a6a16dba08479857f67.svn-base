<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<head>
<base href="<%=basePath%>">
<%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/assets/css/extend.css">
<script type="text/javascript" src="<%=path %>/assets/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/assets/js/diyou.js"></script>
<script type="text/javascript" src="<%=path %>/assets/js/base.js"></script> 
<title>商品详情</title>
<style type="text/css">
.leftContainer{
	border: 1px solid black;
	width: 80%;
	min-height: 400px;
	float: left;
	margin-left: 7%;
}
.goods-list{
	border:1px solid orange;
	width: 23%;
	margin-left:1.5%;
	min-height: 395px;
	float: left; 
}
.rightCOntainer{
	border: 1px solid #FFEFD5;
	width: 6%;
	min-height: 200px;
	float: left;
	margin-top:200px;
	margin-left:93%;
	position:fixed;
	background-color:#FFEFD5;
	z-index: 10;
}
.icon{
	width: 50px;
	height: 50px;
	margin-left:15%;
}
.page-list{
	width: 80%;
	height: 60px;
	margin-left: 10%;
	background-color: #FFF5EE;
	border: 1px solid black;
	float: left;
}
.leftInfo{
	width: 47.5%;
	height: 200px;
	margin:10px;
	float: left;
}
</style>
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include><hr style="border: 1px solid red;"/>
<!-- 商品详情展示 -->
<!-- <div class="mainContainer"> -->
<!-- 容器 -->
	<div class="leftContainer"> 
	
		<table style="width: 100%;">
			<tbody id="tableDiv" url="<%=basePath%>/dollhouse_user/user/goods/goods-page">
			<tr>
				<td></td>
				<td>loading。。。。</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div class="rightContainer">
		<a ><img alt="购物车" src="<%=path %>/assets/images/shopCar.png"  class="icon" style="margin-top: 50%;"></a><br>
		<a href="javascript:returnTop();" target="_self"><img alt="返回顶部" src="<%=path %>/assets/images/returnTop.png" class="icon" style="margin-top: 30px;"></a>
	</div>
	<!-- 分页 -->
	
<!-- </div> -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<script type="text/javascript">
	diyou.use("application/system",function(fn){fn.tableGrid("#tableDiv","#searchForm")});
	//var search=document.getElementById("search").value;
	//document.getElementById("searchGoods").value=search;
	var sdelay=0;
	function returnTop() {
		/* scrollBy函数第二个参数我设了-100，越大（比如-10）滚动越慢，越小滚动越快，启动滚动只需在页面底部加个 */
	 window.scrollBy(0,-50);//Only for y vertical-axis
	 if(document.body.scrollTop>0) { 
	  sdelay= setTimeout('returnTop()',50);
	 }
	}
</script>
</body>
</html>