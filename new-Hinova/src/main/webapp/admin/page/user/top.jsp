<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>育智云</title>
    
	<!-- JSTL -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
    
    <script src="static/hinova/js/jquery-1.12.1.min.js"></script>
    <link rel="stylesheet" href="static/hinova/js/layui/css/layui.css" media="all">
    <script src="static/hinova/js/layui/layui.js"></script>
    <!-- 控制头样式显示-->
	<link rel="stylesheet" href="static/hinova/css/public2.css">
    <link rel="stylesheet" href="static/hinova/css/page4.css">
    
</head>
<body>
<script>

$(document).ready(function(){
    var Uarry=$("#tree li");//获取所有的li元素
    //$("#container").css("display","none"); 
    $("#tree li").click(function(){//点击事件
         //测试直接获取值 var v=$(this).text(); alert(v); 
         var html = '';
         var count=$(this).index();//获取li的下标
         var Tresult=Uarry.eq(count).text();
         var list = document.getElementById('container');
         <c:forEach var="tree" items="${treeList }">
         
         if(Tresult=='${tree.text}'){
        	 /* if($("#container").css("display")=="none"){ 
        		 $("#container").css("display","block"); 
        		 }else{ 
        			 $("#container").css("display","none"); 
                 }  */
 		 <c:forEach var="child" items="${tree.children}">
 		//	html+='<li><a href="${child.url}">${child.text}</a></li>';
 			
 			html+='<li class=""><dd id="${child.dd_id}" url="${child.url}" type="${child.type}" text="${child.text}"><a style="cursor:pointer;">${child.text}</a></dd></li>';
    		
			list.innerHTML = html;
 			
 		 </c:forEach>
 		 
         }
 	     </c:forEach>
 	 //   document.getElementById('container').innerHTML = html;
         
    }) 
});

//全屏和退出全屏
function toggleFullScreen() {  
    if (!document.fullscreenElement && // alternative standard method  
        !document.mozFullScreenElement && !document.webkitFullscreenElement) {// current working methods  
        if (document.documentElement.requestFullscreen) {  
            document.documentElement.requestFullscreen();  
        } else if (document.documentElement.mozRequestFullScreen) {  
            document.documentElement.mozRequestFullScreen();  
        } else if (document.documentElement.webkitRequestFullscreen) {  
            document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);  
        }  
    } else {  
        if (document.cancelFullScreen) {  
            document.cancelFullScreen();  
        } else if (document.mozCancelFullScreen) {  
            document.mozCancelFullScreen();  
        } else if (document.webkitCancelFullScreen) {  
            document.webkitCancelFullScreen();  
        }  
    }  
}
</script>
<!-- 头部 -->
<div class="headCon" id ="headConId">
    <div class="head clearfix">
        <div class="loge"><img src="static/hinova/images/loge.png"></div>
       
        <ul class="nav" id = "tree">
        <c:forEach var="tree" items="${treeList }">
         <li class="nav">${tree.text}</li> 
            <%--  <li class="nav"> <a onclick="show()">${tree.text}</a></li>  --%>
            <%-- <c:forEach var="child" items="${tree.children}">
			<dd id="${child.dd_id}" url="${child.url}" type="${child.type}" text="${child.text}"><a style="cursor:pointer;"><i class="layui-icon">${child.iconCls }</i>&nbsp; ${child.text}</a></dd>            </c:forEach>
         --%></c:forEach>
        </ul>
        
       <ul class="headRight">
           <li class="layui-nav-item"><a href="javascript:;"
				class="admin-header-user"> <img
					src="${pageContext.request.contextPath}/static/images/base/default_head_img.jpg"
					style="width: 40px; height: 40px; border-radius: 100%;"> <span>${currentUser.trueName }</span>
					<span class="layui-nav-more"></span></a>
				</li>
           <%-- <li>
           <img class="pic4" src="static/hinova/images/icon3.png">
                <a href="${pageContext.request.contextPath}/user/logout">注销</a>
           </li> --%>
           <li>
                <img class="pic4" src="static/hinova/images/icon3.png">
               <a href="${pageContext.request.contextPath}/user/logout">注销</a>
          </li>
           
       </ul>
      
    </div>
    <div class="subNav" >
        <ul class="clearfix" id = "container" lay-filter="left_menu">
            <!-- <li >首页</li>
            <li><a onclick=go()>sdfsd</a></li>
            <li>班级</li>
            <li>考勤设备</li>
            <li class="active">访客记录</li>
            <li>监控大屏</li> -->
        </ul>
    </div>
</div>
<!-- 主体部分 -->
<div class="mainCon">
    <iframe src="device/facehistory/firstpage" frameborder="0" id="addPage" style="width:100%;height:100%;display:block"></iframe>
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
		, element = layui.element;  //元素操作
  //监听导航点击
  element.on('nav(left_menu)', function(elem){
  //  console.log(elem);
    //layer.msg(elem.text());
    var id = $(elem).attr("id");
	var url = "<%=path%>/"+ $(elem).attr("url");
	var text = $(elem).attr("text");
	var type = $(elem).attr("type");
	var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"+url+"'></iframe>";
	
	switch(type){
		case '0':
			//页面内  tab内部   添加  页面
			addTab(id,text,content);
			break;
		case '1':
			//新窗口打开。
			window.open(url);
			break;
		case '2':
			break;
	}
	
  });
//页面切换
  $(".subNav li").click(function(){
      var index = $(this).index();
      if(index == 0){
          $(".mainCon").css("background","#fff")
          $("#addPage").attr("src","url");
      }else if(index == 1){
          $(".mainCon").css("background","transparent")
          $("#addPage").attr("src","url");
      }
  })	
});

 $("#container").on("click", "li", function(){
	 
	 $(this).addClass("active").siblings().removeClass("active");
	 
	 var url = $(this).find("dd").attr("url");
	
	 $(".mainCon").css("background","#fff");
	 $("#addPage").attr("src",url);
	 
	 
	});
 
 
/* var ul = document.getElementById("container");
	
	ul.addEventListener("click", function(event){
		
	event = event || window.event;
	var target = event.target || event.srcElement;
	alert(target.dd);
	
	alert(target.innerHTML);
	
	
	
}, false);  */


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