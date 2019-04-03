<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="/admin/page/user/top.jsp"%>   --%>
<%@ include file="/admin/page/user/common.jsp"%>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/hinova/css/page3.css">
</head>
<body>
    <div class="subNav">
        <ul class="clearfix">
        </ul>
    </div>
</div>
<!-- 主体部分 -->
    <div class="box clearfix">
        <div class="box-pic">
            <a href="javascript:;" class="fullScreen">全屏</a>
            <img src="" id='current_img'>
            <p><span id='current_name'></span></p>
            <p><span id='current_time'></span></p>
            <p><span id='current_jz'></span></p>
            <p><span id='current_tc'></span></p>
        </div>
        <div class="box-table">
            <table id="table" class="layui-table tableStyle"></table>
        </div>
    </div>


        
<script>
var c=0;
function showLogin()
{
	$.get("basicgarden/monitor_last",function(data){
        if(data&&data.length>0) {
        	data = data.shift();
            setCruurent(data);
        }
    },"json");
}
setInterval("showLogin()","5000");
function setCruurent(last1){
	var url = 'http://121.42.139.114/ruvs/' +last1.facePhoto;
    $("#current_img").attr("src",url)
    $("#current_name").text("访客: "+showValue(last1.patriarchName))
    $("#current_time").text("到访时间: "+showValue(last1.faceTime))
    $("#current_jz").text(showValue(last1.studentGradeName)+"  "+showValue(last1.studentClazzName)+"  "+showValue(last1.studentName)+" 的 "+showValue(last1.patriarchRelation))
    $("#current_tc").text("教师: "+showValue(last1.teacherName));
}
function showValue(value){

    if(value==null||value==""){
        return "--";
    }else{
        return value;
    }
}
   /*  var onoff = true; //全屏开关
    $(".fullScreen").click(function(){
        if(onoff){
            $(this).text("退出全屏");
            $(this).css("background-image","url(static/hinova/images/fullScreen2.png)");
            parent.document.getElementById("headCon").style.display="none";
            $(".headCon").css("display","none");
            $(".mainCon").css("height","94%");
            onoff = false;
        }else{
            $(this).text("全屏");
            $(this).css("background-image","url(static/hinova/images/fullScreen1.png)");
            parent.document.getElementById("headCon").style.display="none";
            $(".headCon").css("display","block");
            $(".mainCon").css("height","100%");
            onoff = true;
        }
       
    }) */
	var onoff = false; //全屏开关
    $(".fullScreen").click(function(){
        if(onoff){
            $(this).text("全屏");
            $(this).css("background-image","url(static/hinova/images/fullScreen2.png)");
            parent.document.getElementById("headConId").style.display="block";
            $(".mainCon").css("height","74%");
            onoff = false;
        }else{
            $(this).text("退出全屏");
            $(this).css("background-image","url(static/hinova/images/fullScreen1.png)");
            parent.document.getElementById("headConId").style.display="none";
            $(".mainCon").css("height","100%");
            onoff = true;
        }
       
    })
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
            ,url: 'basicgarden/list_monitor'
            ,height: 'full-20'
            ,cellMinWidth: 70
            ,cols: [[ //表头
            {field: 'faceTime', title:'时间', align:'center', minWidth:160}
            ,{field: 'patriarchName', title:'姓名', align:'center'} 
            ,{field: 'studentClazzName', title:'班级', align:'center'}
            ,{field: 'studentName', title:'幼儿', align:'center',}
            ,{field: 'patriarchRelation', title:'关系', align:'center',}
            ,{field: 'teacherName', title:'老师', align:'center',  }
            ,{field: 'patriarchPhone', title:'电话号码', align:'center',}
            ]]
		  	,done: function () {
	            $("[data-field='id']").css('display','none');
	        }
            ,skin: 'nob' //表格风格
            ,even: true
            ,id: 'table'
		    ,page: true
			,limits:[100,200,500,1000],
		    limit:30
        });
    });
    // 头部导航
    $(".nav li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    })
    $(".subNav li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    })

</script>
</body>
</html>