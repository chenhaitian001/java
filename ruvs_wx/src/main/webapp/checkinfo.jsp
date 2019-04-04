<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@page import="java.text.SimpleDateFormat"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			//String loginid = request.getParameter("loginidStr");
			//String userticket = request.getParameter("userticket");
			
			String loginId = request.getParameter("loginId");
			String lastName = request.getParameter("lastName");
			String departmentcode = request.getParameter("departmentcode");
			String departmentname = request.getParameter("departmentname");
			
			Date date = new Date();
	    	SimpleDateFormat dfDate = new SimpleDateFormat("yyyyMMdd");
	    	SimpleDateFormat lineDate = new SimpleDateFormat("yyyy-MM-dd");
	    	String today = dfDate.format(date);
	    	String lineToday = lineDate.format(date);
	    	String shortToday = today.substring(6, 8);
%>
<!-- 查看我的考勤 -->
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>育智云</title>
<!--  <link rel="stylesheet" href="assets/css/amazeui.min.css"> -->
<link rel="stylesheet" href="assets/css/base.css">
<link rel="stylesheet" href="assets/css/mobiscroll.2.13.2.css">
<link rel="stylesheet" href="assets/css/app11.css">
<script src="assets/js/jquery.min.js"></script>
<script type="text/javascript">
        //方法一：
        //获取url中的参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }
 </script>

</head>

<body>
	<div class="Topbox">
		 <!-- <a href="#" onClick="javascript :history.go(-1);" class="Top_back">返回</a>  -->
		<div class="Top_title">考勤日历</div>
		<div class="Top_timeimg">
			<input id="demo3" class="Choice_time"
				placeholder="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日期" />
			<!--<p class="Choice_time">2016年&nbsp;<span>7</span>月<spsan class="day">25</spsan></p>-->
			<p class="Top_timetitle">选择时间</p>
		</div>
	</div>
	<input type="hidden" value="<%=loginId %>" id="loginId" />
	<div class="calendar">
		<div class="Refresh">
			<img src="assets/svg/circles.svg">
		</div>
		<ul class="week">
			<li class="wk">一</li>
			<li class="wk">二</li>
			<li class="wk">三</li>
			<li class="wk">四</li>
			<li class="wk">五</li>
			<li class="wk">六</li>
			<li class="wk">日</li>
		</ul>
		<ul class="Datebox">

		</ul>

		
		<!-- <div class="Labelbox">
			<p class="Labelist1">正常</p>
			<p class="Labelist2">异常</p>
			<p class="Labelist3">上月正常</p> <p class="Labelist4">上月异常</p>
		</div> -->
	<div class="atdce_box">
			<span class="name" id ="works"></span> <span class="number" id = "rests"></span>
	</div>
	<div id = "ifshow">
	  <div style="height:30px; margin:47px auto; border-left:1px   solid  #366595;  position:absolute;  left:15px;">
                    <ul class="time-vertical">
                        <li class="cicle5"></li>
                        <li class="cicle6"></li>
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
    </div>
 </div>
	<div class="dayPunch" id='hidenTextId1'>
		<p class="dats">
			<!-- <span>星期六</span> -->
			<span id='timeSpanId'><%=lineToday %></span>
		</p>
		<p class="PunchState">
			考勤<span class="state1" id='checkinTextId'></span> <span
				class="state2" style="display: none">异常</span>
		</p>
	</div>

	<ul class="Punchdetails" id='hidenTextId2'>
		<li><span class="ID" id='amBeforeWorkAddressId'></span> <span
			class="time" id='amBeforeWorkTimeId'></span> <!-- <span class="sta1">正常</span> --></li>
		<!--  <li><span class="ID" id='pmBeforeWorkAddressId'></span>  <span class="time" id='pmBeforeWorkTimeId'></span> -->
		<!-- <span class="sta2">缺卡</span> -->
		</li>
		<li><span class="ID" id='pmAfterWorkAddressId'></span> <span
			class="time" id='pmAfterWorkTimeId'></span> <!-- <span class="sta2">异常</span> --></li>
	</ul>
	<!-- <div class="boxbutton">
     <a  class="btnbox" id='queryMyScheduledId'>查看我的排班</a>
</div> -->


	<!--jquery类库-->
	
	<!--选择器脚本-->
	<script type="text/javascript" src="assets/js/mobiscroll.2.13.2.js"></script>
	<script type="text/javascript" src="assets/js/Roll.js"></script>
	<script type="text/javascript" src="assets/js/checkinfo.js"></script>
	<script type="text/javascript">
