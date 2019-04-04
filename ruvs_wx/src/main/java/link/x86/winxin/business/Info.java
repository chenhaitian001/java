package link.x86.winxin.business;

import com.alibaba.fastjson.JSONObject;
import link.x86.winxin.TemplateData;
import link.x86.wx.init.Config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Info {


    /**
     * 家长到园通知信息
     * @param studentName
     * @param relation
     */
    public static String info1(String openId,String studentName,String relation,String jiazhang){


        SimpleDateFormat sfm=new SimpleDateFormat("MM月dd日 HH:mm");
        String dateTime=sfm.format(new Date());
        TemplateData templateData=TemplateData.New().setTemplate_id(Config.msgTemplateId)
                .setTouser(openId)
                .add("first","老师您好，有家长在园外等候。","#173177")
                .add("keyword1",dateTime,"#173177")
                .add("keyword2",studentName,"#173177")
                .add("keyword3",jiazhang,"#173177")
                .add("keyword4",relation,"#173177")
                .add("remark","如有疑问，请留言说明，谢谢。","#173177");

        return JSONObject.toJSONString(templateData);
    }
}
