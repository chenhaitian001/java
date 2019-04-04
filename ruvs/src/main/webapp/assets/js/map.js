/*if (window.navigator.geolocation) {
  var options = {
    enableHighAccuracy: true,
  };
  window.navigator.geolocation.getCurrentPosition(handleSuccess, handleError, options);
} else {
  alert("浏览器不支持html5来获取地理位置信息");
}*/

function handleSuccess(position){
  // 获取到当前位置经纬度  本例中是chrome浏览器取到的是google地图中的经纬度
  var x = position.coords.longitude;
  var y = position.coords.latitude;
  // 百度地图API功能
  //谷歌坐标
//        var x = 116.32715863448607;
//        var y = 39.990912172420714;
  var ggPoint = new BMap.Point(x,y);

  //地图初始化
  var bm = new BMap.Map("aaa");
  bm.centerAndZoom(ggPoint, 15);
  bm.addControl(new BMap.NavigationControl());

  var geoc = new BMap.Geocoder();

  //添加gps marker和label
  //var markergg = new BMap.Marker(ggPoint);
  // bm.addOverlay(markergg); //添加GPS marker
  //var labelgg = new BMap.Label();
  // markergg.setLabel(labelgg); //添加GPS label
  var longitudeStr ;
  var latitudeStr  ;
  //坐标转换完之后的回调函数
  translateCallback = function (data){

    if(data.status === 0) {
      var marker = new BMap.Marker(data.points[0]);
      bm.addOverlay(marker);
      var label = new BMap.Label("你当前所在位置",{offset:new BMap.Size(10,-10)});

      marker.setLabel(label); //添加百度label
      bm.setCenter(data.points[0]);
      //alert(data.points[0].lng);
      //alert(data.points[0].lat);
      longitudeStr = data.points[0].lng;
      latitudeStr = data.points[0].lat;
      $('#longValueId').val(longitudeStr);
      $('#latValueId').val(latitudeStr);
      
      $('#showLongID').val(longitudeStr);
      $('#showLatID').val(latitudeStr);
      var url = $("#basePath").val() + "OaProject/employeeCheckinData_getCheckinDataByGPS";
      //alert('url map '+url);
      var loginIdStr = $("#loginId").val();
      var departmentcodeIdStr = $('#departmentcodeId').val();
      //alert('loginId '+loginIdStr);
      $.ajax({
    		url: url,
    		type : 'POST',
    		data : {
    			loginid : loginIdStr,
    			longitude : longitudeStr,
    			latitude : latitudeStr,
    			departmentcode:departmentcodeIdStr,
    		},
    		success : function(response, stutas, xhr) {
    			//alert('success');
    			//alert('response '+response);
    			var responseData = eval("(" + response + ")");
    			//alert('responseData '+responseData);
    			var addressJson = responseData.ajaxData;
    			//alert('addressJson '+addressJson);
    			var result = eval("(" + addressJson + ")");
    			if(result.length>0){
    				$('#matchingID').hide();
    			}
    			//alert(result);
    			$('#city').empty();
    			for (var i = 0; i < result.length; i++) {
    				var idStr = result[i].checkinAddressId;
    				var nameStr = result[i].checkinAddress;
    				//alert(idStr+'  '+nameStr);
    				/*$('#city').append(
    						'<option value='+"'"+idStr+"'"+' selected=true >'
    								+ nameStr + '</option>');*/
    				$('#city').append("<option value='" + idStr + "' selected >" + nameStr + "</option>");
    			}
    			
    			$("#city_dummy").val($("#city").find("option:selected").text());
    			/*$("#weizhiBaidu").html($("#city").find("option:selected").text());*/
    			var tttttV = $("#city_dummy").val();
    			if(tttttV==null || tttttV==''){
    				$('#btn_1').attr('class','dised');
    				$('#btn_2').attr('class','dised');
    			}else{
    				$('#btn_1').attr('class','shied');
    				$('#btn_2').attr('class','shied');
    			}
    		},
    		error : function(xhr, errorText, errorStatus) {
    			alert(xhr.status + ':' + xhr.statusText, +' '
    					+ errorText);
    			alert('wrong');
    		}
    	});

      geoc.getLocation(data.points[0], function(rs){
        var addComp = rs.addressComponents;
        var addr = addComp.province + addComp.city  + addComp.district  + addComp.street + addComp.streetNumber;
        $("#weizhiBaidu").text(addr);
      });

    }
  }

  setTimeout(function(){
    var convertor = new BMap.Convertor();
    var pointArr = [];
    pointArr.push(ggPoint);
    convertor.translate(pointArr, 1, 5, translateCallback)
  }, 1000);
  
  /*var longitudeStr = ggPoint.lng;
  var latitudeStr = ggPoint.lat;
  $('#longValueId').val(longitudeStr);
  $('#latValueId').val(latitudeStr);*/
 
   //alert('longitudeStr map '+longitudeStr);
   //alert('latitudeStr map '+latitudeStr);
  
  return ggPoint;

}

function handleError(error){
}
