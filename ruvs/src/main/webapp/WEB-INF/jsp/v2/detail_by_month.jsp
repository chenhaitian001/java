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
					<input type="hidden" id="input_bumen" name="bumen" /> 
					<input type="hidden" id="input_area" name="area" /> 
					<input type="hidden" id="input_bumen_ids" name="bumen_ids">
						
						<input type="text" class="easyui-datebox" name="sdate" value="${sdate }" style="width:100px" 
						data-options="formatter:function(date){
							var y = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							return y+'-'+(m<10?'0'+m:m);
						},parser:function(s){
							var t = Date.parse(s);
							if (!isNaN(t)){
								return new Date(t);
							} else {
								return new Date();
							}
						}"> 
						&nbsp;-&nbsp;
						<input type="text" class="easyui-datebox" name="edate" value="${edate }" style="width:100px"
						data-options="formatter:function(date){
							var y = date.getFullYear();
							var m = date.getMonth()+1;
							var d = date.getDate();
							return y+'-'+(m<10?'0'+m:m);
						},parser:function(s){
							var t = Date.parse(s);
							if (!isNaN(t)){
								return new Date(t);
							} else {
								return new Date();
							}
						}"> 
						
						<span><input
						type="text" class="easyui-textbox" name="num"
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
					<thead frozen="true">
						<tr>
						
							<th data-options="field:'date_month',sortable:true" style="width:90px">月份</th>
							<th data-options="field:'num',sortable:true" style="width:90px">工号</th>
							<th data-options="field:'area',sortable:true" style="width:90px">地市</th>
							<th data-options="field:'bumen_p',sortable:true" style="width:90px">上级单位</th>
							<th data-options="field:'bumen',sortable:true" style="width:90px">直属单位</th>
							<th data-options="field:'name',sortable:true" style="width:90px">姓名</th>
						</tr>
					</thead>
					<thead>
						<tr>
							<th data-options="field:'day01',formatter:function(v,r){
								if(r.days['01']){
									return r.days['01'];
								}else{
									return '';
								}
							}" style="width:40px">1</th>
							
							<th data-options="field:'day02',formatter:function(v,r){
								if(r.days['02']){
									return r.days['02'];
								}else{
									return '';
								}
							}" style="width:40px">2</th>
							
							<th data-options="field:'day03',formatter:function(v,r){
								if(r.days['03']){
									return r.days['03'];
								}else{
									return '';
								}
							}" style="width:40px">3</th>
							
							<th data-options="field:'day04',formatter:function(v,r){
								if(r.days['04']){
									return r.days['04'];
								}else{
									return '';
								}
							}" style="width:40px">4</th>
							
							<th data-options="field:'day05',formatter:function(v,r){
								
								if(r.days['05']){
									return r.days['05'];
								}else{
									return '';
								}
							}" style="width:40px">5</th>
							
							<th data-options="field:'day06',formatter:function(v,r){
								
								if(r.days['06']){
									return r.days['06'];
								}else{
									return '';
								}
							}" style="width:40px">6</th>
							
							<th data-options="field:'day07',formatter:function(v,r){
								
								if(r.days['07']){
									return r.days['07'];
								}else{
									return '';
								}
							}" style="width:40px">7</th>
							
							<th data-options="field:'day08',formatter:function(v,r){
								
								if(r.days['08']){
									return r.days['08'];
								}else{
									return '';
								}
							}" style="width:40px">8</th>
							
							<th data-options="field:'day09',formatter:function(v,r){
								
								if(r.days['09']){
									return r.days['09'];
								}else{
									return '';
								}
							}" style="width:40px">9</th>
							
							<th data-options="field:'day10',formatter:function(v,r){
								
								if(r.days['10']){
									return r.days['10'];
								}else{
									return '';
								}
							}" style="width:40px">10</th>
							
							<th data-options="field:'day11',formatter:function(v,r){
								
								if(r.days['11']){
									return r.days['11'];
								}else{
									return '';
								}
							}" style="width:40px">11</th>
							
							<th data-options="field:'day12',formatter:function(v,r){
								
								if(r.days['12']){
									return r.days['12'];
								}else{
									return '';
								}
							}" style="width:40px">12</th>
							
							<th data-options="field:'day13',formatter:function(v,r){
								
								if(r.days['13']){
									return r.days['13'];
								}else{
									return '';
								}
							}" style="width:40px">13</th>
							
							<th data-options="field:'day14',formatter:function(v,r){
								
								if(r.days['14']){
									return r.days['14'];
								}else{
									return '';
								}
							}" style="width:40px">14</th>
							
							<th data-options="field:'day15',formatter:function(v,r){
								
								if(r.days['15']){
									return r.days['15'];
								}else{
									return '';
								}
							}" style="width:40px">15</th>
							
							<th data-options="field:'day16',formatter:function(v,r){
								
								if(r.days['16']){
									return r.days['16'];
								}else{
									return '';
								}
							}" style="width:40px">16</th>
							
							<th data-options="field:'day17',formatter:function(v,r){
								
								if(r.days['17']){
									return r.days['17'];
								}else{
									return '';
								}
							}" style="width:40px">17</th>
							
							<th data-options="field:'day18',formatter:function(v,r){
								
								if(r.days['18']){
									return r.days['18'];
								}else{
									return '';
								}
							}" style="width:40px">18</th>
							
							<th data-options="field:'day19',formatter:function(v,r){
								
								if(r.days['19']){
									return r.days['19'];
								}else{
									return '';
								}
							}" style="width:40px">19</th>
							
							<th data-options="field:'day20',formatter:function(v,r){
								
								if(r.days['20']){
									return r.days['20'];
								}else{
									return '';
								}
							}" style="width:40px">20</th>
							
							<th data-options="field:'day21',formatter:function(v,r){
								
								if(r.days['21']){
									return r.days['21'];
								}else{
									return '';
								}
							}" style="width:40px">21</th>
							
							<th data-options="field:'day22',formatter:function(v,r){
								
								if(r.days['22']){
									return r.days['22'];
								}else{
									return '';
								}
							}" style="width:40px">22</th>
							
							<th data-options="field:'day23',formatter:function(v,r){
								
								if(r.days['23']){
									return r.days['23'];
								}else{
									return '';
								}
							}" style="width:40px">23</th>
							
							<th data-options="field:'day24',formatter:function(v,r){
								
								if(r.days['24']){
									return r.days['24'];
								}else{
									return '';
								}
							}" style="width:40px">24</th>
							
							<th data-options="field:'day25',formatter:function(v,r){
								
								if(r.days['25']){
									return r.days['25'];
								}else{
									return '';
								}
							}" style="width:40px">25</th>
							
							<th data-options="field:'day26',formatter:function(v,r){
								
								if(r.days['26']){
									return r.days['26'];
								}else{
									return '';
								}
							}" style="width:40px">26</th>
							
							<th data-options="field:'day27',formatter:function(v,r){
								
								if(r.days['27']){
									return r.days['27'];
								}else{
									return '';
								}
							}" style="width:40px">27</th>
							
							<th data-options="field:'day28',formatter:function(v,r){
								
								if(r.days['28']){
									return r.days['28'];
								}else{
									return '';
								}
							}" style="width:40px">28</th>
							
							<th data-options="field:'day29',formatter:function(v,r){
								
								if(r.days['29']){
									return r.days['29'];
								}else{
									return '';
								}
							}" style="width:40px">29</th>
							
							<th data-options="field:'day30',formatter:function(v,r){
								
								if(r.days['30']){
									return r.days['30'];
								}else{
									return '';
								}
							}" style="width:40px">30</th>
							
							<th data-options="field:'day31',formatter:function(v,r){
								
								if(r.days['31']){
									return r.days['31'];
								}else{
									return '';
								}
							}" style="width:40px">31</th>
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
			_data_grid.datagrid("options").url='v2/detail_by_month.do';
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
			window.open("v2/detail_by_month_excel.do?" + param);
		}
		
		function _pdf() {
			var param = "search=1";
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
			window.open("v2/detail_by_month_v2_export_pdf.do?" + param);
		}
	</script>
</body>
</html>