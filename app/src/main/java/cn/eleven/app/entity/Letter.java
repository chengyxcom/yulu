package cn.eleven.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Letter implements Serializable {
    private Integer letterId;
    private Integer userLeft;
    private Integer userRight;
    private String letter;
    private Date letterTime;
    private Integer isSend;
    private Integer userRightDelete;
    private Integer userRightTop;
    private Integer isDelete;
    private Integer isRead;
}
