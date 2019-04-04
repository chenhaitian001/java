<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'west'" style="width:200px;padding:10px;">
		<%@include file="/WEB-INF/jsp/includes/bumen_tree.jsp"%>
	</div>
	<div data-options="region:'center',split:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false"
				style="height:50px;overflow: hidden;">
				<div id="search_area">
					<input type="hidden" id="input_area" name="area" /> 
					<input type="hidden" id="input_bumen_ids" name="bumen_ids">
						
						<input type="text" class="easyui-datebox" name="sdate" value="${sdate }" style="width:100px"> 
						&nbsp;-&nbsp;
						<input type="text" class="easyui-datebox" name="edate" value="${edate }" style="width:100px"> 
						
						<span><input
						type="text" class="easyui-textbox" name="name"
						data-options="prompt:'姓名|工号'"> </span> 
						
						<span><input
						type="text" class="easyui-textbox" name="sbmc"
						data-options="prompt:'设备名称|SN'"> </span> 
						
						 <a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="_search()">查找</a> <a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-redo'" onclick="_excel()">导出Excel</a>
					<a href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-print'" onclick="print()">打印</a>
				</div>
			</div>

			<div data-options="region:'center',border:false">
				<table id="_data_grid"
					data-options="method:'get',border:false,fit:true">
					<thead>
						<tr>
							<th data-options="field:'num',sortable:true" style="width:90px">工号</th>
							<th data-options="field:'name',sortable:true" style="width:90px">姓名</th>
							<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
							<th data-options="field:'bumen',sortable:true" style="width:90px">单位</th>
							<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
							<th data-options="field:'dksj',sortable:true" style="width:120px">打卡时间</th>
							<th data-options="field:'sbmc',sortable:true" style="width:90px">设备名称</th>
							<th data-options="field:'azwz',sortable:true" style="width:90px">设备安装位置</th>
							<th data-options="field:'djzp',sortable:true,formatter:function(v,r){
								return '<img src='+v+' width=96 height=128>';
							}" style="width:90px" >登记照片</th>
							<th data-options="field:'sjzp',sortable:true,formatter:function(v,r){
								return '<img src='+v+' width=96 height=128>';
							}"" style="width:90px">实际照片</th>
							<th data-options="field:'scsj',sortable:true" style="width:120px">上传时间</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		var _data_grid = $("#_data_grid");

		_data_grid.datagrid({
   			 method: 'POST',
			rownumbers : true,
			striped : true,
			sortName : "area",
			sortOrder : "asc",
			pagination : true

		});
		_data_grid.datagrid("getPager").pagination("refresh",{total:0});

		setTimeout(function() {
			$("body").layout("panel", "north").panel("resize", {
				"height" : $("#search_area").height() + 10
			});
			$("body").layout("resize");

			var month = new Date().getMonth();

			$("#month_selected").combobox("setValue",
					month > 9 ? "" + month : "0" + month);
		}, 100);

		function _search() {
			var param = {};
			$.each($("#search_area input[name]"), function(i, n) {
				n = $(n);
				if (n.attr('type') != "radio"
						|| (n.filter(":checked").length > 0)) {
					param[n.attr('name')] = n.val();
				}
			});
			_data_grid.datagrid("options").url='rpt2/pageKadian.do';
			_data_grid.datagrid("load", param);
		}

		function _excel() {
			var param = "";
			$.each($("#search_area input[name]"), function(i, n) {
				n = $(n);

				if (n.attr('type') != "radio"
						|| (n.filter(":checked").length > 0)) {
					if (param.length > 0) {
						param += "&";
					}
					param += n.attr('name');
					param += "=";
					param += n.val();
				}
			});
			window.open("rpt2/pageKadian_excel.do?" + param);
		}
	</script>
</body>
</html>