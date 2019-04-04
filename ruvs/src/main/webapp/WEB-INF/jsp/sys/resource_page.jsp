<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">
			<span><input type="text" class="easyui-textbox" name="like:String:name" data-options="prompt:'名称'"></span> 
			<span><input type="text" class="easyui-combobox" name="like:String:parent.id" 
				data-options="prompt:'上级资源',url:'combo/Resource/id/name.do?_:String:parent.id='" ></span> 
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'name',sortable:true" style="width:90px">资源名</th>
					<th data-options="field:'parent',sortable:true,formatter:function(v){
						if(v&&v.name){
							return v.name;
						}else{
							return '';
						}
					}" style="width:90px">上级资源</th>
					<th data-options="field:'type',sortable:true,formatter:function(v,r){
							if(v==1){
								return '模块';
							}else if(v==2){
								return '菜单';
							}else if(v==3){
								return '按钮';
							}else {
								return '未知';
							}
					
					}" style="width:90px">类型</th>
					<th data-options="field:'url'" style="width:90px">连接</th>
					<th data-options="field:'icon'" style="width:90px">图标</th>
					<th data-options="field:'sort',sortable:true" style="width:90px">排序</th>
					<th data-options="field:'status',sortable:true,formatter:function(v,r){
						if(v==0){
							return '正常';
						}else if(v==-1){
							return '禁用';
						}else{
							return '未知';
						}
					}" style="width:90px">状态</th>
					<th data-options="field:'editUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">编辑人</th>
					<th data-options="field:'editTime',sortable:true" style="width:90px">编辑时间</th>
					<th data-options="field:'createUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">创建人</th>
					<th data-options="field:'createTime',sortable:true" style="width:90px">创建时间</th>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="sys/resource";
		
	</script>
	<%@ include file="/WEB-INF/jsp/includes/footer.page.jsp"%>