<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ include file="WEB-INF/jsp/includes/header.jsp"%>

	  <style type="text/css">

		  body{
			  background:#fff;
			  min-height: 685px;min-width:900px;
			  position: relative;
			  overflow-x: hidden;
			  overflow-y: auto;
              padding: 0px;
              margin: 0px;
		  }
		  .login_l{
			  background: url("static/img/login/login_lbg.png") no-repeat;
		  }
		  input{
			  padding:5px;
			  letter-spacing: 0.1em;
			  background: rgba(0,0,0,0);
			  width:146px;
			  height:29px;
			  border:solid 1px #DBDBDB;
			  border-radius:3px;
			  width:293px;
			  height:45px;
		  }



		  .yuan{
			  z-index:999;
			  width: 100px;
			  height: 100px;
			  background-color:red;
			  border-radius: 50%;
			  -moz-border-radius: 50%;
			  -webkit-border-radius: 50%;
			  position: absolute;

			  background: -webkit-linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3)); /* Safari 5.1 - 6.0 */
			  background: -o-linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3)); /* Opera 11.1 - 12.0 */
			  background: -moz-linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3)); /* Firefox 3.6 - 15 */
			  background: linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3));  /* 标准的语法 */
		  }

		  #login_form_div{
			  padding-top:34px;
			  width:420px;
			  height:450px;
			  -moz-box-shadow:0px 0px 15px #68AEFF;
			  -webkit-box-shadow:0px 0px 15px #68AEFF;
			  box-shadow:0px 0px 15px #68AEFF;
			  border-radius:5px;
			  float: right;
		  }


		  input,button,select,textarea{outline:none}
		  #login_form_div div{
			  margin-top:40px;
			  text-align: center;
		  }
		  #login_form_div .login_title{
			  font-size: 30px;
			  color: #627EC9;
		  }
		  #login_form_div a{

			  display:inline-block;
			  font-weight: bolder;
			  font-size: 24px;
			  color:#fff;
			  cursor: pointer;
			  width:133px;
			  height:46px;
			  border-radius:3px;
			  line-height: 46px;
			  background: #4A8BE3;
		  }

		  #login_form_div a:hover{
			  box-shadow: 0 0 10px #ff0;
		  }

		  #login_form_div a:active {
			  box-shadow: 0 0 10px #f0f;
		  }

		  #login_form_div a:nth-child(2){

			  margin-left:25px;
			  background: #848290;
		  }


		  input{
			  letter-spacing: 0.1em;
			  border:solid 1px #DBDBDB;
			  border-radius:3px;
			  width:293px;
			  height:45px;
			  padding-left:36px;
		  }

		  input[name='loginName']{background:url('static/img/login/icon_user_name.png') no-repeat 6px 0 ;}
		  input[name='password']{background:url('static/img/login/icon_password.png') no-repeat 6px 0 ;}


		  #login_form_div #msg{
			  font-size:14px;
			  color:#faa;
			  margin-top:15px;
			  letter-spacing: 0.1em;
		  }


		  #bottom_div{
			  position:absolute;
			  bottom:0px;
			  width:1600px;
			  height:218px;
			  margin:0 auto;
			  background: url("static/img/login/login_bottom_bg.png") no-repeat;

		  }

	  </style>
<script type="text/javascript">
    if(top.location.href!=location.href){
        top.location.href=location.href;
    }
</script>


  </head>
  <body >


  <div class="yuan" style="width:80px;height:80px;left:10%;top:10%;
background: -webkit-linear-gradient(90deg, rgba(226, 234, 251,0.1), rgba(91, 163, 254,0.1)); /* Safari 5.1 - 6.0 */
background: -o-linear-gradient(90deg, rgba(226, 234, 251,0.1), rgba(91, 163, 254,0.1)); /* Opera 11.1 - 12.0 */
background: -moz-linear-gradient(90deg, rgba(226, 234, 251,0.1), rgba(91, 163, 254,0.1)); /* Firefox 3.6 - 15 */
background: linear-gradient(90deg, rgba(226, 234, 251,0.1), rgba(91, 163, 254,0.1));
"></div>
  <div class="yuan" style="width:140px;height:140px;top:-70px;left:40%;
background: -webkit-linear-gradient(90deg, rgba(226, 234, 251,0.15), rgba(91, 163, 254,0.15)); /* Safari 5.1 - 6.0 */
background: -o-linear-gradient(90deg, rgba(226, 234, 251,0.15), rgba(91, 163, 254,0.15)); /* Opera 11.1 - 12.0 */
background: -moz-linear-gradient(90deg, rgba(226, 234, 251,0.15), rgba(91, 163, 254,0.15)); /* Firefox 3.6 - 15 */
background: linear-gradient(90deg, rgba(226, 234, 251,0.15), rgba(91, 163, 254,0.15));"></div>
  <div class="yuan" style="width:230px;height:230px;bottom:-00px;left:80%;
