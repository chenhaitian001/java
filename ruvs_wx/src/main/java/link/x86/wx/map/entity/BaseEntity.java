package link.x86.wx.map.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1588997919190551273L;

    /**
     * 创建时间
     */
    @Column(name = "F_Create_Date")
    protected Date createDate;

    /**
     * 创建者
     */
    @JoinColumn(name = "F_Creator_ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    protected User creator;

    /**
     * 内码
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_ID")
    protected Integer id;

    /**
     * 当前状态
     */
    @Column(name = "F_Status")
    protected Short status;

    @Column(name = "F_Update_Date")
    protected Date updateDate;

    /**
     * 更新人
     */
    @JoinColumn(name = "F_UpdateUser_ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    protected User updateUser;

    public Date getCreateDate() {
        return createDate;
    }


    public Integer getId() {
        return id;
    }

    public Short getStatus() {
        return status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

}
