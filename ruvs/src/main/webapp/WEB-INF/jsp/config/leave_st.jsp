<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area" >
			<span><input type="text" id ="id1" class="easyui-textbox" name="like:String:name" data-options="prompt:'学生姓名'"></span>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="newsearch()">查找</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" class="easyui-datagrid" url ="config/newstudent/findLeaveST.do" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'name',sortable:true" style="width:90px">姓名</th>
					<th data-options="field:'clazz',sortable:true,formatter:function(v,r){
					    return r.clazz?r.clazz.name:'';
					}" style="width:90px">班级</th>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="config/student";
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
        
        function newsearch(){
   		 $("#_data_grid").datagrid({
				url : 'config/newstudent/findLeaveST.do',
				queryParams : {
					p1 : $("#id1").val(),
				}
			});
   	    }
        
		
	</script>
