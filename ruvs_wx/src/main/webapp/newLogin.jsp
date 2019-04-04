<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();   
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>登陆</title>
<link rel="stylesheet" href="wx/css/phReset.css">
<link rel="stylesheet" href="wx/css/weui.min.css">
<link rel="stylesheet" href="wx/css/style.css">
<link rel="stylesheet" href="wx/css/Mdate.css">
<script>
	(function(){
		function autoRem(sum){
			var width = window.screen.width; //获取移动端屏幕宽度
			document.documentElement.style.fontSize = width/sum + "px";
		}
		autoRem(15);
	})();
	
	function submitForm()
	{
		if(checkform()){
			document.loginForm.submit();
		}
	}

	function  checkform()   
	{   
		 
		//校验
		var loginId=document.getElementById("loginId").value;
		var Code=document.getElementById("Code").value;
		var Phoneno=document.getElementById("Phoneno").value;
		n = false;//全局变量，以便下面做判断
		 if(loginId==""){
			 document.getElementById("texttt").innerText="* 请填写幼儿姓名!";
			//document.getElementById("loginId").focus();
			return false;
		}
		 if(Code==""){
			 document.getElementById("texttt").innerText="* 请填写身份识别码!";
			//document.getElementById("loginId").focus();
			return false;
		}
		 
		 if(Phoneno==""){
			 document.getElementById("texttt").innerText="* 请填写家长手机号!";
			//document.getElementById("loginId").focus();
			return false;
		}
		 
		 $.ajax({
				url:  "checkCode.do",
				traditional : true,
				async:false, 
				type:'POST',
				dateType : "json",
				data:{
					flag:'10',
					Code:Code,
					phonecode:Phoneno,
					studentName:loginId,
				},
				
				success : function(resu) {
					if (resu.code == 200) {
					 n = true;
					 document.getElementById("texttt").innerText="*恭喜您!注册成功了";
					}else if(resu.code == 250){
						document.getElementById("texttt").innerText="* 手机号被占用,请重新输入手机号!";
						 n = false;
					}else if(resu.code == 300){
						document.getElementById("texttt").innerText="* 不存在的学生姓名!";
						 n = false;
					}else{
					 document.getElementById("texttt").innerText="* 错误的识别码!";
					 n = false;
					}
			    }
		 });
		 if(n) {
	         return true;
	    }else{
	        return false;
	    }
		 
		return true;
	}
</script>
</head>
<body>
<div class="wrapper">
	<div class="loge">
		<img src="wx/images/loge.jpg">
	</div>
	<div class="formCon">
		<form method="post" id="loginForm" name="loginForm" class="signupform" action='checkLogin.do'>
			<input type="hidden" name="oppenid" value="${oppenid }"/>
			<div class="clearfix">
				<span>幼儿姓名 :</span>
				<input id="loginId" name="loginId" type="text" placeholder="姓名" />
			</div>
			<div class="clearfix">
				<span>性别 :</span>
				<input id="sex" name="sex" type="text" placeholder="男" />
			</div>
			<div class="clearfix">
				<span>生日 :</span>
				<input id="birthday" name="birthday" type="text" placeholder="2019-01-03" />
			</div>
			<div class="clearfix">
				<span>识别码 :</span>
				<input id="Code" name="Code" type="text" placeholder="识别码" />
			</div>
			<div class="clearfix">
				<span>关系 :</span>
				<input id="relation" name="relation" type="text" placeholder="爸爸" />
			</div>
			<div class="clearfix">
				<span>手机号 :</span>
				<input id="Phoneno" name="Phoneno" type="text" placeholder="手机号" />
			</div>
			<div class="errorMessage"><i></i><strong  id="texttt"></strong></div>
			<input type="button" onclick="submitForm()" class="registerBtn" value="注册"/>
		</form>
	</div>
	<div id="datePlugin"></div>
</div>
<script src="wx/js/jquery-2.0.0.min.js"></script>
<!-- 表单验证 -->
<script src="wx/js/jquery.validate.min.js"></script>
<!-- 性别关系选择 -->
<script src="https://res.wx.qq.com/open/libs/weuijs/1.1.0/weui.min.js"></script>
<!-- 日期选择插件 -->
<script src="wx/js/iScroll.js"></script>
<script src="wx/js/Mdate.js"></script>
<!-- 自定义 -->
<script src="wx/js/login.js"></script>

<script type="text/javascript">
	$('.wrapper').css({'height':$("body").height()}) //安卓H5页面背景图被键盘挤压
	//点击input标签防止手机弹出字符面板
	$("#gender").focus(function(){
        document.activeElement.blur();
	});
	$("#birthday").focus(function(){
        document.activeElement.blur();
	});
	$("#relationship").focus(function(){
        document.activeElement.blur();
	});

	// 提交表单
	$.validator.setDefaults({
		submitHandler: function() {
			alert("提交事件!"); 
		},
		debug:true  //只验证不提交表单
	}); 

	// 生日日期选择
	new Mdate("birthday", {
		format: "-"
	})

	// 关系
	$('#relation').on('click', function () {
		var that = this;
		weui.picker(
			[
				
				{
					label: '爸爸',
					value: '爸爸'
				},
				{
					label: '妈妈',
					value: '妈妈'
				},
				{
					label: '爷爷',
					value: '爷爷'
				},
				{
					label: '奶奶',
					value: '奶奶'
				},
				{
					label: '姥爷',
					value: '姥爷'
				},
				{
					label: '姥姥',
					value: '姥姥'
				},
				{
					label: '其他',
					value: '其他'
				},
			],
			{
				onConfirm: function (result) {
					$(that).val(result[0].value)
				}
			}
		);
	});

	// 性别
	$('#sex').on('click', function () {
		var that = this;
		weui.picker(
			[
				{
					label: '男',
					value: '男'
				},
				{
					label: '女',
					value: '女'
				},
			],
			{
				onConfirm: function (result) {
					$(that).val(result[0].value)
				}
			}
		);
	});
</script>
</body>
</html>