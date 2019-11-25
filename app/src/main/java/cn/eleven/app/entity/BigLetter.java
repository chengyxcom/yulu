package cn.eleven.app.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BigLetter {
    private Integer letterId;
    private Integer uid;
    private String username;
    private String avatar;
    private String letter;
    private Date letterTime;
    private Integer userRightTop;
    private Integer isSend;
}
