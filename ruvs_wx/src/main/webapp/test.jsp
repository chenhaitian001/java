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
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Lvx9OrEMlRFTkIkGSIsTzXxNUB671Ew2"></script>
	<title>浏览器定位</title>
</head>
<body>
	<div id="allmap"></div>
</body>
	<script src="assets/js/jquery.min.js"></script>
	<script type="text/javascript">
	$(function(){
		map.addOverlay(circle);            //增加圆
	});
	</script>

<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.57881,39.796264,8); 
	setTimeout(function(){
		map.setZoom(16);   
	}, 2000);  //2秒后放大到16级
	map.enableScrollWheelZoom(true);
	//;方法重置显示区域及缩放比例
	map.centerAndZoom(point,12);
	
	//添加控件
	map.addControl(new BMap.NavigationControl());    
	map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));//左上角，添加比例尺
	map.addControl(new BMap.GeolocationControl()); //定位
	
	//创建小狐狸
	/* var pt = new BMap.Point(116.57881, 39.796264);
	var myIcon = new BMap.Icon("http://lbsyun.baidu.com/jsdemo/img/fox.gif", new BMap.Size(300,157));
	var marker2 = new BMap.Marker(pt,{icon:myIcon});  // 创建标注
	map.addOverlay(marker2);   */ 
	
	
	
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var myP1 = new BMap.Point(r.point.lng,r.point.lat);    //起点
			var myP2 = new BMap.Point(116.57881,39.796264);    //终点
			var myIcon = new BMap.Icon("assets/i/cadfads.png", new BMap.Size(32, 70), {    //小车图片
				//offset: new BMap.Size(0, -5),    //相当于CSS精灵
				imageOffset: new BMap.Size(0, 0)    //图片的偏移量。为了是图片底部中心对准坐标点。
			  });
			var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
			driving2.search(myP1, myP2);    //显示一条公交线路

			window.run = function (){
				var driving = new BMap.DrivingRoute(map);    //驾车实例
				driving.search(myP1, myP2);
				driving.setSearchCompleteCallback(function(){
					var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组
					var paths = pts.length;    //获得有几个点

					var carMk = new BMap.Marker(pts[0],{icon:myIcon});
					map.addOverlay(carMk);
					i=0;
					function resetMkPoint(i){
						carMk.setPosition(pts[i]);
						if(i < paths){
							setTimeout(function(){
								i++;
								resetMkPoint(i);
							},300);
						}
					}
					setTimeout(function(){
						resetMkPoint(5);
					},100)

				});
			}

			setTimeout(function(){
				run();
			},1500);
			
			//创建标注
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			mk.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			map.panTo(r.point);
		//	alert('您的位置：'+r.point.lng+','+r.point.lat);
			 $.ajax({
		    		url:  "config/checkmap.do?key="+new Date().getTime(),
		    		traditional : true,
		    		type:'POST',
					dateType : "json",
		    		data : {
		    			longitude : r.point.lng,
		    			latitude : r.point.lat,
		    		},
		    		success : function(resu) {
		    			if (resu.code == 200) {
		    				window.parent.document.getElementById("matchingID").innerText="已在可打卡范围";

						}else{
							window.parent.document.getElementById("matchingID").innerText="未匹配到打卡地点";
							window.parent.document.getElementById('btn_3').style.display="none";
							top.alert(resu.msg);
						}
				    	}
		    		
		    	});
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	
		var circle = new BMap.Circle(point,500,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5}); //创建圆

	//关于状态码
	//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
	//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
	//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
	//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
	//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
	//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
	//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
	//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
	//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
</script>
</html>