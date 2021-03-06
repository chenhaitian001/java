<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">
			<span><input type="text" id="year_selected" style="width:100px"  name="_:String:year" class="easyui-combobox" 
			data-options="data:[{value:'2016',text:'2016',selected:true},
				{value:'2017',text:'2017'},
				{value:'2018',text:'2018'},
				{value:'2019',text:'2019'},
				{value:'2020',text:'2020'}]" name="like:String:name" /> </span> 
				
			<span><input type="text" id="month_selected" style="width:100px" name="_:String:month"  class="easyui-combobox" 
			data-options="data:[{value:'01',text:'01',selected:true},
				{value:'02',text:'02'},
				{value:'03',text:'03'},
				{value:'04',text:'04'},
				{value:'05',text:'05'},
				{value:'06',text:'06'},
				{value:'07',text:'07'},
				{value:'08',text:'08'},
				{value:'09',text:'09'},
				{value:'10',text:'10'},
				{value:'11',text:'11'},
				{value:'12',text:'12'}]" name="like:String:name" /> </span> 
			<span><input type="text" class="easyui-textbox" name="like:String:area" data-options="prompt:'名称'"></span> 
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="_excel()">导出Excel</a>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="print()">打印</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
					<th data-options="field:'beLate',sortable:true" style="width:90px">迟到</th>
					<th data-options="field:'none',formatter:function(v,r){
							return ((r.beLate*1.0)/r.size*100).toFixed(2);
						}" style="width:90px">迟到占比(%)</th>
					<th data-options="field:'leaveEarly',sortable:true" style="width:90px">早退</th>
					<th data-options="field:'none1',formatter:function(v,r){
							return ((r.leaveEarly*1.0)/r.size*100).toFixed(2);
						}" style="width:90px">早退占比(%)</th>
					<th data-options="field:'absentee',sortable:true" style="width:90px">旷工</th>
					<th data-options="field:'none2',formatter:function(v,r){
							return ((r.absentee*1.0)/r.size*100).toFixed(2);
						}" style="width:90px">旷工占比(%)</th>
					<%--<th data-options="field:'traps',sortable:true" style="width:90px">补漏</th>
					<th data-options="field:'overtime',sortable:true" style="width:90px">加班</th>
					<th data-options="field:'sickLeave',sortable:true" style="width:90px">病假</th>
					<th data-options="field:'casualLeave',sortable:true" style="width:90px">事假</th>
					<th data-options="field:'annualLeave',sortable:true" style="width:90px">年假</th>
					<th data-options="field:'evection',sortable:true" style="width:90px">出差</th>
				--%>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target = "report/personnel";
	</script>

	<script type="text/javascript">
		var _data_grid = $("#_data_grid");

		_data_grid.datagrid({
			rownumbers : true,
			striped : true,
			sortName : "be_late",
			sortOrder : "desc"

		});

		setTimeout(function() {
			$("body").layout("panel", "north").panel("resize", {
				"height" : $("#search_area").height() + 10
			});
			$("body").layout("resize");
			

			var month=new Date().getMonth();
			
			$("#month_selected").combobox("setValue",month>9?""+month:"0"+month);
			_data_grid.datagrid("options").url='report/attendance/dishi.do';
			_search();
			
		}, 100);

		function _search() {
			var param = {};
			$.each($("#search_area input[name]"), function(i, n) {
				n = $(n);
				param[n.attr('name')] = n.val();
			});
			_data_grid.datagrid("load", param);
		}
		
	</script>
</body>
</html>