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
					<input type="hidden" id="input_area" name="area"> 
					<input type="hidden" id="input_bumen_ids" name="bumen_ids"> <span><input
						type="text" class="easyui-textbox" name="name"
						data-options="prompt:'姓名'" style="width:100px"> </span> 
						
					<input type="radio" name="status" value="在职" checked="checked"> 在职
					<input type="radio" name="status" value="离职" > 离职
					
					
						
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
					
						<a href="javascript:void(0)"
						id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="if(setBumens())_search()">查找</a> <a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-redo'" onclick="if(setBumens())_excel()">导出Excel</a>
					<a href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-print'" onclick="if(setBumens())print()">打印</a>
				</div>
			</div>

			<div data-options="region:'center',border:false">
				<table id="_data_grid"
					data-options="method:'get',border:false,fit:true,fitColumns:true">
					<thead>
						<tr>
							<th data-options="field:'num',sortable:true" style="width:90px">工号</th>
							<th data-options="field:'name',sortable:true" style="width:90px">员工姓名</th>
							<th data-options="field:'idcard',sortable:true" style="width:90px">身份证</th>
							<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
							<th data-options="field:'bumen_p'" style="width:90px">上级单位</th>
							<th data-options="field:'department',sortable:true"
								style="width:90px">单位</th>
							<th data-options="field:'position',sortable:true"
								style="width:90px">职位</th>
							<th data-options="field:'sex',sortable:true" style="width:90px">性别</th>
							<th data-options="field:'templatecount',sortable:true"
								style="width:90px">模版数量</th>
							<th data-options="field:'att_state',sortable:true,formatter:function(v,r){
								if(v==0){
									return '考勤';
								}else{
									return '不考勤';
								}
							}"
								style="width:90px">是否考勤</th>
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
			_data_grid.datagrid("options").url='rpt2/pageRenshi.do';
			_data_grid.datagrid("load", param);
		}

		function _excel() {
			var param="";
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
			window.open("rpt2/pageRenshi_excel.do?" + param);
		}
	</script>
</body>
</html>