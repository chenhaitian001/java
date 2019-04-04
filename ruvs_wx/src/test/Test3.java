import java.text.MessageFormat;

public class Test3 {

    public static void main(String[] args) {
        String uri=MessageFormat.format("https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid={0}&secret={1}&code={2}&grant_type=authorization_code",1,2,3);

        System.out.println(uri);
    }
}
