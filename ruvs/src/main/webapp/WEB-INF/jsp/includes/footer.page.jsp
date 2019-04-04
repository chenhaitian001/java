<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 添加窗口 -->
<div id="_dialog1" class="easyui-dialog" title="&nbsp;&nbsp;添加" data-options="
	modal:true,
		closed:true,
		iconCls: 'icon-edit',
		width:683,
		height:347,
		top:20,
		buttons: [{
			text:'保存',
			iconCls:'icon-ok',
			handler:function(){
				__save();
			}
		},{
			text:'关闭',
			handler:function(){
				$('#_dialog1').dialog('close');
			}
		}],
		onLoad:function(){
			if(window['__init_form']){
				__init_form();
			}
		},
		onClose:function(){
		 $('#_form').remove();
		}
">加载中...</div>
<div id="_dialog2" class="easyui-dialog" title="&nbsp;&nbsp;添加" data-options="
	
		closed:true,
		iconCls: 'icon-edit',
		width:683,
		height:347,
		
">加载中...</div>
<!-- 添加窗口 -->
<div id="_dialog" class="easyui-dialog" title="&nbsp;&nbsp;添加" data-options="
	modal:true,
		closed:true,
		iconCls: 'icon-edit',
		width:683,
		height:347,
		top:20,
		buttons: [{
			text:'保存',
			iconCls:'icon-ok',
			handler:function(){
				_save();
			}
		},{
			text:'关闭',
			handler:function(){
				_dialog.dialog('close');
			}
		}],
		onLoad:function(){
			if(window['_init_form']){
				_init_form();
			}
		},
		onClose:function(){
		 $('#_form').remove();
		}
">加载中...</div>

<script type="text/javascript">



	var add_url = "ui/"+_target+"_add.do";
	var edit_url = "ui/"+_target+"_edit.do";
	var save_url= _target+"/save.do";
	var del_url = _target+"/deleteByIds.do";
	var paging_url=_target+"/findPaging.do";
	var find_id_url=_target+"/findById.do";

	var _data_grid = $("#_data_grid");
	var _dialog=$("#_dialog");
	var _dialog1=$("#_dialog1");
	
	var _form_type="add";
	
	var _edit_id="";
	var _from_load=false;
	
	_data_grid.datagrid({
		url:paging_url,
		rownumbers:true,
		striped:true,
		pagination:true,
		sortName:"editTime",
		sortOrder:"desc",
		toolbar : [ {
			text : "添加",
			iconCls : 'icon-add',
			handler : function() {
				_form_type="add";
				open("添加", add_url);
			}
		}, {
			text : "编辑",
			iconCls : 'icon-edit',
			handler : function() {

				var selected_rows = _data_grid.datagrid("getSelections");
				if (selected_rows.length == 0) {
					top.alert("在执行编辑，必须先选择一条记录。");
					return;
				}else if(selected_rows.length >1){
					top.alert("每次只能编辑一条记录，请取消不必要的选择。");
					return;
				}
				_edit_id=selected_rows[0]["id"];
				_form_type="edit";
				open("编辑", edit_url+"?id="+_edit_id);
			}
		}, {
			text : "删除",
			iconCls : 'icon-remove',
			handler : function() {
				var selected_rows = _data_grid.datagrid("getSelections");
				if (selected_rows.length == 0) {
					top.alert("在执行删除操作前，必须先选择一条或多条记录。");
					return;
				}
				
				$.messager.confirm("系统提示", "您确定要删除这些记录吗？", function(r){
	                if (r){

	    				var param = {
	    					id : []
	    				};
	    				for ( var i = 0; i < selected_rows.length; i++) {
	    					param["id"].push(selected_rows[i]["id"]);
	    				}
	    				$.ajax({
	    					url : del_url,
	    					data : param,
	    					success : function(resu) {
	    						if (resu.code == 200) {
	    							_data_grid.datagrid("load");
	    						}
	    						top.alert(resu.msg);
	    					},
	    					traditional : true,
	    					dateType : "json"
	    				});
	                }
	            });
				
			}
		} ]
	});
		_data_grid.datagrid("getPager").pagination("refresh",{total:0});
	
	function _save(){
		var _form=_dialog.find('#_form');
		
		var _form_submit=_form.attr("_submit");
		if(_form_submit){
			eval(_form_submit);
			return;
		}
		
		_form.form("submit",{
			onSubmit:function(){
				return _form.form("validate");
			},
			success:function(resu){
				resu=eval("("+resu+")");
				if (resu.code == 200) {
					_data_grid.datagrid("load");
					_dialog.dialog("close");
				}else{
					if(resu.errors){
						$.each(resu.errors,function(k,v){
							var errorLi=_form.find("#"+k+"Error");
							if(errorLi){
								errorLi.text(v);
							}
						});
					}
				}
				top.alert(resu.msg);
			}
		});
	}

	function open1(title, url) {
		_dialog1.dialog("setTitle", "&nbsp;&nbsp;" + title);
		_dialog1.dialog("open");
		_dialog1.dialog("refresh", url);
	}

	function open(title, url) {
		_dialog.dialog("setTitle", "&nbsp;&nbsp;" + title);
		_dialog.dialog("open");
		_dialog.dialog("refresh", url);
	}
	function open2(title, url) {
		var _dialog2=$("#_dialog2");
		_dialog2.dialog("setTitle", "&nbsp;&nbsp;" + title);
		_dialog2.dialog("open");
		_dialog2.dialog("refresh", url);
	}
	setTimeout(function() {
		$("body").layout("panel", "north").panel("resize", {
			"height" : $("#search_area").height() + 10
		});
		$("body").layout("resize");
	}, 100);
	
	function _search(){
		var param={};
		$.each($("#search_area input[name]"),function(i,n){
			n=$(n);
			param[n.attr('name')]=n.val();
		});
		_data_grid.datagrid("load",param);
	}
	
	
	function _init_form(){
		var url=$("#_form").attr("action");
		$("#_form").form({
			url:url?url:save_url,
			onLoadSuccess:function(data){
				if(_from_load){
					return;
				}
				_from_load=true;
				var param={};
				$.each(data,function(i,n){
					if(typeof(n)==typeof({})){
						if(n&&n["id"]){
							param[i+".id"]=n["id"];
						}
					}
				});
				$("#_form").form("load",param);
			}
		});
		if(_form_type=="edit"){
			_from_load=false;
			$("#_form").form("load",find_id_url+"?id="+_edit_id+"&_="+new Date().getTime());
		}
	}
	
</script>
</body>
</html>