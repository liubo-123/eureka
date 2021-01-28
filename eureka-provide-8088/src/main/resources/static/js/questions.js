
//@sourceURL=questions.js

var questionId;


$(function(){
	//当第一次点击用户管理时,带有模糊条件的分页查询,查询时第一页
	findQuestionsByPage(1);
	//给用户的模糊搜索添加click事件
	$("#search_Question_ahead_button").click(function(){
		//alert("搜索");
		findQuestionsByPage(1);

	});
	//表单返回键事件
	$("#answer_Cancel").click(function(){
		CloseDiv("MyDiv_answerQuestion","fade_answerQuestion");
		
	});
	
	
	
});

//弹出隐藏层
function ShowDiv(show_div,bg_div){
	var scrollHeight = document.body.scrollHeight; //文档高度
	document.getElementById(bg_div).style.height=scrollHeight+'px';
	document.getElementById(show_div).style.display='block';
	document.getElementById(bg_div).style.display='block';
	return true;
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
	//document.getElementById("label").value = '';
	document.getElementById(show_div).style.display='none';
	document.getElementById(bg_div).style.display='none';
};
//查询所有数据库真实角色信息
function findAllGallerys(){
	$.ajax({
		url:"qgallery/findAllGallerys",
		type:"get",
		dataType:"json",
		success:function(result){
			//给新增标签页中的拉列表添加角色选项
			if(result.status==1){
				var gallerys=result.data;
				//赋值给全局变量
				galleries=gallerys;

				$("#allGallerys").html('');

				$(gallerys).each(function(index,gallery){
					var select_option="<option value='"+gallery.cId+"'>"+gallery.cName+"</option>";

					//alert("option："+select_option);
					$("#allGallerys").append(select_option);
				});

			}else if(result.status==0){
				//给提示信息
				$("#allGallerys").append("<option>没有类别数据,不能添加题目</option>");
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}


function trans_answer(data){
	document.getElementById("MyDiv_answerQuestion").style.display='block';
	//ShowDiv("MyDiv_answerQuestion","fade_answerQuestion");
	$("#write_answer").text(data);
	
}
function trans_question(name){
	
	$("#lay_question_black span").text(name);
	

}


//查询指定页号那页数据
function findQuestionsByPage(currentPage){
	//alert(currentPage);
	//获取模糊关键字
	var questionKeyword=$("#search_Question_ahead").val();

	if(questionKeyword==''){
		questionKeyword="undefined";
	}

	$.ajax({
		url:"question/find",
		type:"get",
		data:{
			"currentPage":currentPage,
			"questionKeyword":questionKeyword
		},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				//顺利获取到数据库的数据,并正确返回js端
				var page=result.data;
				var questions=page.data;

				//清空表格
				$("#question_ahead_tbody").html("");
				//alert("开始清表格");
				$(questions).each(function(index,question){

					/*var tr='<tr id="tr_'+question.qId+'">'+*/
					var tr='<tr onmouseover="this.style.backgroundColor=\''+"#ffff66"+'\';"'+
					'onmouseout="this.style.backgroundColor=\''+"#d4e3e5"+'\';"'+
					'id="tr_'+question.qId+'">'+
					'<td class="td_small">'+(index+1)+'</td>'+
					'<td class="td_small">'+question.qCategory+'</td>'+
					'<td style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;"><a href="#lay_question_black" onclick="trans_question(\''+question.qName+'\')" >'+question.qName+'</a></td>'+
					
					'<td><a  onclick="trans_answer(\''+question.qData+'\')">点此查看参考代码</a></td>'+
					'</tr>';

					//alert(tr);
					$("#question_ahead_tbody").append(tr);
				});	

				//清空分页条
				$("#question_ahead_Pages").html("");
				//alert("开始分页");
				if(page.totalPage>1){
					//添加分页条
					var previousPage='<li>'+
					'<a href="javascript:findQuestionsByPage('+page.previousPage+')" >'+
					'<<'+
					'</a>'+
					'</li>';
					$("#question_ahead_Pages").append(previousPage);
					$(page.nums).each(function(n,value){
						var middlePage='<li><a href="javascript:findQuestionsByPage('+value+')">'+value+'</a></li>';
						$("#question_ahead_Pages").append(middlePage);
					});

					var nextPage='<li>'+
					'<a href="javascript:findQuestionsByPage('+page.nextPage+')" >'+
					'>>'+
					'</a>'+
					'</li>';
					$("#question_ahead_Pages").append(nextPage);

				}

			}		
		},
		error:function(){
			alert("添加失败了");
		}
	});	

}


