<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@page import="java.text.SimpleDateFormat"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String loginId = request.getParameter("loginIdd");
			String lastName = request.getParameter("lastName");
			String departmentcode = request.getParameter("departmentcode");
			String departmentname = request.getParameter("departmentname");
			Date date = new Date();
	    	SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
	    	SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
	    	String today = dfDate.format(date);
	    	String timeValue = dfTime.format(date);
%>

<!DOCTYPE html>
<html class="no-js">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
<title>打卡</title>
<link rel="stylesheet" href="assets/css/base.css">
<link rel="stylesheet" href="assets/css/mobiscroll.2.13.2.css">
<link rel="stylesheet" href="assets/css/prompt_wind.css">
<link rel="stylesheet" href="assets/css/app11.css">
<link rel="stylesheet" href="assets/css/flowChart.css">
<link rel="stylesheet" href="assets/css/style6.css">
<link rel="stylesheet" href="assets/css/demo.css">
	<!---地图脚本--->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Lvx9OrEMlRFTkIkGSIsTzXxNUB671Ew2"></script>
	
<script charset="utf-8" src="https://apis.map.qq.com/api/js?v=2.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77"></script>
 <style>
	#city_dummy{width:100%;height:25px;border:none;line-height:26px;text-indent:26px;margin:0 auto 10px auto; color:#bab9ba;
    font-size:9pt;background:url(assets/i/home2.png)8px no-repeat;background-size:15px;tap-highlight-color:rgba(0,0,0,0);-webkit-tap-highlight-color:rgba(0,0,0,0);
    -moz-tap-highlight-color:rgba(0,0,0,0);-o-tap-highlight-color:rgba(0,0,0,0);}
