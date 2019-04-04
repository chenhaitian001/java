package link.x86.wx.db;

import link.x86.winxin.bean.UserInfo;
import link.x86.wx.init.Config;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DBUtils {

    static {
        try {
            Class.forName(Config.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection createConnection() {

        try {
            return DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = createConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void closes(Object... objs) {
        for (Object obj : objs) {

            if (obj instanceof Connection) {
                try {
                    ((Connection) obj).close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (obj instanceof Statement) {
                try {
                    ((Statement) obj).close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (obj instanceof ResultSet) {
                try {
                    ((ResultSet) obj).close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (obj instanceof PreparedStatement) {
                try {
                    ((PreparedStatement) obj).close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }


    public static void saveIFHistory(IFHistory ifHistory) {

        String sql = "INSERT INTO `lf_history` " +
                "(`card_time`, `openid`, `student_name`, `guanxi`, `jie_song`, `send_info`,`teacher_phone`,`jiazhang`)" +
                " VALUES (?, ?, ?, ?, ? ,?,?,?);";

        Connection conn = null;
        PreparedStatement pstate = null;
        try {

            conn = getConnection();
            pstate = conn.prepareStatement(sql);
            pstate.setTimestamp(1, new Timestamp(ifHistory.getTimestamp().getTime()));
            pstate.setString(2, ifHistory.getOpenid());
            pstate.setString(3, ifHistory.getStudentName());
            pstate.setString(4, ifHistory.getGx());
            pstate.setString(5, ifHistory.getJieSong());
            pstate.setString(6, ifHistory.getSendInfo());
            pstate.setString(7, ifHistory.getTeacherPhone());
            pstate.setString(8, ifHistory.getJiazhang());
            pstate.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closes(pstate, conn);
        }
    }


    public static List<IFHistory> getIFHistory(String openid, String date, String jieSong) {

        String sql = null;



        if ("jie".equals(jieSong)) {
            //接使用最晚时间
            sql = "select * from lf_history where id in (" +
                    "SELECT max(id) as id FROM lf_history " +
                    "where openid=? and  card_time>=? and  card_time<? and jie_song=? group by student_name ) order by id desc ";
        }else{

            sql = "select * from lf_history where id in (" +
                    "SELECT min(id) as id FROM lf_history " +
                    "where openid=? and  card_time>=? and  card_time<? and jie_song=? group by student_name ) order by id desc ";
            //送使用最早时间
        }



        Timestamp stime = null, etime = null;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date ddste = fmt.parse(date);
            Calendar cs = Calendar.getInstance();
            cs.setTime(ddste);
            stime = new Timestamp(cs.getTimeInMillis());
            cs.add(Calendar.DAY_OF_MONTH, 1);
            etime = new Timestamp(cs.getTimeInMillis());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<IFHistory> ifHistories = new ArrayList<IFHistory>();

        Connection conn = null;
        PreparedStatement pstate = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, openid);
            pstate.setTimestamp(2, stime);
            pstate.setTimestamp(3, etime);
            pstate.setString(4, jieSong);

            resultSet = pstate.executeQuery();

            while (resultSet.next()) {
                IFHistory ifHistory = new IFHistory();
                ifHistory.setStudentName(resultSet.getString("student_name"));
                ifHistory.setJieSong(resultSet.getString("jie_song"));
                ifHistory.setGx(resultSet.getString("guanxi"));
                ifHistory.setTimestamp(resultSet.getTimestamp("card_time"));
                ifHistory.setSendInfo(resultSet.getString("send_info"));
                ifHistory.setId(resultSet.getLong("id"));
                ifHistory.setOpenid(resultSet.getString("openid"));
                ifHistory.setTeacherPhone(resultSet.getString("teacher_phone"));
                ifHistory.setJiazhang(resultSet.getString("jiazhang"));
                ifHistories.add(ifHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closes(pstate, conn);
        }

        return ifHistories;


    }


    public static void saveWeixinInfo(String openid, String wxname) {


        String sql = "INSERT INTO `lf_weixin_phone` (`openid`,`wxname`)" +
                " VALUES (?,?);";

        Connection conn = null;
        PreparedStatement pstate = null;
        try {

            conn = getConnection();
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, openid);
            //TODO 微信名有异常编码，暂使用空字符串代替,更新数据库编码后可用
            pstate.setString(2, "  ");
            pstate.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closes(pstate, conn);
        }


    }

    public static void changeWeixinInfoPhone(String openid, String phone) {

        String sql = "update  `lf_weixin_phone` set phone=? where openid=? ";

        Connection conn = null;
        PreparedStatement pstate = null;
        try {

            conn = getConnection();
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, phone);
            pstate.setString(2, openid);
            pstate.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closes(pstate, conn);
        }
    }


    public static void removeWeixinInfo(String openid) {

        String sql = "delete from  `lf_weixin_phone` where openid=? ";

        Connection conn = null;
        PreparedStatement pstate = null;
        try {

            conn = getConnection();
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, openid);
            pstate.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closes(pstate, conn);
        }
    }

    public static void removeWeixinInfoByPhone(String phone) {

        String sql = "delete from  `lf_weixin_phone` where phone=? ";

        Connection conn = null;
        PreparedStatement pstate = null;
        try {

            conn = getConnection();
            pstate = conn.prepareStatement(sql);
            pstate.setString(1, phone);
            pstate.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closes(pstate, conn);
        }
    }


    public static List<UserInfo> userInfoList() {


        String sql = "SELECT `id`, `openid`, `phone`, `wxname` FROM `lf_weixin_phone` order by id desc;";
        List<UserInfo> ifHistorys = new ArrayList<UserInfo>();

        Connection conn = null;
        PreparedStatement pstate = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            pstate = conn.prepareStatement(sql);
            resultSet = pstate.executeQuery();

            while (resultSet.next()) {
                UserInfo userInfo = new UserInfo();
                userInfo.setPhone(resultSet.getString("phone"));
                userInfo.setOpenid(resultSet.getString("openid"));
                userInfo.setNickname(resultSet.getString("wxname"));
                ifHistorys.add(userInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closes(pstate, conn);
        }

        return ifHistorys;

    }


}
