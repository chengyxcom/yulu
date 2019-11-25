package cn.eleven.app.service.impl;

import cn.eleven.app.entity.User;
import cn.eleven.app.mapper.FriendshipMapper;
import cn.eleven.app.service.IFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements IFriendshipService {
    @Autowired
    private FriendshipMapper friendshipMapper;


    @Override
    public void saveFriendship(Integer userLeft, Integer userRight) {
        friendshipMapper.saveFriendship(userLeft, userRight);
    }

    @Override
    public void cancelFriendship(Integer userLeft, Integer userRight) {
        friendshipMapper.cancelFriendship(userLeft, userRight);
    }

    @Override
    public Integer getRelationByUserRight(Integer userLeft, Integer userRight) {
        return friendshipMapper.getRelationByUserRight(userLeft, userRight);
    }

    @Override
    public void updateRelationLeft(Integer userLeft, Integer userRight, Integer relation) {
        friendshipMapper.updateRelationLeft(userLeft, userRight, relation);
    }

    @Override
    public void updateRelationRight(Integer userLeft, Integer userRight, Integer relation) {
        friendshipMapper.updateRelationRight(userLeft, userRight, relation);
    }

    @Override
    public List<User> showMutualConcern(Integer userLeft) {
        return friendshipMapper.showMutualConcern(userLeft);
    }

    @Override
    public List<User> showMyConcern(Integer userLeft) {
        return friendshipMapper.showMyConcern(userLeft);
    }

    @Override
    public List<User> showConcernAboutMe(Integer userRight) {
        return friendshipMapper.showConcernAboutMe(userRight);
    }
}
