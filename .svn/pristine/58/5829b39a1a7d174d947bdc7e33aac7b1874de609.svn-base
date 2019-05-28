<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ page isELIgnored="false"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
	<c:if test="${data != null && data.totalSize > 0}">
	<c:forEach var="item" items="${data.items}" varStatus="i">
		<tr id="tab2">
			<td><input type="checkbox" value="${item.id}"></td>
			<td><p class="nowrap w120" title="${item.dollGoodsName}">${item.dollGoodsName}</p></td>
			<td><p class="nowrap w100" title="${item.dollOrgancode}">${item.dollOrgancode}</p></td>
			<td><p class="nowrap w100" title="${item.dollType}">${item.dollType}</p></td>
			<td>
				<a href="<%=basePath%><%=path %>/admin/manager/searchDetial?id=${item.id}" class="see link">详情</a>
			</td> 
		</tr>
	</c:forEach>
		<tr>
			<td colspan="5"><div id="pages"  data-page="${data.pageNo-1}" data-total="${data.totalSize}" data-epage="10"></div></td>
		</tr>
	</c:if>
	<c:if test="${data == null || data.totalSize <= 0}">
		<tr>
			<td colspan="5"><div class="not-data">暂无数据</div></td>
		</tr>
	</c:if>