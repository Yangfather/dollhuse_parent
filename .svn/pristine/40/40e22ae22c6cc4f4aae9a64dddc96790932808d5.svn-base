<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<!DOCTYPE html>
<html>
<jsp:include page="../../common/base.jsp"/>
<body class="bodybg">
    <jsp:include page="../../common/top.jsp"/>
    <!--page content start-->
    <div class="page">
        <div class="wrapper fn-clear">
            <!--当月统计-->
            <div class="count mt30 b-none">
            	<div class="count-tit">
	                    <i class="iconfont">&#xe605;</i>
	                    <span class="title-con">网站设置</span>
	            </div>
                <div class="count-con">
                 <div class="search-block mt20">
                 <form action="" id="gridForm">
                      <label class="sch-label">名称：</label>
                      <input type="text" class="sch-input sch-w" name="keywords"/>
                      <input type="button" value="查询" class="sch-btn"/>
                 </form>
                 </div>
                    <div class="tab-top mt20">
                     <a href="system/web/edit" id="gridEdit"><i class="iconfont">&#xe618;</i>修改</a>
                    </div>
                     <table class="counttable tab-ot mt0" cellpadding="0" cellspacing="0" border="0">
                     	<colgroup>
                     		<col width="25">
                     		<col width="60">
                     		<col width="120">
                     		<col width="140">
                     		<col width="500">
                     		<col width="140">
                     	</colgroup>
					    <thead>
					    <tr class="table-tit-ot">
	                  		<th></th>
					        <th>ID</th>
					        <th>名称</th>
					        <th>nid</th>
					        <th>值</th>
					        <th>备注</th>
					    </tr>
					    </thead>
		              <tbody url="<%=basePath%>dollhouse_admin/system/web/contentPage"  id="mydiv">
		                <tr>
		                  <td colspan="6">数据加载中……</td>
		                </tr>
		              </tbody>
	              </table>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	diyou.use("application/system",function(fn){fn.tableGrid("#mydiv","#gridForm")});
    </script>
    <!--page content end -->
    <jsp:include page="../../common/footer1.jsp"></jsp:include>
</body>

</html>
