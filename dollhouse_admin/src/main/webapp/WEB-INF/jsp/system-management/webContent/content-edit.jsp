<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<!DOCTYPE html>
<html>
<jsp:include page="../../common/base.jsp"></jsp:include>
<body>
<!-- cls="{required:true,minlength:5,messages:{required:'请输入内容'}}" -->
	<div class="item-form item-form-o">
		<form id="configForm" action="<%=basePath%>dollhouse_admin/system/web/save">
			<input type="hidden" name="id" value="${data.id}">
			<div class="item-bl">
				<label class="form-label">名称</label>
				<div class="form-con">
					<input type="text" class="form-ipt" name="webName" value="${data.name}" />
				</div>
			</div>
			<div class="item-bl">
				<label class="form-label">值</label>
				<c:if test="${data.nid != 'site_logo' && data.nid != 'service_weixin_imgcode'}">
					<div class="form-con">
						<textarea class="form-area" cls="{required:true}" name="value">${data.value}</textarea>
						<p class="remind-txt">${description}</p>
					</div>
				</c:if>
        		<c:if test="${data.nid == 'site_logo' || data.nid == 'service_weixin_imgcode'}">
        			<div class="file-box">
	        			<a href="javascript:;" title="请选择" class="file-btn"><input type="file" name="imgValue"/>请选择</a>
	        			<img src="${data.value}" style="width:100px;height:100px;"/>
	        			<p>${imgDes}</p>
        			</div>
        		</c:if>
			</div>
			<div class="item-bln">
				<input type="submit" class="tijiao" value="提交">
				<a href="#" onclick="parent.layer.close(parent.layer.getFrameIndex(window.name));return false;" class="quxiao">取消</a>
			</div>
		</form>
	</div>
<script type="text/javascript">
   diyou.use("application/layForm",function(fn){fn.validForm("#configForm");});
</script>
</body>

</html>
