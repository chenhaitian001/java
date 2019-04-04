package link.x86.wx.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IFHistory {

    private Long id;
    private Long cardTime;
    private String openid;
    private String teacherPhone;
    private String studentName;
    private String gx;
    private String jieSong;//jie song
    private String sendInfo;//y n
    private String jiazhang;



    private Date timestamp;


    public String getJiazhang() {
        return jiazhang;
    }

    public void setJiazhang(String jiazhang) {
        this.jiazhang = jiazhang;
    }

    private static SimpleDateFormat fmt=new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public String getTimestampStr(){
        return fmt.format(this.timestamp);
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardTime() {
        return cardTime;
    }

    public void setCardTime(Long cardTime) {
        this.cardTime = cardTime;
        this.timestamp=new Date(cardTime);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        this.cardTime=timestamp.getTime();
    }



    public static SimpleDateFormat getFmt() {
        return fmt;
    }

    public static void setFmt(SimpleDateFormat fmt) {
        IFHistory.fmt = fmt;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGx() {
        return gx;
    }

    public void setGx(String gx) {
        this.gx = gx;
    }

    public String getJieSong() {
        return jieSong;
    }

    public void setJieSong(String jieSong) {
        this.jieSong = jieSong;
    }

    public String getSendInfo() {
        return sendInfo;
    }

    public void setSendInfo(String sendInfo) {
        this.sendInfo = sendInfo;
    }
}
