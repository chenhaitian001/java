<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();   
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>鹤诺科技考勤平台</title>
<meta name="viewport" content="width=device-width"/>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<style>

.login_nav{text-align:center;margin: 1px 0px 30px 0px;}
.text{
	width:33%;
	text-align:right;
	display:inline;
	font-weight: lighter;
	font-size:30px;
	vertical-align: top;
	margin: 40px 0px 20px 10px;
	color: rgba(59, 59, 59, 1);
}

.button {
    border: 1px solid #bbb;
    border-radius: 1px;
    box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
    width: 300px;
    background: #f3f3f3;
    background: -moz-linear-gradient(top, #ffffff 0%, #e5e5e5 100%);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(100%,#e5e5e5));
    background: -webkit-linear-gradient(top, #ffffff 0%,#e5e5e5 100%);
    background: -o-linear-gradient(top, #ffffff 0%,#e5e5e5 100%);
    background: -ms-linear-gradient(top, #ffffff 0%,#e5e5e5 100%);
    background: linear-gradient(to bottom, #ffffff 0%,#e5e5e5 100%);
}
/* --------------  */
/*  custom-select  */
/* --------------  */
.custom-select {
  position: relative;
}
.custom-select select {
  margin:-4px 111px 0px 11px;
  background:none;
  border: 1px solid transparent;
  outline: none;
  /* Prefixed box-sizing rules necessary for older browsers */
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  /* Remove select styling */
  appearance: none;
  -webkit-appearance: none;
  /* Font size must the 16px or larger to prevent iOS page zoom on focus */
  /* General select styles: change as needed */
  font-family: helvetica, sans-serif;
    /*font-weight: bold;*/
  color: #080808;
  line-height:normal;
}

</style>

<link rel="stylesheet" href="assets/css/stylelogin.css" type="text/css" />
<link href="sysadmin/themes/css/login.css" rel="stylesheet" type="text/css" />
<script src="assets/js/jquery.min.js"></script>	

<script language="JavaScript">


function check(){
	var msg="${request.msg}";
	if(msg!=""){
	alert(msg);
	}
}
function autoLoad(){
		//document.loginForm.submit();
} 



function submitForm()
{
	if(checkform()){
		document.loginForm.submit();
	}
}

function  checkform()   
{   
	 
	//校验
	var loginId=document.getElementById("loginId").value;
	var Code=document.getElementById("Code").value;
	var Phoneno=document.getElementById("Phoneno").value;
	n = false;//全局变量，以便下面做判断
	 if(loginId==""){
		 document.getElementById("texttt").innerText="* 请填写幼儿姓名!";
		//document.getElementById("loginId").focus();
		return false;
	}
	 if(Code==""){
		 document.getElementById("texttt").innerText="* 请填写身份识别码!";
		//document.getElementById("loginId").focus();
		return false;
	}
	 
	 if(Phoneno==""){
		 document.getElementById("texttt").innerText="* 请填写家长手机号!";
		//document.getElementById("loginId").focus();
		return false;
	}
	 
	 $.ajax({
			url:  "checkCode.do",
			traditional : true,
			async:false, 
			type:'POST',
			dateType : "json",
			data:{
				flag:'10',
				Code:Code,
				phonecode:Phoneno,
				studentName:loginId,
			},
			
			success : function(resu) {
				if (resu.code == 200) {
				 n = true;
				 document.getElementById("texttt").innerText="*恭喜您!注册成功了";
				}else if(resu.code == 250){
					document.getElementById("texttt").innerText="* 手机号被占用,请重新输入手机号!";
					 n = false;
				}else if(resu.code == 300){
					document.getElementById("texttt").innerText="* 不存在的学生姓名!";
					 n = false;
				}else{
				 document.getElementById("texttt").innerText="* 错误的识别码!";
				 n = false;
				}
		    }
	 });
	 if(n) {
         return true;
    }else{
        return false;
    }
	 
	return true;
}

//回车登陆
function enterSubmit(e)  
{  
	 if (window.event)       
	 {         
	  keyCode=event.keyCode;      
	}else  
	{     
		keyCode=e.which; 	 /*ff support e.which*/   
	}        
	 if (keyCode==13)    
	 {        
		 submitForm();
	 } 
}  

</script>
<script language="javascript">
			(function($){
			$.extend({
			ms_DatePicker: function (options) {
						var defaults = {
							YearSelector: "#sel_year",
							MonthSelector: "#sel_month",
							DaySelector: "#sel_day",
							FirstText: "--",
							FirstValue: 0
						};
						var opts = $.extend({}, defaults, options);
						var $YearSelector = $(opts.YearSelector);
						var $MonthSelector = $(opts.MonthSelector);
						var $DaySelector = $(opts.DaySelector);
						var FirstText = opts.FirstText;
						var FirstValue = opts.FirstValue;
			
						// 初始化
						var str = "<option value=\"" + FirstValue + "\">" + FirstText + "</option>";
						$YearSelector.html(str);
						$MonthSelector.html(str);
						$DaySelector.html(str);
			
						// 年份列表
						var yearNow = new Date().getFullYear();
						var yearSel = $YearSelector.attr("rel");
						for (var i = yearNow; i >= 1900; i--) {
							var sed = yearSel==i?"selected":"";
							var yearStr = "<option value=\"" + i + "\" " + sed+">" + i + "</option>";
							$YearSelector.append(yearStr);
						}
			
						// 月份列表
						var monthSel = $MonthSelector.attr("rel");
						for (var i = 1; i <= 12; i++) {
							var sed = monthSel==i?"selected":"";
							var monthStr = "<option value=\"" + i + "\" "+sed+">" + i + "</option>";
							$MonthSelector.append(monthStr);
						}
			
						// 日列表(仅当选择了年月)
						function BuildDay() {
							if ($YearSelector.val() == 0 || $MonthSelector.val() == 0) {
								// 未选择年份或者月份
								$DaySelector.html(str);
							} else {
								$DaySelector.html(str);
								var year = parseInt($YearSelector.val());
								var month = parseInt($MonthSelector.val());
								var dayCount = 0;
								switch (month) {
									case 1:
									case 3:
									case 5:
									case 7:
									case 8:
									case 10:
									case 12:
										dayCount = 31;
										break;
									case 4:
									case 6:
									case 9:
									case 11:
										dayCount = 30;
										break;
									case 2:
										dayCount = 28;
										if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
											dayCount = 29;
										}
										break;
									default:
										break;
								}
								
								var daySel = $DaySelector.attr("rel");
								for (var i = 1; i <= dayCount; i++) {
									var sed = daySel==i?"selected":"";
									var dayStr = "<option value=\"" + i + "\" "+sed+">" + i + "</option>";
									$DaySelector.append(dayStr);
								}
							}
						}
						$MonthSelector.change(function () {
							BuildDay();
						});
						$YearSelector.change(function () {
							BuildDay();
						});
						if($DaySelector.attr("rel")!=""){
							BuildDay();
						}
					} // End ms_DatePicker
			});
			})(jQuery);
       </script>
        <script language="javascript">
            $(function() {
                $.ms_DatePicker({
                    YearSelector: "#select_year",
                    MonthSelector: "#select_month",
                    DaySelector: "#select_day"
                });
                $.ms_DatePicker({
                    YearSelector: "#select_year2",
                    MonthSelector: "#select_month2",
                    DaySelector: "#select_day2"
                });
            });
        </script> 

</head>
<body>

<div class="cont">
           <div class="login_header">
				<h1 class="login_logo">
					<a href="###"><img
						src="sysadmin/images/login/logo1.png" width="100" /></a>
				</h1>
				
			</div>
  
       <div class="login">
      <div class="login__form"> 
       <form  id="loginForm" name="loginForm"  action='checkLogin.do' method="post" accept-charset ='UTF-8'>
       <input type="hidden" name="oppenid" value="${oppenid }"/>
       <div class="login_nav">
          <label  class="login__row "><input type="text" required="required" name="loginId" id="loginId" class="login__input name"  placeholder="幼儿姓名" /></label >
       </div>
       
       <div class="login_nav">
        <label  class="login__row custom-select ">
		    <select  name="sex" id="sex" style="font-size:20px">
		        <option value="1">男生</option>
		        <option value="2">女生</option>
		    </select>
		</label >
		</div>
		
		<!-- <div class="login_nav">
          <label  class="login__row demo1 ">
                    <select id="select_year2" rel="2000" name="year2" style="font-size:20px"></select>
                    <select id="select_month2" rel="01" name="month2" style="font-size:20px"></select>
                    <select id="select_day2" rel="01" name="day2" style="font-size:20px"></select>
          </label >
       </div> -->
       <div class="login_nav">
          <label  class="login__row ">
          <input type="text" name="birthday" id="birthday" class="login__input name" placeholder="生日:yyyy-mm-dd"/>
        </label >
        </div>
       <div class="login_nav">
          <label  class="login__row ">
          <input type="text" name="Code" id="Code" class="login__input name" placeholder="识别码"/>
        </label >
        </div>
        
        <div class="login_nav">
        <label  class="login__row custom-select">
		    <select name="relation" id="relation" style="font-size:20px">
		        <option value="1">父亲</option>
		        <option value="2">母亲</option>
		        <option value="3">爷爷</option>
		        <option value="4">奶奶</option>
		        <option value="5">姥爷</option>
		        <option value="6">姥姥</option>
		        <option value="7">其他</option>
		    </select>
		</label >
		</div>
		<div class="login_nav">
          <label  class="login__row">
          <input type="text" name="Phoneno" id="Phoneno" class="login__input name" placeholder="手机号码"/>
        </label >
        </div>
        <button type="button" onclick="submitForm()" class="login__submit">注册</button>
        <div style="font-size:10px"><span id="texttt" style="color:#F00 ;font-size:22px;"></span></div> 
        </form>
        </div>
     </div> 
     <!-- <div id="login_footer">
			<p>鹤诺科技版权所有&copy;&nbsp;2018
			<p>
  </div> -->
</div>
  
  

</body>
</html>
