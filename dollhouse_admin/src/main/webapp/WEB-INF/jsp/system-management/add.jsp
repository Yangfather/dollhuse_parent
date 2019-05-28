<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html>
<html>
	<jsp:include page="../common/base.jsp"></jsp:include>
	<style type="text/css">
		.item-bln .form-tck {width: 162px;}
	</style>
	<body>
		<div class="item-form">
			<form id="adminForm" action="<%=path %>/system/admin/submit" method="post">
			<c:if test="${data.path == 'add' }">
			
			</c:if>
				<input type="text" hidden="true" name="id" value="${data.admin.id}" />
		       	<div class="item-bln">
			        <label class="form-label">名称：</label>
			        <input type="text" class="form-tck" cls="{required:true}" name="adminName" value="${data.admin.adminName}" />
		       	</div>
		       	<div class="item-bln">
			        <label class="form-label">类型：</label>
			        <select class="form-tck sel-wn" cls="{required:true}" name="adminGrade">
			        <c:forEach var="item" items="${data.type}">
			        	<option value="${item.value}" ${item.value == data.admin.adminGrade ? 'selected' : '' }>${item.text}</option>
			        </c:forEach>
			        </select>
		       	</div>
		       	<div class="item-bln">
			        <label class="form-label">状态：</label>
			        <select class="form-tck sel-wn" cls="{required:true}" name="status">
			        <c:forEach var="item" items="${data.status}">
			        	<option value="${item.value}" ${item.value == data.admin.status ? 'selected' : '' }>${item.text}</option>
			        </c:forEach>
			        </select>
		       	</div>
		       	<c:if test="${data.path == 'add' }">
		       	<div class="item-bln">
			        <label class="form-label">密码：</label>
			        <input type="password" class="form-tck" name="adminPassword"/>
			        <!-- <input type="password" class="form-tck" cls="{required:true}" name="adminPassword"/> -->
		       	</div>
		       	</c:if>
		       	<div class="item-bln">
			        <label class="form-label">手机号：</label>
			       	<input type="text" class="form-tck" cls="{required:true,phone:true}" name="adminPhone" value="${data.admin.adminPhone}" />
		       	</div>
		       	<div class="item-bln">
			        <label class="form-label">邮箱：</label>
			       	<input type="text" class="form-tck" cls="{required:true,qq:true}" name="adminEmail" value="${data.admin.adminEmail}" />
		       	</div>
		       	<div class="item-bln">
			        <input type="submit" class="tijiao" value="提交">
       				<a href="#" onclick="parent.layer.close(parent.layer.getFrameIndex(window.name));return false;" class="quxiao">取消</a>
		       	</div>
	       	</form>
	    </div>
	    
		<script type="text/javascript">
	        diyou.use("application/layForm",function(fn){fn.validForm("#adminForm");});
	    </script>
	</body>
</html>