<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
%>
<div class="header" style="width: 100%;height: 130px;">
	<div class="header-top" style="width: 100%;">
		<div class="wrapper fn-clear" style="height: 70px;margin-top: 0px;">
			<div style="width: 20%;float: left;">玩具屋后台管理系统</div>
			<div class="fr inform" style="width: 20%;margin-left:56%;float: left;">
				<img src="<%=path %>/assets/img/a9.jpg" style="width: 40px;height: 40px;border-radius:50%;"/>
				欢迎您：<span>${hd.sessionUser }</span>
				<a href="<%=path %>/admin/sessionOut">[退出]</a>
			</div>
		</div>
		<div class="header-main wrapper" style="width: 100%;height: 59px;">
			<div class="wrapper" style="width: 100%;">
				<div class="header-nav" style="width: 100%;">
					<ul class="fn-clear" style="width: 100%;">
						<c:forEach var="topMenu" items="${hd.menuList }">
							<li style="float: left;margin-left: 7%;margin-top: auto;margin-bottom: auto;">
								<a <c:choose><c:when test="${empty topMenu.url || (topMenu.subMenu != null && topMenu.subMenu.size() > 0)}">href="javascript:;"</c:when><c:otherwise>href="${topMenu.url}"</c:otherwise></c:choose> >${topMenu.name}</a>
								<c:if test="${topMenu.subMenu !=null && topMenu.subMenu.size()>0 }">
									<div class="second-nav">
										<dl>
											<c:forEach var="subMenu" items="${topMenu.subMenu }">
												<dd>
													<a href="<%=path %>/${subMenu.url }">${subMenu.name }</a>
												</dd>
											</c:forEach>
										</dl>
										<i class="arrow"></i>
									</div>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>	
	</div>
</div>