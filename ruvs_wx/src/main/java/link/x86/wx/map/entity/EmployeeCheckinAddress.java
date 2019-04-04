package link.x86.wx.map.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 每个员工的打卡地点
 * 
 * @author xuwei
 * 
 */
@Entity
@Table(name = "t_employee_checkin_address")
public class EmployeeCheckinAddress extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 5189545606568928751L;

    /**
     * 员工loginid
     */
    @Column
    private String loginid;

    /**
     * 打卡地点名称
     */
    @Column
    private String checkinAddress;
    
    /**
     * 打卡地点维护表的id,也是checkinAddress对应的ID
     */
    @Column
    private Integer checkinAddressId;

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

}
