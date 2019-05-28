<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<html>
<head>
<base href="<%=basePath%>">
<%@ page isELIgnored="false"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>购物车</title>
<link rel="shortcut icon" href="http://s01.mifile.cn/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="<%=path %>/assets/css/main.css">
<!-- 购物车内容 -->
<link rel="stylesheet" href="<%=path %>/assets/css/shopCar/reset.css">
<link rel="stylesheet" href="<%=path %>/assets/css/shopCar/carts.css">
<script type="text/javascript" src="<%=path %>/assets/js/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/assets/js/shopCar/carts.js"></script>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<hr style="height:3px;border:none;border-top:3px solid #CDC9A5;" >
	<div class="container">
	<section class="cartMain">
        <div class="cartMain_hd">
            <ul class="order_lists cartTop">
                <li class="list_chk">
                    <!--所有商品全选-->
                    <input type="checkbox" id="all" class="whole_check">
                    <label for="all" style="border: 1px solid #b8b8b8;"></label> 全选
                </li>
                <li class="list_con">商品信息</li>
                <li class="list_info">商品参数</li>
                <li class="list_price">单价</li>
                <li class="list_amount">数量</li>
                <li class="list_sum">金额</li>
                <li class="list_op">操作</li>
            </ul>
        </div>
		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
        <div class="cartBox">
            <div class="shop_info">
            	<div class="all_check">
                    <!--店铺全选-->
                    <input type="checkbox" id="shop_a" class="shopChoice">
                    <label for="shop_a" class="shop"></label>
                </div>
                <div class="shop_name">
                    <a href="javascript:;">玩具屋</a>
                </div>
            </div>
            <div class="order_content">
            	<c:if test="${empty data}">
            		<span style="font-size: 18px;color: #FFAEB9;">您还没在购物车添加宝贝哟，快去查找想要的宝贝吧！</span>
            	</c:if>
            	<c:if test="${data!=null ||data!=null }">
            		<c:forEach var="item" items="${data }">
            			<ul class="order_lists">
		                    <li class="list_chk">
		                        <input type="checkbox" id="${item.id }" class="son_check" value="${item.id }">
		                        <label for="${item.id }" style="border: 1px solid #b8b8b8;"></label>
		                    </li>
		                    <li class="list_con">
		                    	<p class="imgurl" style="display: none;">${item.dollImg}</p>
		                        <div class="list_img">
		                        	<img src="dollhouse_admin/${item.dollImg}" alt="${item.dollName }" >
		                        </div>
		                        <div class="list_text"><a href="javascript:;" class="name">${item.dollName }</a></div>
		                    </li>
		                    <li class="list_info">
		                        <p class="type">${item.dollType }</p>
		                        <p class="color">${item.dollColor }</p>
		                    </li>
		                    <li class="list_price">
		                        <p class="price">${item.dollPrice }</p>
		                    </li>
		                    <li class="list_amount">
		                        <div class="amount_box">
		                            <a href="javascript:;" class="reduce reSty">-</a>
		                            <input type="text" value="${item.dollNum }" class="sum">
		                            <a href="javascript:;" class="plus">+</a>
		                        </div>
		                    </li>
		                    <li class="list_sum">
		                        <p class="sum_price">${item.dollTotal }</p>
		                    </li>
		                    <li class="list_op">
		                        <p class="del"><a href="javascript:;" class="delBtn">移除商品</a></p>
		                    </li>
		                </ul>
            		</c:forEach>
            	</c:if>
            </div>
        </div>
        <!--底部-->
        <div class="bar-wrapper">
            <div class="bar-right">
                <div class="piece">已选商品<strong class="piece_num"  id="totalNum">0</strong>件</div>
                <div class="totalMoney">共计: <strong class="total_text" id="totalMoney">0.00</strong></div>
                <div class="calBtn"><a href="javascript:;" onclick="settleAccounts()">结算</a></div>
            </div>
        </div>
    </section>
    <section class="model_bg"></section>
    <section class="my_model">
        <p class="title">删除宝贝<span class="closeModel">X</span></p>
        <p>您确认要删除该宝贝吗？</p>
        <div class="opBtn"><a href="javascript:;" class="dialog-sure">确定</a><a href="javascript:;" class="dialog-close">关闭</a></div>
    </section>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>