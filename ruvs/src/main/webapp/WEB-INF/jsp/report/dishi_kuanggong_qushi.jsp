<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<script type="text/javascript" src="static/js/libs/echarts.min.js"></script>
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">
			<span><input type="text" id="year_selected"  class="easyui-combobox" 
			data-options="data:[{value:'2016',text:'2016',selected:true},
				{value:'2017',text:'2017'},
				{value:'2018',text:'2018'},
				{value:'2019',text:'2019'},
				{value:'2020',text:'2020'}]" name="like:String:name" /> </span> 
			<span><input type="text" id="area_selected"  class="easyui-combobox" data-options="url:'combo/Area/id/name.do'"  /> </span> 
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="print()">打印</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">


		<div id="main" style="width: 100%;height:400px;"></div>
		

	</div>

	<script type="text/javascript">
	
	
		function _search(){
			
			show(['absentee']);
		}
	
		var myChart = echarts.init(document.getElementById('main'));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '地市旷工趋势'
			},
			tooltip : {},
			legend : {
				//data : [ '济南市' ]
			},
			xAxis : {
				data : []
			},
			yAxis : {},
			series : [ {
				name : '济南市',
				type : 'line',
				data : []
			} ]
		};

		myChart.setOption(option);

		function show(columns) {
			var param = {};
			param["columns"] = columns;
			param["area"] = $("#area_selected").combobox("getText");
			param["year"] = $("#year_selected").combobox("getText");
			$.ajax({
				url : "report/attendance/lie.do",
				data : param,
				success : function(resu) {
					
					if(resu.x.length==0){
						myChart.setOption({
							xAxis : {
								data : []
							},
							series : [{data:[]}]
						});
						return ;
					}

					var series = [];

					for ( var i = 0; i < resu.y.length; i++) {

						series.push({
							data : resu.y[i]
						});
					}

					myChart.setOption({
						xAxis : {
							data : resu.x
						},
						series : series
					});

				},
				traditional : true,
				dateType : "json"
			});
		}

		//show(["beLate"], "济南");

		// 使用刚指定的配置项和数据显示图表。
	</script>

</body>
</html>