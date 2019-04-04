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
						
						<span><input
						type="text" class="easyui-textbox" name="num"
						data-options="prompt:'姓名|工号'"> </span> 
						
						
						<input type="checkbox" name="gds" value="1"> 供电所考勤查询
						
						 <a href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
						<a href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-redo'" onclick="_pdf()" title="此功能不能跨月，以开始时间为准">导出PDF</a>
				</div>
			</div>

			<div data-options="region:'center',border:false">
				<iframe id="pdfpage" style="width:100%;height:100%;border:none" src="v2/detail_by_month_v2.do">
				
				
				</iframe>
			
			</div>
		</div>

	</div>
	<script type="text/javascript">

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
			var param = "search=1&limit=20";
			$.each($("#search_area input[name]"), function(i, n) {
				n = $(n);

				
				if(n.attr('type') == "radio" && !n.get(0).checked){
					return;
				}
				
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
			
			$("#pdfpage").attr("src","v2/detail_by_month_v2.do?_="+new Date().getTime()+"&"+param);
		}

		
		function _pdf() {
			var param = "search=1";
			$.each($("#search_area input[name]"), function(i, n) {
				n = $(n);

				
				if(n.attr('type') == "radio" && !n.get(0).checked){
					return;
				}
				
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
			window.open("v2/detail_by_month_v2_export_pdf.do?_="+new Date().getTime()+"&" + param);
		}
	</script>
</body>
</html>