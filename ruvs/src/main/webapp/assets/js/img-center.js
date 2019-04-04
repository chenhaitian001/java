/**
 * Created by Randy on 2016/9/13.
 */
$(function(){
    zmnImgCenter($(".t-img"));//JQ??dom
});
//??????
function zmnImgCenter(obj){
    obj.each(function(){
        var $this=$(this);
        var objHeight=$this.height();//?????
        var objWidth=$this.width();//?????
        var parentHeight=$this.parent().height();//???????????
        var parentWidth=$this.parent().width();//???????????
        var ratio=objHeight/objWidth;
        if(objHeight>parentHeight && objWidth>parentWidth){//????????????????????
            if(objHeight>objWidth) {//??????
                $this.width(parentWidth);
                $this.height(parentWidth*ratio);
            }
            else {
                $this.height(parentHeight);
                $this.width(parentHeight/ratio);
            }
            objHeight=$this.height();//?????????
            objWidth=$this.width();
            if(objHeight>objWidth) {
                $(this).css("top",(parentHeight-objHeight)/2);
                //????top????
            }
            else
            {
                //????left????
                $(this).css("left",(parentWidth-objWidth)/2);
            }
        }
        else{//???????§³??????????
            if(objWidth>parentWidth){//???????????????§³???????css text-align???????
                $(this).css("left",(parentWidth-objWidth)/2);
            }
            $(this).css("top",(parentHeight-objHeight)/2);
        }
    })
}