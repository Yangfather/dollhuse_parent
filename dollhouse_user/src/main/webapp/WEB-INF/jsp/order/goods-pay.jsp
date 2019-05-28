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
<form action="<%=path %>/common/buy/pay" onsubmit="return check()" method="post">
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
		<div style="">
			<strong>您一共选中了</strong><strong></strong>${data.totalNum }<strong>件商品，合计金额</strong><strong></strong>${data.totalMoney }<strong>元</strong>
		</div>
		<!-- 买家留言 -->
		<div>
			<label>给商家留言：</label>	<textarea id="leaveMessage" rows="1" cols="50" placeholder="请确认留言已和商家确认"></textarea>
		</div>	
	</div>
	<div>
		<label>实付款：</label><span id="pay-total"></span><br>
		<label>寄送至：</label><span id="receive-address"></span><br>
		<label>收货人：</label><span id="receiveName"></span><br>
		<label>电话：</label><span id="receivePhone"></span><br>
		<input type="submit" value="提交订单">
	</div>
</form>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- 表单提交前验证 -->
<script type="text/javascript" defer>
	new PCAS("receiveAddressProvince","receiveAddressCity","receiveAddressArea","北京","市辖区","朝阳区");
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