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
			            title: [{
			                text: '购买人分布',
			                subtext: '非精准数据',
			                left: 'center'
			            },{
			                text:'合计:'+ sum0,
			                right: 120,
			                top: 40,
			                width: 100,
			                textStyle: {
			                    color: '#000',
			                    fontSize: 16
			                }
			            }],
			            tooltip: {
			                trigger: 'item'
			            },
			            legend: {
			                orient: 'vertical',
			                left: 'left',
			                selectedMode: 'single',
			            },
			            visualMap: {
			                min: 0,
			                max: sum0/20,
			                left: 'left',
			                top: 'bottom',
			                text: ['高', '低'],
			                calculable: true,
			                colorLightness: [0.2, 100],
			                color: ['#c05050','#e5cf0d','#5ab1ef'],
			                dimension: 0
			            },
			            toolbox: {
			                show: true,
			                orient: 'vertical',
			                left: 'right',
			                top: 'center',
			                feature: {
			                    dataView: {
			                        readOnly: false
			                    },
			                    restore: {},
			                    saveAsImage: {}
			                }
			            },
			            grid: {
			                right: 40,
			                top: 100,
			                bottom: 40,
			                width: '30%'
			            },
			            xAxis: [{
			                position: 'top',
			                type: 'value',
			                boundaryGap: false,
			                splitLine: {
			                    show: false
			                },
			                axisLine: {
			                    show: false
			                },
			                axisTick: {
			                    show: false
			                },
			            }],
			            yAxis: [{
			                type: 'category',
			                data: name1,
			                axisTick: {
			                    alignWithLabel: true
			                }
			            }],
			            series: [{
			                z: 1,
			                name:'人数(个)',
			                type: 'map',
			                mapType: 'china',
			                selectedMode : 'multiple',
			                roam: true,
			                left: '10',
			                right: '35%',
			                top: 100,
			                bottom: "35%",
			                zoom: 0.75,
			                label: {
			                    normal: {
			                        show: true
			                    },
			                    emphasis: {
			                        show: true
			                    }
			                },
			                data: resultdata0
			            }, {
			                z: 2,
			                name:'人数(个)',
			                type: 'bar',
			                label: {
			                    normal: {
			                        show: true
			                    },
			                    emphasis: {
			                        show: true,
			                    }
			                },
			                itemStyle: {
			                    emphasis: {
			                        color: "rgb(254,153,78)"
			                    }
			                },
			                data: resultdata0
			            }, {
			                z: 2,
			                name:'人数(个)',
			                type: 'pie',
			                tooltip: {
			                    trigger: 'item',
			                    formatter: "{a} <br/>{b}: {c} ({d}%)"
			                },
			                radius: ['17%', '25%'],
			                center: ['30%', '82.5%'],
			                label: {
			                    normal: {
			                        show: true
			                    },
			                    emphasis: {
			                        show: true,
			                    }
			                },
			                itemStyle: {
			                    emphasis: {
			                        color: "rgb(254,153,78)"
			                    }
			                },
			                data: resultdata0
			            }]
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