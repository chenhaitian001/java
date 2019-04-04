/**
 * 
 */
package link.x86.wx.map.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author xuwei
 * 每个 员工的打卡 考勤数据
 */
@Entity
@Table(name = "t_employee_checkin_data")
public class EmployeeCheckinData implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4316979100259683178L;

    /**
     * 内码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Integer id;

    /**
     * 当前状态
     */
    @Column(name = "status")
    protected Short status;

    /**
     * 员工loginid
     */
    @Column
    private String loginid;

    /**
     * 员工姓名
     */
    @Column
    private String username;

    /**
     * 员工员工部门
     */
    @Column
    private String departmentCode;

    /**
     * 员工员工部门名称
     */
    @Column
    private String departmentName;

    @Column
    private String year;

    @Column
    private String month;

    /**
     * 打卡类型 上班标识为P10，下班标识为P20
     */
    @Column
    private String flag;

    /**
     * 打卡日期 yyyyMMdd
     */
    @Column
    private String checkinDate;

    /**
     * 打卡时间 hhMMss
     */
    @Column
    private String checkinTime;

    @Column
    private String checkinAddress;

    /**
     * 打卡地点维护表的id,也是checkinAddress对应的ID
     */
    @Column
    private Integer checkinAddressId;

    /**
     * 经度
     */
    @Column(scale = 14)
    private BigDecimal longitude;

    /**
     * 纬度
     */
    @Column(scale = 14)
    private BigDecimal latitude;

    /**
     * 补充说明
     */
    @Column
    private String mark;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getCheckinAddress() {
        return checkinAddress;
    }

    public void setCheckinAddress(String checkinAddress) {
        this.checkinAddress = checkinAddress;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getCheckinAddressId() {
        return checkinAddressId;
    }

    public void setCheckinAddressId(Integer checkinAddressId) {
        this.checkinAddressId = checkinAddressId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
