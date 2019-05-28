<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+"/";
%>
<%@ page isELIgnored="false"%>
<c:if test="${ data.totalSize >0 }">
	<tr>
		<td>
			<c:forEach var="item" items="${data.items }">
				<div class="leftInfo">
					<img alt="${item.dollGoodsName }" src="dollhouse_admin/${item.dollImgUrl }">
					<label>${item.dollGoodsName }</label>
				</div>
			</c:forEach>
		</td>
	</tr>
	<tr style="width: 100%;margin-bottom: 10px;">
		<td style="width: 100%;"><div class="page-list" id="pages" data-page="${data.pageNo-1}" data-epage="10" }></div></td>
	</tr>
</c:if>
<c:if test="${data == null || data.totalSize <= 0 }">

	<tr>
		<td><span>暂无数据</span></td>
	
	</tr>
</c:if>

