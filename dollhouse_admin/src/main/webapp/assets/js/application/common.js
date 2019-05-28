/****************************************
 ***@处理站点的一些公用方法
 ***@author:james;
 ***@date:2015-01-13; 
 ****************************************/
define(function(require, exports, module) {
	//点击弹窗提示确认取消
	exports.dyConfirm = function(classname,url){
		require("layer");
		$("."+classname).each(function(){
			$(this).on("click",function(){
				var rel = $(this).attr("rel")?$(this).attr("rel"):"";
				//Ajax获取
				$.ajax({
					type:"get",
					url:url+rel,
					success:function(data){
						layer.open({
							title:"审核",
					        type: 1,
					        area:["500px","300px"],
					        content: data //注意，如果str是object，那么需要字符拼接。
					    });
					}
				});
			});
		});
	}
	
	//数据统计模块，图表的绘制，需要从后台获取数据
	exports.plateform = function(){ //平台监控
		//打印图标
    	require("highcharts");
    	$.ajax({
	 		url:"statistics/platform/type",
	 		type:"post",
	 		dataType:"json"
	 	}).done(function(_data){
	 		var plateTypeData = {
    			chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        tooltip: {
		    	    pointFormat: '所占比例: <b>{point.percentage:.2f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                	distance: 10,
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: <br>{point.percentage:.2f} %'
		                }
		            }
		        },
    	        series: [{
    	            type: 'pie',
    	            name: null,
    	            data: _data.data
    	        }]
	 		};
	 		exports.creatCharts('#plateType',plateTypeData);	//平台按类型占比
	 	});
	 	$.ajax({
	 		url:"public/statistics/amount",
	 		type:"post",
	 		dataType:"json"
	 	}).done(function(_data){
	 		var plateAmountData = {
    			chart: {
    	            type: 'column'
    	        },
    	        title: {
    	            text: null
    	        },
    	        xAxis: {
    	            categories: _data.data.x_data
    	        },
    	        yAxis: {
    	            min: 0,
    	            title: {
    	                text: '金额(万元)'
    	            }
    	        },
    	         tooltip: {
			            valueSuffix: '万元'
			        },
    	        plotOptions: {
    	            column: {
    	                pointPadding: 0.2,
    	                borderWidth: 0
    	            }
    	        },
    	        series: _data.data.y_data
	 		}
	 		exports.creatCharts('#plateAmount',plateAmountData); //平台备案金额分析
	 	});    	
    }
	
	//极地蛛网图
	exports.netCharts = function(){
		//打印图标
    	require("highcharts");
    	require("highcharts-more");
    	require("exporting");
    	$(".net-chart").each(function(){
    		var $this = $(this),
    			_id = $this.attr("id"),
    			_url = $this.data("url");
    		$.ajax({
    	 		url: _url,
    	 		type: "post",
				data: {id: _id},
    	 		dataType: "json"
    	 	}).done(function(_data){
    	 		var chartNet = {
	 				chart: {
			            polar: true,
			            type: 'line'
			        },
				    pane: {
				    	size: "70%"
				    },
				    xAxis: {
				        categories: _data.data.categories,
				        tickmarkPlacement: "on",				
				        lineWidth: 0
				    },
				    yAxis: {
				        gridLineInterpolation: "polygon",			     
				        lineWidth: 0,
				        tickPositions: [0, 50,100],
				        min: 0
				    },
				    tooltip: {
				    	shared: true,
				        pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>'
				    },
				    legend: {
				        align: "center",
				        verticalAlign: "bottom",
				        y: 40,
				        layout: "vertical"
				    },
					series: _data.data.series/*[{
            name: 'Allocated Budget',
            data: [100, 100, 100, 100, 100, 98],
            pointPlacement: 'on'
        }]*/
    	 		};
    	 		exports.creatCharts('#' + _id ,chartNet);
    	 	});
    	})
    }
	//折线图
	exports.curveCharts = function(){
		//打印图标
		require("highcharts");
		$(".curve-chart").each(function(){
			var $this = $(this),
				_id = $this.attr("id"),
				_url = $this.data("url");
			$.ajax({
				url: _url,
				type: "post",
				data: {id: _id},
				dataType: "json"
			}).done(function(_data){
				var chartCurve = {
					chart: {
	                    type: 'line'
	                },
					title: {
                        text: _data.data.title
                    },
                    xAxis: { //x轴的信息
                        categories: _data.data.categories,
                        reversed: true
                    },
                    yAxis: { //y轴标题
                        title: {
                            text: _data.data.ytext,
                            reversed: true
                        }
                    },
                    tooltip: {
			            valueSuffix: _data.data.valueSuffix
			        },
                    series: _data.data.series
				};
    	 		exports.creatCharts('#' + _id, chartCurve);
			});
		});
	}
	
	//柱状图
	exports.columnarCharts = function(){
		//打印图标
		require("highcharts");
		$(".columnar-chart").each(function(){
			var $this = $(this),
				_id = $this.attr("id"),
				_url = $this.data("url");
			$.ajax({
				type: "post",
				url: _url,
				data: {id: _id},
				dataType: "json"
			}).done(function(_data){
				var chartColumnar = {
					chart: {
			            type: 'column'
			        },
			        title: {
			            text: _data.data.title
			        },
			        xAxis: {
			            categories: _data.data.categories,
			            reversed: true
			        },
			        credits: {
			            enabled: false
			        },
			        series: _data.data.series
				};
    	 		exports.creatCharts('#' + _id, chartColumnar);
			});
		});
	}
	
	//饼图
	exports.pieCharts = function(){
		//打印图标
		require("highcharts");
		$(".pie-chart").each(function(){
			var $this = $(this),
				_id = $this.attr("id"),
				_url = $this.data("url");
			$.ajax({
				type: "post",
				url: _url,
				data: {id: _id},
				dataType: "json"
			}).done(function(_data){
				var chartPie = {
			        title: {
			            text: _data.data.title
			        },
			        plotOptions: {
			        	pie: {
			        		innerSize: '180'
			        	}
			        },
			        tooltip: {
				    	shared: true,
				        pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y:,.0f}</b><br/>所占比例: <b>{point.percentage:.2f}% </b><br/>'
				    },
			        credits: {
			            enabled: false
			        },
			        series: _data.data.series
				};
    	 		exports.creatCharts('#' + _id, chartPie);
			});
		});
	}
	
	//面积图
	exports.areaCharts = function(){
    	require("highcharts");
    	$(".area-chart").each(function(){
    		var $this = $(this),
    			_id = $this.attr("id"),
    			_url = $this.data("url");
   		$.ajax({
  	 		url: _url,
   	 		type: "post",
   	 		data: {id: _id},
    	 	dataType: "json"
    	 	}).done(function(_data){
    	 		var chartArea = {
	 				chart: {
	 		            type: 'area'
	 		        },
	 		        title: {
	 		            text: _data.data.title
	 		        },	 		  
	 		       xAxis: {
	 		            categories:_data.data.categories,
	 		            reversed: true
	 		        },
	 		        plotOptions: {
	 		            area: {
	 		            	marker:{
	 		            		enabled: false
	 		            	}
	 		            }
	 		        },
	 		        series: _data.data.series
    	 		};
    	 		exports.creatCharts('#' + _id ,chartArea);
  	 	});
    	})
    }
	
	//曲线图
	exports.splineCharts = function(){
    	require("highcharts");
    	$(".spline-chart").each(function(){
    		var $this = $(this),
    			_id = $this.attr("id"),
    			_url = $this.data("url");
    		$.ajax({
    	 		url: _url,
    	 		type: "post",
				data: {id: _id},
    	 		dataType: "json"
    	 	}).done(function(_data){
    	 		var chartSpline = {
	 				chart: {
	 		            type: 'spline'
	 		        },
	 		        title: {
	 		            text: _data.data.title
	 		        },
	 		        xAxis: {
	 		            categories:_data.data.categories,
	 		            reversed: true
	 		        },
	 		        plotOptions: {
	 		            spline: {
	 		                marker: {
	 		                	enabled: false,
	 		                    radius: 4,
	 		                    lineColor: '#666666',
	 		                    lineWidth: 1
	 		                }
	 		            }
	 		        },
	 		        series: _data.data.series
    	 		};
    	 		exports.creatCharts('#' + _id ,chartSpline);
   	 	});
    	})
    }
	
	//混合图
	exports.mixingCharts = function(){
    	require("highcharts");
    	$(".mixing-chart").each(function(){
    		var $this = $(this),
    			_id = $this.attr("id"),
    			_url = $this.data("url");
    		$.ajax({  
   	 		url: _url,
    	 	type: "post",
			data: {id: _id},
    	 	dataType: "json"
    	 	}).done(function(_data){
    	 		var chartMixing = {
	 				title: {
	 		            text: _data.data.title
	 		        },
	 		        xAxis: {
	 		            categories: _data.data.categories,
	 		           reversed: true
	 		        },
	 		       plotOptions: {
	 		            area: {
	 		            	marker:{
	 		            		enabled: false
	 		            	}
	 		            },
	 		           spline: {
	 		            	marker:{
	 		            		enabled: false
	 		            	}
	 		            }
	 		        },
	 		        series: _data.data.series
    	 		};
    	 		exports.creatCharts('#' + _id ,chartMixing);
  	 	});
    	})
    }
	
	//图表绘制
    exports.creatCharts = function(id,optconfig){
    	require("highcharts");
    	require("highcharts-more");
    	require("exporting");
    	var optionDef = {                        //默认配置
                credits: {
                    enabled: false               //去除版权信息
                },
                legend: {
                    enabled: false               //图例开关
                },
                chart: {
                    type: 'spline'               //指定图表的类型，默认是曲线图（spline）
                },
                title: {
                    text: null                   //指定图表标题
                },
                xAxis: {
                    categories: []               //指定x轴分组
                },
                yAxis: {
                    title: {
                        text: null               //指定y轴的标题
                    }
                },
                series: [{                       //指定数据列
                    data: []                     //数据
                }]
            },
            optionConfig;
    	optionConfig = $.extend({},optionDef,optconfig);
    	$(id).highcharts(optionConfig);
    }

 exports.statisFunds=function(amount,chartForm){
 	require("datepicker");
 	var $amount=$(amount);	
 	$("#timebar").find("a").on("click",function(e){
 		e.preventDefault();
 		$(this).closest('form').find("input[type=text]").val("");
 		$(this).addClass('on').siblings('a').removeClass('on');
 		$(this).siblings('input[name=dateType]').val($(this).attr("data-val"));
 		statis();//图表更新
 	});
 	$("#amountType").on("change",function(e){
 		e.preventDefault();
 		statis();//图表更新
 	});
 	$("#timestart1").on("click",function(e){
	    e.preventDefault();
	    WdatePicker({maxDate:'#F{$dp.$D(\'timeend1\')}',dchanging:dateChart});
	  });
   $("#timeend1").on("click",function(e){
   	 e.preventDefault();
     WdatePicker({minDate:'#F{$dp.$D(\'timestart1\')}',dchanging:dateChart});
  });//时间选择触发
 	
	   
	function dateChart(){//时间触发
 		var newdate =$dp.cal.newdate;
 		$("#timebar").find("a").removeClass('on');
 		$("#timebar").find("input[name=dateType]").val("");
 		statis(this.name, newdate);//图表更新
 	}
 	function statis(name, newdate){//资金图表
 		var chartData=[];
 		var arr=$(chartForm).serializeArray(),_obj={};
	 	$.each(arr,function(k,v){_obj[v.name]=v.value;});
	 	if(newdate){
	 		var day = newdate.d < 10 ? "0" + newdate.d : newdate.d,
	 			month = newdate.M < 10 ? "0" + newdate.M : newdate.M;
	 		_obj[name] = newdate.y + '-' + month + '-' + day;	
	 	}
	 	$.ajax({
	 		url:$amount.attr("url"),
	 		type:"post",
	 		data:_obj,
	 		dataType:"json"
	 	}).done(function(_data){
	 		var results=_data.data;
	 		if(results.timeList && results.timeList.length>0){
	 			chartData={
			        xAxis: {
			            categories: results.timeList
			        },
			        yAxis: {
			            title: {
			                text: '单位('+results.unit+')'
			            },
			            plotLines: [{
			                value: 0,
			                width: 1,
			                color: '#808080'
			            }]
			        },
			        tooltip: {
			            valueSuffix: results.unit
			        },
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'middle',
			            borderWidth: 0
			        },
			        series: results.se
	 		};
	 		exports.creatCharts(amount,chartData); 	
	 		}else{
	 			$amount.html("<div class='not-chart'>暂无数据</div>");
	 		}
	 				
	 	});
 	}
 	statis();//初始化
 };
