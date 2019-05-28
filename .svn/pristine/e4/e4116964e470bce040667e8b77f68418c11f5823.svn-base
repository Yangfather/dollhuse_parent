	<%@ page language="java" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- 自动补全 -->
	<%String path = request.getContextPath(); %>
	<link rel="stylesheet" href="dollhouse_admin/assets/css/jquery-ui.css">
	<link rel="stylesheet" href="dollhouse_user/assets/css/main.css">
	<script type="text/javascript" src="dollhouse_user/assets/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="dollhouse_admin/assets/js/jquery-ui.min.js">
    <script type="text/javascript" src="dollhouse_admin/assets/js/fileUpload/jquery-2.1.3.min.js"></script>
    <c:if test="${lunboMenuList==null }">
   	 <script>
	  $(function() {
		  $.ajax({ 
			  url: "<%=path %>/common/showMenu"
			  }).done(function(data){
	    			 if(data.status == 200) {
		                } else {
		                	location.href = "<%=path %>/common/showMenu";
		                }
	    		});
		  var availableTags;
		  $.ajax({
			  type: "GET",
	          url: "<%=path %>/common/autocomplete",
	          success: function(data){    
	        	  availableTags=JSON.parse(data);
                    $( "#tags" ).autocomplete({
                        source: availableTags
                      });
                  }
		  })
	  });
  </script>
    </c:if>
   
 	<!-- 顶部导航条 -->
	<div class="topbar">
		<div class="container">
			<div class="topbar-nav">
				<a href="#">玩具屋商城</a> <span class="ver">|</span> <a href="#">DAMI</a>
				<span class="ver">|</span> <a href="#">米聊</a> <span class="ver">|</span>
				<a href="#">游戏</a> <span class="ver">|</span> <a href="#">多看阅读</a> 
				<span class="ver">|</span> <a href="#">服务</a> <span class="ver">|</span>
				<a href="#">产品</a> <span class="ver">|</span>
				<span class="ver">|</span> <a href="#">问题反馈</a> <span class="ver">|</span>
				<a href="#">Select Refion</a>
			</div>
			<div class="topbar-cart">
				<a href="javascript:;" onclick="shopCar()" ><i></i>购物车</a>
			</div>
			<div class="topbar-info">
				<c:if test="${sessionUser==null }">
					<a href="<%=path %>/common/login" class="link">登录</a> <span class="ver">|</span>
					<a href="<%=path %>/common/register" class="link">注册</a> <span class="ver">|</span> <a href="#"class="link">消息通知</a>
				</c:if>
				<c:if test="${sessionUser != null }">
					欢迎您:<span class="c-blue font-20" ><a href="dollhouse_user/member/member/memberInfo?id=${sessionUser.id}" >${sessionUser.userName}</a></span>
					<a href="<%=path %>/common/logout" style="margin-left: 15px">[退出]</a>
				</c:if>
			</div>
		</div>
	</div>
	<!-- header区 -->
	<div class="header">
		<div>
			<div class="header-logo">
				<a href="index.html" class="logo"></a>
				<div class="advertising">
					<a href="#"></a>
				</div>
			</div>
			<div class="header-nav">
				<ul class="navlist clearfix">
					<c:if test="${allMenu.topMenu==null}">
						<script>
						$(function() {
							  $.ajax({ url: "<%=path %>/common/showMenu"});
						});
						</script>
					
					</c:if>
					<c:if test="${allMenu.topMenu!=null}">
						<c:forEach items="${allMenu.topMenu}" var="menu">
							<li class="item">
								<a href="<%=path%>/user/goods/goods-list?menuType=${menu.linkId}&menuName=${menu.name}">${menu.name}</a>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<!-- 搜索框部分 -->
			<div class="header-search">
				<form action="<%=path %>/user/goods/toGoodsDetial" method="post" class="search-form" id="searchForm">
					<input name="searchGoods"  class="search-text"  id="tags" value="${doll.dollType }"> <input type="submit" class="search-btn iconfont" value="&#xe652;">
				</form>
			</div> 
		</div>
	</div>
	
    <!-- header end -->