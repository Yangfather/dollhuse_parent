<%@ page language="java" pageEncoding="UTF-8"%>
<%	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<head>
    <title>${site_name}</title>
    
    <meta charset="utf-8">
    <meta name="keywords" content="${site_keywords}">
    <meta name="description" content="${site_discription}">
    
    <link rel="stylesheet" type="text/css" href="dollhouse_admin/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="dollhouse_admin/assets/css/index.css">
    <link rel="stylesheet" type="text/css" href="dollhouse_admin/assets/css/extend.css">
    <link rel="stylesheet" type="text/css" href="dollhouse_admin/assets/css/datepicker/css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="dollhouse_admin/assets/js/widgets/layer/skin/layer.css">
    <script type="text/javascript" src="dollhouse_admin/assets/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="dollhouse_admin/assets/js/diyou.js"></script>
    <script type="text/javascript" src="dollhouse_admin/assets/js/base.js"></script>
    <script type="text/javascript" src="dollhouse_admin/assets/js/echarts.js"></script>
    
    <script type="text/javascript" src="dollhouse_admin/assets/js/fileUpload/fileUpload.js"></script>
</head>