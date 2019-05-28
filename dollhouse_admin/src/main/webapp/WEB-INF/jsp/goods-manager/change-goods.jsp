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
	<div class="page" style="margin-top: 20px;">
		<div class="wrapper fn-clear">
			<div class="count-tit">
				<i class="iconfont">&#xe605;</i>
				<span class="title-con">商品修改</span>
			</div><hr>
			<div class="count-con">
				<div class="search-block mt20">
					<form id="searchForm" action="<%=path %>/admin/manager/changePage">
						<label>商品名称：</label>
						<input type="text" name="dollGoodsName" class="sch-input"/>
						<label>商品编号：</label>
						<input type="text" name="dollOrgancode" class="sch-input">
						<input type="submit" value="查询" class="sch-btn">
					</form>
				</div>
				<div class="tab-top">
					<c:if test="${isAdmin }">
						
					</c:if>
					<h2 style="color:white">商品修改列表</h2>
				</div>
					<table class="counttable mt0" cellpadding="0" cellspacing="0" border="0" id="tab1">
						<colgroup>
							<col width="25">
							<col width="160">
							<col width="160">
							<col width="160">
							<col width="160">
						</colgroup>
						<thead>
							<tr class="table-tit-ot">
								<th>ID</th>
								<th>商品名称</th>
								<th>商品编号</th>
								<th>商品类别</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tableDiv" url="<%=basePath%><%=path %>/admin/manager/changePage"></tbody>
					</table>
				</div>
			</div>
		</div>
	<script type="text/javascript">
		diyou.use("application/system",function(fn){fn.tableGrid("#tableDiv","#searchForm")});
	</script>
	<jsp:include page="../common/footer1.jsp"></jsp:include>
</body>
</html>