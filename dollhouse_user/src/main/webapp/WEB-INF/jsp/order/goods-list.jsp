<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!-- 商品列表 -->
	<c:if test="${data != null && data.totalSize > 0}">
	<c:forEach var="item" items="${data.items}" varStatus="i">
		<tr id="tab2">
			<td><input type="checkbox" value="${item.id}"></td>
			<td><p class="nowrap w120" title="${item.date}">${item.date}</p></td>
			<td><p class="nowrap w100" title="${item.platformName}">${item.platformName}</p></td>
			<td><p class="nowrap w100" title="${item.url}">${item.url}</p></td>
			<td><p class="nowrap w180" title="${item.content}">${item.content}</p></td>
			<td>${item.warningKey }</td>
			<td>
				<a href="<%=basePath%>sentiment/mangement/detail?id=${item.id}" class="see link">查看</a>
			</td> 
		</tr>
	</c:forEach> 
	<!-- 分页部分 -->
		<tr>
			<td colspan="9"><div id="pages"  data-page="${data.pageNo-1}" data-total="${data.totalSize}" data-epage="10"></div></td>
		</tr>
	</c:if>
	<c:if test="${data == null || data.totalSize <= 0}">
		<tr>
			<td colspan="2"><div class="not-data">暂无数据</div></td>
		</tr>
	</c:if>
<script type="text/javascript">
	diyou.use("application/common",function(fn){
		fn.dyConfirm('check','platform/manager/toPlatformVerify');
	})
</script>