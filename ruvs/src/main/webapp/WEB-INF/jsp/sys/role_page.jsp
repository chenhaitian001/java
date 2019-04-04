<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">

			<span><input type="text" class="easyui-textbox" name="like:String:name" data-options="prompt:'名称'"></span> 
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'name',sortable:true" style="width:90px">登录名</th>
					<th data-options="field:'description'" style="width:90px">描述</th>
					<th data-options="field:'null',formatter:_configResource" style="width:90px">配置资源</th>
					<th data-options="field:'editUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">编辑人</th>
					<th data-options="field:'editTime',sortable:true" style="width:90px">编辑时间</th>
					<th data-options="field:'createUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">创建人</th>
					<th data-options="field:'createTime',sortable:true" style="width:90px">创建时间</th>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="sys/role";
		function _configResource(v,r){
			return "<a href='javascript:open1(\"配置资源\",\"ui/sys/role_add_resource.do\");var _roleId=\""+r.id+"\";'>资源配置</a>";
		}
	</script>
	<%@ include file="/WEB-INF/jsp/includes/footer.page.jsp"%>