<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
<script type="text/javascript" src="static/js/libs/echarts.min.js"></script>
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area">
			<span><input type="text" class="easyui-textbox" name="like:String:name" data-options="prompt:'名称'">
			</span> <a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">

		

	</div>

</body>
</html>