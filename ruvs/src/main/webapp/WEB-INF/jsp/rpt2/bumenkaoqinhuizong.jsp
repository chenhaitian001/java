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
							<th rowspan="2" data-options="field:'area',sortable:true" style="width:90px">地市</th>
							<th rowspan="2" data-options="field:'bumen',sortable:true" style="width:90px">单位</th>
							<th rowspan="2" data-options="field:'count',sortable:true" style="width:90px">人数</th>
							<th rowspan="2" data-options="field:'sjcq',sortable:true" style="width:120px">实际出勤(天)</th>
							<th rowspan="2" data-options="field:'ycq',sortable:true" style="width:120px">应出勤(天)</th>
							<th rowspan="2" data-options="field:'cql',sortable:true,formatter:function(v,r){
								if(r.ycq>0){
									return (r.sjcq/r.ycq).toFixed(4);
								}else{
									return '0.000';
								}
							}" style="width:120px">出勤率(%)</th>
							<th colspan="6">扣款项</th>
							<th colspan="3">非扣款项</th>
						</tr>
						<tr>
							<th data-options="field:'cd',sortable:true" style="width:120px">迟到(次)</th>
							<th data-options="field:'zt',sortable:true" style="width:120px">早退(次)</th>
							<th data-options="field:'kg',sortable:true" style="width:120px">旷工(天)</th>
							<th data-options="field:'bj',sortable:true" style="width:120px">病假(天)</th>
							<th data-options="field:'sj',sortable:true" style="width:120px">事假(天)</th>
							<th data-options="field:'nj',sortable:true" style="width:120px">年假(天)</th>
							<th data-options="field:'kqyl',sortable:true" style="width:120px">考勤遗漏(次)</th>
							<th data-options="field:'jbts',sortable:true" style="width:120px">加班天数</th>
							<th data-options="field:'ygcc',sortable:true" style="width:120px">因公出差</th>
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
			_data_grid.datagrid("options").url='rpt2/pageBumenkaoqinhuizong.do';
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
			window.open("rpt2/pageBumenkaoqinhuizong_excel.do?" + param);
		}
	</script>
</body>
</html>