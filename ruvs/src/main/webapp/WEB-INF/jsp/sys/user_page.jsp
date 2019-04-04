<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">

			<span><input type="text" class="easyui-textbox" name="like:String:show_name|login_name" data-options="prompt:'用户名 / 昵称'"></span> 
			<span><input type="text" class="easyui-textbox" name="=:String:phone" data-options="prompt:'手机号'"></span> 
			<span><input type="text" class="easyui-textbox" name="like:String:email" data-options="prompt:'email'"></span>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'loginName',sortable:true" style="width:90px">登录名</th>
					<th data-options="field:'showName',sortable:true" style="width:90px">昵称</th>
					<th data-options="field:'role',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">角色</th>
					<th data-options="field:'phone',sortable:true" style="width:90px">手机号</th>
					<th data-options="field:'email',sortable:true" style="width:90px">email</th>
					<th data-options="field:'editTime',sortable:true" style="width:90px">编辑时间</th>
					<th data-options="field:'createTime',sortable:true" style="width:90px">创建时间</th>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="sys/user";
		
	</script>
	<%@ include file="/WEB-INF/jsp/includes/footer.page.jsp"%>