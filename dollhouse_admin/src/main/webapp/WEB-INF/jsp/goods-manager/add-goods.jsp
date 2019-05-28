<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<html>
<base href="<%=basePath%>">
    <title>添加商品</title>
    <!-- 图片上传 -->
    <link href="<%=path %>/assets/css/fileUpload.css" rel="stylesheet" type="text/css"/>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="<%=path %>/assets/js/fileUpload/fileUpload.js"></script>
   <jsp:include page="../common/base.jsp"></jsp:include>
    <base target="_blank">
    <style type="text/css">
    	.contion{width: 100%;
    	}
    	
    	.imgUp{width: 90%;
    	margin-left:auto;
    	margin-right:auto;
    	min-height: 50%;
    	}
    	
    	ul li{list-style: none;padding-top: 20px;}
    	
    	span{padding-left: 5%;}
    	
    	input{height: 35px;}
    	
    	.baseInfo{width: 20%;height: 35px;}
    	
    	.price{width: 8%;}
    	
    	.typeColor{width: 65%;}
    	
    	.type{width: 20%;}
    	
    	.desprtion{
    	width: 76%;
    	min-height:65px; }
    </style>
<body class="bodybg">
<!-- 商品详情部分 -->
<jsp:include page="../common/top.jsp"></jsp:include>
  <div  class="contion" style="margin-top: 10px;">
	  <ul>
	  	<li>
		  	<span class="text">名称：</span><input class="baseInfo" type="text" id="goods.name">
		  	<span class="text">类别：</span>
		  	<select class="baseInfo"  id="goodsType"  cls="{required:true}">
				<c:forEach var="item" items="${data}">
					<option value="${item.value}" ${item.value == platform.goodsType ? 'selected' : '' }>${item.text}</option>
				</c:forEach>
			</select>
		  	<span class="text">价格：</span><input type="text" id="goods.price1" class="price" value="">&nbsp;--&nbsp;<input type="text" id="goods.price2" class="price" value="">
	 	</li>
	 	<li>
	 		<span class="text">颜色：</span><input type="text" id="goods.color" class="typeColor"><font>(多种颜色用","隔开)</font>
	 	</li>
	 	<li id="addvalue">
	 		<span class="type">型号：</span><input class="baseInfo" type="text" name="goods.type">
	  		<span class="type">价格：</span><input class="baseInfo" type="text" name="goods.price">
	  		<span class="type"><img alt="添加型号" src="<%=path %>/assets/img/add.png" style="width: 35px;height: 35px;" onclick="add()"><font>(点击"+"添加型号)</font></span>
	 	</li>
	 	<li style="display: -webkit-flex;align-items: center;">
	 		<span class="ms">描述：</span><textarea class="desprtion" id="desprtion"></textarea>
 		</li>
	  </ul>
  </div>
  <!-- 图片上传部分 -->
  <div class="imgUp">
  	<div id="fileUploadContent" class="fileUploadContent"></div>
        <input type="submit" onclick="testUpload()" value="提交">
        <!-- <button onclick="">提交</button> -->
  </div>
<!-- </form> -->
  <jsp:include page="../common/footer1.jsp"></jsp:include>  
</body>
<script type="text/javascript">

	var i=1;
	function add(){
		i++;
		if(i<7){
			var input="<li id='addvalue'>"+
							"<span class='type'>型号：</span><input style='padding-top: 20px;' class='baseInfo' type='text' name='goods.type' value=''>"+
				      		"<span class='type'>价格：</span><input style='padding-top: 20px;' class='baseInfo' type='text' name='goods.price' value=''>"+
				      "</li>"
				$("#addvalue").append(input);
		}else{
			alert("最多只能添加6个型号")
		}

}
	


    $("#fileUploadContent").initUpload({
        "uploadUrl":"<%=path%>/public/admin/goodsImg",//上传文件信息地址
        //"size":350,//文件大小限制，单位kb,默认不限制
        "maxFileNumber":6,//文件个数限制，为整数
        //"filelSavePath":"",//文件上传地址，后台设置的根目录
        "beforeUpload":beforeUploadFun,//在上传前执行的函数
        //"onUpload":onUploadFun，//在上传后执行的函数
        //autoCommit:true,//文件是否自动上传
        "fileType":['png','jpg','docx','doc']//文件类型限制，默认不限制，注意写的是文件后缀
    });
    function beforeUploadFun(opt){
        opt.otherData =[{"name":"name","value":"zxm"}];;

    }
    function onUploadFun(opt,data){
    	
        uploadTools.uploadError(opt);//显示上传错误
        uploadTools.uploadSuccess(opt);//显示上传成功
    }
    
    
    function testUpload(){
    	/* 名称 */
    	var goodsName=document.getElementById("goods.name").value;
    	
    	/* alert(goodsName); */
    	/*类型  */
    	var type=document.getElementById("goodsType").value;
    	alert(type);
    	/* 最低价 */
    	var price1=document.getElementById("goods.price1").value;
    	/* alert(price1.value); */
    	/* 最高价 */
    	var price2=document.getElementById("goods.price2").value;
    	/* alert(price2.value); */
    	/* 颜色 */
    	var color=document.getElementById("goods.color").value;
    	/* alert(color.value); */
    	/* 描述 */
    	var desprtion=document.getElementById("desprtion").value;
    	/* alert(desprtion.value); */
    	/* 型号和价格 */
    	var goodsType=document.getElementsByName("goods.type");
    	var goodsPrice=document.getElementsByName("goods.price");
    	var goodsTypes=[];
    	var goodsPrices=[];
    	for (var i = 0, j = goodsType.length; i < j; i++){
    		goodsTypes.push(goodsType[i].value);
    		/* alert(goodsType[i].value); */
    		goodsPrices.push(goodsPrice[i].value);
    		/* alert(goodsPrice[i].value); */
    		}
    	/* alert(good); */
    	var opt = uploadTools.getOpt("fileUploadContent");
    	uploadEvent.uploadFileEvent(opt,goodsName,type,price1,price2,color,desprtion,goodsTypes,goodsPrices);
    }
</script>
</html>
