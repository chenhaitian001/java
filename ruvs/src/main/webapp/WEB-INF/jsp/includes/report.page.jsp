<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 添加窗口 -->
<div id="_dialog1" class="easyui-dialog" title="&nbsp;&nbsp;添加" data-options="
	modal:true,
		closed:true,
		iconCls: 'icon-add',
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
			text:'清空',
			handler:function(){
				__clear();
			}
		}]
">加载中...</div>
<!-- 添加窗口 -->
<div id="_dialog" class="easyui-dialog" title="&nbsp;&nbsp;添加" data-options="
	modal:true,
		closed:true,
		iconCls: 'icon-add',
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
			text:'清空',
			handler:function(){
				_dialog.find('form').form('clear');
			}
		}],
		onLoad:function(){
			_init_form();
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
		sortName:"insertTime",
		sortOrder:"desc"
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