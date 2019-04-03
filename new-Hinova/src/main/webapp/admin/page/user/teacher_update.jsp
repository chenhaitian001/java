<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>添加教师</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <script src="static/hinova/js/jquery-1.12.1.min.js"></script>
    <link href="static/hinova/css/heziCss.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="static/hinova/js/address.js"></script>
    <script src="static/hinova/js/layui/layui.js"></script> 
    <!--添加  vue.js 支持加载-->
	<script src="static/vue/vue.min.js"></script>
	<!--添加  vue.js 支持加载-->
	<script src="static/easy-ui/jquery.easyui.min.js"></script>
    
</head>
<body>
<div class="title">修改教师</div>
<form method="post" id="loginForm" name="loginForm" class="signupform" action='worker/teacher/new_update'>
  <input type="hidden" name="id" value="${teacher.id }">  

<div class="main">
	<div class="hrLins">
	    <hr class="hrLine"/>
	    基本资料
	    <hr class="hrLine"/>
	</div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">姓名：</h2>
            <input class="box1_input" type="text" name="name" value="${teacher.name }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">工号：</h2>
            <input class="box1_input" type="text" name="id" disabled="disabled" placeholder="工号自动生成" value="${teacher.id }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">性别：</h2>

            <select name="sex"  value="${teacher.gender }">
                <option value="1" <c:if test="${'1' eq teacher.gender}">selected</c:if> >男</option>
                <option value="0" <c:if test="${'0' eq teacher.gender}">selected</c:if> >女</option>
            </select>
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">所属机构编号：</h2>
            <input class="box1_input" type="text" name="agencyNumber" value="${teacher.agencyNumber }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">职务：</h2>
            <input class="box1_input" type="text" name="worker" value="${teacher.worker }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">考勤识别码：</h2>
            <input class="box1_input" type="text" name="code" value="${teacher.code }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">出生日期：</h2>
           <input class="box1_input" type="text" id="test1" name="birthday" value="${teacher.birthday }">
        </div>
	<div class="box1_right">
            <h2 class="title_right_name">班级：</h2>
            <input  type="text"  name="gradeid"  id="gradeId"  v-model="gradeId" class="easyui-combobox box1_input_min" data-options="valueField:'gid',textField:'gname',url:'dictionary/grade',editable:false"/>
            <input type="text"  name="clazzid"  id="clazzId" value="${teacher.clazzid }" v-model="clazzId"  autocomplete="off" class="easyui-combobox box1_input_min" data-options="valueField:'cid',textField:'cname',url:'dictionary/clazz',editable:false"/>
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">手机号：</h2>
             <input type="text" id="phone" name="phone" autocomplete="off" v-model="phone" value="${teacher.phone}" placeholder="" class="box1_input">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">电子邮箱：</h2>
            <input type="text" id="email" name="email" autocomplete="off" v-model="email" value="${teacher.email}" placeholder="" class="box1_input">
        </div>
    </div>
    <div class="box1">
        <div class="box1">
            <h2 class="title_left_name">家庭住址：</h2>
            <input class="input2" type="text" name="provinces" value="${teacher.provinces }">
            <input class="input2" type="text" name="cities" value="${teacher.cities }">
            <input class="input3" type="text" name="counties" value="${teacher.counties }">
        </div>
    </div>
    <div class="box1">
        <div class="box1">
            <h2 class="title_left_name">描述：</h2>
            <input class="input4" type="text" name="address" value="${teacher.address }">
        </div>
    </div>
    <div class="hrLins">
        <hr class="hrLine"/>
        详细资料
        <hr class="hrLine"/>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">国籍：</h2>
            <input class="box1_input" type="text" name="nationality" value="${teacher.nationality }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">是否学前教育专业毕业：</h2>
            <div class="box1_right_line">
                <div class="box1_left_inline">
                    <p class="db_left">是</p>
                    <input class="gcs-radio" type="radio" name="preschoolEducation" value="1" id="one"><label for="one"
                                                                                                               class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">否</p>
                    <input class="gcs-radio" type="radio" name="preschoolEducation" value="0" id="two"><label for="two"
                                                                                                               class="txt"></label>
                </div>
            </div>
        </div>
    </div>
    <div class="box1">
         <div class="box1_left">
            <h2 class="title_left_name">证件类型：</h2>
            <input class="box1_input" type="text" name="cardType" value="${teacher.cardType }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">幼儿师范毕业：</h2>
            <div class="box1_right_line">
                <div class="box1_left_inline">
                    <p class="db_left">是</p>
                    <input class="gcs-radio" type="radio" name="preschoolTeachers" value="1" id="thr"><label for="thr"
                                                                                                               class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">否</p>
                    <input class="gcs-radio" type="radio" name="preschoolTeachers" value="0" id="for"><label for="for"
                                                                                                               class="txt"></label>
                </div>
            </div>
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">证件号：</h2>
            <input class="box1_input" type="text" name="idCard" value="${teacher.idCard }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">是否有教师资格证：</h2>
            <div class="box1_right_line">
                <div class="box1_left_inline">
                    <p class="db_left">是</p>
                    <input class="gcs-radio" type="radio" name="teacherPc" value="1" id="fiv"><label for="fiv"
                                                                                                               class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">否</p>
                    <input class="gcs-radio" type="radio" name="teacherPc" value="0" id="six"><label for="six"
                                                                                                               class="txt"></label>
                </div>
            </div>
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">港澳台侨：</h2>
            <input class="box1_input" type="text" name="pHm" value="${teacher.pHm }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">职称：</h2>
            <input class="box1_input" type="text" name="title" value="${teacher.title }">
        </div>
    </div>
     <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">政治面貌：</h2>
            <input class="box1_input" type="text" name="politicalLandscape" value="${teacher.politicalLandscape }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">学历：</h2>
            <input class="box1_input" type="text" name="schooling" value="${teacher.schooling }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">民族：</h2>
            <input class="box1_input" type="text" name="volk" value="${teacher.volk }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">教师资格证种类：</h2>
            <input class="box1_input" type="text" name="teacherPcType" value="${teacher.teacherPcType }">
        </div>
    </div>
     <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">户口性质：</h2>
            <input class="box1_input" type="text" name="residenceTyp" value="${teacher.residenceTyp }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">发证机关：</h2>
            <input class="box1_input" type="text" name="issuing" value="${teacher.issuing }">
        </div>
    </div>
     <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">户口所在地：</h2>
            <input class="box1_input" type="text" name="residence" value="${teacher.residence }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">教师资格证号码：</h2>
            <input class="box1_input" type="text" name="teacherPcNo" value="${teacher.teacherPcNo }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">出生地：</h2>
            <input class="box1_input" type="text" name="placeBirth" value="${teacher.placeBirth }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">编制情况：</h2>
            <div class="box1_right_line">
                <div class="box1_left_inline">
                    <p class="db_left">在编</p>
                    <input class="gcs-radio" type="radio" name="compileType" value="1" id="seven"><label for="seven"
                                                                                                               class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">不在编</p>
                    <input class="gcs-radio" type="radio" name="compileType" value="0" id="ten"><label for="ten"
                                                                                                               class="txt"></label>
                </div>
            </div>
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">入职时间：</h2>
            <input class="box1_input" type="text" name="workerType" value="${teacher.workerType }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">是否在职：</h2>
            <div class="box1_right_line">
                <div class="box1_left_inline">
                    <p class="db_left">在职</p>
                    <input class="gcs-radio" type="radio" name="ifJob" value="1" id="eleven"><label for="eleven"
                                                                                                               class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">离职</p>
                    <input class="gcs-radio" type="radio" name="ifJob" value="0" id="twvo"><label for="twvo"
                                                                                                               class="txt"></label>
                </div>
            </div>
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">用工方式：</h2>
            <input class="box1_input" type="text" name="employingWay" value="${teacher.employingWay }">
        </div>
        
    </div>
