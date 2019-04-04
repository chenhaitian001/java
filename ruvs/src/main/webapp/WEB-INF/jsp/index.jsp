<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/jsp/includes/header.index.jsp"%>

<style type="text/css">
	.layout-panel-north{
		overflow: visible;
	}

	#login_right>ul{
		float: left;
	}

	#login_right > ul > li{
		height:100%;
		padding: 0px 20px;
		letter-spacing: 2px;
		color: #fff;
		cursor: pointer;
		margin-right: 5px;
		position: relative;
        line-height: 50px;
        float: left;
	}
	#login_right > ul > li>i{
		font-size: 20px;
		vertical-align: middle;
	}
	#login_right > ul > li>span{
		vertical-align: middle;
	}


	#login_right>ul>li:hover{
		background: rgba(0,0,0,0.1);
		color: #efefef;
	}

    #login_right>ul>.selected{
        background: rgba(0,0,0,0.1);
        color: #efefef;
    }

    #login_right>ul>li a{
        text-decoration: none;padding:5px 10px;
        background: #efefef;border:1px solid #eee;line-height: 20px;
        box-shadow: 0 0 3px rgba(0,0,0,0.3); height: 20px;color: #555;
        border-radius: 3px;  position: absolute;right:10px;top: 20px;
    }
    #login_right>ul>li a:hover{
        box-shadow: 0 0 6px rgba(199, 59, 201, 0.6);
    }
    .login_info .other{
        display: none;}


    .color-red{
        color:#DC4895;
    }
    .color-blue{
        color: #CE58DB;
    }
    .color-green{
        color: #BC5F4E;
    }


</style>

<body class="easyui-layout">
	<div id="layout_top" data-options="region:'north',border:false" >
		
		<div id="logo" style="width: 250px;float: left;">
			<span class="site_name">${siteName}</span>
		</div>


		<div id="login_right" style="float: right;">

			<ul id="menu" >
	
				<c:forEach items="${models }" var="res" varStatus="status">
					<li class="${status.index==index?'selected':'' }" onclick="window.location.href='index.do?index=${status.index}'">
						<i class="fa ${res.icon}"></i>
						<span class="title" style="font-size:14px;">${res.name }</span>
					</li>
				</c:forEach>
			</ul>
	
	
			<ul class="login_info">
				<li>${user.showName },您好<i class="fa  fa-arrow-circle-down" style="font-size:14px;font-weight: bolder;"></i>
					<ul  class="other" style="border: #eaeaea 1px solid;background: #fafafa;position: absolute;top: 50px;width: 275px;
					        border-bottom-right-radius: 5px;border-bottom-left-radius: 5px;
							right: 0px;">
						<li style="height: 176px;background: #4c94f6;padding:15px 0">
							<div style="background: #eee;border-radius: 50%;width: 100px;height: 100px;
								box-shadow: 0 0 20px rgb(253, 230, 230);margin: 0px auto;background: url('static/img/hp/hp_boy.png') no-repeat 100% ;
								border-left: 1px solid #fff;border-right: 1px solid #fff;
								background-size:100% auto;"></div>
							<div style="height: 30px;text-align: center;" title="${starWord}">${starWordText}</div>
							<div style="height: 30px;text-align: center;">${date}</div>
						</li>
						<li style="height: 70px;  padding: 0 20px;position: relative;line-height: 70px;">
							<a href="javascript:void(8)" onclick="open_password()" style="left:10px;width: 58px;">修改密码</a>
							<a href="loginout.do" style="right:10px;">退出</a>
						</li>
					</ul>
				</li>
			</ul>

		</div>

	</div>



	<div id="layout_left" data-options="region:'west',border:false"  style="width:200px;border-top:solid 1px #fff;">
		<div class="title" style="position: relative;"><i class="fa fa-bars" style="font-size: 16px;margin: 0 20px;"></i> 功能菜单
			
		</div>
		<div >
			<ul>
				<c:forEach items="${linkMaps[models[index].id] }" var="res" >
					<li class="item"  onclick="select_menu_item(this);openTab('${res.name }','${res.url}')">
                        <i class="${res.icon}"></i>
                        ${res.name }
                    </li>
				</c:forEach>
			
				<%--<li class="item" style="background-image:url('static/img/index/yhpz.png')" onclick="select_menu_item(this);openTab('人员统计','ui/report/attendance_page.do')">地市部门</li>
				<li class="item" style="background-image:url('static/img/index/yhpz.png')" onclick="select_menu_item(this);openTab('人员统计','ui/report/personnel_change_page.do')">人员统计</li>
				<li class="item" style="background-image:url('static/img/index/yhpz.png')" onclick="select_menu_item(this);openTab('人员详情','ui/report/personnel_page.do')">人员详情</li>
				
			--%></ul>
		</div>
	</div>
	<div id="layout_bottom" data-options="region:'south'" style="height:2px;">
	</div>
	<div data-options="region:'center',border:false">
		<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false,tools:'#tab-tools'" style="width:700px;height:250px;
			">
			<%--<div title="欢迎" style="padding:10px;padding:0px;overflow: hidden;">
				<img src="static/img/main/main_01.jpg" style="width: 100%;"/>

			</div>--%>
		</div>
	</div>
	
	
	<div id="tab-tools" style="border-left: none;border-right: none;">

	</div>
	<script type="text/javascript">
	
		function open_password(){
			open('修改密码','ui/sys/change_password.do');
		}
		function openArea(n){
			if(n.value=='-100'){
				//$("#tabs").tabs("remove",n.text);				
				return;
			}
			$.get("config/area/getUP.do?id="+n.value+"&_="+new Date().getTime(),function(resu){
				openTab(n.text,resu.login_ip+"/login.whtml?user.userName="+resu.login_name+"&user.password="+resu.login_password);
				setTimeout(function(){
					openTab(n.text,resu.login_ip+"/index.html");
				},100);
			},"json");
		}
	</script>
	
	
	<div id="_dialog" class="easyui-dialog" title="&nbsp;&nbsp;添加" data-options="
	modal:true,
		closed:true,
		iconCls: 'icon-edit',
		width:583,
		height:247,
		top:20,
		buttons: [{
			text:'保存',
			iconCls:'icon-ok',
			handler:function(){
				change_password();
			}
		},{
			text:'关闭',
			handler:function(){
				_dialog.dialog('close');
			}
		}]
">加载中...</div>

<script type="text/javascript">
	var _dialog=$("#_dialog");
function open(title, url) {
	_dialog.dialog("setTitle", "&nbsp;&nbsp;" + title);
	_dialog.dialog("open");
	_dialog.dialog("refresh", url);
}

function change_password(){
	
	if(!_dialog.find('#_form').form("validate")){
		alert("操作失败");
		return;
	}
	
	var old_password=$("input[name='old_password']").val();
	var new_password=$("input[name='new_password']").val();
	
	$.post("updatePassword.do",{new_password:new_password,old_password:old_password},function(resu){
		if(resu.code==200){
			alert("操作成功");
			_dialog.dialog("close");
		}else{
			alert("操作失败");
		}
	},"json");
}

    setTimeout(function(){
        $("#layout_left ul li")[0].click();
    },500)

</script>
	
</body>
</html>
