package link.x86.wx.map.entity;

import java.io.Serializable;
import java.math.BigDecimal;



public class EmployeeCheckinAddressVO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1148233650011151585L;

    private String loginid;
    
    /**
     * 姓名
     */
    private String username;

    /**
     * 打卡地点名称
     */
    private String checkinAddress;

    /**
     * 打卡地点维护表的id,也是checkinAddress对应的ID
     */
    private Integer checkinAddressId;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    private Integer preciseMeter;

    /**
     * 打卡日期 yyyyMMdd
     */
    private String checkinDate;

    /**
     * 打卡时间 hhMMss
     */
    private String checkinTime;

    /**
     * 是否有多个打卡地点 Y/N
     */
    private String hasOtherCheckinPlace;

    private String jobTitleName;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getCheckinAddress() {
        return checkinAddress;
    }

    public void setCheckinAddress(String checkinAddress) {
        this.checkinAddress = checkinAddress;
    }

    public Integer getCheckinAddressId() {
        return checkinAddressId;
    }

    public void setCheckinAddressId(Integer checkinAddressId) {
        this.checkinAddressId = checkinAddressId;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getPreciseMeter() {
        return preciseMeter;
    }

    public void setPreciseMeter(Integer preciseMeter) {
        this.preciseMeter = preciseMeter;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getHasOtherCheckinPlace() {
        return hasOtherCheckinPlace;
    }

    public void setHasOtherCheckinPlace(String hasOtherCheckinPlace) {
        this.hasOtherCheckinPlace = hasOtherCheckinPlace;
    }

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
