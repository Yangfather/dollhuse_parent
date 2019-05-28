<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${data != null && data.totalSize > 0}">
	<c:forEach var="item" items="${data.items}" varStatus="i">
		<tr class="${i.index%2==0 ? '' : 'evenbg'}">
		 <td><input type="checkbox" value="${item.id}"></td>
	     <td>${item.id}</td>
	     <td>${item.adminName}</td>
	     <td>${item.adminEmail}</td>
	     <td>${item.adminPhone}</td>
	     <td class="c-blue">${item.adminGrade}</td>
	  </tr>
	</c:forEach>
	<tr>
	<td colspan="5"><div id="pages" data-page="${data.pageNo - 1}" data-total="${data.totalSize}" data-epage="10"></div></td>
</tr>
</c:if>
   <c:if test="${data == null || data.totalSize <= 0}">
		<tr>
    	<td colspan="5"><div class="not-data">暂无数据</div></td>
    	</tr>
	</c:if>


