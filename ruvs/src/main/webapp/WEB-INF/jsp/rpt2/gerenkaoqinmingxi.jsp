<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'west'" style="width:200px;padding:10px;">
		<%@include file="/WEB-INF/jsp/includes/bumen_tree_checkbox.jsp"%>
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
						data-options="prompt:'姓名|工号'" style="width:100px;"> </span> 
						
						
						
						<span><select name="ren_type" class="easyui-combobox">
							<option value="">==请选择==</option>
							<option value="长期职工">长期职工</option>
							<option value="农电用工">农电用工</option>
							<option value="集体企业用工">集体企业用工</option>
							<option value="其他人员">其他人员</option>
						</select></span> 
						
						<span><select name="gds" class="easyui-combobox">
							<option value="">==请选择==</option>
							<option value="gds">供电所</option>
							<option value="ngds">非供电所</option>
						</select></span> 
						
						
						<input type="checkbox" name="noweek" value="1"> 忽略周末
						
						 <a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="if(setBumens())_search()">查找</a> <a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-redo'" onclick="if(setBumens())_excel()">导出Excel</a>
					<a href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-print'" onclick="if(setBumens())print()">打印</a>
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
							<th data-options="field:'bumen_p',sortable:true" style="width:90px">上级单位</th>
							<th data-options="field:'bumen',sortable:true" style="width:90px">单位</th>
							<th data-options="field:'date',sortable:true" style="width:90px">日期</th>
							<th data-options="field:'rqlx',sortable:true" style="width:90px">日期类型</th>
							<th data-options="field:'glbcmc',sortable:true" style="width:120px">规律班次名称</th>
							<th data-options="field:'jbbcmc',sortable:true" style="width:120px">基本班次名称</th>
							<th data-options="field:'sdmc',sortable:true" style="width:120px">时段名称</th>
							<th data-options="field:'sdxx',sortable:true" style="width:90px">时段信息</th>
							<th data-options="field:'sbkd',sortable:true" style="width:120px">上班卡点</th>
							<th data-options="field:'xbkd',sortable:true" style="width:120px">下班卡点</th>
							<th data-options="field:'cd',sortable:true" style="width:120px">迟到(次)</th>
							<th data-options="field:'zt',sortable:true" style="width:120px">早退(次)</th>
							<th data-options="field:'kg',sortable:true" style="width:120px">旷工(天)</th>
							<th data-options="field:'kqyl',sortable:true" style="width:120px">考勤遗漏(次)</th>
							<th data-options="field:'jbts',sortable:true" style="width:120px">加班天数</th>
							<th data-options="field:'bj',sortable:true" style="width:120px">病假(天)</th>
							<th data-options="field:'sj',sortable:true" style="width:120px">事假(天)</th>
							<th data-options="field:'nj',sortable:true" style="width:120px">年假(天)</th>
							<th data-options="field:'ygcc',sortable:true" style="width:120px">因公出差(天)</th>
							<th data-options="field:'notes',sortable:true" style="width:90px">备注</th>
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
				
				if(n.attr('type') == "radio" && !n.get(0).checked){
					return;
				}
				
				// TODO 只处理复选框为单选的情况，多选则会出错
				if(n.attr('type') == "checkbox" && !n.get(0).checked){
					return;
				}

				param[n.attr('name')] = n.val();
			});
			_data_grid.datagrid("options").url='rpt2/pageGerenmingxi.do';
			_data_grid.datagrid("load", param);
		}

		function _excel() {
			var param = "";
			$.each($("#search_area input[name]"), function(i, n) {
				n = $(n);

				if(n.attr('type') == "radio" && !n.get(0).checked){
					return;
				}
				
				// TODO 只处理复选框为单选的情况，多选则会出错
				if(n.attr('type') == "checkbox" && !n.get(0).checked){
					return;
				}

				if (param.length > 0) {
					param += "&";
				}
				param += n.attr('name');
				param += "=";
				param += n.val();
			});
			window.open("rpt2/pageGerenkaoqinmingxi_excel.do?" + param);
		}
	</script>
</body>
</html>