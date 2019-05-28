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
			        var base = +new Date(1968, 9, 3);
			        var oneDay = 24 * 3600 * 1000;
			        var date = [];

			        var data = [Math.random() * 300];

			        for (var i = 1; i < 20000; i++) {
			            var now = new Date(base += oneDay);
			            date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
			            data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
			        }

			        option = {
			            tooltip: {
			                trigger: 'axis',
			                position: function (pt) {
			                    return [pt[0], '10%'];
			                }
			            },
			            title: {
			                left: 'center',
			                text: '大数据量面积图',
			            },
			            toolbox: {
			                feature: {
			                    dataZoom: {
			                        yAxisIndex: 'none'
			                    },
			                    restore: {},
			                    saveAsImage: {}
			                }
			            },
			            xAxis: {
			                type: 'category',
			                boundaryGap: false,
			                data: date
			            },
			            yAxis: {
			                type: 'value',
			                boundaryGap: [0, '100%']
			            },
			            dataZoom: [{
			                type: 'inside',
			                start: 0,
			                end: 10
			            }, {
			                start: 0,
			                end: 10,
			                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
			                handleSize: '80%',
			                handleStyle: {
			                    color: '#fff',
			                    shadowBlur: 3,
			                    shadowColor: 'rgba(0, 0, 0, 0.6)',
			                    shadowOffsetX: 2,
			                    shadowOffsetY: 2
			                }
			            }],
			            series: [
			                {
			                    name:'模拟数据',
			                    type:'bar',
			                    smooth:true,
			                    symbol: 'none',
			                    sampling: 'average',
			                    itemStyle: {
			                        normal: {
			                            color: 'rgb(255, 70, 131)'
			                        }
			                    },
			                    areaStyle: {
			                        normal: {
			                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
			                                offset: 0,
			                                color: 'rgb(255, 158, 68)'
			                            }, {
			                                offset: 1,
			                                color: 'rgb(255, 70, 131)'
			                            }])
			                        }
			                    },
			                    data: data
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