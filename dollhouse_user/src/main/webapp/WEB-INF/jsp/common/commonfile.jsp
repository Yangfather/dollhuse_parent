<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<base href="<%=basePath%>">
<link rel="stylesheet" href="dollhouse_user/assets/static/plugins/amaze/css/amazeui.min.css">
<link rel="stylesheet" href="dollhouse_user/assets/static/plugins/amaze/css/admin.css">
<link rel="stylesheet" href="dollhouse_user/assets/static/plugins/contextjs/css/context.standalone.css">
<script src="dollhouse_user/assets/static/plugins/jquery/jquery-2.1.4.min.js"></script>
<script src="dollhouse_user/assets/static/plugins/amaze/js/amazeui.min.js"></script>
<script src="dollhouse_user/assets/static/plugins/amaze/js/app.js"></script>
<script src="dollhouse_user/assets/static/plugins/layer/layer.js"></script>
<script src="dollhouse_user/assets/static/plugins/laypage/laypage.js"></script>
<script src="dollhouse_user/assets/static/plugins/contextjs/js/context.js"></script>
<script>
    var path = '${ctx}';
</script>