package cn.eleven.app.service.impl;

import cn.eleven.app.entity.BigPost;
import cn.eleven.app.mapper.CollectionMapper;
import cn.eleven.app.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements ICollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public void collectionPost(Integer uid, Integer postId, Date date) {
        collectionMapper.collectionPost(uid, postId, date);
    }

    @Override
    public void collectionMusic(Integer uid, Integer musicId, Date date) {
        collectionMapper.collectionMusic(uid, musicId, date);
    }

    @Override
    public void collectionStation(Integer uid, Integer stationId, Date date) {
        collectionMapper.collectionStation(uid, stationId, date);
    }

    @Override
    public Integer countPostCollection(Integer postId) {
        return collectionMapper.countPostCollection(postId);
    }

    @Override
    public Integer findPostId(Integer postId) {
        return collectionMapper.findPostId(postId);
    }

    @Override
    public void insertPostId(Integer postId) {
        collectionMapper.insertPostId(postId);
    }

    @Override
    public void updateCollectionCount(Integer postId, Integer count) {
        collectionMapper.updateCollectionCount(postId, count);
    }

    @Override
    public void undoCollectionPost(Integer uid, Integer postId) {
        collectionMapper.undoCollectionPost(uid, postId);
    }

    @Override
    public void undoCollectionMusic(Integer uid, Integer musicId) {
        collectionMapper.undoCollectionMusic(uid, musicId);
    }

    @Override
    public Integer countMusicCollection(Integer musicId) {
        return collectionMapper.countMusicCollection(musicId);
    }

    @Override
    public Integer findMusicId(Integer musicId) {
        return collectionMapper.findMusicId(musicId);
    }

    @Override
    public void insertMusicId(Integer musicId) {
        collectionMapper.insertMusicId(musicId);
    }

    @Override
    public void updateCollectionCount1(Integer musicId, Integer count) {
        collectionMapper.updateCollectionCount1(musicId, count);
    }

    @Override
    public void undoCollectionStation(Integer uid, Integer stationId) {
        collectionMapper.undoCollectionStation(uid, stationId);
    }

    @Override
    public Integer countStationCollection(Integer stationId) {
        return collectionMapper.countStationCollection(stationId);
    }

    @Override
    public Integer findStationId(Integer stationId) {
        return collectionMapper.findStationId(stationId);
    }

    @Override
    public void insertStationId(Integer stationId) {
        collectionMapper.insertStationId(stationId);
    }

    @Override
    public void updateCollectionCount2(Integer stationId, Integer count) {
        collectionMapper.updateCollectionCount2(stationId, count);
    }

    @Override
    public List<BigPost> collectionPostList(Integer uid) {
        return collectionMapper.collectionPostList(uid);
    }
}
