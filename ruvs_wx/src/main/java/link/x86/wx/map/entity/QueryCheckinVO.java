/**
 * 
 */
package link.x86.wx.map.entity;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * @author xuwei
 * 考勤结果信息vo 调HCM接口返回的数据
 */
public class QueryCheckinVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6422499638101818194L;

    /**
     * 员工工号
     */
    private String loginid;

    /**
     * 姓名
     */
    private String username;

    /**
     * 打卡日期yyyyMMdd
     */
    private String checkinDate;

    /**
     * 打卡日期dd 只有日期，没有年月
     */
    private String shortCheckinDate;

    /**
     * 排班计划
     */
    private String checkinPlan;

    /**
     * 店铺班次名称
     */
    private String scheduleName;

    /**
     * 排班具体上班时间段
     */
    private String scheduleTimeString;

    /**
     * 排班是否正常,排班了Y ，没排班 N，休息日R
     */
    private String scheduleFlag;

    /**
     * 排班是否正常,排班了“已排” ，，休息日 “休”
     */
    private String scheduleStatus;

    /**
     * 上午上班打卡时间 hhMMss
     */
    private String amBeforeWorkTime;

    /**
     * 上午上班打卡地点
     */
    private String amBeforeWorkPlace;

    /**
     * 下午上班打卡时间 hhMMss
     */
    private String pmBeforeWorkTime;

    /**
     * 下午上班打卡地点
     */
    private String pmBeforeWorkPlace;

    /**
     * 下午下班打卡时间 hhMMss
     */
    private String pmAfterWorkTime;

    /**
     * 下午下班打卡地点
     */
    private String pmAfterWorkPlace;

    /**
     * 考勤结果类型 E:考勤异常，S：考勤正常
     */
    private String checkinResultFlag;

    /**
     * 考勤结果
     */
    private String checkinResultString;

    /**
     * 移动考勤查询缺勤及出勤返回结果
     */
    private String checkinResultInfo;

    /**
     * 当月当天以后的情况，则这个字段赋值YES，否则为空
     */
    private String previousDaysFlag;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckinPlan() {
        return checkinPlan;
    }

    public void setCheckinPlan(String checkinPlan) {
        this.checkinPlan = checkinPlan;
    }

    public String getCheckinResultFlag() {
        return checkinResultFlag;
    }

    public void setCheckinResultFlag(String checkinResultFlag) {
        this.checkinResultFlag = checkinResultFlag;
    }

    public String getCheckinResultString() {
        return checkinResultString;
    }

    public void setCheckinResultString(String checkinResultString) {
        this.checkinResultString = checkinResultString;
    }

    public String getCheckinResultInfo() {
        return checkinResultInfo;
    }

    public void setCheckinResultInfo(String checkinResultInfo) {
        this.checkinResultInfo = checkinResultInfo;
    }

    public String getAmBeforeWorkTime() {
        return amBeforeWorkTime;
    }

    public void setAmBeforeWorkTime(String amBeforeWorkTime) {
        this.amBeforeWorkTime = amBeforeWorkTime;
    }

    public String getAmBeforeWorkPlace() {
        return amBeforeWorkPlace;
    }

    public void setAmBeforeWorkPlace(String amBeforeWorkPlace) {
        this.amBeforeWorkPlace = amBeforeWorkPlace;
    }

    public String getPmBeforeWorkTime() {
        return pmBeforeWorkTime;
    }

    public void setPmBeforeWorkTime(String pmBeforeWorkTime) {
        this.pmBeforeWorkTime = pmBeforeWorkTime;
    }

    public String getPmBeforeWorkPlace() {
        return pmBeforeWorkPlace;
    }

    public void setPmBeforeWorkPlace(String pmBeforeWorkPlace) {
        this.pmBeforeWorkPlace = pmBeforeWorkPlace;
    }

    public String getPmAfterWorkTime() {
        return pmAfterWorkTime;
    }

    public void setPmAfterWorkTime(String pmAfterWorkTime) {
        this.pmAfterWorkTime = pmAfterWorkTime;
    }

    public String getPmAfterWorkPlace() {
        return pmAfterWorkPlace;
    }

    public void setPmAfterWorkPlace(String pmAfterWorkPlace) {
        this.pmAfterWorkPlace = pmAfterWorkPlace;
    }

    public String getShortCheckinDate() {
        return shortCheckinDate;
    }

    public void setShortCheckinDate(String shortCheckinDate) {
        this.shortCheckinDate = shortCheckinDate;
    }

    public String getPreviousDaysFlag() {
        return previousDaysFlag;
    }

    public void setPreviousDaysFlag(String previousDaysFlag) {
        this.previousDaysFlag = previousDaysFlag;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleTimeString() {
        return scheduleTimeString;
    }

    public void setScheduleTimeString(String scheduleTimeString) {
        this.scheduleTimeString = scheduleTimeString;
    }

    public String getScheduleFlag() {
        return scheduleFlag;
    }

    public void setScheduleFlag(String scheduleFlag) {
        this.scheduleFlag = scheduleFlag;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

}