background: -webkit-linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3)); /* Safari 5.1 - 6.0 */
background: -o-linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3)); /* Opera 11.1 - 12.0 */
background: -moz-linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3)); /* Firefox 3.6 - 15 */
background: linear-gradient(90deg, rgba(226, 234, 251,0.3), rgba(91, 163, 254,0.3));"></div>


  <div style="width:1000px;margin:0 auto;padding-top:5%;">
	  <div id="bgl" style="background: url('static/img/login/login_lbg.png') no-repeat; width:557px;height:450px;float: left;"></div>

	  <div id="login_form_div">
		  <form id="login_form" onkeypress="if(event.keyCode==13){$(this).submit();}">

			  <div class="login_title">${siteName}</div>
			  <div><input type="text" name="loginName"   id="userName" maxlength="20" placeholder="请输入用户名"></div>
			  <div><input type="password" name="password"  id="password"  maxlength="20"  placeholder="请输入密码"></div>
			  <div>
				  <a href="javascript:void(0)"  onclick="$('#login_form').submit()" >登&nbsp;录</a>
				  <a href="javascript:void(0)"  onclick="$('#login_form')[0].reset()" >重&nbsp;置</a>
			  </div>
			  <div id="msg"></div>
		  </form>
	  </div>
  </div>





  <div id="bottom_div">

  </div>
  <div style="z-index:999;position: absolute;bottom:0px;width:100%;text-align: center;color:#fff;font-size:14px;height:30px;line-height:30px;">
      Copyright&nbsp;©&nbsp;2016 鹤诺科技（北京）有限公司 &nbsp;<a href="http://www.hinova.com.cn/">www.hinova.com.cn</a>
  </div>

  <script type="application/javascript">

      $(function(){

          $.map($(".yuan"),function(i){
              $(i).animate({"left":($(i).offset().left+50)},1000);
          });
          $("#bottom_div").animate({"height":parseInt($("#bottom_div").height())-20},1000)
          $("#bgl").animate({"margin-left":parseInt($("#bgl").css("margin-left"))-20},1000)
          $("#login_form_div").animate({"margin-top":parseInt($("#login_form_div").css("margin-top"))+20},1000)
      })

      $("#userName").focus();

      var cl=null;
      function showMsg(msg){
          if(cl!=null){
              clearTimeout(cl);
              cl=null;
          }
          $("#msg").html(msg);
          cl=setTimeout(function(){
              $("#msg").text("");
          },3000)
      }

      $("#login_form").submit(function(){
          $("#login_form").ajaxSubmit({
              url: "login.do?_="+new Date().getTime(), //提交地址：默认是form的action,如果申明,则会覆盖
              type: "post",   //默认是form的method（get or post），如果申明，则会覆盖
              beforeSubmit: function(varr,f,o){

                  for(i in varr){
                      var item=varr[i]
                      if(item.name=='loginName'){

                          if(item.value.trim()==''){
                              showMsg("用户名不能为空");
                              return false;
                          }
                          if(!/[a-zA-Z0-9]{4,}/.test(item.value)){
                              showMsg("用户名格式错误！<br/>要求：英文或数字");
                              return false;
                          }
                      }else if(item.name=='password'){
                          if(item.value.trim()==''){
                              showMsg("密码不能为空");
                              return false;
                          }
                          if(!/[a-zA-Z0-9]{4,}/.test(item.value)){
                              showMsg("密码格式错误！<br/>要求：英文或数字");
                              return false;
                          }
                      }

                  }

              }, //提交前的回调函数
              success: function(resu){
                  if(resu.code=="200"){
                      document.location.href="index.do"
                  }else{
                      showMsg("登录失败！<br/>用户名或密码错误");
                  }

              },  //提交成功后的回调函数
              error:function(resu){
                  console.log(resu);
                  showMsg("请求失败！");
              },
              // target: "#output",  //把服务器返回的内容放入id为output的元素中
              dataType: "json", //html(默认), xml, script, json...接受服务端返回的类型
              // clearForm: true,  //成功提交后，是否清除所有表单元素的值
              // resetForm: true,  //成功提交后，是否重置所有表单元素的值
              timeout: 10000     //限制请求的时间，当请求大于3秒后，跳出请求
          });
          return false;
      });



  </script>
  </body>

</html>