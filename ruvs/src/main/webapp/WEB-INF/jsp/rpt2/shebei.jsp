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
					<input type="hidden" id="input_bumen_ids" name="bumen_ids"><span><input
						type="text" class="easyui-textbox" name="name"
						data-options="prompt:'设备名称|SN'" style="width:100px;"> </span> 
						
					<input type="radio" name="syzt" value="启用" checked="checked"> 启用
					<input type="radio" name="syzt" value="停用" > 停用
					
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
						data-options="iconCls:'icon-reload'" onclick="loadDate()">同步数据</a>
					<a href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-print'" onclick="if(setBumens())print()">打印</a>
				</div>
			</div>

			<div data-options="region:'center',border:false">
				<table id="_data_grid"
					data-options="method:'get',border:false,fit:true">
					<thead>
						<tr>
							<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
							<th data-options="field:'azwz',sortable:true" style="width:90px">安装位置</th>
							<th data-options="field:'sbmc',sortable:true" style="width:90px">设备名称</th>
							<th data-options="field:'sn',sortable:true" style="width:110px">设备SN</th>
							<th data-options="field:'gjbb',sortable:true" style="width:90px">固件版本</th>
							<th data-options="field:'sblx',sortable:true" style="width:90px">设备类型</th>
							<th data-options="field:'ljzt',sortable:true,formatter:function(v,r){
									if(v=='异常'){
										return '<span style=\'color:red;\'>异常</span>';
									}else{
										return v;
									}
								}" style="width:90px">连接状态</th>
							<th data-options="field:'sfbb',sortable:true" style="width:90px">识别算法版本</th>
							<th data-options="field:'dqip',sortable:true" style="width:90px">当前IP</th>
							<th data-options="field:'zjzxsj',sortable:true" style="width:120px">最近在线时间</th>
							<th data-options="field:'syzt',sortable:true,formatter:function(v,r){
									if(v=='停用'){
										return '<span style=\'color:red;\'>停用</span>';
									}else{
										return v;
									}
								}" style="width:90px">使用状态</th>
							<th data-options="field:'yyyhrl',sortable:true" style="width:110px">已用用户容量(人)</th>
							<th data-options="field:'huzrl',sortable:true" style="width:100px">用户总容量(人)</th>
							<th data-options="field:'yyjlrl',sortable:true" style="width:110px">已用记录容量(条)</th>
							<th data-options="field:'jlzrl',sortable:true" style="width:100px">记录总容量(条)</th>
							<th data-options="field:'yhrlgj',sortable:true" style="width:90px">用户容量告警</th>
							<th data-options="field:'jlrlgj',sortable:true" style="width:90px">记录容量告警</th>
							<th data-options="field:'mac',sortable:true" style="width:120px">MAC地址</th>
							<th data-options="field:'notes',sortable:true" style="width:90px">备注</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
	<script type="text/javascript">
	
		function loadDate(){
			$.post("rpt2_load/loadByDataName.do?date=2016-09-27&dataName=kaoqinji",{"_":new Date().getTime()},function(data){
				if(data.status==200){
					alert(data.msg);
				}else{
					alert("数据获取捅到被占用，请稍后再试。。。");
				}
			},"json");
		
		}
	
	
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
			_data_grid.datagrid("options").url='rpt2/pageShebei.do';
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
			window.open("rpt2/pageShebei_excel.do?" + param);
		}
	</script>
</body>
</html>