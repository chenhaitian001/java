import link.x86.util.HttpUtil;

public class test2 {


    public static void main(String[] args) {

        String appid="";
        String code="";


        String resuString=HttpUtil.getInstance().doGet("https://api.weixin.qq.com/cgi-bin/token?" +
                "appid=wx4b55379ff824cbad&secret=4adcbed79887253ad0d8d06a14469627&code=021kvkrO0Qw2eb29lYnO0cJirO0kvkrE&grant_type=authorization_code");

        System.out.println(resuString);
    }
}
