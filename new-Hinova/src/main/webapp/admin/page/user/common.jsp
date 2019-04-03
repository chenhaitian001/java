<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 整个common.jsp影响着ifream内的样式 -->
	<!-- JSTL -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
    <link rel="stylesheet" href="static/hinova/css/public2.css">
    
      <link rel="stylesheet" href="static/hinova/js/layui/css/layui.css" media="all">  
     
     <!-- <link rel="stylesheet" href="static/hinova/css/layui.css" media="all"> -->
      <script src="static/hinova/js/layui/layui.js"></script> 
      
    <link rel="stylesheet" href="static/hinova/css/page1.css">
    <script src="static/hinova/js/jquery-1.12.1.min.js"></script>
   <!-- 引入manage 的基础css 页面基础控制 删除 添加 刷新js封装 -->
	 <script	src="static/js/houtai/manage_base.js"></script>
	<!--<link href="static/css/houtai/manage_base.css" rel="stylesheet"/> -->
			
	<!-- 强制  高速模式 渲染网页    -->
	<meta NAME=”renderer” content=”webkit”>
	<!-- 强制  高速模式 渲染网页    -->
	
	<link href="static/favicon.ico" rel="shortcut icon" />
	
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link href="static/favicon.ico" rel="shortcut icon" />
	
	<!--添加  vue.js 支持加载-->
	<script src="static/vue/vue.min.js"></script>
	<!--添加  vue.js 支持加载-->
	<script src="static/easy-ui/jquery.easyui.min.js"></script>
</head>
<body>

</body>
</html>
