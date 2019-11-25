package cn.eleven.app.service;

import java.util.Date;

public interface ISupportService {
    Integer countSupport(Integer postId);

    void doSupport(Integer uid, Integer postId, Date supportPostTime);

    void undoSupport(Integer uid, Integer postId);

    void updateSupportCount(Integer postId, Integer count);

    Integer findPostId(Integer postId);

    void insertPostId(Integer postId);
}
