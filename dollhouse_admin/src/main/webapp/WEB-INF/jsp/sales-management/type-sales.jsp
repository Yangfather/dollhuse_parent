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
<link rel="stylesheet" type="text/css" href="<%=path %>/assets/css/newstatic.css">
<script src="<%=path %>/assets/js/china.js"></script>
<body class="bodybg">
	<jsp:include page="../common/top.jsp"></jsp:include>
	<div class="body">
        <div class="main">
            <div class="main-content">
                <div class="table-grid clearfix" style="padding-top:20px; background-color: white;">
                       <div class="chart-data">
						<div class="chart-con fn-clear" class="overview-card-title-container">
							<div class="pie-chart" id="main" style="width: 80%;height:650px;margin-left: 10%" data-url="<%=path%>/admin/salesManagement/areaSales">
							</div>
					    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	var myChart = echarts.init(document.getElementById('main'));
    	var result=[];
    	$("#main").each(function(){
    		var $this = $(this),
			_id = $this.attr("id"),
			_url = $this.data("url");
			$.ajax({
	 		url: _url,
	 		type: "post",
	 		data: {id: _id},
	 		dataType: "json",
			success:function(_data){
			$.each(_data,function(n,value){
				if(value=="200"){	
				}else if (value=="OK") {	
				}else{
					 var resultdata0 = [];
					 resultdata0=value;
					 var name1=[];
					 for (var i = 0; i < resultdata0.length; i++) {
				            var d0 = {
				                name: resultdata0[i].name 
				            };
				            name1.push(resultdata0[i].name); 
				        }
			        var sum0 = 0;
			        for (var i = 0; i < resultdata0.length; i++) {
		                var sum=resultdata0[i].value 
			            sum0+=sum;				            
			        }

			        option = {
			            tooltip: {
			                trigger: 'item',
			                formatter: "{a} <br/>{b}: {c} ({d}%)"
			            },
			            legend: {
			                orient: 'vertical',
			                x: 'left',
			                data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
			            },
			            series: [
			                {
			                    name:'访问来源',
			                    type:'pie',
			                    radius: ['50%', '70%'],
			                    avoidLabelOverlap: false,
			                    label: {
			                        normal: {
			                            show: false,
			                            position: 'center'
			                        },
			                        emphasis: {
			                            show: true,
			                            textStyle: {
			                                fontSize: '30',
			                                fontWeight: 'bold'
			                            }
			                        }
			                    },
			                    labelLine: {
			                        normal: {
			                            show: false
			                        }
			                    },
			                    data:[
			                        {value:335, name:'直接访问'},
			                        {value:310, name:'邮件营销'},
			                        {value:234, name:'联盟广告'},
			                        {value:135, name:'视频广告'},
			                        {value:1548, name:'搜索引擎'}
			                    ]
			                }
			            ]
			        };
			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
				}
			})
		}
	});
});	
</script>
<jsp:include page="../common/footer1.jsp"></jsp:include>
</body>
</html>