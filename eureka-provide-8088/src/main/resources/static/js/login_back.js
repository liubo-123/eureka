var flag='';
$(function(){
	//在login_back.html中的登录表单添加submit事件
//	$("form").click(function(){
//		return loginBack();
//		//return false是终止页面中的表单的提交按钮提交
//		//return true用页面的提交按钮提交表单，而不是用
//	});
	// 为input添加失去焦点事件
	$("input[name='username']").blur(function(){
		return checkNull("username");
	});
	$("input[name='password']").blur(function(){
		return checkNull("password");
	});
	$("input[name='code']").blur(function(){
		return checkNull("code");
	});
	//为验证码添加失去焦点事件
	$("input[name='code']").blur(function(){
		//return checkCode("code");
	});
	//为验证码动态实时点击获取
	$("#valiImg").click(function(){
		var time=new Date().getTime();
		var url="user/doGet1?time="+time;
		$(this).attr("src",url);
	});
//	$("#login_Name").submit(function(){
//			alert("login")
//		 var flag = loginBack();
//		 return flag; 
//	});
});
function checkCode(name){
    valiImg2=$("input[name='"+name+"']").val();
	$.ajax({
		url:"user/doGetBack/valiName/"+valiImg2,//user/login
		type:"post",
		async: false,
		dataType:"json",
		success:function(result){
			if(result.status==1){
				//alert(result.message+"....");
				flag=true;
			}else{
				$("#sss").html("验证码错误,重新输入！").css("color","red");
			flag = false;
			}
		},
		error:function(){
			//alert("登录失败");
			//flag=false;
		}
	});
};	
function checkNull(name){
	   //获取输入框的值
	   var value=$("input[name='"+name+"']").val();
	   //判断是否为空
	   if(name=="username"){
	   if($.trim(value)==""){
	   //不符合添加错误提示信息
		   $("#ssu").html("用户名不能为空！").css("color","red");
	       return false;
	   }else{
		   $("#ssu").html("");
	      return true;
	   }
	   }
	   if(name=="password"){
		   if($.trim(value)==""){
		   //不符合添加错误提示信息
			   $("#ssp").html("密码不能为空！").css("color","red");
		       return false;
		   }else{
			   $("#ssp").html("");
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
function loginBack(){
	//获取页面中的数据
	var loginName=$("form #inputName").val();
	var password=$("form #inputPassword").val();
	
	//前端判断用户名和密码
	if(loginName==null || password==null ||  $.trim(loginName)=='' || $.trim(password)=='' ){
		$("#ssp").html("用户名或密码不能为空！").css("color","red");
		return false;
	}
	//alert(loginName+" "+password);
	checkCode("code");
	//alert(flag);
   if(flag){
	//把获取的数据异步提交给服务端Controller
	   
	$.ajax({
		url:"user/loginBack/name/"+loginName+"/password/"+password,//user/login
		type:"post",
		dataType:"json",
		async: false,
		success:function(result){
			if(result.status==1){
				window.location.href="index02.html";
				//将用户名添加到cookie中
				addCookie("loginAddress",loginName);
				//alert(getCookie("loginAddress"))
			}else{
				//后台判断用户名或密码是否正确，返回给用户
				$("#sss").html("用户名或密码错误,重新输入！").css("color","red");
			}
		},
		error:function(){
			//后台异常返回信息
			alert("登录失败！");
		}
	})};
	return false;
}