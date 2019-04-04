<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'west',width:200">

		<%@include file="/WEB-INF/jsp/includes/bumen_dataType_tree.jsp"%>

	</div>

	<div data-options="region:'center'">
		<div class="easyui-layout" data-options="fit:true">

			<div data-options="region:'north',border:false"
				style="height:80px;overflow: hidden;">
				<div id="search_area" style="border-bottom: 1px #95B8E7 solid">

						<div style="background: #FFF9DE;padding:3px;"><strong>功能说明：</strong>将个人明细表中的数据，转换到月表</div>
					日期范围:<input type="text" class="easyui-datebox" name="sdate"
						value="${sdate }" style="width:100px"> &nbsp;-&nbsp;<input
						type="text" class="easyui-datebox" name="edate" value="${edate }"
						style="width:100px"> 
						<a href="javascript:void(0)" id="btn"
						class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
						onclick="_load()">转换</a>
						
				</div>
			</div>
			<div data-options="region:'center',border:false">
				<div id="info_area"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function getInfo() {
			$.get("rpt2_load/loadStatus.do?_=" + new Date().getTime(),
					function(data) {
						if (data.status == 200) {
							$("#info_area").html(data.msg);
							setTimeout("getInfo()", 5000);
						} else {
							$("#info_area").html(data.msg + "<br/>数据获取完成！！");
						}
					}, "json");
		}

		function _load() {

			/* var roots = $("#area_tree").tree('getRoots');
			var roots_arrs = [];

			for ( var i = 0; i < roots.length; i++) {
				var item_childs = [ roots[i].id ];
				var childs = $("#area_tree").tree('getChildren',
						roots[i].target);
				for ( var j = 0; j < childs.length; j++) {
					if (childs[j].checkState == 'checked') {
						item_childs.push(childs[j].id);
					}
				}
				if (item_childs.length > 1) {
					roots_arrs.push(item_childs.join(","));
				}
			}

			var root_str = roots_arrs.join(";");

			if (root_str.length < 2) {
				$("#info_area").text("请选择地市");
				return;
			} */

			$("#info_area").text("加载中。。。");
			$.post("v2/day2month.do", {
				sdate : $("input[name='sdate']").val(),
				edate : $("input[name='edate']").val()
			}, function(data) {
				if (data == "success") {
					$("#info_area").text("操作成功");
				} else {
					$("#info_area").text("操作失败");
				}
			}, "text");

		}
	</script>
</body>
</html>