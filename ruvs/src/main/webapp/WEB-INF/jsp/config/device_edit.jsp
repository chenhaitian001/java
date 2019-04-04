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
		<input type="hidden" name="id">
		<ul class="edit_area">
			<li>
				<span class="text">安装位置</span>
				<span class="value"><input type="text" name="location" class="easyui-textbox"
										   data-options="required:true,validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度2-50个字符</span>
			</li>
			<li class="error" id="locationError"></li>


			<%--<li>
				<span class="text">IP地址</span>
				<span class="value"><input type="text" name="ip" class="easyui-textbox"
										   data-options="required:true,validType:'length[7,15]'"/></span>
				<span class="rule"><span class="red">*</span>长度2-15字符</span>
			</li>
			<li class="error" id="ipError"></li>--%>


			<li>
				<span class="text">SN号</span>
				<span class="value"><input type="text" name="sn" class="easyui-textbox"
										   data-options="required:true,validType:'length[2,100]'"/></span>
				<span class="rule"><span class="red">*</span>长度2-50个字符</span>
			</li>
			<li class="error" id="snError"></li>

			<li>
				<span class="text">描&nbsp;述</span>
				<span class="value">
					<textarea name="description" class="easyui-textbox" data-options="multiline:true"></textarea>
				</span>
				<span class="rule">长度1-50个字符</span>
			</li>
		</ul>
	</form>
	
</body>
</html>
