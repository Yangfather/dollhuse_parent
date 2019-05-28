// user
var user_Boolean = false;
var password_Boolean = false;
var varconfirm_Boolean = false;
var emaile_Boolean = false;
var Mobile_Boolean = false;
//手机号正则
var mPattern = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
//Email正则
var ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
$(".reg_user").blur(function(){
  if ((/^[a-z0-9_-]{4,8}$/).test($(".reg_user").val())){
    $(".user_hint").html("✔").css("color","green");
    user_Boolean = true;
  }else {
    $('.user_hint').html("×").css("color","red");
    user_Boolean = false;
  }
});
// password
$('.reg_password').blur(function(){
  if ((/^[a-z0-9_-]{6,16}$/).test($(".reg_password").val())){
    $('.password_hint').html("✔").css("color","green");
    password_Boolean = true;
  }else {
    $('.password_hint').html("×").css("color","red");
    password_Boolean = false;
  }
});


// password_confirm
$('.reg_confirm').blur(function(){
  if (($(".reg_password").val())==($(".reg_confirm").val())){
    $('.confirm_hint').html("✔").css("color","green");
    varconfirm_Boolean = true;
  }else {
    $('.confirm_hint').html("×").css("color","red");
    varconfirm_Boolean = false;
  }
});


// Email
$('.reg_email').blur(function(){
  if (ePattern.test($(".reg_email").val())){
    $('.email_hint').html("✔").css("color","green");
    emaile_Boolean = true;
  }else {
    $('.email_hint').html("×").css("color","red");
    emaile_Boolean = false;
  }
});


// Mobile
$('.reg_mobile').blur(function(){
  if (mPattern.test($(".reg_mobile").val())){
    $('.mobile_hint').html("✔").css("color","green");
    Mobile_Boolean = true;
  }else {
    $('.mobile_hint').html("×").css("color","red");
    Mobile_Boolean = false;
  }
});


// click
$('.red_button').click(function(){
  if(user_Boolean && password_Boolean && varconfirm_Boolean && emaile_Boolean && Mobile_Boolean == false){
	  $('#register_hint').html("请完善注册信息！").css("color","red");
  }else {
    
    $.ajax({
        url : "common/user/register",

        data : {username:$(".reg_user").val(),password:$(".reg_password").val(),email:$(".reg_email").val(),phone:$(".reg_mobile").val()},

        type:"post",
        
		dataType:"json"
        
	}).done(function(data){
		//$("#loading").hide();
		 if(data.status == 200) {
             /*location.href = "system/index";*/
			 alert("注册成功")
         } else {
             //$('#errorMessage').html(data.description);
             alert(data.description);
         }
	}); 
  }
  return false;
});
