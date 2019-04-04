<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area" >
			<span><input type="text" class="easyui-textbox" name="like:String:name" data-options="prompt:'班级名'"></span>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'name',sortable:true" style="width:90px">班级名</th>
					<th data-options="field:'null',sortable:true,formatter:function(v,r){
						if(r.grade){
							return r.grade.name;
						}else{
							return '';
						}
					}" style="width:90px" >年级</th>
					<th data-options="field:'description',sortable:true" style="width:190px">描述</th>
					<th data-options="field:'editUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">编辑人</th>
					<th data-options="field:'editTime',sortable:true" style="width:90px">编辑时间</th>
					<th data-options="field:'createUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">创建人</th>
					<th data-options="field:'createTime',sortable:true" style="width:90px">创建时间</th>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="config/clazz";
		function _configParam(v,r){
			return "<a href='javascript:open1(\"配置参数\",\"ui/config/area_attrbute_add.do\");var _area_id=\""+r.id+"\";'>配置参数</a>";
		}
        function _status(v,r){
            if(v==1){
                return '<span style="color:green;font-size:14px;font-weight: 900;">在线</a>';
            }else{
                return '<span style="color:red;font-size:14px;font-weight: 900;">离线</a>';
            }
        }
	</script>
	<%@ include file="/WEB-INF/jsp/includes/footer.page.jsp"%>