<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/page/user/common.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
var save_url = '<%=request.getContextPath()%>'+'${save_url}';
function save(){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	if(app.cname.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入班级!!!!!!');
		return ; 
	}
	if($('#gname').combobox('getValue').length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入年级!!!!!!');
		return ; 
	}
	$.post(save_url,{
		cname:app.cname,
		gradeid:$('#gname').combobox('getValue')
		,description:app.description
		 
		},function(result){
		if(result.success){
			//调用 父窗口的  关闭所有窗口 并且刷新 页面
			window.parent.closeDlg(result.msg);
		}else{
			layer.closeAll();//关闭loading
			layer.msg(result.msg);
		}
	},'json');
	
}


</script>
<style>
html, body {
}
.layui-form-item {
    margin-bottom: 3px;
}

</style>
</head>
<body id="app">


<div style="padding: 10px;">
	<form class="layui-form layui-form-pane">
	
	  <div class="layui-form-item">
	    <label class="layui-form-label">班级</label>
	    <div class="layui-input-block">
		      <input type="text" id="cname" autocomplete="off" value="${clazz.cname }" v-model="cname" placeholder="请输班级" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">年级</label>
	    <div class="layui-input-block layui-select" >
	    <input type="text"  name="grade.gname" value="${grade.gid }" id="gname"  v-model="gname"  autocomplete="off" class="easyui-combobox" data-options="valueField:'gid',textField:'gname',url:'dictionary/grade',editable:false"/>
          </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">描述</label>
	    <div class="layui-input-block">
		      <input type="text" id="description" autocomplete="off" v-model="description" value="${clazz.description}" placeholder="" class="layui-input">
	    </div>
	  </div>
	  </form>
		<div class="site-demo-button" style="margin-top: 20px;">
		  <button id="save" onclick="save()" class="layui-btn site-demo-layedit" data-type="content">${btn_text }</button>
		</div>
</div>


<script>
layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel',
		'upload', 'element' ], function() {
	var laydate = layui.laydate //日期
	, laypage = layui.laypage //分页
	layer = layui.layer //弹层
	, table = layui.table //表格
	, carousel = layui.carousel //轮播
	, upload = layui.upload //上传
	, element = layui.element; //元素操作
});
</script>
<script>
var app = new Vue({
	el : '#app',
	data : {
	}
});
 
</script>


</body>
</html>