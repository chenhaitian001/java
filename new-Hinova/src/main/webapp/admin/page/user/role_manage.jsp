<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/page/user/common.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>

</head>
<style>
body{
	padding-top: 3px;
}
</style>
<body>
<script>

//用户选中的行ids = 1,2,3,5   len=4
var global_ids;
var global_ids_len;
//用户选中的行ids = 1,2,3,5   len=4
var w ;//窗口的宽
var h ;//窗口的高

//子窗口调用 的  关闭窗口方法 
function closeDlg(msg){
	 layer.closeAll();
	 layer.msg(msg);
	 reload_data();
}

//相当前刷新  重新加载
function reload_data(){
	table.reload('table', {
		 where: {
	        }
    });
}

//打开添加窗口
function add(){
	w = 500;
	h = 400;
	checkWindow();
	layer.open({
	  type: 2,
	  title: '添加',
	  shadeClose: true,
	  shade: 0.8,
	  area: [w+'px', h+'px'],
	  content: 'houtai/user/role/add' //iframe的url
	});
}


//打开编辑窗口
function edit(id){
	w = 500;
	h = 400;
	checkWindow();
	layer.open({
	  type: 2,
	  title: '修改',
	  shadeClose: true,
	  shade: 0.8,
	  area: [w+'px', h+'px'],
	  content: 'houtai/user/role/edit?id='+id //iframe的url
	});
}


function del(ids){
	var index = layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	$.post('admin/user/role/delete',{ids:ids},function(result){
		if(result.success){
			layer.closeAll();
			layer.msg('删除成功');
			reload_data();
		}else{
			layer.closeAll();
			layer.msg(result.msg);
		}
	},'json');
}

function set_persm(id){
	w = 500;
	h = 1200;
	checkWindow();
	layer.open({
		  type: 2,
		  title: '设置权限',
		  shadeClose: true,
		  shade: 0.8,
		  area: [w+'px', h+'px'],
		  content: 'houtai/user/role/setPersm?id='+id //iframe的url
	}); 
}

function set_ps(id){
	w = 500;
	h = 300;
	checkWindow();
	layer.open({
		  type: 2,
		  title: '设置新密码',
		  shadeClose: true,
		  shade: 0.8,
		  area: [w+'px', h+'px'],
		  content: 'houtai/user/role/setPassword?id='+id //iframe的url
	}); 
}


function table_edit_update(id,field,value){
	$.post('admin/user/role/update?'+field+'='+value,{id:id},function(result){
		if(result.success){
			layer.msg('修改成功');
		}else{
			layer.closeAll();
			layer.msg(result.msg);
		}
	},'json');
}

</script>

<div class="layui-form" style=" ">
<!-- 主体部分 -->
<div class="box">
	<div class="box-search clearfix">
	<div class="layui-btn-group">
	 	<button onclick="add()" class="layui-btn   layui-btn-sm"><i class="layui-icon">&#xe654;</i>增加</button>
	  	<button class="layui-btn layui-btn-sm" onclick="deleteSelected()"><i class="layui-icon">&#xe640;</i>删除选中项</button>
	    <button onclick="reload_data()" class="layui-btn layui-btn-sm"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
 	</div> 
 	 <div class="box-table">
            <table id="table" class="layui-table tableStyle" lay-filter="table"></table> 
     </div>
 	 </div>
<script type="text/html" id="table_bar">
<div class="layui-btn-group">
	<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="set_persm">设置权限</a>
</div>
</script>

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
			  table.render({
			    elem: '#table'
			    ,url: 'admin/user/role/list'
	            ,height: 'full-70'
	                ,cellMinWidth: 70,
			   cols: [[
			       {type:'checkbox',width: 30,align:'center'}
			      ,{field:'id', title: 'ID', width:50}
			      ,{field:'name', title: '角色', width:120, edit: 'text'}
			      ,{field:'createDateTime', title: '创建时间', width:150}
			      ,{field:'remark', title: '备注', width:200, edit: 'text'}
			      
			      ,{fixed:'right', width:230,title: '操作', align:'center', toolbar: '#table_bar'}
			    ]]
			  ,done: function () {
		            $("[data-field='id']").css('display','none');
		        }
			    ,id: 'table'
			    ,page: true
			    ,limits:[100,200,500,1000],
			   limit:100
			  });
			    
			
			//监听工具条 table_bar
				table.on('tool(table)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
					var data = obj.data //获得当前行数据
					, layEvent = obj.event; //获得 lay-event 对应的值
					if (layEvent === 'del') {
						//layer.msg('查看操作' + data.id);
						opend_del_dlg(data.id);
					} else if (layEvent === 'set_ps') {
						set_ps(data.id);
					} else if (layEvent === 'edit') {
						//layer.msg('编辑操作' + data.id);
						edit(data.id);
					} else if (layEvent === 'set_persm'){
						set_persm(data.id);
					}
				});
			
			
				//监听单元格编辑
				  table.on('edit(table)', function(obj){
				    var value = obj.value //得到修改后的值
				    ,data = obj.data //得到所在行所有键值
				    ,field = obj.field; //得到字段
				    //layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
				    table_edit_update(data.id,field,value);
				  });
				
				
		});
</script>


</body>
</html>
