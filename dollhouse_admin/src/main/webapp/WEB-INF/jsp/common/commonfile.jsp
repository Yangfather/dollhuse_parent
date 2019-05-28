<%@ page language="java" pageEncoding="UTF-8"%>
<%	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<head>
<title>${site_name}</title>

<meta charset="utf-8">
<meta name="keywords" content="${site_keywords}">
<meta name="description" content="${site_discription}">

<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/extend.css">

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