</style>
<style type="text/css">
	body{background: #EF7148;}
	img{cursor:pointer;-webkit-animation: scaleout 1.3s infinite ease-in-out;animation: scaleout 1.3s infinite ease-in-out;}
	@-webkit-keyframes scaleout {
	    0% { -webkit-transform: scale(1.0) }
	    100% {
	        -webkit-transform: scale(1.1);
	        opacity: 0;
	    }
	}
	@keyframes scaleout {
	    0% {
	        transform: scale(1.0);
	        -webkit-transform: scale(1.0);
	    } 100% {
	          transform: scale(1.1);
	          -webkit-transform: scale(1.1);
	          opacity: 0;
	      }
	}
	
</style>
<script language="javascript">
	//防止页面后退
	history.pushState(null, null, document.URL);
	window.addEventListener('popstate', function () {
	    history.pushState(null, null, document.URL);
	});
</script>
</head>
<body style="background:#f9f9f9;">
	<input type="hidden" value="<%=loginId %>" id="loginId" />
	<input type="hidden" value="<%=departmentcode %>" id="departmentcodeId" />
	<input type="hidden" value="<%=basePath %>" id="basePath" />
	
	<input type="hidden" value="" id="longValueId" />
	<input type="hidden" value="" id="latValueId" />
	
	<input type="button" value="" id="successBeforeWorkButton" style="display: none"/>
	<input type="button" value="" id="failedBeforeWorkButton" style="display: none"/>
	<input type="button" value="" id="successAfterWorkButton" style="display: none"/>
	<input type="button" value="" id="failedAfterWorkButton" style="display: none"/>
	<input type="button" value="" id="nopermissionButton" style="display: none"/>
	<input type="button" value="" id="checkTimeWorkButton" style="display: none"/>
	<div class="Signbox"> 
		<div class="Sign-top">
			<p class="nowtime" id="aaTimeId"><%=timeValue %></p>
			<p class="degree ellips"><%=today %></p> 
			<div class="Top_timebox">
			<p class="Top_timetitle1" id='queryMyScheduledId'>我的考勤</p>
			</div>
		</div>
		<div class="weizhi" id="weizhiBaidu">正在定位...</div>
		<div class="Mapbox">
			<div class="margins"></div>
		
			<iframe id="markPage"class="box" frameborder=0 scrolling="no" src=""></iframe>
		</div>
	<%-- <div class="flowChart">
		<!--左侧轴-->
		<div class="flowChart-left">
			<!--虚线-->
			<div class="dashed"></div>
		</div>
		<!--右侧内容-->
		<div class="flowChart-right">
		<div class="oneNode">
			<!--左侧小球-->
			<div class="check check-success">
				入园
			</div>
			<div class="tag-boder">
				<div class="tag">
				</div>
			</div>
			<!--右侧内容-->
			<div class="NodeDetail">
				<!--上-->
				<div class="NodeDetail-title">
					<!--头像-->
					<!--内容-->
					<div class="details">
						<h4>入园打卡时间09:00</h4>
					</div>
				</div>
				<!--中-->
				<div class="NodeDetail-content">
					<span class="badge">打卡时间:</span>
					<p>填写时间</p>
				</div>
				<!--下-->
				<div class="NodeDetail-footer">
					<span><%=today %></span>
				</div>
			</div>
		</div>
		<!--一个节点-->
		<div class="oneNode">
			<!--左侧小球-->
			<div class="check check-danger">
				离园
			</div>
			<div class="tag-boder">
				<div class="tag">
				</div>
			</div>
			<!--右侧内容-->
			<div class="NodeDetail">
				<!--上-->
				<div class="NodeDetail-title">
					<!--头像-->
					<!--内容-->
					<div class="details">
						<p>离园打卡时间17:00</p>
					</div>
				</div>
				<!--中-->
				<div class="NodeDetail-content">
					<span class="badge">打卡时间:</span>
					<p>填写时间</p>
					
				</div>
				<!--下-->
				<div class="NodeDetail-footer">
					<span><%=today %></span>

				</div>
			</div>
		</div>
		

	</div>
</div> 
	
	<%--<p class="matching" id="matchingID">未匹配到打卡地点</p>--%>	
	<span class="matching"  id="matchingID"></span>
</div>	
	<!-- <table class="dates">
	        <tr>
    		 <td>家长：<span id='myNameId'></span></td>
    		 <td>学生：<span class="rg" id='myDepartmentId'></span></td>
    		 </tr>
	</table> -->
	<iframe id="geoPage" width=0 height=0 frameborder=0  style="display:none;" scrolling="no"
    src="https://apis.map.qq.com/tools/geolocation?key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&referer=myapp&effect=zoom">
	</iframe> 
	<div id = "ifshow">
	<div style="height:30px; margin:10px auto; border-left:1px   solid  #366595; position:absolute;  left:15px;">
                    <ul class="time-vertical">
                        <li class="cicle1"></li>
                        <li class="cicle4"></li>
                    </ul>
 
                </div>
                <div  class="timeright">
                <div style="float:left;">
                    <p>签到时间：<span id='amTime'></span></p>
                </div>
                <div style="float:left;">
                    <p1>家长：<span id='amRelation'></span></p1>
                </div>
                </div>
                <!-- <div class="timeright1">
                    <p>签退时间：<span id='pmTime'></p>
                </div> -->
                <div  class="timeright1">
                <div style="float:left;">
                    <p>签退时间：<span id='pmTime'></span></p>
                </div>
                <div style="float:left;">
                    <p1>家长：<span id='pmRelation'></span></p1>
                </div>
                </div>
	<!-- <div class="Sign_btnbox">
		  <button id="btn_1" class="">入园打卡</button>
    	  <button id="btn_2"  class="">离园打卡</button>
	</div> -->
	</div>
	<div id="btn_3" style="width:150px;height:150px;background:url('assets/i/606120849763069500.png') center center no-repeat;margin:2.5rem auto 0rem auto;">
	<a  onclick="daka()"><img src="assets/i/606120849763069500.png;" height="150" width="150"></a>
	</div>
	<!-- <div class="boxbutton">
     <a  class="btnbox" id='queryMyScheduledId'>查看我的考勤</a>
	</div> -->
	<!--jquery类库-->
	<script src="assets/js/jquery.min.js"></script>
	<!--选择器脚本-->
	<script type="text/javascript" src="assets/js/mobiscroll.2.13.2.js"></script>
	<script type="text/javascript" src="assets/js/Roll.js"></script>
	<!--提示窗脚本-->
	<script src="assets/js/prompt_wind.js"></script>
	<!-- <script src="assets/js/app.js"></script> -->
	<script type="text/javascript">
	$(function(){
		$('#queryMyScheduledId').click(function(e){//查看我的排班
			var date = '${loginIdd}';
			window.location.href='checkinfo.jsp?loginIdno='+date;
		});
	});
	</script>

<script type="text/javascript">
 var count = 0;	
var loginidValue='<%=loginId%>';
 <%-- var nameValue = '<%=lastName%>'; --%>
 var nameValue = '学生';
var departmentNameValue = '<%=departmentname%>';
var departmentCodeValue = '<%=departmentcode%>';
/* var departmentCodeValue = '10000423';  */
$('#nopermissionButton').click(function(){
 swal({
          title: "亲，你暂无打卡权限哦~",
          text: "请联系权限管理员开放权限吧",
          imageUrl: 'assets/i/iocna.png'
      });
});
//查询是否有权限打卡
$(function(){
	
	$('#successBeforeWorkButton').click(function(){
		swal("打卡成功",name, "success");
	});

	$('#failedBeforeWorkButton').click(function(){
		swal("打卡失败", "请重新打卡", "error");
	});
	
	$('#successAfterWorkButton').click(function(){
		swal("打卡成功",name, "success");
	});

	$('#failedAfterWorkButton').click(function(){
		swal("打卡失败", "请重新打卡", "error");
	});
	$('#checkTimeWorkButton').click(function(){
		swal({
	          title: "亲，休息3分钟后再来哦~",
	          text: "连续打卡提示！",
	          imageUrl: 'assets/i/logo1.png'
	      });
	});
	var ifhide = '${ifshow}';
	if(ifhide){
		$('#ifshow').hide();
	}
	// 把姓名部门显示放在外面
	$('#amTime').text('${amTime}');
	$('#pmTime').text('${pmTime}');
	$('#amRelation').text('${amRelation}');
	$('#pmRelation').text('${pmRelation}');
	$('#myDepartmentId').text(departmentNameValue);
					var pathBath = $('#basePath').val();
					$('#loginId').val(loginidValue);
					/* if (window.navigator.geolocation) {
					  var options = {
					    enableHighAccuracy: true,
					  };
					  window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
					} else {
					  alert("浏览器不支持html5来获取地理位置信息");
					} */
					
					var loc;
					window.addEventListener('message', function(event) {
					    // 接收位置信息
					    
					    	 loc = event.data; 
						    if(loc  && loc.module == 'geolocation') { //定位成功,防止其他应用也会向该页面post信息，需判断module是否为'geolocation'
						    	//显示地图
						      //   	var markUrl = 'https://apis.map.qq.com/tools/routeplan/eword=园区打卡地点&zoombutton=0&footdetail=0&topbar=0&transport=3&editstartbutton=0&epointx=116.572065&epointy=39.790521?referer=myapp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77';
						    	 
						         	var markUrl ='test.jsp'
						    
						    //给位置展示组件赋值
				            document.getElementById('markPage').src = markUrl;
						    } else { //定位组件在定位失败后，也会触发message, event.data为null
						        alert('定位失败'); 
						    }
						      var addr=loc.province+loc.city+loc.addr;
						      $("#weizhiBaidu").text(addr);
					    
					    
					    
					}, false); 
					 //设置12s超时，防止定位组件长时间获取位置信息未响应
					//为防止定位组件在message事件监听前已经触发定位成功事件，在此处显示请求一次位置信息
        			document.getElementById("geoPage").contentWindow.postMessage('getLocation', '*');
         
			        //设置60s超时，防止定位组件长时间获取位置信息未响应
			        setTimeout(function() {
			            if(!loc) {
			                //主动与前端定位组件通信（可选），获取粗糙的IP定位结果
			            document.getElementById("geoPage")
			                .contentWindow.postMessage('getLocation.robust', '*');
			            }
			        }, 60000); //60s为推荐值，业务调用方可根据自己的需求设置改时间，不建议太短
					
					$('#city').change(function(){
						$("#city_dummy").val($("#city").find("option:selected").text());
						//alert($("#city_dummy").val());
						var tttttV = $("#city_dummy").val();
							if(tttttV==null || tttttV==''){
								$('#btn_1').attr('class','dised');
								$('#btn_2').attr('class','dised');
							}else{
								$('#btn_1').attr('class','shied');
								$('#btn_2').attr('class','Puning');
							}
					});
					
					initPunchEvent();
					//alert($("#city_dummy").val());
			
});

function initPunchEvent() {
	//上班打卡
	$('#btn_1').click(function(){
		//alert('btn_1');
		//alert($("#city").find("option selected").text());
		var oppenid = $('#oppenid').val(); 
		var checkinAddressIdValue = $('#city').val();
		//alert(checkinAddressIdValue);
		//alert(loginidValue);
		var longitudebtn1 = $('#longValueId').val();
		var latitudebtn1 = $('#latValueId').val();
		var loginidValue=$("#loginId").val();
		//alert('longitudebtn1 btn1 '+longitudebtn1);
 	 	//alert('latitudebtn1 btn1 '+latitudebtn1);
		$.ajax({
			url:  "config/go_work.do",
			traditional : true,
    		type:'POST',
			dateType : "json",
			data:{
				flag:'10',
				loginid:loginidValue,
				checkinAddressId:checkinAddressIdValue,
				longitude : longitudebtn1,
				latitude : latitudebtn1,
				departmentcode:	departmentCodeValue,
				username:'<%=lastName%>',
				departmentName:'<%=departmentname%>',
				oppenidno:'${oppenid}'
				
			},
			
			success : function(resu) {
    			if (resu.code == 200) {
    				var newamTime = resu.newAmTime;
    				var newpmTime = resu.newPmTime;
    				$('#amTime').text(newamTime);
    				$('#pmTime').text(newpmTime);
    				$('#successBeforeWorkButton').click();
    				
				}else{
					$('#failedBeforeWorkButton').click();
				}
		    },
			error:function(xhr, errorText, errorStatus){
				$('#failedBeforeWorkButton').click();
			},
		});
		
	});
	
	//下班打卡
	$('#btn_2').click(function(){
		//alert('btn_2');
		//alert($("#city").find("option selected").text());
		var oppenid = $('#oppenid').val(); 
		var loginidValue=$("#loginId").val();
		var checkinAddressIdValue = $('#city').val();
		//alert(checkinAddressIdValue);
		//alert(loginidValue);
		var longitudebtn1 = $('#longValueId').val();
		var latitudebtn1 = $('#latValueId').val();
		//alert('longitudebtn1 btn1 '+longitudebtn1);
 	 	//alert('latitudebtn1 btn1 '+latitudebtn1);
		$.ajax({
			url:  "config/leave_work.do",
			traditional : true,
    		type:'POST',
			dateType : "json",
			data:{
				flag:'20',
				loginid:loginidValue,
				checkinAddressId:checkinAddressIdValue,
				longitude : longitudebtn1,
				latitude : latitudebtn1,
				departmentcode:	departmentCodeValue,
				username:'<%=lastName%>',
				departmentName:'<%=departmentname%>',
				oppenidno:'${oppenid}'
				
			},
			success : function(resu) {
    			if (resu.code == 200) {
    				$('#successAfterWorkButton').click();
				}else{
					$('#failedAfterWorkButton').click();
				}
		    },
			error:function(xhr, errorText, errorStatus){
				$('#failedAfterWorkButton').click();
			},
		});
		
	});
}
/* var ele=documen.getElementById('daka1');//先获取元素对象，再绑定onclick事件
ele.onclick=function(){
    alert('这是onclick事件');
}; */
function daka(){
	//alert('btn_1');
	//alert($("#city").find("option selected").text());
	var oppenid = $('#oppenid').val(); 
	var checkinAddressIdValue = $('#city').val();
	//alert(checkinAddressIdValue);
	//alert(loginidValue);
	var longitudebtn1 = $('#longValueId').val();
	var latitudebtn1 = $('#latValueId').val();
	var loginidValue=$("#loginId").val();
	//alert('longitudebtn1 btn1 '+longitudebtn1);
	 	//alert('latitudebtn1 btn1 '+latitudebtn1);
	$.ajax({
		url:  "config/go_work.do",
		traditional : true,
		type:'POST',
		dateType : "json",
		data:{
			flag:'10',
			loginid:loginidValue,
			checkinAddressId:checkinAddressIdValue,
			longitude : longitudebtn1,
			latitude : latitudebtn1,
			departmentcode:	departmentCodeValue,
			username:'<%=lastName%>',
			departmentName:'<%=departmentname%>',
			oppenidno:'${oppenid}'
			
		},
		
		success : function(resu) {
			if (resu.code == 200) {
				var newamTime = resu.newAmTime;
				var newpmTime = resu.newPmTime;
				var newamrelation = resu.newAmRelation;
				var newpmrelation = resu.newPmRelation;
				$('#amTime').text(newamTime);
				$('#pmTime').text(newpmTime);
				$('#amRelation').text(newamrelation);
				$('#pmRelation').text(newpmrelation);
				$('#ifshow').show();
				$('#successBeforeWorkButton').click();
			}else if(resu.code == 250){
				$('#checkTimeWorkButton').click();
				$('#ifshow').show();
			}else{
				$('#failedBeforeWorkButton').click();
			}
	    },
		error:function(xhr, errorText, errorStatus){
			$('#failedBeforeWorkButton').click();
		},
	});
}
function dituFun(){
	count=0;
	//获取当前所在地理位置，调用一次即重新定位一次，定位数据比较精确。
	//document.getElementById("geoPage").contentWindow.postMessage("getLocation", "*");
	//更新时间
	/* var myDate = new Date(); 
	var hour =myDate.getHours();
	if(hour.length==1){
		hour = '0'+hour;
	}
	var minute = myDate.getMinutes();
	alert(minute.length);
	if(minute.length==1){
		minute = '0'+minute;
	}
	$('#aaTimeId').html(hour+':'+minute); */
	
} 
function mapRefresh(){
	count=0;
	document.getElementById("geoPage").contentWindow.postMessage("getLocation", "*");
  
}
</script>
</body>
</html>