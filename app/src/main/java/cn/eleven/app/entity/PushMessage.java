package cn.eleven.app.entity;

import lombok.Data;

@Data
public class PushMessage {
    /**
     * 当前登陆的用户
     */
    private String loginUser;
    /**
     * 当前登录用户的姓名
     */
    private String loginUserAvatar;
    /**
     * 接受消息的用户
     */
    private String receiveUser;
    /**
     * 推送的内容
     */
    private String content;

    public PushMessage(String loginUser, String receiveUser, String content) {
        this.loginUser = loginUser;
        this.receiveUser = receiveUser;
        this.content = content;
    }
}