exports.statisLumn=function(){
	var chartData=[];
 		$.ajax({
	 		url:"statistics/funds/amountApr",
	 		type:"post",
	 		dataType:"json"
	 	}).done(function(_data){
	 		var results=_data.data;
	 		for(var i=0;i<results.se.length;i++){
	 			if(results.timeList && results.timeList.length>0){
			 		chartData[i] = {
						chart: {
				            type: 'column'
				        },
				        tooltip: {
			            valueSuffix: '%'
			       			},
				        xAxis: {
				            categories: results.timeList
				        },
				        yAxis: {
				            min: 0,
				            title: {
					                text: '比例(%)'
					            }
				        },
				        plotOptions: {
				            column: {
				                borderWidth: 0
				            }
				        },
				       series: results.se[i].ylist
				};
	 		exports.creatCharts("#"+results.se[i].yname,chartData[i]);
	 		}else{
	 			$("#"+results.se[i].yname).html("<div class='not-chart'>暂无数据</div>");
	 		}
	 				
	 			}		
	 	});
}
exports.statisPie=function(){
	var chartData=[];
 		$.ajax({
	 		url:"statistics/project/getProjectApr",
	 		type:"post",
	 		dataType:"json"
	 	}).done(function(_data){
	 		var results=_data.data;
	 		for(var i=0;i<results.se.length;i++){
	 			if(results.se[i].ydata && results.se[i].ydata.length>0){
				chartData[i] = {
				 chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        tooltip: {
		    	    pointFormat: '所占比例: <b>{point.percentage:.2f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.2f} %'
		                }
		            }
		        },
	            series: [{
	                type: 'pie',
	                name: null,
	                data: results.se[i].ydata
	            }]
		};
	 		exports.creatCharts("#"+results.se[i].yname,chartData[i]);
	 		}else{
	 			$("#"+results.se[i].yname).html("<div class='not-chart'>暂无数据</div>");
	 		}
	 				
	 			}		
	 	});
}

	exports.sendKey = function() {
	    $("#sendKey").on("click",function(e){
	   		e.preventDefault();
	   		require("layer");
	       	$.post("platform/manager/sendKey", {pid:$("#pid").val()},function(data) {
	       		if(data.status == '200'){
	       			layer.msg(data.description,{icon:1, time: 1000});			 
	       		}else{
	       			layer.msg(data.description,{icon:2, time: 2000});
	       		}
	       	},"json");
	   	});
	
	}
	
	//点击图片放大
	exports.fancybox = function(){
		require("fancybox");
		$(".fancybox").fancybox({
		    'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic',
			'titlePosition' : 'inside'
		});
	}

