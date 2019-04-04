<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/jstl.let.jsp"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>${siteName}</title>
	<meta http-equiv="Cache-Control" content="max-age=7200" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit" />
	<meta http-equiv="expires" content="0">

<%@ include file="/WEB-INF/jsp/includes/js.let.jsp"%>
<link rel="stylesheet" type="text/css" href="static/css/common.css">
<link rel="stylesheet" type="text/css" href="static/css/index.css">
<link rel="stylesheet" type="text/css" href="static/js/libs/font-awesome-4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="static/js/libs/jquery.cookie.js"></script>
<script type="text/javascript" src="static/js/common.js"></script>
<script type="text/javascript" src="static/js/index.js"></script>

</head>