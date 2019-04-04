<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout" >
			<div data-options="region:'north',border:false"
				style="height:50px;overflow: hidden;">
				<div id="search_area">
						
						数据日期：
						<input id="_date" type="text" class="easyui-datebox" name="date" value="${sdate }" style="width:100px">
						<a href="javascript:void(0)" onclick="$('#_date').datebox('setValue','');">清空日期</a> 
						同步结果：
						<select name="status" class="easyui-combobox" panelHeight="70" style="width:80px;">
							<option value="">全部</option>
							<option value="1">成功</option>
							<option value="0">失败</option>
						</select>
						
						 <a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
				</div>
			</div>

			<div data-options="region:'center',border:false">
				<table id="_data_grid"
					data-options="method:'get',border:false,fit:true">
					<thead>
						<tr>
							<th data-options="field:'area',sortable:true" style="width:50px">地市</th>
							<th data-options="field:'date',sortable:true" style="width:90px">数据日期</th>
							<th data-options="field:'data_type',sortable:true,formatter:function(v,r){
								switch(v){
								case 'RS':
									return '人事';
								case 'RS_ZIP':
									return '人事_ZIP';
								case 'NJ':
									return '年假';
								case 'KQJ':
									return '考勤机';
								case 'KD':
									return '卡点';
								case 'BM_TREE':
									return '部门树';
								case 'DKJL':
									return '打卡记录';
								case 'KQCX':
									return '考勤查询';
								case 'GRMX':
									return '个人明细';
								case 'BMMX':
									return '部门明细';
								case 'XJTJ':
									return '休假统计';
								case 'WPBTJ':
									return '未排班统计';
								}
							}" style="width:90px">数据类型</th>
							<th data-options="field:'content',sortable:true" style="width:300px">日志</th>
							<th data-options="field:'status',sortable:true,formatter:function(v,r){
								if(v==0){
									return '<span style= color:red >失败</span>'
								}else if(v==1){
									return '<span style= color:green >成功</span>'
								}
							}" style="width:50px">获取结果</th>
							<th data-options="field:'async_count',sortable:true" style="width:90px">数据同步次数</th>
							<!-- <th data-options="field:'auto_async_count',sortable:true" style="width:100px">自动同步次数</th> -->
							<th data-options="field:'time_stamp',sortable:true" style="width:120px">数据同步时间</th>
							<th data-options="field:'z',sortable:true,formatter:function(v,r){
								cache_rows[r.id]=r;
								return cz(r.status,r.id);
							}" style="width:120px">操作</th>
						</tr>
					</thead>
				</table>
			</div>
	<script type="text/javascript">
		var _data_grid = $("#_data_grid");

		_data_grid.datagrid({
   			 method: 'POST',
			rownumbers : true,
			striped : true,
			sortName : "time_stamp",
			sortOrder : "desc",
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
			_data_grid.datagrid("options").url='rpt2/hwlog.do';
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
			window.open("rpt2/pageWupaibanrenyuan_excel.do?" + param);
		}
		
		var cache_rows={};
		function cz(status,id){
			return "<a href='javascript:void(0)' onclick='loadData("+id+")'>同步数据</a>";
		}
		
		function loadData(id){
			var r=cache_rows[id];
			alert("开始获取数据，请稍后。。。");
			$.post("rpt2_load/loadByData.do",{
				"area":r.area,
				"date":r.date,
				"url":r.url,
				"userName":r.user_name,
				"password":r.password,
				"data_type":r.data_type,
				"_":new Date().getTime()},function(data){
				if(data.status==200){
					alert(data.msg);
				}else{
					alert("数据获取通道被占用，请稍后再试。。。");
				}
			},"json");
		}
	</script>
</body>
</html>