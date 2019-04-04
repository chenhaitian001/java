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

	<link rel="stylesheet" type="text/css" href="static/js/libs/jquery-easyui-1.4.5/themes/material/easyui.css">
	<link rel="stylesheet" type="text/css" href="static/js/libs/jquery-easyui-1.4.5/themes/icon.css">
	<script type="text/javascript" src="static/js/libs/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/libs/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/js/libs/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="static/js/libs/jquery.form.js"></script>

<link rel="stylesheet" type="text/css" href="static/css/common.css">
<script type="text/javascript" src="static/js/libs/jquery.cookie.js"></script>
<script type="text/javascript" src="static/js/common.js"></script>
    <script type="text/javascript">
        var basepath="<%=basePath%>";
    </script>
