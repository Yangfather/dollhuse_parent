<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<!DOCTYPE html>
<html>
<jsp:include page="../common/base.jsp"/>

<body>
   <div class="item-form">
     <form id="userForm" action="<%=path %>/system/admin/savePassword" method="post">
     	<input type="hidden" class="form-ipt" name="id" value="${data.id}"/>
       <div class="item-bl">
        <label class="form-label">用户名</label>
        <span class="unit">${data.adminName}</span>
       </div>
       <div class="item-bl">
        <label class="form-label">登录密码</label>
        <input type="password" class="form-ipt" id="password" name="adminPassword" cls="{required:true}"/>
       </div>
       <div class="item-bl">
        <label class="form-label">确认密码</label>
        <input type="password" class="form-ipt" name="confirmPassword" cls="{required:true,equalTo:'#password'}"/>
       </div>
        <div class="item-bln">
        <input type="submit" value="提交" class="tijiao"/>
        <a href="#" onclick="parent.layer.close(parent.layer.getFrameIndex(window.name));return false;" class="quxiao">取消</a>
       </div>
     </form>
   </div>

</body>

<script type="text/javascript">
   diyou.use("application/layForm",function(fn){fn.validForm("#userForm");});
</script>
</html>
