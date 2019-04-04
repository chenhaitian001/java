package link.x86.winxin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import link.x86.util.HttpUtil;
import link.x86.util.ObjectUtil;
import link.x86.winxin.bean.AccessToken;
import link.x86.winxin.bean.UserInfo;
import link.x86.winxin.business.Info;
import org.apache.http.client.methods.HttpPost;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeiXin {


    private static Logger logger=LoggerFactory.getLogger(WeiXin.class);

    private String appId;
    private String appsecret;
    private String token;


    public void loadToken(){
        String uri=new StringBuffer("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=")
                .append(this.appId).append("&secret=").append(this.appsecret).toString();

        String resuString=HttpUtil.getInstance().doGet(uri);

        JSONObject jsonObject = JSONObject.parseObject(resuString);

        String token=jsonObject.getString("access_token");
        this.token=token;
        logger.info("token {}",token);
        logger.info(resuString);
    }

    /**
     * 获取token
     * @return
     */
    public String getToken(){



        return this.token;
    }


    /**
     * 获取用户OpenId信息
     * @param nexOpenId
     * @return
     */
    public String[] getUserOpenIds(String nexOpenId){
        String uri=new StringBuilder("https://api.weixin.qq.com/cgi-bin/user/get?access_token=")
                .append(this.token).append("&next_openid=").append(nexOpenId==null?"":nexOpenId).toString();
        String resuString=HttpUtil.getInstance().doGet(uri);

        JSONObject jsonObject = JSONObject.parseObject(resuString);

        if(jsonObject.getInteger("count")>0){

            return jsonObject.getJSONObject("data").getJSONArray("openid").toArray(new String[]{});
        }else{
            return null;
        }


    }


    public UserInfo getUserInfo(String openId){

        String uri=new StringBuilder("https://api.weixin.qq.com/cgi-bin/user/info?access_token=")
                .append(this.token).append("&openid=").append(openId).toString();
        String resuString=HttpUtil.getInstance().doGet(uri);
        return JSONObject.parseObject(resuString,new TypeReference<UserInfo>(){});

    }



    public String sendTemplateMsg(String jsonBody) throws UnsupportedEncodingException{

        String uri="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+this.token;
        logger.debug(uri);
        String resuString=HttpUtil.getInstance().doPostJson(uri,jsonBody);
        logger.debug(jsonBody);
        logger.debug(resuString);
		return resuString;

    }


    public List<UserInfo> getUserInfos(){

        List<UserInfo> userInfos=new ArrayList<UserInfo>();
        String [] openids=this.getUserOpenIds(null);

        for (String openid:openids) {
            userInfos.add(this.getUserInfo(openid));
        }
        return userInfos;
    }


    public AccessToken getAccessToken(String code){


        String uri=MessageFormat.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid={0}&secret={1}&code={2}&grant_type=authorization_code",this.appId,this.appsecret,code);


        logger.debug(uri);
        String resuJson=HttpUtil.getInstance().doGet(uri);
        logger.debug("获取accessToken {}",resuJson);
        logger.debug("codex信息为 {}",code);
        return JSONObject.parseObject(resuJson,AccessToken.class);

    }


    public String getTextMsg(String openId,String formUserName,String msg){


        String tmp="<xml> " +
                "   <ToUserName><![CDATA[{0}]]></ToUserName> " +
                "   <FromUserName><![CDATA[{1}]]></FromUserName> " +
                "   <CreateTime>{2}</CreateTime> " +
                "   <MsgType><![CDATA[text]]></MsgType> " +
                "   <Content><![CDATA[{3}]]></Content> " +
                "</xml>";


        return MessageFormat.format(tmp,openId,formUserName,((int)new Date().getTime()/1000)+"",msg);

    }


    public static void main(String[] args) {


        SimpleDateFormat sfm=new SimpleDateFormat("MM月dd日 HH:mm");

        String dateTime=sfm.format(new Date());


//        Map<String,Object>  mapBody=ObjectUtil.toMap("touser","oACbC0meENNvieRddBpAcx2NrL0o"
//                ,"template_id","9vzPmRQw2XWONwjY3SZzMSzuWF0-KJp3QAomezXRar8"
//                ,"data",ObjectUtil.toMap("dateTime",ObjectUtil.toMap("value",dateTime,"color","#173177"),
//                        "relation",ObjectUtil.toMap("value","母亲","color","#173177"),
//                        "studentName",ObjectUtil.toMap("value","圆圆","color","#173177")));





        WeiXin weiXin=new WeiXin();
        weiXin.appId="wx4b55379ff824cbad";
        weiXin.appsecret="4adcbed79887253ad0d8d06a14469627";
        weiXin.loadToken();

        String jsonBody=Info.info1("oT6Bi57D1aUNA8Ugftbv24dkC_2U","圆圆","父亲","大圆");
        logger.debug(jsonBody);
        logger.debug(weiXin.token);
        try {
            weiXin.sendTemplateMsg(jsonBody);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
