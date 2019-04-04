$(function(){
      $(".Datebox").click(function (e){
        cal(e);
      });

      $("#Shift").click(function (){
          Shift();

      });

      var mark= $("#staff").val();
          $(".staff").click(function (e){
              mark= $("#staff").val();

              if(mark=="true"){
                  staff(e);
              }
          });



})

function cal(e){
    var obj = e.srcElement || e.target;
    if(obj.nodeName != "DIV"){return}
    var valuebox = obj.innerText;
    var jqObj=$(obj);
    $('.Datebox div').removeClass('addBox');
    jqObj.addClass('addBox');
}


function toshare(){
    $(".am-share").addClass("am-modal-active");
    if($(".sharebg").length>0){
        $(".sharebg").addClass("sharebg-active");
    }else{
        $("body").append('<div class="sharebg"></div>');
        $(".sharebg").addClass("sharebg-active");
    }
    $(".sharebg-active,.share_btn").click(function(){
        $(".am-share").removeClass("am-modal-active");
        setTimeout(function(){
            $(".sharebg-active").removeClass("sharebg-active");
            $(".sharebg").remove();
        },300);
    })
}



function Shift(){
    $("#staff").val('true');
    $("#Shift").text('确定');
    $("#Shift").removeClass("default").addClass("active");

}

function staff(e){


    var obj = e.srcElement || e.target;//获取当前节点的事件源
    var tdval = $(obj).text();//获取td节点的文本内容
    var tdobj = $(obj);//将当前节点转化为jquery型
    tdobj.addClass("bgks");//给当前节点增加样式

}







