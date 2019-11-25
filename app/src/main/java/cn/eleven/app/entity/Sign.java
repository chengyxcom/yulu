package cn.eleven.app.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sign {
    private Integer signId;
    private Integer uid;
    private Integer signTypeId;
    private Date signTime;

}
