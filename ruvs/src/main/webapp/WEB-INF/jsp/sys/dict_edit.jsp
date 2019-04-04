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
		<input type="hidden" name="id">
		<ul class="edit_area">
			<li>
				<span class="text">上级菜单</span>
				<span class="value">
					<input type="text" name="parent.id" data-options="editable:false"/>
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
	<script type="text/javascript">

		$("input[name='parent.id']").combobox({url:"combo/Dict/id/name.do?!_:String:id="+_edit_id});
	</script>
</body>
</html>
