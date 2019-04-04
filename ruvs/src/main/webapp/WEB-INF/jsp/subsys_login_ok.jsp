<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="static/js/libs/jquery-easyui-1.4.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/js/libs/jquery-easyui-1.4.5/themes/icon.css">
	<script type="text/javascript" src="static/js/libs/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/libs/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>


	<style type="text/css">
	html,body{
		height: 100%;
	}
	body{
		background: url(static/img/login/login_bg.png);
	}
	</style>
	<script type="text/javascript">
	
		$(function(){

			if("${status}"=="500"){
				$("#_info").html("登录失败<br/>${msg}");
				setTimeout(function(){
					location.href="subsys_login.jsp";
				}, 2000);
				return;
			}
			
			var url="${url}";
			var login_name="${login_name}";
			var login_passwd="${login_passwd}";
			
			$("#login_iframe").attr("src",url+"/login.whtml?user.userName="+encodeURI(login_name)+"&user.password="+encodeURI(login_passwd));
			setTimeout(function(){
				location.href=url+"/index.html";
			}, 2000);
		});
	</script>
</head>
<body>
<iframe frameborder="0" style="display: none" id="login_iframe"></iframe>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<div id="_info" style="font: 20px bolder;text-align: center;width: 100%">
		页面跳转中。。。
	</div>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
<div style="text-align:center;padding-top:8px;font-size:13px;font-weight:bold">鹤诺科技（北京）有限公司</div>

</body>
</html>