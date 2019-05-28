<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String path=request.getContextPath();
   String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false"%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path %>/assets/css/shopCar/goods-pay.css">
<script type="text/javascript" src="<%=path%>/assets/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="<%=path%>/assets/js/shopCar/pcasunzip.js"></script>
<title>提交订单</title>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include><hr style="border: 1px solid red;"/>
<!-- 地址添加 -->
<div class="main-container">
<form action="<%=path %>/user/buy/pay" onsubmit="return check()" method="post">
	<div class="address-container">
		<fieldset style="padding:5px;">
		<legend>创建收货地址</legend>
			<label>请选择地址：</label>
			<select name="receiveAddressProvince" id="province"></select>
		    <select name="receiveAddressCity" id="city"></select>
			<select name="receiveAddressArea" id="area"></select><br>
			<label>请填写详细收货地址：</label><input type="text" name="receiveAddressDetial" id="receiveAddressDetial" placeholder="请填写详细收货地址" value=""/><br>
			<label>请填写收货人姓名：</label><input type="text" name="receiveName" id="receive-name" placeholder="请填写收货人姓名" value=""/><br>
			<label>请填写收货人电话：</label><input type="text" name="receivePhone" id="receive-phone" placeholder="请填写收货人电话" value=""/><br>
		</fieldset>
	</div><hr style="border: 1px solid red;"/>
	<!-- 购买商品 -->
	<div class="goodsInfo-container">
		<table>
			<thead>
				<th class="goods-name">宝贝</th>
				<th class="goods-type">商品属性</th>
				<th class="goods-price">单价</th>
				<th class="goods-num">数量</th>
				<th class="goods-total">小计</th>
			</thead>
			<!-- 商品信息 -->
			<tr>
				<td><img src="${shopCar.dollImg }" name="dollImg" style="width: 50%;height: 130px;"><label name="dollName">${shopCar.dollName }</label></td>
				<td><label name="dollType">${shopCar.dollType }</label> &nbsp;&nbsp; <label name="dollColor">${shopCar.dollColor }</label></td>
				<td><label name="dollPrice">${shopCar.dollPrice }</label></td>
				<td><label name="dollNum">${shopCar.dollNum }</label></td>
				<td><label name="dollTotal" id="total">${shopCar.dollTotal }</label></td>
			</tr>
			<!-- 买家留言 -->
			<tr>
				<td colspan="5">
				<label>给商家留言：</label>	<textarea id="leaveMessage" rows="1" cols="50" placeholder="请确认留言已和商家确认"></textarea>
				</td>
			</tr>
			<!-- 商品信息结束 -->
		</table>
	</div>
	<input type="text" value="${shopCar.dollImg }" style="display: none;" name="dollImg">
	<input type="text" value="${shopCar.dollName }" style="display: none;" name="dollName">
	<input type="text" value="${shopCar.dollType }" style="display: none;" name="dollType">
	<input type="text" value="${shopCar.dollColor}" style="display: none;" name="dollColor">
	<input type="text" value="${shopCar.dollPrice}" style="display: none;" name="dollPrice">
	<input type="text" value="${shopCar.dollNum }" style="display: none;" name="dollNum">
	<input type="text" value="${shopCar.dollTotal}" style="display: none;" name="dollTotal">
	<div>
		<label>实付款：</label><span id="pay-total"></span><br>
		<label>寄送至：</label><span id="receive-address"></span><br>
		<label>收货人：</label><span id="receiveName"></span><br>
		<label>电话：</label><span id="receivePhone"></span><br>
		<input type="submit" value="提交订单">
	</div>
</form>
</div>
<!-- 表单提交前验证 -->
<script type="text/javascript" defer>
	new PCAS("receiveAddressProvince","receiveAddressCity","receiveAddressArea","北京市","市辖区","朝阳区");
	function check(){
		var province=document.getElementById("province").value;
		var city=document.getElementById("city").value;
		var area=document.getElementById("area").value;
		var addressDetial=document.getElementById("receiveAddressDetial").value.replace(/\s+/g, "");
		var receiveName=document.getElementById("receive-name").value.replace(/\s+/g, "");
		var phone=document.getElementById("receive-phone").value.replace(/\s+/g, "");
		var total=document.getElementById("total").innerHTML;
		document.getElementById("pay-total").innerHTML =total;
		var address=province+city+area+addressDetial;
		document.getElementById("receive-address").innerHTML =address;
		document.getElementById("receiveName").innerHTML =receiveName;
		document.getElementById("receivePhone").innerHTML =phone;
		if(province!=null &&city!=null &&area!=null && addressDetial!=null &&addressDetial.length>0 && receiveName !=null &&receiveName.length>0 &&phone!=null &&phone.length>0){
			con=confirm("请核对收货地址，正确请确定，错误请取消修改"); //在页面上弹出对话框
			if(con==true){
				return true;
			}else{
				return false;
			}
			
		}else{
			alert("请完善收货信息！")
			return false;
		}
		
	}

</script>
</body>
</html>