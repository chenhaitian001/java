<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/page/user/common.jsp"%>
<!-- 主体部分 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>离园幼儿</title>

</head>
<body>
<script>
//子窗口调用 的  关闭窗口方法 
    function closeDlg(msg){
    	 layer.closeAll();
    	 layer.msg(msg);
    	 reload_data();
    }
    function reload_data(){
    	table.reload('table', {
    		 where: {
    	        }
        });
    }
  //打开编辑窗口
    function edit(id){
    	w = 1000;
    	h = 1000;
    	checkWindow();
    	layer.open({
    	  type: 2,
    	  title: '修改',
    	  shadeClose: true,
    	  shade: 0.8,
    	  area: [w+'px', h+'px'],
    	  content: 'chileren/student/edit?id='+id //iframe的url
    	});
    }
    </script>
	<div class="box-search clearfix">
	<div class="layui-btn-group">
 	    <button onclick="reload_data()" class="layui-btn layui-btn-sm"><i class="layui-icon">&#x1002;</i>刷新</button>
 	 </div>
 	</div> 
 	 <div class="box-table">
            <table id="table" class="layui-table tableStyle" lay-filter="table"></table> 
     </div>
	<script type="text/html" id="table_bar">
	<div class="layui-btn-group">
		<a class="layui-btn layui-btn-xs" lay-event="edit">修改</a>
	</div>
	</script>
	 <script type="text/html" id="sex">
        {{#if (d.sex == 1) { }}
         <span>男</span>
        {{# }else if(d.sex == 0){ }}
        <span>女</span>
        {{# } }}
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
            ,url: '<%=request.getContextPath()%>/chileren/leavelist'
            ,height: 'full-70'
            ,cellMinWidth: 70
            ,cols: [[ //表头
            {type:'checkbox',width: 30,align:'center'}
            ,{field:'id', title: 'ID', sort: true,align:'center'} 
            ,{field: 'name', title: '姓名',align:'center'} 
            ,{field: 'sex', title: '性别',width: 70,align:'center',templet: '#sex'}
            ,{field: 'gradeId', title: '年级',width: 150,align:'center'}
            ,{field: 'clazzId', title: '班级',align:'center'}
            ,{field: 'keeper', title: '主监护人',align:'center'}
            ,{field: 'keeperPhone', title: '手机号',align:'center'}
            ,{field: 'goSchoolTime', title: '入园时间',width: 200,align:'center'}
           // ,{field: 'description', title: '描述',align:'center'}
            ,{fixed:'right', width:150,title: '操作', align:'center', toolbar: '#table_bar'}
            ]]
            ,skin: 'nob' //表格风格
                ,even: true
            ,id: 'table'
			    ,page: true
			    ,limits:[100,200,500,1000],
			   limit:100
        });
		   
		  //执行实例
		  var uploadInst = upload.render({
		    elem: '#test1' //绑定元素
		    ,url: 'chileren/student/input' //上传接口
		    ,accept: 'file'
		    ,done: function(res){
		    	 if(res.code == 0){
		    		 layer.closeAll();
		        	 layer.msg("导入成功");
		        	 reload_data();
		    	    }
		    	 else{
		    		 layer.closeAll();
		        	 layer.msg(res.failreason);
		        	 reload_data();
		    	 }
		    }
		    ,error: function(){
		    	layer.closeAll();
		    	 layer.msg(res.failreason);
		    	 reload_data();
		    }
		  });
		  
		 //监听工具条 table_bar
			table.on('tool(table)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data //获得当前行数据
				, layEvent = obj.event; //获得 lay-event 对应的值
				if (layEvent === 'del') {
					//layer.msg('查看操作' + data.id);
					opend_del_dlg(data.gid);
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