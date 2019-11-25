package cn.eleven.app.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer commentId;
    private Integer postId;
    private Integer commentUid;
    private String commentUsername;
    private Integer postUid;
    private String postUsername;
    private String comment;
    private Date commentTime;
    private Integer mark;
}
