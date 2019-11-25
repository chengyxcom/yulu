package cn.eleven.app.mapper;

import cn.eleven.app.entity.BigPost;

import java.util.Date;
import java.util.List;

public interface CollectionMapper {
    void collectionPost(Integer uid, Integer postId, Date date);

    void collectionMusic(Integer uid, Integer musicId, Date date);

    void collectionStation(Integer uid, Integer stationId, Date date);

    Integer countPostCollection(Integer postId);

    Integer findPostId(Integer postId);

    void insertPostId(Integer postId);

    void updateCollectionCount(Integer postId, Integer count);

    void undoCollectionPost(Integer uid, Integer postId);

    void undoCollectionMusic(Integer uid, Integer musicId);

    Integer countMusicCollection(Integer musicId);

    Integer findMusicId(Integer musicId);

    void insertMusicId(Integer musicId);

    void updateCollectionCount1(Integer musicId, Integer count);

    void undoCollectionStation(Integer uid, Integer stationId);

    Integer countStationCollection(Integer stationId);

    Integer findStationId(Integer stationId);

    void insertStationId(Integer stationId);

    void updateCollectionCount2(Integer stationId, Integer count);

    List<BigPost> collectionPostList(Integer uid);
}
