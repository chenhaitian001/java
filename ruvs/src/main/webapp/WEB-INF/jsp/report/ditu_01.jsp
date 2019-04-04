<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<script type="text/javascript" src="static/js/libs/echarts.min.js"></script>
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">
			<span><input type="text" id="year_selected" style="width:100px" name="_:String:year" class="easyui-combobox"
				data-options="data:[{value:'2016',text:'2016',selected:true},
				{value:'2017',text:'2017'},
				{value:'2018',text:'2018'},
				{value:'2019',text:'2019'},
				{value:'2020',text:'2020'}]" name="like:String:name" /> </span> <span><input type="text" id="month_selected" style="width:100px"
				name="_:String:month" class="easyui-combobox"
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
				{value:'12',text:'12'}]"
				name="like:String:name" /> </span> <a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a> <a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-print'" onclick="print()">打印</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">

		<div id="main" style="width: 100%;height:400px;"></div>

	</div>

	<script type="text/javascript">
	

		function _search() {

			show([ 'leaveEarly' ]);
		}

		var myChart = echarts.init(document.getElementById('main'));

		myChart.showLoading();

		$.get('static/js/libs/shandong.json', function(geoJson) {

			myChart.hideLoading();

			echarts.registerMap('SHANDONG', geoJson);

			myChart.setOption(option = {
				title : {
					text : '地图概览-地市迟到概览',
					subtext : '',
				},
				tooltip : {
					trigger : 'item',
					formatter : '{b}<br/>{c} (次)'
				},
				toolbox : {
					show : true,
					orient : 'vertical',
					left : 'right',
					top : 'center',
					feature : {
						dataView : {
							readOnly : false
						},
						restore : {},
						saveAsImage : {}
					}
				},
				visualMap : {
					min : 1,
					max : 100,
					text : [ 'High', 'Low' ],
					realtime : false,
					calculable : true,
					color : [ 'orangered', 'yellow', 'lightskyblue' ]
				},
				series : [ {
					name : '地图概览-地市迟到概览',
					type : 'map',
					mapType : 'SHANDONG', // 自定义扩展图表类型
					itemStyle : {
						normal : {
							label : {
								show : true
							}
						},
						emphasis : {
							label : {
								show : true
							}
						}
					},
					data : []
				} ]
			});
		});


		function _search(){
			
			show(['beLate']);
		}
		
		function show(columns) {

			var param = {};
			param["columns"] = columns;
			param["month"] = $("#month_selected").combobox("getText");
			param["year"] = $("#year_selected").combobox("getText");
			param["order"] = "asc";
			param["sort"] = columns[0];


			var url = "report/attendance/all_area_lie.do";
			$.ajax({
				url:url, 
				data:param, 
				success:function(resu) {

					var data = [];
	
					for ( var i = 0; i < resu.x.length; i++) {
						data.push({
							name : resu.x[i],
							value : resu.y[0][i]
						});
	
					}
	
					myChart.setOption({
						visualMap : {
							min : 1,
							max : 100,
							text : [ 'High', 'Low' ],
							realtime : false,
							calculable : true,
							color : [ 'orangered', 'yellow', 'lightskyblue' ]
						},
						series : [ {
							data : data
						} ]
					});
	
				}, 
				traditional : true,
				dataType:"json"});
		}
	</script>

</body>
</html>