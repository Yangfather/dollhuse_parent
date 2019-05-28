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
	<jsp:include page="../common/top.jsp"></jsp:include>
	 <!--page content start-->
	    <div class="page">
	        <div class="wrapper fn-clear">
	            <div class="count mt30 b-none">
	                <div class="count-tit">
	                    <i class="iconfont">&#xe604;</i>
	                    <span class="title-con">管理员</span>
	                </div>
	                <div class="count-con">
	                    <div class="tab-top mt20">
	                    <!-- 添加 -->
	                    	
	                    	<a href="<%=path %>/system/admin/add" id="gridAdd"><i class="iconfont">&#xe617;</i>添加</a>
	                    	
	                    	 <!-- 修改-->
		                    <a href="<%=path %>/system/admin/edit" id="gridEdit"><i class="iconfont">&#xe618;</i>修改</a>
		                    
		                     <!-- 修改密码 -->
		                   
		                    <a href="<%=path %>/system/admin/editPassword" id="gridEditPwd"><i class="iconfont">&#xe618;</i>修改密码</a>
		                    
		                    <!-- 删除 -->
	                    	
		                    <a href="<%=path %>/system/admin/delete" id="gridDel"><i class="iconfont">&#xe615;</i>删除</a>
		                    
	                    </div>
	                    <table class="counttable tab-ot mt0">
	                    	<colgroup>
	                    		<col width="65">
	                    		<col width="100">
	                    		<col width="200">
	                    		<col width="200">
	                    		<col width="200">
	                    		<col width="100">
	                    	</colgroup>
	                    	<thead>
		                        <tr class="table-tit-ot">
		                        	<th></th>
		                            <th>ID</th>
		                            <th>名称</th>
		                            <th>邮箱</th>
		                            <th>电话</th>
		                            <th>状态</th>
		                        </tr>
	                        </thead>
	                        <tbody id="mydiv" url="<%=basePath%><%=path %>/system/admin/page">
								 <tr>
				                     <td colspan="5"><div class="not-data">数据加载中...</div></td>
				                  </tr>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!--page content end -->
	    
	    <script type="text/javascript">
	    	diyou.use("application/system",function(fn){fn.tableGrid("#mydiv","")});
	    </script>
    
	    <jsp:include page="../common/footer1.jsp" />
	</body>
</html>