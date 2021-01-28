var findC;
$(function(){
	findC=getCookie("loginName2");
    
	var flag = autoLogin(findC);
	if(flag=="true"){
		return false;
	}
	//从cookie中查找指定key的cookie的值
	//$("form #inputName").val(findC);
	//是否记住用户名
	isRememberName();
	//在login.html中的登录表单添加submit事件
	$("form").submit(function(){
		return login();
		//return false是终止页面中的表单的提交按钮提交
		//return true用页面的提交按钮提交表单，而不是用
	});
	// 为input添加失去焦点事件
	$("input[name='username']").blur(function(){
		return checkNull("username");
	});
	$("input[name='password']").blur(function(){
		return checkNull("password");
	});
});
//记住用户名
function isRememberName(){
	findA=getCookie("loginAddress");
	if(findA!=''&& findA.length>0){
		$("#inputName").val(findA);
		if(getCookie("loginType")=="remember"){
			//alert("true")
			
		}
	}
}
		
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
};	  
//30天自动登录勾选后自动跳转到首页
function autoLogin(autoLogin){
	 if(autoLogin!=""){
		 window.location.href="index";
	 }
	 return true;
};
function login(){
	//获取页面中的数据
	var loginName=$("form #inputName").val();
	alert("loginName:"+loginName)
	var password=$("form #inputPassword").val();
	alert("password:"+password)
	var remember=$("#left").get(0).checked;
	alert("remember:"+remember)
	var remember2=$("#right").get(0).checked;
	alert("remember2:"+remember2)
	//前端判断用户名和密码
	if(loginName==null || password==null ||  $.trim(loginName)=='' || $.trim(password)=='' ){
		$("#ssp").html("用户名或密码不能为空！").css("color","red");
		return false;
	}
	//alert(loginName+" "+password);
	
	//把获取的数据异步提交给服务端Controller
	$.ajax({
		url:"user/login",//user/login
		type:"post",
		data:{"loginName":loginName,"password":password},
		dataType:"json",
		success:function(map){
		alert("map:"+map)
			if(map.res==1){
				window.location.href="index";
				//判断30天免登录是否勾选
				if(remember2){
					//将30天免登录添加到cookie中
					addCookie("loginName2",loginName,24*7);
				}
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
	});
	return false;
}