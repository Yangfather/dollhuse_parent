<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<jsp:include page="../common/base.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/static.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/newstatic.css">
<body>
	<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="body">
		<div class="main">
			<div class="main-content">
				<div class="overview-card-title-container">
					网站概况
					<a title="点击可查看报告详细帮助" id="report-tip-icon" class="report-help" href="javascript:void(0);">&nbsp;</a>
					<div class="report-tip" id="report-tip" style="display: none;">
				    	<div class="tip-arrow" style="left: 157px;"></div>
					    <div class="report-tip-content">
					        <p></p>
						</div>
					</div>	
				</div>
				<div class="table-grid clearfix" style="margin-top: 60px;">
					<div class="table-grid-item plat-analysis">
                       	<ul class="ex-list">
                       		<li class="ex-item">
								<b class="ex-tit">全部订单：</b>
								<div class="ex-con">
									<p class="nowrap" title="${data.handleNum}'+'${data.unHandleNum }个"><b class="c-blue">${data.handleNum}+${data.unHandleNum }个</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 96px;">总金额：</b>
								<div class="ex-con" style="margin-left: 14px;">
									<p class="nowrap" title="${data.handleMoney}+${data.unHandleMoney }元"><b class="c-blue">${data.handleMoney}+${data.unHandleMoney }元</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 96px;">总配送数：</b>
								<div class="ex-con" style="margin-left: 14px;">
									<p class="nowrap" title="${data.handleOrder}+${data.unHandleOrder }笔"><b class="c-blue">${data.handleOrder}+${data.unHandleOrder }笔</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 106px;">已处理订单：</b>
								<div class="ex-con" style="padding-left: 12px">
									<p class="nowrap" title="${data.handleNum}个"><b class="c-red">${data.handleNum}个</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 96px;">已处理订单金额：</b>
								<div class="ex-con" style="margin-left: 14px;">
									<p class="nowrap" title="${data.handleMoney}元"><b class="c-blue">${data.handleMoney}元</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 96px;">已处理配送数：</b>
								<div class="ex-con" style="margin-left: 14px;">
									<p class="nowrap" title="${data.handleOrder}笔"><b class="c-blue">${data.handleOrder}笔</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 106px;">未处理订单：</b>
								<div class="ex-con" style="padding-left: 12px">
									<p class="nowrap" title="${data.unHandleNum}个"><b class="c-red">${data.unHandleNum}个</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 96px;">未处理订单金额：</b>
								<div class="ex-con" style="margin-left: 14px;">
									<p class="nowrap" title="${data.unHandleMoney}元"><b class="c-blue">${data.unHandleMoney}元</b></p>
								</div>
							</li>
							<li class="ex-item">
								<b class="ex-tit" style="width: 96px;">未处理配送数：</b>
								<div class="ex-con" style="margin-left: 14px;">
									<p class="nowrap" title="${data.unHandleOrder}笔"><b class="c-blue">${data.unHandleOrder}笔</b></p>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<div class="table-grid clearfix">
					<div class="left">
                        <div class="table-grid-item">   
                        		<div class="chart-data">
	                        	<div class="chart-con fn-clear">
									<div class="pie-chart" id="amountpie"
										data-url="public/newloanSmallSta/amountpie?id=${id}"></div>
								</div>
							</div>	                	
                        </div>
                    </div>
                    <div class="right">
                        <div class="table-grid-item">
                        		<div class="net-chart" id="loansmallrisk" data-url="static/platform/risk?id=${id}&type=1"></div>       
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer1.jsp"></jsp:include>
<script type="text/javascript">
    diyou.use("index", function(fn) {
       // fn.datepicker();
       // fn.detiltab();
    })
    $(function(){
    	var session='${sessionUser.adminName }';
    	$.ajax({
    		url:'dollhouse_user/dollhouse/admin/session',
    		data:{adminName: session},
			type:"post",
			dataType:"json"
    	}).done(function(data){
			$("#loading").hide();
			 if(data.status == 200) {
               } else {
                   alert("error");
               }
		});
    })
</script>
</body>
</html>