exports.chartTab=function(_form){
	diyou.use("application/system",function(fn){fn.tableGrid("#tableDiv","#gridForm")});
	$("#chartTab").find("li").on("click",function(e){
		e.preventDefault();
		$(this).addClass('on').siblings().removeClass('on');
		$("#gridForm").find("input[type='hidden']").val($(this).attr("data-val"));
		diyou.use("application/system",function(fn){fn.tableGrid("#tableDiv","#gridForm")});
	});
}
exports.showNavSelect=function(){
	$(function(){
		//默认选中值
		$('#nav-text').text($("#selects").find("option:selected").text());
		// 显示网站导航下拉选项
		$('#event-monitor-operation').click(function(){
			$('#nav-select').toggle(200);
		})
		// 选中下拉后把文字显示到外面,并跳转相应的页面
		$("#selects").change(function(){
			var text = $("#selects").find("option:selected").text();
			var type = $("#selects").find("option:selected").attr('data-type');
			var id = $("#selects").val();
			$('#nav-text').text(text);
			$('#nav-select').hide(100);
			function getContextPath() {
			    var pathName = document.location.pathname;
			    var index = pathName.substr(1).indexOf("/");
			    var result = pathName.substr(0,index+1);
			    return result;
			  }
			var path = getContextPath();
			if(type=='1'){
				window.location.href="/static/newplatform/index?id="+id;
				//window.location.href=path+"/static/newplatform/index?id="+id;			
				return false;
			}else if(type=='5'){
				window.location.href="/static/newSmallplatform/index?id="+id ;
				//window.location.href=path+"/static/newSmallplatform/index?id="+id ;
				return false;
			}
		})
		//收缩或展开菜单
		$('.expandable').click(function(){
			$(this).next('ul').toggle();
		})
		//为a标签跳转后添加样式
		 $('#aside a').each(function () {
            if ($($(this))[0].href == String(window.location))
                $(this).addClass('visited').attr('href', 'javascript:void(0);');
        });
	})
}
	

