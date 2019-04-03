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
    <title>添加幼儿</title>
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
<div class="title">修改幼儿</div>
<form method="post" id="loginForm" name="loginForm" class="signupform" action='chileren/student/new_update'>

  <input type="hidden" name="id" value="${student.id }">  
<div class="main">
	<div class="hrLins">
	    <hr class="hrLine"/>
	    基本资料
	    <hr class="hrLine"/>
	</div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">姓名：</h2>
            <input class="box1_input" type="text" name="name" value="${student.name }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">编号：</h2>
            <input class="box1_input" type="text" name="id" disabled="disabled"  value="${student.id }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">性别：</h2>

            <select name="sex"  value="${student.sex }">
                <option value="1" <c:if test="${'1' eq student.sex}">selected</c:if> >男</option>
                <option value="0" <c:if test="${'0' eq student.sex}">selected</c:if> >女</option>
            </select>

        </div>
        <div class="box1_right">
            <h2 class="title_right_name">所属机构编号：</h2>
            <input class="box1_input" type="text" name="organizeId" value="${student.organizeId }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">出生日期：</h2>
            <input class="box1_input" type="text" id="test1" name="birthday" value="${student.birthday }">
        </div>
        
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">入园日期：</h2>
            <input class="box1_input" id="test1" type="text" name="goSchoolTime" value="${student.goSchoolTime }">
        </div>
	<div class="box1_right">
            <h2 class="title_right_name">班级：</h2>
            <input  type="text"  name="gradeId"  id="gradeId"  v-model="gradeId" class="easyui-combobox box1_input_min" data-options="valueField:'gid',textField:'gname',url:'dictionary/grade',editable:false"/>
            <input type="text"  name="clazzId" value="${student.clazzId }" id="clazzId"  v-model="clazzId"  autocomplete="off" class="easyui-combobox box1_input_min" data-options="valueField:'cid',textField:'cname',url:'dictionary/clazz',editable:false"/>
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">主监护人：</h2>
            <input class="box1_input" type="text" name="keeper" value="${student.keeper }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">电话号码：</h2>
            <input type="text" id="keeperPhone" name="keeperPhone" autocomplete="off" v-model="keeperPhone" value="${student.keeperPhone}" placeholder="" class="box1_input">
            
        </div>
    </div>
    <div class="box1">
        <div class="box1">
            <h2 class="title_left_name">家庭住址：</h2>
            <input class="input2" type="text" name="provinces" value="${student.provinces }">
            <input class="input2" type="text" name="cities" value="${student.cities }">
            <input class="input3" type="text" name="counties" value="${student.counties }">
        </div>
    </div>
    <div class="box1">
        <div class="box1">
            <h2 class="title_left_name">描述：</h2>
            <input class="input4" type="text" name="addressDetial" value="${student.addressDetial }">
        </div>
    </div>
    <div class="hrLins">
        <hr class="hrLine"/>
        详细资料
        <hr class="hrLine"/>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">英文名：</h2>
            <input class="box1_input" type="text" name="englishName" value="${student.englishName }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">民族：</h2>
            <input class="box1_input" type="text" name="volk" value="${student.volk }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">国籍：</h2>
            <input class="box1_input" type="text" name="nationality" value="${student.nationality }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">户口所在地：</h2>
            <input class="box1_input" type="text" name="residence" value="${student.residence }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">证件类型：</h2>
            <input class="box1_input" type="text" name="cardType" value="${student.cardType }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">户口类型：</h2>
            <input class="box1_input" type="text" name="residenceTyp" value="${student.residenceTyp }">
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">证件号：</h2>
            <input class="box1_input" type="text" name="idCard" value="${student.idCard }">
        </div>
        <div class="box1_right">
            <h2 class="title_right_name">入园体检证明：</h2>
            <div class="box1_right_line">
                <div class="box1_left_inline">
                    <p class="db_left">有：</p>
                    <input class="gcs-radio" type="radio" name="physicalExamination" value="1" id="one"><label for="one"
                                                                                                               class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">无：</p>
                    <input class="gcs-radio" type="radio" name="physicalExamination" value="0" id="two"><label for="two"
                                                                                                               class="txt"></label>
                </div>
            </div>
        </div>
    </div>
    <div class="box1">
        <div class="box1_left">
            <h2 class="title_left_name">接种本：</h2>
            <div class="box1_left_line">
                <div class="box1_left_inline">
                    <p class="db_left">有：</p>
                    <input class="gcs-radio" type="radio" name="physicalBook" value="1" id="thr"><label for="thr"
                                                                                                        class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">无：</p>
                    <input class="gcs-radio" type="radio" name="physicalBook" value="0" id="for"><label for="for"
                                                                                                        class="txt"></label>
                </div>
            </div>

        </div>
        <div class="box1_right">
            <h2 class="title_right_name">保健手册：</h2>
            <div class="box1_right_line">
                <div class="box1_left_inline">
                    <p class="db_left">有：</p>
                    <input class="gcs-radio" type="radio" name="healthyBook" value="1" id="fiv"><label for="fiv"
                                                                                                       class="txt"></label>
                </div>
                <div class="box1_left_inline">
                    <p class="db_left">无：</p>
                    <input class="gcs-radio" type="radio" name="healthyBook" value="0" id="six"><label for="six"
                                                                                                       class="txt"></label>
                </div>
            </div>
        </div>
    </div>
    <div class="hrLins">
        <hr class="hrLine"/>
        健康状况
        <hr class="hrLine"/>
    </div>
    <div class="box3">
        <h2 class="title_left_name">病史：</h2>
        <div class="box1_left_line">
            <div class="box1_left_inline">
                <input type="checkbox" id="1" name="physicalHistory" value="1" class="gcs-checkbox">
                <label for="1"></label>
                <p class="db_left">水痘</p>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">肝炎</p><input type="checkbox" id="2" value="2" name="physicalHistory"  class="gcs-checkbox">
                <label for="2"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">肺炎</p><input type="checkbox" id="3" value="3" name="physicalHistory"  class="gcs-checkbox">
                <label for="3"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">哮喘</p><input type="checkbox" id="4" value="4" name="physicalHistory"  class="gcs-checkbox">
                <label for="4"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">骨折</p><input type="checkbox" id="5" value="5" name="physicalHistory"  class="gcs-checkbox">
                <label for="5"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">肾病</p><input type="checkbox" id="6" value="6" name="physicalHistory"  class="gcs-checkbox">
                <label for="6"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">风疹</p><input type="checkbox" id="7" value="7" name="physicalHistory"  class="gcs-checkbox">
                <label for="7"></label>
            </div>
        </div>
        <div class="box1_left_line">
            <div class="box1_left_inline">
                <p class="db_left">白喉</p><input type="checkbox" id="8" value="8" name="physicalHistory"  class="gcs-checkbox">
                <label for="8"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">麻疹</p><input type="checkbox" id="9" value="9" name="physicalHistory"  class="gcs-checkbox">
                <label for="9"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">外伤</p><input type="checkbox" id="10" value="10" name="physicalHistory"  class="gcs-checkbox">
                <label for="10"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">贫血</p><input type="checkbox" id="11" value="11" name="physicalHistory"  class="gcs-checkbox">
                <label for="11"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">抽筋</p><input type="checkbox" id="12" value="12" name="physicalHistory"  class="gcs-checkbox">
                <label for="12"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">皮肤病</p><input type="checkbox" id="13" value="13" name="physicalHistory"  class="gcs-checkbox">
                <label for="13"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">腮腺炎</p><input type="checkbox" id="14" value="14" name="physicalHistory"  class="gcs-checkbox">
                <label for="14"></label>
            </div>
        </div>
        <div class="box1_left_line">
            <div class="box1_left_inline">
                <p class="db_left">心脏病</p><input type="checkbox" id="15" value="15" name="physicalHistory"  class="gcs-checkbox">
                <label for="15"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">癫痫病</p><input type="checkbox" id="16" value="16" name="physicalHistory"  class="gcs-checkbox">
                <label for="16"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">百日咳</p><input type="checkbox" id="17" value="17" name="physicalHistory"  class="gcs-checkbox">
                <label for="17"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">高热惊厥</p><input type="checkbox" id="18" value="18" name="physicalHistory"  class="gcs-checkbox">
                <label for="18"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">胃病</p><input type="checkbox" id="19" value="19" name="physicalHistory"  class="gcs-checkbox">
                <label for="19"></label>
            </div>
            <div class="box1_left_inline">
                <p class="db_left">其他</p><input type="checkbox" id="20" value="20" name="physicalHistory"  class="gcs-checkbox">
                <label for="20"></label>
            </div>
            <div class="box1_left_inline">
                <h2 class="db_left_beizhu">备注：</h2>
                <input class="box1_line" type="text" name="physicalHistoryMark" value="${student.physicalHistoryMark }">
            </div>
        </div>
    </div>
    <div class="box2">
        <h2 class="title_left_name">过敏物：</h2>
        <div class="box1_left_line">
            <div class="box1_left_inline">
                <p class="db_left">有：</p>
                <input class="gcs-radio" type="radio" name="allergy" value="1" id="seven"><label for="seven"
                                                                                               class="txt"></label>

            </div>
            <div class="box1_left_inline">
                <p class="db_left">无：</p>
                <input class="gcs-radio" type="radio" name="allergy" value="0" id="ten"><label for="ten"
                                                                                               class="txt"></label>
            </div>
        </div>
        <div class="box1_left_line">
            <h2 class="db_left_beizhu">备注：</h2>
            <input class="box1_line" type="text" name="allergyMark" value="${student.allergyMark }">
        </div>
    </div>
    <div class="box1">
        <div class="box1">
            <h2 class="title_left_name">有无接防御针：</h2>
            <div class="box1_left_line">
                <div class="box1_left_inline">
                    <p class="db_left">有：</p>
                    <input class="gcs-radio" type="radio" name="defensive" value="1" id="elevens"><label for="elevens"
                                                                                                     class="txt"></label>

                </div>
                <div class="box1_left_inline">
                    <p class="db_left">无：</p>
                    <input class="gcs-radio" type="radio" name="defensive" value="0" id="tennd"><label for="tennd"
                                                                                                     class="txt"></label>
                </div>
            </div>
            <div class="box1_left_line">
                <h2 class="db_left_beizhu">备注：</h2>
                <input class="box1_line" type="text" name="defensiveMark" value="${student.defensiveMark }">
            </div>
        </div>
    </div>
    <div class="box1">
        <div class="box1">
            <h2 class="title_left_name">特殊事项告知：</h2>
            <input class="input4" type="text" name="specialMatter" value="${student.specialMatter }">
        </div>
    </div>
