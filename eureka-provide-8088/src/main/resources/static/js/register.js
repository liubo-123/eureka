var valiImg2;
var flag='';
$(function(){
	//在register.html中的注册表单添加submit事件
	$("form").submit(function(){
		return regist();
		//return false是终止页面中的表单的提交按钮提交
		//return true用页面的提交按钮提交表单，而不是用
	});
	// 为input添加失去焦点事件
	$("input[name='username']").blur(function(){
		checkNull("username");
		checkName("username");
	});
	$("input[name='password']").blur(function(){
		return checkNull("password");
	});
	$("input[name='password2']").blur(function(){
		return checkNull("password2");
	});
	$("input[name='code']").blur(function(){
		return checkNull("code");
	});
	//为验证码添加失去焦点事件
	$("input[name='code']").blur(function(){
		return checkCode("code");
	});
	//为验证码动态实时点击获取
	$("#valiImg").click(function(){
		var time=new Date().getTime();
		var url="user/doGet1?time="+time;
		
		$(this).attr("src",url);
	});
	
});
function checkName(name){
	
}
function checkCode(name){
    valiImg2=$("input[name='"+name+"']").val();
	$.ajax({
		url:"user/doGet/valiName/"+valiImg2,//user/login
		type:"post",
		dataType:"json",
		success:function(map){
			if(map.res==1){
				//alert(result.message);
				flag=true;
				$("#sss").html("");
			}else{
				$("#sss").html("验证码错误,重新输入！").css("color","red");
			    flag=false;
			}
		},
		error:function(){
			//$("#sss").html("验证码错误,重新输入！").css("color","red");
			//alert("注册失败");
			//alert("验证码输入错误！");
		}
	});
	return false;
};

function checkNull(name){
	   //获取输入框的值
	   var value=$("input[name='"+name+"']").val();
	   //判断是否为空
	   alert("name====="+name)
	   if(name=="username"){
	   if($.trim(value)==""){
	   //不符合添加错误提示信息
	   alert("$.trim(value)===="+$.trim(value))
		   $("#ssu").html("用户名不能为空！").css("color","red");
	       return false;
	   }else{
			$.ajax({
				url:"user/findUserByName",
				type:"get",
				data:{"name":value},
				dataType:"json",
				success:function(map){
					//后端验证用户是否已存在
					if(map.res==0){
						$("#sss").html("");
						
					}else {
						$("#sss").html("用户名已存在").css("color","red");
					}
				},
				error:function(){
					alert("服务器异常！");
				}
			});
		   $("#ssu").html("");
		   
	      return true;
	   }
	   }
	   if(name=="password"){
		   if($.trim(value)==""){
		   //不符合添加错误提示信息
		   alert("password$.trim(value)===="+$.trim(value))
			   $("#ssp").html("密码不能为空！").css("color","red");
		       return false;
		   }else{
			   $("#ssp").html("");
		      return true;
		   }
		   }
	   if(name=="password2"){
		   if($.trim(value)==""){
		   //不符合添加错误提示信息
			   $("#ssp2").html("重复密码不能为空！").css("color","red");
		       return false;
		   }else{
			   $("#ssp2").html("");
		      return true;
		   }
		   }
	   if(name=="code"){
		   if($.trim(value)==""){
		   //不符合添加错误提示信息
			   $("#ssv").html("验证码不能为空！").css("color","red");
		       return false;
		   }else{
			   $("#ssv").html("");
		      return true;
		   }
		   }
};	  

function regist(){
	//密码最少6位，包括至少1个大写字母，1个小写字母，1个数字
//	var pattern=/(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])/;
	//获取页面中的数据
	var loginName=$("form #inputName").val();
	var password=$("form #inputPassword").val();
	var password2=$("form #inputPassword2").val();
    valiImg2=$("form #valiImg2").val();
    //前端验证两次密码是否一致
	if(password!=password2){
		$("#sspp").html("两次密码不一致,重新输入！").css("color","red");
		return false;
	}
	//前端验证验证码是否为空
    if(valiImg2==null){       	  
    	$("#ssv").html("验证码不能为空！").css("color","red");
  	  return false;
    } 
//    var regex = new RegExp(pattern);
//    var flag01 = regex.test(password);
   
    //前端验证密码格式是否正确
//    if(!flag01){
//    	$("#sspp").text("密码格式不正确！").css("color","red");
//
//    	 return false;
//    }
	//alert(loginName+" "+password+" "+password2);
	if(flag){
	//把获取的数据异步提交给服务端Controller
	$.ajax({
		url:"user/register",
		type:"post",
		data:{"username":loginName,"password":password},
		dataType:"json",
		success:function(map){
			//后端验证用户是否已存在
			if(map.res==1){
				window.location.href="login";
			}else {
				$("#sss").html("用户名或密码错误！").css("color","red");
			}
			//alert(result.message);
		},
		error:function(){
			alert("注册失败！");
		}
	})};
	return false;
}
	