exports.echarts=function(){
	require.config({  
	    paths:{   
	        'echarts' : 'assets/js/echarts',  
	        'echarts/chart/bar' : 'assets/js/widgets/echarts' ,
	        'echarts/chart/line' :'assets/js/widgets/echarts',
	    }  
	});  
	require("echarts"),
	require("echarts/chart/bar"),
	require("echarts/chart/line"),
	$(".pie-chart").each(function(){
		alert("world")
		var myChart = echart.init(document.getElementById('main'));
		var $this = $(this),
			_id = $this.attr("id"),
			_url = $this.data("url");
		alert(_id)
		alert(_url)
		$.ajax({
	 		url: _url,
	 		type: "post",
			data: {id: _id},
	 		dataType: "json"
	 	}).done(function(_data){
	 		 option = {
	 		        tooltip : {
	 		            trigger: 'axis',
	 		            axisPointer: {
	 		                type: 'shadow',
	 		                label: {
	 		                    show: true
	 		                }
	 		            }
	 		        },
	 		        toolbox: {
	 		            show : true,
	 		            feature : {
	 		                mark : {show: true},
	 		                dataView : {show: true, readOnly: false},
	 		                magicType: {show: true, type: ['line', 'bar']},
	 		                restore : {show: true},
	 		                saveAsImage : {show: true}
	 		            }
	 		        },
	 		        calculable : true,
	 		        legend: {
	 		            data:['Growth', 'Budget 2011', 'Budget 2012'],
	 		            itemGap: 5
	 		        },
	 		        grid: {
	 		            top: '12%',
	 		            left: '1%',
	 		            right: '10%',
	 		            containLabel: true
	 		        },
	 		        xAxis: [
	 		            {
	 		                type : 'category',
	 		                data : obama_budget_2012.names
	 		            }
	 		        ],
	 		        yAxis: [
	 		            {
	 		                type : 'value',
	 		                name : 'Budget (million USD)',
	 		                axisLabel: {
	 		                    formatter: function (a) {
	 		                        a = +a;
	 		                        return isFinite(a)
	 		                            ? echarts.format.addCommas(+a / 1000)
	 		                            : '';
	 		                    }
	 		                }
	 		            }
	 		        ],
	 		        dataZoom: [
	 		            {
	 		                show: true,
	 		                start: 94,
	 		                end: 100
	 		            },
	 		            {
	 		                type: 'inside',
	 		                start: 94,
	 		                end: 100
	 		            },
	 		            {
	 		                show: true,
	 		                yAxisIndex: 0,
	 		                filterMode: 'empty',
	 		                width: 30,
	 		                height: '80%',
	 		                showDataShadow: false,
	 		                left: '93%'
	 		            }
	 		        ],
	 		        series : [
	 		            {
	 		                name: 'Budget 2011',
	 		                type: 'bar',
	 		                data: obama_budget_2012.budget2011List
	 		            },
	 		            {
	 		                name: 'Budget 2012',
	 		                type: 'bar',
	 		                data: obama_budget_2012.budget2012List
	 		            }
	 		        ]
	 		    };

	 		    myChart.setOption(option);
			 	});
			})
		}


	//权限分配(树分支)
