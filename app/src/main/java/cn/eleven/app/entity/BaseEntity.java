package cn.eleven.app.entity;

import java.io.Serializable;
import java.util.Date;

abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1926585913692881820L;

    private Date regTime;// 注册时间

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
