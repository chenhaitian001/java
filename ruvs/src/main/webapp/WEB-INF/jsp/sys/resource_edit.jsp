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
		<input type="hidden" name="id" />
		<ul class="edit_area">
			<li>
				<span class="text">上级菜单</span>
				<span class="value">
					<input id="parentResource" type="text" name="parent.id" data-options="editable:false"/>
				</span>
				<span class="rule"><span class="red">*</span>长度1-50个字符</span>
			</li>
			<li>
				<span class="text">名&nbsp;称</span>
				<span class="value"><input type="text" name="name" class="easyui-textbox" 
					data-options="required:true,validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度1-50个字符</span>
			</li>
			<li class="error" id="nameError"></li>
			<li>
				<span class="text">类&nbsp;型</span>
				<span class="value">
					<input type="text" name="type"  class="easyui-combobox" data-options="data:[
						{value:1,text:'模块'},
						{value:2,text:'菜单or连接'},
						{value:3,text:'连接or按钮'},
					],required:true,editable:false"/>
				</span>
				<span class="rule"><span class="red">*</span></span>
			</li>
			<li>
				<span class="text">URL</span>
				<span class="value"><input type="text" name="url" class="easyui-textbox" 
					data-options="validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">&nbsp;</span>格式如：ui/sys/resource_page.do</span>
			</li>
			<li>
				<span class="text">图&nbsp;标</span>
				<span class="value"><input type="text" name="icon" class="easyui-textbox" 
					data-options="validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">&nbsp;</span>格式如：icon_demo.png</span>
			</li>
			<li>
				<span class="text">排&nbsp;序</span>
				<span class="value"><input type="text" name="sort" class="easyui-numberbox" 
					data-options="required:true,validType:'integer'"/></span>
				<span class="rule"><span class="red">*</span>整数</span>
			</li>
			<li>
				<span class="text">描&nbsp;述</span>
				<span class="value">
					<textarea name="description"></textarea>
				</span>
				<span class="rule"><span class="red">&nbsp;</span>长度1-50个字符</span>
			</li>
			<li class="error"></li>
		</ul>
	</form>
	<script type="text/javascript">
		$("#parentResource").combobox({url:"combo/Resource/id/name.do?!_:String:id="+_edit_id});
		
	</script>
</body>
</html>
