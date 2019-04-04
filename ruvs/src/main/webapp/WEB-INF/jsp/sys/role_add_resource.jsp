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
	<form id="_form"   method="POST" action="sys/role/saveResources.do" style="padding:20px 30px;">
			<ul id="config_res" data-options="method:'get',animate:true,checkbox:true"></ul>
	</form>
	<script type="text/javascript">
	
		$("#config_res").tree({
			url:"sys/role/resource_tree.do?roleId="+_roleId,
		});
		function __save(){
			var nodes = $('#config_res').tree('getChecked', ['checked', 'indeterminate']);
			var ids=[];
			for(var i=0; i<nodes.length; i++){
				ids.push(nodes[i].id);
			}
			
			$.ajax({
				method:'post',
				url : "sys/role/saveResources.do",
				data : {roleId:_roleId,resourceIds:ids},
				success : function(resu) {
					if (resu.code == 200) {
						_data_grid.datagrid("load");
						_dialog1.dialog("close");
					}
					top.alert(resu.msg);
				},
				traditional : true,
				dateType : "json"
			});
		}
		function __clear(){
			var _tree=$('#config_res');
			var nodes = _tree.tree('getChecked');
			for(var i=0;i<nodes.length;i++){
				_tree.tree("uncheck",nodes[i].target);
			}
		}
	</script>
</body>
</html>
