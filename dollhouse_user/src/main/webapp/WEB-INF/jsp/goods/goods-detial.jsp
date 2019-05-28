<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ page isELIgnored="false"%>
	<base href="<%=basePath%>">
	<link rel="shortcut icon" href="dollhouse_admin/assets/img/1521100274886FZGQMXYLstorageFZGQMXYLemulatedFZGQMXYL0FZGQMXYLDCIMFZGQMXYLCameraFZGQMXYLIMG_20170317_110514.jpg"" type="image/x-icon">
	<link rel="stylesheet" href="<%=path %>/assets/css/goodsDetial.css">
	<script type="text/javascript" src="<%=path %>/assets/js/jquery-1.9.1.min.js"></script>
	<title>商品详情</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <style type="text/css">
        .raceShow {
            background-color: red; /* #ffffff*/
            border: solid 1px #ccc;
            position: absolute;
            display: none;
            width: 800px;
            height: 600px;

            padding: 5px;       

            top: 40px;

            left: 64px;
        }
    </style>
</head>
<body>
	<div>
		<jsp:include page="../common/header.jsp"></jsp:include><hr style="border: 1px solid red;"/>
		<span class="c-blue font-20" id="userName" style="display: none;">${sessionUser.userName}</span>
		<div class="mainInfo">
			<div class="mainLeft">
				<div class="preview">
					<div id="vertical" class="bigImg">
				
						<img src="dollhouse_admin/${detial.imgUrl.get(1).URL}" alt="" id="midimg" />
				
						<div style="display:none;" id="winSelector"></div>
				
					</div>
					<!--bigImg end-->	
					<div class="smallImg">
				
						<div class="scrollbutton smallImgUp disabled"></div>
				
						<div id="imageMenu">
							<ul>
								<li id="onlickImg"><img src="dollhouse_admin/${detial.imgUrl.get(1).URL}" id="doll-img" alt="玩具屋"/></li>
								
								<c:forEach var="imgPic" items="${detial.imgUrl }">
									<li><img src="dollhouse_admin/${imgPic.URL}"  alt="玩具屋"/></li>
								</c:forEach>
							</ul>
						</div>
				
						<div class="scrollbutton smallImgDown"></div>
				
					</div>
					<!--smallImg end-->	

				<div id="bigView" style="display:none;"><img width="800" height="800" alt="" src="" /></div>
			
			</div>
			</div>
			<div class="mainRight">
				<c:if test="${detial==null }">
					detial没数据
				</c:if>
				<!-- 商品名称 -->
				<h1 style="font-size: 33px;" id="name">${detial.name}</h1>
				<!-- 商品编号 -->
				<label style="display: none;" id="organCode">${detial.organCode }</label>
				<!-- 商品颜色 -->
				<h1 style="font-size: 23px;margin-top: 10px;">请选择颜色：</h1>
				<c:forEach  var="goods" items="${detial.colors}">
					<label class="box" style="font-size: 20px;"><input name="color" type="radio" value="${goods.color }" />${goods.color } </label>
				</c:forEach><br />
				<!-- 商品型号 -->
				<h1 style="font-size: 23px;margin-top: 5px;">请选择型号：</h1>
				<c:forEach var="goods" items="${detial.types}">
					<label class="box" style="font-size: 20px;"><input name="type" type="radio" id="${goods.price }" onclick="return price(this.id)" value="${goods.type }" />${goods.type } </label>
				</c:forEach><br />	
				<!-- 商品价格 -->
				<h1 style="font-size: 23px;margin-top: 5px;">商品单价：</h1>
				<c:forEach var="goods" items="${detial.prices}">
					<label class="box" style="font-size: 20px;"><input name="price" type="radio" id="${goods.type }" onclick="return toType(this.id)"  value="${goods.price }" />${goods.price } </label>
				</c:forEach>
				<!-- 商品数量加减 -->	
				<div >
					<h1 style="font-size: 23px;margin-top: 5px;float: left;">购买数量：</h1>
					<input type="button" value="-" onclick="subtract()" style="font-size: 28px;margin-top: 2%;width: 30px;"/>
					<input type="text" id="num"  disabled="disabled" value="1" name="num" style="font-size: 28px;margin-top: 2%;width: 80px;"/>
					<input type="button" value="+" onclick="plus()" style="font-size: 28px;margin-top: 2%;width: 30px;"/>
				</div>
				<div>
					<h1 style="font-size: 23px;margin-top: 5px;float: left;margin-left: 0%;">合计金额：</h1>
					<input type="text" id="total"  disabled="disabled" value="0" name="total" style="font-size: 28px;margin-top: 2%;width: 150px;"/>
				</div>
				<div id="btnShowDiv" style="float: left;width: 40px;margin-bottom: 30px;margin-top: 5%;">
					<a href="<%=path %>/user/goods/chat">
						<img src="<%=path %>/assets/images/service.png" alt="客服" style="width: 40px;height: 40px;"/>&nbsp;&nbsp;客服
					</a>
				</div>
				<div id="addCar" onclick="addCar()">加入购物车</div>
				<div id="buyNow" onclick="buyNow()">立即购买</div>
			</div>
		</div>
		<jsp:include page="../common/footer.jsp"></jsp:include>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	// 图片上下滚动

	var count = $("#imageMenu li").length - 5; /* 显示 6 个 li标签内容 */

	var interval = $("#imageMenu li:first").width();

	var curIndex = 0;

	

	$('.scrollbutton').click(function(){

		if( $(this).hasClass('disabled') ) return false;

		

		if ($(this).hasClass('smallImgUp')) --curIndex;

		else ++curIndex;

		

		$('.scrollbutton').removeClass('disabled');

		if (curIndex == 0) $('.smallImgUp').addClass('disabled');

		if (curIndex == count-1) $('.smallImgDown').addClass('disabled');

		

		$("#imageMenu ul").stop(false, true).animate({"marginLeft" : -curIndex*interval + "px"}, 600);

	});	

	// 解决 ie6 select框 问题

	$.fn.decorateIframe = function(options) {

        if ('undefined' == typeof(document.body.style.maxHeight)) {

            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);

            $(this).each(function() {

                var $myThis = $(this);

                //创建一个IFRAME

                var divIframe = $("<iframe />");

                divIframe.attr("id", opts.iframeId);

                divIframe.css("position", "absolute");

                divIframe.css("display", "none");

                divIframe.css("display", "block");

                divIframe.css("z-index", opts.iframeZIndex);

                divIframe.css("border");

                divIframe.css("top", "0");

                divIframe.css("left", "0");

                if (opts.width == 0) {

                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");

                }

                if (opts.height == 0) {

                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");

                }

                divIframe.css("filter", "mask(color=#fff)");

                $myThis.append(divIframe);

            });

        }

    }

    $.fn.decorateIframe.defaults = {

        iframeId: "decorateIframe1",

        iframeZIndex: -1,

        width: 0,

        height: 0

    }

    //放大镜视窗

    $("#bigView").decorateIframe();

    //点击到中图

    var midChangeHandler = null;

	

    $("#imageMenu li img").bind("click", function(){

		if ($(this).attr("id") != "onlickImg") {

			midChange($(this).attr("src").replace("small", "mid"));

			$("#imageMenu li").removeAttr("id");

			$(this).parent().attr("id", "onlickImg");

		}

	}).bind("mouseover", function(){

		if ($(this).attr("id") != "onlickImg") {

			window.clearTimeout(midChangeHandler);

			midChange($(this).attr("src").replace("small", "mid"));

			$(this).css({ "border": "3px solid #959595" });

		}

	}).bind("mouseout", function(){

		if($(this).attr("id") != "onlickImg"){

			$(this).removeAttr("style");

			midChangeHandler = window.setTimeout(function(){

				midChange($("#onlickImg img").attr("src").replace("small", "mid"));

			}, 1000);

		}

	});

    function midChange(src) {

        $("#midimg").attr("src", src).load(function() {

            changeViewImg();

        });

    }

    //大视窗看图

    function mouseover(e) {

        if ($("#winSelector").css("display") == "none") {

            $("#winSelector,#bigView").show();

        }

        $("#winSelector").css(fixedPosition(e));

        e.stopPropagation();

    }

    function mouseOut(e) {

        if ($("#winSelector").css("display") != "none") {

            $("#winSelector,#bigView").hide();

        }

        e.stopPropagation();

    }

    $("#midimg").mouseover(mouseover); //中图事件

    $("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件



    var $divWidth = $("#winSelector").width(); //选择器宽度

    var $divHeight = $("#winSelector").height(); //选择器高度

    var $imgWidth = $("#midimg").width(); //中图宽度

    var $imgHeight = $("#midimg").height(); //中图高度

    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度



    function changeViewImg() {

        $("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));

    }

    changeViewImg();

    $("#bigView").scrollLeft(0).scrollTop(0);

    function fixedPosition(e) {

        if (e == null) {

            return;

        }

        var $imgLeft = $("#midimg").offset().left; //中图左边距

        var $imgTop = $("#midimg").offset().top; //中图上边距

        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X

        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y

        X = X < 0 ? 0 : X;

        Y = Y < 0 ? 0 : Y;

        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;

        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;



        if ($viewImgWidth == null) {

            $viewImgWidth = $("#bigView img").outerWidth();

            $viewImgHeight = $("#bigView img").height();

            if ($viewImgWidth < 200 || $viewImgHeight < 200) {

                $viewImgWidth = $viewImgHeight = 800;

            }

            $height = $divHeight * $viewImgHeight / $imgHeight;

            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);

            $("#bigView").height($height);

        }

        var scrollX = X * $viewImgWidth / $imgWidth;

        var scrollY = Y * $viewImgHeight / $imgHeight;

        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });

        $("#bigView").css({ "top": 75, "left": $(".preview").offset().left + $(".preview").width() + 15 });



        return { left: X, top: Y };

    }

});
/* 根据类型补全价格 */
function price(id){
	document.getElementById(id).checked="true";
	var type=$('input:radio[name="type"]:checked').val();
	document.getElementById(type).checked="true";
	var price=$('input:radio[name="price"]:checked').val();
	$("#total").attr("value",price);

};
/* 根据价格补全类型 */
function toType(id){
	document.getElementById(id).checked="true";
	var price=$('input:radio[name="price"]:checked').val();
	document.getElementById(price).checked="true";
	$("#total").attr("value",price);
}
/* 商品数量加减 */
function subtract(){
	var num=document.getElementById("num").value;
	var price=$('input:radio[name="price"]:checked').val();
	if(num>1){
		num--;
		$("#num").attr("value",num);
		var total=num*price;
		$("#total").attr("value",total.toFixed(2));
	}
}
function plus(){
	var num=document.getElementById("num").value;
	var price=$('input:radio[name="price"]:checked').val();
	num++;
	$("#num").attr("value",num);
	var total=num*price;
	$("#total").attr("value",total.toFixed(2));
}
/* 加入购物车 */
function addCar(){
	var color=$('input:radio[name="color"]:checked').val();
	var type=$('input:radio[name="type"]:checked').val();
	var price=$('input:radio[name="price"]:checked').val();
	var userName=document.getElementById("userName").innerText;
	//alert(userName.replace(/(^\s*)|(\s*$)/g, "")=="");
	if(userName.replace(/(^\s*)|(\s*$)/g, "")==""){
		alert("加入购物车前请先登录！")
	}else{
		if(color==null){
			alert("请选择颜色");
			if(type==null){
				alert("请选择型号");
				if(price==null){
					alert("请选择价格");
				}
			}
		}
	}
	if(userName.replace(/(^\s*)|(\s*$)/g, "")!="" && color!=null && type!=null && price!=null){
		var name=document.getElementById("name").innerText;
		var organCode=document.getElementById("organCode").innerText;
		var color=$('input:radio[name="color"]:checked').val();
		var type=$('input:radio[name="type"]:checked').val();
		var price=$('input:radio[name="price"]:checked').val();
		var total=document.getElementById("total").value;
		var num=document.getElementById("num").value;
		var url=document.getElementById("doll-img").src;
		url=url.replace("http://","");
		url=url.replace("localhost:8080/","");
		url=url.replace("dollhouse_admin","");
		url=url.replace("dollhouse_user","")
		$.ajax({
			url:"<%=path%>/user/goods/addShopCar",
			type:"post",
			data:{"name":name,"organcode":organCode,"color":color,"type":type,"price":price,"num":num,"total":total,"img":url},
			dataType:"json"
		}).done(function(data){
			$("#loading").hide();
			 if(data.status == 200) {
                  alert("加入购物车成功！")
               } else {
                   alert(data);
               }
		});
		
	}
}
/* 立即购买 */
function buyNow(){
	var color=$('input:radio[name="color"]:checked').val();
	var type=$('input:radio[name="type"]:checked').val();
	var price=$('input:radio[name="price"]:checked').val();
	var userName=document.getElementById("userName").innerText;
	if(userName.replace(/(^\s*)|(\s*$)/g, "")==""){
		alert("购买前请先登录！")
	}else{
		if(color==null){
			alert("请选择颜色");
			if(type==null){
				alert("请选择型号");
				if(price==null){
					alert("请选择价格");
				}
			}
		}
	}
	if(userName.replace(/(^\s*)|(\s*$)/g, "")!="" && color!=null && type!=null && price!=null){
		var name=document.getElementById("name").innerText;
		var organCode=document.getElementById("organCode").innerText;
		var color=$('input:radio[name="color"]:checked').val();
		var type=$('input:radio[name="type"]:checked').val();
		var price=$('input:radio[name="price"]:checked').val();
		var total=document.getElementById("total").value;
		var num=document.getElementById("num").value;
		var dollImg=document.getElementById("doll-img").src;
		var img=dollImg.replace("http://localhost:8080/","");
		window.location.href="<%=path%>/user/goods/toPayPage"+"?total="+total+"&name="+name+"&organcode="+name+"&color="+color+"&type="+type+"&price="+price+"&num="+num+"&img="+img;
	}
}
</script>
</body>
</html>