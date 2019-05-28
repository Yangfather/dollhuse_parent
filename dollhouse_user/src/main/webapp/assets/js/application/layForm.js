define(function(require, exports, module) {
	exports.validForm=function(_form){
		require("form");
		require("validate");
		require("layer");
		var $form = $(_form);
		var _url = $form.attr("action");
		$form.validate({
			submitHandler: function(form){
				var index = layer.load();
				var txt = $form.find("input[type='submit']").val();
				$form.find("input[type='submit']").val("提交中...").attr("disabled","disabled");
				$(form).ajaxSubmit({
					url:_url,
					type:"post",
					dataType:"json",
					success:function(data){
						layer.close(index);
						if(data.status=="200"){
							layer.msg("提交成功",{icon:1,time:1000},function(){
								parent.location.reload();
							});
						}else{
							layer.msg(data.description,{icon:2,time:2000});
							$form.find("input[type='submit']").val(txt).removeAttr("disabled");
						}
					}
				}); 
				return false;    
			}  
		});
	}
	exports.datePick = function(){
		require("datepicker");
		$("#time").on("click", function(e){
			e.preventDefault();
			WdatePicker();
		})
	}
});