</div>
<div style="text-align:center; ">
    <input type="button" onclick="submitForm()" class="registerBtn" value="修改"/>
</div>
</form>

<script type="text/javascript">  
$(function(){
	//下拉框 省市县赋值
	$('#provinces').find('option[value='+'${student.provinces}'+']').attr('selected','selected');
	$('#cities').find('option[value='+'${student.cities}'+']').attr('selected','selected');
	$('#counties').find('option[value='+'${student.counties}'+']').attr('selected','selected');
	
	//给选择框赋值
	$("input[name='physicalExamination'][value='${student.physicalExamination}']").attr("checked",true);
	$("input[name='physicalBook'][value='${student.physicalBook}']").attr("checked",true);
	$("input[name='healthyBook'][value='${student.healthyBook}']").attr("checked",true);
	$("input[name='allergy'][value='${student.allergy}']").attr("checked",true);
	$("input[name='defensive'][value='${student.defensive}']").attr("checked",true);
	//给复选框赋值
	var checkBox = '${student.physicalHistory}';
	var checkBoxArray = checkBox.split(",");
	for(var i=0;i<checkBoxArray.length;i++){
		$("input[name='physicalHistory']").each(function(){
			if($(this).val()==checkBoxArray[i]){
				$(this).attr("checked","checked");
			}
		})
	}
});
//addressInit('provinces', 'cities', 'counties');

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
     
    $("#gradeId").textbox('setValue','${student.gradeId }');
});
function submitForm()
{
	 var name =$("input[name='name']").val(); 
     var gradeId =$("input[name='gradeId']").val(); 
	 var clazzId =$("input[name='clazzId']").val(); 
	 
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