var wholeMonthData;
var loginidValue= getUrlParam('loginIdno');
/* var loginidValue='00002440'; */ 
var nameValue = '<%=lastName%>';
var departmentNameValue = '<%=departmentname%>';
var departmentCodeValue = '<%=departmentcode%>';
/* var departmentCodeValue = '10000092'; */

$(function(){
  $(".Datebox").click(function (e){
    cal(e);
    
  })
});

function cal(e){
    var obj = e.srcElement || e.target;
    if(obj.nodeName != "DIV"){return}
    var valuebox = obj.innerText;
    var jqObj=$(obj);
    $('.Datebox div').removeClass('addBox');
    jqObj.addClass('addBox');
    
    //alert('点击');
    $('#hidenTextId1').show();
	$('#hidenTextId2').show();
	
	 $('#amBeforeWorkTimeId').text('');//上午上班时间
	 $('#pmAfterWorkTimeId').text('');//下午上班时间
	
	//alert(jqObj.text());
	var dateInput = $('#demo3').val();
	//alert(dateInput);
	if(dateInput){//选择了日期
		//alert('选择了日期');
		var timeText =dateInput.substring(0,8).trim()+jqObj.text().trim();
		//alert(timeText);
		 $('#timeSpanId').text(timeText);
		 $.each(wholeMonthData.queryCheckinVOList,function(index,element){
				//alert(element.checkinDate.length);
				//alert(timeText);
				 if(element.checkinDate==timeText){//取选择到的日期考勤数据
					// alert(element.checkinResultFlag);
					 if(element.checkinResultFlag=='S'){//正常
						 //$('#checkinTextId').text('正常');
						 $('#checkinTextId').attr('class','state1');
					// alert(element.amBeforeWorkPlace);
						 $('#amBeforeWorkAddressId').text(element.amBeforeWorkPlace);//上午上班地点
						 $('#pmBeforeWorkAddressId').text(element.pmBeforeWorkPlace);//下午上班地点
						 $('#pmAfterWorkAddressId').text(element.pmAfterWorkPlace);//下午上班地点
						 
						// alert(element.amBeforeWorkTime);
						/*  $('#amBeforeWorkTimeId').text(element.amBeforeWorkTime);//上午上班时间
						 $('#pmBeforeWorkTimeId').text(element.pmBeforeWorkTime);//下午上班时间
						 $('#pmAfterWorkTimeId').text(element.pmAfterWorkTime);//下午上班时间 */
						 //alert(element.amBeforeWorkTime);
						
							 $('#amBeforeWorkTimeId').text(element.amBeforeWorkTime);//上午上班时间
						 
						
						 
							 $('#pmBeforeWorkTimeId').text(element.pmBeforeWorkTime);//下午上班时间
						 
						 
							 $('#pmAfterWorkTimeId').text(element.pmAfterWorkTime);//下午上班时间
						 
						 return false;//跳出循环
					 }else if(element.checkinResultFlag=='E'){//异常
						 
						 $('#checkinTextId').text(element.checkinResultString);
						 $('#checkinTextId').attr('class','state2');
						 $('#amBeforeWorkAddressId').text(element.amBeforeWorkPlace);//上午上班地点
						 $('#pmBeforeWorkAddressId').text(element.pmBeforeWorkPlace);//下午上班地点
						 $('#pmAfterWorkAddressId').text(element.pmAfterWorkPlace);//下午上班地点
						 
						 /* $('#amBeforeWorkTimeId').text(element.amBeforeWorkTime);//上午上班时间
						 $('#pmBeforeWorkTimeId').text(element.pmBeforeWorkTime);//下午上班时间
						 $('#pmAfterWorkTimeId').text(element.pmAfterWorkTime);//下午上班时间 */
						 
							 $('#amBeforeWorkTimeId').text(element.amBeforeWorkTime);//上午上班时间
						 
						
						
							 $('#pmBeforeWorkTimeId').text(element.pmBeforeWorkTime);//下午上班时间
						 
						
							 $('#pmAfterWorkTimeId').text(element.pmAfterWorkTime);//下午上班时间
						 
						 return false;//跳出循环
					 }
				 }else if(element.checkinResultFlag==null){
					 //alert('未来日期');
					 $('#hidenTextId1').hide();
					$('#hidenTextId2').hide();
					return false;//跳出循环
				 }
				 //alert('index '+index+'  element  '+element.checkinDate);
			 });
		 
		 
	}else{//没有选择日期，查看当前月份
		//alert('没有选择日期，查看当前月份');
		$.ajax({
			url:'config/leave_work.do',
			type:'POST',
			async:false, 
			traditional : true,
			dateType : "json",
			data:{
				loginid:loginidValue,
			},
			success:function(response,status,xhr){
				wholeMonthData = response;
			}
		});
	
	
	
		var timeText = '<%=lineToday.substring(0, 8).trim()%>'+jqObj.text().trim();
		 $('#timeSpanId').text(timeText);
		 //alert(wholeMonthData.queryCheckinVOList);
		// alert(timeText);
		 //alert(wholeMonthData.queryCheckinVOList.length);
		 $.each(wholeMonthData.queryCheckinVOList,function(index,element){
			//alert('checkinDate:'+element.checkinDate);
			//alert('checkinResultFlag:'+element.checkinResultFlag);
			 if(element.checkinDate==timeText){//取选择到的日期考勤数据
				// alert(element.checkinResultFlag);
				 if(element.checkinResultFlag=='S'){//正常
					 //$('#checkinTextId').text('正常');
					 $('#checkinTextId').attr('class','state1');
				   // alert(element.amBeforeWorkTime);
					 $('#amBeforeWorkAddressId').text(element.amBeforeWorkPlace);//上午上班地点
					 $('#pmBeforeWorkAddressId').text(element.pmBeforeWorkPlace);//下午上班地点
					 $('#pmAfterWorkAddressId').text(element.pmAfterWorkPlace);//下午上班地点
				    $('#amBeforeWorkTimeId').text(element.amBeforeWorkTime);//上午上班时间
				    $('#pmBeforeWorkTimeId').text(element.pmBeforeWorkTime);//下午上班时间
				   $('#pmAfterWorkTimeId').text(element.pmAfterWorkTime);//下午上班时间
					 return false;//跳出循环
				 }else if(element.checkinResultFlag=='E'){//异常
					 
					 $('#checkinTextId').text(element.checkinResultString);
					 $('#checkinTextId').attr('class','state2');
					 $('#amBeforeWorkAddressId').text(element.amBeforeWorkPlace);//上午上班地点
					 $('#pmBeforeWorkAddressId').text(element.pmBeforeWorkPlace);//下午上班地点
					 $('#pmAfterWorkAddressId').text(element.pmAfterWorkPlace);//下午上班地点
					 
					 /* $('#amBeforeWorkTimeId').text(element.amBeforeWorkTime);//上午上班时间
					 $('#pmBeforeWorkTimeId').text(element.pmBeforeWorkTime);//下午上班时间
					 $('#pmAfterWorkTimeId').text(element.pmAfterWorkTime);//下午上班时间 */
					 
						 $('#amBeforeWorkTimeId').text(element.amBeforeWorkTime);//上午上班时间
					 
					
					
						 $('#pmBeforeWorkTimeId').text(element.pmBeforeWorkTime);//下午上班时间
					 
					 
						 $('#pmAfterWorkTimeId').text(element.pmAfterWorkTime);//下午上班时间
					 
					 return false;//跳出循环
				 }
			 }else if(element.checkinResultFlag==null){
				 //alert('未来日期');
				 $('#hidenTextId1').hide();
				$('#hidenTextId2').hide();
				return false;//跳出循环
			 }
			 //alert('index '+index+'  element  '+element.checkinDate);
		 });
		
	}

}


