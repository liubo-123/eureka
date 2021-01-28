var findC;
$(function (){
	//alert(getCookie("loginAddress"))
   findC=getCookie("loginName2");
   autoLogin(findC);
   $("#logout").click(function(){
	   logout(findC);
   });
 $("#se").click(function(){
 		return bu();
 		//return false是终止页面中的表单的提交按钮提交
 		//return true用页面的提交按钮提交表单，而不是用
 	});
});

function bu(){
	window.location.href="search";
};
//注销
function logout(deleteC){
	delCookie("loginName2");
};
function autoLogin(autoLogin){
	// 带cookie的访问
	// 或者带有记住用户名的cookie
	findA = getCookie("loginAddress");
	//alert(findA);
	 if(autoLogin!=""){
		$("#login_groups").html(`<li><a>${autoLogin}</a></li><li id="logout" class="pull-left">
				<a href="login">注销</a>
				</li>`);
	 }else if(findA!=""){
		 $("#login_groups").html(`<li><a>${findA}</a></li><li id="logout" class="pull-left">
					<a href="login">注销</a>
					</li>`);
	 }else{
		 $("#login_id").html(`<li class="pull-left">
							<a href="register">注册</a>
						</li>`)
		 $("#login_id2").html(`<li class="pull-left">
			<a href="login">登录</a>
		</li>`)
	 }
};