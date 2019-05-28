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
	<div class="page" style="margin-top: 20px">
		<div class="wrapper fn-clear">
			<div class="count-tit">
				<i class="iconfont">&#xe605;</i>
				<span class="title-con">左侧导航栏菜单</span>
			</div><hr>
			<table class="counttable mt0" cellpadding="0" cellspacing="0" border="0" id="tab1">
				<colgroup>
					<col width="25">
					<col width="160">
					<col width="160">
					<col width="160">
				</colgroup>
				<thead>
					<tr class="table-tit-ot">
						<th>ID</th>
						<th>商品名称</th>
						<th>商品图片</th>
						<th>操作</th>
					</tr>
				</thead>
				<c:forEach var="item" items="${data}">
					<tbody id="tableDiv">
						<tr style="background-color:#B0E0E6 ">
						<td colspan="3">
							<div class="tab-top" style="background-color:#B0E0E6">
								<h2 style="color:white">${item.name }</h2>
							</div>
						</td>
						<td>
							<a href="<%=path %>/system/menu/search" class="see link">添加</a>
						</td>	
						</tr>
						<c:if test="${item.subMenu==null ||item.subMenu.size()<=0 }">
							<tr>
								<td colspan="4">
									<strong>该菜单下还没有子菜单！</strong>
									<a href="<%=path %>/system/menu/search" class="see link">添加</a>
								</td>
							</tr>
						</c:if>
						<c:if test="${item.subMenu!=null }">
							<c:forEach var="secMenu" items="${item.subMenu }">
								<tr>
									<td><input type="checkbox" value="${secMenu.id}"></td>
									<td><p class="nowrap w120" title="${secMenu.name}">${secMenu.name}</p></td>
									<td><p class="nowrap w120" title="${secMenu.url}"><img src="dollhouse_admin/${secMenu.url}" alt="${secMenu.name}" style="width: 50;height: 40px;"></p></td>
									<td><a href="javascript:;" onclick="delSec(this.id)" id="${secMenu.id}" class="see link">删除</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</c:forEach>
			</table> 
		</div>
	</div>
	<jsp:include page="../common/footer1.jsp"></jsp:include>
	<script type="text/javascript">
		function delSec(id) {
			$.ajax({
				url:"<%=path%>system/secMenu/delete",
				type:"post",
				data:{"id":id}
			}).done(function(data){
				window.location.reload();
			});
		}
	</script>
</body>
</html>