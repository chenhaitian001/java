<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<style type="text/css">
		.log_info ul,log_info li{list-style: none;padding:0px;margin:0px;}
		.log_info ul{padding:20px}
		.log_info li{margin:5px 10px 0 10px;line-height: 20px;padding-left:10px;}
		.log_info li:nth-child(even){background: #FFAA00}
		.log_info li:nth-child(odd){background: #3C7FB1;color:#fff;}
		.field_title{padding-left:20px;}
		.area .field_tile{font-weight: bolder;}
		.step1,.step2{    width: 5px;
    height: 12px;
    border: 1px #95b8e7 solid;
    margin-left: 1px;
    vertical-align: text-bottom;
    display: inline-block;}
		.status_-1{background: gray;}
		.status_0{background: blue;}
		.status_1{background: green;}
		.error{background: red;}
		.error *{background: red;}
	</style>
	<div data-options="region:'west',width:200">
		
			<%@include file="/WEB-INF/jsp/includes/bumen_dataType_tree.jsp" %>
	
	</div>
	
	<div data-options="region:'center'">
		<div class="easyui-layout" data-options="fit:true">
			
			<div data-options="region:'north',border:false"
				style="height:50px;overflow: hidden;">
				<div id="search_area" style="border-bottom: 1px #95B8E7 solid">
					
					
					<input type="text" class="easyui-datebox" name="sdate"
						value="${sdate }" style="width:100px"> <a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-search'" onclick="_load()">加载</a>
						<a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-reload'" onclick="$('#info_area').text('加载中...');setTimeout('getInfo()',1000);">获取实时日志</a>
						<a
						href="javascript:void(0)" id="btn" class="easyui-linkbutton"
						data-options="iconCls:'icon-no'" onclick="clearAllDownThread()">清空获下载线程</a>
				</div>
			</div>
			<div data-options="region:'center',border:false">
			&nbsp;&nbsp;颜色说明：
				<span class="status_-1 step1"></span>未开始&nbsp;
				<span class="status_0 step1"></span>进行中&nbsp;
				<span class="status_1 step1"></span>完成&nbsp;
				<span class="error step1"></span>失败
				<div id="info_area">
				
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		
		function getInfo(){
			$.get("rpt2_load/loadStatus.do?_="+new Date().getTime(),function(data){
				if(data.status==200){
					$("#info_area").html(data.msg);
					setTimeout("getInfo()",5000);
				}else{
					$("#info_area").html(data.msg+"<br/>数据获取完成！！");
				}
			},"json");
		}
		
		function clearAllDownThread(){
			$.get("rpt2_load/clearAllDownThread.do?_="+new Date().getTime(),function(data){
				if(data.status==200){
					alert(data.msg);
				}else{
					alert(data.msg);
				}
			},"json");
		}
		
		
		
		function _load(){
			
			var roots=$("#area_tree").tree('getRoots');
			var roots_arrs=[];
			
			for(var i=0;i<roots.length;i++){
				var item_childs=[roots[i].id];
				var childs=$("#area_tree").tree('getChildren',roots[i].target);
				for(var j=0;j<childs.length;j++){
					if(childs[j].checkState=='checked'){
						item_childs.push(childs[j].id);
					}
				}
				if(item_childs.length>1){
					roots_arrs.push(item_childs.join(","));
				}
			}
					
			var root_str=roots_arrs.join(";");
			
			if(root_str.length<2){
				$("#info_area").text("请选择地市");
				return;
			}
			
			$("#info_area").text("加载中。。。");
			$.post("rpt2_load/loadByDataAreaDataTypes.do",
					{dateStr:$("input[name='sdate']").val(),dataStr:root_str},
					 function(data) {
						if(data.status==200){
							alert(data.msg);
							//setTimeout("getInfo()",5000);
							
						}else{
							alert("数据获取通道被占用，请稍后再试。。。");					
							//getInfo();
						}
						setTimeout("getInfo()",5000);
			}, "json");
			
			
		}
		
	</script>
</body>
</html>