<%--
  Created by IntelliJ IDEA.
  User: ren
  Date: 2018/8/14
  Time: 上午01:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <base href="<%=basePath%>%">
    <title>接送记录</title>


    <link rel="stylesheet" href="static/lib/jquery-weui/lib/weui.min.css">
    <link rel="stylesheet" href="static/lib/jquery-weui/css/jquery-weui.css">
    <style type="text/css">
        *{
            font-size: 14px;
        }
        ul,li{
            padding: 0px;
            margin: 0px;
            list-style: none;
        }
        li{
            background: #fff;
            margin: 10px 0px;
            padding: 10px 0px;
        }
        .lsspaniv{
            padding: 0 20px;
        }

        li{

        }
        .c1,c2,.c3{
            padding: 0 15px;
            line-height: 30px;
        }
        .c4{
            float: right;
            line-height: 5px;
            padding-right: 5px;
            display: inline-block;
            line-height: 30px;
        }

        i.icon.icon-next,i.icon.icon-prev{
            margin-top: 14px;
        }
        .weui-btn+.weui-btn{
            margin-top: 0px;
        }
        .sdiv{line-height: 30px}
        .sdiv *{vertical-align: middle}
    </style>

    <script type="text/javascript">

        var openid="<%=request.getSession().getAttribute("openId")%>";


        function changejiesong(id) {
            $('.js').removeClass('weui-btn_default').removeClass('weui-btn_primary ');
            if(id=="jie"){
                $('#jie').addClass('weui-btn_primary ');
                $('#song').addClass('weui-btn_default ');
            }else{

                $('#jie').addClass('weui-btn_default ');
                $('#song').addClass('weui-btn_primary ');
            }
            $("#jiesong").val(id);
            loadData();
        }

    </script>
</head>
<body style="padding: 0px;margin: 0px;overflow: auto;background: #ebf8fe;padding-top: 40px;">
<div style="position: fixed;top: 0px;width: 100%;background: #ebf8fe;">
<div style="    background: #fff;
    box-shadow: 2px 2px 2px 0em rgba(0,0,0,0.3);
    padding: 6px;
    margin: 5px 20px;" class="sdiv">
   <span > 日期选择：</span>
    <input class="weui-input" id="date" type="text" style="width: 85px;" value="${requestScope.currentDate}" >
    <input type="hidden" id="jiesong" value="song">
    <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default js" onclick="changejiesong('jie')" id="jie"  style="float: right;margin-left: 4px">接</a>
    <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary js" onclick="changejiesong('song')" id="song" style="float:right">送</a>
</div>
</div>
<div class="lsspaniv">

    <ul style="width: 100%;" id="contentul">

    </ul>
</div>



<script src="static/lib/jquery-2.1.4.js"></script>
<script src="static/lib/jquery-weui/lib/fastclick.js"></script>
<script>
    $(function() {
        FastClick.attach(document.body);
    });
</script>
<script src="static/lib/jquery-weui/js/jquery-weui.js"></script>
<script type="text/javascript">

    function loadData(sdate){

        if(!sdate){
            sdate=$("#date").val()
        }

        var jiesong=$("#jiesong").val();

        $("#contentul").html("<li style='text-align:center;padding-left:10px'>数据加载中。。。</li>");

        $.post("ifhistory",{"date":sdate,"jiesong":jiesong},function(data){

            var htmlDate=""
            if(data.length>0) {
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    htmlDate += "<li>";
                    htmlDate += "<span  class=\"c1\">" + (i + 1) + "</span>";
                    htmlDate += "<span  class=\"c2\">" + item.studentName + "</span>";
                    htmlDate += "<span  class=\"c3\">" + item.gx + "</span>";
                    htmlDate += "<span  class=\"c4\">" + (item.jieSong == 'jie' ? '接' : '送') + "：" + item.timestampStr.split(" ")[1] + "</span>";
                    htmlDate += "</li>";
                }
            }else{
                htmlDate="<li style='text-align:center;padding-left:10px'>没有找到记录!</li>";
            }
            $("#contentul").html(htmlDate);

        },"json");


    }
    loadData("");

    $("#date").calendar({
        onChange: function (p, values, displayValues) {
            if(values.length>0){
                loadData(values[0])
            }
        }
    });
</script>
</body>
</html>
