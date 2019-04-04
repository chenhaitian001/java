<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();   
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>鹤诺科技考勤平台</title>
<link href="sysadmin/themes/css/login.css" rel="stylesheet"
	type="text/css" />
<script src="assets/js/jquery.min.js"></script>	
<script language="JavaScript">
(function (doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            if(clientWidth>=640){
                docEl.style.fontSize = '100px';
            }else{
                docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
            }
        };

    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);

function check(){
	var msg="${request.msg}";
	if(msg!=""){
	alert(msg);
	}
}
function autoLoad(){
		//document.loginForm.submit();
} 

function ConcelInput()
{
	var s1=document.getElementById("loginId");
	var s2=document.getElementById("loginPass");
	s1.value="";
	s2.value="";
}

function submitForm()
{
	if(checkform()){
		document.loginForm.submit();
	}
}

function  checkform()   
{   
	var loginId=document.getElementById("loginId").value;
	var loginPass=document.getElementById("loginPass").value;
//	var input_verifyCode=document.getElementById("input_verifyCode").value;
	if(loginId==""){
		alert('请输入账号');
		document.getElementById("loginId").focus();
		return false;
	}
	if(loginPass==""){
		alert('请输入密码');
		document.getElementById("loginPass").focus();
		return false;
	}
	/*if(input_verifyCode==""){
		alert('请输入验证码');
		document.getElementById("input_verifyCode").focus();
		return false;
	}*/
	return true;
}

//回车登陆
function enterSubmit(e)  
{  
	 if (window.event)       
	 {         
	  keyCode=event.keyCode;      
	}else  
	{     
		keyCode=e.which; 	 /*ff support e.which*/   
	}        
	 if (keyCode==13)    
	 {        
		 submitForm();
	 } 
}  

</script>


</head>
<body onLoad="check();autoLoad();">

	<div class="content">
		<div id="login">
			<div id="login_header">
				<h1 class="login_logo">
					<a href="###"><img
						src="sysadmin/images/login/logo.png" width="260" /></a>
				</h1>
				
			</div>
			<div id="login_content">
				<div class="loginForm">
					<h2>账号注册</h2>
					<form  id="loginForm" name="loginForm"  action="login/checkLogin.do" method="post" >
						<p class="margin-bm20">
							
							<input name="user.loginId" id="loginId" type="text" size="20" placeholder="学生姓名" 
								class="login_input" value="" onkeydown="enterSubmit(event);" />
							<script type="text/javascript">document.getElementById("loginId").focus();</script>
						</p>
						<p class="margin-bm20">
							
							<input name="user.password" id="loginPass" type="text" placeholder="所属班级"
								size="20" class="login_input" value=""
								onkeydown="enterSubmit(event);" />
							<%-- <div class="forget margin-bm20"><span><a href="#">忘记密码？</a></span><span><a href="#">登陆失败</a></span></div> --%>
						</p>
						<p class="margin-bm20">
							
							<input name="user.password" id="loginPass" type="text" placeholder="识别码"
								size="20" class="login_input" value=""
								onkeydown="enterSubmit(event);" />
							<%-- <div class="forget margin-bm20"><span><a href="#">忘记密码？</a></span><span><a href="#">登陆失败</a></span></div> --%>
						</p>
						<p class="margin-bm20">
							
							<input name="user.password" id="loginPass" type="text" placeholder="所属关系"
								size="20" class="login_input" value=""
								onkeydown="enterSubmit(event);" />
							<%-- <div class="forget margin-bm20"><span><a href="#">忘记密码？</a></span><span><a href="#">登陆失败</a></span></div> --%>
						</p>
						<p class="margin-bm20">
							
							<input name="user.password" id="loginPass" type="text" placeholder="家长手机号码"
								size="20" class="login_input" value=""
								onkeydown="enterSubmit(event);" />
							<%-- <div class="forget margin-bm20"><span><a href="#">忘记密码？</a></span><span><a href="#">登陆失败</a></span></div> --%>
						</p>
						
						<p>
							<input class="sub" type="button" value="注册"
								onclick="submitForm()" />
						</p>
					<form>
				</div>
				<div class="cr"></div>
			</div>
		</div>
		<div id="login_footer">
			<p>鹤诺科技版权所有&copy;&nbsp;2018
			<p>
		</div>
	</div>

</body>
</html>

