package cn.eleven.app.service;

import cn.eleven.app.entity.User;

import java.util.List;

public interface IFriendshipService {
    void saveFriendship(Integer userLeft, Integer userRight);

    void cancelFriendship(Integer userLeft, Integer userRight);

    Integer getRelationByUserRight(Integer userLeft, Integer userRight);

    void updateRelationLeft(Integer userLeft, Integer userRight, Integer relation);

    void updateRelationRight(Integer userLeft, Integer userRight, Integer relation);

    List<User> showMutualConcern(Integer userLeft);

    List<User> showMyConcern(Integer userLeft);

    List<User> showConcernAboutMe(Integer userRight);
}
