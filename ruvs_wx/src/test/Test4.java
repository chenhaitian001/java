import link.x86.winxin.WeiXin;
import link.x86.wx.init.Config;

public class Test4 {

    public static void main(String[] args) throws Exception {

        WeiXin wx=new WeiXin();
        wx.setAppId(Config.appid);
        wx.setAppsecret(Config.appsecret);
        Config.load();
        wx.loadToken();
        Thread.sleep(10000);
        System.out.println(wx.getToken());
        Thread.sleep(10000);
    }
}
