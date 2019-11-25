package cn.eleven.app.mapper;

import java.util.Date;

public interface SupportMapper {
    Integer countSupport(Integer postId);

    void doSupport(Integer uid, Integer postId, Date supportPostTime);

    void undoSupport(Integer uid, Integer postId);

    void updateSupportCount(Integer postId, Integer count);

    Integer findPostId(Integer postId);

    void insertPostId(Integer postId);
}
