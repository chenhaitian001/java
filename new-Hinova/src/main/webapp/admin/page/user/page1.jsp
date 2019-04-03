<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="/admin/page/user/top.jsp"%>   --%>
<%@ include file="/admin/page/user/common.jsp"%>
<!-- 主体部分 -->

    <div class="box">
        <div class="box-search clearfix">
            <div class="searchLeft">
                <input type="text" id="timeBtn1" placeholder="yyyy-MM-dd" autocomplete="off">
                <input type="text" id="timeBtn2" placeholder="yyyy-MM-dd" autocomplete="off">
                <a class="searchBtn1" onclick="searchBtn1()" >搜索</a>
            </div>
            <div class="searchRight">
               <div class="choose">
                   <a href="javascript:;">家长姓名</a>
                   <ul class="hideBox">
                        <li>家长姓名</li>
                        <li>家长电话</li>
                   </ul>
               </div>
               <input id ="comoncode" type="text" class="searchCon">
               <a class="searchBtn2" onclick = "searchBtn2()"></a>
            </div>
        </div>
        <div class="box-table">
            <table id="table" class="layui-table tableStyle"></table>
        </div>
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
		, element = layui.element; //元素操作
		  table.render({
            elem: '#table'
            ,url: '<%=request.getContextPath()%>/device/facehistory/list_face'
            ,height: 'full-68'
            ,cellMinWidth: 80
            ,cols: [[ //表头
            {type:'checkbox',width: 30,align:'center'}
           // ,{field: 'id', title: '序号', width: 66,align:'center'}
            //,{field: 'gname', title: '桥型', width: 66,align:'center', templet: '<div>{{d.grade.gname}}</div>'}
            ,{field: 'faceTime', title: '时间', minWidth: 180,align:'center'}
            ,{field: 
                'facePhoto',title:'图片',
                templet: function(d){
                    return '<img src=http://121.42.139.114/ruvs/'+d.facePhoto+'>'
                },
                minWidth:120,
                style:'height:100px',
                align:'center'
            }
            ,{field: 'patriarchName', title: '家长',align:'center'} 
            ,{field: 'patriarchPhone', title: '家长电话',minWidth: 150,align:'center'}
            ,{field: 'studentName', title: '幼儿',align:'center'}
            ,{field: 'studentGradeName', title: '年级',align:'center'}
            ,{field: 'studentClazzName', title: '班级',align:'center'}
            ,{field: 'teacherName', title: '老师',align:'center'}
            ,{field: 'id', title: '考勤识别号',width:188,align:'center'}
            ,{field: 'deviceLocation', title: '设备位置',minWidth: 150,align:'center'}
            ]]
            ,skin: 'nob' //表格风格
            ,even: true
            ,id: 'table'
		 	,page: true
			,limits:[100,200,500,1000]
            ,limit:100
        });
      
			 //监听工具条 table_bar
				table.on('tool(table)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
					var data = obj.data //获得当前行数据
					, layEvent = obj.event; //获得 lay-event 对应的值
					if (layEvent === 'del') {
						//layer.msg('查看操作' + data.id);
						opend_del_dlg(data.id);
					} else if (layEvent === 'set_ps') {
						set_ps(data.id);
					} else if (layEvent === 'edit') {
						//layer.msg('编辑操作' + data.id);
						edit(data.id);
					} else if (layEvent === 'set_persm'){
						set_persm(data.id);
					}
				});
			
			
				//监听单元格编辑
				  table.on('edit(table)', function(obj){
				    var value = obj.value //得到修改后的值
				    ,data = obj.data //得到所在行所有键值
				    ,field = obj.field; //得到字段
				    //layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
				    table_edit_update(data.id,field,value);
				  });
        
    
    });
  //模板方法
    function createrFormat(o){
    		return o;
    }
    // 头部导航
    $(".nav li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    })
    $(".subNav li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    })

    //laydate时间控件
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        laydate.render({
            elem: '#timeBtn1',
            done: function(value, date){
                
            },
        });
        laydate.render({
            elem: '#timeBtn2',
            done: function(value, date){
                
            },
        });
    });
    
    // 仿select样式
    $(".choose>a").click(function(event){
        event.stopPropagation();           //阻止冒泡
        $(this).siblings("ul").toggle();
    })
    $(".hideBox li").click(function(){
        var text = $(this).text();
        $(".choose>a").text(text);
        $(".hideBox").hide();
    })
    $("html").click(function(){
        $(".hideBox").hide();
    })

    //单选全选
    $(function(){
        $("tbody td:nth-child(1)").css("display","none") //隐藏单选框
        var radioInput = $("input[name='btSelectItem']");

        //全选
        $("input[name='btSelectAll']").click(function(){
            if ($(this).is(":checked") == true) {
                radioInput.each(function(){
                    $(this).prop("checked",true);
                });
                $("tbody").css("border","2px solid #54b3b2");
                $(".tableStyle tbody tr").css("background","#f0fbfe");
                $(".tableStyle tbody tr:nth-child(even)").css("background","#e9f7fc");
            }else{
                radioInput.each(function(){
                    $(this).prop("checked",false);
                })  
                $("tbody").css("border","2px solid #f5f8fd");
                $(".tableStyle tbody tr").css("background","#fff");
                $(".tableStyle tbody tr:nth-child(even)").css("background","#f5f8fd");
            }
        })

        //单选   
        var checknum = radioInput.size(); //单选数量 
        radioInput.click(function () {
            var count = $("input[name='btSelectItem']:checkbox:checked").length; //选中个数
            if ($(this).is(":checked") == true) {	     
                if(count == checknum){ 
                    $("input[name='btSelectAll']").prop("checked",true);
                }
            }else {
                $("input[name='btSelectAll']").prop("checked",false);                   		                        
            }
        });
      
    });
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
    function searchBtn1(){
    	var timeBtn1 = $("#timeBtn1").val();
    	var timeBtn2 = $("#timeBtn2").val();
    	layui.table.reload('table', {
    		 where: {timeBtn1:timeBtn1,timeBtn2:timeBtn2},
    		 page:  {
                 curr: 1 //重新从第 1 页开始
             }
        });
    }
    function searchBtn2(){
    	var comoncode = $("#comoncode").val();
    	layui.table.reload('table', {
    		 where: {comoncode:comoncode},
    		 page:  {
                 curr: 1 //重新从第 1 页开始
             }
        });
    }
    //分页
    // $('#box-pager').Paging({pagesize:10,count:85,toolbar:true,callback:function(page,size,count){
    //     // console.log(arguments)
    //     // alert('当前第 ' +page +'页,每页 '+size+'条,总页数：'+count+'页')
	// }});

</script>
</body>
</html>