$(function(){
	 $('#loginId').val(loginidValue);
	 //等人员加载成功后 才去查看考勤
	$.ajax({
		url:'config/leave_work.do',
		type:'POST',
		traditional : true,
		dateType : "json",
		data:{
			loginid:loginidValue,
		},
		success:function(response,status,xhr){
			var rests =response.rests;
			var works = response.works;
			var ifhide = response.ifshow;
			if(!ifhide){
				$('#ifshow').hide();
			}
			var newamTime = response.newAmTime;
			var newpmTime = response.newPmTime;
			var newamrelation = response.newAmRelation;
			var newpmrelation = response.newPmRelation;
			$('#amTime').text(newamTime);
			$('#pmTime').text(newpmTime);
			$('#amRelation').text(newamrelation);
			$('#pmRelation').text(newpmrelation);
			
			$('#rests').text("休息天数："+rests+" 天 。");
			$('#works').text("本月出勤天数："+works+" 天 ，");
			//alert(response.firstMonthWeek);
			//var responseData = eval("("+response.queryCheckinVOList+")");
			
			//var count=1;//，填充的格子计数
			var optionStr='';
			var firstMonthWeek = response.firstMonthWeek;
			$('.Datebox').empty();//先清空格子
			for(var i=1;i<firstMonthWeek;i++){
				/* $('.Datebox').append('<li><p class="margins"></p><div class="box pass"><span class="punched1"></span> </div>') */
				$('.Datebox').append('<li><p class="margins"></p><div class="box pass"> </div></li>');
				//count=count+1;
			}
			var responseData = response.queryCheckinVOList;
			$.each(responseData,function(index,element){
				if(element.checkinResultFlag=='S'){//正常
					$('.Datebox').append('<li><p class="margins"></p>  <div class="box now">'+element.shortCheckinDate+'<span class="punch1"></span> </div> </li>')
				}else if(element.checkinResultFlag=='E'){//异常
					$('.Datebox').append('<li><p class="margins"></p>  <div class="box now">'+element.shortCheckinDate+'<span class="punch2"></span> </div> </li>')
				}else if(element.checkinResultFlag=='W'){//周末
					$('.Datebox').append('<li><p class="margins"></p>  <div class="box pass">'+element.shortCheckinDate+' </div> </li>')
				}else if(element.checkinDate=='<%=today%>'){//今天的
					 //
					$('.Datebox').append(' <li><p class="margins"></p> <div class="box today">'+'<%=shortToday%>'+'<span class="punch1"></span> </div></li>');
				}else{//
					$('.Datebox').append('<li><p class="margins"></p>  <div class="box now">'+element.shortCheckinDate +'  </div> </li>')
				}
				//count=count+1;
			});

		},
		
	});
});

