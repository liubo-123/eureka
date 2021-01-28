$(function(){

	//在login.html中的登录表单添加submit事件
	$("#button").click(function(){
		return bt();
		//return false是终止页面中的表单的提交按钮提交
		//return true用页面的提交按钮提交表单，而不是用
	});
		$("#button1").click(function(){
    		return bt1();
    		//return false是终止页面中的表单的提交按钮提交
    		//return true用页面的提交按钮提交表单，而不是用
    	});

});
/*
function bt(){
	//获取页面中的数据
	var name=$("#name").val();
	alert("name:"+name)
//	//前端判断用户名和密码
//	if(name==null ||  $.trim(name)==''  ){
//		$("#ssu").html("查询内容不能为空").css("color","red");
//		return false;
//	}
	//把获取的数据异步提交给服务端Controller
	$.ajax({
		url:"search/product",
		type:"get",
		data:{"name":name},
		dataType:"json",
		success:function(map){
		alert("map:"+map)
		ESInfo= JSON.stringify(map.o)
		alert("ESInfo===="+ESInfo )
		},
		error:function(){
			//后台异常返回信息
			alert("系统异常！");
		}

	});
	return false;
}
*/
