<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>文件上传</title>

    

</head>
<body style="padding:10px">
<script type="text/javascript">
    function downLoadMODO(obj){
		var href = "config/input/downLoadMODO.do";
    	obj.href = href;
    }
    		
    function btn_upload(){
    	var fileName = $("#upfile").val();
        var fileName2 = $("#fileText").val();
        if(fileName == "" || fileName2 == ""){
            alert("请先选择哦~~~");
            return false;
        }
        $.ajaxFileUpload({  
            url: "config/input/Input.do",
            secureuri: false,
            fileElementId: "upfile", 
            dataType: "text", 

            success: function (data, status)
			{
            	var obj = eval('(' + data + ')');
            	if(obj.result=='success'){
						showTip("执行完毕，导入成功");
            	}else{
					if(obj.failreason){
						if(obj.failreason=="文件类型不支持"){
							showTip("导入失败！您选择的文件格式不支持！");
						}else{
							showTip("导入失败:"+obj.failreason);
						}
						
					}else{
						showTip("导入失败！");
					}
				}
			},
			 error: function (data, status, e)
			{
				showTip("导入失败");
			}

        });
    }
    function text_click(){
        $("#upfile").click();
      }
    /**
	 * 显示提示信息
	 */
	function showTip(tipValue){
		 $("#tipText").html(tipValue);
		 $("#tipText").fadeIn();
		 //setTimeout(function(){$("#tipText").fadeOut();}, 3000);
	}
    //选择文件后触发事件函数
    function test(){
        //将文件名赋值到文本输入框
        var fileName = $("#upfile").val();
        $("#fileText").val(fileName);
    }
    </script>
    <div class="fzxx">
			<div class="biaoti">
				<div class="biaoti-left1"></div>
				<div class="biaoti-left2">批量导入</div>
			</div>
				<table align="center" class="data">
					<tr>
						<td align="left" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue">导入格式：</font></td>
					</tr>
    				<tr bgcolor ="#FFFFFF" >
    				 	<td align="left" colspan="1">&nbsp;&nbsp;&nbsp;&nbsp;<b>xls文件格式：</b></td>
    				 	<td align="left" colspan="1">点击下方"下载模版"按钮，下载excel模版，按要求填入信息，再导入
    				</tr>
				</table>
		</div>
    <div >
        文件名(上传): <input type="text"  style="height: 25px;width: 250px;" id="fileText" onclick="text_click()" />

        <button onclick="btn_upload()">开始上传</button>
    </div>
				<a href="javascript:void(0)" id="btn" class="easyui-linkbutton"  onclick="downLoadMODO(this)">下载模版</a>
    <div hidden="hidden">
        <input type="file" id="upfile" name="upfile" onchange="test();">
    </div>
    <div id="tipText" style="display:none ;color: red; padding-top:15px;"></div>
</body>
</html>