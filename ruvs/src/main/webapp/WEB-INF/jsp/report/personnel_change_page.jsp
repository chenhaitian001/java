<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false,split:true" style="height:50px">
		<div id="search_area" style="margin:5px 10px;">
			<span><input type="text" class="easyui-textbox" name="like:String:name" data-options="prompt:'名称'"></span> 
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="print()">打印</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'jobnum',sortable:true" style="width:90px">工号</th>
					<th data-options="field:'name',sortable:true" style="width:90px">员工姓名</th>
					<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
					<th data-options="field:'location',sortable:true" style="width:90px">区县</th>
					<th data-options="field:'duty',sortable:true" style="width:90px">在职人数</th>
					<th data-options="field:'status',sortable:true" style="width:90px">入职人数</th>
					<th data-options="field:'status',sortable:true" style="width:90px">离职人数</th>
					<th data-options="field:'insertTime',sortable:true" style="width:90px">统计时间</th>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="report/personnel_change";
		
	</script>
	<%@ include file="/WEB-INF/jsp/includes/report.page.jsp"%>