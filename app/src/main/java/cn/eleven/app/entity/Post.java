package cn.eleven.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Post implements Serializable {
    private Integer postId;
    private Integer uid;
    private Integer postState;
    private Date postTime;
    private String postWords;
    private String postImg;
}
