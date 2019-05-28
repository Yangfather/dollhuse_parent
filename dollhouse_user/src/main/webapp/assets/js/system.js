define(function(require, exports, module) {
	exports._list = function(page,_var,_form){
		require("layer")
		var index=layer.load();
		var _url=$(_var).attr("url");
		_url=($(_form).length>0) ? _url+"?"+$(_form).serialize() :_url;
        $.ajax({
        	
            url: _url,
            data: "page="+page,
            success: function(data){
            	layer.close(index);
                $(_var).html(data);
                 exports._pages(_var,_form);
            }
        })
    }
	 exports._pages = function(_var,_form){
        require("pages");
         $("#pages").pagination(parseInt($("#pages").attr('data-total')), {
            callback: pageselectCallback,//PageCallback() 为翻页调用次函数。
            prev_text: "上一页",
            next_text: "下一页",
            items_per_page: parseInt($("#pages").attr('data-epage')), //每页的数据个数
            num_display_entries: 3, //两侧首尾分页条目数
            current_page: parseInt($("#pages").attr('data-page')),   //当前页码
            //current_page:0,
            num_edge_entries: 5,
            load_first_page:false
        });
         $("#pages1").pagination(parseInt($("#pages1").attr('data-total')), {
             callback: pageselectCallback,//PageCallback() 为翻页调用次函数。
             prev_text: "上一页",
             next_text: "下一页",
             items_per_page: parseInt($("#pages1").attr('data-epage')), //每页的数据个数
             num_display_entries: 3, //两侧首尾分页条目数
             current_page: parseInt($("#pages1").attr('data-page')),   //当前页码
             //current_page:0,
             num_edge_entries: 5,
             load_first_page:false
         });
        function pageselectCallback(page_id, jq) {
            exports._list(page_id,_var,_form);
            return false;
        }
    }
    exports._selected=function(_var){
    	$(_var).on("click",">tr",function(){
    		var $this = $(this), $sib = $this.siblings("tr"), len = $this.find("input:checkbox").length;
    		if(len){
    			$this.addClass('selected').siblings().removeClass('selected');
    			$this.find("input:checkbox").prop("checked",true);
    			$sib.find("input:checkbox").prop("checked",false);
    		}
    	});
    }
   exports.gridTool=function(_var){
   		require("layer");
   		$("#gridAdd").on("click",function(e){
   			e.preventDefault();
   			var _content=$(this).attr("href"),
   				title = $(this).data("title");
   			layer.open({
			    type: 2,
			    title: title || "添加",
			    area: ['700px', '560px'],
			    fix: false,
			    maxmin: true,
			    content: _content
			});
   		});
//   	平台管理-----修改
   		$("#gridEdit").on("click",function(e){
   			e.preventDefault();
   			var _content=$(this).attr("href"),
				title = $(this).data("title"),
   				_id=$(_var).find(".selected").find("input:checkbox").val();
   			if(_id){
   				layer.open({
			    type: 2,
			    title: title || "修改",
			    area: ['700px', '560px'],
			    fix: false,
			    maxmin: true,
			    content: _content+"?id="+_id
				});
   			}else{
   				layer.msg('请选择至少一项', {icon: 2});
  			}
   			
   		});
//   	配置风险系数
   		$("#gridEditInfo").on("click",function(e){
   			e.preventDefault();
   			var _content=$(this).attr("href"),
  				title = $(this).data("title"),
   				_id=$(_var).find(".selected").find("input:checkbox").val();
   			if(_id){
   				layer.open({
			    type: 2,
			    title: title || "修改附加信息",
			    area: ['700px', '560px'],
			    fix: false,
			    maxmin: true,
			    content: _content+"?id="+_id
				});
   			}else{
   				layer.msg('请选择至少一项', {icon: 2});
   			}
   		});
//		舆情采集
   		$("#sentimentInfo").on("click",function(e){
   			e.preventDefault();
   			var _content=$(this).attr("href"),
   				title = $(this).data("title"),
   				_id=$(_var).find(".selected").find("input:checkbox").val();
   			if(_id){
   				layer.open({
			    type: 2,
			    title: title || "舆情采集设置",
			    area: ['700px', '560px'],
			    fix: false,
			    maxmin: true,
			    content: _content+"?id="+_id
				});
   			}else{
   				layer.msg('请选择至少一项', {icon: 2});
   			}
   		});
//   		修改密码
   		$("#gridEditPwd").on("click",function(e){
   			e.preventDefault();
   			var _content=$(this).attr("href"),
				title = $(this).data("title"),
   				_id=$(_var).find(".selected").find("input:checkbox").val();
   			if(_id){
   				layer.open({
			    type: 2,
			    title: title || "修改密码",
			    area: ['500px', '330px'],
			    fix: false,
			    maxmin: true,
			    content: _content+"?id="+_id
				});
   			}else{
   				layer.msg('请选择至少一项', {icon: 2});
   			}
   			
   		});
   		$("#gridAuth").on("click",function(e){
   			e.preventDefault();
   			var _content=$(this).attr("href"),
				title = $(this).data("title"),
   				_id=$(_var).find(".selected").find("input:checkbox").val();
   			if(_id){
   				layer.open({
			    type: 2,
			    title: title || "分配权限",
			    area: ['700px', '530px'],
			    fix: false,
			    maxmin: true,
			    content: _content+"?id="+_id
				});
   			}else{
   				layer.msg('请选择至少一项', {icon: 2});
   			}
   			
   		});
   		$("#gridDel").on("click",function(e){
   			e.preventDefault();
   			var _content=$(this).attr("href"),
				title = $(this).data("title"),
				text = title || "删除"
   				_id=$(_var).find(".selected").find("input:checkbox").val();
   			if(_id){
   				layer.confirm("请确认是否" + text,function(){
   					$.ajax({
   						url:_content,
   						type:"post",
   						data:{id:_id},
   						success:function(data){
   							if(data.status=="200"){
   								layer.msg(text + "成功！",{icon:1,time:1000},function(){
   									window.location.reload();
   								});
   							}else{
   								if(data.description!=null){
   									layer.msg(data.description,{icon:2,time:2000});
   								}else{
   									layer.msg(text + "失败！",{icon:2,time:2000});
   								}
   							}
   						}
   					});
   				});
   			}else{
   				layer.msg('请选择至少一项', {icon: 2});
   			}
   			
   		});
   }
  exports.searchForm=function(_var,_form){
  	$(_form).find("input[type=button],input[type=submit],a").on("click",function(e){
  		e.preventDefault();
      e.stopPropagation();
  		exports._list(0,_var,_form);
  	});
  }
exports.datePick=function(){
  require("datepicker");
  $("#timestart").on("click",function(e){
    e.preventDefault();
    WdatePicker({maxDate:'#F{$dp.$D(\'timeend\')}'});
  });
   $("#timeend").on("click",function(e){
    e.preventDefault();
     WdatePicker({minDate:'#F{$dp.$D(\'timestart\')}'});
  });

}
    exports.tableGrid=function(_var,_form){
    	exports._list(0,_var,_form);
    	exports._selected(_var);
    	exports.gridTool(_var);//表格
    	exports.searchForm(_var,_form);//搜索
      exports.datePick();//时间控件
    }
    
    
    //平台分析tab切换
    exports.tabChange = function(){
    	$("#detailDiv dt a").each(function(){
    		var $this = $(this),
    			$form = $("#searchForm"),
    			index = $this.attr("data-type");
    		$this.on("click", function(){
    			$this.parent().addClass("on").siblings("dt").removeClass("on");
    			$form.find("input[type='hidden']").val(index);
    			exports.tableGrid("#platformDiv","#searchForm");
    		})
    	})
    }
    //平台分析详情
    exports.platformDetail = function(){
		require("superslide");
		require("layer");
		$("#detailDiv dt").on("click", function(){
//			var $this = $(this),
				_url = $this.data("url"),
				i = $this.index();
			$this.addClass("on").siblings("dt").removeClass("on");
			$(".panel").eq(i).addClass("panel-show").siblings(".panel").removeClass("panel-show");
			if(!$this.hasClass("oneAjax")){
				$this.addClass("oneAjax");
				var index = layer.load();
				$.ajax({
					type: "get",
					url: _url,
					success:function(result){
						layer.close(index);	
						$(".panel").eq(i).html(result);
					}
				})
			}
		})
	}
 
});
