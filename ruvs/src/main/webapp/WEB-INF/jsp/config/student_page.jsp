<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area" >
			<span><input type="text" id ="id1" class="easyui-textbox" name="like:String:name" data-options="prompt:'学生姓名'"></span>
			<span><input type="text" id ="id2" class="easyui-textbox" name="like:String:name" data-options="prompt:'家长姓名'"></span>
			<span class="value"><input id ="id3" type="text" name="clazz.id"  class="easyui-combobox" data-options="url:'config/clazz/combo.do',editable:false,prompt:'班级'"/></span>
			<li>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="newsearch()">查找</a>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="downLoad(this)">名单下载</a>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="daoru(this)">导入幼儿信息</a>
		    <a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="upClass(this)">批量升班</a>
			
			</li>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'name',sortable:true" style="width:90px">姓名</th>
					<th data-options="field:'clazz',sortable:true,formatter:function(v,r){
					    return r.clazz?r.clazz.name:'';
					}" style="width:90px">班级</th>
					<%--<th data-options="field:'null',sortable:true" style="width:90px">家长</th>--%>
					<th data-options="field:'description',sortable:true" style="width:190px">描述</th>
					<th data-options="field:'editUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">编辑人</th>
					<th data-options="field:'editTime',sortable:true" style="width:90px">编辑时间</th>
					<th data-options="field:'createUser',formatter:function(v){if(v){return v.name;}else{return '';}}" style="width:90px">创建人</th>
					<th data-options="field:'createTime',sortable:true" style="width:90px">创建时间</th>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="config/student";
		function _configParam(v,r){
			return "<a href='javascript:open1(\"配置参数\",\"ui/config/area_attrbute_add.do\");var _area_id=\""+r.id+"\";'>配置参数</a>";
		}
        function _status(v,r){
            if(v==1){
                return '<span style="color:green;font-size:14px;font-weight: 900;">在线</a>';
            }else{
                return '<span style="color:red;font-size:14px;font-weight: 900;">离线</a>';
            }
        }
        
        function newsearch(){
   		 $("#_data_grid").datagrid({
				url : 'config/newstudent/findPaging.do',
				queryParams : {
					p1 : $("#id1").val(),
	   				p2 : $("#id2").val(),
	   			 
	   				p3 : $("#id3").combobox("getValue")
				}
			});
   	    }
        function downLoad(obj){
        		var p1 = $("#id1").val();
				var p2 = $("#id2").val();
				var p3 = $("#id3").combobox("getValue");
				
				
        	var href = "config/input/downLoadEX.do?p1="+p1+"&p2="+p2+"&p3="+p3+"";
        	obj.href = href;
        }
        function daoru(obj){
			
        	window.open2("导入","config/input/inputExcel.do");
        	
        }
		function upClass(obj){
			
			var selected_rows = _data_grid.datagrid("getSelections");
			if (selected_rows.length == 0) {
				top.alert("在执行升班级前，必须先选择一条或多条记录。");
				return;
			}
			
			$.messager.confirm("系统提示", "您确定要将这些学生生班吗？", function(r){
                if (r){

    				var param = {
    					id : []
    				};
    				for ( var i = 0; i < selected_rows.length; i++) {
    					param["id"].push(selected_rows[i]["id"]);
    				}
    				$.ajax({
    					url:"config/newstudent/upClass.do",
    					data :param,
    					traditional : true,
    					dateType : "json",
    					success : function(resu) {
    						if (resu.code == 200) {
    							_data_grid.datagrid("load");
    						}
    						top.alert(resu.msg);
    					}
    				});
                }
            });
        	
        }
	</script>
<%@ include file="/WEB-INF/jsp/includes/footer.page.jsp"%>