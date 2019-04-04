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
		<input name="id" type="hidden"/>
		<ul class="edit_area">
		
			<li>
				<span class="text">角&nbsp;色</span>
				<span class="value"><input type="text" name="role.id" class="easyui-combobox" 
					data-options="url:'combo/Role/id/name.do',editable:false,required:true"/></span>
				<span class="rule"><span class="red">*</span>请选择</span>
			</li>
			<li>
				<span class="text">登录名</span>
				<span class="value"><input type="text" name="loginName" class="easyui-textbox"
					data-options="required:true,validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度2-50个字符</span>
			</li>
			<li class="error" id="loginNameError"></li>
			<li>
				<span class="text">姓&nbsp;名</span>
				<span class="value"><input type="text" name="showName" class="easyui-textbox" 
					data-options="required:true,validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度2-50个字符</span>
			</li>
			<li class="error"></li>
			<li>
				<span class="text">手机号</span>
				<span class="value"><input type="text" name="phone" class="easyui-textbox" 
					data-options="validType:'mobile'"/></span>
				<span class="rule"><span  class="red">&nbsp;</span>格式如：13912345678</span>
			</li>
			<li class="error" id="phoneError"></li>
			<li>
				<span class="text">Email</span>
				<span class="value"><input type="text" name="email" class="easyui-textbox" 
					data-options="validType:'email'"/></span>
				<span class="rule"><span  class="red">&nbsp;</span>格式如：dx@topnetwork.com.cn</span>
			</li>
			<li class="error" id="emailError"></li>
		</ul>
	</form>

</body>
</html>
