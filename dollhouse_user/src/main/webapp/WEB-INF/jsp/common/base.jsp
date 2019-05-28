<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<head>
	<base href="<%=basePath%>">
    <title>${pageTitle}</title>
    
    <meta charset="utf-8">
    <meta name="keywords" content="${siteKeyWord}">
    <meta name="description" content="${siteDescription}">
    <script type="text/javascript" src="<%=path %>/assets/js/jquery-1.9.1.min.js"></script>
</head>
