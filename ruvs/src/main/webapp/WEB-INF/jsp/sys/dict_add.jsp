<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

</head>

<body>
	<form id="_form"   method="POST">
		<ul class="edit_area">
			<li>
				<span class="text">上级菜单</span>
				<span class="value">
					<input type="text" name="parent.id" class="easyui-combobox" 
					data-options="url:'combo/Dict/id/name.do',editable:false"/>
				</span>
				<span class="rule"><span class="red">&nbsp;</span></span>
			</li>
			<li>
				<span class="text">名&nbsp;称</span>
				<span class="value"><input type="text" name="name" class="easyui-textbox"  
					data-options="required:true,validType:'length[1,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度1-50个字符</span>
			</li>
			<li class="error" id="nameError"></li>
			<li>
				<span class="text">值</span>
				<span class="value"><input type="text" name="value" class="easyui-textbox"  
					data-options="required:true,validType:'length[1,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度1-50个字符</span>
			</li>
		</ul>
	</form>
	
</body>
</html>
