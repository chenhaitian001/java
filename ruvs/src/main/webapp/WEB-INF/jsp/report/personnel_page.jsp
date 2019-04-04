<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">
			<%--<span><input type="text" id="year_selected" style="width:100px"  name="_:String:year" class="easyui-combobox" 
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
			--%><span><input type="text" class="easyui-textbox" name="like:String:area" data-options="prompt:'地市'"></span> 
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
			
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="_excel()">导出Excel</a>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="print()">打印</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="url:'report/personnel/findPaging.do',method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'jobnum',sortable:true" style="width:90px">工号</th>
					<th data-options="field:'name',sortable:true" style="width:90px">员工姓名</th>
					<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
					<th data-options="field:'location',sortable:true" style="width:90px">区县</th>
					<th data-options="field:'duty',sortable:true" style="width:90px">职位</th>
					<th data-options="field:'status',sortable:true" style="width:90px">状态</th>
					<th data-options="field:'hiredate',sortable:true" style="width:90px">入职时间</th>
					<th data-options="field:'insertTime',sortable:true" style="width:90px">统计时间</th>
				</tr>
			</thead>
		</table>
	</div>


	<script type="text/javascript">

		var _data_grid = $("#_data_grid");


		_data_grid.datagrid({
			rownumbers : true,
			striped : true,
			sortName : "hiredate",
			sortOrder : "desc",
			pagination:true

		});
		_data_grid.datagrid("getPager").pagination("refresh",{total:0});



		setTimeout(function() {
			$("body").layout("panel", "north").panel("resize", {
				"height" : $("#search_area").height() + 10
			});
			$("body").layout("resize");
			

			var month=new Date().getMonth();
			
			$("#month_selected").combobox("setValue",month>9?""+month:"0"+month);

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

		function _excel() {
			var param = "";
			$.each($("#search_area input[name]"), function(i, n) {
				n = $(n);
				if(param.length>0){
					param+="&";
				}
				param+=n.attr('name');
				param+="=";
				param+= n.val();
			});
			window.open("report/personnel/findPaging_excel.do?"+param);
		}
		
	</script>
</body>
</html>