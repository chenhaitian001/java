<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>

<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:50px;overflow: hidden;">
		<div id="search_area" >
			<span><input type="text" value="${sdate}" class="easyui-datebox" name=">_:String:faceTime" data-options="prompt:'开始时间'"></span>
			<span><input type="text" value="${edate}" class="easyui-datebox" name="<_:String:faceTime" data-options="prompt:'结束时间'"></span>
			<a href="javascript:void(0)" id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="_search()">查找</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="_data_grid" data-options="method:'get',border:false,fit:true,fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>

                    <th data-options="field:'faceTime'" style="width:120px">时间</th>
                    <th data-options="field:'facePhoto',formatter:_img" style="width:90px">照片</th>
                    <%--<th data-options="field:'patriarchPhoto',formatter:_img" style="width:90px" >登记照</th>--%>
                    <th data-options="field:'patriarchName'" style="width:90px">家长</th>
                    <th data-options="field:'patriarchPhone'" style="width:90px">家长电话</th>


					<%--<th data-options="field:'patriarchId'" style="width:90px">家长ID</th>--%>

					<%--<th data-options="field:'studentId'" style="width:90px">学生ID</th>--%>
					<th data-options="field:'studentName'" style="width:90px">学生</th>
					<th data-options="field:'studentGradeName'" style="width:90px">年级</th>
					<th data-options="field:'studentClazzName'" style="width:90px">班级</th>
					<th data-options="field:'teacherName'" style="width:90px">老师</th>
					<th data-options="field:'teacherPhone'" style="width:90px">老师电话</th>


                    <th data-options="field:'deviceLocation'" style="width:90px">设备位置</th>
                    <%--<th data-options="field:'devcieSN'" style="width:90px">设备SN</th>--%>

					<%--<th data-options="field:'year'" style="width:90px">年</th>--%>
					<%--<th data-options="field:'month'" style="width:90px">月</th>--%>
					<%--<th data-options="field:'week'" style="width:90px">周</th>--%>
					<%--<th data-options="field:'day'" style="width:90px">日</th>--%>



				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		var _target="config/faceHistory";
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



		function _img(v,r){
		    return "<img src='"+v+"' width='90' style='padding:0px;margin:0px;' ></img>";
		}


        var paging_url="analyze/bigScreen/faceHistory.do";
        var find_id_url=_target+"/findById.do";

        var _data_grid = $("#_data_grid");

        var _form_type="add";

        var _edit_id="";
        var _from_load=false;

        _data_grid.datagrid({
            url:paging_url,
            rownumbers:true,
            striped:true,
            pagination:true,
            sortName:"faceTime",
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
                    open("编辑", edit_url);
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