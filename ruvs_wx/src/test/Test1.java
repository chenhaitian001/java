import link.x86.util.HttpUtil;
import link.x86.winxin.bean.UserInfo;
import link.x86.wx.init.Cache;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test1 {


    public static void main(String[] args) throws DocumentException, UnsupportedEncodingException {


        String txt = /*"<xml>" +
                "<ToUserName><![CDATA[gh_ad385f30f080]]></ToUserName>" +
                "<FromUserName><![CDATA[oACbC0meENNvieRddBpAcx2NrL0o]]>" +
                "</FromUserName><CreateTime>1534095244</CreateTime>" +
                "<MsgType><![CDATA[event]]></MsgType>" +
                "<Event><![CDATA[subscribe]]></Event>" +
                "<Content><![CDATA[你好]]></Content>" +
                "<MsgId>6588888902356851679</MsgId>" +
                "</xml>";*/
        		                                              
    "    <xml>                                                "  +
    "    <ToUserName><![CDATA[toUser]]></ToUserName>          "  +
    "    <FromUserName><![CDATA[fromUser]]></FromUserName>    "  +
    "    <CreateTime>1351776360</CreateTime>                  "  +
    "    <MsgType><![CDATA[location_select]]></MsgType>              "  +
    "    <Location_X>23.134521</Location_X>                   "  +
    "    <Location_Y>113.358803</Location_Y>                  "  +
    "    <Scale>20</Scale>                                    "  +
    "    <Label><![CDATA[位置信息]]></Label>                  "  +
    "    <MsgId>1234567890123456</MsgId>                      "  +
    "    </xml>                                               "  ;

        SAXReader saxReader = new SAXReader();


        Document document = saxReader.read(new ByteArrayInputStream(txt.getBytes("UTF-8")));
        Element element = document.getRootElement();

        Map<String, String> msgMap = new HashMap<String, String>();
        for (Iterator<Element> it = element.elementIterator(); it.hasNext(); ) {
            Element e = it.next();
            System.out.println(e.getName() + "  " + e.getText());
            msgMap.put(e.getName(), e.getText());
        }


        /*String openId=msgMap.get("FromUserName");
        if (msgMap.get("MsgType").equals("event")) {
            if (msgMap.get("Event").equals("subscribe")) {
                // 用户订阅
                UserInfo userInfo=Cache.weiXin.getUserInfo(openId);
                System.out.println(userInfo);
            } else if (msgMap.get("Event").equals("subscribe")) {
                // 用户取消订阅
            }
        }*/
        if (msgMap.get("MsgType").equals("location_select")) {
            String location_x = msgMap.get("Location_X");
            String location_y = msgMap.get("Location_Y");
            String location = getBaiDuLocationXY(location_x,location_y);
            String msg = MessageFormat.format("地理位置:", location+"--签到成功");
           
        }
        
        

    }
    public static String getBaiDuLocationXY(String x, String y) {
        String result = "";
        String url = "http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
                + x + "&y=" + y + "";
        String response = HttpUtil.getInstance().doGet(url);
        System.out.println(response);
        /*if (!isEmpty(response)) {
            MapConvert map = com.alibaba.fastjson.JSON.parseObject(response,MapConvert.class);
            if (map != null && 0 == map.getError()) {
                byte[] xbuff = Base64.decodeFast(map.getX());
                byte[] ybuff = Base64.decodeFast(map.getY());
                result = new String(xbuff) + "|" + new String(ybuff);
            }
        }*/
        return result;
    }
}
