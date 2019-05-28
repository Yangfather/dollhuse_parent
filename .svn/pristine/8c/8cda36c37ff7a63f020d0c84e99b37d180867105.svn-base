<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html>
<html>
<base href="<%=basePath%>">
<jsp:include page="../common/base.jsp"></jsp:include>
<body class="bodybg">
    <jsp:include page="../common/top.jsp" />
<!--page content start-->
    <div class="page">
        <div class="wrapper fn-clear">
	        <div class="count">
	                <div class="count-tit">
	                    <i class="iconfont">&#xe605;</i>
	                    <span class="title-con">玩具屋未处理订单</span>
	                </div>
	                <div class="count-con">
	                <div class="search-block mt20" style="float:left">
	                 <form id="searchForm" action="<%=path %>/system/search/untreated" method="get">
	                 
	                  <label>商品名称：</label> 
	                   	<input type="text" name="goodsName" class="sch-input" style="width: 150px"/>
	                   <label>收货人：</label>
	                  <input type="text" name="receiveName" class="sch-input"/>
	                  
	                  <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;购买时间： </label>
						<p class="i-dtime">
							<input type="text" class="sch-input sch-w" id="timestart" name="startTime"/> <span class="dtime-bg"></span>
						</p>
						<label>到</label>
						<p class="i-dtime">
							<input type="text" class="sch-input sch-w" id="timeend" name="endTime"/> <span class="dtime-bg"></span>
						</p>
	                  	<input type="submit" value="查询" class="sch-btn"/>
	                 </form>
	                 </div>
	                 <div class="tab-top mt20">
                 	 <c:if test="${isAdmin}">
                 		<h2 style="color:white;margin-left: 5%">玩具屋未处理订单</h2>
               		</c:if>
                 	</div>
	                    <table class="counttable mt0" cellpadding="0" cellspacing="0" border="0" id="tab1">
	                    	<colgroup>
	                    		<col width="25">
	                    		<col width="80">
	                    		<col width="80">
	                    		<col width="80">
	                    		<col width="60">
	                    		<col width="120">
	                    		<col width="200">
	                    		<col width="120">
	                    		<col width="120">
	                    		
	                    	</colgroup>
	                    	<thead>
		                        <tr class="table-tit-ot">
		                            <th>ID</th>
		                            <th>商品名 </th>
		                            <th>颜色</th>
		                            <th>型号</th>
		                            <th>数量</th>
		                            <th>合计</th>
		                            <th>收货地址</th>
		                            <th>留言</th>
		                            <th>操作</th>
		                        </tr>
	                        </thead>
	                        <tbody id="tableDiv" url="<%=basePath%><%=path %>/system/search/untreated">
								<tr>
									<td colspan="9"><div class="not-data">数据加载中</div></td>
								</tr>
	                        </tbody>                         
	                    </table>
	                </div>
	            </div>
	        </div>
        </div>
         <script type="text/javascript">
          	diyou.use("application/system",function(fn){fn.tableGrid("#tableDiv","#searchForm")});
    	</script>
	    <!--page content end -->
	    
	    <jsp:include page="../common/footer1.jsp" />
</body>
</html>