//	exports.zTree = function(){
//		require("ztreecore");
//		require("ztreeexcheck");
//		var setting = {
//			check: {
//				autoCheckTrigger: false,
//				chkboxType: {"Y":"ps","N":"ps"},
//				chkStyle: "checkbox",
//				enable: true,
//				nocheckInherit: false,
//				chkDisabledInherit: false,
//				radioType: "level"
//			},
//			data: {
//				simpleData: {
//					enable: true
//				}
//			}
//		};
//		var zNodes =[
// 			{ id:1, pId:0, name:"首页", checked:true, open:true},
// 			{ id:2, pId:0, name:"平台管理", checked:true, open:true},
// 			{ id:3, pId:0, name:"项目管理", checked:true, open:true},
// 			{ id:4, pId:0, name:"贷款管理", checked:true, open:true},
// 			{ id:5, pId:0, name:"还款管理", checked:true, open:true},
// 			{ id:6, pId:0, name:"数据统计", checked:true, open:true},
// 			{ id:61, pId:6, name:"平台统计", checked:true, open:true},
// 			{ id:62, pId:6, name:"资金统计", checked:true, open:true},
// 			{ id:63, pId:6, name:"项目统计", checked:true, open:true},
// 			{ id:7, pId:0, name:"文章管理", checked:true, open:true},
// 			{ id:8, pId:0, name:"站内信管理", checked:true, open:true},
// 			{ id:81, pId:8, name:"接收记录", checked:true, open:true},
// 			{ id:82, pId:8, name:"发送消息", checked:true, open:true}
// 		];
//		var code;
//
//		function setCheck() {
//			var zTree = $.fn.zTree.getZTreeObj("treeChk"),
//			type = { "Y":"ps", "N":"ps"};
//			zTree.setting.check.chkboxType = type;
//			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
//		}
//		function showCode(str) {
//			if (!code) code = $("#code");
//			code.empty();
//			code.append("<li>"+str+"</li>");
//		}
//
//		$(function(){
//			$.fn.zTree.init($("#treeChk"), setting, zNodes);
//			setCheck();
//		});
//	}
});