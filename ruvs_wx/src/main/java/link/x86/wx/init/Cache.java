package link.x86.wx.init;

import link.x86.winxin.WeiXin;
import link.x86.winxin.bean.UserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 通缓存
 */
public class Cache {

    private static Logger logger=LoggerFactory.getLogger(Cache.class);

    public static WeiXin weiXin;

    static{
        weiXin=new WeiXin();
        weiXin.setAppId(Config.appid);
        weiXin.setAppsecret(Config.appsecret);
        weiXin.loadToken();
    }



    public static Map<String,UserInfo> openidUserInfo=new HashMap<String,UserInfo>();
    public static Map<String,UserInfo> phoneUserInfo=new HashMap<String,UserInfo>();

    public static void addUserInfo(UserInfo userInfo){
        openidUserInfo.put(userInfo.getOpenid(),userInfo);
        phoneUserInfo.put(userInfo.getPhone(),userInfo);
    }
    public static void removeserInfo(UserInfo userInfo){

        openidUserInfo.remove(userInfo.getOpenid());
        phoneUserInfo.remove(userInfo.getPhone());
    }




    public static void main(String[] args) {
//        loadToken();
//        System.out.println(token);


        String signature = "7b1aba952b31cee64aea68bcb83006d883d84b07";
        String timestamp = "1533578920";
        String nonce = "712573294";
        String echostr = "4461001898450595758";
        String token="12_7PmeNNa-48DJ__G4kF9NhWr1tAJXtu9vMRMiav8tY5HzWkLa_SzAuOX3R9lmuwxO6Qy867gSQHqrNKccGDKKwlB7FuMeKruZGkKXxfHaYXH5konFCob00KNcb8XvxK9HrPgTCmuBWM2W3dAaQSSaABAKMM";


        //7b1aba952b31cee64aea68bcb83006d883d84b07 1533578920 712573294 4461001898450595758


        logger.info(signature+" "+timestamp+" "+nonce+" "+echostr);

        if(signature!=null&&timestamp!=null&&nonce!=null&&echostr!=null) {
            String[] tmparr = new String[]{timestamp, nonce, token};
            Arrays.sort(tmparr);

            String tmpstr = tmparr[0]+tmparr[1]+tmparr[2];
            String tmpsha1 = DigestUtils.sha1Hex(tmpstr);

            logger.info("计算结果："+tmpsha1);

        }


            String s="12_7PmeNNa-48DJ__G4kF9NhWr1tAJXtu9vMRMiav8tY5HzWkLa_SzAuOX3R9lmuwxO6Qy867gSQHqrNKccGDKKwlB7FuMeKruZGkKXxfHaYXH5konFCob00KNcb8XvxK9HrPgTCmuBWM2W3dAaQSSaABAKMM15335789091698288697";
//         8561094983895936765

//        7b1aba952b31cee64aea68bcb83006d883d84b07
//        187d8b9c6f3f49dac71f064dedb2923726660a3f
            System.out.println(DigestUtils.sha1Hex(s));;
    }
}
