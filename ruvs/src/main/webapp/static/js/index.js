
function openTab(title,url){
	var tab = $('#tabs').tabs("getTab",title);
	url=encodeURI(url);
	if(tab == null){
		$('#tabs').tabs('add',{
			title: title,
			content: "<iframe src='"+url+"' frameborder='no' style='overflow:hidden;border:0px;width:100%;height:100%;'></iframe>",
			closable: true
		});
	}else{
		$("#tabs").tabs("select",title);
		var _frame=$(tab).find("iframe");
		_frame.attr("src",url);
	}
}



function select_menu_item(_this){

	$('#layout_left .item').removeClass('selected');
	$(_this).addClass('selected');

}



$(function(){

	/*$("#layout_left .title").click(change_left);
	var left_status=$.cookie("left_status");
	if(left_status){
		change_left(null,left_status);
	}else{
		$.cookie("left_status","max");
		change_left(null,"max");
	}*/

	$(".login_info>li").mouseover(function(){
		$(".login_info .other").show();
	});

	$(".login_info>li").mouseout(function(){
		$(".login_info .other").hide();
	});
});

function change_left(o,v){
	if(v){
		if(v=="max"){
			layout_left_resize(192);
			$("#layout_left .operate").html("&lt;&lt;");
			$.cookie("left_status","max");
		}else if(v=="min"){
			layout_left_resize(62);
			$("#layout_left .operate").html("&gt;&gt;");
			$.cookie("left_status","min");
		}
	}else{
		if($.cookie("left_status")=="max"){
			change_left(null,"min");
		}else{
			change_left(null,"max");
		}
	}
}

function layout_left_resize(width){
	$("body").layout("panel","west").panel("resize",{"width":width});
	$("body").layout("resize");
}