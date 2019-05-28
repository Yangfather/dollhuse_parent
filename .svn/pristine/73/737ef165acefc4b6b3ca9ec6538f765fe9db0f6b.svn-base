<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dollhouse 首页</title>
<link rel="shortcut icon" href="dollhouse_admin/assets/img/1521100274886FZGQMXYLstorageFZGQMXYLemulatedFZGQMXYL0FZGQMXYLDCIMFZGQMXYLCameraFZGQMXYLIMG_20170317_110514.jpg"" type="image/x-icon">
<link rel="stylesheet" href="<%=path %>/assets/css/main.css">
<script type="text/javascript" src="<%=path %>/assets/js/jquery-1.9.1.min.js"></script>
</head>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>
	<span class="c-blue font-20" id="userName" style="display: none;">${sessionUser.id}</span>
	<div class="side-nav">
		<ul class="sidenav-list">
			<c:forEach var="leftMenu" items="${allMenu.leftMenu }">
				<li class="side-item"><a href="#">${leftMenu.name }<i class="iconfont">&#xe6a7;</i></a>
					<div class="children-nav">
						<ul class="children-list">
							<c:forEach var="secMenu" items="${leftMenu.subMenu }">
								<li><a href="<%=path %>/user/common/goodsInfo?name=${secMenu.linkId }" class="link"><img alt="玩具屋" src="dollhouse_admin/${secMenu.menuImgUrl}" class="icon-80">
								<span class="title">${secMenu.name }</span></a><a href="<%=path %>/user/common/goodsInfo?name=${secMenu.linkId}" class="buybtn">选购</a></li>
							</c:forEach>
						</ul>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 幻灯片区 -->
	<div class="carousel container">
		<div class="carousel-inner">
			<%-- <img  src="${lunboMenuList.get(0).menuImgUrl }" class="item first"> --%>
			<c:forEach var="lunboMenu" items="${allMenu.lunBo }">
				<c:choose>
					<c:when test="${lunboMenu.name=='轮播1' }">
						<a href="<%=path %>/user/common/goodsInfo?name=${lunboMenu.name }"><img  src="dollhouse_admin/${lunboMenu.menuImgUrl}" class="item first"></a>
					</c:when>
					<c:otherwise>
						<a href="<%=path %>/user/common/goodsInfo?name=${lunboMenu.name }"><img  src="dollhouse_admin/${lunboMenu.menuImgUrl}" class="item"></a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div class="carousel-btn">
			<a href="javascript:" class="btn active"></a> <a href="javascript:" class="btn"></a> <a href="javascript:" class="btn"></a> 
			<a href="javascript:" class="btn"></a> <a href="javascript:" class="btn"></a> <a href="javascript:" class="btn"></a>
		</div>
		<a href="javascript:" class="prev"><i class="iconfont">&#xe697;</i></a>
		<a href="javascript:" class="next"><i class="iconfont">&#xe6a7;</i></a>
	</div>

	<!-- 大米明星单品 -->
	<div class="single-goods">
		<div class="container">
			<div class="s-lg-title">
				<span class="page-title">玩具屋明星单品</span>
				<div class="title-more">
					<a href="javascript:" class="s-prev btn-default"><i class="iconfont">&#xe697;</i></a> 
					<a href="javascript:" class="s-next"><i class="iconfont">&#xe6a7;</i></a>
				</div>
			</div>
			<div class="sgoods-content">
				<ul class="sgoods-list clearfix" style="margin-left: 0;">
					<c:forEach var="star" items="${allMenu.starMenu }">
						<li class="sgoods-item">
							<div class="sgoods-thumb">
								<a href=""><img src="<%=path %>/${star.menuImgUrl}"></a>
							</div>
							<div class="goods-title">
								<a href="" class="title">${star.name }</a>
							</div>
							<p class="goods-info">${star.linkId }</p>
							<p class="goods-price">${star.menuPrice }</p>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
			<!-- 搭配 -->
		<div class="asst clearfix">
			<div class="title">
				<span class="page-title">搭配</span>
				<div class="more">
					<a href="javascript:" class="more-text active">热门 </a> 
					<a href="javascript:" class="more-text">配件</a> 
					<a href="javascript:" class="more-text">新品</a> 
					<a href="javascript:" class="more-text">畅销 </a>
				</div>
			</div>
			<div class="col-md-4">
				<ul class="lg-thumb-list clearfix">
					<li class="goods-item left-s-thumb">
						<a href="" class="s-thumb"><img src="dollhouse_admin/assets/img/a1.jpg" alt=""></a>
					</li>
				</ul>
			</div>
			<div class="col-md-8">
				<ul class="goods-list list-active hot-list clearfix">
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a2.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门1</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门2</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门3</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门4</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门5</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门6</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">热门8</a>
						</div>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
					<a href="#" class="goods-more">浏览更多 <span>热门</span></a>
						<div class="goods-thumb">
							<a href=""><i class="iconfont">&#xe700;</i></a>
						</div></li>
				</ul>
				<ul class="goods-list list tv-list clearfix">
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视1</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视2</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视3</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视4</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视5</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视6</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电视8</a>
						</div>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
					<a href="#" class="goods-more">浏览更多 <span>热门</span></a>
						<div class="goods-thumb">
							<a href=""><i class="iconfont">&#xe700;</i></a>
						</div>
					</li>
				</ul>
				<ul class="goods-list list pc-list clearfix">
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑1</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑2</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑3</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑4</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑5</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑6</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">电脑8</a>
						</div>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
					<a href="#" class="goods-more">浏览更多 <span>热门</span></a>
						<div class="goods-thumb">
							<a href=""><i class="iconfont">&#xe700;</i></a>
						</div></li>
				</ul>
				<ul class="goods-list list home-list clearfix">
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居1</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居2</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居3</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居4</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居5</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居6</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">家居8</a>
						</div>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-s">
					<a href="#" class="goods-more">浏览更多 <span>热门</span></a>
						<div class="goods-thumb">
							<a href=""><i class="iconfont">&#xe700;</i></a>
						</div></li>
				</ul>
			</div>
		</div>
		<!-- 推荐 -->
		<div class="reco-goods">
			<div class="s-lg-title">
				<span class="page-title">为你推荐</span>
				<div class="title-more">
					<a href="javascript:" class="reco-prev btn-default"><i class="iconfont">&#xe697;</i></a> 
					<a href="javascript:" class="reco-next"><i class="iconfont">&#xe6a7;</i></a>
				</div>
			</div>
			<div class="col-md-12">
				<ul class="reco-list clearfix">
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐1</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐2</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#" class="title"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐3</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐4</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐5</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐6</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
					<li class="goods-item goods-item-m">
						<div class="goods-thumb">
							<a href="#"><img src="dollhouse_admin/assets/img/a1.jpg"></a>
						</div>
						<div class="goods-title">
							<a href="#" class="title">推荐7</a>
						</div>
						<p class="goods-info">超长续航，多彩金属</p>
						<p class="goods-price">1440元</p>
					</li>
				</ul>
			</div>
		</div>
		<!-- 热评产品 -->
		<div class="hot-review">
			<div class="title">
				<span class="page-title">热评产品</span>
			</div>
			<div class="col-md-12 ">
				<ul class="hotrew-list clearfix">
					<li class="goods-item goods-item-h">
						<div class="hotrew-thumb">
							<a href=""><img src="dollhouse_admin/assets/img/a1.jpg" alt=""></a>
						</div>
						<div class="review">
							<a href="" class="review-text">这箱子很好，外观漂亮，实用性强。很轻，有点软但不影响它的坚固。这箱子很好，外观漂亮，实用性强。很轻，有点软 但不影响它的坚固。</a>
						</div>
						<p class="form-user">
							来自于 <span class="user-name">子书雁</span> 的评价
						</p>
						<div class="title">
							<a href="" class="goods-title">产品名称4</a> <span class="ver">|</span>
							<span class="goods-price">111元 </span>
						</div>
					</li>
					<li class="goods-item goods-item-h">
						<div class="hotrew-thumb">
							<a href=""><img src="dollhouse_admin/assets/img/a1.jpg" alt=""></a>
						</div>
						<div class="review">
							<a href="" class="review-text">这箱子很好，外观漂亮，实用性强。很轻，有点软但不影响它的坚固。这箱子很好，外观漂亮，实用性强。很轻，有点软 但不影响它的坚固。</a>
						</div>
						<p class="form-user">
							来自于 <span class="user-name">子书雁</span> 的评价
						</p>
						<div class="title">
							<a href="" class="goods-title">产品名称4</a> <span class="ver">|</span>
							<span class="goods-price">111元 </span>
						</div>
					</li>
					<li class="goods-item goods-item-h">
						<div class="hotrew-thumb">
							<a href=""><img src="dollhouse_admin/assets/img/a1.jpg" alt=""></a>
						</div>
						<div class="review">
							<a href="" class="review-text">这箱子很好，外观漂亮，实用性强。很轻，有点软但不影响它的坚固。这箱子很好，外观漂亮，实用性强。很轻，有点软 但不影响它的坚固。</a>
						</div>
						<p class="form-user">来自于 <span class="user-name">子书雁</span> 的评价</p>
						<div class="title">
							<a href="" class="goods-title">产品名称4</a> <span class="ver">|</span>
							<span class="goods-price">111元 </span>
						</div>
					</li>
					<li class="goods-item goods-item-h">
						<div class="hotrew-thumb">
							<a href=""><img src="dollhouse_admin/assets/img/a1.jpg" alt=""></a>
						</div>
						<div class="review">
							<a href="" class="review-text">这箱子很好，外观漂亮，实用性强。很轻，有点软但不影响它的坚固。这箱子很好，外观漂亮，实用性强。很轻，有点软 但不影响它的坚固。</a>
						</div>
						<p class="form-user">来自于 <span class="user-name">子书雁</span> 的评价</p>
						<div class="title">
							<a href="" class="goods-title">产品名称4</a> <span class="ver">|</span>
							<span class="goods-price">111元 </span>
						</div>
					</li>
				</ul>
			</div>
		</div>
	<!-- 页脚 -->
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>
<script src="<%=path %>/assets/js/main.js"></script>
<script src="<%=path %>/assets/js/holder.min.js"></script>
 <script>
 function shopCar(){
	 var userName=document.getElementById("userName").innerText;
	 if(userName.replace(/(^\s*)|(\s*$)/g, "")==""){
			alert("查看购物车前请先登录！")
		}else{
          window.location.href = "<%=path%>/doll/system/shopCar"+"?userName="+userName;
		}
 }
</script> 
</html>