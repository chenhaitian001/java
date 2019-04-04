<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<script type="text/javascript" src="static/js/libs/echarts.min.js"></script>
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
				text : '全省旷工排名'
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
				type : 'bar',
				data : []
			} ]
		};

		myChart.setOption(option);

		function show(columns) {
			var param = {};
			param["columns"] = columns;
			param["month"] = $("#month_selected").combobox("getText");
			param["year"] = $("#year_selected").combobox("getText");
			param["order"] = "asc";
			param["sort"] = columns[0];
			$.ajax({
				url : "report/attendance/all_area_lie.do",
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
		
		
		

		setTimeout(function() {
			

			var month=new Date().getMonth();
			
			$("#month_selected").combobox("setValue",month>9?""+month:"0"+month);

			_search();
			
		}, 100);

		//show(["beLate"], "济南");

		// 使用刚指定的配置项和数据显示图表。
	</script>

</body>
</html>