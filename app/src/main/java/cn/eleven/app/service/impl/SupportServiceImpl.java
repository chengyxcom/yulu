package cn.eleven.app.service.impl;

import cn.eleven.app.mapper.SupportMapper;
import cn.eleven.app.service.ISupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SupportServiceImpl implements ISupportService {
    @Autowired
    private SupportMapper supportMapper;

    @Override
    public Integer countSupport(Integer postId) {
        Integer count = supportMapper.countSupport(postId);
        return count;
    }

    @Override
    public void doSupport(Integer uid, Integer postId, Date supportPostTime) {
        supportMapper.doSupport(uid, postId, supportPostTime);
    }

    @Override
    public void undoSupport(Integer uid, Integer postId) {
        supportMapper.undoSupport(uid, postId);
    }

    @Override
    public void updateSupportCount(Integer postId, Integer count) {
        supportMapper.updateSupportCount(postId, count);
    }

    @Override
    public Integer findPostId(Integer postId) {
        return supportMapper.findPostId(postId);
    }

    @Override
    public void insertPostId(Integer postId) {
        supportMapper.insertPostId(postId);
    }

}
