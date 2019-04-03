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
	if(app.faceCode.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入人脸识别码!!!!!!');
		return ; 
	}
	if(app.name.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入姓名!!!!!!');
		return ; 
	}
	
	if(app.relation.length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输亲属关系!!!!!!');
		return ; 
	}
	if($('#student').combobox('getValue').length<1){
		layer.closeAll();//关闭loading
		layer.msg('请输入学生!!!!!!');
		return ; 
	}
	$.post(save_url,{
		faceCode:app.faceCode,
		name:app.name,
		relation:app.relation,
		address:app.address,
		phone:app.phone,
		email:app.email,
		description:app.description,
		studentId:$('#student').combobox('getValue')
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
 overflow-y:auto; 
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
	    <label class="layui-form-label">识别码</label>
	    <div class="layui-input-block">
		      <input type="text" id="faceCode" autocomplete="off" value="${patriarch.faceCode }" v-model="faceCode" placeholder="请输识别码" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">姓名</label>
	    <div class="layui-input-block">
		      <input type="text" id="name" autocomplete="off" value="${patriarch.name }" v-model="name" placeholder="请输姓名" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">关系</label>
	    <div class="layui-input-block">
		      <input type="text" id="relation" autocomplete="off" value="${patriarch.relation }" v-model="relation" placeholder="请输关系" class="layui-input">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <label class="layui-form-label">学生</label>
	    <div class="layui-input-block layui-select" style="height: 34px; width: 343px;">
	    <input type="text" name="student" value="${student.id }" id="student"  v-model="student"  autocomplete="off" class="easyui-combobox" data-options="valueField:'id',textField:'name',url:'dictionary/student',editable:false"/>
          </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">手机号</label>
	    <div class="layui-input-block">
		      <input type="text" id="phone" autocomplete="off" value="${patriarch.phone }" v-model="phone" placeholder="请输手机号" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">邮箱</label>
	    <div class="layui-input-block">
		      <input type="text" id="email" autocomplete="off" v-model="email" value="${patriarch.email}" placeholder="请输入邮箱" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">地址</label>
	    <div class="layui-input-block">
		      <input type="text" id="address" autocomplete="off" v-model="address" value="${patriarch.address}" placeholder="请输入地址" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">描述</label>
	    <div class="layui-input-block">
		      <input type="text" id="description" autocomplete="off" v-model="description" value="${patriarch.description}" placeholder="" class="layui-input">
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