package link.x86.wx.web;

import link.x86.winxin.bean.UserInfo;
import link.x86.wx.db.DBUtils;
import link.x86.wx.init.Cache;
import link.x86.wx.init.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.MessageFormat;
import java.util.*;


/**
 * 微信认证的接口
 */
public class WXEventServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(WXEventServlet.class);


    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException      接收处理微信认证
     *                          <p>
     *   1. timestamp nonce token 排序，组成一个厂字符串
     *   2. 进行sha1加密，与signature进行比较
     *   3. 结果为true，返回echostr 否则返回error
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");


        logger.info(signature + " " + timestamp + " " + nonce + " " + echostr);

        if (signature != null && timestamp != null && nonce != null && echostr != null) {
            String[] tmparr = new String[]{timestamp, nonce, Config.token};
            Arrays.sort(tmparr);

            String tmpstr = tmparr[0] + tmparr[1] + tmparr[2];
            String tmpsha1 = DigestUtils.sha1Hex(tmpstr);

            logger.info("计算结果：" + tmpsha1);


            if (tmpsha1.equals(signature)) {
                resp.getOutputStream().print(echostr);
                return;
            }
        }
        resp.getOutputStream().print("error");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.setCharacterEncoding("UTF-8");

        SAXReader saxReader = new SAXReader();


        Document document = null ;
        InputStream inputStream=req.getInputStream();
        try {
            document = saxReader.read(new InputStreamReader(inputStream));
            Element element = document.getRootElement();



            Map<String, String> msgMap = new HashMap<String, String>();
            for (Iterator<Element> it = element.elementIterator(); it.hasNext(); ) {
                Element e = it.next();
                System.out.println(e.getName() + "  " + e.getText());
                msgMap.put(e.getName(), e.getText());
            }

            //用户号
            String openId=msgMap.get("FromUserName");
            //公众号
            String toUserName=msgMap.get("ToUserName");
            if (msgMap.get("MsgType").equals("event")) {
                if (msgMap.get("Event").equals("subscribe")) {
                    // 用户订阅
                    UserInfo userInfo=Cache.weiXin.getUserInfo(openId);
                    Cache.addUserInfo(userInfo);
                    DBUtils.saveWeixinInfo(userInfo.getOpenid(),userInfo.getNickname());
                    logger.info("新用户订阅  "+userInfo);
                } else if (msgMap.get("Event").equals("unsubscribe")) {
                    // 用户取消订阅
                    UserInfo userInfo=Cache.openidUserInfo.get(openId);
                    Cache.removeserInfo(userInfo);
                    DBUtils.removeWeixinInfo(userInfo.getOpenid());
                    logger.info("用户取消订阅  "+userInfo);
                } else if(msgMap.get("Event").equals("CLICK")){

                    UserInfo userInfo=Cache.openidUserInfo.get(openId);
                    req.getSession().setAttribute("userInfo",userInfo);
                    logger.info("用户点击菜单  "+userInfo);

                    String eventKey=msgMap.get("EventKey");

                    if("event_binding_phone".equals(eventKey)){
                        // 用户点击绑定手机按钮

                        String msg="您尚未绑定手机号，请输入手机号进行绑定。\n请认真输入以免错误绑定。";
                        if(userInfo.getPhone()!=null&&userInfo.getPhone().length()>5){
                            msg=MessageFormat.format("您已绑定手机号{0}，请输入新手机号进行更换。",userInfo.getPhone());
                        }
                        String resuMsg=Cache.weiXin.getTextMsg(openId,toUserName,msg);

                        resp.getWriter().write(resuMsg);
                        logger.debug("回复消息:{}",resuMsg);
                        resp.getWriter().flush();
                        return;
                    }else if("event_unbinding_phone".equals(eventKey)){
                        // 用户点击绑定手机按钮

                        String phone=userInfo.getPhone();
                        logger.debug("您已经成功取消手机号【{}】的绑定",phone);

                        userInfo.setPhone("");
                        Cache.phoneUserInfo.remove(phone);


                        DBUtils.changeWeixinInfoPhone(userInfo.getOpenid(),userInfo.getPhone());
                        String msg=MessageFormat.format("您已经成功取消手机号【{0}】的绑定",phone);
                        String resuMsg=Cache.weiXin.getTextMsg(openId,toUserName,msg);
                        resp.getWriter().print(resuMsg);

                        resp.getWriter().flush();
                        return;
                    } else if("about_us".equals(eventKey)){

                        String msg="“育智云”是鹤诺科技（北京）有限公司以智能硬件为切入,基于PC平台专为幼儿园量身定做的智慧幼儿园一体化管理系统。" +
                                "通过平台，可以轻松管理和协调幼儿园内部各部门高效运作，提升幼儿园形象和核心竞争力，让园长管理工作更轻松；" +
                                "实现老师高效教学、轻松教学，架起老师与家长便捷沟通的桥梁；" +
                                "家园共育，让家长实时掌握园所动态、幼儿信息，帮助家长快乐育儿、科学育儿。" +
                                "育智云，更懂幼儿更懂你！";

                        String resuMsg=Cache.weiXin.getTextMsg(openId,toUserName,msg);

                        resp.getWriter().write(resuMsg);
                        logger.debug("回复消息:{}",resuMsg);
                        resp.getWriter().flush();

                    } else if("contact_us".equals(eventKey)){
                        String msg="您可在微信公众账号中直接切换至输入框与我们联系，客服人员会及时与您沟通；" +
                                "或拨打我们的客服电话010-53589869（周一至周五9:00—18:00）。";

                        String resuMsg=Cache.weiXin.getTextMsg(openId,toUserName,msg);

                        resp.getWriter().write(resuMsg);
                        logger.debug("回复消息:{}",resuMsg);
                        resp.getWriter().flush();
                    }
                }
            }else if(msgMap.get("MsgType").equals("text")){

                String readmsg=msgMap.get("Content").trim();
                if(readmsg.matches("1\\d{10}")){
                    String phone=readmsg;
                    logger.debug("绑定手机号为:{}",phone);


                    UserInfo userInfo=Cache.phoneUserInfo.get(phone);

                    if(userInfo==null) {
                        userInfo = Cache.openidUserInfo.get(openId);
                        userInfo.setPhone(phone);
                        Cache.phoneUserInfo.put(phone, userInfo);
                        DBUtils.changeWeixinInfoPhone(userInfo.getOpenid(), userInfo.getPhone());
                        String msg = MessageFormat.format("恭喜您，手机号【{0}】绑定成功", phone);
                        String resuMsg = Cache.weiXin.getTextMsg(openId, toUserName, msg);
                        resp.getWriter().print(resuMsg);
                    }else{
                        String msg = MessageFormat.format("手机号【{0}】已经被绑定，请使用其他手机号。或者联系管理员", phone);
                        String resuMsg = Cache.weiXin.getTextMsg(openId, toUserName, msg);
                        resp.getWriter().print(resuMsg);
                    }
                }

            }


//            logger.info(sb.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }


        resp.getWriter().print("");
    }


}
