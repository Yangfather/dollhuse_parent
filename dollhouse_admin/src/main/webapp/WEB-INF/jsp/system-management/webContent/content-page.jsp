﻿<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <c:if test="${data != null && data.items != null}">
         <c:forEach var="item" items="${data.items}" varStatus="i">
             <tr <c:if test="${i.index%2==1}">class="evenbg"</c:if>>
		    	<td><input type="checkbox" value="${item.id}"></td>
		        <td>${item.id}</td>
		        <td>${item.name}</td>
		        <td>${item.nid}</td>
		        <td>
		        <c:if test="${item.nid == 'service_weixin_imgcode' || item.nid == 'site_logo'}">
		        	<img src="${imgPath}/${item.value}" style="height:50px;"/>
		        </c:if>
		        <c:if test="${item.nid != 'service_weixin_imgcode' && item.nid != 'site_logo'}">
		        ${item.value}
		        </c:if>
		        </td>
		        <td>${item.remark}</td>
		    </tr>
     	</c:forEach>
     	<tr>
    	<td colspan="6"><div id="pages"  data-page="${data.pageNo-1}" data-total="${data.totalSize}" data-epage="10"></div></td>
   		 </tr>
    </c:if>
    
    <c:if test="${data == null || data.items == null}">
		<tr>
    	<td colspan="6"><div class="not-data">暂无数据</div></td>
    	</tr>
	</c:if>

