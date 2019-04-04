package link.x86.wx.map.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "t_user")
public class User implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // @GeneratedValue(strategy=GenerationType.AUTO)
    /**
     * 唯一标识（UUID）
     */
    @Id
    @Column(name = "F_ID")
    private String id;

    /**
     * 登录账号
     */
    @Column(name = "F_Login_ID", length = 100)
    private String loginId;

    /**
     * 密码
     */
    @Column(name = "F_Password", length = 255)
    private String password;

    /**
     * 姓名
     */
    @Column(name = "F_Name", length = 100)
    private String name;

    /**
     * 性别
     */
    @Column(name = "F_Sex")
    private Integer sex;

    /**
     * 生日
     */
    @Column(name = "F_Birthday")
    private Date birthday;

    /**
     * 电话
     */
    @Column(name = "F_Telphone", length = 50)
    private String telphone;

    /**
     * 手机
     */
    @Column(name = "F_Mobile", length = 50)
    private String mobile;

    /**
     * 邮件地址
     */
    @Column(name = "F_Email", length = 50)
    private String email;

    /**
     * 地址
     */
    @Column(name = "F_Address", length = 255)
    private String address;

    /**
     * 描述
     */
    @Column(name = "F_Description", length = 255)
    private String description;

    /**
     * 身份证
     */
    @Column(name = "F_Card", length = 50)
    private String card;

    /**
     * 省份
     */
    @Column(name = "F_Province_ID", length = 50)
    private String provinceId;

    /**
     * 城市
     */
    @Column(name = "F_City_ID", length = 50)
    private String cityId;

    /**
     * 邮编
     */
    @Column(name = "F_Zip", length = 50)
    private String zip;

    /**
     * 创建者
     */
    @JoinColumn(name = "F_Creator_ID")
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    private User creator;

    /**
     * 创建日期
     */
    @Column(name = "F_Create_Date")
    private Date createDate;

    /**
     * 更新者
     */
    @JoinColumn(name = "F_UpdateUser_ID")
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    private User updateUser;

    /**
     * 更新时间
     */
    @Column(name = "F_Update_Date")
    private Date updateDate;

    /**
     * 备注
     */
    @Column(name = "F_Note")
    private String note;

    @Column(name = "F_Status")
    private Integer status;



    @Column(name = "F_Zone_Code")
    private String zoneCode;
    
    @Column(name = "F_Permission_Code")
    private String permissionCode;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
