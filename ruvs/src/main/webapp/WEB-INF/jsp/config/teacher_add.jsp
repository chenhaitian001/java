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
				<span class="text">姓名</span>
				<span class="value"><input type="text" name="name" class="easyui-textbox"
										   data-options="required:true,validType:'length[2,50]'"/></span>
				<span class="rule"><span class="red">*</span>长度2-50个字符</span>
			</li>
			<li class="error" id="nameError"></li>


			<li>
				<span class="text">班级</span>
				<span class="value"><input type="text" name="clazz.id"
										   class="easyui-combobox"
										   data-options="required:true,url:'config/teacher/clazzCombo.do',editable:false"/></span>
				<span class="rule"><span class="red">*</span></span>
			</li>


			<li>
				<span class="text">手机</span>
				<span class="value"><input type="text" name="phone" class="easyui-textbox"
										   data-options="validType:'length[11,11]'"/></span>
				<span class="rule">长度2-50个字符</span>
			</li>
			<li class="error" id="phoneError"></li>


			<li>
				<span class="text">邮箱</span>
				<span class="value"><input type="text" name="email" class="easyui-textbox"
										   data-options="validType:'email'"/></span>
				<span class="rule"></span>
			</li>
			<li class="error" id="emailError"></li>


			<li>
				<span class="text">地址</span>
				<span class="value"><input type="text" name="address" class="easyui-textbox"
										   data-options="validType:'length[2,50]'"/></span>
				<span class="rule">长度2-50个字符</span>
			</li>
			<li class="error" id="addressError"></li>



			<li>
				<span class="text">描&nbsp;述</span>
				<span class="value">
					<textarea name="description" class="easyui-textbox" data-options="multiline:true"></textarea>
				</span>
				<span class="rule">长度1-50个字符</span>
			</li>
			<li class="error" id="descriptionError"></li>
		</ul>
	</form>
	
</body>
</html>
