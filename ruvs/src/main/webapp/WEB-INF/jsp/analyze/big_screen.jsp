<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/header.page.jsp"%>
<link rel="stylesheet" type="text/css" href="static/js/libs/font-awesome-4.7.0/css/font-awesome.min.css">

<style type="text/css">

    html,body{
        padding: 0px;
        margin: 0px;
        overflow: hidden;
        background: #9765c9;
    }

    .bigimg{
        position: relative;
    }

    .bigimg div{
        height: 50%;
        text-align: center;
    }

    td{
        background: #855ab3;
    }
    #infos ul li{
        float: left;
        min-width: 150px;
    }
    #icon_max,#icon_min{
        font-size: 14px;
        color: #fff3f3;
        position: absolute;
        left: 3px;
        top: 3px;
        background: #7da9ff;
        border-radius: 3px;
        cursor: pointer;
        z-index: 9999;
    }
    #icon_min{
        display: none;}
    #show_info_table td{
        padding: 5px 10px;
    }
</style>
<script type="text/javaScript">

    function max(){
        top.document.documentElement.webkitRequestFullscreen();
        top.$("iframe[src='ui/analyze/big_screen.do']")
            .css("position","fixed")
            .css("width","100%")
            .css("height","100%")
            .css("left","0px")
            .css("top","0px")
            .css("z-index","9999");
        $("#icon_max").hide();
        $("#icon_min").show();
    }


    function min(){
        top.document.webkitExitFullscreen();
        top.$("iframe[src='ui/analyze/big_screen.do']")
            .css("position","")
            .css("width","100%")
            .css("height","100%")
            .css("left","")
            .css("top","")
            .css("z-index","");
        $("#icon_max").show();
        $("#icon_min").hide();
    }
    var last1=null;
    var data1=[]
    var lastTime;
    function initData(){
        $.get("analyze/bigScreen/last.do",function(data){

            if(data&&data.length>0) {
                last1 = data.shift();
                data1 = data;


                setCruurent(last1);
                setItems(data1);
                lastTime = last1.faceTime;

                setTimeout(getNewData, 3000);
                $("#get_date_time").text(getSdate());
            }
        },"json");
    }

    function getNewData(){
        $.get("analyze/bigScreen/new_last.do?lastTime="+lastTime+"&_="+new Date().getTime(),function(data){


            if(data&&data.length>0){

                data1.unshift(last1);
                last1=data.shift();
                data1=data.concat(data1);
                while(data1.length>20){
                    data1.pop();
                }

                setCruurent(last1);
                setItems(data1);
                lastTime=last1.faceTime;
            }
            // $("#get_date_time").text(getSdate());
           setTimeout(getNewData,3000);
        },"json");

    }




    function setCruurent(last1){
        $("#current_img").attr("src",last1.facePhoto)
        $("#current_name").text("访客: "+showValue(last1.patriarchName))
        $("#current_time").text("到访时间: "+showValue(last1.faceTime))
        $("#current_jz").text(showValue(last1.studentGradeName)+"  "+showValue(last1.studentClazzName)+"  "+showValue(last1.studentName)+" 的 "+showValue(last1.patriarchRelation))
        $("#current_tc").text(showValue(last1.teacherName));
    }

    function setItems(items){

        $("#show_info_table tbody").text("");
        for(i in items){

            $("#show_info_table tbody").prepend(getTr(items[items.length-1-i]))
        }
    }

    function t2(n){
        return n<10?'0'+n:n;
    }

    function getSdate(){

        var date = new Date();
        return date.getFullYear() + "-" + t2(date.getMonth() + 1) + "-" + t2(date.getDate())+ //日
        " " + t2(date.getHours()) +//小时
        ":" + t2(date.getMinutes()) + //分
        ":" + t2(date.getSeconds());
    }

    function showValue(value){

        if(value==null||value==""){
            return "--";
        }else{
            return value;
        }
    }

    function getTr(item){

        var _tr="<tr><td><img src='"+(item!=null?item.facePhoto:'')+"' width='50px'/></td>";
            _tr+="<td>"+(item!=null?showValue(item.faceTime):'')+"</td>";
            _tr+="<td>"+(item!=null?showValue(item.patriarchName):'')+"</td>";
            _tr+="<td>"+(item!=null?showValue(item.studentGradeName):'')+"  "+(item!=null?showValue(item.studentClazzName):'')
                +"  "+(item!=null?showValue(item.studentName):'')+" 的 "+(item!=null?showValue(item.patriarchRelation):'')+"</td>";
            _tr+="<td>"+(item!=null?showValue(item.teacherName):'')+" "+(item!=null?showValue(item.teacherPhone):'')+"</td></tr>";
        return _tr
    }

    $(function(){
        initData();

       // var img_width=$("body").width()/4-40;
        var img_height=$("body").height()/2;

        $(".bigimg img").height(img_height);
        $("#all_table").height($("body").height());

    });


    window.onresize=function(){

        var img_height=$("body").height()/2;

        $("#all_table").height($("body").height());

        $(".bigimg img").height(img_height);
    }
</script>
</head>
<body>
<span onclick="max()" id="icon_max"><i class="fa fa-window-maximize "></i>全屏</span>
<span onclick="min()" id="icon_min"><i class="fa fa-window-minimize "></i>退出</span>

<table style="width: 100%;height: 100%;border: 0px;" id="all_table">

    <tr>
        <%--<td  style="background: #880c8c;color: #eaa5a5;text-align: center;width: 260px;">--%>
            <%--<strong>${siteName}</strong>--%>
            <%--<br/>--%>
            <%--<br/>--%>
            <%--<br/>--%>
            <%--<br/>--%>
            <%--<br/>--%>
            <%--<div>数据同步时间：</div>--%>
            <%--<br/>--%>
            <%--<div id="get_date_time"></div>--%>
            <%--<br/>--%>
            <%--<br/>--%>
            <%--<div>${starWord}</div>--%>
        <%--</td>--%>
        <td  class="bigimg" style="padding-top: 20px;width: 300px;">
            <div>
                <img src=""  id="current_img" >
            </div>
            <div style="text-align: center;">
                <ul style="padding-top: 50px;color: #f1a07d;">
                    <li id="current_name"></li>
                    <br/>
                    <li id="current_time"></li>
                    <br/>
                    <li id="current_jz"></li>
                    <br/>
                    <li id="current_tc"></li>
                </ul>
            </div>
        </td>
        <td  width="70%" valign="top" style="overflow: hidden;">
    <div style="height: 100%;width: 100%; overflow: hidden; overflow-y: auto">
            <table style="color: #fff;" id="show_info_table">
                <thead>
                    <tr>
                        <th>照片</th>
                        <th>时间</th>
                        <th>姓名</th>
                        <th>学生信息</th>
                        <th>老师信息</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
    </div>
        </td>
    </tr>
</table>





</body>
</html>
