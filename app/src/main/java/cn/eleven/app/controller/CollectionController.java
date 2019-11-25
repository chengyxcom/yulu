package cn.eleven.app.controller;

import cn.eleven.app.entity.BigPost;
import cn.eleven.app.service.ICollectionService;
import cn.eleven.app.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("collection")
public class CollectionController extends BaseController {
    @Autowired
    private ICollectionService collectionService;

    @RequestMapping("collectionPost")
    public JsonResult<Integer> collectionPost(Integer postId, Integer uid) {
        //      Integer uid = getUidFromSession(session);
        Date date = new Date();
        collectionService.collectionPost(uid, postId, date);

        Integer count = collectionService.countPostCollection(postId);
        Integer result = collectionService.findPostId(postId);
        if (result == null) {
            collectionService.insertPostId(postId);
        } else {
            collectionService.updateCollectionCount(postId, count);
        }
        Integer counts = collectionService.countPostCollection(postId);
        return new JsonResult<>(SUCCESS, counts);
    }

    @RequestMapping("undoCollectionPost")
    public JsonResult<Integer> undoCollectionPost(Integer postId, Integer uid) {
        collectionService.undoCollectionPost(uid, postId);
        Integer count = collectionService.countPostCollection(postId);
        Integer result = collectionService.findPostId(postId);
        if (result == null) {
            collectionService.insertPostId(postId);
        } else {
            collectionService.updateCollectionCount(postId, count);
        }
        Integer counts = collectionService.countPostCollection(postId);
        return new JsonResult<>(SUCCESS, counts);
    }

    @RequestMapping("undoCollectionMusic")
    public JsonResult<Integer> undoCollectionMusic(Integer musicId, Integer uid) {
        collectionService.undoCollectionMusic(uid, musicId);
        Integer count = collectionService.countMusicCollection(musicId);
        Integer result = collectionService.findMusicId(musicId);
        if (result == null) {
            collectionService.insertMusicId(musicId);
        } else {
            collectionService.updateCollectionCount1(musicId, count);
        }
        Integer counts = collectionService.countMusicCollection(musicId);
        return new JsonResult<>(SUCCESS, counts);
    }

    @RequestMapping("undoCollectionStation")
    public JsonResult<Integer> undoCollectionStation(Integer stationId, Integer uid) {
        collectionService.undoCollectionStation(uid, stationId);
        Integer count = collectionService.countStationCollection(stationId);
        Integer result = collectionService.findStationId(stationId);
        if (result == null) {
            collectionService.insertStationId(stationId);
        } else {
            collectionService.updateCollectionCount2(stationId, count);
        }
        Integer counts = collectionService.countStationCollection(stationId);
        return new JsonResult<>(SUCCESS, counts);
    }

    @RequestMapping("collectionMusic")
    public JsonResult<Integer> collectionMusic(Integer musicId, Integer uid) {
        //     Integer uid = getUidFromSession(session);
        Date date = new Date();
        collectionService.collectionMusic(uid, musicId, date);

        Integer count = collectionService.countMusicCollection(musicId);
        Integer result = collectionService.findMusicId(musicId);
        if (result == null) {
            collectionService.insertMusicId(musicId);
        } else {
            collectionService.updateCollectionCount1(musicId, count);
        }
        Integer counts = collectionService.countMusicCollection(musicId);
        return new JsonResult<>(SUCCESS, counts);
    }

    @RequestMapping("collectionStation")
    public JsonResult<Integer> collectionStation(Integer stationId, Integer uid) {
        //   Integer uid = getUidFromSession(session);
        Date date = new Date();
        collectionService.collectionStation(uid, stationId, date);

        Integer count = collectionService.countStationCollection(stationId);
        Integer result = collectionService.findStationId(stationId);
        if (result == null) {
            collectionService.insertStationId(stationId);
        } else {
            collectionService.updateCollectionCount2(stationId, count);
        }
        Integer counts = collectionService.countStationCollection(stationId);
        return new JsonResult<>(SUCCESS, counts);
    }

    @RequestMapping("countPostCollection")
    public JsonResult<Integer> countPostCollection(Integer postId) {
        Integer count = collectionService.countPostCollection(postId);
        return new JsonResult<>(SUCCESS, count);
    }

    @RequestMapping("countMusicCollection")
    public JsonResult<Integer> countMusicCollection(Integer musicId) {
        Integer count = collectionService.countMusicCollection(musicId);
        return new JsonResult<>(SUCCESS, count);
    }

    @RequestMapping("countStationCollection")
    public JsonResult<Integer> countStationCollection(Integer stationId) {
        Integer count = collectionService.countStationCollection(stationId);
        return new JsonResult<>(SUCCESS, count);
    }

    @RequestMapping("collectionPostList")
    public JsonResult<List<BigPost>> collectionPostList(Integer uid) {
        List<BigPost> list = collectionService.collectionPostList(uid);
        return new JsonResult<>(SUCCESS, list);
    }
}