$(function(){
	$('#hidenTextId1').hide();
	$('#hidenTextId2').hide();
	
	$('#queryMyScheduledId').click(function(e){//查看我的排班
		//alert('点击');
		$('#queryMyScheduledId').attr('href','mySchedule.jsp?loginId='+$('#loginId').val());
	});
	
	
	
	
	//选择查询 月份后的事件
	 $('#demo3').change(function(e){
		 //alert('选择日期');
		 //var loginidValue='00000049';//$('#loginId').val(loginidValue);
		 var loginidValue =$('#loginId').val();
		 var timeText =$('#demo3').val();
			//alert(timeText);
			 $('#timeSpanId').text(timeText);
			//加载
			$.ajax({
				url:'config/leave_work.do',
				type:'POST',
				traditional : true,
				dateType : "json",
				data:{
					queryWholeMonthTime:timeText,
					loginid:loginidValue,
				},
				success:function(response,status,xhr){
					wholeMonthData = response;
					var rests =response.rests;
					var works = response.works;
					
					var newamTime = response.newAmTime;
					var newpmTime = response.newPmTime;
					var newamrelation = response.newAmRelation;
					var newpmrelation = response.newPmRelation;
					$('#amTime').text(newamTime);
					$('#pmTime').text(newpmTime);
					$('#amRelation').text(newamrelation);
					$('#pmRelation').text(newpmrelation);
					
					$('#rests').text("休息天数："+rests+" 天 。");
					$('#works').text("本月出勤天数："+works+" 天 ，");
					//alert(wholeMonthData);
					//alert(response.firstMonthWeek);
					//var responseData = eval("("+response.queryCheckinVOList+")");
					
					//var count=1;//，填充的格子计数
					var optionStr='';
					var firstMonthWeek = response.firstMonthWeek;
					$('.Datebox').empty();//先清空格子
					for(var i=1;i<firstMonthWeek;i++){
						/* $('.Datebox').append('<li><p class="margins"></p><div class="box pass"><span class="punched1"></span> </div></li>'); */
						$('.Datebox').append('<li><p class="margins"></p><div class="box pass"> </div></li>');
						//count=count+1;
					}
					var responseData = response.queryCheckinVOList;
					$.each(responseData,function(index,element){
						if(element.checkinResultFlag=='S'){//正常
							$('.Datebox').append('<li><p class="margins"></p>  <div class="box now">'+element.shortCheckinDate+'<span class="punch1"></span> </div> </li>')
						}else if(element.checkinResultFlag=='E'){//异常
							$('.Datebox').append('<li><p class="margins"></p>  <div class="box now">'+element.shortCheckinDate+'<span class="punch2"></span> </div> </li>')
						}else if(element.checkinResultFlag=='W'){//周末
							$('.Datebox').append('<li><p class="margins"></p>  <div class="box pass">'+element.shortCheckinDate+' </div> </li>')
						}else if(element.checkinDate=='<%=today%>'){//今天的
							 //
							$('.Datebox').append(' <li><p class="margins"></p> <div class="box today">'+'<%=shortToday%>'+'<span class="punch1"></span> </div></li>');
						}else{//
							$('.Datebox').append('<li><p class="margins"></p>  <div class="box now">'+element.shortCheckinDate +'  </div> </li>')
						}
						//count=count+1;
					});

				},
				
			});
			
	 });
	
});
</script>

</body>
</html>