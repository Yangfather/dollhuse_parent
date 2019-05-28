/****************************************
 ***@贷款管理模块
 ***@author:james;
 ***@date:2015-01-13; 
 ****************************************/
define(function(require, exports, module) {
	exports.loanDetail = function(){
		require("superslide");
		var contentArray = []; //用于处理是否需要请求的数组
		$("#detailDiv").slide({trigger:"click",titCell:".hd dt",mainCell:".bd",endFun:function(i){
			/*if(!contentArray[i]){
				contentArray[i]=true; 
			}*/
		/*	if(!contentArray[i]){
				var _url = $("#detailDiv .hd dt").eq(i).attr("rel");
				$.ajax({
	    			type:"get",
	    			url:_url,
	    			success:function(data){
	    				$("#detailDiv .bd .content").eq(i).html(data);
	    				contentArray[i]=true; //请求成功，为true，重新切换回来的时候，不会再发送请求，减少请求数
	    			},
	    			error:function(){
	    				contentArray[i]=false; //请求失败，为false，重新切换回来的时候，会再次发起请求
	    				$("#detailDiv .bd .content").eq(i).html("数据载入错误");
	    			}
	    		})
			}*/
		}});
	}
});