</div>
<div style="text-align:center; ">
    <input type="button" onclick="submitForm()" class="registerBtn" value="修改"/>
</div>
</form>

<script type="text/javascript">  


$(function() {
     $('#gradeId').combobox({ 
              valueField:'gid',
              textField:'gname',
              cache: false,
            url: "dictionary/grade" 
            ,onSelect: function (record) {
            	
                  var a = record.gid;
                   $.ajax({
                      type: "POST",  
                      url:"dictionary/clazz" , 
                      data: {"gradeId":a},  
                      cache: false,  
                      dataType : "json",  
                      success: function(data){  
                              $("#clazzId").combobox("loadData",data);  
                      }  
                    });
            }

        }); 
     //选择分公司时清空三级四级机构
    $('#gradeId').combobox({
              onChange:function(a,b){
                   //分公司赋值
                  $('#clazzId').combobox('setValue',"");//等价于下两行
                  $("#clazzId").attr("value","");
                  $('#clazzId').combobox('select',"");
                  $("#clazzId").combobox("loadData",{});
              }
        }); 
     
    $("#gradeId").textbox('setValue','${teacher.gradeid }');
    
  //给选择框赋值
	$("input[name='preschoolEducation'][value='${teacher.preschoolEducation}']").attr("checked",true);
	$("input[name='preschoolTeachers'][value='${teacher.preschoolTeachers}']").attr("checked",true);
	$("input[name='teacherPc'][value='${teacher.teacherPc}']").attr("checked",true);
	$("input[name='compileType'][value='${teacher.compileType}']").attr("checked",true);
	$("input[name='ifJob'][value='${teacher.ifJob}']").attr("checked",true);
});
function submitForm()
{
	 var name =$("input[name='name']").val(); 
     var gradeId =$("input[name='gradeid']").val(); 
	 var clazzId =$("input[name='clazzid']").val(); 
	 
	 if(name==null||name==""||gradeId==null||gradeId==""||clazzId==null||clazzId==""){ 
		 layer.msg('请填写完整信息!!!!!!');
		    return false; 
	  }  else{
		  document.loginForm.submit();
		//调用 父窗口的  关闭所有窗口 并且刷新 页面
		  window.parent.location.reload();
	  }
	 
		
} 

layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel',
    		'upload', 'element' ], function() {
    	var laydate = layui.laydate //日期
    	, laypage = layui.laypage //分页
    	layer = layui.layer //弹层
    	, table = layui.table //表格
    	, carousel = layui.carousel //轮播
    	, upload = layui.upload //上传
    	, element = layui.element; //元素操作
    	
    	//执行一个laydate实例
    	laydate.render({
    	  elem: '#test1' //指定元素
    	});
    });

</script> 
</body>
</html>