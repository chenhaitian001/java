<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<form id="_form"   method="POST">
		<ul class="edit_area">
			<li>
				<span class="text">名&nbsp;称</span>
				<span class="value"><input type="text" name="name" class="easyui-textbox" 
					data-options="required:true,validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度1-50个字符</span>
			</li>
			<li class="error" id="nameError"></li>
			<li>
				<span class="text">描&nbsp;述</span>
				<span class="value">
					<textarea name="description"></textarea>
				</span>
				<span class="rule"><span class="red">*</span>长度1-50个字符</span>
			</li>
			<li class="error"></li>
		</ul>
	</form>
	
</body>
</html>
