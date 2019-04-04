$(function(){
    //登录注册验证
    $("#signupForm").validate({
        rules: {
            username: "required",
            gender: "required",
            birthday: "required",
            number: {
                required: true,
                minlength: 10
            },
            relationship: "required",
            telphone: {
                required: true,
                minlength: 11,
                digits: true
            }
        },
        messages: {
            username: {
                required: "请填写幼儿姓名!",
            },
            gender: {
                required: "请选择性别!",
            },
            birthday: {
                required: "请选择生日!",
            },
            number: {
                required: "请填写识别号!",
                minlength: "识别号必需由10个数字组成!",
            },
            relationship: {
                required: "请选择关系!",
            },
            telphone: {
                required: "请填写手机号!",
                minlength: "手机号必需由11个数字组成!",
                digits: "只能填写数字!"
            }
        },
        /* 重写错误显示消息方法*/
        showErrors : function(errorMap,errorList) {   
            if(errorList.length>0){
                var msg = errorList[0].message;
                $('.errorMessage').find("strong").text(msg)
            }               
        },
        /* 失去焦点时不验证 */
        onfocusout : false,
        onkeyup:false

    });


    
});
